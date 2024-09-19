package bt.gov.ditt.dofps.controller;

import bt.gov.ditt.dofps.dto.CitizenDetailsDTO;
import bt.gov.ditt.dofps.entitiy.Token;
import bt.gov.ditt.dofps.services.APIService;
import bt.gov.ditt.dofps.services.IServiceCommon;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wso2.client.api.ApiClient;
import org.wso2.client.api.DCRC_CitizenDetailsAPI.DefaultApi;
import org.wso2.client.model.DCRC_CitizenDetailsAPI.CitizenDetailsResponse;
import org.wso2.client.model.DCRC_CitizenDetailsAPI.CitizendetailsObj;
import org.wso2.client.model.DCRC_CitizenDetailsAPI.HouseholddetailsResponse;
import org.wso2.client.model.DCRC_CitizenDetailsAPI.HouseholdingdetailsObj;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping(value = "/getCitizenDetailsOnline")
public class CitizenDetailsOnline {

    @Autowired
    IServiceCommon serviceCommon;

    @Autowired
    private APIService api;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public CitizenDetailsDTO citizenRedirect(ModelMap model,HttpServletRequest request, HttpServletResponse response,String cons_type) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        CitizenDetailsDTO dto = new CitizenDetailsDTO();
        CitizendetailsObj citizendetailsObj = null;
        HouseholdingdetailsObj householdingdetailsObj = null;
        HttpSession session = request.getSession();

        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String citizenDetails =resourceBundle1.getString("getCitizenDetails.endPointURL");

        try {

            OkHttpClient httpClient = new OkHttpClient();
            httpClient.setConnectTimeout(10000, TimeUnit.MILLISECONDS);
            httpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);

            ApiClient apiClient = new ApiClient();
            apiClient.setHttpClient(httpClient);
            apiClient.setBasePath(citizenDetails);
            Token token = api.getApplicationToken();
            apiClient.setAccessToken(token.getAccess_token());

            DefaultApi api = new DefaultApi(apiClient);
            CitizenDetailsResponse citizenDetailsResponse = api.citizendetailsCidGet(request.getParameter("cid"));

