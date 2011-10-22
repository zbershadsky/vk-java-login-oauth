package org.zav.server;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.zav.auth.VkAuthentificatorValve;

public class VkServlet extends GenericServlet
{

   private static final Logger LOG = LoggerFactory.getLogger(VkServlet.class);

   @Override
   public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
   {
      System.out.println("DEBUG: \n\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
      System.out.println("DEBUG: Entering the method");
      System.out.println(">>> alexey: VkServlet.service 1 = " + 1);

      HttpServletRequest httpRequest = (HttpServletRequest)req;

      Principal principal = httpRequest.getUserPrincipal();
      System.out.println("DEBUG:  principal = " + principal);

      httpRequest.getRequestDispatcher("/index_private.jsp").include(req, res);

   }

}
