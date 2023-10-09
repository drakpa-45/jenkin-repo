package bt.gov.ditt.dofps.dto;

import java.util.Arrays;

/**
 * Created by Pema Drakpa on 1/24/2020.
 */
public class Service implements java.io.Serializable  {
    private static final long serialVersionUID = -4587180296523649200L;

    private String serviceId;
    private String serviceName;
    private String serviceCode;
    private Privilege[] privileges;

    public Service() {
    }

    public Service(String serviceId, String serviceName,
                   String serviceCode, Privilege[] privileges) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceCode = serviceCode;
        this.privileges = privileges;
    }

    /**
     * Gets the serviceId value for this Service.
     *
     * @return serviceId
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * Sets the serviceId value for this Service.
     *
     * @param serviceId
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * Gets the serviceName value for this Service.
     *
     * @return serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Sets the serviceName value for this Service.
     *
     * @param serviceName
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * Gets the serviceCode value for this Service.
     *
     * @return serviceCode
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Sets the serviceCode value for this Service.
     *
     * @param serviceCode
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * Gets the privileges value for this Service.
     *
     * @return privileges
     */
    public Privilege[] getPrivileges() {
        return privileges;
    }

    /**
     * Sets the privileges value for this Service.
     *
     * @param privileges
     */
    public void setPrivileges(Privilege[] privileges) {
        this.privileges = privileges;
    }

    public Privilege getPrivileges(int i) {
        return this.privileges[i];
    }

    public void setPrivileges(int i, Privilege _value) {
        this.privileges[i] = _value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Service [serviceId=");
        builder.append(serviceId);
        builder.append(", serviceName=");
        builder.append(serviceName);
        builder.append(", serviceCode=");
        builder.append(serviceCode);
        builder.append(", privileges=");
        builder.append(Arrays.toString(privileges));
        builder.append("]");
        return builder.toString();
    }
}
