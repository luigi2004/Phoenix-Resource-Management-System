<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Visitor Check Out</title>
	<link rel="stylesheet" href="../resources/fontawesome/css/all.css">
	<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="../resources/css/visitorViews.css">
	<script src="../resources/js/jquery.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
	<script src="../resources/js/jquery.js"></script>
	<script src="../resources/js/visitor30SecRedirect.js"></script>
</head>

<body class="container text-center">
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

			<a href="/Phoenix_Resource_Management_System/Visitor/Home"
				class="pull-left fa_left_btn"> <span
				class="fas fa-arrow-alt-circle-left  fa-4x text-center"></span>
			</a> <a onclick="submitForm()" class="fa_right_btn pull-right">
			 <span class="fas fa-arrow-alt-circle-right fa-4x text-center"></span>
			</a>
		</div>
	</section>
	<section class="container">
		<form action="CheckOut" method="post">
			<div class="form-group">
				<input type="text" class="form-control input-lg text-center" name="bid"
					placeholder="Enter Badge ID">
			</div>
			<h3 class="text-center">OR</h3>
			<div class="form-group">
				<input type="text" class="form-control input-lg text-center" name="phone"
					placeholder="Enter Phone">
			</div>
			<br>
			<div class="form-group">
				<button class="btn btn-lg btn-danger btn-block text-center main_btn"
					id="checkout_btn">
					<span class="fas fa-sign-out-alt fa-3x text-center"><h5
							class="btn_string">
							<b>Check Out</b>
						</h5> </span>
				</button>
			</div>
		</form>
	</section>
	<script>
		function submitForm() {
			$("#checkout_btn").click();
		}
	</script>
</body>
</html>