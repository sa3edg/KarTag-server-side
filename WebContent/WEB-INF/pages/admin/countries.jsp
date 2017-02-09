<%@ include file="../general/StandardFrame.jsp" %>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Countries</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<script>

</script>
	<table>
		<tr>
			<td align="left"><html:link page="/admin.do?action=home">
					<bean:message key="app.home" />
				</html:link>
			</td>
			<td>
				<%
					if (request.getSession().getAttribute("USER_ROLE")
							.equals(SystemUser.ADMIN)) {
						;
				%><a
				href="admin.do?action=forwardAssignCountry&userId=<%=request.getParameter("userId")%>">
					Add Country </a> <%
 	}
 	;
 %>
			</td>


		</tr>
	</table>
	<display:table id="country" name="Admin.channels"
		requestURI="/countryActions.do?action=list" pagesize="10">
		<display:column property="id" titleKey="app.id" sortable="true" />
		<display:column property="name" titleKey="app.name" sortable="true" />
	</display:table>
	<table>
		<tr>
			<td align="left"><html:link page="/admin.do?action=home">
					<bean:message key="app.home" />
				</html:link>
			</td>
			<td>
				<%
					if (request.getSession().getAttribute("USER_ROLE")
							.equals(SystemUser.ADMIN)) {
						;
				%><a
				href="admin.do?action=forwardAssignCountry&userId=<%=request.getParameter("userId")%>">
					Add Country </a> <%
 	}
 	;
 %>
			</td>
		</tr>
	</table>
</body>
</html>