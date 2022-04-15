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
                <a href="/trackEmployee">
                    <span class="icon"><ion-icon name="chatbubble-outline"></ion-icon></span>
                    <span class="title">Employee Management</span>
                </a>
            </li>
            <li>
                <a href="/newTracker">
                    <span class="icon"><ion-icon name="help-outline"></ion-icon></span>
                    <span class="title">Track Expense</span>
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

            <!-- cards --> 
            <div class="cardBox">
                <div class="card">
                    <div>
                        <div class="numbers">1,504</div>
                        <div class="cardName">Daily Views</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="eye-outline"></ion-icon>
                    </div>
                </div>
                <div class="card">
                    <div>
                        <div class="numbers">80</div>
                        <div class="cardName">Sales</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="cart-outline"></ion-icon>
                    </div>
                </div>
                <div class="card">
                    <div>
                        <div class="numbers">284</div>
                        <div class="cardName">Comments</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="chatbubbles-outline"></ion-icon>
                    </div>
                </div>
                <div class="card">
                    <div>
                        <div class="numbers">$7,842</div>
                        <div class="cardName">Earning</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="card-outline"></ion-icon>
                    </div>
                </div>
            </div>  
            
            <!--order details list-->
            <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Recent Expenses</h2>
                        <a href="/addExpense" class="btn">New Expense</a>
                    </div>
                    <table>
                        <thead>
                            <tr>
                                <td>Expense Name</td>
                                <td>Date</td>
                                <td>Amount</td>
                                <td>Category</td>
                                <td>Description</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${expenses}" var="expense">
                                <tr>
                                <td>${expense.expenseName}</td> 
                                <td><fmt:formatDate value="${expense.createdDate}" pattern="dd-MMM-yy" /></td>
                                <td>${expense.amount}</td>
                                <td>${expense.userCategory.category}</td>
                                <td>${expense.comments}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            
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
