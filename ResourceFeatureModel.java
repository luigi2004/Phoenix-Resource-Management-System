package srmsModel;

public class ResourceFeatureModel {

	private int resFeaId; // primary key
	private int feaId; // references Feature(feaId)
	private int locResId; // references LocationResource(locResId)
	private int quantity; // not null
	private String description; // not null
	
	public int getResFeaId() {
		return resFeaId;
	}
	public void setResFeaId(int resFeaId) {
		this.resFeaId = resFeaId;
	}
	public int getFeaId() {
		return feaId;
	}
	public void setFeaId(int feaId) {
		this.feaId = feaId;
	}
	public int getLocResId() {
		return locResId;
	}
	public void setLocResId(int locResId) {
		this.locResId = locResId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
