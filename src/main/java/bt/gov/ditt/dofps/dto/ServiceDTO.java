package bt.gov.ditt.dofps.dto;

public class ServiceDTO implements java.io.Serializable {

    private String serviceId;
    private String serviceName;
    private String serviceCode;
    private PrivilegeDTO[] privilegeDTOList;

    public ServiceDTO() {
    }

    public ServiceDTO(String serviceId, String serviceName, String serviceCode, PrivilegeDTO[] privilegeDTOList) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceCode = serviceCode;
        this.privilegeDTOList = privilegeDTOList;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public PrivilegeDTO[] getPrivilegeDTOList() {
        return privilegeDTOList;
    }

    public void setPrivilegeDTOList(PrivilegeDTO[] privilegeDTOList) {
        this.privilegeDTOList = privilegeDTOList;
    }
}
