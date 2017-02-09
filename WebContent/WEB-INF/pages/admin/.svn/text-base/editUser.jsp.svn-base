<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<html>
<head>
<title><bean:message key="app.edit"/></title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<body>
<script>
function validate()
{
    set('update');
	return true;
}
function set(target) 
{
	document.forms[0].action.value=target;
}

</script>
<html:form action="/userActions"  method="post" enctype="multipart/form-data" >
<html:hidden property="id"></html:hidden>
<table  border="0">
<tr>
	<td>
	<font color="blue"> <bean:message key="app.user.id"/> </font>
	</td>
	<td>
		<html:textarea styleId="titleText" property="id" readonly="true" />
	</td>
</tr>
<tr>
	<td>
	<font color="blue"> <bean:message key="app.user.pass"/> </font>
	</td>
	<td>
		<html:textarea styleId="detailsText" property="password" readonly="true" />
	</td>
</tr>
<tr>
	<td>
	<font color="blue"> <bean:message key="app.user.name"/> </font>
	</td>
	<td>
		<html:textarea styleId="detailsText" property="name" readonly="true" />
	</td>
</tr>
<tr>
	<td>
	<font color="blue"> <bean:message key="app.user.email"/> </font>
	</td>
	<td>
		<html:textarea styleId="detailsText" property="email" readonly="true" />
	</td>
</tr>
<tr>
	<td>
	<font color="blue"> <bean:message key="app.user.pin"/> </font>
	</td>
	<td>
		<html:textarea styleId="detailsText" property="pin" readonly="true" />
	</td>
</tr>
<tr>
	<td>
	<font color="blue"> <bean:message key="app.user.age"/> </font>
	</td>
	<td>
		<html:textarea styleId="detailsText" property="age" readonly="true" />
	</td>
</tr>
<tr>
	<td>
	<font color="blue"> <bean:message key="app.user.sex"/> </font>
	</td>
	<td>
		<html:textarea styleId="detailsText" property="sex" readonly="true" />
	</td>
</tr>

<tr>
	<td>
		<html:checkbox property="activated"><bean:message key="app.activated"/></html:checkbox>
	</td>
</tr>
<tr>
<td colspan="5" align="center"> <html:submit onclick= "return validate();" property="action"><bean:message key="app.update"/></html:submit>
</td>
<td colspan="5" align="center"><html:submit onclick= "set('cancel');" property="action"><bean:message key="app.cancel"/></html:submit></td>
</tr>

</table>
</html:form>
</body>
</html>