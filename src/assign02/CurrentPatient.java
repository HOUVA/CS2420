package assign02;

import java.util.GregorianCalendar;

/**
 * This class represents a UHealth patient who is currently attending a
 * facility. Each patient has a physician and a date of when they had their
 * visit.
 * 
 * @author Barrett Carpenter and Matthew Suggars
 * @version January 19, 2025.
 */
public class CurrentPatient extends Patient {
	private int physician;
	private GregorianCalendar lastVisit;

	/**
	 * Creates a current patient with the given patient information, as well as a
	 * physician UID and date of their last visit.
	 * 
	 * @param firstName - of the patient
	 * @param lastName - of the patient
	 * @param uHealthID - of the patient
	 * @param physician UID number
	 * @param lastVisit as a Calendar Date
	 */
	public CurrentPatient(String firstName, String lastName, UHealthID uHealthID, int physician,
			GregorianCalendar lastVisit) {
		super(firstName, lastName, uHealthID);
		this.physician = physician;
		this.lastVisit = lastVisit;
	}

	/**
	 * Getter method for the UID of the current patients physician.
	 * 
	 * @return the physicians UID
	 */
	public int getPhysician() {
		return this.physician;
	}

	/**
	 * Getter method for the date of the current patients last visit.
	 * 
	 * @return the date of the last visit
	 */
	public GregorianCalendar getLastVisit() {
		return this.lastVisit;
	}

	/**
	 * Updates the current patients physician
	 * 
	 * @param newPhysician UID number
	 */
	public void updatePhysician(int newPhysician) {
		this.physician = newPhysician;
	}

	/**
	 * Updates the current patients last visit with their physician.
	 * 
	 * @param date
	 */
	public void updateLastVisit(GregorianCalendar date) {
		this.lastVisit = date;
	}

}
