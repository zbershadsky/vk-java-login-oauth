<%System.out.println("login.jsp: >>> begin");%>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
      <meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
      <title>Open API sample page LOGIN</title>
      <script src="http://code.jquery.com/jquery-1.6.4.min.js" type="text/javascript"></script>
      <script src="http://vkontakte.ru/js/api/openapi.js" type="text/javascript"></script>

  </head> 
  <body> 
  
  
      <script type="text/javascript">
      
          function ajaxGetUser(mid) {
             $.ajax({
                 url: 'https://api.vkontakte.ru/method/getProfiles?uids='+mid,
                 dataType: 'jsonp',
                 crossDomain: true,
                 success: function(data) {
                    if (data) {
                    	  // if user is, add name for login and link for logout
                       var firstname = data.response[0].first_name;
                       var lastname = data.response[0].last_name;
                       $('#loginVkLink').append(' as ('+firstname+' '+lastname+')'); 
                    	  $('<a id ="logoutVkLink" href="javascript:ajaxLogout()">log out</a><br/>').appendTo('body');
                    }
                 },
                 error: function() {
                 },
                 jsonpCallback: function(data) {
                 }
              });
         }
          
          function logouted() {
        	  // TODO maybe need a timeout
        	   $('#logoutVkLink').hide();
        	   $('#loginVkLink').html('log in');
//               var cc = $('#loginVkLink').attr('href');
//               window.location = cc;
          }
           
         function ajaxLogout() {
             $.ajax({
                 url: 'http://m.vkontakte.ru/logout',
                 dataType: 'jsonp',
                 crossDomain: true,
                 success: logouted(),
                 error: function() {
                 },
                 jsonpCallback: function() {
                 }
              });
              // TODO remove change user link
         }

         VK.init({apiId: 2635070});
         
         function authInfo(response) {
           if (response.session) {
             mid = response.session.mid;
             ajaxGetUser(mid);
           }
         }

         VK.Auth.getLoginStatus(authInfo);
         
      </script> 
      
 
LOGIN PAGE <a href="/vk/index.jsp">main</a><br/>
<br/>
<a id ="loginVkLink" href="<%=request.getParameter("url")%>?mk_login=vk">log in </a>&nbsp;
<br/>

<%-- <%=((javax.servlet.http.HttpServletRequest)request).getHeader("cookie") %> --%>

<br/>


  </body> 
</html>