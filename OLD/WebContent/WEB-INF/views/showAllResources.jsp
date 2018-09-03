<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.sql.*"%>
<%
	ResultSet resultset = null;
%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.modal-dialog {
	border-radius: 25px;
	border-color: darkgreen;
	text-align: center;
	width: 370px;
}

.modal-header {
	background-color: green;
	color: white;
	text-align: center;
}

.modal-body {
	border-radius: 25px;
}

.resInput input, #resssTypeId, #resLoc {
	text-align: center;
	width: 250px;
	height: 35px;
}

input[type=number] {
	text-align: center;
}

select {
	text-align-last: center;
}

input:focus::-webkit-input-placeholder {
	color: transparent;
}

td {
	padding: 20px;
}

.checks label:hover {
	background-color: lightgray;
}
</style>
<meta name="viewport" http-equiv="Content-Type"
	content="charset=ISO-8859-1 width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css">

<spring:url value="/resources/css/showAll.css" var="bootMin" />
<link rel="stylesheet" href="${bootMin}" />

<spring:url value="/resources/css/all.css" var="awesome" />
<link rel="stylesheet" href="${awesome}" />

<spring:url value="/resources/css/bootstrap.min.css" var="bootstrap" />
<link rel="stylesheet" href="${bootstrap}" />


<spring:url value="/resources/js" var="JS" />

<script src="${JS}/jquery.js"></script>
<script src="${JS}/bootstrap.js"></script>
<title>AddSearchResources</title>
</head>
<body>
	<div class="container">
		<form action="showResourceByType" method="post">
			<fieldset id="fieldset1">
				<div>
					<label>Location</label> <select class="form-control"
						name="location">
						<c:forEach items="${listCategory}" var="loc">
							<c:set var="locId" value="${fn:substring(loc, 0, 6)}" />
							<c:set var="locName" value="${fn:substring(loc, 6, loc.length())}" />
							<option value="${locId}">${locName}</option>
						</c:forEach>
					</select>
				</div>
			</fieldset>
			
			<fieldset id="fieldset2">
				<div>
					<label>Resources</label> <br> <select class="form-control"
						name="resources">
						<option>Select a Type</option>
						<c:forEach items="${listRes}" var="res">
							<option value="${res.getResourceTypeId()}">${res.getResourceTypeName()}</option>
						</c:forEach>
					</select>
				</div>
			</fieldset>
		</form>


	<div class="container">
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
			
				<br>

				<c:forEach var="allResources" items="${alldata}">
					<div class="col-xs-6 col-sm-4">
						<c:if test= "${allResources.getIsAvailable()==1 && allResources.getIsSuperRoom()==0}" >
							<div class="wrimagecard wrimagecard-topimage dropdown">	
						</c:if>
						<c:if test= "${allResources.getIsAvailable()==0 && allResources.getIsSuperRoom()==0}" >
							<div class="wrimagecardInactive wrimagecard-topimage dropdown thetooltip">	
						  	<span class="tooltiptext">Resource is Unavailable</span>
						</c:if>								
						<c:if test= "${allResources.getIsSuperRoom()==1 && allResources.getIsAvailable()==1}" >
							<div class="wrimagecardSuper wrimagecard-topimage dropdown">	
						</c:if>
						<c:if test= "${allResources.getIsSuperRoom()==1 && allResources.getIsAvailable()==0}" >
							<div class="wrimagecardInactiveSuper wrimagecard-topimage dropdown thetooltip">	
							<span class="tooltiptext">Resource is Unavailable</span>
						</c:if>
						
							<div class="wrimagecard-topimage_header">
								<h4 class="filterResourceName">${allResources.getResourceName()}</h4>

								<!-- Hidden tag that will provide the resource Id on the page -->
								<p style="display: none" class="filterResourceId">${allResources.getResourceId()}</p>

								<div class="dropdown-content">
									<table>
										
										<spring:url value="/resources/images" var="images" /> 
										
										<!-- Loops through all features to choose which belong to this resource -->
										<c:forEach var="feat" items="${featData}">
											<c:if
												test="${fn:containsIgnoreCase(feat.getResourceID(),allResources.getResourceId())}">

												<c:if
													test="${fn:containsIgnoreCase(feat.getFeatureName(), 'Projector')}">
													<tr>
														<td><img class="featureImg" alt="Image result for projector icon png" src="${images}/projector.png" />
														</td>
														<td>${feat.getQuantity()}</td>
													</tr>
												</c:if>

												<c:if
													test="${fn:containsIgnoreCase(feat.getFeatureName(), 'Desktop')}">
													<tr>
														<td><img class="featureImg"
															src="${images}/desktop.jpg"
															alt="Image result for tv icon png"></td>
														<td>${feat.getQuantity()}</td>
													</tr>
												</c:if>

												<c:if
													test="${fn:containsIgnoreCase(feat.getFeatureName(), 'Chair')}">
													<tr>
														<td><img class="featureImg"
															src="${images}/chair.png"
															alt="Chair icon"></td>
														<td>${feat.getQuantity()}</td>
													</tr>
												</c:if>
											
												<c:if
													test="${fn:containsIgnoreCase(feat.getFeatureName(), 'Printer')}">
													<tr>
														<td><img class="featureImg"
															src="${images}/printer.jpg"
															alt="Image result for printer icon png"></td>
														<td>${feat.getQuantity()}</td>
													</tr>
												</c:if>

												<c:if
													test="${fn:containsIgnoreCase(feat.getFeatureName(), 'Whiteboard')}">
													<tr>
														<td><img class="featureImg"
															src="${images}/whiteboard.png"
															alt="Image result for whiteboard icon png"></td>
														<td>${feat.getQuantity()}</td>
													</tr>
												</c:if>
												
																								<c:if
													test="${fn:containsIgnoreCase(feat.getFeatureName(), 'TV')}">
													<tr>
														<td><img class="featureImg"
															src="${images}/tv.jpg"
															alt="Image result for TV icon png"></td>
														<td>${feat.getQuantity()}</td>
													</tr>
												</c:if>
											</c:if>
										</c:forEach>
									</table>
								</div>
								<c:if
									test="${fn:containsIgnoreCase(allResources.getResourceName(),'scrum')}">
									<img class="roomImg" src="${images}/scrum1.png" />
								</c:if>
								<c:if
									test="${fn:containsIgnoreCase(allResources.getResourceName(), 'conference')}">
									<img class="roomImg" src="${images}/video converence room.jpg" />
									
								</c:if>
								<c:if
									test="${fn:containsIgnoreCase(allResources.getResourceName(), 'training')}">
									<img class="roomImg" src="${images}/training.png" />
								</c:if>
								<c:if
									test="${fn:containsIgnoreCase(allResources.getResourceName(), 'board')}">
									<i class="fa fa-users"></i>
								</c:if>
								<c:if
									test="${fn:containsIgnoreCase(allResources.getResourceName(), 'break')}">
									<img class="roomImg" src="${images}/break room.png" />
								</c:if>
								<c:if
									test="${fn:containsIgnoreCase(allResources.getResourceName(), 'recreation')}">
									<img class="roomImg" src="${images}/recreation1.png" />

								</c:if>
							</div>
						
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<footer class="footer" id="footer"">
		<center>
			<p>2018 Syntel, Inc</p>
		</center>

		</footer>

	</div>
</body>
</body>
</html>