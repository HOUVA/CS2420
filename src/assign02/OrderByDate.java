package assign02;

import java.util.Comparator;

import assign02.CurrentPatientGeneric;
import assign02.OrderByUHealthID;

public class OrderByDate<Type> implements Comparator<CurrentPatientGeneric<Type>> {
	private OrderByUHealthID<Type> UHID = new OrderByUHealthID<>();
	
	@Override
	public int compare(CurrentPatientGeneric<Type> patient1, CurrentPatientGeneric<Type> patient2) {
		int compareLastVisit = patient1.getLastVisit().compareTo(patient2.getLastVisit());
		if (compareLastVisit == 0)
			return UHID.compare(patient1, patient2);
		else 
			return compareLastVisit;
	}

}
