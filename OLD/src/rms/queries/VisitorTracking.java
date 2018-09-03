package rms.queries;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.mapper.VisitorsMapper;
import rms.model.Visitors;

/**
 * This class controls the visit and checkout status of Visitors through various methods.
 * @author Re: Syntellions
 * @see rms.model.Visitors
 */
public class VisitorTracking {
	private ApplicationContext context;
	private JdbcTemplate jtemp;
	
	/**
	 * Constructor that establishes connection to database via Spring Framework.
	 */
	public VisitorTracking() {
        this.context = new ClassPathXmlApplicationContext("spring-dao.xml");
        this.jtemp = (JdbcTemplate)context.getBean("jt");
	}
	
	/**
	 * Checks out a visitor, setting has_checked_out to 1 and checked_out_time to a current timestamp.
	 * @param fullName The full name of the person to be checked out. First and last name must be separated by a space, i.e. "Jack Doe".
	 * @return The number of database entries that were altered.
	 */
	public int checkoutUsingFullName(String fullName){
		return jtemp.update("UPDATE visitors SET has_checked_out = 1, checked_out_time = CURRENT_TIMESTAMP WHERE upper(first_name||' '||last_name) = upper(?)",fullName);
	}
	
	/**
	 * Checks out a visitor, setting has_checked_out to 1 and checked_out_time to a current timestamp.
	 * @param firstName The first name of the person to be checked out.
	 * @param lastName The last name of the person to be checked out.
	 * @return The number of database entries that were altered.
	 */
	public int checkoutUsingFirstAndLast(String firstName, String lastName){
		return jtemp.update("UPDATE visitors SET has_checked_out = 1, checked_out_time = CURRENT_TIMESTAMP WHERE upper(first_name) = upper(?) AND upper(last_name) = upper(?)",firstName,lastName);
	}
	
	/**
	 * Checks out a visitor, setting has_checked_out to 1 and checked_out_time to a current timestamp.
	 * @param badgeID The Badge ID of the person to be checked out.
	 * @return The number of database entries that were altered.
	 */
	public int checkoutUsingBadgeID(String badgeID){
		return jtemp.update("UPDATE visitors SET has_checked_out = 1, checked_out_time = CURRENT_TIMESTAMP WHERE badge_id = ?",badgeID);
	}
	
	/**
	 * Checks out a visitor, setting has_checked_out to 1 and checked_out_time to a current timestamp.
	 * @param phone The phone number of the person to be checked out.
	 * @return The number of database entries that were altered.
	 */
	public int checkoutUsingPhone(String phone){
		return jtemp.update("UPDATE visitors SET has_checked_out = 1, checked_out_time = CURRENT_TIMESTAMP WHERE phone = ? AND has_checked_out = 0",phone);
	}
	
	/**
	 * Gets the name of a visitor who has a given badge ID formatted as a string First Name + " " + Last Name.
	 * @param badge_id The badge ID of the user whose name should be returned.
	 * @return The name of the visitor with the given badge ID as First Name + " " + Last Name.
	 */
	public String getVisitorUsingBadgeId(String badge_id) {
		return jtemp.queryForObject("SELECT first_name||' '||last_name FROM visitors WHERE badge_id = ?", String.class, badge_id);
	}
	
	/**
	 * Checks out a visitor, setting has_checked_out to 1 and checked_out_time to a current timestamp.
	 * @param visitor_id The visitor ID of the person to be checked out.
	 * @return The number of database entries that were altered.
	 */
	public int checkoutUsingVisitorId(String visitor_id){
		return jtemp.update("UPDATE visitors SET has_checked_out = 1, checked_out_time = CURRENT_TIMESTAMP WHERE visitor_id = ? and has_checked_out = 0",visitor_id);
	}
	
	/**
	 * Gets the name of a visitor who has a given phone number and has checked out after
	 * an hour ago formatted as a string First Name + " " + Last Name.
	 * @param phone The phone number of the user whose name should be returned.
	 * @return The name of the visitor with the given phone number as First Name + " " + Last Name.
	 */
	public String getVisitorUsingPhone(String phone) {
		return jtemp.queryForObject("SELECT unique first_name||' '||last_name FROM visitors WHERE phone = ? and CHECKED_OUT_TIME >= (systimestamp-1/24)",String.class, phone);
	}
	
	/**
	 * Gets a list of all visitors from the current day.
	 * @return A list of visitors.
	 */
	public List<Visitors> getVisitorsFromToday()
	{
		
		String myTime = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		return jtemp.query("SELECT * FROM visitors WHERE trunc(CHECKED_IN_TIME) = TO_DATE(?,'mm/dd/yyyy')", new VisitorsMapper(), myTime);
	}
	
	/**
	 * Gets all of the visitors from a selected day.
	 * @param day The day to find visitors from. Must be passed as a string in the format "mm/dd/yyyy", ex: "11/27/2018".
	 * @return A list of visitors.
	 */
	public List<Visitors> getVisitorsFromDay(String day)
	{
		return jtemp.query("SELECT * FROM visitors WHERE trunc(CHECKED_IN_TIME) = TO_DATE(?,'mm/dd/yyyy')", new VisitorsMapper(), day);
	}
	
	/**
	 * Gets all of the visitors from a selected day-range.
	 * @param dayfrom The day-range start date. Must be passed as a string in the format "mm/dd/yyyy", ex: "11/27/2018".
	 * @param dayto The day-range end date. Must be passed as a string in the format "mm/dd/yyyy", ex: "11/27/2018".
	 * @return A list of visitors.
	 */
	public List<Visitors> getVisitorsFromRange(String dayfrom, String dayto)
	{
		return jtemp.query("SELECT * FROM visitors WHERE trunc(CHECKED_IN_TIME) BETWEEN TO_DATE(?,'mm/dd/yyyy') AND TO_DATE(?,'mm/dd/yyyy')", new VisitorsMapper(), dayfrom, dayto);
	}

}
