package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.common.ResponseMessage;
import bt.gov.ditt.dofps.dao.IdaoReportService;
import bt.gov.ditt.dofps.dto.CommonDto;
import bt.gov.ditt.dofps.dto.NewTimberDto;
import bt.gov.ditt.dofps.dto.ReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tshedup Gyeltshen on 4/20/2020.
 */
@Service
public class ReportService implements IReportService {
    @Autowired
    IdaoReportService idaoReportService;

    @Override
    @Transactional
    public List<NewTimberDto> getParkList(HttpServletRequest request) {
        return idaoReportService.getParkList(request);
    }

    @Override
    @Transactional
    public List<NewTimberDto> getConstype(HttpServletRequest request) {
        return idaoReportService.getConstype(request);
    }

    @Override
    @Transactional
    public List<NewTimberDto> getWoodAndPoleType(HttpServletRequest request) {
        return idaoReportService.getWoodAndPoleType(request);
    }

    @Override
    @Transactional
    public List<NewTimberDto> getTimberForm(HttpServletRequest request) {
        return idaoReportService.getTimberForm(request);
    }

    @Override
    @Transactional
    public List<CommonDto> getDropdownDetails(String sl_no, String type, String cons_Type) {
        return idaoReportService.getDropdownDetails(sl_no, type, cons_Type);
    }

    @Override
    @Transactional
    public List<CommonDto> getDropdown(String sl_no, String type, String cons_type) {
        return idaoReportService.getDropdown(sl_no, type, cons_type);
    }

    @Override
    @Transactional
    public List<ReportDto> generateReport(String division_park_id, String dzongkhag_name, String gewog_name, String cons_type, String product_desc, String product_catagory, String from_date, String to_date, String type) {
        return idaoReportService.generateReport(division_park_id,dzongkhag_name,gewog_name,cons_type,product_desc,product_catagory,from_date,to_date,type);
    }

    @Override
    @Transactional
    public ResponseMessage generateReportCFO(String division_park_id, String dzongkhag_name, String gewog_name, String from_date, String to_date, String type, HttpServletRequest request, String cons_Type, String product_Category, String rangeId){
        ResponseMessage responseMessage = new ResponseMessage();
        List<ReportDto> reportDtoList = new ArrayList<ReportDto>();
        if(type.equalsIgnoreCase("byDivisionDzoGewog")){
            reportDtoList=idaoReportService.generateReportByDivisionDzoGewog(division_park_id, dzongkhag_name, gewog_name,from_date, to_date, type);
        }else if(type.equalsIgnoreCase("royaltyAmount")){
            reportDtoList=idaoReportService.generateReportForRoyaltyAmount(rangeId, cons_Type, product_Category,from_date, to_date, type);
        }
        responseMessage.setDto(reportDtoList);
        return responseMessage;
    }

    @Override
    @Transactional
    public List<ReportDto> generateRangeReport(String division_park_id, String cons_type, String product_catagory, String from_date, String to_date, String type) {
        return idaoReportService.generateRangeReport(division_park_id,cons_type,product_catagory,from_date,to_date,type);
    }

    @Override
    @Transactional
    public ResponseMessage generateReportGup(String locationId, String cons_type, String product_category, String from_date, String to_date, String type, HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        String construction_status = request.getParameter("construction_status");
        List<ReportDto> reportDtoList = new ArrayList<ReportDto>();
        if(type.equalsIgnoreCase("byConstructionType")){
            reportDtoList=idaoReportService.generateReportbyConstructionType(locationId, cons_type, from_date, to_date, type,product_category);
        }else if(type.equalsIgnoreCase("byConstructionStatus")) {
            reportDtoList=idaoReportService.generateReportbyConstructionStatus(locationId, construction_status, from_date, to_date, type);
        }else{
            reportDtoList=idaoReportService.generateReportGup(locationId, cons_type, product_category, from_date, to_date, type);
        }
        responseMessage.setDto(reportDtoList);
        return responseMessage;
    }

    @Override
    @Transactional
    public List<NewTimberDto> getJurisdication(HttpServletRequest request, String locationId) {
        return idaoReportService.getJurisdication(request, locationId);
    }

    @Override
    @Transactional
    public List<NewTimberDto> getRangeLocation(HttpServletRequest request, String locationId) {
        return idaoReportService.getRangeLocation(request, locationId);
    }
}
