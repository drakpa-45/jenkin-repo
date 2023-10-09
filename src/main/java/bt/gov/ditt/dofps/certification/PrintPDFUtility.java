
package bt.gov.ditt.dofps.certification;

import bt.gov.ditt.dofps.dto.WorkFlowDto;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.jfree.util.Log;

import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Pema Drakpa on 7/22/2020.
 */

public  class PrintPDFUtility{
    String url = null;
    public final List<JasperPrint> getJasperPrintForExporting(
            final String url, HttpServletResponse response, WorkFlowDto workFlowDto, String fileType, String totalVolume) {

            response.setContentType("application/pdf;charset=UTF-8");
            Log.debug("######## Enter PrintPdfUtility[getJasperPrintForExporting] ");
            JasperPrint jasperprint = new JasperPrint();
            Map<String, Object> parameters = new HashMap<String, Object>();
            List<JasperPrint> jasperPrintList=new ArrayList<JasperPrint>();
            String filepath = "";

            String leftLogo = "";
            String rightLogo = "";
            String logo = "";
            String waterMark = "";
            Connection connection=null;
           LocalDate currentDate = LocalDate.now();
        String generatedDate = currentDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            try {
                leftLogo = url + "/jasperImages/dofpsImages/DOFLogo1.png";
                rightLogo = url + "/jasperImages/dofpsImages/DoFPS_New_Logo.png";
                logo = url + "/jasperImages/dofpsImages/back-ground.png";

                parameters.put("URL", url);
                parameters.put("left", leftLogo);
                parameters.put("right", rightLogo);
                parameters.put("logo", logo);

                parameters.put("water_mark", waterMark);

                parameters.put("cidNo",workFlowDto.getCid_Number() );
                parameters.put("name",workFlowDto.getName() );
                parameters.put("constructionType",workFlowDto.getCons_Type() );
                parameters.put("application_Number",workFlowDto.getApplication_Number() );
                parameters.put("house_Hold_No", workFlowDto.getHouse_Hold_No());
                parameters.put("house_No", workFlowDto.getHouse_No());
                parameters.put("thram_No", workFlowDto.getThram_No());
                parameters.put("rangeOfficer", workFlowDto.getDocument_Name());
                parameters.put("dzongkhag_Name", workFlowDto.getDzongkhag_Name());
                parameters.put("village_Name", workFlowDto.getVillage_Name());
                parameters.put("gewog_Name", workFlowDto.getGewog_Name());
                parameters.put("issueDate", workFlowDto.getApp_Submission_Date());
                parameters.put("permitExpiryDate", workFlowDto.getPermitExpiryDate());
                parameters.put("allot_Area", workFlowDto.getAllot_Area());
                parameters.put("generatedDate", generatedDate);
                parameters.put("totalAmount", workFlowDto.getTotal_Royalty());
                parameters.put("permitDate", workFlowDto.getReceipt_Date());
                parameters.put("permitExpDate", workFlowDto.getPermitExpiryDate());
                parameters.put("permitNumber", workFlowDto.getPermit_Number());
                parameters.put("division_Park_Name", workFlowDto.getDivision_Park_Name());

                parameters.put("totalVolume",totalVolume);
                parameters.put("sawingPermitDate",workFlowDto.getSawingPermitDate());
                parameters.put("name_of_Sawmill",workFlowDto.getName_of_Sawmill());
                parameters.put("licenseNo",workFlowDto.getLicenseNo());
                parameters.put("location_of_Sawmill",workFlowDto.getLocation_of_Sawmill());
                parameters.put("permit_Validity_Date",workFlowDto.getPermit_Validity_Date());
                parameters.put("officer_on_Duty",workFlowDto.getOfficer_on_Duty());
                parameters.put("sawing_Rate",workFlowDto.getSawing_Rate());
                parameters.put("mode_of_Swing_Desc",workFlowDto.getMode_of_Swing_Desc());

                /*FOR FOREST PRODUCE CLEARANCE*/
                parameters.put("acres",workFlowDto.getAcres());
                parameters.put("peg_No",workFlowDto.getPeg_No());
                parameters.put("plot_No",workFlowDto.getPlot_No());
                parameters.put("gps_Coordinates",workFlowDto.getGps_Coordinates());
                parameters.put("land_Category_Name",workFlowDto.getLand_Category_Name());


                if(fileType.equalsIgnoreCase("allotmentOrder")){
                    filepath = url + "/jasperFiles/allotmentOrder.jasper";
                }else if(fileType.equalsIgnoreCase("permit")){
                    filepath = url + "/jasperFiles/permit.jasper";
                }else if(fileType.equalsIgnoreCase("FPC")){
                    filepath = url + "/jasperFiles/forestClearance.jasper";
                }else{
                    filepath = url + "/jasperFiles/sawingPermit.jasper";
                }

              // connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/dofps","root","root");
                connection= DriverManager.getConnection("jdbc:mysql://172.30.79.17:3306/dofps","dofps_usr","dofpsUser@2023#");

                //JasperReport jasperreport = (JasperReport) JRLoader.loadObjectFromFile(filepath);
                JasperReport jasperreport = (JasperReport) JRLoader.loadObjectFromFile(filepath.replace("\\", "//"));
                parameters.put(net.sf.jasperreports.engine.JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);
                jasperprint = JasperFillManager.fillReport(jasperreport, parameters, connection);
                jasperPrintList.add(jasperprint);

                jasperprint = JasperFillManager.fillReport(jasperreport, parameters, connection);
                Log.debug("######## Info PrintPdfUtility[getJasperPrintForExporting] Printing is Done!");
            }catch (Exception e){
                e.printStackTrace();
                Log.debug("######## Info PrintPdfUtility[getJasperPrintForExporting] Printing is Done!" + e);
            }
        Log.debug("######## Info PrintPdfUtility[getJasperPrintForExporting] Printing is Done!");
        return jasperPrintList;
    }
}
