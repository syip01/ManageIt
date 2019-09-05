<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    	
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>ManageIt! Update Profile</title>
	
	<!--  Add CSS -->
	<%@include file="/resources/include/addCss.jsp"%>    
	
</head>
<body>

<body class="animsition">
    <div class="page-wrapper">
        <!-- MENU SIDEBAR-->
        <aside class="menu-sidebar2">
            <div class="logo">
                <a href="#">
                    <img src="${pageContext.request.contextPath}/resources/images/icon/logo-short.png" alt="ManageIT!" />
                </a>
            </div>
            <div class="menu-sidebar2__content js-scrollbar1">
        	    <!--  Add Navbar -->
            	<%@include file="/resources/include/navbar.jsp"%>
            </div>
        </aside>
        <!-- END MENU SIDEBAR-->


        <!-- PAGE CONTAINER-->
        <div class="page-container2">
            <!-- HEADER DESKTOP-->
            <header class="header-desktop2">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="header-wrap2">
                            <div class="logo d-block d-lg-none">
                                <a href="#">
                                    <img src="${pageContext.request.contextPath}/resources/images/icon/logo-short.png" alt="ManageIT!" />
                                </a>
                            </div>
                            <div class="header-button2">
                                <div class="header-button-item mr-0 js-sidebar-btn">
                                    <i class="zmdi zmdi-menu"></i>
                                </div>
                                <div class="setting-menu js-right-sidebar d-none d-lg-block">
                                    <div class="account-dropdown__body">
                                        <div class="account-dropdown__item">
                                            <a href="${pageContext.request.contextPath}/updateProfile">
                                                <i class="zmdi zmdi-settings"></i>Profile Settings</a>
                                        </div>
                                        <div class="account-dropdown__item">
                                            <a href="${pageContext.request.contextPath}/logout">
                                                <i class="zmdi zmdi-account"></i>Logout</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <aside class="menu-sidebar2 js-right-sidebar d-block d-lg-none">
                <div class="logo">
                    <a href="#">
                        <img src="${pageContext.request.contextPath}/resources/images/icon/logo-short.png" alt="ManageIt!" />
                    </a>
                </div>
                <div class="menu-sidebar2__content js-scrollbar2">
					<!--  Add Navbar -->
    	        	<%@include file="/resources/include/navbar.jsp"%>
                </div>
            </aside>
            <!-- END HEADER DESKTOP-->

            <!-- MAIN CONTENT-->
            <div class="main-content">

	            <section>
    	            <div class="section__content section__content--p30">
        	            <div class="container-fluid">
            	            <div class="row">
                	            <div class="col-lg-6">
                    	            <div class="card">
                        	            <div class="card-header">
                            	            <strong>Update Profile</strong>
											<small> Form</small>
											<h6>${messageResult}</h6>
                                    	</div>
                                    	<div class="card-body card-block">
                                    		<form:form action="${pageContext.request.contextPath}/confirmUpdateProfile" method="post" modelAttribute="userKey" class="form-horizontal">
	                                        	<div class="form-group">
                                        			<label for="username" class="form-control-label">Username:</label><br>
                                        			<form:input type="text" path="username" id="username" class="form-control"
														placeholder="Enter Desired Username" value="${userLogin.username}" readonly="true"/>
	                                 	      	</div>
                                        		<div class="form-group">
													<label for="oldpassword" class="form-control-label">Old Password:</label><br>
													<input type="password" name="oldpassword" id="oldpassword" class=" form-control"
														placeholder="Enter Old Password" value=""/>
    	                                    	</div>
	                                        	<div class="form-group">
    	                                    		<label for="password" class="form-control-label">New Password:</label><br>
													<form:input type="password" path="password" id="password" class=" form-control"
														placeholder="Enter Desired Password" value=""/>
													<br><form:errors path="password"/><br>
	                	                        </div>
	                                        	<div class="form-group">
		                                        	<label for="password2" class="form-control-label">Repeat Password:</label><br>
													<input type="password" name="password2" id="password2" class=" form-control"
														placeholder="Repeat Desired Password" value=""/>	                                        
        	    	                            </div>
            		                            <div class="form-group">
            		                            	<label for="name" class="form-control-label">Name:</label><br>	
													<form:input type="text" path="name" id="name" class=" form-control"
														placeholder="Enter Your Name" value=""/>
													<br><form:errors path="name"/><br>            	                            
            	                            	</div>
	            	                            <div class="form-group">
    	        	                            	<label for="email" class="form-control-label">Email:</label><br>
													<form:input type="email" path="email" id="email" class="form-control"
														placeholder="Enter Your Email Address" value=""/>
													<br><form:errors path="email"/><br>				            	                            
            	    	                        </div>
	            	                            <div class="form-actions form-group">
    	                                            <button type="submit" class="btn btn-primary btn-sm">Submit</button>
        	                                    </div>
											</form:form>
										</div>
                    	            </div>
                        	    </div>
							</div>
                    	</div>
	                </div>
    	        </section>

        	    <section>
                	<div class="section__content section__content--p30">
	                    <div class="container-fluid">
    	                    <div class="row">
	                        </div>
                    	</div>
                	</div>
	            </section>

    	        <section>
        	        <div class="container-fluid">
            	        <div class="row">
                	        <div class="col-md-12">
                    	        <div class="copyright">
                        	        <p>Copyright © 2019 Simon Yip. All rights reserved. Adapted template from <a href="https://colorlib.com">Colorlib</a> and <a href="https://github.com/puikinsh/CoolAdmin">Cool Admin</a>.</p>
	                            </div>
    	                    </div>
        	            </div>
            	    </div>
            	</section>
            	<!-- END PAGE CONTAINER-->
            </div>
        </div>
    </div>

	<!--  Add JS -->
	<%@include file="/resources/include/addJs.jsp"%>
	
</body>
</html>
