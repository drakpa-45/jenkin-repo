package bt.gov.ditt.dofps.controller.admin.ruralTimberPermit;

import bt.gov.ditt.dofps.common.ResponseMessage;
import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.entitiy.InspectionTeamEntity;
import bt.gov.ditt.dofps.entitiy.PastRecordDetailEntity;
import bt.gov.ditt.dofps.services.IServiceCitizen;
import bt.gov.ditt.dofps.services.IServiceCommon;
import bt.gov.ditt.dofps.services.IServiceWoodPole;
import bt.gov.ditt.dofps.services.iServiceRuralTimber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepa on 7/13/2022.
 */
@Controller
@RequestMapping("/gewog")
public class ControllerGewog {

    @Autowired
    IServiceCommon serviceCommon;

    @Autowired
    IServiceCitizen serviceCitizen;

    @Autowired
    IServiceWoodPole serviceWoodPole;

    @Autowired
    iServiceRuralTimber serviceRuralTimber;

    @RequestMapping(value = "/loadTaskList")
    public String loadApplication(ModelMap model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        try {
         //  model.addAttribute("Park_List", service.getParkList(request,dzo_Id));
           request.setAttribute("Group_Task_List", serviceCommon.populateTaskListforSelectedServices(request, "4"));
           request.setAttribute("My_Task_List", serviceCommon.populateTaskListforSelectedServices(request, "5"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("exception while populating application on gruop task and my task"+e);
        }
        return "admin/gewogTaskList";
    }

    @RequestMapping(value = "loginMain/citizenDashboard", method = RequestMethod.GET)
    public String citizenDashboard(@RequestParam("cons") String cons,HttpServletRequest request,ModelMap model) {
        List<CommonDto> fpProductCategory = serviceRuralTimber.getProductList();
        request.setAttribute("fpProductCategory", fpProductCategory);
        model.addAttribute("cons", cons);
        return "/admin/ruralTimberPermitAdmin";
    }

    @ResponseBody
    @RequestMapping(value = "/loginMain/saveOnlineTimberPermit",  method = RequestMethod.POST )
    public ResponseMessage save(OnlineTimberDTO timberDto,HttpServletRequest request,ModelMap model) {
        String msg="";
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            OnlineTimberDTO insertApplication = serviceCitizen.saveOnlineTimberApplication(timberDto,request);
            if (insertApplication.getStatus().equalsIgnoreCase("Success")) {
                responseMessage.setStatus(1);
                responseMessage.setText("Your requisition for timber permit is successfully submitted with application number " + timberDto.getApplication_Number());
            }else {
                responseMessage.setStatus(0);
                responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
            }
        }catch (Exception e){
            e.printStackTrace();
            responseMessage.setStatus(0);
            responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
        }
        // return "/common/ackPage";
        return responseMessage;
    }

    @RequestMapping(value = "/loginMain/privateLandPermit", method = RequestMethod.GET)
    public String privateLandPermit(ModelMap model,NewTimberDto dto,HttpServletRequest request) {
        // model.addAttribute("message");
        model.addAttribute("cons_type");
        Integer dzo_Id = serviceRuralTimber.getDzoId(request, dto);
        model.addAttribute("Park_List", serviceRuralTimber.getParkList(request, dzo_Id));
        model.addAttribute("DZONGKHAG_LIST", serviceRuralTimber.getDzongkhagList());
        model.addAttribute("Forest_Produce", serviceCommon.getForestProduce());
        return "admin/permitFromPrivateLandAdmin";
    }

    @ResponseBody
    @RequestMapping(value = "/loginMain/submitPrivateLand",method = RequestMethod.POST)
    public String submitPrivateLand(OnlineTimberDTO timberDto, NewTimberDto newTimberDto,@RequestParam("files") MultipartFile[] files, ModelMap model, HttpServletRequest request){
        String retval="";
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage = serviceCitizen.submitPrivateLand(timberDto,newTimberDto,files,request);
        retval = responseMessage.getText();
        model.addAttribute("acknowledgement_message",responseMessage.getText());
        return retval;
    }

    @RequestMapping(value = "/loginMain/firewoodAndFencingPoles", method = RequestMethod.GET)
    public String firewoodAndFencingPoles(ModelMap model,NewTimberDto dto,HttpServletRequest request,@RequestParam("cons_desc") String cons_desc) {
        model.addAttribute("cons_type");
        model.addAttribute("Prod_List", serviceWoodPole.getProductDetails(cons_desc));
        return "/admin/permitForPoleAndFirewoodAdmin";
    }

    @ResponseBody
    @RequestMapping(value = "/loginMain/saveWoodandPoles",method = RequestMethod.POST)
    public ResponseMessage saveWoodandPoles(OnlineTimberDTO timberDto, ModelMap model, HttpServletRequest request){
        String msg="";
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            OnlineTimberDTO insertApplication = serviceCitizen.saveWoodandPoles(timberDto,request);
            if (insertApplication.getStatus().equalsIgnoreCase("Success")) {
                responseMessage.setStatus(1);
                responseMessage.setText("Your requisition for Rural Timber Permit for Wood & Poles is successfully submitted with application number " + timberDto.getApplication_Number());
            }else {
                responseMessage.setStatus(0);
                responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
            }
        }catch (Exception e){
            e.printStackTrace();
            responseMessage.setStatus(0);
            responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
        }
        // return "/common/ackPage";
        return responseMessage;
    }

