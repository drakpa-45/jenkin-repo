package bt.gov.ditt.dofps.entitiy;

import javax.persistence.*;

/**
 * Created by Deepak on 12/18/2020.
 */
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String access_token;
    @Column
    private String scope;
    @Column
    private String token_type;
    @Column
    private int expires_in;
    @Column
    private long created_on;

    public long getCreated_on() {
        return created_on;
    }
    public void setCreated_on(long l) {
        this.created_on = l;
    }
    public String getAccess_token() {
        return access_token;
    }
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getToken_type() {
        return token_type;
    }
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
    public int getExpires_in() {
        return expires_in;
    }
    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}