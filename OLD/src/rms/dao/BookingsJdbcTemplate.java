package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.mapper.BookingsMapper;
import rms.model.Bookings;


/**
 * Jdbc template class for Booking objects
 * @author syntel
 *
 */
public class BookingsJdbcTemplate implements JdbcTemplateInterface<Bookings>{
	
	JdbcTemplate jtemp;
	ApplicationContext context;
	
	/**
	 * Constructor for jdbc template class of type Bookings (no args required)
	 */
	public BookingsJdbcTemplate() {
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}

	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#insert(java.lang.Object)
	 */
	@Override
	public int insert(Bookings bookings){
		int result = jtemp.update("insert into Bookings values(seq_booking.nextval, ?, ?, ?, ?, ?, ?)", 
				bookings.getResourceId(),
				bookings.getUserId(),
				bookings.getIsActive(),
				bookings.getBookedStartTime(),
				bookings.getBookedEndTime(),
				bookings.getDescription());										
		return result;
	}
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#delete(int)
	 */
	@Override
	public int delete(int bookingId){
		int result = jtemp.update("delete from Bookings where booking_id = ?", bookingId);
		
		return result;
	}
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#update(java.lang.Object)
	 */
	@Override
	public int update(Bookings booking){
		int result = jtemp.update("update Bookings set "
										+ "resource_id = ?, "
										+ "user_id = ?, "
										+ "is_active = ?, "
										+ "booked_start_time = ?, "
										+ "booked_end_time = ?, "
										+ "description = ? "
										+ "WHERE booking_id = ?"
									,
									booking.getResourceId(),
									booking.getUserId(),
									booking.getIsActive(),
									booking.getBookedStartTime(),
									booking.getBookedEndTime(),
									booking.getDescription(),
									booking.getBookingId()
									);
		
		return result;
	}
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#search(int)
	 */
	@Override
	public Bookings search(int bookingId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException{
		Bookings bk = jtemp.queryForObject("SELECT * FROM Bookings WHERE booking_id = ? ", new BookingsMapper(), bookingId);
		
		return bk;
	}
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#getAll()
	 */
	@Override
	public List<Bookings> getAll(){
		
		List<Bookings> BookingList = jtemp.query("SELECT * FROM Bookings", new BookingsMapper());
		
		return BookingList;
	}
	
	
	/**
	 * Get all the bookings that have a certain resource type
	 * @param ResourceTypeId The resource type id
	 * @return The list of all the bookings
	 */
	public List<Bookings> getAllByResourceType(int ResourceTypeId){
		
		List<Bookings> BookingList = jtemp.query("SELECT * FROM Bookings b, Resources r where "
				+ "resource_type_id=? AND b.resource_id = r.resource_id", new BookingsMapper(), ResourceTypeId);
		
		return BookingList;
	}
	
	
	/**
	 * Get all the bookings that have a certain resource
	 * @param ResourceId The resource id
	 * @return The list of all the bookings
	 */
	public List<Bookings> getAllByResourceId(int ResourceId){
		
		List<Bookings> BookingList = jtemp.query("SELECT * FROM Bookings b where "
				+ "resource_id=? ", new BookingsMapper(), ResourceId);
		
		return BookingList;
	}


}
