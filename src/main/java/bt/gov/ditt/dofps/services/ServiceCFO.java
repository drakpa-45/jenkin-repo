package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.common.utils.CommonNotificationUtils;
import bt.gov.ditt.dofps.dao.IDaoCFO;
import bt.gov.ditt.dofps.dao.IDaoDealing;
import bt.gov.ditt.dofps.dto.ListDto;
import bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO;
import bt.gov.ditt.dofps.dto.WorkFlowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Created by Pema Drakpa on 2/12/2020.
 */
@Service
public class ServiceCFO implements IServiceCFO {
    @Autowired
    IDaoCFO daoCFO;

    @Autowired
    IDaoDealing daoDealing;

    @Override
    @Transactional
    public WorkFlowDto upadteClaimedApp(WorkFlowDto workFlowDto,ListDto listDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name=userBean.getCurrentRole().getRoleName();
        String actorID =userBean.getUserID();

        WorkFlowDto updateAppAudit = daoDealing.insertAudit(workFlowDto, request);
        if(updateAppAudit.getCurrent_Status().equalsIgnoreCase("success")){
            WorkFlowDto updateAllotAudit = daoDealing.insertAlloAudit(workFlowDto, request);
            if(updateAllotAudit.getCurrent_Status().equalsIgnoreCase("success") ){
                WorkFlowDto updateTdskAudit = daoDealing.insert_task(workFlowDto, request);
                if(updateTdskAudit.getCurrent_Status().equalsIgnoreCase("success")){
                    WorkFlowDto updateWrkAudit = daoDealing.insertWrkFlw(workFlowDto, request);
                    if(updateWrkAudit.getCurrent_Status().equalsIgnoreCase("success")){
                        WorkFlowDto upadteClaimedApp = daoCFO.updateTdsk(workFlowDto, request, roleId, role_name);
                        if(upadteClaimedApp.getCurrent_Status().equalsIgnoreCase("success")){
                            WorkFlowDto upadteWrks = daoCFO.upadteWrks(workFlowDto,request,roleId,role_name,actorID);
                            if(upadteWrks.getCurrent_Status().equalsIgnoreCase("success")) {
                                WorkFlowDto allo = daoCFO.upadteClaimedApp(workFlowDto, request, roleId, role_name);
                                if (allo.getCurrent_Status().equalsIgnoreCase("success") && !CollectionUtils.isEmpty(listDto.getWorkFlowDtoList())) {
                                    for (WorkFlowDto workFlowDtoList : listDto.getWorkFlowDtoList()) {
                                        if(workFlowDtoList.getFp_Product_Id() == 5 || workFlowDtoList.getFp_Product_Id() == 15){
                                            workFlowDtoList.setFp_Product_Id(workFlowDtoList.getFp_Product_Id());
                                            workFlowDtoList.setAllot_Quantity(workFlowDto.getAllot_Quantity_log());
                                            WorkFlowDto updateAllot = daoCFO.updateAllotment(workFlowDtoList,workFlowDto, request);
                                        }else{
                                            workFlowDtoList.setFp_Product_Id(workFlowDtoList.getFp_Product_Id());
                                            workFlowDtoList.setAllot_Quantity(workFlowDtoList.getAllot_Quantity());
                                            workFlowDtoList.setNet_Royalty(workFlowDtoList.getNet_Royalty());
                                            workFlowDtoList.setTotal_Royalty(workFlowDtoList.getTotal_Royalty());
                                            WorkFlowDto updateAllot = daoCFO.updateAllot(workFlowDtoList,workFlowDto, request);
                                        }
                                    }

                                    ArrayList<String> contactNo=new ArrayList<String>();
                                    contactNo.add(workFlowDto.getPhone_Number());
                                    String[] MobileNo=contactNo.toArray(new String[contactNo.size()]);
                                    String allotrange = daoCFO.getAllotRangeOffice(workFlowDto.getApplication_Number());
                                    workFlowDto.setRange_Officer(allotrange);

                                    //Application approval notification
                                    boolean isSend = CommonNotificationUtils.notifyOnApproval(workFlowDto.getApplication_Number(), MobileNo, workFlowDto.getRange_Officer());
                                } else {
                                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                                }
                            }else {
                                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                            }
                        }else {
                            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                        }
                    }else {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                }else {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
            }else {
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            }
        }else {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
        }
        return null;
    }

    @Override
    @Transactional
    public WorkFlowDto upadteClaimedWPApp(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name=userBean.getCurrentRole().getRoleName();
        String actorID = userBean.getUserID();

        WorkFlowDto updateAppAudit = daoDealing.insertAudit(workFlowDto, request);
        if(updateAppAudit.getCurrent_Status().equalsIgnoreCase("success")){
            WorkFlowDto updateAllotAudit = daoDealing.insertAlloAudit(workFlowDto, request);
            if(updateAllotAudit.getCurrent_Status().equalsIgnoreCase("success") ){
                WorkFlowDto updateTdskAudit = daoDealing.insert_task(workFlowDto, request);
                if(updateTdskAudit.getCurrent_Status().equalsIgnoreCase("success")){
                    WorkFlowDto updateWrkAudit = daoDealing.insertWrkFlw(workFlowDto, request);
                    if(updateWrkAudit.getCurrent_Status().equalsIgnoreCase("success")){
                        WorkFlowDto upadteClaimedApp = daoCFO.updateTdsk(workFlowDto, request, roleId, role_name);
                        if(upadteClaimedApp.getCurrent_Status().equalsIgnoreCase("success")){
                            WorkFlowDto upadteWrks = daoCFO.upadteWrks(workFlowDto,request,roleId,role_name, actorID);
                            if(upadteWrks.getCurrent_Status().equalsIgnoreCase("success")) {
                                WorkFlowDto allo = daoCFO.upadteClaimedApp(workFlowDto, request, roleId, role_name);
                                if (allo.getCurrent_Status().equalsIgnoreCase("success") && !CollectionUtils.isEmpty(listDto.getWorkFlowDtoList())) {
                                    for (WorkFlowDto workFlowDtoList : listDto.getWorkFlowDtoList()) {
                                        workFlowDtoList.setFp_Product_Id(workFlowDtoList.getFp_Product_Id());
                                        workFlowDtoList.setAllot_Quantity(workFlowDtoList.getAllot_Quantity());
                                        workFlowDtoList.setNet_Royalty(workFlowDtoList.getNet_Royalty());
                                        workFlowDtoList.setTotal_Royalty(workFlowDtoList.getTotal_Royalty());
                                        WorkFlowDto updateAllot = daoCFO.updateAllotWP(workFlowDtoList,workFlowDto, request);

                                        ArrayList<String> contactNo=new ArrayList<String>();
                                        contactNo.add(workFlowDto.getPhone_Number());
                                        String[] MobileNo=contactNo.toArray(new String[contactNo.size()]);
                                        String allotrange = daoCFO.getAllotRangeOffice(workFlowDto.getApplication_Number());
                                        workFlowDto.setRange_Officer(allotrange);

                                        //Application approval notification
                                        boolean isSend = CommonNotificationUtils.notifyOnApproval(workFlowDto.getApplication_Number(), MobileNo, workFlowDto.getRange_Officer());
                                    }
                                } else {
                                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                                }
                            }else {
                                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                            }
                        }else {
                            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                        }
                    }else {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                }else {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
            }else {
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            }
        }else {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
        }
        return null;
    }

    @Override
    @Transactional
    public WorkFlowDto upadteClaimedAppPrivateLand(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name=userBean.getCurrentRole().getRoleName();
        String actorID = userBean.getUserID();

        WorkFlowDto updateAppAudit = daoDealing.insertAudit(workFlowDto, request);
            if(updateAppAudit.getCurrent_Status().equalsIgnoreCase("success") ){
                WorkFlowDto updateTdskAudit = daoDealing.insert_task(workFlowDto, request);
                if(updateTdskAudit.getCurrent_Status().equalsIgnoreCase("success")){
                    WorkFlowDto updateWrkAudit = daoDealing.insertWrkFlw(workFlowDto, request);
                    if(updateWrkAudit.getCurrent_Status().equalsIgnoreCase("success")){
                        WorkFlowDto upadteClaimedApp = daoCFO.updateTdsk(workFlowDto, request, roleId, role_name);
                        if(upadteClaimedApp.getCurrent_Status().equalsIgnoreCase("success")){
                            WorkFlowDto upadteWrks = daoCFO.upadteWrks(workFlowDto, request, roleId, role_name, actorID);
                            if(upadteWrks.getCurrent_Status().equalsIgnoreCase("success")) {
                                WorkFlowDto allo = daoCFO.upadteClaimedApp(workFlowDto, request, roleId, role_name);
                                ArrayList<String> contactNo=new ArrayList<String>();
                                contactNo.add(workFlowDto.getPhone_Number());
                                String[] MobileNo=contactNo.toArray(new String[contactNo.size()]);
                                String allotrange = daoCFO.getAllotRangeOffice(workFlowDto.getApplication_Number());
                                workFlowDto.setRange_Officer(allotrange);

                                //Application approval notification
                                boolean isSend = CommonNotificationUtils.notifyOnApproval(workFlowDto.getApplication_Number(), MobileNo, workFlowDto.getRange_Officer());
                            }else {
                                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                            }
                        }else {
                            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                        }
                    }else {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                }else {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
            }else {
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            }
        return null;
    }

}
