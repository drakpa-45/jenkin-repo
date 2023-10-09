package bt.gov.ditt.dofps.controller;

import bt.gov.ditt.dofps.dto.ListDto;
import bt.gov.ditt.dofps.dto.WorkFlowDto;
import bt.gov.ditt.dofps.services.IServiceCommon;
import bt.gov.ditt.dofps.services.IServiceRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Pema Drakpa on 2/21/2020.
 */
@Controller
@RequestMapping("/rangeOfficer")
public class RangeController {
    @Autowired
    IServiceRange serviceRange;

    @Autowired
    IServiceCommon serviceCommon;

    @ResponseBody
    @RequestMapping(value = "/saveClaimedApplication")
    public String saveApplicationRange(WorkFlowDto workFlowDto,ListDto listDto,HttpServletRequest request, ModelMap model) {
        try {
            String pageToReturn = "";

            String appNo = "";
            request.setAttribute("appNo", request.getParameter("value"));
            WorkFlowDto update = serviceRange.saveApplicationRange(workFlowDto, listDto,request);
            if(update.getApplication_Number()!="" && update.getApplication_Number()!=null){
                pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>The application number "+workFlowDto.getApplication_Number()+" has being marked. Please print the receipt.</h5></div></span> </div> </div>";
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

    @ResponseBody
    @RequestMapping(value = "/updateClaimedBalanceQty")
    public String updateClaimedBalanceQty(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request, ModelMap model){
        try{
            String pageToReturn = "";
            String AppNo = request.getParameter("value");
            WorkFlowDto update = serviceRange.updateClaimedBalanceQty(workFlowDto, listDto, request);
            if(update.getApplication_Number()!="" && update.getApplication_Number()!=null){
                pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>Additional balance claimed success fully. Please print the receipt.</h5></div></span> </div> </div>";
                model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'>Additional balance claimed success fully</div></span>");
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


    @RequestMapping(value = "/checkForBalc", method = RequestMethod.GET)
    public String viewStatus(ModelMap model,WorkFlowDto workFlowDto, HttpServletRequest request) {
        return "range_page/applicationStatus";
    }

    @RequestMapping(value="/loadpagetoemptylayout/getApplicationByCID", method = RequestMethod.GET)
    public String getApplicationNoByCID(WorkFlowDto workFlowDto, HttpServletRequest request, ModelMap model){
        String pageToReturn = "";
        String CID = request.getParameter("cid");
        String type = request.getParameter("type");
        BigInteger duration = serviceCommon.CountDuration(CID,type);
        if(duration.intValue() == 1 || duration.intValue() == 2){//model.addAttribute("ApplicationNoByCID", serviceCommon.getApplicationNoByCID(CID, type));
            request.setAttribute("ApplicationNoByCID", serviceCommon.getApplicationNoByCID(CID,type));
            request.setAttribute("ApplicationNo", workFlowDto.getApplication_Number());
            return "range_page/ApplicationDetails";
        }else{
            return "range_page/noBalance";
        }

    }

    @RequestMapping(value = "/loadpagetoemptylayout/statusDetail", method = RequestMethod.GET)
    public String getapplicationDetails(WorkFlowDto workFlowDto, ModelMap model, HttpServletRequest request, @RequestParam(value = "type") String type) {
        String AppNo = request.getParameter("Application_Number");
        request.setAttribute("APPLICATION_DETAILS", serviceCommon.getapplicationDetails(request,AppNo));
            return "range_page/balance_page";
    }

    @ResponseBody
    @RequestMapping(value = "/saveOtherCons")
    public String saveOtherCons(WorkFlowDto workFlowDto,ListDto listDto,HttpServletRequest request, ModelMap model) {
        try {
            String pageToReturn = "";

            String appNo = "";
            request.setAttribute("appNo", request.getParameter("value"));
            WorkFlowDto update = serviceRange.saveOtherCons(workFlowDto, listDto,request);
            if(update.getApplication_Number()!="" && update.getApplication_Number()!=null){
                pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>Successfully mark the application " + request.getParameter("value") + ". Please print the receipt.</h5></div></span> </div> </div>";
                model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'>success</div></span>");
                return pageToReturn;
            }else{
                pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-danger text-center'><h5>Failed</h5></div></span> </div> </div>";
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