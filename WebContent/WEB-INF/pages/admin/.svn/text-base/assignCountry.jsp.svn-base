<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<html>
<head>
<script>
	function validate() {
		var name = document.getElementById('text').value;
		if (name.length == 0) {
			alert("data error");
			return false;
		} else
			submitForm(document.forms[0], 'add')
		return true;

	}
	function set(target) {
		document.forms[0].action.value = target;
	}
	function submitForm(form, actionValue) {
		form.action = actionValue;
		form.submit();
	}
</script>
<title><bean:message key="app.add" /></title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<body>
	<html:form action="/countryActions" method="post"
		enctype="multipart/form-data">
		
		<%String userId = (String)request.getAttribute("userId") ;%>
		<html:hidden property="userId" value="<%=userId%>"></html:hidden>
		<table border="0">
		<tr>

                    <td><font color="blue">  select country </font>
                    </td>
                     <td>
                        <html:select property="id" >
                            <html:options collection="countries" property="id" labelProperty="name" />
                        </html:select>
                    </td>
                </tr>
			<tr>
				<td colspan="5" align="center"><html:submit
						property="action">
						assign
					</html:submit></td>
				<td colspan="5" align="center"><html:submit
						property="action">
						back
					</html:submit>
				</td>

			</tr>

		</table>
	</html:form>
</body>
</html>