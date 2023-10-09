package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.common.ResponseMessage;
import bt.gov.ditt.dofps.dto.CommonDto;
import bt.gov.ditt.dofps.dto.NewTimberDto;
import bt.gov.ditt.dofps.dto.ReportDto;
import bt.gov.ditt.dofps.dto.WorkFlowDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by USER on 4/20/2020.
 */
public interface IReportService {
    public List<NewTimberDto> getParkList(HttpServletRequest request);

    public List<CommonDto> getDropdownDetails(String slNo, String type, String cons_Type);

    public List<NewTimberDto> getConstype(HttpServletRequest request);

    public List<NewTimberDto> getTimberForm(HttpServletRequest request);

    public List<ReportDto> generateReport(String division_park_id, String dzongkhag_name, String gewog_name, String cons_type, String product_desc, String product_catagory, String from_date, String to_date, String type);

    public List<NewTimberDto> getWoodAndPoleType(HttpServletRequest request);

    public List<NewTimberDto> getJurisdication(HttpServletRequest request, String locationId);

    public List<CommonDto> getDropdown(String sl_no, String type, String cons_type);

    public List<NewTimberDto> getRangeLocation(HttpServletRequest request, String locationId);

    public List<ReportDto> generateRangeReport(String division_park_id, String cons_type, String product_catagory, String from_date, String to_date, String type);

    public ResponseMessage generateReportGup(String locationId, String cons_type, String product_category, String from_date, String to_date, String type, HttpServletRequest request);

    public ResponseMessage generateReportCFO(String division_park_id, String dzongkhag_name, String gewog_name, String from_date, String to_date, String type, HttpServletRequest request, String cons_Type, String product_Category, String rangeId);
}
