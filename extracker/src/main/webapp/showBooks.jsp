<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="<c:url value="/form.css" />" rel="stylesheet">
<style type="text/css">

.pic{
	height="40";
	width="40";
	
}

.picbig{
	
	position:absolute;
	width:0px;
	-webkit-transition:width:0.3s linear 0s;
	transition:width:0.3s linear 0s;
	 -moz-transition: background 0.3s linear; /* Firefox 4 */
    -webkit-transition: background 0.3s linear; /* Safari and Chrome */
    -o-transition: background 0.3s linear; /* Opera */
    -ms-transition: background 0.3s linear; /* Explorer 10 */
	z-index:10;
}

.pic:hover+ .picbig, .pic:active+ .picbig{
	left:600px;
	width:500px;
	height:400px;
}
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

#customers {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#customers td, #customers th {
	border: 1px solid #ddd;
	padding: 8px;
}

#customers tr:nth-child(even) {
	background-color: #f2f2f2;
}

#customers tr:hover {
	background-color: #ddd;
}

#customers th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #1E90FF;
	color: white;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>books</title>
</head>
<body>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	

		<TABLE id="customers" BORDER="1">
			<TR>
				<TH>Book ISDN</TH>
				<TH>Book Name</TH>
				<TH>Book Author</TH>
				<TH>Book Price</TH>
				<TH>No Of Copies</TH>
			</TR>

			<c:forEach items="${books}" var="book">


				<TR>
					<TD>${book.ISDNNo}</td>
					<TD>${book.name}</TD>
					<TD>${book.aurthor}</TD>
					<TD>${book.price}</TD>
					<TD>${book.noOfCopies}</TD>
				</TR>
			</c:forEach>
		</TABLE>

</body>
</html>