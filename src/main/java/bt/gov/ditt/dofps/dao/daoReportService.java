package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.base.BaseDao;
import bt.gov.ditt.dofps.dto.CommonDto;
import bt.gov.ditt.dofps.dto.NewTimberDto;
import bt.gov.ditt.dofps.dto.ReportDto;
import bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.wso2.client.model.G2C_CommonBusinessAPI.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tshedup Gyeltshen on 4/20/2020.
 */
@Repository
public class daoReportService extends BaseDao implements IdaoReportService {
    @Override
    public List<NewTimberDto> getParkList(HttpServletRequest request) {
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
                        "  Division_Park_Id AS header_id,\n" +
                        "  Division_Park_Name AS header_Name \n" +
                        "FROM\n" +
                        "  t_division_park_lookup";
                Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class);
                newTimberDto = hQuery.list();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return newTimberDto;
    }

    @Override
    public List<NewTimberDto> getJurisdication(HttpServletRequest request, String locationId) {
        List<NewTimberDto> newTimberDto = new ArrayList<NewTimberDto>();
        try{
            String sqlQuery = "SELECT \n" +
                    "a.Division_Park_Id AS header_id,\n" +
                    "a.Division_Park_Name AS header_Name \n" +
                    "FROM\n" +
                    "t_division_park_lookup a\n" +
                    "WHERE a.Division_Park_Id = ?";
            Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class)
                    .setParameter(1,locationId);
            newTimberDto = hQuery.list();
        }catch(Exception e){
            e.printStackTrace();
        }
        return newTimberDto;
    }

    @Override
    public List<NewTimberDto> getRangeLocation(HttpServletRequest request, String locationId) {
        List<NewTimberDto> newTimberDto = new ArrayList<NewTimberDto>();
        try {
            String sqlQuery = "SELECT \n" +
                    "a.Range_Id AS header_id,\n" +
                    "a.Range_Name AS header_Name\n" +
                    "FROM t_range_lookup a WHERE a.Range_Id = ?";
            Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class)
                    .setParameter(1,locationId);
            newTimberDto = hQuery.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return newTimberDto;
    }

    @Override
    public List<NewTimberDto> getConstype(HttpServletRequest request) {
        List<NewTimberDto> newTimberDto = new ArrayList<NewTimberDto>();
        HttpSession session = request.getSession();
        try{
            String sqlQuery = "SELECT DISTINCT\n" +
                    "Construction_Type AS header_Name,\n" +
                    "CASE\n" +
                    "WHEN Construction_Type = 'n' THEN 'New Construction of Rural Houses'\n" +
                    "WHEN Construction_Type = 'r' THEN 'Repair OR Renovation OR Extension of Rural Houses'\n" +
                    "WHEN  Construction_Type = 'o' THEN 'Other Rural Constructions'\n" +
                    "END AS cons_Type\n" +
                    "FROM t_fp_product_master WHERE Construction_Type IS NOT NULL AND Construction_Type<>'x'";
            Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class);
            newTimberDto = hQuery.list();
        }catch (Exception e){
            e.getStackTrace();
        }
        return newTimberDto;
    }

    @Override
    public List<NewTimberDto> getWoodAndPoleType(HttpServletRequest request) {
        List<NewTimberDto> newTimberDto = new ArrayList<NewTimberDto>();
        HttpSession session = request.getSession();
        try{
            String sqlQuery = "SELECT PM.FP_Product_Id AS header_id, PM.Product_Catagory AS header_Name \n" +
                    "FROM t_fp_product_master PM\n" +
                    "WHERE PM.Product_Desc = 'WP'";
            Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class);
            newTimberDto = hQuery.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return newTimberDto;
    }

    @Override
    public List<NewTimberDto> getTimberForm(HttpServletRequest request) {
        List<NewTimberDto> newTimberDto = new ArrayList<NewTimberDto>();
        HttpSession session = request.getSession();
        try{
            String sqlQuery ="select \n" +
                    "a.FP_Product_Id as header_id,\n" +
                    "case\n" +
                    "when a.Product_Desc = 'RTP(s)' and a.Construction_Type = 'n' then 'Standing Form (New Construction)'\n" +
                    "when a.Product_Desc = 'RTP(l)' and a.Construction_Type = 'n' then 'Log Form (New Construction)'\n" +
                    "when a.Product_Desc = 'RTP(s)' and a.Construction_Type = 'r' then 'Standing Form (Renovation)'\n" +
                    "WHEN a.Product_Desc = 'RTP(l)' AND a.Construction_Type = 'r' THEN 'Log Form (Renovation)'\n" +
                    "WHEN a.Product_Desc = 'RTP(s)' AND a.Construction_Type = 'o' and a.FP_Product_Id = '169' THEN 'Tsim (Other Construction)'\n" +
                    "WHEN a.Product_Desc = 'RTP(s)' AND a.Construction_Type = 'o' AND a.FP_Product_Id = '170' THEN 'Dangchung (Other Construction)'\n" +
                    "end as header_Name\n" +
                    "from t_fp_product_master a\n" +
                    "where (a.Product_Desc = 'RTP(s)' || a.Product_Desc = 'RTP(l)')\n" +
                    "and a.Construction_Type <> 'X'";
            Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class);
            newTimberDto = hQuery.list();
        }catch (Exception e){
            e.getStackTrace();
        }
        return newTimberDto;
    }

    @Override
    public List<CommonDto> getDropdownDetails(String sl_No, String type, String cons_Type) {
        List<CommonDto> commonDto = new ArrayList<CommonDto>();
        try{
            if (type.equalsIgnoreCase("dzongkhag_list")) {
                String sqlQuery = "SELECT b.Dzongkhag_Serial_No AS header_id, b.Dzongkhag_Name AS header_Name FROM t_division_park_dzonghkag_mapping a LEFT JOIN t_dzongkhag_lookup b ON a.Dzongkhag_Serial_No = b.Dzongkhag_Serial_No\n" +
                        "WHERE a.Division_Park_Id =:Type_Id";
                Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter("Type_Id", sl_No);
                commonDto = hQuery.list();
            } else if (type.equalsIgnoreCase("gewog_list")) {
                String sqlQuery = "SELECT g.Gewog_Serial_No AS header_id, g.Gewog_Name AS header_Name FROM t_gewog_lookup g LEFT JOIN\n" +
                        "t_dzongkhag_lookup d ON g.Dzongkhag_Serial_No = d.Dzongkhag_Serial_No\n" +
                        "WHERE d.Dzongkhag_Serial_No =:Type_Id";
                Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter("Type_Id", sl_No);
                commonDto = hQuery.list();
            }else if(type.equalsIgnoreCase("product_category")){
                String sqlQuery = "SELECT DISTINCT PM.FP_Product_Id AS header_id, PM.Product_Catagory AS header_Name \n" +
                        "FROM t_fp_product_master PM\n" +
                        "WHERE \n" +
                        "(IF(? = 'ALL', 1 = 1,? = PM.Product_Desc)\n" +
                        "AND IF(? = 'ALL', 1 = 1,? = PM.Construction_Type))";
                Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1, sl_No).setParameter(2, sl_No).setParameter(3, cons_Type).setParameter(4, cons_Type);
                commonDto = hQuery.list();
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return commonDto;
    }

    @Override
    public List<CommonDto> getDropdown(String sl_no, String type, String cons_type) {
        List<CommonDto> commonDto = new ArrayList<CommonDto>();
        try{
            if (type.equalsIgnoreCase("dzongkhag_list")) {
                String sqlQuery = "SELECT \n" +
                        "b.Dzongkhag_Serial_No AS header_id,\n" +
                        "b.Dzongkhag_Name AS header_Name \n" +
                        "FROM\n" +
                        "t_division_park_dzonghkag_mapping a \n" +
                        "LEFT JOIN t_dzongkhag_lookup b \n" +
                        "ON a.Dzongkhag_Serial_No = b.Dzongkhag_Serial_No\n" +
                        "WHERE a.Division_Park_Id =:Type_Id";
                Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter("Type_Id", sl_no);
                commonDto = hQuery.list();
            } else if (type.equalsIgnoreCase("gewog_list")) {
                String sqlQuery = "SELECT g.Gewog_Serial_No AS header_id, g.Gewog_Name AS header_Name FROM t_gewog_lookup g LEFT JOIN\n" +
                        "t_dzongkhag_lookup d ON g.Dzongkhag_Serial_No = d.Dzongkhag_Serial_No\n" +
                        "WHERE d.Dzongkhag_Serial_No =:Type_Id";
                Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter("Type_Id", sl_no);
                commonDto = hQuery.list();
            }else if(type.equalsIgnoreCase("timber_form")){
                String sqlQuery = "select \n" +
                        "a.FP_Product_Id AS header_id,\n" +
                        "CASE\n" +
                        "WHEN a.Product_Desc = 'RTP(s)' AND a.Construction_Type = 'n' THEN 'Standing Form (New Construction)'\n" +
                        "WHEN a.Product_Desc = 'RTP(l)' AND a.Construction_Type = 'n' THEN 'Log Form (New Construction)'\n" +
                        "when a.Product_Desc = 'RTP(s)' and a.Construction_Type = 'r' then 'Standing Form (Renovation)'\n" +
                        "WHEN a.Product_Desc = 'RTP(l)' AND a.Construction_Type = 'r' THEN 'Log Form (Renovation)'\n" +
                        "WHEN a.Product_Desc = 'RTP(s)' AND a.Construction_Type = 'o' and a.FP_Product_Id = '169' THEN 'Tsim (Other Construction)'\n" +
                        "WHEN a.Product_Desc = 'RTP(s)' AND a.Construction_Type = 'o' AND a.FP_Product_Id = '170' THEN 'Dangchung (Other Construction)'" +
                        "END AS header_Name\n" +
                        "FROM t_fp_product_master a WHERE a.Construction_Type = ?";
                Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1, sl_no);
                commonDto = hQuery.list();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return commonDto;
    }

    @Override
    public List<ReportDto> generateReport(String division_park_id, String dzongkhag_name, String gewog_name, String cons_type, String product_desc, String product_catagory, String from_date, String to_date, String type) {
        List<ReportDto> reportDtos = new ArrayList<ReportDto>();
        String sqlQuery="";
        try{
            if(type.equalsIgnoreCase("timber")){
                sqlQuery = "SELECT\n" +
                        "Ap.Application_Number AS application_Number,\n" +
                        "Ap.CID_Number AS cid_Number,\n" +
                        "Ap.House_Hold_No AS house_Hold_No,\n" +
                        "A.Allot_Quantity AS quantity,\n" +
                        "P.UOM AS unit,\n" +
                        "r.Range_Name AS Range_Office,\n" +
                        "A.Allot_Area AS Marking_Location,\n" +
                        "DP.Division_Park_Name AS division_Park_Name,\n" +
                        "(\n" +
                        "CASE\n" +
                        "WHEN P.Construction_Type = 'r'\n" +
                        "THEN 'Renovation'\n" +
                        "WHEN P.Construction_Type = 'n'\n" +
                        "THEN 'New Construction'\n" +
                        "WHEN P.Construction_Type = 'o'\n" +
                        "THEN 'Other'\n" +
                        "END\n" +
                        ") AS cons_Type,\n" +
                        "(\n" +
                        "CASE\n" +
                        "WHEN P.Product_Desc = 'RTP(l)'\n" +
                        "THEN 'Log Form'\n" +
                        "WHEN P.Product_Desc = 'RTP(s)'\n" +
                        "THEN 'Standing Form'\n" +
                        "END\n" +
                        ") AS product_desc,\n" +
                        "P.Product_Catagory AS product_Category,\n" +
                        "G.Gewog_Name AS gewog_Name,\n" +
                        "A.Net_Royalty AS new_Royalty,\n" +
                        "D.Dzongkhag_Name AS dzongkhag_Name,\n" +
                        "A.App_Approval_Date AS approval_Date,\n" +
                        "(CASE\n" +
                        "WHEN Ap.Claiming_status = 'New' THEN 'New Application'\n" +
                        "WHEN Ap.Claiming_status = 'reclaim' THEN 'Reclaimed Application'\n" +
                        "WHEN Ap.Claiming_status IS NULL THEN 'New Application'\n" +
                        "END) AS claim_status,\n" +
                        "(CASE \n" +
                        "WHEN w.Status_Id = '3' THEN 'Approved'\n" +
                        "WHEN w.Status_Id = '8' THEN 'Marked'\n" +
                        "WHEN w.Status_Id = '12' THEN 'Printed'\n" +
                        "END) AS app_atatus\n" +
                        "FROM\n" +
                        "t_fp_application Ap\n" +
                        "LEFT JOIN t_fp_appl_allotment A ON Ap.Application_Number = A.Application_Number\n" +
                        "LEFT JOIN t_workflow_dtls w ON Ap.Application_Number = w.Application_Number\n" +
                        "LEFT JOIN t_range_lookup r ON A.Allot_Range_Officer = r.Range_Id\n" +
                        "LEFT JOIN t_fp_product_master P ON A.FP_Product_Id = P.FP_Product_Id\n" +
                        "LEFT JOIN t_village_lookup v ON (Ap.Cons_Loc_Village_Serial_No = v.Village_Serial_No OR Ap.Village_Serial_No = v.Village_Serial_No)\n" +
                        "LEFT JOIN t_gewog_lookup G ON G.Gewog_Serial_No = v.Gewog_Serial_No\n" +
                        "LEFT JOIN t_dzongkhag_lookup D ON D.Dzongkhag_Serial_No = G.Dzongkhag_Serial_No\n" +
                        "LEFT JOIN t_division_park_lookup DP ON DP.Division_Park_Id = Ap.Division_Park_Id\n" +
                        "WHERE\n" +
                        "Ap.App_Approval_Date BETWEEN ? AND ?\n" +
                        "AND P.Product_Desc LIKE 'RTP%'\n" +
                        "AND Ap.App_Approval_Date IS NOT NULL \n" +
                        "AND A.Allot_Quantity IS NOT NULL\n" +
                        "AND (IF(? = 'ALL', 1 = 1,? = Ap.Division_Park_Id)\n" +
                        "AND IF(? = 'ALL', 1 = 1, ? = Ap.Cons_Type)\n" +
                        "AND IF(? = 'ALL', 1 = 1, ? = P.Product_Desc)\n" +
                        "AND IF(? = 'ALL', 1 = 1, ? = A.FP_Product_Id)\n" +
                        "AND IF(? = 'ALL', 1=1, ? = D.Dzongkhag_Serial_No)\n" +
                        "AND IF(? = 'ALL', 1=1, ? = G.Gewog_Serial_No) )\n"+
                        "AND w.Status_Id <> 7\n" +
                        "AND (w.Status_Id = 3 OR  w.Status_Id = 8 OR  w.Status_Id = 12)\n" +
                        "AND Ap.Application_Number NOT LIKE 'Draft%' GROUP BY Ap.Application_Number";
                Query hQuery = hibernateQuery(sqlQuery, ReportDto.class).setParameter(1, from_date).setParameter(2,to_date)
                        .setParameter(3, division_park_id).setParameter(4, division_park_id).setParameter(5,cons_type).setParameter(6,cons_type)
                        .setParameter(7, product_desc).setParameter(8, product_desc).setParameter(9,product_catagory).setParameter(10,product_catagory)
                        .setParameter(11, dzongkhag_name).setParameter(12, dzongkhag_name).setParameter(13, gewog_name).setParameter(14, gewog_name);
                reportDtos = hQuery.list();
            }else if (type.equalsIgnoreCase("privateLand")){
                sqlQuery = "SELECT \n" +
                        "a.CID_Number AS application_Number,\n" +
                        "g.Gewog_Name AS gewog_Name,\n" +
                        "a.Private_LandForm_Remarks AS cc_remarkPrivateLand,\n" +
                        "d.Dzongkhag_Name AS dzongkhag_Name,\n" +
                        "dv.Division_Park_Name AS division_Park_Name,\n" +
                        "r.Range_Name AS Range_Office,\n" +
                        "l.Land_Category_Name AS land_Cat,\n" +
                        "a.Acres AS acre, pm.Product_Catagory AS prod_Category,al.No_trees AS trees,\n" +
                        "al.No_poles AS poles, al.No_bamboos AS bamboos, al.Volume AS volume,\n" +
                        "(CASE \n" +
                        "WHEN w.Status_Id = '3' THEN 'Approved'\n" +
                        "WHEN w.Status_Id = '8' THEN 'Marked'\n" +
                        "WHEN w.Status_Id = '12' THEN 'Printed'\n" +
                        "END) AS app_atatus\n" +
                        "FROM  \n" +
                        "t_fp_application a\n" +
                        "LEFT JOIN t_land_category_master l ON a.Land_Category = l.Land_Category_Id\n"+
                        "LEFT JOIN t_workflow_dtls w ON a.Application_Number = w.Application_Number\n" +
                        "LEFT JOIN t_fp_appl_allotment al ON a.Application_Number = al.Application_Number\n" +
                        "LEFT JOIN t_fp_product_master pm ON al.FP_Product_Id = pm.FP_Product_Id\n" +
                        "LEFT JOIN t_range_lookup r ON a.Allot_Range_Officer = r.Range_Id\n" +
                        "LEFT JOIN t_village_lookup v ON a.Village_Serial_No = v.Village_Serial_No\n" +
                        "LEFT JOIN t_gewog_lookup g ON g.Gewog_Serial_No = v.Gewog_Serial_No\n" +
                        "LEFT JOIN t_dzongkhag_lookup d ON d.Dzongkhag_Serial_No = g.Dzongkhag_Serial_No\n" +
                        "LEFT JOIN t_division_park_lookup dv ON dv.Division_Park_Id = a.Division_Park_Id\n" +
                        "LEFT JOIN t_division_park_dzonghkag_mapping mp ON mp.Division_Park_Id =dv.Division_Park_Id\n" +
                        "WHERE\n" +
                        "a.Application_Type = 'PRL'\n" +
                        "AND a.App_Approval_Date BETWEEN ? AND ?\n" +
                        "AND (IF(? = 'ALL', 1 = 1, ? = a.Division_Park_Id)\n" +
                        "AND IF(? = 'ALL', 1=1, ? = d.Dzongkhag_Serial_No)\n" +
                        "AND IF(? = 'ALL', 1=1, ? = g.Gewog_Serial_No))\n" +
                        "AND w.Status_Id <> 7\n" +
                        "AND (w.Status_Id = 3 OR  w.Status_Id = 8 OR  w.Status_Id = 12)\n" +
                        "AND a.Application_Number NOT LIKE 'Draft%'";
                Query hQuery = hibernateQuery(sqlQuery, ReportDto.class).setParameter(1,from_date).setParameter(2,to_date)
                        .setParameter(3,division_park_id).setParameter(4, division_park_id).setParameter(5, dzongkhag_name)
                        .setParameter(6, dzongkhag_name).setParameter(7, gewog_name).setParameter(8, gewog_name);
                reportDtos = hQuery.list();
            }else if (type.equalsIgnoreCase("WoodAndPole")){
                sqlQuery = "SELECT\n" +
                        "Ap.Application_Number AS application_Number,\n" +
                        "Ap.CID_Number AS cid_Number,\n" +
                        "Ap.Application_Type AS application_Type,\n" +
                        "A.Allot_Quantity AS quantity,\n" +
                        "P.UOM AS unit,\n" +
                        "DP.Division_Park_Name AS division_Park_Name,\n" +
                        "P.Product_Catagory AS product_Category,\n" +
                        "G.Gewog_Name AS gewog_Name,\n" +
                        "A.Total_Royalty AS new_Royalty,\n" +
                        "D.Dzongkhag_Name AS dzongkhag_Name,\n" +
                        "(CASE \n" +
                        "WHEN w.Status_Id = '3' THEN 'Approved'\n" +
                        "WHEN w.Status_Id = '8' THEN 'Marked'\n" +
                        "WHEN w.Status_Id = '12' THEN 'Printed'\n" +
                        "END) AS app_atatus\n" +
                        "FROM\n" +
                        "t_fp_application Ap\n" +
                        "LEFT JOIN t_village_lookup v ON Ap.Village_Serial_No = v.Village_Serial_No\n" +
                        "LEFT JOIN t_workflow_dtls w ON Ap.Application_Number = w.Application_Number\n" +
                        "LEFT JOIN t_gewog_lookup G ON G.Gewog_Serial_No = v.Gewog_Serial_No\n" +
                        "LEFT JOIN t_fp_appl_allotment A ON Ap.Application_Number = A.Application_Number\n" +
                        "LEFT JOIN t_fp_product_master P ON A.FP_Product_Id = P.FP_Product_Id\n" +
                        "LEFT JOIN t_division_park_lookup DP ON Ap.Division_Park_Id = DP.Division_Park_Id\n" +
                        "LEFT JOIN t_dzongkhag_lookup D ON G.Dzongkhag_Serial_No = D.Dzongkhag_Serial_No\n" +
                        "WHERE \n" +
                        "Ap.App_Approval_Date BETWEEN ? AND ?\n" +
                        "AND P.Product_Desc LIKE 'WP%'\n" +
                        "AND Ap.App_Approval_Date IS NOT NULL\n" +
                        "AND A.Allot_Quantity IS NOT NULL\n" +
                        "AND (IF(? = 'ALL', 1 = 1, ? = Ap.Division_Park_Id)\n" +
                        "AND IF(? = 'ALL', 1 = 1, ? = P.FP_Product_Id)\n" +
                        "AND IF(? = 'ALL', 1=1, ? = D.Dzongkhag_Serial_No)\n" +
                        "AND IF(? = 'ALL', 1=1, ? = G.Gewog_Serial_No))\n"+
                        "AND w.Status_Id <> 7\n" +
                        "AND (w.Status_Id = 3 OR  w.Status_Id = 8 OR  w.Status_Id = 12)\n" +
                        "AND Ap.Application_Number NOT LIKE 'Draft%'";
                Query hQuery = hibernateQuery(sqlQuery, ReportDto.class).setParameter(1,from_date).setParameter(2,to_date)
                        .setParameter(3, division_park_id).setParameter(4,division_park_id).setParameter(5,product_catagory).setParameter(6, product_catagory)
                        .setParameter(7, dzongkhag_name).setParameter(8, dzongkhag_name).setParameter(9, gewog_name).setParameter(10, gewog_name);
                reportDtos = hQuery.list();
            }

        }catch (Exception e){
           e.printStackTrace();
        }
        return reportDtos;
    }

    @Override
    public List<ReportDto> generateRangeReport(String division_park_id, String cons_type, String product_catagory, String from_date, String to_date, String type) {
        List<ReportDto> reportDtos = new ArrayList<ReportDto>();
        String sqlQuery="";
        try{
            if(type.equalsIgnoreCase("timber")){
                sqlQuery = REPORTFORRURALTIMBER;
                Query hQuery = hibernateQuery(sqlQuery, ReportDto.class).setParameter(1, from_date).setParameter(2,to_date)
                        .setParameter(3, division_park_id).setParameter(4, division_park_id).setParameter(5,cons_type).setParameter(6, cons_type)
                        .setParameter(7, product_catagory).setParameter(8, product_catagory);
                reportDtos = hQuery.list();
            }
            if(type.equalsIgnoreCase("WoodAndPole")){
                sqlQuery = REPORTFORRURALTIMBERWP;
                Query hQuery = hibernateQuery(sqlQuery, ReportDto.class)
                        .setParameter(1, from_date).setParameter(2,to_date)
                        .setParameter(3, division_park_id).setParameter(4, division_park_id)
                        .setParameter(5, product_catagory).setParameter(6, product_catagory);
                reportDtos = hQuery.list();
            }
            if (type.equalsIgnoreCase("privateLand")){
                sqlQuery = REPORTFORPRIVATELAND;
                Query hQuery = hibernateQuery(sqlQuery, ReportDto.class)
                        .setParameter(1, from_date).setParameter(2,to_date)
                        .setParameter(3, division_park_id).setParameter(4, division_park_id);
                reportDtos = hQuery.list();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return reportDtos;
    }

    @Override
    public List<ReportDto> generateReportGup(String gupId, String cons_type, String product_catagory, String from_date, String to_date, String type) {
        List<ReportDto> reportDtos = new ArrayList<ReportDto>();
        String sqlQuery="";
        try{
            if(type.equalsIgnoreCase("timber")){
                sqlQuery = REPORTFORRURALTIMBERGUP;
                Query hQuery = hibernateQuery(sqlQuery, ReportDto.class).setParameter(1, from_date).setParameter(2, to_date)
                        .setParameter(3, gupId).setParameter(4,cons_type).setParameter(5, cons_type)
                        .setParameter(6, product_catagory).setParameter(7, product_catagory);
                reportDtos = hQuery.list();
            }
            if(type.equalsIgnoreCase("WoodAndPole")){
                sqlQuery = REPORTFORRURALTIMBERWP;
                Query hQuery = hibernateQuery(sqlQuery, ReportDto.class)
                        .setParameter(1, from_date).setParameter(2,to_date)
                        .setParameter(3, gupId).setParameter(4, gupId)
                        .setParameter(5, product_catagory).setParameter(6, product_catagory);
                reportDtos = hQuery.list();
            }
            if (type.equalsIgnoreCase("privateLand")){
                sqlQuery = REPORTFORPRIVATELAND;
                Query hQuery = hibernateQuery(sqlQuery, ReportDto.class)
                        .setParameter(1, from_date).setParameter(2,to_date)
                        .setParameter(3, gupId).setParameter(4, gupId);
                reportDtos = hQuery.list();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return reportDtos;
    }

    @Override
    public List<ReportDto> generateReportbyConstructionType(String locationId, String cons_type, String from_date, String to_date, String type, String product_category) {
        List<ReportDto> reportDtoList = new ArrayList<ReportDto>();
        String sqlQuery = "SELECT Ap.Application_Number AS application_Number,Ap.CID_Number AS cid_Number,Ap.House_Hold_No AS house_Hold_No,r.Range_Name AS Range_Office,\n" +
                "(CASE WHEN Ap.Cons_Type = 'r' THEN 'Renovation' WHEN Ap.Cons_Type = 'n' THEN 'New Construction' WHEN Ap.Cons_Type = 'o' THEN 'Other' END) AS cons_Type,V.Village_Name village_Name,\n" +
                "G.Gewog_Name AS gewog_Name,D.Dzongkhag_Name AS dzongkhag_Name,Ap.App_Approval_Date AS approval_Date,s.Status_Name AS app_atatus,pm.Product_Catagory product_Category\n" +
                "FROM t_fp_application Ap LEFT JOIN t_workflow_dtls w ON Ap.Application_Number = w.Application_Number\n" +
                "LEFT JOIN t_fp_appl_allotment fa ON Ap.Application_Number = fa.Application_Number\n" +
                "LEFT JOIN t_fp_product_master pm ON fa.FP_Product_Id = pm.FP_Product_Id\n" +
                "LEFT JOIN t_range_lookup r ON Ap.Allot_Range_Officer = r.Range_Id LEFT JOIN t_village_lookup v ON Ap.Village_Serial_No = v.Village_Serial_No LEFT JOIN t_gewog_lookup G ON G.Gewog_Serial_No = v.Gewog_Serial_No\n" +
                "LEFT JOIN t_dzongkhag_lookup D ON D.Dzongkhag_Serial_No = G.Dzongkhag_Serial_No LEFT JOIN t_status_lookup s ON w.Status_Id=s.Status_Id\n" +
                "WHERE Ap.App_Approval_Date BETWEEN ? AND ? AND Ap.App_Approval_Date IS NOT NULL \n" +
                "AND Ap.Geog_No=?  AND IF(? = 'ALL', 1 = 1, ? = Ap.Cons_Type) AND IF(? = 'ALL', 1 = 1, ? = fa.FP_Product_Id) AND w.Status_Id <> 7 AND w.Status_Id IN (3,8,12,18,22,23) AND Ap.Application_Number NOT LIKE 'Draft%' GROUP BY Ap.Application_Number";
        Query hQuery = hibernateQuery(sqlQuery, ReportDto.class).setParameter(1, from_date).setParameter(2,to_date).setParameter(3,locationId).setParameter(4,cons_type).setParameter(5,cons_type)
                .setParameter(6, product_category).setParameter(7, product_category);
        reportDtoList = hQuery.list();
        return reportDtoList;
    }

    @Override
    public List<ReportDto> generateReportbyConstructionStatus(String locationId, String construction_status, String from_date, String to_date, String type) {
        List<ReportDto> reportDtoList = new ArrayList<ReportDto>();
        String sqlQuery = "SELECT Ap.Application_Number AS application_Number,Ap.CID_Number AS cid_Number,Ap.House_Hold_No AS house_Hold_No,r.Range_Name AS Range_Office,\n" +
                "(CASE WHEN Ap.Cons_Type = 'r' THEN 'Renovation' WHEN Ap.Cons_Type = 'n' THEN 'New Construction' WHEN Ap.Cons_Type = 'o' THEN 'Other' END) AS cons_Type,V.Village_Name village_Name,\n" +
                "G.Gewog_Name AS gewog_Name,D.Dzongkhag_Name AS dzongkhag_Name,Ap.App_Approval_Date AS approval_Date,s.Status_Name AS app_atatus,cs.Status construction_status\n" +
                "FROM t_fp_application Ap LEFT JOIN t_workflow_dtls w ON Ap.Application_Number = w.Application_Number\n" +
                "LEFT JOIN t_fp_appl_allotment fa ON Ap.Application_Number = fa.Application_Number\n" +
                "LEFT JOIN t_construction_status cs ON Ap.Construction_Status = cs.Id\n" +
                "LEFT JOIN t_range_lookup r ON Ap.Allot_Range_Officer = r.Range_Id LEFT JOIN t_village_lookup v ON Ap.Village_Serial_No = v.Village_Serial_No LEFT JOIN t_gewog_lookup G ON G.Gewog_Serial_No = v.Gewog_Serial_No\n" +
                "LEFT JOIN t_dzongkhag_lookup D ON D.Dzongkhag_Serial_No = G.Dzongkhag_Serial_No LEFT JOIN t_status_lookup s ON w.Status_Id=s.Status_Id\n" +
                "WHERE Ap.App_Approval_Date BETWEEN ? AND ? AND Ap.App_Approval_Date IS NOT NULL \n" +
                "AND Ap.Geog_No=?  AND IF(? = 'ALL', 1 = 1, ? = Ap.Construction_Status) AND w.Status_Id <> 7 AND w.Status_Id IN (3,8,12,18,22,23) AND Ap.Application_Number NOT LIKE 'Draft%' GROUP BY Ap.Application_Number";
        Query hQuery = hibernateQuery(sqlQuery, ReportDto.class).setParameter(1,from_date).setParameter(2,to_date).setParameter(3,locationId).setParameter(4,construction_status).setParameter(5,construction_status);
        reportDtoList = hQuery.list();
        return reportDtoList;
    }

    @Override
    public List<ReportDto> generateReportByDivisionDzoGewog(String division_park_id, String dzongkhag_name, String gewog_name, String from_date, String to_date, String type) {
        List<ReportDto> reportDtos = new ArrayList<ReportDto>();
        String sqlQuery="";
        try{
           sqlQuery = "SELECT\n" +
                   "Ap.Application_Number AS application_Number,\n" +
                   "Ap.CID_Number AS cid_Number,\n" +
                   "Ap.House_Hold_No AS house_Hold_No,\n" +
                   "A.Allot_Quantity AS quantity,\n" +
                   "P.UOM AS unit,\n" +
                   "r.Range_Name AS Range_Office,\n" +
                   "A.Allot_Area AS Marking_Location,\n" +
                   "DP.Division_Park_Name AS division_Park_Name,\n" +
                   "(\n" +
                   "CASE\n" +
                   "WHEN P.Construction_Type = 'r'\n" +
                   "THEN 'Renovation'\n" +
                   "WHEN P.Construction_Type = 'n'\n" +
                   "THEN 'New Construction'\n" +
                   "WHEN P.Construction_Type = 'o'\n" +
                   "THEN 'Other'\n" +
                   "END\n" +
                   ") AS cons_Type,\n" +
                   "(\n" +
                   "CASE\n" +
                   "WHEN P.Product_Desc = 'RTP(l)'\n" +
                   "THEN 'Log Form'\n" +
                   "WHEN P.Product_Desc = 'RTP(s)'\n" +
                   "THEN 'Standing Form'\n" +
                   "END\n" +
                   ") AS product_desc,\n" +
                   "P.Product_Catagory AS product_Category,\n" +
                   "G.Gewog_Name AS gewog_Name,\n" +
                   "A.Net_Royalty AS new_Royalty,\n" +
                   "D.Dzongkhag_Name AS dzongkhag_Name,\n" +
                   "A.App_Approval_Date AS approval_Date,\n" +
                   "(CASE\n" +
                   "WHEN Ap.Claiming_status = 'New' THEN 'New Application'\n" +
                   "WHEN Ap.Claiming_status = 'reclaim' THEN 'Reclaimed Application'\n" +
                   "WHEN Ap.Claiming_status IS NULL THEN 'New Application'\n" +
                   "END) AS claim_status,\n" +
                   "(CASE \n" +
                   "WHEN w.Status_Id = '3' THEN 'Approved'\n" +
                   "WHEN w.Status_Id = '8' THEN 'Marked'\n" +
                   "WHEN w.Status_Id = '12' THEN 'Printed'\n" +
                   "END) AS app_atatus\n" +
                   "FROM\n" +
                   "t_fp_application Ap\n" +
                   "LEFT JOIN t_fp_appl_allotment A ON Ap.Application_Number = A.Application_Number\n" +
                   "LEFT JOIN t_workflow_dtls w ON Ap.Application_Number = w.Application_Number\n" +
                   "LEFT JOIN t_range_lookup r ON Ap.Allot_Range_Officer = r.Range_Id\n" +
                   "LEFT JOIN t_fp_product_master P ON A.FP_Product_Id = P.FP_Product_Id\n" +
                   "LEFT JOIN t_village_lookup v ON (Ap.Cons_Loc_Village_Serial_No = v.Village_Serial_No OR Ap.Village_Serial_No = v.Village_Serial_No)\n" +
                   "LEFT JOIN t_gewog_lookup G ON G.Gewog_Serial_No = v.Gewog_Serial_No\n" +
                   "LEFT JOIN t_dzongkhag_lookup D ON D.Dzongkhag_Serial_No = G.Dzongkhag_Serial_No\n" +
                   "LEFT JOIN t_division_park_lookup DP ON DP.Division_Park_Id = Ap.Division_Park_Id\n" +
                   "WHERE\n" +
                   "Ap.App_Approval_Date BETWEEN ? AND ?\n" +
                   "AND P.Product_Desc LIKE 'RTP%'\n" +
                   "AND Ap.App_Approval_Date IS NOT NULL \n" +
                   "AND A.Allot_Quantity IS NOT NULL\n" +
                   "AND (IF(? = 'ALL', 1 = 1,? = Ap.Division_Park_Id)\n" +
                   "AND IF(? = 'ALL', 1=1, ? = D.Dzongkhag_Serial_No)\n" +
                   "AND IF(? = 'ALL', 1=1, ? = G.Gewog_Serial_No) )\n"+
                   "AND w.Status_Id <> 7\n" +
                   "AND w.Status_Id IN (3,8,12,18,22,23)\n" +
                   "AND Ap.Application_Number NOT LIKE 'Draft%' GROUP BY Ap.Application_Number";
           Query hQuery = hibernateQuery(sqlQuery, ReportDto.class).setParameter(1, from_date).setParameter(2,to_date)
                   .setParameter(3, division_park_id).setParameter(4, division_park_id).setParameter(5, dzongkhag_name).setParameter(6, dzongkhag_name)
                   .setParameter(7, gewog_name).setParameter(8, gewog_name);
           reportDtos = hQuery.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return reportDtos;
    }

    @Override
    public List<ReportDto> generateReportForRoyaltyAmount(String rangeId, String cons_type, String product_category, String from_date, String to_date, String type) {
        List<ReportDto> reportDtos = new ArrayList<ReportDto>();
        String sqlQuery="";
        try{
            sqlQuery = "SELECT\n" +
                    "Ap.Application_Number AS application_Number,\n" +
                    "Ap.CID_Number AS cid_Number,\n" +
                    "Ap.House_Hold_No AS house_Hold_No,\n" +
                    "A.Allot_Quantity AS quantity,\n" +
                    "P.UOM AS unit,\n" +
                    "r.Range_Name AS Range_Office,\n" +
                    "A.Allot_Area AS Marking_Location,\n" +
                    "DP.Division_Park_Name AS division_Park_Name,\n" +
                    "(\n" +
                    "CASE\n" +
                    "WHEN P.Construction_Type = 'r'\n" +
                    "THEN 'Renovation'\n" +
                    "WHEN P.Construction_Type = 'n'\n" +
                    "THEN 'New Construction'\n" +
                    "WHEN P.Construction_Type = 'o'\n" +
                    "THEN 'Other'\n" +
                    "END\n" +
                    ") AS cons_Type,\n" +
                    "(\n" +
                    "CASE\n" +
                    "WHEN P.Product_Desc = 'RTP(l)'\n" +
                    "THEN 'Log Form'\n" +
                    "WHEN P.Product_Desc = 'RTP(s)'\n" +
                    "THEN 'Standing Form'\n" +
                    "END\n" +
                    ") AS product_desc,\n" +
                    "P.Product_Catagory AS product_Category,\n" +
                    "G.Gewog_Name AS gewog_Name,\n" +
                    "pinfo.Amount_Collected AS amount_collected,pinfo.Payment_Mode payment_Mode,\n" +
                    "D.Dzongkhag_Name AS dzongkhag_Name,\n" +
                    "A.App_Approval_Date AS approval_Date,\n" +
                    "(CASE\n" +
                    "WHEN Ap.Claiming_status = 'New' THEN 'New Application'\n" +
                    "WHEN Ap.Claiming_status = 'reclaim' THEN 'Reclaimed Application'\n" +
                    "WHEN Ap.Claiming_status IS NULL THEN 'New Application'\n" +
                    "END) AS claim_status,\n" +
                    "(CASE \n" +
                    "WHEN w.Status_Id = '3' THEN 'Approved'\n" +
                    "WHEN w.Status_Id = '8' THEN 'Marked'\n" +
                    "WHEN w.Status_Id = '12' THEN 'Printed'\n" +
                    "END) AS app_atatus\n" +
                    "FROM\n" +
                    "t_fp_application Ap\n" +
                    "LEFT JOIN t_fp_appl_allotment A ON Ap.Application_Number = A.Application_Number\n" +
                    "LEFT JOIN t_workflow_dtls w ON Ap.Application_Number = w.Application_Number\n" +
                    "LEFT JOIN t_range_lookup r ON Ap.Allot_Range_Officer = r.Range_Id\n" +
                    "LEFT JOIN t_fp_product_master P ON A.FP_Product_Id = P.FP_Product_Id\n" +
                    "LEFT JOIN t_village_lookup v ON (Ap.Cons_Loc_Village_Serial_No = v.Village_Serial_No OR Ap.Village_Serial_No = v.Village_Serial_No)\n" +
                    "LEFT JOIN t_gewog_lookup G ON G.Gewog_Serial_No = v.Gewog_Serial_No\n" +
                    "LEFT JOIN t_dzongkhag_lookup D ON D.Dzongkhag_Serial_No = G.Dzongkhag_Serial_No\n" +
                    "LEFT JOIN t_division_park_lookup DP ON DP.Division_Park_Id = Ap.Division_Park_Id\n" +
                    "LEFT JOIN t_payment_info pinfo ON Ap.Application_Number=pinfo.Application_Number\n" +
                    "WHERE\n" +
                    "Ap.App_Approval_Date BETWEEN ? AND ?\n" +
                    "AND P.Product_Desc LIKE 'RTP%'\n" +
                    "AND Ap.App_Approval_Date IS NOT NULL \n" +
                    "AND A.Allot_Quantity IS NOT NULL\n" +
                    "AND (IF(? = 'ALL', 1 = 1,? = Ap.Allot_Range_Officer)\n" +
                    "AND IF(? = 'ALL', 1=1, ? = Ap.Cons_Type)\n" +
                    "AND IF(? = 'ALL', 1=1, ? = A.FP_Product_Id) )\n"+
                    "AND w.Status_Id <> 7\n" +
                    "AND w.Status_Id IN (3,8,12,18,22,23)\n" +
                    "AND Ap.Application_Number NOT LIKE 'Draft%' GROUP BY Ap.Application_Number";
            Query hQuery = hibernateQuery(sqlQuery, ReportDto.class).setParameter(1,from_date).setParameter(2,to_date)
                    .setParameter(3,rangeId).setParameter(4,rangeId).setParameter(5,cons_type).setParameter(6,cons_type)
                    .setParameter(7,product_category).setParameter(8,product_category);
            reportDtos = hQuery.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return reportDtos;
    }

    public static String REPORTFORPRIVATELAND = "SELECT \n" +
            "a.CID_Number AS application_Number,\n" +
            "g.Gewog_Name AS gewog_Name,\n" +
            "a.Private_LandForm_Remarks AS cc_remarkPrivateLand,\n" +
            "d.Dzongkhag_Name AS dzongkhag_Name,\n" +
            "dv.Division_Park_Name AS division_Park_Name,\n" +
            "r.Range_Name AS Range_Office,\n" +
            "l.Land_Category_Name AS land_Cat,\n" +
            "a.Acres AS acre, pm.Product_Catagory AS prod_Category,al.No_trees AS trees,\n" +
            "al.No_poles AS poles, al.No_bamboos AS bamboos, al.Volume AS volume,\n" +
            "(CASE \n" +
            "WHEN w.Status_Id = '3' THEN 'Approved'\n" +
            "WHEN w.Status_Id = '8' THEN 'Marked'\n" +
            "WHEN w.Status_Id = '12' THEN 'Printed'\n" +
            "END) AS app_atatus\n" +
            "FROM  \n" +
            "t_fp_application a\n" +
            "LEFT JOIN t_land_category_master l ON a.Land_Category = l.Land_Category_Id\n" +
            "LEFT JOIN t_workflow_dtls w ON a.Application_Number = w.Application_Number\n" +
            "LEFT JOIN t_fp_appl_allotment al ON a.Application_Number = al.Application_Number\n" +
            "LEFT JOIN t_fp_product_master pm ON al.FP_Product_Id = pm.FP_Product_Id\n" +
            "LEFT JOIN t_range_lookup r ON a.Allot_Range_Officer = r.Range_Id\n" +
            "LEFT JOIN t_village_lookup v ON a.Village_Serial_No = v.Village_Serial_No\n" +
            "LEFT JOIN t_gewog_lookup g ON g.Gewog_Serial_No = v.Gewog_Serial_No\n" +
            "LEFT JOIN t_dzongkhag_lookup d ON d.Dzongkhag_Serial_No = g.Dzongkhag_Serial_No\n" +
            "LEFT JOIN t_division_park_lookup dv ON dv.Division_Park_Id = a.Division_Park_Id\n" +
            "LEFT JOIN t_division_park_dzonghkag_mapping mp ON mp.Division_Park_Id =dv.Division_Park_Id\n" +
            "WHERE\n" +
            "a.Application_Type = 'PRL'\n" +
            "AND a.App_Approval_Date BETWEEN ? AND ?\n" +
            "AND (IF(? = 'ALL', 1 = 1, ? = r.Range_Id)\n" +
            "AND IF('ALL' = 'ALL', 1=1, 'ALL' = d.Dzongkhag_Serial_No)\n" +
            "AND IF('ALL' = 'ALL', 1=1, 'ALL' = g.Gewog_Serial_No))\n" +
            "AND w.Status_Id <> 7\n" +
            "AND (w.Status_Id = 3 OR  w.Status_Id = 8 OR  w.Status_Id = 12)\n" +
            "AND a.Application_Number NOT LIKE 'Draft%'";

    public static String REPORTFORRURALTIMBERWP = "SELECT\n" +
            "Ap.Application_Number AS application_Number,\n" +
            "Ap.CID_Number AS cid_Number,\n" +
            "Ap.Application_Type AS application_Type,\n" +
            "A.Allot_Quantity AS quantity,\n" +
            "P.UOM AS unit,\n" +
            "DP.Division_Park_Name AS division_Park_Name,\n" +
            "P.Product_Catagory AS product_Category,\n" +
            "G.Gewog_Name AS gewog_Name,\n" +
            "A.Total_Royalty AS new_Royalty,\n" +
            "D.Dzongkhag_Name AS dzongkhag_Name,\n" +
            "(CASE \n" +
            "WHEN w.Status_Id = '3' THEN 'Approved'\n" +
            "WHEN w.Status_Id = '8' THEN 'Marked'\n" +
            "WHEN w.Status_Id = '12' THEN 'Printed'\n" +
            "END) AS app_atatus\n" +
            "FROM\n" +
            "t_fp_application Ap\n" +
            "LEFT JOIN t_village_lookup v ON Ap.Village_Serial_No = v.Village_Serial_No\n" +
            "LEFT JOIN t_workflow_dtls w ON Ap.Application_Number = w.Application_Number\n" +
            "LEFT JOIN t_gewog_lookup G ON G.Gewog_Serial_No = v.Gewog_Serial_No\n" +
            "LEFT JOIN t_fp_appl_allotment A ON Ap.Application_Number = A.Application_Number\n" +
            "LEFT JOIN t_fp_product_master P ON A.FP_Product_Id = P.FP_Product_Id\n" +
            "LEFT JOIN t_division_park_lookup DP ON Ap.Division_Park_Id = DP.Division_Park_Id\n" +
            "LEFT JOIN t_dzongkhag_lookup D ON G.Dzongkhag_Serial_No = D.Dzongkhag_Serial_No\n" +
            "LEFT JOIN t_range_lookup r ON A.Allot_Range_Officer = r.Range_Id\n" +
            "WHERE \n" +
            "Ap.App_Approval_Date BETWEEN ? AND ? \n" +
            "AND P.Product_Desc LIKE 'WP%'\n" +
            "AND Ap.App_Approval_Date IS NOT NULL\n" +
            "AND A.Allot_Quantity IS NOT NULL\n" +
            "AND (IF(? = 'ALL', 1 = 1, ? = r.Range_Id)\n" +
            "AND IF(? = 'ALL', 1 = 1, ? = P.FP_Product_Id)\n" +
            "AND IF('ALL' = 'ALL', 1=1, 'ALL' = D.Dzongkhag_Serial_No)\n" +
            "AND IF('ALL' = 'ALL', 1=1, 'ALL' = G.Gewog_Serial_No))\n" +
            "AND w.Status_Id <> 7\n" +
            "AND (w.Status_Id = 3 OR  w.Status_Id = 8 OR  w.Status_Id = 12)\n" +
            "AND Ap.Application_Number NOT LIKE 'Draft%'";

    public static String REPORTFORRURALTIMBER = "SELECT\n" +
            "Ap.Application_Number AS application_Number,\n" +
            "Ap.CID_Number AS cid_Number,\n" +
            "Ap.House_Hold_No AS house_Hold_No,\n" +
            "A.Allot_Quantity AS quantity,\n" +
            "P.UOM AS unit,\n" +
            "r.Range_Name AS Range_Office,\n" +
            "A.Allot_Area AS Marking_Location,\n" +
            "DP.Division_Park_Name AS division_Park_Name,\n" +
            "(\n" +
            "CASE\n" +
            "WHEN P.Construction_Type = 'r'\n" +
            "THEN 'Renovation'\n" +
            "WHEN P.Construction_Type = 'n'\n" +
            "THEN 'New Construction'\n" +
            "WHEN P.Construction_Type = 'o'\n" +
            "THEN 'Other'\n" +
            "END\n" +
            ") AS cons_Type,\n" +
            "(\n" +
            "CASE\n" +
            "WHEN P.Product_Desc = 'RTP(l)'\n" +
            "THEN 'Log Form'\n" +
            "WHEN P.Product_Desc = 'RTP(s)'\n" +
            "THEN 'Standing Form'\n" +
            "END\n" +
            ") AS product_desc,\n" +
            "P.Product_Catagory AS product_Category,\n" +
            "G.Gewog_Name AS gewog_Name,\n" +
            "A.Net_Royalty AS new_Royalty,\n" +
            "D.Dzongkhag_Name AS dzongkhag_Name,\n" +
            "A.App_Approval_Date AS approval_Date,\n" +
            "(CASE\n" +
            "WHEN Ap.Claiming_status = 'New' THEN 'New Application'\n" +
            "WHEN Ap.Claiming_status = 'reclaim' THEN 'Reclaimed Application'\n" +
            "WHEN Ap.Claiming_status IS NULL THEN 'New Application'\n" +
            "END) AS claim_status,\n" +
            "(CASE \n" +
            "WHEN w.Status_Id = '3' THEN 'Approved'\n" +
            "WHEN w.Status_Id = '8' THEN 'Marked'\n" +
            "WHEN w.Status_Id = '12' THEN 'Printed'\n" +
            "END) AS app_atatus\n" +
            "FROM\n" +
            "t_fp_application Ap\n" +
            "LEFT JOIN t_fp_appl_allotment A ON Ap.Application_Number = A.Application_Number\n" +
            "LEFT JOIN t_workflow_dtls w ON Ap.Application_Number = w.Application_Number\n" +
            "LEFT JOIN t_range_lookup r ON A.Allot_Range_Officer = r.Range_Id\n" +
            "LEFT JOIN t_fp_product_master P ON A.FP_Product_Id = P.FP_Product_Id\n" +
            "LEFT JOIN t_village_lookup v ON Ap.Village_Serial_No = v.Village_Serial_No\n" +
            "LEFT JOIN t_gewog_lookup G ON G.Gewog_Serial_No = v.Gewog_Serial_No\n" +
            "LEFT JOIN t_dzongkhag_lookup D ON D.Dzongkhag_Serial_No = G.Dzongkhag_Serial_No\n" +
            "LEFT JOIN t_division_park_lookup DP ON DP.Division_Park_Id = Ap.Division_Park_Id\n" +
            "LEFT JOIN t_division_park_dzonghkag_mapping MP ON MP.Division_Park_Id =DP.Division_Park_Id\n" +
            "WHERE\n" +
            "Ap.App_Approval_Date BETWEEN ? AND ?\n" +
            "AND P.Product_Desc LIKE 'RTP%'\n" +
            "AND Ap.App_Approval_Date IS NOT NULL \n" +
            "AND A.Allot_Quantity IS NOT NULL\n" +
            "-- AND Ap.Division_Park_Id = DP.Division_Park_Id\n" +
            "AND (IF(? = 'ALL', 1 = 1,? = r.Range_Id)\n" +
            "AND IF(? = 'ALL', 1 = 1, ? = Ap.Cons_Type)\n" +
            "AND IF(? = 'ALL', 1 = 1, ? = A.FP_Product_Id)\n" +
            "AND IF('ALL' = 'ALL', 1=1, 'ALL'= D.Dzongkhag_Serial_No)\n" +
            "AND IF('ALL'= 'ALL', 1=1, 'ALL' = G.Gewog_Serial_No) )\n" +
            "AND w.Status_Id <> 7\n" +
            "AND (w.Status_Id = 3 OR  w.Status_Id = 8 OR  w.Status_Id = 12)\n" +
            "AND Ap.Application_Number NOT LIKE 'Draft%' GROUP BY Ap.Application_Number";
    public static String REPORTFORRURALTIMBERGUP = "SELECT\n" +
            "Ap.Application_Number AS application_Number,\n" +
            "Ap.CID_Number AS cid_Number,\n" +
            "Ap.House_Hold_No AS house_Hold_No,\n" +
            "A.Allot_Quantity AS quantity,\n" +
            "P.UOM AS unit,\n" +
            "r.Range_Name AS Range_Office,\n" +
            "A.Allot_Area AS Marking_Location,\n" +
            "DP.Division_Park_Name AS division_Park_Name,\n" +
            "(\n" +
            "CASE\n" +
            "WHEN P.Construction_Type = 'r'\n" +
            "THEN 'Renovation'\n" +
            "WHEN P.Construction_Type = 'n'\n" +
            "THEN 'New Construction'\n" +
            "WHEN P.Construction_Type = 'o'\n" +
            "THEN 'Other'\n" +
            "END\n" +
            ") AS cons_Type,\n" +
            "(\n" +
            "CASE\n" +
            "WHEN P.Product_Desc = 'RTP(l)'\n" +
            "THEN 'Log Form'\n" +
            "WHEN P.Product_Desc = 'RTP(s)'\n" +
            "THEN 'Standing Form'\n" +
            "END\n" +
            ") AS product_desc,\n" +
            "P.Product_Catagory AS product_Category,\n" +
            "G.Gewog_Name AS gewog_Name,\n" +
            "A.Net_Royalty AS new_Royalty,\n" +
            "D.Dzongkhag_Name AS dzongkhag_Name,\n" +
            "A.App_Approval_Date AS approval_Date,\n" +
            "(CASE\n" +
            "WHEN Ap.Claiming_status = 'New' THEN 'New Application'\n" +
            "WHEN Ap.Claiming_status = 'reclaim' THEN 'Reclaimed Application'\n" +
            "WHEN Ap.Claiming_status IS NULL THEN 'New Application'\n" +
            "END) AS claim_status,s.Status_Name AS app_atatus\n" +
            "FROM\n" +
            "t_fp_application Ap\n" +
            "LEFT JOIN t_fp_appl_allotment A ON Ap.Application_Number = A.Application_Number\n" +
            "LEFT JOIN t_workflow_dtls w ON Ap.Application_Number = w.Application_Number\n" +
            "LEFT JOIN t_range_lookup r ON A.Allot_Range_Officer = r.Range_Id\n" +
            "LEFT JOIN t_fp_product_master P ON A.FP_Product_Id = P.FP_Product_Id\n" +
            "LEFT JOIN t_village_lookup v ON Ap.Village_Serial_No = v.Village_Serial_No\n" +
            "LEFT JOIN t_gewog_lookup G ON G.Gewog_Serial_No = v.Gewog_Serial_No\n" +
            "LEFT JOIN t_dzongkhag_lookup D ON D.Dzongkhag_Serial_No = G.Dzongkhag_Serial_No\n" +
            "LEFT JOIN t_division_park_lookup DP ON DP.Division_Park_Id = Ap.Division_Park_Id\n" +
            "LEFT JOIN t_division_park_dzonghkag_mapping MP ON MP.Division_Park_Id =DP.Division_Park_Id\n" +
            "LEFT JOIN t_status_lookup s ON w.Status_Id=s.Status_Id\n"+
            "WHERE\n" +
            "Ap.App_Approval_Date BETWEEN ? AND ?\n" +
            "AND P.Product_Desc LIKE 'RTP%'\n" +
            "AND Ap.App_Approval_Date IS NOT NULL \n" +
            "AND A.Allot_Quantity IS NOT NULL\n" +
            "AND Ap.Geog_No=?\n" +
            "AND IF(? = 'ALL', 1 = 1, ? = Ap.Cons_Type)\n" +
            "AND IF(? = 'ALL', 1 = 1, ?= A.FP_Product_Id)\n" +
            "AND w.Status_Id <> 7\n" +
            "AND w.Status_Id IN (3,8,12,18,23)\n" +
            "AND Ap.Application_Number NOT LIKE 'Draft%' GROUP BY Ap.Application_Number";
}
