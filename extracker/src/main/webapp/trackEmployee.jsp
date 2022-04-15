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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
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
                <a href="${viewPage}">
                    <span class="icon"><ion-icon name="chatbubble-outline"></ion-icon></span>
                    <span class="title">Track Employee Expense </span>
                </a>
            </li>
            <li>
                <a href="${group}">
                    <span class="icon"><ion-icon name="help-outline"></ion-icon></span>
                    <span class="title">Add Expense To Group Of Employees</span>
                </a>
            </li>
            <li>
                <a href="${address}">
                    <span class="icon"><ion-icon name="settings-outline"></ion-icon></span>
                    <span class="title">Add new Employee</span>
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
	 
	                 <div class="cardHeader">
                        <h2>Add Employee Expense</h2>
                    </div>
                    
                     <div class="addExpenseForm">
                     <form action="/employeeExpenseSubmission" method="post">
							<table>
									<tr><td>Expense Name : </td> <td><input type="text" name="expenseName"/></td> </tr>
									<tr><td>Amount : </td> <td> <input type="text" name="amount" /></td> </tr>
									<tr><td>Date : </td>  <td> <input type="date" name="createdDate" />   </td> </tr>
									<tr><td>Comment : </td>  <td><input type="text" name="comments" />      </td> </tr>
									
									<tr><td>Employee : </td> <td><select name="employee">
							 											 <c:forEach items="${employees}" var="databaseValue">
							  												  <option value="${databaseValue}">
							     													   ${databaseValue}
							    											  </option>
							 											 </c:forEach>
																	</select>
									  </td></tr>
									
									<tr><td>Category : </td> <td><select name="category">
							 											 <c:forEach items="${categories}" var="databaseValue">
							  												  <option value="${databaseValue}">
							     													   ${databaseValue}
							    											  </option>
							 											 </c:forEach>
																	</select>
																	</td>
																	
							</tr>
									
									
								</table>
								<input type="submit" value="Submit" class="form-submit-button"/>
	                  </form>
                     
                     </div>
                    
                    
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