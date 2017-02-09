<%@page import="com.kartag.server.presentation.form.UserForm"%>
<%@page import="com.kartag.server.presentation.action.UserActions"%>
<%@page import="com.kartag.server.presentation.action.SystemUserActions"%>
<%@page import="com.kartag.server.model.SystemUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.*"%>

<%@page
	import="com.kartag.server.presentation.form.SystemUserForm"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="app.users" />
</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<script>
function confirmDelete(){
var agree=confirm('<bean:message key="app.alert.delete"/>');
if (agree)
     return true ;
else
     return false ;
}
</script>
	<table>
		<tr>
			<td align="left"><br> <html:link
					page="/admin.do?action=home">
					<bean:message key="app.home" />
				</html:link> <br></td>
			<td align="left"><br> <html:link
					page="/admin.do?action=populate">
					<bean:message key="app.add" />
				</html:link> <br></td>

		</tr>
	</table>
	<display:table id="user" name="Admin.users"
		requestURI="/admin.do?action=list" pagesize="10">
		
		<display:column>
		    <%if (!((SystemUserForm) pageContext
				.getAttribute("user")).getUserName().equals("admin")) {;
		    %>
			<a
				href="admin.do?action=delete&id=<%=((SystemUserForm) pageContext
							.getAttribute("user")).getUserName()%>"
				onClick="return confirmDelete();"> <bean:message
					key="app.delete" /> </a>
			<%};%>
		</display:column>
		<display:column>
		<%
						if (!((SystemUser)request.getSession().getAttribute("USER")).getUserName().equals(String.valueOf(((SystemUserForm) pageContext
								.getAttribute("user")).getUserName()))) {
							;
					%> <a
				href="admin.do?action=manageUser&<%=SystemUserActions.userId_req_param_name%>=<%=String.valueOf(((SystemUserForm) pageContext
							.getAttribute("user")).getUserName())%>">
				<bean:message
					key="app.manageuser" /> </a> <%
 	}
 	;
 %>
			
		</display:column>
		<display:column property="userName" titleKey="login.username"
			sortable="true" />
		<display:column property="userPass" titleKey="login.password"
			sortable="true" />
		
	</display:table>
	<table>
		<tr>
			<td align="left"><br> <html:link
					page="/admin.do?action=home">
					<bean:message key="app.home" />
				</html:link> <br></td>
			<td align="left"><br> <html:link
					page="/admin.do?action=populate">
					<bean:message key="app.add" />
				</html:link> <br></td>
		</tr>
	</table>
</body>
</html>