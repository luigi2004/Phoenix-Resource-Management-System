package rms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import rms.model.Guests;

/**
 * This class is meant to be used with the PRMS database. It is a mapper class for the ResourceType table in the database.
 * The rows from the Guests table are mapped to java variables. 
 * @author Re: Syntellions
 *
 */
public class GuestsMapper implements RowMapper<Guests>
{
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	/**
	 * Mapper method. Used to map rows from the table to variables from the Guests class. 
	 */
	@Override
	public Guests mapRow(ResultSet result, int arg1) throws SQLException 
	{
		Guests guest = new Guests();
		guest.setBookingId(result.getInt(1));
		guest.setUserId(result.getInt(2));
		guest.setVisitorId(result.getInt(3));
		
		return guest;
	}
}
