<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.dropbtn {
    background-color: #4CAF50;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f1f1f1;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}
img{

margin-top: 0px;
width: 25px;
height: 25px;



}

input:hover[type="submit"]{
	
}

table,td{
padding: 5px;
text-align: center;
border: 1px solid black;
}

.dropdown-content a:hover {background-color: #ddd;}

.dropdown:hover .dropdown-content {display: block;}

.dropdown:hover .dropbtn {background-color: #3e8e41;}

</style>
</head>
<body>
<div class="dropdown">

  	<input type="submit" class="dropbtn" name="resourceButton" id="resourceButton" value="SCRUM 1"
  	/>

  <div class="dropdown-content">
  <table>
  <tr>
  	<th>Feature</th>
  	<th>Quantity</th>
  </tr>
  <c:forEach var="feat" items="${featData}">

		
	<c:set var = "room"  value="SCRUM 1"/>
	<c:set var = "Projector"  value="Projector"/>
	<c:set var = "DesktopComputers"  value="Desktop Computers"/>
	<c:set var = "Chair"  value="Chair"/>
	<c:set var = "Phone" value="Phone"/>
	<c:set var = "VideoCamera" value="Video Camera"/>
	<c:set var = "Printer" value="Printer"/>

 	
	<c:if test="${feat.getResourceName() == room}">

		<c:choose>
			<c:when test="${feat.getFeatureName() == Projector}">
				<tr>
					<td><img class="irc_mi" src="http://icons.iconarchive.com/icons/iconsmind/outline/256/Projector-icon.png" 
						alt="Image result for projector icon png"></td>
					<td>${feat.getQuantity()}</td>

				</tr>
			
			</c:when>
		
			<c:when test="${feat.getFeatureName() == DesktopComputers}">
				<tr>
					<td><img class="irc_mi" src="https://png.icons8.com/ios/1600/tv.png" 
						alt="Image result for tv icon png"></td>
					<td>${feat.getQuantity()}</td>
				</tr>
			</c:when>
			
			<c:when test="${feat.getFeatureName() == Chair}">
				<tr>
					<td><img class="irc_mi" src="https://image.flaticon.com/icons/png/512/60/60899.png" 
   						 alt="Chair icon"></td>
					<td>${feat.getQuantity()}</td>
				</tr>
			</c:when>
			
			<c:when test="${feat.getFeatureName() == Phone}">
				<tr>
					<td><img class="irc_mi" src="http://www.stickpng.com/assets/images/5a4525cd546ddca7e1fcbc84.png" 
							 alt="Image result for phone icon png"></td>
					<td>${feat.getQuantity()}</td>
				</tr>
			</c:when>
			
			<c:when test="${feat.getFeatureName() == VideoCamera}">
				<tr>
					<td><img class="irc_mi" src="https://cdn1.iconfinder.com/data/icons/office-22/48/video-conference-512.png"
 							 alt="Image result for video conference camera icon png"></td>
					<td>${feat.getQuantity()}</td>
				</tr>
			</c:when>
			
			<c:when test="${feat.getFeatureName() == Printer}">
				<tr>
					<td><img class="irc_mi" src="https://cdn1.iconfinder.com/data/icons/education-set-4/512/print-512.png" 
						alt="Image result for printer icon png"></td>
					<td>${feat.getQuantity()}</td>
				</tr>
			</c:when>
		</c:choose>
	
	
	
	</c:if>
	
  </c:forEach>
</table>
  </div>
</div>	
	
</body>
</html>