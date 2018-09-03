package rms.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rms.dao.BookingsJdbcTemplate;
import rms.dao.FeaturesJdbcTemplate;
import rms.dao.LocationJdbcTemplate;
import rms.dao.ResourceTypeJdbcTemplate;
import rms.dao.ResourcesJdbcTemplate;
import rms.queries.CallUtilizationQueries;
import rms.queries.FeatureQueries;
import rms.queries.LoginQueries;
import rms.queries.UniqueResourcesAndLocations;
import rms.model.Bookings;
import rms.model.FeatureType;
import rms.model.Features;
import rms.model.FeaturesDropDown;
import rms.model.Location;
import rms.model.ResourceType;
import rms.model.Resources;
import rms.dao.FeatureTypeJdbcTemplate;

@Controller
public class MyServices {
	
	/**
	 * The service method called on the main index page.
	 * @return The login.jsp view.
	 */
	@RequestMapping(value = "/")
	public String homeScreen(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return "login";
	}

	/**
	 * The service method called when the logout page is reached.
	 * @return The login.jsp view.
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return "login";
	}
	
	/**
	 * The service method called when the dashboard page is reached.
	 * @return the dashboard.jsp view.
	 */
	@RequestMapping(value = "/dashboard")
	public String dashBoard(HttpServletRequest request, HttpServletResponse response) {

		if(request.getSession().getAttribute("userType")=="1")
			return "dashboard";
		else
			return "dashboardNotAdmin";
	}

	/**
	 * The service method called when the loginOnUserName page is reached.
	 * Retrieves an entered username and password and utilizes {@link LoginQueries} functions.
	 * @param request used to request and store information
	 * @param response
	 * @return If login is correct, returns a redirect to dashboard.jsp. If login is incorrect, returns the loginfailed.jsp view.
	 */
	@RequestMapping(value = "/loginOnUserName", method = RequestMethod.POST)
	public String loginOnUserName(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		LoginQueries login = new LoginQueries();

		System.out.println("CHECKPOINT 1");

		try {
			if (new LoginQueries().loginOnUserName(userName, password) != null && new LoginQueries().checkIsAdminUsingUsername(userName, password)) {
				int userId = login.getUserIdOnUserNameandPassword(userName, password);
				request.getSession().setAttribute("userName", userName);
				request.getSession().setAttribute("userId", userId);
				request.getSession().setAttribute("userType", "1");
				
				System.out.println("CHECKPOINT 2");
				return "redirect:/dashboard";
			}else if(new LoginQueries().loginOnUserName(userName, password) != null && new LoginQueries().checkIsAdminUsingUsername(userName, password)==false) {
				int userId = login.getUserIdOnUserNameandPassword(userName, password);
				request.getSession().setAttribute("userName", userName);
				request.getSession().setAttribute("userId", userId);
				request.getSession().setAttribute("userType", "0");

				return "dashboardNotAdmin";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			System.out.println("CHECKPOINT 3");
			return "loginfailed";
		}
		 	
		return "loginfailed";
	}

	/**
	 * The service method called for checking if an event is deletable
	 * Utilizes {@link BookingsJdbcTemplate} .
	 * @param request used to request and store information
	 * @param response
	 * 
	 */
	@RequestMapping(value = "/deleteCheck")
	public void deleteCheck(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("bookingId"));
		
		Bookings toDisable = new BookingsJdbcTemplate().search(id);
				
		int userId = (int) request.getSession().getAttribute("userId");
		String userType = (String) request.getSession().getAttribute("userType");
		
		PrintWriter out;
		try {
		
			if(toDisable.getUserId() == userId || userType=="1") {
				out = response.getWriter();
				out.write("Success");
			}else {
				out = response.getWriter();
				out.write("Fail");		
			}
		}catch(IOException e) {
			
		}
		
		

	}
	
	/**
	 * The service method called when the deleteEvent page is reached.
	 * Utilizes {@link BookingsJdbcTemplate} and deletes a received booking.
	 * @param request used to request and store information
	 * @param response
	 * 
	 */
	@RequestMapping(value = "/deleteEvent")
	public void deleteEvent(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("bookingId"));
		
		Bookings toDisable = new BookingsJdbcTemplate().search(id);
		
		toDisable.setIsActive(0);
		
		int userId = (int) request.getSession().getAttribute("userId");
		String userType = (String) request.getSession().getAttribute("userType");
		
