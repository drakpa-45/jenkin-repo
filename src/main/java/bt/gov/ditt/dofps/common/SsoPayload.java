package bt.gov.ditt.dofps.common;

/**
 * Created by Drakpa on 10/4/2023.
 */

public class SsoPayload {

    String access_token;
    String refresh_token;
    String scope;
    String id_token;
    String token_type;
    long expires_in;
    public String getAccess_token() {
        return access_token;
    }
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    public String getRefresh_token() {
        return refresh_token;
    }
    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getId_token() {
        return id_token;
    }
    public void setId_token(String id_token) {
        this.id_token = id_token;
    }
    public String getToken_type() {
        return token_type;
    }
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
    public long getExpires_in() {
        return expires_in;
    }
    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }


}