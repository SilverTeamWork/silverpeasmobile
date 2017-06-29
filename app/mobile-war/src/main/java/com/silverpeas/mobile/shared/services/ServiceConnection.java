package com.silverpeas.mobile.shared.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.silverpeas.mobile.shared.dto.DetailUserDTO;
import com.silverpeas.mobile.shared.dto.DomainDTO;
import com.silverpeas.mobile.shared.exceptions.AuthenticationException;
import com.silverpeas.mobile.shared.exceptions.NavigationException;

@RemoteServiceRelativePath("Connection")
public interface ServiceConnection extends RemoteService {
	public DetailUserDTO login(String login, String password, String domainId) throws AuthenticationException;
	public List<DomainDTO> getDomains();
	public void logout() throws AuthenticationException;
  public boolean setTabletMode() throws NavigationException, AuthenticationException;
}
