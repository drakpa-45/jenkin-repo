package bt.gov.ditt.dofps.controller.admin.PrivateLandProduce;

import bt.gov.ditt.dofps.common.ResponseMessage;
import bt.gov.ditt.dofps.dto.WorkFlowDto;
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

/**
 * Created by Pema Drakpa on 20/02/2023.
 */
@Controller
@RequestMapping("/adminPrivateLandProduce")
public class AdminPrivateLandProduceController {

    @Autowired
    PrivateLandProduceService privateLandProduceService;

    @ResponseBody
    @RequestMapping(value = "/privateLandProduceGupForwardToRO")
    public ResponseMessage forwardToRO(HttpServletRequest request, ModelMap model){
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        int range_id = Integer.parseInt(request.getParameter("range_id"));
        ResponseMessage responseMessage = privateLandProduceService.privateLandProduceGupForwardToRO(appNo, remarks, request,range_id);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/privateLandProduceROForwardToCFO", method = RequestMethod.POST )
    public String privateLandProduceROForwardToCFO(WorkFlowDto workFlowDto,HttpServletRequest request, ModelMap model, @RequestParam("files") MultipartFile[] files){
        String retval="";
        ResponseMessage responseMessage = new ResponseMessage();

        responseMessage = privateLandProduceService.privateLandProduceROForwardToCFO(workFlowDto, request, files);
        retval = responseMessage.getText();
        model.addAttribute("acknowledgement_message",responseMessage.getText());
        return retval;
    }

    @ResponseBody
    @RequestMapping(value = "/approvedPrivateLand")
    public ResponseMessage approvedPrivateLand(HttpServletRequest request, ModelMap model) {
        String appNo=request.getParameter("appNo");
        String remarks=request.getParameter("remarks");
        return privateLandProduceService.approvedPrivateLand(request,appNo,remarks);
    }
}
