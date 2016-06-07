<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
    $( "#assignmentDate" ).datepicker({ dateFormat: 'yy-mm-dd' });
  });
  </script>
  
</head>
<body>
<form action="Gradebook" method="post">
Student ID:
<select name="studentId">
<option value="1001">1001</option>
<option value="1002">1002</option>
<option value="1003">1003</option>
<option value="1004">1004</option>
<option value="1005">1005</option>
</select>
<br/>
Assignment Name:<input type="text" name="assignmentName"><br/>
Assignment Type:
<select name="assignmentType">
<option value="homework">Homework</option>
<option value="quiz">Quiz</option>
<option value="test">Test</option>
<option value="project">Project</option>
</select>
<br/>
Assignment Date:<input type="text" id="assignmentDate" name="assignmentDate"><br/>
Assignment Grade:<input type="text" name="assignmentGrade" maxlength="3" size="3"><br/>
<input type="submit" name="submit" value="Submit">
</form>

<h1>Reports</h1>
<ul>
<li>All assignments by <select name="studentId">
<option value="1001">1001</option>
<option value="1002">1002</option>
<option value="1003">1003</option>
<option value="1004">1004</option>
<option value="1005">1005</option>
</select></li>
</ul>

<li>All <select name="assignmentType">
<option value="homework">Homework</option>
<option value="quiz">Quiz</option>
<option value="test">Test</option>
<option value="project">Project</option>
</select> by anyone</li>

<li>All <select name="assignmentType">
<option value="homework">Homework</option>
<option value="quiz">Quiz</option>
<option value="test">Test</option>
<option value="project">Project</option>
</select> by <select name="studentId">
<option value="1001">1001</option>
<option value="1002">1002</option>
<option value="1003">1003</option>
<option value="1004">1004</option>
<option value="1005">1005</option>
</select></li>

<li>Average for <select name="studentId">
<option value="1001">1001</option>
<option value="1002">1002</option>
<option value="1003">1003</option>
<option value="1004">1004</option>
<option value="1005">1005</option>
</select>
</li>

<li>Average for <select name="studentId">
<option value="1001">1001</option>
<option value="1002">1002</option>
<option value="1003">1003</option>
<option value="1004">1004</option>
<option value="1005">1005</option>
</select> by <select name="assignmentType">
<option value="homework">Homework</option>
<option value="quiz">Quiz</option>
<option value="test">Test</option>
<option value="project">Project</option>
</select>
</li>
<li>High/Low for <select name="assignmentType">
<option value="homework">Homework</option>
<option value="quiz">Quiz</option>
<option value="test">Test</option>
<option value="project">Project</option>
</select>
</li>
</body>
</html>