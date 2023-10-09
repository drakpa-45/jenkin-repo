package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.entitiy.PersonalEntity;
import bt.gov.ditt.dofps.entitiy.TimberEntity;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Pema Drakpa on 1/17/2020.
 */
public interface IDaoRuralTimber {
    public List<NewTimberDto> getParkList(HttpServletRequest request, Integer dzo_Id);
    public String DraftFormat(long appCount);
    public long getTempNo();
    public String saveApplicationDetails(Object obj);
    public List<NewTimberDto> getDzongkhagList();
    public List<NewTimberDto> getDropdownList(String sl_no, String type);
    public String update(NewTimberDto newTimberDto);
    public String saveDocumentDetails(Object obj);
    public NewTimberDto saveUpdateApplication(CitizenDetailsDTO dto, NewTimberDto newTimberDto, String appNo, String draftNo);

    public String deleteData(NewTimberDto newTimberDto);

    public String saveTimber(TimberEntity timberEntity);

    public String updateMemberOfCommunity(String appNo, NewTimberDto newTimberDto);

    public List<NewTimberDto> getVillageList(HttpServletRequest request);

    public Integer getDzoId(HttpServletRequest request, NewTimberDto newTimberDto);

    public WorkFlowDto updateWrkFlwTablePrint(WorkFlowDto workFlow, HttpServletRequest request, String roleId, String role_name, String actorID);

    public WorkFlowDto insertWrkFlw(WorkFlowDto workFlow, HttpServletRequest request);

    public BigInteger CountExit(CitizenDetailsDTO dto, NewTimberDto newTimberDto, HttpServletRequest request, String cons_type);

    public List<NewTimberDto> getDivisionPark(String div_id, NewTimberDto dto);

    public List<claimAdditionalTimberDTO> getApplicantDetails(String cid);

    public List<claimAdditionalTimberDTO> getAttachments(String appNo);

    public Long getServiceId();

    public String saveReclaimDetails(PersonalEntity personalEntity);

    public BigInteger validatePreviousApp(claimAdditionalTimberDTO claimAdditionalTimberDTO);

    public BigInteger validateDuration(claimAdditionalTimberDTO claimAdditionalTimberDTO);

    public WorkFlowDto getApplDetails(String cons_type, CitizenDetailsDTO dto);

    public List<NewTimberDto> getLandCategory();

    public List<NewTimberDto> getForestProduce();

    public List<NewTimberDto> getGrowingSeason();

    public List<CommonDto> getProductList();
}
