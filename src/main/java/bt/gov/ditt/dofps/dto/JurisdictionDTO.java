package bt.gov.ditt.dofps.dto;

import java.io.Serializable;

public class JurisdictionDTO implements Serializable {

    private String jurisdictionType;
    private String jurisdiction;
    private String locationID;

    public JurisdictionDTO() {
    }

    public JurisdictionDTO(String jurisdictionType, String jurisdiction, String locationID) {
        this.jurisdictionType = jurisdictionType;
        this.jurisdiction = jurisdiction;
        this.locationID = locationID;
    }

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
