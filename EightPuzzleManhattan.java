import java.util.ArrayList;
/**
 * This is a class to represent the state of an Eight Puzzle with a Manhattan distance heuristic.
 * This extends the EightPuzzle class with the intent of minimizing code duplication. 
 * @author Tom Galloway
 *
 */
public class EightPuzzleManhattan extends EightPuzzle {
	
	/**
	 * Default constructor that initializes everything.
	 */
	public EightPuzzleManhattan()
	{
		super();
	}
	/**
	 * Copy constructor to create the puzzle from an already existing one.
	 * @param p - Puzzle to be copied.
	 */
	public EightPuzzleManhattan(EightPuzzleManhattan p)
	{
		super((EightPuzzle)p);
	}
	/**
	 * A constructor that initializes the state matrix from a String
	 * @param s - The string to be parsed.
	 */
	public EightPuzzleManhattan(String s)
	{
		super(s);
	}
	/**
	 * Produces an ArrayList of valid successor states for the problem at the current state
	 * @return - ArrayList of valid states
	 */
	public ArrayList<Problem> produceSuccessors() 
	{
		ArrayList<Problem> stateList = new ArrayList<Problem>(4);
		
		EightPuzzleManhattan temp = new EightPuzzleManhattan(this);
		
		if(temp.moveUp())
		{	
			stateList.add(temp);
			temp = new EightPuzzleManhattan(this);
		}
		if(temp.moveDown())
		{
			stateList.add(temp);
			temp = new EightPuzzleManhattan(this);
		}
		if(temp.moveLeft())
		{
			stateList.add(temp);
			temp = new EightPuzzleManhattan(this);
		}
		if(temp.moveRight())
		{
			stateList.add(temp);
		}
		return stateList;
	}
	/**
	 * This returns the Manhattan distance heuristic to be used
	 * in the A* search. 
	 */
	public int heuristic()
	{
		int count=0;
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				switch(state[i][j])
				{
				case 1:
					count+=Math.abs(i-0)+Math.abs(j-0);
					break;
				case 2:
					count+=Math.abs(i-0)+Math.abs(j-1);
					break;
				case 3:
					count+=Math.abs(i-0)+Math.abs(j-2);
					break;
				case 4:
					count+=Math.abs(i-1)+Math.abs(j-0);
					break;
				case 5:
					count+=Math.abs(i-1)+Math.abs(j-1);
					break;
				case 6:
					count+=Math.abs(i-1)+Math.abs(j-2);
					break;
				case 7:
					count+=Math.abs(i-2)+Math.abs(j-0);
					break;
				case 8:
					count+=Math.abs(i-2)+Math.abs(j-1);
					break;
				case 0:
					// We do not treat the empty space as a tile 
					break;
				default:
					System.out.println("Invalid Tile");
				}
			}
		}
		return count;
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
		EightPuzzleManhattan temp = (EightPuzzleManhattan)e;
		if(this.heuristic()+this.cost<temp.heuristic()+temp.cost)
			result=-1;
		else if(this.heuristic()+this.cost>temp.heuristic()+temp.cost)
			result=1;
		return result;
	}
}
