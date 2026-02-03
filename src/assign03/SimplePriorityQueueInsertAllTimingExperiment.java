package assign03;

import java.util.ArrayList;
import java.util.Random;

import timing.TimingExperiment;

/**
 * Experiment to measure the running time for the insertAll method for
 * SimplePriorityQueue for various problem sizes.
 *
 * @author Barrett Carpenter and Matthew Suggars
 * @version January 29, 2025
 */
public class SimplePriorityQueueInsertAllTimingExperiment extends TimingExperiment {

	private static String problemSizeDescription = "SPQ size";
	private static int problemSizeMin = 10000;
	private static int problemSizeCount = 30;
	private static int problemSizeStep = 5000;
	private static int experimentIterationCount = 50;

	private final static Random rng = new Random();

	private SimplePriorityQueue<Integer> priorityQueue;
	private ArrayList<Integer> collection;

	public static void main(String[] args) {
		TimingExperiment timingExperiment = new SimplePriorityQueueContainsAllTimingExperiment();

		System.out.println("\n---Computing timing results---\n");
		timingExperiment.printResults();
	}

	public SimplePriorityQueueInsertAllTimingExperiment() {
		super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
	}

	/**
	 * Fills the collection with random integers and initializes the SimplePriorityQueue.
	 * 
	 * @param problemSize - the number of integers to add to the priority queue
	 */
	@Override
	protected void setupExperiment(int problemSize) {
		collection = new ArrayList<Integer>();
		collection.add(rng.nextInt(10));
		for (int i = 1; i < problemSize; i++)
			collection.add(collection.get(i - 1) + rng.nextInt(1, 11));

		priorityQueue = new SimplePriorityQueue<Integer>();

	}

	/**
	 * Runs the insertAll method for the priority queue.
	 */
	@Override
	protected void runComputation() {
		priorityQueue.insertAll(collection);
	}
}
