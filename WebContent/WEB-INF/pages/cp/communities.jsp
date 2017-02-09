<%@ include file="../general/StandardFrame.jsp"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pools</title>
</head>
<body>
	<table>
		<tr>
			<td align="left"><br> <html:link
					page="/admin.do?action=home">
					<bean:message key="app.home" />
				</html:link><br> <br></td>
			<td align="left"><br> <a
				href="communityActions.do?action=populate&<%=PoolsActions.COUNTRY_ID_PARAM%>=<%=request.getAttribute(PoolsActions.COUNTRY_ID_PARAM)%>">
					<bean:message key="app.add" /> </a> <br> <br></td>
		</tr>
	</table>
	<display:table id="community" name="Community.communities"
		requestURI="/communityActions.do?action=list" pagesize="10">

		<display:column property="communityName" titleKey="app.name" sortable="true" />
		<display:column>
			<a
				href="communityActions.do?action=edit&id=<%=""
							+ ((CommunityForm) pageContext.getAttribute("community"))
									.getId()%>">
				<bean:message key="app.edit" /> </a>
		</display:column>
		<display:column>
			<a
				href="communityActions.do?action=delete&<%=PoolsActions.COUNTRY_ID_PARAM%>=<%=request
							.getAttribute(PoolsActions.COUNTRY_ID_PARAM)%>&id=<%=""
							+ ((CommunityForm) pageContext.getAttribute("community"))
									.getId()%>"
				onClick="return confirmDelete();"> <bean:message
					key="app.delete" /> </a>
		</display:column>
		<display:column>
			<a
				href="reportingActions.do?action=filter&countryId=<%=((CommunityForm) pageContext
							.getAttribute("community")).getCountryId()%>&communityId=<%=((CommunityForm) pageContext
							.getAttribute("community")).getId()%>">
				<bean:message key="pool.reports" /> </a>
		</display:column>

	</display:table>
	<table>
		<tr>
			<td align="left"><br> <html:link
					page="/admin.do?action=home">
					<bean:message key="app.home" />
				</html:link><br> <br></td>
			<td align="left"><br> <a
				href="communityActions.do?action=populate&<%=PoolsActions.COUNTRY_ID_PARAM%>=<%=request.getAttribute(PoolsActions.COUNTRY_ID_PARAM)%>">
					<bean:message key="app.add" /> </a> <br> <br></td>
		</tr>
	</table>
</body>
</html>