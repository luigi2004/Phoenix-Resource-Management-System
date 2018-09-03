package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.mapper.FeatureTypeMapper;
import rms.model.FeatureType;

/**
 * Jdbc template class for Feature type objects
 */
public class FeatureTypeJdbcTemplate implements JdbcTemplateInterface<FeatureType>
{
	JdbcTemplate jtemp;
	ApplicationContext context;
	
	/**
	 * Constructor for jdbc template class of type FeatureType (no args required)
	 */
	public FeatureTypeJdbcTemplate() 
	{
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#insert(java.lang.Object)
	 */
	@Override
	public int insert(FeatureType ftype) 
	{
		int result = jtemp.update("INSERT INTO Feature_Type VALUES(seq_feature_type.nextval, ?, ?, ?)",
		ftype.getFeatureTypeName(),
		ftype.getFeatureTypeDescription(),
		ftype.getImgPath());

		return result;
	}
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#delete(int)
	 */
	@Override
	public int delete(int featureTypeId) 
	{
		int result = jtemp.update("DELETE FROM Feature_Type WHERE Feature_Type_Id = ?", featureTypeId);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#update(java.lang.Object)
	 */
	@Override
	public int update(FeatureType ftype)
	{
		int result = jtemp.update("UPDATE Feature_Type "
				+ "SET feature_type_name = ?, "
				+ "feature_type_description = ?, "
				+ "img_path = ? "
				+ "WHERE feature_type_id = ?",
				ftype.getFeatureTypeName(),
				ftype.getFeatureTypeDescription(),
				ftype.getImgPath(),
				ftype.getFeatureTypeId());

		return result;
	}
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#search(int)
	 */
	@Override
	public FeatureType search(int featureTypeId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException
	{
		FeatureType ft = jtemp.queryForObject("SELECT * FROM Feature_Type WHERE feature_type_id = ? ",
				new FeatureTypeMapper(), featureTypeId);
		return ft;
	}
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#getAll()
	 */
	@Override
	public List<FeatureType> getAll() 
	{
		List<FeatureType> featureTypeList = jtemp.query("SELECT * FROM Feature_Type", new FeatureTypeMapper());
		
		return featureTypeList;
	}
	

}
