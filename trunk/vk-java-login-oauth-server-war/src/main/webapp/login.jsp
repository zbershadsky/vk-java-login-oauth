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
              $('#logoutVkLink').hide();
              $('#vk_auth').hide();
              setTimeout(function(){
            	   $('#loginVkLink').show();
              }, 1000 );
          }
          
          function ajaxLogout() {
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

      </script> 
      
 
LOGIN PAGE <a href="/vk/index.jsp">main</a><br/>
<br/>

<div id="mk_vk_auth">
   <a id ="loginVkLink" href="<%=request.getParameter("url")%>?mk_login=vk" >VKontakte log in</a>&nbsp;
</div>

<br/>

<div id="vk_auth"></div>
<script type="text/javascript">
VK.Widgets.Auth("vk_auth", {width: "200px", authUrl: '<%=request.getParameter("url")%>?mk_login=vk'});

function resizeVkAuth() {
	if ($('#vk_auth').height() > 70) {
		// 105 -> 64
      $('#vk_auth iframe').height(64);
      $('#vk_auth').height(64);
      $('#logoutVkLink').show();
   } else {
	   // 63 -> 22
      $('#vk_auth iframe').height(22);
      $('#vk_auth').height(22);
      $('#vk_auth iframe').hide();
      $('#vk_auth').hide();
   }
}

function preResizeVkAuth() {
      if ($('#vk_auth').height() == 105 || $('#vk_auth').height() == 63) {
   	   resizeVkAuth();
      }
}

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
    preResizeVkAuth();
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
    if ($('#vk_auth').height() == 80) {
          // 80 -> 64
          $('#vk_auth iframe').height(64);
          $('#vk_auth').height(64);
          $('#logoutVkLink').show();
       }
}, 8000 );

setTimeout(function(){
    preResizeVkAuth();
}, 9000 );

setTimeout(function(){
	   if ($('#vk_auth').height() == 80) {
		      // 80 -> 64
		      $('#vk_auth iframe').height(64);
		      $('#vk_auth').height(64);
		      $('#logoutVkLink').show();
		   }
}, 10000 );

</script>

<a id ="logoutVkLink" href="javascript:ajaxLogout()" class="hideElem" style="display: none">Log in as another user</a><br/>


  </body> 
</html>