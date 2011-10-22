package org.zav.auth;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VkAuthentificator
{
   private static final Logger LOG = LoggerFactory.getLogger(VkAuthentificator.class);

   private static final String USER_GROUP = "users";

   private static List<String> roles = new ArrayList<String>();
   static
   {
      roles.add(USER_GROUP);
   }

   private static Map<String, String> users = new HashMap<String, String>();

   public static Principal authenticate(HttpServletRequest httpRequest, String appId, String appSecret)
   {
      System.out.println(">>> alexey: VkAuthentificator.authenticate 1 = !!! " + 1);
      System.out.println("DEBUG:  httpRequest.getRequestURI() = " + httpRequest.getRequestURI());


      return null;

   }

//   private static Map<String, String> parseJsonAsParams(String responseAsString)
//   {
//      Map<String, String> result = new HashMap<String, String>();
//
//      if (responseAsString.startsWith("{") && responseAsString.endsWith("}"))
//         responseAsString = responseAsString.substring(1, responseAsString.length() - 1);
//
//      String[] params = responseAsString.split(",");
//      for (String string : params)
//      {
//         String[] pars = string.split(":");
//         String name = pars[0];
//         if (name.startsWith("\"") && name.endsWith("\""))
//            name = name.substring(1, name.length() - 1);
//         String value = pars[1];
//         if (value.startsWith("\"") && value.endsWith("\""))
//            value = value.substring(1, value.length() - 1);
//
//         result.put(name, value);
//      }
//      return result;
//   }
//
//   private static String convertStreamToString(InputStream is) throws IOException
//   {
//      if (is != null)
//      {
//         Writer writer = new StringWriter();
//
//         char[] buffer = new char[1024];
//         try
//         {
//            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//            int n;
//            while ((n = reader.read(buffer)) != -1)
//            {
//               writer.write(buffer, 0, n);
//            }
//         }
//         finally
//         {
//            is.close();
//         }
//         return writer.toString();
//      }
//      else
//      {
//         return "";
//      }
//   }

}