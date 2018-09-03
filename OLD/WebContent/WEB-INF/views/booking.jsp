<%@page import="rms.dao.*, rms.model.*, rms.services.*"%>
    <%@page
	import="java.util.*, org.springframework.web.context.WebApplicationContext,
org.springframework.web.context.support.WebApplicationContextUtils"%>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

            <!DOCTYPE html>

            <html>

            <head>
                <meta charset="utf-8" />
                <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1">
                <title>Booking</title>

                <spring:url value="/resources/css/bootstrap.min.css" var="bootMin" />
                <spring:url value="/resources/css/booking.css" var="bookingCSS" />
                <spring:url value="/resources/css/daterangepicker.css" var="daterangepicker" />
                <spring:url value="/resources/css/fullcalendar.css" var="fullCal" />

                <link rel="stylesheet" href="${bootMin}" />
                <link rel="stylesheet" href="${bookingCSS}" />
                <link rel="stylesheet" href="${daterangepicker}" />
                <link rel="stylesheet" href="${fullCal}" />

                <spring:url value="/resources/js" var="JS" />

                <script src="${JS}/jquery.js">
                </script>
                <script src="${JS}/moment.min.js">
                </script>
                <script src="${JS}/bootstrap.js">
                </script>
                <script src="${JS}/daterangepicker.js">
                </script>
                <script src="${JS}/fullcalendar.js">
                </script>

            </head>

            <body>
				<%  
					if(null == session.getAttribute("userId")){
				    	response.sendRedirect(getServletContext().getRealPath("/")+"/");
				    	return;
					}
				%>
				
                <div class="container" id="allPage">

                    <!-- Default Values provided. these are the id and name of the currently selected resource
					updated in the select event of the fullCalendar -->
                    <p id="pageResourceId" style="display: none">1001</p>
                    <p id="pageResourceName" style="display: none">SCRUM 1</p>
                    <p id="pageUserId" style="display: none">	 
                     <%  
	  	  				out.write(session.getAttribute( "userId").toString()); 
	  				%></p>

                    <div class="container-fluid" id="rows">
                                        
	                   <nav class="navbar navbar-default navbar-static-top">
	                        <div class="container-fluid">
	                            <spring:url value="/resources/images" var="images" /> 
	                            <img class="navbar-brand navbar-right" id="syntel-logo" src="${images}/syntel-logo.png" />
	
	                            <div class="navbar-header">
	                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	                                    <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
	                                </button>
	                                <a class="navbar-brand" href="dashboard">
	                                    <spring:url value="/resources/images" var="images" /> <img id="book-logo" src="${images}/book-logo.png" />
	                                </a>
			                            <span id="welcomeUser">Welcome <% out.print(session.getAttribute("userName")); %></span>
	
	                            </div>
	                            
	                            <div class="collapse navbar-collapse" id="myNavbar">
	                                <ul class="nav navbar-nav navbar-right">
	                                    <li><a href="logout">Logout</a></li>
	                                </ul>
	                            </div>
	                        </div>
	                    </nav>
                    
                        <div class="row">
                            <div class="col-lg-4">
                                <div class="alert alert-warning fade" id="roomAlert">
                                    <strong>Please Choose a Room Before Selecting</strong>
                                </div>

                                <div class="panel">
                                    <div class="panel-body">

                                        <iframe src="showAllResources"> </iframe>

                                    </div>
                                </div>
                                <!--Panel-->
                            </div>
                            <!--Col-->
                            <div class="col-lg-8">
                                <div class="panel">

                                    <div class="panel-body">
                                        <div id="dispCal"></div>
                                    </div>
                                </div>
                                <!--Panel-->
                            </div>
                            <!--Col-->

                        </div>
                        <!--Row-->
                    </div>

                </div>
                <!--All Page-->

                <!--Edit Event Modal -->
                <div class="modal fade" id="changeEventModal" tabindex="-1" role="dialog" aria-labelledby="eventModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header" id="eventChangeHeader">
                                <h5 class="modal-title" id="exampleModalLabel">Edit or Delete Event</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body" id="eventChangeBody">

                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class='glyphicon glyphicon-calendar'></span> <span class="input-group-text">Booking ID</span>
                                    </div>

                                    <input class="form-control input-md" type="text" id="bookingId" name="bookingId" readonly="readonly" />
                                </div>
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class='glyphicon glyphicon-calendar'></span> <span class="input-group-text">Room</span>
                                    </div>
                                    <input class="form-control input-md" type="text" id="editRoom" name="editRoom" readonly="readonly" />
                                </div>
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class='glyphicon glyphicon-calendar'></span> <span class="input-group-text">Date</span>
                                    </div>
                                    <input class="form-control input-md" type="date" placeholder="DD-MM" id="editDate" name="editDate" required />
                                </div>

                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class='glyphicon glyphicon-time'></span> <span class="input-group-text">From</span>
                                    </div>
                                    <input class="form-control input-md" type="time" placeholder="HH:MM" id="editTimeFrom" name="editTimeFrom" min="9:00" max="17:00" required>
                                </div>

                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class='glyphicon glyphicon-time'></span> <span class="input-group-text">To</span>
                                    </div>
                                    <input class="form-control input-md" type="time" placeholder="HH:MM" id="editTimeTo" name="editTimeTo" max="17:00" min="9:00" required>
                                </div>

                            </div>
                            <div class="modal-footer">
                            	<div style="text-align:center;" class="alert alert-danger" roll="alert" id="editAlert">
                            		Changes clash with prexisting booking or the end time is before the start time.
                            		</div>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="button" data-dismiss="modal" class="btn btn-primary" id="editButton">Save Changes</button>

                                <button type="button" data-dismiss="modal" class="btn btn-danger" id="deleteButton">Delete</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Add event modal for weekly view-->
                <div class="modal fade" id="addEventByWeekModal" tabindex="-1" role="dialog" aria-labelledby="eventModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header" id="addWeeklyHeader">
                                <h5 class="modal-title" id="addEventLabel">Add Event</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>

                            <div class="modal-body" id="eventAddBody">
                                <!-- Room -->
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class='glyphicon glyphicon-calendar'></span> <span class="input-group-text">Room</span>
                                    </div>
                                    <input class="form-control input-md" type="text" id="room" name="room" readonly="readonly" />
                                </div>

                                <!-- Date -->
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class='glyphicon glyphicon-calendar'></span> <span class="input-group-text">Date</span>
                                    </div>
                                    <input class="form-control input-md" type="date" placeholder="DD-MM" id="date" name="date" readonly="readonly" required />
                                </div>
                                <!-- Start time -->
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class='glyphicon glyphicon-time'></span> <span class="input-group-text">From</span>
                                    </div>
                                    <input class="form-control input-md" type="time" placeholder="HH:MM" id="timeFrom" name="timeFrom" readonly="readonly" required>
                                </div>

                                <!-- Stop time -->
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class='glyphicon glyphicon-time'></span> <span class="input-group-text">To</span>
                                    </div>
                                    <input class="form-control input-md" type="time" placeholder="HH:MM" id="timeTo" name="timeTo" readonly="readonly" required>
                                </div>
                                <!-- Weekly repeats -->
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class='glyphicon glyphicon-repeat'></span> <span class="input-group-text"> Weekly Repeats </span>
                                    </div>
                                    <input class="form-control input-md" type="number" id="weeklyRep" min="0" max="4" value="0" name="weeklyRep" onkeydown="return false;" required />
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                    <button type='button' data-dismiss="modal" class="btn btn-primary" id="addButton">Add Event</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Add event modal for daily view-->
                <div class="modal fade" id="addEventByDayModal" tabindex="-1" role="dialog" aria-labelledby="eventModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header" id="addDayHeader">
                                <h5 class="modal-title" id="addEventLabel">Add Event</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>

                            <div class="modal-body" id="eventAddBody">
                                <!-- Room -->
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class='glyphicon glyphicon-calendar'></span> <span class="input-group-text">Room</span>
                                    </div>
                                    <input class="form-control input-md" type="text" id="room" name="room" readonly="readonly" />
                                </div>

                                <!-- Start time -->
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class='glyphicon glyphicon-calendar'></span> <span class="input-group-text">Date</span>
                                    </div>
                                    <input class="form-control input-md" type="date" placeholder="DD-MM" id="date" name="date" required readonly="readonly" />
                                </div>

                                <!-- From time -->
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class='glyphicon glyphicon-time'></span> <span class="input-group-text">From</span>
                                    </div>
                                    <input class="form-control input-md" type="time" placeholder="HH:MM" id="timeFrom" name="timeFrom" readonly="readonly" required>
                                </div>

                                <!-- To time -->
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class='glyphicon glyphicon-time'></span> <span class="input-group-text">To</span>
                                    </div>
                                    <input class="form-control input-md" type="time" placeholder="HH:MM" id="timeTo" name="timeTo" readonly="readonly" required>
                                </div>

                                <!-- Day repeats -->
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class='glyphicon glyphicon-repeat'></span> <span class="input-group-text"> Daily Repeats </span>
                                    </div>
                                    <div class="form-control input-md">
                                        <!-- Recurrence validation depends on the ids of the checkboxes -->
                                        <div class="notExtended input-group-addon" style="background: none; border: none;">
                                            <input type="checkbox" id="d0" value="Sunday"> Su
                                        </div>
                                        <div class="notExtended input-group-addon" style="background: none; border: none;">
                                            <input type="checkbox" id="d1" value="Monday"> M
                                        </div>
                                        <div class="notExtended input-group-addon" style="background: none; border: none;">
                                            <input type="checkbox" id="d2" value="Tuesday"> Tu
                                        </div>
                                        <div class="notExtended input-group-addon" style="background: none; border: none;">
                                            <input type="checkbox" id="d3" value="Wednesday"> W
                                        </div>
                                        <div class="notExtended input-group-addon" style="background: none; border: none;">
                                            <input type="checkbox" id="d4" value="Thursday"> Th
                                        </div>
                                        <div class="notExtended input-group-addon" style="background: none; border: none;">
                                            <input type="checkbox" id="d5" value="Friday"> F
                                        </div>
                                        <div class="notExtended input-group-addon" style="background: none; border: none;">
                                            <input type="checkbox" id="d6" value="Saturday"> Sa
                                        </div>
                                    </div>

                                </div>



                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                    <button type='button' data-dismiss="modal" class="btn btn-primary" id="addButton">Add Event</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <script>
                    var active = "view";

                    $(document).ready(
                        function() {
								                                                	
                            //dont show alert on first page load.
                            $("#roomAlert").hide();
                            $("#editAlert").hide();

                            //tell user to choose a room on mouseup on calendar. 
                            $("#dispCal").mouseup(function(event) {

                                console.log(event.target.className);

                                var selectable = $('#dispCal').fullCalendar('option', 'selectable');

                                if (!selectable && event.target.type != "button" && !event.target.className.includes("toolbar") &&
                                    !event.target.className.includes("bg") && !event.target.className.includes("time") &&
                                    !event.target.className.includes("title")) {

                                    $("#roomAlert").fadeTo(4000, 500).slideUp(500, function() {
                                        $("#roomAlert").slideUp(500);
                                    });
                                }
                            });

                            //the initial rendering of the full calendar. 
                            $('#dispCal').fullCalendar({
                                // Limit calendar to show only two months from now
                                validRange: function(currentDate) {
                                    return {
                                        start: currentDate.clone(),
                                        end: currentDate.clone().add(2,
                                            'months')
                                    };
                                },
                                //what buttons show up on the header
                                header: {
                                    left: 'prev,next today',
                                    center: 'title',
                                    right: 'agendaWeek,agendaDay'
                                },
                                themeSystem: 'bootstrap3',
                                minTime: "09:00:00", //the times that are visible on the calendar itself. 
                                maxTime: "17:00:00",
                                height:'parent',//the css height of the calendar
                                defaultView: 'agendaWeek',
                                selectable: false, //initially cannot make event selections
                                selectConstraint: {
                                    start: moment().startOf('day'),
                                    end: moment().startOf('day').add(6,
                                        'months'),
                                },
                                //create tooltip on mouseover of event. 
                                eventMouseover: function(calEvent, jsEvent) {
                                    var tooltip = '<div class="tooltipevent" style="background:' + calEvent.backgroundColor + ';color:white;width:150px;position:absolute;z-index:10001;">' +
                                        calEvent.title + "<br/>" + $.fullCalendar.formatDate(calEvent.start, "hh:mm") + " - " + $.fullCalendar.formatDate(calEvent.end, "hh:mm") + "<br/>Click to edit or delete" + '</div>';
                                    var $tooltip = $(tooltip).appendTo('body');

                                    $(this).mouseover(function(e) {
                                        $(this).css('z-index', 10000);
                                        $tooltip.fadeIn('500');
                                        $tooltip.fadeTo('10', 1.9);
                                    }).mousemove(function(e) {
                                        $tooltip.css('top', e.pageY + 10);
                                        $tooltip.css('left', e.pageX + 20);
                                    });
                                },
                                //for the tooltip. delete it on mouseout of event. 
                                eventMouseout: function(calEvent, jsEvent) {
                                    $(this).css('z-index', 8);
                                    $('.tooltipevent').remove();
                                },


                                // Handle creation of an event
                                select: function(startDate, endDate) {

                                    console.log("Select Event");

									// Don't allow bookings across multiple days
                                    if(startDate.day() != endDate.day()){
                                    	$('#dispCal').fullCalendar('unselect');
                                    	return;
                                    }
                                    
                                    // Find the room picked
                                    var name = $("#pageResourceName").val();

                                    // Fill the views by form type
                                    var viewType = $('#dispCal').fullCalendar('getView').name;
                                    if (viewType === "agendaWeek") {
                                        // Fill in the date
                                        $("#addEventByWeekModal  #date").val($.fullCalendar.formatDate(startDate, "YYYY-MM-DD"));
                                        $("#addEventByWeekModal  #timeFrom").val(startDate.format("HH:mm"));
                                        $("#addEventByWeekModal  #timeTo").val(endDate.format("HH:mm"));
                                        $("#addEventByWeekModal  #room").val(name);

                                        if (name.toLowerCase().includes("scrum"))
                                            $('#addWeeklyHeader').css("background-color","FireBrick");
                                        else if (name.toLowerCase().includes("conference"))
                                            $('#addWeeklyHeader').css("background-color","DarkCyan");
                                        else if (name.toLowerCase().includes("board"))
                                            $('#addWeeklyHeader').css("background-color","OrangeRed");
                                        else if (name.toLowerCase().includes("recreation"))
                                            $('#addWeeklyHeader').css("background-color","Chocolate");
                                        else if (name.toLowerCase().includes("train"))
                                            $('#addWeeklyHeader').css("background-color","OliveDrab");
                                        else if (name.toLowerCase().includes("break"))
                                            $('#addWeeklyHeader').css("background-color","SlateBlue");
                                        else
                                        	$('#addWeeklyHeader').css("background-color","Grey");

                                        
                                        // Ask the server to check for conflicting bookings							
                                        $.ajax({
                                            url: "checkConflicts",

                                            // Pass parameters
                                            data: {
                                                "type": "week",
                                                "id": $("#pageResourceId").val(),
                                                "date": $("#addEventByWeekModal #date").val(),
                                                "timeTo": $("#addEventByWeekModal #timeTo").val(),
                                                "timeFrom": $("#addEventByWeekModal #timeFrom").val()
                                            },
											
                                            // Server responds with the maximum number of weekly repeats allowbale
                                            // without causing a booking conflict
                                            success: function(result) {
                                                //console.log("Conflict response: " + result);
                                                // Set max of weekly repeat input box according to server
                                                $("#addEventByWeekModal #weeklyRep").attr("max", result);
                                                $("#addEventByWeekModal #weeklyRep").val(0);

                                            },

                                            fail: function(result) {
                                                console.log("Failed conflict check service");
                                                console.log(result);
                                            }
                                        });

                                        // Display the modal
                                        $("#addEventByWeekModal").modal("show");
                                    } else if (viewType === "agendaDay") {
                                    	// Modify daily repeat check boxes
                                        var currentDay = startDate.day();
                                        for (var i = 0; i < 7; i++) {
                                            // Disable and uncheck previous days
                                            if (i < currentDay) {
                                                $("#d" + i).attr("disabled", true);
                                                $("#d" + i).prop("checked", false);
                                                // Enable uncheck future days
                                            } else if (i > currentDay) {
                                                $("#d" + i).attr("disabled", false);
                                                $("#d" + i).prop("checked", false);
                                                // Check and disable current day
                                            } else {
                                                $("#d" + i).prop("checked", true);
                                                $("#d" + i).attr("disabled", true);
                                            }
                                        }


                                        // Fill in inputs of the modal
                                        $("#addEventByDayModal  #date").val($.fullCalendar.formatDate(startDate, "YYYY-MM-DD"));
                                        $("#addEventByDayModal  #timeFrom").val(startDate.format("HH:mm"));
                                        $("#addEventByDayModal  #timeTo").val(endDate.format("HH:mm"));
                                        $("#addEventByDayModal  #room").val(name);
                                        
                                        if (name.toLowerCase().includes("scrum"))
                                            $('#addDayHeader').css("background-color","FireBrick");
                                        else if (name.toLowerCase().includes("conference"))
                                            $('#addDayHeader').css("background-color","DarkCyan");
                                        else if (name.toLowerCase().includes("board"))
                                            $('#addDayHeader').css("background-color","OrangeRed");
                                        else if (name.toLowerCase().includes("recreation"))
                                            $('#addDayHeader').css("background-color","Chocolate");
                                        else if (name.toLowerCase().includes("train"))
                                            $('#addDayHeader').css("background-color","OliveDrab");
                                        else if (name.toLowerCase().includes("break"))
                                            $('#addDayHeader').css("background-color","SlateBlue");
                                        else
                                            $('#addDayHeader').css("background-color","Grey");

                                        // Ask the server what daily repeats we can offer without conflixting with
                                        // an existing booking
                                        $.ajax({
                                            url: "checkConflicts",

                                            // Pass parameters
                                            data: {
                                                "type": "day",
                                                "id": $("#pageResourceId").val(),
                                                "date": $("#addEventByDayModal #date").val(),
                                                "timeTo": $("#addEventByDayModal #timeTo").val(),
                                                "timeFrom": $("#addEventByDayModal  #timeFrom").val()
                                            },

                                            // Server sends comma seperated booleans. The boolean is true
                                            // there is no conflict that day and false if there is a conflicting
                                            // booking that day. The same applies for other days.
                                            success: function(result) {
                                                // Parse the response
                                                //console.log("Conflict response: " + result);
                                                result = result.split(",");

                                                // Uncheck and unlabel day that cause conflicts	
                                                for (var i = 0; i < result.length; i++) {
                                                    if (result[i] === "false") {
                                                        $("#d" + i).attr("disabled", true);
                                                        $("#d" + i).prop("checked", false);
                                                    }
                                                }
                                            },

                                            fail: function(result) {
                                                console.log("Failed conflict check service");
                                                console.log(result);
                                            }
                                        });




                                        $("#addEventByDayModal").modal("show");
                                    }
                                },
                                //when clicking an event set all the data inside the editModal then
                                //open the modal.
                                eventClick: function(calEvent, jsEvent, view) {
                                    $("#editDate").val($.fullCalendar.formatDate(calEvent.start, "YYYY-MM-DD"));
                                    $("#editTimeFrom").val(calEvent.start.format("HH:mm"));
                                    $("#editTimeTo").val(calEvent.end.format("HH:mm"));
                                    $("#editRoom").val(calEvent.title);
                                    $("#bookingId").val(calEvent.id);
                                    $('#eventChangeHeader').css("background-color",calEvent.backgroundColor);
                                    $("#changeEventModal").modal('show');
                                
                                    // Validate prefilled values
                                    var bookingID = $("#bookingId").val();
                                    var date = $("#editDate").val();
                                    var timeTo = $("#editTimeTo").val();
                                    var timeFrom = $("#editTimeFrom").val();
                                    
                                    console.log("Changed");
                                    console.log(bookingID);
                                    console.log(date);
                                    console.log(timeTo);
                                    console.log(timeFrom);
                                    var id = $("#bookingId").val();

                                    $.ajax({
                                    	url: "pleaseCheckMyEdit",
                                    	async: false,
                                        data: {
                                        	"bookingID": bookingID,
                                        	"startTime": timeTo,
                                        	"endTime": timeFrom,
                                        	"date": date
                                        },
                                        success: function(result){
                                        	console.log("Success: " + result);
                                        	if(result === "false"){
                                        		$("#editAlert").show();
                                        	} else {
                                        		$("#editAlert").hide();
                                        	}
                                        	$("#editButton").prop("disabled", result === false);
                                        	
                                        },
                                        fail: function(){
                                        	console.log(result);
                                        } 
                                    });
                                    
                                    $.ajax({
                                    	url: "deleteCheck",
                                    	async: false,
                                        data: {
                                        	bookingId:id
                                        },
                                        success: function(result){
                                        	if(result == "Fail"){
                                        		console.log("deleteCheck Fail");
                                            	$("#deleteButton").prop("disabled", true);
                                            	$("#editButton").prop("disabled", true);
                                        	}else{
                                        		console.log("deleteCheck Success");
                                            	$("#deleteButton").prop("disabled", false);
                                            	$("#editButton").prop("disabled", false);
                                        	} 
                                        },
                                        fail: function(){
                                        	console.log(result);
                                        } 
                                    });
                                },
                                agenda: {
                                    eventLimit: 3, // allow "more" link when too many events
                                },

                                eventOverlap: false,//dont let events sit on top of one another.
                                allDaySlot: false,//dont show the alldayslot at the top of the calendar
                                selectOverlap: false,
                                slotEventOverlap: false,
                            }); //End fullCalendar initial render

                            //on iframe load. 		
                            $('iframe').load(function() {

                                //for clicking on cards of resources.   
                                $("iframe").contents().find(".wrimagecard, .wrimagecard .roomImg, .wrimagecardSuper, .wrimagecardSuper .roomImg").on('click', function(event) {

                                    //set all cards to not be highlighted.	
                                    //need to set all children of the card or they will highlight individually
                                    $("iframe").contents().find(".wrimagecard *").css('border-color', 'black');
                                    $("iframe").contents().find(".wrimagecard").css('border-color', 'black');
                                    $("iframe").contents().find(".wrimagecardSuper *").css('border-color', 'black');
                                    $("iframe").contents().find(".wrimagecardSuper").css('border-color', 'black');

                                    
                                    //set the clicked card to be highlighted
                                    //have to set siblings children and parent bc there are multiple elements in the cards like the title and icon. 
                                    //the user could have clicked any of the smaller elements. 
                                    $(event.target).css('border-color', '#00cd13');
                                    $(event.target.parentElement).css('border-color', '#00cd13');
                                    $(event.target.parentElement.parentElement).css('border-color', '#00cd13');
                                    $(event.target.children).css('border-color', '#00cd13');
                                    $(event.target).siblings().css('border-color', '#00cd13');

                                    //need two in case user clicks on child element vs entire card. 
                                    var possibleId = $(event.target.parentElement).contents().find(".filterResourceId").text();
                                    var possibleId2 = $(event.target.parentElement).contents().siblings(".filterResourceId").text();

                                    var resId = "";

                                    //get the id that was actually there. 
                                    if (possibleId)
                                        resId = possibleId;
                                    else
                                        resId = possibleId2;

                                    //need two in case user clicks on child element vs entire card. 
                                    var possibleName = $(event.target.parentElement).contents().find(".filterResourceName").text();
                                    var possibleName2 = $(event.target.parentElement).contents().siblings(".filterResourceName").text();

                                    var name = "";

                                    //get the id that was actually there. 
                                    if (possibleName)
                                        name = possibleName;
                                    else
                                        name = possibleName2;

                                    //set the page resource Id attribute to be the selected id. 
                                    $("#pageResourceId").val(resId);
                                    $("#pageResourceName").val(name);

                                    //This ajax call filters the calendar by the exact resourceId that was just clicked by the user. 
                                    $.ajax({
                                        url: "getBookingsAsTableByResourceId",

                                        dataType: 'html',

                                        //pass the resource Id. 
                                        data: {
                                            "resourceId": resId
                                        },

                                        success: function(result) {

                                            //remove events from the calendar and rerender with the ajax result
                                            $("#dispCal").fullCalendar('removeEvents');
                                            calRender(result);

                                        },

                                        fail: function(result) {
                                            console.log("Failed get all by type service");
                                            console.log(result);
                                        }
                                    });

                                    //the user can now make selections on the calendar. 
                                    $("#dispCal").fullCalendar('option', {
                                        selectable: true
                                    });

                                });

                                //when the user choose a resource type. 
                                $('iframe').contents().find("select[name='resources']").on('change', function(event) {

                                	//the user can now make selections on the calendar. 
                                    $("#dispCal").fullCalendar('option', {
                                        selectable: false
                                    });
                                	
                                    console.log("chose a type");

                                    var resId = ($('iframe').contents().find("select[name='resources'] option:selected").val());

                                    console.log(resId);

                                    $.ajax({
                                        url: "getBookingsAsTableByType",

                                        dataType: 'html',

                                        data: {
                                            "resourceTypeId": resId
                                        },

                                        success: function(result) {

                                            $("#dispCal").fullCalendar('removeEvents');
                                            calRender(result);


                                        },

                                        fail: function(result) {
                                            console.log("Failed get all by type service");
                                            console.log(result);
                                        }
                                    });

                                    ($('iframe').contents().find("form").submit());


                                });
                            });

                            $('iframe').load(function() {
                                $(this).contents().find("#backButton").on('click',
                                    function(event) {

                                        console.log("clicked go back");

                                        $("#dispCal").fullCalendar('option', {
                                            selectable: false
                                        });

                                        $.ajax({
                                            url: "getAllBookingsAsTable",
                                            dataType: 'html',
                                            success: function(result) {

                                                $("#dispCal").fullCalendar("removeEvents");
                                                calRender(result);

                                            },

                                            fail: function(result) {
                                                console.log("Failed get all service");
                                                console.log(result);
                                            }
                                        });



                                    });
                            });

                            $("#deleteButton").click(function() {
                                var id = $("#bookingId").val();
                                console.log(id);
                                
                                if(!confirm("Are you sure you want to delete booking?")){
                                	return;
                                }

                                $.ajax({
                                    url: "deleteEvent",
                                    success: function(result) {
                                        //remove the event from the calendar
                                        $("#dispCal").fullCalendar('removeEvents', id);

                                    },
                                    fail: function(result) {
                                        console.log(result);
                                    },
                                    data: {
                                        "bookingId": id
                                    }
                                });
                            });
                            
                            
                            // Validate editing events
                            var editChecks = "#editDate, #editTimeTo, #editTimeFrom";
                            $(editChecks).change(
                            	function(){
                            		var bookingID = $("#bookingId").val();
                                    var date = $("#editDate").val();
                                    var timeTo = $("#editTimeTo").val();
                                    var timeFrom = $("#editTimeFrom").val();
                                    
                                    console.log("Changed");
                                    console.log(bookingID);
                                    console.log(date);
                                    console.log(timeTo);
                                    console.log(timeFrom);
                                                                                                            
                                    if(Number(timeFrom.split(":")[0].trim()) < 9){
	                                  	$("#editTimeFrom").val("09:00");
                                    }
                                    
                                    if(Number(timeTo.split(":")[0].trim()) > 17){
	                                  	$("#editTimeTo").val("17:00");
                                    }
                                    
                                    //if minutes are past 5. 
                                    if(Number(timeTo.split(":")[0].trim()) == 17){
                                    	if(Number(timeTo.split(":")[1].trim())>0)
	                                  		$("#editTimeTo").val("17:00");
                                    }
                                    
                                    $.ajax({
                                    	url: "pleaseCheckMyEdit",
                                        data: {
                                        	"bookingID": bookingID,
                                        	"startTime": timeTo,
                                        	"endTime": timeFrom,
                                        	"date": date
                                        },
                                        success: function(result){
                                        	console.log("Success: " + result);
                                        	if(result === "false"){
                                        		$("#editAlert").show();
                                        	} else {
                                        		$("#editAlert").hide();
                                        	}
                                        	$("#editButton").attr("disabled", result === "false");
                                        	
                                        },
                                        fail: function(){
                                        	console.log(result);
                                        } 
                                    });
                            	}		
                            );
                            
                            $("#editButton").click(function() {
                                var bookId = $("#bookingId").val();
                                var date = $("#editDate").val();
                                var timeTo = $("#editTimeTo").val();
                                var timeFrom = $("#editTimeFrom").val();
                                var title = $("#editRoom").val();
                                var userId = $("#pageUserId").text();

                                $.ajax({
                                    url: "updateEvent",
                                    success: function(result) {
                                        $.ajax({
                                            url: "getAllBookingsAsTable",
                                            dataType: 'html',
                                            success: function(result) {

                                                $("#dispCal").fullCalendar('removeEvents');
                                                calRender(result);
                                            },

                                            eventLimit: true, // allow "more" link when too many events

                                        });
                                    },
                                    fail: function(result) {
                                        console.log(result);
                                    },
                                    data: {
                                        "bookingId": bookId,
                                        "date": date,
                                        "timeTo": timeTo,
                                        "timeFrom": timeFrom,
                                        "title": title,
                                        "userId": userId,
                                    }
                                });
                            });


                            $("#addEventByDayModal #addButton").click(
                                function() {
                                    console.log("Adding from day...");

                                    //get fields from the inputs inside the add modal
                                    var date = $("#addEventByDayModal #date").val();
                                    var timeTo = $("#addEventByDayModal #timeTo").val();
                                    var timeFrom = $("#addEventByDayModal #timeFrom").val();
                                    var title = $("#addEventByDayModal #room").val();
                                    var resId = $("#pageResourceId").val();
                                    var userId = $("#pageUserId").text();
                                    var su = $("#addEventByDayModal #d0").prop("checked");
                                    var m = $("#addEventByDayModal #d1").prop("checked");
                                    var tu = $("#addEventByDayModal #d2").prop("checked");
                                    var w = $("#addEventByDayModal #d3").prop("checked");
                                    var th = $("#addEventByDayModal #d4").prop("checked");
                                    var f = $("#addEventByDayModal #d5").prop("checked");
                                    var sa = $("#addEventByDayModal #d6").prop("checked");
                                    var repeats = [su, m, tu, w, th, f, sa];

                                    /*
	          						console.log(date);
	          						console.log(timeTo);
	          						console.log(timeFrom);
	          						console.log(title);
	          						console.log(resId);
	          						console.log("Here" + JSON.stringify(repeats));
			  						*/

                                    $.ajax({
                                        url: "addEvent",
                                        success: function() {
                                            $.ajax({
                                                url: "getBookingsAsTableByResourceId",
                                                dataType: 'html',
                                                success: function(result) {
                                                    //remove events from the calendar and rerender with the ajax result
                                                    $("#dispCal").fullCalendar('removeEvents');
                                                    calRender(result);
                                                },
                                                data: {
                                                    "resourceId": resId
                                                },
                                                eventLimit: true, // allow "more" link when too many events

                                            });
                                        },
                                        fail: function(result) {
                                            console.log("Failed to add...");
                                            console.log(result);
                                        },
                                        //passing date and time to the addEvent jsp file. 
                                        data: {
                                            "date": date,
                                            "timeTo": timeTo,
                                            "timeFrom": timeFrom,
                                            "title": title,
                                            "userId": userId,
                                            "resourceId": resId,
                                            "repeats": JSON.stringify(repeats),
                                            "type": "day"
                                        }
                                    });

                                });

                            //When adding an event. 
                            $("#addEventByWeekModal #addButton").click(
                                function() {

                                    console.log("Adding from week...");

                                    //get fields from the inputs inside the add modal
                                    var date = $("#date").val();
                                    var timeTo = $("#timeTo").val();
                                    var timeFrom = $("#timeFrom").val();
                                    var title = $("#room").val();
                                    var resId = $("#pageResourceId").val();
                                    var repeats = $("#weeklyRep").val();
                                    var userId = $("#pageUserId").text();

                                    /*
                                    console.log(date);
                                    console.log(timeTo);
                                    console.log(timeFrom);
                                    console.log(title);
                                    console.log(resId);
                                    console.log(repeats);
                                    console.log(userId);
									*/
                                    
                                    $.ajax({
                                        url: "addEvent",
                                        success: function() {
                                            $.ajax({
                                                url: "getBookingsAsTableByResourceId",
                                                dataType: 'html',
                                                success: function(result) {
                                                    //remove events from the calendar and rerender with the ajax result
                                                    $("#dispCal").fullCalendar('removeEvents');
                                                    calRender(result);
                                                },
                                                data: {
                                                    "resourceId": resId
                                                },
                                                eventLimit: true, // allow "more" link when too many events

                                            });
                                        },
                                        //passing date and time to the addEvent jsp file. 
                                        data: {
                                            "date": date,
                                            "timeTo": timeTo,
                                            "timeFrom": timeFrom,
                                            "title": title,
                                            "resourceId": resId,
                                            "repeats": repeats,
                                            "type": "week",
                                            "userId": userId
                                        }
                                    });
                                });

                            $.ajax({
                                url: "getAllBookingsAsTable",
                                dataType: 'html',
                                success: function(result) {
                                    calRender(result);
                                },
                                fail: function(result) {
                                    console.log("Failed get all service");
                                    console.log(result);
                                }
                            });


                        });



                    function calRender(result) {
                        var table = $.parseHTML(result)[0];

                        var formattedEventData = [];

                        var eventsArray = [];

                        console.log(table);

                        //create an array of event objects for the Calendar on the page. 
                        $(table).find("tr").each(function() {
                            var newEvent = [];

                            var start = this.cells[1].innerHTML;
                            start = start.replace(" ", "T");

                            var end = this.cells[2].innerHTML;
                            end = end.replace(" ", "T");

                            var title = this.cells[0].innerHTML;

                            var id = this.cells[3].innerHTML;

                            newEvent[0] = title;
                            newEvent[1] = start;
                            newEvent[2] = end;

                            if (title.toLowerCase().includes("scrum"))
                                newEvent[3] = "FireBrick";
                            else if (title.toLowerCase().includes("conference"))
                                newEvent[3] = "DarkCyan";
                            else if (title.toLowerCase().includes("board"))
                                newEvent[3] = "OrangeRed";
                            else if (title.toLowerCase().includes("recreation"))
                                newEvent[3] = "Chocolate";
                            else if (title.toLowerCase().includes("train"))
                                newEvent[3] = "OliveDrab";
                            else if (title.toLowerCase().includes("break"))
                                newEvent[3] = "SlateBlue";
                            else
                            	newEvent[3] = "Gray";

                            newEvent[4] = id;
                            eventsArray.push(newEvent);
                        });

                        //place all the events into an array formatted for FullCalendar
                        for (var k = 0; k < eventsArray.length; k++) {
                            formattedEventData.push({
                                title: eventsArray[k][0],
                                start: eventsArray[k][1],
                                end: eventsArray[k][2],
                                backgroundColor: eventsArray[k][3],
                                id: eventsArray[k][4]
                            });
                        }

                        $("#dispCal").fullCalendar('renderEvents', formattedEventData, true);
                    }


                    $("#view").css("background-color", "lightblue");
                </script>

            </body>

            </html>