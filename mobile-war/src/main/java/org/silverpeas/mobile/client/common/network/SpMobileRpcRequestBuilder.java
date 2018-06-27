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

package org.silverpeas.mobile.client.common.network;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;
import org.silverpeas.mobile.client.common.AuthentificationManager;

/**
 * @author: svu
 */
public class SpMobileRpcRequestBuilder extends RpcRequestBuilder {
  private int timeout;

  public SpMobileRpcRequestBuilder() {
    timeout = SpMobileRequestBuilder.TIMEOUT;
  }

  public SpMobileRpcRequestBuilder(int timeout) {
    this.timeout = timeout;
  }

  @Override
  protected RequestBuilder doCreate(String serviceEntryPoint) {
    RequestBuilder builder = super.doCreate(serviceEntryPoint);
    builder.setTimeoutMillis(this.timeout);


    if (AuthentificationManager.getInstance().getHeader(AuthentificationManager.XSTKN) != null) {
      builder.setHeader(AuthentificationManager.XSTKN,
          AuthentificationManager.getInstance().getHeader(AuthentificationManager.XSTKN));
    }
    if (AuthentificationManager.getInstance()
        .getHeader(AuthentificationManager.XSilverpeasSession) != null) {
      builder.setHeader(AuthentificationManager.XSilverpeasSession,
          AuthentificationManager.getInstance()
              .getHeader(AuthentificationManager.XSilverpeasSession));
      builder.setHeader("Cookie", "JSESSIONID=" + AuthentificationManager.getInstance()
          .getHeader(AuthentificationManager.XSilverpeasSession));
    }

    /*if (SpMobil.getUserToken() != null) {
      builder.setHeader("X-Silverpeas-Session", SpMobil.getUserToken());
      builder.setHeader("X-STKN", AuthentificationManager.getInstance().getHeaders().get("X-STKN"));
    }*/


    return builder;
  }
}