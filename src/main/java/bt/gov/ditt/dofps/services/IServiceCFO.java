package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.dto.ListDto;
import bt.gov.ditt.dofps.dto.WorkFlowDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Pema Drakpa on 2/12/2020.
 */
public interface IServiceCFO {
    public WorkFlowDto upadteClaimedApp(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request);

    public WorkFlowDto upadteClaimedAppPrivateLand(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request);

    public WorkFlowDto upadteClaimedWPApp(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request);
}
