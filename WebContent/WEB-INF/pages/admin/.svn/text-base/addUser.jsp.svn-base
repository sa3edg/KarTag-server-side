<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kartag.server.model.SystemUser"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<html>
<head>
<title><bean:message key="app.add" /></title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<body>
	<script>
		function set(target) {
			document.forms[0].action.value = target;
		}
	</script>
	<html:form action="/admin" method="post" enctype="multipart/form-data">
		<html:hidden property="action" value="add"></html:hidden>
		<table border="0">
			<tr>
				<td align="left"><font color="blue"> <bean:message
							key="login.username" /> </font>
				</td>
				<td align="left"><html:text styleId="text" property="userName"
						size="40" maxlength="40" /> <font color="red"> <html:errors
							property="userName" /> </font>
				</td>
			</tr>
			<tr>
				<td><font color="blue"> <bean:message
							key="login.password" /> </font>
				</td>
				<td><html:password styleId="pass" property="userPass" size="40"
						maxlength="40" /> <font color="red"> <html:errors
							property="userPass" /> </font></td>
			</tr>
			<tr>
				<td><font color="blue"> <bean:message
							key="app.user.type" /> </font></td>
				<td><SELECT NAME="type">
						<option value=<%=SystemUser.SUPER_USER %>>
							<bean:message key="app.user.super" />
						</option>
				</SELECT></td>
			</tr>
			<tr>
				<td colspan="5" align="center"><html:submit
						onclick="set('add');">
						<bean:message key="app.add" />
					</html:submit>
				</td>
				<td colspan="5" align="center"><html:submit
						onclick="set('cancel');">
						<bean:message key="app.cancel" />
					</html:submit></td>
			</tr>

		</table>
	</html:form>
</body>
</html>