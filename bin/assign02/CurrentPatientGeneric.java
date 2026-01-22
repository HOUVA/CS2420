package assign02;

import java.util.GregorianCalendar;

/**
 * This generic class represents a UHealth patient who is currently attending a
 * facility. Each patient is assigned a physician and the date of their last
 * visit is saved.
 * 
 * @author Barrett Carpenter and Matthew Suggars
 * @version January 19, 2025.
 */
public class CurrentPatientGeneric<Type> extends Patient {
	private Type physician;
	private GregorianCalendar lastVisit;

	/**
	 * Creates a current patient with the given patient information, as well as a
	 * physician ID Type and date of their last visit.
	 * 
	 * @param firstName - of the patient
	 * @param lastName  - of the patient
	 * @param uHealthID - of the patient
	 * @param physician ID Type
	 * @param lastVisit as a Calendar Date
	 */
	public CurrentPatientGeneric(String firstName, String lastName, UHealthID uHealthID, Type physician,
			GregorianCalendar lastVisit) {
		super(firstName, lastName, uHealthID);
		this.physician = physician;
		this.lastVisit = lastVisit;
	}

	/**
	 * Getter method for the ID Type of the current patients physician.
	 * 
	 * @return the physicians ID 
	 */
	public Type getPhysician() {
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
	 * @param newPhysician ID 
	 */
	public void updatePhysician(Type newPhysician) {
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
