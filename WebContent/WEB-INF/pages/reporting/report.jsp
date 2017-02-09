<%@ include file="../general/StandardFrame.jsp" %>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.*"%>
<html>
<head>
<title>Report</title>
</head>
<body>
		
<html:form action="/reportingActions"  method="post" enctype="multipart/form-data" >
<table  border="0">
<tr>
			<td align="left"> <html:link page="/admin.do?action=home">
						<bean:message key="app.home" />
					</html:link><br><br></td>
</tr>
<tr>
	<td>
	<font color="blue"> <bean:message key="report.start.date" /> </font>
	</td>
	<td>
	<input type="hidden" name="countryId"
					value=<%=request.getAttribute(ReportingActions.COUNTRY_ID_PARAM)%>>
	<input type="hidden" name="poolId"
					value=<%=request.getAttribute(ReportingActions.POOL_ID_PARAM)%>>
	<input type="hidden" name="communityId"
					value=<%=request.getAttribute(ReportingActions.COMMUNITY_ID_PARAM)%>>
		<html:text styleId="effectiveStartDate" property="startDate" size="30" maxlength="1000"/>
	</td>
	<td>
	<font color="blue"> <bean:message key="report.end.date" /> </font>
	</td>
	<td>
		<html:text styleId="effectiveEndDate" property="endDate" size="30" maxlength="1000"/>
	</td>
	<td colspan="5" align="right"> <html:submit onclick= "return showReport();" property="action"><bean:message key="report.filter"/></html:submit></td>
</tr>
<tr>

</tr>

</table>
	<display:table id="report" name="Report"
		requestURI="/reportingActions.do?action=list" export="true">
		<display:column property="registeredUsers" titleKey="reporting.registeredUsers"/>
		<display:column property="iphoneUsers" titleKey="reporting.iphoneUsers"/>
		<display:column property="androidUsers" titleKey="reporting.androidUsers"/>
		<display:column property="needArideRequested" titleKey="reporting.needArideRequested"/>
		<display:column property="giveArideRequested" titleKey="reporting.giveArideRequested"/>
		<display:column property="tripsCount" titleKey="reporting.tripsCount"/>
		<display:column property="acceptedTrips" titleKey="reporting.acceptedTrips"/>
		<display:column property="rejectedTrips" titleKey="reporting.rejectedTrips"/>
		<display:setProperty name="export.pdf" value="true" />
		<display:setProperty name="export.excel.filename" value="KarTagReport.xls"/>
        <display:setProperty name="export.pdf.filename" value="KarTagReport.pdf"/>
	</display:table>
</html:form>
</body>
</html>