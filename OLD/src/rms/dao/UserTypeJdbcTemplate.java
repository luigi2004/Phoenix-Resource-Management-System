package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.mapper.UserTypeMapper;
import rms.model.UserType;

public class UserTypeJdbcTemplate implements JdbcTemplateInterface<UserType>{
	JdbcTemplate jtemp;
	ApplicationContext context;
	
	public UserTypeJdbcTemplate() {
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#insert(java.lang.Object)
	 */
	@Override
	public int insert(UserType userTypeToInsert) {
		
		return jtemp.update("INSERT INTO user_type VALUES (seq_user_type.nextval, ?, ?)", 
				userTypeToInsert.getUserTypeName(), userTypeToInsert.getUserTypeDescription());
	}

	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#delete(int)
	 */
	@Override
	public int delete(int userTypeIdToDelete) {
		// TODO Auto-generated method stub
		return jtemp.update("DELETE FROM user_type WHERE user_type_id = ?", userTypeIdToDelete);
	}

	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#update(java.lang.Object)
	 */
	@Override
	public int update(UserType userTypeToUpdate) {
		// TODO Auto-generated method stub
		return jtemp.update("UPDATE user_type SET "
							+ "user_type_name = ?,"
							+ "user_type_description = ? "
							+ "WHERE user_type_id = ?", 
							userTypeToUpdate.getUserTypeName(),
							userTypeToUpdate.getUserTypeDescription(),
							userTypeToUpdate.getUserTypeId());
	}

	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#search(int)
	 */
	@Override
	public UserType search(int userTypeId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
		// TODO Auto-generated method stub
		return jtemp.queryForObject("SELECT * FROM user_type WHERE user_type_id = ?", new UserTypeMapper(), userTypeId);
	}

	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#getAll()
	 */
	@Override
	public List<UserType> getAll() {
		// TODO Auto-generated method stub
		return jtemp.query("SELECT * FROM user_type", new UserTypeMapper());
	}

}
