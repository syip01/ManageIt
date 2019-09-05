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
	<title>ManageIt! Add Event</title>
	
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
                            	            <strong>Add Event</strong>
											<small> Form</small>
											<h6>${messageResult}</h6>
                                    	</div>
                                    	<div class="card-body card-block">
                                    		<form:form action="${pageContext.request.contextPath}/confirmAddEvent" method="post" modelAttribute="userKey" class="form-horizontal">
												<div class="row form-group">
	                                                <div class="col col-md-3">
    	                                                <label for="groupid" class=" form-control-label">Group:</label>
        	                                        </div>
            	                                    <div class="col-12 col-md-9">
                	                                    <select name="groupid" id="groupid" class="form-control">
														<c:forEach var="group" items="${groupList}">
															<option value = "${group.id}">${group.name}</option>
														</c:forEach>
														</select><br><br>
                                        	        </div>
                                            	</div>
												<div class="row form-group">
	                                                <div class="col col-md-3">
    	                                                <label for="roomid" class=" form-control-label">Room:</label>
        	                                        </div>
            	                                    <div class="col-12 col-md-9">
                	                                    <select name="roomid" id="roomid" class="form-control">
														<c:forEach var="room" items="${roomList}">
															<option value = "${room.id}">${room.name}</option>
														</c:forEach>
														</select><br><br>
                                        	        </div>
                                            	</div>
												<label for="startTime" class="form-control-label">Start Time: </label><br>
												<input type="datetime-local" name="startTime" id="startTime" class="form-control"
													placeholder="Enter Start Time" value=""><br><br>			
												<label for="endTime" class="form-control-label">End Time: </label><br>
												<input type="datetime-local" name="endTime" id="endTime" class="form-control"
													placeholder="Enter End Time" value=""><br><br>
												<div class="row form-group">
	                                                <div class="col col-md-3">
    	                                                <label for="status" class=" form-control-label">Status:</label>
        	                                        </div>
            	                                    <div class="col-12 col-md-9">
                	                                    <select name="status" id="status" class="form-control" disabled>
															<option value = "0" selected>Submitting</option>
															<option value = "1">Requires Resubmit</option>
															<option value = "2">Resubmitting</option>
															<option value = "3">Approved</option>
														</select><br><br>
                                        	        </div>
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
									<!-- ADD USER EVENTS INFO -->
					            	<%@include file="/resources/include/userEvents.jsp"%>
					            	<!-- END OF USER EVENTS INFO -->                            	
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

<%-- 
<body>
	<h1>ADD EVENT PLACEHOLDER</h1>
	
	<h2>${messageResult}</h2>

	<form:form action="${pageContext.request.contextPath}/confirmAddEvent" method="post" modelAttribute="userKey">

		<label for="groupid">Group: </label><br>
		<select name="groupid">	
		<c:forEach var="group" items="${groupList}">
			<option value = "${group.id}">${group.name}</option>
		</c:forEach>
		</select><br><br>
		
		<label for="roomid">Room: </label><br>
		<select name="roomid">
		<c:forEach var="room" items="${roomList}">
			<option value = "${room.id}">${room.name}</option>
		</c:forEach>		
		</select><br><br>
		 
		<label for="startTime">Start Time: </label><br>
		<input type="datetime-local" name="startTime" id="startTime"
			placeholder="Enter Start Time" value=""><br><br>
			
		<label for="endTime">End Time: </label><br>
		<input type="datetime-local" name="endTime" id="endTime"
			placeholder="Enter End Time" value=""><br><br>
			
		<label for="status">Status: </label><br>
		<select name="status" disabled>
			<option value = "0" selected>Submitting</option>
			<option value = "1">Requires Resubmit</option>
			<option value = "2">Resubmitting</option>
			<option value = "3">Approved</option>
		</select><br><br>
		<input type="submit" value="Add Pending Event">
	</form:form>
	
	<hr>
	
	<h2>List Of Your Events:</h2>

	<c:forEach var="event" items="${eventList}">
		<p>ID: ${event.id}</p>
		<p>GROUP: ${event.group.name}</p>
		<p>ROOM: ${event.room.name}</p>
		<p>ADMIN: ${event.admin.name}</p>
		<p>STARTTIME: ${event.startTime}</p>
		<p>ENDTIME: ${event.endTime}</p>
		<p>STATUS: 
		<c:choose>         
        	<c:when test = "${event.status == 0}">
            Submitted
            </c:when>

        	<c:when test = "${event.status == 1}">
            Unapproved
            </c:when>

        	<c:when test = "${event.status == 2}">
            Resubmitted
            </c:when>
            
            <c:when test = "${event.status == 3}">
            Approved
            </c:when>
             
         	<c:otherwise>
            Invalid Status
         	</c:otherwise>
      	</c:choose>
		</p>
		
		<c:choose>
			<c:when test = "${event.status >= 0 && event.status <= 2}">
	 		<a href="${pageContext.request.contextPath}/updateEvent/${event.id}">Make Changes</a>
	 		</c:when>
	 	</c:choose>
 		<hr>
	</c:forEach>

</body>
</html>
 --%>