package bt.gov.ditt.dofps.common;

import bt.gov.ditt.dofps.dto.PaymentDTO;
import bt.gov.ditt.dofps.services.IServiceCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Deepak on 2/3/2021.
 */
@Controller
@RequestMapping("/paymentResponse")
public class PaymentResponse {
    @Autowired
    IServiceCommon serviceCommon;

    @RequestMapping(method = RequestMethod.GET)
    protected void PaymentResponseServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            System.out.println("================ reached to paymentResponse controller ====================");
            PaymentDTO userDTO = new PaymentDTO();
            userDTO.setApplication_Number(request.getParameter("applicationNo"));
            userDTO.setPaymentDate(request.getParameter("paymentDate"));
            userDTO.setReceiptNumber(request.getParameter("txnId"));
            userDTO.setModeOfPayment("Online");
            userDTO.setPaymentAmount((int)Float.parseFloat(request.getParameter("txnAmount")));
            serviceCommon.updatePayment(userDTO.getApplication_Number(),"",request,userDTO.getPaymentAmount(),userDTO.getModeOfPayment(),userDTO.getReceiptNumber());
        } catch (Exception e) {
            System.out.println("================ inside catch  ====================");
            e.printStackTrace();
        }
    }
}
