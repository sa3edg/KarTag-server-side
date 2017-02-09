<%@ include file="../general/StandardFrame.jsp" %>
<html>
<head>
<title><bean:message key="app.add" /></title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<body>
	<html:form action="/countryActions" method="post"
		enctype="multipart/form-data">
		<table border="0">
			<tr>
				<td><font color="blue"> Country Name </font>
				</td>
				<td><html:text styleId="name" property="name" size="50"
						maxlength="100" />
				</td>
			</tr>
			<tr>
				<td colspan="5" align="center"><html:submit
						onclick="return addCountry();" property="action">
						<bean:message key="app.add" />
					</html:submit></td>
				<td colspan="5" align="center"><html:submit
						property="action">
						<bean:message key="app.cancel" />
					</html:submit>
				</td>

			</tr>

		</table>
	</html:form>
</body>
</html>