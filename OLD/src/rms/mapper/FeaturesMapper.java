package rms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import rms.model.Features;


/**
 * Class that implements row mapper for features
 * @author syntel
 *
 */
public class FeaturesMapper implements RowMapper<Features>
{
	/**
	 * Maps feature row to feature object	 
	 */
	@Override
	public Features mapRow(ResultSet result, int arg1) throws SQLException 
	{
		Features feat = new Features();
		feat.setFeatureTypeId(result.getInt(1));
		feat.setQuantity(result.getInt(2));
		feat.setResourceId(result.getInt(3));
		
		return feat;
	}
}
