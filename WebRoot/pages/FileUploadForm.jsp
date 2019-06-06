<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
	<title>File Upload with Spring 3 MVC</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
	</head>
<body>
	<h1>File Upload Form</h1>
	<br/>
	<form:form id="form" commandName="form" enctype="multipart/form-data" method="POST" action="fileUpload">
		<table>
			<tr>
				<td colspan="2" style="color: red;"><form:errors path="*" cssStyle="color : red;"/>
					${errors}
				</td>
			</tr>
			<tr>
				<td>Name : </td>
				<td>
					<input type="file" name="file" />
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Upload File" /></td>
			</tr>
		</table>
		</form:form>
</body>
</html> 
