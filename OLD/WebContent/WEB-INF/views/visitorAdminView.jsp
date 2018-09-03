<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Visitor Admin</title>
	<link rel="stylesheet" href="../resources/fontawesome/css/all.css">
	<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="../resources/css/visitorViews.css">
	<script>
	function checkDateRange() {
		   // Parse the entries
		   var startDate = Date.parse(document.getElementById("fromDate").value);
		   var endDate =  Date.parse(document.getElementById("toDate").value);
		   
		   // Check the date range, 86400000 is the number of milliseconds in one day
		  
		   var difference = (endDate - startDate) / (86400000);
		   //alert( difference ); for DEBUG
		   if (difference < 0) {
		       alert("The start date must come before the end date.");
		       return false;
		   }
		   
		   return true;
		}
	 
	</script>
	<style>
		button:disabled,
button[disabled]{
  border: 1px solid #999999;
  background-color: #cccccc;
  color: #666666;
}
	</style>
</head>

<body class="container-fluid text-center">
	<header class="container text-center" id="title">
		<div class="ml15 pull-center">
			<h1>
				<span class="word">W </span> <span class="word">e </span> <span
					class="word">l </span> <span class="word">c </span> <span
					class="word">o </span> <span class="word">m </span> <span
					class="word welcome">e </span> <span class="word to">To </span> <span
					class="word ">P </span> <span class="word ">h </span> <span
					class="word ">o </span> <span class="word state">e </span> <span
					class="word ">n </span> <span class="word ">i </span> <span
					class="word ">x</span>
			</h1>
			<p class="word logo pull-center">
				<img src="../resources/images/logo.png" />
			</p>
		</div>
	</header>
	<section class="nav">
	<div id="next_prev_btn">

		<a href="/Phoenix_Resource_Management_System/dashboard"
			class="  pull-left"> <span
			class="fas fa-arrow-alt-circle-left fa-4x fa_left_btn text-center"></span>
		</a> 
		<a href="/Phoenix_Resource_Management_System/Visitor/Home" class=" pull-right"> <span
			class="fas fa-arrow-alt-circle-right  fa-4x  fa_right_btn text-center"></span>
		</a>
		</div>
	</section>
	<section class="container">
	<form id="dr" action="" method="post" style="margin: 0; padding-bottom: 50px;">
		<p>
			Enter Date Range
		
		    <input type="text" name="fromDay" id="fromDate" placeholder="From" onfocus="(this.type='date')"  style="display: inline;"/>
		    <input type="text" name="toDay" id="toDate" placeholder="To" onfocus="(this.type='date')" style="display: inline;" />
		    <input style="display: inline;" class="btn btn-success btn-sm " type="submit" value="submit" onclick="checkDateRange(); if(checkDateRange() == true) document.getElementById('dr').action='/Phoenix_Resource_Management_System/Visitor/RangeSearch'; else document.getElementById('dr').action='/Phoenix_Resource_Management_System/Visitor/Admin';"  />
		</p>
	</form>
		<div class="section_block">
			
				<table class="table">
				  <thead>
						<tr>
							<th scope="col">First Name</th>
							<th scope="col">Last Name</th>
							<th scope="col">Phone</th>
							<th scope="col">Purpose</th>
							<th scope="col">Check In Time</th>
							<th scope="col">Check Out Time</th>						
							<th scope="col">Check Out</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="ba" items="${alldata}">
							<tr scope="row">
								<td>${ba.getFirstName()}</td>
								<td>${ba.getLastName()}</td>
								<td>${ba.getPhone()}</td>
								<td>${ba.getVisitPurpose()}</td>
								<td>${ba.getCheckedInTime()}</td>
								<td>${ba.getCheckedOutTime()}</td>
								<td><form class="admin-button"
										action="/Phoenix_Resource_Management_System/Visitor/AdminCO/${ba.getVisitorId()}">
	<button type="submit"  class="admin-btn" <c:if test="${ba.getHasCheckedOut() == 1}"><c:out value="disabled='disabled'"/></c:if>"><h3>Check Out</h3></button>								</form></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			
		</div>
	</section>
</body>
</html>