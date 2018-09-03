package rms.dao;

import java.util.List;


/**
 * The jdbc template class supports methods to map between Java objects and database rows. 
 * Includes methods to insert, delete, update, search, and get all.
 * 
 * @author syntel
 *
 * @param <E> The data type of the java class and database schema you want to map between.
 */
public interface JdbcTemplateInterface <E>{
	
	/**
	 * Will map a java object to a database row and insert it into the the database.
	 * @param objectToInsert A java object containing all the data you want to be inserted into the database.
	 * @return The number of rows inserted. (Either 0 or 1)
	 */
	public int insert(E objectToInsert);
	
	/**
	 * Will delete a database row.
	 * @param objectIdToDelete the ID (primary key) of the row to be deleted.
	 * @return The number of deleted rows. (Either 0 or 1)
	 */
	public int delete(int objectIdToDelete);
	
	/**
	 * Will update an existing row in the database.
	 * @param objectToUpdate The java object containing all the new values to be updated. Will update
	 * the row with the same ID as the java object.
	 * @return The number of rows updated (Either 0 or 1)
	 */
	public int update(E objectToUpdate);
	
	/**
	 * Will search for a database row and map it into a java object.
	 * @param objectID The ID of the row you want to search for.
	 * @return The mapped java object containing all the data from the database row.
	 */
	public E search(int objectID);
	
	
	/**
	 * Get a list of all the database rows from the table.
	 * @return A list of all the rows in a table mapped to java objects
	 */
	public List<E> getAll();
}
