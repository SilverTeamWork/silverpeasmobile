package com.silverpeas.mobile.server.servlets;

import com.silverpeas.mobile.server.config.Configurator;
import com.silverpeas.mobile.server.services.AbstractAuthenticateService;
import com.silverpeas.mobile.shared.exceptions.AuthenticationException;
import com.stratelia.webactiv.beans.admin.OrganizationController;
import com.stratelia.webactiv.beans.admin.UserFull;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SuppressWarnings("serial")
public class SoundServlet extends HttpServlet {

  private OrganizationController organizationController = new OrganizationController();

  protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String id = request.getParameter("id");
    String instanceId = request.getParameter("instanceId");
    String userId = request.getParameter("userId");
    UserFull userF = organizationController.getUserFull(userId);

    // call web service localy on http
    String url = "http://" + Configurator.getConfigValue("localhost") + ":" + Configurator.getConfigValue("jboss.http.port");
    url = url + "/silverpeas/services/gallery/" + instanceId + "/sounds/" + id + "/content";

    getFile(url, response, userF.getToken());

    ((OutputStream) response.getOutputStream()).flush();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      checkUserInSession(request);
      processRequest(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void checkUserInSession(HttpServletRequest request) throws AuthenticationException {
    if (request.getSession().getAttribute(AbstractAuthenticateService.USER_ATTRIBUT_NAME) == null) {
      throw new AuthenticationException(AuthenticationException.AuthenticationError.NotAuthenticate);
    }
  }

  public static void getFile(String host, HttpServletResponse response, String token) {
    InputStream input = null;

    try {

      Protocol.registerProtocol("https", new Protocol("https", new EasySSLProtocolSocketFactory(), 443));

      HttpClient client = new HttpClient();
      HttpMethod method = new GetMethod(host);
      method.addRequestHeader("X-Silverpeas-Session", token);

      client.executeMethod(method);

      input = method.getResponseBodyAsStream();
      response.setContentType(method.getResponseHeader("Content-Type").getValue());
      response.setHeader("content-disposition", method.getResponseHeader("content-disposition").getValue());

      byte[] buffer = new byte[1024];
      int read;
      while ((read = input.read(buffer)) > 0) {
        ((OutputStream) response.getOutputStream()).write(buffer, 0, read);
      }
      response.setContentLength(Integer.parseInt(method.getResponseHeader("Content-Length").getValue()));
      method.releaseConnection();
    } catch (IOException e) {
      System.out.println("Error while trying to download the file.");
      e.printStackTrace();
    }
  }
}
