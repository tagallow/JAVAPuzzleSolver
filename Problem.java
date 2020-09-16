import java.util.ArrayList;
/**
 * This is a Problem interface to represent a generic problem
 * scenario to be solved using any standard search algorithm.
 * 
 * @author Tom Galloway
 */
public interface Problem extends Comparable<Problem> {
	
	void createInitialState();
	
	boolean testGoal();
	
	ArrayList<Problem> produceSuccessors();
	
	int getCost();
	
	boolean equals(Problem p);
	
	void printState();
	
	int compareTo(Problem e);
	
	int heuristic();
}
