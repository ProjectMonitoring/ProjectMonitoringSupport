<%@page import="models.tutor"%>
<%@page import="models.student"%>
<%@page import="java.util.ArrayList"%>

<html>
<head>
	<meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
	<meta charset="UTF-8">
	<title>Contacts</title>
	<link rel="stylesheet" href="css/site.css">
</head>
<body>
	<h1>all students</h1>
	<%
		ArrayList<student> allStudents = (ArrayList<student>) request.getAttribute("allStudents");
		for(student s : allStudents) {
			out.write(""+s.getUsername() + " " + s.getPassword() + "<br>");
		}
	%> 

	<br>
	<a class="btn" href="./addnew">+ New Student</a>

</body>
</html>