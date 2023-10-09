package bt.gov.ditt.dofps.dto;

import java.util.List;

/**
 * Created by Tshedup Gyeltshen on 5/14/2020.
 */
public class claimAdditionalTimberDTO {
    private String application_Number;
    private String cid_Number;
    private String name;
    private String genderType;
    private String dzongkhag_Name;
    private String gewog_Name;
    private String village_Name;
    private String house_Hold_No;
    private String house_No;
    private String head_of_Gung;
    private String phone_Number;
    private Integer village_Serial_No;
    private String cons_Approval_No;
    private String register_Geog;
    private String roofing_Type;
    private String house_Storey;
    private String previous_application_Number;
    private Integer division_Park_Id;
    private String division_Park_Name;
    private Integer mode_Of_Swing_Id;
    private String mode_of_Swing_Desc;
    private Integer cons_Loc_Village_Serial_No;
    private String construction_Loc;
    private String member_of_forest_community;
    private Integer apply_Quantity;
    private Integer allot_Quantity;
    private Integer quantity_Taken;
    private String product_Category;
    private String product_Desc;
    private Integer FP_product_Id;
    private String document_Type;
    private String document_Name;
    private String upload_URL;
    private String uID;
    private String cc_Reclaim_Remarks;
    private String claimStatus;
    private String app_submission_date;
    private String verified_date;
    private String approved_date;
    private String dealing_date;
    private Integer new_apply_Qty;
    private float royalty_Rate;
    private float total_Royalty;
    private String dealing_Officer_Remarks;
    private String all_range_officer;
    private Integer dealing_allot_Quantity;
    private float net_Royalty;
    private String mark_date;
    private Integer bal_Qty;
    private List<CommonDto> documents;

    public claimAdditionalTimberDTO() {
    }

    public claimAdditionalTimberDTO(String application_Number, String cid_Number, String name, String genderType, String dzongkhag_Name, String gewog_Name, String village_Name, String house_Hold_No, String house_No, String head_of_Gung, String phone_Number, Integer village_Serial_No, String cons_Approval_No, String register_Geog, String roofing_Type, String house_Storey, String previous_application_Number, Integer division_Park_Id, String division_Park_Name, Integer mode_Of_Swing_Id, String mode_of_Swing_Desc, Integer cons_Loc_Village_Serial_No, String construction_Loc, String member_of_forest_community, Integer apply_Quantity, Integer allot_Quantity, Integer quantity_Taken, String product_Category, String product_Desc, Integer FP_product_Id, String document_Type, String document_Name, String upload_URL, String uID, String cc_Reclaim_Remarks, String claimStatus, String app_submission_date, String verified_date, String approved_date, String dealing_date, Integer new_apply_Qty, float royalty_Rate, float total_Royalty, String dealing_Officer_Remarks, String all_range_officer, Integer dealing_allot_Quantity, float net_Royalty, String mark_date, Integer bal_Qty, List<CommonDto> documents) {
        this.application_Number = application_Number;
        this.cid_Number = cid_Number;
        this.name = name;
        this.genderType = genderType;
        this.dzongkhag_Name = dzongkhag_Name;
        this.gewog_Name = gewog_Name;
        this.village_Name = village_Name;
        this.house_Hold_No = house_Hold_No;
        this.house_No = house_No;
        this.head_of_Gung = head_of_Gung;
        this.phone_Number = phone_Number;
        this.village_Serial_No = village_Serial_No;
        this.cons_Approval_No = cons_Approval_No;
        this.register_Geog = register_Geog;
        this.roofing_Type = roofing_Type;
        this.house_Storey = house_Storey;
        this.previous_application_Number = previous_application_Number;
        this.division_Park_Id = division_Park_Id;
        this.division_Park_Name = division_Park_Name;
        this.mode_Of_Swing_Id = mode_Of_Swing_Id;
        this.mode_of_Swing_Desc = mode_of_Swing_Desc;
        this.cons_Loc_Village_Serial_No = cons_Loc_Village_Serial_No;
        this.construction_Loc = construction_Loc;
        this.member_of_forest_community = member_of_forest_community;
        this.apply_Quantity = apply_Quantity;
        this.allot_Quantity = allot_Quantity;
        this.quantity_Taken = quantity_Taken;
        this.product_Category = product_Category;
        this.product_Desc = product_Desc;
        this.FP_product_Id = FP_product_Id;
        this.document_Type = document_Type;
        this.document_Name = document_Name;
        this.upload_URL = upload_URL;
        this.uID = uID;
        this.cc_Reclaim_Remarks = cc_Reclaim_Remarks;
        this.claimStatus = claimStatus;
        this.app_submission_date = app_submission_date;
        this.verified_date = verified_date;
        this.approved_date = approved_date;
        this.dealing_date = dealing_date;
        this.new_apply_Qty = new_apply_Qty;
        this.royalty_Rate = royalty_Rate;
        this.total_Royalty = total_Royalty;
        this.dealing_Officer_Remarks = dealing_Officer_Remarks;
        this.all_range_officer = all_range_officer;
        this.dealing_allot_Quantity = dealing_allot_Quantity;
        this.net_Royalty = net_Royalty;
        this.mark_date = mark_date;
        this.bal_Qty = bal_Qty;
        this.documents = documents;
    }

