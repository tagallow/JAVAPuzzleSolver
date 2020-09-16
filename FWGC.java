/*
 * Filename: FWGC.java
 * 
 * Name: Tom Galloway
 * ULID: tagallo
 * Course: IT340
 * Instructor: Dr. Califf
 */
import java.util.ArrayList;
/**
 * This is a class to represent a state in the Farmer, Wolf, Goat, Cabbage problem.
 * @author Tom Galloway
 *
 */
public class FWGC implements Problem {

	private boolean[] state; //The state of the problem represented by an array of booleans. False of west, true for east.
	private int cost;
//	private boolean marked;
	
	/**
	 * A default constructor that just creates the array and initializes it.
	 */
	public FWGC()
	{
		state = new boolean[4];
		createInitialState();
	}
	/**
	 * A copy constructor that takes in a FWGC problem and copies the values of the state array.
	 * Cost is always 1 so it is again set to 1.
	 * @param f - The problem to be copied.
	 */
	public FWGC(FWGC f)
	{
		state = new boolean[4];
		for(int i=0;i<4;i++)
		{
			state[i]=f.state[i];
		}
		cost = f.cost;
	}
	/**
	 * A copy constructor that instead takes in an array of booleans since that is all you need
	 * to represent the state. I haven't yet decided which will work better in the long run.
	 * @param s - the array to be copied.
	 */
//	public FWGC(boolean[] s)
//	{
//		state = new boolean[4];
//		for(int i=0;i<4;i++)
//		{
//			state[i]=s[i];
//		}
//		cost=1;
//	}
	
	@Override
	/**
	 * Sets all values to false, to represent West. Cost is set to 1 since all actions cost 1 movement.
	 */
	public void createInitialState() 
	{
		cost=1;
		for(int i=0;i<4;i++)
		{
			state[i]=false;
		}
	}
	@Override
	/**
	 * Tests of all values are true (east).
	 */
	public boolean testGoal() 
	{
		for(int i=0;i<4;i++)
		{
			if(!state[i])
				return false;
		}
		return true;
	}
	@Override
	/**
	 * Produces an ArrayList of states that can arrived at directly from the calling object's 
	 * current state. A temporary object is created so that the current state's array can
	 * remain unchanged. 
	 */
	public ArrayList<Problem> produceSuccessors() 
	{
		ArrayList<Problem> stateList = new ArrayList<Problem>();
		FWGC temp = new FWGC(this);
		if(temp.moveFarmer())
		{
			stateList.add(temp);
			temp = new FWGC(this);
		}
		if(temp.moveWolf())
		{
			stateList.add(temp);
			temp = new FWGC(this);
		}
		if(temp.moveGoat())
		{
			stateList.add(temp);
			temp = new FWGC(this);
		}
		if(temp.moveCabbage())
		{
			stateList.add(temp);
			temp = new FWGC(this);
		}
		return stateList;
	}
	/**
	 * This moves only the farmer for the current state.
	 * If the move is valid, the method returns true and the state is changed
	 * Otherwise, the farmer is moved back and the method returns false.
	 */
	private boolean moveFarmer()
	{
		state[0]=!state[0];
		if(isValid())
		{
			cost++;
			return true;
		}
		else
		{
			state[0]=!state[0];
			return false;
		}
	}
	/**
	 * Moves an item (and the farmer) to the other side.
	 * @param i - the index of the item to be moved
	 * 1 for wolf, 2 for goat, 3 for cabbage
	 * @return - whether or not the result of the move was valid. If it was not,
	 * the move is undone
	 */
	private boolean moveItem(int i)
	{
		//item cannot move unless on the same side as farmer
		if(state[0]!=state[i])
			return false;
		
		//item and farmer move to other side
		state[0]=!state[0];
		state[i]=!state[i];
		
		/*
		 * If this move is valid, return true
		 * otherwise move items back and return false
		 */
		if(isValid())
		{
			cost++;
			return true;
		}
		else
		{
			state[0]=!state[0];
			state[i]=!state[i];
			return false;
		}
		
	}
	/**
	 * Calls moveItem method for the wolf
	 * @return - Whether or not move was valid
	 */
	private boolean moveWolf()
	{
		return moveItem(1);
	}
	/**
	 * Calls moveItem method for the goat
	 * @return - Whether or not move was valid
	 */
	private boolean moveGoat()
	{
		return moveItem(2);
	}
	/**
	 * Calls moveItem method for the cabbage
	 * @return - Whether or not move was valid
	 */
	private boolean moveCabbage()
	{
		return moveItem(3);
	}
	
	/**
	 * Checks if the current state is valid
	 */
	private boolean isValid()
	{
		//wolf and goat cannot be alone without farmer
		if((state[1]==state[2]) && state[1]!=state[0])
		{
			return false;
		}
		//cabbage and goat cannot be alone without farmer
		if((state[2]==state[3]) && state[2]!=state[0])
		{
			return false;
		}
		return true;
	}
	/**
	 * Returns the cost required to achieve the current state. Since all operations
	 * cost 1, this will always be 1. I think.
	 */
	public int getCost()
	{
		return cost;
	}
	/**
	 * This tests if two states are equal.
	 */
	public boolean equals(Problem p)
	{
		FWGC temp = (FWGC)p;
		for(int i=0;i<4;i++)
		{
			if(state[i]!=temp.state[i])
				return false;
		}
		return true;
	}
	/**
	 * Produces a string of the current state
	 */
	public String toString()
	{
		String str="";
		
		for(int i=0;i<4;i++)
		{
			if(state[i])
				str+="e ";
			else
				str+="w ";	
		}
		return str;
	}
	/**
	 * This returns a number out of place heuristic to be used in an A* search.
	 */
	public int heuristic()
	{
		int result=0;
		
		for(int i=0;i<4;i++)
		{
			if(!state[i])
				result++;
		}
		
		return result;
	}
	/**
	 * Prints the String of the current state
	 */
	public void printState()
	{
		System.out.println(this.toString());
	}
	@Override
	/**
	 * A compare to method to be used by the priority queue in the A* search.
	 * Returns 1 if the heuristic + cost of this state is smaller than the
	 * heuristic + cost of the inputed state. 0 if they are equal, -1 if the inputed
	 * state is smaller.
	 */
	public int compareTo(Problem e)
	{
		int result=0;
		FWGC temp = (FWGC)e;
		if(this.heuristic()+this.cost<temp.heuristic()+temp.cost)
			result=-1;
		else if(this.heuristic()+this.cost>temp.heuristic()+temp.cost)
			result=1;
		return result;
	}
}
