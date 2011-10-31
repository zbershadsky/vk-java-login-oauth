package org.zav.server;

import java.io.IOException;

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
      HttpServletRequest httpRequest = (HttpServletRequest)req;

      httpRequest.getRequestDispatcher("/index_private.jsp").include(req, res);
   }

}
