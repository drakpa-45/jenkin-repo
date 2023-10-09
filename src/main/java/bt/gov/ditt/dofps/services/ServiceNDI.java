package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.controller.publicController.ControllerCitizen;
import bt.gov.ditt.dofps.dto.NdiDTO;
import bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO;
import io.nats.client.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


/**
 * Created by Pema Drakpa on 1/24/2020.
 */
@Service
public class ServiceNDI implements IServiceNDI{

    @Autowired
    private APIService api;

    private final ControllerCitizen controllerCitizen;
    public Map<String, SseEmitter> emitters = new HashMap<>();
    @Autowired
    public ServiceNDI(ControllerCitizen controllerCitizen) {
        this.controllerCitizen = controllerCitizen;
    }

    @Override
    @Transactional
    public NdiDTO getPasswordLessLogin(HttpServletRequest request,HttpServletResponse responses) throws IOException, InterruptedException {
        HttpSession session = request.getSession();
        RestTemplate template = new RestTemplate();

        /*String requestJson = "{\n" +
                "  \"proofName\": \"verifyUser\",\n" +
                "  \"proofAttributes\": [\n" +
                "    {\n" +
                "      \"name\": \"Full Name\",\n" +
                "      \"restrictions\": [\n" +
                "        {\n" +
                "          \"schema_id\": \"2acnD7masG23MBd7nn4xna:2:Foundational ID:1.0.1\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"ID Number\",\n" +
                "      \"restrictions\": [\n" +
                "        {\n" +
                "          \"schema_id\": \"2acnD7masG23MBd7nn4xna:2:Foundational ID:1.0.1\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"forRealtionship\": \"\",\n" +
                "  \"threadId\": \"\"\n" +
                "}";*/

        String requestJson = "{\n" +
                "  \"proofName\": \"verifyUser\",\n" +
                "  \"proofAttributes\": [\n" +
                "    {\n" +
                "      \"name\": \"Full Name\",\n" +
                "      \"restrictions\": [\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"ID Number\",\n" +
                "      \"restrictions\": [\n" +
                "       ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"forRealtionship\": \"\"\n" +
                "}";

        String authCredentials="{\n" +
                "\"client_id\": \"3tq7ho23g5risndd90a76jre5f\",\n" +
                "\"client_secret\":\"111rvn964mucumr6c3qq3n2poilvq5v92bkjh58p121nmoverquh\",\n" +
                "\"grant_type\": \"client_credentials\"\n }";

        HttpHeaders authenticationHeader = new HttpHeaders();
        authenticationHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        authenticationHeader.setContentType(MediaType.APPLICATION_JSON);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String authenticationURL =resourceBundle.getString("authentication-service.endPointProofRequestURL");

        HttpEntity<String> authRequestEntity = new HttpEntity<>(authCredentials,authenticationHeader);
        ResponseEntity<String> authResponse = template.exchange(authenticationURL, HttpMethod.POST, authRequestEntity, String.class);

        JSONObject object = new JSONObject(authResponse.getBody());
        String token = object.getString("access_token");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ token);

        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String url =resourceBundle1.getString("getVerifyProofRequest.endPointProofRequestURL");

        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson,headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, requestEntity, String.class);

        System.out.println(response.getBody());
//       ObjectMapper objectMapper = new ObjectMapper();
        JSONObject obj = new JSONObject(response.getBody());
        JSONObject data = obj.getJSONObject("data");
        String proofRequestURL = data.get("proofRequestURL").toString();
        String proofRequestThreadId = data.get("proofRequestThreadId").toString();
        String deepLinkURL = data.get("deepLinkURL").toString();
        NdiDTO ndiDTO = new NdiDTO();

//         ndiDTO = objectMapper.readValue(response.getBody(), NdiDTO.class);

        ndiDTO.setRelationDID("");
        ndiDTO.setDeepLinkURL(deepLinkURL);
        ndiDTO.setProofRequestURL(proofRequestURL);//        ndiDTO.setInviteURL("inviteURL");
        ndiDTO.setProofRequestThreadId(proofRequestThreadId);
        getProofRequestStatus(proofRequestThreadId,request,responses,token); //listen to nats
