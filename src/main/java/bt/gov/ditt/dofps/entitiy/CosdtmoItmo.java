package bt.gov.ditt.dofps.entitiy;

import javax.persistence.*;
import java.awt.geom.Arc2D;
import java.io.Serializable;

/**
 * Created by Pema Drakpa on 26/10/2022.
 */
@Entity
@Table(name = "t_cosdtmoitmo")
public class CosdtmoItmo implements Serializable{
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name="Application_Number")
    private String application_Number;
    @Column(name="Produce")
    private String produce;
    @Column(name="Species_Id")
    private int species_Id;
    @Column(name="Location_of_Timber")
    private String location_of_Timber;
    @Column(name="LogBlock")
    private String logBlock;
    @Column(name="Length")
    private float length;
    @Column(name="Breath")
    private float breath;
    @Column(name="Girth")
    private float girth;
    @Column(name="Total_Volume")
    private float total_Volume;
    @Column(name="Quantity")
    private int quantity;
    @Column(name="Unit")
    private String unit;

    @Transient
    private String species_Name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApplication_Number() {
        return application_Number;
    }

    public void setApplication_Number(String application_Number) {
        this.application_Number = application_Number;
    }

    public int getSpecies_Id() {
        return species_Id;
    }

    public void setSpecies_Id(int species_Id) {
        this.species_Id = species_Id;
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public String getLocation_of_Timber() {
        return location_of_Timber;
    }

    public void setLocation_of_Timber(String location_of_Timber) {
        this.location_of_Timber = location_of_Timber;
    }

    public String getLogBlock() {
        return logBlock;
    }

    public void setLogBlock(String logBlock) {
        this.logBlock = logBlock;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getBreath() {
        return breath;
    }

    public void setBreath(float breath) {
        this.breath = breath;
    }

    public float getGirth() {
        return girth;
    }

    public void setGirth(float girth) {
        this.girth = girth;
    }

    public float getTotal_Volume() {
        return total_Volume;
    }

    public void setTotal_Volume(float total_Volume) {
        this.total_Volume = total_Volume;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSpecies_Name() {
        return species_Name;
    }

    public void setSpecies_Name(String species_Name) {
        this.species_Name = species_Name;
    }
}
