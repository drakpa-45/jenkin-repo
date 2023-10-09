package bt.gov.ditt.dofps.dto;

public class PrivilegeDTO implements java.io.Serializable {

    private String privilegeId;
    private String privilegeName;
    private String privilegeCode;

    public PrivilegeDTO() {
    }

    public PrivilegeDTO(String privilegeId, String privilegeName, String privilegeCode) {
        this.privilegeId = privilegeId;
        this.privilegeName = privilegeName;
        this.privilegeCode = privilegeCode;
    }

    public String getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeCode() {
        return privilegeCode;
    }

    public void setPrivilegeCode(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }
}
