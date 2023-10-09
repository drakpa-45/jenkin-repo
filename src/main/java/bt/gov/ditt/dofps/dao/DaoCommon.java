package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.base.BaseDao;
import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.entitiy.*;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.wso2.client.model.G2C_CommonBusinessAPI.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Pema Drakpa on 1/24/2020.
 */

@Repository
public class DaoCommon extends BaseDao implements IDaoCommon {

    @Override
    public List<NewTimberDto> populateTaskListforSelectedServices(HttpServletRequest request, String taskStateId) {
        HttpSession session = request.getSession();
        String location_id = "";
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        UserRolePrivilegeDTO dto = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        dto.getCurrentRole();
        String updatequery = "", priveleges = ""; String rangeQuery = "";
        List<NewTimberDto> workList = new ArrayList<NewTimberDto>();
        try {
            if(userRolePriv.getUserRoles() != null){
                JurisdictionsObj jurisdiction = userRolePriv.getJurisdictions();
                location_id += jurisdiction.getJurisdiction().get(0).getLocationId();
                DeptServicesObj serv = userRolePriv.getUserRoles().getUserRole().get(0).getDeptServices();
                List<DeptServiceObj> ser = serv.getDeptService();
                for(int s = 0; s < ser.size(); s++){
                    ServicePrivilegesObj priv = ser.get(s).getServicePrivileges();
                    List<ServicePrivilegeObj> servicePrivilege = priv.getServicePrivilege();
                    String servicepriv = "";
                    for(int p = 0; p < servicePrivilege.size(); p++){
                        if(p == servicePrivilege.size() - 1){
                            servicepriv = servicepriv + servicePrivilege.get(p).getPrivilegeId();
                        }else{
                            servicepriv = servicepriv + servicePrivilege.get(p).getPrivilegeId() + ",";
                        }
                    }
                    if(s == ser.size() - 1){
                        priveleges = priveleges + servicepriv;
                    }else{
                        priveleges = priveleges + servicepriv + ",";
                    }
                }

                updatequery = GET_ALL_TASK;
                rangeQuery = GET_ALLOCATED_RANGE;

                if(dto.getCurrentRole().getRoleName().equalsIgnoreCase("Dealing Officer")){
                    if(taskStateId == "5"){
                        updatequery = updatequery + " AND a.Status_Id=1 AND t.Assigned_Priv_Id IN (?) AND (d.Cons_Type='n' OR d.Cons_Type='r') AND t.Assigned_User_Id=?  AND d.Division_Park_Id= ? GROUP BY a.Application_Number";
                       // updatequery = updatequery + " AND a.Status_Id=1 AND t.Assigned_Priv_Id IN (" + priveleges + ") AND (d.Cons_Type='n' OR d.Cons_Type='r') AND t.Assigned_User_Id='" + dto.getUserID() + "'  AND d.Division_Park_Id= ? GROUP BY a.Application_Number";
                    }/*else {
                        updatequery = updatequery + " AND a.Status_Id=1 AND t.Assigned_Priv_Id IN (" + priveleges + ") AND (d.Cons_Type='n' OR d.Cons_Type='r') AND d.Division_Park_Id= ? GROUP BY a.Application_Number";
                    }*/
                    Query query = sqlQuery(updatequery, NewTimberDto.class).setParameter(1, taskStateId).setParameter(2,priveleges).setParameter(3,dto.getUserID()).setParameter(3, location_id);
                    workList = query.list();
                }
                /*else if (dto.getCurrentRole().getRoleName().equalsIgnoreCase("Beat Officer") || dto.getCurrentRole().getRoleName().equalsIgnoreCase("Range Officer")) {
                    if (taskStateId == "5") {
                        updatequery = rangeQuery + "  AND ((a.Status_Id=3 && (d.Cons_Type='n' OR d.Cons_Type='r'))  OR (a.Status_Id=1 && d.Cons_Type='o')) AND t.Assigned_Priv_Id IN (" + priveleges + ") AND t.Assigned_User_Id='" + dto.getUserID() + "' AND r.Range_Id= ? GROUP BY a.Application_Number";
                    } else {
                        updatequery = rangeQuery + " AND ((a.Status_Id=3 && (d.Cons_Type='n' OR d.Cons_Type='r'))  OR (a.Status_Id=1 && d.Cons_Type='o')) AND t.Assigned_Priv_Id IN (" + priveleges + ") AND r.Range_Id= ? GROUP BY a.Application_Number";
                    }
                    Query query = sqlQuery(updatequery, NewTimberDto.class).setParameter(1, taskStateId).setParameter(2, location_id);
                    workList = query.list();
                } */else if (dto.getCurrentRole().getRoleName().equalsIgnoreCase("Gup")){
                    if(taskStateId == "5"){
                        //   updatequery = updatequery + "  AND a.Status_Id=13 AND (d.Cons_Type='n' OR d.Cons_Type='r') AND t.Assigned_Priv_Id IN (" + priveleges + ") AND t.Assigned_User_Id='" + dto.getUserID() + "' AND d.Geog_No =? GROUP BY a.Application_Number";
                       // updatequery = updatequery + "  AND (a.Status_Id=13 OR a.Status_Id=15) AND (d.Cons_Type='n' OR d.Cons_Type='r' OR d.Cons_Type='o') AND t.Assigned_Priv_Id IN (" + priveleges + ") AND d.Geog_No = ? GROUP BY a.Application_Number";
                        updatequery = updatequery + "  AND (a.Status_Id=13 OR a.Status_Id=15) AND t.Assigned_Priv_Id IN (?) AND d.Geog_No = ? GROUP BY a.Application_Number";
                    }else{
                       // updatequery = updatequery + " AND (a.Status_Id=13 OR a.Status_Id=15) AND (d.Cons_Type='n' OR d.Cons_Type='r') AND t.Assigned_Priv_Id IN (" + priveleges + ") AND  d.Village_Serial_No IN (SELECT Village_Serial_No FROM t_village_lookup WHERE Gewog_Serial_No= ?)  GROUP BY a.Application_Number ";
                        updatequery = updatequery + " AND (a.Status_Id=13 OR a.Status_Id=15) AND (d.Cons_Type='n' OR d.Cons_Type='r') AND t.Assigned_Priv_Id IN (?) AND  d.Village_Serial_No IN (SELECT Village_Serial_No FROM t_village_lookup WHERE Gewog_Serial_No= ?)  GROUP BY a.Application_Number ";
                    }
                    Query query = sqlQuery(updatequery, NewTimberDto.class).setParameter(1, taskStateId).setParameter(2,priveleges).setParameter(3, location_id);
                    workList = query.list();
                }else if(dto.getCurrentRole().getRoleName().equalsIgnoreCase("Gewog")){
                    if(taskStateId == "5"){
                     //   updatequery = updatequery + "  AND a.Status_Id=13 AND (d.Cons_Type='n' OR d.Cons_Type='r') AND t.Assigned_Priv_Id IN (" + priveleges + ") AND t.Assigned_User_Id='" + dto.getUserID() + "' AND d.Geog_No =? GROUP BY a.Application_Number";
                        //updatequery = updatequery + "  AND a.Status_Id=14 AND (d.Cons_Type='n' OR d.Cons_Type='r' OR d.Cons_Type='o') AND t.Assigned_Priv_Id IN (" + priveleges + ") AND d.Geog_No = ? GROUP BY a.Application_Number";
                        updatequery = updatequery + "  AND a.Status_Id=14 AND t.Assigned_Priv_Id IN (?) AND d.Geog_No = ? GROUP BY a.Application_Number";
                    }else{
                       // updatequery = updatequery + " AND a.Status_Id=14 AND (d.Cons_Type='n' OR d.Cons_Type='r') AND t.Assigned_Pri     v_Id IN (" + priveleges + ") AND  d.Village_Serial_No IN (SELECT Village_Serial_No FROM t_village_lookup WHERE Gewog_Serial_No= ?)  GROUP BY a.Application_Number ";
                        updatequery = updatequery + " AND a.Status_Id=14 AND (d.Cons_Type='n' OR d.Cons_Type='r') AND t.Assigned_Pri     v_Id IN (?) AND  d.Village_Serial_No IN (SELECT Village_Serial_No FROM t_village_lookup WHERE Gewog_Serial_No= ?)  GROUP BY a.Application_Number ";
                    }
                    Query query = sqlQuery(updatequery, NewTimberDto.class).setParameter(1, taskStateId).setParameter(2,priveleges).setParameter(3, location_id);
                    workList = query.list();
                } else if (dto.getCurrentRole().getRoleName().equalsIgnoreCase("CFO/PM") || dto.getCurrentRole().getRoleName().equalsIgnoreCase("Officiating CFO/PM")) {
                    if(taskStateId == "5"){
                        // updatequery = updatequery + "  AND a.Status_Id=2 AND (d.Cons_Type='n' OR d.Cons_Type='r') AND t.Assigned_Priv_Id IN (" + priveleges + ") AND t.Assigned_User_Id='" + dto.getUserID() + "' AND d.Division_Park_Id= ? GROUP BY a.Application_Number";
                       // updatequery = updatequery + "  AND a.Status_Id IN (16,20,21) AND (d.Cons_Type='n' OR d.Cons_Type='r' OR d.Cons_Type='o') AND t.Assigned_Priv_Id IN (" + priveleges + ") AND d.Division_Park_Id= ? GROUP BY a.Application_Number";
                        updatequery = updatequery + "  AND a.Status_Id IN (16,20,21,24) AND t.Assigned_Priv_Id IN (?) AND d.Division_Park_Id= ? GROUP BY a.Application_Number";
                    }else{
                     //   updatequery = updatequery + " AND a.Status_Id IN (16,20,21) AND (d.Cons_Type='n' OR d.Cons_Type='r' OR d.Cons_Type='o') AND t.Assigned_Priv_Id IN (" + priveleges + ") AND d.Division_Park_Id= ? GROUP BY a.Application_Number";
                        updatequery = updatequery + " AND a.Status_Id IN (16,20,21,24) AND (d.Cons_Type='n' OR d.Cons_Type='r' OR d.Cons_Type='o') AND t.Assigned_Priv_Id IN (?) AND d.Division_Park_Id= ? GROUP BY a.Application_Number";
                    }
                    Query query = sqlQuery(updatequery, NewTimberDto.class).setParameter(1, taskStateId).setParameter(2,priveleges).setParameter(3, location_id);
                    workList = query.list();
                }else if (dto.getCurrentRole().getRoleName().equalsIgnoreCase("Range Officer")) {
                    if (taskStateId == "5"){
                       // updatequery = rangeQuery + "  AND ((a.Status_Id IN(2,17,19,22,23) && (d.Cons_Type=   'n' OR d.Cons_Type='r'  OR d.Cons_Type='o'))) AND t.Assigned_Priv_Id IN (" + priveleges + ") AND r.Range_Id= ? GROUP BY a.Application_Number";
                        updatequery = rangeQuery + "  AND ((a.Status_Id IN(2,17,19,22,23,25))) AND t.Assigned_Priv_Id IN (?) AND r.Range_Id= ? GROUP BY a.Application_Number";
                      }else{
                        updatequery = rangeQuery + " AND ((a.Status_Id IN(2,17,19,22,23,25) && (d.Cons_Type='n' OR d.Cons_Type='r'))  OR (a.Status_Id=1 && d.Cons_Type='o')) AND t.Assigned_Priv_Id IN (?) AND r.Range_Id= ? GROUP BY a.Application_Number";
                    }
                    Query query = sqlQuery(updatequery, NewTimberDto.class).setParameter(1, taskStateId).setParameter(2,priveleges).setParameter(3, location_id);
                    workList = query.list();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return workList;
    }

    @Override
    public NewTimberDto updateApplicationToClaimed(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String serviceSelected = request.getParameter("serviceId"), location_id = "", services = "";
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        UserRolePrivilegeDTO dto = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        NewTimberDto workList = new NewTimberDto();
        try {
            if(userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null && request.getParameter("action_type").equalsIgnoreCase("open")) {
                String sqlQuery = "UPDATE t_task_dtls SET `Assigned_User_Id`=?,`Task_State_Id`=?,`Action_Date`=CURRENT_DATE WHERE `Application_Number`=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class).setParameter(1, dto.getUserID()).setParameter(2, "5")
                        .setParameter(3, request.getParameter("Application_Number"));
                hQuery.executeUpdate();
                workList.setCurrent_Status("Success");
            } else if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null && request.getParameter("action_type").equalsIgnoreCase("release")) {
                String sqlQuery = "UPDATE t_task_dtls SET `Assigned_User_Id`=?,`Task_State_Id`=?,`Action_Date`=CURRENT_DATE WHERE `Application_Number`=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class).setParameter(1, dto.getUserID()).setParameter(2, "4")
                        .setParameter(3, request.getParameter("Application_Number"));
                hQuery.executeUpdate();
                workList.setCurrent_Status("Success");
            } else {
                workList.setCurrent_Status("Session Out");
            }
        } catch(Exception e) {
            e.printStackTrace();
            workList.setCurrent_Status("Exception: " + e);
        }
        return workList;
    }

    @Override
    public long getApplicationSequenceNo(long service_id) {
        long seqNo = 0;
        try {
            String sql = "SELECT Last_Application_Number FROM t_application_sequence WHERE Service_Id =:type";
            Query query = sqlQuery1(sql).setParameter("type", service_id);
            if(query.list().size() > 0)
                seqNo = (Integer) query.list().get(0);
            seqNo++;
            if(seqNo <= 1) {
                Query query1 = sqlQuery1("INSERT INTO t_application_sequence(Service_Id, Last_Application_Number) VALUES (:valor1,:valor2)").setParameter("valor1", seqNo).setParameter("valor2", service_id);
                query1.executeUpdate();
            }else {
                Query query1 = sqlQuery1("UPDATE t_application_sequence SET Last_Application_Number =? WHERE Service_Id =?");
                query1.setParameter(1, seqNo);
                query1.setParameter(2, service_id);
                query1.executeUpdate();
            }
        }catch (Exception e) {
            System.out.print("Exception in CommonDaoImpl # getApplicationSequenceNo: " + e);
        }
        return seqNo;
    }

    @Override
    public String AppNumberFormater(long applicationSequenceNo, long service_id) {
        int digitCount1 = String.valueOf(service_id).length();
        int digitCount2 = String.valueOf(applicationSequenceNo).length();
        StringBuffer sbf = new StringBuffer();
        for (int i = digitCount1; i < 3; i++) sbf.append("0");
        sbf.append(service_id);
        sbf.append("-");
        for (int i = digitCount2; i < 7; i++)
            sbf.append("0");
        sbf.append(applicationSequenceNo);
        return sbf.toString();
    }

    @Override
    public long getServiceId(String service_short_desc) {
        long Service_Id = 0;
        try {
            String sql = "SELECT Service_Id FROM t_service_lookup WHERE Service_Short_Desc = ?";
            Query query = sqlQuery1(sql).setParameter(1,service_short_desc);
            if (query.list().size() > 0) {
                Service_Id = (Integer) query.list().get(0);
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
        return Service_Id;
    }

    @Override
    public int getStatus_Id(String submitted) {
        int Status_Id = 0;
        try {
            String sqlQuery = "SELECT Status_Id FROM t_status_lookup sl WHERE sl.Status_Type_Short_Desc = ?";
            Query query = sqlQuery1(sqlQuery).setParameter(1, submitted);
            if (query.list().size() > 0)
                Status_Id = (Integer) query.list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Status_Id;
    }

    @Override
    public int getpriId(int service_id, String role) {
        int privId = 0;
        String sql = "SELECT a.Priv_Id FROM t_privilege_lookup a WHERE a.Service_Id=:param1 AND a.Priv_Short_Desc=:param2";
        Query query = sqlQuery1(sql).setParameter("param1", service_id).setParameter("param2", role);
        if (query.list().size() > 0)
            privId = (Integer) query.list().get(0);

        return privId;
    }

    @Override
    public TaskDetailEntity saveWorkFlow(TaskDetailEntity taskDetailEntity) {
        try {
            saveOrUpdate(taskDetailEntity);
            taskDetailEntity.setTask_Remark("Success");
        } catch (Exception e) {
            System.out.print("Exception  # saveApplicationDetails: " + e);
            taskDetailEntity.setTask_Remark("Exception# saveApplicationDetails: " + e);
        }
        return taskDetailEntity;
    }

    @Override
    public List<CommonDto> getProductListOnline(String cons_desc, String storey, String cons_type) {
        List<CommonDto> dropDownList = new ArrayList<CommonDto>();
        try {
            if (cons_type.equalsIgnoreCase("n")) {
                String sqlQuery = "SELECT FP_Product_Id AS header_id,Product_Catagory AS header_Name, Storey AS storey, Max_limit AS maxLimit,UOM product_desc\n" +
                        "FROM t_fp_product_master WHERE Product_Desc =? AND Construction_Type =? AND Storey =?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1, cons_desc).setParameter(2, cons_type).setParameter(3, storey) ;
                dropDownList = hQuery.list();
            }
            else if (cons_type.equalsIgnoreCase("r")) {
                String sqlQuery = "SELECT FP_Product_Id AS header_id,Product_Catagory AS header_Name,Max_limit AS maxLimit,UOM product_desc\n" +
                        "FROM t_fp_product_master WHERE Product_Desc =? AND Construction_Type =?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1, cons_desc).setParameter(2, cons_type) ;
                dropDownList = hQuery.list();
            }
            else{
                String sqlQuery = "SELECT FP_Product_Id AS header_id,Product_Catagory AS header_Name,UOM product_desc FROM t_fp_product_master a WHERE a.Product_Desc =? AND a.Construction_Type =?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1, cons_desc).setParameter(2, cons_type);
                dropDownList = hQuery.list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dropDownList;
    }

    @Override
    public List<CommonDto> productDetailsList(String cons_desc, String cons_type, String product_id) {
        List<CommonDto> dropDownList = new ArrayList<CommonDto>();
        try {
                String sqlQuery = "SELECT Max_limit AS maxLimit,UOM product_desc FROM t_fp_product_master WHERE Product_Desc =? AND Construction_Type =? AND FP_Product_Id=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1, cons_desc).setParameter(2, cons_type).setParameter(3, product_id);
                dropDownList = hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dropDownList;
    }

    @Override
    public List<AppHistoryDTO> getViewStatusApplication(String applicationNumber) {
        String latestAppNo="";
        List<AppHistoryDTO> appHistoryDTOList = new ArrayList<>();
        String sqlQuery = " SELECT a.Application_Number FROM t_fp_application a WHERE (a.Application_Number=? OR a.CID_Number=?) ORDER BY a.CID_Number DESC LIMIT 1";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter(1, applicationNumber).setParameter(2, applicationNumber);
        latestAppNo= (String) hQuery.list().get(0);

        String sqlQuery1 = "SELECT a.Application_Number application_Number,c.Status_Name AS statusName, DATE_FORMAT(a.Action_Date,'%d-%m-%Y %H:%i:%s' ) AS actionDate, a.Role_Name AS roleName, CONCAT(a.Actor_Name,'','(',a.Role_Name,')') AS actorName,a.Service_Id AS serviceId,(CASE WHEN a.Remarks IS NULL THEN '' ELSE a.Remarks END) remarks FROM t_workflow_dtls a, t_status_lookup c WHERE a.Application_Number=? AND a.Status_Id=c.Status_Id UNION SELECT b.Application_Number application_Number,d.Status_Name AS statusName, DATE_FORMAT(b.Action_Date,'%d-%m-%Y %H:%i:%s') AS actionDate, b.Role_Name AS roleName, CONCAT(b.Actor_Name,'','(',b.Role_Name,')') AS actorName,b.Service_Id AS serviceId,(CASE WHEN b.Remarks IS NULL THEN '' ELSE b.Remarks END) remarks FROM t_workflow_dtls_audit b, t_status_lookup d WHERE b.Application_Number=? AND b.Status_Id=d.Status_Id ORDER BY actionDate ASC";
        org.hibernate.Query hQuery1 = hibernateQuery(sqlQuery1, AppHistoryDTO.class).setParameter(1, latestAppNo).setParameter(2, latestAppNo);
        appHistoryDTOList =hQuery1.list();

        return appHistoryDTOList;
    }

    @Override
    public String insertToTaskAudit(String appNo) {
        try {
            String sqlQuery = "INSERT INTO t_task_dtls_audit ( Task_Id, Instance_Id, Seq_Details_Id, Application_Number, Assigned_User_Id, Assigned_Priv_Id, Task_State_Id, Task_Remark, Action_Date ) SELECT Task_Id, Instance_Id, Seq_Details_Id, Application_Number, Assigned_User_Id, Assigned_Priv_Id, Task_State_Id, Task_Remark, Action_Date FROM t_task_dtls t WHERE t.Application_Number=?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter(1, appNo);
            int svtladt = hQuery.executeUpdate();
            if (svtladt > 0) {
                return "Success";
            } else {
                return "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @Override
    public String insertToWorkFlowAudit(String appNo) {
        try {
            String sqlQuery = "INSERT INTO t_workflow_dtls_audit ( Instance_Id, Application_Number, Status_Id, Service_Id, Action_Date, Actor_Id, Actor_Name, Role_Id, Role_Name, Remarks ) SELECT Instance_Id, Application_Number, Status_Id, Service_Id, Action_Date, Actor_Id, Actor_Name, Role_Id, Role_Name, Remarks FROM t_workflow_dtls w WHERE w.Application_Number=?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter(1, appNo);
            int svtladt = hQuery.executeUpdate();
            if (svtladt > 0) {
                return "Success";
            } else {
                return "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @Override
    public String insertTaskdtls(String appNo, String remarks, HttpServletRequest request, String actorId, String assignPrivId, String taskId) {
        int save =0;
        String applicationstatus="";
        String sqlQuery = "UPDATE t_task_dtls SET `Assigned_User_Id`=?,`Task_State_Id`=?,Assigned_Priv_Id =?,Task_Remark=?,`Action_Date`=CURRENT_DATE WHERE `Application_Number`=?";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, actorId).setParameter(2, taskId).setParameter(3,assignPrivId).setParameter(4, remarks)
                .setParameter(5, appNo);
         save = hQuery.executeUpdate();
        if (save > 0) {
            applicationstatus="Success";
        }else{
            applicationstatus="Failed";
        }
        return applicationstatus;
    }

    @Override
    public String insertToWorkFlow(String appNo, String remarks, HttpServletRequest request, String statusId) {
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String actorName=userBean.getFullName();
        int save =0;
        String applicationstatus="";

        String update_wrkflow = "UPDATE t_workflow_dtls SET Status_Id =?, Action_Date = CURRENT_TIMESTAMP, Actor_Id = ?, Actor_Name = ?, Role_Id = ?, Role_Name =?, Remarks = ? WHERE Application_Number = ?";
       Query query1 = (Query) hibernateQuery(update_wrkflow).setParameter(1, statusId).setParameter(2, actorId).setParameter(3, actorName).setParameter(4, roleId).setParameter(5, role_name).setParameter(6, remarks).setParameter(7,appNo);
        save = query1.executeUpdate();
        if (save > 0) {
            applicationstatus="Success";
        }else{
            applicationstatus="Failed";
        }
        return applicationstatus;
    }

    @Override
    public String updateWorkFlowRange(String appNo, String remarks, HttpServletRequest request, String statusId) {
        HttpSession session = request.getSession();
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleId = userBean.getCurrentRole().getRoleId();
        String role_name = userBean.getCurrentRole().getRoleName();
        String actorId = userBean.getUserID();
        String actorName=userBean.getFullName();
        int save =0;
        String applicationstatus="";

        String update_wrkflow = "UPDATE t_workflow_dtls SET  Action_Date = CURRENT_TIMESTAMP, Actor_Id = ?, Actor_Name = ?, Role_Id = ?, Role_Name =?, Remarks = ?,Status_Id=? WHERE Application_Number = ?";
        Query query1 = (Query) hibernateQuery(update_wrkflow).setParameter(1, actorId).setParameter(2, actorName).setParameter(3, roleId).setParameter(4, role_name).setParameter(5, remarks).setParameter(6,statusId).setParameter(7,appNo);
        save = query1.executeUpdate();
        if (save > 0) {
            applicationstatus="Success";
        }else{
            applicationstatus="Failed";
        }
        return applicationstatus;
    }

    @Override
    public String forwardToOtherRange(String appNo, String remarks, int rangeId) {
        int save =0;
        String applicationstatus="";

        String update_wrkflow = "UPDATE t_fp_application SET Allot_Range_Officer=?,Payment_Status=? WHERE Application_Number=?";
        Query query1 = (Query) hibernateQuery(update_wrkflow).setParameter(1, rangeId).setParameter(2,"Unpaid").setParameter(3, appNo);
        save = query1.executeUpdate();
        if (save > 0) {
            applicationstatus="Success";
        }else{
            applicationstatus="Failed";
        }
        return applicationstatus;
    }

    @Override
    public List<NewTimberDto> getAllParkList() {
        List<NewTimberDto> dtoList = new ArrayList<NewTimberDto>();
        try {
            String sqlQuery = "SELECT dpl.Division_Park_Id AS header_id,dpl.Division_Park_Name AS header_Name FROM t_division_park_lookup dpl";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class);
            dtoList = hQuery.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return dtoList;
    }

    @Override
    public String forwardToOtherCFO(String appNo, String remarks, int parkId) {
        int save =0;
        String applicationstatus="";

        String update_wrkflow = "UPDATE t_fp_application SET Division_Park_Id=? WHERE Application_Number=?";
        Query query1 = (Query) hibernateQuery(update_wrkflow).setParameter(1, parkId).setParameter(2, appNo);
        save = query1.executeUpdate();
        if (save > 0) {
            applicationstatus="Success";
        }else{
            applicationstatus="Failed";
        }
        return applicationstatus;
    }

    @Override
    public long getPermitSequenceNo(int serviceId) {
        long seqNo = 0;
        try {
            String sql = "SELECT PermitNumber FROM t_application_sequence WHERE Service_Id =:type";
            Query query = sqlQuery1(sql).setParameter("type", serviceId);
            if (query.list().size() > 0)
                seqNo = (Integer) query.list().get(0);
            seqNo++;
            if (seqNo <= 1) {
                Query query1 = sqlQuery1("INSERT INTO t_application_sequence(Service_Id, PermitNumber) VALUES (:valor1,:valor2)").setParameter("valor1", serviceId).setParameter("valor2", seqNo);
                query1.executeUpdate();
            } else {
                Query query1 = sqlQuery1("UPDATE t_application_sequence SET PermitNumber=? WHERE Service_Id =?");
                query1.setParameter(1, seqNo);
                query1.setParameter(2, serviceId);
                query1.executeUpdate();
            }
        } catch (Exception e) {
            System.out.print("Exception in CommonDaoImpl # getPermitSequenceNo: " + e);
        }
        return seqNo;
    }

    @Override
    public String updateMarkingArea(String appNo, OnlineTimberDTO timberDto) {
        int save =0;
        String applicationstatus="";

        String update_wrkflow = "UPDATE t_fp_application SET Allot_Area=? WHERE Application_Number=?";
        Query query1 = (Query) hibernateQuery(update_wrkflow).setParameter(1,timberDto.getAllot_Area()).setParameter(2, appNo);
        save = query1.executeUpdate();
        if (save > 0) {
            applicationstatus="Success";
        }else{
            applicationstatus="Failed";
        }
        return applicationstatus;
    }


    @Override
    public List<NewTimberDto> getLandCategory() {
        try {
            String sqlQuery = "SELECT Land_Category_Id AS header_id, Land_Category_Name AS header_Name FROM t_land_category_master WHERE Application_type = 'PL'";
            Query hQuery = (Query) hibernateQuery(sqlQuery, NewTimberDto.class);
            return hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<NewTimberDto> getForestProduce() {
        try {
            String sqlQuery = "SELECT a.FP_Product_Id AS header_id, a.Product_Catagory AS header_Name FROM t_fp_product_master a WHERE a.Product_Desc = 'PL'";
            Query hQuery = (Query) hibernateQuery(sqlQuery, NewTimberDto.class);
            return hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<NewTimberDto> getPrivateLandProduceSpecies( char species_Type) {
        try {
            String sqlQuery = "SELECT a.Species_Id AS header_id, a.Species_Name AS header_Name,a.Species_Type status_Id FROM t_species_name a WHERE a.Species_Type =?";
            Query hQuery = (Query) hibernateQuery(sqlQuery, NewTimberDto.class).setParameter(1,species_Type);
            return hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getConsType(String appNo) {
        try {
            String sqlQuery = "SELECT Cons_Type FROM t_fp_application WHERE Application_Number=?";
            Query hQuery = (Query) hibernateQuery(sqlQuery).setParameter(1,appNo);
            return hQuery.list().get(0).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String updateFPA(String appNo, HttpServletRequest request, InspectionDTO inspectionDTO) {
        int save =0;
        String applicationstatus="";

        String update_wrkflow = "UPDATE t_fp_application SET Construction_Site=?,AwayFrom_B_Thromde=?,NatureOfApplicant=?,ProposedConstructionSite=?,HasAvailTimberB4=?,Inspection_Date=CURRENT_DATE WHERE Application_Number=?";
        Query query1 = (Query) hibernateQuery(update_wrkflow).setParameter(1, inspectionDTO.getContructionSite()).setParameter(2, inspectionDTO.getAwayFromThromB()).setParameter(3, inspectionDTO.getNatureOfApplicant())
                .setParameter(4, inspectionDTO.getProposedConstructionSite()).setParameter(5,inspectionDTO.getHasAvailTimberB4()).setParameter(6, appNo);
        save = query1.executeUpdate();
        if (save > 0) {
            applicationstatus="Success";
        }else{
            applicationstatus="Failed";
        }
        return applicationstatus;
    }

    @Override
    public List getDesignation() {
        String sqlQuery = "SELECT Id serviceId, Designation remarks FROM t_inspection_team_designation";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, AppHistoryDTO.class);
        return hQuery.list();
    }

    @Override
    public List getTimberType(String cons_type) {
        String sqlQuery = "SELECT FP_Product_Id serviceId, Product_Catagory remarks FROM t_fp_product_master WHERE Construction_Type=?";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, AppHistoryDTO.class).setParameter(1,cons_type);
        return hQuery.list();
    }

    @Override
    public void saveUpdate(Object object) {
        saveOrUpdate(object);
    }

    @Override
    public List<InspectionTeamEntity> getInspectionTeamMembers(String applicationNumber) {
        String sqlQuery = "SELECT a.InspectionTeam_CID inspectionTeam_CID,a.InspectionTeam_Name inspectionTeam_Name,a.InspectionTeam_ContactNo inspectionTeam_ContactNo,b.Designation designationDesc FROM t_inspection_team_info a LEFT JOIN t_inspection_team_designation b ON a.Designation=b.Id WHERE a.Application_Number=?";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, InspectionTeamEntity.class).setParameter(1,applicationNumber);
        return hQuery.list();
    }

    @Override
    public List<PastRecordDetailEntity> getPastRecordDetails(String applicationNumber) {
        String sqlQuery = "SELECT a.Applicant_CID applicant_CID,b.Product_Catagory timber_Type,a.Quantity_Taken quantity_Taken,a.Year_of_allotment year_of_allotment FROM t_applicant_past_timber_records a LEFT JOIN t_fp_product_master b ON a.Timber_Type=b.FP_Product_Id WHERE a.Application_Number=?";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, PastRecordDetailEntity.class).setParameter(1,applicationNumber);
        return hQuery.list();
    }

    @Override
    public BigInteger checkForValidation(String house_hold, String cons_type) {
        String sqlQuery="";
        try {
            if(cons_type.equalsIgnoreCase("n")){
                sqlQuery = "SELECT COUNT(*) FROM t_fp_application a \n" +
                        "LEFT JOIN t_fp_appl_allotment b ON a.Application_Number = b.Application_Number LEFT JOIN t_workflow_dtls wf \n" +
                        "ON wf.Application_Number = a.Application_Number  WHERE a.House_Hold_No = ? AND \n" +
                        "a.Cons_Type = ? AND a.App_Submission_Date BETWEEN DATE_SUB(CURDATE(), INTERVAL 25 YEAR) AND CURDATE() AND a.Application_Number NOT LIKE 'Draft%' AND wf.Status_Id <> 7";
            }else if(cons_type.equalsIgnoreCase("r")){
                sqlQuery = "SELECT COUNT(*) FROM t_fp_application a \n" +
                        "LEFT JOIN t_fp_appl_allotment b ON a.Application_Number = b.Application_Number LEFT JOIN t_workflow_dtls wf \n" +
                        "ON wf.Application_Number = a.Application_Number  WHERE a.House_Hold_No = ? AND \n" +
                        "(a.Cons_Type = ? OR a.Cons_Type = 'n')AND a.App_Submission_Date BETWEEN DATE_SUB(CURDATE(), INTERVAL 12 YEAR) AND CURDATE() \n" +
                        "AND a.Application_Number NOT LIKE 'Draft%' AND wf.Status_Id <> 7 AND a.Claiming_status <> 'reclaim'";
            }else if(cons_type.equalsIgnoreCase("o")){
                sqlQuery = "SELECT COUNT(*) FROM t_fp_application a \n" +
                        "LEFT JOIN t_fp_appl_allotment b ON a.Application_Number = b.Application_Number LEFT JOIN t_workflow_dtls wf \n" +
                        "ON wf.Application_Number = a.Application_Number  WHERE a.House_Hold_No = ? AND \n" +
                        "a.Cons_Type = ? AND a.App_Submission_Date BETWEEN DATE_SUB(CURDATE(), INTERVAL 5 YEAR) AND CURDATE() AND a.Application_Number NOT LIKE 'Draft%' AND wf.Status_Id <> 7";
            }
            Query hQuery = (Query) hibernateQuery(sqlQuery).setParameter(1, house_hold).setParameter(2, cons_type);
            return (BigInteger) hQuery.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String updateAllot(TimberDetailsDto timberDetails, String appNo, String remarks) {
        String msg="";
        try {
            String sqlQuery = "UPDATE t_fp_appl_allotment SET Allot_Quantity = ?, Net_Royalty = ?, Total_Royalty = ?,App_Approval_Date = CURRENT_DATE, Approval_Remarks=?,Royalty_Rate=?\n" +
                        "WHERE `Application_Number`=? AND FP_Product_Id=?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, timberDetails.getAllot_Quantity()).setParameter(2, timberDetails.getRoyalty_Rate())
                    .setParameter(3, timberDetails.getTotal_Royalty()).setParameter(4, remarks).setParameter(5, timberDetails.getRate())
                    .setParameter(6, appNo).setParameter(7, timberDetails.getfP_Product_Id());
            int save = hQuery.executeUpdate();
            if(save>0){
                msg="Success";
            }else{
                msg="Fail";
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String updatePaymentStatus(String appNo, OnlineTimberDTO timberDto) {
        int save =0;
        String applicationstatus="";

        String update_wrkflow = "UPDATE t_fp_application SET Payment_Status=?,Allot_Range_Officer=?,App_Approval_Date=CURRENT_DATE,Allot_Area=? WHERE Application_Number=?";
        Query query1 = (Query) hibernateQuery(update_wrkflow).setParameter(1, "Unpaid").setParameter(2,timberDto.getAllot_Range_Officer())
                .setParameter(3,timberDto.getAllot_Area()).setParameter(4, appNo);
        save = query1.executeUpdate();
        if (save > 0) {
            applicationstatus="Success";
        }else{
            applicationstatus="Failed";
        }
        return applicationstatus;
    }


    @Override
    public String updatePermitExpiryDate(String appNo, WorkFlowDto dto) {
        int save =0;
        String applicationstatus="";

        String update_wrkflow = "UPDATE t_fp_application SET PermitExpiryDate=? WHERE Application_Number=?";
        Query query1 = (Query) hibernateQuery(update_wrkflow).setParameter(1,dto.getPermitExpiryDate()).setParameter(2, appNo);
        save = query1.executeUpdate();
        if (save > 0) {
            applicationstatus="Success";
        }else{
            applicationstatus="Failed";
        }
        return applicationstatus;
    }

    @Override
    public void saveEntity(Object object) {
        save(object);
    }

    @Override
    public void updateOfflinePayment(String appNo, String permitNumber, Date permitExpiryDate, Date dateOfReceipt) {
        String update_wrkflow = "UPDATE t_fp_application SET Payment_Status=?,Permit_Number=?,PermitExpiryDate=?,Receipt_Date=? WHERE Application_Number=?";
        Query query1 = (Query) hibernateQuery(update_wrkflow).setParameter(1,"Paid").setParameter(2,permitNumber).setParameter(3,permitExpiryDate).setParameter(4,dateOfReceipt).setParameter(5, appNo);
        query1.executeUpdate();
    }

    @Override
    public List<MarkingInformationEntity> getMarkingInformation(String appNo) {
        List<MarkingInformationEntity> dto = new ArrayList<MarkingInformationEntity>();
        try {
            String sqlQuery = "SELECT m.Application_Number application_Number,s.Species_Name species_Name,m.Volume volume,m.Location_of_Timber location_of_Timber FROM t_marking_information m LEFT JOIN t_species_name s ON m.Species_Id=s.Species_Id WHERE m.Application_Number=?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, MarkingInformationEntity.class).setParameter(1, appNo);
            dto = (List<MarkingInformationEntity>) hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public List<CommonDto> getSpeciesName(HttpServletRequest request) {
        List<CommonDto> dto = new ArrayList<CommonDto>();
        try {
            String sqlQuery = "SELECT a.Species_Id AS header_id, a.Species_Name AS header_Name FROM t_species_name a ";
            // org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1,location_id);
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class);
            dto = (List<CommonDto>) hQuery.list();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public List<CosdtmoItmo> getCosdtmoItmoDtls(String appNo) {
        List<CosdtmoItmo> dto = new ArrayList<CosdtmoItmo>();
        try {
            String sqlQuery = "SELECT c.Produce produce,s.Species_Name species_Name,c.Location_of_Timber location_of_Timber,c.LogBlock logBlock,c.Length length,c.Breath breath,c.Girth girth,c.Total_Volume total_Volume,c.Quantity quantity,c.Unit unit FROM t_cosdtmoitmo c LEFT JOIN t_species_name s ON c.Species_Id=s.Species_Id WHERE Application_Number=?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CosdtmoItmo.class).setParameter(1, appNo);
            dto = (List<CosdtmoItmo>) hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public String updatePermitDateOfficerOnDuty(String appNo, String permitValidityDate, String officerOnDuty) {
        int save =0;
        String applicationstatus="";

        String update_wrkflow = "UPDATE t_fp_application SET Permit_Validity_Date=?,Officer_on_Duty=? WHERE Application_Number=?";
        Query query1 = (Query) hibernateQuery(update_wrkflow).setParameter(1,permitValidityDate).setParameter(2,officerOnDuty).setParameter(3, appNo);
        save = query1.executeUpdate();
        if (save > 0) {
            applicationstatus="Success";
        }else{
            applicationstatus="Failed";
        }
        return applicationstatus;
    }

    @Override
    public String insertToAllotmentAudit(String appNo) {
        try {
            String sqlQuery = "INSERT INTO t_fp_appl_allot_audit (FP_Product_Id, Application_Number, Appl_Quantity, Allot_Quantity,Unit, Royalty_Type, Royalty_Rate, Estimated_Value, Royalty_Realised, Parts_Id,\n" +
                    "CID_Number,DTYPE,Geog_No,Village_Serial_No,Net_Royalty,Total_Royalty,Allot_Date,Allot_Area,Allot_Range_Officer,Dealing_Date,App_Approval_Date,App_Rejection_Date,\n" +
                    "Approval_Remarks,No_trees,No_poles,No_bamboos,Volume,Balance_Quantity,Quantity_Taken,Replace_Quantity) \n" +
                    "SELECT FP_Product_Id, Application_Number, Appl_Quantity, Allot_Quantity,Unit, Royalty_Type, Royalty_Rate, Estimated_Value, Royalty_Realised, Parts_Id,\n" +
                    "CID_Number,DTYPE,Geog_No,Village_Serial_No,Net_Royalty,Total_Royalty,Allot_Date,Allot_Area,Allot_Range_Officer,Dealing_Date,App_Approval_Date,App_Rejection_Date,\n" +
                    "Approval_Remarks,No_trees,No_poles,No_bamboos,Volume,Balance_Quantity,Quantity_Taken,Replace_Quantity FROM t_fp_appl_allotment t WHERE t.Application_Number=?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter(1, appNo);
            int svtladt = hQuery.executeUpdate();
            if (svtladt > 0) {
                return "Success";
            } else {
                return "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @Override
    public List<CommonDto> getConstructionStatus() {
        List<CommonDto> dto = new ArrayList<CommonDto>();
        try {
            String sqlQuery = "SELECT a.Id AS header_id, a.Status AS header_Name FROM t_construction_status a ";
            // org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1,location_id);
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class);
            dto = (List<CommonDto>) hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public List<CommonDto> getProductList(String cons_desc, String type, String cons_type) {
        List<CommonDto> dropDownList = new ArrayList<CommonDto>();
        try {
            if (type.equalsIgnoreCase("product_list") && cons_type.equalsIgnoreCase("n")) {
                String sqlQuery = "SELECT FP_Product_Id AS header_id,Product_Catagory AS header_Name FROM t_fp_product_master a WHERE a.Product_Desc =? AND a.Construction_Type =?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1, cons_desc).setParameter(2, cons_type);
                dropDownList = hQuery.list();
            }else if (type.equalsIgnoreCase("product_list") && cons_type.equalsIgnoreCase("r")) {
                String sqlQuery = "SELECT FP_Product_Id AS header_id,Product_Catagory AS header_Name FROM t_fp_product_master a WHERE a.Product_Desc =? AND a.Construction_Type =?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1, cons_desc).setParameter(2, cons_type);
                dropDownList = hQuery.list();
            }else{
                String sqlQuery = "SELECT FP_Product_Id AS header_id,Product_Catagory AS header_Name FROM t_fp_product_master a WHERE a.Product_Desc =? AND a.Construction_Type =?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1, cons_desc).setParameter(2, cons_type);
                dropDownList = hQuery.list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dropDownList;
    }

    @Override
    public List<CommonDto> getproductListWoodAndPole(String cons_type) {
        List<CommonDto> dropDownList = new ArrayList<CommonDto>();
        try{
            String sqlQuery = "SELECT FP_Product_Id AS header_id,Product_Catagory AS header_Name FROM t_fp_product_master a WHERE a.Product_Desc =?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1, cons_type);
            dropDownList = hQuery.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return dropDownList;
    }

    @Override
    public WorkFlowDto getapplicationDetails(HttpServletRequest request, String type, String application_number) {
       // String application_number = request.getParameter("Application_Number");
        HttpSession session = request.getSession();
        String location_id = "";
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        UserRolePrivilegeDTO dto = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String updatequery = "", priveleges = "";
        WorkFlowDto dtoo = new WorkFlowDto();
        try {
            updatequery = GET_APPLICATION_DETAILS;
                Query query = sqlQuery(updatequery, WorkFlowDto.class).setParameter(1, application_number);
            dtoo = (WorkFlowDto) query.list().get(0);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dtoo;
    }

    @Override
    public List<CommonDto> getAttachments(HttpServletRequest request) {
        String application_number = request.getParameter("Application_Number");
        List<CommonDto> dto = new ArrayList<CommonDto>();
        try {
            String sqlQuery = "SELECT d.Document_Type document_Type,d.Document_Name document_Name,d.`Upload_URL` AS upload_URL,d.`UUID` AS uUID FROM t_document d WHERE d.`Application_Number`=?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1, application_number);
            dto = (List<CommonDto>) hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public List<CommonDto> getRange(HttpServletRequest request, Integer divisionParkId) {
        String location_id="";
        UserRolePrivilegeHierarchyObj user = (UserRolePrivilegeHierarchyObj) request.getSession().getAttribute("userdetail");
        JurisdictionsObj jurisdiction = user.getJurisdictions();
        location_id += jurisdiction.getJurisdiction().get(0).getLocationId();
        List<CommonDto> dto = new ArrayList<CommonDto>();
        try {
            String sqlQuery = "SELECT a.Range_Id AS range_Id, a.Range_Name AS range_Name FROM t_range_lookup a WHERE a.Division_Park_Id=?";
           // org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1,location_id);
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1,divisionParkId);
            dto = (List<CommonDto>) hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public List<TimberDetailsDto> getProduct_catagory(String application_number) {
        List<TimberDetailsDto> dto = new ArrayList<TimberDetailsDto>();
        try {
            String sqlQuery = "SELECT \n" +
                    "  a.FP_Product_Id AS fP_Product_Id,a.Unit unit,\n" +
                    "  al.Product_Catagory AS product_Catagory,al.Rate AS rate,\n" +
                    "  a.Appl_Quantity AS appl_Quantity,a.Net_Royalty royalty_Rate,a.Replace_Quantity quantityToReplace,\n" +
                    "  a.Allot_Quantity AS allot_Quantity,a.Total_Royalty total_Royalty \n" +
                    "FROM\n" +
                    "  t_fp_application pm \n" +
                    "  LEFT JOIN t_fp_appl_allotment a \n" +
                    "    ON pm.Application_Number = a.Application_Number \n" +
                    "  LEFT JOIN t_fp_product_master al \n" +
                    "    ON a.FP_Product_Id = al.FP_Product_Id \n" +
                    "WHERE a.Application_Number = ?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, TimberDetailsDto.class).setParameter(1, application_number);
            dto = (List<TimberDetailsDto>) hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public CommonDto getDocumentDetailsByDocId(String uploadDocId) {
        CommonDto dto = new CommonDto();
        try {
            String sqlQuery = "SELECT d.`Document_Name` AS document_Name ,d.Document_Type AS document_Type,d.`Upload_URL` AS upload_URL,d.`UUID` AS uUID FROM t_document d WHERE d.`UUID`=? ";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1, uploadDocId);
            dto = (CommonDto) hQuery.list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public List<WorkFlowDto> fetchCID(WorkFlowDto workFlowDto, HttpServletRequest request) {
        List<WorkFlowDto> getDtls = new ArrayList<WorkFlowDto>();
        try {
            String sqlQuery = "SELECT \n" +
                    "app.CID_Number cid_Number,\n" +
                    "app.Application_Number AS application_Number,\n" +
                    "app.Full_Name AS name,\n" +
                    "sl.Status_Name current_Status,\n" +
                    "CASE \n" +
                    "WHEN app.Cons_Type = 'n' THEN 'New Construction'\n" +
                    "WHEN app.Cons_Type = 'r' THEN 'Renovation'\n" +
                    "WHEN app.Cons_Type = 'o' THEN 'Other Construction'\n" +
                    "WHEN app.Application_Type = 'PRL' THEN 'Private Land Removal'\n" +
                    "WHEN app.Application_Type = 'WP' THEN 'Wood And Poles'\n" +
                    "END AS cons_Type,\n" +
                    "if(wf.Status_Id =7,(SELECT a.Rejection_Reason FROM t_fp_application a WHERE a.Application_Number = app.Application_Number),'Not Rejected') AS rejection_Reason\n" +
                    "FROM\n" +
                    "t_fp_application app\n" +
                    "LEFT JOIN t_workflow_dtls wf ON app.Application_Number = wf.Application_Number\n" +
                    "LEFT JOIN t_status_lookup sl ON wf.Status_Id = sl.Status_Id\n" +
                    "WHERE app.CID_Number =? AND wf.status_Id<>0\n" +
                    "GROUP BY app.Application_Number";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlowDto.getCid_Number());
            getDtls = hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getDtls;
    }

    @Override
    public List<WorkFlowDto> fetchAppStatus(WorkFlowDto workFlowDto, HttpServletRequest request) {
        List<WorkFlowDto> getAppDtls = new ArrayList<WorkFlowDto>();
        try {
            String sqlQuery = "SELECT a.Application_Number AS application_Number, c.Status_Name AS current_Status, a.Action_Date AS action_Date, a.Role_Name AS role_Name, a.Actor_Name AS actor_Name, " +
                    "t.Application_Type AS application_Type FROM t_workflow_dtls a, t_status_lookup c, t_fp_application t WHERE a.Application_Number =? AND a.Status_Id = c.Status_Id AND a.Application_Number = t.Application_Number " +
                    "UNION SELECT b.Application_Number AS application_Number, d.Status_Name AS current_Status, b.Action_Date AS action_Date, b.Role_Name AS role_Name, b.Actor_Name AS actor_Name, ap.Application_Type FROM t_workflow_dtls_audit b, " +
                    "t_status_lookup d, t_fp_application ap WHERE b.Application_Number =? AND b.Status_Id = d.Status_Id AND b.Application_Number = ap.Application_Number";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1,request.getParameter("appNo")).setParameter(2,request.getParameter("appNo"));
            getAppDtls = hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getAppDtls;
    }

    @Override
    public List<WorkFlowDto> getApplicationNoByCID(String CID, String type) {
        List<WorkFlowDto> workFlowDtos = new ArrayList<WorkFlowDto>();
        try{
            String sqlQuery = "SELECT app.Application_Number AS application_Number,app.CID_Number AS cid_Number,\n" +
                    "app.Full_Name AS name,CASE WHEN app.Cons_Type = 'n' THEN 'New Construction'\n" +
                    "WHEN app.Cons_Type = 'r' THEN 'Renovation' END AS cons_Type\n" +
                    "FROM t_fp_application app LEFT JOIN t_workflow_dtls w ON app.Application_Number = w.Application_Number\n" +
                    "WHERE app.CID_Number = ? AND app.Application_Type = ? AND (app.Cons_Type = 'n' OR app.Cons_Type = 'r') AND w.Status_Id <> 7";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1,CID).setParameter(2,type);
            workFlowDtos = hQuery.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return workFlowDtos;
    }

    @Override
    public BigInteger CountDuration(String cid, String type) {
        try{
            String sqlQuery = "SELECT COUNT(*) \n" +
                    "FROM\n" +
                    "t_fp_application a LEFT JOIN t_fp_appl_allotment b \n" +
                    "ON a.Application_Number = b.Application_Number \n" +
                    "LEFT JOIN t_workflow_dtls wf ON wf.Application_Number = a.Application_Number \n" +
                    "WHERE a.CID_Number = ?\n" +
                    "AND (a.Cons_Type = 'n' OR a.Cons_Type = 'r') \n" +
                    "AND b.Dealing_Date BETWEEN DATE_SUB(CURDATE(), INTERVAL 3 YEAR) AND CURDATE() ";
            Query hQuery = (Query) hibernateQuery(sqlQuery).setParameter(1, cid);
            return (BigInteger) hQuery.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<WorkFlowDto> printPRLDetails(WorkFlowDto workFlowDto, String request) {
        List<WorkFlowDto> workFlowDtos = new ArrayList<WorkFlowDto>();
        try{
            String sqlQuery = "SELECT a.CID_Number AS cid_Number,\n" +
                    "a.Full_Name AS name,\n" +
                    "a.Application_Number AS application_Number,\n" +
                    "a.Dealing_Date AS action_Date,\n" +
                    "a.Plot_No AS plot_No,\n" +
                    "l.Land_Category_Name AS allot_Area,\n" +
                    "a.Acres AS acres,\n" +
                    "CASE \n" +
                    "WHEN a.Application_Type = 'PRL' THEN 'Removal of Forest Product From Private Land' \n" +
                    "END AS Cons_Type,\n" +
                    "GROUP_CONCAT(CAST(pm.Product_Catagory AS CHAR CHARACTER SET utf8)) AS app_Qty,\n" +
                    "GROUP_CONCAT(CAST(al.Volume AS CHAR CHARACTER SET utf8)) AS taken_Qty,\n" +
                    "al.No_trees AS no_Trees, al.No_poles AS no_Poles, al.No_bamboos AS no_Bamboos\n" +
                    "FROM t_fp_application a LEFT JOIN t_fp_appl_allotment al ON a.Application_Number = al.Application_Number\n" +
                    "LEFT JOIN t_fp_product_master pm ON al.FP_Product_Id = pm.FP_Product_Id\n" +
                    "LEFT JOIN t_land_category_master l ON a.Land_Category = l.Land_Category_Id\n" +
                    "WHERE a.Application_Number =:appNo";
            Query query = sqlQuery(sqlQuery,WorkFlowDto.class);
            query.setParameter("appNo",request);
            workFlowDtos = query.list();
        }catch(Exception e){
            e.printStackTrace();
        }
        return workFlowDtos;
    }
    @Override
    public List<WorkFlowDto> printDetails(WorkFlowDto workFlowDto, HttpServletRequest request) {
        String application_Number = request.getParameter("application_Number");
     // String QUE = properties.getProperty("CommonDao.printDtls");
        String QUE = "SELECT \n" +
                "fp.CID_Number AS cid_Number,\n" +
                "fp.Full_Name AS name,\n" +
                "fp.Application_Number AS application_Number,\n" +
                "fp.App_Submission_Date AS action_Date,\n" +
                "CASE\n" +
                "WHEN fp.Cons_Type = 'n' THEN 'New Construction' \n" +
                "WHEN fp.Cons_Type = 'r' THEN 'Renovation' \n" +
                "WHEN fp.Cons_Type = 'o' THEN 'Other Rural Construction' \n" +
                "END AS cons_Type,\n" +
                "IF(fp.Cons_Type = 'o',(SELECT a.Net_Royalty),(SELECT a.Total_Royalty))AS balc_amount,\n" +
                "GROUP_CONCAT(CAST(a.Allot_Quantity AS CHAR CHARACTER SET utf8)) AS allocation_qty,\n" +
                "GROUP_CONCAT(CAST( pm.Product_Catagory AS CHAR CHARACTER SET utf8)) AS product_Catagory,\n" +
                "g.Gewog_Name AS gewog_Name,\n" +
                "GROUP_CONCAT(CAST( pm.Rate AS CHAR CHARACTER SET utf8)) AS royalty_Rate,\n" +
                "GROUP_CONCAT(CAST(  a.Appl_Quantity AS CHAR CHARACTER SET utf8)) AS app_Qty,\n" +
                "GROUP_CONCAT(CAST(  a.Quantity_Taken AS CHAR CHARACTER SET utf8)) AS taken_Qty,\n" +
                "sl.Service_Name AS service_Name,a.FP_Product_Id AS fp_Product_Id,\n" +
                "CASE\n" +
                "WHEN fp.Claiming_status='New' THEN 'New Application'\n" +
                "WHEN fp.Claiming_status IS NULL THEN 'New Application'\n" +
                "WHEN fp.Claiming_status='Reclaim' THEN 'Reclaimed Application'\n" +
                "END AS wrStatus \n" +
                "FROM\n" +
                "t_fp_application fp \n" +
                "LEFT JOIN t_fp_appl_allotment a \n" +
                "ON fp.Application_Number = a.Application_Number \n" +
                "LEFT JOIN t_workflow_dtls w \n" +
                "ON w.Application_Number = fp.Application_Number  \n" +
                "LEFT JOIN t_fp_product_master pm \n" +
                "ON a.FP_Product_Id = pm.FP_Product_Id \n" +
                "LEFT JOIN t_village_lookup v \n" +
                "ON v.Village_Serial_No = fp.Village_Serial_No LEFT \n" +
                "JOIN t_gewog_lookup g \n" +
                "ON g.Gewog_Serial_No = v.Gewog_Serial_No \n" +
                "LEFT JOIN t_service_lookup sl \n" +
                "ON sl.Service_Id = w.Service_Id \n" +
                "WHERE fp.Application_Number =:appNo";
        Query query = sqlQuery(QUE,WorkFlowDto.class);
        query.setParameter("appNo",application_Number);
        return query.list();
    }

    private String GET_ALL_TASK ="SELECT a.Application_Number AS application_Number, b.Status_Type_Short_Desc AS current_Status,a.Service_Id AS service_Id,\n" +
            "c.Service_Name AS service_Name, a.Action_Date AS action_Date, a.Status_Id AS status_Id,d.Marking_Date marking_Date,\n" +
            "CASE\n" +
            "WHEN d.Cons_Type='n' THEN 'New Construction'\n" +
            "WHEN d.Cons_Type='r' THEN 'Renovation'\n" +
            "WHEN d.Cons_Type='o' THEN 'Other Rural Construction'\n" +
            "WHEN d.Cons_Type='PRL' THEN 'Removal of Forest produce from Pvt Registered Land'\n" +
            "WHEN d.Cons_Type='WP' THEN 'Permit for Firewood,Flag Poles and Fencing Post'\n" +
            "END AS cons_Type,d.Payment_Status payment_status \n" +
            "FROM t_workflow_dtls a INNER JOIN t_task_dtls t ON a.Application_Number = t.Application_Number\n" +
            "INNER JOIN t_status_lookup b ON b.Status_Id = a.Status_Id INNER JOIN t_fp_application d ON a.Application_Number = d.Application_Number\n" +
            "INNER JOIN t_service_lookup c ON a.Service_Id = c.Service_Id\n" +
            "WHERE t.Task_State_Id = ? AND a.Status_Id !=7 AND a.Status_Id !=8";

    private String GET_ALLOCATED_RANGE = "SELECT a.Application_Number AS application_Number, b.Status_Type_Short_Desc AS current_Status,a.Service_Id AS service_Id,\n" +
            "c.Service_Name AS service_Name, a.Action_Date AS action_Date, a.Status_Id AS status_Id,\n" +
            "CASE\n" +
            "WHEN d.Cons_Type='n' THEN 'New Construction'\n" +
            "WHEN d.Cons_Type='r' THEN 'Renovation'\n" +
            "WHEN d.Cons_Type='o' THEN 'Other Rural Construction'\n" +
            "WHEN d.Cons_Type='PRL' THEN 'Removal of Forest produce from Pvt Registered Land'\n" +
            "WHEN d.Cons_Type='WP' THEN 'Permit for Firewood,Flag Poles and Fencing Post'\n" +
            "END AS cons_Type,d.Payment_Status payment_status,d.Marking_Date marking_Date,d.Name_of_Sawmill name_of_Sawmill,d.LicenseNo licenseNo,d.Location_of_Sawmill location_of_Sawmill,d.Sawing_Rate sawing_Rate\n" +
            "FROM t_workflow_dtls a INNER JOIN t_task_dtls t ON a.Application_Number = t.Application_Number\n" +
            "INNER JOIN t_status_lookup b ON b.Status_Id = a.Status_Id INNER JOIN t_fp_application d ON a.Application_Number = d.Application_Number\n" +
            "INNER JOIN t_service_lookup c ON a.Service_Id = c.Service_Id\n" +
            "INNER JOIN t_range_lookup r ON d.Allot_Range_Officer = r.Range_Id\n" +
            "WHERE t.Task_State_Id = ? AND a.Status_Id !=7 AND a.Status_Id !=8 ";

    private String GET_APPLICATION_DETAILS="SELECT a.Application_Number AS application_Number,b.Allot_Quantity AS allot_Quantity,b.Appl_Quantity AS appl_Quantity,\n" +
            "b.Net_Royalty AS net_Royalty, b.Total_Royalty AS total_Royalty,CAST(b.Allot_Date AS CHAR CHARACTER SET utf8) AS allot_Date,w.Action_Date AS action_Date,a.Allot_Area allot_Area,\n" +
            "r.Range_Name AS range_Officer,a.Dealing_Officer_Remarks AS dealing_Officer_Remarks,di.Division_Park_Name AS division_Park_Name,\n" +
            "a.House_Hold_No AS house_Hold_No,dl.DzongKhag_Name AS dzongkhag_Name,CAST(a.App_Submission_Date AS CHAR CHARACTER SET utf8) AS app_Submission_Date,\n" +
            "v.Village_Name AS village_Name,g.Gewog_Name AS gewog_Name,a.Division_Park_Id AS division_Park_Id,a.Head_of_Gung AS head_of_Gung,\n" +
            "a.Application_Type AS application_Type,a.House_No AS house_No,a.CID_Number AS cid_Number,CAST(a.App_Approval_Date AS CHAR CHARACTER SET utf8) AS app_Approval_Date,\n" +
            "vl.Village_Name AS construction_Location,b.Balance_Quantity AS balance_Quantity,CAST(a.App_Verification_Date AS CHAR CHARACTER SET utf8) AS app_Verification_Date,\n" +
            "a.Member_of_Forest_community AS member_of_Forest_community,a.Register_Geog AS register_Geog,a.Census_Reg_Date AS census_Reg_Date,a.Full_Name AS name, \n" +
            "a.Cons_Approval_No AS cons_Approval_No,pm.Rate AS rate,a.Sawing_Permit_Date sawingPermitDate,a.PermitExpiryDate permitExpiryDate,\n" +
            "d.Mode_of_Swing_Desc AS mode_of_Swing_Desc,a.Roofing_Type AS roofing_Type,a.Approval_Remarks AS approval_Remarks,\n" +
            "a.Phone_Number AS phone_Number,a.Storey_House AS storey_House,w.Status_Id status_Id,a.Name_of_Sawmill name_of_Sawmill,a.LicenseNo licenseNo,a.Location_of_Sawmill location_of_Sawmill,a.Sawing_Rate sawing_Rate,\n" +
            "a.AlternativeNumberRelation AS AlternativeNumberRelation,a.Payment_Status payment_status,a.Marking_Date marking_Date,\n" +
            "CASE\n" +
            "WHEN a.Cons_Type='n' THEN 'New Construction'\n" +
            "WHEN a.Cons_Type='r' THEN 'Renovation'\n" +
            "WHEN a.Cons_Type='o' THEN 'Other Rural Constructions'\n" +
            "END AS cons_Type, a.Thram_No AS thram_No,a.Construction_Site construction_Site,a.AwayFrom_B_Thromde awayFrom_B_Thromde,\n" +
            "a.NatureOfApplicant natureOfApplicant,a.ProposedConstructionSite proposedConstructionSite,a.HasAvailTimberB4 hasAvailTimberB4\n" +
            "FROM t_fp_application a LEFT JOIN t_fp_appl_allotment b ON  a.Application_Number = b.Application_Number LEFT JOIN t_mode_of_swing d \n" +
            "ON a.Mode_of_Swing_Id = d.Mode_of_Swing_Id LEFT JOIN t_village_lookup v ON a.Village_Serial_No = v.Village_Serial_No LEFT JOIN t_fp_product_master pm ON b.FP_Product_Id=pm.`FP_Product_Id`\n" +
            "LEFT JOIN t_village_lookup vl ON vl.Village_Serial_No=a.Cons_Loc_Village_Serial_No LEFT JOIN t_range_lookup r ON r.Range_Id=a.Allot_Range_Officer\n" +
            "LEFT JOIN t_gewog_lookup g ON g.Gewog_Serial_No = v.Gewog_Serial_No LEFT JOIN t_dzongkhag_lookup dl ON dl.Dzongkhag_Serial_No = g.Dzongkhag_Serial_No\n" +
            "LEFT JOIN t_workflow_dtls w ON w.Application_Number = a.Application_Number LEFT JOIN t_document doc ON doc.Application_Number = a.Application_Number LEFT JOIN \n" +
            "t_division_park_lookup di ON a.Division_Park_Id = di.Division_Park_Id WHERE a.Application_Number =?";

    @Override
    public List<CommonDto> getGroupTaskForDealing(HttpServletRequest request, String userID, String location, String userType) {
        List<CommonDto> commonDTO = new ArrayList<CommonDto>();
        String Selectquery = "";

        try {
            Selectquery = SELECT_ALL;
            if(userType.equalsIgnoreCase("Dealing Officer")){
                Selectquery = Selectquery + " AND (w.Status_Id= 1 OR w.Status_Id= 11) AND a.Division_Park_Id = ?";

                org.hibernate.Query hQuery = hibernateQuery(Selectquery, CommonDto.class).setParameter(1,location);
                commonDTO =  hQuery.list();
            }else if(userType.equalsIgnoreCase("CFO") || userType.equalsIgnoreCase("CFO/PM") || userType.equalsIgnoreCase("Officiating CFO/PM")){
                Selectquery = Selectquery + " AND w.Status_Id= '2' AND a.Division_Park_Id=?";

                org.hibernate.Query hQuery = hibernateQuery(Selectquery, CommonDto.class).setParameter(1,location);
                commonDTO =  hQuery.list();
            }else if(userType.equalsIgnoreCase("Beat Officer") || userType.equalsIgnoreCase("Range Officer")){
                Selectquery = "SELECT \n" +
                        "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '181',(IF(t.Task_State_Id = '4',(IF(w.Status_Id ='3',(IF((a.Cons_Type='n' OR a.Cons_Type='r'),1,0)),0)),0)),0)),0),0) AS grouptaskRTPDealing,\n" +
                        "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '181',(IF(t.Task_State_Id = '4',(IF(w.Status_Id ='1',(IF(a.Cons_Type='o',1,0)),0)),0)),0)),0),0) AS groupTaskOtherConsBeating,\n" +
                        "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '182',(IF(t.Task_State_Id = '4',1,0)),0)),0),0) AS grouptaskWPDealing,\n" +
                        "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '184',(IF(t.Task_State_Id = '4',1,0)),0)),0),0) AS grouptaskPRLDealing\n" +
                        "FROM \n" +
                        "t_fp_application a, \n" +
                        "t_workflow_dtls w,\n" +
                        "t_task_dtls t,\n" +
                        "t_fp_appl_allotment al\n" +
                        "WHERE \n" +
                        "a.Application_Number=w.Application_Number \n" +
                        "AND a.Application_Number = t.Application_Number\n" +
                        "AND a.Application_Number = al.Application_Number\n" +
                        "AND al.Allot_Range_Officer = ?";
                org.hibernate.Query hQuery = hibernateQuery(Selectquery, CommonDto.class).setParameter(1,location);
                commonDTO =  hQuery.list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonDTO;
    }

    @Override
    public List<CommonDto> getGroupTaskRangePRL(HttpServletRequest request, String userID, String locationId, String user) {
        List<CommonDto> commonDTO = new ArrayList<CommonDto>();
        String Selectquery = "";
        try{
            if(user.equalsIgnoreCase("Beat Officer") || user.equalsIgnoreCase("Range Officer")){
                Selectquery = "SELECT \n" +
                        "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '181',(IF(t.Task_State_Id = '4',(IF(w.Status_Id ='3',(IF((a.Cons_Type='n' OR a.Cons_Type='r'),1,0)),0)),0)),0)),0),0) AS grouptaskRTPDealing,\n" +
                        "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '181',(IF(t.Task_State_Id = '4',(IF(w.Status_Id ='1',(IF(a.Cons_Type='o',1,0)),0)),0)),0)),0),0) AS groupTaskOtherConsBeating,\n" +
                        "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '182',(IF(t.Task_State_Id = '4',1,0)),0)),0),0) AS grouptaskWPDealing,\n" +
                        "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '184',(IF(t.Task_State_Id = '4',1,0)),0)),0),0) AS grouptaskPRLDealing\n" +
                        "FROM \n" +
                        "t_fp_application a, \n" +
                        "t_workflow_dtls w,\n" +
                        "t_task_dtls t\n" +
                        "WHERE \n" +
                        "a.Application_Number=w.Application_Number \n" +
                        "AND a.Application_Number = t.Application_Number\n" +
                        "AND (a.Application_Number IN(\n" +
                        "SELECT DISTINCT(al.Application_Number) FROM t_fp_appl_allotment al WHERE al.Allot_Range_Officer = ?\n" +
                        ")OR a.Allot_Range_Officer = ?)\n" +
                        "AND (w.Status_Id = 1 OR w.Status_Id = 3)";
                org.hibernate.Query hQuery = hibernateQuery(Selectquery, CommonDto.class).setParameter(1,locationId).setParameter(2,locationId);
                commonDTO =  hQuery.list();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return commonDTO;
    }

    @Override
    public List<CommonDto> getPersonalTaskRangePRL(HttpServletRequest request, String userID, String locationId, String user) {
        List<CommonDto> commonDTO = new ArrayList<CommonDto>();
        String Selectquery = "";
        try{
            if(user.equalsIgnoreCase("Beat Officer") || user.equalsIgnoreCase("Range Officer")){
                Selectquery = "SELECT \n" +
                        "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '181',(IF(t.Task_State_Id = '5',(IF(w.Status_Id ='3',(IF((a.Cons_Type='n' OR a.Cons_Type='r'),1,0)),0)),0)),0)),0),0) AS personaltaskRTPDealing,\n" +
                        "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '181',(IF(t.Task_State_Id = '5',(IF(w.Status_Id ='1',(IF(a.Cons_Type='o',1,0)),0)),0)),0)),0),0) AS personalTaskOtherConsBeating,\n" +
                        "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '182',(IF(t.Task_State_Id = '5',1,0)),0)),0),0) AS personaltaskWPDealing,\n" +
                        "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '184',(IF(t.Task_State_Id = '5',1,0)),0)),0),0) AS personaltaskPRLDealing\n" +
                        "FROM \n" +
                        "t_fp_application a, \n" +
                        "t_workflow_dtls w,\n" +
                        "t_task_dtls t\n" +
                        "WHERE \n" +
                        "a.Application_Number=w.Application_Number \n" +
                        "AND a.Application_Number = t.Application_Number\n" +
                        "AND (a.Application_Number IN (\n" +
                        "SELECT DISTINCT(al.Application_Number) FROM t_fp_appl_allotment al WHERE al.Allot_Range_Officer = ?) OR a.Allot_Range_Officer = ?) \n" +
                        "AND (w.Status_Id = 1 OR w.Status_Id = 3) AND t.Assigned_User_Id = ?";
                org.hibernate.Query hQuery = hibernateQuery(Selectquery, CommonDto.class).setParameter(1,locationId).setParameter(2,locationId).setParameter(3,userID);
                commonDTO =  hQuery.list();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return commonDTO;
    }

    String SELECT_ALL = "SELECT \n" +
            "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '181',(IF(t.Task_State_Id = '4',(IF(a.Cons_Type <> 'o',1,0)),0)),0)),0),0) AS grouptaskRTPDealing,\n" +
            "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '182',(IF(t.Task_State_Id = '4',1,0)),0)),0),0) AS grouptaskWPDealing,\n" +
            "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '184',(IF(t.Task_State_Id = '4',(IF((w.Status_Id = 11 OR w.Status_Id = 2),1,0)),0)),0)),0),0) AS grouptaskPRLDealing\n" +
            "FROM \n" +
            "t_fp_application a, \n" +
            "t_workflow_dtls w,\n" +
            "t_task_dtls t\n" +
            "WHERE \n" +
            "a.Application_Number=w.Application_Number \n" +
            "AND a.Application_Number = t.Application_Number";

    @Override
    public List<CommonDto> getPersonalTaskDealing(HttpServletRequest request, String userID, String user, String locationId) {
        List<CommonDto> commonDTO = new ArrayList<CommonDto>();
        String SelectPersonal = "";
        try {
            SelectPersonal = SELECT_PERSONAL_TASK;
            if(user.equalsIgnoreCase("Dealing Officer")){
                SelectPersonal = SelectPersonal + " AND (w.Status_Id= 1 OR w.Status_Id= 11) AND a.Division_Park_Id = ? ";
                org.hibernate.Query hQuery = hibernateQuery(SelectPersonal, CommonDto.class).setParameter(1,locationId);
                commonDTO =  hQuery.list();
            }else if(user.equalsIgnoreCase("CFO") || user.equalsIgnoreCase("CFO/PM") || user.equalsIgnoreCase("Officiating CFO/PM")){
                SelectPersonal = SelectPersonal + " AND w.Status_Id= '2' AND a.Division_Park_Id =? AND t.Assigned_User_Id = ? ";
                org.hibernate.Query hQuery = hibernateQuery(SelectPersonal, CommonDto.class).setParameter(1,locationId).setParameter(2, userID);
                commonDTO =  hQuery.list();
            }else if(user.equalsIgnoreCase("Beat Officer") || user.equalsIgnoreCase("Range Officer") ){
                SelectPersonal = "SELECT \n" +
                        "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '181',(IF(t.Task_State_Id = '5',(IF(w.Status_Id ='3',(IF((a.Cons_Type='n' OR a.Cons_Type='r'),1,0)),0)),0)),0)),0),0) AS personaltaskRTPDealing,\n" +
                        "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '181',(IF(t.Task_State_Id = '5',(IF(w.Status_Id ='1',(IF(a.Cons_Type='o',1,0)),0)),0)),0)),0),0) AS personalTaskOtherConsBeating,\n" +
                        "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '182',(IF(t.Task_State_Id = '5',1,0)),0)),0),0) AS personaltaskWPDealing,\n" +
                        "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '184',(IF(t.Task_State_Id = '5',1,0)),0)),0),0) AS personaltaskPRLDealing\n" +
                        "FROM \n" +
                        "t_fp_application a, \n" +
                        "t_workflow_dtls w,\n" +
                        "t_task_dtls t,\n" +
                        "t_fp_appl_allotment al\n" +
                        "WHERE \n" +
                        "a.Application_Number=w.Application_Number \n" +
                        "AND a.Application_Number = t.Application_Number\n" +
                        "AND a.Application_Number = al.Application_Number\n" +
                        "AND al.Allot_Range_Officer = ? AND t.Assigned_User_Id = ?";
                org.hibernate.Query hQuery = hibernateQuery(SelectPersonal, CommonDto.class).setParameter(1,locationId).setParameter(2,userID);
                commonDTO =  hQuery.list();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonDTO;
    }

    String SELECT_PERSONAL_TASK = "SELECT \n" +
            "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '181',(IF(t.Task_State_Id = '5',(IF(a.Cons_Type <> 'o',1,0)),0)),0)),0),0) AS personaltaskRTPDealing,\n" +
            "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '182',(IF(t.Task_State_Id = '5',1,0)),0)),0),0) AS personaltaskWPDealing,\n" +
            "FORMAT(IFNULL(SUM(IF(a.Request_Service_Id = '184',(IF(t.Task_State_Id = '5',(IF((w.Status_Id = 11 OR w.Status_Id = 2),1,0)),0)),0)),0),0) AS personaltaskPRLDealing\n" +
            "FROM \n" +
            "t_fp_application a, \n" +
            "t_workflow_dtls w,\n" +
            "t_task_dtls t\n" +
            "WHERE \n" +
            "a.Application_Number=w.Application_Number \n" +
            "AND a.Application_Number = t.Application_Number";

    @Override
    public BigInteger CountExit(WorkFlowDto dto) {
        try{
            String sqlQuery = "SELECT COUNT(*) FROM t_fp_application a\n" +
                    "WHERE a.Application_Number = ?\n" +
                    "AND a.Dealing_Date BETWEEN DATE_SUB(a.Dealing_Date, INTERVAL 2 YEAR) AND CURDATE()";
            Query hQuery = (Query) hibernateQuery(sqlQuery).setParameter(1, dto.getApplication_Number());
            return (BigInteger) hQuery.uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public claimAdditionalTimberDTO getClaimStatus(HttpServletRequest request) {
        String sqlQuery = "";
        String application_number = request.getParameter("Application_Number");
        claimAdditionalTimberDTO claimAdditionalTimberDTO = new claimAdditionalTimberDTO();
        try{
            sqlQuery = "SELECT a.Claiming_status AS claimStatus FROM t_fp_application a WHERE a.Application_Number = ?";
            Query query = sqlQuery(sqlQuery, claimAdditionalTimberDTO.class).setParameter(1, application_number);
            claimAdditionalTimberDTO = (claimAdditionalTimberDTO) query.list().get(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return claimAdditionalTimberDTO;
    }

    @Override
    public claimAdditionalTimberDTO getReclaimApplicationDetails(HttpServletRequest request) {
        String sqlQuery = "";
        String application_number = request.getParameter("Application_Number");
        claimAdditionalTimberDTO claimAdditionalTimberDTO = new claimAdditionalTimberDTO();
        try{
            sqlQuery = GetReclaimApplicationDetails;
            Query query = sqlQuery(sqlQuery, claimAdditionalTimberDTO.class).setParameter(1, application_number);
            claimAdditionalTimberDTO = (claimAdditionalTimberDTO) query.list().get(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return claimAdditionalTimberDTO;
    }

    private String GetReclaimApplicationDetails = "SELECT \n" +
            "a.CID_Number AS cid_Number,a.Application_Number AS application_Number,a.Full_Name AS name, a.Previous_Application_No AS previous_application_Number ,a.GenderType AS genderType, d.Dzongkhag_Name AS dzongkhag_Name,g.Gewog_Name AS gewog_Name,v.Village_Name AS village_Name,\n" +
            "a.House_Hold_No AS house_Hold_No, a.Division_Park_Id AS division_Park_Id,dv.Division_Park_Name AS division_Park_Name,a.House_No AS house_No,a.Head_of_Gung AS head_of_Gung,a.Phone_Number AS phone_Number, a.Village_Serial_No AS village_Serial_No,\n" +
            "a.Cons_Approval_No AS cons_Approval_No, a.Register_Geog AS register_Geog, a.Roofing_Type AS roofing_Type,a.Storey_House AS house_Storey,\n" +
            "vl.Village_Name AS construction_Loc, a.Member_of_Forest_community AS member_of_forest_community,\n" +
            "a.Mode_of_Swing_Id AS mode_Of_Swing_Id, a.Cons_Loc_Village_Serial_No AS cons_Loc_Village_Serial_No,m.Mode_of_Swing_Desc AS mode_of_Swing_Desc,\n" +
            "al.Appl_Quantity AS apply_Quantity, al.Allot_Quantity AS allot_Quantity, al.Quantity_Taken AS quantity_Taken, pm.Product_Catagory AS product_Category,\n" +
            "pm.FP_Product_Id AS FP_product_Id,CAST(a.App_Submission_Date AS CHAR CHARACTER SET utf8) AS app_submission_date,  \n" +
            "CAST(a.App_Verification_Date AS CHAR CHARACTER SET utf8) AS verified_date, CAST(a.App_Approval_Date AS CHAR CHARACTER SET utf8) AS approved_date,\n" +
            "alt.Appl_Quantity AS new_apply_Qty,pm.Rate AS royalty_Rate,\n" +
            "CASE\n" +
            "WHEN pm.Product_Desc = 'RTP(s)' THEN 'Standing Form'\n" +
            "WHEN pm.Product_Desc = 'RTP(l)' THEN 'Log Form'\n" +
            "END AS product_Desc, alt.Allot_Quantity AS dealing_allot_Quantity, alt.Total_Royalty AS total_Royalty,\n" +
            "a.Reclaiming_Timber_Remark_CC AS cc_Reclaim_Remarks,a.Dealing_Officer_Remarks AS dealing_Officer_Remarks,r.Range_Name AS all_range_officer,\n" +
            "alt.Net_Royalty AS net_Royalty\n" +
            "FROM \n" +
            "t_fp_application a \n" +
            "INNER JOIN t_village_lookup v ON a.Village_Serial_No = v.Village_Serial_No\n" +
            "INNER JOIN t_village_lookup vl ON a.Cons_Loc_Village_Serial_No = vl.Village_Serial_No\n" +
            "LEFT JOIN t_division_park_lookup dv ON a.Division_Park_Id = dv.Division_Park_Id\n" +
            "LEFT JOIN t_mode_of_swing m ON a.Mode_of_Swing_Id = m.Mode_of_Swing_Id\n" +
            "LEFT JOIN t_gewog_lookup g ON v.Gewog_Serial_No = g.Gewog_Serial_No\n" +
            "LEFT JOIN t_dzongkhag_lookup d ON g.Dzongkhag_Serial_No = d.Dzongkhag_Serial_No\n" +
            "INNER JOIN t_fp_appl_allotment al ON a.Previous_Application_No = al.Application_Number\n" +
            "INNER JOIN t_fp_appl_allotment alt ON a.Application_Number = alt.Application_Number\n" +
            "LEFT JOIN t_fp_product_master pm ON al.FP_Product_Id = pm.FP_Product_Id\n" +
            "LEFT JOIN t_range_lookup r ON al.Allot_Range_Officer = r.Range_Id\n" +
            "WHERE a.Application_Number = ?\n" +
            "AND a.Cons_Type = 'n'";

    @Override
    public BigInteger getYearValidation(String sl_no, String house_no) {
        String sqlQuery = "", product1 = "", product2 = "";
        try{
            if(sl_no.equalsIgnoreCase("6") || sl_no.equalsIgnoreCase("7")){
                product1 = "6";
                product2 = "7";
                sqlQuery = COUNTERPRODUCTSELECTED;
                Query hQuery = (Query) hibernateQuery(sqlQuery)
                        .setParameter(1, product1)
                        .setParameter(2, product2)
                        .setParameter(3, house_no);
                return (BigInteger) hQuery.uniqueResult();
            }
            if(sl_no.equalsIgnoreCase("188") || sl_no.equalsIgnoreCase("189")){
                product1 = "188";
                product2 = "189";
                sqlQuery = COUNTERPRODUCTSELECTED;
                Query hQuery = (Query) hibernateQuery(sqlQuery)
                        .setParameter(1, product1)
                        .setParameter(2, product2)
                        .setParameter(3, house_no);
                return (BigInteger) hQuery.uniqueResult();
            }
            if(sl_no.equalsIgnoreCase("201") || sl_no.equalsIgnoreCase("202")){
                product1 = "201";
                product2 = "202";
                sqlQuery = COUNTERPRODUCTSELECTED;
                Query hQuery = (Query) hibernateQuery(sqlQuery)
                        .setParameter(1, product1)
                        .setParameter(2, product2)
                        .setParameter(3, house_no);
                return (BigInteger) hQuery.uniqueResult();
            }
            if(sl_no.equalsIgnoreCase("187")){
                product1 = "187";
                sqlQuery = "SELECT \n" +
                        "COUNT(*) AS header_id \n" +
                        "FROM \n" +
                        "t_fp_application app \n" +
                        "LEFT JOIN t_fp_appl_allotment apal \n" +
                        "ON app.Application_Number = apal.Application_Number \n" +
                        "LEFT JOIN t_fp_product_master pm \n" +
                        "ON apal.FP_Product_Id = pm.FP_Product_Id \n" +
                        "LEFT JOIN t_workflow_dtls wf \n" +
                        "ON app.Application_Number = wf.Application_Number \n" +
                        "WHERE \n" +
                        "apal.FP_Product_Id = ?\n" +
                        "AND app.House_Hold_No = ?\n" +
                        "AND wf.Status_Id <>  7\n" +
                        "AND YEAR(app.App_Submission_Date) = YEAR(CURDATE())\n" +
                        "AND app.Application_Number NOT LIKE 'Draft%'";
                Query hQuery = (Query) hibernateQuery(sqlQuery)
                        .setParameter(1, product1)
                        .setParameter(2, house_no);
                return (BigInteger) hQuery.uniqueResult();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String COUNTERPRODUCTSELECTED = "SELECT \n" +
            "COUNT(*) AS header_id \n" +
            "FROM \n" +
            "t_fp_application app \n" +
            "LEFT JOIN t_fp_appl_allotment apal \n" +
            "ON app.Application_Number = apal.Application_Number \n" +
            "LEFT JOIN t_fp_product_master pm \n" +
            "ON apal.FP_Product_Id = pm.FP_Product_Id \n" +
            "LEFT JOIN t_workflow_dtls wf \n" +
            "ON app.Application_Number = wf.Application_Number \n" +
            "WHERE \n" +
            "(apal.FP_Product_Id = ? OR apal.FP_Product_Id = ?)\n" +
            "AND app.House_Hold_No = ?\n" +
            "AND wf.Status_Id <>  7\n" +
            "AND YEAR(app.App_Submission_Date) = YEAR(CURDATE())\n" +
            "AND app.Application_Number NOT LIKE 'Draft%'";

    @Override
    public List<TimberDetailsDto> getPvtProduct_catagory(String appNo) {
        List<TimberDetailsDto> dto = new ArrayList<TimberDetailsDto>();
        try {
            String sqlQuery = "SELECT al.Species_Id AS fP_Product_Id,al.Species_Name AS product_Catagory,a.No_trees AS appl_Quantity,a.No_poles AS allot_Quantity, a.No_bamboos AS estimated_Value,a.Volume AS volume\n" +
                    "FROM t_fp_application pm LEFT JOIN t_fp_appl_allotment a ON pm.Application_Number = a.Application_Number LEFT JOIN t_species_name al ON a.FP_Product_Id = al.Species_Id WHERE a.Application_Number = ?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, TimberDetailsDto.class).setParameter(1, appNo);
            dto = (List<TimberDetailsDto>) hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public List<LandDetailsDto> getLandDetails(String appNo) {
        List<LandDetailsDto> dto = new ArrayList<LandDetailsDto>();
        try {
            String sqlQuery = "SELECT l.Plot_No plot_No,l.Peg_No peg_No,l.Gps_Coordinates gps_Coordinates,lc.Land_Category_Name land_Category_Name,l.Areas areas FROM t_land_details l LEFT JOIN t_land_category_master lc ON l.Land_Category = lc.Land_Category_Id WHERE Application_Number=?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, LandDetailsDto.class).setParameter(1, appNo);
            dto = (List<LandDetailsDto>) hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public String getCCGewog(String cClocationID) {
        CitizenDetailsDTO dto = new CitizenDetailsDTO();
        Integer gID = Integer.parseInt(cClocationID);
        String CCGewog = "";
        try{
            String sqlQuery = "SELECT g.Gewog_Name AS gewog_Name \n" +
                    "FROM t_cc_master c LEFT JOIN t_gewog_lookup g \n" +
                    "ON c.Gewog_Serial_No = g.Gewog_Serial_No\n" +
                    "WHERE c.CC_Id = ?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CitizenDetailsDTO.class).setParameter(1, gID);
            dto = (CitizenDetailsDTO)hQuery.list().get(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        CCGewog = dto.getGewog_Name();
        return CCGewog;
    }
}