<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,  initial-scale=1.0">
    <title>Expense Manager Dashboard</title>
    <link rel="stylesheet"  type="text/css" href="${contextPath}/resources/css/dashboard.css">
    
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
</head>

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

    </c:if>
    
   <div class="container">
       <div class="navigation">
           <ul>
               <li>
                   <a href="#">
                       <span class="icon"><ion-icon name="logo-apple"></ion-icon></span>
                       <span class="title">Expense Manager</span>
                   </a>
               </li>
               <li>
                <a href="/dashboard">
                    <span class="icon"><ion-icon name="home-outline"></ion-icon></span>
                    <span class="title">Dashboard</span>
                </a>
            </li>
            <li>
                <a href="/allExpense">
                    <span class="icon"><ion-icon name="person-outline"></ion-icon></span>
                    <span class="title">All Expenses</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="icon"><ion-icon name="chatbubble-outline"></ion-icon></span>
                    <span class="title">Message</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="icon"><ion-icon name="help-outline"></ion-icon></span>
                    <span class="title">Help</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="icon"><ion-icon name="settings-outline"></ion-icon></span>
                    <span class="title">Settings</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="icon"><ion-icon name="lock-closed-outline"></ion-icon></span>
                    <span class="title">Password</span>
                </a>
            </li>
            <li>
                <a href="#" onclick="document.forms['logoutForm'].submit()">
                    <span class="icon"><ion-icon name="log-out-outline"></ion-icon></span>
                    <span class="title">Sign Out</span>
                </a>
            </li>
           </ul>
       </div>

       <!-- main -->
       <div class="main">
            <div class="topbar">
                <div class="toggle">
                    <ion-icon name="menu-outline"></ion-icon>
                </div>
                <div class="search">
                    <label>
                        <input type="text" placeholder="search here">
                        <ion-icon name="search-outline"></ion-icon>
                    </label>
                </div>
            
                <!-- user Tag-->
                <div class="user">
                    <img src="${contextPath}/resources/img/bhoomika.jpg" width="50" height="60">
                </div>
            </div>
 
            <!-- Specific  Section -->   
            <div class="details"> 
                        <table><tr>
                        
                        <td  align="left"><div><img src="/pieChart" title = "PieChart"  height="315" width="100%"/></div></td>
                        <td align="right"><div ><img src="/lineChart" title = "LineChart"  height="315" width="100%"/></div></td>
                        </tr></table>
						
						
                        <table>
                        <tr> 
                            <td><h4 align="left" class="sansserif">
							From
							<fmt:formatDate value="${from}" pattern="dd-MMM-yy" />
							till
							<fmt:formatDate value="${to}" pattern="dd-MMM-yy" />
						</h4></td>
						
						<td align="right">
						<form action="/sendEmail" method="post">
						       Email ID : <input type="text" name="email" /><input type="submit" value=" Send Email" />
						 </form>
						</td>
						<td align="right"><a  href="/downloadExcel">Download Excel Document </a> <br> </td>

                        
                         </tr>
                        </table>
                        
						<form action="/deleteExpenseFromCategory" method="POST">
							<TABLE id="customers" BORDER="1">
								<TR>
									<TH>Expense Name</TH>
									<TH>Comments</TH>
									<TH>Date</TH>
									<TH>Amount</TH>
									<TH>Category</TH>
									<TH>Receipt</TH>
									<TH>Delete</TH>
									<th>Edit</th>
								</TR>
					
								<c:forEach items="${expense}" var="element">
					
					
									<TR>
										<TD>${element.expenseName}</td>
										<TD>${element.comments}</TD>
										<TD><fmt:formatDate value="${element.createdDate}" pattern="dd-MMM-yy" /></TD>
										<TD>${element.amount}</TD>
										<TD>${element.userCategory.category}</TD>
										
										<td><a><img class="pic" src="/imageDisplay?id=${element.expenseId}" width="40" height="40" />
											<img class="picbig" src="/imageDisplay?id=${element.expenseId}" />
											</a></td>
									
					
										<td align="center"><input type="checkbox" name="checkboxgroup"
											value="${element.expenseId}" /></td>
											
									     <td> <a href = "<c:url value="/edit/${element.expenseId}" />" >EDIT</a></td>
									</TR>
								</c:forEach>
							</TABLE>
							<table>
							<tr> 
							
							     <td><h3 class="sansserif">Total Expenditure :$ ${total}</h3></td>
							     <td align="right"><input type="submit" value="Delete Expenses" /></td>
					        </tr>
							</table>
							
							
						</form>
           </div>

           <!-- Specific Section End -->  
    
        </div>
    </div>

   <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
   <script nomodule src="https://unpkg.com/ioniconns@5.5.2/dist/ionicons/ionicons.js"></script>

    <script>

        //Menu Toggle
        let toggle = document.querySelector('.toggle');
        let navigation = document.querySelector('.navigation');
        let main = document.querySelector('.main');

        toggle.onclick = function()
        {
            navigation.classList.toggle('active');
            main.classList.toggle('active');
        }

        // add hovered class in selected list item
       let list = document.querySelectorAll('.navigation li');
       function activelink(){
           list.forEach((item) =>
           item.classList.remove('hovered'));
           this.classList.add('hovered');
       }
       list.forEach((item) =>
       item.addEventListener('mouseover',activelink));

   </script>
</html>
