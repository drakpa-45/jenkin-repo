package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.dao.IDaoGewog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by deepa on 7/13/2022.
 */
@Service
public class ServiceGewog implements IServiceGewog {

    @Autowired
    IDaoGewog dao;
}
