package bt.gov.ditt.dofps.controller;


import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.entitiy.Token;
import bt.gov.ditt.dofps.services.APIService;
import bt.gov.ditt.dofps.services.IServiceCommon;
import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import bt.gov.g2c.framework.userdetail.InvokeWS;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wso2.client.model.G2C_CommonBusinessAPI.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pema Drakpa on 1/15/2020.
 */

@Controller
@RequestMapping("/login")
public class ControllerLogin {

    @Autowired
    IServiceCommon serviceCommon;

    @Autowired
    private APIService api;


    @RequestMapping(method = RequestMethod.GET)
    public String dofps(ModelMap model) {
        return "common/login";
    }

    @RequestMapping(value = "/loginRedirect", method = RequestMethod.POST)
    public String loginRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Role currentRole = null;
        UserRolePrivilegeDTO dto = new UserRolePrivilegeDTO();
        int userRoleId = 0;
        String LocationId = "";
        String Juri_Type_Id = "";
        {
            HttpSession session = request.getSession();
            try {
                String uid = null;
               /* if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
                    uid = request.getUserPrincipal().getName();*/

                if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
                    uid = request.getUserPrincipal().getName();
                } else if (request.getParameter("userId") != null) {
                    uid = (String) request.getParameter("userId");
                }

                String roleId = request.getParameter("roleId");

                if (session.getAttribute("userdetail") == null) {//If user details not present in session, populate it here
                    if (uid != null) {
                        UserRolePrivilege userRolePrivilegeDol = new UserRolePrivilege();
                        userRolePrivilegeDol.setUserId(uid);
                        String userID = uid;
                        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
                        String userRolePrivilegeEndPointUrl = resourceBundle1.getString("getUserRolePrivilege.endPointURL");
                        InvokeWS invokeWS = null;
                        invokeWS = new InvokeWS(userRolePrivilegeEndPointUrl);
                        userRolePrivilegeDol = invokeWS.populateUserRolePrivilegeHierarchy(userRolePrivilegeDol, roleId, "M_MOA.D_DoFPS");
                        UserRolePrivilegeHierarchyObj userRolePrivilegeObj = new UserRolePrivilegeHierarchyObj();

                        bt.gov.g2c.framework.common.vo.Jurisdiction[] jurisdictions = userRolePrivilegeDol.getJurisdictions();

                        //set user person details

                        userRolePrivilegeObj.setFullName(userRolePrivilegeDol.getUserName());
                        userRolePrivilegeObj.setUserType(userRolePrivilegeDol.getUserType());
                        userRolePrivilegeObj.setEmail(userRolePrivilegeDol.getEmail());
                        userRolePrivilegeObj.setCid(userRolePrivilegeDol.getUserCID());
                        userRolePrivilegeObj.setMobile(userRolePrivilegeDol.getMobileNumber());

                        // set jurisdiction object datas

                        JurisdictionObj jurisdictionObj = new JurisdictionObj();
                        jurisdictionObj.setLocationId(Integer.parseInt(userRolePrivilegeDol.getJurisdictions()[0].getLocationID()));
                        jurisdictionObj.setJurisdiction(userRolePrivilegeDol.getJurisdictions()[0].getJurisdiction());

                        //jurisdiction object add to list

                        List<JurisdictionObj> jurisdictionObjs = new ArrayList<JurisdictionObj>();
                        jurisdictionObjs.add(jurisdictionObj);

                        //set jurisdiction list to jurisdictions object

                        JurisdictionsObj jurisdictionsObj = new JurisdictionsObj();
                        jurisdictionsObj.setJurisdiction(jurisdictionObjs);
                        userRolePrivilegeObj.setJurisdictions(jurisdictionsObj);

                        //set role model datas
                        UserRoleObj userRoleObj = new UserRoleObj();


                        userRoleObj.setRoleId(Integer.parseInt(userRolePrivilegeDol.getCurrentRole().getRoleId()));
                        userRoleObj.setRoleName(userRolePrivilegeDol.getCurrentRole().getRoleName());

                        //set services and privileges of current role
                        int serviceLength = userRolePrivilegeDol.getCurrentRole().getServices().length;
                        DeptServicesObj deptServicesObj = new DeptServicesObj();
                        List<DeptServiceObj> deptServiceList = new ArrayList<DeptServiceObj>();
                        for (int i = 0; i < serviceLength; i++) {
                            DeptServiceObj deptServiceObj = new DeptServiceObj();
                            deptServiceObj.setServiceId(Integer.parseInt(userRolePrivilegeDol.getCurrentRole().getServices(i).getServiceId()));
                            deptServiceObj.setServiceName(userRolePrivilegeDol.getCurrentRole().getServices(i).getServiceName());
                            deptServiceList.add(deptServiceObj);
                            deptServicesObj.setDeptService(deptServiceList);
                            int privilegeLength = userRolePrivilegeDol.getCurrentRole().getServices(i).getPrivileges().length;
                            ServicePrivilegesObj servicePrivilegesObj = new ServicePrivilegesObj();
                            List<ServicePrivilegeObj> servicePrivilegeObjList = new ArrayList<ServicePrivilegeObj>();
                            for (int j = 0; j < privilegeLength; j++) {
                                ServicePrivilegeObj servicePrivilegeObj = new ServicePrivilegeObj();
                                servicePrivilegeObj.setPrivilegeId(Integer.parseInt(userRolePrivilegeDol.getCurrentRole().getServices(i).getPrivileges(j).getPrivilegeId()));
                                servicePrivilegeObj.setPrivilegeName(userRolePrivilegeDol.getCurrentRole().getServices(i).getPrivileges(j).getPrivilegeName());
                                servicePrivilegeObjList.add(servicePrivilegeObj);
                                servicePrivilegesObj.setServicePrivilege(servicePrivilegeObjList);
                                deptServiceObj.setServicePrivileges(servicePrivilegesObj);
                            }
                            userRoleObj.setDeptServices(deptServicesObj);
                        }

                        // set role object to list
                        List<UserRoleObj> userRoleList = new ArrayList<UserRoleObj>();
                        userRoleList.add(userRoleObj);

                        // set role list to roles object
                        UserRolesObj userRolesObj = new UserRolesObj();
                        userRolesObj.setUserRole(userRoleList);
                        userRolePrivilegeObj.setUserRoles(userRolesObj);

                        dto.setFullName(userRolePrivilegeObj.getFullName());
                        dto.setCid(userRolePrivilegeObj.getCid());
                        dto.setMobileNo(userRolePrivilegeObj.getMobile());
                        dto.setEmailId(userRolePrivilegeObj.getEmail());
                        dto.setUserType(userRolePrivilegeObj.getUserType());
                        dto.setTelephoneNo(userRolePrivilegeObj.getTelephone());

                        List<Role> roles1 = new ArrayList<Role>();
                        UserRolesObj userRoleList1 = userRolePrivilegeObj.getUserRoles();
                        for (int i = 0; i < userRoleList1.getUserRole().size(); i++) {
                            UserRoleObj userRole = userRoleList1.getUserRole().get(i);
                            int roleId1 = userRole.getRoleId();
                            String roleName = userRole.getRoleName();
                            String roleDescription = userRole.getRoleDescription();

                            Role role = new Role();
                            userRoleId = roleId1;
                            role.setRoleId(Integer.toString(roleId1));
                            role.setRoleCode(roleDescription);
                            role.setRoleName(roleName);

                            List<Service> services = new ArrayList<Service>();
                            DeptServicesObj deptServicesObj1 = userRole.getDeptServices();
                            for (int j = 0; j < deptServicesObj1.getDeptService().size(); j++) {
                                DeptServiceObj deptServiceObj = deptServicesObj1.getDeptService().get(j);
                                Service service = new Service();

                                List<Privilege> privileges = new ArrayList<Privilege>();
                                ServicePrivilegesObj servicePrivilegesObj = deptServiceObj.getServicePrivileges();
                                for (int k = 0; k < servicePrivilegesObj.getServicePrivilege().size(); k++) {
                                    ServicePrivilegeObj servicePrivilegeObj = servicePrivilegesObj.getServicePrivilege().get(k);
                                    Privilege privilege = new Privilege();
                                    privilege.setPrivilegeId(Integer.toString(servicePrivilegeObj.getPrivilegeId()));
                                    privilege.setPrivilegeCode(servicePrivilegeObj.getPrivilegeDescription());
                                    privilege.setPrivilegeName(servicePrivilegeObj.getPrivilegeName());
                                    privileges.add(privilege);
                                }
                                service.setPrivileges(privileges.toArray(new Privilege[privileges.size()]));
                                services.add(service);
                            }

                            List<Jurisdiction> jurisdictions1 = new ArrayList<Jurisdiction>();
                            JurisdictionsObj jurisdictionsObj1 = userRolePrivilegeObj.getJurisdictions();
                            for (int k = 0; k < jurisdictionsObj1.getJurisdiction().size(); k++) {
                                JurisdictionObj jurisdictionObj1 = jurisdictionsObj.getJurisdiction().get(k);
                                Jurisdiction jurisdiction = new  Jurisdiction();

                                jurisdiction.setJurisdiction(userRolePrivilegeDol.getJurisdictions()[0].getJurisdiction());
                                jurisdiction.setJurisdictionType(userRolePrivilegeDol.getJurisdictions()[0].getJurisdictionType());
                                jurisdiction.setLocationID(userRolePrivilegeDol.getJurisdictions()[0].getLocationID());
                                jurisdictions1.add(jurisdiction);
                            }
                            dto.setJurisdictions(jurisdictions1.toArray(new Jurisdiction[jurisdictions1.size()]));

                            role.setServices(services.toArray(new Service[services.size()]));
                            roles1.add(role);
                        }

                        dto.setRoles(roles1.toArray(new Role[roles1.size()]));
                        if (roles1 != null && roles1.size() == 1) {
                            dto.setCurrentRole(roles1.get(0));
                        } else if (roles1.size() > 1) {
                            currentRole = roles1.get(0);
                            dto.setCurrentRole(currentRole);
                        }

                        String user = dto.getCurrentRole().getRoleName();

                        for (int n = 0; n < dto.getJurisdictions().length; n++) {
                            LocationId = dto.getJurisdictions()[n].getLocationID();
                            String type = dto.getJurisdictions()[n].getJurisdiction();
                            //System.out.println("Jurisdiction Id: " + dto.getJurisdictions()[n].getJurisdiction() + " Jurisdiction Type:  " + dto.getJurisdictions()[n].getJurisdictionType() + " Location Id: " + dto.getJurisdictions()[n].getLocationID());
                        }

                        session.setAttribute("UserRolePrivilege", dto);

                        session.setAttribute("userdetail", userRolePrivilegeObj);
                        if (!user.equalsIgnoreCase("CC Operator")) {
                            if (dto.getCurrentRole().getRoleName() != "" && dto.getJurisdictions()[0].getLocationID() != "") {
                                request.setAttribute("GroupTaskDealing", serviceCommon.getGroupTaskDealing(request, userID, LocationId, user));
                                request.setAttribute("PersonalTaskDealing", serviceCommon.getPersonalTaskDealing(request, userID, user, LocationId));

                                request.setAttribute("GroupTaskPRL", serviceCommon.getGroupTaskRangePRL(request, userID, LocationId, user));
                                request.setAttribute("PersonalTaskPRL", serviceCommon.getPersonalTaskRangePRL(request, userID, LocationId, user));
                                return "common/dashBoard";
                            }
                        }else {
                            return "common/dashBoard";
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Error at GetUserDetailsLocal[doAction]" + e);
            }
        }
        return "hello";
    }
}

