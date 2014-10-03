package com.silverpeas.mobile.server.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.silverpeas.mobile.server.common.SpMobileLogModule;
import com.stratelia.silverpeas.silvertrace.SilverTrace;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.silverpeas.mobile.shared.dto.DetailUserDTO;
import com.silverpeas.mobile.shared.dto.contact.ContactFilters;
import com.silverpeas.mobile.shared.exceptions.AuthenticationException;
import com.silverpeas.mobile.shared.exceptions.ContactException;
import com.silverpeas.mobile.shared.services.ServiceContact;
import com.silverpeas.socialnetwork.relationShip.RelationShipService;
import com.stratelia.webactiv.beans.admin.OrganizationController;
import com.stratelia.webactiv.beans.admin.UserDetail;
import com.stratelia.webactiv.beans.admin.UserFull;
import com.stratelia.webactiv.util.GeneralPropertiesManager;

public class ServiceContactImpl extends AbstractAuthenticateService implements ServiceContact {

  private static final long serialVersionUID = 1L;
  private OrganizationController organizationController = new OrganizationController();
  private RelationShipService relationShipService = new RelationShipService();

  /**
   * Return list of DetailUserDTO of my contacts
   * @return list of UserDetailDTO
   * @throws ContactException
   */
  public List<DetailUserDTO> getContacts(String filter, int pageSize, int startIndex) throws ContactException, AuthenticationException {
    ArrayList<DetailUserDTO> listUsers = new ArrayList<DetailUserDTO>();
    try {
      checkUserInSession();
      UserDetail user = getUserInSession();

      if(filter.equals(ContactFilters.ALL)){
        UserDetail[] tabUserDetail = organizationController.getAllUsers();
        for(int i=0; i<tabUserDetail.length; i++){
          if (i >= startIndex && i < startIndex + pageSize) {
            listUsers.add(populate(tabUserDetail[i]));
          }
        }
      }
      else if(filter.equals(ContactFilters.MY)){
        List<String> contactsIds = relationShipService.getMyContactsIds(Integer.parseInt(user.getId()));

        for (int j = 0; j < contactsIds.size(); j++) {
          if (j >= startIndex && j < startIndex + pageSize) {
            String id = contactsIds.get(j);
            UserDetail userDetail = getUserDetail(id);
            DetailUserDTO userDTO = populate(userDetail);
            listUsers.add(userDTO);
          }
        }
      }
    } catch (Exception e) {
      SilverTrace.error(SpMobileLogModule.getName(), "ServiceContactImpl.getContacts", "root.EX_NO_MESSAGE", e);
      throw new ContactException(e);
    }

    return listUsers;
  }

  /**
   * Populate user DTO.
   * @param userDetail
   * @return
   */
  private DetailUserDTO populate(UserDetail userDetail) {
    if (userDetail != null) {
      SilverTrace.debug(SpMobileLogModule.getName(), "ServiceContactImpl.populate",
          "User id=" + userDetail.getId());
      UserFull userFull = UserFull.getById(userDetail.getId());
      Mapper mapper = new DozerBeanMapper();
      DetailUserDTO userDTO = mapper.map(userDetail, DetailUserDTO.class);
      userDTO.setAvatar(GeneralPropertiesManager.getString("ApplicationURL") + userDetail.getAvatar());
      if (userFull != null) {
        userDTO.setPhoneNumber(userFull.getValue("phone"));
        userDTO.setCellularPhoneNumber(userFull.getValue("cellularPhone"));
        userDTO.setFaxPhoneNumber(userFull.getValue("fax"));
      }
      return userDTO;
    }
    return null;
  }

  /**
   * Return UserDetail with the id contact
   * @param id
   * @return UserDetail
   * @throws ContactException
   */
  private UserDetail getUserDetail(String id) throws ContactException {
    String ldapUserId = organizationController.getUserDetailByDBId(Integer.parseInt(id));
    UserDetail User = organizationController.getUserDetail(ldapUserId);
    return User;
  }
}