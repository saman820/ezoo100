<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp"/>

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
		<main>
		<div class="container">
		<c:choose>
		<c:when test="${not empty message }">
			<p class="alert ${messageClass}">${message}</p>
		<%
			session.setAttribute("message", null);
			session.setAttribute("messageClass",null);
		%>
		</c:when>
		</c:choose>
			
			<h1>EZOO <small>Feeding Schedules</small></h1>
			<hr class="paw-primary">
			<table class="table table-striped table-hover table-responsive ezoo-datatable">
				<thead>
					<tr>
						<th class="text-center">ID</th>
						<th class="text-center">Feeding time</th>
						<th class="text-center">Recurrence</th>
						<th class="text-center">Food</th>
						<th class="text-center">Notes</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="feedingSchedule" items="${feedingSchedules}">
						<tr> 
							<td><c:out value="${feedingSchedule.scheduleID}" /></td>
							<td><c:out value="${feedingSchedule.feedingTime}" /></td>
							<td><c:out value="${feedingSchedule.recurrence}" /></td>
							<td><c:out value="${feedingSchedule.food}" /></td>
							<td><c:out value="${feedingSchedule.notes}" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</main>

	<jsp:include page="footer.jsp" />
	