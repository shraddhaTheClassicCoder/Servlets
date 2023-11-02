<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Record</title>
</head>
<body bgcolor="lightblue">
	<c:choose>
		<c:when test="${status eq 'success'}">
			<h1 style='color: green; text-align: center;'>Record Deleted
				Successfully</h1>
		</c:when>

		<c:when test="${status eq 'fail'}">
			<h1 style='color: red; text-align: center;'>Record Deletion
				Failed</h1>
		</c:when>


		<c:otherwise>

			<h1 style='color: red; text-align: center;'>The given Record is
				not available for deletion</h1>

		</c:otherwise>
	</c:choose>
</body>
</html>