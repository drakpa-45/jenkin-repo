package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.dao.IDaoFencingAndWood;
import bt.gov.ditt.dofps.dto.CommonDto;
import bt.gov.ditt.dofps.dto.NewTimberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Pema Drakpa on 3/29/2020.
 */
@Service
public class ServiceFireWoodAndFencingPole implements iServiceFireWoodAndFencingPole{
    @Autowired
    IDaoFencingAndWood daoFencingAndWood;

    @Override
    @Transactional
    public List<NewTimberDto> populateTaskList(HttpServletRequest request, String type, String app_type) {
        List<NewTimberDto> dto = daoFencingAndWood.populateTaskList(request, type,app_type);
        return dto;
    }

    @Override
    @Transactional
    public List<CommonDto> getRejList() {
        List<CommonDto> dto = daoFencingAndWood.getRejList();
        return dto;
    }
}
