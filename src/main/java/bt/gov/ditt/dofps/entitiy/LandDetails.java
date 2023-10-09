package bt.gov.ditt.dofps.entitiy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Pema Drakpa on 14/08/2023.
 */
@Entity
@Table(name = "t_land_details")
public class LandDetails implements Serializable{
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name="Application_Number")
    private String application_Number;
    @Column(name="Plot_No")
    private String plot_No;
    @Column(name="peg_No")
    private String peg_No;
    @Column(name="Gps_Coordinates")
    private String gps_Coordinates;
    @Column(name="Land_Category")
    private int land_Category;
    @Column(name="Areas")
    private String areas;

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

    public String getPlot_No() {
        return plot_No;
    }

    public void setPlot_No(String plot_No) {
        this.plot_No = plot_No;
    }

    public String getPeg_No() {
        return peg_No;
    }

    public void setPeg_No(String peg_No) {
        this.peg_No = peg_No;
    }

    public String getGps_Coordinates() {
        return gps_Coordinates;
    }

    public void setGps_Coordinates(String gps_Coordinates) {
        this.gps_Coordinates = gps_Coordinates;
    }

    public int getLand_Category() {
        return land_Category;
    }

    public void setLand_Category(int land_Category) {
        this.land_Category = land_Category;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }
}
