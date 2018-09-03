package rms.model;

import java.sql.Timestamp;

/**
 * This class is meant to be used with the PRMS database. It is a model class for a user for the Visitors table in
 * the database.
 * 
 * @author Re:Syntellions
 *
 */
public class Visitors {
	/**
	 * Visitor Id of type int. This should be unique in the PRMS database.
	 */
	private int visitorId;
	/**
	 * Visiting name is the name of the person the visitor is visiting. Type string.
	 */
	private String visitingName;
	/**
	 * Email of the visitor. Type string.
	 */
	private String email;
	/**
	 * Phone number of the visitor. Type string.
	 */
	private String phone;
	/**
	 * Time that the visitor checked in. Type Timestamp.
	 */
	private Timestamp checkedInTime;
	/**
	 * Location id that the visitor is checking in to. Type int.
	 */
	private int locationId;
	/**
	 * Badge id provided to the visitor. Type int.
	 */
	private String badgeId;
	/**
	 * Time that the visitor checked out at. Type timestamp.
	 */
	private Timestamp checkedOutTime;
	/**
	 * String that explains the purpose of the visit.
	 */
	private String visitPurpose;
	/**
	 * Company name of the visitor. Type string.
	 */
	private String companyName;
	/**
	 * Int that equals 0 if the visitor has not checked out and 1 if the visitor has checked out.
	 */
	private int hasCheckedOut;
	/**
	 * First name of the visitor.Type string.
	 */
	private String firstName;
	/**
	 * Last name of the visitor. Type string.
	 */
	private String lastName;
	/**
	 * Last name of the visitor. Type string.
	 */
	private String scheduledMeetingTime;
	/**
	 * Integer that is set to 1 if the visitor has id proof and 0 otherwise.
	 */
	private int hasIdProof;
	

	/**
	 * Default constructor that constructs a Visitor.
	 */
	public Visitors() {
		super();
	}
	/**
	 * Parameterized constructor that constructs a visitor with all input.
	 * @param visitorId
	 * @param visitingName
	 * @param email
	 * @param phone
	 * @param checkedInTime
	 * @param locationId
	 * @param badgeId
	 * @param checkedOutTime
	 * @param visitPurpose
	 * @param companyName
	 * @param hasCheckedOut
	 * @param firstName
	 * @param lastName
	 * @param scheduledMeetingTime
	 * @param hasIdProof
	 */
	public Visitors(int visitorId, String visitingName, String email, String phone, Timestamp checkedInTime,
			int locationId, String badgeId, Timestamp checkedOutTime, String visitPurpose, String companyName,
			int hasCheckedOut, String firstName, String lastName, String scheduledMeetingTime, int hasIdProof) {
		super();
		this.visitorId = visitorId;
		this.visitingName = visitingName;
		this.email = email;
		this.phone = phone;
		this.checkedInTime = checkedInTime;
		this.locationId = locationId;
		this.badgeId = badgeId;
		this.checkedOutTime = checkedOutTime;
		this.visitPurpose = visitPurpose;
		this.companyName = companyName;
		this.hasCheckedOut = hasCheckedOut;
		this.firstName = firstName;
		this.lastName = lastName;
		this.scheduledMeetingTime = scheduledMeetingTime;
		this.hasIdProof = hasIdProof;
	}
	@Override
	public String toString() {
		return "Visitors [visitorId=" + visitorId + ", visitingName=" + visitingName + ", email=" + email + ", phone="
				+ phone + ", checkedInTime=" + checkedInTime + ", locationId=" + locationId + ", badgeId=" + badgeId
				+ ", checkedOutTime=" + checkedOutTime + ", visitPurpose=" + visitPurpose + ", companyName="
				+ companyName + ", hasCheckedOut=" + hasCheckedOut + ", firstName=" + firstName + ", lastName="
				+ lastName + ", scheduledMeetingTime=" + scheduledMeetingTime + ", hasIdProof=" + hasIdProof + "]";
	}
	public int getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}
	public String getVisitingName() {
		return visitingName;
	}
	public void setVisitingName(String visitingName) {
		this.visitingName = visitingName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Timestamp getCheckedInTime() {
		return checkedInTime;
	}
	public void setCheckedInTime(Timestamp checkedInTime) {
		this.checkedInTime = checkedInTime;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getBadgeId() {
		return badgeId;
	}
	public void setBadgeId(String badgeId) {
		this.badgeId = badgeId;
	}
	public Timestamp getCheckedOutTime() {
		return checkedOutTime;
	}
	public void setCheckedOutTime(Timestamp checkedOutTime) {
		this.checkedOutTime = checkedOutTime;
	}
	public String getVisitPurpose() {
		return visitPurpose;
	}
	public void setVisitPurpose(String visitPurpose) {
		this.visitPurpose = visitPurpose;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getHasCheckedOut() {
		return hasCheckedOut;
	}
	public void setHasCheckedOut(int hasCheckedOut) {
		this.hasCheckedOut = hasCheckedOut;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getScheduledMeetingTime() {
		return scheduledMeetingTime;
	}
	public void setScheduledMeetingTime(String scheduledMeetingTime) {
		this.scheduledMeetingTime = scheduledMeetingTime;
	}
	public int getHasIdProof() {
		return hasIdProof;
	}
	public void setHasIdProof(int hasIdProof) {
		this.hasIdProof = hasIdProof;
	}
	
	
	
	

	
	
}
