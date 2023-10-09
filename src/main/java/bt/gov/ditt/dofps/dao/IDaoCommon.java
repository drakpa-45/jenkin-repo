package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.entitiy.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by Pema Drakpa on 1/24/2020.
 */
public interface IDaoCommon {
    public List<NewTimberDto> populateTaskListforSelectedServices(HttpServletRequest request, String type);

    public long getApplicationSequenceNo(long service_id);

    public String AppNumberFormater(long applicationSequenceNo, long service_id);

    public long getServiceId(String service_short_desc);

    public List<CommonDto> getProductList(String sl_no, String type, String cons_type);

    public int getpriId(int service_id, String role);

    public TaskDetailEntity saveWorkFlow(TaskDetailEntity taskDetailEntity);

    public int getStatus_Id(String submitted);

    public NewTimberDto updateApplicationToClaimed(HttpServletRequest request);

    public WorkFlowDto getapplicationDetails(HttpServletRequest request, String type, String application_number);

    public CommonDto getDocumentDetailsByDocId(String uploadDocId);

    public List<CommonDto> getAttachments(HttpServletRequest request);

    public List<TimberDetailsDto> getProduct_catagory(String application_number);

    public List<CommonDto> getRange(HttpServletRequest request, Integer divisionParkId);

    public List<WorkFlowDto> fetchAppStatus(WorkFlowDto workFlowDto, HttpServletRequest request);

    public List<WorkFlowDto> printDetails(WorkFlowDto workFlowDto, HttpServletRequest request);

    public List<WorkFlowDto> fetchCID(WorkFlowDto workFlowDto, HttpServletRequest request);

    public List<CommonDto> getGroupTaskForDealing(HttpServletRequest request, String userID, String location, String userType);

    public List<CommonDto> getPersonalTaskDealing(HttpServletRequest request, String userID, String user, String locationId);

    public BigInteger CountExit(WorkFlowDto dto);

    public List<WorkFlowDto> printPRLDetails(WorkFlowDto workFlowDto, String request);

    public List<WorkFlowDto> getApplicationNoByCID(String cid, String cons_type);

    public List<CommonDto> getproductListWoodAndPole(String cons_type);

    public claimAdditionalTimberDTO getClaimStatus(HttpServletRequest request);

    public claimAdditionalTimberDTO getReclaimApplicationDetails(HttpServletRequest request);

    public BigInteger CountDuration(String cid, String type);

    public BigInteger getYearValidation(String sl_no, String house_no);

    public List<CommonDto> getGroupTaskRangePRL(HttpServletRequest request, String userID, String locationId, String user);

    public List<CommonDto> getPersonalTaskRangePRL(HttpServletRequest request, String userID, String locationId, String user);

    public List<TimberDetailsDto> getPvtProduct_catagory(String appNo);

    public List<LandDetailsDto> getLandDetails(String appNo);

    public String getCCGewog(String cClocationID);

    public List<CommonDto> getProductListOnline(String cons_desc, String storey, String cons_type);

    public List<CommonDto> productDetailsList(String cons_desc, String cons_type, String product_id);

    public List<AppHistoryDTO> getViewStatusApplication(String applicationNumber);

    public String insertToTaskAudit(String appNo);

    public String insertToWorkFlowAudit(String appNo);

    public String insertTaskdtls(String appNo, String remarks, HttpServletRequest request, String actorId, String assignPrivId, String taskId);

    public String insertToWorkFlow(String appNo, String remarks, HttpServletRequest request, String statusId);

    public String updateFPA(String appNo, HttpServletRequest request, InspectionDTO inspectionDTO);

    public List getDesignation();

    public List getTimberType(String cons_type);

    public void saveUpdate(Object object);

    public List<InspectionTeamEntity> getInspectionTeamMembers(String applicationNumber);

    public List<PastRecordDetailEntity> getPastRecordDetails(String applicationNumber);

    public BigInteger checkForValidation(String house_hold, String cons_type);

    public String updateAllot(TimberDetailsDto timberDetails, String appNo, String remarks);

    public String updatePaymentStatus(String appNo, OnlineTimberDTO timberDto);

    public String updatePermitExpiryDate(String appNo, WorkFlowDto timberDto);

    public void saveEntity(Object object);

    public void updateOfflinePayment(String appNo, String permitNumber, Date permitExpiryDate, Date dateOfReceipt);

    public List<MarkingInformationEntity> getMarkingInformation(String appNo);

    public List<CommonDto> getSpeciesName(HttpServletRequest request);

    public List<CosdtmoItmo> getCosdtmoItmoDtls(String appNo);

    public String updatePermitDateOfficerOnDuty(String appNo, String permitValidityDate, String officerOnDuty);

    public String insertToAllotmentAudit(String appNo);

    public List<CommonDto> getConstructionStatus();

    public String updateWorkFlowRange(String appNo, String remarks, HttpServletRequest request, String statusId);

    public String forwardToOtherRange(String appNo, String remarks, int rangeId);

    public List<NewTimberDto> getAllParkList();

    public String forwardToOtherCFO(String appNo, String remarks, int parkId);

    public long getPermitSequenceNo(int serviceId);

    public String updateMarkingArea(String appNo, OnlineTimberDTO timberDto);

    List<NewTimberDto> getLandCategory();

    List<NewTimberDto> getForestProduce();

    List<NewTimberDto> getPrivateLandProduceSpecies(char species_type);

    public String getConsType(String appNo);
}
