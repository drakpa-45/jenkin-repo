package bt.gov.ditt.dofps.entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Pema Drakpa on 1/17/2020.
 */
@Entity
@Table(name = "t_fp_application")
public class PersonalEntity implements Serializable{
    @Id
    @Column(name="Application_Number")
    private String application_Number;
    @Column(name="Application_Type")
    private String application_Type;
    @Column(name="CID_Number")
    private String cid_Number;
    @Column(name="Full_Name")
    private String name;
    @Column(name="house_Hold_No")
    private String house_Hold_No;
    @Column(name="House_No")
    private String house_No;
    @Column(name="NWFP_Management_Group_Sl_No")
    private int nwfp_Management_Group_Sl_No;
    @Column(name="Applicant_Area_Tag")
    private String applicant_Area_Tag;
    @Column(name = "Request_Service_Id")
    private int request_Service_Id;
    @Column(name = "App_Submission_Date")
    private Date app_Submission_Date;
    @Column(name="App_Verification_Date")
    private Date app_Verification_Date;
    @Column(name = "App_Approval_Date")
    private Date app_Approval_Date;
    @Column(name = "App_Rejection_Date")
    private Date app_Rejection_Date;
    @Column(name = "Rejection_Reason")
    private String rejection_Reason;
    @Column(name = "Phone_Number")
    private String phone_Number;
    @Column(name = "Mobile_Number")
    private String mobile_Number;
    @Column(name = "AlternativeNumberRelation")
    private String AlternativeNumberRelation;
    @Column(name = "Division_Park_Id")
    private int division_Park_Id;
    @Column(name = "Division_Park_Id_1")
    private int division_Park_Id_1;
    @Column(name = "Geog_No")
    private int geog_No;
    @Column(name = "Register_Geog")
    private String register_Geog;
    @Column(name = "Cons_Loc_Village_Serial_No")
    private int cons_Loc_Village_Serial_No;
    @Column(name = "Cons_Approval_No")
    private String cons_Approval_No;
    @Column(name = "Cons_TypeFP_Details")
    private String cons_TypeFP_Details;
    @Column(name = "Cons_Type")
    private String cons_Type;
    @Column(name = "Certify_Tag")
    private String certify_Tag;
    @Column(name = "Inspection_Remark")
    private String inspection_Remark;
    @Column(name = "Mode_of_Swing_Id")
    private int mode_of_Swing_Id;
    @Column(name = "Roofing_Type")
    private String roofing_Type;
    @Column(name = "Dealing_Date")
    private Date dealing_Date;
    @Column(name = "Dealing_Division_Park")
    private String dealing_Division_Park;
    @Column(name = "Allot_Range_Officer")
    private String allot_Range_Officer;
    @Column(name = "Allot_Date")
    private Date allot_Date;
    @Column(name = "Allot_Area")
    private String allot_Area;
    @Column(name = "Member_of_Forest_community")
    private String member_of_Forest_community;
    @Column(name = "Total_Royalty")
    private Float total_Royalty;
    @Column(name = "Permit_Fee")
    private int permit_Fee;
    @Column(name = "Net_Royalty")
    private Float net_Royalty;
    @Column(name = "Dealing_Officer_Remarks")
    private String dealing_Officer_Remarks;
    @Column(name = "Div_Change_Reason")
    private String div_Change_Reason;
    @Column(name = "Verified_Record_Tag")
    private String verified_Record_Tag;
    @Column(name = "Head_of_Gung")
    private String head_of_Gung;
    @Column(name = "Construction_Location")
    private String construction_Location;
    @Column(name = "Service_Fees")
    private int service_Fees;
    @Column(name = "Application_Fees")
    private int application_Fees;
    @Column(name = "Census_Reg_Date")
    private Date census_Reg_Date;
    @Column(name = "NWFP_App_Type")
    private String nwfp_App_Type;
    @Column(name = "Plot_No")
    private String plot_No;
    @Column(name = "Approval_Remarks")
    private String approval_Remarks;
    @Column(name = "Village_Serial_No")
    private int village_Serial_No;
    @Column(name = "Receipt_Number")
    private String receipt_Number;
    @Column(name = "Receipt_Date")
    private Date receipt_Date;
    @Column(name = "Sync_G2C")
    private int sync_G2C;
    @Column(name = "Storey_House")
    private String storey_House;
    @Column(name = "Dzongkhag_Name")
    private String dzongkhag_Name;
    @Column(name="Gewog_Name")
    private String gewog_Name;
    @Column(name = "Village_Name")
    private String village_Name;
    @Column(name = "GenderType")
    private String genderType;
    @Column(name = "Village_Id")
    private Integer village_Id;
    @Column(name = "Private_LandForm_Remarks")
    private String private_LandForm_Remarks;
    @Column(name = "Inspection_Date")
    private Date Inspection_Date;
    @Column(name = "Ranger_Remark_PRL")
    private String Ranger_Remark_PRL;
    @Column(name = "Previous_Application_No")
    private String previous_application_Number;
    @Column (name = "Claiming_status")
    private String claimStatus;
    @Column (name = "Reclaiming_Timber_Remark_CC")
    private String cc_Reclaim_Remarks;
    @Column (name = "Peg_No")
    private String peg_No;
    @Column (name = "Payment_Status")
    private String payment_Status;
    @Column (name = "Thram_No")
    private int thram_No;

