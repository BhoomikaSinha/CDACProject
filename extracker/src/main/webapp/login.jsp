<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="form" uri="/WEB-INF/spring-form.tld" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script
      src="https://kit.fontawesome.com/64d58efce2.js"
      crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="${contextPath}/resources/css/loginstyle.css" />
    <title>Sign in & Sign up Form</title>
  </head>
  <body>
    <div class="container">
      <div class="forms-container">
        <div class="signin-signup">
        
          <form method="POST" action="${contextPath}/login" class="sign-in-form">
            <h2 class="title">Sign in</h2>
  
            <span style="color:red">${error}</span>
            <div class="input-field">
              <i class="fas fa-user"></i>
              <input name="username" type="text" placeholder="Username" />
            </div>
            
            <div class="input-field">
              <i class="fas fa-lock"></i>
              <input name="password" type="password" placeholder="Password" />
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Login" class="btn solid" />
            <p class="social-text">Or Sign in with social platforms</p>
            <div class="social-media">
              <a href="#" class="social-icon">
                <i class="fab fa-facebook-f"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-twitter"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-google"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-linkedin-in"></i>
              </a>
            </div>
          </form>
          
          
          <form name="regForm" action="${contextPath}/registration" method="POST" onsubmit="return validateForm()" class="sign-up-form">
            <h2 class="title">Sign up</h2>
            <div class="input-field">
              <i class="fas fa-user"></i>
              <input name="username" type="text" placeholder="Username" minlength="7" maxlength="32" title="Must contain minimum lenght 7 " required/>
            </div>
            <div class="input-field">
              <i class="fas fa-envelope"></i>
              <input name="password" type="password" placeholder="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" maxlength="32" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required/>
            </div>
            <div class="input-field">
              <i class="fas fa-lock"></i>
              <input name="passwordConfirm" type="password" placeholder="Confirm Password" required/>
            </div>
            <input type="submit" class="btn" value="Sign up" />
            <p class="social-text">Or Sign up with social platforms</p>
            <div class="social-media">
              <a href="#" class="social-icon">
                <i class="fab fa-facebook-f"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-twitter"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-google"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-linkedin-in"></i>
              </a>
            </div>
          </form>
        </div>
      </div>

      <div class="panels-container">
        <div class="panel left-panel">
          <div class="content">
            <h3>New here ?</h3>
            <p>
              Let's Go Sign Up !!!
            </p>
            <button class="btn transparent" id="sign-up-btn">
              Sign up
            </button>
          </div>
          <img src="${contextPath}/resources/img/log.svg" class="image" alt="" />
        </div>
        <div class="panel right-panel">
          <div class="content">
            <h3>One of us ?</h3>
            <p>
             You can Login In !!!
            </p>
            <button class="btn transparent" id="sign-in-btn">
              Sign in
            </button>
          </div>
          <img src="${contextPath}/resources/img/register.svg" class="image" alt="" />
        </div>
      </div>
    </div>

    <script src="${contextPath}/resources/js/loginpage.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
  </body>
</html>

