package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.common.ResponseMessage;
import bt.gov.ditt.dofps.dto.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Santosh on 7/7/2022.
 */
public interface IServiceCitizen {


    public OnlineTimberDTO saveOnlineTimberApplication(OnlineTimberDTO timberDto, HttpServletRequest request);

    public List<NewTimberDto> getDropdownList(String id, String type, HttpServletRequest request);

    public List<OnlineTimberDTO> getApplicationList(String appNo);

    public ResponseMessage getTimberReplacementRequest(String appNo);

    public ResponseMessage submitTimberRR(String appNo, WorkFlowDto dto, HttpServletRequest request);

    public ResponseMessage checkForMarking(String appNo);

    public ResponseMessage scheduleMarkingDate(String appNo, String markingDate, HttpServletRequest request);

    public ResponseMessage checkForSawingPermit(String appNo);

    public ResponseMessage scheduleSawingPermitDate(String appNo, String sawingPermitDate, HttpServletRequest request, String nameOfOperator, String licenseNo, String locationOfSawmill, String rateOfSawing, int mode_of_Swing_Id);

    public ResponseMessage fetchAllotmentDtls(String appNo);

    public ResponseMessage isDateAvailable(String thisDate, Integer allot_Range_Officer);

    public ResponseMessage getTimberExtensionRequest(String appNo);

    public ResponseMessage submitTimberPE(String appNo, WorkFlowDto dto, HttpServletRequest request, MultipartFile[] files, String remarks);

    public ResponseMessage check4Completion(String appNo);

    public ResponseMessage updateCompletionDate(String appNo, String completionDate, HttpServletRequest request);

    public WorkFlowDto generateAllotmentOrderDtls(String applicationNumber);

    public ResponseMessage fetchSowingPermitDtls(String appNo);

    public ResponseMessage fetchPermitDtls(String appNo);

    public OnlineTimberDTO submitManualRecord(OnlineTimberDTO timberDto, HttpServletRequest request, MultipartFile[] files);

    public ResponseMessage submitPrivateLand(OnlineTimberDTO citizenDetailsDTO, NewTimberDto newTimberDto, MultipartFile[] files, HttpServletRequest request);

    public ResponseMessage fetchForestProduceDtls(String appNo, HttpServletRequest request);

    public OnlineTimberDTO saveWoodandPoles(OnlineTimberDTO timberDto, HttpServletRequest request);
}
