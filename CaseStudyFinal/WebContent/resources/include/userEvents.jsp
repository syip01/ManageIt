<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
                                	<!-- DATA TABLE -->
                                	<h3 class="title-5 m-b-35">Event List</h3>
                                	<h6>${messageResult}</h6>
                                	<div class="table-responsive table-responsive-data2">
	                                    <table class="table table-data2">
                                        	<thead>
	                                            <tr>
                                                	<th>id</th>
	                                                <th>group</th>
	                                                <th>room</th>
	                                                <th>admin</th>
	                                                <th>start time</th>
	                                                <th>end time</th>
	                                                <th>status</th>
    	                                            <th></th>
        	                                    </tr>
            	                            </thead>
                	                        <tbody>
                    	                    	<c:forEach var="event" items="${eventList}">
                        	                    <tr class="tr-shadow">
                            	                    <td>${event.id}</td>
                                	                <td class="desc">${event.group.name}</td>
                                    	            <td class="desc">${event.room.name}</td>
                                    	            <td class="desc">${event.admin.name}</td>
                                    	            <td>${event.startTime}</td>
                                    	            <td>${event.endTime}</td>
                                    	            <td>
	                               	            		<c:choose>
   													    	<c:when test = "${event.status == 0}">
        	   												<span class="status--denied">Submitted</span>
												            </c:when>
       														<c:when test = "${event.status == 1}">
           													<span class="status--denied">Not approved</span>
												            </c:when>
       														<c:when test = "${event.status == 2}">
           													<span class="status--denied">Resubmitted</span>
           													</c:when>
           													<c:when test = "${event.status == 3}">
           													<span class="status--process">Approved</span>
											    	        </c:when>             
       														<c:otherwise>
           													<span class="status--denied">Invalid/Unknown Status</span>
       														</c:otherwise>
									      				</c:choose>
										      		</td>                                    	            
                                        	        <td>
                                            	        <div class="table-data-feature">
															<c:if test = "${event.status >= 0 && event.status <= 2 && userType.equals('user')}">
                                                        	<button class="item" data-toggle="tooltip" data-placement="top" title="Update Event" type="button" onclick="location.href='${pageContext.request.contextPath}/updateEvent/${event.id}'" >
	                                                            <i class="zmdi zmdi-edit"></i>
	                                                        </button>
															</c:if>
															<c:if test = "${event.status != 3 && userType.equals('admin')}">
															<button class="item" data-toggle="tooltip" data-placement="top" title="Approve Event" type="button" onclick="location.href='${pageContext.request.contextPath}/approveEvent/${event.id}'" >
	                                                            <i class="zmdi zmdi-mail-send"></i>
	                                                        </button>															
	 														</c:if>
	 														<c:if test = "${event.status != 1 && userType.equals('admin')}">
															<button class="item" data-toggle="tooltip" data-placement="top" title="Reject Event" type="button" onclick="location.href='${pageContext.request.contextPath}/disapproveEvent/${event.id}'" >
	                                                            <i class="zmdi zmdi-more"></i>	 														
															</button>
															</c:if>
	 														<c:if test = "${userType.equals('admin')}">
															<button class="item" data-toggle="tooltip" data-placement="top" title="Delete Event" type="button" onclick="location.href='${pageContext.request.contextPath}/deleteEvent/${event.id}'" >
	                                                            <i class="zmdi zmdi-delete"></i>	 														
															</button>
	 														</c:if>
                 	                                    </div>
                    	                            </td>
                        	                    </tr>
                            	                <tr class="spacer"></tr>
                                            	</c:forEach>
                                        	</tbody>
                                    	</table>
                                	</div>
	                                <!-- END DATA TABLE -->
