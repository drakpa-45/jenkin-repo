package bt.gov.ditt.dofps.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by deepa on 7/13/2022.
 */
public class OnlineTimberDTO {
    private String application_Number;
    private String application_Type;
    private String cid_Number;
    private String gender;
    private String house_No;
    private String house_Hold_No;
    private int division_Park_Id;
    private String head_of_Gung;
    private String member_of_Forest_community;
    private String register_Geog;
    private Date census_Reg_Date;
    private String village_Name;
    private String cons_Approval_No;
    private int cons_Loc_Village_Serial_No;
    private String construction_Location;
    private String cons_Type;
    private String mode_of_Swing_Desc;
    private int mode_of_Swing_Id;
    private String roofing_Type;
    private int appl_Quantity;
    private int allot_Quantity;
    private int allot_Quantity_log;
    private String product_Catagory;
    private int fp_Product_Id;
    private float rate;
    private String phone_Number;
    private String app_Approval_Date;
    private String dealing_Division_Park;
    private float net_Royalty;
    private String rejection_Reason;
    private String approval_Remarks;
    private String division_Park_Name;
    private String telephone_Number;
    private int village_Serial_No;
    private String name;
    private String dzongkhag_Name;
    private String gewog_Name;
    private String storey_House;
    private String document_Type;
    private Date action_Date;
    private Integer allot_Range_Officer;
    private String range_Officer;
    private String allot_Date;
    private String allot_Area;
    private float total_Royalty;
    private String current_Status;
    private String dealing_Officer_Remarks;
    private String app_Verification_Date;
    private String document_Name;
    private String service_Name;
    private String app_Submission_Date;
    private List<CommonDto> commonDetails;
    private List<CommonDto> documents;
    private List<TimberDetailsDto> timberDetails;
    private String wrStatus;
    private String dealing_Date;
    private String assigned_User_Id;
    private Integer status_Id;
    private String actor_Id;
    private String actor_Name;
    private String role_Name;
    private Integer role_Id;
    private Integer balance_Quantity;
    private Integer rejection_Id;
    private double balc_amount;
    private int qty_taken;
    private String private_LandForm_Remarks;
    private String dzongkhag_Serial_No;
    private String Product_Desc;
    private Date Inspection_Date;
    private String Ranger_Remark_PRL;
    private String AlternativeNumberRelation;
    private String allocation_qty;
    private String taken_Qty;
    private String app_Qty;
    private String royalty_Rate;
    private String plot_No;
    private String gps_Coordinates;
    private Integer land_Category;
    private String land_Category_Name;
    private float acres;
    private String peg_No;
    private List<PrivateLandDto> privateLandDtos;
    private String thram_No;
    private String landAdjoining;
    private int no_Trees;
    private int no_Poles;
    private int no_Bamboos;
    private String status;
    private int gewogId;
    private Date permitExpiryDate;
    private String unit;


    public int getGewogId() {
        return gewogId;
    }

