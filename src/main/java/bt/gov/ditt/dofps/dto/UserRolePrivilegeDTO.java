package bt.gov.ditt.dofps.dto;


/**
 * Created by Pema Drakpa on 1/24/2020.
 */
public class UserRolePrivilegeDTO implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6647518571149081230L;
    private Role currentRole;
    private Role[] roles;
    private Jurisdiction[] jurisdictions;
    private String fullName;
    private String cid;
    private String mobileNo;
    private String emailId;
    private String userType;
    private String telephoneNo;
    private String userID;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public Role getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(Role currentRole) {
        this.currentRole = currentRole;
    }

    public Role[] getRoles() {
        return roles;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }

    public Jurisdiction[] getJurisdictions() {
        return jurisdictions;
    }

    public void setJurisdictions(Jurisdiction[] jurisdictions) {
        this.jurisdictions = jurisdictions;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}

