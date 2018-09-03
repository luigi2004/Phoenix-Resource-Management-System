package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.model.Resources;
import rms.model.Users;
import rms.model.Visitors;
import java.sql.Timestamp;



public class JdbcTemplateDriver {
    ApplicationContext context;
    JdbcTemplate jtemp;
    public JdbcTemplateDriver(){
        context = new ClassPathXmlApplicationContext("spring-dao.xml");
        jtemp = (JdbcTemplate)context.getBean("jt");
    }
    
    public void testResources(){
    	ResourcesJdbcTemplate resJTemp = new ResourcesJdbcTemplate();
    	List<Resources> resourcesList;
    	Resources res = (Resources) context.getBean("resource");
    	
    	System.out.println("Before Insert");
    	resJTemp.insert(res);
    	resourcesList = resJTemp.getAll();

    	for(Resources resource:resourcesList){
    		System.out.println(resource.toString());
    	}
    	res.setResourceName("Majde's Room");
    	
    	System.out.println("Before update");
    	if (resJTemp.update(res) <= 0){
    		System.out.println("id doesn't exist");
    		res.setResourceId(2011);
    		resJTemp.update(res);
    	}
    	
    	resourcesList = resJTemp.getAll();
    	
    	
    	for(Resources resource:resourcesList){
    		System.out.println(resource.toString());
    	}

    	System.out.println("Before Delete");
    	if(resJTemp.delete(2001) <= 0){
    		System.out.println("resource doesn't exit");
    		resJTemp.delete(2011);
    	}
    	
    	resourcesList = resJTemp.getAll();
    	
    	for(Resources resource:resourcesList){
    		System.out.println(resource.toString());
    	}
    	
    	System.out.println("Searching for ID");
    	Resources foundResource = resJTemp.search(1001);
    	System.out.println(foundResource.toString());
    	
    	try{
    	Resources fResource = resJTemp.search(1);
    	}catch (EmptyResultDataAccessException e){
    		System.out.println("doesn't exist");
    	}
    }
    
    public void testVisitors() {
    	VisitorsJdbcTemplate vtemp = new VisitorsJdbcTemplate();
    	
    	Visitors visitor = new Visitors();
		visitor.setVisitorId(1000);
		visitor.setVisitingName("Bob");
		visitor.setEmail("bob@mail.com");
		visitor.setPhone("03000");
		visitor.setCheckedInTime(new Timestamp(System.currentTimeMillis()));
		visitor.setLocationId(12);
		visitor.setBadgeId("14");
		visitor.setCheckedOutTime(new Timestamp(System.currentTimeMillis()));
		visitor.setVisitPurpose("llama");
		visitor.setCompanyName("llama");
		visitor.setHasCheckedOut(1);
		visitor.setFirstName("Poop");
		visitor.setLastName("Poop");
		visitor.setScheduledMeetingTime("13 oclock");
		visitor.setHasIdProof(0);
		
		
		
		vtemp.delete(visitor.getVisitorId());
		List<Visitors> visitors = vtemp.getAll();
		
		for(Visitors v:visitors) {
			System.out.println(v.toString());
		}
    }
    public void testUsers() {
    	UsersJdbcTemplate uTemp = new UsersJdbcTemplate();
    	Users user = new Users();
		user.setUserId(106);
		user.setUserName("ma2");
		user.setUserEmail("ma2@mail.com");
		user.setUserPassword("pass");
		user.setUserType(2);
		user.setUserPhone("323-131-3123");
		user.setLocationId(100001);
		user.setFirst_name("Bob");
		user.setLast_name("Pops");
		uTemp.update(user);
		

    	List<Users> users = uTemp.getAll();
    	
    	for(Users u:users) {
    		System.out.println(u.toString());
    	}
    }
    public void testBookings() {
    	BookingsJdbcTemplate btemp = new BookingsJdbcTemplate();
    	
    }
    public static void main(String args[]){
    		//new JdbcTemplateDriver().testResources();
    		new JdbcTemplateDriver().testVisitors();
        	//new JdbcTemplateDriver().testUsers();
    		//new JdbcTemplateDriver().testBookings();
    }
    
    
}
