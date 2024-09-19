package bt.gov.ditt.dofps.controller;

import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.services.IServiceCommon;
import bt.gov.ditt.dofps.services.IServiceDealing;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wso2.client.model.G2C_CommonBusinessAPI.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pema Drakpa on 1/24/2020.
 */
@Controller
@RequestMapping("/common")
public class ControllerCommon {

    @Autowired
    IServiceCommon serviceCommon;

    @Autowired
    IServiceDealing serviceDealing;

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, Model model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "expired", required = false) String expired) {
        HttpSession session = request.getSession();
        session.invalidate();
        //return "/common/login";
        return "/common/logout";
    }
    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String loginRedirect(HttpServletRequest request, HttpServletResponse response, @RequestParam("userId") String userId ,@RequestParam("CurrentUser") String CurrentUser, @RequestParam("locationId") String locationId) throws ServletException, IOException {
        {
            HttpSession session = request.getSession();
            UserRolePrivilegeDTO dto = new UserRolePrivilegeDTO();
            String uid = null;
            String LocationId = "";

            request.setAttribute("userId", request.getParameter("userId"));
            request.setAttribute("CurrentUser", request.getParameter("CurrentUser"));
            LocationId = request.getParameter("locationId");
            if (request.getParameter("userId") != "" && request.getParameter("locationId") != "") {
                    request.setAttribute("GroupTaskDealing", serviceCommon.getGroupTaskDealing(request, CurrentUser, LocationId, userId));
                    request.setAttribute("PersonalTaskDealing", serviceCommon.getPersonalTaskDealing(request, CurrentUser, userId, LocationId));

                    request.setAttribute("GroupTaskPRL", serviceCommon.getGroupTaskRangePRL(request, CurrentUser, LocationId, userId));
                    request.setAttribute("PersonalTaskPRL", serviceCommon.getPersonalTaskRangePRL(request, CurrentUser, LocationId, userId));
            }
        }
        return "common/dashBoard";
    }

    @RequestMapping(value = "/loadapplication")
   public String loadApplication(ModelMap model, HttpServletRequest request) {
        try {
            request.setAttribute("Group_Task_List", serviceCommon.populateTaskListforSelectedServices(request, "4"));
            request.setAttribute("My_Task_List", serviceCommon.populateTaskListforSelectedServices(request, "5"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("exception while populating application on gruop task and my task"+e);
        }
        return "common/taskList";
    }

    @RequestMapping(value = "/loadpagetoemptylayout/openAndClaimApplication")
    public String openAndClaimApplication(ModelMap model, NewTimberDto newTimberDto, HttpServletRequest request) {
        request.setAttribute("page_type", request.getParameter("page_type"));
        request.setAttribute("serviceId", request.getParameter("serviceId"));
      //  request.setAttribute("range_list", serviceCommon.getRange(request));
        model.addAttribute("rejection_list", serviceDealing.getRejList());
        WorkFlowDto workFlowDto = new WorkFlowDto();
        Integer divisionParkId =0;
        HttpSession session = request.getSession();
        String serviceSelected = request.getParameter("serviceId"), location_id = "", servicesfor = request.getParameter("page_type");
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        UserRolePrivilegeDTO dto = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        UserRolePrivilegeDTO userRolePrivilege = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String updatequery = "", priveleges = "";
        if (userRolePriv.getUserRoles() != null) {
            JurisdictionsObj jurisdiction = userRolePriv.getJurisdictions();
            DeptServicesObj serv = userRolePriv.getUserRoles().getUserRole().get(0).getDeptServices();
            List<DeptServiceObj> ser = serv.getDeptService();
            for (int s = 0; s < ser.size(); s++) {
                ServicePrivilegesObj priv = ser.get(s).getServicePrivileges();
                List<ServicePrivilegeObj> servicePrivilege = priv.getServicePrivilege();
                String servicepriv = "";
                for (int p = 0; p < servicePrivilege.size(); p++) {
                    if (p == servicePrivilege.size() - 1) {
                        servicepriv = servicepriv + servicePrivilege.get(p).getPrivilegeId();
                    } else {
                        servicepriv = servicepriv + servicePrivilege.get(p).getPrivilegeId() + ",";
                    }
                }
                if (s == ser.size() - 1) {
                    priveleges = priveleges + servicepriv;
                } else {
                    priveleges = priveleges + servicepriv + ",";
                }
            }
            // updatequery = GET_ALL_TASK;
            jurisdiction = userRolePriv.getJurisdictions();
            for (int k = 0; k < jurisdiction.getJurisdiction().size(); k++) {
                if (k == jurisdiction.getJurisdiction().size() - 1) {
                    location_id += jurisdiction.getJurisdiction().get(k).getLocationId();
                } else {
                    location_id += jurisdiction.getJurisdiction().get(k).getLocationId() + ", ";
                }
            }

            NewTimberDto dtoo = serviceCommon.updateApplicationToClaimed(request);
            String pageToReturn = "";
            if (request.getParameter("action_type").equalsIgnoreCase("open")) {
                request.setAttribute("Group_Task_List", serviceCommon.populateTaskListforSelectedServices(request, "4"));
                request.setAttribute("My_Task_List", serviceCommon.populateTaskListforSelectedServices(request, "5"));
                pageToReturn = "common/taskList";
            } else if (request.getParameter("action_type").equalsIgnoreCase("release")) {
                request.setAttribute("Group_Task_List", serviceCommon.populateTaskListforSelectedServices(request, "4"));
                request.setAttribute("My_Task_List", serviceCommon.populateTaskListforSelectedServices(request, "5"));
                pageToReturn = "common/taskList";
            } else if (request.getParameter("action_type").equalsIgnoreCase("claim")) {
               /* claimAdditionalTimberDTO ClaimStatus = serviceCommon.getClaimStatus(request);
                String status = ClaimStatus.getClaimStatus();
                if(status.equalsIgnoreCase("New")){*/
                    String type=request.getParameter("cons_type");
                    workFlowDto=serviceCommon.getapplicationDetails(request, type);
                    request.setAttribute("APPLICATION_DETAILS",workFlowDto );

                    divisionParkId =workFlowDto.getDivision_Park_Id();
                    if (dto.getCurrentRole().getRoleName().equalsIgnoreCase("Dealing Officer")) {
                        pageToReturn = "dealing_page/ruralTimberPermit";
                    }/*else  if (request.getParameter("cons_type").equalsIgnoreCase("r") || request.getParameter("cons_type").equalsIgnoreCase("n") && dto.getCurrentRole().getRoleName().equalsIgnoreCase("Beat Officer") || request.getParameter("cons_type").equalsIgnoreCase("r")
                            || request.getParameter("cons_type").equalsIgnoreCase("n") && dto.getCurrentRole().getRoleName().equalsIgnoreCase("Range Officer")) {
                        pageToReturn = "range_page/range_approve";
                    }else  if ((request.getParameter("cons_type").equalsIgnoreCase("o") && dto.getCurrentRole().getRoleName().equalsIgnoreCase("Beat Officer")) ||
                            (request.getParameter("cons_type").equalsIgnoreCase("o") && dto.getCurrentRole().getRoleName().equalsIgnoreCase("Range Officer"))) {
                        pageToReturn = "range_page/other_Cons";
                    }*/else  if(dto.getCurrentRole().getRoleName().equalsIgnoreCase("Gup")) {
                        request.setAttribute("range_list", serviceCommon.getRange(request,divisionParkId));
                        if (request.getParameter("cons_type").equalsIgnoreCase("PRL")){
                            model.addAttribute("Land_Category", serviceCommon.getLandCategory());
                            model.addAttribute("Forest_Produce", serviceCommon.getForestProduce());
                            pageToReturn = "admin/adminPrivateLandForestProduce/privateLandGup";
                        }else if (request.getParameter("cons_type").equalsIgnoreCase("WP")){
                            pageToReturn = "admin/adminWoodPolesAndFlagPoles/wpGup";
                        }else{
                            pageToReturn = "admin/adminRuralTimberPermit/ruralTimberPermitGup";
                        }
                    }else  if(dto.getCurrentRole().getRoleName().equalsIgnoreCase("Gewog")) {
                        model.addAttribute("designationListHr", serviceCommon.getDesignation());
                        model.addAttribute("timberType", serviceCommon.getTimberType(request.getParameter("cons_type")));
                        pageToReturn = "admin/adminRuralTimberPermit/ruralTimberPermitGAO";
                    }else  if (dto.getCurrentRole().getRoleName().equalsIgnoreCase("CFO/PM") || dto.getCurrentRole().getRoleName().equalsIgnoreCase("Officiating CFO/PM")) {
                        request.setAttribute("range_list", serviceCommon.getRange(request,divisionParkId));
                        request.setAttribute("all_division_park_list", serviceCommon.getAllParkList());
                        if(workFlowDto.getStatus_Id().equals(20)){
                            pageToReturn = "admin/adminRuralTimberPermit/permitExtensionCFO";
                        }else if(workFlowDto.getStatus_Id().equals(21)){
                            pageToReturn = "admin/adminRuralTimberPermit/timberReplacementCFO";
                        }else if (request.getParameter("cons_type").equalsIgnoreCase("PRL")){
                            pageToReturn = "admin/adminPrivateLandForestProduce/privateLandCFO";
                        }else if (request.getParameter("cons_type").equalsIgnoreCase("WP")){
                            pageToReturn = "admin/adminWoodPolesAndFlagPoles/wpCFO";
                        }else{
                            pageToReturn = "admin/adminRuralTimberPermit/ruralTimberPermitCFO";
                        }
                    }else  if(dto.getCurrentRole().getRoleName().equalsIgnoreCase("Range Officer")) {
                        request.setAttribute("speciesName", serviceCommon.getSpeciesName(request));
                        model.addAttribute("designationListHr", serviceCommon.getDesignation());
                        request.setAttribute("range_list", serviceCommon.getRange(request, divisionParkId));
                        model.addAttribute("timberType", serviceCommon.getTimberType(request.getParameter("cons_type")));
                        model.addAttribute("Land_Category", serviceCommon.getLandCategory());
                        model.addAttribute("Forest_Produce", serviceCommon.getForestProduce());
                        model.addAttribute("privateLandProduceSpecies", serviceCommon.getPrivateLandProduceSpecies('1'));
                        if(workFlowDto.getStatus_Id().equals(17)){
                            pageToReturn = "admin/adminRuralTimberPermit/sawingPermitRO";
                        }else if(workFlowDto.getStatus_Id().equals(19)){
                            pageToReturn = "admin/adminRuralTimberPermit/timberReplacementRO";
                        }else if (request.getParameter("cons_type").equalsIgnoreCase("PRL")){
                            pageToReturn = "admin/adminPrivateLandForestProduce/privateLandRO";
                        }else if (request.getParameter("cons_type").equalsIgnoreCase("WP")){
                            pageToReturn = "admin/adminWoodPolesAndFlagPoles/wpRO";
                        }else if(request.getParameter("cons_type").equalsIgnoreCase("o")) {
                            String allot_qty = String.valueOf(workFlowDto.getTimberdetails().get(0).getAllot_Quantity());
                            if(workFlowDto.getPayment_status().equalsIgnoreCase("Unpaid") && allot_qty.equals("0")){
                                pageToReturn = "admin/adminRuralTimberPermit/ruralTimberPermitOcRO";
                            }else{
                                pageToReturn = "admin/adminRuralTimberPermit/ruralTimberPermitROPayment";
                            }
                        }else{
                            pageToReturn = "admin/adminRuralTimberPermit/ruralTimberPermitRO";
                        }
                    }
               /* }else{
                    request.setAttribute("APPLICATION_DETAILS", serviceCommon.getreclaimapplicationDetails(request, "application_details"));
                    if (dto.getCurrentRole().getRoleName().equalsIgnoreCase("Dealing Officer")) {
                        pageToReturn = "dealing_page/ruralTimberReclaimPermit";
                    }else  if (dto.getCurrentRole().getRoleName().equalsIgnoreCase("CFO/PM") || dto.getCurrentRole().getRoleName().equalsIgnoreCase("Officiating CFO/PM")) {
                        pageToReturn = "cfo_page/ruralTimberReclaimPermitCFO";
                    }else  if (dto.getCurrentRole().getRoleName().equalsIgnoreCase("Beat Officer") || dto.getCurrentRole().getRoleName().equalsIgnoreCase("Range Officer")) {
                        pageToReturn = "range_page/ruralTimberReclaimPermitRange";
                    }
                } */
            } else {
                model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-danger text-center'>Not able to claim this application. Please try again or contact ICT  </div></span>");
                return "common/acknowledgementPage";
            }
            return pageToReturn;
        }
        return null;
    }
    @RequestMapping(value = "/viewAppStatus", method = RequestMethod.GET)
    public String viewStatus(ModelMap model,WorkFlowDto workFlowDto, HttpServletRequest request) {
        return "common/viewStatusOnly";
    }
    @RequestMapping(value = "/statusDetail", method = RequestMethod.GET)
    public String fetchStatusDtls(CitizenDetailsDTO dto,WorkFlowDto workFlowDto, ModelMap model, HttpServletRequest request) {
        request.setAttribute("applicationDetails",serviceCommon.fetchAppStatus(workFlowDto, request));
        return "common/viewStatusOnly";
    }

    @RequestMapping(value = "/loadpagetoemptylayout/donwloadFiles", method = RequestMethod.GET)
    public String donwloadFiles(@RequestParam("type") String type, WorkFlowDto dto, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String uploadDocId = request.getParameter("uuid"), requesttype = request.getParameter("type");
        try {
            CommonDto doc = serviceCommon.getDocumentDetailsByDocId(uploadDocId);
            byte[] fileContent = downloadFile(doc.getUpload_URL());
            if(requesttype.equalsIgnoreCase("view")){
                if(doc.getDocument_Name().substring(doc.getDocument_Name().length()-3).equalsIgnoreCase("JPG")||doc.getDocument_Name().substring(doc.getDocument_Name().length()-4).equalsIgnoreCase("jpeg") || doc.getDocument_Name().substring(doc.getDocument_Name().length()-3).equalsIgnoreCase("png")){
                    response.setContentType("image/jpeg");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getDocument_Name());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getDocument_Name().substring(doc.getDocument_Name().length()-3).equalsIgnoreCase("pdf")){
                    response.setContentType("APPLICATION/PDF");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getDocument_Name());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getDocument_Name().substring(doc.getDocument_Name().length()-4).equalsIgnoreCase("docx")){
                    response.reset();
                    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    response.setHeader("Content-Disposition", "inline;filename="+doc.getDocument_Name());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getDocument_Name().substring(doc.getDocument_Name().length()-3).equalsIgnoreCase("xls")){
                    response.setContentType("APPLICATION/vnd.ms-excel");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getDocument_Name());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getDocument_Name().substring(doc.getDocument_Name().length()-4).equalsIgnoreCase("xlsx")){
                    response.setContentType("Application/x-msexcel");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getDocument_Name());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else{
                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-disposition", "attachment; filename="+doc.getDocument_Name());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
            }
            else{
                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition", "attachment; filename="+doc.getDocument_Name());
                response.getOutputStream().write(fileContent);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            }
        } catch (Exception e) {
            System.out.print("Exception while viewing/downloading your file"+e);
            return "" + e;
        }
        return null;
    }
    public static byte[] downloadFile(String uploadUlr) throws Exception{
        FileInputStream fileInputStream = new FileInputStream(uploadUlr);
        return IOUtils.toByteArray(fileInputStream);
    }
}
