package srmsModel;

public class ResourceModel {

	private int resId; // primary key
	private int roomNumber; // not null
	private String resName; // not null
	private String resType; // not null
	private String description; // not null
	private String status; // not null
	private String photoOfResource;
	
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPhotoOfResource() {
		return photoOfResource;
	}
	public void setPhotoOfResource(String photoOfResource) {
		this.photoOfResource = photoOfResource;
	}
	
}