            if (citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail() != null && !citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail().isEmpty()) {
                citizendetailsObj = citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail().get(0);

                dto.setCid_Number(citizendetailsObj.getCid());
                dto.setName(citizendetailsObj.getFirstName() + " " + citizendetailsObj.getMiddleName() + " " + citizendetailsObj.getLastName());
                dto.setName(dto.getName().replaceAll("null", ""));
                dto.setHouse_No(citizendetailsObj.getHouseNo());
                dto.setHouse_Hold_No(citizendetailsObj.getHouseholdNo());
                dto.setDzongkhag_Name(citizendetailsObj.getDzongkhagName());
                dto.setDzongkhag_Id(citizendetailsObj.getDzongkhagId());
                dto.setGewog_Name(citizendetailsObj.getGewogName());
                dto.setGewog_Id(citizendetailsObj.getGewogId());
                dto.setVillage_Name(citizendetailsObj.getVillageName());
                dto.setVillage_Serial_No(Integer.parseInt(citizendetailsObj.getVillageSerialNo()));
                dto.setThram_No(citizendetailsObj.getThramNo());
                dto.setPhoneNumber(citizendetailsObj.getMobileNumber());

                if(citizendetailsObj.getGender()!=null){
                    if(citizendetailsObj.getGender().equalsIgnoreCase("M")){
                        dto.setGenderType("Male");
                    }else {
                        dto.setGenderType("Female");
                    }
                }
               // String isCFMember = getDetailsFromCF(cid);

                String house_hold = citizendetailsObj.getHouseholdNo();
                if(house_hold!= null) {
                    HouseholddetailsResponse householddetailsResponse = api.householdingdetailsHouseholdnoGet(house_hold);
                    if (householddetailsResponse.getHouseholdDetailsResponse().getHouseholdDetail() != null && !householddetailsResponse.getHouseholdDetailsResponse().getHouseholdDetail().isEmpty()) {
                        householdingdetailsObj = householddetailsResponse.getHouseholdDetailsResponse().getHouseholdDetail().get(0);
                        dto.setHead_Gung(householdingdetailsObj.getCid());

                        if(dto.getHead_Gung() != null && dto.getHead_Gung().equalsIgnoreCase(dto.getCid_Number())){
                            dto.setHoh("YES");
                        }else{
                            dto.setHoh("NO");
                        }

                        System.out.print("HOH" + dto.getHoh());
                        if(dto.getHead_Gung() == null || dto.getHead_Gung().equalsIgnoreCase("")){
                            dto.setHoh_Cid("FALSE");
                        }else{
                            dto.setHoh_Cid("TRUE");
                        }

                        if(!cons_type.isEmpty()) {
                            if(cons_type.equalsIgnoreCase("n") || cons_type.equalsIgnoreCase("r") || cons_type.equalsIgnoreCase("o")){
                                BigInteger untExit = serviceCommon.checkForValidation(house_hold,cons_type);
                                if(untExit.intValue()>0){
                                    if(cons_type.equalsIgnoreCase("n")){
                                        dto.setActor_Name("SORRY!. This household number " + house_hold + " has already raised the requisition for New construction of Rural Houses previously. You can apply only after 25 years.");
                                    }else if(cons_type.equalsIgnoreCase("r")){
                                        dto.setActor_Name("SORRY!. This household number " + house_hold + " has already raised the requisition for renovation of Rural Houses previously. You can apply only after 12 years.");
                                    }else{
                                        dto.setActor_Name("SORRY!. This household number " + house_hold + " has already raised the requisition for other Constructions previously. You can apply only after 5 years.");
                                    }
                                    dto.setJuri_Type_Id(0);
                                }
                            }
                            dto.setJuri_Type_Id(1);
                        }
                    }
                }
                dto.setStatus_Id(1);
            }else{
                dto.setStatus_Id(0);
            }
        }catch (Exception e) {
            dto.setCid_Number("10101000022");
            dto.setName("Full Name");
            dto.setName(dto.getName().replaceAll("null", ""));
            dto.setHouse_No("123456789");
            dto.setHouse_Hold_No("12457");
            dto.setDzongkhag_Name("Bumthang");
            dto.setDzongkhag_Id("1");
            dto.setGewog_Name("Chokhor");
            dto.setGewog_Id("1");
            dto.setVillage_Name("Chokhor");
            dto.setVillage_Serial_No(1);
            dto.setThram_No("TA-123");
            dto.setGenderType("Male");
            dto.setStatus_Id(1);
            System.out.println("Error at GetCitizenDetails[doAction]" + e);
            String isCFMember = getDetailsFromCF(cid);
        }
        return dto;
    }

    private String getDetailsFromCF(String cid) {
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String cfEndPointUrl = resourceBundle1.getString("getCF.endPointURL");
        String isCFMember="";
        try {
            OkHttpClient httpClient = new OkHttpClient();
            httpClient.setConnectTimeout(10000, TimeUnit.MILLISECONDS);
            httpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);

            ApiClient apiClient = new ApiClient();
            apiClient.setHttpClient(httpClient);
            apiClient.setBasePath(cfEndPointUrl);

            Token token = api.getApplicationToken();
            apiClient.setAccessToken(token.getAccess_token());

            DefaultApi cfApi = new DefaultApi(apiClient);


           /*GetEmployeeDetailByCidResponse employeeDetailsResponse = cfApi.(cid);*/
           /* GetEmployeeDetailByCidOBJ employeeDetailsOBJ=null;
            if (employeeDetailsResponse.getEmployeedetails().getEmployeedetail() != null && !employeeDetailsResponse.getEmployeedetails().getEmployeedetail().isEmpty()) {
                employeeDetailsOBJ = employeeDetailsResponse.getEmployeedetails().getEmployeedetail().get(0);
                dto.setFullName(employeeDetailsOBJ.getFirstName() + " " + employeeDetailsOBJ.getMiddleName() + " " + employeeDetailsOBJ.getLastName());
                dto.setId(employeeDetailsOBJ.getEmployeeNumber());
                dto.setDesignation(employeeDetailsOBJ.getPositionTitle());
                dto.setGradeId(employeeDetailsOBJ.getPositionLevel());
                dto.setMinistryName(employeeDetailsOBJ.getParentAgency());
            }*/

        } catch (Exception e) {
            System.out.println("Could not connect to Community Forest API. Please wait for the connection OR enter the information correctly. " + e);
            // TODO: un-comment when API is using.
        }

        return isCFMember;
    }
}
