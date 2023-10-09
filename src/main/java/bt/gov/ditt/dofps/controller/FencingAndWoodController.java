package bt.gov.ditt.dofps.controller;

import bt.gov.ditt.dofps.dto.ListDto;
import bt.gov.ditt.dofps.dto.NewTimberDto;
import bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO;
import bt.gov.ditt.dofps.dto.WorkFlowDto;
import bt.gov.ditt.dofps.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Pema Drakpa on 3/29/2020.
 */
@Controller
@RequestMapping("/fencingAndWood")
public class FencingAndWoodController {
    @Autowired
    iServiceFireWoodAndFencingPole serviceFireWoodAndFencingPole;
    @Autowired
    IServiceDealing serviceDealing;
    @Autowired
    IServiceCommon serviceCommon;
    @Autowired
    IServiceCFO serviceCFO;

    @Autowired
    IsavePrivateLand isavePrivateLand;

    @RequestMapping(value = "/loadapplication")
    public String loadApplication(ModelMap model, HttpServletRequest request,@RequestParam("app_type") String app_type) {
        try {
            if(app_type.equalsIgnoreCase("WP")) {
                System.out.print("Wood and Poles task population");
                request.setAttribute("Group_Task_List", serviceFireWoodAndFencingPole.populateTaskList(request, "4", app_type));
                request.setAttribute("My_Task_List", serviceFireWoodAndFencingPole.populateTaskList(request, "5", app_type));
                return "common/taskListWood";
            }
            else{
                request.setAttribute("Group_Task_List", serviceFireWoodAndFencingPole.populateTaskList(request, "4", app_type));
                request.setAttribute("My_Task_List", serviceFireWoodAndFencingPole.populateTaskList(request, "5", app_type));
                return "common/taskListPvtLand";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("exception while populating application on group task and my task" + e);
        }
        return null;
    }

    @RequestMapping(value = "/loadpagetoemptylayout/openAndClaimApplication")
    public String openAndClaimApplication(ModelMap model, NewTimberDto newTimberDto, HttpServletRequest request, @RequestParam("app_type") String app_type, @RequestParam("Status_id") String status_id) {
        request.setAttribute("page_type", request.getParameter("page_type"));
        request.setAttribute("serviceId", request.getParameter("serviceId"));
      //  request.setAttribute("range_list", serviceCommon.getRange(request, divisionParkId));
        model.addAttribute("rejection_list", serviceFireWoodAndFencingPole.getRejList());
        model.addAttribute("PRLreject_List", serviceDealing.getPRLRejList());

        HttpSession session = request.getSession();
        String serviceSelected = request.getParameter("serviceId"), location_id = "", servicesfor = request.getParameter("page_type");
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        UserRolePrivilegeDTO dto = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        UserRolePrivilegeDTO userRolePrivilege = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String updatequery = "", priveleges = "";
        NewTimberDto dtoo = serviceCommon.updateApplicationToClaimed(request);
        String pageToReturn = "";
        if (request.getParameter("action_type").equalsIgnoreCase("open") && app_type.equalsIgnoreCase("WP")) {
            request.setAttribute("Group_Task_List", serviceFireWoodAndFencingPole.populateTaskList(request, "4", app_type));
            request.setAttribute("My_Task_List", serviceFireWoodAndFencingPole.populateTaskList(request, "5", app_type));
            pageToReturn = "common/taskListWood";
        }else if (request.getParameter("action_type").equalsIgnoreCase("open") && app_type.equalsIgnoreCase("PRL")) {
            request.setAttribute("Group_Task_List", serviceFireWoodAndFencingPole.populateTaskList(request, "4", app_type));
            request.setAttribute("My_Task_List", serviceFireWoodAndFencingPole.populateTaskList(request, "5", app_type));
            pageToReturn = "common/taskListPvtLand";
        }
        else if (request.getParameter("action_type").equalsIgnoreCase("release") && app_type.equalsIgnoreCase("WP")) {
            request.setAttribute("Group_Task_List", serviceFireWoodAndFencingPole.populateTaskList(request, "4", app_type));
            request.setAttribute("My_Task_List", serviceFireWoodAndFencingPole.populateTaskList(request, "5", app_type));
            pageToReturn = "common/taskListWood";
        } else if (request.getParameter("action_type").equalsIgnoreCase("release") && app_type.equalsIgnoreCase("PRL")) {
            request.setAttribute("Group_Task_List", serviceFireWoodAndFencingPole.populateTaskList(request, "4", app_type));
            request.setAttribute("My_Task_List", serviceFireWoodAndFencingPole.populateTaskList(request, "5", app_type));
            pageToReturn = "common/taskListPvtLand";
        }
        else if (request.getParameter("action_type").equalsIgnoreCase("claim") && app_type.equalsIgnoreCase("WP")) {
            request.setAttribute("APPLICATION_DETAILS", serviceCommon.getapplicationDetails(request, "application_details"));
            if (dto.getCurrentRole().getRoleName().equalsIgnoreCase("Dealing Officer")) {
                pageToReturn = "dealing_page/fencingPoleAndFirewood";
            } else if (dto.getCurrentRole().getRoleName().equalsIgnoreCase("CFO/PM") || dto.getCurrentRole().getRoleName().equalsIgnoreCase("Officiating CFO/PM")) {
                pageToReturn = "cfo_page/fencingPoleAndFirewoodCfo";
            } else if (dto.getCurrentRole().getRoleName().equalsIgnoreCase("Beat Officer") || dto.getCurrentRole().getRoleName().equalsIgnoreCase("Range Officer")) {
                pageToReturn = "range_page/fencingPoleAndFirewoodRO";
            } else {
                model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-danger text-center'>Not able to claim this application. Please try again or contact ICT  </div></span>");
                return "common/acknowledgementPage";
            }
        }
        else if (request.getParameter("action_type").equalsIgnoreCase("claim") && app_type.equalsIgnoreCase("PRL")) {
            if (dto.getCurrentRole().getRoleName().equalsIgnoreCase("Dealing Officer")) {
                request.setAttribute("APPLICATION_DETAILS", isavePrivateLand.getapplicationDetails(request, "application_details"));
                pageToReturn = "dealing_page/privateLandDealing";
            } else if (dto.getCurrentRole().getRoleName().equalsIgnoreCase("CFO/PM") || dto.getCurrentRole().getRoleName().equalsIgnoreCase("Officiating CFO/PM")) {
                request.setAttribute("APPLICATION_DETAILS", isavePrivateLand.getapplicationDetails(request, "application_details"));
                pageToReturn = "cfo_page/privateLandCFO";
            } else if (dto.getCurrentRole().getRoleName().equalsIgnoreCase("Beat Officer") || dto.getCurrentRole().getRoleName().equalsIgnoreCase("Range Officer")) {
                if(status_id.equalsIgnoreCase("3")){
                    request.setAttribute("APPLICATION_DETAILS", isavePrivateLand.getapplicationDetails(request, "application_details"));
                    pageToReturn = "range_page/privateLandMarkRanger";
                }else{
                    request.setAttribute("APPLICATION_DETAILS", isavePrivateLand.getappDetails(request, "application_details"));
                    model.addAttribute("Land_Category", isavePrivateLand.getLandCategory());
                    model.addAttribute("Forest_Produce", isavePrivateLand.getForestProduce());
                    pageToReturn = "range_page/privateLandRanger";
                }
            } else {
                model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-danger text-center'>Not able to claim this application. Please try again or contact ICT  </div></span>");
                return "common/acknowledgementPage";
            }
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
            WorkFlowDto update = serviceDealing.upadteClaimedApp(workFlowDto,listDto, request);
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>The Application  number" + request.getParameter("value") + " for  flagpoles/fencing post/firewood has been verified on" + new Date() +". </h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'><h5>The Application  number" + request.getParameter("value")  + "for flagpoles/fencing post/firewood has been verified on"+ new Date() +".</h5></div></span>");
        }catch(Exception e){
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-danger text-center'><h5>Sorry! verification of this application number is unsuccessful </h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-danger '><div class='alert-danger text-danger text-center'><h5>Sorry! verification of this application number is unsuccessful </h5></div></span>");
            System.out.print("Sorry! verification of this application number is unsuccessful"+e);
            return pageToReturn;
        }
        return pageToReturn;
    }

    @ResponseBody
    @RequestMapping(value = "/saveApplication")
    public String saveAndForwardCfo(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request, ModelMap model) {
        String pageToReturn = "";
        String appNo = "";
        try {
            request.setAttribute("appNo", request.getParameter("value"));
            WorkFlowDto update = serviceCFO.upadteClaimedWPApp(workFlowDto, listDto, request);
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>The Application  number" + request.getParameter("value") + " for  flagpoles/fencing post/firewood has been verified on" + new Date() +".</h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'><h5>The Application  number" + request.getParameter("value") + " for  flagpoles/fencing post/firewood has been verified on" + new Date() +".</h5></div></span>");
        } catch (Exception e) {
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-danger text-center'><h5>Sorry! approval of this application number is unsuccessful </h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-danger '><div class='alert-danger text-danger text-center'><h5>Sorry! approval of this application number is unsuccessful </h5></div></span>");
            System.out.print("Sorry! verification of this application number is unsuccessful" + e);
            return pageToReturn;
        }
        return pageToReturn;
    }
}


