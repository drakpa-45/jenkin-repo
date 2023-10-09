package bt.gov.ditt.dofps.entitiy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Pema Drakpa on 26/10/2022.
 */
@Entity
@Table(name = "t_marking_information")
public class MarkingInformationEntity implements Serializable{
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name="Application_Number")
    private String application_Number;
    @Column(name="Species_Name")
    private String species_Name;
    @Column(name="Species_Id")
    private int species_Id;
    @Column(name="Volume")
    private Float volume;
    @Column(name="Location_of_Timber")
    private String location_of_Timber;
    @Column(name="GPS_Coordinates")
    private String gPS_Coordinates;


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

    public String getSpecies_Name() {
        return species_Name;
    }

    public void setSpecies_Name(String species_Name) {
        this.species_Name = species_Name;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public String getLocation_of_Timber() {
        return location_of_Timber;
    }

    public void setLocation_of_Timber(String location_of_Timber) {
        this.location_of_Timber = location_of_Timber;
    }

    public String getgPS_Coordinates() {
        return gPS_Coordinates;
    }

    public void setgPS_Coordinates(String gPS_Coordinates) {
        this.gPS_Coordinates = gPS_Coordinates;
    }
}
