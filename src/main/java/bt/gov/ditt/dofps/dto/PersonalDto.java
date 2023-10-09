package bt.gov.ditt.dofps.dto;

/**
 * Created by Pema Drakpa on 1/17/2020.
 */
public class PersonalDto {
    private String cid_Number;
    private String name;
    private String house_Hold_No;
    private String phone_Number;
    private String mobile_Number;
    private int division_Park_Id;
    private String dzongkhag_Name;
    private String gewog_Name;
    private String village_Name;
    private String telephone_Number;

    public String getTelephone_Number() {
        return telephone_Number;
    }

    public void setTelephone_Number(String telephone_Number) {
        this.telephone_Number = telephone_Number;
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

    public String getCid_Number() {
        return cid_Number;
    }

    public void setCid_Number(String cid_Number) {
        this.cid_Number = cid_Number;
    }
}
