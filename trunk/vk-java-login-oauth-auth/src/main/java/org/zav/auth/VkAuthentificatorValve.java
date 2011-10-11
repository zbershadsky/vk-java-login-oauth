package org.zav.auth;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.Principal;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.authenticator.AuthenticatorBase;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.deploy.LoginConfig;
import org.apache.catalina.realm.GenericPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VkAuthentificatorValve extends AuthenticatorBase
{

   private static final Logger LOG = LoggerFactory.getLogger(VkAuthentificatorValve.class);

   @Override
   protected boolean authenticate(Request request, Response response, LoginConfig loginConfig) throws IOException
   {
      
      System.out.println(">>> alexey: VkAuthentificatorValve.authenticate 1 = " + 1);
      LOG.debug("Entering the method");

      HttpServletRequest httpRequest = (HttpServletRequest)request;
      
      Principal principal = httpRequest.getUserPrincipal();

      if (principal != null)
         System.out.println("DEBUG:  principal.getName() = " + principal.getName());

      // if ALL OK
      if (principal != null && principal.getName() != null)
         return true;

      // the authentication
      String codeParameter = httpRequest.getParameter("code");
      System.out.println(">>> alexey: VkAuthentificatorValve.authenticate codeParameter = " + codeParameter);
      if (codeParameter == null) {
         // need to send to the vk to auth
         String currentUri = httpRequest.getRequestURL().toString();
         System.out.println(">>> alexey: VkAuthentificatorValve.authenticate currentUri = " + currentUri);
         
         String encodedCurrentUri = URLEncoder.encode(currentUri, "UTF-8");
         System.out.println(">>> alexey: VkAuthentificatorValve.authenticate encodedCurrentUri = " + encodedCurrentUri);
         
         String myuriredirect = httpRequest.getParameter("myuriredirect");
         System.out.println(">>> alexey: VkAuthentificatorValve.authenticate myuriredirect = " + myuriredirect);
         
         if (myuriredirect != null) {
            encodedCurrentUri = URLEncoder.encode(myuriredirect, "UTF-8");
            System.out.println(">>> alexey: VkAuthentificatorValve.authenticate !!! encodedCurrentUri = "
               + encodedCurrentUri);
         }
         
         String redirectUri = "moyakarta.dyndns.org/vk/private/login/%3Fmyuriredirect%3D" + encodedCurrentUri ;
         
         String uri = "http://api.vkontakte.ru/oauth/authorize?" + //
         		"client_id=2635070" + //
         		"&scope=friends,video,offline" + //
         		"&redirect_uri=" + redirectUri + // 
         		"&display=page" + //
         		"&response_type=code" ;
//         		"&_hash=0";
         System.out.println(">>> alexey: VkAuthentificatorValve.authenticate REDIRECTING ... = ");
         response.sendRedirect(uri);
         return false;
      } else {
         // come from vkontakte with code
        principal = (GenericPrincipal)VkAuthentificator.authenticate(httpRequest);
      }
      
      LOG.debug(" principal = " + principal);
      if (principal == null)
      {
         System.out.println(">>> alexey: VkAuthentificatorValve.authenticate ERROR = ");
         sendRedirectToErrorPage(request, response, loginConfig);
         return false;
      }

      register(request, response, principal, "", principal.getName(), "N/P");
      return true;
   }
   
   

   protected void forwardToErrorPage(Request request, Response response, LoginConfig config)
   {
      System.out.println(">>> alexey:  ++++++++++++ !!! VkAuthentificatorValve.forwardToErrorPage");
      String errorPage = "/error.jsp"; 
      RequestDispatcher disp = context.getServletContext().getRequestDispatcher(errorPage);
      try
      {
         disp.forward(request.getRequest(), response.getResponse());
      }
      catch (Throwable t)
      {
         LOG.warn("Unexpected error forwarding to error page", t);
      }
   }
   
   
   protected void sendRedirectToErrorPage(Request request, Response response, LoginConfig config) throws IOException
   {
      System.out.println(">>> alexey:  ++++++++++++ !!! VkAuthentificatorValve.sendRedirectToErrorPage");
      String errorPage = "/vk/index.jsp";
      response.sendRedirect(errorPage);
   }
}
