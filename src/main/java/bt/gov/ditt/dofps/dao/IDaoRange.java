package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.dto.WorkFlowDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Pema Drakpa on 2/21/2020.
 */
public interface IDaoRange {
    public WorkFlowDto updateAllotmentQty(WorkFlowDto workFlowDto, HttpServletRequest request, Date dealing_date);

    public WorkFlowDto updateWorkFlowStatus(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name, String actorId);

    public WorkFlowDto updateAllotAudit(WorkFlowDto workFlowDto, HttpServletRequest request);

    public WorkFlowDto updateTdsk(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name);

    public WorkFlowDto updateAllotment(WorkFlowDto workFlowDtoList, WorkFlowDto workFlowDto, HttpServletRequest request, Date dealing_date);

    public WorkFlowDto updateAllountedQty(WorkFlowDto workFlowDtoList, HttpServletRequest request, Date dealing_date);

    public WorkFlowDto updateWPAllotQty(WorkFlowDto workFlowDtoList, HttpServletRequest request, Date dealing_date);

    public WorkFlowDto updateBalAllotmentQty(WorkFlowDto workFlowDtoList, HttpServletRequest request, Date dealing_date);

    public WorkFlowDto updatefpApplication(WorkFlowDto workFlowDto, HttpServletRequest request);



    public WorkFlowDto updateBalAllotmentTable(WorkFlowDto workFlowDtoList, WorkFlowDto workFlowDto, HttpServletRequest request);

    public WorkFlowDto updateAllotmentTable(WorkFlowDto workFlowDtoList, WorkFlowDto workFlowDto, HttpServletRequest request);

    //public String getAllotRangeOfficePRL(String application_number);
}
