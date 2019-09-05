<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title Page-->
	<title>ManageIt! Modify Rooms</title>
	
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
                            	            <strong>Add Room</strong>
											<small> Form</small>
											<h6>${messageResult}</h6>
                                    	</div>
                                    	<div class="card-body card-block">
                                    		<form:form action="${pageContext.request.contextPath}/addRoom" method="post" modelAttribute="userKey" class="form-horizontal">
	                                        	<div class="form-group">
													<label for="name" class="form-control-label">Name:</label><br>
													<form:input type="text" path="name" id="name" class="form-control"
														placeholder="Enter Room Name" value=""/>
													<br><form:errors path="name"/><br>													
	                                        	</div>
                                        		<div class="form-group">
													<label for="location" class="form-control-label">Location:</label><br>
													<form:input type="text" path="location" id="location" class="form-control"
														placeholder="Enter Room Location" value=""/>
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

	            <section>
    	            <div class="section__content section__content--p30">
        	            <div class="container-fluid">
            	            <div class="row">
								<div class="col-md-12">
                                	<!-- DATA TABLE -->
                                	<h3 class="title-5 m-b-35">Room List</h3>
                                	<div class="table-responsive table-responsive-data2">
	                                    <table class="table table-data2">
                                        	<thead>
	                                            <tr>
                                                	<th>id</th>
	                                                <th>room name</th>
	                                                <th>location</th>
    	                                            <th></th>
        	                                    </tr>
            	                            </thead>
                	                        <tbody>
                    	                    	<c:forEach var="room" items="${roomList}">
                        	                    <tr class="tr-shadow">
                            	                    <td>${room.id}</td>
                                	                <td class="desc">${room.name}</td>
                                    	            <td>${room.location}</td>
                                        	        <td>
                                            	        <div class="table-data-feature">
                                                        	<button class="item" data-toggle="tooltip" data-placement="top" title="Update" type="button" onclick="location.href='${pageContext.request.contextPath}/updateRoom/${room.id}'" >
	                                                            <i class="zmdi zmdi-edit"></i>
	                                                        </button>
    	                                                    <button class="item" data-toggle="tooltip" data-placement="top" title="Delete" type="button" onclick="location.href='${pageContext.request.contextPath}/removeRoom/${room.id}'">
        	                                                    <i class="zmdi zmdi-delete"></i>
            	                                            </button>
                	                                    </div>
                    	                            </td>
                        	                    </tr>
                            	                <tr class="spacer"></tr>
                                            	</c:forEach>
                                        	</tbody>
                                    	</table>
                                	</div>
	                                <!-- END DATA TABLE -->
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
