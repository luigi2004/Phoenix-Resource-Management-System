package rms.model;

/**
 * This class is meant to be used with the PRMS database. It is a model class for the ResourceType table in the database.
 * Each type of resource is given a unique ID. 
 * Each ID represents a resource type with name, description, and an image path. 
 * @author Re: Syntellions
 *
 */
public class ResourceType 
{
	/**
	 * Unique integer ID created for every new resource type
	 */
	int resourceTypeId;
	/**
	 * Unique String name created for a type of resource
	 */
	String resourceTypeName;
	/**
	 * Unique String description created for a type of resource
	 */
	String resourceTypeDescription;
	/**
	 * Unique String image path created for a type of resource
	 */
	String imgPath;
	
	/**
	 * Empty constructor for Resource Type
	 */
	public ResourceType() 
	{
		super();
	}

	/**
	 * Parameterized constructor for Resource Type
	 * @param resourceTypeId Unique integer ID created for every new resource type
	 * @param resourceTypeName Unique String name created for a type of resource
	 * @param resourceTypeDescription Unique String description created for a type of resource
	 * @param imgPath Unique String image path created for a type of resource
	 */
	public ResourceType(int resourceTypeId, String resourceTypeName, String resourceTypeDescription, String imgPath) 
	{
		super();
		this.resourceTypeId = resourceTypeId;
		this.resourceTypeName = resourceTypeName;
		this.resourceTypeDescription = resourceTypeDescription;
		this.imgPath = imgPath;
	}
	

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "ResourceType: resourceTypeId=" + resourceTypeId + ", resourceTypeName=" + resourceTypeName
				+ ", resourceTypeDescription=" + resourceTypeDescription + ", imgPath=" + imgPath;
	}
	
	// Getters and Setters ---------------------------
	/**
	 * Getter method that gets the value Image Path
	 * @return String imgPath
	 */
	public String getImgPath() 
	{
		return imgPath;
	}
	/**
	 * Setter method that sets the value of Image Path
	 * @param imgPath String Image Path
	 */
	public void setImgPath(String imgPath) 
	{
		this.imgPath = imgPath;
	}
	
	
	/**
	 * Getter method that gets the value of Resource Type ID
	 * @return String resourceTypeId
	 */
	public int getResourceTypeId() 
	{
		return resourceTypeId;
	}
	/**
	 * Setter method that sets the value of Resource Type ID
	 * @param resourceTypeId String Resource Type ID
	 */
	public void setResourceTypeId(int resourceTypeId)
	{
		this.resourceTypeId = resourceTypeId;
	}
	
	
	/**
	 * Getter method that gets the value of Resource Type Name
	 * @return String resourceTypeName
	 */
	public String getResourceTypeName()
	{
		return resourceTypeName;
	}
	/**
	 * Setter method that sets the value of Resource Type Name
	 * @param resourceTypeName String Resource Type Name
	 */
	public void setResourceTypeName(String resourceTypeName) 
	{
		this.resourceTypeName = resourceTypeName;
	}
	
	
	/**
	 * Getter method that gets the value of Resource Type Description
	 * @return String resourceTypeDescription
	 */
	public String getResourceTypeDescription()
	{
		return resourceTypeDescription;
	}
	/**
	 * Setter method that sets the value of Resource Type Description
	 * @param resourceTypeDescription String Resource Type Description
	 */
	public void setResourceTypeDescription(String resourceTypeDescription) 
	{
		this.resourceTypeDescription = resourceTypeDescription;
	}
	
}
