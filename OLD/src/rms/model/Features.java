package rms.model;


/**
 * Model class representing a Feature
 * @author syntel
 *
 */
public class Features
{
	private int featureTypeId;
	private int quantity;
	private int resourceId;
	
	@Override
	public String toString() 
	{
		return "Features [featureTypeId=" + featureTypeId + ", quantity=" + quantity + ", resourceId=" + resourceId
				+ "]";
	}
	
	
	public int getFeatureTypeId() 
	{
		return featureTypeId;
	}
	public void setFeatureTypeId(int featureTypeId) 
	{
		this.featureTypeId = featureTypeId;
	}
	
	
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	
	
	public int getResourceId()
	{
		return resourceId;
	}
	public void setResourceId(int resourceId)
	{
		this.resourceId = resourceId;
	}
	
	
}
