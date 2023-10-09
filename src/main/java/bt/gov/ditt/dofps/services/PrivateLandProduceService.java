package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.common.ResponseMessage;
import bt.gov.ditt.dofps.dao.IDaoCommon;
import bt.gov.ditt.dofps.dao.IDaoRuralTimber;
import bt.gov.ditt.dofps.dao.IsavePrivateLandDao;
import bt.gov.ditt.dofps.dto.LandDetailsDto;
import bt.gov.ditt.dofps.dto.PrivateLandDto;
import bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO;
import bt.gov.ditt.dofps.dto.WorkFlowDto;
import bt.gov.ditt.dofps.entitiy.DocumentEntity;
import bt.gov.ditt.dofps.entitiy.LandDetails;
import bt.gov.ditt.dofps.entitiy.TimberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * Created by Pema Drakpa.
 * Created on 2023-02-24.
 * Business logic for private land produce services
 */
@Service
public class PrivateLandProduceService{
    @Autowired
    IsavePrivateLandDao isavePrivateLandDao;

    @Autowired
    IDaoCommon daoCommon;
    @Autowired
    IDaoRuralTimber daoRuralTimber;


    @Transactional
    public ResponseMessage privateLandProduceGupForwardToRO(String appNo, String remarks, HttpServletRequest request, int range_id) {
        ResponseMessage responseMessage = new ResponseMessage();

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
        try{
            insertToTaskAudit=daoCommon.forwardToOtherRange(appNo,remarks,range_id);
            if(insertToTaskAudit.equalsIgnoreCase("Success")){
                insertToTaskAudit = daoCommon.insertToTaskAudit(appNo);
                if(insertToTaskAudit.equalsIgnoreCase("Success")){
                    insertToTaskAudit= daoCommon.insertToWorkFlowAudit(appNo);
                    if(insertToTaskAudit.equalsIgnoreCase("Success")){
                        insertToTaskAudit=daoCommon.insertTaskdtls(appNo,remarks,request,actorId,assignPrivId, taskId);
                        if(insertToTaskAudit.equalsIgnoreCase("Success")){
                            insertToTaskAudit=daoCommon.insertToWorkFlow(appNo,remarks,request, statusId);
                            if(insertToTaskAudit.equalsIgnoreCase("Success")){
                                responseMessage.setStatus(1);
                                responseMessage.setText("Application Number" + appNo + "forwarded successfully to Range Office");
                            }else{
                                responseMessage.setStatus(0);
                                responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            responseMessage.setStatus(0);
            responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
        }
        return responseMessage;
    }

    @Transactional
    public ResponseMessage privateLandProduceROForwardToCFO(WorkFlowDto workFlowDto, HttpServletRequest request, MultipartFile[] files) {
        ResponseMessage responseMessage = new ResponseMessage();

        String uuid = null, filePath = null, status = "", returnMsg = "";
        DocumentEntity document = new DocumentEntity();

        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String actorName = userBean.getFullName();
        String assignPrivId = "1813";
        String statusId = "16";
        String taskId = "5";
        String fileType = "PRL";
        String appNo = "";

        appNo = request.getParameter("appNo");
        try {
            returnMsg = isavePrivateLandDao.updatePrivateProduceDetailsByRO(appNo, request, workFlowDto);
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

            //region Save Land details
            for (LandDetailsDto landDetailsDto : workFlowDto.getLandDetailsDtos()) {
                LandDetails landDetails = new LandDetails();
                    landDetails.setApplication_Number(appNo);
                    landDetails.setPlot_No(landDetailsDto.getPlot_No());
                    landDetails.setPeg_No(landDetailsDto.getPeg_No());
                    landDetails.setGps_Coordinates(landDetailsDto.getGps_Coordinates());
                    landDetails.setLand_Category(landDetailsDto.getLand_Category());
                    landDetails.setAreas(landDetailsDto.getAreas());
                    daoCommon.saveUpdate(landDetails);
            }
            //end of region

            //region Save timber details Teams
            for (PrivateLandDto privateLandDto : workFlowDto.getPrivateLandDtos()) {
                TimberEntity timberEntity = new TimberEntity();
                if(privateLandDto.getfP_Product_Id()!=null || privateLandDto.getfP_Product_Id()!= 0){
                    timberEntity.setfP_Product_Id(privateLandDto.getfP_Product_Id());
                    timberEntity.setNo_trees(privateLandDto.getNo_trees());
                    timberEntity.setNo_poles(privateLandDto.getNo_poles());
                    timberEntity.setNo_bamboos(privateLandDto.getNo_bamboos());
                    timberEntity.setVolume(privateLandDto.getVolume());
                    timberEntity.setTotal_Royalty(10);
                    timberEntity.setAllot_Quantity(0);
                    timberEntity.setAppl_Quantity(0);
                    timberEntity.setApplication_Number(appNo);
                    daoCommon.saveUpdate(timberEntity);
                }
            }
            //end of region

            if (returnMsg.equalsIgnoreCase("Success")) {
                returnMsg = daoCommon.insertToTaskAudit(appNo);
                if (returnMsg.equalsIgnoreCase("Success")) {
                    returnMsg = daoCommon.insertToWorkFlowAudit(appNo);
                    if (returnMsg.equalsIgnoreCase("Success")) {
                        returnMsg = daoCommon.insertTaskdtls(appNo, workFlowDto.getRemarks(), request, actorId, assignPrivId, taskId);
                        if (returnMsg.equalsIgnoreCase("Success")) {
                            returnMsg = daoCommon.insertToWorkFlow(appNo, workFlowDto.getRemarks(), request, statusId);
                            if (returnMsg.equalsIgnoreCase("Success")) {
                                responseMessage.setStatus(1);
                                responseMessage.setText("Application Number:" + appNo + "is verified and details updated successfully!");
                            } else {
                                responseMessage.setStatus(0);
                                responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
        }
        return responseMessage;
    }

    @Transactional
    public ResponseMessage approvedPrivateLand(HttpServletRequest request, String appNo, String remarks) {
        ResponseMessage responseMessage = new ResponseMessage();

        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String actorName=userBean.getFullName();
        String assignPrivId="1841";
        String statusId="3";
       String taskId="6";

        String insertToTaskAudit = "";
        insertToTaskAudit = daoCommon.insertToTaskAudit(appNo);
        if(insertToTaskAudit.equalsIgnoreCase("Success")){
            insertToTaskAudit= daoCommon.insertToWorkFlowAudit(appNo);
            if(insertToTaskAudit.equalsIgnoreCase("Success")){
                insertToTaskAudit=daoCommon.insertTaskdtls(appNo,remarks,request,actorId,assignPrivId, taskId);
                if(insertToTaskAudit.equalsIgnoreCase("Success")){
                    insertToTaskAudit=daoCommon.insertToWorkFlow(appNo,remarks,request,statusId);
                    if(insertToTaskAudit.equalsIgnoreCase("Success")){
                        String allo = isavePrivateLandDao.updateApprovedDate(request, appNo, remarks);
                        responseMessage.setStatus(1);
                        responseMessage.setText("Application Number" + appNo +" approved successfully");
                        //sms here for marking
                    }else{
                        responseMessage.setStatus(0);
                        responseMessage.setText("System encountered with problem(s). Please try again or if the problem persist, contact with Administrator.");
                    }
                }
            }
        }
        return responseMessage;
    }
}