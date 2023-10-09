package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.base.BaseDao;
import bt.gov.ditt.dofps.dto.WorkFlowDto;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pema Drakpa on 2/12/2020.
 */
@Repository
public class DaoCFO extends BaseDao implements IDaoCFO {

    @Override
    public WorkFlowDto updateAppAudit(WorkFlowDto workFlowDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
            WorkFlowDto appdto = new WorkFlowDto();

            String appquery = "SELECT a.Allot_Range_Officer AS Allot_Range_Officer FROM t_fp_application a WHERE a.Application_Number=?";
            Query hQuery = hibernateQuery(appquery, WorkFlowDto.class).setParameter(1, request.getParameter("value"));
            appdto = (WorkFlowDto) hQuery.list().get(0);

            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null && request.getParameter("value")!=null) {
                String sqlQuery = "UPDATE t_fp_application_audit SET App_Approval_Date=CURRENT_DATE,Allot_Range_Officer=? WHERE `Application_Number`=?";
                Query hQuery1 = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1,appdto.getAllot_Range_Officer()).setParameter(2, request.getParameter("value"));
                int fpa=hQuery1.executeUpdate();
                if(fpa>0) {
                    workList.setCurrent_Status("Success");
                }else{
                    workList.setCurrent_Status("fail");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workList;
    }


