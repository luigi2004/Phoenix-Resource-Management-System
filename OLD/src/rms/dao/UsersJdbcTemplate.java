package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.dao.JdbcTemplateInterface;
import rms.model.Users;
import rms.mapper.UsersMapper;

public class UsersJdbcTemplate implements JdbcTemplateInterface<Users>{
	JdbcTemplate jtemp;
	ApplicationContext context;
	
	public UsersJdbcTemplate() {
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#insert(java.lang.Object)
	 */
	@Override
	public int insert(Users userToInsert) {
		int result = jtemp.update("INSERT INTO Users VALUES(seq_user.nextVal, ?, ?, ?, ?, ?, ?, ?, ?)", 
																userToInsert.getUserName(),
																userToInsert.getUserEmail(),
																userToInsert.getUserPassword(),
																userToInsert.getUserType(),
																userToInsert.getUserPhone(),
																userToInsert.getLocationId(),
																userToInsert.getFirst_name(),
																userToInsert.getLast_name());
		return result;

	}

	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#delete(int)
	 */
	@Override
	public int delete(int userIdToDelete) {
		int result = jtemp.update("DELETE FROM users WHERE user_id = ?", userIdToDelete);
		
		return result;	
	}

	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#update(java.lang.Object)
	 */
	@Override
	public int update(Users userToUpdate) {
		int result = jtemp.update("UPDATE users "
								+ "SET "
									+ "user_name = ?, "
									+ "user_email = ?, "
									+ "user_password = ?, "
									+ "user_type = ?, "
									+ "user_phone = ?, "
									+ "location_id = ?, "
									+ "user_first_name = ?, "
									+ "user_last_name = ? "
								+ "WHERE user_id = ?",
									userToUpdate.getUserName(),
									userToUpdate.getUserEmail(),
									userToUpdate.getUserPassword(),
									userToUpdate.getUserType(),
									userToUpdate.getUserPhone(),
									userToUpdate.getLocationId(),
									userToUpdate.getFirst_name(),
									userToUpdate.getLast_name(),
									userToUpdate.getUserId());
		return result;
	}

	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#search(int)
	 */
	@Override
	public Users search(int userId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
		Users user = jtemp.queryForObject("SELECT * FROM Users WHERE user_id = ? ", new UsersMapper(), userId);	
		return user;
	}

	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#getAll()
	 */
	@Override
	public List<Users> getAll() {
		List<Users> usersList = jtemp.query("SELECT * FROM Users", new UsersMapper());
		return usersList;
	}

}
