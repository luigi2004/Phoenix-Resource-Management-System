package rms.queries;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * UtilizationQueries is a class that returns the utilization of a single resource,all the resources of a resource_type, or all resources
 * in the table by day, by week, or by month. The utilization is returned as a decimal percentage. 
 * @author Re:Syntellions
 *
 */
public class UtilizationQueries
{
	private ApplicationContext context;
	private JdbcTemplate jtemp;
	
	/**
	 * Constructor that makes the connection to the database using Spring JdbcTemplate.
	 */
	public UtilizationQueries()
	{
        this.context = new ClassPathXmlApplicationContext("spring-dao.xml");
        this.jtemp = (JdbcTemplate)context.getBean("jt");
	}
	/**
	 * Queries for all of the bookings within a day for a single resource. Takes the sum of all the differences of
	 * booked_end_time minus book_start_time and divides it by eight to return a percentage of utilization for that day.
	 * 
	 * In layman terms it takes all of the hours that were booked in a single day for a single room and divides
	 * it by 8 (the number of available hours in a day to book) to return the percentage of utilization for that room. 
	 * @param resourceId Used to query the resource you wish to see the utilization for.
	 * @param date Used to limit the scope of utilization to the date provided.
	 * @return returns a double that represents the percentage of utilization.
	 * @throws EmptyResultDataAccessException if the resource does not exist or if the date that is being queried does not exist.
	 */
	public Double dailyUtilizationByResourceId(int resourceId, String date) throws EmptyResultDataAccessException
    {
        return jtemp.queryForObject("SELECT SUM(EXTRACT(hour from (BOOKED_END_TIME - BOOKED_START_TIME)))/8 FROM bookings" + 
                " WHERE resource_id = ? AND TRUNC(booked_start_time) = TO_DATE(?,'dd-MM-yyyy HH24:MI') AND is_active > 0" + 
                " GROUP BY TRUNC(booked_start_time)", 
                Double.class,
                resourceId,
                date);
		

    }
	/**
	 * 
	 * Queries for all of the bookings within a week for a single resource. Takes the sum of all the differences of
	 * booked_end_time minus book_start_time and divides it by 40 to return a percentage of utilization for that week.
	 * 
	 * In layman terms it takes all of the hours that were booked in a week for a single room and divides
	 * it by 40 (the number of available hours in a week to book) to return the percentage of utilization for that room. 
	 * @param resourceId Used to query the resource you wish to see the utilization for.
	 * @param startDate Used to limit the scope of utilization to the date provided.
	 * @param endDate Used to limit the scope of utilization to the date provided.
	 * @return returns a double that represents the percentage of utilization.
	 * @throws EmptyResultDataAccessException
	 */
	public Double weeklyUtilizationByResourceId(int resourceId, String startDate, String endDate) throws EmptyResultDataAccessException
	{

		return jtemp.queryForObject("select sum(extract(hour from (BOOKED_END_TIME-BOOKED_START_TIME)))/40 from bookings " + 
				"where resource_id=? AND trunc(booked_start_time) BETWEEN TO_DATE(?,'dd-MM-yyyy HH24:MI') AND" + 
				" TO_DATE(?,'dd-MM-yyyy HH24:MI') AND is_active > 0" + 
				" group by resource_id",
				Double.class,
				resourceId,
				startDate,
				endDate);
	}
	/**
	 * 
	 * Queries for all of the bookings within a month for a single resource. Takes the sum of all the differences of
	 * booked_end_time minus book_start_time and divides it by 160 to return a percentage of utilization for that month.
	 * 
	 * In layman terms it takes all of the hours that were booked in a month for a single room and divides
	 * it by 160 (the average number of available hours in a month to book) to return the percentage of utilization for that room. 
	 * @param resourceId Used to query the resource you wish to see the utilization for.
	 * @param startDate Used to limit the scope of utilization to the date provided.
	 * @param endDate Used to limit the scope of utilization to the date provided.
	 * @return returns a double that represents the percentage of utilization.
	 * @throws EmptyResultDataAccessException
	 */	
	public Double monthlyUtilizationByResourceId(int resourceId, String startDate, String endDate) throws EmptyResultDataAccessException
	{
		return jtemp.queryForObject("select sum(extract(hour from (BOOKED_END_TIME-BOOKED_START_TIME)))/160 from bookings\r\n" + 
				"where resource_id=? AND trunc(booked_start_time) BETWEEN TO_DATE(?,'dd-MM-yyyy HH24:MI') AND\r\n" + 
				" TO_DATE(?,'dd-MM-yyyy HH24:MI') AND is_active > 0" + 
				" group by resource_id", 
				Double.class,
				resourceId,
				startDate,
				endDate);
	}
	
