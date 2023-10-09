package bt.gov.ditt.dofps.entitiy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Pema Drakpa on 1/21/2020.
 */
@Entity
@Table(name = "t_fp_appl_allotment")
public class TimberEntity implements Serializable {
    @Id
    @Column(name="Sl_No")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int sl_No;
    @Column(name="Application_Number")
    private String application_Number;
    @Column(name="CID_Number")
    private String cid_Number;
    @Column(name="fP_Product_Id")
    private Integer fP_Product_Id;
    @Column(name = "appl_Quantity")
    private Integer appl_Quantity;
    @Column(name = "allot_Quantity")
    private Integer allot_Quantity;
    @Column(name = "Unit")
    private String unit;
    @Column(name = "royalty_Type")
    private String royalty_Type;
    @Column(name = "royalty_Rate")
    private Integer royalty_Rate;
    @Column(name = "estimated_Value")
    private Integer estimated_Value;
    @Column (name = "royalty_Realised")
    private Integer royalty_Realised;
    @Column(name = "parts_Id")
    private Integer parts_Id;
    @Column(name = "DTYPE")
    private String dTYPE;
    @Column(name = "Geog_No")
    private int geog_No;
    @Column(name="Village_Serial_No")
    private int village_Serial_No;
    @Column(name = "Allot_Range_Officer")
    private String allot_Range_Officer;
    @Column(name = "No_trees")
    private int no_trees;
    @Column (name = "No_poles")
    private int no_poles;
    @Column (name = "No_bamboos")
    private int no_bamboos;
    @Column (name = "Volume")
    private float volume;
    @Column (name = "Total_Royalty")
    private float total_Royalty;

    public int getSl_No() {
        return sl_No;
    }

    public void setSl_No(int sl_No) {
        this.sl_No = sl_No;
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

    public Integer getfP_Product_Id() {
        return fP_Product_Id;
    }

    public void setfP_Product_Id(Integer fP_Product_Id) {
        this.fP_Product_Id = fP_Product_Id;
    }

    public Integer getAppl_Quantity() {
        return appl_Quantity;
    }

    public void setAppl_Quantity(Integer appl_Quantity) {
        this.appl_Quantity = appl_Quantity;
    }

    public Integer getAllot_Quantity() {
        return allot_Quantity;
    }

    public void setAllot_Quantity(Integer allot_Quantity) {
        this.allot_Quantity = allot_Quantity;
    }

    public String getRoyalty_Type() {
        return royalty_Type;
    }

    public void setRoyalty_Type(String royalty_Type) {
        this.royalty_Type = royalty_Type;
    }

    public Integer getRoyalty_Rate() {
        return royalty_Rate;
    }

    public void setRoyalty_Rate(Integer royalty_Rate) {
        this.royalty_Rate = royalty_Rate;
    }

    public Integer getEstimated_Value() {
        return estimated_Value;
    }

    public void setEstimated_Value(Integer estimated_Value) {
        this.estimated_Value = estimated_Value;
    }

    public Integer getRoyalty_Realised() {
        return royalty_Realised;
    }

    public void setRoyalty_Realised(Integer royalty_Realised) {
        this.royalty_Realised = royalty_Realised;
    }

    public Integer getParts_Id() {
        return parts_Id;
    }

    public void setParts_Id(Integer parts_Id) {
        this.parts_Id = parts_Id;
    }

    public String getdTYPE() {
        return dTYPE;
    }

    public void setdTYPE(String dTYPE) {
        this.dTYPE = dTYPE;
    }

    public int getGeog_No() {
        return geog_No;
    }

    public void setGeog_No(int geog_No) {
        this.geog_No = geog_No;
    }

    public int getVillage_Serial_No() {
        return village_Serial_No;
    }

    public void setVillage_Serial_No(int village_Serial_No) {
        this.village_Serial_No = village_Serial_No;
    }

    public String getAllot_Range_Officer() {
        return allot_Range_Officer;
    }

    public void setAllot_Range_Officer(String allot_Range_Officer) {
        this.allot_Range_Officer = allot_Range_Officer;
    }

    public int getNo_trees() {
        return no_trees;
    }

    public void setNo_trees(int no_trees) {
        this.no_trees = no_trees;
    }

    public int getNo_poles() {
        return no_poles;
    }

    public void setNo_poles(int no_poles) {
        this.no_poles = no_poles;
    }

    public int getNo_bamboos() {
        return no_bamboos;
    }

    public void setNo_bamboos(int no_bamboos) {
        this.no_bamboos = no_bamboos;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getTotal_Royalty() {
        return total_Royalty;
    }

    public void setTotal_Royalty(float total_Royalty) {
        this.total_Royalty = total_Royalty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