    public void setGewogId(int gewogId) {
        this.gewogId = gewogId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCons_Loc_Village_Serial_No() {
        return cons_Loc_Village_Serial_No;
    }

    public void setCons_Loc_Village_Serial_No(int cons_Loc_Village_Serial_No) {
        this.cons_Loc_Village_Serial_No = cons_Loc_Village_Serial_No;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCid_Number() {
        return cid_Number;
    }

    public void setCid_Number(String cid_Number) {
        this.cid_Number = cid_Number;
    }

    public String getHouse_No() {
        return house_No;
    }

    public void setHouse_No(String house_No) {
        this.house_No = house_No;
    }

    public String getHouse_Hold_No() {
        return house_Hold_No;
    }

    public void setHouse_Hold_No(String house_Hold_No) {
        this.house_Hold_No = house_Hold_No;
    }

    public int getDivision_Park_Id() {
        return division_Park_Id;
    }

    public void setDivision_Park_Id(int division_Park_Id) {
        this.division_Park_Id = division_Park_Id;
    }

    public String getHead_of_Gung() {
        return head_of_Gung;
    }

    public void setHead_of_Gung(String head_of_Gung) {
        this.head_of_Gung = head_of_Gung;
    }

    public String getMember_of_Forest_community() {
        return member_of_Forest_community;
    }

    public void setMember_of_Forest_community(String member_of_Forest_community) {
        this.member_of_Forest_community = member_of_Forest_community;
    }

    public String getRegister_Geog() {
        return register_Geog;
    }

    public void setRegister_Geog(String register_Geog) {
        this.register_Geog = register_Geog;
    }

    public Date getCensus_Reg_Date() {
        return census_Reg_Date;
    }

    public void setCensus_Reg_Date(Date census_Reg_Date) {
        this.census_Reg_Date = census_Reg_Date;
    }

    public String getVillage_Name() {
        return village_Name;
    }

    public void setVillage_Name(String village_Name) {
        this.village_Name = village_Name;
    }

    public String getCons_Approval_No() {
        return cons_Approval_No;
    }

    public void setCons_Approval_No(String cons_Approval_No) {
        this.cons_Approval_No = cons_Approval_No;
    }

    public String getConstruction_Location() {
        return construction_Location;
    }

    public void setConstruction_Location(String construction_Location) {
        this.construction_Location = construction_Location;
    }

    public String getCons_Type() {
        return cons_Type;
    }

    public void setCons_Type(String cons_Type) {
        this.cons_Type = cons_Type;
    }

    public String getMode_of_Swing_Desc() {
        return mode_of_Swing_Desc;
    }

    public void setMode_of_Swing_Desc(String mode_of_Swing_Desc) {
        this.mode_of_Swing_Desc = mode_of_Swing_Desc;
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

    public int getAppl_Quantity() {
        return appl_Quantity;
    }

    public void setAppl_Quantity(int appl_Quantity) {
        this.appl_Quantity = appl_Quantity;
    }

    public int getAllot_Quantity() {
        return allot_Quantity;
    }

    public void setAllot_Quantity(int allot_Quantity) {
        this.allot_Quantity = allot_Quantity;
    }

    public int getAllot_Quantity_log() {
        return allot_Quantity_log;
    }

    public void setAllot_Quantity_log(int allot_Quantity_log) {
        this.allot_Quantity_log = allot_Quantity_log;
    }

    public String getProduct_Catagory() {
        return product_Catagory;
    }

    public void setProduct_Catagory(String product_Catagory) {
        this.product_Catagory = product_Catagory;
    }

    public int getFp_Product_Id() {
        return fp_Product_Id;
    }

    public void setFp_Product_Id(int fp_Product_Id) {
        this.fp_Product_Id = fp_Product_Id;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }

    public String getApp_Approval_Date() {
        return app_Approval_Date;
    }

    public void setApp_Approval_Date(String app_Approval_Date) {
        this.app_Approval_Date = app_Approval_Date;
    }

    public String getDealing_Division_Park() {
        return dealing_Division_Park;
    }

    public void setDealing_Division_Park(String dealing_Division_Park) {
        this.dealing_Division_Park = dealing_Division_Park;
    }

    public float getNet_Royalty() {
        return net_Royalty;
    }

    public void setNet_Royalty(float net_Royalty) {
        this.net_Royalty = net_Royalty;
    }

    public String getRejection_Reason() {
        return rejection_Reason;
    }

    public void setRejection_Reason(String rejection_Reason) {
        this.rejection_Reason = rejection_Reason;
    }

    public String getApproval_Remarks() {
        return approval_Remarks;
    }

    public void setApproval_Remarks(String approval_Remarks) {
        this.approval_Remarks = approval_Remarks;
    }

    public String getDivision_Park_Name() {
        return division_Park_Name;
    }

    public void setDivision_Park_Name(String division_Park_Name) {
        this.division_Park_Name = division_Park_Name;
    }

    public String getTelephone_Number() {
        return telephone_Number;
    }

    public void setTelephone_Number(String telephone_Number) {
        this.telephone_Number = telephone_Number;
    }

    public int getVillage_Serial_No() {
        return village_Serial_No;
    }

    public void setVillage_Serial_No(int village_Serial_No) {
        this.village_Serial_No = village_Serial_No;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStorey_House() {
        return storey_House;
    }

    public void setStorey_House(String storey_House) {
        this.storey_House = storey_House;
    }

    public String getDocument_Type() {
        return document_Type;
    }

    public void setDocument_Type(String document_Type) {
        this.document_Type = document_Type;
    }

    public Date getAction_Date() {
        return action_Date;
    }

    public void setAction_Date(Date action_Date) {
        this.action_Date = action_Date;
    }

    public Integer getAllot_Range_Officer() {
        return allot_Range_Officer;
    }

    public void setAllot_Range_Officer(Integer allot_Range_Officer) {
        this.allot_Range_Officer = allot_Range_Officer;
    }

    public String getRange_Officer() {
        return range_Officer;
    }

    public void setRange_Officer(String range_Officer) {
        this.range_Officer = range_Officer;
    }

    public String getAllot_Date() {
        return allot_Date;
    }

    public void setAllot_Date(String allot_Date) {
        this.allot_Date = allot_Date;
    }

    public String getAllot_Area() {
        return allot_Area;
    }

    public void setAllot_Area(String allot_Area) {
        this.allot_Area = allot_Area;
    }

    public float getTotal_Royalty() {
        return total_Royalty;
    }

    public void setTotal_Royalty(float total_Royalty) {
        this.total_Royalty = total_Royalty;
    }

    public String getCurrent_Status() {
        return current_Status;
    }

    public void setCurrent_Status(String current_Status) {
        this.current_Status = current_Status;
    }

    public String getDealing_Officer_Remarks() {
        return dealing_Officer_Remarks;
    }

    public void setDealing_Officer_Remarks(String dealing_Officer_Remarks) {
        this.dealing_Officer_Remarks = dealing_Officer_Remarks;
    }

    public String getApp_Verification_Date() {
        return app_Verification_Date;
    }

    public void setApp_Verification_Date(String app_Verification_Date) {
        this.app_Verification_Date = app_Verification_Date;
    }

    public String getDocument_Name() {
        return document_Name;
    }

    public void setDocument_Name(String document_Name) {
        this.document_Name = document_Name;
    }

    public String getService_Name() {
        return service_Name;
    }

    public void setService_Name(String service_Name) {
        this.service_Name = service_Name;
    }

    public String getApp_Submission_Date() {
        return app_Submission_Date;
    }

    public void setApp_Submission_Date(String app_Submission_Date) {
        this.app_Submission_Date = app_Submission_Date;
    }

    public List<CommonDto> getCommonDetails() {
        return commonDetails;
    }

    public void setCommonDetails(List<CommonDto> commonDetails) {
        this.commonDetails = commonDetails;
    }

    public List<CommonDto> getDocuments() {
        return documents;
    }

    public void setDocuments(List<CommonDto> documents) {
        this.documents = documents;
    }

    public List<TimberDetailsDto> getTimberDetails() {
        return timberDetails;
    }

    public void setTimberDetails(List<TimberDetailsDto> timberDetails) {
        this.timberDetails = timberDetails;
    }

    public String getWrStatus() {
        return wrStatus;
    }

    public void setWrStatus(String wrStatus) {
        this.wrStatus = wrStatus;
    }

    public String getDealing_Date() {
        return dealing_Date;
    }

    public void setDealing_Date(String dealing_Date) {
        this.dealing_Date = dealing_Date;
    }

    public String getAssigned_User_Id() {
        return assigned_User_Id;
    }

    public void setAssigned_User_Id(String assigned_User_Id) {
        this.assigned_User_Id = assigned_User_Id;
    }

    public Integer getStatus_Id() {
        return status_Id;
    }

    public void setStatus_Id(Integer status_Id) {
        this.status_Id = status_Id;
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

    public String getRole_Name() {
        return role_Name;
    }

    public void setRole_Name(String role_Name) {
        this.role_Name = role_Name;
    }

    public Integer getRole_Id() {
        return role_Id;
    }

    public void setRole_Id(Integer role_Id) {
        this.role_Id = role_Id;
    }

    public Integer getBalance_Quantity() {
        return balance_Quantity;
    }

    public void setBalance_Quantity(Integer balance_Quantity) {
        this.balance_Quantity = balance_Quantity;
    }

    public Integer getRejection_Id() {
        return rejection_Id;
    }

    public void setRejection_Id(Integer rejection_Id) {
        this.rejection_Id = rejection_Id;
    }

    public double getBalc_amount() {
        return balc_amount;
    }

    public void setBalc_amount(double balc_amount) {
        this.balc_amount = balc_amount;
    }

    public int getQty_taken() {
        return qty_taken;
    }

    public void setQty_taken(int qty_taken) {
        this.qty_taken = qty_taken;
    }

    public String getPrivate_LandForm_Remarks() {
        return private_LandForm_Remarks;
    }

    public void setPrivate_LandForm_Remarks(String private_LandForm_Remarks) {
        this.private_LandForm_Remarks = private_LandForm_Remarks;
    }

    public String getDzongkhag_Serial_No() {
        return dzongkhag_Serial_No;
    }

    public void setDzongkhag_Serial_No(String dzongkhag_Serial_No) {
        this.dzongkhag_Serial_No = dzongkhag_Serial_No;
    }

    public String getProduct_Desc() {
        return Product_Desc;
    }

    public void setProduct_Desc(String product_Desc) {
        Product_Desc = product_Desc;
    }

    public Date getInspection_Date() {
        return Inspection_Date;
    }

    public void setInspection_Date(Date inspection_Date) {
        Inspection_Date = inspection_Date;
    }

    public String getRanger_Remark_PRL() {
        return Ranger_Remark_PRL;
    }

    public void setRanger_Remark_PRL(String ranger_Remark_PRL) {
        Ranger_Remark_PRL = ranger_Remark_PRL;
    }

    public String getAlternativeNumberRelation() {
        return AlternativeNumberRelation;
    }

    public void setAlternativeNumberRelation(String alternativeNumberRelation) {
        AlternativeNumberRelation = alternativeNumberRelation;
    }

    public String getAllocation_qty() {
        return allocation_qty;
    }

    public void setAllocation_qty(String allocation_qty) {
        this.allocation_qty = allocation_qty;
    }

    public String getTaken_Qty() {
        return taken_Qty;
    }

    public void setTaken_Qty(String taken_Qty) {
        this.taken_Qty = taken_Qty;
    }

    public String getApp_Qty() {
        return app_Qty;
    }

    public void setApp_Qty(String app_Qty) {
        this.app_Qty = app_Qty;
    }

    public String getRoyalty_Rate() {
        return royalty_Rate;
    }

    public void setRoyalty_Rate(String royalty_Rate) {
        this.royalty_Rate = royalty_Rate;
    }

    public String getPlot_No() {
        return plot_No;
    }

    public void setPlot_No(String plot_No) {
        this.plot_No = plot_No;
    }

    public String getGps_Coordinates() {
        return gps_Coordinates;
    }

    public void setGps_Coordinates(String gps_Coordinates) {
        this.gps_Coordinates = gps_Coordinates;
    }

    public Integer getLand_Category() {
        return land_Category;
    }

    public void setLand_Category(Integer land_Category) {
        this.land_Category = land_Category;
    }

    public String getLand_Category_Name() {
        return land_Category_Name;
    }

    public void setLand_Category_Name(String land_Category_Name) {
        this.land_Category_Name = land_Category_Name;
    }

    public float getAcres() {
        return acres;
    }

    public void setAcres(float acres) {
        this.acres = acres;
    }

    public String getPeg_No() {
        return peg_No;
    }

    public void setPeg_No(String peg_No) {
        this.peg_No = peg_No;
    }

    public List<PrivateLandDto> getPrivateLandDtos() {
        return privateLandDtos;
    }

    public void setPrivateLandDtos(List<PrivateLandDto> privateLandDtos) {
        this.privateLandDtos = privateLandDtos;
    }

    public String getThram_No() {
        return thram_No;
    }

    public void setThram_No(String thram_No) {
        this.thram_No = thram_No;
    }

    public String getLandAdjoining() {
        return landAdjoining;
    }

    public void setLandAdjoining(String landAdjoining) {
        this.landAdjoining = landAdjoining;
    }

    public int getNo_Trees() {
        return no_Trees;
    }

    public void setNo_Trees(int no_Trees) {
        this.no_Trees = no_Trees;
    }

    public int getNo_Poles() {
        return no_Poles;
    }

    public void setNo_Poles(int no_Poles) {
        this.no_Poles = no_Poles;
    }

    public int getNo_Bamboos() {
        return no_Bamboos;
    }

    public void setNo_Bamboos(int no_Bamboos) {
        this.no_Bamboos = no_Bamboos;
    }

    public Date getPermitExpiryDate() {
        return permitExpiryDate;
    }

    public void setPermitExpiryDate(Date permitExpiryDate) {
        this.permitExpiryDate = permitExpiryDate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
