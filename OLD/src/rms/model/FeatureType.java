package rms.model;


/**
 * Model class representing a FeatureType
 * @author syntel
 *
 */
public class FeatureType 
{
	private int featureTypeId;
	private String featureTypeName;
	private String featureTypeDescription;
	private String imgPath;
	

	
	@Override
	public String toString() {
		return "FeatureType [featureTypeId=" + featureTypeId + ", featureTypeName=" + featureTypeName
				+ ", featureTypeDescription=" + featureTypeDescription + ", imgPath=" + imgPath + "]";
	}
	
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public int getFeatureTypeId() 
	{
		return featureTypeId;
	}
	public void setFeatureTypeId(int featureTypeId)
	{
		this.featureTypeId = featureTypeId;
	}
	
	
	public String getFeatureTypeName() 
	{
		return featureTypeName;
	}
	public void setFeatureTypeName(String featureTypeName)
	{
		this.featureTypeName = featureTypeName;
	}
	
	
	public String getFeatureTypeDescription() 
	{
		return featureTypeDescription;
	}
	public void setFeatureTypeDescription(String featureTypeDescription) 
	{
		this.featureTypeDescription = featureTypeDescription;
	}
	
	
}