//        getRelationshipReused(threadId);
return ndiDTO;
    }

    @Override
    public SseEmitter subscribe(String threadId) throws IOException {
        SseEmitter sseEmitter = new SseEmitter();
        sseEmitter.send(SseEmitter.event().name("INIT"));
        sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));
        sseEmitter.onError((e) -> emitters.remove(sseEmitter));
        sseEmitter.onTimeout(() -> emitters.remove(sseEmitter));
        emitters.put(threadId, sseEmitter);
        return sseEmitter;

    }

    private String getProofRequestStatus(String threadId, HttpServletRequest request,HttpServletResponse responses,String token) throws IOException, InterruptedException {
        AtomicReference<String> returnMessage = new AtomicReference<>("");
        HttpSession session = request.getSession();
        // String natsURL = "nats://18.142.249.134:4222";

        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String natsURL =resourceBundle1.getString("getNats.endPointNATSURL");

        String seedString = "SUAPXY7TJFUFE3IX3OEMSLE3JFZJ3FZZRSRSOGSG2ANDIFN77O2MIBHWUM";

        char[] seed = seedString.toCharArray();
//        byte[] seed = seedString.getBytes();
        NKey nKey = NKey.fromSeed(seed);
        String[] array = new String[]{natsURL};
        Options opts = new Options.Builder().servers(array).maxReconnects(-1).
                authHandler((new AuthHandler() {
                    @Override
                    public byte[] sign(byte[] bytes) {
                        try{
                            return nKey.sign(bytes);
                        } catch (GeneralSecurityException | IOException e) {
                            return null;
                        }
                    }

                    @Override
                    public char[] getID() {
                        try {
                            return nKey.getPublicKey();
                        } catch (GeneralSecurityException |IOException e) {
                            return null;
                        }
                    }

                    @Override
                    public char[] getJWT() {
                        return null;
                    }
                })).
                build();

        Connection connect = Nats.connect(opts);

        array = new String[]{natsURL};
        opts = new Options.Builder().servers(array).maxReconnects(-1).build();
        connect = Nats.connect(opts);
        Date expirationDate = null;
        if(connect.getStatus().toString().equalsIgnoreCase("CONNECTED")){
            // Set the expiration time
            long expirationTimeMillis = System.currentTimeMillis() + 180000; // 3 minutes
            expirationDate = new Date(expirationTimeMillis);
            System.out.println("Expiration Date: " + expirationDate);
        }else{
            System.out.println("Could not connect to nats: with exception");
        }
        Dispatcher dispatcher = null;
        final Date finalExpirationDate = expirationDate;
        final Dispatcher finalDispatcher = dispatcher;

        if(session.getAttribute("userdetail") !=null) {
            //redirect do system URL
            String redirectUrl = "http://localhost:8083/dofps/loginDashboard";
           // String redirectUrl = "http://brtp.citizenservices.gov.bt/loginDashboard";
            try {
                Desktop.getDesktop().browse(new URI(redirectUrl));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            dispatcher = connect.createDispatcher(message -> {
                String s = new String(message.getData(), StandardCharsets.UTF_8);
                JSONObject obj = new JSONObject(s);

                JSONObject data = obj.getJSONObject("data");
                JSONObject requestedPresentation = data.getJSONObject("requested_presentation");
                JSONObject selfAttested = requestedPresentation.getJSONObject("self_attested_attrs");
                JSONObject revealedAttr = requestedPresentation.getJSONObject("revealed_attrs");

                String fullName = revealedAttr.getJSONObject("Full Name").getString("value");
                String IDNumber = revealedAttr.getJSONObject("ID Number").getString("value");
              //  String mobileNumber = selfAttested.get("Mobile Number").toString();

//            JSONObject data = obj.getJSONObject("data");
//            String status = data.getString("status");
//            String relationshipDid = data.getString("relationDid");

                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("Authorization", "Bearer " + token);
                RestTemplate template = new RestTemplate();

                String url =resourceBundle1.getString("getVerifyProofRequest.endPointProofRequestURL");

                HttpEntity<String> requestEntity = new HttpEntity<>(headers);
                ResponseEntity<String> response = template.exchange(url+"?threadId="+threadId, HttpMethod.GET, requestEntity, String.class);

                JSONObject object = new JSONObject(response.getBody());
                JSONObject fetcheddata = object.getJSONObject("data");
                String status = fetcheddata.get("status").toString();

                if(status.equalsIgnoreCase("proofValidated")) {
                    UserRolePrivilegeDTO dto = new UserRolePrivilegeDTO();

                    // Check if the token has expired
                    boolean isExpired = finalExpirationDate.before(new Date());
                    if(isExpired==false){
                        //GENERATE TOKEN WITH CID AND SAVED
                        dto.setCid(IDNumber);
                        dto.setFullName(fullName);
                        //dto.setMobileNo(mobileNumber);
                        session.setAttribute("userdetail", dto);
                        //redirect do system URL
                        String redirectUrl = "http://localhost:8083/dofps/loginDashboard";
                       // String redirectUrl = "http://brtp.citizenservices.gov.bt/loginDashboard";
                        sendEvent("exists", threadId, "email");
                        if (Desktop.isDesktopSupported()) {
                            openURLUsingDesktop(redirectUrl);
                        } else {
                            openURLFallback(redirectUrl);
                        }
                    }else{
                        //redirect do system URL
                        String redirectUrl = "http://localhost:8083/dofps/";
                       //String redirectUrl = "http://brtp.citizenservices.gov.bt";
                        sendEvent("exists", threadId, "email");
                        try {
                            Desktop.getDesktop().browse(new URI(redirectUrl));
                            finalDispatcher.unsubscribe(threadId);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        dispatcher.subscribe(threadId);
        return returnMessage.toString();
    }

    private static void openURLUsingDesktop(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace(); // Handle or log any exceptions that occur while opening the URL.
        }
    }

    private static void openURLFallback(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();
        System.out.println("================= inside openURLFallback ========="+ os);
        try {
            if (os.contains("win")) {
                // Windows
                System.out.println("================= inside openURLFallback ========="+ os.contains("win") + " window");
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.contains("mac")) {
                // macOS
                System.out.println("================= inside openURLFallback ========="+ os.contains("win") + " macOS");
                rt.exec("open " + url);
            } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                // Linux/Unix
                System.out.println("================= inside openURLFallback ========="+ os.contains("win") + " Linux/Unix");
               // String[] browsers = {"xdg-open", "google-chrome", "firefox", "mozilla", "konqueror", "epiphany", "netscape"};
                String[] browsers = {"xdg-open", "google-chrome", "firefox"};
                boolean found = false;
                for (String browser : browsers) {
                   /* System.out.println("================= inside openURLFallback browser========="+ browser);
                    if (!found) {
                        System.out.println("================= inside openURLFallback !found ========="+ found);
                        found = rt.exec(new String[]{"which", browser}).waitFor() == 0;
                       // if (found) {
                            System.out.println("================= inside openURLFallback found ========="+ found);
                            rt.exec(new String[]{browser, url});
                       // rt.exec(new String[]{"/usr/bin/xdg-open", url});
                       // }
                    }*/
                    ProcessBuilder processBuilder = new ProcessBuilder(browser, url);
                    System.out.println("============= processBuilder============ " + processBuilder);
                    Process process = processBuilder.start();
                    // Read and print the process output (including errors).
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                        String line;
                        System.out.println("=================== reader ============"+reader);
                        while ((line = reader.readLine()) != null) {
                            System.out.println("=================== line ============"+line);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        System.out.println("=================== Exception ============"+e);
                    }
                    int exitCode = process.waitFor();
                    System.out.println("============= exitCode============ " + exitCode);

                    if (exitCode == 0) {
                        System.out.println("URL opened successfully in " + browser);
                        break; // If one browser succeeds, no need to try others.
                    }
                }
            } else {
                System.out.println("Fallback mechanism not available for the current operating system.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); // Handle or log any exceptions that occur while opening the URL.
            System.out.println("================== InterruptedException ================== " +e);
        }
    }

    public void sendEvent(String value, String pThId, String relationDID) {
        String jsonObject = new JSONObject().put("status", value).put("pThID", pThId).put("relationDID", relationDID).toString();

        SseEmitter sseEmitter = emitters.get(pThId);
        try {
            if (sseEmitter != null) {
                sseEmitter.send(SseEmitter.event().name("proofSuccessEvent").data(jsonObject));
                sseEmitter.onTimeout(sseEmitter::complete);
            }
        } catch (IOException e) {
            emitters.remove(sseEmitter);
            throw new RuntimeException(e);
            }
        }
}