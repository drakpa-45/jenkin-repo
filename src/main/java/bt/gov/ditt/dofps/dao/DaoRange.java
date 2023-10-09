package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.base.BaseDao;
import bt.gov.ditt.dofps.dto.WorkFlowDto;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Pema Drakpa on 2/21/2020.
 */
@Repository
public class DaoRange extends BaseDao implements IDaoRange {

    @Override
    public WorkFlowDto updateAllotmentQty(WorkFlowDto workFlowDto, HttpServletRequest request, Date dealing_date) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        try {
                 String sqlQuery = "UPDATE t_fp_appl_allotment SET Allot_Area = ?,Dealing_Date = ?, Balance_Quantity = Allot_Quantity - ?, `Net_Royalty`= ?, Quantity_Taken= ?, Total_Royalty = ? WHERE Application_Number = ?";
                 Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlowDto.getAllot_Area()).setParameter(2, dealing_date)
                         .setParameter(3, workFlowDto.getBalance_Quantity()).setParameter(4, workFlowDto.getNet_Royalty()).setParameter(5, workFlowDto.getBalance_Quantity()).setParameter(6, (workFlowDto.getNet_Royalty()+10)).setParameter(7,request.getParameter("value"));
            int save = hQuery.executeUpdate();
            if(save>0){
                workFlowDto.setCurrent_Status("success");
            }else{
                workFlowDto.setCurrent_Status("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto updateAllotmentTable(WorkFlowDto workFlowDtoList, WorkFlowDto workFlowDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        try {
            String sqlQuery = "UPDATE t_fp_appl_allotment SET Allot_Area = ?,Dealing_Date = CURRENT_DATE, Balance_Quantity = Allot_Quantity - ?, `Net_Royalty`= ?, Quantity_Taken= ?, Total_Royalty = ? WHERE Application_Number = ?";
            Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, "NRDCL")
                    .setParameter(2, workFlowDto.getAllot_Quantity()).setParameter(3, "10").setParameter(4, workFlowDto.getAllot_Quantity()).setParameter(5, "10")
                    .setParameter(6, request.getParameter("value"));
            int save = hQuery.executeUpdate();
            if(save>0){
                workFlowDto.setCurrent_Status("success");
            }else{
                workFlowDto.setCurrent_Status("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto updateBalAllotmentQty(WorkFlowDto workFlowDto, HttpServletRequest request, Date dealing_date) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        try {
            String sqlQuery = "UPDATE t_fp_appl_allotment SET Allot_Area = ?,Dealing_Date = ?, Balance_Quantity = Balance_Quantity - ?, `Net_Royalty`= ?, Quantity_Taken= ?, Total_Royalty = ? WHERE Application_Number = ?";
            Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlowDto.getAllot_Area()).setParameter(2, dealing_date)
                    .setParameter(3, workFlowDto.getBalance_Quantity()).setParameter(4, workFlowDto.getNet_Royalty()).setParameter(5, workFlowDto.getBalance_Quantity()).setParameter(6, (workFlowDto.getNet_Royalty()+10)).setParameter(7,request.getParameter("value"));
            int save = hQuery.executeUpdate();
            if(save>0){
                workFlowDto.setCurrent_Status("success");
            }else{
                workFlowDto.setCurrent_Status("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto updateBalAllotmentTable(WorkFlowDto workFlowDtoList, WorkFlowDto workFlowDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        try {
            String sqlQuery = "UPDATE t_fp_appl_allotment SET Allot_Area = ?,Dealing_Date = CURRENT_DATE, Balance_Quantity = Balance_Quantity - ?, `Net_Royalty`= ?, Quantity_Taken= ?, Total_Royalty = ? WHERE Application_Number = ?";
            Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, "NRDCL")
                    .setParameter(2, workFlowDto.getBalance_Quantity()).setParameter(3, "10").setParameter(4, workFlowDto.getBalance_Quantity()).setParameter(5, "10").setParameter(6,request.getParameter("value"));
            int save = hQuery.executeUpdate();
            if(save>0){
                workFlowDto.setCurrent_Status("success");
            }else{
                workFlowDto.setCurrent_Status("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto updateWPAllotQty(WorkFlowDto workFlowDto, HttpServletRequest request, Date dealing_date) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        try {
            String sqlQuery = "UPDATE t_fp_appl_allotment SET Allot_Area = ?,Dealing_Date = ?, Balance_Quantity = 0, `Net_Royalty`= ?,Quantity_Taken= Allot_Quantity WHERE Application_Number = ?";
            Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlowDto.getAllot_Area()).setParameter(2, dealing_date)
                    .setParameter(3, workFlowDto.getNet_Royalty()).setParameter(4, request.getParameter("value"));
            int save = hQuery.executeUpdate();
            if(save>0){
                workFlowDto.setCurrent_Status("success");
            }else{
                workFlowDto.setCurrent_Status("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto updateAllountedQty(WorkFlowDto workFlowDto, HttpServletRequest request, Date dealing_date) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        try {
            String sqlQuery = "UPDATE t_fp_appl_allotment SET Allot_Area = ?,Dealing_Date = ?, Balance_Quantity = Appl_Quantity - Allot_Quantity, `Net_Royalty`= Total_Royalty-10, Quantity_Taken= Allot_Quantity WHERE Application_Number = ?";
            Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlowDto.getAllot_Area()).setParameter(2, dealing_date)
                    .setParameter(3, request.getParameter("value"));
            int save = hQuery.executeUpdate();
            if(save>0){
                workFlowDto.setCurrent_Status("success");
            }else{
                workFlowDto.setCurrent_Status("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto updateWorkFlowStatus(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name, String actorId) {
     HttpSession session = request.getSession();
     UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        try {
            String sqlQuery = "UPDATE t_workflow_dtls SET Actor_Name=?,Status_Id=?,Actor_Id=?,Role_Name=?,Role_Id=?,`Action_Date`=CURRENT_DATE WHERE `Application_Number`=?";
           Query hQuery1 = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, userRolePriv.getFullName()).setParameter(2, "8")
                    .setParameter(3, actorId).setParameter(4, role_name).setParameter(5,roleId).setParameter(6, request.getParameter("value"));
            int wkflw =hQuery1.executeUpdate();
            if(wkflw>0) {
                workFlowDto.setCurrent_Status("Success");
            }else{
                workFlowDto.setCurrent_Status("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto updateAllotAudit(WorkFlowDto workFlowDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        List<WorkFlowDto> wrk = new ArrayList<WorkFlowDto>();
        try {
            WorkFlowDto appdto = new WorkFlowDto();
            String appquery = "SELECT a.Balance_Quantity AS balance_Quantity FROM t_fp_appl_allotment a WHERE a.Application_Number=?";
            Query hQuery = hibernateQuery(appquery, WorkFlowDto.class).setParameter(1, request.getParameter("value"));
            appdto = (WorkFlowDto) hQuery.list().get(0);

            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null && request.getParameter("value")!=null) {
                String sqlQuery = "UPDATE t_fp_appl_allot_audit SET Balance_Quantity=? WHERE `Application_Number`=?";
                Query hQuery1 = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, appdto.getBalance_Quantity())
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
    public WorkFlowDto updateTdsk(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_task_dtls SET `Assigned_User_Id`=?,`Task_State_Id`=?,`Action_Date`=CURRENT_DATE WHERE `Application_Number`=?";
                Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, userRolePriv.getUserType()).setParameter(2, "6")
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
    public WorkFlowDto updateAllotment(WorkFlowDto workFlowDtoList, WorkFlowDto workFlowDto, HttpServletRequest request, Date dealing_date) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        try {
            String sqlQuery = "UPDATE \n" +
                    "  t_fp_appl_allotment \n" +
                    "SET\n" +
                    "  Allot_Area = ?,Total_Royalty=?,Net_Royalty=?,\n" +
                    "  Dealing_Date = ?,Allot_Quantity=?,Quantity_Taken = Allot_Quantity, App_Approval_Date = CURRENT_DATE\n" +
                    "WHERE Application_Number = ? AND FP_Product_Id=?";
            Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlowDto.getAllot_Area()).setParameter(2,workFlowDtoList.getTotal_Royalty())
                    .setParameter(3,workFlowDto.getNet_Royalty()).setParameter(4, dealing_date)
                    .setParameter(5, workFlowDtoList.getAllot_Quantity()).setParameter(6, request.getParameter("value")).setParameter(7,workFlowDtoList.getFp_Product_Id());
            int save = hQuery.executeUpdate();
            if(save>0){
                workFlowDto.setCurrent_Status("success");
            }else{
                workFlowDto.setCurrent_Status("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto updatefpApplication(WorkFlowDto workFlowDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        try {
            String sqlQuery = "UPDATE \n" +
                    "  t_fp_application \n" +
                    "SET\n" +
                    "  App_Approval_Date = CURRENT_DATE\n" +
                    "WHERE Application_Number = ?";
            Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, request.getParameter("value"));
            int save = hQuery.executeUpdate();
            if(save>0){
                workFlowDto.setCurrent_Status("success");
            }else{
                workFlowDto.setCurrent_Status("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    /*@Override
    public String getAllotRangeOfficePRL(String application_number) {
        WorkFlowDto dto = new WorkFlowDto();
        try{
            String sqlQuery = "SELECT a.Allot_Range_Officer AS allot_Range_Officer FROM t_fp_application a WHERE a.Application_Number = ?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, application_number);
            dto = (WorkFlowDto)hQuery.list().get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        Integer range = dto.getAllot_Range_Officer();
        return null;*/
    //}
}