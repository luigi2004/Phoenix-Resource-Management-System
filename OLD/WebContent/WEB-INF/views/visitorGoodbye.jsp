<%@page import="rms.dao.UsersJdbcTemplate"%>
<%@page import="rms.model.Users"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Visitor Goodbye</title>
	<link rel="stylesheet" href="../resources/fontawesome/css/all.css">
	<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="../resources/css/visitorViews.css">
	<script src="../resources/js/anime.min.js"></script>
	<script src="../resources/js/bootstrap.js"></script>
	<script src="../resources/js/jquery.js"></script>
	<script src="../resources/js/visitor5SecRedirect.js"></script>
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
	<section class="nav"></section>
	<section class="container">
		<div class="section_block welcome-goodbye-block">
			<h1 class="message-text">Thanks For Visiting Syntel,</h1>
			<h1 class="visitor-name">
				<c:out value="${full_name}"/>!
			</h1>
		</div>
	</section>
</body>
</html>

