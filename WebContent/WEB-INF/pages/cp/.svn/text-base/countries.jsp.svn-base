<%@ include file="../general/StandardFrame.jsp" %>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Countries</title>
</head>
<body>
	<table>
		<tr>
			<td align="left"> <html:link page="/admin.do?action=home">
						<bean:message key="app.home" />
					</html:link></td>
			<td>
				<%
					if (request.getSession().getAttribute("USER_ROLE")
							.equals(SystemUser.ADMIN)) {
						;
				%><html:link page="/countryActions.do?action=populate">
					Add Country
				</html:link> <%
 	}
 	;
 %>
			</td>

		</tr>
	</table>
	<display:table id="country" name="Country.countries"
		requestURI="/countryActions.do?action=list" pagesize="10">
		<display:column property="id" titleKey="app.id" sortable="true" />
		<display:column property="name" titleKey="app.name" sortable="true" />
		<%if (request.getSession().getAttribute("USER_ROLE")
							.equals(SystemUser.ADMIN)) {;
		%>
		<display:column>
			<a
				href="countryActions.do?action=edit&id=<%=((CountryForm) pageContext
							.getAttribute("country")).getId()%>">
				<bean:message key="app.edit" /> </a>
		</display:column>
		<display:column>
			<a
				href="countryActions.do?action=delete&id=<%=((CountryForm) pageContext
							.getAttribute("country")).getId()%>"
				onClick="return confirmDelete();"> <bean:message
					key="app.delete" /> </a>
		</display:column>
		<%};%>
		<display:column>
			<a
				href="poolActions.do?action=list&<%=PoolsActions.COUNTRY_ID_PARAM%>=<%=""
							+ ((CountryForm) pageContext.getAttribute("country"))
									.getId()%>">
				 <bean:message
					key="country.pools" /> </a>
		</display:column>
		<display:column>
			<a
				href="communityActions.do?action=list&<%=PoolsActions.COUNTRY_ID_PARAM%>=<%=""
							+ ((CountryForm) pageContext.getAttribute("country"))
									.getId()%>">
				 <bean:message
					key="country.communities" /> </a>
		</display:column>
		<display:column>
			<a
				href="reportingActions.do?action=filter&countryId=<%=((CountryForm) pageContext
							.getAttribute("country")).getId()%>">
				<bean:message key="country.reports" /> </a>
		</display:column>

	</display:table>
	<table>
		<tr>
			<td align="left"> <html:link page="/admin.do?action=home">
						<bean:message key="app.home" />
					</html:link></td>
			<td>
				<%
					if (request.getSession().getAttribute("USER_ROLE")
							.equals(SystemUser.ADMIN)) {
						;
				%><html:link page="/countryActions.do?action=populate">
					Add Country
				</html:link> <%
 	}
 	;
 %>
			</td>
		</tr>
	</table>
</body>
</html>