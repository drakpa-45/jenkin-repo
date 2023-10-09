package bt.gov.ditt.dofps.trackApplication;

import bt.gov.ditt.dofps.dto.AppHistoryDTO;
import bt.gov.ditt.dofps.services.IServiceCommon;
import bt.gov.ditt.dofps.services.ServiceCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pema Drakpa on 10/26/2022.
 */
@Controller
@RequestMapping("/track")
public class TrackApplication {

    @Autowired
    IServiceCommon serviceCommon;

    @RequestMapping(value = "/initiate/getApplicationDetails")
    public String getApplicationDetails( ModelMap model,HttpServletRequest request,String applicationNo) {
        List<AppHistoryDTO> appHistoryDTO = new ArrayList<AppHistoryDTO>();
        appHistoryDTO=serviceCommon.getViewStatusApplication(applicationNo);
        request.setAttribute("appHistoryDTO", appHistoryDTO);
        model.addAttribute("appNo",applicationNo);
        return "trackApplication/trackApp";
    }
}