    @Override
    public WorkFlowDto updateAllotAudit(WorkFlowDto workFlowDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        List<WorkFlowDto> wrk = new ArrayList<WorkFlowDto>();
        try {
            WorkFlowDto appdto = new WorkFlowDto();
            String appquery = "SELECT a.Allot_Quantity AS allot_Quantity FROM t_fp_appl_allotment a WHERE a.Application_Number=?";
            Query hQuery = hibernateQuery(appquery, WorkFlowDto.class).setParameter(1, request.getParameter("value"));
            appdto = (WorkFlowDto) hQuery.list().get(0);

            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null && request.getParameter("value")!=null) {
                String sqlQuery = "UPDATE t_fp_appl_allot_audit SET Allot_Quantity=? WHERE `Application_Number`=?";
                Query hQuery1 = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, appdto.getAllot_Quantity())
                        .setParameter(2, request.getParameter("value"));
                int allotUpdts = hQuery1.executeUpdate();
                if(allotUpdts>0){
                    workFlowDto.setCurrent_Status("success");
                }else{
                    workFlowDto.setCurrent_Status("fail");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto updateTdskAudit(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
            List<WorkFlowDto> wrk = new ArrayList<WorkFlowDto>();
            String appquery = "SELECT t.Action_Date AS action_Date,t.Assigned_User_Id AS assigned_User_Id FROM t_task_dtls t WHERE t.Application_Number=?";
            Query hQuery = hibernateQuery(appquery, WorkFlowDto.class).setParameter(1, request.getParameter("value"));
            wrk = hQuery.list();

            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null && request.getParameter("value")!=null) {
                String sqlQuery = "UPDATE t_task_dtls_audit SET `Assigned_User_Id`=?,`Task_State_Id`=?,`Action_Date`=? WHERE `Application_Number`=?";
                Query hQuery1 = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1,wrk.get(0).getAssigned_User_Id()).setParameter(2, "5")
                        .setParameter(3,wrk.get(0).getAction_Date()).setParameter(4, request.getParameter("value"));
                int updateTask=hQuery1.executeUpdate();
                if(updateTask > 0) {
                    workList.setCurrent_Status("Success");
                }else{
                    workList.setCurrent_Status("fail");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workList;
    }

    @Override
    public WorkFlowDto updateWrkAudit(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
            List<WorkFlowDto> wrk = new ArrayList<WorkFlowDto>();
            String appquery = "SELECT \n" +
                    "  w.Action_Date AS action_Date,w.Actor_Id AS actor_Id,\n" +
                    "  w.Actor_Name AS actor_Name, w.Role_Id AS role_Id,\n" +
                    "  w.Role_Name AS role_Name,w.Status_Id AS status_Id\n" +
                    "FROM\n" +
                    "  t_workflow_dtls w\n" +
                    "WHERE w.Application_Number =?";
            Query hQuery = hibernateQuery(appquery, WorkFlowDto.class).setParameter(1, request.getParameter("value"));
            wrk = hQuery.list();

            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null && request.getParameter("value")!=null) {
                String sqlQuery = "UPDATE t_workflow_dtls_audit SET Actor_Name=?,Status_Id=?,Actor_Id=?,Role_Name=?,Role_Id=?,`Action_Date`=? WHERE `Application_Number`=?";
                Query hQuery1 = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1,wrk.get(0).getActor_Name()).setParameter(2,wrk.get(0).getStatus_Id())
                        .setParameter(3,wrk.get(0).getActor_Id()).setParameter(4, wrk.get(0).getRole_Name()).setParameter(5,wrk.get(0).getRole_Id()).setParameter(6,wrk.get(0).getAction_Date()).setParameter(7, request.getParameter("value"));
                int wkflw =hQuery1.executeUpdate();
                if(wkflw>0) {
                    workList.setCurrent_Status("Success");
                }else{
                    workList.setCurrent_Status("fail");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workList;
    }

    @Override
    public WorkFlowDto upadteClaimedApp(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_fp_application SET App_Approval_Date = CURRENT_DATE, Approval_Remarks=? WHERE `Application_Number`=?";
                Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlowDto.getApproval_Remarks()).setParameter(2, request.getParameter("value"));
                int sizee = hQuery.executeUpdate();
                if(sizee>0) {
                    workList.setCurrent_Status("Success");
                }else{
                    workList.setCurrent_Status("fail");
                }
            }
        }catch (Exception e){
            System.out.print("Exception while approving:"+e);
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto updateAllot(WorkFlowDto workFlowDto, WorkFlowDto workFlow, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        List<WorkFlowDto> wrk = new ArrayList<WorkFlowDto>();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_fp_appl_allotment SET Allot_Quantity = ?, Net_Royalty = ?, Total_Royalty = ?,App_Approval_Date = CURRENT_DATE, Approval_Remarks=?\n" +
                        "WHERE `Application_Number`=? AND FP_Product_Id=?";
                Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlow.getAllot_Quantity()).setParameter(2, workFlow.getNet_Royalty()).setParameter(3,workFlow.getTotal_Royalty()).setParameter(4, workFlow.getApproval_Remarks())
                        .setParameter(5, request.getParameter("value")).setParameter(6, workFlowDto.getFp_Product_Id());
                int save = hQuery.executeUpdate();
                if(save>0){
                    workFlowDto.setCurrent_Status("success");
                }else{
                    workFlowDto.setCurrent_Status("fail");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto updateAllotment(WorkFlowDto workFlowDto, WorkFlowDto workFlow, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        List<WorkFlowDto> wrk = new ArrayList<WorkFlowDto>();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_fp_appl_allotment SET Allot_Quantity = ?, Net_Royalty = ?, Total_Royalty = ?,App_Approval_Date = CURRENT_DATE, Approval_Remarks=?\n" +
                        "WHERE `Application_Number`=? AND FP_Product_Id=?";
                Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlow.getAllot_Quantity_log()).setParameter(2, "10").setParameter(3,"10").setParameter(4, workFlow.getApproval_Remarks())
                        .setParameter(5, request.getParameter("value")).setParameter(6, workFlowDto.getFp_Product_Id());
                int save = hQuery.executeUpdate();
                if(save>0){
                    workFlowDto.setCurrent_Status("success");
                }else{
                    workFlowDto.setCurrent_Status("fail");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto updateAllotWP(WorkFlowDto workFlowDto, WorkFlowDto workFlow, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        List<WorkFlowDto> wrk = new ArrayList<WorkFlowDto>();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_fp_appl_allotment SET Allot_Quantity = ?, Net_Royalty = ?, Total_Royalty = ?,App_Approval_Date = CURRENT_DATE, Approval_Remarks=?\n" +
                        "WHERE `Application_Number`=? AND FP_Product_Id=?";
                Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlowDto.getAllot_Quantity()).setParameter(2, workFlowDto.getTotal_Royalty()).setParameter(3,workFlow.getTotal_Royalty()).setParameter(4, workFlow.getApproval_Remarks())
                        .setParameter(5, request.getParameter("value")).setParameter(6, workFlowDto.getFp_Product_Id());
                int save = hQuery.executeUpdate();
                if(save>0){
                    workFlowDto.setCurrent_Status("success");
                }else{
                    workFlowDto.setCurrent_Status("fail");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto upadteWrks(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name, String actorID) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
    if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
        String sqlQuery = "UPDATE t_workflow_dtls SET Actor_Name=?,Status_Id=?,Actor_Id=?,Role_Name=?,Role_Id=?,`Action_Date`=CURRENT_DATE WHERE `Application_Number`=?";
        Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, userRolePriv.getFullName()).setParameter(2, "3")
                .setParameter(3,actorID).setParameter(4,role_name).setParameter(5,roleId).setParameter(6, request.getParameter("value"));
        hQuery.executeUpdate();
        workList.setCurrent_Status("Success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto updateTdsk(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_task_dtls SET `Assigned_User_Id`=?,`Task_State_Id`=?,`Action_Date`=CURRENT_DATE WHERE `Application_Number`=?";
                Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, userRolePriv.getUserType()).setParameter(2, "4")
                        .setParameter(3, request.getParameter("value"));
                int updateTask = hQuery.executeUpdate();
                if (updateTask > 0) {
                    workList.setCurrent_Status("Success");
                } else {
                    workList.setCurrent_Status("fail");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workList;
    }

    @Override
    public String getAllotRangeOffice(String application_number) {
        WorkFlowDto dto = new WorkFlowDto();
        String allotrange ="";
        try{
            String sqlQuery = "SELECT r.Range_Name AS range_Officer FROM t_fp_appl_allotment a LEFT JOIN t_range_lookup r ON a.Allot_Range_Officer = r.Range_Id\n" +
                    "WHERE a.Application_Number = ?";
            Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, application_number);
            dto = (WorkFlowDto)hQuery.list().get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        allotrange=dto.getRange_Officer();
        return allotrange;
    }
}