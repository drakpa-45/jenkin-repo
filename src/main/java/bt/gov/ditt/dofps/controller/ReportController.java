package bt.gov.ditt.dofps.controller;

import bt.gov.ditt.dofps.common.ResponseMessage;
import bt.gov.ditt.dofps.dto.CommonDto;
import bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO;
import bt.gov.ditt.dofps.services.IReportService;
import bt.gov.ditt.dofps.services.IServiceCommon;
import bt.gov.ditt.dofps.services.iServiceRuralTimber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Tshedup Gyeltshen on 4/20/2020.
 */

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    IReportService iReportService;
    @Autowired
    iServiceRuralTimber serviceRuralTimber;
    @Autowired
    IServiceCommon serviceCommon;

    @RequestMapping(value = "/RuralTimberReport")
    public String RuralTimberReport(ModelMap model, HttpServletRequest request, @RequestParam("header_Name") String type){
        HttpSession session = request.getSession();
        UserRolePrivilegeHierarchyObj userRolePriv=(UserRolePrivilegeHierarchyObj) session.getAttribute("userdetail");
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String roleName = userBean.getCurrentRole().getRoleName();
        String LocationId = userBean.getJurisdictions()[0].getLocationID();
        if(roleName.equalsIgnoreCase("Dealing Officer") || roleName.equalsIgnoreCase("CFO/PM") || roleName.equalsIgnoreCase("Officiating CFO/PM")){
            model.addAttribute("Division", iReportService.getJurisdication(request, LocationId));
        }
     //   if(roleName.equalsIgnoreCase("Range Officer")){
            model.addAttribute("rangeList", iReportService.getRangeLocation(request, LocationId));
    //    }
        model.addAttribute("Park_list", iReportService.getParkList(request));
        model.addAttribute("cons_type", iReportService.getConstype(request));
        request.setAttribute("construction_status", serviceCommon.getConstructionStatus());

        List<CommonDto> fpProductCategory = serviceRuralTimber.getProductList();
        request.setAttribute("product_category", fpProductCategory);
        model.addAttribute("timber_Form", iReportService.getTimberForm(request));
        model.addAttribute("WoodAndPole", iReportService.getWoodAndPoleType(request));

        if(type.equalsIgnoreCase("by_construction_type")){
            return "report/byConstructionType";
        }else if(type.equalsIgnoreCase("by_construction_status")){
            return "report/byConstructionStatus";
        }else if(type.equalsIgnoreCase("by_division")){
            return "report/byDivisionDzoGewog";
        }else if(type.equalsIgnoreCase("royaltyAmount")){
            return "report/royaltyCollected";
        }else if(type.equalsIgnoreCase("private_Land")){
            return "common/privateLandRemovalReport";
        }else if(type.equalsIgnoreCase("woodAndpole")){
            return "common/WoodAndPoleReport(main)";
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/getCommonDropdown", method = RequestMethod.GET)
    public List<CommonDto> getDropdownDetails(@RequestParam("sl_no") String sl_no, @RequestParam("type") String type, @RequestParam("cons_Type") String cons_Type) {
        return iReportService.getDropdownDetails(sl_no, type , cons_Type);
    }

    @ResponseBody
    @RequestMapping(value = "/getDropdown", method = RequestMethod.GET)
    public List<CommonDto> getDropdown(@RequestParam("sl_no") String sl_no, @RequestParam("type") String type, @RequestParam("cons_Type") String cons_Type) {
        return iReportService.getDropdown(sl_no, type , cons_Type);
    }

    @RequestMapping(value = "/loadpagetoemptylayout/generateReport", method = RequestMethod.GET)
    public String generateReport(ModelMap model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String division_Park_Id = request.getParameter("division_Park_Id");
        String dzongkhag_Name = request.getParameter("dzongkhag_Name");
        String gewog_Name = request.getParameter("gewog_Name");
        String cons_Type = request.getParameter("cons_Type");
        String Product_Desc = request.getParameter("Product_Desc");
        String product_Catagory = request.getParameter("product_Catagory");
        String from_Date = request.getParameter("from_Date");
        String to_Date = request.getParameter("to_Date");
        String type = request.getParameter("type");
       // if(type.equalsIgnoreCase("timber")){
            request.setAttribute("reportTimber", iReportService.generateReport(division_Park_Id, dzongkhag_Name, gewog_Name, cons_Type, Product_Desc, product_Catagory, from_Date, to_Date, type));
        if(type.equalsIgnoreCase("timber")){
            return "common/newTimberReport";
        }else if(type.equalsIgnoreCase("privateLand")) {
            return "common/privateLandReport";
        }else{
            return "common/woodAndPoleReport";
        }
    }

    @RequestMapping(value = "/loadpagetoemptylayout/generateRangeReport", method = RequestMethod.GET)
    public String generateRangeReport(ModelMap model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String division_Park_Id = request.getParameter("range_Id");
        String cons_Type = request.getParameter("cons_Type");
        String product_category = request.getParameter("product_category");
        String from_Date = request.getParameter("from_Date");
        String to_Date = request.getParameter("to_Date");
        String type = request.getParameter("type");

        request.setAttribute("reportTimber", iReportService.generateRangeReport(division_Park_Id, cons_Type, product_category, from_Date, to_Date, type));

        if(type.equalsIgnoreCase("timber")){
            return "common/newTimberReport";
        }else if(type.equalsIgnoreCase("privateLand")) {
            return "common/privateLandReport";
        }else{
            return "common/woodAndPoleReport";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/loadpagetoemptylayout/generateReportGup", method = RequestMethod.GET)
    public ResponseMessage generateReportGup(ModelMap model, HttpServletRequest request){
        HttpSession session = request.getSession();
        ResponseMessage responseMessage = new ResponseMessage();
        String locationId = request.getParameter("locationId");
        String cons_Type = request.getParameter("cons_Type");
        String product_Category = request.getParameter("product_category");
        String from_Date = request.getParameter("from_Date");
        String to_Date = request.getParameter("to_Date");
        String type = request.getParameter("type");
        responseMessage= iReportService.generateReportGup(locationId, cons_Type, product_Category, from_Date, to_Date, type,request);
    return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/loadpagetoemptylayout/generateReportCFO", method = RequestMethod.GET)
    public ResponseMessage generateReportCFO(ModelMap model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ResponseMessage responseMessage = new ResponseMessage();
        String division_Park_Id = request.getParameter("division_Park_Id");
        String dzongkhag_Name = request.getParameter("dzongkhag_Name");
        String gewog_Name = request.getParameter("gewog_Name");
        String from_Date = request.getParameter("from_Date");
        String to_Date = request.getParameter("to_Date");
        String type = request.getParameter("type");
        String cons_Type = request.getParameter("cons_Type");
        String rangeId = request.getParameter("rangeId");
        String product_Category = request.getParameter("product_category");
        // if(type.equalsIgnoreCase("timber")){
        responseMessage= iReportService.generateReportCFO(division_Park_Id, dzongkhag_Name, gewog_Name, from_Date, to_Date, type, request,cons_Type,product_Category,rangeId);
        return responseMessage;
    }
}
