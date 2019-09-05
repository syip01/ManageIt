<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- Title Page-->
	<title>ManageIt! Please Login</title>
	
	<!--  Add CSS -->
	<%@include file="/resources/include/addCss.jsp"%>    
	
</head>
<body>
	
	<body class="animsition">
    <div class="page-wrapper">
        <div class="page-content--bge5">
            <div class="container">
                <div class="login-wrap">
                    <div class="login-content">
                        <div class="login-logo">
                            <a href="#">
                                <img src="resources/images/icon/logo-image.png" alt="ManageIt!">
                            </a>
                        </div>
                        <div>
                        	<p>
                        		${messageResult}
                        	</p>
                        </div>
                        <div class="login-form">
                            <form action="${pageContext.request.contextPath}/login" method="post">
                                <div class="form-group">
                                    <label for="username">Username</label>
                                    <input class="au-input au-input--full" type="text" name="username" id="username" placeholder="Enter Username">
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input class="au-input au-input--full" type="password" name="password" id="password" placeholder="Enter Password">
                                </div>
                                <button class="au-btn au-btn--block au-btn--green m-b-20" type="submit">Sign In</button>
                            </form>
                            <div class="register-link">
                                <p>
                                    No account?
                                    <a href="${pageContext.request.contextPath}/register">Sign Up</a>
                                </p>
								<p>
                                    Register new admin: 
                                    <a href="${pageContext.request.contextPath}/registerAdmin">TOTALLY IMPOSSIBLE!</a>
                                </p>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

	<!--  Add JS -->
	<%@include file="/resources/include/addJs.jsp"%>

</body>

</html>
<!-- end document-->