    public String getApplication_Number() {
        return application_Number;
    }

    public void setApplication_Number(String application_Number) {
        this.application_Number = application_Number;
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

    public String getGenderType() {
        return genderType;
    }

    public void setGenderType(String genderType) {
        this.genderType = genderType;
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

    public String getHead_of_Gung() {
        return head_of_Gung;
    }

    public void setHead_of_Gung(String head_of_Gung) {
        this.head_of_Gung = head_of_Gung;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }

    public Integer getVillage_Serial_No() {
        return village_Serial_No;
    }

    public void setVillage_Serial_No(Integer village_Serial_No) {
        this.village_Serial_No = village_Serial_No;
    }

    public String getCons_Approval_No() {
        return cons_Approval_No;
    }

    public void setCons_Approval_No(String cons_Approval_No) {
        this.cons_Approval_No = cons_Approval_No;
    }

    public String getRegister_Geog() {
        return register_Geog;
    }

    public void setRegister_Geog(String register_Geog) {
        this.register_Geog = register_Geog;
    }

    public String getRoofing_Type() {
        return roofing_Type;
    }

    public void setRoofing_Type(String roofing_Type) {
        this.roofing_Type = roofing_Type;
    }

    public String getHouse_Storey() {
        return house_Storey;
    }

    public void setHouse_Storey(String house_Storey) {
        this.house_Storey = house_Storey;
    }

    public String getPrevious_application_Number() {
        return previous_application_Number;
    }

    public void setPrevious_application_Number(String previous_application_Number) {
        this.previous_application_Number = previous_application_Number;
    }

    public Integer getDivision_Park_Id() {
        return division_Park_Id;
    }

    public void setDivision_Park_Id(Integer division_Park_Id) {
        this.division_Park_Id = division_Park_Id;
    }

    public String getDivision_Park_Name() {
        return division_Park_Name;
    }

    public void setDivision_Park_Name(String division_Park_Name) {
        this.division_Park_Name = division_Park_Name;
    }

    public Integer getMode_Of_Swing_Id() {
        return mode_Of_Swing_Id;
    }

    public void setMode_Of_Swing_Id(Integer mode_Of_Swing_Id) {
        this.mode_Of_Swing_Id = mode_Of_Swing_Id;
    }

    public String getMode_of_Swing_Desc() {
        return mode_of_Swing_Desc;
    }

    public void setMode_of_Swing_Desc(String mode_of_Swing_Desc) {
        this.mode_of_Swing_Desc = mode_of_Swing_Desc;
    }

    public Integer getCons_Loc_Village_Serial_No() {
        return cons_Loc_Village_Serial_No;
    }

    public void setCons_Loc_Village_Serial_No(Integer cons_Loc_Village_Serial_No) {
        this.cons_Loc_Village_Serial_No = cons_Loc_Village_Serial_No;
    }

    public String getConstruction_Loc() {
        return construction_Loc;
    }

    public void setConstruction_Loc(String construction_Loc) {
        this.construction_Loc = construction_Loc;
    }

    public String getMember_of_forest_community() {
        return member_of_forest_community;
    }

    public void setMember_of_forest_community(String member_of_forest_community) {
        this.member_of_forest_community = member_of_forest_community;
    }

    public Integer getApply_Quantity() {
        return apply_Quantity;
    }

    public void setApply_Quantity(Integer apply_Quantity) {
        this.apply_Quantity = apply_Quantity;
    }

    public Integer getAllot_Quantity() {
        return allot_Quantity;
    }

    public void setAllot_Quantity(Integer allot_Quantity) {
        this.allot_Quantity = allot_Quantity;
    }

    public Integer getQuantity_Taken() {
        return quantity_Taken;
    }

    public void setQuantity_Taken(Integer quantity_Taken) {
        this.quantity_Taken = quantity_Taken;
    }

    public String getProduct_Category() {
        return product_Category;
    }

    public void setProduct_Category(String product_Category) {
        this.product_Category = product_Category;
    }

    public String getProduct_Desc() {
        return product_Desc;
    }

    public void setProduct_Desc(String product_Desc) {
        this.product_Desc = product_Desc;
    }

    public Integer getFP_product_Id() {
        return FP_product_Id;
    }

    public void setFP_product_Id(Integer FP_product_Id) {
        this.FP_product_Id = FP_product_Id;
    }

    public String getDocument_Type() {
        return document_Type;
    }

    public void setDocument_Type(String document_Type) {
        this.document_Type = document_Type;
    }

    public String getDocument_Name() {
        return document_Name;
    }

    public void setDocument_Name(String document_Name) {
        this.document_Name = document_Name;
    }

    public String getUpload_URL() {
        return upload_URL;
    }

    public void setUpload_URL(String upload_URL) {
        this.upload_URL = upload_URL;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getCc_Reclaim_Remarks() {
        return cc_Reclaim_Remarks;
    }

    public void setCc_Reclaim_Remarks(String cc_Reclaim_Remarks) {
        this.cc_Reclaim_Remarks = cc_Reclaim_Remarks;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getApp_submission_date() {
        return app_submission_date;
    }

    public void setApp_submission_date(String app_submission_date) {
        this.app_submission_date = app_submission_date;
    }

    public String getVerified_date() {
        return verified_date;
    }

    public void setVerified_date(String verified_date) {
        this.verified_date = verified_date;
    }

    public String getApproved_date() {
        return approved_date;
    }

    public void setApproved_date(String approved_date) {
        this.approved_date = approved_date;
    }

    public String getDealing_date() {
        return dealing_date;
    }

    public void setDealing_date(String dealing_date) {
        this.dealing_date = dealing_date;
    }

    public Integer getNew_apply_Qty() {
        return new_apply_Qty;
    }

    public void setNew_apply_Qty(Integer new_apply_Qty) {
        this.new_apply_Qty = new_apply_Qty;
    }

    public float getRoyalty_Rate() {
        return royalty_Rate;
    }

    public void setRoyalty_Rate(float royalty_Rate) {
        this.royalty_Rate = royalty_Rate;
    }

    public float getTotal_Royalty() {
        return total_Royalty;
    }

    public void setTotal_Royalty(float total_Royalty) {
        this.total_Royalty = total_Royalty;
    }

    public String getDealing_Officer_Remarks() {
        return dealing_Officer_Remarks;
    }

    public void setDealing_Officer_Remarks(String dealing_Officer_Remarks) {
        this.dealing_Officer_Remarks = dealing_Officer_Remarks;
    }

    public String getAll_range_officer() {
        return all_range_officer;
    }

    public void setAll_range_officer(String all_range_officer) {
        this.all_range_officer = all_range_officer;
    }

    public Integer getDealing_allot_Quantity() {
        return dealing_allot_Quantity;
    }

    public void setDealing_allot_Quantity(Integer dealing_allot_Quantity) {
        this.dealing_allot_Quantity = dealing_allot_Quantity;
    }

    public float getNet_Royalty() {
        return net_Royalty;
    }

    public void setNet_Royalty(float net_Royalty) {
        this.net_Royalty = net_Royalty;
    }

    public String getMark_date() {
        return mark_date;
    }

    public void setMark_date(String mark_date) {
        this.mark_date = mark_date;
    }

    public Integer getBal_Qty() {
        return bal_Qty;
    }

    public void setBal_Qty(Integer bal_Qty) {
        this.bal_Qty = bal_Qty;
    }

    public List<CommonDto> getDocuments() {
        return documents;
    }

    public void setDocuments(List<CommonDto> documents) {
        this.documents = documents;
    }
}
