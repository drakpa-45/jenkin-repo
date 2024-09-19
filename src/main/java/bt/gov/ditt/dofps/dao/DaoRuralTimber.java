package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.base.BaseDao;
import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.entitiy.PersonalEntity;
import bt.gov.ditt.dofps.entitiy.TimberEntity;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.wso2.client.model.G2C_CommonBusinessAPI.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pema Drakpa on 1/17/2020.
 */
@Repository
public class DaoRuralTimber extends BaseDao implements IDaoRuralTimber {

    @Override
    public List<NewTimberDto> getParkList(HttpServletRequest request, Integer dzo_Id) {
        List<NewTimberDto> newTimberDto = new ArrayList<NewTimberDto>();
       HttpSession session = request.getSession();
        String serviceSelected=request.getParameter("serviceId"),location_id="",servicesfor=request.getParameter("page_type");
        UserRolePrivilegeHierarchyObj userRolePriv=(UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        UserRolePrivilegeDTO dto = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String updatequery ="",priveleges="";
        try {
            if (userRolePriv.getUserRoles() != null) {
                JurisdictionsObj jurisdiction = userRolePriv.getJurisdictions();
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
                jurisdiction = userRolePriv.getJurisdictions();
                for (int k = 0; k < jurisdiction.getJurisdiction().size(); k++) {
                    if (k == jurisdiction.getJurisdiction().size() - 1) {
                        location_id += jurisdiction.getJurisdiction().get(k).getLocationId();
                    } else {
                        location_id += jurisdiction.getJurisdiction().get(k).getLocationId() + ", ";
                    }
                }
                String sqlQuery = "SELECT \n" +
                        "  dpl.Division_Park_Id AS header_id,\n" +
                        "  dpl.Division_Park_Name AS header_Name \n" +
                        "FROM\n" +
                        "  t_division_park_lookup dpl \n" +
                        "  LEFT JOIN t_division_park_dzonghkag_mapping d \n" +
                        "    ON d.Division_Park_Id = dpl.Division_Park_Id \n" +
                        "  LEFT JOIN t_dzongkhag_lookup dl \n" +
                        "    ON dl.Dzongkhag_Serial_No = d.Dzongkhag_Serial_No \n" +
                        "WHERE dl.Dzongkhag_Serial_No =?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class).setParameter(1,dzo_Id);
                newTimberDto = hQuery.list();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return newTimberDto;
    }

    @Override
    public Integer getDzoId(HttpServletRequest request, NewTimberDto newTimberDto) {
        int dzo_id = 0;
        HttpSession session = request.getSession();
        String serviceSelected=request.getParameter("serviceId"),location_id="",servicesfor=request.getParameter("page_type");
        UserRolePrivilegeHierarchyObj userRolePriv=(UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        UserRolePrivilegeDTO dto = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String updatequery ="",priveleges="";
        try {
            if (userRolePriv.getUserRoles() != null) {
                JurisdictionsObj jurisdiction = userRolePriv.getJurisdictions();
                jurisdiction = userRolePriv.getJurisdictions();
                for (int k = 0; k < jurisdiction.getJurisdiction().size(); k++) {
                    if (k == jurisdiction.getJurisdiction().size() - 1) {
                        location_id += jurisdiction.getJurisdiction().get(k).getLocationId();
                    } else {
                        location_id += jurisdiction.getJurisdiction().get(k).getLocationId() + ", ";
                    }
                }
            }
            String sqlQuery = "SELECT \n" +
                    "d.Dzongkhag_Serial_No AS header_id \n" +
                    "FROM t_dzongkhag_lookup d\n" +
                    "LEFT JOIN t_gewog_lookup g ON g.Dzongkhag_Serial_No=d.Dzongkhag_Serial_No \n" +
                    "LEFT JOIN t_cc_master c ON c.Gewog_Serial_No = g.Gewog_Serial_No\n" +
                    "WHERE c.CC_Id = ?";
            Query query = sqlQuery1(sqlQuery).setParameter(1, location_id);
            if(query.list().size()>0)
                dzo_id=(Integer)query.list().get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dzo_id;
    }

    @Override
    public List<NewTimberDto> getVillageList(HttpServletRequest request) {

        List<NewTimberDto> dtoList = new ArrayList<NewTimberDto>();
        HttpSession session = request.getSession();
        String serviceSelected = request.getParameter("serviceId"), location_id = "", servicesfor = request.getParameter("page_type");
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        UserRolePrivilegeDTO dto = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String updatequery = "", priveleges = "";
        List<NewTimberDto> workList = new ArrayList<NewTimberDto>();
        if (userRolePriv.getUserRoles() != null) {
            JurisdictionsObj jurisdiction = userRolePriv.getJurisdictions();
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
            // updatequery = GET_ALL_TASK;
            jurisdiction = userRolePriv.getJurisdictions();
            for (int k = 0; k < jurisdiction.getJurisdiction().size(); k++) {
                if (k == jurisdiction.getJurisdiction().size() - 1) {
                    location_id += jurisdiction.getJurisdiction().get(k).getLocationId();
                } else {
                    location_id += jurisdiction.getJurisdiction().get(k).getLocationId() + ", ";
                }
            }
            String sqlQuery = "SELECT v.Village_Serial_No AS header_id, v.Village_Name AS header_Name \n" +
                    "FROM t_village_lookup v LEFT JOIN t_gewog_lookup g ON v.Gewog_Serial_No = g.Gewog_Serial_No\n" +
                    "LEFT JOIN t_cc_master c ON g.Gewog_Serial_No = c.Gewog_Serial_No\n" +
                    "WHERE c.CC_Id=:Type_Id";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class).setParameter("Type_Id", location_id);
            dtoList = hQuery.list();
        }
        return dtoList;
    }
    @Override
    public List<NewTimberDto> getDzongkhagList() {
        try {
            String sqlQuery = "SELECT Dzongkhag_Serial_No AS header_id,\n" +
                    "Dzongkhag_Name AS header_Name \n" +
                    "FROM t_dzongkhag_lookup";
            Query hQuery = (Query) hibernateQuery(sqlQuery, NewTimberDto.class);
            return hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<claimAdditionalTimberDTO> getApplicantDetails(String cid) {
        List<claimAdditionalTimberDTO> dtoList = new ArrayList<>();
        try{
            String sqlQuery = "SELECT \n" +
                    "a.Full_Name AS name, a.Application_Number AS previous_application_Number ,a.GenderType AS genderType, d.Dzongkhag_Name AS dzongkhag_Name,g.Gewog_Name AS gewog_Name,v.Village_Name AS village_Name,\n" +
                    "a.House_Hold_No AS house_Hold_No, a.Division_Park_Id AS division_Park_Id,dv.Division_Park_Name AS division_Park_Name,a.House_No AS house_No,a.Head_of_Gung AS head_of_Gung,a.Phone_Number AS phone_Number, a.Village_Serial_No AS village_Serial_No,\n" +
                    "a.Cons_Approval_No AS cons_Approval_No, a.Register_Geog AS register_Geog, a.Roofing_Type AS roofing_Type,a.Storey_House AS house_Storey,\n" +
                    "vl.Village_Name AS construction_Loc,a.Member_of_Forest_community AS member_of_forest_community,\n" +
                    "a.Mode_of_Swing_Id AS mode_Of_Swing_Id, a.Cons_Loc_Village_Serial_No AS cons_Loc_Village_Serial_No,m.Mode_of_Swing_Desc AS mode_of_Swing_Desc,\n" +
                    "al.Appl_Quantity AS apply_Quantity, al.Allot_Quantity AS allot_Quantity, al.Quantity_Taken AS quantity_Taken, pm.Product_Catagory AS product_Category,\n" +
                    "pm.FP_Product_Id AS FP_product_Id,\n" +
                    "CASE\n" +
                    "WHEN pm.Product_Desc = 'RTP(s)' THEN 'Standing Form'\n" +
                    "WHEN pm.Product_Desc = 'RTP(l)' THEN 'Log Form'\n" +
                    "END AS product_Desc, CAST(al.Dealing_Date AS CHAR CHARACTER SET utf8) AS mark_date, al.Balance_Quantity AS bal_Qty\n" +
                    "FROM \n" +
                    "t_fp_application a \n" +
                    "INNER JOIN t_village_lookup v ON a.Village_Serial_No = v.Village_Serial_No\n" +
                    "INNER JOIN t_village_lookup vl ON a.Cons_Loc_Village_Serial_No = vl.Village_Serial_No\n" +
                    "LEFT JOIN t_division_park_lookup dv ON a.Division_Park_Id = dv.Division_Park_Id\n" +
                    "LEFT JOIN t_mode_of_swing m ON a.Mode_of_Swing_Id = m.Mode_of_Swing_Id\n" +
                    "LEFT JOIN t_gewog_lookup g ON v.Gewog_Serial_No = g.Gewog_Serial_No\n" +
                    "LEFT JOIN t_dzongkhag_lookup d ON g.Dzongkhag_Serial_No = d.Dzongkhag_Serial_No\n" +
                    "LEFT JOIN t_fp_appl_allotment al ON a.Application_Number = al.Application_Number\n" +
                    "LEFT JOIN t_fp_product_master pm ON al.FP_Product_Id = pm.FP_Product_Id\n" +
                    "LEFT JOIN t_workflow_dtls w ON a.Application_number = w.Application_Number\n" +
                    "WHERE a.CID_Number = ?\n" +
                    "AND a.Cons_Type = 'n' AND w.Status_Id <> 7 AND a.Application_Number NOT LIKE 'Draft%'\n" +
                    "AND al.Dealing_Date IS NOT NULL";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, claimAdditionalTimberDTO.class).setParameter(1, cid);
            dtoList = hQuery.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return dtoList;
    }

    @Override
    public List<claimAdditionalTimberDTO> getAttachments(String appNo) {
        List<claimAdditionalTimberDTO> dto = new ArrayList<claimAdditionalTimberDTO>();
        try {
            String sqlQuery = "SELECT \n" +
                    "td.Document_Type AS document_Type, td.Document_Name AS document_Name, td.Upload_URL AS upload_URL, td.UUID AS uID\n" +
                    "FROM t_document td\n" +
                    "WHERE td.Application_Number =?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, claimAdditionalTimberDTO.class).setParameter(1, appNo);
            dto =  hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public List<NewTimberDto> getDropdownList(String sl_no, String type) {
        List<NewTimberDto> dtoList = new ArrayList<NewTimberDto>();
        if (type.equalsIgnoreCase("gewog_list")) {
            String sqlQuery = "SELECT Gewog_Serial_No AS header_id, Gewog_Name AS header_Name \n" +
                    "FROM t_gewog_lookup WHERE Dzongkhag_Serial_No=:Type_Id";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class).setParameter("Type_Id", sl_no);
            dtoList = hQuery.list();
        } else if (type.equalsIgnoreCase("village_list")) {
            String sqlQuery = "SELECT Village_Serial_No AS header_id, Village_Name AS header_Name \n" +
                    "FROM t_village_lookup WHERE Gewog_Serial_No=:Type_Id";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class).setParameter("Type_Id", sl_no);
            dtoList = hQuery.list();
        }
        return dtoList;
    }

    @Override
    public long getTempNo() {
        Integer seqNo = 0;
        try {
          // String QUE = properties.getProperty("CommonDao.draft");
            String QUE = "SELECT Last_Application_Number AS header_id FROM t_application_sequence WHERE Type =:type";
            Query query = sqlQuery1(QUE);
            query.setParameter("type", "Draft");
            int value = (int) query.uniqueResult();
            //int value = (int) query.list().get(0);
            if (value > 0)
                seqNo = (Integer) query.list().get(0);
            //seqNo = (Integer) query.uniqueResult();
            seqNo++;
            if (seqNo <= 1) {
               //String insert = properties.getProperty("CommonDao.draftInsert");
                String insert = "INSERT INTO t_application_sequence (Last_Application_Number, Service_Id, Type) VALUES (:seqNo,:serviceId,:type)";
                Query query1 = sqlQuery1(insert);
                query1.setParameter("seqNo", seqNo);
                query1 .setParameter("serviceId", 181);
                query1.setParameter("type", "Draft");
                query1.executeUpdate();
            } else {
               // String update = properties.getProperty("CommonDao.draftUpdate");
                String update = "UPDATE t_application_sequence SET Last_Application_Number =:SeqNo WHERE Type =:type";
                Query query1 = sqlQuery1(update);
                query1.setParameter("SeqNo", seqNo);
                query1.setParameter("type", "Draft");
                query1.executeUpdate();
            }
        } catch (Exception e) {
            System.out.print("Exception in DaoRuralTimber # getApplicationSequenceNo: " + e);
        }
        return seqNo;
    }

    @Override
    public String DraftFormat(long appCount) {
        int digitCount2 = String.valueOf(appCount).length();
        StringBuffer sbf = new StringBuffer();
        sbf.append("Draft-");
        for (int i = digitCount2; i < 9; i++)
            sbf.append("0");
        sbf.append(appCount);
        return sbf.toString();
    }

    @Override
    public String deleteData(NewTimberDto newTimberDto) {
        String sqlQuery="DELETE FROM t_fp_appl_allotment WHERE  Application_Number = ?";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter(1, newTimberDto.getApplication_Number());
        hQuery.executeUpdate();
        return "success";
    }

    @Override
    public String saveApplicationDetails(Object obj) {
        try {
            saveOrUpdate(obj);
            return "Success";

        } catch (Exception e) {
            System.out.print("Exception in timber detail # saveApplicationDetails: " + e);
            return "Exception from saveApplicationDetails+" + e;
        }
    }

    @Override
    public String update(NewTimberDto newTimberDto) {
        String roof_type = "";
        try {
            if(newTimberDto.getRoofing_Type().equalsIgnoreCase("c")) {
                roof_type = "CGI sheet";
            }else{
                roof_type = "Singlep";
            }
            String sqlQuery = "UPDATE t_fp_application SET Mode_of_Swing_Id = ?,Roofing_Type = ?,App_Submission_Date=CURRENT_DATE, Storey_House = ? WHERE Application_Number = ?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter(1, newTimberDto.getMode_of_Swing_Id())
                    .setParameter(2, roof_type).setParameter(3, newTimberDto.getStorey_House())
                   .setParameter(4, newTimberDto.getApplication_Number());
            hQuery.executeUpdate();
        } catch (Exception e) {
            System.out.println("issue while updating: #" + e);
        }
        return "success";
    }

    @Override
    public String saveDocumentDetails(Object obj) {
        try {
            saveOrUpdate(obj);
            return "Success";
        } catch (Exception e) {
            System.out.print("Exception in fileUploading detail # saveApplicationDetails: " + e);
            return "Exception from saveDocumentDetails+" + e;
        }
    }

    @Override
    public String saveTimber(TimberEntity timberEntity) {
        try {
            saveOrUpdate(timberEntity);
            return "Success";
        } catch (Exception e) {
            System.out.print("Exception in personal detail # saveApplicationDetails: " + e);
            return "Exception from saveTimber+" + e;
        }
    }

    @Override
    public NewTimberDto saveUpdateApplication(CitizenDetailsDTO dto, NewTimberDto newTimberDto, String appNo, String draftNo) {
        NewTimberDto saveApplication = new NewTimberDto();
        try {
            Query query1 = sqlQuery("UPDATE t_fp_application SET Application_Number =?, Geog_No=?,Cons_Loc_Village_Serial_No=?,Construction_Location=?,App_Submission_Date=CURRENT_DATE WHERE Application_Number =?", NewTimberDto.class);
            query1.setParameter(1, appNo).setParameter(2,dto.getGeog_No()).setParameter(3,newTimberDto.getCons_Loc_Village_Serial_No()).setParameter(4,newTimberDto.getCons_Loc_Village_Serial_No()).setParameter(5, draftNo);
            int save = query1.executeUpdate();
            if(save > 0){
                Query query2 = sqlQuery("UPDATE t_fp_appl_allotment SET Application_Number =? WHERE Application_Number =?", NewTimberDto.class);
                query2.setParameter(1, appNo).setParameter(2, draftNo);
                 save = query2.executeUpdate();
            }
            saveApplication.setApplication_Number(appNo);
        } catch (Exception e) {
            System.out.println("issue while updating: # saveUpdateApplication #" + e);
        }
        return saveApplication;
    }

    @Override
    public String updateMemberOfCommunity(String appNo, NewTimberDto newTimberDto) {
        String member = "";
        try {
            if(newTimberDto.getMember_of_Forest_community().equalsIgnoreCase("y")) {
                member = "Yes";
            }else{
                member = "No";
            }
            String sqlQuery = "UPDATE t_fp_application SET Member_of_Forest_community=? WHERE Application_Number = ?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter(1, member).setParameter(2, appNo);
            hQuery.executeUpdate();
        } catch (Exception e) {
            System.out.println("issue while updating: # updateMemberOfCommunity #" + e);
        }
        return "success";
    }

    @Override
    public WorkFlowDto updateWrkFlwTablePrint(WorkFlowDto workFlow, HttpServletRequest request, String roleId, String role_name, String actorID) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        WorkFlowDto workList = new WorkFlowDto();
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_workflow_dtls SET Actor_Name=?,Status_Id=?,Actor_Id=?,Role_Name=?,Role_Id=?,`Action_Date`=CURRENT_DATE WHERE `Application_Number`=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, userRolePriv.getFullName()).setParameter(2, "12")
                        .setParameter(3, actorID).setParameter(4, role_name).setParameter(5, roleId).setParameter(6, workFlow.getApplication_Number());
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
    public WorkFlowDto insertWrkFlw(WorkFlowDto workFlow, HttpServletRequest request) {
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
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter("value1", workFlow.getApplication_Number());
            int svawfadt = hQuery.executeUpdate();
            if (svawfadt > 0) {
                workFlow.setCurrent_Status("success");
            } else {
                workFlow.setCurrent_Status("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workFlow;
    }

    @Override
    public BigInteger validatePreviousApp(claimAdditionalTimberDTO claimAdditionalTimberDTO) {
        String cid_number = claimAdditionalTimberDTO.getCid_Number();
        try{
            String sqlQuery = "SELECT \n" +
                    "  COUNT(a.CID_Number) \n" +
                    "FROM\n" +
                    "  t_fp_application a LEFT JOIN t_workflow_dtls w\n" +
                    "  ON a.Application_Number = w.Application_Number\n" +
                    "WHERE a.CID_Number = ? \n" +
                    "  AND (\n" +
                    "    a.Claiming_status = 'reclaim' \n" +
                    "    OR a.Rejection_Reason = '(NULL)' \n" +
                    "    OR a.Previous_Application_No = '(NULL)'\n" +
                    "  )\n" +
                    "AND w.Status_Id <> 7";
            Query hQuery = (Query) hibernateQuery(sqlQuery).setParameter(1, cid_number);
            return (BigInteger) hQuery.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BigInteger validateDuration(claimAdditionalTimberDTO claimAdditionalTimberDTO) {
        String cid_number = claimAdditionalTimberDTO.getCid_Number();
        try{
            String sqlQuery = "SELECT COUNT(*) \n" +
                    "FROM t_fp_application a LEFT JOIN t_fp_appl_allotment al ON a.Application_Number = al.Application_Number\n" +
                    "WHERE a.CID_Number = ?\n" +
                    "and a.Cons_Type = 'n'\n" +
                    "AND al.Dealing_Date BETWEEN DATE_SUB(CURDATE(), INTERVAL 3 YEAR) AND CURDATE()";
            Query hQuery = (Query) hibernateQuery(sqlQuery).setParameter(1, cid_number);
            return (BigInteger) hQuery.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BigInteger CountExit(CitizenDetailsDTO dto, NewTimberDto newTimberDto, HttpServletRequest request, String cons_type) {
       String sqlQuery="";
        try {
            if(cons_type.equalsIgnoreCase("n")) {
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
            Query hQuery = (Query) hibernateQuery(sqlQuery).setParameter(1, dto.getHouse_Hold_No())
                    .setParameter(2, cons_type);
            return (BigInteger) hQuery.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public WorkFlowDto getApplDetails(String cons_type, CitizenDetailsDTO dto) {
        WorkFlowDto dtoo = new WorkFlowDto();
        try{
            String sqlQuery = "SELECT a.CID_Number AS cid_Number,a.House_Hold_No AS house_Hold_No, a.App_Submission_Date AS action_Date FROM t_fp_application a \n" +
                    "WHERE a.House_Hold_No = ? AND a.Cons_Type = ?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1, dto.getHouse_Hold_No()).setParameter(2,cons_type);
            //Query query = sqlQuery(sqlQuery, WorkFlowDto.class).setParameter(1, dto.getHouse_Hold_No()).setParameter(2,cons_type);
            dtoo = (WorkFlowDto) hQuery.list().get(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return dtoo;
    }

    @Override
    public List<NewTimberDto> getDivisionPark(String div_id, NewTimberDto dto) {
        List<NewTimberDto> newTimberDto = new ArrayList<NewTimberDto>();
        try{
            String sqlQuery = "SELECT r.Range_Id AS header_id,\n" +
                    "r.Range_Name AS header_Name\n" +
                    "FROM t_range_lookup r WHERE r.Division_Park_Id = ?";
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class).setParameter(1,div_id);
            newTimberDto = hQuery.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return newTimberDto;
    }

    @Override
    public Long getServiceId() {
        long Service_Id = 0;
        try {
            String sql = "SELECT Service_Id FROM t_service_lookup WHERE Service_Short_Desc = ?";
            Query query = sqlQuery1(sql).setParameter(1,"RTP");
            if (query.list().size() > 0) {
                Service_Id = (Integer) query.list().get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Service_Id;
    }

    @Override
    public String saveReclaimDetails(PersonalEntity personalEntity) {
        try{
            saveOrUpdate(personalEntity);
            return "success";
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<NewTimberDto> getLandCategory() {
        try {
            String sqlQuery = "SELECT Land_Category_Id AS header_id, Land_Category_Name AS header_Name \n" +
                    "FROM t_land_category_master WHERE Application_type = 'PL'";
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
            String sqlQuery = "SELECT a.FP_Product_Id AS header_id, a.Product_Catagory AS header_Name \n" +
                    "FROM t_fp_product_master a WHERE a.Product_Desc = 'PL'";
            Query hQuery = (Query) hibernateQuery(sqlQuery, NewTimberDto.class);
            return hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<NewTimberDto> getGrowingSeason() {
        try {
            String sqlQuery = "SELECT a.Season_Id AS header_id, a.Season_Name AS header_Name \n" +
                    "FROM t_growing_season_master a where a.Growing_Season = 'N'";
            Query hQuery = (Query) hibernateQuery(sqlQuery, NewTimberDto.class);
            return hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CommonDto> getProductList() {
        try {
            String sqlQuery = "SELECT a.FP_Product_Id AS header_id, a.Product_Catagory AS header_Name FROM t_fp_product_master a where a.Construction_Type = 'o'";
            Query hQuery = (Query) hibernateQuery(sqlQuery, CommonDto.class);
            return hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
