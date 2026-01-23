package assign02;

import java.util.Comparator;

/**
 * Comparator that defines an ordering among current patients using their Last
 * Appointment Date. More specifically, the comparison is based on the Gregorian
 * Calendar date object associated with each patient, with the patient UHID id
 * number as a tie-breaker.
 * 
 * @author Barrett Carpenter and Matthew Suggars
 * @version January 22, 2026
 */

public class OrderByDate<Type> implements Comparator<CurrentPatientGeneric<Type>> {
	private OrderByUHealthID<Type> UHID = new OrderByUHealthID<>();

	/**
	 * Returns a negative value if patient1's last visit date is BEFORE patient2's
	 * last visit date. Returns a positive value if patient1's last visit date is
	 * AFTER patient2's last visit date. If the last visit dates are equal, compares
	 * the two patients using their uHealthIDs.
	 * 
	 * @param patient1 - first patient
	 * @param patient2 - second patient
	 * @return an int as described herein
	 */

	@Override
	public int compare(CurrentPatientGeneric<Type> patient1, CurrentPatientGeneric<Type> patient2) {
		int compareLastVisit = patient1.getLastVisit().compareTo(patient2.getLastVisit());
		if (compareLastVisit == 0)
			return UHID.compare(patient1, patient2);
		else
			return compareLastVisit;
	}

}
