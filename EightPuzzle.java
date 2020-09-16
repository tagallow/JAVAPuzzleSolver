/*
 * Filename: EightPuzzle.java
 * 
 * Name: Tom Galloway
 * ULID: tagallo
 * Course: IT340
 * Instructor: Dr. Califf
 */
import java.util.ArrayList;

/**
 * This is a class to represent the state of an Eight Puzzle with a number out of place heuristic.
 * @author Tom Galloway
 *
 */
public class EightPuzzle implements Problem {

	protected int[][] state; //A two dimensional array to represent the puzzle. 0 is used to represent the blank space.
	protected int row,col; //Two variables to help easily track the location of the blank (0) space.
	protected int cost; //A variable to keep track of the cost of an operation.
	
	/**
	 * Default constructor that initializes everything.
	 */
	public EightPuzzle()
	{
		state = new int[3][3];
		cost=0;
		createInitialState();
	}
	/**
	 * Copy constructor to create the puzzle from an already existing one.
	 * @param p - Puzzle to be copied.
	 */
	public EightPuzzle(EightPuzzle p)
	{
		state = new int[3][3];
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				state[i][j]=p.state[i][j];			
			}
		}
		row=p.row;
		col=p.col;
		cost=p.cost;
	}
	/**
	 * A constructor that initializes the state matrix from a String
	 * @param s - The string to be parsed.
	 */
	public EightPuzzle(String s)
	{
		state = new int[3][3];
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				state[i][j]=Integer.parseInt(""+s.charAt(i*3+j));
				if(state[i][j]==0)
				{
					row=i;
					col=j;
				}			
			}
		}
		cost=0;
	}
	
	@Override
	/**
	 * Asks the user for the initial state of the puzzle. Right now a valid 
	 * puzzle is hard coded in for debugging.
	 */
	public void createInitialState() 
	{
		int[] solution = {1,2,3,4,5,6,7,8,0};
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				state[i][j]=solution[i*3+j]; //row major form
				if(state[i][j]==0)
				{
					row=i;
					col=j;
				}
			}
		}
	}
	@Override
	/**
	 * Tests whether or not the puzzle is solved.
	 */
	public boolean testGoal()
	{
		int[] solution = {1,2,3,4,5,6,7,8,0};
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(state[i][j]!=solution[i*3+j]) //row major form
					return false;
			}
		}
		return true;
	}
	@Override
	/**
	 * Produces an ArrayList of valid successor states for the problem at the current state
	 * @return - ArrayList of valid states
	 */
	public ArrayList<Problem> produceSuccessors() 
	{
		ArrayList<Problem> stateList = new ArrayList<Problem>(4);
		
		EightPuzzle temp = new EightPuzzle(this);
		
		if(temp.moveUp())
		{	
			stateList.add(temp);
			temp = new EightPuzzle(this);
		}
		if(temp.moveDown())
		{
			stateList.add(temp);
			temp = new EightPuzzle(this);
		}
		if(temp.moveLeft())
		{
			stateList.add(temp);
			temp = new EightPuzzle(this);
		}
		if(temp.moveRight())
		{
			stateList.add(temp);
			//temp = new EightPuzzle(this);
		}
		return stateList;
	}
	/**
	 * Checks if there is room to move the blank space up, changes state if there is
	 * @return - Whether or not the move was valid.
	 */
	protected boolean moveUp()
	{
		if(row==0)
			return false;
		
		state[row][col]=state[row-1][col];
		state[row-1][col]=0;
		row--;
		
		cost++;
		return true;
	}
	/**
	 * Checks if there is room to move the blank space down, changes state if there is
	 * @return - Whether or not the move was valid.
	 */
	protected boolean moveDown()
	{
		if(row==2)
			return false;
		
		state[row][col]=state[row+1][col];
		state[row+1][col]=0;
		row++;
		
		cost++;
		return true;
	}
	/**
	 * Checks if there is room to move the blank space left, changes state if there is
	 * @return - Whether or not the move was valid.
	 */
	protected boolean moveLeft()
	{
		if(col==0)
			return false;
		
		state[row][col]=state[row][col-1];
		state[row][col-1]=0;
		col--;
		
		cost++;
		return true;
	}
	/**
	 * Checks if there is room to move the blank space right, changes state if there is
	 * @return - Whether or not the move was valid.
	 */
	protected boolean moveRight()
	{
		if(col==2)
			return false;
		
		state[row][col]=state[row][col+1];
		state[row][col+1]=0;
		col++;
		
		cost++;
		return true;
	}
	/**
	 * This tests if two states are equal.
	 */
	public boolean equals(Problem p)
	{
		EightPuzzle temp = (EightPuzzle)p;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(state[i][j]!=temp.state[i][j])
					return false;
							
			}
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
	 * Produces a string of the current state
	 */
	public String toString()
	{
		String str="";
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(j!=0)
					str+=" ";
				if(state[i][j]==0)
				{
					str+=" ";
				}
				else
				{
					str+=state[i][j];
				}
			}
			str+="\n";
		}
		
		return str;
	}
	/**
	 * Prints the String of the current state
	 */
	public void printState()
	{
		System.out.println(this.toString());
	}
	/**
	 * This returns a number out of place heuristic to be used in an A* search.
	 */
	public int heuristic()
	{
		int count=0;
		
		int[] solution = {1,2,3,4,5,6,7,8,0};
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(state[i][j]!=solution[i*3+j]) //row major form
					count++;
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
		EightPuzzle temp = (EightPuzzle)e;
		if(this.heuristic()+this.cost<temp.heuristic()+temp.cost)
			result=-1;
		else if(this.heuristic()+this.cost>temp.heuristic()+temp.cost)
			result=1;
		return result;
	}
}
