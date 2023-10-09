package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.entitiy.DocumentEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Pema Drakpa on 1/16/2020.
 */
public interface iServiceRuralTimber {

   public List<NewTimberDto> getParkList(HttpServletRequest request, Integer dzo_Id);

    public String saveApplicationDetails(String cons_type, CitizenDetailsDTO dto, String cons_desc, NewTimberDto newTimberDto, String type, HttpServletRequest request, String locationId, String juri_Type_Id);

    public List<NewTimberDto>getDzongkhagList();

    public List<NewTimberDto> getDropdownList(String sl_no, String type);

    public DocumentEntity saveDoc(MultipartFile[] files, String appNo, HttpServletRequest request, String cons_type, NewTimberDto newTimberDto);

    public NewTimberDto saveFinalApplciationDetails(CitizenDetailsDTO dto, NewTimberDto newTimberDto, String type, HttpServletRequest request);

    public List<NewTimberDto> getVillageList(HttpServletRequest request);

    public Integer getDzoId(HttpServletRequest request, NewTimberDto dto);

    public WorkFlowDto printApplication(WorkFlowDto dto, HttpServletRequest request);

    public List<claimAdditionalTimberDTO> getApplicantDetails(String cid);

    public List<claimAdditionalTimberDTO> viewAttachedDocs(String appNo);

    public String saveReclaimDetails(claimAdditionalTimberDTO claimAdditionalTimberDTO, String claimStatus, HttpServletRequest request);

    public WorkFlowDto getApplDetails(String cons_type, CitizenDetailsDTO dto);

    public List<NewTimberDto> getGrowingSeason();

    public List<CommonDto> getProductList();

}