		PrintWriter out;
		try {
		
			if(toDisable.getUserId() == userId || userType=="1") {
				out = response.getWriter();
				out.write("Success");
				new BookingsJdbcTemplate().update(toDisable);
			}else {
				out = response.getWriter();
				out.write("Fail");		
			}
		}catch(IOException e) {
			
		}
		
		

	}

	
	
	/**
	 * The service method called when the updateEvent page is reached.
	 * Retrieves a date, timeTo, timeFrom, bookingId, and userID, formats dates and timestamps,
	 * and updates the details of the corresponding booking by utilizing {@link BookingsJdbcTemplate}.
	 * @param request used to request and store information
	 * @param response
	 */
	@RequestMapping(value = "/updateEvent")
	public void updateEvent(HttpServletRequest request, HttpServletResponse response) {

		// Read data from ajax call
		String date = request.getParameter("date");
		String timeTo = request.getParameter("timeTo");
		String timeFrom = request.getParameter("timeFrom");
		int id = Integer.parseInt(request.getParameter("bookingId"));
		int userId = Integer.parseInt(request.getParameter("userId").trim());

		int resourceId = new BookingsJdbcTemplate().search(id).getResourceId();

		System.out.println(date);
		System.out.println(timeTo);
		
	   	DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String dates1 = date+" "+timeFrom;
		String dates2 = date+" "+timeTo;
		
		// translate the calendar date into a date for the database.
		LocalDateTime date1 = LocalDateTime.parse(dates1, format);
		LocalDateTime date2 = LocalDateTime.parse(dates2, format);
		Timestamp setter1 = new Timestamp(date1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		Timestamp setter2 = new Timestamp(date2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

		Bookings booking = new Bookings();
		booking.setIsActive(1);
		booking.setBookedStartTime(setter1);
		booking.setBookedEndTime(setter2);
		booking.setResourceId(resourceId);
		booking.setBookingId(id);
		booking.setUserId(userId);
		booking.setDescription("An event");

		new BookingsJdbcTemplate().update(booking);

	}

	/**
	 * The service method called when the addEvent page is reached.
	 * Retrieves a date, timeTo, timeFrom, resourceId, and type, formats dates and timestamps,
	 * and adds bookings in the pattern requested by the user (one time, weekly, etc.).
	 * @param request used to request and store information
	 * @param response
	 */
	@RequestMapping(value = "/addEvent")
	public void addEvent(HttpServletRequest request, HttpServletResponse response) {
		// Read data from ajax call
		String date = request.getParameter("date");
		String timeTo = request.getParameter("timeTo");
		String timeFrom = request.getParameter("timeFrom");
		String resourceId = request.getParameter("resourceId");
		String userId = request.getParameter("userId").trim();
		String type = request.getParameter("type");

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String dates1 = date + " " + timeFrom;
		String dates2 = date + " " + timeTo;

		// translate the calendar date into a date for the database.
		LocalDateTime date1 = LocalDateTime.parse(dates1, format);
		LocalDateTime date2 = LocalDateTime.parse(dates2, format);

		// Check the number of repeats
		if (type.equals("week")) {
			int repeats = Integer.parseInt(request.getParameter("repeats"));

			// Create a booking for each week
			for (int i = 0; i < repeats + 1; i++) {
				// Get the start timestamp
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(date1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
				cal.add(Calendar.WEEK_OF_MONTH, i);
				Timestamp start = new Timestamp(cal.getTimeInMillis());

				// Get the stop time stamp
				cal.setTimeInMillis(date2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
				cal.add(Calendar.WEEK_OF_MONTH, i);
				Timestamp stop = new Timestamp(cal.getTimeInMillis());

				// Add the booking
				Bookings booking = new Bookings();
				booking.setIsActive(1);
				booking.setBookedStartTime(start);
				booking.setBookedEndTime(stop);
				booking.setResourceId(Integer.parseInt(resourceId));
				booking.setUserId(Integer.parseInt(userId));
				booking.setDescription("An event");
				new BookingsJdbcTemplate().insert(booking);
			}
		} else {
			// Parse the repeat array
			String str = request.getParameter("repeats");
			str = str.substring(1, str.length() - 1);
			String[] split = str.split(",");

			// Make a booking for each day of the week checked
			Calendar cal = Calendar.getInstance();
			for (int i = 0; i < split.length; i++) {
				if (Boolean.parseBoolean(split[i])) {

					// Get the start timestamp
					cal.setTimeInMillis(date1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
					cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
					cal.add(Calendar.DAY_OF_YEAR, i);
					Timestamp start = new Timestamp(cal.getTimeInMillis());

					// Get the stop time stamp
					cal.setTimeInMillis(date2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
					cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
					cal.add(Calendar.DAY_OF_YEAR, i);
					Timestamp stop = new Timestamp(cal.getTimeInMillis());

					// Add the booking
					Bookings booking = new Bookings();
					booking.setIsActive(1);
					booking.setBookedStartTime(start);
					booking.setBookedEndTime(stop);
					booking.setResourceId(Integer.parseInt(resourceId));
					booking.setUserId(Integer.parseInt(userId));
					booking.setDescription("An event");
					new BookingsJdbcTemplate().insert(booking);
				}
			}
		}
	}
	
	/**
	 * Check to see if a new booking would cause a resource conflict.
	 */
	@RequestMapping(value = "/checkConflicts")
	public void checkConflicts(HttpServletRequest request, HttpServletResponse response) {
		// Read request parameters
		String type = request.getParameter("type");
		int resourceID = Integer.parseInt(request.getParameter("id"));
		String date = request.getParameter("date");
		String timeTo = request.getParameter("timeTo");
		String timeFrom = request.getParameter("timeFrom");

		/*
		 * System.out.println(type); System.out.println(resourceID);
		 * System.out.println(date); System.out.println(timeTo);
		 * System.out.println(timeFrom);
		 */

		// Make times sql friendly
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String dates1 = date + " " + timeFrom;
		String dates2 = date + " " + timeTo;
		LocalDateTime date1 = LocalDateTime.parse(dates1, format);
		LocalDateTime date2 = LocalDateTime.parse(dates2, format);

		// Get all bookings that are active
		List<Bookings> bookings = new BookingsJdbcTemplate().getAllByResourceId(resourceID);
		bookings = bookings.stream().filter(e -> e.getIsActive() == 1).collect(Collectors.toList());

		// Check which recurrences don't clash
		Calendar cal = Calendar.getInstance();
		if (type.equals("day")) {
			// Index i tells if a booking on day i (sunday = 0, monday = 1, ...) doesn't
			// clash with an existing booking
			boolean[] allowableRepeats = {true, false, false, false, false, false, false};

			// Check each day
			for (int i = 1; i < allowableRepeats.length; i++) {
				// Make the times sql friendly
				cal.setTimeInMillis(date1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				cal.add(Calendar.DAY_OF_YEAR, i);
				Timestamp start = new Timestamp(cal.getTimeInMillis());
				cal.setTimeInMillis(date2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				cal.add(Calendar.DAY_OF_YEAR, i);
				Timestamp stop = new Timestamp(cal.getTimeInMillis());

				// Make a hypothetical booking
				Bookings hypothetical = new Bookings();
				hypothetical.setIsActive(1);
				hypothetical.setBookedStartTime(start);
				hypothetical.setBookedEndTime(stop);
				hypothetical.setResourceId(resourceID);
				hypothetical.setUserId(101);
				hypothetical.setDescription("An event");
				System.out.println(hypothetical);
	
				// Check to see if the hypothetical booking conflicts
				boolean valid = true;
				for (Bookings existing : bookings) {
					// Is the hypothetical bookings before the exiting booking?
					boolean before =	(hypothetical.getBookedStartTime().before(existing.getBookedStartTime()) || hypothetical.getBookedStartTime().equals(existing.getBookedStartTime()))
										&& (hypothetical.getBookedEndTime().before(existing.getBookedStartTime()) || hypothetical.getBookedEndTime().equals(existing.getBookedStartTime()));

					// Is the hypothetical booking after the existing booking?
					boolean after = (hypothetical.getBookedStartTime().after(existing.getBookedEndTime()) || hypothetical.getBookedStartTime().equals(existing.getBookedEndTime()))
							&& (hypothetical.getBookedEndTime().after(existing.getBookedEndTime()) || hypothetical.getBookedEndTime().equals(existing.getBookedEndTime()));
					valid = valid & (before || after);
				}
				allowableRepeats[i] = valid;
			}
			
			// Build the response. The response is a list of booleans in string form.
			// i.e false,true, ...
			String resp ="";
			for(int i = 0; i < allowableRepeats.length; i++){
				resp += allowableRepeats[i] + ",";
			}
			// Get rid of the extraneous end commma
			resp = resp.substring(0, resp.length() - 1);
			
			// Send the response
			try {
				response.getWriter().write(resp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (type.equals("week")) {
			int maxBookableWeeks = 0;
			for (int i = 1; i <= 4; i++) {
				// Calculate time stamp for each possible week
				cal.setTimeInMillis(date1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
				cal.add(Calendar.WEEK_OF_MONTH, i);
				Timestamp start = new Timestamp(cal.getTimeInMillis());
				cal.setTimeInMillis(date2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
				cal.add(Calendar.WEEK_OF_MONTH, i);
				Timestamp stop = new Timestamp(cal.getTimeInMillis());

				// Make a hypothetical booking
				Bookings hypothetical = new Bookings();
				hypothetical.setIsActive(1);
				hypothetical.setBookedStartTime(start);
				hypothetical.setBookedEndTime(stop);
				hypothetical.setResourceId(resourceID);
				hypothetical.setUserId(101);
				hypothetical.setDescription("An event");

				// Check to see if the hypothetical booking clashes with any existing booking
				boolean valid = true;
				for (Bookings existing : bookings) {
					// Is the hypothetical bookings before the exiting booking?
					boolean before = (hypothetical.getBookedStartTime().before(existing.getBookedStartTime()) || hypothetical.getBookedStartTime().equals(existing.getBookedStartTime()))
							&& (hypothetical.getBookedEndTime().before(existing.getBookedStartTime()) || hypothetical.getBookedEndTime().equals(existing.getBookedStartTime()));

					// Is the hypothetical booking after the existing booking?
					boolean after = hypothetical.getBookedStartTime().after(existing.getBookedEndTime())
							&& hypothetical.getBookedEndTime().after(existing.getBookedEndTime());

					valid = valid && (before || after);

				}
				
				// Stop checking when you find a clash
				if (!valid) {
					// Respond to the ajax request
					maxBookableWeeks = i - 1;
					System.out.println(maxBookableWeeks);
					try {
						response.getWriter().write(maxBookableWeeks + "");
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				} else {
					maxBookableWeeks = i;
				}
			}

			// Respond to the ajax request. Response is the maximum number of 
			// weekly repeats that do not clash with an existing booking
			try {
				response.getWriter().write(maxBookableWeeks + "");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Get all the bookings and convert them into an HTML table
	 */
	@RequestMapping(value = "/getAllBookingsAsTable")
	public void getAllBookingsAsTable(HttpServletRequest request, HttpServletResponse response) {

		List<Bookings> allBookings = new BookingsJdbcTemplate().getAll();
		allBookings = allBookings.stream().filter(e -> e.getIsActive() == 1).collect(Collectors.toList());

		PrintWriter out = null;

		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		out.print("<table id='dataTable' border='solid' style='display:none'> ");

		for (Bookings b : allBookings) {

			out.print("<tr>");
			String resourceName = new ResourcesJdbcTemplate().search(b.getResourceId()).getResourceName();

			out.print("<td>" + resourceName + "</td>");
			out.print("<td>" + b.getBookedStartTime() + "</td>");
			out.print("<td>" + b.getBookedEndTime() + "</td>");
			out.print("<td>" + b.getBookingId() + "</td>");
			out.print("</tr>");
		}

		out.print("</table>");

	}
	
	/**
	 * Get all the bookings for a certain resource and convert them into an HTML table
	 */
	@RequestMapping(value = "/getBookingsAsTableByResourceId")
	public void getBookingsAsTableByResourceId(HttpServletRequest request, HttpServletResponse response) {

		String resId = request.getParameter("resourceId");

		int resourceId = Integer.parseInt(resId);
		List<Bookings> allBookings = new BookingsJdbcTemplate().getAllByResourceId(resourceId);
		allBookings = allBookings.stream().filter(e -> e.getIsActive() == 1).collect(Collectors.toList());
		PrintWriter out = null;

		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		out.print("<table id='dataTable' border='solid' style='display:none'> ");

		for (Bookings b : allBookings) {

			out.print("<tr>");
			String resourceName = new ResourcesJdbcTemplate().search(b.getResourceId()).getResourceName();

			out.print("<td>" + resourceName + "</td>");
			out.print("<td>" + b.getBookedStartTime() + "</td>");
			out.print("<td>" + b.getBookedEndTime() + "</td>");
			out.print("<td>" + b.getBookingId() + "</td>");
			out.print("</tr>");
		}

		out.print("</table>");

	}
	
	/**
	 * Get all bookings with a certain resource type and convert them into an HTML table
	 */
	@RequestMapping(value = "/getBookingsAsTableByType")
	public void getBookingsAsTableByType(HttpServletRequest request, HttpServletResponse response) {

		String resId = request.getParameter("resourceTypeId");
		
		System.out.println(resId);
		
		int resourceTypeId = Integer.parseInt(request.getParameter("resourceTypeId"));
		List<Bookings> allBookings = new BookingsJdbcTemplate().getAllByResourceType(resourceTypeId);
		allBookings = allBookings.stream().filter(e -> e.getIsActive() == 1).collect(Collectors.toList());
		PrintWriter out = null;

		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		out.print("<table id='dataTable' border='solid' style='display:none'> ");

		for (Bookings b : allBookings) {

			out.print("<tr>");
			String resourceName = new ResourcesJdbcTemplate().search(b.getResourceId()).getResourceName();

			out.print("<td>" + resourceName + "</td>");
			out.print("<td>" + b.getBookedStartTime() + "</td>");
			out.print("<td>" + b.getBookedEndTime() + "</td>");
			out.print("<td>" + b.getBookingId() + "</td>");
			out.print("</tr>");
		}

		out.print("</table>");

	}
	/**
	 * Returns the booking view of the service.
	 * @return Returns booking.jsp view.
	 */
	@RequestMapping(value = "/booking")
	public String booking(HttpServletRequest request, HttpServletResponse response) {

		if(request.getSession(false) == null) {
			return "login";
		}
		
		return "booking";
	}
	/**
	 * Inputs all of the rows of ResourceType into an attribute of a ModelMap object.
	 * Calls the types view which will use the information stored in the ModelMap.
	 * @param map used to addAttributes for the view to use.
	 * @return types.jsp
	 */
	@RequestMapping(value = "/types")
	public String pickResource(ModelMap map) {
		map.addAttribute("types", new ResourceTypeJdbcTemplate().getAll());
		return "types";
	}
	/**
	 * Gets list of rooms by the type of room provided by the user. Gets the features of each room,
	 * and the quantity of each feature for each room and sends it to rooms.jsp file. 
	 * @param request HttpServletRequest
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/resources", method = RequestMethod.POST)
	public String pickRoom(HttpServletRequest request, HttpServletRequest response) {
		// Get rooms of a given type
		int type = Integer.parseInt(request.getParameter("type"));
		List<Resources> roomsOfThisType = new ResourcesJdbcTemplate().resourcesByResourceType(type);

		// Map rooms to their resources
		Map<Resources, List<FeatureType>> roomsAndFeatures = new HashMap<Resources, List<FeatureType>>();
		Map<FeatureType, Features> featuresAndQuantities = new HashMap<FeatureType, Features>();
		for (Resources room : roomsOfThisType) {
			List<FeatureType> features = new ResourcesJdbcTemplate().getFeatures(room.getResourceId());
			roomsAndFeatures.put(room, features);
			for (FeatureType feature : features) {
				featuresAndQuantities.put(feature, new FeaturesJdbcTemplate()
						.searchByFeatureTypeIdAndResourceId(feature.getFeatureTypeId(), room.getResourceId()));
			}
		}

		// Pass the maps and the rooms
		response.setAttribute("type", type);
		response.setAttribute("rooms", roomsOfThisType);
		response.setAttribute("featureMap", roomsAndFeatures);
		response.setAttribute("quantityMap", featuresAndQuantities);

		return "rooms";
	}
	/**
	 * Gets the room Id as input from the user and uses that Id to get the rooms information. Also gets
	 * all the features of the same room and stores it in featureMap. Both of these are passed to HttpServletRequest
	 * (Should be Response) and then displayRoom.jsp is called.
	 * @param request
	 * @param response
	 * @return displayRoom.jsp
	 */
	@RequestMapping(value = "/roomDesc", method = RequestMethod.POST)
	public String displayRoom(HttpServletRequest request, HttpServletRequest response) {
		// Find the room
		int roomID = Integer.parseInt(request.getParameter("room"));
		Resources room = new ResourcesJdbcTemplate().search(roomID);

		Map<FeatureType, Features> featuresAndQuantities = new HashMap<FeatureType, Features>();

		List<FeatureType> features = new ResourcesJdbcTemplate().getFeatures(room.getResourceId());

		for (FeatureType feature : features) {
			featuresAndQuantities.put(feature, new FeaturesJdbcTemplate()
					.searchByFeatureTypeIdAndResourceId(feature.getFeatureTypeId(), room.getResourceId()));
		}

		// Pass the room
		response.setAttribute("room", room);
		response.setAttribute("featureMap", featuresAndQuantities);

		return "displayRoom";
	}
	/**
	 * Gets all of the Resources by LocationId and ResourceTypeId that the user passes in. Stores the result into
	 * an attribute of ModelMap. FilterResources is then called which uses that data from the modelmap.
	 * @param map
	 * @param request
	 * @param response
	 * @return FilterResources.jsp
	 */
	@RequestMapping(value="/LocationResources")
	public String searchLocationResources(ModelMap map, HttpServletRequest request, HttpServletResponse response){

		System.out.println("=-----------------Search Location Resources");
		//System.out.println(request.getParameter("location")+"-----"+ request.getParameter("resources"));
		int locationId=Integer.parseInt(request.getParameter("location"));
		int resourceTypeId=Integer.parseInt(request.getParameter("resources"));
		System.out.println(locationId+" l "+resourceTypeId);
		List<Resources> allResources= new UniqueResourcesAndLocations().getResourcesByLocationAndResourceType(locationId, resourceTypeId);
		map.addAttribute("alldata", allResources);
		
		List<Location> locs = new LocationJdbcTemplate().getAll();
		request.setAttribute("listCategory", locs);
		List<ResourceType> res=new ResourceTypeJdbcTemplate().getAll();
		request.setAttribute("listRes", res);

		
		List<FeaturesDropDown> listOfFeatures = new FeatureQueries().getFeatureNameAndQuantityByResouceId();
		map.addAttribute("featData", listOfFeatures);
		
		
		
		
		System.out.println("=-----------------helloo service got executed");
		return "AddSearchResources"; //view name
	}
	/**
	 * Gets the list of features and their quantity by ResourceId and sets it as an attribute in the HttpServletRequest request object.
	 * Gets the list of the Locations and the cities and and sets it as an attribute in the HttpServletRequest request object.
	 * Gets the list of all the resource types and sets it as an attribute in the HttpServletRequest request object.
	 * Gets a list of all the resources of a single location and adds it as an attribute in the ModelMap object.
	 * Calls the AddSearchResources.jsp file. 
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/AddSearchResources1")
	public String searchAllResources1(ModelMap map,HttpServletRequest request, HttpServletResponse response){

		System.out.println("=-----------------searchAllResources1");
		List<Location> locs = new LocationJdbcTemplate().getAll();
		request.setAttribute("listCategory", locs);
		
		List<FeaturesDropDown> listOfFeatures = new FeatureQueries().getFeatureNameAndQuantityByResouceId();
		request.setAttribute("featData", listOfFeatures);
		
		//get resource types instead of all resources
		List<ResourceType> res=new ResourceTypeJdbcTemplate().getAll();
		request.setAttribute("listRes", res);

		// for printing all the resources at the bottom of view.
		List<Resources> allResources = new UniqueResourcesAndLocations().getResourcesByLocation(100001);

		map.addAttribute("alldata", allResources);
		System.out.println("=-----------------helloo service got executed");
		return "AddSearchResources"; // view name

	}
	/**
	 * Gets a list of all cities and locations and sets it as an attribute to the HttpServletResponse request object.
	 * Gets a list of features and their quantities by the resource id and sets it as an attribute to the HttpServletResponse request object.
	 * Gets a list of all the resources and adds it as an attribute to ModelMap.
	 * Calls the showAllResources view.
	 * @param map
	 * @param request
	 * @param response
	 * @return showAllResources.jsp
	 */
	@RequestMapping(value = "/showAllResources")
	public String showAllResources(ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("=-----------------searchAllResources1");
		List<String> loc = new UniqueResourcesAndLocations().getLocationAndCity();
		request.setAttribute("listCategory", loc);
		
		List<FeaturesDropDown> listOfFeatures = new FeatureQueries().getFeatureNameAndQuantityByResouceId();
		request.setAttribute("featData", listOfFeatures);
		
		//get resource types instead of all resources
		List<ResourceType> res=new ResourceTypeJdbcTemplate().getAll();
		List<ResourceType> tempRes = new ArrayList<ResourceType>(res);
		request.setAttribute("listRes", res);

		//remove all resource types without any rooms
		for(ResourceType resource : tempRes) {
			List<Resources> temp= new ResourcesJdbcTemplate().resourcesByResourceType(resource.getResourceTypeId());
			if(temp.size()==0)
				res.remove(resource);
		}
		
		List<Resources> allResources = null;
		
		//if super user just show all rooms. 
		if(request.getSession().getAttribute("userType")=="1") {
			// for printing all the resources 
			allResources = new UniqueResourcesAndLocations().getResourcesByLocation(100001);
			allResources = allResources.stream().filter(e -> e.getIsAvailable() != -1).collect(Collectors.toList());
		}else{
			// for printing non super resources.
			allResources = new UniqueResourcesAndLocations().getResourcesByLocationForNonSuperUser(100001);
			allResources = allResources.stream().filter(e -> e.getIsAvailable() != -1).collect(Collectors.toList());
		}
		
		map.addAttribute("alldata", allResources);
		System.out.println("=-----------------helloo service got executed");

		return "showAllResources"; // view name

	}
	
	

	
	
	
	/**
	 * Service that inserts a resource into the Database based on user input and 
	 * also inserts all the features of the resource in the database as well.
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value="/insertResource", method=RequestMethod.POST) 
	public String addResourceService(HttpServletRequest request, HttpServletResponse response) {
		String desc = request.getParameter("desc");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		String roomNum = request.getParameter("roomNum");
		int resourceTypeId = Integer.parseInt(request.getParameter("resources"));
		int locId = Integer.parseInt(request.getParameter("location"));
		int isSupRoom = Integer.parseInt(request.getParameter("isSuperRoom"));
		
		List<String> res2=new UniqueResourcesAndLocations().ResourceTypeName(Integer.parseInt(request.getParameter("resources")));
		request.setAttribute("typeName", res2);
        
		System.out.println(isSupRoom);
		
		//Number of features
		int numProjectorFeature = Integer.parseInt(request.getParameter("numResProjName"));
		int numPrinterFeature = Integer.parseInt(request.getParameter("numResPrintName"));
		int numDesktopFeature = Integer.parseInt(request.getParameter("numResDesktopName"));
		int numTVFeature = Integer.parseInt(request.getParameter("numResTVName"));
		int numWhiteBoardFeature = Integer.parseInt(request.getParameter("numResWhiteBoardName"));
		int numChairFeature = Integer.parseInt(request.getParameter("numResChairName"));
		
		//Create resource object
		Resources res = new Resources();		
		res.setResourceName(res2.get(0));
		res.setResourceDescription(desc);
		res.setResourceRoomNumber(roomNum);
		res.setResourceTypeId(resourceTypeId);
		res.setLocationId(locId);
		res.setIsAvailable(1); 
		res.setIsSuperRoom(isSupRoom);
		res.setCapacity(capacity);
		
		
		ResourcesJdbcTemplate resTemp = new ResourcesJdbcTemplate();
		FeaturesJdbcTemplate featTemp = new FeaturesJdbcTemplate();
		
		//Add a new resource
		resTemp.insert(res);
		
		List<Integer> resourceIdTest=new UniqueResourcesAndLocations().getMostRecentResourceId();
		System.out.println(resourceIdTest.get(0));
		
		//Create feature object
		if (numProjectorFeature>0) {
			Features featProj = new Features();
			featProj.setFeatureTypeId(101);
			featProj.setQuantity(numProjectorFeature);
			featProj.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featProj);
			System.out.println("projector feat inserted");
		}
		if (numPrinterFeature>0) {
			Features featPrint = new Features();
			featPrint.setFeatureTypeId(104);
			featPrint.setQuantity(numPrinterFeature);
			featPrint.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featPrint);
			System.out.println("printer feat inserted");
		}
		if (numDesktopFeature>0) {
			Features featDesktop = new Features();
			featDesktop.setFeatureTypeId(102);
			featDesktop.setQuantity(numDesktopFeature);
			featDesktop.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featDesktop);
			System.out.println("desktop feat inserted");
		}
		if (numTVFeature>0) {
			Features featTV = new Features();
			featTV.setFeatureTypeId(105);
			featTV.setQuantity(numTVFeature);
			featTV.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featTV);
			System.out.println("tv feat inserted");
		}
		if (numWhiteBoardFeature>0) {
			Features featWhiteBoard = new Features();
			featWhiteBoard.setFeatureTypeId(106);
			featWhiteBoard.setQuantity(numWhiteBoardFeature);
			featWhiteBoard.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featWhiteBoard);
			System.out.println("whiteboard feat inserted");
		}
		if (numChairFeature>0) {
			Features featChair = new Features();
			featChair.setFeatureTypeId(103);
			featChair.setQuantity(numChairFeature);
			featChair.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featChair);
			System.out.println("chair feat inserted");
		}
		
		return "redirect:/AddSearchResources1";
	}
	
	/**
	 * Service method called when charts page is reached.
	 * This method serves as the main service method for charts.
	 * When the Charts page is clicked from the menu, the initial information of resource types and rooms are retrieved and put into the dropdown menus. 
	 * The util value is set to -1.0 as a default so that no chart is created upon the initial page load. 
	 * @param request HttpServletRequest used to request and store information
	 * @param response HttpServletResponse
	 * @return The utilizationChart.jsp view
	 */
	@RequestMapping(value="/charts")
	public String mainService(HttpServletRequest request, HttpServletResponse response) 
	{
	//	System.out.println("loading");
		//drop down stuff
		List<ResourceType> vt=new ResourceTypeJdbcTemplate().getAll();
        request.setAttribute("resourceTypes", vt);
        List<Resources> rt=new ResourcesJdbcTemplate().getAll();
        request.setAttribute("rooms", rt);
        HttpSession session=request.getSession();
        session.setAttribute("util",-1.0);
	/*	if((resourceType!="all") && (resourceType !=null)) {
			int iresourceType = Integer.parseInt(resourceType );
			List<String> rt=new UniqueResourcesAndLocations().idkTheName(iresourceType);
	        request.setAttribute("rooms", rt);
	        System.out.println(rt.get(1));
		} */
         
		return "utilizationChart"; //view name
	}
	
	/**
	 * Service method called when drawChart page is reached.
	 * This method gets chart parameters and converts dates to the correct, usable format. 
	 * The parameters are passed back to the jsp file to create the actual utilization charts. 
	 * 	 * @param request HttpServletRequest used to request and store information
	 * @param response HttpServletResponse
	 * @return The utilizationChart.jsp view
	 */
	@RequestMapping(value="/drawChart",method=RequestMethod.POST)
	public String drawChart(HttpServletRequest request, HttpServletResponse response) 
	{
		String viewType = request.getParameter("viewType");
		String roomType = request.getParameter("roomType2");
		String period= request.getParameter("period");
		String sdate = request.getParameter("pickedDate");
		//sdate += " 00:00";
		System.out.println(viewType);
		System.out.println(roomType);
		System.out.println(period);
		if(roomType.length()>4)
			roomType = roomType.substring(0,4);
		//System.out.println(sdate);
		// sdate is string date that was passed in by the user
		System.out.println("what we have as a string: "+sdate);
		String pattern = "yyyy-MM-dd"; //what we have
		String pattern2 = "dd-MM-yyyy";	// what we want
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		SimpleDateFormat format2 = new SimpleDateFormat(pattern2);
		Date date= new Date(); // new date
		try 
		{
			date = format.parse(sdate); // convert what we have from string to date
			System.out.println("what we have as a date: "+date);
			sdate = format2.format(date); // convert date to string in the format that we want
			System.out.println("what we want as a string: "+sdate);
			date = format2.parse(sdate); // convert it back to date in the format that we want
			System.out.println("what we want as a date: "+date);
		} 
		catch(Exception e) 
		{
			System.out.println("wrong date");
		}
		//sdate = format.format(date);
		//System.out.println(sdate);
		//System.out.println(date);
		HttpSession session=request.getSession();
		double util = 0.0;
		try
		{
			if(viewType.equals("all")) 
			{
				util = periodTypeMethod(period, date);
			}
			else 
			{
				if(roomType.equals("all"))
				{
					util = periodTypeMethod(viewType,period, date);
				} 
				else 
				{
					util = periodTypeMethodWithRoomId(roomType,period, date);
				}
			}
		} 
		catch (NullPointerException e) 
		{
			util=0.0;
		}
		catch (EmptyResultDataAccessException e) 
		{
			util=0.0;
		}
	//	util = 0.5;
		session.setAttribute("util",util);
		//drop down stuff
		List<ResourceType> vt=new ResourceTypeJdbcTemplate().getAll();
        request.setAttribute("resourceTypes", vt);
        List<Resources> rt=new ResourcesJdbcTemplate().getAll();
        request.setAttribute("rooms", rt);
		return "utilizationChart";
	}
	
	/**
	 * Method to determine the type of period that is needed and return it (daily, weekly, monthly).
	 * @param period String used to select a period
	 * @param day Date input from the user
	 * @return The periodical utilization of all resources.
	 */
	private double periodTypeMethod(String period, Date day) 
	{
		double x=0.0;
		CallUtilizationQueries util = new CallUtilizationQueries();
		System.out.println("this is the date: "+day);
		switch(period)
		{
			case "day":
				x = util.callDailyUtilizationForAllResources(day);
				break;
			case "weekly":
				x=util.callWeeklyUtilizationForAllResources(day);
				break;
			case "monthly":
				x=util.callMonthlyUtilizationForAllResources(day);
				break;
		}
		return x;
	}
	
	/**
	 * Method to determine the type of period that is needed and return it (daily, weekly, monthly).
	 * @param viewType String of resource type ID input from user
	 * @param period String used to select a period
	 * @param day Date input from the user
	 * @return The periodical utilization of a resource based on a resource type ID.
	 */
	private double periodTypeMethod(String viewType, String period, Date day)
	{
		double x =0.0;
		CallUtilizationQueries util = new CallUtilizationQueries();
		switch(period) 
		{
			case "day":
				x = util.callDailyUtilizationByResourceTypeId(Integer.parseInt(viewType), day);
				break;
			case "weekly":
				x = util.callWeeklyUtilizationByResourceTypeId(Integer.parseInt(viewType), day);
				break;
			case "monthly":
				x = util.callMonthlyUtilizationByResourceTypeId(Integer.parseInt(viewType), day);
				break;
		}
		return x;
	}
	
	/**
	 * Method to determine the type of period that is needed and return it (daily, weekly, monthly).
	 * @param roomId String of resource ID input from user
	 * @param period String used to select a period
	 * @param day Date input from the user
	 * @return The periodical utilization of a resource based on a resource type ID.
	 */
	private double periodTypeMethodWithRoomId(String roomId, String period, Date day) 
	{
		double x=0.0;
		CallUtilizationQueries util = new CallUtilizationQueries();
		switch(period)
		{
			case "day":
				x = util.callDailyUtilizationByResourceId(Integer.parseInt(roomId), day);
				break;
			case "weekly":
				x = util.callWeeklyUtilizationByResourceId(Integer.parseInt(roomId), day);
				break;
			case "monthly":
				x = util.callMonthlyUtilizationByResourceId(Integer.parseInt(roomId), day);
				break;
		}
		return x;
	}

	/**
	 * Service method called when showResourceByType page is reached.
	 * Method is used to display resources based on resource type. 
	 * @param map ModelMap that all data is put into
	 * @param request HttpServletRequest used to request and store information
	 * @param response HttpServletResponse
	 * @return The showResourceByType.jsp view
	 */
	@RequestMapping(value = "/showResourceByType")
	public String showResourceByType(ModelMap map, HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("=-----------------Search Location Resources");

		//System.out.println(request.getParameter("location")+"-----"+ request.getParameter("resources"));
		List<FeaturesDropDown> listOfFeatures = new FeatureQueries().getFeatureNameAndQuantityByResouceId();
		request.setAttribute("featData", listOfFeatures);
		int locationId=Integer.parseInt(request.getParameter("location"));
		int resourceTypeId=Integer.parseInt(request.getParameter("resources"));

		List<Resources> allResources;
		
		//if super user just show all rooms. 
		if(request.getSession().getAttribute("userType")=="1") {
			// for printing all the resources 
			allResources = new UniqueResourcesAndLocations().getResourcesByLocationAndResourceType(locationId, resourceTypeId);
			allResources = allResources.stream().filter(e -> e.getIsAvailable() != -1).collect(Collectors.toList());

		}else{
			// for printing non super resources.
			allResources = new UniqueResourcesAndLocations().getResourcesByLocationAndResourceTypeNonSuper(locationId, resourceTypeId);
			allResources = allResources.stream().filter(e -> e.getIsAvailable() != -1).collect(Collectors.toList());

		}		
		map.addAttribute("alldata", allResources);
		
		System.out.println("=-----------------helloo service got executed");
		return "showResourceByType"; // view name
	}

	@RequestMapping(value="/pleaseCheckMyEdit")
	public void validateEdit(HttpServletRequest request, HttpServletResponse response){
		// Read request parameters
		int bookingID = Integer.parseInt(request.getParameter("bookingID"));
		int resourceID = new BookingsJdbcTemplate().search(bookingID).getResourceId();
		String date = request.getParameter("date");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		
		// Make a booking from the edit
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String dates1 = date + " " + endTime;
		String dates2 = date + " " + startTime;

		// Make timestamps for validation
		Calendar cal = Calendar.getInstance();
		LocalDateTime date1 = LocalDateTime.parse(dates1, format);
		LocalDateTime date2 = LocalDateTime.parse(dates2, format);
		cal.setTimeInMillis(date1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		Timestamp start = new Timestamp(cal.getTimeInMillis());
		cal.setTimeInMillis(date2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		Timestamp stop = new Timestamp(cal.getTimeInMillis());
		
		// Create a hypothetical booking
		Bookings posed = new Bookings();
		posed.setBookedStartTime(start);
		posed.setBookedEndTime(stop);
		System.out.println(posed);
		
		// Check to see if it clashes
		List<Bookings> allExisting = new BookingsJdbcTemplate().getAllByResourceId(resourceID);
		allExisting = allExisting.stream().filter(e -> e.getBookingId() != bookingID && e.getIsActive() == 1).collect(Collectors.toList());
		System.out.println(allExisting.size());
		boolean valid = true;
		for(Bookings existing: allExisting){
			boolean after = 	(posed.getBookedEndTime().after(existing.getBookedEndTime()) || posed.getBookedEndTime().equals(existing.getBookedEndTime())) &&
							 	(posed.getBookedStartTime().after(existing.getBookedEndTime()) || posed.getBookedStartTime().equals(existing.getBookedEndTime()));
			boolean before = 	(posed.getBookedEndTime().before(existing.getBookedStartTime()) || posed.getBookedEndTime().equals(existing.getBookedStartTime())) &&
								(posed.getBookedStartTime().before(existing.getBookedStartTime()) || posed.getBookedStartTime().equals(existing.getBookedStartTime()));
			valid = valid && (before || after);
		}
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		// Check to see the start time is before the end time
		valid = valid && (start.before(stop)) && (start.after(now));

		// Tell the page if the edit works or not
		System.out.println(valid);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(valid + "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/DeleteResource/{resourceId}") 
	  public String deleteResourceService(@PathVariable int resourceId) { 
	    System.out.println("Deletion" + resourceId); 
	    //if 
	    int upd=new ResourcesJdbcTemplate().setIsAvailableToDelete(resourceId); 
	    if(upd==1) { 
	      return "redirect:/AddSearchResources1"; 
	    }else { 
	    return "errorPage"; 
	    } 
	  }
}