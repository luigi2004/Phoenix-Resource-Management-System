package rms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import rms.model.Users;

/**
 * Class that implements row mapper for Users
 * @author Re:Syntellions
 *
 */
public class UsersMapper implements RowMapper<Users>{
	/**
	 * Maps users row into users object
	 */
	@Override
	public Users mapRow(ResultSet result, int arg1) throws SQLException {
		Users user = new Users();
		user.setUserId(result.getInt(1));
		user.setUserName(result.getString(2));
		user.setUserEmail(result.getString(3));
		user.setUserPassword(result.getString(4));
		user.setUserType(result.getInt(5));
		user.setUserPhone(result.getString(6));
		user.setLocationId(result.getInt(7));
		user.setFirst_name(result.getString(8));
		user.setLast_name(result.getString(9));
		return user;
	}

}
