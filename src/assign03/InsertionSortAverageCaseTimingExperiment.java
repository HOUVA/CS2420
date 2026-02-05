package assign03;

import java.util.Comparator;
import assign04.InsertionSortWorstCaseTimingExperiment;
import assign04.LargestNumberSolver;
import timing.ArrayGenerator;
import timing.TimingExperiment;

public class InsertionSortAverageCaseTimingExperiment extends TimingExperiment{
	
	private static String problemSizeDescription = "size";
	private static int problemSizeMin = 10;
	private static int problemSizeCount = 150;
	private static int problemSizeStep = 10;
	private static int experimentIterationCount = 50;

	private Integer[] experimentArray;

	public static void main(String[] args) {
		TimingExperiment timingExperiment = new InsertionSortWorstCaseTimingExperiment();

		System.out.println("\n---Computing timing results---\n");
		timingExperiment.printResults();
	}

	public InsertionSortAverageCaseTimingExperiment() {
		super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
	}

	@Override
	protected void setupExperiment(int problemSize) {
		experimentArray = ArrayGenerator.generatePermutedArray(problemSize);

	}

	@Override
	protected void runComputation() {
		LargestNumberSolver.insertionSort(experimentArray, Comparator.<Integer>naturalOrder());

	}

}
