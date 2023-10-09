package bt.gov.ditt.dofps.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by Pema Drakpa on 1/21/2020.
 */
public class NewTimberDto {

    private int header_id;
    private String header_Name;
    private String application_Number;
    private String application_Type;
    private int nwfp_Management_Group_Sl_No;
    private String applicant_Area_Tag;
    private int request_Service_Id;
    private Date app_Submission_Date;
    private Date app_Verification_Date;
    private Date app_Approval_Date;
    private Date app_Rejection_Date;
    private String rejection_Reason;
    private String phone_Number;
    private String mobile_Number;
    private int division_Park_Id;
    private String division_Park_Name;
    private int division_Park_Id_1;
    private String register_Geog;
    private int cons_Loc_Village_Serial_No;
    private String cons_Approval_No;
    private String cons_Type;
    private String fp_Details;
    private String certify_Tag;
    private String inspection_Remark;
    private int mode_of_Swing_Id;
    private String roofing_Type;
    private Date dealing_Date;
    private String dealing_Division_Park;
    private String allot_Range_Officer;
    private String range_Officer;
    private Date allot_Date;
    private String allot_Area;
    private String member_of_Forest_community;
    private float total_Royalty;
    private int permit_Fee;
    private float net_Royalty;
    private String dealing_Officer_Remarks;
    private String div_Change_Reason;
    private String verified_Record_Tag;
    private String head_of_Gung;
    private String construction_Location;
    private int service_Fees;
    private int application_Fees;
    private Date census_Reg_Date;
    private String nwfp_App_Type;
    private String plot_No;
    private String approval_Remarks;
    private String receipt_Number;
    private Date receipt_Date;
    private int sync_G2C;
    private String storey_House;
    private String telephone_Number;
    private Integer role_Id;
    private String role_Name;
    private String service_Name;
    private String user_Id;
    private String user_Name;
    private int juri_Type_Id;
    private int status_Id;
    private BigInteger stat_Id;
    private int service_Id;
    private Date action_Date;
    private String actor_Id;
    private String actor_Name;
    private String current_Status;
    private int village_Serial_No;
    private int gewog_Serial_No;
    private String cons_TypeFP_Details;
    private List<PersonalDto> personaldetails;
    private List<TimberDetailsDto> timberdetails;
    private String private_LandForm_Remarks;
    private int Product_Id;
    private String AlternativeNumberRelation;
    private String payment_status;
    private int thram_NO;
    private String marking_Date;

    private String name_of_Sawmill;
    private String licenseNo;
    private String location_of_Sawmill;
    private float sawing_Rate;

    public int getHeader_id() {
        return header_id;
    }

    public void setHeader_id(int header_id) {
        this.header_id = header_id;
    }

    public String getHeader_Name() {
        return header_Name;
    }

    public void setHeader_Name(String header_Name) {
        this.header_Name = header_Name;
    }

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

    public int getDivision_Park_Id() {
        return division_Park_Id;
    }

    public void setDivision_Park_Id(int division_Park_Id) {
        this.division_Park_Id = division_Park_Id;
    }

    public String getDivision_Park_Name() {
        return division_Park_Name;
    }

    public void setDivision_Park_Name(String division_Park_Name) {
        this.division_Park_Name = division_Park_Name;
    }

    public int getDivision_Park_Id_1() {
        return division_Park_Id_1;
    }

