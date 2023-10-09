package bt.gov.ditt.dofps.entitiy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Pema Drakpa on 26/10/2022.
 */
@Entity
@Table(name = "t_inspection_team_info")
public class InspectionTeamEntity implements Serializable{
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name="Application_Number")
    private String application_Number;
    @Column(name="InspectionTeam_CID")
    private String inspectionTeam_CID;
    @Column(name="InspectionTeam_Name")
    private String inspectionTeam_Name;
    @Column(name="InspectionTeam_ContactNo")
    private int inspectionTeam_ContactNo;
    @Column(name="Designation")
    private int designation;

    @Transient
    private String designationDesc;

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

    public String getInspectionTeam_CID() {
        return inspectionTeam_CID;
    }

    public void setInspectionTeam_CID(String inspectionTeam_CID) {
        this.inspectionTeam_CID = inspectionTeam_CID;
    }

    public String getInspectionTeam_Name() {
        return inspectionTeam_Name;
    }

    public void setInspectionTeam_Name(String inspectionTeam_Name) {
        this.inspectionTeam_Name = inspectionTeam_Name;
    }

    public int getInspectionTeam_ContactNo() {
        return inspectionTeam_ContactNo;
    }

    public void setInspectionTeam_ContactNo(int inspectionTeam_ContactNo) {
        this.inspectionTeam_ContactNo = inspectionTeam_ContactNo;
    }

    public int getDesignation() {
        return designation;
    }

    public void setDesignation(int designation) {
        this.designation = designation;
    }

    public String getDesignationDesc() {
        return designationDesc;
    }

    public void setDesignationDesc(String designationDesc) {
        this.designationDesc = designationDesc;
    }
}
