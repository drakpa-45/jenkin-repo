package bt.gov.ditt.dofps.controller;

import bt.gov.ditt.dofps.dto.ListDto;
import bt.gov.ditt.dofps.dto.WorkFlowDto;
import bt.gov.ditt.dofps.services.IServiceCFO;
import bt.gov.ditt.dofps.services.IServiceCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Pema Drakpa on 2/11/2020.
 */
@Controller
@RequestMapping(value="/cfo")
public class CfoController {
    @Autowired
    IServiceCommon serviceCommon;

    @Autowired
    IServiceCFO serviceCFO;
    @ResponseBody
    @RequestMapping(value = "/saveClaimedApplication")
    public String saveAndForward(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request, ModelMap model) {
        String pageToReturn = "";
        String appNo = "";
        try {
            request.setAttribute("appNo", request.getParameter("value"));
            WorkFlowDto update = serviceCFO.upadteClaimedApp(workFlowDto, listDto, request);
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>Your Application  number " + request.getParameter("value") + " for the rural timber has been approved on " +new Date()+ "..</h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'><h5>Your Application  number " + request.getParameter("value") + " for the rural timber has been approved on " + new Date()+ ".</h5></div></span>");
        } catch (Exception e) {
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-danger text-center'><h5>Sorry! approval of this application number is unsuccessful </h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-danger '><div class='alert-danger text-danger text-center'><h5>Sorry! approval of this application number is unsuccessful </h5></div></span>");
            System.out.print("Sorry! verification of this application number is unsuccessful" + e);
            return pageToReturn;
        }
        return pageToReturn;
    }
}