package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.dto.WorkFlowDto;
import bt.gov.ditt.dofps.entitiy.PersonalEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by USER on 4/8/2020.
 */
public interface IsavePrivateLandDao {
    public String savePrivateLand(PersonalEntity personalEntity);

    public long getServiceId();

    public WorkFlowDto getapplicationDetails(HttpServletRequest request, String type);

    public WorkFlowDto getapplDetails(String application_number, String application_details);

    public String updatePrivateProduceDetailsByRO(String appNo, HttpServletRequest request, WorkFlowDto workFlowDto);

    public String updateApprovedDate(HttpServletRequest request, String appNo, String remarks);
}
