import java.util.Stack;
/**
 * This is a Node class to be used in a search algorithm to solve a Problem.
 * I made this a separate class instead of using private subclasses with the intent
 * of minimizing code duplication 
 * @author Tom Galloway
 */
public class Node implements Comparable<Node>
{
	protected Problem data; //The state the node is holding
	protected Node parent; //The node that the current state was generated from
	
	/**
	 * A constructor that takes in the node's state, and parent pointer.
	 * @param data - Problem state
	 * @param parent - Parent from which current state was generated
	 */
	public Node(Problem data,Node parent)
	{
		this.data = data;
		this.parent = parent;
	}
	/**
	 * 
	 * @return Node's state
	 */
	public Problem getData()
	{
		return data;
	}
	/**
	 * 
	 * @return Parent from which current state was generated
	 */
	public Node getParent()
	{
		return parent;
	}
	/**
	 * Returns a string of the node's state
	 */
	public String toString()
	{
		return data.toString();
	}
	/**
	 * Checks if current node is equal to parameter node
	 * @param n - Node to be compared to
	 * @return - Whether or not they are the same state
	 */
	public boolean equals(Node n)
	{
		return data.equals(n.getData());
	}
	/**
	 * Returns the cost to get to this node's state
	 */
	public int getCost()
	{
		return data.getCost();
	}
	/**
	 * Prints every state in the path required to get to this node.
	 * A stack is used because DFS sometimes runs out of memory if this
	 * is done recursively. 
	 */
	public void printStateToRoot()
	{
		Stack<Problem> path = new Stack<Problem>();
		path.add(data);
		Node temp = parent;
		while(temp!=null)
		{
			path.add(temp.data);
			temp = temp.parent;
		}
		while(!path.isEmpty())
		{
			System.out.println(path.pop());
		}
	}

	@Override
	/**
	 * Compares the node to another node. Returns the result of the comparison
	 * between the nodes' problem states. 
	 */
	public int compareTo(Node o)
	{
		return data.compareTo(o.data);
	}
}