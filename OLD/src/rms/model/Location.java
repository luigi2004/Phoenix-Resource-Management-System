package rms.model;

/**
 * This class is meant to be used with the PRMS database. It is a model class for the Location table in the database
 * Each location is given a unique Location ID. 
 * Other information such as city, state, zip, etc. are filled out for each location.
 * @author Re: Syntellions
 *
 */
public class Location 
{
	
	/**
	 * Unique integer ID created for every new location added to the database.
	 */
	private int locationId;
	/**
	 * String for defining the city of a location. 
	 */
	private String city;
	/**
	 * String for defining the state of a location.
	 */
	private String state;
	/**
	 * String for defining the zipcode of a location.
	 */
	private String zip;
	/**
	 * String for defining the country of a location.
	 */
	private String country;
	/**
	 * String for defining the phone number of a location.
	 */
	private String phone;
	
	/**
	 * Empty constructor for Location
	 */
	public Location() 
	{
		super();
	}

	/**
	 * Parameterized constructor for Location
	 * @param city String for defining the city of a location. 
	 * @param state String for defining the state of a location.
	 * @param zip String for defining the zipcode of a location.
	 * @param country String for defining the country of a location.
	 * @param phone String for defining the phone number of a location.
	 */
	public Location(String city, String state, String zip, String country, String phone)
	{
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
		this.phone = phone;
	}
	
	// Getters and setters --------------------------------------
	/**
	 * Getter method that gets the value of Location ID
	 * @return integer locationId
	 */
	public int getLocationId() 
	{
		return locationId;
	}

	/**
	 * Setter method that sets the value of Location ID
	 * @param locationId integer Location ID
	 */
	public void setLocationId(int locationId)
	{
		this.locationId = locationId;
	}

	
	/**
	 * Getter method that gets the value of City
	 * @return String city
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * Setter method that sets the value of City
	 * @param city String City
	 */
	public void setCity(String city)
	{
		this.city = city;
	}
	
	
	/**
	 * Getter method that gets the value of State
	 * @return String state
	 */
	public String getState() 
	{
		return state;
	}

	/**
	 * Setter method that sets the value of State
	 * @param state String State
	 */
	public void setState(String state) 
	{
		this.state = state;
	}

	
	/**
	 * Getter method that gets the value of Zipcode
	 * @return String zip
	 */
	public String getZip() 
	{
		return zip;
	}

	/**
	 * Setter method that sets the value of Zipcode
	 * @param zip String Zip
	 */
	public void setZip(String zip) 
	{
		this.zip = zip;
	}

	
	/**
	 * Getter method that gets the value of Country
	 * @return String country
	 */
	public String getCountry() 
	{
		return country;
	}

	/**
	 * Setter method that sets the value of Country
	 * @param country String Country
	 */
	public void setCountry(String country) 
	{
		this.country = country;
	}

	
	/**
	 * Getter method that gets the value of Phone
	 * @return String phone
	 */
	public String getPhone()
	{
		return phone;
	}

	/**
	 * Setter method that sets the value of Phone
	 * @param phone String Phone
	 */
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	
}
