package rms.queries;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * Helper class that calls the methods of the UtilizationQueries
 * @author Re:Syntellions
 *
 */
public class CallUtilizationQueries {
	/**
	 * Calls the {@link rms.queries.UtilizationQueries#dailyUtilizationByResourceId dailyUtilizationByResourceId} method from UtilizationQueries class.
	 * @param resourceId Used to query the resource you wish to see the utilization for.
	 * @param date Used to limit the scope of utilization to the date provided.
	 * @return returns a double that represents the percentage of utilization.
	 */
	public Double callDailyUtilizationByResourceId(int resourceId, Date date)
    {		
        return new UtilizationQueries().dailyUtilizationByResourceId(resourceId, formatDate(date));		
    }
	/**
	 * Calls the {@link rms.queries.UtilizationQueries#weeklyUtilizationByResourceId weeklyUtilizationByResourceId} method from UtilizationQueries class.
	 * Creates the end date a week from the start date and passes it into the call. 
	 * @param resourceId Used to query the resource you wish to see the utilization for.
	 * @param date Used to limit the scope of utilization to the date provided.
	 * @return returns a double that represents the percentage of utilization.
	 */
	public Double callWeeklyUtilizationByResourceId(int resourceId, Date startDate)
	{
        Date endDate = addWeek(startDate);
		return new UtilizationQueries().weeklyUtilizationByResourceId(resourceId, formatDate(startDate), formatDate(endDate));
	}
	/**
	 * Calls the {@link rms.queries.UtilizationQueries#monthlyUtilizationByResourceId monthlyUtilizationByResourceId} method from UtilizationQueries class.
	 * Creates the end date a month from the start date and passes it into the call.
	 * @param resourceId Used to query the resource you wish to see the utilization for.
	 * @param date Used to limit the scope of utilization to the date provided.
	 * @return returns a double that represents the percentage of utilization.
	 */
	public Double callMonthlyUtilizationByResourceId(int resourceId, Date startDate)
	{
		Date endDate = addMonth(startDate);
		return new UtilizationQueries().monthlyUtilizationByResourceId(resourceId, formatDate(startDate), formatDate(endDate));
	}
	
	/**
	 * Calls the {@link rms.queries.UtilizationQueries#dailyUtilizationByResourceTypeId dailyUtilizationByResourceTypeId} method from UtilizationQueries class.
	 * @param resourceTypeId Used to query the resources of a certain resource type you wish to see the utilization for.
	 * @param date Used to limit the scope of utilization to the date provided.
	 * @return returns a double that represents the percentage of utilization.
	 */
	public Double callDailyUtilizationByResourceTypeId(int resourceTypeId, Date date)
	{
		return new UtilizationQueries().dailyUtilizationByResourceTypeId(resourceTypeId, formatDate(date));	
	}
	/**
	 * Calls the {@link rms.queries.UtilizationQueries#weeklyUtilizationByResourceTypeId weeklyUtilizationByResourceTypeId} method from UtilizationQueries class.
	 * Creates the end date a week from the start date and passes it into the call.
	 * @param resourceTypeId Used to query the resources of a certain resource type you wish to see the utilization for.
	 * @param date Used to limit the scope of utilization to the date provided.
	 * @return returns a double that represents the percentage of utilization.
	 */
	public Double callWeeklyUtilizationByResourceTypeId(int resourceTypeId, Date startDate)
	{
		
		Date endDate = addWeek(startDate);
		return new UtilizationQueries().weeklyUtilizationByResourceTypeId(resourceTypeId, formatDate(startDate), formatDate(endDate));
	}
	/**
	 * Calls the {@link rms.queries.UtilizationQueries#monthlyUtilizationByResourceTypeId monthlyUtilizationByResourceTypeId} method from UtilizationQueries class.
	 * Creates the end date a month from the start date and passes it into the call.
	 * @param resourceTypeId Used to query the resources of a certain resource type you wish to see the utilization for.
	 * @param date Used to limit the scope of utilization to the date provided.
	 * @return returns a double that represents the percentage of utilization.
	 */
	public Double callMonthlyUtilizationByResourceTypeId(int resourceTypeId, Date startDate)
	{
		Date endDate = addMonth(startDate);
		return new UtilizationQueries().monthlyUtilizationByResourceTypeId(resourceTypeId, formatDate(startDate), formatDate(endDate));
	}
	/**
	 * Calls the {@link rms.queries.UtilizationQueries#dailyUtilizationForAllResources dailyUtilizationForAllResources} method from UtilizationQueries class.
	 * @param date Used to limit the scope of utilization to the date provided.
	 * @return returns a double that represents the percentage of utilization.
	 */
	public Double callDailyUtilizationForAllResources(Date date) {
		
		return new UtilizationQueries().dailyUtilizationForAllResources(formatDate(date));
		
	}
	/**
	 * Calls the {@link rms.queries.UtilizationQueries#weeklyUtilizationForAllResources weeklyUtilizationForAllResources} method from UtilizationQueries class.
	 * Creates the end date a week from the start date and passes it into the call.
	 * @param date Used to limit the scope of utilization to the date provided.
	 * @return returns a double that represents the percentage of utilization.
	 */
	public Double callWeeklyUtilizationForAllResources(Date startDate) {
		Date endDate = addWeek(startDate);
		return new UtilizationQueries().weeklyUtilizationForAllResources(formatDate(startDate), formatDate(endDate));
	}
	/** 
	 * Calls the {@link rms.queries.UtilizationQueries#monthlyUtilizationForAllResources monthlyUtilizationForAllResources} method from UtilizationQueries class.
	 * Creates the end date a month from the start date and passes it into the call.
	 * @param date Used to limit the scope of utilization to the date provided.
	 * @return returns a double that represents the percentage of utilization.
	 */
	public Double callMonthlyUtilizationForAllResources(Date startDate) {
		Date endDate = addMonth(startDate);
		return new UtilizationQueries().monthlyUtilizationForAllResources(formatDate(startDate), formatDate(endDate));
	}
	/**
	 * Formats the date to a format that is used in all of the queries in the UtilizationQueries class.
	 * @param date 
	 * @return String of needed format. ("dd-MM-yyyy")
	 */
	public String formatDate(Date date) {
		SimpleDateFormat ddmmyyyyy = new SimpleDateFormat("dd-MM-yyyy");

		return ddmmyyyyy.format(date) +" 00:00";
	}
	/**
	 * Adds one month to the date that is provided in the parameter and returns that date.
	 * @param startDate
	 * @return A date one month from the date provided.
	 */
	public Date addMonth(Date startDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.MONTH, 1);

		return calendar.getTime();
	}
	/**
	 * Adds one week to the date that is provided in the parameter and returns that date.
	 * @param startDate
	 * @return A date one week from the date provided.
	 */
	public Date addWeek(Date startDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.DAY_OF_YEAR, 7);

		return calendar.getTime();
	}

}
