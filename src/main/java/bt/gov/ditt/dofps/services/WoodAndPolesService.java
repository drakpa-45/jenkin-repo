package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.common.ResponseMessage;
import bt.gov.ditt.dofps.common.SmsSender;
import bt.gov.ditt.dofps.dao.IDaoCommon;
import bt.gov.ditt.dofps.dao.IDaoWoodPole;
import bt.gov.ditt.dofps.dao.WoodAndPolesDao;
import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.dto.PaymentDTO;
import bt.gov.ditt.dofps.entitiy.PaymentEntity;
import bt.gov.ditt.dofps.entitiy.TaskDetailEntity;
import bt.gov.ditt.dofps.entitiy.TimberEntity;
import bt.gov.ditt.dofps.entitiy.WorkFlowEntity;
import bt.gov.g2c.aggregator.business.InvokePaymentWS;
import bt.gov.g2c.aggregator.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by Pema Drakpa on 06/03/2023.
 */
@Service
public class WoodAndPolesService{
    @Autowired
    WoodAndPolesDao woodAndPolesDao;

    @Autowired
    IDaoCommon daoCommon;

    @Transactional
    public ResponseMessage gupApprove(String appNo, String remarks, HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();

        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String actorName=userBean.getFullName();
        String assignPrivId="1813";
        String statusId="16";
        String taskId="5";

        String insertToTaskAudit = "";
        insertToTaskAudit = daoCommon.insertToTaskAudit(appNo);
        if(insertToTaskAudit.equalsIgnoreCase("Success")){
            insertToTaskAudit= daoCommon.insertToWorkFlowAudit(appNo);
            if(insertToTaskAudit.equalsIgnoreCase("Success")){
                insertToTaskAudit=daoCommon.insertTaskdtls(appNo,remarks,request,actorId,assignPrivId, taskId);
                if(insertToTaskAudit.equalsIgnoreCase("Success")){
                    insertToTaskAudit=daoCommon.insertToWorkFlow(appNo,remarks,request,statusId);
                    if(insertToTaskAudit.equalsIgnoreCase("Success")){
                        responseMessage.setStatus(1);
                        responseMessage.setText("Application Number" + appNo +" forwarded successfully to CFO");
                    }else{
                        responseMessage.setStatus(0);
                        responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                    }
                }
            }
        }
        return responseMessage;
    }

