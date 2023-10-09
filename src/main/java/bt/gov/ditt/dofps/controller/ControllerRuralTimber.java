package bt.gov.ditt.dofps.controller;

import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.entitiy.DocumentEntity;
import bt.gov.ditt.dofps.services.IServiceCommon;
import bt.gov.ditt.dofps.services.iServiceRuralTimber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Pema Drakpa on 1/16/2020.
 */
@Controller
@RequestMapping("/ruralTimber")
public class ControllerRuralTimber {
    @Autowired
    iServiceRuralTimber serviceRuralTimber;

    @Autowired
    IServiceCommon serviceCommon;

    @RequestMapping(value = "/loadpagetoemptylayout/loadapplication")
    /*public String loadApplication(ModelMap model,NewTimberDto newTimberDto, HttpServletRequest request,@RequestParam("page_type") String page_type,@RequestParam("serviceId") String serviceId){*/
    public String loadApplication(ModelMap model, NewTimberDto newTimberDto, HttpServletRequest request) {
        request.setAttribute("page_type", request.getParameter("page_type"));
        request.setAttribute("serviceId", request.getParameter("serviceId"));
        try {
            request.setAttribute("Group_Task_List", serviceCommon.populateTaskListforSelectedServices(request, "8"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Exception while populating application number on group task" + e);
        }
        return "cc/cc_tkls";
    }

    @RequestMapping(value = "/loadpagetoemptylayout/openAndClaimApplication")
    public String openAndClaimApplication(ModelMap model, NewTimberDto newTimberDto, HttpServletRequest request) {
        request.setAttribute("page_type", request.getParameter("page_type"));
        request.setAttribute("serviceId", request.getParameter("serviceId"));
       // request.setAttribute("range_list", serviceCommon.getRange(request, divisionParkId));
        String pageToReturn = "";
        if (request.getParameter("action_type").equalsIgnoreCase("viewApp")) {
            request.setAttribute("APPLICATION_DETAILS", serviceCommon.getapplicationDetails(request, "application_details"));
            pageToReturn = "cc/ruralTimberPermitCC";
        } else {
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-danger text-center'>Not able to claim this application. Please try again or contact ICT  </div></span>");
            return "common/acknowledgementPage";
        }
        return pageToReturn;
    }

    @RequestMapping(value = "/ruralTimberApplication", method = RequestMethod.GET)
    public String applyForRuralTimber(ModelMap model, HttpServletRequest request,NewTimberDto dto, @RequestParam("cons_type") String cons_type) {
        model.addAttribute("cons_type", cons_type);
        Integer dzo_Id = serviceRuralTimber.getDzoId(request, dto);
        model.addAttribute("Park_List", serviceRuralTimber.getParkList(request,dzo_Id));
        model.addAttribute("Cons_Village_List", serviceRuralTimber.getVillageList(request));
        model.addAttribute("DZONGKHAG_LIST", serviceRuralTimber.getDzongkhagList());
        if (cons_type.equalsIgnoreCase("n")) {
            int month;
            GregorianCalendar date = new GregorianCalendar();
            month = date.get(Calendar.MONTH);
            month = month+1;
            String currentMonth = String.valueOf(month);

            //Applying season
            List<NewTimberDto> growingSeason = serviceRuralTimber.getGrowingSeason();

            StringBuilder season = new StringBuilder("");
            for(int i=0; i< growingSeason.size(); i++){
                season.append(growingSeason.get(i).getHeader_id()).append(",");
            }

            String seasonFetched = season.toString();

            if(seasonFetched.contains(currentMonth)){
                return "cc/ruralTimber";
            }else{
                return "cc/growingSeasonPage";
            }
        } else if (cons_type.equalsIgnoreCase("r")) {
            int month;
            GregorianCalendar date = new GregorianCalendar();
            month = date.get(Calendar.MONTH);
            month = month+1;
            String currentMonth = String.valueOf(month);

            //Applying season
            List<NewTimberDto> growingSeason = serviceRuralTimber.getGrowingSeason();
            StringBuilder season = new StringBuilder("");
            for(int i=0; i< growingSeason.size(); i++){
                season.append(growingSeason.get(i).getHeader_id()).append(",");
            }

            String seasonFetched = season.toString();

            if(seasonFetched.contains(currentMonth)){
                return "cc/Renovation";
            }else{
                return "cc/growingSeasonPage";
            }
        } else {
            return "cc/otherConstruction";
        }
    }

    @RequestMapping(value = "/claimAdditionalTimber")
    public String claimAdditionalTimber(HttpServletRequest request){
        return "cc/claimAdditionalTimber";
    }

    @ResponseBody
    @RequestMapping(value = "/getApplicantDetails", method = RequestMethod.GET)
    public List<claimAdditionalTimberDTO> getApplicantDetails(@RequestParam("cid") String  cid){
        return serviceRuralTimber.getApplicantDetails(cid);
    }

    @ResponseBody
    @RequestMapping(value = "/viewAttachedDocs", method = RequestMethod.GET)
    public List<claimAdditionalTimberDTO> viewAttachedDocs(ModelMap model,@RequestParam("appNo") String appNo){
        /*model.addAttribute("AttachedDocs", serviceRuralTimber.viewAttachedDocs(appNo));
        return "cc/claimAdditionalTimber";*/
        return serviceRuralTimber.viewAttachedDocs(appNo);
    }

    @ResponseBody
    @RequestMapping(value="/saveReclaimDetails" , method = RequestMethod.POST)
    public String saveReclaimDetails(claimAdditionalTimberDTO claimAdditionalTimberDTO,@RequestParam("type") String ClaimStatus,@RequestParam("files") MultipartFile[] files, HttpServletRequest request, ModelMap model, NewTimberDto newTimberDto){
        HttpSession session = request.getSession();
        String retval="";
        String cons_type = "n";
        String insertDetails = serviceRuralTimber.saveReclaimDetails(claimAdditionalTimberDTO, ClaimStatus, request);

        if(insertDetails.equalsIgnoreCase("failed")){
            retval = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>You have already applied previously. Please wait for the approval.<br/>Or the balance is not claimed with in the time period of 3 years</h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'><h5>You have already applied previously. Please wait for the approval.<br/>Or the balance is not claimed with in the time period of 3 years</h5></div></span>");
            return retval;
        }else if(!insertDetails.equalsIgnoreCase("")) {
            DocumentEntity doc = serviceRuralTimber.saveDoc(files, claimAdditionalTimberDTO.getApplication_Number(), request, cons_type, newTimberDto);
            retval = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>Your Application  number " + claimAdditionalTimberDTO.getApplication_Number() + " for reclaiming additional timber has been submitted on " + new Date() + "</h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'><h5>Your Application  number " + claimAdditionalTimberDTO.getApplication_Number() + " for reclaiming additional timber has been submitted on " + new Date() + "</h5></div></span>");
            return retval;
        }
        return insertDetails;
    }

    @ResponseBody
    @RequestMapping(value = "/saveApplication", method = RequestMethod.POST)
    public String saveApplication(ModelMap model,@RequestParam("cons_type") String cons_type, @RequestParam("type") String type, String cons_desc, CitizenDetailsDTO dto, NewTimberDto newTimberDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String roleId = "";
        String roleName = "";
        String roleCode = "";
        String userType = "";
        String LocationId = "";
        String Juri_Type_Id = "";

        try {
            UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
            roleId = userBean.getCurrentRole().getRoleId();
            roleName = userBean.getCurrentRole().getRoleName();
            userType = userBean.getUserType();
            roleCode = userBean.getCurrentRole().getRoleName();

            dto.setRole_Id(Integer.parseInt(String.valueOf(roleId)));
            dto.setUser_Id(roleCode);

            for (int n = 0; n < userBean.getJurisdictions().length; n++) {
                LocationId = userBean.getJurisdictions()[n].getLocationID();
                //Juri_Type_Id = userBean.getJurisdictions()[n].getJurisdiction();
                Juri_Type_Id = "0";
                dto.setRole_Id(Integer.parseInt(roleId));
                dto.setJuri_Type_Id(Integer.parseInt(Juri_Type_Id));
                dto.setActor_Id(userBean.getCurrentRole().getRoleId());
                dto.setActor_Name(userBean.getCurrentRole().getRoleName());
            }
            /*for (int m = 0; m < userBean.getRoles().length; m++) {
                for (int i = 0; i < userBean.getRoles()[m].getServices().length; i++) {
                    Service svc = userBean.getCurrentRole().getServices()[i];
                    for (int j = 0; j < svc.getPrivileges().length; j++) {
                        Privilege priv = svc.getPrivileges()[j];
                    }
                }
            }*/
            if (type.equalsIgnoreCase("personal_details")) {
                String insertDetails = serviceRuralTimber.saveApplicationDetails(cons_type, dto, cons_desc, newTimberDto, type, request,LocationId,Juri_Type_Id);
                if(insertDetails.equalsIgnoreCase("exist") && cons_type.equalsIgnoreCase("n")){
                    //request.setAttribute("applicantDetails", serviceRuralTimber.getApplDetails(cons_type,dto));
                    return "n";
                }else if(insertDetails.equalsIgnoreCase("exist") && cons_type.equalsIgnoreCase("r")){
                    return "r";
                }else if(insertDetails.equalsIgnoreCase("exist") && cons_type.equalsIgnoreCase("o")){
                    return "o";
                }else {
                    return insertDetails;
                }
            } else if (type.equalsIgnoreCase("save_timber_details")) {
                String insertDetails = serviceRuralTimber.saveApplicationDetails(cons_type, dto, cons_desc, newTimberDto, type, request,LocationId,Juri_Type_Id);
                return insertDetails;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/productListOnline", method = RequestMethod.GET)
    public List<CommonDto> getProductListOnline(@RequestParam("cons_desc") String cons_desc, @RequestParam("storey") String storey, @RequestParam("cons_type") String cons_type) {
        return serviceCommon.getProductListOnline(cons_desc, storey, cons_type);
    }

    @ResponseBody
    @RequestMapping(value = "/productDetailsList", method = RequestMethod.GET)
    public List<CommonDto> getProductDetailsList(@RequestParam("cons_desc") String cons_desc,@RequestParam("cons_type") String cons_type,@RequestParam("product_id") String product_id) {
        return serviceCommon.productDetailsList(cons_desc, cons_type,product_id);
    }

    @ResponseBody
    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public List<CommonDto> getProductList(@RequestParam("cons_desc") String cons_desc, @RequestParam("type") String type, @RequestParam("cons_type") String cons_type) {
        return serviceCommon.getProductList(cons_desc, type, cons_type);
    }

    @ResponseBody
    @RequestMapping(value = "/productListWoodAndPole", method = RequestMethod.GET)
    public List<CommonDto> getproductListWoodAndPole(@RequestParam("cons_type") String cons_type){
        return serviceCommon.getproductListWoodAndPole(cons_type);
    }

    @ResponseBody
    @RequestMapping(value = "/getLocationDetails", method = RequestMethod.GET)
    public List<NewTimberDto> locationDetails(@RequestParam("sl_no") String sl_no, @RequestParam("type") String type, ModelMap model) {
        return serviceRuralTimber.getDropdownList(sl_no, type);
        //return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveDoc", method = RequestMethod.POST)
    public String saveFinalApplciationDetails( @RequestParam("files") MultipartFile[] files,@RequestParam("type") String type, CitizenDetailsDTO dto, NewTimberDto newTimberDto, HttpServletRequest request, ModelMap model, @RequestParam("cons_type") String cons_type) {
        HttpSession session = request.getSession();
        String retval = "";
        try {
            NewTimberDto insertDetails = serviceRuralTimber.saveFinalApplciationDetails(dto, newTimberDto, type, request);

            DocumentEntity doc = serviceRuralTimber.saveDoc(files, insertDetails.getApplication_Number(), request, cons_type, newTimberDto);
            if (type.equalsIgnoreCase("update_final_data") && cons_type.equalsIgnoreCase("n")) {
                retval = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>Your Application  number " + insertDetails.getApplication_Number() + " for the rural timber(New Construction) is Submitted on " +new Date() +".<br> The validity of this permit is for two years for new house construction and one year for renovation of house.</h5></div></span> </div> </div>";
                model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'><h5>Your Application  number " + insertDetails.getApplication_Number() + " for the rural timber(New Construction) is Submitted on " + new Date()  +".<br> The validity of this permit is for two years for new house construction and one year for renovation of house.</h3></div></span>");
            } else if (type.equalsIgnoreCase("update_final_data") && cons_type.equalsIgnoreCase("r")) {
                retval = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>Your Application  number " + insertDetails.getApplication_Number() + " for the rural timber(Renovation) is Submitted on " + new Date() +".<br> The validity of this permit is for two years for new house construction and one year for renovation of house.</h5></div></span> </div> </div>";
                model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'><h5>Your Application  number " + insertDetails.getApplication_Number() + " for the rural timber(New Construction) is Submitted on " + new Date()  +".<br> The validity of this permit is for two years for new house construction and one year for renovation of house.</h5></div></span>");
            } else if (type.equalsIgnoreCase("update_final_data") && cons_type.equalsIgnoreCase("o")) {
                retval = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>Your Application  number " + insertDetails.getApplication_Number() + " for the rural timber(Other Construction) is Submitted on " +new Date()  +".<br> The validity of this permit is for two years for new house construction and one year for renovation of house.</h5> </div></span> </div> </div>";
                model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'><h5>Your Application  number " + insertDetails.getApplication_Number() + " for the rural timber(Other Construction) is Submitted on " + new Date()  +".<br> The validity of this permit is for two years for new house construction and one year for renovation of house.</h5> </div></span>");
            }
            return retval;
        } catch (Exception e) {
            System.out.print(e);
            retval = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h3>Your Application is not submitted <br> Please try again </h3></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info '><div class='alert-info text-danger text-center'><h3>Your Application is not submitted <br> Please try again </h3></div></span>");
            return retval;
        }
    }
    @RequestMapping(value = "/viewAppStatus", method = RequestMethod.GET)
    public String viewStatus(ModelMap model,WorkFlowDto workFlowDto, HttpServletRequest request) {
        model.addAttribute("cidNo","");
        return "common/viewStatus";
    }

    @RequestMapping(value = "/cidDetail", method = RequestMethod.GET)
    public String fetchCID(CitizenDetailsDTO dto, WorkFlowDto workFlowDto, ModelMap model, HttpServletRequest request) {
        model.addAttribute("cidDetails", serviceCommon.fetchCID(workFlowDto, request));
        model.addAttribute("cidNo",dto.getCid_Number());
        return "common/viewStatus";
    }

    @RequestMapping(value = "/loadpagetoemptylayout/statusDetail", method = RequestMethod.GET)
    public String fetchStatusDtls(WorkFlowDto workFlowDto, ModelMap model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv=(UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        request.setAttribute("applicationDetails", serviceCommon.fetchAppStatus(workFlowDto, request));
        if(userRolePriv.getUserRoles().getUserRole().get(0).getRoleName().equalsIgnoreCase("CC Operator")/* ||
                userRolePriv.getUserRoles().getUserRole().get(0).getRoleName().equalsIgnoreCase("Beat Officer") ||
                userRolePriv.getUserRoles().getUserRole().get(0).getRoleName().equalsIgnoreCase("Range Officer")*/) {
            return "common/statusDetails";
        }else{
            return "common/statusDetailsOnly";
        }
    }

    @RequestMapping(value = "/loadpagetoemptylayout/printDetails")
    public String printDetails(WorkFlowDto workFlowDto, ModelMap model, HttpServletRequest request, @RequestParam("application_Number") String appNo) {
        //String appNo = request.getParameter("application_Number");
        //if(workFlowDto.getApplication_Type().equalsIgnoreCase("PRL,PRL,PRL,PRL,PRL")){
        if(appNo.startsWith("184")){
            request.setAttribute("applicationDetails",serviceCommon.printPRLDetails(workFlowDto, appNo));
            return "common/printPRLApplication";
        }else{
            request.setAttribute("applicationDetails",serviceCommon.printDetails(workFlowDto, request));
            return "common/printApplication";
        }
    }


    @RequestMapping(value = "/afterPrintingApp", method = RequestMethod.POST)
    public String printApplication(WorkFlowDto dto, ModelMap model, HttpServletRequest request) {
        try{
            WorkFlowDto  update = serviceRuralTimber.printApplication(dto, request);
            if(update.getCurrent_Status().equalsIgnoreCase("success")){
                model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-info col-12 text-center'>Successfully printed the application!!!");
            }else {
                model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-danger col-12 text-center'>Not update in the system. Print again!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "common/acknowledgementPage";
    }
}

