package rms.queries;

import java.util.List;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import rms.mapper.RoomDropMapper;
import rms.model.FeaturesDropDown;

/**
 * Class containing queries involving features.
 * @author Re: Syntellions
 * @see rms.model.FeaturesDropDown
 * @see rms.mapper.RoomDropMapper
 */

public class FeatureQueries {
	
	private ApplicationContext context;
	private JdbcTemplate jtemp;
	
	
	/**
	 * Constructor that establishes connection to database via Spring Framework.
	 */
	public FeatureQueries() {
        this.context = new ClassPathXmlApplicationContext("spring-dao.xml");
        this.jtemp = (JdbcTemplate)context.getBean("jt");
	}
	
	/**
	 * Gets a list of "FeaturesDropDown", a special class made for this function that holds a
	 * feature type name, a quantity of that feature in a specific resource, and the name of
	 * that resource where that quantity of the feature is located. The function creates
	 * and returns a list of all the existing combinations in the database so that it can
	 * be used by the {@link rms.services.RoomService} function.
	 * 
	 * @return A list of FeaturesDropDown.
	 */
	public List<FeaturesDropDown> getFeatureNameAndQuantityByResouceId() {
		return jtemp.query("SELECT FT.Feature_Type_Name, F.Quantity, R.Resource_Name, R.Resource_ID FROM FEATURES F JOIN FEATURE_TYPE FT ON F.Feature_Type_ID = FT.Feature_Type_ID JOIN RESOURCES R ON F.Resource_ID = R.Resource_ID",
				new RoomDropMapper());
	} 	
	
}