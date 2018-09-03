package rms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import rms.model.ResourceType;


/**
 * This class is meant to be used with the PRMS database. It is a mapper class for the ResourceType table in the database.
 * The rows from the ResourceType table are mapped to java variables. 
 * @author Re: Syntellions
 *
 */
public class ResourceTypeMapper implements RowMapper<ResourceType>{

	/** 
	 * Maps ResourceType row to ResourceType object.
	 */
	@Override
	public ResourceType mapRow(ResultSet result, int arg1) throws SQLException {

		ResourceType resType = new ResourceType();
		resType.setResourceTypeId(result.getInt(1));
		resType.setResourceTypeName(result.getString(2));
		resType.setResourceTypeDescription(result.getString(3));
		resType.setImgPath(result.getString(4));
		return resType;
	}

}
