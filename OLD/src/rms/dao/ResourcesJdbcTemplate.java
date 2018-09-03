package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.model.*;
import rms.mapper.*;

/**
 * Java Database Connectivity Template that utilizes Spring Framework to manage Resources through a database connection.
 * This class contains methods for inserting, deleting, updating, and getting all from the Resources table.
 * Other methods include searching for a specific Resource based on a Resource ID, getting a list of resources by resource type ID,
 * and getting a list of feature types by resource ID. 
 * @author Re: Syntellions
 *
 */
public class ResourcesJdbcTemplate implements JdbcTemplateInterface<Resources>
{
	/**
	 * Local variable for the JdbcTemplate
	 */
	JdbcTemplate jtemp;
	/**
	 * Local variable for the ApplicationContext
	 */
	ApplicationContext context;
	
	
	/**
	 * Constructor that establishes the connection with the database
	 */
	public ResourcesJdbcTemplate()
	{
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}
	
	
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#insert(java.lang.Object)
	 */
	@Override
	public int insert(Resources resource)
	{
		int result = jtemp.update("insert into Resources values(seq_resource.nextval, ?, ?, ?, ?, ?, ?, ?, ?)", 
														resource.getResourceName(),
														resource.getResourceDescription(),
														resource.getResourceRoomNumber(),
														resource.getResourceTypeId(),
														resource.getLocationId(),
														resource.getIsAvailable(),
														resource.getIsSuperRoom(),
														resource.getCapacity()
														);
		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#delete(int)
	 */
	@Override
	public int delete(int resourceId)
	{
		int result = jtemp.update("delete from Resources where resource_id = ?", resourceId);
		
		return result;
	}
	
	/** 
     * Sets the is_available column to -1 (to signify that the resource is deleted) where the resourceId 
     * equals the resourceId that was passed in. 
     * @param resourceId The Id of the resource that is being set to -1. 
     * @return The number of columns updated. Should only be one column that is updated if the resource exists and 0 if 
     * if it doesn't exist.
     */ 
    public int setIsAvailableToDelete(int resourceId) { 
        return jtemp.update("UPDATE resources SET is_available = -1 WHERE resource_id = ?", resourceId); 
    }
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#update(java.lang.Object)
	 */
	@Override
	public int update(Resources resource){
		int result = jtemp.update("update Resources "
									+ "set resource_name = ?, "
									+ "resource_description = ?, "
									+ "resource_room_number = ?, "
									+ "resource_type_id = ?, "
									+ "location_id = ?, "
									+ "is_available = ?, "
									+ "is_super_room = ?, "
									+ "capacity = ? "
									+ "where resource_id = ?", 
									resource.getResourceName(),
									resource.getResourceDescription(),
									resource.getResourceRoomNumber(),
									resource.getResourceTypeId(),
									resource.getLocationId(),
									resource.getIsAvailable(),
									resource.getIsSuperRoom(),
									resource.getCapacity(),
									resource.getResourceId());
		
		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#search(int)
	 */
	@Override
	public Resources search(int resourceId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException
	{
		Resources rs = jtemp.queryForObject("SELECT * FROM Resources WHERE resource_id = ? ", new ResourcesMapper(), resourceId);
		
		return rs;
	}
	
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#getAll()
	 */
	@Override
	public List<Resources> getAll()
	{
		
		List<Resources> resourcesList = jtemp.query("SELECT * FROM Resources", new ResourcesMapper());
		
		return resourcesList;
	}

	/**
	 * Method to return a list of Resources rows based on a Resource Type ID
	 * @param resourceTypeId int Resource Type ID
	 * @return The list of Resources
	 */
	public List<Resources> resourcesByResourceType(int resourceTypeId)
	{
		String sql = "select * from resources where resource_type_id = ?";
		return jtemp.query(sql, new ResourcesMapper(), resourceTypeId);
	}
	
	/**
	 * Method to return a list of Feature Types based on a Resource Type ID
	 * @param resourceId int Resource ID
	 * @return The list of Feature Types
	 */
	public List<FeatureType> getFeatures(int resourceId)
	{
		String sql =	"select feature_type.feature_type_id, feature_type.feature_type_name, feature_type.feature_type_description, feature_type.img_path " + 
						"from resources, features, feature_type " +
						"where resources.resource_id = features.resource_id " + 
						"and features.feature_type_id = feature_type.feature_type_id " + 
						"and resources.resource_id = ?";
		return jtemp.query(sql, new FeatureTypeMapper(), resourceId);
	}
}
