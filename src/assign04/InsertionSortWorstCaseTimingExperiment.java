package assign04;

/**
 * Experiment to measure the running time for the insertionSort method for
 * LargestNumberSolver for various problem sizes.
 * 
 * @author Matthew Suggars
 * @version 02-05-26
 */
import timing.TimingExperiment;

import java.util.Comparator;

import timing.ArrayGenerator;

public class InsertionSortWorstCaseTimingExperiment extends TimingExperiment {

	private static String problemSizeDescription = "size";
	private static int problemSizeMin = 10;
	private static int problemSizeCount = 150;
	private static int problemSizeStep = 10;
	private static int experimentIterationCount = 50;

	protected Integer[] array;

	public static void main(String[] args) {
		TimingExperiment timingExperiment = new InsertionSortWorstCaseTimingExperiment();
		
		System.out.println("Worst Case");
		System.out.println("\n---Computing timing results---\n");
		timingExperiment.printResults();
	}

	public InsertionSortWorstCaseTimingExperiment() {
		super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
	}

	@Override
	protected void setupExperiment(int problemSize) {
		array = ArrayGenerator.generateDescendingArray(problemSize);

	}

	@Override
	protected void runComputation() {
		LargestNumberSolver.insertionSort(array, Comparator.<Integer>naturalOrder());

	}

}
