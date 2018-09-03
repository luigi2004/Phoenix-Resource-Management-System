<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Select a room</h1>
	<p id="type">${type}</p>
	<form method="post" action="roomDesc">
		<c:forEach var="room" items="${rooms}">
			<input type="radio" name="room" value='${room.getResourceId()}'> ${room.getResourceName()} <br>
			<ul style="margin:0">
				<c:forEach var="feature" items="${featureMap.get(room)}">
					<li> Name: ${feature.getFeatureTypeName()}</li>
					<ul><li> Desc:${feature.getFeatureTypeDescription()}</li>
					<li> Qty: ${quantityMap.get(feature).getQuantity()}</li></ul>
				</c:forEach>
			</ul>
		</c:forEach>
	</form>	
</body>
</html>