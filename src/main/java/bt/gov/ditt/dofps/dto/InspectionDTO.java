package bt.gov.ditt.dofps.dto;

import bt.gov.ditt.dofps.entitiy.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Pema Drakpa on 26/10/2022.
 */
public class InspectionDTO {
    private String application_Number;
    private String contructionSite;
    private String awayFromThromB;
    private String natureOfApplicant;
    private String proposedConstructionSite;
    private String remarks;
    private String hasAvailTimberB4;
    private String permitValidityDate;
    private String officerOnDuty;
    private int phone_Number;

    private List<InspectionTeamEntity> inspectionTeamEntityList;
    private List<PastRecordDetailEntity> pastRecordDetailEntityList;
    private List<MarkingInformationEntity> markingInformationEntityList;
    private List<CosdtmoItmo> cosdtmoItmosList;

    private List<DocumentEntity> documentEntities;

    public String getApplication_Number() {
        return application_Number;
    }

    public void setApplication_Number(String application_Number) {
        this.application_Number = application_Number;
    }

    public String getContructionSite() {
        return contructionSite;
    }

    public void setContructionSite(String contructionSite) {
        this.contructionSite = contructionSite;
    }

    public String getAwayFromThromB() {
        return awayFromThromB;
    }

    public void setAwayFromThromB(String awayFromThromB) {
        this.awayFromThromB = awayFromThromB;
    }

    public String getNatureOfApplicant() {
        return natureOfApplicant;
    }

    public void setNatureOfApplicant(String natureOfApplicant) {
        this.natureOfApplicant = natureOfApplicant;
    }

    public String getProposedConstructionSite() {
        return proposedConstructionSite;
    }

    public void setProposedConstructionSite(String proposedConstructionSite) {
        this.proposedConstructionSite = proposedConstructionSite;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<InspectionTeamEntity> getInspectionTeamEntityList() {
        return inspectionTeamEntityList;
    }

    public void setInspectionTeamEntityList(List<InspectionTeamEntity> inspectionTeamEntityList) {
        this.inspectionTeamEntityList = inspectionTeamEntityList;
    }

    public List<PastRecordDetailEntity> getPastRecordDetailEntityList() {
        return pastRecordDetailEntityList;
    }

    public void setPastRecordDetailEntityList(List<PastRecordDetailEntity> pastRecordDetailEntityList) {
        this.pastRecordDetailEntityList = pastRecordDetailEntityList;
    }

    public String getHasAvailTimberB4() {
        return hasAvailTimberB4;
    }

    public void setHasAvailTimberB4(String hasAvailTimberB4) {
        this.hasAvailTimberB4 = hasAvailTimberB4;
    }

    public List<DocumentEntity> getDocumentEntities() {
        return documentEntities;
    }

    public void setDocumentEntities(List<DocumentEntity> documentEntities) {
        this.documentEntities = documentEntities;
    }

    public List<MarkingInformationEntity> getMarkingInformationEntityList() {
        return markingInformationEntityList;
    }

    public void setMarkingInformationEntityList(List<MarkingInformationEntity> markingInformationEntityList) {
        this.markingInformationEntityList = markingInformationEntityList;
    }

    public List<CosdtmoItmo> getCosdtmoItmosList() {
        return cosdtmoItmosList;
    }

    public void setCosdtmoItmosList(List<CosdtmoItmo> cosdtmoItmosList) {
        this.cosdtmoItmosList = cosdtmoItmosList;
    }

    public String getPermitValidityDate() {
        return permitValidityDate;
    }

    public void setPermitValidityDate(String permitValidityDate) {
        this.permitValidityDate = permitValidityDate;
    }

    public String getOfficerOnDuty() {
        return officerOnDuty;
    }

    public void setOfficerOnDuty(String officerOnDuty) {
        this.officerOnDuty = officerOnDuty;
    }

    public int getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(int phone_Number) {
        this.phone_Number = phone_Number;
    }
}
