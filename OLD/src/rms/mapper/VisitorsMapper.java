package rms.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import rms.model.Visitors;

/**
 * Class that implements row mapper for visitors
 * @author Re:Syntellions
 *
 */
public class VisitorsMapper implements RowMapper<Visitors>{

	/**
	 *
	 * Maps visitors row to visitors object	 
	 *
	 */
	@Override
	public Visitors mapRow(ResultSet result, int arg1) throws SQLException {
		Visitors visitor = new Visitors();
		visitor.setVisitorId(result.getInt(1));
		visitor.setVisitingName(result.getString(2));
		visitor.setEmail(result.getString(3));
		visitor.setPhone(result.getString(4));
		visitor.setCheckedInTime(result.getTimestamp(5));
		visitor.setLocationId(result.getInt(6));
		visitor.setBadgeId(result.getString(7));
		visitor.setCheckedOutTime(result.getTimestamp(8));
		visitor.setVisitPurpose(result.getString(9));
		visitor.setCompanyName(result.getString(10));
		visitor.setHasCheckedOut(result.getInt(11));
		visitor.setFirstName(result.getString(12));
		visitor.setLastName(result.getString(13));
		visitor.setScheduledMeetingTime(result.getString(14));
		visitor.setHasIdProof(result.getInt(15));
		return visitor;
	}

}
