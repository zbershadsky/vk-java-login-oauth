package org.zav.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

   private static final String APP_ID = "2635070";

   private static final String APP_SECRET = "PZHMAHJrvGCzjEYQd6J8";

   private static final String SCOPE = "friends,video,offline";

   private static final String DISPLAY_TYPE = "page"; //page, popup, touch и wap. 

   private static final String USER_GROUP = "users";

   private static List<String> roles = new ArrayList<String>();
   static
   {
      roles.add(USER_GROUP);
   }

   @Override
   protected boolean authenticate(Request request, Response response, LoginConfig loginConfig) throws IOException
   {
      System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>> \n>>> alexey: VkAuthentificatorValve.authenticate 1 = " + 1);

      HttpServletRequest httpRequest = (HttpServletRequest)request;
      
      String cookie = httpRequest.getHeader("cookie");
      System.out.println(">>> alexey: VkAuthentificatorValve.authenticate cookie = " + cookie);
      

      Principal principal = httpRequest.getUserPrincipal();
      System.out.println(">>> alexey: VkAuthentificatorValve.authenticate principal = " + principal);

      // if ALL OK
      if (principal != null && principal.getName() != null)
      {
         // TODO
         //         String access_token = users.get(principal.getName());
         //         System.out.println(">>> alexey: VkAuthentificatorValve.authenticate access_token = " + access_token);
         //         httpRequest.getSession(true).setAttribute("access_token", access_token);
         // to remove token in the target servlet in each request iteration
         return true;
      }

      // the authentication
      String codeParameter = httpRequest.getParameter("code");
      System.out.println(">>> alexey: VkAuthentificatorValve.authenticate codeParameter = " + codeParameter);
      if (codeParameter == null)
      {

         // CHECK THE PARAMETER FROM THE LOGIN FORM
         String login = httpRequest.getParameter("mk_login");
         System.out.println(">>> alexey: VkAuthentificatorValve.authenticate login = " + login);
         if (login == null)
         {
            String currentUri = httpRequest.getRequestURL().toString();
            System.out.println(">>> alexey: VkAuthentificatorValve.authenticate httpRequest.getRequestURI() = "
               + httpRequest.getRequestURI());
            if ("/vk/private/login/".equalsIgnoreCase(httpRequest.getRequestURI())) {
               // CANCELLED LOGIN
               response.sendRedirect("/vk/index.jsp");
            } else {
               // USER REDIRECTS TO LOGIN PAGE
               String page = "/vk/login.jsp?url=" + currentUri;
               response.sendRedirect(page);
            }
            return false;
         }
         else
         {

            // need to send to the VK to auth

            System.out.println(">>> alexey: VkAuthentificatorValve.authenticate httpRequest.getQueryString() = "
               + httpRequest.getQueryString());

            String currentUri = httpRequest.getRequestURL().toString();

            String queryString = httpRequest.getQueryString();
            if (queryString != null && !"".equals(queryString) && queryString.contains("&"))
            {
               // if query not null|empty and contains more than "login=" param
               String query = "?";
               String[] paramPairArray = queryString.split("&");
               for (String paramPair : paramPairArray)
               {
                  if (!paramPair.startsWith("login="))
                  {
                     if (query.length() != 1)
                        query += "&";
                     query += paramPair;
                  }
               }
               currentUri += query;
            }

            System.out.println(">>> alexey: VkAuthentificatorValve.authenticate currentUri = " + currentUri);

            String encodedCurrentUri = URLEncoder.encode(currentUri, "UTF-8");
            System.out.println(">>> alexey: VkAuthentificatorValve.authenticate encodedCurrentUri = "
               + encodedCurrentUri);

            String myuriredirect = httpRequest.getParameter("myuriredirect");
            System.out.println(">>> alexey: VkAuthentificatorValve.authenticate myuriredirect = " + myuriredirect);

            if (myuriredirect != null)
            {
               // if cancelled login and will login again
               encodedCurrentUri = URLEncoder.encode(myuriredirect, "UTF-8");
               System.out.println(">>> alexey: VkAuthentificatorValve.authenticate !!! encodedCurrentUri = "
                  + encodedCurrentUri);
            }

            String redirectUri =
               "moyakarta.dyndns.org/vk/private/login/%3F" + "myuriredirect" + "%3D" + encodedCurrentUri;

            System.out.println(">>> alexey: VkAuthentificatorValve.authenticate redirectUri = " + redirectUri);

            String uri = "https://api.vkontakte.ru/oauth/authorize?" + //
               "client_id=" + APP_ID + //
               "&scope=" + SCOPE + //
               "&redirect_uri=" + redirectUri + // 
               "&display=" + DISPLAY_TYPE + //
               "&response_type=code";
            System.out.println(">>> alexey: VkAuthentificatorValve.authenticate REDIRECTING ... = ");
            response.sendRedirect(uri);
            return false;
         }
      }
      else
      {
         // come from VK with 'code' parameter
         //         principal = (GenericPrincipal)VkAuthentificatorValve.authenticate(httpRequest, APP_ID, APP_SECRET);

         String accessTokenUri = "https://api.vkontakte.ru/oauth/access_token" // 
            + "?" + "client_id=" + APP_ID //
            + "&" + "client_secret=" + APP_SECRET // 
            + "&" + "code=" + codeParameter;
         System.out.println(">>> alexey: VkAuthentificatorValve.authenticate accessTokenUri = " + accessTokenUri);

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
            System.out
               .println(">>> alexey: VkAuthentificatorValve.authenticate responseAsString = " + responseAsString);
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }

         Map<String, String> params = parseJsonAsParams(responseAsString);

         String username = params.get("user_id");
         //               String access_token = params.get("access_token");
         //               users.put(username, access_token);
         System.out.println(">>> alexey: VkAuthentificatorValve.authenticate username = " + username);

         if (username != null)
         {
            // the user is ok
            principal = new GenericPrincipal(null, username, "N/P", roles);
            System.out.println(">>> alexey: VkAuthentificatorValve.authenticate principal = " + principal);

            register(request, response, principal, "", principal.getName(), "N/P");
            return true;
         }
         else
         {
            System.out.println(">>> alexey: VkAuthentificatorValve.authenticate ERROR = ");
            String page = "/vk/index.jsp";
            response.sendRedirect(page);
            return false;
         }

      }

   }

   //   protected void forwardToErrorPage(Request request, Response response, LoginConfig config)
   //   {
   //      System.out.println(">>> alexey:  ++++++++++++ !!! VkAuthentificatorValve.forwardToErrorPage");
   //      String errorPage = "/error.jsp"; 
   //      RequestDispatcher disp = context.getServletContext().getRequestDispatcher(errorPage);
   //      try
   //      {
   //         disp.forward(request.getRequest(), response.getResponse());
   //      }
   //      catch (Throwable t)
   //      {
   //         LOG.warn("Unexpected error forwarding to error page", t);
   //      }
   //   }

   //   protected void sendRedirectToIndexPage(Request request, Response response, LoginConfig config) throws IOException
   //   {
   //      System.out.println(">>> alexey:  ++++++++++++ !!! VkAuthentificatorValve.sendRedirectToErrorPage");
   //      String page = "/vk/index.jsp";
   //      response.sendRedirect(page);
   //   }

   private static Map<String, String> parseJsonAsParams(String responseAsString)
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
