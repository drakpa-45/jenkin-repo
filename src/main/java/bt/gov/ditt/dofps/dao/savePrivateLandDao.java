package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.base.BaseDao;
import bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO;
import bt.gov.ditt.dofps.dto.WorkFlowDto;
import bt.gov.ditt.dofps.entitiy.PersonalEntity;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Tshedup Gyeltshen on 4/8/2020.
 */
@Repository
public class savePrivateLandDao extends BaseDao implements IsavePrivateLandDao {
    @Override
    public long getServiceId() {
        long Service_Id = 0;
        try {
            String sql = "SELECT Service_Id FROM t_service_lookup WHERE Service_Short_Desc = ?";
            Query query = sqlQuery1(sql).setParameter(1,"PRL");
            if (query.list().size() > 0) {
                Service_Id = (Integer) query.list().get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Service_Id;
    }

    @Override
    public String savePrivateLand(PersonalEntity personalEntity) {
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
    public WorkFlowDto getapplicationDetails(HttpServletRequest request, String type) {
        String application_number = request.getParameter("Application_Number");
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

    private String GET_APPLICATION_DETAILS = "SELECT \n" +
            "  a.Application_Number AS application_Number,CAST(\n" +
            "    b.Allot_Date AS CHAR CHARACTER SET utf8\n" +
            "  ) AS allot_Date,\n" +
            "  w.Action_Date AS action_Date,a.Dealing_Officer_Remarks AS dealing_Officer_Remarks,di.Division_Park_Name AS division_Park_Name,\n" +
            "  a.House_Hold_No AS house_Hold_No,dl.DzongKhag_Name AS dzongkhag_Name,\n" +
            "  CAST(\n" +
            "    a.App_Submission_Date AS CHAR CHARACTER SET utf8\n" +
            "  ) AS app_Submission_Date,v.Village_Name AS village_Name,g.Gewog_Name AS gewog_Name,a.Division_Park_Id AS division_Park_Id,\n" +
            "  a.Head_of_Gung AS head_of_Gung,a.Application_Type AS application_Type,a.House_No AS house_No,\n" +
            "  a.CID_Number AS cid_Number,a.Thram_No AS thram_No,\n" +
            "  CAST(\n" +
            "    a.App_Approval_Date AS CHAR CHARACTER SET utf8\n" +
            "  ) AS app_Approval_Date,vl.Village_Name AS construction_Location,\n" +
            "  CAST(\n" +
            "    a.App_Verification_Date AS CHAR CHARACTER SET utf8\n" +
            "  ) AS app_Verification_Date,\n" +
            "  a.Census_Reg_Date AS census_Reg_Date,a.Full_Name AS name,\n" +
            "  a.Cons_Approval_No AS cons_Approval_No,a.Approval_Remarks AS approval_Remarks,\n" +
            "  a.Phone_Number AS phone_Number,a.Mobile_Number AS telephone_Number,a.Private_LandForm_Remarks AS private_LandForm_Remarks,\n" +
            "  a.Ranger_Remark_PRL AS Ranger_Remark_PRL,a.AlternativeNumberRelation AS AlternativeNumberRelation,\n" +
            "  r.Range_Name AS range_Officer, a.Plot_No AS plot_No, a.GPS_Coordinates AS gps_Coordinates, la.Land_Category_Name AS land_Category_Name,\n" +
            "  a.Acres AS acres, a.Peg_No AS peg_No,\n" +
            "  CASE \n"+
            "  WHEN a.PrivateLand_Adjoining_SRFL = 'Y' THEN 'Yes'\n"+
            "  WHEN a.PrivateLand_Adjoining_SRFL = 'N' THEN 'No'\n"+
            "  END AS landAdjoining\n"+
            "  FROM\n" +
            "  t_fp_application a \n" +
            "  LEFT JOIN t_fp_appl_allotment b \n" +
            "    ON a.Application_Number = b.Application_Number \n" +
            "  LEFT JOIN t_village_lookup v \n" +
            "    ON a.Village_Serial_No = v.Village_Serial_No \n" +
            "  LEFT JOIN t_fp_product_master pm \n" +
            "    ON pm.FP_Product_Id = b.FP_Product_Id \n" +
            "  LEFT JOIN t_village_lookup vl \n" +
            "    ON vl.Village_Serial_No = a.Cons_Loc_Village_Serial_No \n" +
            "  LEFT JOIN t_range_lookup r \n" +
            "    ON r.Range_Id = a.Allot_Range_Officer \n" +
            "  LEFT JOIN t_gewog_lookup g \n" +
            "    ON g.Gewog_Serial_No = v.Gewog_Serial_No \n" +
            "  LEFT JOIN t_dzongkhag_lookup dl \n" +
            "    ON dl.Dzongkhag_Serial_No = g.Dzongkhag_Serial_No \n" +
            "  LEFT JOIN t_workflow_dtls w \n" +
            "    ON w.Application_Number = a.Application_Number \n" +
            "  LEFT JOIN t_division_park_lookup di \n" +
            "    ON a.Division_Park_Id = di.Division_Park_Id \n" +
            "left join t_land_category_master la \n" +
            "    on a.Land_Category = la.Land_Category_Id \n" +
            "WHERE a.Application_Number =? GROUP BY a.Application_Number";

    @Override
    public WorkFlowDto getapplDetails(String application_number, String application_details) {
        String updatequery = "", priveleges = "";
        WorkFlowDto dtoo = new WorkFlowDto();
        try {
            updatequery = "SELECT a.Application_Number AS application_Number, CAST( b.Allot_Date AS CHAR CHARACTER SET utf8 ) AS allot_Date, w.Action_Date AS action_Date, \n" +
                    "di.Division_Park_Name AS division_Park_Name, a.House_Hold_No AS house_Hold_No, dl.DzongKhag_Name AS dzongkhag_Name, CAST( a.App_Submission_Date AS CHAR CHARACTER SET utf8 ) AS app_Submission_Date,\n" +
                    "v.Village_Name AS village_Name, g.Gewog_Name AS gewog_Name, a.Division_Park_Id AS division_Park_Id, a.Head_of_Gung AS head_of_Gung, a.Application_Type AS application_Type, a.House_No AS house_No,\n" +
                    "a.CID_Number AS cid_Number, CAST( a.App_Approval_Date AS CHAR CHARACTER SET utf8 ) AS app_Approval_Date, vl.Village_Name AS construction_Location, CAST( a.App_Verification_Date AS CHAR CHARACTER SET utf8 ) AS app_Verification_Date, \n" +
                    "a.Census_Reg_Date AS census_Reg_Date, a.Full_Name AS name, a.Cons_Approval_No AS cons_Approval_No, a.Approval_Remarks AS approval_Remarks, a.Phone_Number AS phone_Number, a.Mobile_Number AS telephone_Number, a.Private_LandForm_Remarks AS private_LandForm_Remarks,\n" +
                    "a.Ranger_Remark_PRL AS Ranger_Remark_PRL,w.Status_Id status_Id, a.AlternativeNumberRelation AS AlternativeNumberRelation, r.Range_Name AS range_Officer,a.Acres AS acres,a.Peg_No AS peg_No, a.Plot_No AS plot_No, a.GPS_Coordinates AS gps_Coordinates, la.Land_Category_Name AS land_Category_Name,\n" +
                    "CASE WHEN a.PrivateLand_Adjoining_SRFL = 'Y' THEN 'Yes' WHEN a.PrivateLand_Adjoining_SRFL = 'N' THEN 'No' END AS landAdjoining FROM t_fp_application a LEFT JOIN t_fp_appl_allotment b ON a.Application_Number = b.Application_Number LEFT JOIN t_village_lookup v ON a.Village_Serial_No = v.Village_Serial_No\n" +
                    "LEFT JOIN t_species_name pm ON pm.Species_Id = b.FP_Product_Id LEFT JOIN t_village_lookup vl ON vl.Village_Serial_No = a.Cons_Loc_Village_Serial_No LEFT JOIN t_range_lookup r ON r.Range_Id = a.Allot_Range_Officer LEFT JOIN t_gewog_lookup g ON g.Gewog_Serial_No = v.Gewog_Serial_No \n" +
                    "LEFT JOIN t_dzongkhag_lookup dl ON dl.Dzongkhag_Serial_No = g.Dzongkhag_Serial_No LEFT JOIN t_workflow_dtls w ON w.Application_Number = a.Application_Number LEFT JOIN t_division_park_lookup di ON a.Division_Park_Id = di.Division_Park_Id LEFT JOIN t_land_category_master la ON a.Land_Category = la.Land_Category_Id WHERE a.Application_Number =?";
            Query query = sqlQuery(updatequery, WorkFlowDto.class).setParameter(1, application_number);
            dtoo = (WorkFlowDto) query.list().get(0);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dtoo;
    }

    @Override
    public String updatePrivateProduceDetailsByRO(String appNo, HttpServletRequest request, WorkFlowDto workFlowDto) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        String returnMsg="";
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
               // String sqlQuery = "UPDATE t_fp_application SET Inspection_Date=CURRENT_DATE,Plot_No=?,GPS_Coordinates=?,Land_Category=?,Acres=?,Peg_No=?, PrivateLand_Adjoining_SRFL = ? WHERE `Application_Number`=?";
                String sqlQuery = "UPDATE t_fp_application SET Inspection_Date=CURRENT_DATE, PrivateLand_Adjoining_SRFL = ? WHERE `Application_Number`=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, workFlowDto.getLandAdjoining()).setParameter(2, appNo);
                int save = hQuery.executeUpdate();
                if (save > 0) {
                    returnMsg="Success";
                } else {
                    returnMsg="Failed";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMsg;
    }

    @Override
    public String updateApprovedDate(HttpServletRequest request, String appNo, String remarks) {
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv = (UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        String returnMsg="";
        try {
            if (userRolePriv.getUserRoles().getUserRole().get(0).getRoleName() != null) {
                String sqlQuery = "UPDATE t_fp_application SET App_Approval_Date = CURRENT_DATE WHERE `Application_Number`=?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, appNo);
                int sizee = hQuery.executeUpdate();
                if(sizee>0) {
                    returnMsg="Success";
                }else{
                    returnMsg="Failed";
                }
            }
        }catch (Exception e){
            System.out.print("Exception while approving:"+e);
        }
        return returnMsg;
    }
}