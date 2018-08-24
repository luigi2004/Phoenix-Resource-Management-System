<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html>
<html>
<head>

<style type="text/css">
   body { background: lightblue !important; } /* Adding !important forces the browser to overwrite the default style applied by Bootstrap */
</style>

	<title>Register Now!</title>
	<link rel="stylesheet" href="../resources/fontawesome/css/all.css">
	<script src="../resources/js/jquery.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
	<script src="../resources/js/jquery.js"></script>
	<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="../resources/css/visitorRegistrationForm.css">
	<!-- script src="../resources/js/visitor30SecRedirect.js"></script-->
	
</head>


<body class="container"  onload="startTime()">
	<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="page-header">
				<h1 class="text-center">
					Welcome To Phoenix!
				</h1>
			</div>
		</div>
	</div>
</div>

	<section id="visitor_form_section" >
		<form id="registration_form" action ="RegisterVisitor" method="post">
			
			<!-- MY STUFF -->

<div class="form-group">
	<div class="row">
		<div class="col-md-2">
		</div>
		<div class="col-md-4">
		<input type="text" class="form-control text-center input-lg" name="visitorFirstName" placeholder="First Name" required/>
		</div>
		<div class="col-md-4">
		<input type="text" class="form-control text-center input-lg" name="visitorLastName" placeholder="Last Name" required/>
		</div>
		<div class="col-md-2">
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
		</div>
		<div class="col-md-4">
		<input type="email" class="form-control text-center input-lg" name="visitorEmail" placeholder="Email"/>
		</div>
		<div class="col-md-4">
		<input type="tel" class="form-control text-center input-lg" name="visitorPhone" placeholder="Phone" required/>
		</div>
		<div class="col-md-2">
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
		</div>
		<div class="col-md-4">
		<input type="text" class="form-control text-center input-lg" name="visitorVisitingName" placeholder="Host"/>
		</div>
		<div class="col-md-4">
		<input type="text" class="form-control text-center input-lg" name="visitorCompanyName" placeholder="Company"/>
		</div>
		<div class="col-md-2">
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
		</div>
		<div class="col-md-8">
		<textarea  class=" form_text_area form-control" rows="2" name="visitorVisitPurpose" placeholder="Reason for visit" required></textarea>
		</div>
		<div class="col-md-2">
		</div>
	</div>
	<div class="row">
		<div class="col-md-1 col-md-offset-2 col-lg-1 col-lg-offset-2">
			 
			<button type="button" class="btn-lg btn-danger btn pull-left">
				Cancel
			</button>
		</div>
		<div class="col-md-1 col-md-offset-6 col-lg-1 col-lg-offset-6">
			 
			<button type="button" class="btn-lg btn-success btn pull-right">
				 Next 
			</button>
		</div>
	</div>
</div>

<!-- END OF MY STUFF -->

			
			<input type="submit" id="submit_btn" >
			
		</form>
	</section>
	
	<script>
		
	    
		$('#other').hide();
		$('#submit_btn').hide();
		$("#form_btn").hide(); //hide form submit button; the right arrow button uses this button to triger the submission of the form
		$('input[name="visitorCompanyName"]').change(function(){
		    if ($('#option3').is(':checked')) {
		        $('#otherCompanyName').show();
		    } else {
		        $('#otherCompanyName').hide();
		    }
		});
		
		function submitForm(){
			
			$("#otherCompanyName").prop('required', false);
			
			if($("#otherCompanyName").val().length == 0  && $("#option3").is(':checked')){
				$("#otherCompanyName").prop('required', true);
			}//if Ends 
			
			$("#submit_btn").click();
		};
		
		function startTime() {
		    var today = new Date();
		    var h = today.getHours();
		    var m = today.getMinutes();
		    var s = today.getSeconds();
		    m = checkTime(m);
		    s = checkTime(s);
		    document.getElementById('timer').innerHTML =
		    h + ":" + m + ":" + s;
		    var t = setTimeout(startTime, 500);
		}
		function checkTime(i) {
		    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
		    return i;
		}
	</script>
	
	
</body>
</html>