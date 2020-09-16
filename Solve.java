/**
 * @author tagallo
 * 
 * This is a Main class to test the methods of the Problem classes.
 */
public class Solve
{	
	/**
	 * This is the main method that takes in all the user's input and parses
	 * the string so the program knows the algorithms to use. 
	 * @param args - the user's input.
	 */
	public static void main(String[] args)
	{
		boolean debug=false;
		Problem p;
		char heuristic,algorithm,puzzle;
		String startState;
		long startTime = System.currentTimeMillis();

		if(args[args.length-1].equals("-d") || args[args.length-2].equals("-d"))
			debug=true;
		
		puzzle=args[0].charAt(0);
		algorithm=args[1].charAt(0);
		
		if(puzzle=='f')
		{
			p = new FWGC();
			switch(algorithm)
			{
			case 'd':
				DFS dfs = new DFS(p);
				dfs.solve();
				if(debug)
					dfs.printDebugInfo();
				break;
			case 'b':
				BFS bfs = new BFS(p);
				bfs.solve();
				if(debug)
					bfs.printDebugInfo();
				break;
			case 'a':
				AStarSearch aStar = new AStarSearch(p);
				aStar.solve();
				if(debug)
					aStar.printDebugInfo();
				break;
			default:
				System.out.println("Invalid Algorithm");
			}
		}
		else if(puzzle=='e')
		{
			switch(algorithm)
			{
			case 'a':			
				heuristic=args[2].charAt(0);
				startState=args[3];
				if(heuristic=='o')
					p = new EightPuzzle(startState);
				else
					p = new EightPuzzleManhattan(startState);
				
				AStarSearch aStar = new AStarSearch(p);
				aStar.solve();
				if(debug)
					aStar.printDebugInfo();
				break;
			case 'd':
				startState=args[2];
				p = new EightPuzzle(startState);
				DFS dfs = new DFS(p);
				dfs.solve();
				if(debug)
					dfs.printDebugInfo();
				break;
			case 'b':
				startState=args[2];
				p = new EightPuzzle(startState);
				BFS bfs = new BFS(p);
				bfs.solve();
				if(debug)
					bfs.printDebugInfo();
				break;
			default:
				System.out.println("Invalid Algorithm");
			}
		}
		//for printing the amount of time taken for the search
		if(args[args.length-1].equals("-t") || args[args.length-2].equals("-t"))
			System.out.println("Elapsed Time: " + (System.currentTimeMillis()-startTime) + " ms");
	}
}
