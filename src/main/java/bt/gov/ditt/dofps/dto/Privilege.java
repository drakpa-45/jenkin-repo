package bt.gov.ditt.dofps.dto;

/**
 * Created by Pema Drakpa on 1/24/2020.
 */
public class Privilege implements java.io.Serializable  {
    private static final long serialVersionUID = 2300132684774850799L;
    private String privilegeId;
    private String privilegeName;
    private String privilegeCode;

    public Privilege() {
    }

    public Privilege(String privilegeId,
                     String privilegeName, String privilegeCode) {
        this.privilegeId = privilegeId;
        this.privilegeName = privilegeName;
        this.privilegeCode = privilegeCode;
    }

    /**
     * Gets the privilegeId value for this Privilege.
     *
     * @return privilegeId
     */
    public String getPrivilegeId() {
        return privilegeId;
    }

    /**
     * Sets the privilegeId value for this Privilege.
     *
     * @param privilegeId
     */
    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId;
    }

    /**
     * Gets the privilegeName value for this Privilege.
     *
     * @return privilegeName
     */
    public String getPrivilegeName() {
        return privilegeName;
    }

    /**
     * Sets the privilegeName value for this Privilege.
     *
     * @param privilegeName
     */
    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    /**
     * Gets the privilegeCode value for this Privilege.
     *
     * @return privilegeCode
     */
    public String getPrivilegeCode() {
        return privilegeCode;
    }

    /**
     * Sets the privilegeCode value for this Privilege.
     *
     * @param privilegeCode
     */
    public void setPrivilegeCode(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }

    private Object __equalsCalc = null;

    @SuppressWarnings("unused")
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Privilege))
            return false;
        Privilege other = (Privilege) obj;
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true
                && ((this.privilegeId == null && other.getPrivilegeId() == null) || (this.privilegeId != null && this.privilegeId
                .equals(other.getPrivilegeId())))
                && ((this.privilegeName == null && other.getPrivilegeName() == null) || (this.privilegeName != null && this.privilegeName
                .equals(other.getPrivilegeName())))
                && ((this.privilegeCode == null && other.getPrivilegeCode() == null) || (this.privilegeCode != null && this.privilegeCode
                .equals(other.getPrivilegeCode())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;

    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getPrivilegeId() != null) {
            _hashCode += getPrivilegeId().hashCode();
        }
        if (getPrivilegeName() != null) {
            _hashCode += getPrivilegeName().hashCode();
        }
        if (getPrivilegeCode() != null) {
            _hashCode += getPrivilegeCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Privilege [privilegeId=");
        builder.append(privilegeId);
        builder.append(", privilegeName=");
        builder.append(privilegeName);
        builder.append(", privilegeCode=");
        builder.append(privilegeCode);
        builder.append(", __equalsCalc=");
        builder.append(__equalsCalc);
        builder.append(", __hashCodeCalc=");
        builder.append(__hashCodeCalc);
        builder.append("]");
        return builder.toString();
    }
}
