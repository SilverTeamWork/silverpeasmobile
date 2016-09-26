package com.silverpeas.mobile.shared.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.silverpeas.mobile.shared.dto.DetailUserDTO;

public interface ServiceContactAsync {
  void getContacts(String filter, int pageSize, int startIndex, final AsyncCallback<List<DetailUserDTO>> async);
}
