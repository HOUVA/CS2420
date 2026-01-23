package assign02;

import java.util.Comparator;

/**
 * Comparator that defines an ordering among current patients using their names.
 * More specifically, the comparison is based on the patient's first and last
 * names, with their UHIDs as a tie-breaker..
 * 
 * @author Barrett Carpenter and Matthew Suggars
 * @version January 22, 2026
 */

public class OrderByName<Type> implements Comparator<CurrentPatientGeneric<Type>> {
	private OrderByUHealthID<Type> UHID = new OrderByUHealthID<>();

	/**
	 * Returns a negative value if patient1's first name is less than patient2's
	 * first name. Returns a positive value if patient1's first name is greater than
	 * patient2's first name. If first names are equal, performs the same operations
	 * above but using the patients' last names. If both first and last names are
	 * equal, compares the two patients using their uHealthIDs.
	 * 
	 * @param patient1 - first patient
	 * @param patient2 - second patient
	 * @return an int as described herein
	 */

	@Override
	public int compare(CurrentPatientGeneric<Type> patient1, CurrentPatientGeneric<Type> patient2) {
		int compareFirstName = patient1.getFirstName().compareTo(patient2.getFirstName());

		if (compareFirstName == 0) {
			int compareLastName = patient1.getLastName().compareTo(patient2.getLastName());
			if (compareLastName == 0) {
				return UHID.compare(patient1, patient2);
			} else {
				return compareLastName;
			}

		} else
			return compareFirstName;
	}

}
