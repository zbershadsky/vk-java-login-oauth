<%System.out.println("index_private.jsp: >>> begin");%>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
      <meta http-equiv="content-type" content="text/html; charset=windows-1251" /> 
      <title>Open API sample page PRIVATE</title> 
      <script src="http://code.jquery.com/jquery-1.6.4.min.js" type="text/javascript"></script>
      <script type="text/javascript">
      
         var hash_href =  document.location.hash;
         
         var beginIndex = hash_href.indexOf('access_token=') + "access_token=".length;
        	var endIndex = hash_href.indexOf('&',beginIndex);
         var access_token = hash_href.substring(beginIndex, endIndex);

         function ajaxSendToVk() {
            $.ajax({
               url: 'https://api.vkontakte.ru/method/getProfiles?uid=1&access_token=' + access_token,
		            dataType: 'jsonp',
		            crossDomain: true,
                  success: function( data ) {
                     var items = [];
                     items.push('<li id="' + data.response[0].uid + '">' + data.response[0].first_name + '</li>');
                     $('<ul/>', {
                   	   'class': 'my-new-list',
                   	   html: items.join('')
                     }).appendTo('body');
                  }
            });
         }
      </script> 

  </head> 
  <body> 
  
PRIVATE PAGE <a href="/vk/logout.jsp">logout</a><br/>
<br/>
<a href="http://moyakarta.dyndns.org/vk">public</a><br/>
<br/>
<a href="http://api.vkontakte.ru/oauth/authorize?client_id=2635070&scope=friends,video,offline&display=page&redirect_uri=<%= request.getRequestURL().toString().substring(request.getRequestURL().indexOf("/")+2) %>&response_type=token">link to client auth</a><br/>
<input id="gpsbutton" type="button" value="ajax send" name="buttongpsmarker" onClick="ajaxSendToVk()">
<br/>

  </body> 
</html>