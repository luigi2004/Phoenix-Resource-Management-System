package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.mapper.ResourceTypeMapper;
import rms.model.ResourceType;

public class ResourceTypeJdbcTemplate implements JdbcTemplateInterface<ResourceType>
{
	
	JdbcTemplate jtemp;
	ApplicationContext context;
	
	public ResourceTypeJdbcTemplate() 
	{
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}

	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#insert(java.lang.Object)
	 */
	@Override
	public int insert(ResourceType resourceTypeToInsert) 
	{
		
		return jtemp.update("insert into Resource_Type "
				+ "values(seq_resource_type.nextval,?,?,?)", 
				resourceTypeToInsert.getResourceTypeName(), 
				resourceTypeToInsert.getResourceTypeDescription(),
				resourceTypeToInsert.getImgPath());
	}

	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#delete(int)
	 */
	@Override
	public int delete(int resourceTypeIdToDelete) 
	{
		
		return jtemp.update("delete from Resource_Type where resource_type_id = ?", resourceTypeIdToDelete);
	}

	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#update(java.lang.Object)
	 */
	@Override
	public int update(ResourceType resourceTypeToUpdate) 
	{

		return jtemp.update("update Resource_Type "
							+ "set "
							+ "resource_type_name = ?, "
							+ "resource_type_description = ?, "
							+ "img_path = ? "
							+ "where "
							+ "resource_type_id = ?",
							resourceTypeToUpdate.getResourceTypeName(),
							resourceTypeToUpdate.getResourceTypeDescription(),
							resourceTypeToUpdate.getImgPath(),
							resourceTypeToUpdate.getResourceTypeId());
	}

	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#search(int)
	 */
	@Override
	public ResourceType search(int resourceTypeId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException 
	{
		
		return jtemp.queryForObject("SELECT * FROM Resource_Type WHERE resource_type_id = ?", new ResourceTypeMapper(), resourceTypeId);
	}

	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#getAll()
	 */
	@Override
	public List<ResourceType> getAll() 
	{

		return jtemp.query("SELECT * FROM resource_type", new ResourceTypeMapper());
	}

}