    @RequestMapping(value = "/loginMain/manualRecord", method = RequestMethod.GET)
    public String manualRecord(ModelMap model,HttpServletRequest request) {
        List<CommonDto> fpProductCategory = serviceRuralTimber.getProductList();
        request.setAttribute("fpProductCategory", fpProductCategory);
        return "/admin/manualRecords";
    }

    @ResponseBody
    @RequestMapping(value = "/loginMain/submitManualRecord",method = RequestMethod.POST)
    public String submitManualRecord(HttpServletRequest request, ModelMap model,OnlineTimberDTO timberDto,@RequestParam("files") MultipartFile[] files){
        String msg="";
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            OnlineTimberDTO insertApplication = serviceCitizen.submitManualRecord(timberDto,request,files);
            if (insertApplication.getStatus().equalsIgnoreCase("Success")) {
                responseMessage.setStatus(1);
                responseMessage.setText("Your manual reports for timber permit is successfully submitted with application number " + timberDto.getApplication_Number());
            }else {
                responseMessage.setStatus(0);
                responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
            }
            msg=responseMessage.getText();
        }catch (Exception e){
            e.printStackTrace();
            responseMessage.setStatus(0);
            responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
        }
        // return "/common/ackPage";
        return msg;
    }

    @RequestMapping(value = "/loginMain/permitExtension", method = RequestMethod.GET)
    public String permitExtension(ModelMap model,HttpServletRequest request) {
        return "/admin/permit_extensionAdmin";
    }

    @RequestMapping(value = "/loginMain/timberReplacement", method = RequestMethod.GET)
    public String timberReplacement(ModelMap model,HttpServletRequest request) {
        return "/admin/timber_replacementAdmin";
    }


    @RequestMapping(value = "/loginMain/markingDate", method = RequestMethod.GET)
    public String markingDate(ModelMap model,HttpServletRequest request) {
        return "/admin/marking_dateAdmin";
    }

    @RequestMapping(value = "/loginMain/sowingPermit", method = RequestMethod.GET)
    public String sowingPermit(ModelMap model,HttpServletRequest request) {
        return "/admin/sowing_permitAdmin";
    }

    @RequestMapping(value = "/loginMain/allotmentOrder", method = RequestMethod.GET)
    public String allotmentOrder(ModelMap model,HttpServletRequest request) {
        return "/admin/allotmentOrderAdmin";
    }

    @RequestMapping(value = "/loginMain/permit", method = RequestMethod.GET)
    public String permit(ModelMap model,HttpServletRequest request) {
        return "/admin/permitAdmin";
    }

    @RequestMapping(value = "/loginMain/printSowingPermit", method = RequestMethod.GET)
    public String printSowingPermit(ModelMap model,HttpServletRequest request) {
        return "/admin/sowingPermitAdmin";
    }

    @RequestMapping(value = "/loginMain/forestProduceClearance", method = RequestMethod.GET)
    public String forestProduceClearance(ModelMap model,HttpServletRequest request) {
        return "/admin/forestProduceClearanceAdmin";
    }

    @ResponseBody
    @RequestMapping(value = "/loginMain/submitTimberPE", method = RequestMethod.POST)
    public String submitTimberPE(HttpServletRequest request,WorkFlowDto dto,@RequestParam("files") MultipartFile[] files){
        // return serviceCitizen.submitTimberPE(appNo,dto,request);
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        ResponseMessage responseMessage = new ResponseMessage();
        String retval="";
        try{
            responseMessage=serviceCitizen.submitTimberPE(appNo, dto, request,files,remarks);
            retval = responseMessage.getText();
        }catch (Exception e){
            System.out.println("--Exception here-- "+ e);
        }
        return retval;
    }

    @ResponseBody
    @RequestMapping(value = "/loginMain/submitTimberRR", method = RequestMethod.POST)
    public ResponseMessage timberReplaceRequest(String appNo,HttpServletRequest request,WorkFlowDto dto){
        return serviceCitizen.submitTimberRR(appNo, dto, request);
    }

    @ResponseBody
    @RequestMapping(value="/getApplicationDetails",method = RequestMethod.GET)
    public ResponseMessage getApplicationDetails(String applicationNumber) throws IOException {
        List<AppHistoryDTO> applicationDTOList=new ArrayList<AppHistoryDTO>();
        ResponseMessage responseMessage=new ResponseMessage();
        applicationDTOList=serviceCommon.getViewStatusApplication(applicationNumber);
        responseMessage.setDto(applicationDTOList);
        return responseMessage;
    }

    @RequestMapping(value = "/appHistory",method = RequestMethod.GET)
    public String getApplicationDetails( ModelMap model,HttpServletRequest request,String applicationNo) {
        List<AppHistoryDTO> appHistoryDTO = new ArrayList<AppHistoryDTO>();
        appHistoryDTO=serviceCommon.getViewStatusApplication(applicationNo);
        request.setAttribute("appHistoryDTO", appHistoryDTO);
        model.addAttribute("appNo", applicationNo);
        return "trackApplication/trackApp";
    }

    @ResponseBody
    @RequestMapping(value="/getInspectionTeamMembers",method = RequestMethod.GET)
    public ResponseMessage getInspectionTeamMembers(String applicationNumber) throws IOException {
        List<InspectionTeamEntity> applicationDTOList=new ArrayList<InspectionTeamEntity>();
        ResponseMessage responseMessage=new ResponseMessage();
        applicationDTOList=serviceCommon.getInspectionTeamMembers(applicationNumber);
        responseMessage.setDto(applicationDTOList);
        return responseMessage;
    }

    /**
     * getPastRecordDetails -- method to fetch past record of timber details for particular application
     * @param applicationNumber
     * @return
     * @throws java.io.IOException
     */
    @ResponseBody
    @RequestMapping(value="/getPastRecordDetails",method = RequestMethod.GET)
    public ResponseMessage getPastRecordDetails(String applicationNumber) throws IOException {
        List<PastRecordDetailEntity> applicationDTOList=new ArrayList<PastRecordDetailEntity>();
        ResponseMessage responseMessage=new ResponseMessage();
        applicationDTOList=serviceCommon.getPastRecordDetails(applicationNumber);
        responseMessage.setDto(applicationDTOList);
        return responseMessage;
    }

    /**
     * forwardToGAO -- method to forward application to GAO by Gup for inpections
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/forwardToGAO")
    public ResponseMessage forwardToGAO(HttpServletRequest request, ModelMap model){
            String remarks = request.getParameter("remarks");
            String appNo = request.getParameter("appNo");
            ResponseMessage responseMessage = serviceCommon.forwardToGAO(appNo, remarks, request);
        return responseMessage;
    }

    /**
     * gupApprove -- methods to approve application to CFO after inspections details is updated by GAO
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/gupApprove")
    public ResponseMessage gupApprove(HttpServletRequest request, ModelMap model){
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        ResponseMessage responseMessage = serviceCommon.gupApprove(appNo, remarks, request);
        return responseMessage;
    }

    /**
     * cfoApprove -- method to approve application by CFO
     * if the timber type is log then the application process will be end and
     * if it is standing type then application is forwarded to Range Officer for further action
     * @param request
     * @param model
     * @param timberDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cfoApprove")
    public ResponseMessage cfoApprove(HttpServletRequest request, ModelMap model,OnlineTimberDTO timberDto){
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        String statusId = request.getParameter("statusId");
        String productName = request.getParameter("productName");
        timberDto.setStatus_Id(Integer.valueOf(statusId));
        timberDto.setStatus(productName);
        ResponseMessage responseMessage = serviceCommon.cfoApprove(appNo, remarks, request, timberDto);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/ocRangeApprove")
    public ResponseMessage ocRangeApprove(HttpServletRequest request, ModelMap model,OnlineTimberDTO timberDto){
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        String statusId = request.getParameter("statusId");
        String productName = request.getParameter("productName");
        timberDto.setStatus_Id(Integer.valueOf(statusId));
        timberDto.setStatus(productName);
        ResponseMessage responseMessage = serviceCommon.ocRangeApprove(appNo, remarks, request, timberDto);
        return responseMessage;
    }

    /**
     * updatePayment -- method to appro ve offline payment details by range officer
     * @param request
     * @param model
     * @return response message.
     */
    @ResponseBody
    @RequestMapping(value = "/updatePayment")
    public ResponseMessage updatePayment(HttpServletRequest request, ModelMap model){
        int amount= Integer.parseInt(request.getParameter("amount"));
        String modeOfPayment=request.getParameter("modeOfPayment");
        String receiptNo=request.getParameter("receiptNo");
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        String paymentDate = request.getParameter("paymentDate");
        ResponseMessage responseMessage = serviceCommon.updatePayment(appNo, remarks, request, amount, modeOfPayment, receiptNo);
        return responseMessage;
    }

    /**
     * updateInspectionDetails -- method to update inspection details by GAO
     * updates past records and inspection team details
     * @param request
     * @param model
     * @param inspectionDTO --- object
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/updateInspectionDetails",method = RequestMethod.POST)
    public String updateInspectionDetails(HttpServletRequest request, ModelMap model,InspectionDTO inspectionDTO,@RequestParam("files") MultipartFile[] files){
        //MultipartFile[] files = new MultipartFile[0];
        String retval="";
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage=serviceCommon.updateInspectionDetails(inspectionDTO,request,files);
        retval = responseMessage.getText();
        model.addAttribute("acknowledgement_message",responseMessage.getText());
        return retval;
    }

    /**
     * approveRO -- method to approved by Range officer
     * @param request --- contains application number and remarks
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/approveRO")
    public ResponseMessage approveRO(HttpServletRequest request, ModelMap model,InspectionDTO inspectionDTO){
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        return serviceCommon.approveRO(appNo, remarks, request, inspectionDTO);
    }

    /**
     * approveSWP --- method to approve sawing permit by range officer
     * @param request --- contains application number and remarks
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/approveSWP")
    public ResponseMessage approveSWP(HttpServletRequest request, ModelMap model,InspectionDTO inspectionDTO){
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        String permitValidityDate =inspectionDTO.getPermitValidityDate();
        String officerOnDuty = inspectionDTO.getOfficerOnDuty();
        return serviceCommon.approveSWP(appNo, remarks, request, inspectionDTO, permitValidityDate, officerOnDuty);
    }

    @ResponseBody
    @RequestMapping(value = "/approvePermitExtension")
    public ResponseMessage approvePermitExtension(HttpServletRequest request, ModelMap model){
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        return serviceCommon.approvePermitExtension(appNo, remarks, request);
    }

    @ResponseBody
    @RequestMapping(value = "/verifyReplacement",method = RequestMethod.POST)
    public String verifyReplacement(HttpServletRequest request, ModelMap model,@RequestParam("files") MultipartFile[] files){
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        ResponseMessage responseMessage = new ResponseMessage();
        String retval="";
        try{
            responseMessage=serviceCommon.verifyReplacement(appNo, remarks, request,files);
            retval = responseMessage.getText();
            model.addAttribute("acknowledgement_message",responseMessage.getText());
        }catch (Exception e){
            System.out.println("--Exception here-- "+ e);
        }
        return retval;
    }

    @RequestMapping(value = "/acknowledgement")
    public String acknowledgement( ModelMap model,HttpServletRequest request,@ModelAttribute("acknowledgement_message")String acknowledgement_message) {
        model.addAttribute("ackMessage", acknowledgement_message);
        return "acknowledgement";
    }

    @RequestMapping(value = "/loginMain/updateConstructionCompletion", method = RequestMethod.GET)
    public String updateConstructionCompletion(ModelMap model,HttpServletRequest request) {
        request.setAttribute("construction_status", serviceCommon.getConstructionStatus());
        return "/admin/updateConstructionCompletion";
    }

    @ResponseBody
    @RequestMapping(value = "/loginMain/check4Completion", method = RequestMethod.GET)
    public ResponseMessage check4Completion(String appNo){
        return serviceCitizen.check4Completion(appNo);
    }

    @ResponseBody
    @RequestMapping(value = "/loginMain/updateCompletionDate", method = RequestMethod.POST)
    public ResponseMessage updateCompletionDate(String appNo,HttpServletRequest request,String completionDate){
        return serviceCitizen.updateCompletionDate(appNo, completionDate,request);
    }

    @ResponseBody
    @RequestMapping(value = "/forwardToOtherRange")
    public ResponseMessage forwardToOtherRange(HttpServletRequest request, ModelMap model){
        int rangeId= Integer.parseInt(request.getParameter("rangeId"));
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        ResponseMessage responseMessage = serviceCommon.forwardToOtherRange(appNo, remarks, request, rangeId);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/forwardToOtherCFO")
    public ResponseMessage forwardToOtherCFO(HttpServletRequest request, ModelMap model){
        int parkId= Integer.parseInt(request.getParameter("parkId"));
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        ResponseMessage responseMessage = serviceCommon.forwardToOtherCFO(appNo, remarks, request, parkId);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/reject")
    public ResponseMessage reject(HttpServletRequest request, ModelMap model){
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        ResponseMessage responseMessage = serviceCommon.reject(appNo, remarks, request);
        return responseMessage;
    }

}