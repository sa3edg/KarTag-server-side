<%@ include file="../general/StandardFrame.jsp" %>
<html>
<head>
<title><bean:message key="app.add" /></title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		

<body>
   <%if (request.getParameter("result") != null) {;%>
   <%if ("succed".equals(request.getParameter("result"))) {;%>
   <script type="text/javascript">
   		alert('<bean:message key="notification.succed" />');
   </script>
   <%}else{;%>
   <script type="text/javascript">
   		alert('<bean:message key="notification.failed" />');
   </script>
   <%};%>
   <%};%>
	<html:form action="/notificationActions" method="post"
		enctype="multipart/form-data">
		<table border="0">
		   <tr>
           		<td><font color="blue">  <bean:message key="notification.selectCountry"/> </font>
           </td>
           <td>
           		<html:select property="countryId" >
                		<html:options collection="countries" property="id" labelProperty="name" />
                </html:select>
           </td>
           </tr>
           <tr>
           <td>
           		<font color="blue">  <bean:message key="notification.selectHandset"/> </font>
           </td>
           <td>
           		<html:select property="targetHandset" >
                	<html:options collection="handsets" property="id" labelProperty="name" />
                </html:select>
            </td>
            </tr>
			<tr>
				<td>
					<font color="blue"> <bean:message key="notification.message"/> </font>
				</td>
				<td>
					<html:textarea styleId="message" property="notificationMessage" />
				</td>
			</tr>
			<tr>
				<td colspan="5" align="center"><html:submit
						onclick="return sendMessage();" property="action">
						<bean:message key="notification.send" />
					</html:submit></td>
				<td colspan="5" align="center"><html:submit
						 property="action">
						<bean:message key="app.cancel" />
					</html:submit>
				</td>

			</tr>

		</table>
	</html:form>
</body>
</html>