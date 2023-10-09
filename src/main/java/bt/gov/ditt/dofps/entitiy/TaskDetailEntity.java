package bt.gov.ditt.dofps.entitiy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Pema Drakpa on 1/31/2020.
 */
@Entity
@Table(name="t_task_dtls")
public class TaskDetailEntity {
    @Id
    @Column(name="Task_Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int task_Id;
    /*@Column(name="Instance_Id")
    private int Instance_Id;*/
    @Column(name="Seq_Details_Id")
    private int seq_Details_Id;
    @Column(name="Application_Number")
    private String application_Number;
    @Column(name="Assigned_User_Id")
    private String assigned_User_Id;
    @Column(name="Assigned_Priv_Id")
    private int assigned_Priv_Id;
    @Column(name="Task_State_Id")
    private int task_State_Id;
    @Column(name="Task_Remark")
    private String task_Remark;
    @Column(name="Action_Date")
    private Date action_Date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Instance_Id", referencedColumnName = "Instance_Id")
    private WorkFlowEntity work;

    public TaskDetailEntity() {
    }

    public TaskDetailEntity(int seq_Details_Id, String application_Number, String assigned_User_Id, int assigned_Priv_Id, int task_State_Id, String task_Remark, Date action_Date, WorkFlowEntity work) {
        this.seq_Details_Id = seq_Details_Id;
        this.application_Number = application_Number;
        this.assigned_User_Id = assigned_User_Id;
        this.assigned_Priv_Id = assigned_Priv_Id;
        this.task_State_Id = task_State_Id;
        this.task_Remark = task_Remark;
        this.action_Date = action_Date;
        this.work = work;
    }

    public int getTask_Id() {
        return task_Id;
    }

    public void setTask_Id(int task_Id) {
        this.task_Id = task_Id;
    }

    public int getSeq_Details_Id() {
        return seq_Details_Id;
    }

    public void setSeq_Details_Id(int seq_Details_Id) {
        this.seq_Details_Id = seq_Details_Id;
    }

    public String getApplication_Number() {
        return application_Number;
    }

    public void setApplication_Number(String application_Number) {
        this.application_Number = application_Number;
    }

    public String getAssigned_User_Id() {
        return assigned_User_Id;
    }

    public void setAssigned_User_Id(String assigned_User_Id) {
        this.assigned_User_Id = assigned_User_Id;
    }

    public int getAssigned_Priv_Id() {
        return assigned_Priv_Id;
    }

    public void setAssigned_Priv_Id(int assigned_Priv_Id) {
        this.assigned_Priv_Id = assigned_Priv_Id;
    }

    public int getTask_State_Id() {
        return task_State_Id;
    }

    public void setTask_State_Id(int task_State_Id) {
        this.task_State_Id = task_State_Id;
    }

    public String getTask_Remark() {
        return task_Remark;
    }

    public void setTask_Remark(String task_Remark) {
        this.task_Remark = task_Remark;
    }

    public Date getAction_Date() {
        return action_Date;
    }

    public void setAction_Date(Date action_Date) {
        this.action_Date = action_Date;
    }

    public WorkFlowEntity getWork() {
        return work;
    }

    public void setWork(WorkFlowEntity work) {
        this.work = work;
    }
}
