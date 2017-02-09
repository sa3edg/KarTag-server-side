<%@ include file="../general/StandardFrame.jsp"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>feedbacks</title>
</head>
<body>
	<table>
		<tr>
			<td align="left"><br> <html:link
					page="/admin.do?action=home">
					<bean:message key="app.home" />
				</html:link><br> <br></td>
		</tr>
	</table>
	<display:table id="feedback" name="Feedback.feedbacks"
		requestURI="/feedbackActions.do?action=list" pagesize="10">

		<display:column property="type" titleKey="feedback.type" sortable="true" />
		<display:column property="text" titleKey="feedback.text" sortable="true" />
		<display:column>
			<a
				href="feedbackActions.do?action=delete&<%=""
							+ ((FeedbackForm) pageContext.getAttribute("feedback"))
									.getId()%>"
				onClick="return confirmDelete();"> <bean:message
					key="app.delete" /> </a>
		</display:column>
	</display:table>
	<table>
		<tr>
			<td align="left"><br> <html:link
					page="/admin.do?action=home">
					<bean:message key="app.home" />
				</html:link><br> <br></td>
		</tr>
	</table>
</body>
</html>