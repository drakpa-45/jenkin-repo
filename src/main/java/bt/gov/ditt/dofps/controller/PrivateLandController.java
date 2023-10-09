package bt.gov.ditt.dofps.controller;

import bt.gov.ditt.dofps.dto.CitizenDetailsDTO;
import bt.gov.ditt.dofps.dto.ListDto;
import bt.gov.ditt.dofps.dto.NewTimberDto;
import bt.gov.ditt.dofps.dto.WorkFlowDto;
import bt.gov.ditt.dofps.entitiy.DocumentEntity;
import bt.gov.ditt.dofps.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


/**
 * Created by Pema Drakpa on 3/29/2020.
 */
@Controller
@RequestMapping("/privateLand")
public class PrivateLandController {
    @Autowired
    iServiceRuralTimber serviceRuralTimber;

    @Autowired
    IsavePrivateLand isavePrivateLand;

    @Autowired
    IServiceCommon serviceCommon;

    @Autowired
    IServiceCFO serviceCFO;

    @Autowired
    IServiceDealing serviceDealing;

    @Autowired
    IServiceRange serviceRange;

    @RequestMapping(value = "/private", method = RequestMethod.GET)
    public String applyForRuralTimber(ModelMap model,NewTimberDto dto,HttpServletRequest request) {
        // model.addAttribute("message");
        model.addAttribute("cons_type");
        Integer dzo_Id = serviceRuralTimber.getDzoId(request, dto);
        model.addAttribute("Park_List", serviceRuralTimber.getParkList(request, dzo_Id));
        model.addAttribute("DZONGKHAG_LIST", serviceRuralTimber.getDzongkhagList());
        return "cc/permitFromPrivate";
    }

    @ResponseBody
    @RequestMapping(value="selectRangeOffice", method = RequestMethod.GET)
    public List<NewTimberDto> getRangeOffice(ModelMap model, HttpServletRequest request, NewTimberDto dto){
        String div_ID = request.getParameter("Division_Park_Id");
        return  isavePrivateLand.getRangeOffice(div_ID,dto);
    }

    @ResponseBody
    @RequestMapping(value="/saveDoc", method = RequestMethod.POST)
    public String savePrivateLand(CitizenDetailsDTO dto, NewTimberDto CDTO,@RequestParam("files") MultipartFile[] files, ModelMap model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String retval="";
        String insertDetails = isavePrivateLand.savePrivateLand(dto, CDTO, request);
        if(!insertDetails.equalsIgnoreCase("")) {
            DocumentEntity doc = isavePrivateLand.saveDoc(files, insertDetails, CDTO);
            retval = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>Your Application  number " + CDTO.getApplication_Number() + " for the removal of forest produce from private land has been approved on " + new Date() + "</h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'><h5>Your Application  number " + CDTO.getApplication_Number() + " for the removal of forest produce from private land has been approved on " + new Date() + "</h5></div></span>");
            return retval;
        }
        return insertDetails;
    }

    @ResponseBody
    @RequestMapping(value = "/saveClaimedApplicationRanger", method = RequestMethod.POST )
    public String saveAndForwardRanger(WorkFlowDto workFlowDto,ListDto listDto,HttpServletRequest request, ModelMap model, @RequestParam("files") MultipartFile[] files, NewTimberDto newTimberDto){
        String pageToReturn = "";
        String appNo="";
        try {
            request.setAttribute("appNo", request.getParameter("value"));
            String AppNo = request.getParameter("value");

            WorkFlowDto update = serviceRange.saveClamApplication(workFlowDto,listDto, request);

            DocumentEntity doc = isavePrivateLand.saveDoc(files, AppNo,newTimberDto);
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>Your Application  number " + request.getParameter("value") + "  for the removal of forest produce from private land has been submitted on " + new Date() +".</h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'><h5>Your Application  number " + request.getParameter("value") + "  for the removal of forest produce from private land has been submitted on " + new Date() +".</h5></div></span>");
        }catch(Exception e){
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-danger text-center'><h5>Sorry! verification of this application number is unsuccessful </h3></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-danger '><div class='alert-danger text-danger text-center'><h5>Sorry! verification of this application number is unsuccessful </h5></div></span>");
            System.out.print("Sorry! Submission of this application number is unsuccessful"+e);
            return pageToReturn;
        }
        return pageToReturn;
    }

