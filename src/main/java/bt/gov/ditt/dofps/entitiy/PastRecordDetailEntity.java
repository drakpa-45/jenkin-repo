package bt.gov.ditt.dofps.entitiy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Pema Drakpa on 26/10/2022.
 */
@Entity
@Table(name = "t_applicant_past_timber_records")
public class PastRecordDetailEntity implements Serializable{
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name="Application_Number")
    private String application_Number;
    @Column(name="Applicant_CID")
    private String applicant_CID;
    @Column(name="Timber_Type")
    private String timber_Type;
    @Column(name="Quantity_Taken")
    private int quantity_Taken;
    @Column(name="Year_of_allotment")
    private String year_of_allotment;

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

    public String getApplicant_CID() {
        return applicant_CID;
    }

    public void setApplicant_CID(String applicant_CID) {
        this.applicant_CID = applicant_CID;
    }

    public String getTimber_Type() {
        return timber_Type;
    }

    public void setTimber_Type(String timber_Type) {
        this.timber_Type = timber_Type;
    }

    public int getQuantity_Taken() {
        return quantity_Taken;
    }

    public void setQuantity_Taken(int quantity_Taken) {
        this.quantity_Taken = quantity_Taken;
    }

    public String getYear_of_allotment() {
        return year_of_allotment;
    }

    public void setYear_of_allotment(String year_of_allotment) {
        this.year_of_allotment = year_of_allotment;
    }
}
