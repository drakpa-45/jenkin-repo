package bt.gov.ditt.dofps.dao;

import bt.gov.ditt.dofps.dto.CommonDto;
import bt.gov.ditt.dofps.dto.NewTimberDto;
import bt.gov.ditt.dofps.dto.ReportDto;
import bt.gov.ditt.dofps.dto.WorkFlowDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Tshedup Gyeltshen on 4/20/2020.
 */
public interface IdaoReportService {
    public List<NewTimberDto> getParkList(HttpServletRequest request);

    public List<CommonDto> getDropdownDetails(String sl_No, String type, String cons_Type);

    public List<NewTimberDto> getConstype(HttpServletRequest request);

    public List<NewTimberDto> getTimberForm(HttpServletRequest request);

    public List<ReportDto> generateReport(String division_park_id, String dzongkhag_name, String gewog_name, String cons_type, String product_desc, String product_catagory, String from_date, String to_date, String type);

    public List<NewTimberDto> getWoodAndPoleType(HttpServletRequest request);

    public List<NewTimberDto> getJurisdication(HttpServletRequest request, String locationId);

    public List<CommonDto> getDropdown(String sl_no, String type, String cons_type);

    public List<NewTimberDto> getRangeLocation(HttpServletRequest request, String locationId);

    public List<ReportDto> generateRangeReport(String division_park_id, String cons_type, String product_catagory, String from_date, String to_date, String type);

    public List<ReportDto> generateReportGup(String gupId, String cons_type, String product_catagory, String from_date, String to_date, String type);

    public List<ReportDto> generateReportbyConstructionType(String locationId, String cons_type, String from_date, String to_date, String type, String product_category);

    public List<ReportDto> generateReportbyConstructionStatus(String locationId, String construction_status, String from_date, String to_date, String type);

    public List<ReportDto> generateReportByDivisionDzoGewog(String division_park_id, String dzongkhag_name, String gewog_name, String from_date, String to_date, String type);

    public List<ReportDto> generateReportForRoyaltyAmount(String rangeId, String cons_type, String product_category, String from_date, String to_date, String type);
}
