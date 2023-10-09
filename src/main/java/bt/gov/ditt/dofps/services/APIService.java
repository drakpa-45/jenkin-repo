package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.dao.TokenDAO;
import bt.gov.ditt.dofps.entitiy.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Base64;
import java.util.ResourceBundle;

/**
 * Created by Deepak on 12/18/2020.
 */
@Service
@Transactional
public class APIService {
    @Autowired
    private TokenDAO tokenRepository;

/*
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }*/

  /*  @Autowired
    private RestTemplate restTemplate;*/

    public Token getApplicationToken() throws ParseException {
        Token token = tokenRepository.findToken();

        if(null == token){
            token = generateToken();
        } else {
            float secondsElapsed = (System.currentTimeMillis() - token.getCreated_on()) / 1000F;
            if(secondsElapsed > token.getExpires_in()) {
                tokenRepository.deleteAll(token);
                token = generateToken();
            } else {
                return token;
            }
        }
        return token;
    }

    private Token generateToken() {
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String dataHubEndPointUrl =resourceBundle1.getString("getDatahubToken.endPointURL");
        String consumerKey = resourceBundle1.getString("CONSUMER.KEY");
        String consumerSecret = resourceBundle1.getString("CONSUMER.SECRET");

        RestTemplate restTemplate = new RestTemplate();
        Token token = new Token();

        String authStringEnc = Base64.getEncoder().encodeToString((consumerKey+":"+consumerSecret).getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic "+authStringEnc);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<Token> response = restTemplate.exchange(dataHubEndPointUrl, HttpMethod.POST, request, Token.class);

        token.setAccess_token(response.getBody().getAccess_token());
        token.setExpires_in(response.getBody().getExpires_in());
        token.setScope(response.getBody().getScope());
        token.setToken_type(response.getBody().getToken_type());
        token.setCreated_on(System.currentTimeMillis());
        tokenRepository.save(token);
        return token;
    }

   /* public JsonNode getSsoToken(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String consumerKey = resourceBundle.getString("CONSUMER.KEY");
        String consumerSecret = resourceBundle.getString("CONSUMER.SECRET");
        String dittSsoBaseUri = resourceBundle.getString("ditt.sso.base.uri");
        String tokenEndPoint = resourceBundle.getString("ditt.sso.token.endpoint");
        String redirectUri = resourceBundle.getString("dofps.login.redirect_uri");

        String _baseCode = Base64.getEncoder().encodeToString((consumerKey + ":" + consumerSecret).getBytes());

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());

        String userAgent = request.getHeader("User-Agent");

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", userAgent);
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + _baseCode);

        String access_token_url = dittSsoBaseUri.concat(tokenEndPoint);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "authorization_code");
        form.add("code", request.getParameter("code").trim());
        form.add("redirect_uri", redirectUri);
        form.add("scope", "openid");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(form, headers);

        try {
            ResponseEntity<JsonNode> apiResponse = restTemplate.exchange(access_token_url, HttpMethod.POST, httpEntity, JsonNode.class);
            System.out.println("################## apiResponse #############3"+apiResponse.getBody());
            return apiResponse.getBody();
        } catch (HttpStatusCodeException e) {
            // Handle exceptions as needed
            e.printStackTrace();
            return null;
        }
    }*/
}
