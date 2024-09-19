package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.common.ResponseMessage;
import bt.gov.ditt.dofps.common.SmsSender;
import bt.gov.ditt.dofps.dao.IDaoCitizen;
import bt.gov.ditt.dofps.dao.IDaoCommon;
import bt.gov.ditt.dofps.dao.IsavePrivateLandDao;
import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.entitiy.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Santosh on 7/7/2022.
 */
@Service
public class ServiceCitizen implements IServiceCitizen {
    @Autowired
    IDaoCitizen daoCitizen;

    @Autowired
    IDaoCommon daoCommon;

    @Autowired
    IsavePrivateLandDao isavePrivateLandDao;

    @Override
    @Transactional
    public OnlineTimberDTO saveOnlineTimberApplication(OnlineTimberDTO timberDto, HttpServletRequest request) {
        long Service_Id = 0;
        String applicationNumber = "";
        String status = "";
        int Status_Id = daoCommon.getStatus_Id("SUBMITTED_BY_CITIZEN");

        Calendar cal = Calendar.getInstance();
        Timestamp timestamp = new Timestamp(new Date().getTime());
        cal.setTimeInMillis(timestamp.getTime());

        // add 30 seconds
        cal.add(Calendar.SECOND, 30);
        timestamp = new Timestamp(cal.getTime().getTime());
        System.out.println(timestamp);

        try {
            Service_Id = daoCommon.getServiceId("RTP");
            applicationNumber = daoCommon.AppNumberFormater(daoCommon.getApplicationSequenceNo(Service_Id), Service_Id);
            System.out.print(applicationNumber + "applicationNumber");
            PersonalEntity personalEntity = convertToEntity(timberDto);
            personalEntity.setApplication_Number(applicationNumber);
            personalEntity.setRequest_Service_Id((int) Service_Id);
            personalEntity.setGeog_No(timberDto.getGewogId());
            personalEntity.setDivision_Park_Id(timberDto.getDivision_Park_Id());
            personalEntity.setVillage_Serial_No(timberDto.getVillage_Serial_No());
            personalEntity.setCons_Type(timberDto.getCons_Type());
            personalEntity.setApp_Submission_Date(new Date());

            personalEntity.setCons_Loc_Village_Serial_No(timberDto.getCons_Loc_Village_Serial_No());
            //personalEntity.setNwfp_Management_Group_Sl_No(1);
            personalEntity.setHead_of_Gung("Yes");
            personalEntity.setApplication_Type("RTP");

            personalEntity.setClaimStatus("New");
            personalEntity.setRequest_Service_Id((int) Service_Id);
            personalEntity.setMode_of_Swing_Id(timberDto.getMode_of_Swing_Id());
            personalEntity.setRoofing_Type(timberDto.getRoofing_Type());
            status = daoCitizen.saveOnlineTimberApplication(personalEntity);
            if (status.equalsIgnoreCase("success") && timberDto.getCons_Type().equalsIgnoreCase("o")) {
                if (!CollectionUtils.isEmpty(timberDto.getTimberDetails())) {
                    for (TimberDetailsDto timberDetails : timberDto.getTimberDetails()) {
                        if(timberDetails.getfP_Product_Id() !=0){
                            TimberEntity timberEntity = convertToTimberEntity(timberDetails);
                            timberEntity.setApplication_Number(applicationNumber);
                            timberEntity.setCid_Number(timberDto.getCid_Number());
                            timberEntity.setfP_Product_Id(timberDetails.getfP_Product_Id());
                            // timberEntity.setParts_Id(1);
                            daoCitizen.saveTimber(timberEntity);
                        }
                    }
                } else {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
            } else{
                TimberEntity timberEntity = new TimberEntity();
                timberEntity.setApplication_Number(applicationNumber);
                timberEntity.setCid_Number(timberDto.getCid_Number());
                timberEntity.setfP_Product_Id(timberDto.getFp_Product_Id());
                timberEntity.setAppl_Quantity(timberDto.getAppl_Quantity());
                timberEntity.setAllot_Quantity(0);
                timberEntity.setUnit(timberDto.getUnit());
                daoCitizen.saveTimber(timberEntity);
            }
            if(applicationNumber != null){
                WorkFlowEntity workFlowEntity = new WorkFlowEntity();
                workFlowEntity.setApplication_Number(applicationNumber);
                workFlowEntity.setStatus_Id(Status_Id);
                workFlowEntity.setService_Id((int) Service_Id);
                workFlowEntity.setAction_Date(timestamp);
                workFlowEntity.setActor_Id(timberDto.getCid_Number());
                workFlowEntity.setActor_Name(timberDto.getName());
                workFlowEntity.setRole_Name("Citizen");
                workFlowEntity.setRole_Id(4);

                TaskDetailEntity taskDetailEntity = new TaskDetailEntity();
                taskDetailEntity.setApplication_Number(applicationNumber);
              //  int privId=daoCommon.getpriId((int) Service_Id,"Verifier");
              //  taskDetailEntity.setAssigned_Priv_Id(privId);
                taskDetailEntity.setAssigned_Priv_Id(1822);
                taskDetailEntity.setAction_Date(new Date());
                taskDetailEntity.setSeq_Details_Id(27);
                taskDetailEntity.setTask_State_Id(5);
                taskDetailEntity.setWork(workFlowEntity);
                taskDetailEntity.setAssigned_User_Id("");
                taskDetailEntity.setTask_Remark("");
                TaskDetailEntity task = daoCommon.saveWorkFlow(taskDetailEntity);
                if(task.getTask_Remark().equalsIgnoreCase("Success")) {
                    timberDto.setApplication_Number(applicationNumber);
                } else {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
            } else {
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            }
            String smsContent = "Dear user,<br>"+
                    "Your requisition for timber permit has been submitted successfully with application Number: "+applicationNumber+"  on "+new Date()+ ".";
            SmsSender.smsSender(timberDto.getPhone_Number(), "", null, smsContent, "Application Registered Success");
        } catch (Exception e) {
            e.printStackTrace();
            timberDto.setStatus("Exception in saveOnlineTimberApplication # : " + e);
        }
        timberDto.setStatus("Success");
        return timberDto;
    }

    @Override
    @Transactional
    public List<NewTimberDto> getDropdownList(String id,String type, HttpServletRequest request) {
        return daoCitizen.getDropdownList(id, type,request);
    }

    @Override
    @Transactional
    public List<OnlineTimberDTO> getApplicationList(String appNo) {
        return daoCitizen.getApplicationList(appNo);
    }

    @Override
    @Transactional
    public ResponseMessage getTimberReplacementRequest(String appNo) {
        ResponseMessage responseMessage = new ResponseMessage();

        WorkFlowDto dto = new WorkFlowDto();
        Integer appStatus = daoCitizen.getApplicationStatus(appNo);
        Integer replacementCount = daoCitizen.getReplacementCount(appNo);
        if(replacementCount<1 || appStatus.equals(7)){
            if(appStatus.equals(3) || appStatus.equals(7)){
                dto = daoCitizen.getTimberReplacementRequest(appNo);

                List<TimberDetailsDto> product_name=daoCommon.getProduct_catagory(appNo);
                dto.setTimberdetails(product_name);
                responseMessage.setStatus(1);
                responseMessage.setDto(dto);
            }else{
                responseMessage.setStatus(0);
                responseMessage.setText("Replacement of RTP can be available after marking scheduling and RO's approval !");
            }
        }else{
            responseMessage.setStatus(0);
            responseMessage.setText("Replacement of RTP is allowed only once !!");
        }
        return responseMessage;
    }

    @Override
    @Transactional
    public ResponseMessage submitTimberRR(String appNo, WorkFlowDto dto, HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();

        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        /*String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();*/
       // String actorId = userBean.getUserID();
        String actorId = "";
       /* String actorName=userBean.getFullName();*/
        String assignPrivId="1841";
        String statusId="19";
        String sucMsg ="";
        String taskId="5";

    try {
        if (!CollectionUtils.isEmpty(dto.getTimberdetails())) {
            for (TimberDetailsDto timberDetails : dto.getTimberdetails()) {
                sucMsg = daoCitizen.submitTimberRR(appNo, timberDetails);
            }
        }

        if (sucMsg.equalsIgnoreCase("Success")) {
            sucMsg = daoCitizen.updateReplacementCount(appNo);
            if (sucMsg.equalsIgnoreCase("Success")) {
                sucMsg = daoCommon.insertToTaskAudit(appNo);
                if (sucMsg.equalsIgnoreCase("Success")) {
                    sucMsg = daoCommon.insertToWorkFlowAudit(appNo);
                    if (sucMsg.equalsIgnoreCase("Success")) {
                        sucMsg = daoCommon.insertTaskdtls(appNo, dto.getRemarks(), request, actorId, assignPrivId, taskId);
                        if (sucMsg.equalsIgnoreCase("Success")) {
                            sucMsg = daoCommon.insertToWorkFlow(appNo, dto.getRemarks(), request, statusId);
                            if (sucMsg.equalsIgnoreCase("Success")) {
                                responseMessage.setStatus(1);
                                responseMessage.setText("Your requisition for replacement of timber is successfully submitted");
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
    public ResponseMessage checkForMarking(String appNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        WorkFlowDto dto = new WorkFlowDto();

        Integer appStatus = daoCitizen.getApplicationStatus(appNo);
        dto = daoCitizen.getTimberReplacementRequest(appNo);
        //uncomment or check it with issue while submitting marking during replacement
       // if((appStatus.equals(2) || appStatus.equals(22))  && dto.getPayment_status().equalsIgnoreCase("Paid")){
            responseMessage.setStatus(1);
            responseMessage.setDto(dto);
      /*  }else{
            responseMessage.setStatus(0);
            responseMessage.setText("You can schedule marking date only after CFO's approval and royalty payment");
        }*/
        return responseMessage;
    }

    @Override
    @Transactional
    public ResponseMessage scheduleMarkingDate(String appNo, String markingDate, HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();

        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");

        String sucMsg ="";
        sucMsg = daoCitizen.updateMarkingDate(appNo,markingDate);
        if(sucMsg.equalsIgnoreCase("Success")) {
            responseMessage.setStatus(1);
            responseMessage.setText("Your marking date is successfully scheduled on:" + markingDate);
        }else {
            responseMessage.setStatus(0);
            responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
        }
    return responseMessage;
}


    @Override
    @Transactional
    public ResponseMessage checkForSawingPermit(String appNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        WorkFlowDto dto = new WorkFlowDto();

        Integer appStatus = daoCitizen.getApplicationStatus(appNo);
        dto = daoCitizen.getTimberReplacementRequest(appNo);

        List<MarkingInformationEntity> markingInformation=daoCommon.getMarkingInformation(appNo);
        dto.setMarkingInformationEntityList(markingInformation);
        if(appNo.startsWith("182-")){
            responseMessage.setStatus(0);
            responseMessage.setText("For Firewood,Fencing Post & Flg Poles and Other forest produce Sawing permit is not required !");
        }else{
            if(appStatus.equals(3)){
                responseMessage.setStatus(1);
                responseMessage.setDto(dto);
            }else{
                responseMessage.setStatus(0);
                responseMessage.setText("You can schedule sawing permit date only after RO's approval!");
            }
        }
        return responseMessage;
    }

    @Override
    @Transactional
    public ResponseMessage check4Completion(String appNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        WorkFlowDto dto = new WorkFlowDto();

        Integer appStatus = daoCitizen.getApplicationStatus(appNo);
        dto = daoCitizen.getTimberReplacementRequest(appNo);
        if(appStatus.equals(3)){
            responseMessage.setStatus(1);
            responseMessage.setDto(dto);
        }else{
            responseMessage.setStatus(0);
            responseMessage.setText("Update Construction Completion only after Rural Timber Permit process.");
        }
        return responseMessage;
    }

    @Override
    @Transactional
    public ResponseMessage updateCompletionDate(String appNo, String completionDate, HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        int completionStatus= Integer.parseInt(request.getParameter("construction_status"));
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");

        String sucMsg ="";
        sucMsg = daoCitizen.updateCompletionDate(appNo,completionDate,completionStatus);
        if(sucMsg.equalsIgnoreCase("Success")) {
            responseMessage.setStatus(1);
            responseMessage.setText("Updated successfully");
        }else {
            responseMessage.setStatus(0);
            responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
        }
        return responseMessage;
    }

    @Override
    @Transactional
    public WorkFlowDto generateAllotmentOrderDtls(String applicationNumber) {
        WorkFlowDto dto = new WorkFlowDto();
        List<TimberDetailsDto> timberDetailsDtoList = new ArrayList<TimberDetailsDto>();
        if(applicationNumber.startsWith("184-")){
            timberDetailsDtoList=daoCommon.getPvtProduct_catagory(applicationNumber);
        }else{
            timberDetailsDtoList=daoCommon.getProduct_catagory(applicationNumber);
        }
        dto=daoCitizen.getTimberReplacementRequest(applicationNumber);
        dto.setTotal_Royalty(timberDetailsDtoList.get(0).getTotal_Royalty());
        return dto;
    }

    @Override
    @Transactional
    public ResponseMessage fetchSowingPermitDtls(String appNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        WorkFlowDto dto = new WorkFlowDto();

        Integer appStatus = daoCitizen.getApplicationStatus(appNo);
        dto = daoCitizen.getTimberReplacementRequest(appNo);
        dto.setApplication_Number(appNo);
        List<TimberDetailsDto> product_name=daoCommon.getProduct_catagory(appNo);
        dto.setTimberdetails(product_name);

        List<MarkingInformationEntity> markingInformation=daoCommon.getMarkingInformation(appNo);
        dto.setMarkingInformationEntityList(markingInformation);

        List<CosdtmoItmo> cosdtmoItmoList = daoCommon.getCosdtmoItmoDtls(appNo);
        dto.setCosdtmoItmoList(cosdtmoItmoList);

       //  if(dto.getPayment_status() !=null && dto.getPayment_status().equalsIgnoreCase("Paid")){
       if(appStatus.equals(18)){
            responseMessage.setStatus(1);
            responseMessage.setDto(dto);
        }else{
            responseMessage.setStatus(0);
            responseMessage.setText("You can print sawing permit only after permit approved by Range Officer!");
        }
        return responseMessage;
    }


    @Override
    @Transactional
    public ResponseMessage fetchForestProduceDtls(String appNo, HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        WorkFlowDto dto = new WorkFlowDto();

        Integer appStatus = daoCitizen.getApplicationStatus(appNo);
        dto = isavePrivateLandDao.getapplDetails(appNo, "");
        List<TimberDetailsDto> product_name = daoCommon.getPvtProduct_catagory(appNo);
        dto.setTimberdetails(product_name);

        List<LandDetailsDto> landDetailsDtos = daoCommon.getLandDetails(appNo);
        dto.setLandDetailsDtos(landDetailsDtos);

        responseMessage.setStatus(1);
        responseMessage.setDto(dto);

        return responseMessage;
    }

    @Override
    @Transactional
    public ResponseMessage fetchPermitDtls(String appNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        WorkFlowDto dto = new WorkFlowDto();

        Integer appStatus = daoCitizen.getApplicationStatus(appNo);
        dto = daoCitizen.getTimberReplacementRequest(appNo);
        dto.setApplication_Number(appNo);
        List<TimberDetailsDto> product_name=daoCommon.getProduct_catagory(appNo);
        dto.setTimberdetails(product_name);

        if(dto.getPayment_status() !=null && dto.getPayment_status().equalsIgnoreCase("Paid")){
            if(product_name.get(0).getProduct_Catagory().equalsIgnoreCase("Log")){
                responseMessage.setStatus(0);
                responseMessage.setText("You can't print Rural Timber Permit, since your application process is completed!");
            }else{
                responseMessage.setStatus(1);
                responseMessage.setDto(dto);
            }
        }else {
            responseMessage.setStatus(0);
            responseMessage.setText("You can print Rural Timber Permit only after royalty payment is done!");
        }
        return responseMessage;
    }

    @Override
    @Transactional
    public ResponseMessage scheduleSawingPermitDate(String appNo, String sawingPermitDate, HttpServletRequest request, String nameOfOperator, String licenseNo, String locationOfSawmill, String rateOfSawing, int mode_of_Swing_Id) {
        ResponseMessage responseMessage = new ResponseMessage();

        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");

        //String roleId = userBean.getCurrentRole().getRoleId();
        //String role_name = userBean.getCurrentRole().getRoleName();
       // String actorId = userBean.getUserID();
        String actorId = "10101000022";
        //String actorName=userBean.getFullName();
        String assignPrivId="1841";
        String statusId="17";
        String taskId="5";

        String sucMsg ="";
        sucMsg = daoCitizen.scheduleSawingPermitDate(appNo, sawingPermitDate,nameOfOperator,licenseNo,locationOfSawmill,rateOfSawing,mode_of_Swing_Id);
        if (sucMsg.equalsIgnoreCase("Success")) {
            sucMsg = daoCommon.insertToTaskAudit(appNo);
            if (sucMsg.equalsIgnoreCase("Success")) {
                sucMsg = daoCommon.insertToWorkFlowAudit(appNo);
                if (sucMsg.equalsIgnoreCase("Success")) {
                    sucMsg = daoCommon.insertTaskdtls(appNo, "", request, actorId, assignPrivId, taskId);
                    if (sucMsg.equalsIgnoreCase("Success")) {
                        sucMsg = daoCommon.insertToWorkFlow(appNo, "", request, statusId);
                        if (sucMsg.equalsIgnoreCase("Success")) {
                            responseMessage.setStatus(1);
                            responseMessage.setText("Your Sawing Permit date is successfully scheduled on:" + sawingPermitDate);
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
    public ResponseMessage fetchAllotmentDtls(String appNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        WorkFlowDto dto = new WorkFlowDto();

        Integer appStatus = daoCitizen.getApplicationStatus(appNo);
        dto = daoCitizen.getTimberReplacementRequest(appNo);
        dto.setApplication_Number(appNo);
        List<TimberDetailsDto> product_name=daoCommon.getProduct_catagory(appNo);
        dto.setTimberdetails(product_name);

      //  if(appStatus.equals(2)){
            if((dto.getPayment_status() !=null && dto.getPayment_status().equalsIgnoreCase("Paid")) && product_name.get(0).getProduct_Catagory().equalsIgnoreCase("Log")){
                responseMessage.setStatus(1);
                responseMessage.setDto(dto);
            }else{
                responseMessage.setStatus(1);
                responseMessage.setDto(dto);
            }
        /*}else {
            responseMessage.setStatus(0);
            responseMessage.setText("You can print allotment order once application is approved by CFO!");
        }*/
        return responseMessage;
    }

    @Override
    @Transactional
    public ResponseMessage isDateAvailable(String thisDate, Integer allot_Range_Officer) {
        ResponseMessage responseMessage = new ResponseMessage();

        BigInteger existDateCount = daoCitizen.getExistDateCount(thisDate,allot_Range_Officer);

        if(existDateCount.intValue()>=2){
            responseMessage.setStatus(0);
            responseMessage.setText("This date is already booked by other applicants, please choose other available date");
        }else{
            responseMessage.setStatus(1);
        }
        return responseMessage;
    }

    @Override
    @Transactional
    public ResponseMessage getTimberExtensionRequest(String appNo) {
        ResponseMessage responseMessage = new ResponseMessage();

        WorkFlowDto dto = new WorkFlowDto();
        Integer appStatus = daoCitizen.getApplicationStatus(appNo);
      //  if(appStatus.equals(2) || appStatus.equals(23)){
            dto = daoCitizen.getTimberReplacementRequest(appNo);
        if(dto.getCons_Type().equalsIgnoreCase("New Construction")){
            List<TimberDetailsDto> product_name=daoCommon.getProduct_catagory(appNo);
            dto.setTimberdetails(product_name);
            responseMessage.setStatus(1);
            responseMessage.setDto(dto);
        }else{
            responseMessage.setStatus(0);
            responseMessage.setText("Rural Timber Permit Extension is only for new Construction !");
        }

        /*}else{
            responseMessage.setStatus(0);
            responseMessage.setText("Your application is not yet approved by CFO");
        }*/
        return responseMessage;
    }

    @Override
    @Transactional
    public ResponseMessage submitTimberPE(String appNo, WorkFlowDto dto, HttpServletRequest request, MultipartFile[] files, String remarks) {
        ResponseMessage responseMessage = new ResponseMessage();

        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
       // String actorId = "";
        String actorName=userBean.getFullName();
        String assignPrivId="1813";
        String statusId="20";
        String sucMsg ="";
        String taskId="5";
        String fileType="PE";
        String uuid = null,filePath=null,status="",insertToTaskAudit="";
        DocumentEntity document= new DocumentEntity();

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
            sucMsg = daoCommon.insertToTaskAudit(appNo);
            if (sucMsg.equalsIgnoreCase("Success")) {
                sucMsg = daoCommon.insertToWorkFlowAudit(appNo);
                if (sucMsg.equalsIgnoreCase("Success")) {
                    sucMsg = daoCommon.insertTaskdtls(appNo, dto.getRemarks(), request, actorId, assignPrivId, taskId);
                    if (sucMsg.equalsIgnoreCase("Success")) {
                        sucMsg = daoCommon.insertToWorkFlow(appNo, dto.getRemarks(), request, statusId);
                        if (sucMsg.equalsIgnoreCase("Success")) {
                            responseMessage.setStatus(1);
                            responseMessage.setText("Your requisition for EXTENSION of timber is successfully submitted");
                        } else {
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
    public OnlineTimberDTO submitManualRecord(OnlineTimberDTO timberDto, HttpServletRequest request, MultipartFile[] files) {
        long Service_Id = 0;
        String applicationNumber = "";
        String status = "";
        int Status_Id = daoCommon.getStatus_Id("SUBMITTED_BY_CITIZEN");

        Calendar cal = Calendar.getInstance();
        Timestamp timestamp = new Timestamp(new Date().getTime());
        cal.setTimeInMillis(timestamp.getTime());

        // add 30 seconds
        cal.add(Calendar.SECOND, 30);
        timestamp = new Timestamp(cal.getTime().getTime());
        System.out.println(timestamp);

        String uuid = null,filePath=null,insertToTaskAudit="";
        DocumentEntity document= new DocumentEntity();

        try {
            Service_Id = daoCommon.getServiceId("RTP");
            applicationNumber = daoCommon.AppNumberFormater(daoCommon.getApplicationSequenceNo(Service_Id), Service_Id);
            System.out.print(applicationNumber + "applicationNumber");
            PersonalEntity personalEntity = convertToEntity(timberDto);
            personalEntity.setApplication_Number(applicationNumber);
            personalEntity.setRequest_Service_Id((int) Service_Id);
            personalEntity.setGeog_No(timberDto.getGewogId());
            personalEntity.setThram_No(Integer.parseInt(timberDto.getThram_No()));
            personalEntity.setDivision_Park_Id(timberDto.getDivision_Park_Id());
            personalEntity.setVillage_Serial_No(timberDto.getVillage_Serial_No());
            personalEntity.setCons_Type(timberDto.getCons_Type());
            personalEntity.setApp_Submission_Date(new Date());

            personalEntity.setCons_Loc_Village_Serial_No(timberDto.getCons_Loc_Village_Serial_No());
            //personalEntity.setNwfp_Management_Group_Sl_No(1);
            personalEntity.setHead_of_Gung("Yes");
            personalEntity.setApplication_Type("RTP");

            personalEntity.setClaimStatus("New");
            personalEntity.setRequest_Service_Id((int) Service_Id);
            personalEntity.setMode_of_Swing_Id(timberDto.getMode_of_Swing_Id());
            personalEntity.setRoofing_Type(timberDto.getRoofing_Type());
            status = daoCitizen.saveOnlineTimberApplication(personalEntity);
            if (status.equalsIgnoreCase("success") && timberDto.getCons_Type().equalsIgnoreCase("o")) {
                if (!CollectionUtils.isEmpty(timberDto.getTimberDetails())) {
                    for (TimberDetailsDto timberDetails : timberDto.getTimberDetails()) {
                        TimberEntity timberEntity = convertToTimberEntity(timberDetails);
                        timberEntity.setApplication_Number(applicationNumber);
                        timberEntity.setCid_Number(timberDto.getCid_Number());
                        timberEntity.setfP_Product_Id(timberDetails.getfP_Product_Id());
                        // timberEntity.setParts_Id(1);
                        daoCitizen.saveTimber(timberEntity);
                    }
                } else {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
            } else{
                TimberEntity timberEntity = new TimberEntity();
                timberEntity.setApplication_Number(applicationNumber);
                timberEntity.setCid_Number(timberDto.getCid_Number());
                timberEntity.setfP_Product_Id(timberDto.getFp_Product_Id());
                timberEntity.setAppl_Quantity(timberDto.getAppl_Quantity());
                timberEntity.setAllot_Quantity(timberDto.getAllot_Quantity());
                timberEntity.setUnit(timberDto.getUnit());
                daoCitizen.saveTimber(timberEntity);
            }
            if(applicationNumber != null){
                WorkFlowEntity workFlowEntity = new WorkFlowEntity();
                workFlowEntity.setApplication_Number(applicationNumber);
                workFlowEntity.setStatus_Id(3);
                workFlowEntity.setService_Id((int) Service_Id);
                workFlowEntity.setAction_Date(timestamp);
                workFlowEntity.setActor_Id(timberDto.getCid_Number());
                workFlowEntity.setActor_Name(timberDto.getName());
                workFlowEntity.setRole_Name(timberDto.getRole_Name());
                workFlowEntity.setRole_Id(4);

                TaskDetailEntity taskDetailEntity = new TaskDetailEntity();
                taskDetailEntity.setApplication_Number(applicationNumber);
                //  int privId=daoCommon.getpriId((int) Service_Id,"Verifier");
                //  taskDetailEntity.setAssigned_Priv_Id(privId);
                taskDetailEntity.setAssigned_Priv_Id(1822);
                taskDetailEntity.setAction_Date(new Date());
                taskDetailEntity.setSeq_Details_Id(27);
                taskDetailEntity.setTask_State_Id(7);
                taskDetailEntity.setWork(workFlowEntity);
                taskDetailEntity.setAssigned_User_Id("");
                taskDetailEntity.setTask_Remark("");
                TaskDetailEntity task = daoCommon.saveWorkFlow(taskDetailEntity);
                if(task.getTask_Remark().equalsIgnoreCase("Success")) {
                    timberDto.setApplication_Number(applicationNumber);
                }else{
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
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
                    doc.setApplication_Number(applicationNumber);
                    daoCommon.saveEntity(doc);
                    if (!status.equalsIgnoreCase("Success")) {
                        document.setApplication_Number(null);
                    }else {
                        document.setApplication_Number(applicationNumber);
                    }
                }
                //end of region
            }else{
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            }
            String smsContent = "Dear user,<br>"+
                    "Your requisition for timber permit has been submitted successfully with application Number: "+applicationNumber+"  on "+new Date()+ ".";
            System.out.println("----- phone number ----------"+timberDto.getPhone_Number());
            SmsSender.smsSender(timberDto.getPhone_Number(), "", null, smsContent, "Application Registered Success");
        }catch (Exception e) {
            e.printStackTrace();
            timberDto.setStatus("Exception in saveOnlineTimberApplication # : " + e);
        }
        timberDto.setStatus("Success");
        return timberDto;
    }

    @Override
    @Transactional
    public ResponseMessage submitPrivateLand(OnlineTimberDTO timberDto, NewTimberDto newTimberDto, MultipartFile[] files, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ResponseMessage responseMessage = new ResponseMessage();
        UserRolePrivilegeHierarchyObj userRolePriv=(UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        //String roleId = userBean.getCurrentRole().getRoleId();

        String applicationNumber = "";
        String uuid = null,filePath=null,insertToTaskAudit="";
        DocumentEntity document= new DocumentEntity();
        String fileType="PRL";

        Calendar cal = Calendar.getInstance();
        Timestamp timestamp = new Timestamp(new Date().getTime());
        cal.setTimeInMillis(timestamp.getTime());

        // add 30 seconds
        cal.add(Calendar.SECOND, 30);
        timestamp = new Timestamp(cal.getTime().getTime());
        System.out.println(timestamp);

        long Service_Id=0;
        Service_Id = daoCommon.getServiceId("PRL");
        int Status_Id = daoCommon.getStatus_Id("SUBMITTED_BY_CITIZEN");
        applicationNumber = daoCommon.AppNumberFormater(daoCommon.getApplicationSequenceNo(Service_Id), Service_Id);
        if(!applicationNumber.equalsIgnoreCase("") && applicationNumber!=null) {
            PersonalEntity personalEntity = convertToEntity(timberDto);
            personalEntity.setApplication_Number(applicationNumber);
            personalEntity.setApplication_Type("PRL");
            personalEntity.setMobile_Number(newTimberDto.getMobile_Number());
            personalEntity.setPhone_Number(newTimberDto.getPhone_Number());
            personalEntity.setRequest_Service_Id((int) Service_Id);
            personalEntity.setCons_Loc_Village_Serial_No(timberDto.getVillage_Serial_No());
            personalEntity.setApp_Submission_Date(new Date());
            personalEntity.setNwfp_Management_Group_Sl_No(1);
            personalEntity.setMode_of_Swing_Id(1);
            personalEntity.setCons_Type(fileType);
            personalEntity.setPayment_Status("Not Applicable");
            personalEntity.setGeog_No(timberDto.getGewogId());
            personalEntity.setCons_Approval_No(newTimberDto.getCons_Approval_No());
            personalEntity.setDivision_Park_Id(newTimberDto.getDivision_Park_Id());
            personalEntity.setPrivate_LandForm_Remarks(newTimberDto.getPrivate_LandForm_Remarks());
           // personalEntity.setAllot_Range_Officer(newTimberDto.getAllot_Range_Officer());
            personalEntity.setAlternativeNumberRelation(newTimberDto.getAlternativeNumberRelation());
            personalEntity.setPlot_No(newTimberDto.getHeader_Name());
            personalEntity.setThram_No(newTimberDto.getThram_NO());
            String entity = daoCitizen.saveOnlineTimberApplication(personalEntity);

            if(entity.equalsIgnoreCase("success")) {
                try {
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
                        doc.setApplication_Number(applicationNumber);
                        doc.setType(fileType);
                        daoCommon.saveEntity(doc);
                    }
                    //end of region

                    WorkFlowEntity workFlowEntity = new WorkFlowEntity();
                    workFlowEntity.setApplication_Number(applicationNumber);
                    workFlowEntity.setStatus_Id(Status_Id);
                    workFlowEntity.setService_Id((int) Service_Id);
                    workFlowEntity.setAction_Date(timestamp);
                    workFlowEntity.setActor_Id(timberDto.getCid_Number());
                    //workFlowEntity.setActor_Name(userRolePriv.getFullName());
                    workFlowEntity.setActor_Name(timberDto.getName());
                    workFlowEntity.setRole_Name("Citizen");
                    workFlowEntity.setRole_Id(4);

                    TaskDetailEntity taskDetailEntity = new TaskDetailEntity();
                    taskDetailEntity.setApplication_Number(applicationNumber);
                   // int privId=daoCommon.getpriId((int) Service_Id,"Submitter");
                    //taskDetailEntity.setAssigned_Priv_Id(privId);
                    taskDetailEntity.setAssigned_Priv_Id(1822);
                    taskDetailEntity.setAction_Date(new Date());
                    taskDetailEntity.setSeq_Details_Id(27);
                    taskDetailEntity.setTask_State_Id(5);
                    taskDetailEntity.setWork(workFlowEntity);
                    taskDetailEntity.setAssigned_User_Id("");
                    taskDetailEntity.setTask_Remark("");
                    TaskDetailEntity status = daoCommon.saveWorkFlow(taskDetailEntity);
                    if(status.getTask_Remark().equalsIgnoreCase("Success")) {
                        newTimberDto.setApplication_Number(applicationNumber);
                            responseMessage.setStatus(1);
                            responseMessage.setText("Your requisition for Removal of Forest Products from Private Land is successfully submitted with application number:" + applicationNumber + ". \n Your application will be verified and approved by respective officers!");

                        String smsContent = "Dear user,<br>"+
                                "Your requisition for timber permit has been submitted successfully with application Number: "+applicationNumber+"  on "+new Date()+ ".";
                        System.out.println("----- phone number ----------"+timberDto.getPhone_Number());
                        SmsSender.smsSender(timberDto.getPhone_Number(), "", null, smsContent, "Application Registered Success");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    responseMessage.setStatus(0);
                    responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                }
            }
        }
        return responseMessage;
    }

    @Override
    @Transactional
    public OnlineTimberDTO saveWoodandPoles(OnlineTimberDTO timberDto, HttpServletRequest request) {
        long Service_Id = 0;
        String applicationNumber = "";
        String status = "";
        String fileType="WP";
        int Status_Id = daoCommon.getStatus_Id("SUBMITTED_BY_CITIZEN");

        Calendar cal = Calendar.getInstance();
        Timestamp timestamp = new Timestamp(new Date().getTime());
        cal.setTimeInMillis(timestamp.getTime());

        // add 30 seconds
        cal.add(Calendar.SECOND, 30);
        timestamp = new Timestamp(cal.getTime().getTime());
        System.out.println(timestamp);

        try {
            Service_Id = daoCommon.getServiceId("WP");
            applicationNumber = daoCommon.AppNumberFormater(daoCommon.getApplicationSequenceNo(Service_Id), Service_Id);
            System.out.print(applicationNumber + "applicationNumber");
            timberDto.setApplication_Number(applicationNumber);
            PersonalEntity personalEntity = convertToEntity(timberDto);
            personalEntity.setApplication_Number(applicationNumber);
            personalEntity.setCid_Number(timberDto.getCid_Number());
            personalEntity.setName(timberDto.getName());
            personalEntity.setVillage_Serial_No(timberDto.getVillage_Serial_No());
            // personalEntity.setGenderType(timberDto.getGenderType());
            personalEntity.setHouse_No(timberDto.getHouse_No());
            personalEntity.setHouse_Hold_No(timberDto.getHouse_Hold_No());
            personalEntity.setMobile_Number(timberDto.getPhone_Number());
            personalEntity.setAlternativeNumberRelation(timberDto.getAlternativeNumberRelation());
            personalEntity.setPhone_Number(timberDto.getPhone_Number());
            personalEntity.setGeog_No(timberDto.getGewogId());
            personalEntity.setDivision_Park_Id(timberDto.getDivision_Park_Id());
            personalEntity.setCons_Loc_Village_Serial_No(1);
            personalEntity.setNwfp_Management_Group_Sl_No(1);
            personalEntity.setDzongkhag_Name(timberDto.getDzongkhag_Name());
            personalEntity.setGewog_Name(timberDto.getGewog_Name());
            personalEntity.setVillage_Name(timberDto.getVillage_Name());
            personalEntity.setApplication_Type(fileType);
            personalEntity.setHead_of_Gung("Yes");
            personalEntity.setRequest_Service_Id((int) Service_Id);
            personalEntity.setMode_of_Swing_Id(1);
            personalEntity.setCons_Type(fileType);
            personalEntity.setCons_Approval_No(timberDto.getCons_Approval_No());
            personalEntity.setApp_Submission_Date(new Date());
            if (!applicationNumber.equalsIgnoreCase("")) {
                personalEntity.setApplication_Number(applicationNumber);
            } else {
                personalEntity.setApplication_Number(timberDto.getApplication_Number());
            }
            status = daoCitizen.saveOnlineTimberApplication(personalEntity);

            if (status.equalsIgnoreCase("success")) {
                if (!org.springframework.util.CollectionUtils.isEmpty(timberDto.getTimberDetails())) {
                    for (TimberDetailsDto timberdetails : timberDto.getTimberDetails()) {
                        if(timberdetails.getfP_Product_Id() !=0) {
                            TimberEntity timberEntity = convertToTimberEntity(timberdetails);
                            timberEntity.setApplication_Number(applicationNumber);
                            timberEntity.setCid_Number(timberDto.getCid_Number());
                            timberEntity.setfP_Product_Id(timberdetails.getfP_Product_Id());
                            timberEntity.setUnit(timberdetails.getUnit());
                            if (timberdetails.getAppl_Quantity() == 0) {
                                timberEntity.setAppl_Quantity(timberdetails.getAppl_Quantity_Log());
                            } else {
                                timberEntity.setAppl_Quantity(timberdetails.getAppl_Quantity());
                            }
                            timberEntity.setAllot_Quantity(0);
                            status = daoCitizen.saveTimber(timberEntity);
                        }
                    }
                }

                WorkFlowEntity workFlowEntity = new WorkFlowEntity();
                workFlowEntity.setApplication_Number(applicationNumber);
                workFlowEntity.setStatus_Id(Status_Id);
                workFlowEntity.setService_Id((int) Service_Id);
                workFlowEntity.setAction_Date(timestamp);
                workFlowEntity.setActor_Id(timberDto.getCid_Number());
                //workFlowEntity.setActor_Name(userRolePriv.getFullName());
                workFlowEntity.setActor_Name(timberDto.getName());
                workFlowEntity.setRole_Name("Citizen");
                workFlowEntity.setRole_Id(4);

                TaskDetailEntity taskDetailEntity = new TaskDetailEntity();
                taskDetailEntity.setApplication_Number(applicationNumber);
                taskDetailEntity.setAssigned_Priv_Id(1822);
                taskDetailEntity.setAction_Date(new Date());
                taskDetailEntity.setSeq_Details_Id(27);
                taskDetailEntity.setTask_State_Id(5);
                taskDetailEntity.setWork(workFlowEntity);
                taskDetailEntity.setAssigned_User_Id("");
                taskDetailEntity.setTask_Remark("");
                TaskDetailEntity save = daoCommon.saveWorkFlow(taskDetailEntity);

                String smsContent = "Dear user,<br>" +
                        "Your requisition for timber permit has been submitted successfully with application Number: " + applicationNumber + "  on " + new Date() + ".";
                System.out.println("----- phone number ----------" + timberDto.getPhone_Number());
                SmsSender.smsSender(timberDto.getPhone_Number(), "", null, smsContent, "Application Registered Success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        timberDto.setStatus("Success");
        return timberDto;
    }

    @Transactional
    private PersonalEntity convertToEntity(OnlineTimberDTO dto) {
        PersonalEntity personalEntity = new PersonalEntity();
        BeanUtils.copyProperties(dto, personalEntity);
        return personalEntity;
    }

    @Transactional
    private TimberEntity convertToTimberEntity(TimberDetailsDto dto) {
        TimberEntity timberEntity = new TimberEntity();
        BeanUtils.copyProperties(dto, timberEntity);
        return timberEntity;
    }
}
