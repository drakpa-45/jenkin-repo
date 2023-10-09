package bt.gov.ditt.dofps.controller;

import bt.gov.ditt.dofps.dto.CommonDto;
import bt.gov.ditt.dofps.dto.ListDto;
import bt.gov.ditt.dofps.dto.WorkFlowDto;
import bt.gov.ditt.dofps.services.IServiceCommon;
import bt.gov.ditt.dofps.services.IServiceDealing;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.Date;

/**
 * Created by Pema Drakpa on 1/27/2020.
 */
@Controller
@RequestMapping(value = "/dealing")
public class ControllerDealing {

    @Autowired
    IServiceCommon serviceCommon;
    @Autowired
    IServiceDealing serviceDealing;

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
    @ResponseBody
    @RequestMapping(value = "/saveClaimedApplication")
    public String saveAndForward(WorkFlowDto workFlowDto,ListDto listDto,HttpServletRequest request, ModelMap model) {
        String pageToReturn = "";
        String appNo="";
        try {
            request.setAttribute("appNo", request.getParameter("value"));

            //WorkFlowDto update = serviceCommon.upadteClaimedApp(workFlowDto,listDto, request);
            WorkFlowDto update = serviceDealing.upadteClaimedApp(workFlowDto,listDto, request);
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>The Application  number " + request.getParameter("value") + " for the rural timber has been verified on "+ new Date() +".</h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'><h5>The Application  number " + request.getParameter("value")  + " for the rural timber has been verified on " + new Date() +".</h5></div></span>");
        }catch(Exception e){
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-danger text-center'><h5>Sorry! verification of this application number is unsuccessful </h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-danger '><div class='alert-danger text-danger text-center'><h5>Sorry! verification of this application number is unsuccessful </h5></div></span>");
            System.out.print("Sorry! verification of this application number is unsuccessful"+e);
           return pageToReturn;
        }
        return pageToReturn;
    }

    @ResponseBody
    @RequestMapping(value = "/rejectApplication")
    public String rejectApplication(WorkFlowDto workFlowDto,HttpServletRequest request, ModelMap model) {
        String pageToReturn = "";
        String appNo="";
        try {
            request.setAttribute("appNo", request.getParameter("value"));
            WorkFlowDto reject = serviceDealing.rejectApplication(workFlowDto, request);
             String reason = serviceDealing.getRejReason(workFlowDto);
            pageToReturn = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-danger text-center'><h5>This application is being rejected</h5></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-danger text-center'>This application is rejected because of some invalid/ missing information <br>Thank You</div></span>");
        }catch(Exception e){
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info '><div class='alert-info text-danger text-center'><h6>Sorry! verification of this application number is unsuccessful </h6></div></span>");
            System.out.print("Sorry! verification of this application number is unsuccessful"+e);
            return "" + e;
        }
        return pageToReturn;
    }


}