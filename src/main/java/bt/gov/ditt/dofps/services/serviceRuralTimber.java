package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.dao.IDaoCommon;
import bt.gov.ditt.dofps.dao.IDaoDealing;
import bt.gov.ditt.dofps.dao.IDaoRuralTimber;
import bt.gov.ditt.dofps.dao.IDaoWoodPole;
import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.entitiy.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Pema Drakpa on 1/16/2020.
 */
@Service
public class serviceRuralTimber implements iServiceRuralTimber {

    @Autowired
    IDaoRuralTimber daoRuralTimber;

    @Autowired
    IDaoCommon daoCommon;

    @Autowired
    IDaoDealing daoDealing;

    @Autowired
    IDaoWoodPole iDaoWoodPole;

    @Override
    @Transactional
    public List<NewTimberDto> getParkList(HttpServletRequest request, Integer dzo_Id) {
        return daoRuralTimber.getParkList(request,dzo_Id);
    }

    @Override
    @Transactional
    public Integer getDzoId(HttpServletRequest request, NewTimberDto dto) {
        return daoRuralTimber.getDzoId(request, dto);
    }

    @Override
    @Transactional
    public List<NewTimberDto> getVillageList(HttpServletRequest request) {
        return daoRuralTimber.getVillageList(request);
    }

    @Override
    @Transactional
    public List<NewTimberDto> getDzongkhagList() {
        return daoRuralTimber.getDzongkhagList();
    }

    @Override
    @Transactional
    public List<NewTimberDto> getDropdownList(String sl_no, String type) {
        return daoRuralTimber.getDropdownList(sl_no, type);
    }

    @Override
    @Transactional
    public List<claimAdditionalTimberDTO> getApplicantDetails(String cid) {
        return daoRuralTimber.getApplicantDetails(cid);
    }

    @Override
    @Transactional
    public List<claimAdditionalTimberDTO> viewAttachedDocs(String appNo) {
        return daoRuralTimber.getAttachments(appNo);
    }

    @Override
    @Transactional
    public WorkFlowDto getApplDetails(String cons_type, CitizenDetailsDTO dto) {
        return daoRuralTimber.getApplDetails(cons_type,dto);
    }

