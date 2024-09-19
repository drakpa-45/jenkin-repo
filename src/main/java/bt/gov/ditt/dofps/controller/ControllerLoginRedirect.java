package bt.gov.ditt.dofps.controller;

import bt.gov.ditt.dofps.dto.*;
import bt.gov.ditt.dofps.services.IServiceCommon;
import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import bt.gov.g2c.framework.userdetail.InvokeWS;
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

@Controller
@RequestMapping("/loginMain")
public class ControllerLoginRedirect {

	/*@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "common/login";
	}*/

    @Autowired
    IServiceCommon serviceCommon;

 /*   @Autowired
    private APIService api;*/

    @RequestMapping(method = RequestMethod.GET)
    public String loginRedirect(HttpServletRequest request, HttpServletResponse response,ModelMap model) throws ServletException, IOException {

        Role currentRole = null;
        UserRolePrivilegeDTO dto = new UserRolePrivilegeDTO();
        int userRoleId = 0;
        String LocationId = "";
        String Juri_Type_Id = "";
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

                System.out.println("------- request.getUserPrincipal().getName() -------"+request.getUserPrincipal().getName());

                System.out.println("------- before userdetail -------");

                String roleId = request.getParameter("roleId");
                session.setAttribute("userdetail",null);

                if (session.getAttribute("userdetail") == null) {//If user details not present in session, populate it here
                    if (uid != null) {
                        System.out.println("--------UID ------" +uid);
                        UserRolePrivilege userRolePrivilegeDol = new UserRolePrivilege();
                        userRolePrivilegeDol.setUserId(uid);
                        String userID = uid;

                        System.out.println("--------before api call ------");
                        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
                        String userRolePrivilegeEndPointUrl = resourceBundle1.getString("getUserRolePrivilege.endPointURL");
                        System.out.println("--------after api call ------");
                        InvokeWS invokeWS = null;
                        System.out.println("--------before invokeWS call ------");
                        invokeWS = new InvokeWS(userRolePrivilegeEndPointUrl);

                        userRolePrivilegeDol = invokeWS.populateUserRolePrivilegeHierarchy(userRolePrivilegeDol, roleId, "M_MOA.D_DoFPS");
                        UserRolePrivilegeHierarchyObj userRolePrivilegeObj = new UserRolePrivilegeHierarchyObj();

                        bt.gov.g2c.framework.common.vo.Jurisdiction[] jurisdictions = userRolePrivilegeDol.getJurisdictions();
                        System.out.println("--------after invokeWS call ------"+invokeWS);

                        //set user person details
                        dto.setUserID(uid);
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
                        System.out.println("--------ROLE ID  ------"+dto.getCurrentRole().getRoleId());
                        String user = dto.getCurrentRole().getRoleName();
                        for (int n = 0; n < dto.getJurisdictions().length; n++) {
                            LocationId = dto.getJurisdictions()[n].getLocationID();
                            String type = dto.getJurisdictions()[n].getJurisdiction();
                            //System.out.println("Jurisdiction Id: " + dto.getJurisdictions()[n].getJurisdiction() + " Jurisdiction Type:  " + dto.getJurisdictions()[n].getJurisdictionType() + " Location Id: " + dto.getJurisdictions()[n].getLocationID());
                        }

                        session.setAttribute("UserRolePrivilege", dto);
                        session.setAttribute("userdetail", userRolePrivilegeObj);

                      /*  if(user.equalsIgnoreCase("CC Operator")){
                            return "common/dashBoard";
                        }else*/ if(user.equalsIgnoreCase("Gewog") || user.equalsIgnoreCase("CFO/PM") || user.equalsIgnoreCase("LG_ROLE") || user.equalsIgnoreCase("Gup")  || user.equalsIgnoreCase("Range Officer")){
                            request.setAttribute("My_Task_List", serviceCommon.populateTaskListforSelectedServices(request, "5"));
                            return "admin/dashboard";
                        }/*else if(user.equalsIgnoreCase("CFO/PM")){
                            request.setAttribute("My_Task_List", serviceCommon.populateTaskListforSelectedServices(request, "5"));
                            return "gewog/dashboard";
                        }*/else{
                            model.addAttribute("acknowledgement_message","Your are not allow to access with this role:" + roleId);
                               return "common/ackPage";
                        }
//                        if (!user.equalsIgnoreCase("CC Operator")) {
//                            if (dto.getCurrentRole().getRoleName() != "" && dto.getJurisdictions()[0].getLocationID() != "") {
//                                request.setAttribute("GroupTaskDealing", serviceCommon.getGroupTaskDealing(request, userID, LocationId, user));
//                                request.setAttribute("PersonalTaskDealing", serviceCommon.getPersonalTaskDealing(request, userID, user, LocationId));
//                                request.setAttribute("GroupTaskPRL", serviceCommon.getGroupTaskRangePRL(request, userID, LocationId, user));
//                                request.setAttribute("PersonalTaskPRL", serviceCommon.getPersonalTaskRangePRL(request, userID, LocationId, user));
//                                return "common/dashBoard";
//                            }
//                        }else {
//                           return "common/dashBoard";
//                        }
                    }
                    System.out.println("------------ outside uid ------" +uid);
                }
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Error at GetUserDetailsLocal[doAction]" + e);
            }
       return "admin/dashboard";
    //    return "common/login";
    }

    /**
     * to generate authentication error message
     *
     * @param request request
     * @param key     key
     * @return String
     */
    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);
        if (exception != null) {
            return exception.getMessage();
        } else {
            return null;
        }
    }
}

