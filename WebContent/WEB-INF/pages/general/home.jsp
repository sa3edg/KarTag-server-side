<%@page import="com.kartag.server.model.SystemUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<CENTER>
		<table border="2">
			<CAPTION>
				<EM> <bean:message key="home.appname" /> </EM>
			</CAPTION>

			<tr>
				<td>
					<%
						if (request.getSession().getAttribute("USER_ROLE")
								.equals(SystemUser.ADMIN)
								|| request.getSession().getAttribute("USER_ROLE")
										.equals(SystemUser.SUPER_USER)) {
							;
					%> <html:link page="/countryActions.do?action=list">
						Countries
					</html:link> <%
 	} else if (request.getSession().getAttribute("USER_ROLE")
 			.equals(SystemUser.MALL_USER)) {
 		;
 %> <html:link page="/mallActions.do?action=list">
						<bean:message key="malls.title" />
					</html:link> <%
 	} else if (request.getSession().getAttribute("USER_ROLE")
 			.equals(SystemUser.STORE_USER)) {
 		;
 %> <html:link page="/storeActions.do?action=list">
						<bean:message key="store.title" />
					</html:link> <%
 	}
 	;
 %>
				</td>
			</tr>
			<tr>
				<td>
					<%
						if (request.getSession().getAttribute("USER_ROLE")
								.equals(SystemUser.ADMIN)) {
							;
					%> <html:link page="/notificationActions.do?action=list">
						<bean:message key="notification.title" />
					</html:link> <%
 	}
 	;
 %>
				</td>
			</tr>
			<tr>
				<td>
					<%
						if (request.getSession().getAttribute("USER_ROLE")
								.equals(SystemUser.ADMIN)) {
							;
					%> <html:link page="/feedbackActions.do?action=list">
						<bean:message key="feedback.title" />
					</html:link> <%
 	}
 	;
 %>
				</td>
			</tr>
			<tr>
				<td>
					<%
						if (request.getSession().getAttribute("USER_ROLE")
								.equals(SystemUser.ADMIN)) {
							;
					%> <html:link page="/admin.do?action=list">
						<bean:message key="app.adminarea" />
					</html:link> <%
 	}
 	;
 %>
				</td>
			</tr>
			<tr>
				<td><html:link page="/admin.do?action=logout">
						<bean:message key="home.logout" />
					</html:link></td>
			</tr>

		</table>
	</CENTER>
</body>
</html>