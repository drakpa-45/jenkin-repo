package bt.gov.ditt.dofps.certification;/*
package bt.gov.ditt.dofps.certification;


import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * ====================================================================
 * Created by Pema Drakpa on 7/18/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 *//*

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(value ={"/printInfoCert"})
public class CertificateController {

    private PrintPDFUtility printPDFUtility;

    public void setPrintPDFUtility(PrintPDFUtility printPDFUtility) {
        this.printPDFUtility = printPDFUtility;
    }

    @RequestMapping(value = "/printCertificateBirthReg", method = RequestMethod.GET)
    public void printCertificateContractor (ModelMap model,HttpServletRequest request,HttpServletResponse response) {
        String applicationNumber = request.getParameter("applicationNumber");
        File filepath = null;
        String initialRegistrationDate = "";
        String revalidationDate = "";
        String ownerName = "";
        String ownerCID ="";
        String regExpiryDate ="";
        String engineerType ="";
        String dzongkhagName ="";
        String newPhotoPath = null;
        String applicationServices = "contractor";

        JasperPrint jasperprint = new JasperPrint();
        List<JasperPrint> jasperPrintList=new ArrayList<JasperPrint>();
        CertificateDTO certificateDTO = new CertificateDTO();
        String url =null;
        try {

            //   url = request.getSession().getServletContext().getRealPath("/resources/JasperCertificate");
            filepath = new File(request.getSession().getServletContext().getRealPath("/resources/JasperCertificate"));
          */
/*  jasperPrintList = printPDFUtility.getJasperPrintForExporting(
                    filepath.getPath(),
                    applicationNumber,
                    initialRegistrationDate,
                    ownerCID,
                    regExpiryDate,
                    dzongkhagName,
                    ownerName,
                    response,applicationServices,revalidationDate,engineerType);*//*

            //ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/pdf;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=Contractor.pdf");
            JRPdfExporter exporter = new JRPdfExporter();
            ServletOutputStream out = response.getOutputStream();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e);
            // return null;
        }
        //  return null;
    }
}
*/
