package bt.gov.ditt.dofps.controller.publicController;

import bt.gov.ditt.dofps.certification.PrintPDFUtility;
import bt.gov.ditt.dofps.common.ResponseMessage;
import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.services.*;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by deepa on 7/7/2022.
 */
@Controller
@RequestMapping("/")
public class ControllerCitizen {

    @Autowired
    IServiceCitizen serviceCitizen;

    @Autowired
    iServiceRuralTimber serviceRuralTimber;

    @Autowired
    APIService apiService;

    @Autowired
    IServiceCommon serviceCommon;

    @Autowired
    IServiceNDI serviceNDI;

    @Autowired
    IServiceWoodPole serviceWoodPole;

    public Map<String, SseEmitter> emitters = new HashMap<>();

    @RequestMapping(method = RequestMethod.GET)
    public String citizenDashboad(ModelMap model,HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpSession session = request.getSession();
       // return "/common/blank";
       return "/citizen/ruralTimberPermit";
    }


    @ResponseBody
    @RequestMapping(value = "/passwordLessLogin", method = RequestMethod.GET)
    public NdiDTO getPasswordLessLogin(HttpServletRequest request,HttpServletResponse responses) throws IOException, InterruptedException {
        NdiDTO ndiDTO = new NdiDTO();
        ndiDTO = serviceNDI.getPasswordLessLogin(request,responses);
        return ndiDTO;
    }

    @RequestMapping(value = "/loginDashboard",method = RequestMethod.GET)
    public String loginDashboard(Model model, HttpSession session,HttpServletResponse response,HttpServletRequest request) {
        return "/citizen/ruralTimberPermit";
    }

