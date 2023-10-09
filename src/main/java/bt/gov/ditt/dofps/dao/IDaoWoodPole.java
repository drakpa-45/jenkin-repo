package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.dto.CitizenDetailsDTO;
import bt.gov.ditt.dofps.dto.CommonDto;
import bt.gov.ditt.dofps.dto.NewTimberDto;
import bt.gov.ditt.dofps.entitiy.TimberEntity;

import java.util.List;

/**
 * Created by Pema Drakpa on 2/19/2020.
 */
public interface IDaoWoodPole {

    public List<CommonDto> getProductDetails(String cons_desc);

    public List<CommonDto> getUnitDetails(String sl_no, String type);

    public long getServiceId();

    public NewTimberDto saveUpdateApplication(CitizenDetailsDTO dto, NewTimberDto newTimberDto, String applicationNumber, String application_number);

    public String saveTimberdtls(TimberEntity timberEntity);
}
