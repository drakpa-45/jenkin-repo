package bt.gov.ditt.dofps.entitiy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Pema Drakpa on 1/31/2020.
 */
@Entity
@Table(name="t_payment_info")
public class PaymentEntity{
    @Id
    @Column(name="Application_Number")
    private String applicationNumber;
    @Column(name="Service_Id")
    private int serviceId;
    @Column(name="User_Id")
    private String userId;
    @Column(name="CC_Id")
    private String ccId;
    @Column(name="Juri_Type_Id")
    private int juriTypeId;
    @Column(name="payment_Jurisdiction")
    private String paymentJuri;
    @Column(name="Amount_Collected")
    private int amountCollected;
    @Column(name="payment_receipt_no")
    private String paymentReceiptNo;
    @Column(name="DateOfReceipt")
    private Date dateOfReceipt;
    @Column(name="Payment_Mode")
    private String modeOfPayment;
    @Column(name="Sync_With_G2C")
    private String syncWithG2C;

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCcId() {
        return ccId;
    }

    public void setCcId(String ccId) {
        this.ccId = ccId;
    }

    public int getJuriTypeId() {
        return juriTypeId;
    }

    public void setJuriTypeId(int juriTypeId) {
        this.juriTypeId = juriTypeId;
    }

    public String getPaymentJuri() {
        return paymentJuri;
    }

    public void setPaymentJuri(String paymentJuri) {
        this.paymentJuri = paymentJuri;
    }

    public int getAmountCollected() {
        return amountCollected;
    }

    public void setAmountCollected(int amountCollected) {
        this.amountCollected = amountCollected;
    }

    public String getPaymentReceiptNo() {
        return paymentReceiptNo;
    }

    public void setPaymentReceiptNo(String paymentReceiptNo) {
        this.paymentReceiptNo = paymentReceiptNo;
    }

    public Date getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(Date dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public String getSyncWithG2C() {
        return syncWithG2C;
    }

    public void setSyncWithG2C(String syncWithG2C) {
        this.syncWithG2C = syncWithG2C;
    }
}
