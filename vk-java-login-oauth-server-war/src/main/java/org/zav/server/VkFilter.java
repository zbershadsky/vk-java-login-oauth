package org.zav.server;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VkFilter implements Filter
{

   private static final Logger LOG = LoggerFactory.getLogger(VkFilter.class);

   @Override
   public void destroy()
   {
      // nothing to do.
   }

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
      ServletException
   {
      System.out.println(">>> alexey: VkFilter.doFilter 1 = " + 1);

      HttpServletRequest httpRequest = (HttpServletRequest)request;
      Principal principal = httpRequest.getUserPrincipal();
      System.out.println(">>> alexey: VkServlet.service principal = " + principal);
      if (principal != null)
         System.out.println(">>> alexey: VkServlet.service principal.getName() = " + principal.getName());

      chain.doFilter(request, response);

   }

   @Override
   public void init(FilterConfig filterConfig) throws ServletException
   {
      // nothing to do.
   }

}
