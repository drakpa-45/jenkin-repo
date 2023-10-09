package bt.gov.ditt.dofps.dto;

/**
 * Created by Pema Drakpa on 8/10/2023.
 */
public class LandDetailsDto {
    private int id;
    private String application_Number;
    private String plot_No;
    private String peg_No;
    private String gps_Coordinates;
    private int land_Category;
    private String areas;
    private String land_Category_Name;


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

    public String getLand_Category_Name() {
        return land_Category_Name;
    }

    public void setLand_Category_Name(String land_Category_Name) {
        this.land_Category_Name = land_Category_Name;
    }
}

