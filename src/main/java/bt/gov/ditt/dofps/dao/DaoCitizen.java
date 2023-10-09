package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.base.BaseDao;
import bt.gov.ditt.dofps.dto.NewTimberDto;
import bt.gov.ditt.dofps.dto.OnlineTimberDTO;
import bt.gov.ditt.dofps.dto.TimberDetailsDto;
import bt.gov.ditt.dofps.dto.WorkFlowDto;
import bt.gov.ditt.dofps.entitiy.PersonalEntity;
import bt.gov.ditt.dofps.entitiy.TimberEntity;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Santosh on 7/7/2022.
 */
@Repository
public class DaoCitizen extends BaseDao implements IDaoCitizen {

    @Override
    public String saveOnlineTimberApplication(PersonalEntity personalEntity) {
        try {
            saveOrUpdate(personalEntity);
            return "Success";

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Exception in timber detail # saveApplicationDetails: " + e);
            return "Exception from saveApplicationDetails+" + e;
        }
      //  return personalEntity;
    }

    @Override
    public String saveTimber(TimberEntity timberEntity) {
        try {
            saveOrUpdate(timberEntity);
            return "Success";

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Exception in timber detail # saveApplicationDetails: " + e);
            return "Exception from saveApplicationDetails+" + e;
        }
    }

