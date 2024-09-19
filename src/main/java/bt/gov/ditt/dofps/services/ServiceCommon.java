package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.common.ResponseMessage;
import bt.gov.ditt.dofps.common.SmsSender;
import bt.gov.ditt.dofps.dao.IDaoCommon;
import bt.gov.ditt.dofps.dao.IsavePrivateLandDao;
import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.entitiy.*;
import bt.gov.g2c.aggregator.business.InvokePaymentWS;
import bt.gov.g2c.aggregator.dto.PaymentDTO;
import bt.gov.g2c.aggregator.dto.RequestDTO;
import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import bt.gov.g2c.framework.userdetail.InvokeWS;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Pema Drakpa on 1/24/2020.
 */
@Service
public class ServiceCommon implements IServiceCommon{
    @Autowired
    IDaoCommon daoCommon;

    @Autowired
    IsavePrivateLandDao isavePrivateLandDao;

    @Override
    @Transactional(readOnly = true)
    public UserRolePrivilege populateUserRolePrivilegeHierarchy(UserRolePrivilege userRolePrivilege, String roleId, HttpServletRequest request) {
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String userRolePrivilegeEndPointUrl =resourceBundle1.getString("getUserRolePrivilege.endPointURL");
        String deptCode = resourceBundle1.getString("getDeptCode.endPointURL");
        HttpSession session = request.getSession();
        try{
            InvokeWS invokeWS = null;
            invokeWS = new InvokeWS(userRolePrivilegeEndPointUrl);
            userRolePrivilege = invokeWS.populateUserRolePrivilegeHierarchy(userRolePrivilege,roleId,deptCode);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userRolePrivilege;
    }

    @Override
    @Transactional
    public List<NewTimberDto> populateTaskListforSelectedServices(HttpServletRequest request, String type) {
        List<NewTimberDto> dto = daoCommon.populateTaskListforSelectedServices(request,type);
        return dto;
    }
    @Override
    @Transactional
    public NewTimberDto updateApplicationToClaimed(HttpServletRequest request) {
        return daoCommon.updateApplicationToClaimed(request);
    }

    @Override
    @Transactional
    public List<CommonDto> getProductListOnline(String cons_desc, String storey, String cons_type) {
        return daoCommon.getProductListOnline(cons_desc, storey, cons_type);
    }

    @Override
    @Transactional
    public List<CommonDto> productDetailsList(String cons_desc, String cons_type, String product_id) {
        return daoCommon.productDetailsList(cons_desc, cons_type, product_id);
    }

    @Override
    @Transactional
    public List<AppHistoryDTO> getViewStatusApplication(String applicationNumber) {
        return daoCommon.getViewStatusApplication(applicationNumber);
    }

    @Override
    @Transactional
    public ResponseMessage forwardToGAO(String appNo, String remarks, HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();

        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String actorName=userBean.getFullName();
        String assignPrivId="1811";
        String statusId="14";
        String taskId="5";

        String insertToTaskAudit = "";
        insertToTaskAudit = daoCommon.insertToTaskAudit(appNo);
        if(insertToTaskAudit.equalsIgnoreCase("Success")){
            insertToTaskAudit= daoCommon.insertToWorkFlowAudit(appNo);
            if(insertToTaskAudit.equalsIgnoreCase("Success")){
                insertToTaskAudit=daoCommon.insertTaskdtls(appNo,remarks,request,actorId,assignPrivId, taskId);
                if(insertToTaskAudit.equalsIgnoreCase("Success")){
                    insertToTaskAudit=daoCommon.insertToWorkFlow(appNo,remarks,request, statusId);
                    if(insertToTaskAudit.equalsIgnoreCase("Success")){
                        responseMessage.setStatus(1);
                        responseMessage.setText("Application Number" + appNo + "forwarded successfully to GAO");
                    }else{
                        responseMessage.setStatus(0);
                        responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                    }
                }
            }
        }
        return responseMessage;
    }

    @Override
    @Transactional
    public ResponseMessage updateInspectionDetails(InspectionDTO inspectionDTO, HttpServletRequest request, MultipartFile[] files) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<InspectionTeamEntity> inspectionTeamEntityList = inspectionDTO.getInspectionTeamEntityList();
        List<PastRecordDetailEntity> pastRecordDetailEntityList = inspectionDTO.getPastRecordDetailEntityList();

        String uuid = null,filePath=null,status="",insertToTaskAudit="";
        DocumentEntity document= new DocumentEntity();

        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String actorName=userBean.getFullName();
        String assignPrivId="1822";
        String statusId="15";
        String taskId="5";
        String fileType="IR";

        String appNo="";
        appNo= inspectionDTO.getApplication_Number();
        try{
            insertToTaskAudit = daoCommon.updateFPA(appNo, request, inspectionDTO);
            //region to insert inspection report
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                String filename = files[i].getOriginalFilename();
                String randomUUID = UUID.randomUUID().toString();
                uuid = randomUUID.replaceAll("-", "");
                ResourceBundle resourceBundle = ResourceBundle.getBundle("documentUploads");
                String filePathPrefix = resourceBundle.getString("fileStore");
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
                String urlAppender = "/" + calendar.get(Calendar.YEAR) + "/" + dateFormat.format(calendar.getTime()) + "/" + calendar.get(Calendar.DATE) + "/";
                filePath = filePathPrefix + urlAppender + uuid + "_" + filename;
                File fileloc = new File(urlAppender);
                if (!fileloc.exists()) {
                    new File(filePathPrefix + urlAppender).mkdirs();
                }
                FileCopyUtils.copy(file.getBytes(), new File(filePath));
                DocumentEntity doc = new DocumentEntity();
                doc.setDocument_Name(filename);
                // files[i].getContentType();
                doc.setDocument_Type(files[i].getContentType());
                doc.setUpload_URL(filePath);
                doc.setuUID(randomUUID);
                //doc.setManual_Document_Name(ServiceName);
                doc.setApplication_Number(appNo);
                doc.setType(fileType);
                daoCommon.saveEntity(doc);
                if (!status.equalsIgnoreCase("Success")) {
                    document.setApplication_Number(null);
                } else {
                    document.setApplication_Number(appNo);
                }
            }
            //end of region

            //region Save Inspection Teams
            if(inspectionTeamEntityList != null && !inspectionTeamEntityList.isEmpty()) {
                for (InspectionTeamEntity inspectionTeamEntity : inspectionTeamEntityList) {
                    inspectionTeamEntity.setApplication_Number(appNo);
                    daoCommon.saveUpdate(inspectionTeamEntity);
                }
            }
            //end of region

            //region Save past timber taken details
            if(inspectionDTO.getHasAvailTimberB4().equalsIgnoreCase("Yes")){
                if(pastRecordDetailEntityList != null && !pastRecordDetailEntityList.isEmpty()) {
                    for (PastRecordDetailEntity pastRecordDetailEntity : pastRecordDetailEntityList) {
                        pastRecordDetailEntity.setApplication_Number(appNo);
                        daoCommon.saveUpdate(pastRecordDetailEntity);
                    }
                }
            }
            //end of region

            if(insertToTaskAudit.equalsIgnoreCase("Success")) {
                insertToTaskAudit = daoCommon.insertToTaskAudit(appNo);
                if (insertToTaskAudit.equalsIgnoreCase("Success")) {
                    insertToTaskAudit = daoCommon.insertToWorkFlowAudit(appNo);
                    if(insertToTaskAudit.equalsIgnoreCase("Success")){
                        insertToTaskAudit=daoCommon.insertTaskdtls(appNo,inspectionDTO.getRemarks(),request,actorId,assignPrivId, taskId);
                        if (insertToTaskAudit.equalsIgnoreCase("Success")) {
                            insertToTaskAudit = daoCommon.insertToWorkFlow(appNo, inspectionDTO.getRemarks(), request, statusId);
                            if (insertToTaskAudit.equalsIgnoreCase("Success")) {
                                responseMessage.setStatus(1);
                                responseMessage.setText("Inspection report for application Number:" + appNo + "has been updated successfully!");
                            } else {
                                responseMessage.setStatus(0);
                                responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
        }
        return responseMessage;
    }

    @Override
    @Transactional(readOnly = true)
    public List getDesignation() {
        return daoCommon.getDesignation();
    }

    @Override
    @Transactional(readOnly = true)
    public List getTimberType(String cons_type) {
        return daoCommon.getTimberType(cons_type);
    }

    @Override
    @Transactional
    public List<InspectionTeamEntity> getInspectionTeamMembers(String applicationNumber) {
        return daoCommon.getInspectionTeamMembers(applicationNumber);
    }

    @Override
    @Transactional
    public List<PastRecordDetailEntity> getPastRecordDetails(String applicationNumber) {
        return daoCommon.getPastRecordDetails(applicationNumber);
    }

    @Override
    @Transactional
    public ResponseMessage gupApprove(String appNo, String remarks, HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();

        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String actorName=userBean.getFullName();

        String assignPrivId="";
        String statusId="";
        String taskId="";
        String insertToTaskAudit = "";

        String consType = daoCommon.getConsType(appNo);
        if(consType.equalsIgnoreCase("o")){
             assignPrivId="1841";
             statusId="2";
             taskId="5";
            int rangeId= Integer.parseInt(request.getParameter("rangeId"));
            insertToTaskAudit=daoCommon.forwardToOtherRange(appNo,remarks,rangeId);
        }else{
             assignPrivId="1813";
             statusId="16";
             taskId="5";
        }

        insertToTaskAudit = daoCommon.insertToTaskAudit(appNo);
        if(insertToTaskAudit.equalsIgnoreCase("Success")){
            insertToTaskAudit= daoCommon.insertToWorkFlowAudit(appNo);
            if(insertToTaskAudit.equalsIgnoreCase("Success")){
                insertToTaskAudit=daoCommon.insertTaskdtls(appNo,remarks,request,actorId,assignPrivId, taskId);
                if(insertToTaskAudit.equalsIgnoreCase("Success")){
                    insertToTaskAudit=daoCommon.insertToWorkFlow(appNo,remarks,request,statusId);
                    if(insertToTaskAudit.equalsIgnoreCase("Success")){
                        responseMessage.setStatus(1);
                        if(consType.equalsIgnoreCase("o")){
                            responseMessage.setText("Application Number" + appNo +" forwarded successfully to RO");
                        }else{
                            responseMessage.setText("Application Number" + appNo +" forwarded successfully to CFO");
                        }
                    }else {
                        responseMessage.setStatus(0);
                        responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                    }
                }
            }
        }
        return responseMessage;
    }

    @Override
    @Transactional
    public ResponseMessage cfoApprove(String appNo, String remarks, HttpServletRequest request, OnlineTimberDTO timberDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            HttpSession session = request.getSession();
            UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
            String roleId = userBean.getCurrentRole().getRoleId();
            String role_name = userBean.getCurrentRole().getRoleName();
            String actorId = userBean.getUserID();
            String actorName=userBean.getFullName();
            String assignPrivId="1841";
            String statusId="";
            if(timberDto.getStatus_Id().equals(21)){
                statusId="23";
            }else{
                statusId="2";
            }

            String taskId="5";
            String insertToTaskAudit = "";

/*        if(timberDto.getStatus().equalsIgnoreCase("Log")){
            taskId="6";
            statusId="3";
        }*/

            if (!CollectionUtils.isEmpty(timberDto.getTimberDetails())) {
                if(timberDto.getStatus_Id().equals(21)){
                    insertToTaskAudit = daoCommon.insertToAllotmentAudit(appNo);
                }
                for (TimberDetailsDto timberDetails : timberDto.getTimberDetails()) {
                    insertToTaskAudit =daoCommon.updateAllot(timberDetails,appNo,remarks);
                }
            }

            if(insertToTaskAudit.equalsIgnoreCase("Success")) {
                insertToTaskAudit=daoCommon.updateMarkingArea(appNo, timberDto);
                if(timberDto.getStatus_Id().equals(21)){

                }else{
               /* if(timberDto.getStatus().equalsIgnoreCase("Log")){

                }else{
                    RequestDTO dto = new RequestDTO();
                    dto.setApplicationNo(appNo);
                    dto.setAgencyCode("DAY01071");
                    dto.setServiceName("Department of Forests and Park Services");
                    dto.setExpiryDate(null);
                    ArrayList<PaymentDTO> paymentList = new ArrayList<PaymentDTO>();
                    PaymentDTO paymentdto = new PaymentDTO();
                    //Integer amount = passportUploadDAO.getServiceFees(applicationDTO.getApplication_Number());
                    paymentdto.setServiceFee(String.valueOf(amount));

                    //paymentdto.setAccountHeadId("131310001");
                    paymentdto.setAccountHeadId("5100083189001") ;
                    paymentList.add(paymentdto);

                    dto.setPaymentList(paymentList.toArray(new PaymentDTO[paymentList.size()]));
                    System.out.println("Response from Aggregator: "+paymentdto.getServiceFee());

                    InvokePaymentWS invokews = new InvokePaymentWS(bundle.getString("getPayment.endPointURL"));
                    boolean isSaved = invokews.insertPaymentDetailsOnApproval(dto);
                    System.out.println("Response from Aggregator: "+isSaved);*/

                    RequestDTO dto = new RequestDTO() ;
                    dto.setApplicationNo(appNo);
                    dto.setAgencyCode("DAY02881");
                    dto.setServiceName("Department of Forests and Park Services");
                    dto.setExpiryDate(null);//only for renewal services or services which involve penalty or late fees
                    ArrayList<PaymentDTO> paymentList = new ArrayList<PaymentDTO>();
                    Integer product_Id=timberDto.getTimberDetails().get(0).getfP_Product_Id();
                    Integer totalAmt=timberDto.getTimberDetails().get(0).getTotal_Royalty();
                    Integer serviceFee=10;
                    totalAmt=totalAmt-serviceFee;
                    if(product_Id.equals(5) || product_Id.equals(15)){
                        PaymentDTO dto1 = new PaymentDTO();
                        //dto1.setServiceFee(String.valueOf(totalAmt));
                        dto1.setServiceFee("1");
                        dto1.setAccountHeadId("115223602");
                        paymentList.add(dto1);
                    }else{
                        PaymentDTO dto2 = new PaymentDTO();
                        //dto2.setServiceFee(String.valueOf(totalAmt));
                        dto2.setServiceFee("1");
                        dto2.setAccountHeadId("115223601");
                        paymentList.add(dto2);
                    }
                    PaymentDTO dto3 = new PaymentDTO();
                    //dto3.setServiceFee("10");
                    dto3.setServiceFee("1");
                    dto3.setAccountHeadId("115229010");
                    paymentList.add(dto3);

                    dto.setPaymentList(paymentList.toArray(new PaymentDTO[paymentList.size()]));
                    /*ResourceBundle bundle = ResourceBundle.getBundle("wsEndPointURL_en_US");
                    InvokePaymentWS invokews = new InvokePaymentWS(bundle.getString("getPayment.endPointURL"));
                    boolean isSaved = invokews.insertPaymentDetailsOnApproval(dto);
                    System.out.println("Response from Aggregator: "+isSaved);*/
                    insertToTaskAudit=daoCommon.updatePaymentStatus(appNo,timberDto);
                    //  }
                }
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
                                    if(timberDto.getStatus().equalsIgnoreCase("Log")){
                                        responseMessage.setText("Application Number" + appNo + " approved successfully");
                                    }else{
                                        responseMessage.setText("Application Number" + appNo + " forwarded successfully to RO");
                                    }
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
            responseMessage.setStatus(0);
        }
        return responseMessage;
    }


    @Override
    @Transactional
    public ResponseMessage ocRangeApprove(String appNo, String remarks, HttpServletRequest request, OnlineTimberDTO timberDto) {
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

            if (!CollectionUtils.isEmpty(timberDto.getTimberDetails())) {
                for (TimberDetailsDto timberDetails : timberDto.getTimberDetails()) {
                    insertToTaskAudit =daoCommon.updateAllot(timberDetails,appNo,remarks);
                }
            }

            if(insertToTaskAudit.equalsIgnoreCase("Success")) {
                insertToTaskAudit=daoCommon.updateMarkingArea(appNo, timberDto);

                RequestDTO dto = new RequestDTO() ;
                dto.setApplicationNo(appNo);
                dto.setAgencyCode("DAY02881");
                dto.setServiceName("Department of Forests and Park Services");
                dto.setExpiryDate(null);//only for renewal services or services which involve penalty or late fees
                ArrayList<PaymentDTO> paymentList = new ArrayList<PaymentDTO>();
                Integer product_Id=timberDto.getTimberDetails().get(0).getfP_Product_Id();
                Integer totalAmt=timberDto.getTimberDetails().get(0).getTotal_Royalty();
                Integer serviceFee=10;
                totalAmt=totalAmt-serviceFee;

                    if(product_Id.equals(5) || product_Id.equals(15)){
                        PaymentDTO dto1 = new PaymentDTO();
                        dto1.setServiceFee(String.valueOf(totalAmt));
                       // dto1.setServiceFee("1");
                        dto1.setAccountHeadId("115223602");
                        paymentList.add(dto1);
                    }else{
                        PaymentDTO dto2 = new PaymentDTO();
                        dto2.setServiceFee(String.valueOf(totalAmt));
                        //dto2.setServiceFee("1");
                        dto2.setAccountHeadId("115223601");
                        paymentList.add(dto2);
                    }
                    PaymentDTO dto3 = new PaymentDTO();
                    dto3.setServiceFee("10");
                   // dto3.setServiceFee("1");
                    dto3.setAccountHeadId("115229010");
                    paymentList.add(dto3);

                dto.setPaymentList(paymentList.toArray(new PaymentDTO[paymentList.size()]));
                ResourceBundle bundle = ResourceBundle.getBundle("wsEndPointURL_en_US");
                InvokePaymentWS invokews = new InvokePaymentWS(bundle.getString("getPayment.endPointURL"));
                boolean isSaved = invokews.insertPaymentDetailsOnApproval(dto);
                System.out.println("Response from Aggregator: "+isSaved);
                insertToTaskAudit=daoCommon.updateMarkingArea(appNo,timberDto);

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
                                    if(timberDto.getStatus().equalsIgnoreCase("Log")){
                                        responseMessage.setText("Application Number" + appNo + " approved successfully");
                                    }else{
                                        responseMessage.setText("Application Number" + appNo + " forwarded successfully to RO");
                                    }
                                    String smsContent = "Dear user,<br>"+
                                            "Your application Number: "+appNo+" is verified on "+new Date()+ ". You can make payment through given link:https://tinyurl.com/y3m7wa3c or visit range office. Once payment is made, you can schedule your marking date.";
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

    @Override
    @Transactional
    public BigInteger checkForValidation(String house_hold, String cons_type) {
        return daoCommon.checkForValidation(house_hold, cons_type);
    }

    @Override
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

        String permitNumber = permitFormatter(daoCommon.getPermitSequenceNo(181));
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
        if(dto.getCons_Type().equalsIgnoreCase("New Construction")){
            calendar.add(Calendar.YEAR, 2);
        }else{
            calendar.add(Calendar.YEAR, 1);
        }

        permitExpiryDate=calendar.getTime();

       /* LocalDate currentDate = LocalDate.now();
        String dateOfReceipt = currentDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));*/

        Date dateOfReceipt = new Date();

        daoCommon.updateOfflinePayment(appNo,permitNumber,permitExpiryDate,dateOfReceipt);

        if(product_name.get(0).getProduct_Catagory().equalsIgnoreCase("Log")){
            insertToTaskAudit = daoCommon.insertToTaskAudit(appNo);
            if(insertToTaskAudit.equalsIgnoreCase("Success")){
                insertToTaskAudit= daoCommon.insertToWorkFlowAudit(appNo);
                if(insertToTaskAudit.equalsIgnoreCase("Success")){
                    insertToTaskAudit=daoCommon.insertTaskdtls(appNo,remarks,request,actorId,assignPrivId, taskId);
                    if(insertToTaskAudit.equalsIgnoreCase("Success")){
                        insertToTaskAudit=daoCommon.insertToWorkFlow(appNo,remarks,request,statusId);
                        if(insertToTaskAudit.equalsIgnoreCase("Success")){
                            responseMessage.setStatus(1);
                            responseMessage.setText("Application Number" + appNo +" is approved and payment updated successfully");
                        }else{
                            responseMessage.setStatus(0);
                            responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                        }
                    }
                }
            }
        }else{
            responseMessage.setStatus(1);
            responseMessage.setText("Payment updated successfully!");
        }
        /*Sms notification saying that client to apply for marking date*/
        String smsContent = "Dear user,<br>"+
                "Your payment updated successfully of amount Nu.: "+amount+"  on "+new Date()+ ". Please schedule your marking date";
        try {
            SmsSender.smsSender(dto.getPhone_Number(), "", null, smsContent, "Payment Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMessage;
    }

    private String permitFormatter(long permitSequenceNo) {

        String firstpart="",secondpart="";

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        System.out.println("Current year: " + year);

        int digitCount2 = String.valueOf(permitSequenceNo).length();
        StringBuffer sbf = new StringBuffer();
        firstpart="RTP/";
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

    @Override
    @Transactional
    public ResponseMessage approveRO(String appNo, String remarks, HttpServletRequest request, InspectionDTO inspectionDTO) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<MarkingInformationEntity> markingInformationEntityList = inspectionDTO.getMarkingInformationEntityList();

        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String actorName=userBean.getFullName();
        String assignPrivId="1842";
        String statusId="3";
        String taskId="6";
        String phoneNumber= String.valueOf(inspectionDTO.getPhone_Number());

        String insertToTaskAudit = "";

        try{
            //region Save marking Info
            if(markingInformationEntityList != null && !markingInformationEntityList.isEmpty()) {
                for (MarkingInformationEntity markingInformationEntity : markingInformationEntityList) {
                    markingInformationEntity.setApplication_Number(appNo);
                    daoCommon.saveUpdate(markingInformationEntity);
                }
            }
            //end of region

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
                            System.out.println("----- phone number ----------"+inspectionDTO.getPhone_Number());
                            SmsSender.smsSender(phoneNumber, "", null, smsContent, "Application Approved Success");
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

    @Override
    @Transactional
    public ResponseMessage approveSWP(String appNo, String remarks, HttpServletRequest request, InspectionDTO inspectionDTO, String permitValidityDate, String officerOnDuty) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<CosdtmoItmo> cosdtmoItmosList = inspectionDTO.getCosdtmoItmosList();
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String actorName=userBean.getFullName();
        String assignPrivId="1842";
        String statusId="18";
        String taskId="7";

        String insertToTaskAudit = "";

        //region Save marking Info
        if(cosdtmoItmosList != null && !cosdtmoItmosList.isEmpty()) {
            for (CosdtmoItmo cosdtmoItmo : cosdtmoItmosList) {
                cosdtmoItmo.setApplication_Number(appNo);
                daoCommon.saveUpdate(cosdtmoItmo);
            }
        }
        //end of region
        insertToTaskAudit=daoCommon.updatePermitDateOfficerOnDuty(appNo, permitValidityDate,officerOnDuty);

        insertToTaskAudit = daoCommon.insertToTaskAudit(appNo);
        if(insertToTaskAudit.equalsIgnoreCase("Success")){
            insertToTaskAudit= daoCommon.insertToWorkFlowAudit(appNo);
            if(insertToTaskAudit.equalsIgnoreCase("Success")){
                insertToTaskAudit=daoCommon.insertTaskdtls(appNo, remarks, request, actorId, assignPrivId, taskId);
                if(insertToTaskAudit.equalsIgnoreCase("Success")){
                    insertToTaskAudit=daoCommon.insertToWorkFlow(appNo, remarks, request, statusId);
                    if(insertToTaskAudit.equalsIgnoreCase("Success")){
                        responseMessage.setStatus(1);
                        responseMessage.setText("Application Number" + appNo +" is approved successfully");
                    }else{
                        responseMessage.setStatus(0);
                        responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                    }
                }
            }
        }
        return responseMessage;
    }

    @Override
    @Transactional
    public ResponseMessage approvePermitExtension(String appNo, String remarks, HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();

        WorkFlowDto dto = new WorkFlowDto();
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String actorName=userBean.getFullName();
        String assignPrivId="1841";
        String statusId="22";
        String taskId="5";

        String insertToTaskAudit = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 2);
        dto.setPermitExpiryDate(calendar.getTime());

        insertToTaskAudit=daoCommon.updatePermitExpiryDate(appNo, dto);
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
                            responseMessage.setText("Permit Extension date successfully updated for Application Number" + appNo);
                        } else {
                            responseMessage.setStatus(0);
                            responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                        }
                    }
                }
            }
        }
        return responseMessage;
    }

    @Override
    @Transactional
    public ResponseMessage verifyReplacement(String appNo, String remarks, HttpServletRequest request, MultipartFile[] files) {
        String uuid = null,filePath=null,status="",insertToTaskAudit="";
        DocumentEntity document= new DocumentEntity();
        ResponseMessage responseMessage = new ResponseMessage();

        WorkFlowDto dto = new WorkFlowDto();
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String actorName=userBean.getFullName();
        String assignPrivId="1813";
        String statusId="21";
        String taskId="5";
        String fileType="AR";
        try {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                String filename = files[i].getOriginalFilename();
                String randomUUID = UUID.randomUUID().toString();
                uuid = randomUUID.replaceAll("-", "");
                ResourceBundle resourceBundle = ResourceBundle.getBundle("documentUploads");
                String filePathPrefix = resourceBundle.getString("fileStore");
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
                String urlAppender = "/" + calendar.get(Calendar.YEAR) + "/" + dateFormat.format(calendar.getTime()) + "/" + calendar.get(Calendar.DATE) + "/";
                filePath = filePathPrefix + urlAppender + uuid + "_" + filename;
                File fileloc = new File(urlAppender);
                if (!fileloc.exists()) {
                    new File(filePathPrefix + urlAppender).mkdirs();
                }
                FileCopyUtils.copy(file.getBytes(), new File(filePath));
                DocumentEntity doc = new DocumentEntity();
                doc.setDocument_Name(filename);
                // files[i].getContentType();
                doc.setDocument_Type(files[i].getContentType());
                doc.setUpload_URL(filePath);
                doc.setuUID(randomUUID);
                //doc.setManual_Document_Name(ServiceName);
                doc.setApplication_Number(appNo);
                doc.setType(fileType);
                daoCommon.saveEntity(doc);
                if (!status.equalsIgnoreCase("Success")) {
                    document.setApplication_Number(null);
                } else {
                    document.setApplication_Number(appNo);
                }
            }
            insertToTaskAudit = daoCommon.insertToTaskAudit(appNo);
            if (insertToTaskAudit.equalsIgnoreCase("Success")) {
                insertToTaskAudit = daoCommon.insertToWorkFlowAudit(appNo);
                if (insertToTaskAudit.equalsIgnoreCase("Success")) {
                    insertToTaskAudit = daoCommon.insertTaskdtls(appNo, remarks, request, actorId, assignPrivId, taskId);
                    if (insertToTaskAudit.equalsIgnoreCase("Success")) {
                        insertToTaskAudit = daoCommon.insertToWorkFlow(appNo, remarks, request, statusId);
                        if (insertToTaskAudit.equalsIgnoreCase("Success")) {
                            responseMessage.setStatus(1);
                            responseMessage.setText("Timber replacement successfully verified");
                        } else {
                            responseMessage.setStatus(0);
                            responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                        }
                    }else {
                        responseMessage.setStatus(0);
                        responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                    }
                }else {
                    responseMessage.setStatus(0);
                    responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                }
            }else {
                responseMessage.setStatus(0);
                responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
            }
        }catch (Exception e){
            System.out.print(e);
            document.setApplication_Number(null);
        }
        return responseMessage;
    }

    @Override
    @Transactional
    public List<CommonDto> getSpeciesName(HttpServletRequest request) {
        return daoCommon.getSpeciesName(request);
    }

    @Override
    @Transactional
    public List<CommonDto> getConstructionStatus() {
        return daoCommon.getConstructionStatus();
    }

    @Override
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

    @Override
    @Transactional
    public List<NewTimberDto> getAllParkList() {
        return daoCommon.getAllParkList();
    }

    @Override
    @Transactional
    public ResponseMessage forwardToOtherCFO(String appNo, String remarks, HttpServletRequest request, int parkId) {
        HttpSession session = request.getSession();
        ResponseMessage responseMessage = new ResponseMessage();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String actorId = userBean.getUserID();
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorName=userBean.getFullName();
        String assignPrivId="1842";
        String statusId="24";
        String taskId="6";

        String insertToTaskAudit = "";

        List<TimberDetailsDto> product_name=daoCommon.getProduct_catagory(appNo);

        insertToTaskAudit=daoCommon.forwardToOtherCFO(appNo, remarks, parkId);

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

    @Override
    @Transactional
    public ResponseMessage reject(String appNo, String remarks, HttpServletRequest request) {
        String uuid = null,filePath=null,status="",insertToTaskAudit="";
        DocumentEntity document= new DocumentEntity();
        ResponseMessage responseMessage = new ResponseMessage();

        WorkFlowDto dto = new WorkFlowDto();
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String actorName=userBean.getFullName();
        String assignPrivId="1813";
        String statusId="7";
        String taskId="6";
        String phone_Number = request.getParameter("phone_Number");

        try{
            insertToTaskAudit = daoCommon.insertToTaskAudit(appNo);
            if(insertToTaskAudit.equalsIgnoreCase("Success")) {
                insertToTaskAudit = daoCommon.insertToWorkFlowAudit(appNo);
                if(insertToTaskAudit.equalsIgnoreCase("Success")) {
                    insertToTaskAudit = daoCommon.insertTaskdtls(appNo, remarks, request, actorId, assignPrivId, taskId);
                    if(insertToTaskAudit.equalsIgnoreCase("Success")) {
                        insertToTaskAudit = daoCommon.insertToWorkFlow(appNo, remarks, request, statusId);
                        if(insertToTaskAudit.equalsIgnoreCase("Success")) {
                            responseMessage.setStatus(1);
                            responseMessage.setText("Application rejected successfully");
                            String smsContent = "Dear user,<br>"+
                                    "Your application Number: "+appNo+"is rejected on "+new Date()+ ". Because of " + remarks;
                            System.out.println("----- phone number ----------"+phone_Number);
                            SmsSender.smsSender(phone_Number, "", null, smsContent, "Application Rejected");
                        }else{
                            responseMessage.setStatus(0);
                            responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.print(e);
            responseMessage.setStatus(0);
            responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
            document.setApplication_Number(null);
        }
        return responseMessage;
    }

    @Override
    @Transactional
    public List<NewTimberDto> getLandCategory() {
        return daoCommon.getLandCategory();
    }

    @Override
    @Transactional
    public List<NewTimberDto> getForestProduce() {
        return daoCommon.getForestProduce();
    }

    @Override
    @Transactional
    public List<NewTimberDto> getPrivateLandProduceSpecies(char species_Type) {
        return daoCommon.getPrivateLandProduceSpecies(species_Type);
    }

    @Override
    @Transactional
    public List<CommonDto> getProductList(String cons_desc, String type, String cons_type) {
        return daoCommon.getProductList(cons_desc, type,cons_type);
    }

    @Override
    @Transactional
    public List<CommonDto> getproductListWoodAndPole(String cons_type) {
        return daoCommon.getproductListWoodAndPole(cons_type);
    }

    @Override
    @Transactional
    public WorkFlowDto getapplicationDetails(HttpServletRequest request, String type) {
        WorkFlowDto dto = new WorkFlowDto();
        String application_number = request.getParameter("Application_Number");
        try{
            if(type.equalsIgnoreCase("PRL")){
                dto = isavePrivateLandDao.getapplDetails(application_number, type);
                List<TimberDetailsDto> product_name = daoCommon.getPvtProduct_catagory(application_number);
                dto.setTimberdetails(product_name);
                List<LandDetailsDto> landDetailsDtos = daoCommon.getLandDetails(application_number);
                dto.setLandDetailsDtos(landDetailsDtos);
            }else{
                dto=daoCommon.getapplicationDetails(request,type, application_number);
                List<TimberDetailsDto> product_name=daoCommon.getProduct_catagory(application_number);
                dto.setTimberdetails(product_name);
            }

            List<CommonDto> attachment=daoCommon.getAttachments(request);
            dto.setDocuments(attachment);

            List<CommonDto> range=daoCommon.getRange(request, dto.getDivision_Park_Id());
            dto.setCommonDetails(range);
        }catch(Exception e){
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    @Transactional
    public String getApplicationValidation(WorkFlowDto workFlowDto) {
        String status = "";
        try{
            BigInteger untExit = daoCommon.CountExit(workFlowDto);
            if(untExit.intValue()==0){
                return "true";
            }else{
                return "failed";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }

    @Override
    @Transactional
    public List<CommonDto> getRange(HttpServletRequest request, Integer divisionParkId) {
        List<CommonDto> range=daoCommon.getRange(request,divisionParkId);
        return range;
    }

    @Override
    @Transactional
    public CommonDto getDocumentDetailsByDocId(String uploadDocId) {
        CommonDto dto = daoCommon.getDocumentDetailsByDocId(uploadDocId);
        return dto;
    }

    @Override
    @Transactional
    public List<WorkFlowDto> fetchAppStatus(WorkFlowDto workFlowDto, HttpServletRequest request) {
       return daoCommon.fetchAppStatus(workFlowDto, request);

    }

    @Override
    @Transactional
    public List<WorkFlowDto> printDetails(WorkFlowDto workFlowDto, HttpServletRequest request) {
        List<WorkFlowDto> dto = new ArrayList<WorkFlowDto>();
        dto = daoCommon.printDetails(workFlowDto, request);
        return dto;
    }

    @Override
    @Transactional
    public List<WorkFlowDto> printPRLDetails(WorkFlowDto workFlowDto, String request) {
        List<WorkFlowDto> dto = new ArrayList<WorkFlowDto>();
        dto = daoCommon.printPRLDetails(workFlowDto, request);
        return dto;
    }

    @Override
    @Transactional
    public List<WorkFlowDto> getApplicationNoByCID(String CID, String type) {
        return daoCommon.getApplicationNoByCID(CID,type);
    }

    @Override
    @Transactional
    public BigInteger CountDuration(String cid, String type) {
        return daoCommon.CountDuration(cid,type);
    }

    @Override
    @Transactional
    public List<WorkFlowDto> fetchCID(WorkFlowDto workFlowDto, HttpServletRequest request) {
        return daoCommon.fetchCID(workFlowDto, request);
    }

    @Override
    @Transactional
    public List<CommonDto> getGroupTaskDealing(HttpServletRequest request, String userID, String location, String userType) {
        List<CommonDto> dto = daoCommon.getGroupTaskForDealing(request, userID, location, userType);
        return dto;
    }

    @Override
    @Transactional
    public List<CommonDto> getPersonalTaskDealing(HttpServletRequest request, String currentUser, String user, String locationId) {
        List<CommonDto> dto = daoCommon.getPersonalTaskDealing(request, currentUser, user, locationId);
        return dto;
    }

    @Override
    @Transactional
    public List<CommonDto> getGroupTaskRangePRL(HttpServletRequest request, String userID, String locationId, String user) {
        List<CommonDto> dto = daoCommon.getGroupTaskRangePRL(request, userID, locationId, user);
        return dto;
    }

    @Override
    @Transactional
    public List<CommonDto> getPersonalTaskRangePRL(HttpServletRequest request, String userID, String locationId, String user) {
        List<CommonDto> dto = daoCommon.getPersonalTaskRangePRL(request, userID, locationId, user);
        return dto;
    }

    @Override
    @Transactional
    public claimAdditionalTimberDTO getClaimStatus(HttpServletRequest request) {
        return daoCommon.getClaimStatus(request);
    }

    @Override
    @Transactional
    public claimAdditionalTimberDTO getreclaimapplicationDetails(HttpServletRequest request, String application_details) {
        claimAdditionalTimberDTO claimAdditionalTimberDTO = new claimAdditionalTimberDTO();
        try{
            claimAdditionalTimberDTO = daoCommon.getReclaimApplicationDetails(request);

            List<CommonDto> attachment=daoCommon.getAttachments(request);
            claimAdditionalTimberDTO.setDocuments(attachment);
        }catch(Exception e){
            e.printStackTrace();
        }
        return claimAdditionalTimberDTO;
    }

    @Override
    @Transactional
    public String getCCGewog(String cClocationID) {
        String gewog = "";
        return gewog = daoCommon.getCCGewog(cClocationID);
    }


}
