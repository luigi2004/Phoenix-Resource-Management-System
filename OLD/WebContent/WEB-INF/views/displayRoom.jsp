<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 id="roomName">${room.getResourceName()}</h1>
	<p id="resourceId" style="display:none">${room.getResourceId()}</p>
	<br>
	Room #: ${room.getResourceRoomNumber()} <br>
	Description: ${room.getResourceDescription()} <br>
	Features: <ul style="margin:0">
				<c:forEach var="feature" items="${featureMap}">
					<li> Name: ${feature.key.getFeatureTypeName()}</li>
					<ul><li> Desc:${feature.key.getFeatureTypeDescription()}</li>
					<li> Qty: ${feature.value.getQuantity()}</li></ul>
				</c:forEach>
			</ul>
			
		<form action="types" style="content-align:center; text-align:center">
			<button id="backButton" type="submit">Go Back</button>
		</form>
</body>
</html>