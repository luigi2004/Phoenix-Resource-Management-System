package rms.services;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import rms.dao.VisitorsJdbcTemplate;
import rms.model.Visitors;
import rms.queries.VisitorTracking;

/**
 * VisitorServices program is the central location where all services
 * regarding visit registration occur. The program includes visitor
 * registration, administrative page to observe registration data, and 
 * page invocation.
 * 
 * 
 * @author Manal Abdalla, Luis Zinzun, Logan Johnson
 * @version 1.0
 * @since 2018-07-27
 */
@Controller
public class VisitorServices {
	

	/**
	 * This RequestMapping returns visitorHome at invocation
	 * 
	 * @param None
	 * @return String This returns visitorHome jsp page
	 */
	@RequestMapping(value="/Visitor/Home")
	public String homeScreen() {
		return "visitorHome";
	}

	/**
	 * This RequestMapping returns visitorRegistrationForm at invocation
	 * 
	 * @param None
	 * @return String This returns visitorRegistrationForm jsp page
	 */
	@RequestMapping(value="/Visitor/Registration")
	public String openRegistration() {
		return "visitorRegistrationForm"; 
	}
	
	/**
	 * This method digests all user input personal info from registration page
	 * and sets it at Visitor class' variables while filling the rest of the
	 * variables that user isn't directly responsible for (i.e. timestamp, locationID)
	 * After Visitor object is complete send it to VisitorJDBC template.
	 * Returns String for visitorWelcome jsp page.
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @return String This returns visitorHome jsp page
	 */
	@RequestMapping(value="/Visitor/RegisterVisitor")
	public String registerVisitorService(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //
		
		//Set Strings for fields that user could fill in Register Form page
		String visitorFirstName = req.getParameter("visitorFirstName");
		String visitorLastName = req.getParameter("visitorLastName");
		String visitorEmail = req.getParameter("visitorEmail");
		String visitorPhone = req.getParameter("visitorPhone");
		String visitorVisitingName = req.getParameter("visitorVisitingName");
		String visitorVisitPurpose = req.getParameter("visitorVisitPurpose");
		String visitorCompanyName = req.getParameter("visitorCompanyName");
		if (visitorCompanyName.equals("Other")) {
			visitorCompanyName = req.getParameter("otherCompanyName");
		}
		Timestamp visitorCheckedInTime = timestamp; //
		int visitorLocationID = 100001;
		
		model.addAttribute("full_name", (visitorFirstName + " " + visitorLastName));
		//Setting rest of all the fields from Visitor table to null
		String visitorBadgeID = null;
		int visitorIDProof = 0;
		Timestamp visitorCheckedOutTime = null; //signout time
		int visitorHasCheckedOut = 0; //signout time
		
		
		//Create Visitor object to insert
		Visitors newVisitor = new Visitors();
		newVisitor.setFirstName(visitorFirstName);
		newVisitor.setLastName(visitorLastName);
		newVisitor.setEmail(visitorEmail);
		newVisitor.setPhone(visitorPhone);
		newVisitor.setVisitingName(visitorVisitingName);
		newVisitor.setVisitPurpose(visitorVisitPurpose);
		newVisitor.setCompanyName(visitorCompanyName);
		
		newVisitor.setCheckedInTime(visitorCheckedInTime);
		newVisitor.setLocationId(visitorLocationID);
		newVisitor.setBadgeId(visitorBadgeID);
		newVisitor.setCheckedOutTime(visitorCheckedOutTime);
		newVisitor.setHasIdProof(visitorIDProof);
		newVisitor.setHasCheckedOut(visitorHasCheckedOut);
		
		
		System.out.println(newVisitor.getFirstName());
		System.out.println(newVisitor.getLastName());
		System.out.println(newVisitor.getCompanyName());
		
		
		VisitorsJdbcTemplate vjt = new VisitorsJdbcTemplate();
		vjt.insert(newVisitor);
		
		return "visitorWelcome";
		
		
	}

	/**
	 * This method digests either badge number or phone number info from form to 
	 * check out visitor. If wrong input the same form loads, if correct input entered
	 * the method returns String for visitorGoodbye page.
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @return String This returns current visitorCOForm or visitorGoodbye jsp page.
	 */
	@RequestMapping(value="/Visitor/CheckOut")
	public String insertAccountsService(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		String phone = request.getParameter("phone");
		String badgId = request.getParameter("bid");
		
		if(phone.equals("")) {
			if(new rms.queries.VisitorTracking().checkoutUsingBadgeID(badgId) > 0)
			{
				String name = new VisitorTracking().getVisitorUsingBadgeId(badgId);
				model.addAttribute("full_name", name);
				return "visitorGoodbye";
			}
			else
				return "visitorCOForm";
		} else {
			if(new VisitorTracking().checkoutUsingPhone(phone) > 0)
			{
				String name = new VisitorTracking().getVisitorUsingPhone(phone);
				model.addAttribute("full_name", name);
				return "visitorGoodbye";
			}
			else
				return "visitorCOForm";
		}
	}
	
	/**
	 * This RequestMapping returns COForm at invocation.
	 * 
	 * @param None
	 * @return String This returns check out form jsp page.
	 */
	@RequestMapping(value="/Visitor/COForm")
	public String insertFormService(){
		return "visitorCOForm";
		
	}
	

	@RequestMapping(value ="/Visitor/RangeSearch")
	public String visitorRangeSearch(HttpServletRequest request, HttpServletResponse response, ModelMap map) {

		String fromDay = request.getParameter("fromDay");
		String toDay = request.getParameter("toDay");
		 
		String pattern = "yyyy-MM-dd"; //what we have
		String pattern2 = "MM-dd-yyyy";	// what we want
		
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		SimpleDateFormat format2 = new SimpleDateFormat(pattern2);
		Date datef= new Date(); // new date
		Date datet= new Date(); 
		try {
			datef = format.parse(fromDay);// convert what we have from string to date
			datet = format.parse(toDay); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		fromDay = format2.format(datef); // convert date to string in the format that we want
		toDay = format2.format(datet); // convert date to string in the format that we want
		
		List<Visitors> rangeVisitors=new VisitorTracking().getVisitorsFromRange(fromDay,toDay);
		map.addAttribute("alldata", rangeVisitors);
		return "visitorAdminView";
	}
	


	/**
	 * Upon admin-page load, this will display all the visitors and their registration information
	 * in a tabular manner.
	 * 
	 * @param ModelMap
	 * @return String This returns page for administrative access to visitors' data.
	 */

	@RequestMapping(value="/Visitor/Admin")
	public String visitorAdminService(ModelMap map){
		List<Visitors> dailyVisitors= new VisitorTracking().getVisitorsFromToday();
		map.addAttribute("alldata", dailyVisitors);
		return "visitorAdminView";
		
	}
	
	/**
	 * This RequestMap complements visitorAdmin's function to get all
	 * visitors for given date and check out visitors based on ID.
	 * 
	 * @param String Consumes visitor's ID to check out.
	 * @return String Redirects mapping to admin service.
	 */
	@RequestMapping(value="/Visitor/AdminCO/{visitor_id}")
	public String visitorsAdminCheckOutService(@PathVariable String visitor_id){
		new VisitorTracking().checkoutUsingVisitorId(visitor_id);
			return "redirect:/Visitor/Admin";
	}
	
}
