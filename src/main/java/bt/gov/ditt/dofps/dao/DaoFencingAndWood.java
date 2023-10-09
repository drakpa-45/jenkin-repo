package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.base.BaseDao;
import bt.gov.ditt.dofps.dto.CommonDto;
import bt.gov.ditt.dofps.dto.NewTimberDto;
import bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.wso2.client.model.G2C_CommonBusinessAPI.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pema Drakpa on 3/29/2020.
 */
@Repository
public class DaoFencingAndWood extends BaseDao implements IDaoFencingAndWood {

    @Override
    public List<NewTimberDto> populateTaskList(HttpServletRequest request, String taskStateId, String app_type) {
        HttpSession session = request.getSession();
        String location_id = "";
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        UserRolePrivilegeDTO dto = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String updatequery = "", priveleges = "";  String wpRangerTaskList = "", getPrivateLandDetls="";
        List<NewTimberDto> workList = new ArrayList<NewTimberDto>();
        try {
           /* if (userRolePriv.getUserRoles() != null) {
                JurisdictionsObj jurisdiction = userRolePriv.getJurisdictions();
                location_id += jurisdiction.getJurisdiction().get(0).getLocationId();
                DeptServicesObj serv = userRolePriv.getUserRoles().getUserRole().get(0).getDeptServices();
                List<DeptServiceObj> ser = serv.getDeptService();
                for (int s = 0; s < ser.size(); s++) {
                    ServicePrivilegesObj priv = ser.get(s).getServicePrivileges();
                    List<ServicePrivilegeObj> servicePrivilege = priv.getServicePrivilege();
                    String servicepriv = "";
                    for (int p = 0; p < servicePrivilege.size(); p++) {
                        if (p == servicePrivilege.size() - 1) {
                            servicepriv = servicepriv + servicePrivilege.get(p).getPrivilegeId();
                        } else {
                            servicepriv = servicepriv + servicePrivilege.get(p).getPrivilegeId() + ",";
                        }
                    }
                    if (s == ser.size() - 1) {
                        priveleges = priveleges + servicepriv;
                    } else {
                        priveleges = priveleges + servicepriv + ",";
                    }
                }
                updatequery = GET_ALL_TASK;
                getPrivateLandDetls = GET_PRIVATE_LAND_DTLS;
                if (dto.getCurrentRole().getRoleName().equalsIgnoreCase("Dealing Officer") && app_type.equalsIgnoreCase("WP")) {
                    if (taskStateId == "5") {
                        updatequery = updatequery + " AND e.Status_Id=1 AND c.Assigned_Priv_Id IN (" + priveleges + ")  AND c.Assigned_User_Id ='" + dto.getUserID() + "' AND a.Division_Park_Id = ? AND a.Application_Type='WP' GROUP BY a.Application_Number";
                    } else {
                        updatequery = updatequery + " AND e.Status_Id=1 AND c.Assigned_Priv_Id IN (" + priveleges + ")  AND a.Division_Park_Id = ? AND a.Application_Type='WP' GROUP BY a.Application_Number";
                    }
                    Query query = sqlQuery(updatequery, NewTimberDto.class).setParameter(1, taskStateId).setParameter(2, location_id);
                    workList = query.list();
                }else if(dto.getCurrentRole().getRoleName().equalsIgnoreCase("Dealing Officer") && app_type.equalsIgnoreCase("PRL")) {
                    if (taskStateId == "5") {
                        updatequery = updatequery + " AND e.Status_Id=11 AND c.Assigned_Priv_Id IN (" + priveleges + ")  AND c.Assigned_User_Id ='" + dto.getUserID() + "'  AND a.Division_Park_Id = ? AND a.Application_Type='PRL' GROUP BY a.Application_Number";
                    } else {
                        updatequery = updatequery + " AND e.Status_Id=11 AND c.Assigned_Priv_Id IN (" + priveleges + ")  AND a.Division_Park_Id = ? AND a.Application_Type='PRL' GROUP BY a.Application_Number";
                    }
                    Query query = sqlQuery(updatequery, NewTimberDto.class).setParameter(1, taskStateId).setParameter(2, location_id);
                    workList = query.list();
                }
                else if ((dto.getCurrentRole().getRoleName().equalsIgnoreCase("CFO/PM")&& app_type.equalsIgnoreCase("WP")) || (dto.getCurrentRole().getRoleName().equalsIgnoreCase("Officiating CFO/PM")&& app_type.equalsIgnoreCase("WP"))) {
                    if (taskStateId == "5") {
                        updatequery = updatequery + " AND e.Status_Id=2 AND c.Assigned_Priv_Id IN (" + priveleges + ")  AND c.Assigned_User_Id ='" + dto.getUserID() + "' AND a.Division_Park_Id = ? AND a.Application_Type='WP' GROUP BY a.Application_Number";
                    } else {
                        updatequery = updatequery + " AND e.Status_Id=2 AND c.Assigned_Priv_Id IN (" + priveleges + ")  AND a.Division_Park_Id = ? AND a.Application_Type='WP' GROUP BY a.Application_Number";
                    }
                    Query query = sqlQuery(updatequery, NewTimberDto.class).setParameter(1, taskStateId).setParameter(2, location_id);
                    workList = query.list();
                }else if ((dto.getCurrentRole().getRoleName().equalsIgnoreCase("CFO/PM")&& app_type.equalsIgnoreCase("PRL")) || (dto.getCurrentRole().getRoleName().equalsIgnoreCase("Officiating CFO/PM")&& app_type.equalsIgnoreCase("PRL"))) {
                    if (taskStateId == "5") {
                        updatequery = updatequery + " AND e.Status_Id=2 AND c.Assigned_Priv_Id IN (" + priveleges + ")  AND c.Assigned_User_Id ='" + dto.getUserID() + "' AND a.Division_Park_Id = ? AND a.Application_Type='PRL' GROUP BY a.Application_Number";
                    } else {
                        updatequery = updatequery + " AND e.Status_Id=2 AND c.Assigned_Priv_Id IN (" + priveleges + ")  AND a.Division_Park_Id = ? AND a.Application_Type='PRL' GROUP BY a.Application_Number";
                    }
                    Query query = sqlQuery(updatequery, NewTimberDto.class).setParameter(1, taskStateId).setParameter(2, location_id);
                    workList = query.list();
                }

                else if ((dto.getCurrentRole().getRoleName().equalsIgnoreCase("Beat Officer")&& app_type.equalsIgnoreCase("WP")) || dto.getCurrentRole().getRoleName().equalsIgnoreCase("Range Officer")&& app_type.equalsIgnoreCase("WP")) {
                    if (taskStateId == "5") {
                        wpRangerTaskList = "SELECT \n" +
                                "a.Application_Number AS application_Number,\n" +
                                "e.Status_Type_Short_Desc AS current_Status,\n" +
                                "d.Service_Id AS service_Id,\n" +
                                "d.Service_Name AS service_Name,\n" +
                                "b.Action_Date AS action_Date,\n" +
                                "e.Status_Id AS stat_Id \n" +
                                "FROM\n" +
                                "t_fp_application a\n" +
                                "LEFT JOIN t_workflow_dtls b ON a.Application_Number = b.Application_Number\n" +
                                "LEFT JOIN t_task_dtls c ON b.Application_Number = c.Application_Number\n" +
                                "LEFT JOIN t_service_lookup d ON d.Service_Id = a.Request_Service_Id\n" +
                                "LEFT JOIN t_status_lookup e ON e.Status_Id = b.Status_Id\n" +
                                "LEFT JOIN t_fp_appl_allotment al ON a.Application_Number = al.Application_Number\n" +
                                "WHERE c.Task_State_Id = ?\n" +
                                "AND (b.Status_Id != 7 OR b.Status_Id != 8) AND e.Status_Id=3 AND c.Assigned_Priv_Id IN ("+ priveleges +")  \n" +
                                "AND c.Assigned_User_Id='"+ dto.getUserID() +"' AND al.Allot_Range_Officer = ? AND a.Application_Type='WP' GROUP BY a.Application_Number";
                    } else {
                        wpRangerTaskList = "SELECT \n" +
                                "a.Application_Number AS application_Number,\n" +
                                "e.Status_Type_Short_Desc AS current_Status,\n" +
                                "d.Service_Id AS service_Id,\n" +
                                "d.Service_Name AS service_Name,\n" +
                                "b.Action_Date AS action_Date,\n" +
                                "e.Status_Id AS stat_Id \n" +
                                "FROM\n" +
                                "t_fp_application a\n" +
                                "LEFT JOIN t_workflow_dtls b ON a.Application_Number = b.Application_Number\n" +
                                "LEFT JOIN t_task_dtls c ON b.Application_Number = c.Application_Number\n" +
                                "LEFT JOIN t_service_lookup d ON d.Service_Id = a.Request_Service_Id\n" +
                                "LEFT JOIN t_status_lookup e ON e.Status_Id = b.Status_Id\n" +
                                "LEFT JOIN t_fp_appl_allotment al ON a.Application_Number = al.Application_Number\n" +
                                "WHERE c.Task_State_Id = ?\n" +
                                "AND (b.Status_Id != 7 OR b.Status_Id != 8) AND e.Status_Id=3 \n" +
                                "AND c.Assigned_Priv_Id IN ("+ priveleges +")  \n" +
                                "AND al.Allot_Range_Officer = ? AND a.Application_Type='WP' GROUP BY a.Application_Number";
                    }
                    Query query = sqlQuery(wpRangerTaskList, NewTimberDto.class).setParameter(1, taskStateId).setParameter(2, location_id);
                    workList = query.list();
                }
                else if ((dto.getCurrentRole().getRoleName().equalsIgnoreCase("Beat Officer")&& app_type.equalsIgnoreCase("PRL")) || dto.getCurrentRole().getRoleName().equalsIgnoreCase("Range Officer")&& app_type.equalsIgnoreCase("PRL")){
                    if (taskStateId == "5") {
                        updatequery = getPrivateLandDetls + " AND (e.Status_Id=1 OR e.Status_Id=3) AND c.Assigned_Priv_Id IN (" + priveleges + ")  AND c.Assigned_User_Id ='" + dto.getUserID() + "' AND a.Allot_Range_Officer = ? AND a.Application_Type='PRL' GROUP BY a.Application_Number";
                    } else {
                        updatequery = getPrivateLandDetls + " AND (e.Status_Id=1 OR e.Status_Id=3) AND c.Assigned_Priv_Id IN (" + priveleges + ")  AND a.Allot_Range_Officer = ? AND a.Application_Type='PRL' GROUP BY a.Application_Number";
                    }
                    Query query = sqlQuery(updatequery, NewTimberDto.class).setParameter(1, taskStateId).setParameter(2, location_id);
                    workList = query.list();
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workList;
    }

    @Override
    public List<CommonDto> getRejList() {
        List<CommonDto> dto = new ArrayList<CommonDto>();
        try {
            String query = "SELECT rm.Rejection_Id AS header_id, rm.Rejection_Reason AS header_Name FROM t_rejection_master rm LEFT JOIN t_fp_application a ON \n" +
                    "a.Application_Type = rm.Application_Type WHERE a.Application_Type =:Apptype GROUP BY rm.Rejection_Reason";
            Query hQuery = sqlQuery(query, CommonDto.class).setParameter("Apptype","WP");
            dto = hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    private String GET_ALL_TASK ="SELECT \n" +
            "a.Application_Number AS application_Number,\n" +
            "e.Status_Type_Short_Desc AS current_Status,\n" +
            "d.Service_Id AS service_Id,\n" +
            "d.Service_Name AS service_Name,\n" +
            "b.Action_Date AS action_Date,\n" +
            "e.Status_Id AS stat_Id \n" +
            "FROM\n" +
            "t_fp_application a\n" +
            "LEFT JOIN t_workflow_dtls b ON a.Application_Number = b.Application_Number\n" +
            "LEFT JOIN t_task_dtls c ON b.Application_Number = c.Application_Number\n" +
            "LEFT JOIN t_service_lookup d ON d.Service_Id = a.Request_Service_Id\n" +
            "LEFT JOIN t_status_lookup e ON e.Status_Id = b.Status_Id\n" +
            "WHERE c.Task_State_Id = ?\n" +
            "AND (b.Status_Id != 7 OR b.Status_Id != 8)";

    private String GET_PRIVATE_LAND_DTLS = "SELECT \n" +
            "a.Application_Number AS application_Number,\n" +
            "e.Status_Type_Short_Desc AS current_Status,\n" +
            "d.Service_Id AS service_Id,\n" +
            "d.Service_Name AS service_Name,\n" +
            "b.Action_Date AS action_Date,\n" +
            "e.Status_Id AS status_Id \n" +
            "FROM\n" +
            "t_fp_application a\n" +
            "LEFT JOIN t_workflow_dtls b ON a.Application_Number = b.Application_Number\n" +
            "LEFT JOIN t_task_dtls c ON b.Application_Number = c.Application_Number\n" +
            "LEFT JOIN t_service_lookup d ON d.Service_Id = a.Request_Service_Id\n" +
            "LEFT JOIN t_status_lookup e ON e.Status_Id = b.Status_Id\n" +
            "WHERE c.Task_State_Id = ?\n" +
            "AND (b.Status_Id != 7 OR b.Status_Id != 8)";
}
