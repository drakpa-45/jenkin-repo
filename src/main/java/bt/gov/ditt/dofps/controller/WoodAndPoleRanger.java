package bt.gov.ditt.dofps.controller;

import bt.gov.ditt.dofps.dto.ListDto;
import bt.gov.ditt.dofps.dto.WorkFlowDto;
import bt.gov.ditt.dofps.services.IServiceRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Pema Drakpa on 3/29/2020.
 */
@Controller
@RequestMapping("/rangeWoodAndPole")
public class WoodAndPoleRanger {
    @Autowired
    IServiceRange serviceRange;
    @ResponseBody
    @RequestMapping(value = "/saveClaimedApplication")
    public String saveApplicationRange(WorkFlowDto workFlowDto,ListDto listDto,HttpServletRequest request, ModelMap model) {
        try {
            String pageToReturn = "";

            String appNo = "";
            request.setAttribute("appNo", request.getParameter("value"));
            WorkFlowDto update = serviceRange.saveWPApplicationRange(workFlowDto, listDto,request);
            if(update.getApplication_Number()!="" && update.getApplication_Number()!=null){
                pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>Success marked the application. Print the receipt.</h5></div></span> </div> </div>";
                model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'>success</div></span>");
                return pageToReturn;
            }else{
                pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-danger text-center'><h3>Failed</h3></div></span> </div> </div>";
                model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-danger' ><div class='alert-danger text-center'>Failed</div></span>");
                return pageToReturn;
            }
        }catch (Exception e){
            System.out.print(e);
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info '><div class='alert-info text-danger text-center'><h3>Error!!!! <br> Please try again </h3></div></span>");
            return "" + e;
        }
    }
}
