<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page import="java.util.*" %>
<html lang="en">
	<head>
		<%@page
		import="java.util.*, org.springframework.web.context.WebApplicationContext,
		org.springframework.web.context.support.WebApplicationContextUtils"%>
		<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>Chart</title>
		
		<!-- Dashboard Css -->	
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
		
		<spring:url value="/resources/css/bootstrap.min.css" var="bootMin" />
		<spring:url value="/resources/css/dashboard.css" var="DashboardCSS" />
		
		<link rel="stylesheet" href="${bootMin}" />
		<link rel="stylesheet" href="${DashboardCSS}" />
		
		
		<spring:url value="/resources/js" var="JS" />
		
		<script src="${JS}/jquery.js"></script>
		<script src="${JS}/bootstrap.js"></script>
		<!-- End Dashboard Css -->	
		<script> 
			$(function() {
				$('.drawArea').hide();
				$('#theButton').click(function() {
					$('.drawArea').show();
				});
				$('#viewType').change(function(){
					if ($( "#viewType" ).val() == "all") {
						$('#middle').hide(); 	// hide room list
					} else {
						$('#middle').show();
						$('#roomType2').children().remove().end().append('<option selected value="all">All Rooms</option>');
						
						 $("#roomType option").each(function(i){
							 	var resourceType = $( "#viewType" ).val();
							 	if($(this).attr('class') == resourceType) {
							 		$('#roomType2').append($('<option>', {	// add rooms based on the type to roomType2 dropdown
							 		    value: $(this).val(),
							 		    text: $(this).text()
							 		}));
							 	} 
						       // alert($(this).attr('class'));
						       // alert(resourceType);
						 });
					}
					//window.location="${context}/chart/"+this.value();
				});
				$('#dbConnect').hide();
				$('#period').change(function() {
					var type = $('#period').val();
					if (type == 'day') {
						$('#dateText').html('Date: ');
					} else {
						$('#dateText').html('Start date: ');
					}
				});
				var today = new Date();
				var dd = today.getDate();
				var mm = today.getMonth()+1; //January is 0!
				var yyyy = today.getFullYear();
				if(dd<10){
			        dd='0'+dd
			    } 
			    if(mm<10){
			        mm='0'+mm
			    } 
				
				today = yyyy+'-'+mm+'-'+dd;
				$('#datefield').attr("max",today);
				Date.prototype.toDateInputValue = (function() {
				    var local = new Date(this);
				    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
				    return local.toJSON().slice(0,10);
				});
				$('#datefield').val(new Date().toDateInputValue());
			});
		</script>
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		<style>
			#middle,#middle2 {
				display: none;
			}
			#header {
				border-bottom-style: double;
				margin-bottom: 50px;
			}
			#datefield {
				margin-top: 5px;
			}
			
			#goRight {
				float: right;
				padding: 5px;
				padding-right:20px;
			}
			div.container.text-center{
				background-image:url("/Phoenix_Resource_Management_System/resources/images/green.jpg");
				
			}
			
		</style>
		<script type="text/javascript">
			google.charts.load('current', {
				'packages' : [ 'corechart' ]
			});		
			google.charts.setOnLoadCallback(drawChart);
			function drawChart() {
				//the google chart table
				var dataTable = new google.visualization.DataTable();
				
				//add the types of info we are looking at. 
				//room type and number of bookings in the room
				dataTable.addColumn('string', 'Room Type');
				dataTable.addColumn('number', 'Bookings');
				
				//the table that has our database output
				var outTable = document.getElementById("dbConnect");
				
				//the rows of the html table
				var rowsArr = outTable.rows;
				
				//add rowsArg.length number of empty rows to the google table
				dataTable.addRows(3);
						
				//first loop is the rows second is the columns.
		/*		for(var i=1; i<rowsArr.length; i++){
					for(var j=0; j<2; j++){
						dataTable.setCell(i,j,rowsArr[i].cells[j].textContent);
					}
				}
				*/
					// fill the first row of the table	(1,1) => percent utilized
					for(var j=0; j<2; j++){
						dataTable.setCell(1,j,rowsArr[1].cells[j].textContent);
					}
					// fill the second row of the table
					dataTable.setCell(2,0,"Not Used");
					dataTable.setCell(2,1,1-rowsArr[1].cells[1].textContent); // (2,1) => 1-(percent utilized)
					
					var options = {
						title : 'Utilization Chart',
						titleFontSize:25,
						colors: ['#ff0000','#33cc33'],
						backgroundColor: { fill:'transparent' },
						is3D: true
					};
					var chart = new google.visualization.PieChart(document
							.getElementById('chart_div'));
			
					var btnSave = document.getElementById('save-pdf');
					
					google.visualization.events.addListener(chart, 'ready', function () {
					    btnSave.disabled = false;
					});
					
					btnSave.addEventListener('click', function () {
					    var doc = new jsPDF();
					    doc.addImage(chart.getImageURI(), 0, 0);
					    doc.save('chart.pdf');
					}, false);				
					chart.draw(dataTable, options);
			}
		</script>
	</head>
	
	<body id="bod">
		<!-- navigation header -->
		<div class="container">
			<nav class="navbar navbar-default">
				<div class="container">
					<div class="navbar-header">
						<a href="dashboard">
							<img 
								src="resources/images/syntrans.png" 
								alt="logo"
								style="
									height: 10%; 
									width: 20%; 
									padding-top: 0px; 
									padding-bottom: 10px;">
						</a>
						<button 
							type="button" 
							class="navbar-toggle" 
							data-toggle="collapse"
							data-target="#myNavbar">
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span>
						</button>
					</div>
					
					<div class="collapse navbar-collapse" id="myNavbar">
						<ul class="nav navbar-nav navbar-center"
							style="left-padding: 300px;">
						</ul>
						<ul class="nav navbar-nav navbar-right">
					<li style="font-size: 22px;">

					<li class="dropdown">
    					<a href="javascript:void(0)" class="glyphicon glyphicon-user"></a>
    								<div class="dropdown-content">
     								<a href="logout">Log Out</a>
     								
					</li>
				</li>
			</div>

				

				</ul>
					</div>
				</div>
			</nav>
		</div>
		<!-- end navigation header -->
		<div class="container text-center">		
			<table style="width:100%">
			<tr>
			<td>
				<form action="drawChart" method="post" style="margin: 0 auto; width: 250px;" >
					<div class="row">
						<div class="col-sm" >
							<div class="form-group">
								<br>
								<br>
								<br>
								<label for="sel1">Select Type of Room:</label>
								<select class="form-control" id="viewType" name="viewType">
									<option value="all" selected>All Rooms</option>
					                <c:forEach items="${resourceTypes}" var="rt">
					                <c:set var="resourceTypeId" value="${rt.getResourceTypeId()}" />
					                <option value="${resourceTypeId}">${rt.getResourceTypeName()}</option>
					                </c:forEach>
				                </select>
							</div> 
						</div>
						<div class="col-sm">
							<div id=middle class="form-group">
								<label>Select Room:</label>
								<select class="form-control" name="roomType2" id="roomType2">
					                <option value="all">All rooms</option>
					          	</select>
							</div>
						</div>
						<div class="col-sm">
							<div class="form-group">
								<label for="sel1">Select Utilization Period:</label>
								<select class="form-control" name="period" id="period">
									<option value="day">Day</option>
									<option value="weekly">Weekly</option>
									<option value="monthly">Monthly</option>
								</select>
							</div> 
						</div>
						<div class="col-sm">
							<label>Date: </label><br>
							<input id="datefield" type="date" name="pickedDate" required max='2000-13-13' />
						</div>
						<div class="col-sm">
							<br>
							<input type="submit" class="btn btn-primary" id = "theButton"></input>
						</div>
					</div>
					<div class="row">
						<br>
						<div id="middle2" class="form-group"> <!-- get the list of all rooms and save it in this dropdown -->
								<label for="sel1">Select room:</label>	<!-- this dropdown is hidden so the users can't see it. It's only used to filter another dropdown -->
								<select class="form-control" name="roomType" id="roomType">
					                <option value="all">All rooms</option>
					                <c:forEach items="${rooms}" var="room">
					                <c:set var="roomId" value="${room.getResourceId()}" />
					                <c:set var = "typeOfRoom" value="${room.getResourceTypeId()}" />
					                <option value="${roomId}" class="${typeOfRoom}">${room.getResourceRoomNumber()} ${room.getResourceName()}</option>
					                </c:forEach>
					          	</select>
						</div> 
					</div>
				</form>
			</td>
			<td>
				<div class="row" class="drawArea">
					<br>
					<div id="chart_div" style="width: 600px; height: 440px; margin:0 auto; border-radius:25px;"></div>
				</div>
				<div class="row" class="drawArea">
					<%
						double utilNum =(double)session.getAttribute("util");
						if(utilNum >= 0) {
							String finalStr="";
							
							finalStr+="<table id='dbConnect' style='border:1px solid black'>";	
			
							//table headers row
							finalStr+="<tr>";
							
							finalStr+="<th style='border:1px solid black'>col1</th>";
							finalStr+="<th style='border:1px solid black'>col2</th>";
							
							//close table header row. 
							finalStr+="</tr>";
							//first row
							finalStr+="<tr>";
							finalStr+="<td>Used</td>";
							finalStr+="<td>";
							finalStr+=utilNum;
							finalStr+="</td>";
							finalStr+="</tr>";
							finalStr+="</table>";
							out.write(finalStr);	
						}
					%>
				</div>
			</td>
			</tr>
			</table>
			<div class="row" id="goRight">
				<input id="save-pdf" class="btn btn-danger" class="drawArea" type="button" value="Save as PDF" disabled>
				<br>
			</div>
		<br>
		<br>
		<br>
			<footer class="footer" id="footer">
				<center>
					<p>© 2018 Syntel, Inc</p>
				</center>
			</footer>
		</div>
		
	
		<!-- pdf -->
		<script src="https://www.gstatic.com/charts/loader.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.min.js"></script>
	</body>
</html>