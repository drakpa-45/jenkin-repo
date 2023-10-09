package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.base.BaseDao;
import bt.gov.ditt.dofps.dto.CitizenDetailsDTO;
import bt.gov.ditt.dofps.dto.CommonDto;
import bt.gov.ditt.dofps.dto.NewTimberDto;
import bt.gov.ditt.dofps.entitiy.TimberEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pema Drakpa on 2/19/2020.
 */
@Repository
public class DaoWoodPole extends BaseDao implements IDaoWoodPole{

    @Override
    public List<CommonDto> getProductDetails(String cons_desc) {
        List<CommonDto> dropDownList = new ArrayList<CommonDto>();
        try {
                String sqlQuery = "select a.FP_Product_Id AS header_id, a.Product_Catagory AS header_Name from t_fp_product_master a WHERE a.Product_Desc = ? AND a.FP_Product_Id <> 8";
                Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter(1,cons_desc);
                dropDownList = hQuery.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dropDownList;
    }

    @Override
    public List<CommonDto> getUnitDetails(String sl_no, String type) {
        List<CommonDto> dtoList = new ArrayList<CommonDto>();
        try {
            if (type.equalsIgnoreCase("select_Unit")) {
                String sqlQuery = "SELECT p.FP_Product_Id AS header_id,p.UOM AS header_Name FROM t_fp_product_master p WHERE FP_Product_Id=:Type_Id";
                Query hQuery = hibernateQuery(sqlQuery, CommonDto.class).setParameter("Type_Id", sl_no);
                dtoList = hQuery.list();
            }
        } catch (Exception e) {
            System.out.print(e);
            e.printStackTrace();
        }
        return dtoList;
    }

    @Override
    public long getServiceId() {
        long Service_Id = 0;
        try {
            String sql = "SELECT Service_Id FROM t_service_lookup WHERE Service_Short_Desc = ?";
            org.hibernate.query.Query query = sqlQuery1(sql).setParameter(1,"WP");
            if (query.list().size() > 0) {
                Service_Id = (Integer) query.list().get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Service_Id;
    }

    @Override
    public NewTimberDto saveUpdateApplication(CitizenDetailsDTO dto, NewTimberDto newTimberDto, String appNo, String draftNo) {
        NewTimberDto saveApplication = new NewTimberDto();
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE t_fp_application SET Application_Number =?, App_Submission_Date=CURRENT_DATE WHERE Application_Number =?", NewTimberDto.class);
            query1.setParameter(1, appNo).setParameter(2, draftNo);
            int save = query1.executeUpdate();
            saveApplication.setApplication_Number(appNo);
        } catch (Exception e) {
            System.out.println("issue while updating: # saveUpdateApplication #" + e);
        }
        return saveApplication;
    }

    @Override
    public String saveTimberdtls(TimberEntity timberEntity) {
        try {
            saveOrUpdate(timberEntity);
            return "Success";
        } catch (Exception e) {
            System.out.print("Exception in personal detail # saveApplicationDetails: " + e);
            return "Exception from saveTimber+" + e;
        }
    }
}
