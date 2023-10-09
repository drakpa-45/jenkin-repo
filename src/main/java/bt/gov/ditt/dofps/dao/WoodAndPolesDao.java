package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.base.BaseDao;
import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.entitiy.TimberEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pema Drakpa on 06/03/2023.
 */
@Repository
public class WoodAndPolesDao extends BaseDao{

    public String updateAllot(TimberDetailsDto timberDetails, String appNo, String remarks) {
        String msg="";
        try {
            String sqlQuery = "UPDATE t_fp_appl_allotment SET Allot_Quantity = ?, Net_Royalty = ?, Total_Royalty = ?,App_Approval_Date = CURRENT_DATE, Approval_Remarks=?,Royalty_Rate=?\n" +
                    "WHERE `Application_Number`=? AND FP_Product_Id=?";
            Query hQuery = hibernateQuery(sqlQuery, WorkFlowDto.class).setParameter(1, timberDetails.getAllot_Quantity()).setParameter(2, timberDetails.getRoyalty_Rate())
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

    public String updatePaymentStatus(String appNo, OnlineTimberDTO timberDto) {
        int save =0;
        String applicationstatus="";

        String update_wrkflow = "UPDATE t_fp_application SET Payment_Status=?,Allot_Range_Officer=?,App_Approval_Date=CURRENT_DATE,Allot_Area=? WHERE Application_Number=?";
        org.hibernate.query.Query query1 = (org.hibernate.query.Query) hibernateQuery(update_wrkflow).setParameter(1, "Unpaid").setParameter(2,timberDto.getAllot_Range_Officer())
                .setParameter(3,timberDto.getAllot_Area()).setParameter(4, appNo);
        save = query1.executeUpdate();
        if (save > 0) {
            applicationstatus="Success";
        }else{
            applicationstatus="Failed";
        }
        return applicationstatus;
    }

}
