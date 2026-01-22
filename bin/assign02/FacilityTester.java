package assign02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for Facility.
 * 
 * @author CS 2420 course staff, Barrett Carpenter, and Matthew Suggars
 * @version January 19, 2025;
 */
public class FacilityTester {

	private Facility emptyFacility, verySmallFacility, smallFacility, largeFacility;
	private UHealthID uHID1, uHID2, uHID3;
	private GregorianCalendar date1, date2, date3;

	@BeforeEach
	void setUp() throws Exception {

		uHID1 = new UHealthID("AAAA-1111");
		uHID2 = new UHealthID("BCBC-2323");
		uHID3 = new UHealthID("HRHR-7654");

		date1 = new GregorianCalendar(2023, 0, 1);
		date2 = new GregorianCalendar(2023, 3, 17);
		date3 = new GregorianCalendar(2022, 8, 21);

		emptyFacility = new Facility();

		verySmallFacility = new Facility();
		verySmallFacility.addPatient(new CurrentPatient("Jane", "Doe", uHID1, 1010101, date1));
		verySmallFacility.addPatient(new CurrentPatient("Drew", "Hall", uHID2, 3232323, date2));
		verySmallFacility.addPatient(new CurrentPatient("Riley", "Nguyen", uHID3, 9879876, date3));

		smallFacility = new Facility();
		smallFacility.addAll(readFromFile("src/assign02/small_patient_list.txt"));

		largeFacility = new Facility();
		UHealthID[] uHIDArray = generateUHIDs(50);
		GregorianCalendar[] datesArray = generateDates(50);
		String[] firstNamesArray = generateNames(50);
		String[] lastNamesArray = generateNames(50);
		int[] physicianArray = { 0000000, 1111111, 2222222, 3333333, 4444444 };

		for (int i = 0; i < 50; i++) {
			int physician;
			if (i < 10)
				physician = physicianArray[0];
			else if (i >= 10 && i < 20)
				physician = physicianArray[1];
			else if (i >= 20 && i < 30)
				physician = physicianArray[2];
			else if (i >= 30 && i < 40)
				physician = physicianArray[3];
			else
				physician = physicianArray[4];

			largeFacility.addPatient(
					new CurrentPatient(firstNamesArray[i], lastNamesArray[i], uHIDArray[i], physician, datesArray[i]));
		}

	}

	// Empty Facility tests --------------------------------------------------------

	@Test
	public void testEmptyLookupUHID() {
		assertNull(emptyFacility.lookupByUHID(uHID1));
	}

	@Test
	public void testEmptyLookupPhysician() {
		ArrayList<CurrentPatient> patients = emptyFacility.lookupByPhysician(1010101);
		assertEquals(0, patients.size());
	}

	@Test
	public void testEmptySetVisit() {
		// ensure no exceptions thrown
		emptyFacility.setLastVisit(uHID2, date3);
	}

	@Test
	public void testEmptySetPhysician() {
		// ensure no exceptions thrown
		emptyFacility.setPhysician(uHID2, 1010101);
	}

	@Test
	public void testEmptyGetRecentPatients() {
		ArrayList<CurrentPatient> patients = emptyFacility.getRecentPatients(date3);
		assertEquals(0, patients.size());
	}

	@Test
	public void testEmptyGetPhysicianList() {
		assertEquals(0, emptyFacility.getPhysicianList().size());
	}

	@Test
	public void testEmptyAddPatient() {
		emptyFacility.addPatient(new CurrentPatient("Blake", "Stodden", uHID1, 1111111, date1));
		assertEquals(1, emptyFacility.lookupByPhysician(1111111).size());
	}

	// Very small facility tests ---------------------------------------------------

	@Test
	public void testVerySmallLookupUHID() {
		Patient expected = new Patient("Drew", "Hall", new UHealthID("BCBC-2323"));
		CurrentPatient actual = verySmallFacility.lookupByUHID(new UHealthID("BCBC-2323"));
		assertEquals(expected, actual);
	}

	@Test
	public void testVerySmallLookupPhysicianCount() {
		ArrayList<CurrentPatient> actualPatients = verySmallFacility.lookupByPhysician(9879876);
		assertEquals(1, actualPatients.size());
	}

