<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<html>
<head>
<title>Log in</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<body>
	<CENTER>
		<table>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<img src="images/logo.png">
					</div>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</CENTER>
	<CENTER>
		<html:form action="/login" method="post"
			enctype="text/plain;charset=UTF-8">
			<html:hidden property="action" value="login"></html:hidden>
            
			<table border="0">
				<tr>
					<td align="left"><font color="blue"> <bean:message
								key="login.username" /> </font>
					</td>
					<td align="left"><html:text property="userName" size="20"
							maxlength="20" />
				    <font color="red">
		               <html:errors property="userName"/>
		            </font>
					</td>
				</tr>
				<tr>
					<td align="left"><font color="blue"> <bean:message
							key="login.password" /> </font></td>
					<td align="left"><html:password property="userPass" size="20"
							maxlength="20" />
				    <font color="red">
		               <html:errors property="userPass"/>
		            </font>
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
					<html:submit >
							<bean:message key="login.login" />
					</html:submit>
					</td>
				</tr>
			</table>
		</html:form>
	</CENTER>
</body>
</html>