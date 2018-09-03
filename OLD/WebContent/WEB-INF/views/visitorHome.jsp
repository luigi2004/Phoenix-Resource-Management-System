<%@page import="rms.dao.UsersJdbcTemplate"%>
<%@page import="rms.model.Users"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Visitor Home</title>
	<link rel="stylesheet" href="../resources/fontawesome/css/all.css">
	<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="../resources/css/visitorHome.css">
	<script src="../resources/js/anime.min.js"></script>
	<script src="../resources/js/bootstrap.js"></script>
	<script src="../resources/js/jquery.js"></script>
	<script src="../resources/js/custom_anime.js"></script>
	
	<% response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
	response.addHeader("Pragma", "no-cache"); 
	response.addDateHeader ("Expires", 0); %>
	<script type="text/javascript">
	
		$(document).ready( function() {
			
			
			anime.timeline({
				loop : false
			}).add({
				targets : '.ml15 .word',
				scale : [ 24, 1 ],
				opacity : [ 0, 1 ],
				easing : "easeOutCirc",
				duration : 900,
				delay : function(el, i) {
					return 120 * i;
				}
			});
			var title = $("#title");
			var checkout= $("#check_out_block");
			var checkin= $("#check_in_block");
			
			smoothTransition(checkout, checkin, title);
			smoothTransition(checkin, checkout, title);
			
		});
		
		
	</script>
</head>


<body id="home-page-body" class="container-fluid text-center">
   
	<header  class="container text-center" id="title" >
				<div class="ml15 pull-center">
				  
				  <h1>
					  <span class="word">W </span> 
					  <span class="word">e </span> 
					  <span class="word">l </span>
					  <span class="word">c </span>
					  <span class="word">o </span>
					  <span class="word">m </span>
					  <span class="word welcome">e </span>
					  <span class="word to">To </span>
					  <span class="word ">P </span> 
					  <span class="word ">h </span>
					  <span class="word ">o </span>
					  <span class="word state">e </span>
					  <span class="word ">n </span>
					  <span class="word ">i </span>
					  <span class="word ">x</span>
				  </h1>
				  <p class="word logo pull-center">
				  	<img src="../resources/images/logo.png"/> 
				  </p>
				</div>
				
				 
	</header>

	<section class="container"> 

		<!--This div will hold a registration button; when clicked it will open the registration form-->
		
		<div class="container section_block">
		
			<form id="check_in_block" action="/Phoenix_Resource_Management_System/Visitor/Registration" class="card section_content text-center" id="check_in_block">
				<button  class="btn btn-lg btn-success btn-block text-center main_btn"  id="checkin_btn">
					<span class="fas fa-sign-in-alt fa-3x"><h5 class="btn_string"><b>Check in</b></h5></span>
					
				</button>
				
			</form>
			
			<form  id="check_out_block" action="/Phoenix_Resource_Management_System/Visitor/COForm" class="card section_content text-center" >
				
				<button  class="btn btn-lg btn-danger btn-block text-center main_btn" id="checkout_btn">
					<span class="fas fa-sign-out-alt fa-3x text-center"><h5 class="btn_string"> <b>Check out</b></h5></span>			
					
			    </button>
					
			</form>
				
		</div>
	</section>

</body>
</html>