	@Test
	public void testVerySmallLookupPhysicianPatient() {
		Patient expectedPatient = new Patient("Riley", "Nguyen", new UHealthID("HRHR-7654"));
		ArrayList<CurrentPatient> actualPatients = verySmallFacility.lookupByPhysician(9879876);
		assertEquals(expectedPatient, actualPatients.get(0));
	}

	@Test
	public void testVerySmallAddNewPatient() {
		assertTrue(verySmallFacility
				.addPatient(new CurrentPatient("Jane", "Doe", new UHealthID("BBBB-2222"), 1010101, date1)));
	}

	@Test
	public void testVerySmallUpdatePhysician() {
		verySmallFacility.lookupByUHID(uHID1).updatePhysician(9090909);
		CurrentPatient patient = verySmallFacility.lookupByUHID(uHID1);
		assertEquals(9090909, patient.getPhysician());
	}

	// Small facility tests ----------------------------------------------------

	@Test
	public void testSmallGetRecentPatients() {
		ArrayList<CurrentPatient> actual = smallFacility.getRecentPatients(new GregorianCalendar(2020, 0, 0));
		assertEquals(2, actual.size());
	}

	@Test
	public void testSmallGetRecentPatientsFutureDate() {
		ArrayList<CurrentPatient> actual = smallFacility.getRecentPatients(new GregorianCalendar(2030, 0, 0));
		assertEquals(0, actual.size());
	}

	@Test
	public void testSmallGetRecentPatientsPastDate() {
		ArrayList<CurrentPatient> actual = smallFacility.getRecentPatients(new GregorianCalendar(1990, 0, 0));
		assertEquals(11, actual.size());
	}

	@Test
	public void testSmallAddPatient() {
		assertTrue(smallFacility.addPatient(new CurrentPatient("Lynx", "Shelly", uHID1, 000000, date1)));

	}

	@Test
	public void testSmallAddPatientAlreadyExists() {
		assertFalse(smallFacility.addPatient(new CurrentPatient("Jordan", "Jones", new UHealthID("AEHK-3524"), 6786786,
				new GregorianCalendar(2019, 9, 19))));
	}

	@Test
	public void testSmallLookupUHID() {
		CurrentPatient foundPatient = smallFacility.lookupByUHID(new UHealthID("JHSD-7483"));
		assertEquals("Blake", foundPatient.getFirstName());
	}

	@Test
	public void testSmallLookupUHIDPatientDoesNotExist() {
		assertNull(smallFacility.lookupByUHID(uHID1));
	}

	@Test
	public void testSmallLookupPhysicianPatients() {
		CurrentPatient expectedPatient1 = new CurrentPatient("Samantha", "Schooner", new UHealthID("OUDC-6143"),
				1111111, new GregorianCalendar(2001, 4, 3));
		CurrentPatient expectedPatient2 = new CurrentPatient("Amy", "Gilmer", new UHealthID("VBIU-1616"), 1111111,
				new GregorianCalendar(2000, 8, 8));
		ArrayList<CurrentPatient> actual = smallFacility.lookupByPhysician(1111111);

		assertEquals(expectedPatient1, actual.get(0));
		assertEquals(expectedPatient2, actual.get(1));

	}

	@Test
	public void testSmallLookupPhysicianNoPatients() {
		assertEquals(0, smallFacility.lookupByPhysician(1212121).size());
	}

	@Test
	public void testGetPhysicianListNoDuplicates() {
		assertEquals(7, smallFacility.getPhysicianList().size());
	}

	@Test
	public void testGetPhysicianListContains() {
		assertTrue(smallFacility.getPhysicianList().contains(1111111));
	}

	@Test
	public void testSmallSetPhysician() {
		assertEquals(2, smallFacility.lookupByPhysician(1111111).size());
		smallFacility.setPhysician(new UHealthID("QRST-3456"), 1111111);
		assertEquals(3, smallFacility.lookupByPhysician(1111111).size());
	}

	@Test
	public void testSmallSetLastVisit() {
		GregorianCalendar date = new GregorianCalendar(2020, 0, 0);
		assertEquals(2, smallFacility.getRecentPatients(date).size());
		smallFacility.setLastVisit(new UHealthID("ITER-7777"), new GregorianCalendar(2024, 0, 0));
		assertEquals(3, smallFacility.getRecentPatients(date).size());
	}

	// Large Facility tests ------------------------------------------------------

