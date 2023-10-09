package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.dao.IDaoCommon;
import bt.gov.ditt.dofps.dao.IDaoWoodPole;
import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.entitiy.TaskDetailEntity;
import bt.gov.ditt.dofps.entitiy.TimberEntity;
import bt.gov.ditt.dofps.entitiy.WorkFlowEntity;
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
import java.util.Date;
import java.util.List;

/**
 * Created by Pema Drakpa on 2/19/2020.
 */
@Service
public class ServiceWoodPole implements IServiceWoodPole{
    @Autowired
    IDaoWoodPole iDaoWoodPole;
    @Autowired
    IDaoCommon daoCommon;

    @Override
    @Transactional
    public List<CommonDto> getProductDetails(String cons_desc) {
        return iDaoWoodPole.getProductDetails(cons_desc);
    }

    @Override
    @Transactional
    public List<CommonDto> getUnitDetails(String sl_no, String type) {
        return iDaoWoodPole.getUnitDetails(sl_no, type);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public NewTimberDto saveFinalApplciationDetails(CitizenDetailsDTO dto, NewTimberDto newTimberDto, String type, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv=(UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String actorID = userBean.getUserID();
        String roleName = userBean.getCurrentRole().getRoleName();
        String applicationNumber = "";
        NewTimberDto dtoDetails = null;
        long Service_Id=0;
        try {
            Service_Id = iDaoWoodPole.getServiceId();
            int Status_Id = daoCommon.getStatus_Id("Submitted");
            applicationNumber = daoCommon.AppNumberFormater(daoCommon.getApplicationSequenceNo(Service_Id), Service_Id);
            if(applicationNumber!=null){
                if (!CollectionUtils.isEmpty(newTimberDto.getTimberdetails())) {
                    for (TimberDetailsDto timberdetails : newTimberDto.getTimberdetails()) {
                        TimberEntity timberEntity = convertToTimberEntity(newTimberDto);
                        if(timberdetails.getfP_Product_Id()!= 0){
                            timberEntity.setApplication_Number(applicationNumber);
                            timberEntity.setfP_Product_Id(timberdetails.getfP_Product_Id());
                            timberEntity.setAppl_Quantity(timberdetails.getAppl_Quantity());
                            timberEntity.setAllot_Quantity(0);
                            String result = iDaoWoodPole.saveTimberdtls(timberEntity);
                        }

                    }
                } else {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
            }
            dtoDetails = iDaoWoodPole.saveUpdateApplication(dto,newTimberDto,applicationNumber,newTimberDto.getApplication_Number());
            if(dtoDetails.getApplication_Number() != null){
                WorkFlowEntity workFlowEntity = new WorkFlowEntity();
                workFlowEntity.setApplication_Number(applicationNumber);
                workFlowEntity.setStatus_Id(Status_Id);
                workFlowEntity.setService_Id((int) Service_Id);
                workFlowEntity.setAction_Date(new Date());
                workFlowEntity.setActor_Id(actorID);
                workFlowEntity.setActor_Name(userRolePriv.getFullName());
                workFlowEntity.setRole_Name(roleName);
                workFlowEntity.setRole_Id(Integer.parseInt(roleId));

                TaskDetailEntity taskDetailEntity = new TaskDetailEntity();
                taskDetailEntity.setApplication_Number(applicationNumber);
                int privId=daoCommon.getpriId((int) Service_Id,"Submitter");
                taskDetailEntity.setAssigned_Priv_Id(privId);
                taskDetailEntity.setAction_Date(new Date());
                taskDetailEntity.setSeq_Details_Id(27);
                taskDetailEntity.setTask_State_Id(4);
                taskDetailEntity.setWork(workFlowEntity);
                taskDetailEntity.setAssigned_User_Id(newTimberDto.getActor_Name());
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

    @Transactional
    private TimberEntity convertToTimberEntity(NewTimberDto newTimberDto) {
        TimberEntity timberEntity = new TimberEntity();
        BeanUtils.copyProperties(newTimberDto, timberEntity);
        return timberEntity;
    }

    @Override
    @Transactional
    public BigInteger getYearValidation(String sl_no, String house_no) {
        BigInteger count = daoCommon.getYearValidation(sl_no, house_no);
        return count; }
}
