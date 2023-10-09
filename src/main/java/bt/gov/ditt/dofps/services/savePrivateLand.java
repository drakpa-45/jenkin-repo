package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.dao.IDaoCommon;
import bt.gov.ditt.dofps.dao.IDaoRuralTimber;
import bt.gov.ditt.dofps.dao.IsavePrivateLandDao;
import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.entitiy.DocumentEntity;
import bt.gov.ditt.dofps.entitiy.PersonalEntity;
import bt.gov.ditt.dofps.entitiy.TaskDetailEntity;
import bt.gov.ditt.dofps.entitiy.WorkFlowEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Tshedup Gyeltshen on 4/8/2020.
 */
@Service
public class savePrivateLand implements IsavePrivateLand {
    @Autowired
    IsavePrivateLandDao isavePrivateLandDao;

    @Autowired
    IDaoCommon daoCommon;
    @Autowired
    IDaoRuralTimber daoRuralTimber;

    @Override
    @Transactional
    public String savePrivateLand(CitizenDetailsDTO dto, NewTimberDto cdto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv=(UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();

        String applicationNumber = "";
        long Service_Id=0;
        Service_Id = isavePrivateLandDao.getServiceId();
        int Status_Id = daoCommon.getStatus_Id("Submitted");
        applicationNumber = daoCommon.AppNumberFormater(daoCommon.getApplicationSequenceNo(Service_Id), Service_Id);
        if(!applicationNumber.equalsIgnoreCase("") && applicationNumber!=null) {
            PersonalEntity personalEntity = convertToEntity(dto);
            personalEntity.setApplication_Number(applicationNumber);
            personalEntity.setApplication_Type("PRL");
            personalEntity.setMobile_Number(cdto.getMobile_Number());
            personalEntity.setPhone_Number(cdto.getPhone_Number());
            personalEntity.setRequest_Service_Id((int) Service_Id);
            personalEntity.setCons_Loc_Village_Serial_No(1);
            personalEntity.setApp_Submission_Date(new Date());
            personalEntity.setNwfp_Management_Group_Sl_No(1);
            personalEntity.setMode_of_Swing_Id(1);
            personalEntity.setCons_Approval_No(cdto.getCons_Approval_No());
            personalEntity.setDivision_Park_Id(cdto.getDivision_Park_Id());
            personalEntity.setPrivate_LandForm_Remarks(cdto.getPrivate_LandForm_Remarks());
            personalEntity.setAllot_Range_Officer(cdto.getAllot_Range_Officer());
            personalEntity.setAlternativeNumberRelation(cdto.getAlternativeNumberRelation());
            personalEntity.setPlot_No(cdto.getHeader_Name());
            String entity = isavePrivateLandDao.savePrivateLand(personalEntity);

            if(entity.equalsIgnoreCase("success")) {
                try {
                    WorkFlowEntity workFlowEntity = new WorkFlowEntity();
                    workFlowEntity.setApplication_Number(applicationNumber);
                    workFlowEntity.setStatus_Id(Status_Id);
                    workFlowEntity.setService_Id((int) Service_Id);
                    workFlowEntity.setAction_Date(new Date());
                    workFlowEntity.setActor_Id(roleId);
                    workFlowEntity.setActor_Name(userRolePriv.getFullName());
                    workFlowEntity.setRole_Name(userRolePriv.getUserRoles().getUserRole().get(0).getRoleName());
                    workFlowEntity.setRole_Id(Integer.parseInt(roleId));

                    TaskDetailEntity taskDetailEntity = new TaskDetailEntity();
                    taskDetailEntity.setApplication_Number(applicationNumber);
                    int privId=daoCommon.getpriId((int) Service_Id,"Submitter");
                    taskDetailEntity.setAssigned_Priv_Id(privId);
                    taskDetailEntity.setAction_Date(new Date());
                    taskDetailEntity.setSeq_Details_Id(27);
                    taskDetailEntity.setTask_State_Id(4);
                    taskDetailEntity.setWork(workFlowEntity);
                    taskDetailEntity.setAssigned_User_Id(userRolePriv.getUserRoles().getUserRole().get(0).getRoleName());
                    taskDetailEntity.setTask_Remark("");
                    TaskDetailEntity status = daoCommon.saveWorkFlow(taskDetailEntity);
                    if(status.getTask_Remark().equalsIgnoreCase("Success")) {
                        cdto.setApplication_Number(applicationNumber);
                    }else{
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.print("exception while inserting to t_task_app and t_work_flow" + e);
                }
            }

        }
        return applicationNumber;
    }

    @Override
    @Transactional
    public DocumentEntity saveDoc(MultipartFile[] files, String insertDetails, NewTimberDto cdto) {
        String uuid = null,filePath=null,status="";
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
                    doc.setApplication_Number(insertDetails);
                    status = daoRuralTimber.saveDocumentDetails(doc);
                    if (!status.equalsIgnoreCase("Success")) {
                        document.setApplication_Number(null);
                    } else {
                        document.setApplication_Number(insertDetails);
                    }
                }
        }
        catch (Exception e){
            System.out.print(e);
            document.setApplication_Number(null);
        }
        return document;
    }

    @Override
    @Transactional
    public WorkFlowDto getapplicationDetails(HttpServletRequest request, String type) {
        WorkFlowDto dto = new WorkFlowDto();
        String application_number = request.getParameter("Application_Number");
        try{

            dto = isavePrivateLandDao.getapplicationDetails(request, type);

            List<CommonDto> attachment = daoCommon.getAttachments(request);
            dto.setDocuments(attachment);

            List<TimberDetailsDto> product_name = daoCommon.getPvtProduct_catagory(application_number);
            dto.setTimberdetails(product_name);

            List<CommonDto> range = daoCommon.getRange(request, dto.getDivision_Park_Id());
            dto.setCommonDetails(range);

        }catch (Exception e){
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    @Transactional
    public WorkFlowDto getappDetails(HttpServletRequest request, String application_details) {
        WorkFlowDto dto = new WorkFlowDto();
        String application_number = request.getParameter("Application_Number");
        try{

            dto = isavePrivateLandDao.getapplDetails(application_number, application_details);

            List<CommonDto> attachment = daoCommon.getAttachments(request);
            dto.setDocuments(attachment);

            List<TimberDetailsDto> product_name = daoCommon.getProduct_catagory(application_number);
            dto.setTimberdetails(product_name);

            List<CommonDto> range = daoCommon.getRange(request, dto.getDivision_Park_Id());
            dto.setCommonDetails(range);

        }catch (Exception e){
            e.printStackTrace();
        }
        return dto;
    }

    private PersonalEntity convertToEntity(CitizenDetailsDTO dto) {
        PersonalEntity personalEntity = new PersonalEntity();
        BeanUtils.copyProperties(dto, personalEntity);
        return personalEntity;
    }

    @Override
    @Transactional
    public List<NewTimberDto> getRangeOffice(String div_id, NewTimberDto dto) {
        return daoRuralTimber.getDivisionPark(div_id, dto);
    }

    @Override
    @Transactional
    public List<NewTimberDto> getLandCategory() {
        return daoRuralTimber.getLandCategory();
    }

    @Override
    @Transactional
    public List<NewTimberDto> getForestProduce() {
        return daoRuralTimber.getForestProduce();
    }
}