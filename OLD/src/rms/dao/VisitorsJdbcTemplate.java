package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.mapper.VisitorsMapper;
import rms.model.Visitors;


public class VisitorsJdbcTemplate implements JdbcTemplateInterface<Visitors>{
	JdbcTemplate jtemp;
	ApplicationContext context;
	
	public VisitorsJdbcTemplate() {
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#insert(java.lang.Object)
	 */
	@Override
	public int insert(Visitors visitorToInsert) {
		String statement = "INSERT INTO VISITORS VALUES(seq_visitors.nextval, "
							+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int result = jtemp.update(statement, 
								visitorToInsert.getVisitingName(),
								visitorToInsert.getEmail(),
								visitorToInsert.getPhone(),
								visitorToInsert.getCheckedInTime(),
								visitorToInsert.getLocationId(),
								visitorToInsert.getBadgeId(),
								visitorToInsert.getCheckedOutTime(),
								visitorToInsert.getVisitPurpose(),
								visitorToInsert.getCompanyName(),
								visitorToInsert.getHasCheckedOut(),
								visitorToInsert.getFirstName(),
								visitorToInsert.getLastName(),
								visitorToInsert.getScheduledMeetingTime(),
								visitorToInsert.getHasIdProof());
		return result;
	}

	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#delete(int)
	 */
	@Override
	public int delete(int visitorIdToDelete) {
		
		return jtemp.update("DELETE FROM visitors where visitor_id = ?", visitorIdToDelete);
	}
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#update(java.lang.Object)
	 */
	@Override
	public int update(Visitors visitorToUpdate) {
		String statement = "UPDATE visitors SET "
							+ "visiting_name = ?, "
							+ "email = ?, "
							+ "phone = ?, "
							+ "checked_in_time = ?, "
							+ "location_id = ?, "
							+ "badge_id = ?, "
							+ "checked_out_time = ?, "
							+ "visit_purpose = ?, "
							+ "company_name = ?, "
							+ "has_checked_out = ?, "
							+ "first_name = ?, "
							+ "last_name = ?, "
							+ "scheduled_meeting_time = ?, "
							+ "has_id_proof = ? "
							+ "WHERE visitor_id = ?";
		
		int result = jtemp.update(statement, 
				visitorToUpdate.getVisitingName(),
				visitorToUpdate.getEmail(),
				visitorToUpdate.getPhone(),
				visitorToUpdate.getCheckedInTime(),
				visitorToUpdate.getLocationId(),
				visitorToUpdate.getBadgeId(),
				visitorToUpdate.getCheckedOutTime(),
				visitorToUpdate.getVisitPurpose(),
				visitorToUpdate.getCompanyName(),
				visitorToUpdate.getHasCheckedOut(),
				visitorToUpdate.getFirstName(),
				visitorToUpdate.getLastName(),
				visitorToUpdate.getScheduledMeetingTime(),
				visitorToUpdate.getHasIdProof(),
				visitorToUpdate.getVisitorId());
		
		
		return result;
	}

	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#search(int)
	 */
	@Override
	public Visitors search(int visitorId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException{		
		
		return jtemp.queryForObject("SELECT * FROM visitors WHERE visitor_id = ?", new VisitorsMapper(), visitorId);
	}

	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#getAll()
	 */
	@Override
	public List<Visitors> getAll() {

		return jtemp.query("SELECT * FROM visitors", new VisitorsMapper());
	}

}
