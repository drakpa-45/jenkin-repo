package bt.gov.ditt.dofps.dto;

import java.util.Arrays;

/**
 * Created by Pema Drakpa on 1/24/2020.
 */
public class Role implements java.io.Serializable {
    private static final long serialVersionUID = 6010750021611206985L;

    private String roleId;
    private String roleName;
    private String roleCode;
    private Service[] services;

    public Role() {
    }

    public Role(String roleId, String roleName, String roleCode, Service[] services) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleCode = roleCode;
        this.services = services;
    }

    /**
     * Gets the roleId value for this Role.
     *
     * @return roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * Sets the roleId value for this Role.
     *
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * Gets the roleName value for this Role.
     *
     * @return roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the roleName value for this Role.
     *
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Gets the roleCode value for this Role.
     *
     * @return roleCode
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * Sets the roleCode value for this Role.
     *
     * @param roleCode
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * Gets the services value for this Role.
     *
     * @return services
     */
    public Service[] getServices() {
        return services;
    }

    /**
     * Sets the services value for this Role.
     *
     * @param services
     */
    public void setServices(Service[] services) {
        this.services = services;
    }

    public Service getServices(int i) {
        return this.services[i];
    }

    public void setServices(int i, Service _value) {
        this.services[i] = _value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Role [roleId=");
        builder.append(roleId);
        builder.append(", roleName=");
        builder.append(roleName);
        builder.append(", roleCode=");
        builder.append(roleCode);
        builder.append(", services=");
        builder.append(Arrays.toString(services));
        builder.append("]");
        return builder.toString();
    }
}
