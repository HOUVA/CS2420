package assign02;

import java.util.Comparator;

public class OrderByName<Type> implements Comparator<CurrentPatientGeneric<Type>> {
	private OrderByUHealthID<Type> UHID = new OrderByUHealthID<>();
	
	@Override
	public int compare(CurrentPatientGeneric<Type> patient1, CurrentPatientGeneric<Type> patient2) {
		int compareFirstName = patient1.getFirstName().compareTo(patient2.getFirstName());
		
		if (compareFirstName == 0) {
			int compareLastName = patient1.getLastName().compareTo(patient2.getLastName());
			if (compareLastName == 0) {
				return UHID.compare(patient1, patient2);
			}else {
				return compareLastName;
			}
				
		}else
			return compareFirstName;
	}

}
