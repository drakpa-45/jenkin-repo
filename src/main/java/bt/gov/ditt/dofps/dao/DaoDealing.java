package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.base.BaseDao;
import bt.gov.ditt.dofps.dto.CommonDto;
import bt.gov.ditt.dofps.dto.WorkFlowDto;
import bt.gov.ditt.dofps.entitiy.TimberEntity;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pema Drakpa on 2/21/2020.
 */
@Repository
public class DaoDealing extends BaseDao implements IDaoDealing{

    @Override
    public List<CommonDto> getRejList() {
        List<CommonDto> dto = new ArrayList<CommonDto>();
        try {
            String query = "SELECT rm.Rejection_Id AS header_id, rm.Rejection_Reason AS header_Name FROM t_rejection_master rm LEFT JOIN t_fp_application a ON \n" +
                    "a.Application_Type = rm.Application_Type WHERE a.Application_Type =:Apptype GROUP BY rm.Rejection_Reason";
            Query hQuery = sqlQuery(query, CommonDto.class).setParameter("Apptype","RTP");
            dto = hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public List<CommonDto> getPRLRejList() {
        List<CommonDto> dto = new ArrayList<CommonDto>();
        try {
            String query = "SELECT r.Rejection_Reason AS header_Name FROM t_rejection_master r WHERE r.Application_Type =?;";
            Query hQuery = sqlQuery(query, CommonDto.class).setParameter(1,"PL");
            dto = hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public WorkFlowDto rejected_application(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_workflow_dtls SET Actor_Name=?,Status_Id=?,Actor_Id=?,Role_Name=?,Role_Id=?,`Action_Date`=CURRENT_DATE WHERE `Application_Number`=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, userRolePriv.getFullName()).setParameter(2, "7")
                        .setParameter(3,roleId).setParameter(4, role_name).setParameter(5,roleId).setParameter(6, request.getParameter("value"));
                int wkflw =hQuery.executeUpdate();
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
    @Transactional
    public WorkFlowDto update_t_ap_rejected_application(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_fp_application SET App_Rejection_Date = CURRENT_DATE, Rejection_Reason=? WHERE `Application_Number`=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlowDto.getRejection_Reason()).setParameter(2, request.getParameter("value"));
                hQuery.executeUpdate();
                workList.setCurrent_Status("Success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workList;
    }


    @Override
    public WorkFlowDto insertAudit(WorkFlowDto workFlowDto, HttpServletRequest request) {
        try {
            String sqlQuery = "INSERT INTO t_fp_application_audit"+
                    "(Application_Number,Application_Type,CID_Number,Full_Name,Dzongkhag_Name,Gewog_Name,Village_Name,House_No,House_Hold_No,NWFP_Management_Group_Sl_No,\n" +
                    "Applicant_Area_Tag,Request_Service_Id,App_Submission_Date,App_Verification_Date,App_Approval_Date,App_Rejection_Date,Rejection_Reason,Phone_Number,\n" +
                    "Mobile_Number,Division_Park_Id,Division_Park_Id_1,Geog_No,Register_Geog,Cons_Loc_Village_Serial_No,Cons_Approval_No,Cons_Type,FP_Details,Certify_Tag,\n" +
                    "Inspection_Remark,Mode_of_Swing_Id,Roofing_Type,Dealing_Date,Dealing_Division_Park,Allot_Range_Officer,Allot_Date,Allot_Area,Member_of_Forest_community,\n" +
                    "Total_Royalty,Permit_Fee,Net_Royalty,Dealing_Officer_Remarks,Div_Change_Reason,Verified_Record_Tag,Head_of_Gung,Construction_Location,Service_Fees,\n" +
                    "Application_Fees,Census_Reg_Date,NWFP_App_Type,Plot_No,Approval_Remarks,Village_Serial_No,Receipt_Number,Receipt_Date,Sync_G2C,Storey_House,\n" +
                    "Inspection_Date,Ranger_Remark_PRL,Previous_Application_No,Claiming_status,Reclaiming_Timber_Remark_CC,AlternativeNumberRelation,GPS_Coordinates,Land_Category,Acres,Peg_No)\n" +
                    "SELECT \n" +
                    "Application_Number,Application_Type,CID_Number,Full_Name,Dzongkhag_Name,Gewog_Name,Village_Name,House_No,House_Hold_No,NWFP_Management_Group_Sl_No,\n" +
                    "Applicant_Area_Tag,Request_Service_Id,App_Submission_Date,App_Verification_Date,App_Approval_Date,App_Rejection_Date,Rejection_Reason,Phone_Number,\n" +
                    "Mobile_Number,Division_Park_Id,Division_Park_Id_1,Geog_No,Register_Geog,Cons_Loc_Village_Serial_No,Cons_Approval_No,Cons_Type,FP_Details,Certify_Tag,\n" +
                    "Inspection_Remark,Mode_of_Swing_Id,Roofing_Type,Dealing_Date,Dealing_Division_Park,Allot_Range_Officer,Allot_Date,Allot_Area,Member_of_Forest_community,\n" +
                    "Total_Royalty,Permit_Fee,Net_Royalty,Dealing_Officer_Remarks,Div_Change_Reason,Verified_Record_Tag,Head_of_Gung,Construction_Location,Service_Fees,\n" +
                    "Application_Fees,Census_Reg_Date,NWFP_App_Type,Plot_No,Approval_Remarks,Village_Serial_No,Receipt_Number,Receipt_Date,Sync_G2C,Storey_House,\n" +
                    "Inspection_Date,Ranger_Remark_PRL,Previous_Application_No,Claiming_status,Reclaiming_Timber_Remark_CC,AlternativeNumberRelation,GPS_Coordinates,Land_Category,Acres,Peg_No\n" +
                    "FROM t_fp_application WHERE Application_Number=:value1";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter("value1", request.getParameter("value"));
            int svadt = hQuery.executeUpdate();
            if (svadt > 0) {
                workFlowDto.setCurrent_Status("success");
            } else {
                workFlowDto.setCurrent_Status("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto insertAlloAudit(WorkFlowDto workFlowDto, HttpServletRequest request) {
        try {
            String sqlQuery = "INSERT INTO t_fp_appl_allot_audit\n" +
                    "(\n" +
                    " FP_Product_Id, Application_Number, Appl_Quantity, Allot_Quantity, Royalty_Type, Royalty_Rate, Estimated_Value, Royalty_Realised, \n" +
                    " Parts_Id,Balance_Quantity,Allot_Area,Dealing_Date,App_Rejection_Date,App_Approval_Date,Approval_Remarks,Quantity_Taken,Net_Royalty,Total_Royalty,No_trees,No_poles,No_bamboos,Volume)\n" +
                    "SELECT \n" +
                    "FP_Product_Id,Application_Number,Appl_Quantity,Allot_Quantity,Royalty_Type,Royalty_Rate,Estimated_Value,Royalty_Realised,\n" +
                    "Parts_Id,Balance_Quantity,Allot_Area,Dealing_Date,App_Rejection_Date,App_Approval_Date,Approval_Remarks,Quantity_Taken,Net_Royalty,Total_Royalty,No_trees,No_poles,No_bamboos,Volume\n" +
                    "FROM t_fp_appl_allotment a WHERE a.Application_Number=:value1";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter("value1", request.getParameter("value"));
            int svalloadt = hQuery.executeUpdate();
            if (svalloadt > 0) {
                workFlowDto.setCurrent_Status("success");
            } else {
                workFlowDto.setCurrent_Status("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto insertWrkFlw(WorkFlowDto workFlowDto, HttpServletRequest request) {
        try {
            String sqlQuery = "INSERT INTO t_workflow_dtls_audit\n" +
                    "(\n" +
                    " Instance_Id,\n" +
                    " Application_Number,\n" +
                    " Status_Id,\n" +
                    " Service_Id,\n" +
                    " Action_Date,\n" +
                    " Actor_Id,\n" +
                    " Actor_Name,\n" +
                    " Role_Id,\n" +
                    " Role_Name)\n" +
                    "SELECT\n" +
                    "Instance_Id,\n" +
                    "Application_Number,\n" +
                    "Status_Id,\n" +
                    "Service_Id,\n" +
                    "Action_Date,\n" +
                    "Actor_Id,\n" +
                    "Actor_Name,\n" +
                    "Role_Id,\n" +
                    "Role_Name FROM t_workflow_dtls w WHERE w.Application_Number=:value1";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter("value1", request.getParameter("value"));
            int svawfadt = hQuery.executeUpdate();
            if (svawfadt > 0) {
                workFlowDto.setCurrent_Status("success");
            } else {
                workFlowDto.setCurrent_Status("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto updateWrkFlw(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name, String actorID) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_workflow_dtls SET Actor_Name=?,Status_Id=?,Actor_Id=?,Role_Name=?,Role_Id=?,`Action_Date`=CURRENT_DATE WHERE `Application_Number`=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, userRolePriv.getFullName()).setParameter(2, "2")
                        .setParameter(3, actorID).setParameter(4, role_name).setParameter(5, roleId).setParameter(6, request.getParameter("value"));
                int wkflw = hQuery.executeUpdate();
                if (wkflw > 0) {
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
    public WorkFlowDto updateWrokFlow(WorkFlowDto workFlow, HttpServletRequest request, String roleId, String role_name, String actorId) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_workflow_dtls SET Actor_Name=?,Status_Id=?,Actor_Id=?,Role_Name=?,Role_Id=?,`Action_Date`=CURRENT_DATE WHERE `Application_Number`=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, userRolePriv.getFullName()).setParameter(2, "11")
                        .setParameter(3, actorId).setParameter(4, role_name).setParameter(5, roleId).setParameter(6, request.getParameter("value"));
                int wkflw = hQuery.executeUpdate();
                if (wkflw > 0) {
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
    public WorkFlowDto updateWrokFlow_dtls(WorkFlowDto workFlow, HttpServletRequest request, String roleId, String role_name, String actorId) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_workflow_dtls SET Actor_Name=?,Status_Id=?,Actor_Id=?,Role_Name=?,Role_Id=?,`Action_Date`=CURRENT_DATE WHERE `Application_Number`=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, userRolePriv.getFullName()).setParameter(2, "8")
                        .setParameter(3, actorId).setParameter(4, role_name).setParameter(5, roleId).setParameter(6, request.getParameter("value"));
                int wkflw = hQuery.executeUpdate();
                if (wkflw > 0) {
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
    public WorkFlowDto insert_task(WorkFlowDto workFlowDto, HttpServletRequest request) {
        try {
            String sqlQuery = "INSERT INTO t_task_dtls_audit\n" +
                    "(\n" +
                    " Task_Id,\n" +
                    " Instance_Id,\n" +
                    " Seq_Details_Id,\n" +
                    " Application_Number,\n" +
                    " Assigned_User_Id,\n" +
                    " Assigned_Priv_Id,\n" +
                    " Task_State_Id,\n" +
                    " Action_Date,\n" +
                    " Task_Remark)\n" +
                    "SELECT\n" +
                    "Task_Id,\n" +
                    "Instance_Id,\n" +
                    "Seq_Details_Id,\n" +
                    "Application_Number,\n" +
                    "Assigned_User_Id,\n" +
                    "Assigned_Priv_Id,\n" +
                    "Task_State_Id,\n" +
                    "Action_Date,\n" +
                    "Task_Remark FROM t_task_dtls t WHERE t.Application_Number=:value1";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter("value1", request.getParameter("value"));
            int svatskadt = hQuery.executeUpdate();
            if (svatskadt > 0) {
                workFlowDto.setCurrent_Status("success");
            } else {
                workFlowDto.setCurrent_Status("fail");
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
                String sqlQuery = "UPDATE t_fp_appl_allotment SET Allot_Quantity=?, Total_Royalty=?,Allot_Range_Officer=?,Net_Royalty=?,\n" +
                        "Allot_Date=? WHERE `Application_Number`=? AND FP_Product_Id=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlowDto.getAllot_Quantity()).setParameter(2, workFlow.getTotal_Royalty())
                        .setParameter(3, workFlow.getAllot_Range_Officer()).setParameter(4, workFlow.getNet_Royalty())
                        .setParameter(5, workFlow.getAllot_Date()).setParameter(6, request.getParameter("value")).setParameter(7, workFlowDto.getFp_Product_Id());
                int save = hQuery.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                String sqlQuery = "UPDATE t_fp_appl_allotment SET Allot_Quantity=?, Total_Royalty=?,Allot_Range_Officer=?,Net_Royalty=?,\n" +
                        "Allot_Date=? WHERE `Application_Number`=? AND FP_Product_Id=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlow.getAllot_Quantity()).setParameter(2, "10")
                        .setParameter(3, workFlow.getAllot_Range_Officer()).setParameter(4, "10")
                        .setParameter(5, workFlow.getAllot_Date()).setParameter(6, request.getParameter("value")).setParameter(7, workFlowDto.getFp_Product_Id());
                int save = hQuery.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlowDto;
    }

    @Override
    public WorkFlowDto update_fp_app(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_fp_application SET App_Verification_Date=CURRENT_DATE,Dealing_Officer_Remarks=? WHERE `Application_Number`=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlowDto.getDealing_Officer_Remarks()).setParameter(2, request.getParameter("value"));
                int fp = hQuery.executeUpdate();
                if (fp > 0) {
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
    public WorkFlowDto update_fp_application(WorkFlowDto workFlow, HttpServletRequest request, String roleId, String role_name) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_fp_application SET Inspection_Date=CURRENT_DATE,Ranger_Remark_PRL=?,Plot_No=?,GPS_Coordinates=?,Land_Category=?,Acres=?,Peg_No=?, PrivateLand_Adjoining_SRFL = ? WHERE `Application_Number`=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlow.getRanger_Remark_PRL()).setParameter(2, workFlow.getPlot_No()).setParameter(3, workFlow.getGps_Coordinates())
                        .setParameter(4, workFlow.getLand_Category()).setParameter(5, workFlow.getAcres()).setParameter(6, workFlow.getPeg_No()).setParameter(7, workFlow.getLandAdjoining()).setParameter(8, request.getParameter("value"));
                int fp = hQuery.executeUpdate();
                if (fp > 0) {
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
    public WorkFlowDto update_fp_Application(WorkFlowDto workFlow, HttpServletRequest request, String roleId, String role_name) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_fp_application SET Dealing_Date=? WHERE `Application_Number`=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlow.getDealing_Date()).setParameter(2, request.getParameter("value"));
                int fp = hQuery.executeUpdate();
                if (fp > 0) {
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
    public WorkFlowDto upadteClaimedApp(WorkFlowDto workFlowDto, HttpServletRequest request, String roleId, String role_name) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");

        WorkFlowDto workList = new WorkFlowDto();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_task_dtls SET `Assigned_User_Id`=?,`Task_State_Id`=?,Assigned_Priv_Id = 1842,`Action_Date`=CURRENT_DATE WHERE `Application_Number`=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, userRolePriv.getUserType()).setParameter(2, "4")
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
    public WorkFlowDto upadteMarkedDate(WorkFlowDto workFlow, HttpServletRequest request, String roleId, String role_name) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_task_dtls SET `Assigned_User_Id`=?,`Task_State_Id`=?,`Action_Date`=CURRENT_DATE WHERE `Application_Number`=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, userRolePriv.getUserType()).setParameter(2, "8")
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
    public String getRejReason(WorkFlowDto workFlowDto) {
        String reason="";
        try {
            String query = "SELECT rm.Rejection_Reason FROM t_rejection_master rm LEFT JOIN t_fp_application a ON \n" +
                    "a.`Rejection_Reason` = rm.`Rejection_Id` WHERE a.Rejection_Reason=?";
            Query hQuery = sqlQuery1(query).setParameter(1, workFlowDto.getRejection_Id());
             reason = (String) hQuery.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reason;
    }

    @Override
    public String updateAllotmentPvtLand(TimberEntity timberEntity) {
        try {
            saveOrUpdate(timberEntity);
            return "Success";
        } catch (Exception e) {
            System.out.print("Exception in Product detail # saveApplicationDetails: " + e);
            return "Exception from SaveProductDetails+" + e;
        }
    }
}
