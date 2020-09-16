import java.util.ArrayList;
import java.util.Stack;
/**
 * This is an implementation of a breadth first
 * search to solve a problem that implements
 * the Problem interface.
 * @author Tom Galloway
 */
public class DFS
{
	private int nodeCount; //keeps track of nodes tested for solution
	private Node root; //The root node for the tree to be generated from.

	/**
	 * A constructor that takes in the initial problem state 
	 * @param p - The initial state
	 */
	public DFS(Problem p)
	{
		root=new Node(p,null);
		nodeCount=0;
	}
	/**
	 * Solves the problem using DFS
	 */
	public void solve()
	{
		Stack<Node> q = new Stack<Node>();
		q.add(root);
		Node temp;
		ArrayList<Node> list;
		while(!q.isEmpty())
		{
			nodeCount++;
			temp=q.pop();
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
					q.push(list.get(i));
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
		System.out.println("Nodes tested: " + nodeCount);
	}
}
