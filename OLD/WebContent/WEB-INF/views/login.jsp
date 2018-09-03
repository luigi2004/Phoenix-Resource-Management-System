<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*, org.springframework.web.context.WebApplicationContext,
org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<spring:url value="/resources/css/bootstrap.min.css" var="bootMin"/>
	<spring:url value="/resources/css/login.css" var="loginCSS"/>

	
	<link rel="stylesheet" href="${bootMin}"/>
	<link rel="stylesheet" href="${loginCSS}"/>

	
	
	<spring:url value="resources/js" var="JS"/>
	
	<script src="${JS}/jquery.js"></script>
	<script src="${JS}/bootstrap.js"></script>
	


<title>Login</title>


</head>

<body>
	<div class="login-page">
	  <div class="form" style="opacity=2.5">
	    <img src="resources/images/book-logo.png" alt="logo" style="height:100%; width:100%; padding-bottom:60px; "> 
	    <form class="register-form">
	      <input type="text" placeholder="name"/>
	      <input type="password" placeholder="password"/>
	      <input type="text" placeholder="email address"/>
	      <button>create</button>
	      <p class="message">Already registered? <a href="#">Sign In</a></p>
	    </form>
	      
	 <form action="loginOnUserName" name="loginform" method="post"  onSubmit="validateForm()">
	  <div class="input-group">
	    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
	    <input id="email" type="text" class="form-control" name="userName" placeholder="Username" value='${user.getUserName()}'>
	    	  
	  </div>
	          <br>
	  <div class="input-group">
	    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
	    <input id="password" type="password" class="form-control" name="password" placeholder="Password" value='${user.getPassword()}'>
	  </div>
	
	      
	      <br>
	   
	   
	    <input class="btn btn-success" type="submit" value="Login" style="width:100px"/>
	    </form>
	      
	      <br>
	        <img src="resources/images/syntrans.png" alt="logo" style="height:50%; width:50%; padding-bottom:60px; "> 
	    
	  </div>
	</div>
</body>
</html>