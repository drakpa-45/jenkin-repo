package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.dao.IDaoCFO;
import bt.gov.ditt.dofps.dao.IDaoDealing;
import bt.gov.ditt.dofps.dao.IDaoRange;
import bt.gov.ditt.dofps.dto.ListDto;
import bt.gov.ditt.dofps.dto.PrivateLandDto;
import bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO;
import bt.gov.ditt.dofps.dto.WorkFlowDto;
import bt.gov.ditt.dofps.entitiy.TimberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Pema Drakpa on 2/21/2020.
 */
@Service
public class ServiceRange implements IServiceRange{
    @Autowired
    IDaoRange daoRange;
    @Autowired
    IDaoDealing daoDealing;
    @Autowired
    IDaoCFO daoCFO;

    @Override
    @Transactional
    public WorkFlowDto saveApplicationRange(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String appNo=request.getParameter("value");
        workFlowDto.setApplication_Number(appNo);
        String allocatd = request.getParameter("status");

        WorkFlowDto allotment= new WorkFlowDto();
        try {
            WorkFlowDto updateAppAudit = daoDealing.insertAudit(workFlowDto, request);
            if(updateAppAudit.getCurrent_Status().equalsIgnoreCase("success")) {
                WorkFlowDto updateAllotAudit = daoDealing.insertAlloAudit(workFlowDto, request);
                if (updateAllotAudit.getCurrent_Status().equalsIgnoreCase("success")) {
                    for (WorkFlowDto workFlowDtoList : listDto.getWorkFlowDtoList()) {
                        if(allocatd.equalsIgnoreCase("new")){
                            if(workFlowDtoList.getFp_Product_Id()==5 || workFlowDtoList.getFp_Product_Id()==15){
                                workFlowDtoList.setFp_Product_Id(workFlowDtoList.getFp_Product_Id());
                                workFlowDtoList.setAllot_Quantity(workFlowDtoList.getAllot_Quantity());
                                allotment = daoRange.updateAllotmentTable(workFlowDtoList,workFlowDto, request);
                            }else{
                                SimpleDateFormat date = new SimpleDateFormat("yyyy-mm-dd");
                                String dd = workFlowDto.getDealing_Date();
                                Date dealing_date = date.parse(dd);
                                workFlowDtoList.setFp_Product_Id(workFlowDtoList.getFp_Product_Id());
                                workFlowDtoList.setAllot_Quantity(workFlowDtoList.getAllot_Quantity());
                                workFlowDtoList.setNet_Royalty((workFlowDtoList.getNet_Royalty()-10));
                                allotment = daoRange.updateAllotmentQty(workFlowDtoList, request, dealing_date);
                            }
                        }
                        else{
                            if(workFlowDtoList.getFp_Product_Id()==5 || workFlowDtoList.getFp_Product_Id()==15){
                                workFlowDtoList.setFp_Product_Id(workFlowDtoList.getFp_Product_Id());
                                workFlowDtoList.setAllot_Quantity(workFlowDtoList.getAllot_Quantity());
                                allotment = daoRange.updateAllotmentTable(workFlowDtoList,workFlowDto, request);
                            }else{
                                SimpleDateFormat date = new SimpleDateFormat("yyyy-mm-dd");
                                String dd = workFlowDto.getDealing_Date();
                                Date dealing_date = date.parse(dd);
                                workFlowDtoList.setFp_Product_Id(workFlowDtoList.getFp_Product_Id());
                                workFlowDtoList.setAllot_Quantity(workFlowDtoList.getAllot_Quantity());
                                workFlowDtoList.setNet_Royalty((workFlowDtoList.getNet_Royalty()-10));
                                //allotment = daoRange.updateAllotmentQty(workFlowDtoList, request, dealing_date);
                                allotment = daoRange.updateAllountedQty(workFlowDtoList, request, dealing_date);
                            }
                            //TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                        }
                    }
                    if (request.getParameter("value") != "" && request.getParameter("value") != null && allotment.getCurrent_Status().equalsIgnoreCase("success")) {
                        WorkFlowDto updateWrkAudit = daoDealing.insertWrkFlw(workFlowDto, request);
                        if (updateWrkAudit.getCurrent_Status().equalsIgnoreCase("success")) {
                            WorkFlowDto updateWorkFlowStatus = daoRange.updateWorkFlowStatus(workFlowDto, request, roleId, role_name, actorId);
                            if(updateWorkFlowStatus.getCurrent_Status().equalsIgnoreCase("success")){
                                WorkFlowDto updateTdskAudit = daoDealing.insert_task(workFlowDto, request);
                                if(updateTdskAudit.getCurrent_Status().equalsIgnoreCase("success")){
                                    WorkFlowDto upadteTskDtls = daoRange.updateTdsk(workFlowDto, request, roleId, role_name);
                                }else {
                                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                                }
                            }else {
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
            }else {
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    @Transactional
    public WorkFlowDto saveWPApplicationRange(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String appNo=request.getParameter("value");
        workFlowDto.setApplication_Number(appNo);
        String allocatd = request.getParameter("status");

        WorkFlowDto allotment= new WorkFlowDto();
        try {
            WorkFlowDto updateAppAudit = daoDealing.insertAudit(workFlowDto, request);
            if(updateAppAudit.getCurrent_Status().equalsIgnoreCase("success")) {
                WorkFlowDto updateAllotAudit = daoDealing.insertAlloAudit(workFlowDto, request);
                if (updateAllotAudit.getCurrent_Status().equalsIgnoreCase("success")) {
                    SimpleDateFormat date = new SimpleDateFormat("yyyy-mm-dd");
                    String dd = workFlowDto.getDealing_Date();
                    Date dealing_date = date.parse(dd);
                    for (WorkFlowDto workFlowDtoList : listDto.getWorkFlowDtoList()) {
                        workFlowDtoList.setFp_Product_Id(workFlowDtoList.getFp_Product_Id());
                        workFlowDtoList.setAllot_Quantity(workFlowDtoList.getAllot_Quantity());
                        workFlowDtoList.setAllot_Area(workFlowDto.getAllot_Area());
                        allotment = daoRange.updateWPAllotQty(workFlowDtoList, request, dealing_date);
                    }
                    if (request.getParameter("value") != "" && request.getParameter("value") != null && allotment.getCurrent_Status().equalsIgnoreCase("success")) {
                        WorkFlowDto updateWrkAudit = daoDealing.insertWrkFlw(workFlowDto, request);
                        if (updateWrkAudit.getCurrent_Status().equalsIgnoreCase("success")) {
                            WorkFlowDto updateWorkFlowStatus = daoRange.updateWorkFlowStatus(workFlowDto, request, roleId, role_name, actorId);
                            if(updateWorkFlowStatus.getCurrent_Status().equalsIgnoreCase("success")){
                                WorkFlowDto updateTdskAudit = daoDealing.insert_task(workFlowDto, request);
                                if(updateTdskAudit.getCurrent_Status().equalsIgnoreCase("success")){
                                    WorkFlowDto upadteTskDtls = daoRange.updateTdsk(workFlowDto, request, roleId, role_name);
                                }else {
                                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                                }
                            }else {
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
            }else {
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    @Transactional
    public WorkFlowDto updateClaimedBalanceQty(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String appNo=request.getParameter("value");
        workFlowDto.setApplication_Number(appNo);

        WorkFlowDto allotment= new WorkFlowDto();
        try {
            WorkFlowDto updateAppAudit = daoDealing.insertAudit(workFlowDto, request);
            if(updateAppAudit.getCurrent_Status().equalsIgnoreCase("success")) {
                WorkFlowDto updateAllotAudit = daoDealing.insertAlloAudit(workFlowDto, request);
                if (updateAllotAudit.getCurrent_Status().equalsIgnoreCase("success")) {
                    for (WorkFlowDto workFlowDtoList : listDto.getWorkFlowDtoList()) {
                        workFlowDtoList.setFp_Product_Id(workFlowDtoList.getFp_Product_Id());
                        //workFlowDtoList.setAllot_Quantity(workFlowDtoList.getAllot_Quantity());
                        if(workFlowDtoList.getFp_Product_Id()==5){
                            workFlowDtoList.setFp_Product_Id(workFlowDtoList.getFp_Product_Id());
                            workFlowDtoList.setAllot_Area(workFlowDtoList.getAllot_Area());
                            allotment = daoRange.updateBalAllotmentTable(workFlowDtoList, workFlowDto, request);
                        }else {
                            SimpleDateFormat date = new SimpleDateFormat("yyyy-mm-dd");
                            String dd = workFlowDto.getDealing_Date();
                            Date dealing_date = date.parse(dd);
                            workFlowDtoList.setNet_Royalty((workFlowDtoList.getNet_Royalty()-10));
                            workFlowDtoList.setTotal_Royalty(workFlowDtoList.getNet_Royalty());
                            workFlowDtoList.setAllot_Area(workFlowDtoList.getAllot_Area());
                            workFlowDtoList.setDealing_Date(workFlowDtoList.getDealing_Date());
                            allotment = daoRange.updateBalAllotmentQty(workFlowDtoList, request, dealing_date);
                        }
                    }
                    if (request.getParameter("value") != "" && request.getParameter("value") != null && allotment.getCurrent_Status().equalsIgnoreCase("success")) {
                        WorkFlowDto updateWrkAudit = daoDealing.insertWrkFlw(workFlowDto, request);
                        if (updateWrkAudit.getCurrent_Status().equalsIgnoreCase("success")) {
                            WorkFlowDto updateWorkFlowStatus = daoRange.updateWorkFlowStatus(workFlowDto, request, roleId, role_name, actorId);
                            if(updateWorkFlowStatus.getCurrent_Status().equalsIgnoreCase("success")){
                                WorkFlowDto updateTdskAudit = daoDealing.insert_task(workFlowDto, request);
                                if(updateTdskAudit.getCurrent_Status().equalsIgnoreCase("success")){
                                    WorkFlowDto upadteTskDtls = daoRange.updateTdsk(workFlowDto, request, roleId, role_name);
                                }else {
                                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                                }
                            }else {
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
            }else {
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return workFlowDto;
    }

    @Override
    @Transactional
    public WorkFlowDto saveOtherCons(WorkFlowDto workFlowDto, ListDto listDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String appNo=request.getParameter("value");
        workFlowDto.setApplication_Number(appNo);

        WorkFlowDto allotment= new WorkFlowDto();
        try {
            WorkFlowDto updateAppAudit = daoDealing.insertAudit(workFlowDto, request);
            if(updateAppAudit.getCurrent_Status().equalsIgnoreCase("success")) {
                WorkFlowDto updateAllotAudit = daoDealing.insertAlloAudit(workFlowDto, request);
                if (updateAllotAudit.getCurrent_Status().equalsIgnoreCase("success")) {
                    SimpleDateFormat date = new SimpleDateFormat("yyyy-mm-dd");
                    String dd = workFlowDto.getDealing_Date();
                    Date dealing_date = date.parse(dd);
                    for (WorkFlowDto workFlowDtoList : listDto.getWorkFlowDtoList()) {
                        workFlowDtoList.setFp_Product_Id(workFlowDtoList.getFp_Product_Id());
                        workFlowDtoList.setAllot_Quantity(workFlowDtoList.getAllot_Quantity());
                        workFlowDtoList.setTotal_Royalty(workFlowDtoList.getTotal_Royalty());
                        workFlowDtoList.setNet_Royalty(workFlowDtoList.getNet_Royalty());
                        allotment = daoRange.updateAllotment(workFlowDtoList,workFlowDto, request, dealing_date);
                    }
                    if (request.getParameter("value") != "" && request.getParameter("value") != null && allotment.getCurrent_Status().equalsIgnoreCase("success")) {
                        WorkFlowDto updateWrkAudit = daoDealing.insertWrkFlw(workFlowDto, request);
                        if (updateWrkAudit.getCurrent_Status().equalsIgnoreCase("success")) {
                            WorkFlowDto updateWorkFlowStatus = daoRange.updateWorkFlowStatus(workFlowDto, request, roleId, role_name,actorId);
                            if(updateWorkFlowStatus.getCurrent_Status().equalsIgnoreCase("success")){
                                WorkFlowDto updateTdskAudit = daoDealing.insert_task(workFlowDto, request);
                                if(updateTdskAudit.getCurrent_Status().equalsIgnoreCase("success")){
                                    WorkFlowDto upadteTskDtls = daoRange.updateTdsk(workFlowDto, request, roleId, role_name);
                                    if(upadteTskDtls.getCurrent_Status().equalsIgnoreCase("success")){
                                        WorkFlowDto updatefpApplication = daoRange.updatefpApplication(workFlowDto,request);
                                    }else {
                                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                                    }
                                }else {
                                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                                }
                            }else {
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
            }else {
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    @Transactional
    public WorkFlowDto saveClamApplication(WorkFlowDto workFlow, ListDto listDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        WorkFlowDto dto = daoDealing.insertAudit(workFlow, request);
        if (dto.getCurrent_Status().equalsIgnoreCase("success")) {
            WorkFlowDto fp_app = daoDealing.update_fp_application(workFlow, request, roleId, role_name);
            if (fp_app.getCurrent_Status().equalsIgnoreCase("success")) {
                WorkFlowDto workfolw = daoDealing.insertWrkFlw(workFlow, request);
                if (workfolw.getCurrent_Status().equalsIgnoreCase("success")) {
                    WorkFlowDto wrkflw = daoDealing.updateWrokFlow(workFlow, request, roleId, role_name, actorId);
                    if (wrkflw.getCurrent_Status().equalsIgnoreCase("success")) {
                        WorkFlowDto t_task = daoDealing.insert_task(workFlow, request);
                        if (t_task.getCurrent_Status().equalsIgnoreCase("success")) {
                            WorkFlowDto allo = daoDealing.upadteClaimedApp(workFlow, request, roleId, role_name);
                            if (allo.getCurrent_Status().equalsIgnoreCase("success")) {
                                for (PrivateLandDto privateLandDto : workFlow.getPrivateLandDtos()) {
                                    //String allotrange = daoRange.getAllotRangeOfficePRL(workFlow.getApplication_Number());
                                    TimberEntity timberEntity = new TimberEntity();
                                    if(privateLandDto.getfP_Product_Id()!=null || privateLandDto.getfP_Product_Id()!= 0){
                                        timberEntity.setfP_Product_Id(privateLandDto.getfP_Product_Id());
                                        timberEntity.setNo_trees(privateLandDto.getNo_trees());
                                        timberEntity.setNo_poles(privateLandDto.getNo_poles());
                                        timberEntity.setNo_bamboos(privateLandDto.getNo_bamboos());
                                        timberEntity.setVolume(privateLandDto.getVolume());
                                        timberEntity.setAllot_Range_Officer(workFlow.getService_Name());
                                        timberEntity.setTotal_Royalty(10);
                                        timberEntity.setApplication_Number(request.getParameter("value"));
                                        String allotment = daoDealing.updateAllotmentPvtLand(timberEntity);
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
        return dto;
    }

    @Override
    @Transactional
    public WorkFlowDto saveMarkingDate(WorkFlowDto workFlow, HttpServletRequest request, ListDto listDto) {
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        WorkFlowDto dto = daoDealing.insertAudit(workFlow, request);
        if (dto.getCurrent_Status().equalsIgnoreCase("success")) {
            WorkFlowDto fp_app = daoDealing.update_fp_Application(workFlow, request, roleId, role_name);
            if (fp_app.getCurrent_Status().equalsIgnoreCase("success")) {
                WorkFlowDto t_task = daoDealing.insert_task(workFlow, request);
                if (t_task.getCurrent_Status().equalsIgnoreCase("success")) {
                    WorkFlowDto marked = daoDealing.upadteMarkedDate(workFlow, request, roleId, role_name);
                    if (marked.getCurrent_Status().equalsIgnoreCase("success")) {
                        WorkFlowDto workfolw = daoDealing.insertWrkFlw(workFlow, request);
                        if (workfolw.getCurrent_Status().equalsIgnoreCase("success")) {
                            WorkFlowDto wrkflw = daoDealing.updateWrokFlow_dtls(workFlow, request, roleId, role_name,actorId);
                        } else {
                            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                        }
                    } else {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    }
                }else{
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }
            }else{
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            }
        }else{
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
        }
            return dto;
        }

}