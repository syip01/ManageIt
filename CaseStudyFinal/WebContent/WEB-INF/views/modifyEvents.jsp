<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title Page-->
	<title>ManageIt! Modify Events</title>
	
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

<%-- <body>
	<h1>MODIFY EVENT PLACEHOLDER</h1>
	
	<h2>${messageResult}</h2>

	<h2>List Of All Events:</h2>

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
            Pending
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
			<c:when test = "${event.status != 3}">
	 		<a href="${pageContext.request.contextPath}/approveEvent/${event.id}">Approve Event</a>
	 		</c:when>
	 		<c:when test = "${event.status != 1}">
	 		<a href="${pageContext.request.contextPath}/disapproveEvent/${event.id}">Not Approve Event</a>
	 		</c:when>
	 	</c:choose>
	 	<a href="${pageContext.request.contextPath}/deleteEvent/${event.id}">Delete Event</a>
 		<hr>
	</c:forEach>

</body>
</html>
 --%>