    public String getApplication_Number() {
        return application_Number;
    }

    public void setApplication_Number(String application_Number) {
        this.application_Number = application_Number;
    }

    public String getApplication_Type() {
        return application_Type;
    }

    public void setApplication_Type(String application_Type) {
        this.application_Type = application_Type;
    }

    public String getCid_Number() {
        return cid_Number;
    }

    public void setCid_Number(String cid_Number) {
        this.cid_Number = cid_Number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHouse_Hold_No() {
        return house_Hold_No;
    }

    public void setHouse_Hold_No(String house_Hold_No) {
        this.house_Hold_No = house_Hold_No;
    }

    public String getHouse_No() {
        return house_No;
    }

    public void setHouse_No(String house_No) {
        this.house_No = house_No;
    }

    public int getNwfp_Management_Group_Sl_No() {
        return nwfp_Management_Group_Sl_No;
    }

    public void setNwfp_Management_Group_Sl_No(int nwfp_Management_Group_Sl_No) {
        this.nwfp_Management_Group_Sl_No = nwfp_Management_Group_Sl_No;
    }

    public String getApplicant_Area_Tag() {
        return applicant_Area_Tag;
    }

    public void setApplicant_Area_Tag(String applicant_Area_Tag) {
        this.applicant_Area_Tag = applicant_Area_Tag;
    }

    public int getRequest_Service_Id() {
        return request_Service_Id;
    }

    public void setRequest_Service_Id(int request_Service_Id) {
        this.request_Service_Id = request_Service_Id;
    }

    public Date getApp_Submission_Date() {
        return app_Submission_Date;
    }

    public void setApp_Submission_Date(Date app_Submission_Date) {
        this.app_Submission_Date = app_Submission_Date;
    }

    public Date getApp_Verification_Date() {
        return app_Verification_Date;
    }

    public void setApp_Verification_Date(Date app_Verification_Date) {
        this.app_Verification_Date = app_Verification_Date;
    }

    public Date getApp_Approval_Date() {
        return app_Approval_Date;
    }

    public void setApp_Approval_Date(Date app_Approval_Date) {
        this.app_Approval_Date = app_Approval_Date;
    }

    public Date getApp_Rejection_Date() {
        return app_Rejection_Date;
    }

    public void setApp_Rejection_Date(Date app_Rejection_Date) {
        this.app_Rejection_Date = app_Rejection_Date;
    }

    public String getRejection_Reason() {
        return rejection_Reason;
    }

    public void setRejection_Reason(String rejection_Reason) {
        this.rejection_Reason = rejection_Reason;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }

    public String getMobile_Number() {
        return mobile_Number;
    }

    public void setMobile_Number(String mobile_Number) {
        this.mobile_Number = mobile_Number;
    }

    public String getAlternativeNumberRelation() {
        return AlternativeNumberRelation;
    }

    public void setAlternativeNumberRelation(String alternativeNumberRelation) {
        this.AlternativeNumberRelation = alternativeNumberRelation;
    }

    public int getDivision_Park_Id() {
        return division_Park_Id;
    }

    public void setDivision_Park_Id(int division_Park_Id) {
        this.division_Park_Id = division_Park_Id;
    }

    public int getDivision_Park_Id_1() {
        return division_Park_Id_1;
    }

    public void setDivision_Park_Id_1(int division_Park_Id_1) {
        this.division_Park_Id_1 = division_Park_Id_1;
    }

    public int getGeog_No() {
        return geog_No;
    }

    public void setGeog_No(int geog_No) {
        this.geog_No = geog_No;
    }

    public String getRegister_Geog() {
        return register_Geog;
    }

    public void setRegister_Geog(String register_Geog) {
        this.register_Geog = register_Geog;
    }

    public int getCons_Loc_Village_Serial_No() {
        return cons_Loc_Village_Serial_No;
    }

    public void setCons_Loc_Village_Serial_No(int cons_Loc_Village_Serial_No) {
        this.cons_Loc_Village_Serial_No = cons_Loc_Village_Serial_No;
    }

    public String getCons_Approval_No() {
        return cons_Approval_No;
    }

    public void setCons_Approval_No(String cons_Approval_No) {
        this.cons_Approval_No = cons_Approval_No;
    }

    public String getCons_TypeFP_Details() {
        return cons_TypeFP_Details;
    }

    public void setCons_TypeFP_Details(String cons_TypeFP_Details) {
        this.cons_TypeFP_Details = cons_TypeFP_Details;
    }

    public String getCons_Type() {
        return cons_Type;
    }

    public void setCons_Type(String cons_Type) {
        this.cons_Type = cons_Type;
    }

    public String getCertify_Tag() {
        return certify_Tag;
    }

    public void setCertify_Tag(String certify_Tag) {
        this.certify_Tag = certify_Tag;
    }

    public String getInspection_Remark() {
        return inspection_Remark;
    }

    public void setInspection_Remark(String inspection_Remark) {
        this.inspection_Remark = inspection_Remark;
    }

    public int getMode_of_Swing_Id() {
        return mode_of_Swing_Id;
    }

    public void setMode_of_Swing_Id(int mode_of_Swing_Id) {
        this.mode_of_Swing_Id = mode_of_Swing_Id;
    }

    public String getRoofing_Type() {
        return roofing_Type;
    }

    public void setRoofing_Type(String roofing_Type) {
        this.roofing_Type = roofing_Type;
    }

    public Date getDealing_Date() {
        return dealing_Date;
    }

    public void setDealing_Date(Date dealing_Date) {
        this.dealing_Date = dealing_Date;
    }

    public String getDealing_Division_Park() {
        return dealing_Division_Park;
    }

    public void setDealing_Division_Park(String dealing_Division_Park) {
        this.dealing_Division_Park = dealing_Division_Park;
    }

    public String getAllot_Range_Officer() {
        return allot_Range_Officer;
    }

    public void setAllot_Range_Officer(String allot_Range_Officer) {
        this.allot_Range_Officer = allot_Range_Officer;
    }

    public Date getAllot_Date() {
        return allot_Date;
    }

    public void setAllot_Date(Date allot_Date) {
        this.allot_Date = allot_Date;
    }

    public String getAllot_Area() {
        return allot_Area;
    }

    public void setAllot_Area(String allot_Area) {
        this.allot_Area = allot_Area;
    }

    public String getMember_of_Forest_community() {
        return member_of_Forest_community;
    }

    public void setMember_of_Forest_community(String member_of_Forest_community) {
        this.member_of_Forest_community = member_of_Forest_community;
    }

    public Float getTotal_Royalty() {
        return total_Royalty;
    }

    public void setTotal_Royalty(Float total_Royalty) {
        this.total_Royalty = total_Royalty;
    }

    public int getPermit_Fee() {
        return permit_Fee;
    }

    public void setPermit_Fee(int permit_Fee) {
        this.permit_Fee = permit_Fee;
    }

    public Float getNet_Royalty() {
        return net_Royalty;
    }

    public void setNet_Royalty(Float net_Royalty) {
        this.net_Royalty = net_Royalty;
    }

    public String getDealing_Officer_Remarks() {
        return dealing_Officer_Remarks;
    }

    public void setDealing_Officer_Remarks(String dealing_Officer_Remarks) {
        this.dealing_Officer_Remarks = dealing_Officer_Remarks;
    }

    public String getDiv_Change_Reason() {
        return div_Change_Reason;
    }

    public void setDiv_Change_Reason(String div_Change_Reason) {
        this.div_Change_Reason = div_Change_Reason;
    }

    public String getVerified_Record_Tag() {
        return verified_Record_Tag;
    }

    public void setVerified_Record_Tag(String verified_Record_Tag) {
        this.verified_Record_Tag = verified_Record_Tag;
    }

    public String getHead_of_Gung() {
        return head_of_Gung;
    }

    public void setHead_of_Gung(String head_of_Gung) {
        this.head_of_Gung = head_of_Gung;
    }

    public String getConstruction_Location() {
        return construction_Location;
    }

    public void setConstruction_Location(String construction_Location) {
        this.construction_Location = construction_Location;
    }

    public int getService_Fees() {
        return service_Fees;
    }

    public void setService_Fees(int service_Fees) {
        this.service_Fees = service_Fees;
    }

    public int getApplication_Fees() {
        return application_Fees;
    }

    public void setApplication_Fees(int application_Fees) {
        this.application_Fees = application_Fees;
    }

    public Date getCensus_Reg_Date() {
        return census_Reg_Date;
    }

    public void setCensus_Reg_Date(Date census_Reg_Date) {
        this.census_Reg_Date = census_Reg_Date;
    }

    public String getNwfp_App_Type() {
        return nwfp_App_Type;
    }

    public void setNwfp_App_Type(String nwfp_App_Type) {
        this.nwfp_App_Type = nwfp_App_Type;
    }

    public String getPlot_No() {
        return plot_No;
    }

    public void setPlot_No(String plot_No) {
        this.plot_No = plot_No;
    }

    public String getApproval_Remarks() {
        return approval_Remarks;
    }

    public void setApproval_Remarks(String approval_Remarks) {
        this.approval_Remarks = approval_Remarks;
    }

    public int getVillage_Serial_No() {
        return village_Serial_No;
    }

    public void setVillage_Serial_No(int village_Serial_No) {
        this.village_Serial_No = village_Serial_No;
    }

    public String getReceipt_Number() {
        return receipt_Number;
    }

    public void setReceipt_Number(String receipt_Number) {
        this.receipt_Number = receipt_Number;
    }

    public Date getReceipt_Date() {
        return receipt_Date;
    }

    public void setReceipt_Date(Date receipt_Date) {
        this.receipt_Date = receipt_Date;
    }

    public int getSync_G2C() {
        return sync_G2C;
    }

    public void setSync_G2C(int sync_G2C) {
        this.sync_G2C = sync_G2C;
    }

    public String getStorey_House() {
        return storey_House;
    }

    public void setStorey_House(String storey_House) {
        this.storey_House = storey_House;
    }

    public String getDzongkhag_Name() {
        return dzongkhag_Name;
    }

    public void setDzongkhag_Name(String dzongkhag_Name) {
        this.dzongkhag_Name = dzongkhag_Name;
    }

    public String getGewog_Name() {
        return gewog_Name;
    }

    public void setGewog_Name(String gewog_Name) {
        this.gewog_Name = gewog_Name;
    }

    public String getVillage_Name() {
        return village_Name;
    }

    public void setVillage_Name(String village_Name) {
        this.village_Name = village_Name;
    }

    public String getGenderType() {
        return genderType;
    }

    public void setGenderType(String genderType) {
        this.genderType = genderType;
    }

    public Integer getVillage_Id() {
        return village_Id;
    }

    public void setVillage_Id(Integer village_Id) {
        this.village_Id = village_Id;
    }

    public String getPrivate_LandForm_Remarks() {
        return private_LandForm_Remarks;
    }

    public void setPrivate_LandForm_Remarks(String private_LandForm_Remarks) {
        this.private_LandForm_Remarks = private_LandForm_Remarks;
    }

    public Date getInspection_Date() {
        return Inspection_Date;
    }

    public void setInspection_Date(Date inspection_Date) {
        this.Inspection_Date = inspection_Date;
    }

    public String getRanger_Remark_PRL() {
        return Ranger_Remark_PRL;
    }

    public void setRanger_Remark_PRL(String ranger_Remark_PRL) {
        this.Ranger_Remark_PRL = ranger_Remark_PRL;
    }

    public String getPrevious_application_Number() {
        return previous_application_Number;
    }

    public void setPrevious_application_Number(String previous_application_Number) {
        this.previous_application_Number = previous_application_Number;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getCc_Reclaim_Remarks() {
        return cc_Reclaim_Remarks;
    }

    public void setCc_Reclaim_Remarks(String cc_Reclaim_Remarks) {
        this.cc_Reclaim_Remarks = cc_Reclaim_Remarks;
    }

    public String getPeg_No() {
        return peg_No;
    }

    public void setPeg_No(String peg_No) {
        this.peg_No = peg_No;
    }

    public String getPayment_Status() {
        return payment_Status;
    }

    public void setPayment_Status(String payment_Status) {
        this.payment_Status = payment_Status;
    }

    public int getThram_No() {
        return thram_No;
    }

    public void setThram_No(int thram_No) {
        this.thram_No = thram_No;
    }
}
