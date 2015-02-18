<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Admission Form</title>
</head>
<body>
<h1>${msg}</h1>

<form action="/SpringSecondProject/submitAdmissionForm.html" method="post">
	<p>First name: <br>
	<input type="text" name="FirstName">
	</p>
	<p>
	Last name:  <br>
<input type="text" name="LastName" >
</p>
<p>
Age:   <br>
<input type="text" name="Age">
</p>
<p> Mobile:<br>
<input type="text" name="Mobile">
</p>
<p>
  Birthday:<br/>
  <input type="date" name="Birthday">
  </p>
 <p>
  Student Skills :
  <br> <input type="checkbox" name="StudentSkills" value="Java">Java<br>
  <input type="checkbox" name="StudentSkills" value="C">C<br>
  <input type="checkbox" name="StudentSkills" value="Python">Python<br>
  </p>
<br><br>

<input type="submit" value="Submit">
</form>
</body>
</html>