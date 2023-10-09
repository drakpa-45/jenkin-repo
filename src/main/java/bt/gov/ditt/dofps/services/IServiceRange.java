package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.dto.ListDto;
import bt.gov.ditt.dofps.dto.WorkFlowDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Pema Drakpa on 2/21/2020.
 */
public interface IServiceRange {
    public WorkFlowDto saveApplicationRange(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request);

    public WorkFlowDto saveOtherCons(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request);

    public WorkFlowDto saveClamApplication(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request);

    public WorkFlowDto saveMarkingDate(WorkFlowDto workFlowDto, HttpServletRequest request, ListDto listDto);

    public WorkFlowDto updateClaimedBalanceQty(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request);

    public WorkFlowDto saveWPApplicationRange(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request);
}
