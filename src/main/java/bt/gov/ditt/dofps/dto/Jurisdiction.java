package bt.gov.ditt.dofps.dto;

/**
 * Created by Pema Drakpa on 1/24/2020.
 */
public class Jurisdiction implements java.io.Serializable  {

    private static final long serialVersionUID = 1L;

    private String jurisdictionType;
    private String jurisdiction;
    private String locationID;
    public String getJurisdictionType() {
        return jurisdictionType;
    }
    public void setJurisdictionType(String jurisdictionType) {
        this.jurisdictionType = jurisdictionType;
    }
    public String getJurisdiction() {
        return jurisdiction;
    }
    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }
    public String getLocationID() {
        return locationID;
    }
    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }
}
