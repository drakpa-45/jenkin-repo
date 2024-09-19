package bt.gov.ditt.dofps.controller.publicController;

import io.nats.client.*;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by deepa on 7/7/2022.
 */
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/ndi")
public class ControllerNDI {
    String status = "";
    @RequestMapping(value = "/natsURL", method = RequestMethod.GET)
    public ResponseEntity<String> natsURL(HttpServletRequest request, @RequestParam("threadId") String threadId, @RequestParam("token") String token) throws IOException, InterruptedException {
        HttpSession session = request.getSession();
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        //        byte[] seed = seedString.getBytes();
        String natsURL =resourceBundle1.getString("getNats.endPointNATSURL");

        String seedString = "SUAOCNCDWVZGDKIT63PAJVGCK5O6GMBMEJG3S52LZZILDNP4LTVPNN5FPE";



        Dispatcher dispatcher = null;
        Date expirationDate = null;
        final Date finalExpirationDate = expirationDate;

        char[] seed = seedString.toCharArray();

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



        if(connect.getStatus().toString().equalsIgnoreCase("CONNECTED")){
            // Set the expiration time
            long expirationTimeMillis = System.currentTimeMillis() + 180000; // 3 minutes
            expirationDate = new Date(expirationTimeMillis);
            System.out.println("Expiration Date: " + expirationDate);
        }else{
            System.out.println("Could not connect to nats: with exception");
        }


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
                status = fetcheddata.get("status").toString();

                // Create a JSON response with the status
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("status", status);

                ResponseEntity.ok(jsonResponse.toString());
            });
            return ResponseEntity.ok("Processing started");
        }
        dispatcher.subscribe(threadId);
        return null;
    }

    // Create a dispatcher with a callback function
    @GetMapping("/startProcessing")
    @ResponseBody
    public ResponseEntity<String> startProcessing(HttpServletRequest request, @RequestParam("threadId") String threadId,@RequestParam("token") String token) {
        HttpSession session = request.getSession();
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        //        byte[] seed = seedString.getBytes();
        String natsURL =resourceBundle1.getString("getNats.endPointNATSURL");

        String seedString = "SUAOCNCDWVZGDKIT63PAJVGCK5O6GMBMEJG3S52LZZILDNP4LTVPNN5FPE";



        Dispatcher dispatcher = null;
        Date expirationDate = null;
        final Date finalExpirationDate = expirationDate;

        char[] seed = seedString.toCharArray();

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

        Connection connect = null;
        try {
            connect = Nats.connect(opts);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if(connect.getStatus().toString().equalsIgnoreCase("CONNECTED")){
            // Set the expiration time
            long expirationTimeMillis = System.currentTimeMillis() + 180000; // 3 minutes
            expirationDate = new Date(expirationTimeMillis);
            System.out.println("Expiration Date: " + expirationDate);
        }else{
            System.out.println("Could not connect to nats: with exception");
        }


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
                status = fetcheddata.get("status").toString();

                // Create a JSON response with the status
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("status", status);

                ResponseEntity.ok(jsonResponse.toString());
            });
        }
        dispatcher.subscribe(threadId);
        return ResponseEntity.ok("Processing started"); // Acknowledgment response
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        if (status != null) {
            return ResponseEntity.ok(status.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Status not available");
        }
    }
}