    @Override
    public List<NewTimberDto> getDropdownList(String id, String type,HttpServletRequest request) {
        List<NewTimberDto> dtoList = new ArrayList<NewTimberDto>();
        try {
            if(type.equalsIgnoreCase("parkList")){
                String sqlQuery = "SELECT \n" +
                        "dpl.Division_Park_Id AS header_id,\n" +
                        "dpl.Division_Park_Name AS header_Name \n" +
                        "FROM\n" +
                        "t_division_park_lookup dpl \n" +
                        "LEFT JOIN t_division_park_dzonghkag_mapping d \n" +
                        "ON d.Division_Park_Id = dpl.Division_Park_Id \n" +
                        "LEFT JOIN t_dzongkhag_lookup dl \n" +
                        "ON dl.Dzongkhag_Serial_No = d.Dzongkhag_Serial_No \n" +
                        "WHERE dl.Dzongkhag_Serial_No =?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class).setParameter(1,id);
                dtoList = hQuery.list();
            }else if (type.equalsIgnoreCase("villegeList")){
                String sqlQuery = "SELECT \n" +
                        "v.Village_Serial_No AS header_id,\n" +
                        "v.Village_Name AS header_Name \n" +
                        "FROM t_village_lookup v \n" +
                        "WHERE v.Gewog_Serial_No =?";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class).setParameter(1,id);
                dtoList = hQuery.list();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dtoList;
    }

    @Override
    public List<OnlineTimberDTO> getApplicationList(String appNo) {
        List<OnlineTimberDTO> applicationList = new ArrayList<OnlineTimberDTO>();
        try {
                String sqlQuery = "";
                org.hibernate.Query hQuery = hibernateQuery(sqlQuery, NewTimberDto.class).setParameter(1,appNo);
            applicationList = hQuery.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return applicationList;
    }

    @Override
    public Integer getApplicationStatus(String appNo) {
        String sqlQuery = "SELECT c.Status_Id FROM t_workflow_dtls c WHERE c.Application_Number=?";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter(1,appNo);
        return (Integer) hQuery.list().get(0);
    }

    @Override
    public WorkFlowDto getTimberReplacementRequest(String appNo) {
        String sqlQuery = "SELECT a.Application_Number AS application_Number,b.Allot_Quantity AS allot_Quantity,b.Appl_Quantity AS appl_Quantity,\n" +
                "a.House_Hold_No AS house_Hold_No,a.House_No house_No,a.Thram_No thram_No,r.Range_Name document_Name,dl.DzongKhag_Name AS dzongkhag_Name,CAST(a.App_Submission_Date AS CHAR CHARACTER SET utf8) AS app_Submission_Date,\n" +
                "v.Village_Name AS village_Name,g.Gewog_Name AS gewog_Name,a.CID_Number AS cid_Number,DATE_FORMAT(a.Permit_Validity_Date,'%d-%m-%Y') permit_Validity_Date,a.Officer_on_Duty officer_on_Duty,\n" +
                "a.Member_of_Forest_community AS member_of_Forest_community,a.Full_Name AS name,DATE_FORMAT(a.App_Approval_Date,'%d-%m-%Y') AS app_Approval_Date,a.Sawing_Permit_Date sawingPermitDate,\n" +
                "DATE_FORMAT(a.App_Marking_Schedule_Date,'%d-%m-%Y') AS app_Marking_Schedule_Date,a.Allot_Range_Officer allot_Range_Officer,m.Mode_of_Swing_Desc mode_of_Swing_Desc,dpl.Division_Park_Name division_Park_Name,\n" +
                "a.Cons_Approval_No AS cons_Approval_No,a.Roofing_Type AS roofing_Type,a.Payment_Status payment_status,a.Name_of_Sawmill name_of_Sawmill,a.LicenseNo licenseNo,a.Location_of_Sawmill location_of_Sawmill,a.Sawing_Rate sawing_Rate,\n" +
                "a.Phone_Number AS phone_Number,a.Storey_House AS storey_House,pm.Product_Catagory product_Catagory,a.PermitExpiryDate permitExpiryDate,a.Allot_Area allot_Area,a.Permit_Number permit_Number,a.Receipt_Date receipt_Date,\n" +
                "CASE\n" +
                "WHEN a.Cons_Type='n' THEN 'New Construction'\n" +
                "WHEN a.Cons_Type='r' THEN 'Renovation'\n" +
                "WHEN a.Cons_Type='o' THEN 'Other Rural Constructions'\n" +
                "END AS cons_Type,a.Acres AS acres,a.Peg_No AS peg_No, a.Plot_No AS plot_No, a.GPS_Coordinates AS gps_Coordinates, la.Land_Category_Name AS land_Category_Name\n" +
                "FROM t_fp_application a LEFT JOIN t_fp_appl_allotment b ON  a.Application_Number = b.Application_Number LEFT JOIN t_village_lookup v ON a.Village_Serial_No = v.Village_Serial_No \n" +
                "LEFT JOIN t_fp_product_master pm ON pm.FP_Product_Id=b.`FP_Product_Id` LEFT JOIN t_division_park_lookup dpl ON a.Division_Park_Id =dpl.Division_Park_Id\n" +
                "LEFT JOIN t_gewog_lookup g ON g.Gewog_Serial_No = v.Gewog_Serial_No LEFT JOIN t_dzongkhag_lookup dl ON dl.Dzongkhag_Serial_No = g.Dzongkhag_Serial_No\n" +
                "LEFT JOIN t_range_lookup r ON a.Allot_Range_Officer=r.Range_Id LEFT JOIN t_mode_of_swing m ON a.Mode_of_Swing_Id = m.Mode_of_Swing_Id\n" +
                "LEFT JOIN t_land_category_master la ON a.Land_Category = la.Land_Category_Id\n" +
                " WHERE a.Application_Number =? AND b.FP_Product_Id = pm.FP_Product_Id;";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1,appNo);
        return (WorkFlowDto) hQuery.list().get(0);
    }

    @Override
    public String submitTimberRR(String appNo, TimberDetailsDto timberDetails) {
        int save =0;
        String applicationstatus="";

        String update_wrkflow = "UPDATE t_fp_appl_allotment SET Replace_Quantity=? WHERE Application_Number=? AND FP_Product_Id=?";
        Query query1 = (Query) hibernateQuery(update_wrkflow).setParameter(1, timberDetails.getQuantityToReplace()).setParameter(2, appNo).setParameter(3, timberDetails.getfP_Product_Id());
        save = query1.executeUpdate();
        if (save > 0) {
            applicationstatus="Success";
        }else{
            applicationstatus="Failed";
        }
        return applicationstatus;
    }

    @Override
    public String updateMarkingDate(String appNo, String markingDate) {
        String msg="";
        String update_wrkflow = "UPDATE t_fp_application SET Marking_Date=?,App_Marking_Schedule_Date=? WHERE Application_Number=?";
        Query query1 = (Query) hibernateQuery(update_wrkflow).setParameter(1, markingDate).setParameter(2,markingDate).setParameter(3,appNo);
        int save =query1.executeUpdate();
        if(save>0){
            msg="Success";
        }else{
            msg="Failed";
        }
        return msg;
    }

    @Override
    public String scheduleSawingPermitDate(String appNo, String sawingPermitDate, String nameOfOperator, String licenseNo, String locationOfSawmill, String rateOfSawing, int mode_of_Swing_Id) {
        String msg="";
        String update_wrkflow = "UPDATE t_fp_application SET Sawing_Permit_Date=?,Name_of_Sawmill=?,LicenseNo=?,Location_of_Sawmill=?,Sawing_Rate=?,Mode_of_Swing_Id=? WHERE Application_Number=?";
        Query query1 = (Query) hibernateQuery(update_wrkflow).setParameter(1, sawingPermitDate).setParameter(2,nameOfOperator).setParameter(3,licenseNo).setParameter(4,locationOfSawmill)
                .setParameter(5,rateOfSawing).setParameter(6,mode_of_Swing_Id).setParameter(7,appNo);
        int save =query1.executeUpdate();
        if(save>0){
            msg="Success";
        }else{
            msg="Failed";
        }
        return msg;
    }

    @Override
    public BigInteger getExistDateCount(String thisDate, Integer allot_Range_Officer) {
        String sqlQuery = "SELECT COUNT(*) FROM t_fp_application a WHERE a.Marking_Date=? and a.Allot_Range_Officer=?";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter(1,thisDate).setParameter(2,allot_Range_Officer);
        return (BigInteger) hQuery.list().get(0);
    }

    @Override
    public String updateCompletionDate(String appNo, String completionDate, int completionStatus) {
        String msg="";
        String update_wrkflow = "UPDATE t_fp_application SET Completed_Date=?,Construction_Status=? WHERE Application_Number=?";
        Query query1 = (Query) hibernateQuery(update_wrkflow).setParameter(1, completionDate).setParameter(2,completionStatus).setParameter(3,appNo);
        int save =query1.executeUpdate();
        if(save>0){
            msg="Success";
        } else{
            msg="Failed";
        }
        return msg;
    }

    @Override
    public Integer getReplacementCount(String appNo) {
        String sqlQuery = "SELECT c.ReplacementCount FROM t_fp_application c WHERE c.Application_Number=?";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter(1,appNo);
        return (Integer) hQuery.list().get(0);
    }

    @Override
    public String updateReplacementCount(String appNo) {
        String msg="";
        String update_wrkflow = "UPDATE t_fp_application SET ReplacementCount=?,Marking_Date='' WHERE Application_Number=?";
        Query query1 = (Query) hibernateQuery(update_wrkflow).setParameter(1, 1).setParameter(2,appNo);
        int save =query1.executeUpdate();
        if(save>0){
            msg="Success";
        }else{
            msg="Failed";
        }
        return msg;
    }
}
