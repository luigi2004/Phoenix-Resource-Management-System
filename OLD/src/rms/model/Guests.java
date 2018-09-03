package rms.model;

/**
 * This class is meant to be used with the PRMS database. It is a model class for the Guests table in the database
 * Guests are recorded based on a Booking ID.
 * Guests with a User ID are Syntel employees. 
 * Guests with a Visitor ID are visitors that are not based in the default location
 * or are not Syntel employees. 
 * Guests is used by GuestsMapper class to create Guest objects,
 * therefore a constructor is not needed. 
 * @author Re: Syntellions
 *
 */
public class Guests 
{
	/**
	 * Unique integer created each time a booking is created. 
	 */
	private int bookingId;
	/**
	 * Unique integer created for each local Syntel employee.
	 */
	private int userId;
	/**
	 * Unique integer created for visiting Syntel employees or other visitors that are not Syntel employees.
	 */
	private int visitorId;
	
	
	/**
	 * Parameterized constructor for Guests
	 * @param bookingId Unique integer created each time a booking is created. 
	 * @param userId Unique integer created for each local Syntel employee.
	 * @param visitorId Unique integer created for visiting Syntel employees or other visitors that are not Syntel employees.
	 */
	public Guests(int bookingId, int userId, int visitorId)
	{
		this.bookingId = bookingId;
		this.userId = userId;
		this.visitorId = visitorId;
	}

	/**
	 * Empty constructor for Guests
	 */
	public Guests()
	{
		super();
	}

	@Override
	public String toString() 
	{
		return "Guests [bookingId=" + bookingId + ", userId=" + userId + ", visitorId=" + visitorId + "]";
	}
	
	// Getters and setters --------------------------
	/**
	 * Getter method that gets the value of Booking ID
	 * @return integer bookingId
	 */
	public int getBookingId() 
	{
		return bookingId;
	}
	
	/**
	 * Setter method that sets the value of Booking ID
	 * @param bookingId integer Booking ID
	 */
	public void setBookingId(int bookingId) 
	{
		this.bookingId = bookingId;
	}
	
	/**
	 * Getter method that gets the value of User ID
	 * @return integer userId
	 */
	public int getUserId() 
	{
		return userId;
	}
	
	/**
	 * Setter method that sets the value of User ID
	 * @param userId integer User ID
	 */
	public void setUserId(int userId) 
	{
		this.userId = userId;
	}

	/**
	 * Getter method that gets the value of Visitor ID
	 * @return integer visitorId
	 */
	public int getVisitorId() 
	{
		return visitorId;
	}
	
	/**
	 * Setter method that sets the value of Visitor ID
	 * @param visitorId integer Visitor ID
	 */
	public void setVisitorId(int visitorId) 
	{
		this.visitorId = visitorId;
	}
	
	
}
