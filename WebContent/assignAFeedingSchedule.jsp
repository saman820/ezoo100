	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
		
		<main>
		 <div class="container">
		<c:choose>
		<c:when test="${not empty message }">
		  <p class="alert ${messageClass}">${message }</p>
		<%
		  session.setAttribute("message", null);
		  session.setAttribute("messageClass", null);
		%>
		</c:when>
		</c:choose>
			<h1>eZoo <small>Assign a Feeding Schedule to an Animal</small></h1>
			<hr class="paw-primary">
			<form action="assignFeedingSchedule2" method="post" class="form-horizontal">
				
				<div class="form-group">
		    		<label for="animal" class="col-sm-4 control-label">Choose Animal</label>
		    		<div class="col-sm-4">
						<select required="required" id="animal" name="animalID" class="form-control" onchange="myFunction()">
							<c:forEach var="animal" items="${animals}">
								<option value="${animal.animalID}">
									<c:out value="${animal.name} - ID: ${animal.animalID}" />
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
			<div class="form-group">
		    	<label for="feeding" class="col-sm-4 control-label">Choose Feeding Schedule</label>
		    	<div class="col-sm-4">
					<select required="required" id="feeding" name="feedingScheduleID" class="form-control">
						<c:forEach var="feedingSchedule" items="${feedingSchedules}">
							<option value="${feedingSchedule.scheduleID}">
								<c:out value="ID: ${feedingSchedule.scheduleID} - ${feedingSchedule.feedingTime}- ${feedingSchedule.recurrence}- ${feedingSchedule.food}" />
							</option>
						</c:forEach>
					</select>
				</div>
			</div>
				
			<div class="form-group">
    			<div class="col-sm-offset-4 col-sm-1">
	      		<button type="submit" class="btn btn-primary"> &nbsp&nbspAssign&nbsp&nbsp&nbsp </button>
	    		</div>
			</div>
			<div class="form-group">
    			<div class="col-sm-offset-4 col-sm-1">
    			<!--  notice type="button -->
	      		<a id="unassignLink" href="unassignFeedingSchedule"><button type="button" class="btn btn-primary">Unassign</button></a>
	    		</div>
			</div>
			</form>
		</div>
		</main>
	
		<script type="text/javascript">
			var val = document.getElementById("animal").value;
			var hre = "unassignFeedingSchedule?animalID="+val;
			document.getElementById("unassignLink").setAttribute("href",hre);
	
			function myFunction(){
				var val = document.getElementById("animal").value;
				var hre = "unassignFeedingSchedule?animalID="+val;
				document.getElementById("unassignLink").setAttribute("href",hre);
	
			}
		</script>
	
	
	
		<!-- Footer -->
	<jsp:include page="footer.jsp" />