    @RequestMapping(value = "/ndiLandingPage.html", method = RequestMethod.POST)
    public ResponseEntity ndiLandingPage(@RequestBody UserRolePrivilegeDTO dto,HttpSession session) {
        if (dto != null) {
            // For example:
             session.setAttribute("userdetail", dto);
            // Return success response
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } else {
            // If DTO is null, return bad request response
           return new ResponseEntity<>("Invalid data received", HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/redirect.html", method = RequestMethod.GET)
    public String ndiLandingPageOpen(Model model, HttpSession session) {
        // Retrieve DTO object from session
        UserRolePrivilegeDTO dto = (UserRolePrivilegeDTO) session.getAttribute("userdetail");
        if (dto != null) {
            // Retrieve data from DTO
            session.setAttribute("userdetail", dto);
            return "/citizen/ruralTimberPermit";
        } else {
            // Handle case where DTO is not found in session
            System.out.println("DTO not found in session.");
            return ""; // Redirect to home page or appropriate error page
        }
    }

    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logout(Model models, HttpServletRequest request, HttpSession session) {
        //clearing sessions
        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:http://brtp.citizenservices.gov.bt/";
    }

    @RequestMapping(value = "public/initiate/citizenDashboard", method = RequestMethod.GET)
    public String citizenDashboard(@RequestParam("cons") String cons,HttpServletRequest request,ModelMap model) {
        List<CommonDto> fpProductCategory = serviceRuralTimber.getProductList();
        request.setAttribute("fpProductCategory", fpProductCategory);
        model.addAttribute("cons", cons);
        return "/citizen/ruralTimberPermit";
        //return "common/blank";
    }

    @RequestMapping(value = "public/initiate/markingDate", method = RequestMethod.GET)
    public String markingDate(ModelMap model,HttpServletRequest request) {
        return "/citizen/marking_date";
    }

    @RequestMapping(value = "public/initiate/sowingPermit", method = RequestMethod.GET)
    public String sowingPermit(ModelMap model,HttpServletRequest request) {
        return "/citizen/sowing_permit";
    }

    @RequestMapping(value = "public/initiate/permitExtension", method = RequestMethod.GET)
    public String permitExtension(ModelMap model,HttpServletRequest request) {
        return "/citizen/permit_extension";
    }

    @RequestMapping(value = "public/initiate/timberReplacement", method = RequestMethod.GET)
    public String timberReplacement(ModelMap model,HttpServletRequest request) {
        return "/citizen/timber_replacement";
    }

    @RequestMapping(value = "public/initiate/allotmentOrder", method = RequestMethod.GET)
    public String allotmentOrder(ModelMap model,HttpServletRequest request) {
        return "/citizen/allotmentOrder";
    }

    @RequestMapping(value = "public/initiate/permit", method = RequestMethod.GET)
    public String permit(ModelMap model,HttpServletRequest request) {
        return "/citizen/permit";
    }

    @RequestMapping(value = "public/initiate/printSowingPermit", method = RequestMethod.GET)
    public String printSowingPermit(ModelMap model,HttpServletRequest request) {
        return "/citizen/sowingPermit";
    }

    @RequestMapping(value = "public/initiate/forestProduceClearance", method = RequestMethod.GET)
    public String forestProduceClearance(ModelMap model,HttpServletRequest request) {
        return "/citizen/forestProduceClearance";
    }

    @ResponseBody
    @RequestMapping(value = "public/loadpagetoemptylayout/saveOnlineTimberPermit",  method = RequestMethod.POST )
    public ResponseMessage save(OnlineTimberDTO timberDto,HttpServletRequest request,ModelMap model) {
        String msg="";
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            OnlineTimberDTO insertApplication = serviceCitizen.saveOnlineTimberApplication(timberDto,request);
            if (insertApplication.getStatus().equalsIgnoreCase("Success")) {
                responseMessage.setStatus(1);
                responseMessage.setText("Your requisition for timber permit is successfully submitted with application number " + timberDto.getApplication_Number());
            } else {
                responseMessage.setStatus(0);
                responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setStatus(0);
            responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
        }
       // return "/common/ackPage";
       return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "public/getParkDropdown", method = RequestMethod.GET)
    public List<NewTimberDto> getDropdownDetails(@RequestParam("dzongkhagId") String dzongkhagId ,@RequestParam("type") String type,HttpServletRequest request) {
        return serviceCitizen.getDropdownList(dzongkhagId,type,request);
    }

    @ResponseBody
    @RequestMapping(value = "public/getConstructionLocation", method = RequestMethod.GET)
    public List<NewTimberDto> getConstructionLocation(@RequestParam("gewogId") String gewogId ,@RequestParam("type") String type,HttpServletRequest request) {
        return serviceCitizen.getDropdownList(gewogId, type, request);
    }

    @ResponseBody
    @RequestMapping(value = "public/initiate/timberReplacementRequest", method = RequestMethod.GET)
    public ResponseMessage getTimberReplacementRequest(String appNo){
        return serviceCitizen.getTimberReplacementRequest(appNo);
    }

    @ResponseBody
    @RequestMapping(value = "public/initiate/submitTimberRR", method = RequestMethod.POST)
    public ResponseMessage timberReplaceRequest(String appNo,HttpServletRequest request,WorkFlowDto dto){
        return serviceCitizen.submitTimberRR(appNo, dto, request);
    }

    @ResponseBody
    @RequestMapping(value = "public/initiate/checkForMarking", method = RequestMethod.GET)
    public ResponseMessage checkForMarking(String appNo){
        return serviceCitizen.checkForMarking(appNo);
    }

    @ResponseBody
    @RequestMapping(value = "public/initiate/scheduleMarkingDate", method = RequestMethod.POST)
    public ResponseMessage scheduleMarkingDate(String appNo,HttpServletRequest request,String markingDate){
        return serviceCitizen.scheduleMarkingDate(appNo, markingDate, request);
    }

    @ResponseBody
    @RequestMapping(value = "public/initiate/checkForSawingPermit", method = RequestMethod.GET)
    public ResponseMessage checkForSawingPermit(String appNo){
        return serviceCitizen.checkForSawingPermit(appNo);
    }

    @ResponseBody
    @RequestMapping(value = "public/initiate/scheduleSawingPermitDate", method = RequestMethod.POST)
    public ResponseMessage scheduleSawingPermitDate(String appNo,HttpServletRequest request,String sawingPermitDate,String nameOfOperator,
              String licenseNo,String locationOfSawmill,String rateOfSawing,int mode_of_Swing_Id){
        return serviceCitizen.scheduleSawingPermitDate(appNo, sawingPermitDate, request, nameOfOperator, licenseNo, locationOfSawmill, rateOfSawing,mode_of_Swing_Id);
    }

    @ResponseBody
    @RequestMapping(value = "public/initiate/fetchAllotmentDtls", method = RequestMethod.GET)
    public ResponseMessage fetchAllotmentDtls(String appNo){
        return serviceCitizen.fetchAllotmentDtls(appNo);
    }

    @ResponseBody
    @RequestMapping(value = "public/initiate/fetchPermitDtls", method = RequestMethod.GET)
    public ResponseMessage fetchPermitDtls(String appNo){
        return serviceCitizen.fetchPermitDtls(appNo);
    }

    @ResponseBody
    @RequestMapping(value = "public/initiate/fetchSowingPermitDtls", method = RequestMethod.GET)
    public ResponseMessage fetchSowingPermitDtls(String appNo){
        return serviceCitizen.fetchSowingPermitDtls(appNo);
    }

    @ResponseBody
    @RequestMapping(value = "public/initiate/fetchForestProduceDtls", method = RequestMethod.GET)
    public ResponseMessage fetchForestProduceDtls(String appNo,HttpServletRequest request){
        return serviceCitizen.fetchForestProduceDtls(appNo,request);
    }

    @ResponseBody
    @RequestMapping(value = "public/initiate/isDateAvailable", method = RequestMethod.GET)
    public ResponseMessage isDateAvailable(String thisDate,Integer allot_Range_Officer){
        return serviceCitizen.isDateAvailable(thisDate,allot_Range_Officer);
    }

    @ResponseBody
    @RequestMapping(value = "public/initiate/timberExtensionRequest", method = RequestMethod.GET)
    public ResponseMessage getTimberExtensionRequest(String appNo){
        return serviceCitizen.getTimberExtensionRequest(appNo);
    }

    @ResponseBody
    @RequestMapping(value = "public/loadpagetoemptylayout/submitTimberPE", method = RequestMethod.POST)
    public String submitTimberPE(HttpServletRequest request,WorkFlowDto dto,@RequestParam("files") MultipartFile[] files, ModelMap model){
       // return serviceCitizen.submitTimberPE(appNo,dto,request);
        String remarks = dto.getRemarks();
        String appNo = dto.getApplication_Number();
        ResponseMessage responseMessage = new ResponseMessage();
        String retval="";

        responseMessage=serviceCitizen.submitTimberPE(appNo, dto, request,files,remarks);
        retval = responseMessage.getText();
        model.addAttribute("acknowledgement_message",responseMessage.getText());

        return retval;
    }

    @RequestMapping(value = "/public/initiate/privateLandPermit", method = RequestMethod.GET)
    public String privateLandPermit(ModelMap model,NewTimberDto dto,HttpServletRequest request) {
        // model.addAttribute("message");
        model.addAttribute("cons_type");
        Integer dzo_Id = serviceRuralTimber.getDzoId(request, dto);
        model.addAttribute("Park_List", serviceRuralTimber.getParkList(request, dzo_Id));
        model.addAttribute("DZONGKHAG_LIST", serviceRuralTimber.getDzongkhagList());
        model.addAttribute("Forest_Produce", serviceCommon.getForestProduce());
        return "citizen/permitFromPrivateLand";
    }

    @ResponseBody
    @RequestMapping(value = "public/loadpagetoemptylayout/submitPrivateLand",method = RequestMethod.POST)
    public String submitPrivateLand(OnlineTimberDTO timberDto, NewTimberDto newTimberDto,@RequestParam("files") MultipartFile[] files, ModelMap model, HttpServletRequest request){
        String retval="";
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage = serviceCitizen.submitPrivateLand(timberDto,newTimberDto,files,request);
        retval = responseMessage.getText();
        model.addAttribute("acknowledgement_message",responseMessage.getText());
        return retval;
    }

    @RequestMapping(value = "/public/initiate/firewoodAndFencingPoles", method = RequestMethod.GET)
    public String firewoodAndFencingPoles(ModelMap model,NewTimberDto dto,HttpServletRequest request,@RequestParam("cons_desc") String cons_desc) {
        model.addAttribute("cons_type");
      /*  Integer dzo_Id = serviceRuralTimber.getDzoId(request, dto);
        model.addAttribute("Park_List", serviceRuralTimber.getParkList(request,dzo_Id));
        model.addAttribute("DZONGKHAG_LIST", serviceRuralTimber.getDzongkhagList());*/
        model.addAttribute("Prod_List", serviceWoodPole.getProductDetails(cons_desc));
        return "citizen/permitForPoleAndFirewood";
    }

    @ResponseBody
    @RequestMapping(value = "public/loadpagetoemptylayout/saveWoodandPoles",method = RequestMethod.POST)
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
    @RequestMapping(value = "/getYearValidation", method = RequestMethod.GET)
    public BigInteger getYearValidation(@RequestParam("sl_no") String sl_no, @RequestParam("household_no") String house_no){
        BigInteger count = serviceWoodPole.getYearValidation(sl_no,house_no);
        return count;
    }

    @ResponseBody
    @RequestMapping(value = "/getUnitDetails", method = RequestMethod.GET)
    public List<CommonDto> getUnitDetails(@RequestParam("sl_no") String sl_no, @RequestParam("type") String type) {
        return serviceWoodPole.getUnitDetails(sl_no, type);
    }

    @ResponseBody
    @RequestMapping(value ="public/initiate/generatePDF", method = RequestMethod.GET)
    public ResponseMessage generateAllotmentOrderPDF(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String applicationNumber = request.getParameter("appNo");
        String fileType = request.getParameter("fileType");
        String totalVolume = request.getParameter("totalVolume");
        File filepath = null;

        ResponseMessage responseMessage = new ResponseMessage();

        JasperPrint jasperprint = new JasperPrint();
        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        WorkFlowDto workFlowDto = new WorkFlowDto();
        String url = null;

        try {
            workFlowDto =serviceCitizen.generateAllotmentOrderDtls(applicationNumber);
            workFlowDto.setApplication_Number(applicationNumber);

            PrintPDFUtility printPDFUtility = new PrintPDFUtility();
            //   url = request.getSession().getServletContext().getRealPath("/resources/JasperCertificate");
            filepath = new File(request.getSession().getServletContext().getRealPath("/resources/JasperCertificate"));
            jasperPrintList = printPDFUtility.getJasperPrintForExporting(
                    filepath.getPath(),response,workFlowDto,fileType,totalVolume);

            //  ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/pdf;charset=UTF-8");
           /* if(fileType.equalsIgnoreCase("allotmentOrder")){
                response.setHeader("Content-Disposition", "attachment; filename=allotmentOrder.pdf");
            }else{*/
                response.setHeader("Content-Disposition", "attachment; filename=certificate.pdf");
          //  }

            JRPdfExporter exporter = new JRPdfExporter();
            ServletOutputStream out = response.getOutputStream();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.exportReport();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e);
        }
        return null;
    }
}
