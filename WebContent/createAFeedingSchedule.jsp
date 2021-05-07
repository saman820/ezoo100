<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp"/>

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

	
	<main>
		<div class="container">
	<c:choose>
	<c:when test="${not empty message}">
		<p class="alert ${messageClass}">${message }</p>
	<%
		session.setAttribute("message", null);
		session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
		<h1>EZOO <small>Add Feeding Schedule</small></h1>
		<hr class="paw-primary">
		
		<form action="createFeedingSchedule" method="post" class="form-horizontal">
			<div class="form-group">
				<label for="id" class="col-sm-4 control-label">ID</label>
				<div class= "col-sm-4">
					<input type="number" class="form-control" id="id" name="id" placeholder = "ID" required="required" />
					
				</div>
			</div>
			<div class="form-group">
				<label for="feedingTime" class="col-sm-4 control-label">Feeding Time</label>
				<div class= "col-sm-4">
					<input type="text" class="form-control" id="feedingTime" name="feedingTime" placeholder = "Feeding Time" required="required" />
					
				</div>
			</div>
			<div class="form-group">
				<label for="recurrence" class="col-sm-4 control-label">Recurrence</label>
				<div class= "col-sm-4">
					<input type="text" class="form-control" id="recurrence" name="recurrence" placeholder = "Recurrence" required="required" />
					
				</div>
			</div>
			<div class="form-group">
				<label for="food" class="col-sm-4 control-label">Food</label>
				<div class= "col-sm-4">
					<input type="text" class="form-control" id="food" name="food" placeholder = "Food" required="required" />
					
				</div>
			</div>
			<div class="form-group">
				<label for="notes" class="col-sm-4 control-label">Notes</label>
				<div class= "col-sm-4">
					<input type="text" class="form-control" id="notes" name="notes" placeholder = "Notes" required="required" />
					
				</div>
			</div>
			<div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-primary">Add</button>
		    </div>
		  	</div>
		</form>
		</div>
	</main>
	
	
	<jsp:include page="footer.jsp" />
