package bt.gov.ditt.dofps.entitiy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Pema Drakpa on 1/31/2020.
 */
@Entity
@Table(name="t_workflow_dtls")
public class WorkFlowEntity {
    @Id
    @Column(name="Instance_Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int instance_Id;
    @Column(name="Application_Number")
    private String application_Number;
    @Column(name="Status_Id")
    private int status_Id;
    @Column(name="Service_Id")
    private int service_Id;
    @Column(name="Action_Date")
    private Date action_Date;
    @Column(name="Actor_Id")
    private String actor_Id;
    @Column(name="Actor_Name")
    private String actor_Name;
    @Column(name="Role_Id")
    private int role_Id;
    @Column(name="Role_Name")
    private String role_Name;

    public WorkFlowEntity() {
    }

    public WorkFlowEntity(String application_Number, int status_Id, int service_Id, Date action_Date, String actor_Id, String actor_Name, int role_Id, String role_Name) {
        this.application_Number = application_Number;
        this.status_Id = status_Id;
        this.service_Id = service_Id;
        this.action_Date = action_Date;
        this.actor_Id = actor_Id;
        this.actor_Name = actor_Name;
        this.role_Id = role_Id;
        this.role_Name = role_Name;
    }

    public int getInstance_Id() {
        return instance_Id;
    }

    public void setInstance_Id(int instance_Id) {
        this.instance_Id = instance_Id;
    }

    public String getApplication_Number() {
        return application_Number;
    }

    public void setApplication_Number(String application_Number) {
        this.application_Number = application_Number;
    }

    public int getStatus_Id() {
        return status_Id;
    }

    public void setStatus_Id(int status_Id) {
        this.status_Id = status_Id;
    }

    public int getService_Id() {
        return service_Id;
    }

    public void setService_Id(int service_Id) {
        this.service_Id = service_Id;
    }

    public Date getAction_Date() {
        return action_Date;
    }

    public void setAction_Date(Date action_Date) {
        this.action_Date = action_Date;
    }

    public String getActor_Id() {
        return actor_Id;
    }

    public void setActor_Id(String actor_Id) {
        this.actor_Id = actor_Id;
    }

    public String getActor_Name() {
        return actor_Name;
    }

    public void setActor_Name(String actor_Name) {
        this.actor_Name = actor_Name;
    }

    public int getRole_Id() {
        return role_Id;
    }

    public void setRole_Id(int role_Id) {
        this.role_Id = role_Id;
    }

    public String getRole_Name() {
        return role_Name;
    }

    public void setRole_Name(String role_Name) {
        this.role_Name = role_Name;
    }
}

