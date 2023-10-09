package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.common.ResponseMessage;
import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.entitiy.InspectionTeamEntity;
import bt.gov.ditt.dofps.entitiy.PastRecordDetailEntity;
import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Pema Drakpa on 1/24/2020.
 */
public interface IServiceCommon {
    public List<NewTimberDto> populateTaskListforSelectedServices(HttpServletRequest request, String type);

    public List<CommonDto> getProductList(String cons_desc, String type, String cons_type);

    public UserRolePrivilege populateUserRolePrivilegeHierarchy(UserRolePrivilege userRolePrivilege, String roleId, HttpServletRequest request);

    public NewTimberDto updateApplicationToClaimed(HttpServletRequest request);

    public WorkFlowDto getapplicationDetails(HttpServletRequest request, String type);

    public CommonDto getDocumentDetailsByDocId(String uploadDocId);

    public List<CommonDto> getRange(HttpServletRequest request, Integer divisionParkId);

   // public WorkFlowDto upadteClaimedApp(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request);


    public List<WorkFlowDto> fetchAppStatus(WorkFlowDto workFlowDto, HttpServletRequest request);

    public List<WorkFlowDto>  printDetails(WorkFlowDto workFlowDto, HttpServletRequest request);

    public List<WorkFlowDto>  fetchCID(WorkFlowDto workFlowDto, HttpServletRequest request);

    public List<CommonDto> getGroupTaskDealing(HttpServletRequest request, String userID, String location, String userType);

    public List<CommonDto> getPersonalTaskDealing(HttpServletRequest request, String currentUser, String user, String locationId);

    public String getApplicationValidation(WorkFlowDto workFlowDto);

    public List<WorkFlowDto> printPRLDetails(WorkFlowDto workFlowDto, String request);

    public List<WorkFlowDto> getApplicationNoByCID(String cid, String cons_type);

    public List<CommonDto> getproductListWoodAndPole(String cons_type);

    public claimAdditionalTimberDTO getClaimStatus(HttpServletRequest request);

    public claimAdditionalTimberDTO getreclaimapplicationDetails(HttpServletRequest request, String application_details);

    public BigInteger CountDuration(String cid, String type);

    public List<CommonDto> getGroupTaskRangePRL(HttpServletRequest request, String userID, String locationId, String user);

    public List<CommonDto> getPersonalTaskRangePRL(HttpServletRequest request, String userID, String locationId, String user);

    public String getCCGewog(String cClocationID);

    public List<CommonDto> getProductListOnline(String cons_desc, String storey, String cons_type);

    public List<CommonDto> productDetailsList(String cons_desc, String cons_type, String product_id);

    public List<AppHistoryDTO> getViewStatusApplication(String applicationNumber);

    public ResponseMessage forwardToGAO(String appNo, String remarks, HttpServletRequest request);

    public ResponseMessage updateInspectionDetails(InspectionDTO inspectionDTO, HttpServletRequest request, MultipartFile[] files);

    public List getDesignation();

    public List getTimberType(String cons_type);

    public List<InspectionTeamEntity> getInspectionTeamMembers(String applicationNumber);

    public List<PastRecordDetailEntity> getPastRecordDetails(String applicationNumber);

    public ResponseMessage gupApprove(String appNo, String remarks, HttpServletRequest request);

    public ResponseMessage cfoApprove(String appNo, String remarks, HttpServletRequest request, OnlineTimberDTO timberDto);

    public ResponseMessage ocRangeApprove(String appNo, String remarks, HttpServletRequest request, OnlineTimberDTO timberDto);

    public BigInteger checkForValidation(String house_hold, String cons_type);

    public ResponseMessage updatePayment(String appNo, String remarks, HttpServletRequest request, int amount, String modeOfPayment, String receiptNo);

    public ResponseMessage approveRO(String appNo, String remarks, HttpServletRequest request, InspectionDTO inspectionDTO);

    public ResponseMessage approveSWP(String appNo, String remarks, HttpServletRequest request, InspectionDTO inspectionDTO, String permitValidityDate, String officerOnDuty);

    public ResponseMessage approvePermitExtension(String appNo, String remarks, HttpServletRequest request);

    public ResponseMessage verifyReplacement(String appNo, String remarks, HttpServletRequest request, MultipartFile[] files);

    public List<CommonDto> getSpeciesName(HttpServletRequest request);

    public List<CommonDto> getConstructionStatus();

    public ResponseMessage forwardToOtherRange(String appNo, String remarks, HttpServletRequest request, int rangeId);

    public List<NewTimberDto> getAllParkList();

    public ResponseMessage forwardToOtherCFO(String appNo, String remarks, HttpServletRequest request, int parkId);

    public ResponseMessage reject(String appNo, String remarks, HttpServletRequest request);

    public List<NewTimberDto> getLandCategory();

    public List<NewTimberDto> getForestProduce();

    public List<NewTimberDto> getPrivateLandProduceSpecies(char species_Type);
}
