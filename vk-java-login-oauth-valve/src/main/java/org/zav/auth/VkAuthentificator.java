package org.zav.auth;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

public class VkAuthentificator
{

   public static Principal authenticate(HttpServletRequest httpRequest, String appId, String appSecret)
   {
      System.out.println(">>> alexey: VkAuthentificator.authenticate 1 = " + 1);
      System.out.println(">>> alexey:  httpRequest.getRequestURI() = " + httpRequest.getRequestURI());

      return null;
   }

}