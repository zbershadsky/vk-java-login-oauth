package org.zav.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.realm.GenericPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VkAuthentificator
{
   private static final Logger LOG = LoggerFactory.getLogger(VkAuthentificator.class);

   private static final String APP_ID = "2635070";

   private static final String APP_SECRET = "PZHMAHJrvGCzjEYQd6J8";

   private static final String USER_GROUP = "users";

   private static List<String> roles = new ArrayList<String>();
   static
   {
      roles.add(USER_GROUP);
   }

   private static Map<String, String> users = new HashMap<String, String>();

   public static Principal authenticate(HttpServletRequest httpRequest)
   {
      System.out.println(">>> alexey: VkAuthentificator.authenticate 1 = !!! " + 1);
      System.out.println("DEBUG:  httpRequest.getRequestURI() = " + httpRequest.getRequestURI());
      Principal principal = httpRequest.getUserPrincipal();
      System.out.println("DEBUG:  principal = " + principal);
      if (principal != null)
         System.out.println("DEBUG:  principal.getName() = " + principal.getName());

      if (principal != null && principal.getName() != null)
      {
         // TODO
         String access_token = users.get(principal.getName());
//         httpRequest.getSession(true).setAttribute("access_token", access_token);
         // to remove token in the target servlet in each request iteration
         
         return principal;
      }

      // the authentication
      String codeParameter = httpRequest.getParameter("code");
      System.out.println(">>> alexey: VkAuthentificator.authenticate codeParameter = " + codeParameter);

      if (codeParameter != null)
      {
         String accessTokenUri = "https://api.vkontakte.ru/oauth/access_token" // 
            + "?" + "client_id=" + APP_ID //
            + "&" + "client_secret=" + APP_SECRET // 
            + "&" + "code=" + codeParameter;
         System.out.println(">>> alexey: VkAuthentificator.authenticate accessTokenUri = " + accessTokenUri);

         String responseAsString = null;
         try
         {
            URL url = new URL(accessTokenUri);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            InputStream inputStream = conn.getInputStream();
            responseAsString = convertStreamToString(inputStream);
            System.out.println(">>> alexey: VkAuthentificator.authenticate responseAsString = " + responseAsString);
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }

         Map<String, String> params = parseParams(responseAsString);

         String username = params.get("user_id");
         String access_token = params.get("access_token");;
         users.put(username, access_token);
         System.out.println(">>> alexey: VkAuthentificator.authenticate username = " + username);

         if (username != null)
         {
            // the user is ok
            GenericPrincipal serverPrincipal = new GenericPrincipal(null, username, "N/P", roles);
            return serverPrincipal;
         }
      }

      // the user was wrong
      return null;

   }

   private static Map<String, String> parseParams(String responseAsString)
   {
      Map<String, String> result = new HashMap<String, String>();

      if (responseAsString.startsWith("{") && responseAsString.endsWith("}"))
         responseAsString = responseAsString.substring(1, responseAsString.length() - 1);

      String[] params = responseAsString.split(",");
      for (String string : params)
      {
         String[] pars = string.split(":");
         String name = pars[0];
         if (name.startsWith("\"") && name.endsWith("\""))
            name = name.substring(1, name.length() - 1);
         String value = pars[1];
         if (value.startsWith("\"") && value.endsWith("\""))
            value = value.substring(1, value.length() - 1);

         System.out.println(">>> alexey: SomeStuff.main name = " + name);
         System.out.println(">>> alexey: SomeStuff.main value = " + value);

         result.put(name, value);
      }
      return result;
   }

   private static String convertStreamToString(InputStream is) throws IOException
   {
      if (is != null)
      {
         Writer writer = new StringWriter();

         char[] buffer = new char[1024];
         try
         {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1)
            {
               writer.write(buffer, 0, n);
            }
         }
         finally
         {
            is.close();
         }
         return writer.toString();
      }
      else
      {
         return "";
      }
   }

}