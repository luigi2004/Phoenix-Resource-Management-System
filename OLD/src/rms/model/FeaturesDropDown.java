package rms.model;

/**
 * This class is meant to be used with the PRMS database. It is a model class for the FeatureType , Features, and Resources tables in the database tables in the database.
 * This class uses data from multiple tables and requires the RoomDropMapper class to accomplish this.  
 * FeatureName is retrieved from FeatureType, Quantity from Features, and ResourceName from Resources.
 * @author Re: Syntellions
 *
 */
public class FeaturesDropDown
{
	/**
	 * String name assigned to a feature type
	 */
	private String featureName;
	/**
	 * Integer quantity assigned to features for a room
	 */
	private int quantity;
	/**
	 * String name assigned to a resource
	 */
	private String resourceName;
	
	private int resourceID;

	public int getResourceID() {
		return resourceID;
	}


	public void setResourceID(int resourceID) {
		this.resourceID = resourceID;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "Features [featureName=" + featureName + ", quantity=" + quantity + ", resourceName=" + resourceName
				+ "]";
	}

	
	public String getFeatureName() 
	{
		return featureName;
	}
	public void setFeatureName(String featureTypeId) 
	{
		this.featureName = featureTypeId;
	}
	
	
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	
	
	public String getResourceName()
	{
		return resourceName;
	}


	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	
}
