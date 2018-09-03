package rms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import rms.model.FeaturesDropDown;

/**
 * This class is meant to be used with the PRMS database. It is a mapper class for the FeatureType , Features, and Resources tables in the database.
 * The rows from the FeatureType and Resources tables are mapped to java variables. 
 * @author Re: Syntellions
 *
 */
public class RoomDropMapper implements RowMapper<FeaturesDropDown>
{
	/** 
	 * Maps FeatureType, Features, and Resources rows to FeaturesDropDown object.
	 */
	@Override
	public FeaturesDropDown mapRow(ResultSet result, int arg1) throws SQLException 
	{
		FeaturesDropDown feat = new FeaturesDropDown();
		feat.setFeatureName(result.getString(1));
		feat.setQuantity(result.getInt(2));
		feat.setResourceName(result.getString(3));
		feat.setResourceID(result.getInt(4));
		
		return feat;
	}
}

