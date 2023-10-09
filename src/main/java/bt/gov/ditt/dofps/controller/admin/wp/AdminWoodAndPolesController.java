package bt.gov.ditt.dofps.controller.admin.wp;

import bt.gov.ditt.dofps.common.ResponseMessage;
import bt.gov.ditt.dofps.dto.OnlineTimberDTO;
import bt.gov.ditt.dofps.services.WoodAndPolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Pema Drakpa on 06/03/2023.
 */
@Controller
@RequestMapping("/adminWoodAndPoles")
public class AdminWoodAndPolesController {

    @Autowired
    WoodAndPolesService woodAndPolesService;

    /**
     * gupApprove -- methods to approve application to CFO
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/woodAndPolesForwardToCFO")
    public ResponseMessage gupApprove(HttpServletRequest request, ModelMap model){
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        ResponseMessage responseMessage = woodAndPolesService.gupApprove(appNo, remarks, request);
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
    @RequestMapping(value = "/woodAndPolesForwardToRO")
    public ResponseMessage cfoApprove(HttpServletRequest request, ModelMap model,OnlineTimberDTO timberDto){
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        ResponseMessage responseMessage = woodAndPolesService.woodAndPolesForwardToRO(appNo, remarks, request, timberDto);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/forwardToOtherRange")
    public ResponseMessage forwardToOtherRange(HttpServletRequest request, ModelMap model){
        int rangeId= Integer.parseInt(request.getParameter("rangeId"));
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        ResponseMessage responseMessage = woodAndPolesService.forwardToOtherRange(appNo, remarks, request, rangeId);
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
        ResponseMessage responseMessage = woodAndPolesService.updatePayment(appNo, remarks, request, amount, modeOfPayment, receiptNo);
        return responseMessage;
    }

    /**
     * approveRO -- method to approved by Range officer
     * @param request --- contains application number and remarks
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/woodAndPolesROApprove")
    public ResponseMessage woodAndPolesROApprove(HttpServletRequest request, ModelMap model){
        String remarks = request.getParameter("remarks");
        String appNo = request.getParameter("appNo");
        String phoneNo = request.getParameter("phoneNo");
        return woodAndPolesService.woodAndPolesROApprove(appNo, remarks,phoneNo, request);
    }
}
