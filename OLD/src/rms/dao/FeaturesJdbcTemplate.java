package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.model.Features;
import rms.mapper.FeaturesMapper;

/*     -------WARNING-------
 Does not implement JdbcTemplateInterface
 Has unique methods
 Please read comments on methods below
 for more information
*/

/**
 * Jdbc template class for Feature objects
 */
public class FeaturesJdbcTemplate
{

	JdbcTemplate jtemp;
	ApplicationContext context;
	
	
	/**
	 * Constructor for jdbc template class of type Feature (no args required)
	 */
	public FeaturesJdbcTemplate() {
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}
	
	/**
	 * Will map a java object to a database row and insert it into the the database.
	 * @param feat A java object containing all the data you want to be inserted into the database.
	 * @return The number of rows inserted. (Either 0 or 1)
	 */
	public int insert(Features feat)
	{
		int result = jtemp.update("INSERT INTO Features VALUES(?, ?, ?)",
		feat.getFeatureTypeId(),
		feat.getQuantity(),
		feat.getResourceId());

		return result;
	}
	
	/**
	 * Will delete a database row that has a matching feature type id and resource id
	 * @param ftypeId The feature type id
	 * @param resourceId The resource id
	 * @return The number of deleted rows.
	 */
	public int deleteByFeatureTypeIdAndResourceId(int ftypeId, int resourceId) 
	{
		int result = jtemp.update("DELETE FROM Features WHERE Feature_Type_Id= ? "
				+ "AND Resource_Id = ?", ftypeId, resourceId);
		return result;
	}

	/**
	 * Will delete a database row with the following resource id
	 * @param resourceId The resource id
	 * @return The number of deleted rows.
	 */
	public int deleteByResourceId(int resourceId) 
	{
		int result = jtemp.update("DELETE FROM Features WHERE Resource_Id = ?", resourceId);
		return result;
	}

	/**
	 * Will update an existing row in the database.
	 * @param feat The java object containing all the new values to be updated. Will update
	 * the row with the same ID as the java object.
	 * @return The number of rows updated (Either 0 or 1)
	 */
	public int update(Features feat) 
	{
		int result = jtemp.update("UPDATE Features "
				+ "SET quantity = ? "
				+ "WHERE feature_type_id = ?, "
				+ "AND resource_id = ?",
				feat.getQuantity(),
				feat.getFeatureTypeId(),
				feat.getResourceId());

		return result;
	}

	/**
	 * Will search for a feature by feature type id and resource id
	 * @param ftypeId The feature type ID
	 * @param resourceId The resource ID
	 * @return The feature row mapped to a java object.
	 */
	public Features searchByFeatureTypeIdAndResourceId(int ftypeId, int resourceId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException
	{
		Features feat = jtemp.queryForObject("SELECT * FROM Features "
				+ "WHERE feature_type_id = ? AND resource_id = ?",
				new FeaturesMapper(), ftypeId, resourceId);
		return feat;
	}
	
	/**
	 * Get a list of all the features from the table by feature type ID.
	 * @param ftypeId The feature type ID
	 * @return The list of feature objects
	 */
	public Features searchByFeatureTypeId(int ftypeId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException
	{
		Features feat = jtemp.queryForObject("SELECT * FROM Features WHERE feature_type_id = ?",new FeaturesMapper(), ftypeId);
		return feat;
	}
	
	/**
	 * Get a list of all the features from the table by resource ID.
	 * @param resourceId The resource ID
	 * @return The list of feature objects
	 */
	public Features searchByResourceId(int resourceId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException
	{
		Features feat = jtemp.queryForObject("SELECT * FROM Features WHERE resource_id = ?",new FeaturesMapper(), resourceId);
		return feat;
	}
	
	/**
	 * Get a list of all the database rows from the table.
	 * @return A list of all the rows in a table mapped to java objects
	 */
	public List<Features> getAll() 
	{
		List<Features> featuresList = jtemp.query("SELECT * FROM Features", new FeaturesMapper());
		
		return featuresList;
	}
	
	
}
