package org.zav.server;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
      System.out.println("\n\n");
      System.out.println(">>> alexey: VkServletLogin.service \n\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
      System.out.println(">>> alexey: VkServletLogin.service 1 = " + 1);

      HttpServletRequest httpRequest = (HttpServletRequest)req;

      Principal principal = httpRequest.getUserPrincipal();
      System.out.println("DEBUG:  principal = " + principal);

      String myuriredirect = httpRequest.getParameter("myuriredirect");
      System.out.println(">>> alexey: VkServletLogin.service myuriredirect = " + myuriredirect);

      if (myuriredirect!=null && !"".equalsIgnoreCase(myuriredirect)){
         System.out.println(">>> alexey: VkServletLogin.service Redirecting... myuriredirect :) = ");
         ((HttpServletResponse)res).sendRedirect(myuriredirect);
      } else {
         System.out.println(">>> alexey: VkServletLogin.service Redirecting... main :( = ");
         ((HttpServletResponse)res).sendRedirect("/vk/index.jsp");
      }
   }

}
