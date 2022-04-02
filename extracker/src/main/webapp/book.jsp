<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>
<%ResultSet resultset =null;%>
<html>
<head>
<style>

h1.sansserif {
    font-family: Arial, Helvetica, sans-serif;
    }
h2.sansserif {
    font-family: Arial, Helvetica, sans-serif;
    }
h3.sansserif {
    font-family: Arial, Helvetica, sans-serif;
    }
h4.sansserif {
    font-family: Arial, Helvetica, sans-serif;
}
</style>
<link href="<c:url value="/form.css" />" rel="stylesheet">
<link href="bootstrap.css" type="text/css" rel="stylesheet">
<h1 class="sansserif"><center>SVS Technologies</center></h1>
<h2 class="sansserif"><center>Book tracker</center></h2>
</head>
<body>

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

 
<h3 class="sansserif">Add New Book</h3>
<form action="/addBook" method="post">
<table>
		<tr><td>Book Name :   		</td>  <td>       <input type="text" name="name"/>    </td> </tr>
		<tr><td>Book Author :  				</td>  <td>       <input type="text" name="aurthor" /> 		 </td> </tr>
		<tr><td>Book Price : 					</td>  <td>       <input type="number" name="price" />   </td> </tr>
		<tr><td>No of Copies :    	    	</td>  <td>       <input type="number" name="noOfCopies" />      </td> </tr>
		<tr><td><input type="submit" value="Submit" /></td></tr>
	</table>
</form>
	

</body>
</html>
