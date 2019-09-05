<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<c:choose>
    <c:when test="${userType.equals('admin')}">
                    <div class="account2">
                        <div class="image img-cir img-120">
                            <img src="${pageContext.request.contextPath}/resources/images/icon/avatar-big-01.jpg" alt="${userLogin.name}" />
                        </div>
                        <h4 class="name">${userLogin.name} (${userLogin.username}) [Admin]</h4>
                        <a href="${pageContext.request.contextPath}/logout">Sign out</a>
                    </div>
                    <nav class="navbar-sidebar2">
	                    <ul class="list-unstyled navbar__list">
    	                    <li class="active">
        	                    <a href="${pageContext.request.contextPath}/dashboard">
            	                    <i class="fas fa-tachometer-alt"></i>Dashboard</a>                        
                	        </li>
                    	    <li class="has-sub">
                        	    <a class="js-arrow" href="#">
                            	    <i class="far fa-window-maximize"></i>Manage
                                	<span class="arrow">
                                    	<i class="fas fa-angle-down"></i>
                                	</span>
                            	</a>
                            	<ul class="list-unstyled navbar__sub-list js-sub-list">
                               	 	<li>
                                    	<a href="${pageContext.request.contextPath}/modifyRooms">
                                        	<i class="fas fa-th-large"></i>Rooms</a>                                
                                	</li>
                                	<li>
                                    	<a href="${pageContext.request.contextPath}/modifyGroups">
                                        	<i class="far fa-id-card"></i>Groups</a>
                                	</li>
                                	<li>
                                    	<a href="${pageContext.request.contextPath}/modifyEvents">
                                        	<i class="fas fa-tasks"></i>Events</a>
                                	</li>                                
                            	</ul>
                        	</li>
                    	</ul>
                    </nav>
    </c:when>
    <c:when test="${userType.equals('user')}">
                    <div class="account2">
                        <div class="image img-cir img-120">
                            <img src="${pageContext.request.contextPath}/resources/images/icon/avatar-big-01.jpg" alt="${userLogin.name}" />
                        </div>
                        <h4 class="name">${userLogin.name} (${userLogin.username})</h4>
                        <a href="${pageContext.request.contextPath}/logout">Sign out</a>
                    </div>
                    <nav class="navbar-sidebar2">
	                    <ul class="list-unstyled navbar__list">
    	                    <li class="active">
        	                    <a href="${pageContext.request.contextPath}/dashboard">
            	                    <i class="fas fa-tachometer-alt"></i>Dashboard</a>                        
           					</li>
                        	<li>
                            	<a href="${pageContext.request.contextPath}/associateGroups">
                                	<i class="fas fa-tasks"></i>Associate Groups</a>                        
                        	</li>
                        	<li>
                            	<a href="${pageContext.request.contextPath}/addEvent">
                                	<i class="fas fa-th-large"></i>Add Event</a>                        
                        	</li>
                    	</ul>
                    </nav>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>