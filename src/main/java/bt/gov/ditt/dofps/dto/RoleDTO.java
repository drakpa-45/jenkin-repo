package bt.gov.ditt.dofps.dto;

import java.io.Serializable;

public class RoleDTO implements Serializable {

    private String roleId;
    private String roleName;
    private String roleCode;
    private ServiceDTO[] serviceDTOList;

    public RoleDTO() {
    }

    public RoleDTO(String roleId, String roleName, String roleCode, ServiceDTO[] serviceDTOList) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleCode = roleCode;
        this.serviceDTOList = serviceDTOList;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public ServiceDTO[] getServiceDTOList() {
        return serviceDTOList;
    }

    public void setServiceDTOList(ServiceDTO[] serviceDTOList) {
        this.serviceDTOList = serviceDTOList;
    }
}
