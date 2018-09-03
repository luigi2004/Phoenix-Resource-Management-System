package rms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import rms.model.Location;

public class LocationMapper implements RowMapper<Location>{

		@Override
		public Location mapRow(ResultSet result, int ar1) throws SQLException {
			Location location = new Location();
			location.setLocationId(result.getInt(1));
			location.setCity(result.getString(2));
			location.setState(result.getString(3));
			location.setZip(result.getString(4));
			location.setCountry(result.getString(5));
			location.setPhone(result.getString(6));
			
			return location;
		}

}