    @Transactional
    public ResponseMessage woodAndPolesForwardToRO(String appNo, String remarks, HttpServletRequest request, OnlineTimberDTO timberDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            HttpSession session = request.getSession();
            UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
            String roleId = userBean.getCurrentRole().getRoleId();
            String role_name = userBean.getCurrentRole().getRoleName();
            String actorId = userBean.getUserID();
            String actorName=userBean.getFullName();
            String assignPrivId="1841";
            String statusId="2";

            String taskId="5";
            String insertToTaskAudit = "";

            if (!org.apache.commons.collections.CollectionUtils.isEmpty(timberDto.getTimberDetails())) {
                for (TimberDetailsDto timberDetails : timberDto.getTimberDetails()) {
                    insertToTaskAudit =woodAndPolesDao.updateAllot(timberDetails,appNo,remarks);
                }
            }

            if(insertToTaskAudit.equalsIgnoreCase("Success")) {
                 RequestDTO dto = new RequestDTO();
                 dto.setApplicationNo(appNo);
                 dto.setAgencyCode("DAY01071");
                 dto.setServiceName("Department of Forests and Park Services");
                 dto.setExpiryDate(null);//only for renewal services or services which involve penalty or late fees
                 ArrayList<bt.gov.g2c.aggregator.dto.PaymentDTO> paymentList = new ArrayList<bt.gov.g2c.aggregator.dto.PaymentDTO>();
                 Integer product_Id=timberDto.getTimberDetails().get(0).getfP_Product_Id();
                 Integer totalAmt=timberDto.getTimberDetails().get(0).getTotal_Royalty();
                 Integer serviceFee=10;
                 totalAmt=totalAmt-serviceFee;
                 if(product_Id.equals(5) || product_Id.equals(15)){
                     bt.gov.g2c.aggregator.dto.PaymentDTO dto1 = new bt.gov.g2c.aggregator.dto.PaymentDTO();
                     dto1.setServiceFee(String.valueOf(totalAmt));
                     dto1.setAccountHeadId("115223602");
                     paymentList.add(dto1);
                 }else{
                     bt.gov.g2c.aggregator.dto.PaymentDTO dto2 = new bt.gov.g2c.aggregator.dto.PaymentDTO();
                     dto2.setServiceFee(String.valueOf(totalAmt));
                     dto2.setAccountHeadId("115223601");
                     paymentList.add(dto2);
                 }
                 bt.gov.g2c.aggregator.dto.PaymentDTO dto3 = new bt.gov.g2c.aggregator.dto.PaymentDTO();
                 dto3.setServiceFee("10");
                 dto3.setAccountHeadId("115229010");
                 paymentList.add(dto3);

                 dto.setPaymentList(paymentList.toArray(new bt.gov.g2c.aggregator.dto.PaymentDTO[paymentList.size()]));
                 ResourceBundle bundle = ResourceBundle.getBundle("wsEndPointURL_en_US");
                 InvokePaymentWS invokews = new InvokePaymentWS(bundle.getString("getPayment.endPointURL"));
                 boolean isSaved = invokews.insertPaymentDetailsOnApproval(dto);
                 System.out.println("Response from Aggregator: "+isSaved);

              /*  applicationDTO = passportUploadDAO.updateUploadWorkTaskFlowONLINE(applicationDTO, userDTO, request);

                ArrayList<String> contactNo=new ArrayList<String>();
                contactNo.add(applicationDTO.getMobile_Number());
                String[] MobileNo=contactNo.toArray(new String[contactNo.size()]);
                boolean isSend =  .notifyOnVerification(applicationDTO.getApplication_Number(),MobileNo);*/

                insertToTaskAudit=woodAndPolesDao.updatePaymentStatus(appNo,timberDto);

                if (insertToTaskAudit.equalsIgnoreCase("Success")) {
                    insertToTaskAudit = daoCommon.insertToTaskAudit(appNo);
                    if (insertToTaskAudit.equalsIgnoreCase("Success")) {
                        insertToTaskAudit = daoCommon.insertToWorkFlowAudit(appNo);
                        if (insertToTaskAudit.equalsIgnoreCase("Success")) {
                            insertToTaskAudit = daoCommon.insertTaskdtls(appNo, remarks, request, actorId, assignPrivId, taskId);
                            if (insertToTaskAudit.equalsIgnoreCase("Success")) {
                                insertToTaskAudit = daoCommon.insertToWorkFlow(appNo, remarks, request, statusId);
                                if (insertToTaskAudit.equalsIgnoreCase("Success")) {
                                    responseMessage.setStatus(1);
                                    responseMessage.setText("Application Number" + appNo + " forwarded successfully to RO");
                                    String smsContent = "Dear user,<br>"+
                                            "Your application Number: "+appNo+" is verified by CFO on "+new Date()+ ". You can make payment through given link:https://tinyurl.com/y3m7wa3c or visit range office. Once payment is made, you can schedule your marking date.";
                                    System.out.println("----- phone number ----------"+timberDto.getPhone_Number());
                                    SmsSender.smsSender(timberDto.getPhone_Number(), "", null, smsContent, "Application Verified Success");
                                } else {
                                    responseMessage.setStatus(0);
                                    responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseMessage;
    }

    @Transactional
    public ResponseMessage updatePayment(String appNo, String remarks, HttpServletRequest request, int amount, String modeOfPayment, String receiptNo) {
        HttpSession session = request.getSession();
        ResponseMessage responseMessage = new ResponseMessage();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String actorId = userBean.getUserID();
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorName=userBean.getFullName();

        String assignPrivId="1842";
        String statusId="3";
        String taskId="6";

        String insertToTaskAudit = "";
        Date permitExpiryDate =null;

        String permitNumber = permitFormatter(daoCommon.getPermitSequenceNo(182));
        List<TimberDetailsDto> product_name=daoCommon.getProduct_catagory(appNo);

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setApplicationNumber(appNo);
        paymentEntity.setServiceId(181);
        paymentEntity.setUserId(actorId);
        paymentEntity.setCcId(actorId);
        paymentEntity.setAmountCollected(amount);
        paymentEntity.setDateOfReceipt(new Date());
        paymentEntity.setPaymentReceiptNo(receiptNo);
        paymentEntity.setModeOfPayment(modeOfPayment);
        daoCommon.saveEntity(paymentEntity);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        WorkFlowDto dto = daoCommon.getapplicationDetails(request,"",appNo);
     /*   if(dto.getCons_Type().equalsIgnoreCase("WP")){
            calendar.add(Calendar.YEAR, 2);
        }else{*/
            calendar.add(Calendar.YEAR, 1);
        //}

        permitExpiryDate=calendar.getTime();

       /* LocalDate currentDate = LocalDate.now();
        String dateOfReceipt = currentDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));*/

        Date dateOfReceipt = new Date();

        daoCommon.updateOfflinePayment(appNo,permitNumber,permitExpiryDate,dateOfReceipt);

        responseMessage.setStatus(1);
        responseMessage.setText("Payment updated successfully!");

        /*Sms notification saying that client to apply for marking date*/
        return responseMessage;
    }


    private String permitFormatter(long permitSequenceNo) {

        String firstpart="",secondpart="";

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        System.out.println("Current year: " + year);

        int digitCount2 = String.valueOf(permitSequenceNo).length();
        StringBuffer sbf = new StringBuffer();
        firstpart="WP/";
        secondpart=year +"/";
        if(String.valueOf(digitCount2).length()==1){
            sbf.append(firstpart+secondpart+"000"+permitSequenceNo);
        } else if(String.valueOf(digitCount2).length()==2){
            sbf.append(firstpart+secondpart+"00"+permitSequenceNo);
        }else{
            sbf.append(firstpart+secondpart+permitSequenceNo);
        }
        return sbf.toString();
    }


    @Transactional
    public ResponseMessage woodAndPolesROApprove(String appNo, String remarks, String phoneNo, HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();

        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String actorName=userBean.getFullName();
        String assignPrivId="1842";
        String statusId="3";
        String taskId="6";

        String insertToTaskAudit = "";

        try{
            insertToTaskAudit = daoCommon.insertToTaskAudit(appNo);
            if(insertToTaskAudit.equalsIgnoreCase("Success")){
                insertToTaskAudit= daoCommon.insertToWorkFlowAudit(appNo);
                if(insertToTaskAudit.equalsIgnoreCase("Success")){
                    insertToTaskAudit=daoCommon.insertTaskdtls(appNo,remarks,request,actorId,assignPrivId, taskId);
                    if(insertToTaskAudit.equalsIgnoreCase("Success")){
                        insertToTaskAudit=daoCommon.insertToWorkFlow(appNo,remarks,request,statusId);
                        if(insertToTaskAudit.equalsIgnoreCase("Success")){
                            responseMessage.setStatus(1);
                            responseMessage.setText("Application Number" + appNo +" is approved successfully");

                            String smsContent = "Dear user,<br>"+
                                    "Your application Number: "+appNo+"is approved by Range Officer on "+new Date()+ ".You can apply for sawing permit.";
                            SmsSender.smsSender(phoneNo, "", null, smsContent, "Application Approved Success");
                        }else{
                            responseMessage.setStatus(0);
                            responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return responseMessage;
    }

    @Transactional
    public ResponseMessage forwardToOtherRange(String appNo, String remarks, HttpServletRequest request, int rangeId) {
        HttpSession session = request.getSession();
        ResponseMessage responseMessage = new ResponseMessage();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String actorId = userBean.getUserID();
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorName=userBean.getFullName();
        String assignPrivId="1842";
        String statusId="25";
        String taskId="6";

        String insertToTaskAudit = "";

        List<TimberDetailsDto> product_name=daoCommon.getProduct_catagory(appNo);

        insertToTaskAudit=daoCommon.forwardToOtherRange(appNo, remarks,rangeId);

        if(insertToTaskAudit.equalsIgnoreCase("Success")) {
            insertToTaskAudit= daoCommon.insertToWorkFlowAudit(appNo);
            if(insertToTaskAudit.equalsIgnoreCase("Success")) {
                insertToTaskAudit = daoCommon.updateWorkFlowRange(appNo, remarks, request, statusId);
                responseMessage.setStatus(1);
                responseMessage.setText("Application Forwarded Successfully!");
            }
        }else{
            responseMessage.setStatus(0);
            responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
        }
        /*Sms notification saying that client to apply for marking date*/

        return responseMessage;
    }
}
