/*
 * Filename: BFS.java
 * Created: Feb 2, 2012
 * 
 * Name: Tom Galloway
 * ULID: tagallo
 * Course: IT340
 * Instructor: Dr. Califf
 */
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
/**
 * This is an implementation of a breadth first
 * search to solve a problem that implements
 * the Problem interface.
 * @author Tom Galloway
 */
public class BFS
{
	private int nodeCount; //keeps track of nodes checked for solution
	private Node root; //The root node for the tree to be generated from.

	/**
	 * A constructor that takes in the initial problem state 
	 * @param p - The initial state
	 */
	public BFS(Problem p)
	{
		root=new Node(p,null);
		nodeCount=0;
	}
	
	/**
	 * This is the method used to solve a the problem. It accepts a 
	 * problem state as the parameter, then follows a generic BFS algorithm
	 * until it finds a solution.
	 */
	public void solve()
	{
		Queue<Node> q = new LinkedList<Node>();
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
