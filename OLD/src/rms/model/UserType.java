package rms.model;
/**
 * This class is meant to be used with the PRMS database. It is a model class for a user for the usertype table in
 * the database.
 * 
 * @author Re:Syntellions
 *
 */
public class UserType {
	/**
	 * Unique id for every userType. This id signifies the type of user. 1=SuperUser 2=Basic User 3=Manager 
	 */
	private int userTypeId;
	/**
	 *The names of each user type.
	 */
	private String userTypeName;
	/**
	 * Describes the permissions of each user type.
	 */
	private String userTypeDescription;
	
	
	public UserType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserType(int userTypeId, String userTypeName, String userTypeDescription) {
		super();
		this.userTypeId = userTypeId;
		this.userTypeName = userTypeName;
		this.userTypeDescription = userTypeDescription;
	}

	@Override
	public String toString() {
		return "UserType [userTypeId=" + userTypeId + ", userTypeName=" + userTypeName + ", userTypeDescription="
				+ userTypeDescription + "]";
	}
	
	public int getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public String getUserTypeDescription() {
		return userTypeDescription;
	}
	public void setUserTypeDescription(String userTypeDescription) {
		this.userTypeDescription = userTypeDescription;
	}
	
	
}