    /*@RequestMapping(value = "/loginRedirect", method = RequestMethod.POST)
    public String loginRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role currentRole = null;
        UserRolePrivilege dto = new UserRolePrivilege();
        int userRoleId = 0;
        String LocationId = "";
        HttpSession session = request.getSession();
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String userRolePrivilegeEndPointUrl =resourceBundle1.getString("getUserRolePrivilege.endPointURL");
        {

            try {
                request.setAttribute("userId", request.getParameter("userId"));
                OkHttpClient httpClient = new OkHttpClient();
                httpClient.setConnectTimeout(10000, TimeUnit.MILLISECONDS);
                httpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);

                org.wso2.client.api.ApiClient apiClient = new org.wso2.client.api.ApiClient();
                apiClient.setHttpClient(httpClient);

                //apiClient.setAccessToken("1a1e7391-07c4-3763-b4eb-2c30c810d80d");
                *//*apiClient.setBasePath("https://staging-datahub-apim.dit.gov.bt/g2c_common_business_api/1.0.0");*//*
                apiClient.setBasePath(userRolePrivilegeEndPointUrl);

                Token token = api.getApplicationToken();
                apiClient.setAccessToken(token.getAccess_token());

                *//*session.getAttribute("TOKEN");
                apiClient.setAccessToken("1a1e7391-07c4-3763-b4eb-2c30c810d80d");*//*

                org.wso2.client.api.G2C_CommonBusinessAPI.DefaultApi api = new org.wso2.client.api.G2C_CommonBusinessAPI.DefaultApi(apiClient);
                UserRolePrivilegeHierarchyResponse userRolePrivilegeResponse = api.userroleprivilegehierarchyUseridDeptshortcodeGet(request.getParameter("userId"), "M_MOA.D_DoFPS");
                UserRolePrivilegeHierarchyObj userRolePrivilegeObj = null;

                if (userRolePrivilegeResponse.getUserRolePrivilegeHierarchyResponse().getUserRolePrivilegeHierarchy() != null
                        && !userRolePrivilegeResponse.getUserRolePrivilegeHierarchyResponse().getUserRolePrivilegeHierarchy().isEmpty()) {
                    userRolePrivilegeObj = userRolePrivilegeResponse.getUserRolePrivilegeHierarchyResponse().getUserRolePrivilegeHierarchy().get(0);
                    userRolePrivilegeObj.setUserType(request.getParameter("userId"));
                    String userID = request.getParameter("userId");
                    String fullName = userRolePrivilegeObj.getFullName();
                    String cid = userRolePrivilegeObj.getCid();
                    String mobileNo = userRolePrivilegeObj.getMobile();
                    String emailId = userRolePrivilegeObj.getEmail();
                    String userType = userRolePrivilegeObj.getUserType();
                    String telephoneNo = userRolePrivilegeObj.getTelephone();

                    dto.setUserID(userID);
                    dto.setFullName(fullName);
                    dto.setCid(cid);
                    dto.setMobileNo(mobileNo);
                    dto.setEmailId(emailId);
                    dto.setUserType(userType);
                    dto.setTelephoneNo(telephoneNo);

                    List<Role> roles = new ArrayList<Role>();
                    UserRolesObj userRoleList = userRolePrivilegeObj.getUserRoles();
                    for (int i = 0; i < userRoleList.getUserRole().size(); i++) {
                        UserRoleObj userRole = userRoleList.getUserRole().get(i);
                        int roleId = userRole.getRoleId();
                        String roleName = userRole.getRoleName();
                        String roleDescription = userRole.getRoleDescription();

                        Role role = new Role();
                        userRoleId = roleId;
                        role.setRoleId(Integer.toString(roleId));
                        role.setRoleCode(roleDescription);
                        role.setRoleName(roleName);

                        List<Service> services = new ArrayList<Service>();
                        DeptServicesObj deptServicesObj = userRole.getDeptServices();
                        for (int j = 0; j < deptServicesObj.getDeptService().size(); j++) {
                            DeptServiceObj deptServiceObj = deptServicesObj.getDeptService().get(j);
                            int serviceId = deptServiceObj.getServiceId();
                            String serviceName = deptServiceObj.getServiceName();
                            String serviceDescription = deptServiceObj.getServiceDescription();

                            Service service = new Service();
                            service.setServiceId(Integer.toString(serviceId));
                            service.setServiceCode(serviceDescription);
                            service.setServiceName(serviceName);

                            List<Privilege> privileges = new ArrayList<Privilege>();
                            ServicePrivilegesObj servicePrivilegesObj = deptServiceObj.getServicePrivileges();
                            for (int k = 0; k < servicePrivilegesObj.getServicePrivilege().size(); k++) {
                                ServicePrivilegeObj servicePrivilegeObj = servicePrivilegesObj.getServicePrivilege().get(k);
                                int privilegeId = servicePrivilegeObj.getPrivilegeId();
                                String privilegeName = servicePrivilegeObj.getPrivilegeName();
                                String privilegeDescription = servicePrivilegeObj.getPrivilegeDescription();

                                Privilege privilege = new Privilege();
                                privilege.setPrivilegeId(Integer.toString(privilegeId));
                                privilege.setPrivilegeCode(privilegeDescription);
                                privilege.setPrivilegeName(privilegeName);
                                privileges.add(privilege);
                            }
                            service.setPrivileges(privileges.toArray(new Privilege[privileges.size()]));
                            services.add(service);
                        }

                        role.setServices(services.toArray(new Service[services.size()]));
                        roles.add(role);
                    }

                    dto.setRoles(roles.toArray(new Role[roles.size()]));
                    if (roles != null && roles.size() == 1) {
                        dto.setCurrentRole(roles.get(0));
                    } else if (roles.size() > 1) {
                        currentRole = roles.get(0);
                        dto.setCurrentRole(currentRole);
                    }

                    List<Jurisdiction> jurisdictions = new ArrayList<Jurisdiction>();
                    JurisdictionsObj jurisdictionsObj = userRolePrivilegeObj.getJurisdictions();
                    for (int k = 0; k < jurisdictionsObj.getJurisdiction().size(); k++) {
                        JurisdictionObj jurisdictionObj = jurisdictionsObj.getJurisdiction().get(k);

                        Jurisdiction jurisdiction = new Jurisdiction();
                        jurisdiction.setJurisdiction(Integer.toString(jurisdictionObj.getJurisdictionId()));
                        jurisdiction.setJurisdictionType(jurisdictionObj.getJurisdiction());
                        jurisdiction.setLocationID(Integer.toString(jurisdictionObj.getLocationId()));
                        jurisdictions.add(jurisdiction);
                    }

                    dto.setJurisdictions(jurisdictions.toArray(new Jurisdiction[jurisdictions.size()]));
                    for (int m = 0; m < dto.getRoles().length; m++) {
                        for (int i = 0; i < dto.getRoles()[m].getServices().length; i++) {
                            Service svc = dto.getCurrentRole().getServices()[i];
                            for (int j = 0; j < svc.getPrivileges().length; j++) {
                                Privilege priv = svc.getPrivileges()[j];

                                System.out.println("role name : " + dto.getRoles()[m].getRoleCode() + " svc name : " + svc.getServiceName() + " && priv code : " + priv.getPrivilegeCode() + "(" + priv.getPrivilegeId() + ")");
                            }
                        }
                    }

                    for (int n = 0; n < dto.getJurisdictions().length; n++) {
                        LocationId = dto.getJurisdictions()[n].getLocationID();
                        System.out.println("Jurisdiction Id: " + dto.getJurisdictions()[n].getJurisdiction() + " Jurisdiction Type:  " + dto.getJurisdictions()[n].getJurisdictionType() + " Location Id: " + dto.getJurisdictions()[n].getLocationID());
                    }
                    session.setAttribute("UserRolePrivilege", dto);
                    String user = dto.getCurrentRole().getRoleName();

                  session.setAttribute("userdetail", userRolePrivilegeObj);
                    if (dto.getCurrentRole().getRoleName() != "" && dto.getJurisdictions()[0].getLocationID() != "") {
                        request.setAttribute("GroupTaskDealing", serviceCommon.getGroupTaskDealing(request, userID, LocationId, user));
                        request.setAttribute("PersonalTaskDealing", serviceCommon.getPersonalTaskDealing(request, userID, user, LocationId));

                        request.setAttribute("GroupTaskPRL", serviceCommon.getGroupTaskRangePRL(request, userID, LocationId, user));
                        request.setAttribute("PersonalTaskPRL", serviceCommon.getPersonalTaskRangePRL(request, userID, LocationId, user));
                        return "common/dashBoard";
                    }
                }
            } catch (Exception e) {
                System.out.println("Error at GetUserDetails[doAction]" + e);
            }
        }
        return "hello";
    }*/
