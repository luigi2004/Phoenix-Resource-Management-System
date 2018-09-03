package rms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import rms.model.FeatureType;

public class FeatureTypeMapper implements RowMapper<FeatureType>
{
	/**
	 * Maps feature type row to feature type object
	 */
	@Override
	public FeatureType mapRow(ResultSet result, int arg1) throws SQLException 
	{
		FeatureType ftype = new FeatureType();
		ftype.setFeatureTypeId(result.getInt(1));
		ftype.setFeatureTypeName(result.getString(2));
		ftype.setFeatureTypeDescription(result.getString(3));
		ftype.setImgPath(result.getString(4));
		
		return ftype;
	}
}
