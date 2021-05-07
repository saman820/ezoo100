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
			<h1>eZoo <small>Update a Feeding Schedule to an Animal</small></h1>
			<hr class="paw-primary">
			<form action="updateFeedingSchedule2" method="post" class="form-horizontal">
				

			<div class="form-group">
		    	<label for="healthStatus" class="col-sm-4 control-label">Choose a Feeding Schedule to Update</label>
		    	<div class="col-sm-4">
					<select required="required" id="select" name="select" class="form-control" onchange="myFunction()">
						<c:forEach var="feedingSchedule" items="${feedingSchedules}">
							<option value="${feedingSchedule.scheduleID}*${feedingSchedule.feedingTime}*${feedingSchedule.food}*${feedingSchedule.recurrence}*${feedingSchedule.notes}">
								<c:out value="ID: ${feedingSchedule.scheduleID} - ${feedingSchedule.food} - ${feedingSchedule.feedingTime}" />
							</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
			<label for="id" class="col-sm-4 control-label">ID</label>
				<div class= "col-sm-4">
					<input type="number" class="form-control" id="id" name="feedingScheduleID2"  required="required" disabled/>
				</div>
			</div>
			
			<input type="hidden" id="hiddenID" name="feedingScheduleID" value="">

			<div class="form-group">
				<label for="feedingTime" class="col-sm-4 control-label">Feeding Time</label>
				<div class= "col-sm-4">
					<input type="text" class="form-control" id="feedingTime" name="feedingTime" value = "" />
					
				</div>
			</div>
			<div class="form-group">
				<label for="recurrence" class="col-sm-4 control-label">Recurrence</label>
				<div class= "col-sm-4">
					<input type="text" class="form-control" id="recurrence" name="recurrence" value = ""  />
					
				</div>
			</div>
			<div class="form-group">
				<label for="food" class="col-sm-4 control-label">Food</label>
				<div class= "col-sm-4">
					<input type="text" class="form-control" id="food" name="food" value = "" />
					
				</div>
			</div>
			<div class="form-group">
				<label for="notes" class="col-sm-4 control-label">Notes</label>
				<div class= "col-sm-4">
					<input type="text" class="form-control" id="notes" name="notes"  value="" />
					
				</div>
			</div>
			
			<div class="form-group">
	   			<div class="col-sm-offset-4 col-sm-1">
	      		<button type="submit" class="btn btn-primary">Update feeding schedule</button>
	    		</div>
			</div>
			<div class="form-group">
	   			<div class="col-sm-offset-4 col-sm-1">
	      		<a href="deleteFeedingSchedule?feedingScheduleID=" id="deleteLink"><button type="button" class="btn btn-primary">Delete feeding schedule</button></a>
	      	<!-- 	<a href="deleteFeedingSchedule?feedingScheduleID=" id="deleteLink"><input class="btn btn-primary" value="Delete feeding schedule" /></a>-->
	    		</div>
			</div>
			
			</form>
		</div>
		</main>
		
		<script type="text/javascript">
		var val= document.getElementById("select").value;
		var arr= val.split("*");
		document.getElementById("hiddenID").setAttribute("value",arr[0]);
		document.getElementById("id").setAttribute("value",arr[0]);
		document.getElementById("feedingTime").setAttribute("value",arr[1]);
		document.getElementById("recurrence").setAttribute("value",arr[2]);
		document.getElementById("food").setAttribute("value",arr[3]);
		document.getElementById("notes").setAttribute("value",arr[4]);
		var hre = "deleteFeedingSchedule?feedingScheduleID="+arr[0];
		document.getElementById("deleteLink").setAttribute("href",hre);

		function myFunction(){
			var val= document.getElementById("select").value;
			var arr= val.split("*");
			document.getElementById("hiddenID").setAttribute("value",arr[0]);
			document.getElementById("id").setAttribute("value",arr[0]);
			document.getElementById("feedingTime").setAttribute("value",arr[1]);
			document.getElementById("recurrence").setAttribute("value",arr[2]);
			document.getElementById("food").setAttribute("value",arr[3]);
			document.getElementById("notes").setAttribute("value",arr[4]);
			var hre = "deleteFeedingSchedule?feedingScheduleID="+arr[0];
			document.getElementById("deleteLink").setAttribute("href",hre);

		}
		</script>
	
	
	
	
		<!-- Footer -->
	<jsp:include page="footer.jsp" />