<%System.out.println(">>> login.jsp: begin");%>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
      <meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
      <title>Open API sample page LOGIN</title>
      <script src="http://code.jquery.com/jquery-1.6.4.min.js" type="text/javascript"></script>
      <script src="http://vkontakte.ru/js/api/openapi.js" type="text/javascript"></script>
<!--       <script src="http://userapi.com/js/api/openapi.js?34" type="text/javascript"></script> -->




  </head> 
  <body> 
  
  
      <script type="text/javascript">
          
          VK.init({apiId: 2635070});

          
          // LOGOUT
          
          function logouted() {
              
              setTimeout(function(){
            	   $('#loginVkLink').show();
              }, 1000 );
          }
          
          function ajaxLogout() {
        	    $('#vk_auth').hide();
        	    $('#vk_auth iframe').hide();
        	    $('#mk_vk_auth_logout').hide();
        	    
        	    $('#loginVkLink').hide();
             $.ajax({
                 url: 'https://m.vkontakte.ru/logout',
                 dataType: 'jsonp',
                 crossDomain: true,
                 success: logouted(),
                 error: function() {
                 },
                 jsonpCallback: function() {
                 }
              });
          }
          
          
          // RESIZE VK AUTH
          
          function showLogout() {
             $('#vk_auth').height(64);
             $('#vk_auth iframe').height(64);
        	    $('#mk_vk_auth_logout').show();
        	    $('#vk_auth').show();
          }
          
         function realResizeVkAuth() {
            if ($('#vk_auth iframe').height() > 70) {
               // 105 -> 64
               // logined
               showLogout();
            } else {
               // 63 -> 22
               // logouted
            }
         }
          
         function realResizeVkAuthMobile() {
            if ($('#vk_auth iframe').height() == 80) {
                // 80 -> 64
                showLogout();
            }
         }
         
         function preResizeVkAuth() {
            if ($('#vk_auth iframe').height() >= 105 || $('#vk_auth iframe').height() == 63) {
            	// || $('#vk_auth iframe').height() == 185 || $('#vk_auth iframe').height() == 147) {
               realResizeVkAuth();
            }
         }
         
         // TIMEOUT ON RESIZE
         
         function resizeVkAuth() {
            setTimeout(function(){
                preResizeVkAuth();
            }, 750 );
        	   
            setTimeout(function(){
                preResizeVkAuth();
            }, 1000 );
            
            setTimeout(function(){
                preResizeVkAuth();
            }, 1500 );
            
            setTimeout(function(){
                preResizeVkAuth();
            }, 2000 );
            
            setTimeout(function(){
                preResizeVkAuth();
            }, 3000 );
            
            setTimeout(function(){
                realResizeVkAuthMobile();
            }, 4000 );
            
            setTimeout(function(){
                preResizeVkAuth();
            }, 5000 );
            
            setTimeout(function(){
                preResizeVkAuth();
            }, 6000 );
            
            setTimeout(function(){
                preResizeVkAuth();
            }, 7000 );
            
            setTimeout(function(){
            	 realResizeVkAuthMobile();
            }, 8000 );
            
            setTimeout(function(){
                preResizeVkAuth();
            }, 9000 );
            
            setTimeout(function(){
            	 realResizeVkAuthMobile();
            }, 10000 );
         }

      </script> 
      
 
LOGIN PAGE <a href="/vk/index.jsp">main</a><br/>
<br/>

<div id="mk_vk_auth" style="width: 200px;">
   <div id="vk_auth_mk" style="font-family: tahoma, arial, verdana, sans-serif, Lucida Sans;font-size: 11px;width: 200px; background-image: none; background-attachment: initial; background-origin: initial; background-clip: initial; background-color: initial; height: 39px; background-position: initial initial; background-repeat: initial initial; ">
      <div style="padding-top: 5px; padding: 3px 3px 6px 3px;background: white;border: 1px solid #CCC;">
         <div id="auth_button" style="border: 1px solid #3B6798; text-shadow: 0px 1px 0px #45688E; margin: 2px 5px; cursor: pointer; width: auto; height: auto;">
            <a id ="loginVkLink" href="<%=request.getParameter("url")%>?mk_login=vk" style="text-decoration: none;">
               <div id="auth_user" style="border-style: solid;border-width: 1px;border-color: #7E9CBC #5C82AB #5C82AB;background-color: #6D8FB3;color: white;padding: 4px 14px;text-align: center;">Войти через ВКонтакте</div>
            </a>
         </div>
      </div>
   </div>
</div>

<div id="vk_auth" style="display: none;"></div>

<script type="text/javascript">
   VK.Widgets.Auth("vk_auth", {width: "200px", authUrl: '<%=request.getParameter("url")%>?mk_login=vk'});
   resizeVkAuth();
</script>

<!-- LOGOUT -->
<div id="mk_vk_auth_logout" style="width: 200px; display: none;">
   <div id="vk_auth_mk_logout" style="font-family: tahoma, arial, verdana, sans-serif, Lucida Sans;font-size: 11px;width: 200px; background-image: none; background-attachment: initial; background-origin: initial; background-clip: initial; background-color: initial; height: 39px; background-position: initial initial; background-repeat: initial initial; ">
      <div style="padding-top: 5px; padding: 3px 3px 6px 3px;background: white;border: 1px solid #CCC;">
         <div id="auth_button_logout" style="margin: 2px 5px; cursor: pointer; width: auto; height: auto;">
            <a id ="logoutVkLink" href="javascript:ajaxLogout()" style="text-decoration: none;">
               <div id="auth_user" style="border-style: solid;border-width: 1px;border-color: #7E9CBC #5C82AB #5C82AB;background-color: #F2F2F2;color: #2B587A;padding: 4px 14px;text-align: center;">Другой пользователь</div>
            </a>
         </div>
      </div>
   </div>
</div>

  </body> 
</html>