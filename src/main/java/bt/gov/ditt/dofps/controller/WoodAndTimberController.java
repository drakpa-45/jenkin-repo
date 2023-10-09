package bt.gov.ditt.dofps.controller;

import bt.gov.ditt.dofps.dto.CitizenDetailsDTO;
import bt.gov.ditt.dofps.dto.CommonDto;
import bt.gov.ditt.dofps.dto.NewTimberDto;
import bt.gov.ditt.dofps.entitiy.DocumentEntity;
import bt.gov.ditt.dofps.services.IServiceCommon;
import bt.gov.ditt.dofps.services.IServiceWoodPole;
import bt.gov.ditt.dofps.services.iServiceRuralTimber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Pema Drakpa on 2/19/2020.
 */
@Controller
@RequestMapping("/woodPole")
public class WoodAndTimberController {

    @Autowired
    iServiceRuralTimber serviceRuralTimber;

    @Autowired
    IServiceCommon serviceCommon;

    @Autowired
    IServiceWoodPole serviceWoodPole;

    @RequestMapping(value = "/wood_Pole", method = RequestMethod.GET)
    public String applyForRuralTimber(ModelMap model,NewTimberDto dto,HttpServletRequest request,@RequestParam("cons_desc") String cons_desc) {
        // model.addAttribute("message");
        model.addAttribute("cons_type");
        Integer dzo_Id = serviceRuralTimber.getDzoId(request, dto);
        model.addAttribute("Park_List", serviceRuralTimber.getParkList(request,dzo_Id));
        model.addAttribute("DZONGKHAG_LIST", serviceRuralTimber.getDzongkhagList());
        model.addAttribute("Prod_List", serviceWoodPole.getProductDetails(cons_desc));
        return "cc/permitForPoleAndFirewood";
    }

    @ResponseBody
    @RequestMapping(value = "/saveDoc", method = RequestMethod.POST)
    public String saveFinalApplciationDetails(@RequestParam("type") String type, CitizenDetailsDTO dto, NewTimberDto newTimberDto, HttpServletRequest request, @RequestParam("files") MultipartFile[] files, ModelMap model, @RequestParam("cons_type") String cons_type) {
        HttpSession session = request.getSession();
        String retval = "";
        try {
            NewTimberDto insertDetails = serviceWoodPole.saveFinalApplciationDetails(dto, newTimberDto, type, request);

            DocumentEntity doc = serviceRuralTimber.saveDoc(files, insertDetails.getApplication_Number(), request, cons_type, newTimberDto);
            if (type.equalsIgnoreCase("update_final_data")) {
                retval = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h5>Your Application  number " + insertDetails.getApplication_Number() + " for  flagpoles/fencing post/firewood has been Submitted. The validity of permit is until the start of next calendar year.</h5></div></span> </div> </div>";
                model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info' ><div class='alert-info text-center'><h5>Your Application  number " + insertDetails.getApplication_Number() +" for the permit for Pole and Firewood is Submitted.</h5></div></span>");
            }
            return retval;
        } catch (Exception e) {
            System.out.print(e);
            retval = "<div class='card'><div class='card-header'><div class='card-body'><span class='col-md-12 col-sm-12 col-lg-12 col-xs-12' ><div class='alert-info text-center'><h3>Your Application is not submitted <br> Please try again </h3></div></span> </div> </div>";
            model.addAttribute("acknowledgement_message", "<span class='col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info '><div class='alert-info text-danger text-center'><h5>Your Application is not submitted <br> Please try again </h5></div></span>");
            return retval;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getYearValidation", method = RequestMethod.GET)
    public BigInteger getYearValidation(@RequestParam("sl_no") String sl_no, @RequestParam("household_no") String house_no){
        BigInteger count = serviceWoodPole.getYearValidation(sl_no,house_no);
        return count;
    }

    @ResponseBody
    @RequestMapping(value = "/getUnitDetails", method = RequestMethod.GET)
    public List<CommonDto> getUnitDetails(@RequestParam("sl_no") String sl_no, @RequestParam("type") String type) {
        return serviceWoodPole.getUnitDetails(sl_no, type);
    }

}