package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.mapper.GuestsMapper;
import rms.model.Guests;

/**
 * Java Database Connectivity Template that utilizes Spring Framework to manage Guests through a database connection.
 * This class contains methods for inserting, deleting, updating from the Guests table.
 * Other methods include searching for a specific Guest based on a Booking ID and also showing all Guests. 
 * @author Re: Syntellions
 *
 */
public class GuestsJdbcTemplate
{
	/**
	 * Local variable for the JdbcTemplate
	 */
	JdbcTemplate jtemp;
	/**
	 * Local variable for the ApplicationContext
	 */
	ApplicationContext context;
	

	/**
	 * Constructor that establishes the connection with the database
	 */
	public GuestsJdbcTemplate() 
	{
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}
	
	
	/**
	 * Method to insert a new row into the Guests table
	 * @param guest A java object containing all the Guest data you want to be inserted into the database.
	 * @return The number of rows inserted. (Either 0 or 1)
	 */
	public int insert(Guests guest)
	{
		int result = jtemp.update("INSERT INTO Guests VALUES(?, ?, ?)", 
				guest.getBookingId(),
				guest.getUserId(),
				guest.getVisitorId());
														
		return result;
	}

	/**
	 * Method to delete a row from the Guests table.
	 * @param bookingId the ID (primary key) of the row to be deleted.
	 * @return The number of deleted rows. (Either 0 or 1)
	 */
	public int delete(int bookingId) 
	{
		int result = jtemp.update("DELETE FROM Guests WHERE booking_id = ?", bookingId);
		
		return result;
	}

	/**
	 * Method to update a row from the Guests table
	 * @param guest The java object containing all the new values to be updated. Will update
	 * the row with the same ID as the java object. ID is based on both Booking ID and User ID.
	 * @return The number of rows updated (Either 0 or 1)
	 */
	public int update(Guests guest) 
	{
		int result = jtemp.update("UPDATE Guests SET"
				+ " user_id = ?,"
				+ " visitor_id = ?"
				+ " WHERE booking_id = ? AND user_id = ?",				
			guest.getUserId(),
			guest.getVisitorId(),
			guest.getBookingId(),
			guest.getUserId());
		
		return result;
	}

	/**
	 * Will search for a database row and map it into a java object.
	 * @param bookingId The ID of the row you want to search for.
	 * @return The mapped java object containing all the data from the database row.
	 * @throws EmptyResultDataAccessException exception for returning a result set that does not exist
	 * @throws IncorrectResultSizeDataAccessException exception for returning a result set that is the incorrect size
	 */
	public List<Guests> search(int bookingId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException
	{
		return jtemp.query("SELECT * FROM Guests WHERE booking_id = ? ", new GuestsMapper(), bookingId);
	}

	
	/**
	 * Get a list of all the database rows from the Guests table.
	 * @return A list of all the rows in the Guests table mapped to java objects
	 */
	public List<Guests> getAll()
	{
		List<Guests> guestList = jtemp.query("SELECT * FROM Guests", new GuestsMapper());
		
		return guestList;
	}

}
