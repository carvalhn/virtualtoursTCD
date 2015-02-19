<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Second Spring Application</title>
</head>
<body>
	<h1>${msg}</h1>
	<br />
	<table>
		<tr>
			<td>First Name</td>
			<td>${user1.getFirstName()}</td>

		</tr>
		<tr>
			<td>Last Name</td>
			<td>${user1.getLastName()}</td>
		</tr>
		<tr>
			<td>Age</td>
			<td>${user1.getAge()}</td>
		</tr>
		<tr>
			<td>Mobile</td>
			<td>${user1.getMobile()}</td>
		</tr>
		<tr>
			<td>Student Skills</td>
			<td>${user1.getStudentSkills()}</td>
		</tr>

	</table>

</body>
</html>