	/**
	 * This method prints all the patients and their information in the largeFacility.
	 */
	@Test
	public void printPatients() {
		for (int i = 0; i <= 4444444; i += 1111111) {
			ArrayList<CurrentPatient> patients = largeFacility.lookupByPhysician(i);
			for (CurrentPatient patient : patients) {
				System.out.print(patient.getFirstName() + " " + patient.getLastName() + " "
						+ patient.getUHealthID().toString() + " " + patient.getPhysician() + " ");
				System.out.print(patient.getLastVisit().toZonedDateTime());
				System.out.println();
			}
			System.out.println();
		}

	}

	// Helper methods ------------------------------------------------------------

	/**
	 * A private helper to generate unique UHealthIDs. Valid for up to 260,000 IDs.
	 * 
	 * @param howMany - IDs to make
	 * @return an array of UHealthIDs
	 */
	private UHealthID[] generateUHIDs(int howMany) {
		UHealthID[] ids = new UHealthID[howMany];
		for (int i = 0; i < howMany; i++) {
			String prefix = "JKL" + (char) ('A' + (i / 10000) % 26);
			ids[i] = new UHealthID(prefix + "-" + String.format("%04d", i % 10000));
		}
		return ids;
	}

	/**
	 * A private helper to generate dates.
	 * 
	 * @param howMany - dates to generate
	 * @return an array of dates
	 */
	private GregorianCalendar[] generateDates(int howMany) {
		GregorianCalendar[] dates = new GregorianCalendar[howMany];
		for (int i = 0; i < howMany; i++)
			dates[i] = new GregorianCalendar(2000 + i % 22, i % 12, i % 28);
		return dates;
	}

	/**
	 * A private helper to generate names.
	 * 
	 * @param howMany - names to generate
	 * @return an array of names
	 */
	private String[] generateNames(int howMany) {
		String[] names = new String[howMany];
		Random rng = new Random();
		for (int i = 0; i < howMany; i++)
			names[i] = "" + (char) ('A' + rng.nextInt(26)) + (char) ('a' + rng.nextInt(26))
					+ (char) ('a' + rng.nextInt(26)) + (char) ('a' + rng.nextInt(26));
		return names;
	}

	/**
	 * Adds the patients specified by the input file to a list.
	 * 
	 * Assumes a strict file format: (each line): FirstName LastName ABCD-0123
	 * u0123456 2023 05 16
	 * 
	 * Also assumes there are no duplicate patients in the file.
	 * 
	 * @param filename - full or relative path to file containing patient data
	 */
	public ArrayList<CurrentPatient> readFromFile(String filename) {
		ArrayList<CurrentPatient> patients = new ArrayList<CurrentPatient>();
		try {
			Scanner fileIn = new Scanner(new File(filename));
			int lineNumber = 0;

			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();
				lineNumber++;
				patients.add(parsePatient(line, lineNumber));
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage() + "  Patient file couldn't be opened.");
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
					+ ". Not all patients added to list.");
		}
		return patients;
	}

	/**
	 * Helper method for parsing the information about a patient from file.
	 * 
	 * @param line       - string to be parsed
	 * @param lineNumber - line number in file, used for error reporting (see above)
	 * @return the Patient constructed from the information
	 * @throws ParseException if file containing line is not properly formatted (see
	 *                        above)
	 */
	private CurrentPatient parsePatient(String line, int lineNumber) throws ParseException {
		Scanner lineIn = new Scanner(line);
		lineIn.useDelimiter(" ");

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("First name", lineNumber);
		}
		String firstName = lineIn.next();

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("Last name", lineNumber);
		}
		String lastName = lineIn.next();

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("UHealth ID", lineNumber);
		}
		String patientIDString = lineIn.next();

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("physician", lineNumber);
		}
		String physicianString = lineIn.next();
		int physician = Integer.parseInt(physicianString.substring(1, 8));

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("year of last visit", lineNumber);
		}
		String yearString = lineIn.next();
		int year = Integer.parseInt(yearString);

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("month of last visit", lineNumber);
		}
		String monthString = lineIn.next();
		int month = Integer.parseInt(monthString);

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("day of last visit", lineNumber);
		}
		String dayString = lineIn.next();
		int day = Integer.parseInt(dayString);

		GregorianCalendar lastVisit = new GregorianCalendar(year, month, day);

		lineIn.close();

		return new CurrentPatient(firstName, lastName, new UHealthID(patientIDString), physician, lastVisit);
	}
}
