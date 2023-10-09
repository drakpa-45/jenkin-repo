package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.common.utils.CommonNotificationUtils;
import bt.gov.ditt.dofps.dao.IDaoDealing;
import bt.gov.ditt.dofps.dao.IDaoRange;
import bt.gov.ditt.dofps.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pema Drakpa on 2/21/2020.
 */
@Service
public class ServiceDealing implements IServiceDealing {
    @Autowired
    IDaoDealing daoDealing;


    @Autowired
    IDaoRange daoRange;

    @Override
    @Transactional
    public List<CommonDto> getRejList() {
        List<CommonDto> dto = daoDealing.getRejList();
        return dto;
    }

    @Override
    @Transactional
    public List<CommonDto> getPRLRejList() {
        List<CommonDto> dto = daoDealing.getPRLRejList();
        return dto;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public WorkFlowDto upadteClaimedApp(WorkFlowDto workFlow, ListDto listDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");

        for(int m=0; m<userBean.getRoles().length;m++){
            for(int i=0; i<userBean.getRoles()[m].getServices().length;i++){
                bt.gov.ditt.dofps.dto.Service svc = userBean.getCurrentRole().getServices()[i];
                for (int j = 0; j < svc.getPrivileges().length; j++) {
                    Privilege priv = svc.getPrivileges()[j];
                    //   System.out.println("role name : " + userBean.getRoles()[m].getRoleCode() + " svc name : " + svc.getServiceName() + " && priv code : " + priv.getPrivilegeCode() + "(" + priv.getPrivilegeId() + ")");
                    if (priv.getPrivilegeName().equalsIgnoreCase("Verifier")) {
                        workFlow.setActor_Id(priv.getPrivilegeId());
                    }
                }
            }
        }
        String roleId = workFlow.getActor_Id();
        String actorID = userBean.getUserID();
        String role_name = userBean.getCurrentRole().getRoleName();
        WorkFlowDto dto = daoDealing.insertAudit(workFlow, request);
        if (dto.getCurrent_Status().equalsIgnoreCase("success")) {
            WorkFlowDto fp_app = daoDealing.update_fp_app(workFlow, request, roleId, role_name);
            if (fp_app.getCurrent_Status().equalsIgnoreCase("success")) {
                WorkFlowDto workfolw = daoDealing.insertWrkFlw(workFlow, request);
                if (workfolw.getCurrent_Status().equalsIgnoreCase("success")) {
                    WorkFlowDto wrkflw = daoDealing.updateWrkFlw(workFlow, request, roleId, role_name, actorID);
                    if (wrkflw.getCurrent_Status().equalsIgnoreCase("success")) {
                        WorkFlowDto t_task = daoDealing.insert_task(workFlow, request);
                        if (t_task.getCurrent_Status().equalsIgnoreCase("success")) {
                            WorkFlowDto allo = daoDealing.upadteClaimedApp(workFlow, request, roleId, role_name);
                            if (allo.getCurrent_Status().equalsIgnoreCase("success")) {
                                WorkFlowDto task = daoDealing.insertAlloAudit(workFlow, request);
                                if (task.getCurrent_Status().equalsIgnoreCase("success") && !CollectionUtils.isEmpty(listDto.getWorkFlowDtoList())) {
                                    for (WorkFlowDto workFlowDtoList : listDto.getWorkFlowDtoList()) {
                                        if(workFlowDtoList.getFp_Product_Id()==5 ||workFlowDtoList.getFp_Product_Id()== 15){
                                            workFlowDtoList.setFp_Product_Id(workFlowDtoList.getFp_Product_Id());
                                            WorkFlowDto allot = daoDealing.updateAllot(workFlowDtoList,workFlow,request);
                                        }else{
                                            workFlowDtoList.setFp_Product_Id(workFlowDtoList.getFp_Product_Id());
                                            workFlowDtoList.setAllot_Quantity(workFlowDtoList.getAllot_Quantity());
                                            workFlowDtoList.setNet_Royalty(workFlow.getNet_Royalty());
                                            workFlowDtoList.setTotal_Royalty(workFlow.getTotal_Royalty());
                                            WorkFlowDto allotment = daoDealing.updateAllotment(workFlowDtoList,workFlow, request);
                                        }
                                    }
                                } else {
                                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                                }
                            } else {
                                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                            }
                        } else {
                            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                        }
                    } else {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                } else {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
            } else {
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            }
        } else {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
        }
        return dto;
    }

    @Override
    @Transactional
    public WorkFlowDto rejectApplication(WorkFlowDto workFlowDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        WorkFlowDto rejected = daoDealing.rejected_application(workFlowDto, request, roleId, role_name);
        if(rejected.getCurrent_Status().equalsIgnoreCase("success")) {
            WorkFlowDto updateTsk = daoRange.updateTdsk(workFlowDto, request, roleId, role_name);
            if (updateTsk.getCurrent_Status().equalsIgnoreCase("success")) {
                String reject_reason = workFlowDto.getRejection_Reason();
                if(reject_reason.equalsIgnoreCase("Others")){
                    workFlowDto.setRejection_Reason(workFlowDto.getDocument_Type());
                    WorkFlowDto tsk_dtls = daoDealing.update_t_ap_rejected_application(workFlowDto, request, roleId, role_name);

                    ArrayList<String> contactNo=new ArrayList<String>();
                    contactNo.add(workFlowDto.getPhone_Number());
                    String[] MobileNo=contactNo.toArray(new String[contactNo.size()]);

                    //Added Reason for Rejection
                    boolean isSend = CommonNotificationUtils.notifyOnRejection(workFlowDto.getApplication_Number(), MobileNo, workFlowDto.getRejection_Reason());
                }else{
                    WorkFlowDto tsk_dtls = daoDealing.update_t_ap_rejected_application(workFlowDto, request, roleId, role_name);

                    ArrayList<String> contactNo=new ArrayList<String>();
                    contactNo.add(workFlowDto.getPhone_Number());
                    String[] MobileNo=contactNo.toArray(new String[contactNo.size()]);

                    //Added Reason for Rejection
                    boolean isSend = CommonNotificationUtils.notifyOnRejection(workFlowDto.getApplication_Number(), MobileNo, workFlowDto.getRejection_Reason());
                }
            }
        }
        return null;
    }

    @Override
    @Transactional
    public String getRejReason(WorkFlowDto workFlowDto) {
       String reason = daoDealing.getRejReason(workFlowDto);
        return reason;}

    @Override
    @Transactional
    public WorkFlowDto upadteClaimedPLApp(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");

        for(int m=0; m<userBean.getRoles().length;m++){
            for(int i=0; i<userBean.getRoles()[m].getServices().length;i++){
                bt.gov.ditt.dofps.dto.Service svc = userBean.getCurrentRole().getServices()[i];
                for (int j = 0; j < svc.getPrivileges().length; j++) {
                    Privilege priv = svc.getPrivileges()[j];
                    //   System.out.println("role name : " + userBean.getRoles()[m].getRoleCode() + " svc name : " + svc.getServiceName() + " && priv code : " + priv.getPrivilegeCode() + "(" + priv.getPrivilegeId() + ")");
                    if (priv.getPrivilegeName().equalsIgnoreCase("Verifier")) {
                        workFlowDto.setActor_Id(priv.getPrivilegeId());
                    }
                }
            }
        }
        String roleId = workFlowDto.getActor_Id();
        String actorID = userBean.getUserID();
        String role_name = userBean.getCurrentRole().getRoleName();
        WorkFlowDto dto = daoDealing.insertAudit(workFlowDto, request);
        if (dto.getCurrent_Status().equalsIgnoreCase("success")) {
            WorkFlowDto fp_app = daoDealing.update_fp_app(workFlowDto, request, roleId, role_name);
            if (fp_app.getCurrent_Status().equalsIgnoreCase("success")) {
                WorkFlowDto workfolw = daoDealing.insertWrkFlw(workFlowDto, request);
                if (workfolw.getCurrent_Status().equalsIgnoreCase("success")) {
                    WorkFlowDto wrkflw = daoDealing.updateWrkFlw(workFlowDto, request, roleId, role_name, actorID);
                    if (wrkflw.getCurrent_Status().equalsIgnoreCase("success")) {
                        WorkFlowDto t_task = daoDealing.insert_task(workFlowDto, request);
                        if (t_task.getCurrent_Status().equalsIgnoreCase("success")) {
                            WorkFlowDto allo = daoDealing.upadteClaimedApp(workFlowDto, request, roleId, role_name);
                        } else {
                            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                        }
                    } else {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                } else {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
            } else {
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            }
        } else {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
        }
        return dto;
    }
}
