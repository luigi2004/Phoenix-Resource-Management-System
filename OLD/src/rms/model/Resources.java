package rms.model;

/**
 * This class is meant to be used with the PRMS database. It is a model class for the Resources table in the database.
 * Each resource is given a unique ID. 
 * Each ID represents a resource with name, description, room number, resource type id, location id, 
 * availability, super room designation, and capacity. 
 * @author Re: Syntellions
 *
 */
public class Resources 
{
	/**
	 * Unique integer created for every new resource
	 */
	private int resourceId;
	/**
	 * String name assigned to a resource
	 */
	private String resourceName;
	/**
	 * String description assigned to a resource
	 */
	private String resourceDescription;
	/**
	 * String room number assigned to a resource
	 */
	private String resourceRoomNumber;
	/**
	 * Integer resource type ID assigned to a resource
	 */
	private int resourceTypeId;
	/**
	 * Integer location ID assigned to a resource
	 */
	private int locationId;
	/**
	 * Integer is available assigned to a resource to tell whether a room is available or not
	 */
	private int isAvailable;
	/**
	 * Integer is super room assigned to a resource to tell whether or not a room needs manager privileges to access
	 */
	private int isSuperRoom;
	/**
	 * Integer capacity assigned to a resource
	 */
	private int capacity;
	
	/**
	 * Empty constructor for Resources
	 */
	public Resources()
	{
		super();
	}

	
	/**
	 * Parameterized constructor for Resources
	 * @param resourceId Unique integer created for every new resource
	 * @param resourceName String name assigned to a resource
	 * @param resourceDescription String room number assigned to a resource
	 * @param resourceRoomNumber String room number assigned to a resource
	 * @param resourceTypeId Integer resource type ID assigned to a resource
	 * @param locationId  Integer location ID assigned to a resource
	 * @param isAvailable Integer is available assigned to a resource to tell whether a room is available or not
	 * @param isSuperRoom Integer is super room assigned to a resource to tell whether or not a room needs manager privileges to access
	 * @param capacity Integer capacity assigned to a resource
	 */
	public Resources(int resourceId, String resourceName, String resourceDescription, String resourceRoomNumber,
			int resourceTypeId, int locationId, int isAvailable, int isSuperRoom, int capacity) 
	{
		super();
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.resourceDescription = resourceDescription;
		this.resourceRoomNumber = resourceRoomNumber;
		this.resourceTypeId = resourceTypeId;
		this.locationId = locationId;
		this.isAvailable = isAvailable;
		this.isSuperRoom = isSuperRoom;
		this.capacity = capacity;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Resources [resourceId=" + resourceId + ", resourceName=" + resourceName + ", resourceDescription="
				+ resourceDescription + ", resourceRoomNumber=" + resourceRoomNumber + ", resourceTypeId="
				+ resourceTypeId + ", locationId=" + locationId + ", isAvailable=" + isAvailable + ", isSuperRoom="
				+ isSuperRoom + ", capacity=" + capacity + "]";
	}
	
	public int getIsSuperRoom()
	{
		return isSuperRoom;
	}
	public void setIsSuperRoom(int isSuperRoom)
	{
		this.isSuperRoom = isSuperRoom;
	}
	public int getCapacity()
	{
		return capacity;
	}
	public void setCapacity(int capacity) 
	{
		this.capacity = capacity;
	}
	public int getResourceId()
	{
		return resourceId;
	}
	public void setResourceId(int resourceId) 
	{
		this.resourceId = resourceId;
	}
	public String getResourceName() 
	{
		return resourceName;
	}
	public void setResourceName(String resourceName)
{
		this.resourceName = resourceName;
	}
	public String getResourceDescription() 
	{
		return resourceDescription;
	}
	public void setResourceDescription(String resourceDescription)
	{
		this.resourceDescription = resourceDescription;
	}
	public String getResourceRoomNumber() 
	{
		return resourceRoomNumber;
	}
	public void setResourceRoomNumber(String resourceRoomNumber)
	{
		this.resourceRoomNumber = resourceRoomNumber;
	}
	public int getResourceTypeId() 
	{
		return resourceTypeId;
	}
	public void setResourceTypeId(int resourceTypeId) 
	{
		this.resourceTypeId = resourceTypeId;
	}
	public int getLocationId()
	{
		return locationId;
	}
	public void setLocationId(int locationId)
	{
		this.locationId = locationId;
	}
	public int getIsAvailable()
	{
		return isAvailable;
	}
	public void setIsAvailable(int isAvailable)
	{
		this.isAvailable = isAvailable;
	}
	
	
}
