import java.util.ArrayList;
import java.util.PriorityQueue;
/*
 * Filename: AStarSearch.java
 * 
 * Name: Tom Galloway
 * ULID: tagallo
 * Course: IT340
 * Instructor: Dr. Califf
 */
/**
 * This is an implementation of an A* 
 * search to solve a problem that implements
 * the Problem interface.
 * @author Tom Galloway
 */
public class AStarSearch
{
	private int nodeCount; //keeps track of nodes checked for solution
	private Node root; //The root node for the tree to be generated from.
	
	/**
	 * A constructor that takes in the initial problem state 
	 * @param p - The initial state
	 */
	public AStarSearch(Problem p)
	{
		root=new Node(p,null);
		nodeCount=0;
	}
	
	/**
	 * This is the method used to solve a the problem. It accepts a 
	 * problem state as the parameter, then follows the A* Search algorithm
	 * until it finds a solution.
	 */
	public void solve()
	{
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.add(root);
		Node temp;
		ArrayList<Node> list;
		while(!q.isEmpty())
		{
			nodeCount++;
			temp=q.remove();
			if(temp.getData().testGoal())
			{
				System.out.println("Solution:\n");
				temp.printStateToRoot();
				System.out.println("Path Cost: " + temp.getCost());
				return;
			}
			list = makeList(temp);
			for(int i=0;i<list.size();i++)
			{
				if(!isLoop(list.get(i)))
					q.add(list.get(i));
			}
		}
	}
	/**
	 * This method is used to create a list of nodes out of the successor states for a given
	 * root problem state.
	 * @param root - The state for the successors to be generated from.
	 * @return - The ArrayList of nodes.
	 */
	private ArrayList<Node> makeList(Node root)
	{
		ArrayList<Problem> problemList = root.getData().produceSuccessors();
		ArrayList<Node> nodeList = new ArrayList<Node>();
		
		for(int i=0;i<problemList.size();i++)
		{
			nodeList.add(new Node(problemList.get(i),root));
		}
		
		return nodeList;
	}
	/**
	 * This makes sure that a given node is not a duplicate of any of it's parents.
	 * @param n - Node to be checked.
	 * @return - True if it is a duplicate, false if not.
	 */
	private boolean isLoop(Node n)
	{
		boolean result=false;
		Node temp = n.getParent();
		while(temp!=null && !result)
		{
			if(n.equals(temp))
				result=true;
			temp=temp.getParent();
		}
		return result;
	}
	/**
	 * A method for printing debug information
	 */
	public void printDebugInfo()
	{
		System.out.println("Nodes checked: " + nodeCount);
	}
}
