    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
                                	<!-- DATA TABLE -->
                                	<h3 class="title-5 m-b-35">Your Groups</h3>
                                	<div class="table-responsive table-responsive-data2">
	                                    <table class="table table-data2">
                                        	<thead>
	                                            <tr>
                                                	<th>id</th>
	                                                <th>group name</th>
	                                                <th>email</th>
    	                                            <th>${messageResult}</th>
        	                                    </tr>
            	                            </thead>
                	                        <tbody>
                    	                    	<c:forEach var="group" items="${userGroupList}">
                        	                    <tr class="tr-shadow">
                            	                    <td>${group.id}</td>
                                	                <td class="desc">${group.name}</td>
                                    	            <td><span class="block-email">${group.email}</span></td>
                                        	        <td>
                                            	        <div class="table-data-feature">
                                                        	<button class="item" data-toggle="tooltip" data-placement="top" title="Leave" type="button" onclick="location.href='${pageContext.request.contextPath}/removeUserGroup/${group.id}'" >
	                                                            <i class="zmdi zmdi-more"></i>
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
