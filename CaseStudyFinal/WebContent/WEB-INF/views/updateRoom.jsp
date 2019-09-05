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
	<title>ManageIt! Update Room</title>
	
	<!--  Add CSS -->
	<%@include file="/resources/include/addCss.jsp"%>    
	
</head>

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
                        <img src="${pageContext.request.contextPath}/resources/images/icon/logo-short.png" alt="ManageIT!" />
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
                            	            <strong>Update Room</strong>
											<small> Form</small>
											<h6>${messageResult}</h6>
                                    	</div>
                                    	<div class="card-body card-block">
											<form:form action="${pageContext.request.contextPath}/confirmUpdateRoom" method="post" modelAttribute="userKey" class="form-horizontal">
												 <div class="form-group">
													<label for="id" class="form-control-label">Id:</label><br>
													<form:input type="text" path="id" id="id" class="form-control"
														placeholder="Enter Id" value="${room.id}" readonly="true"/><br><br>
	                                        	</div>													
	                                        	<div class="form-group">
													<label for="name" class="form-control-label">Name:</label><br>
													<form:input type="text" path="name" id="name" class="form-control"
														placeholder="Enter Room Name" value="${room.name}"/>
													<br><form:errors path="name"/><br>													
	                                        	</div>
                                        		<div class="form-group">
													<label for="location" class="form-control-label">Location:</label><br>
													<form:input type="text" path="location" id="location" class="form-control"
														placeholder="Enter Room Location" value="${room.location}"/>
													<br><form:errors path="location"/><br>													
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

				<!--  Add copyright section -->
				<%@include file="/resources/include/copyright.jsp"%>

            <!-- END PAGE CONTAINER-->
			</div>
		</div>
    </div>

	<!--  Add JS -->
	<%@include file="/resources/include/addJs.jsp"%>
	
</body>
</html>