    public void setDivision_Park_Id_1(int division_Park_Id_1) {
        this.division_Park_Id_1 = division_Park_Id_1;
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

    public String getCons_Type() {
        return cons_Type;
    }

    public void setCons_Type(String cons_Type) {
        this.cons_Type = cons_Type;
    }

    public String getFp_Details() {
        return fp_Details;
    }

    public void setFp_Details(String fp_Details) {
        this.fp_Details = fp_Details;
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

    public String getRange_Officer() {
        return range_Officer;
    }

    public void setRange_Officer(String range_Officer) {
        this.range_Officer = range_Officer;
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

    public float getTotal_Royalty() {
        return total_Royalty;
    }

    public void setTotal_Royalty(float total_Royalty) {
        this.total_Royalty = total_Royalty;
    }

    public int getPermit_Fee() {
        return permit_Fee;
    }

    public void setPermit_Fee(int permit_Fee) {
        this.permit_Fee = permit_Fee;
    }

    public float getNet_Royalty() {
        return net_Royalty;
    }

    public void setNet_Royalty(float net_Royalty) {
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

    public String getTelephone_Number() {
        return telephone_Number;
    }

    public void setTelephone_Number(String telephone_Number) {
        this.telephone_Number = telephone_Number;
    }

    public Integer getRole_Id() {
        return role_Id;
    }

    public void setRole_Id(Integer role_Id) {
        this.role_Id = role_Id;
    }

    public String getRole_Name() {
        return role_Name;
    }

    public void setRole_Name(String role_Name) {
        this.role_Name = role_Name;
    }

    public String getService_Name() {
        return service_Name;
    }

    public void setService_Name(String service_Name) {
        this.service_Name = service_Name;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public int getJuri_Type_Id() {
        return juri_Type_Id;
    }

    public void setJuri_Type_Id(int juri_Type_Id) {
        this.juri_Type_Id = juri_Type_Id;
    }

    public int getStatus_Id() {
        return status_Id;
    }

    public void setStatus_Id(int status_Id) {
        this.status_Id = status_Id;
    }

    public BigInteger getStat_Id() {
        return stat_Id;
    }

    public void setStat_Id(BigInteger stat_Id) {
        this.stat_Id = stat_Id;
    }

    public int getService_Id() {
        return service_Id;
    }

    public void setService_Id(int service_Id) {
        this.service_Id = service_Id;
    }

    public Date getAction_Date() {
        return action_Date;
    }

    public void setAction_Date(Date action_Date) {
        this.action_Date = action_Date;
    }

    public String getActor_Id() {
        return actor_Id;
    }

    public void setActor_Id(String actor_Id) {
        this.actor_Id = actor_Id;
    }

    public String getActor_Name() {
        return actor_Name;
    }

    public void setActor_Name(String actor_Name) {
        this.actor_Name = actor_Name;
    }

    public String getCurrent_Status() {
        return current_Status;
    }

    public void setCurrent_Status(String current_Status) {
        this.current_Status = current_Status;
    }

    public int getVillage_Serial_No() {
        return village_Serial_No;
    }

    public void setVillage_Serial_No(int village_Serial_No) {
        this.village_Serial_No = village_Serial_No;
    }

    public int getGewog_Serial_No() {
        return gewog_Serial_No;
    }

    public void setGewog_Serial_No(int gewog_Serial_No) {
        this.gewog_Serial_No = gewog_Serial_No;
    }

    public String getCons_TypeFP_Details() {
        return cons_TypeFP_Details;
    }

    public void setCons_TypeFP_Details(String cons_TypeFP_Details) {
        this.cons_TypeFP_Details = cons_TypeFP_Details;
    }

    public List<PersonalDto> getPersonaldetails() {
        return personaldetails;
    }

    public void setPersonaldetails(List<PersonalDto> personaldetails) {
        this.personaldetails = personaldetails;
    }

    public List<TimberDetailsDto> getTimberdetails() {
        return timberdetails;
    }

    public void setTimberdetails(List<TimberDetailsDto> timberdetails) {
        this.timberdetails = timberdetails;
    }

    public String getPrivate_LandForm_Remarks() {
        return private_LandForm_Remarks;
    }

    public void setPrivate_LandForm_Remarks(String private_LandForm_Remarks) {
        this.private_LandForm_Remarks = private_LandForm_Remarks;
    }

    public int getProduct_Id() {
        return Product_Id;
    }

    public void setProduct_Id(int product_Id) {
        Product_Id = product_Id;
    }

    public String getAlternativeNumberRelation() {
        return AlternativeNumberRelation;
    }

    public void setAlternativeNumberRelation(String alternativeNumberRelation) {
        AlternativeNumberRelation = alternativeNumberRelation;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public int getThram_NO() {
        return thram_NO;
    }

    public void setThram_NO(int thram_NO) {
        this.thram_NO = thram_NO;
    }

    public String getMarking_Date() {
        return marking_Date;
    }

    public void setMarking_Date(String marking_Date) {
        this.marking_Date = marking_Date;
    }

    public String getName_of_Sawmill() {
        return name_of_Sawmill;
    }

    public void setName_of_Sawmill(String name_of_Sawmill) {
        this.name_of_Sawmill = name_of_Sawmill;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getLocation_of_Sawmill() {
        return location_of_Sawmill;
    }

    public void setLocation_of_Sawmill(String location_of_Sawmill) {
        this.location_of_Sawmill = location_of_Sawmill;
    }

    public float getSawing_Rate() {
        return sawing_Rate;
    }

    public void setSawing_Rate(float sawing_Rate) {
        this.sawing_Rate = sawing_Rate;
    }
}