    @ResponseBody
    @RequestMapping(value="/saveMarkingDate", method = RequestMethod.POST)
    public String saveMarkingDate(ModelMap model,WorkFlowDto workFlowDto, HttpServletRequest request,ListDto listDto){
        String pageToReturn = "";
        String appNo="";
        try {
            request.setAttribute("appNo", request.getParameter("value"));
            WorkFlowDto update = serviceRange.saveMarkingDate(workFlowDto, request, listDto);
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5> Application  number" + request.getParameter("value") + "  for the removal of forest produce from private land has been updated to be marked.</h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'><h5>Your Application  number" + request.getParameter("value") + "  for the removal of forest produce from private land has been has been updated to be marked.</h5></div></span>");
        }catch (Exception e){
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-danger text-center'><h5>Sorry! Not able to update this application</h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-danger '><div class='alert-danger text-danger text-center'><h5>Sorry! Not able to update this application</h5></div></span>");
            System.out.print("Sorry! Not able to update this application"+e);
            return pageToReturn;
        }
        return pageToReturn;
    }

    @ResponseBody
    @RequestMapping(value = "/saveClaimedApplicationdealing")
    public String saveAndForwardDealing(WorkFlowDto workFlowDto,ListDto listDto,HttpServletRequest request, ModelMap model) {
        String pageToReturn = "";
        String appNo="";
        try {
            request.setAttribute("appNo", request.getParameter("value"));

            //WorkFlowDto update = serviceCommon.upadteClaimedApp(workFlowDto,listDto, request);
            WorkFlowDto update = serviceDealing.upadteClaimedPLApp(workFlowDto,listDto, request);
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>Your Application  number" + request.getParameter("value") + "  for the removal of forest produce from private land has been approved on " + new Date() +".</h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'><h5>Your Application  number\" + request.getParameter(\"value\") + \"  for the removal of forest produce from private land has been approved on" + new Date() +".</h5></div></span>");
        }catch(Exception e){
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-danger text-center'><h5>Sorry! verification of this application number is unsuccessful </h3></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-danger '><div class='alert-danger text-danger text-center'><h5>Sorry! verification of this application number is unsuccessful </h5></div></span>");
            System.out.print("Sorry! verification of this application number is unsuccessful"+e);
            return pageToReturn;
        }
        return pageToReturn;
    }

    @ResponseBody
    @RequestMapping(value = "/saveClaimedApplication")
    public String saveAndForward(WorkFlowDto workFlowDto,ListDto listDto,HttpServletRequest request, ModelMap model) {
        String pageToReturn = "";
        String appNo="";
        try {
            request.setAttribute("appNo", request.getParameter("value"));
            request.setAttribute("app_type", request.getParameter("app_type"));

            //WorkFlowDto update = serviceCommon.upadteClaimedApp(workFlowDto,listDto, request);
            WorkFlowDto update = serviceCFO.upadteClaimedAppPrivateLand(workFlowDto, listDto, request);
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>Your Application  number" + request.getParameter("value") + "  for the removal of forest produce from private land has been approved on " + new Date() +".</h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'><h5>Your Application  number\" + request.getParameter(\"value\") + \"  for the removal of forest produce from private land has been approved on\" + workFlowDto.getAllot_Date() +\".</h5></div></span>");
        }catch(Exception e){
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-danger text-center'><h5>Sorry! verification of this application number is unsuccessful </h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-danger '><div class='alert-danger text-danger text-center'><h5>Sorry! verification of this application number is unsuccessful </h5></div></span>");
            System.out.print("Sorry! verification of this application number is unsuccessful"+e);
            return pageToReturn;
        }
        return pageToReturn;
    }
}
