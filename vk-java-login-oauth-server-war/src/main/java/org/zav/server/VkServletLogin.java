package org.zav.server;

import java.io.IOException;
import java.security.Principal;
import java.util.Enumeration;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VkServletLogin extends GenericServlet
{

   private static final Logger LOG = LoggerFactory.getLogger(VkServletLogin.class);

   @Override
   public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
   {
      System.out.println("DEBUG: \n\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
      System.out.println("DEBUG: Entering the method");
      System.out.println(">>> alexey: VkServlet.service 1 = " + 1);

      HttpServletRequest httpRequest = (HttpServletRequest)req;

      Principal principal = httpRequest.getUserPrincipal();
      System.out.println("DEBUG:  principal = " + principal);
      if (principal != null)
         System.out.println("DEBUG:  principal.getName() = " + principal.getName());

      System.out.println("DEBUG:  hReq.getRemoteUser() = " + httpRequest.getRemoteUser());
      System.out.println("DEBUG:  hReq.getUserPrincipal() = " + httpRequest.getUserPrincipal());
      if (httpRequest.getUserPrincipal() != null)
         System.out.println("DEBUG:  hReq.getUserPrincipal().getName() = " + httpRequest.getUserPrincipal().getName());
      System.out.println("DEBUG:  hReq.getSession(false) = " + httpRequest.getSession(false));

      //      printParsAndAttrs(httpRequest);
      //      printHeaders(httpRequest);
      //      printCookies(httpRequest);

      String redirect_uri = httpRequest.getParameter("myuriredirect");
      System.out.println(">>> alexey: VkServletLogin.service redirect_uri = " + redirect_uri);

      ((HttpServletResponse)res).sendRedirect(redirect_uri);

   }

//   private void printParsAndAttrs(ServletRequest req)
//   {
//      Enumeration<String> parameterNames = req.getParameterNames();
//      while (parameterNames.hasMoreElements())
//      {
//         String name = (String)parameterNames.nextElement();
//         System.out.println("INFO: >>> alexey: VkServlet.logParsAndAttrs P name = " + name);
//         System.out.println("INFO: >>> alexey: VkServlet.logParsAndAttrs        = " + req.getParameter(name));
//      }
//      Enumeration<String> attributeNames = req.getAttributeNames();
//      while (attributeNames.hasMoreElements())
//      {
//         String name = (String)attributeNames.nextElement();
//         System.out.println("INFO: >>> alexey: VkServlet.logParsAndAttrs A name = " + name);
//         System.out.println("INFO: >>> alexey: VkServlet.logParsAndAttrs        = " + req.getAttribute(name));
//      }
//   }
//
//   private void printHeaders(HttpServletRequest hReq)
//   {
//      Enumeration<String> headerNames = hReq.getHeaderNames();
//      if (headerNames != null)
//      {
//         while (headerNames.hasMoreElements())
//         {
//            String name = (String)headerNames.nextElement();
//            System.out.println("INFO: >>> alexey: VkServlet.printHeaders H name = " + name);
//            System.out.println("INFO: >>> alexey: VkServlet.printHeaders        = " + hReq.getHeader(name));
//         }
//      }
//   }
//
//   private void printCookies(HttpServletRequest hReq)
//   {
//      Cookie[] cookies = hReq.getCookies();
//      if (cookies != null)
//      {
//         for (Cookie cookie : cookies)
//         {
//            System.out.println("INFO: >>> alexey: VkServlet.printCookies C name = " + cookie.getName());
//            System.out.println("INFO: >>> alexey: VkServlet.printCookies        = " + cookie.getValue());
//         }
//      }
//   }

}
