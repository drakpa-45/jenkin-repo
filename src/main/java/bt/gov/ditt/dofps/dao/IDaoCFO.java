package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.dto.WorkFlowDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Pema Drakpa on 2/12/2020.
 */
public interface IDaoCFO {
    public WorkFlowDto upadteClaimedApp(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name);

    public WorkFlowDto updateAppAudit(WorkFlowDto workFlowDto, HttpServletRequest request);

    public WorkFlowDto updateAllotAudit(WorkFlowDto workFlowDto, HttpServletRequest request);

    public WorkFlowDto updateTdskAudit(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name);

    public WorkFlowDto updateWrkAudit(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name);

    public WorkFlowDto updateAllot(WorkFlowDto workFlowDtoList, WorkFlowDto workFlowDto, HttpServletRequest request);

    public WorkFlowDto upadteWrks(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name, String actorID);

    public WorkFlowDto updateTdsk(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name);

    public WorkFlowDto updateAllotWP(WorkFlowDto workFlowDtoList, WorkFlowDto workFlowDto, HttpServletRequest request);

    public WorkFlowDto updateAllotment(WorkFlowDto workFlowDtoList, WorkFlowDto workFlowDto, HttpServletRequest request);

    public String getAllotRangeOffice(String application_number);
}
