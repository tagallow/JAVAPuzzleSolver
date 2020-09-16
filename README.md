# JAVAPuzzleSolver
Generic puzzle solver using various search algorithms

This is a program designed to solve generic logic problem using various search algorithms. Right now only two problems are implemented: the farmer, wolf, goat and cabbage problem, and and eight puzzle. Three search algorithms are also implemented: Breadth first search, depth first search, and A* search. The FWGC problem and 8 puzzle have a # out of place heuristic implemented. There is also a manhattan distance heuristic option available of the 8 puzzle.

To compile, navigate to the folder containing the source files in a command prompt, then enter javac *.java

Run the program with this format:

java Solve [puzzle] [algorithm] [heuristic] [starting state] [-d for debug information]

puzzle: 
		f for FWGC
		e for 8 Puzzle
algorithm: 
		d for DFS
		b for BFS
		a for A*
heuristic:
		only for 8 puzzle with A*
		o for # out of place
		m for manhattan distance
starting state:
		must be in row major form, enter 0 for the empty space


Examples:

FWGC with BFS and prints debug info:
	java Solve f b -d

FWGC with DFS:
	java Solve f d

FWGC with BFS:
	java Solve f a

8 Puzzle with BFS:
	java Solve e b 240781365

8 Puzzle with DFS:
	java Solve e d 123485706

8 Puzzle with A* using # out of place heuristic:
	java Solve e a o 867254301

8 Puzzle with A* using Manhattan distance heuristic:
	java Solve e a m 867254301
	
	
8 Puzzle starting states:

Easy
	123485706

Medium
	240781365
	641523078

Hard
	086257314
	675841302

Very Hard (Does not work with BFS, takes very long with DFS)
	867254301
	647850321

