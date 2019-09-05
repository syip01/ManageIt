<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<!-- Title Page-->
	<title>ManageIt! Register</title>
	
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
                            <form:form action="${pageContext.request.contextPath}/confirmRegistration" method="post" modelAttribute="userKey">
							<!--  Add registration form -->
							<%@include file="/resources/include/registerForm.jsp"%> 
                            
                            </form:form>
                            <div class="register-link">
                                <p>
                                    Already have account?
                                    <a href="${pageContext.request.contextPath}/index">Sign In</a>
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