    @Override
    @Transactional
    public String saveReclaimDetails(claimAdditionalTimberDTO claimAdditionalTimberDTO, String claimStatus, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv=(UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String actorId = userBean.getUserID();
        BigInteger untExit = daoRuralTimber.validatePreviousApp(claimAdditionalTimberDTO);
        BigInteger inTime = daoRuralTimber.validateDuration(claimAdditionalTimberDTO);
        String applicationNumber = "";
        long Service_Id=0;
        String Status = "";

        if (untExit.intValue() == 0) {
            if(inTime.intValue() == 1){
                Service_Id = daoRuralTimber.getServiceId();
                int Status_Id = daoCommon.getStatus_Id("Submitted");
                applicationNumber = daoCommon.AppNumberFormater(daoCommon.getApplicationSequenceNo(Service_Id), Service_Id);
                if(!applicationNumber.equalsIgnoreCase("") && applicationNumber!=null) {
                    PersonalEntity personalEntity = new PersonalEntity();
                    personalEntity.setApplication_Number(applicationNumber);
                    personalEntity.setApplication_Type("RTP");
                    personalEntity.setRequest_Service_Id(181);
                    personalEntity.setCid_Number(claimAdditionalTimberDTO.getCid_Number());
                    personalEntity.setName(claimAdditionalTimberDTO.getName());
                    personalEntity.setGenderType(claimAdditionalTimberDTO.getGenderType());
                    personalEntity.setDzongkhag_Name(claimAdditionalTimberDTO.getDzongkhag_Name());
                    personalEntity.setGewog_Name(claimAdditionalTimberDTO.getGewog_Name());
                    personalEntity.setVillage_Name(claimAdditionalTimberDTO.getVillage_Name());
                    personalEntity.setVillage_Serial_No(claimAdditionalTimberDTO.getVillage_Serial_No());
                    personalEntity.setHouse_Hold_No(claimAdditionalTimberDTO.getHouse_Hold_No());
                    personalEntity.setHouse_No(claimAdditionalTimberDTO.getHouse_No());
                    personalEntity.setHead_of_Gung(claimAdditionalTimberDTO.getHead_of_Gung());
                    personalEntity.setPhone_Number(claimAdditionalTimberDTO.getPhone_Number());
                    personalEntity.setRegister_Geog(claimAdditionalTimberDTO.getRegister_Geog());
                    personalEntity.setRoofing_Type(claimAdditionalTimberDTO.getRoofing_Type());
                    personalEntity.setCons_Type("n");
                    personalEntity.setApp_Submission_Date(new Date());
                    personalEntity.setPrevious_application_Number(claimAdditionalTimberDTO.getPrevious_application_Number());
                    personalEntity.setClaimStatus(claimStatus);
                    personalEntity.setCc_Reclaim_Remarks(claimAdditionalTimberDTO.getCc_Reclaim_Remarks());
                    personalEntity.setDivision_Park_Id(claimAdditionalTimberDTO.getDivision_Park_Id());
                    personalEntity.setMode_of_Swing_Id(claimAdditionalTimberDTO.getMode_Of_Swing_Id());
                    personalEntity.setCons_Approval_No(claimAdditionalTimberDTO.getCons_Approval_No());
                    personalEntity.setConstruction_Location(claimAdditionalTimberDTO.getConstruction_Loc());
                    personalEntity.setCons_Loc_Village_Serial_No(claimAdditionalTimberDTO.getVillage_Serial_No());
                    personalEntity.setMember_of_Forest_community(claimAdditionalTimberDTO.getMember_of_forest_community());
                    personalEntity.setStorey_House(claimAdditionalTimberDTO.getHouse_Storey());
                    personalEntity.setNwfp_Management_Group_Sl_No(1);
                    personalEntity.setDivision_Park_Id_1(0);
                    personalEntity.setGeog_No(0);
                    personalEntity.setPermit_Fee(0);
                    personalEntity.setService_Fees(0);
                    personalEntity.setApplication_Fees(0);
                    personalEntity.setSync_G2C(0);


                    String entity = daoRuralTimber.saveReclaimDetails(personalEntity);
                    if(entity.equalsIgnoreCase("success")){
                        try{
                            WorkFlowEntity workFlowEntity = new WorkFlowEntity();
                            workFlowEntity.setApplication_Number(applicationNumber);
                            workFlowEntity.setStatus_Id(Status_Id);
                            workFlowEntity.setService_Id((int) Service_Id);
                            workFlowEntity.setAction_Date(new Date());
                            workFlowEntity.setActor_Id(actorId);
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
                                claimAdditionalTimberDTO.setApplication_Number(applicationNumber);
                            }else{
                                return null;
                            }

                            TimberEntity timberEntity = new TimberEntity();
                            timberEntity.setApplication_Number(applicationNumber);
                            timberEntity.setfP_Product_Id(claimAdditionalTimberDTO.getFP_product_Id());
                            timberEntity.setAppl_Quantity(claimAdditionalTimberDTO.getApply_Quantity());
                            String update = daoRuralTimber.saveTimber(timberEntity);

                        }catch (Exception e){
                            e.printStackTrace();
                            System.out.print("exception while inserting to t_task_app and t_work_flow" + e);
                        }
                    }
                }
                Status = applicationNumber;
            }else{
                Status = "failed";
            }
        }
        else {
            Status = "failed";
        }
        return Status;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public String saveApplicationDetails(String cons_type, CitizenDetailsDTO dto, String cons_desc, NewTimberDto newTimberDto, String type, HttpServletRequest request, String locationId, String juri_Type_Id) {
        String status="", applicationNumber="";
        long Service_Id=0;
        List<NewTimberDto> timberDetails = new ArrayList<NewTimberDto>();

        try {
            if(cons_desc.equalsIgnoreCase("WP")){
                if (newTimberDto.getApplication_Number().equalsIgnoreCase("0")) {
                    applicationNumber = daoRuralTimber.DraftFormat(daoRuralTimber.getTempNo());
                    newTimberDto.setApplication_Number(applicationNumber);
                }
                if (type.equalsIgnoreCase("personal_details")) {
                    /*if (cons_desc.equalsIgnoreCase("WP")) {*/
                        Service_Id = iDaoWoodPole.getServiceId();
                    /*} else if (cons_desc.equalsIgnoreCase("RTP")) {
                        Service_Id = daoCommon.getServiceId();
                    }*/
                    PersonalEntity personalEntity = convertToEntity(newTimberDto);
                    personalEntity.setApplication_Number(applicationNumber);
                    personalEntity.setCid_Number(dto.getCid_Number());
                    personalEntity.setName(dto.getName());
                    personalEntity.setVillage_Serial_No(dto.getVillage_Serial_No());
                    personalEntity.setGenderType(dto.getGenderType());
                    personalEntity.setHouse_No(dto.getHouse_No());
                    personalEntity.setHouse_Hold_No(dto.getHouse_Hold_No());
                    personalEntity.setMobile_Number(newTimberDto.getMobile_Number());
                    personalEntity.setAlternativeNumberRelation(newTimberDto.getAlternativeNumberRelation());
                    personalEntity.setPhone_Number(newTimberDto.getPhone_Number());
                    personalEntity.setDivision_Park_Id(newTimberDto.getDivision_Park_Id());
                    personalEntity.setCons_Loc_Village_Serial_No(1);
                    personalEntity.setNwfp_Management_Group_Sl_No(1);
                    personalEntity.setDzongkhag_Name(dto.getDzongkhag_Name());
                    personalEntity.setGewog_Name(dto.getGewog_Name());
                    personalEntity.setVillage_Name(dto.getVillage_Name());
                    personalEntity.setApplication_Type("WP");
                    personalEntity.setHead_of_Gung("Yes");
                    personalEntity.setRequest_Service_Id((int) Service_Id);
                    personalEntity.setMode_of_Swing_Id(1);
                    personalEntity.setCons_Type(cons_type);
                    if (newTimberDto.getRegister_Geog().equalsIgnoreCase("y")) {
                        personalEntity.setRegister_Geog("Yes");
                    } else {
                        personalEntity.setRegister_Geog("No");
                    }
                    personalEntity.setCons_Approval_No(newTimberDto.getCons_Approval_No());

                    if (!applicationNumber.equalsIgnoreCase("")) {
                        personalEntity.setApplication_Number(applicationNumber);
                    } else {
                        personalEntity.setApplication_Number(newTimberDto.getApplication_Number());
                    }
                    status = daoRuralTimber.saveApplicationDetails(personalEntity);
                } else if (type.equalsIgnoreCase("save_timber_details")) {
                    status = daoRuralTimber.deleteData(newTimberDto);
                    if (!CollectionUtils.isEmpty(newTimberDto.getTimberdetails())) {
                        for (TimberDetailsDto timberdetails : newTimberDto.getTimberdetails()) {
                            TimberEntity timberEntity = convertToTimberEntity(newTimberDto);
                            timberEntity.setCid_Number(dto.getCid_Number());
                            timberEntity.setfP_Product_Id(timberdetails.getfP_Product_Id());
                            if(timberdetails.getAppl_Quantity() == 0){
                                timberEntity.setAppl_Quantity(timberdetails.getAppl_Quantity_Log());
                            }else{
                                timberEntity.setAppl_Quantity(timberdetails.getAppl_Quantity());
                            }
                            timberEntity.setAllot_Quantity(0);
                            status = daoRuralTimber.saveTimber(timberEntity);
                            if (status.equalsIgnoreCase("success")) {
                                daoRuralTimber.update(newTimberDto);
                            } else {
                                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                            }
                        }
                    } else {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                } else {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
                if (status.equalsIgnoreCase("Success") && applicationNumber != "") {
                    status = applicationNumber;
                }
                if (status.equalsIgnoreCase("Success") && applicationNumber == "") {
                    status = newTimberDto.getApplication_Number();
                }
            }else if(cons_desc.equalsIgnoreCase("RTP")) {
                //25 years validation
                BigInteger untExit = daoRuralTimber.CountExit(dto, newTimberDto, request, cons_type);
                if (untExit.intValue() == 0) {
                    if (newTimberDto.getApplication_Number().equalsIgnoreCase("0")) {
                        applicationNumber = daoRuralTimber.DraftFormat(daoRuralTimber.getTempNo());
                        newTimberDto.setApplication_Number(applicationNumber);
                    }
                    if (type.equalsIgnoreCase("personal_details")) {
                        Service_Id = daoCommon.getServiceId("RTP");
                        PersonalEntity personalEntity = convertToEntity(newTimberDto);
                        personalEntity.setApplication_Number(applicationNumber);
                        personalEntity.setCid_Number(dto.getCid_Number());
                        personalEntity.setName(dto.getName());
                        personalEntity.setVillage_Serial_No(dto.getVillage_Serial_No());
                        personalEntity.setGenderType(dto.getGenderType());
                        personalEntity.setHouse_No(dto.getHouse_No());
                        personalEntity.setHouse_Hold_No(dto.getHouse_Hold_No());
                        personalEntity.setMobile_Number(newTimberDto.getMobile_Number());
                        personalEntity.setPhone_Number(newTimberDto.getPhone_Number());
                        personalEntity.setDivision_Park_Id(newTimberDto.getDivision_Park_Id());
                        personalEntity.setCons_Loc_Village_Serial_No(1);
                        personalEntity.setNwfp_Management_Group_Sl_No(1);
                        personalEntity.setDzongkhag_Name(dto.getDzongkhag_Name());
                        personalEntity.setDzongkhag_Name(dto.getGewog_Name());
                        personalEntity.setVillage_Name(dto.getVillage_Name());
                        personalEntity.setHead_of_Gung("Yes");
                        personalEntity.setApplication_Type("RTP");
                        personalEntity.setRequest_Service_Id((int) Service_Id);
                        personalEntity.setMode_of_Swing_Id(1);
                        personalEntity.setCons_Type(cons_type);
                        personalEntity.setClaimStatus("New");
                        personalEntity.setThram_No(newTimberDto.getThram_NO());
                        if (newTimberDto.getRegister_Geog().equalsIgnoreCase("y")) {
                            personalEntity.setRegister_Geog("Yes");
                        } else {
                            personalEntity.setRegister_Geog("No");
                        }
                        personalEntity.setCons_Approval_No(newTimberDto.getCons_Approval_No());

                        if (!applicationNumber.equalsIgnoreCase("")) {
                            personalEntity.setApplication_Number(applicationNumber);
                        } else {
                            personalEntity.setApplication_Number(newTimberDto.getApplication_Number());
                        }
                        status = daoRuralTimber.saveApplicationDetails(personalEntity);
                    } else if (type.equalsIgnoreCase("save_timber_details")) {
                        status = daoRuralTimber.deleteData(newTimberDto);
                        if (!CollectionUtils.isEmpty(newTimberDto.getTimberdetails())) {
                            for (TimberDetailsDto timberdetails : newTimberDto.getTimberdetails()) {
                                TimberEntity timberEntity = convertToTimberEntity(newTimberDto);
                                timberEntity.setCid_Number(dto.getCid_Number());
                                timberEntity.setfP_Product_Id(timberdetails.getfP_Product_Id());


                                if (timberdetails.getProduct_Catagory().equalsIgnoreCase("RTP(l)")) {
                                    timberEntity.setAppl_Quantity(timberdetails.getAppl_Quantity_Log());
                                } else {
                                    timberEntity.setAppl_Quantity(timberdetails.getAppl_Quantity());
                                }

                                timberEntity.setAllot_Quantity(0);
                                status = daoRuralTimber.saveTimber(timberEntity);
                                if (status.equalsIgnoreCase("success")) {
                                    if (timberdetails.getProduct_Catagory().equalsIgnoreCase("RTP(l)")) {
                                        newTimberDto.setStorey_House(newTimberDto.getHeader_Name());
                                    } else {
                                        newTimberDto.setStorey_House(newTimberDto.getStorey_House());
                                    }
                                    daoRuralTimber.update(newTimberDto);
                                } else {
                                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                                }
                            }
                        } else {
                            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                        }
                    } else {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                    if (status.equalsIgnoreCase("Success") && applicationNumber != "") {
                        status = applicationNumber;
                    }
                    if (status.equalsIgnoreCase("Success") && applicationNumber == "") {
                        status = newTimberDto.getApplication_Number();
                    }
                } else {
                    return "exist";
                }
            }else {
                return "failed";
            }
        }
            catch (Exception e){
            e.printStackTrace();
            status="Exception: inserting to t_fp_application and t_allotment_tables"+e;
        }
        return status;
    }
   @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public NewTimberDto saveFinalApplciationDetails(CitizenDetailsDTO dto, NewTimberDto newTimberDto, String type, HttpServletRequest request) {
       HttpSession session = request.getSession();
       UserRolePrivilegeHierarchyObj userRolePriv=(UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
       UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
       String roleId = userBean.getCurrentRole().getRoleId();
       String actorId = userBean.getUserID();
       String applicationNumber = "";
       NewTimberDto dtoDetails = null;
       long Service_Id=0;
        try {
            Service_Id = daoCommon.getServiceId("RTP");
            int Status_Id = daoCommon.getStatus_Id("Submitted");
            applicationNumber = daoCommon.AppNumberFormater(daoCommon.getApplicationSequenceNo(Service_Id), Service_Id);
            dtoDetails = daoRuralTimber.saveUpdateApplication(dto,newTimberDto,applicationNumber,newTimberDto.getApplication_Number());
            if(dtoDetails.getApplication_Number() != null){
                WorkFlowEntity workFlowEntity = new WorkFlowEntity();
                workFlowEntity.setApplication_Number(applicationNumber);
                workFlowEntity.setStatus_Id(Status_Id);
                workFlowEntity.setService_Id((int) Service_Id);
                workFlowEntity.setAction_Date(new Date());
                workFlowEntity.setActor_Id(actorId);
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
                    newTimberDto.setApplication_Number(applicationNumber);
                }
                return newTimberDto;
            }
            else{
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.print("exception while inserting to t_task_app and t_work_flow"+e);
        }
        return dtoDetails;
    }

   @Override
    @Transactional
    public DocumentEntity saveDoc(MultipartFile[] files, String appNo, HttpServletRequest request, String cons_type,NewTimberDto newTimberDto) {
        String uuid = null,filePath=null,status="";
        DocumentEntity document= new DocumentEntity();

        try {
            status = daoRuralTimber.updateMemberOfCommunity(appNo, newTimberDto);
            if (status.equalsIgnoreCase("success")) {
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
                    status = daoRuralTimber.saveDocumentDetails(doc);
                    if (!status.equalsIgnoreCase("Success")) {
                        document.setApplication_Number(null);
                    } else {
                        document.setApplication_Number(appNo);
                    }
                }
            } else{
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
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
    public WorkFlowDto printApplication(WorkFlowDto workFlow, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorID = userBean.getUserID();
        WorkFlowDto workfolw = daoRuralTimber.insertWrkFlw(workFlow, request);
       if (workfolw.getCurrent_Status().equalsIgnoreCase("success")) {
            WorkFlowDto wrkflw = daoRuralTimber.updateWrkFlwTablePrint(workFlow, request, roleId, role_name,actorID);
        } else {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
        }
        return workFlow;
    }
    @Transactional
    private PersonalEntity convertToEntity(NewTimberDto newTimberDto) {
        PersonalEntity personalEntity = new PersonalEntity();
        BeanUtils.copyProperties(newTimberDto, personalEntity);
        return personalEntity;
    }

    @Transactional
    private TimberEntity convertToTimberEntity(NewTimberDto newTimberDto) {
        TimberEntity timberEntity = new TimberEntity();
        BeanUtils.copyProperties(newTimberDto, timberEntity);
        return timberEntity;
    }

    @Transactional
    private DocumentEntity convertToDocumentEntity(CitizenDetailsDTO dto) {
        DocumentEntity documentEntity = new DocumentEntity();
        BeanUtils.copyProperties(dto, documentEntity);
        return documentEntity;
    }

    @Override
    @Transactional
    public List<NewTimberDto> getGrowingSeason() {
        return daoRuralTimber.getGrowingSeason();
    }

    @Override
    @Transactional
    public List<CommonDto> getProductList() {
        return daoRuralTimber.getProductList();
    }
}
