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
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping(value = "/getCitizenDetails")
public class GetCitizenDetails {

    @Autowired
    IServiceCommon serviceCommon;

    @Autowired
    private APIService api;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public CitizenDetailsDTO citizenRedirect(ModelMap model,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            //ApiClient apiClient = new ApiClient();
            org.wso2.client.api.ApiClient apiClient = new org.wso2.client.api.ApiClient();
            apiClient.setHttpClient(httpClient);
            //apiClient.setBasePath("https://staging-datahub-apim.dit.gov.bt/dcrc_citizen_details_api/1.0.0");

			/*
             * HttpSession session = request.getSession(); TokenDTO tokendto = (TokenDTO)
			 * session.getAttribute("TOKEN");
			 */
            //apiClient.setAccessToken("1a1e7391-07c4-3763-b4eb-2c30c810d80d");
            apiClient.setBasePath(citizenDetails);
            Token token = api.getApplicationToken();
            apiClient.setAccessToken(token.getAccess_token());

            DefaultApi api = new DefaultApi(apiClient);
            CitizenDetailsResponse citizenDetailsResponse = api.citizendetailsCidGet(request.getParameter("cid"));


            if (citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail() != null && !citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail().isEmpty()) {
                citizendetailsObj = citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail().get(0);

                dto.setCid_Number(citizendetailsObj.getCid());
                dto.setName(citizendetailsObj.getFirstName() + " " + citizendetailsObj.getMiddleName() + citizendetailsObj.getLastName());
                dto.setName(dto.getName().replaceAll("null", ""));
                dto.setHouse_No(citizendetailsObj.getHouseNo());
                dto.setHouse_Hold_No(citizendetailsObj.getHouseholdNo());
                dto.setDzongkhag_Name(citizendetailsObj.getDzongkhagName());
                dto.setDzongkhag_Id(citizendetailsObj.getDzongkhagId());
                dto.setGewog_Name(citizendetailsObj.getGewogName());
                dto.setVillage_Name(citizendetailsObj.getVillageName());
                dto.setVillage_Serial_No(Integer.parseInt(citizendetailsObj.getVillageSerialNo()));
                //dto.setGenderType(citizendetailsObj.getGender());

                if(citizendetailsObj.getGender()!=null){
                    if(citizendetailsObj.getGender().equalsIgnoreCase("M")){
                        dto.setGenderType("Male");
                    }else {
                        dto.setGenderType("Female");
                    }
                }

                String householdno = citizendetailsObj.getHouseholdNo();
                if(householdno!= null) {
                    HouseholddetailsResponse householddetailsResponse = api.householdingdetailsHouseholdnoGet(householdno);
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
                    }
                }

                String CClocationID = request.getParameter("CCLocationID");
                String gewogName = serviceCommon.getCCGewog(CClocationID);

                String gewog = citizendetailsObj.getGewogName();
                if(gewog!=null){
                    if(gewog.equalsIgnoreCase(gewogName)){
                        dto.setRegisteredGewog("TRUE");
                    }else{
                        dto.setRegisteredGewog("FALSE");
                    }
                    System.out.print("Gewog" + dto.getRegisteredGewog());
                }

            }
            } catch (Exception e) {
            System.out.println("Error at GetCitizenDetails[doAction]" + e);
        }
        return dto;


        
    }


}
