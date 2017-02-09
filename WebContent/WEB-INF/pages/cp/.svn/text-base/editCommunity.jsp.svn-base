<%@ include file="../general/StandardFrame.jsp" %>
<html>
<head>
<title><bean:message key="app.add"/></title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
<html:form action="/communityActions"  method="post" enctype="multipart/form-data" >
<table  border="0">
<html:hidden property="id"></html:hidden>
<tr>
	<td>
	<font color="blue"> <bean:message key="community.name" /> </font>
	</td>
	<td>
		<html:text styleId="name" property="communityName" size="50" maxlength="1000"/>
		<html:hidden property="countryId"></html:hidden>
	</td>
</tr>
<tr>
	<td>
		<font color="blue"> <bean:message key="community.startDate" /> </font>
	</td>
	<td>
		<html:text styleId="effectiveStartDate" property="startDate" size="50" maxlength="100" />
	</td>
</tr>
<tr>
	<td>
		<font color="blue"> <bean:message key="community.endDate" /></font>
    </td>
	<td>
		<html:text styleId="effectiveEndDate" property="endDate" size="50" maxlength="100" />
	</td>
</tr>
<tr>
	<td>
		<font color="blue"> <bean:message key="community.dnsName" /> </font>
	</td>
	<td>
		<html:text styleId="dnsServer" property="dnsServer" size="50" maxlength="1000"/>
	</td>
</tr>
<tr>
<td colspan="5" align="center"> <html:submit onclick= "return addCommunity();" property="action"><bean:message key="app.edit"/></html:submit>
</td>
<td colspan="5" align="center"><html:submit  property="action"><bean:message key="app.cancel"/></html:submit></td>
</tr>

</table>
</html:form>
</body>
</html>