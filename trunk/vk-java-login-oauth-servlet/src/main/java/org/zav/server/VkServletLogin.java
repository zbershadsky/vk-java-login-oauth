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
      HttpServletRequest httpRequest = (HttpServletRequest)req;

      String myuriredirect = httpRequest.getParameter("myuriredirect");

      if (myuriredirect!=null && !"".equalsIgnoreCase(myuriredirect)){
         ((HttpServletResponse)res).sendRedirect(myuriredirect);
      } else {
         ((HttpServletResponse)res).sendRedirect("/vk/index.jsp");
      }
   }

}
