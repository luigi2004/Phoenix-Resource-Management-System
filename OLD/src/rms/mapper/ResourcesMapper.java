package rms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import rms.model.Resources;

/**
 * This class is meant to be used with the PRMS database. It is a mapper class for the Resources table in the database.
 * The rows from the Resources table are mapped to java variables. 
 * @author Re: Syntellions
 *
 */
public class ResourcesMapper implements RowMapper<Resources>{

		/** 
		 * Maps Resources row to Resources object.
		 */
		@Override
		public Resources mapRow(ResultSet result, int ar1) throws SQLException {
			Resources res = new Resources();
			res.setResourceId(result.getInt(1));
			res.setResourceName(result.getString(2));
			res.setResourceDescription(result.getString(3));
			res.setResourceRoomNumber(result.getString(4));
			res.setResourceTypeId(result.getInt(5));
			res.setLocationId(result.getInt(6));
			res.setIsAvailable(result.getInt(7));
			res.setIsSuperRoom(result.getInt(8));
			res.setCapacity(result.getInt(9));
			return res;
		}

}
