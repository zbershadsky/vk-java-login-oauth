<%@page import="org.slf4j.Logger"%>
<%@page import="org.slf4j.LoggerFactory"%>
<% final Logger LOG = LoggerFactory.getLogger("index.jsp");%>
<%LOG.debug("Entering...");%>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
      <meta http-equiv="content-type" content="text/html; charset=windows-1251" /> 
      <title>Open API sample page</title> 
      <script src="http://code.jquery.com/jquery-1.6.4.min.js" type="text/javascript"></script>
      <script type="text/javascript">
         var hash_href =  location.hash;
         
         if (hash_href != null && "" != hash_href && hash_href=="#logout_vk") {
             hash_href = "";
             location.hash = "";
                 $.ajax({
                    url: 'http://api.vkontakte.ru/oauth/logout',
                       dataType: 'jsonp',
                       crossDomain: true,
                       success: function( data ) {
                       }
                 });
            }
      </script>
      <script type="text/javascript">
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
 
PUBLIC PAGE <a href="http://moyakarta.dyndns.org/vk/logout.jsp">clean session</a>
<br/>

<a href="http://api.vkontakte.ru/oauth/authorize?client_id=2635070&scope=friends,video,offline&redirect_uri=<%= request.getRequestURL().toString().substring(request.getRequestURL().indexOf("/")+2) %>&display=page&response_type=token">link to client auth</a>
<br /><br />
<a href="http://moyakarta.dyndns.org/vk/private/ok">link secure path</a>
<br /><br />

<input id="gpsbutton" type="button" value="ajax send" name="buttongpsmarker" onClick="ajaxSendToVk()">
  </body> 
</html>