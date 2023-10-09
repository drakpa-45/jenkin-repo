package bt.gov.ditt.dofps.dto;

/**
 * Created by Pema Drakpa on 3/23/2020.
 */
public class printingDTO {
    private String application_Number;
    private String current_Status;
    private String action_Date;
    private String role_Name;
    private String actor_Name;

    public printingDTO() {
    }

    public printingDTO(String application_Number, String current_Status, String action_Date, String role_Name, String actor_Name) {
        this.application_Number = application_Number;
        this.current_Status = current_Status;
        this.action_Date = action_Date;
        this.role_Name = role_Name;
        this.actor_Name = actor_Name;
    }

    public String getApplication_Number() {
        return application_Number;
    }

    public void setApplication_Number(String application_Number) {
        this.application_Number = application_Number;
    }

    public String getCurrent_Status() {
        return current_Status;
    }

    public void setCurrent_Status(String current_Status) {
        this.current_Status = current_Status;
    }

    public String getAction_Date() {
        return action_Date;
    }

    public void setAction_Date(String action_Date) {
        this.action_Date = action_Date;
    }

    public String getRole_Name() {
        return role_Name;
    }

    public void setRole_Name(String role_Name) {
        this.role_Name = role_Name;
    }

    public String getActor_Name() {
        return actor_Name;
    }

    public void setActor_Name(String actor_Name) {
        this.actor_Name = actor_Name;
    }
}

