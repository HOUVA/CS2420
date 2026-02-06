package assign04;

import java.util.Comparator;

import timing.ArrayGenerator;
import timing.TimingExperiment;

public class InsertionSortBestCaseTimingExperiment extends TimingExperiment {
	private static String problemSizeDescription = "size";
	private static int problemSizeMin = 10;
	private static int problemSizeCount = 500;
	private static int problemSizeStep = 10;
	private static int experimentIterationCount = 50;

	protected Integer[] array;
	private Integer[] sortedArray;

	public static void main(String[] args) {
		TimingExperiment timingExperiment = new InsertionSortWorstCaseTimingExperiment();

		System.out.println("Best Case");
		System.out.println("\n---Computing timing results---\n");
		timingExperiment.printResults();
	}

	public InsertionSortBestCaseTimingExperiment() {
		super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
	}

	@Override
	protected void setupExperiment(int problemSize) {
		array = ArrayGenerator.generateNearlyAscendingArray(problemSize);
		sortedArray = new Integer[problemSize];
		for (int i = 0; i < problemSize; i++)
			sortedArray[i] = i;
	}

	@Override
	protected void runComputation() {
		LargestNumberSolver.insertionSort(array, Comparator.<Integer>naturalOrder());

	}
}
