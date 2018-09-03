package rms.queries;

import rms.model.Resources;
import rms.mapper.ResourcesMapper;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * This class retrieves information on various Locations and Resources with varying conditions.
 * @author Re: Syntellions
 * @see rms.model.Resources
 * @see rms.model.Location
 */
public class UniqueResourcesAndLocations {
	ApplicationContext context;
	JdbcTemplate jtemp;
	
	/**
	 * Constructor that establishes connection to database via Spring Framework.
	 */
	public UniqueResourcesAndLocations() {
        this.context = new ClassPathXmlApplicationContext("spring-dao.xml");
        this.jtemp = (JdbcTemplate)context.getBean("jt");
	}
	
	/**
	 * Finds all of the resources from a given location.
	 * @param locationId The ID of the location that one wants to search at.
	 * @return A list of Resources that are at the given location.
	 */
	public List<Resources> getResourcesByLocation(int locationId){
		return jtemp.query("SELECT * FROM Resources WHERE location_id = ? AND is_available >= 0 order By Resource_name", new ResourcesMapper(), locationId);
	}
	
	/**
	 * Finds all of the resources from a given location.
	 * @param locationId The ID of the location that one wants to search at.
	 * @return A list of Resources that are at the given location.
	 */
	public List<Resources> getResourcesByLocationForNonSuperUser(int locationId){
		return jtemp.query("SELECT * FROM Resources WHERE location_id = ? AND is_super_room = ? order By is_available DESC,Resource_name", new ResourcesMapper(), locationId,0);
	}
	
	/**
	 * Finds only the resources that are of a selected type from a given location.
	 * @param locationId The ID of the location that one wants to search at.
	 * @param resourceTypeId The ID of the type of resource that one is looking for.
	 * @return A list of Resources of the selected type that are at the given location.
	 */
	public List<Resources> getResourcesByLocationAndResourceType(int locationId, int resourceTypeId){
		return jtemp.query("SELECT * FROM Resources "
										+ "WHERE location_id = ? "
										+ "AND resource_type_id = ? " 
					                    + "AND is_available >= 0",
										new ResourcesMapper(),
										locationId,
										resourceTypeId);
	}
	
	/**
	 * Finds only the resources that are of a selected type from a given location.
	 * @param locationId The ID of the location that one wants to search at.
	 * @param resourceTypeId The ID of the type of resource that one is looking for.
	 * @return A list of Resources of the selected type that are at the given location.
	 */
	public List<Resources> getResourcesByLocationAndResourceTypeNonSuper(int locationId, int resourceTypeId){
		return jtemp.query("SELECT * FROM Resources "
										+ "WHERE location_id = ? "
										+ "AND resource_type_id = ? "
										+ "AND is_super_room = ?"
										+ " order by is_available DESC", 
										new ResourcesMapper(),
										locationId,
										resourceTypeId,
										0);
	}
	
	//change
	/**
	 * Gets a list of strings of every distinct Resource formatted as Resource Type ID + Resource Name.
	 * @return List of strings of Resource Type ID + Resource Name. Ex: "1102Conference 2".
	 */
	public List<String> getDistinctResourceName(){
		return jtemp.queryForList("SELECT DISTINCT resource_type_id||resource_name FROM resources", String.class);
	}
	
	/**
	 * Gets a list of strings of every Location formatted as Location ID + City.
	 * @return List of strings of Location ID + City. Ex: "100001Phoenix".
	 */
	public List<String> getLocationAndCity(){
		return jtemp.queryForList("SELECT location_id||city FROM locations", String.class);
	}
	
	/**
	 * Gets a list of the highest Resource ID.
	 * @return A list containing just the highest Resource ID number. 
	 */
	public List<Integer> getMostRecentResourceId() {
		return jtemp.queryForList("select max(resource_id) from resources", Integer.class);
		//Note: Should probably be changed to use queryForObject.
	}
	
	/**
	 * Gets the Resource Type Name from a given Resource Type ID.
	 * @param resourceTypeId The ID of the Resource one wants to find the name for.
	 * @return The name of the resource of the given Resource Type ID.
	 */
	public List<String> ResourceTypeName(int resourceTypeId) {
		return jtemp.queryForList("SELECT resource_type_name FROM resource_type WHERE resource_type_id=?", String.class, resourceTypeId );
	}
	
	/**
	 * Gets a list of strings of every Resource Type formatted as Resource Type ID + " " + Resource Type Name.
	 * @return List of strings of Resource Type ID + " " + Resource Type Name. Ex: "1002 Conference Room".
	 */
	public List<String> getResourceTypes(){
		return jtemp.queryForList("SELECT resource_type_id||' '||resource_type_name FROM resource_type", String.class);
	}
	
	/**
	 * Gets a list of strings of every Resource with a distinct formatting of Resource ID + " " + Resource Type ID + " " Resource Name.
	 * @return List of strings of Resource ID + " " + Resource Type ID + " " Resource Name. Ex: "1103 1002 Conference Room 3".
	 */
	public List<String> getDistinctResourceIdName(){
		return jtemp.queryForList("SELECT DISTINCT resource_id||' '||resource_type_id||' '||resource_name FROM resources", String.class);
	}
}
