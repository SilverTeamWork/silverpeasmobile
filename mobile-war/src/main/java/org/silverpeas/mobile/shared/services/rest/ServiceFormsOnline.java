/*
 * Copyright (C) 2000 - 2018 Silverpeas
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception.  You should have received a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "http://www.silverpeas.org/docs/core/legal/floss_exception.html"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.silverpeas.mobile.shared.services.rest;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import org.silverpeas.mobile.shared.dto.BaseDTO;
import org.silverpeas.mobile.shared.dto.FormFieldDTO;
import org.silverpeas.mobile.shared.dto.UserDTO;
import org.silverpeas.mobile.shared.dto.formsonline.FormDTO;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * @author svu
 */
@Path("/formsOnline")
public interface ServiceFormsOnline extends RestService {


  @GET
  @Path("sendables/{appId}")
  public void getSendablesForms(@PathParam("appId") String appId,
      MethodCallback<List<FormDTO>> callback);


  @GET
  @Path("form/{appId}/{formName}")
  public void getForm(@PathParam("appId") String appId, @PathParam("formName") String formName,
      MethodCallback<List<FormFieldDTO>> callback);

  @POST
  @Path("saveForm")
  public void saveForm(MethodCallback<Boolean> callback);

  @GET
  @Path("form/{appId}/{formName}/{fieldName}")
  public void getUserField(@PathParam("appId") String appId, @PathParam("formName") String formName,
      @PathParam("fieldName") String fieldName, MethodCallback<List<BaseDTO>> callback);


}
