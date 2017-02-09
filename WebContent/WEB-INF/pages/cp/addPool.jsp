<%@ include file="../general/StandardFrame.jsp" %>
<html>
<head>
<title><bean:message key="app.add"/></title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<body>
<html:form action="/poolActions"  method="post" enctype="multipart/form-data" >
<table  border="0">

<tr>
	<td>
	<font color="blue"> <bean:message key="pool.name" /> </font>
	</td>
	<td>
		<html:text styleId="name" property="poolName" size="50" maxlength="1000"/>
		<input type="hidden" name="countryId" value=<%= request.getAttribute(PoolsActions.COUNTRY_ID_PARAM) %>>
	</td>
</tr>
<tr>
<td colspan="5" align="center"> <html:submit onclick= "return addPool();" property="action"><bean:message key="app.add"/></html:submit>
</td>
<td colspan="5" align="center"><html:submit property="action"><bean:message key="app.cancel"/></html:submit></td>
</tr>

</table>
</html:form>
</body>
</html>