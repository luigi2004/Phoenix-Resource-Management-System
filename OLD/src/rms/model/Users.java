package rms.model;
/**
 * This class is meant to be used with the PRMS database. It is a model class for a user for the users table in
 * the database.
 * 
 * @author Re:Syntellions
 * 
 */
public class Users {
	/**
	 * int that represents the identification of a user. This value is unique in the PRMS database.
	 */
	private int userId;
	/**
	 * String that represents the username or login credentials of a user. This value should be unique in the database.
	 */
	private String userName;
	/**
	 * String that represents the email or login credentials of a user. This value should be unique in the
	 * database and verified to see if email actually exists. 
	 */
	private String userEmail;
	/**
	 * String that represents the password of a user. The value passed into this variable should be hashed.
	 */
	private String userPassword;
	/**
	 * int that represents the type of user. Types of users include 1=Super User 2=Basic User 3=Manager
	 */
	private int userType;
	/**
	 * String that represents the phone number of the user. The current format of these numbers is:
	 * 123-456-7890
	 */
	private String userPhone;
	/**
	 * int that represents the location id of the user. This is mapped to the locations table to
	 * get more information on the location of the user.
	 */
	private int locationId;
	/**
	 * String that represents the first name of the user.
	 */
	private String first_name;
	/**
	 * String that represents the last name of the user.
	 */
	private String last_name;
	/**
	 * Default constructor that takes no parameters.
	 */
	public Users() {
		super();
	}

	/**
	 * Constructor that constructs a user when all information for the user is present.
	 * @param userId int that represents the identification of a user. This value is unique in the PRMS database.
	 * @param userName String that represents the username or login credentials of a user. This value should be unique in the database.
	 * @param userEmail String that represents the email or login credentials of a user. This value should be unique in the
	 * database and verified to see if email actually exists. 
	 * @param userPassword String that represents the password of a user. The value passed into this variable should be hashed.
	 * @param userType int that represents the type of user. Types of users include 1=Super User 2=Basic User 3=Manager
	 * @param userPhone String that represents the phone number of the user. The current format of these numbers is:
	 * 123-456-7890
	 * @param locationId int that represents the location id of the user. This is mapped to the locations table to
	 * get more information on the location of the user.
	 * @param first_name String that represents the first name of the user.
	 * @param last_name String that represents the last name of the user.
	 */
	public Users(int userId, String userName, String userEmail, String userPassword, int userType, String userPhone,
			int locationId, String first_name, String last_name) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userType = userType;
		this.userPhone = userPhone;
		this.locationId = locationId;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
				+ userPassword + ", userType=" + userType + ", userPhone=" + userPhone + ", locationId=" + locationId
				+ ", first_name=" + first_name + ", last_name=" + last_name + "]";
	}
	
	/**
	 * Getter method that returns the first name of this user.
	 * @return String of first_name of this user.
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * Setter method that sets the first name of this user.
	 * @param first_name of type String. 
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * Getter method that returns the last name of this user.
	 * @return String of last_name 
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * Setter method that sets the last name of this user.
	 * @param last_name of type String.
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * Getter method that gets the location Id of this user. 
	 * @return location_id of type int
	 */
	public int getLocationId() {
		return locationId;
	}
	/**
	 * Setter method that sets the location id of this user.
	 * @param locationId of type int
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	/**
	 * Getter method that gets the user id of this user. 
	 * @return userId of type int
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * Setter method that sets the user id of this user.
	 * @param userId of type int
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * Getter method that gets the userName of this user. 
	 * @return userName of type String
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * Setter method that sets the userName of this user.
	 * @param userName of type String
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * Getter method that gets the email of this user. 
	 * @return userEmail of type String.
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * Setter method that sets the email of this user.
	 * @param userEmail of type String.
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * Getter method that gets the password of this user. 
	 * @return userPassword of type String
	 */
	public String getUserPassword() {
		return userPassword;
	}
	/**
	 * Setter method that sets the password of this user.
	 * @param userPassword of type String
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	/**
	 * Getter method that gets the type of this user. 
	 * @return userType of type String
	 */
	public int getUserType() {
		return userType;
	}
	/**
	 * Setter method that sets the type of this user.
	 * @param userType of type String
	 */
	public void setUserType(int userType) {
		this.userType = userType;
	}
	/**
	 * Getter method that gets the phone number of this user. 
	 * @return userPhone of type String
	 */
	public String getUserPhone() {
		return userPhone;
	}
	/**
	 * Setter method that sets the phone number of this user.
	 * @param userPhone of type String
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	
}
