package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.dto.CommonDto;
import bt.gov.ditt.dofps.dto.PrivateLandDto;
import bt.gov.ditt.dofps.dto.WorkFlowDto;
import bt.gov.ditt.dofps.entitiy.TimberEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Pema Drakpa on 2/21/2020.
 */
public interface IDaoDealing {
    public WorkFlowDto rejected_application(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name);

    public WorkFlowDto update_t_ap_rejected_application(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name);

    public WorkFlowDto insertAudit(WorkFlowDto workFlowDto, HttpServletRequest request);

    public WorkFlowDto updateWrkFlw(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name, String actorID);

    public WorkFlowDto updateAllotment(WorkFlowDto workFlowDto, WorkFlowDto workFlow, HttpServletRequest request);

    public WorkFlowDto update_fp_app(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name);

    public WorkFlowDto insertAlloAudit(WorkFlowDto workFlowDto, HttpServletRequest request);

    public WorkFlowDto insertWrkFlw(WorkFlowDto workFlowDto, HttpServletRequest request);

    public WorkFlowDto insert_task(WorkFlowDto workFlowDto, HttpServletRequest request);

    public WorkFlowDto upadteClaimedApp(WorkFlowDto workFlow, HttpServletRequest request, String roleId, String role_name);

    public List<CommonDto> getRejList();

    public String getRejReason(WorkFlowDto workFlowDto);

    public WorkFlowDto updateWrokFlow(WorkFlowDto workFlow, HttpServletRequest request, String roleId, String role_name, String actorId);

    public WorkFlowDto update_fp_application(WorkFlowDto workFlow, HttpServletRequest request, String roleId, String role_name);

    public WorkFlowDto update_fp_Application(WorkFlowDto workFlow, HttpServletRequest request, String roleId, String role_name);

    public WorkFlowDto upadteMarkedDate(WorkFlowDto workFlow, HttpServletRequest request, String roleId, String role_name);

    public WorkFlowDto updateWrokFlow_dtls(WorkFlowDto workFlow, HttpServletRequest request, String roleId, String role_name, String actorId);

    public WorkFlowDto updateAllot(WorkFlowDto workFlowDtoList, WorkFlowDto workFlow, HttpServletRequest request);

    public String updateAllotmentPvtLand(TimberEntity timberEntity);

    public List<CommonDto> getPRLRejList();
}