	/**
	 * Queries for all of the bookings within a day for all the resources 
	 * of a single resource type. Takes the sum of all the differences of
	 * booked_end_time minus book_start_time and divides it by eight 
	 * times the number of resources of the resource type provided
	 * to return a percentage of utilization for that day.
	 * 
	 * In layman terms it takes all of the hours that were booked in a single day for all the rooms of a single resource type and divides
	 * it by 8 (the number of available hours in a day to book) times the number of resources of that resource type
	 * to return the percentage of utilization for those rooms. 
	 * @param resourceTypeId Used to query the resources of a certain resource type you wish to see the utilization for.
	 * @param date Used to limit the scope of utilization to the date provided.
	 * @return returns a double that represents the percentage of utilization.
	 * @throws EmptyResultDataAccessException
	 */
	public Double dailyUtilizationByResourceTypeId(int resourceTypeId, String date) throws EmptyResultDataAccessException
	{
		return jtemp.queryForObject("select sum(extract(hour from (BOOKED_END_TIME-BOOKED_START_TIME)))/(8*(SELECT COUNT(*) FROM RESOURCES WHERE resource_type_id = ?)) from bookings inner join resources using (resource_id)\r\n" + 
				"where resource_type_id=? AND trunc(booked_start_time) = TO_DATE(?,'dd-MM-yyyy HH24:MI') AND is_active > 0 " + 
				"group by resource_type_id", 
				Double.class,
				resourceTypeId,
				resourceTypeId,
				date);		
	}
	/**
	 * Queries for all of the bookings within a week for all the resources 
	 * of a single resource type. Takes the sum of all the differences of
	 * booked_end_time minus book_start_time and divides it by 40 
	 * times the number of resources of the resource type provided
	 * to return a percentage of utilization for that week.
	 * 
	 * In layman terms it takes all of the hours that were booked in a week for all the rooms of a single resource type and divides
	 * it by 40 (the number of available hours in a week to book) times the number of resources of that resource type
	 * to return the percentage of utilization for those rooms. 
	 * @param resourceTypeId Used to query the resources of a certain resource type you wish to see the utilization for.
	 * @param startDate Used to limit the scope of utilization to the date provided.
	 * @param endDate Used to limit the scope of utilization to the date provided.
	 * @return Returns a double that represents the percentage of utilization.
	 * @throws EmptyResultDataAccessException
	 */
	public Double weeklyUtilizationByResourceTypeId(int resourceTypeId, String startDate, String endDate) throws EmptyResultDataAccessException
	{
		return jtemp.queryForObject("select sum(extract(hour from (BOOKED_END_TIME-BOOKED_START_TIME)))/(40*(SELECT COUNT(*) FROM RESOURCES WHERE resource_type_id = ?)) from bookings inner join resources using (resource_id)\r\n" + 
				"where resource_type_id=? AND trunc(booked_start_time) BETWEEN TO_DATE(?,'dd-MM-yyyy HH24:MI') AND" + 
				" TO_DATE(?,'dd-MM-yyyy HH24:MI') AND is_active > 0 group by resource_type_id", 
				Double.class,
				resourceTypeId,
				resourceTypeId,
				startDate,
				endDate);
	}
	/**
	 * Queries for all of the bookings within a month for all the resources 
	 * of a single resource type. Takes the sum of all the differences of
	 * booked_end_time minus book_start_time and divides it by 160 
	 * times the number of resources of the resource type provided
	 * to return a percentage of utilization for that month.
	 * 
	 * In layman terms it takes all of the hours that were booked in a month for all the rooms of a single resource type and divides
	 * it by 160 (the number of available hours in a month to book) times the number of resources of that resource type
	 * to return the percentage of utilization for those rooms. 
	 * @param resourceTypeId Used to query the resources of a certain resource type you wish to see the utilization for.
	 * @param startDate Used to limit the scope of utilization to the date provided.
	 * @param endDate Used to limit the scope of utilization to the date provided.
	 * @return Returns a double that represents the percentage of utilization.
	 * @throws EmptyResultDataAccessException
	 */
	public Double monthlyUtilizationByResourceTypeId(int resourceTypeId, String startDate, String endDate) throws EmptyResultDataAccessException
	{
		return jtemp.queryForObject("select sum(extract(hour from (BOOKED_END_TIME-BOOKED_START_TIME)))/(160*(SELECT COUNT(*) FROM RESOURCES WHERE resource_type_id = ?)) from bookings inner join resources using (resource_id)\r\n" + 
				"where resource_type_id=? AND trunc(booked_start_time) BETWEEN TO_DATE(?,'dd-MM-yyyy HH24:MI') AND " + 
				"TO_DATE(?,'dd-MM-yyyy HH24:MI') AND is_active > 0 group by resource_type_id",
				Double.class,
				resourceTypeId,
				resourceTypeId,
				startDate,
				endDate);
	}
	/**
	 * Queries for all of the bookings within a day for all the resources. 
	 * Takes the sum of all the differences of
	 * booked_end_time minus book_start_time and divides it by eight 
	 * times the number of resources to return a percentage of utilization for that day.
	 * 
	 * In layman terms it takes all of the hours that were booked in a single day for all the rooms and divides
	 * it by 8 (the number of available hours in a day to book) times the number of resources of that resource type
	 * to return the percentage of utilization for all rooms. 
	 * @param date Used to limit the scope of utilization to the date provided.
	 * @return Returns a double that represents the percentage of utilization.
	 * @throws EmptyResultDataAccessException
	 */
	public Double dailyUtilizationForAllResources(String date) throws EmptyResultDataAccessException{
		return jtemp.queryForObject("select sum((extract(hour from (BOOKED_END_TIME-BOOKED_START_TIME)))/(8*(SELECT COUNT(*) FROM RESOURCES))) from bookings\r\n" + 
				"where trunc(booked_start_time) = TO_DATE(?,'dd-MM-yyyy HH24:MI') AND is_active > 0",
				Double.class,
				date);
	}
	/**
	 * Queries for all of the bookings within a week for all the resources. 
	 * Takes the sum of all the differences of
	 * booked_end_time minus book_start_time and divides it by 40 
	 * times the number of resources to return a percentage of utilization for that week.
	 * 
	 * In layman terms it takes all of the hours that were booked in a week for all the rooms and divides
	 * it by 40 (the number of available hours in a day to book) times the number of resources of that resource type
	 * to return the percentage of utilization for all rooms. 
	 * @param startDate Used to limit the scope of utilization to the date provided.
	 * @param endDate Used to limit the scope of utilization to the date provided.
	 * @return Returns a double that represents the percentage of utilization.
	 * @throws EmptyResultDataAccessException
	 */
	public Double weeklyUtilizationForAllResources(String startDate, String endDate) throws EmptyResultDataAccessException{
		return jtemp.queryForObject("select sum((extract(hour from (BOOKED_END_TIME-BOOKED_START_TIME)))/(40*(SELECT COUNT(*) FROM RESOURCES))) from bookings\r\n" + 
				"where trunc(booked_start_time) BETWEEN TO_DATE(?,'dd-MM-yyyy HH24:MI') AND TO_DATE(?,'dd-MM-yyyy HH24:MI') AND is_active > 0",
				Double.class,
				startDate,
				endDate);
	}
	/**
	 * Queries for all of the bookings within a month for all the resources. 
	 * Takes the sum of all the differences of
	 * booked_end_time minus book_start_time and divides it by 160
	 * times the number of resources to return a percentage of utilization for that month.
	 * 
	 * In layman terms it takes all of the hours that were booked in a month for all the rooms and divides
	 * it by 160 (the average number of available hours in a day to book) times the number of resources of that resource type
	 * to return the percentage of utilization for all rooms. 
	 * @param startDate Used to limit the scope of utilization to the date provided.
	 * @param endDate Used to limit the scope of utilization to the date provided.
	 * @return Returns a double that represents the percentage of utilization.
	 * @throws EmptyResultDataAccessException
	 */
	public Double monthlyUtilizationForAllResources(String startDate, String endDate) throws EmptyResultDataAccessException{
		return jtemp.queryForObject("select sum((extract(hour from (BOOKED_END_TIME-BOOKED_START_TIME)))/(160*(SELECT COUNT(*) FROM RESOURCES))) from bookings\r\n" + 
				"where trunc(booked_start_time) BETWEEN TO_DATE(?,'dd-MM-yyyy HH24:MI') AND TO_DATE(?,'dd-MM-yyyy HH24:MI') AND is_active > 0",
				Double.class,
				startDate,
				endDate);
	}

}

