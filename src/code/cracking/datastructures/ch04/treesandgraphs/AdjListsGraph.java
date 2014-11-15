package code.cracking.datastructures.ch04.treesandgraphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import code.cracking.datastructures.RandomTestKit;

/*
 * 
 * Adjacency List Representation Implementation
 * Reference from :
 * 		http://www.cs.toronto.edu/~krueger/cscB63h/w07/assignments/pgm4/AdjListsGraph.java
 * 
 */


//adjacency list representation
class Node {
	protected int id;
	protected double weight;
		
	public Node(int d, double w) 
	{
		id = d;
		weight = w;	
	}
};


public class AdjListsGraph {

	private LinkedList<Node>[] lists;

	protected int vertices;
	protected int edges;
	protected boolean directed;
	
	protected boolean[] visited = null;

	public AdjListsGraph(int nodes, boolean isDirected) {
		vertices = nodes;
		directed = isDirected;
		edges	= 0;
		initLists();
	}

	private void initLists() {	
		lists = new LinkedList[vertices];
		for (int i=0;i<vertices;i++) {
			lists[i] = new LinkedList<Node>();
		}
	}

	private boolean checkBound(int n) {
		return ( n>=0 && n<lists.length );
	}
	
	private boolean containsEdge(int from, int to) {
		
		if ( !(checkBound(from) && checkBound(to)) ) {
			System.out.println("No such vertices!");
			return false;	
		}
		
		LinkedList<Node> fromList = lists[from];
		for(Node edge : fromList) {
			if (to == edge.id)
				return true;
		}
		
		return false;
	}


	public boolean insertEdge(int from, int to, double weight) {
		if (lists.length == 0) {
			vertices=vertices+2;
			initLists();
		}

		if ( !(checkBound(from) && checkBound(to)) ) {
			System.out.println("No such vertices!");
			return false;	
		}
		
		if ( containsEdge(from, to) ) {
			System.out.println("The edge already exist!");
			return false;	
		}		

		LinkedList<Node> fromList = lists[from];

		fromList.add(new Node(to, weight));
		edges++;
	
		if(!directed)
			insertEdge(to, from, weight);
		
		return true;
	}

	public void printGraph() {
		System.out.println("/=========================================/");
		String directStr = directed ? "Directed" : "Undirected";
		System.out.println(vertices + " vertices, " + edges + " edges " + directStr + " graph");	
		for(int i=0;i<lists.length; i++) {
			System.out.print(i);		
			for(Node edge : lists[i]) {
				System.out.print(" -> " + edge.id);
			}
			System.out.println(" #");
		}
	}
	
	public void resetVisitedFlags() {
		if (visited != null) {
			visited = null;
		}

		visited = new boolean[vertices];
		for(boolean flag : visited) {
			flag = false;
		}
	}

	// flag: 0-recur DFS, 1-stack DFS, 2-BFS
	public void traversalDriver(int flag) {
		int connectedComp = 0;
		for(int i=0;i<vertices;i++) {
			if (!visited[i]) {
				System.out.println("\nStarting connected component " + connectedComp + " ...");
				switch (flag) {
					case 0:	recurDFS(i); break;
					case 1:	noRurDFS(i); break;
					case 2: BFS(i);	break;
					default:break;
				}
				connectedComp++;
			}
		}
	}
	

	public void recurDFS (int node) {
		if ( !(checkBound(node)) ) {
			System.out.println("No such vertices!");
			return;
		}

		System.out.println("Visiting node " + node + " ... ");
		visited[node]= true;
			
		if(lists[node].size() > 0) {
			for(Node n : lists[node]) {
				if (!visited[n.id])
					recurDFS (n.id);
			}
		}
	}
	
	private int getUnvisitedChild(int node) {
		
		if ( !(checkBound(node)) ) {
			System.out.println("No such vertices!");
			return -1;
		}
		
		if(lists[node].size() > 0){
			for(Node e : lists[node]) {
				if (!visited[e.id]) {
					return e.id;
				}
			}
		}
		
		return -1;
		
	}
	
	public void noRurDFS(int node) {
		
		if ( !(checkBound(node)) ) {
			System.out.println("No such vertices!");
			return;
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(node);
		System.out.println("Visiting node " + node + " ... ");
		visited[node] = true;
		
		while(!stack.isEmpty()) {
			int n = stack.peek();
			int unvisitedChild = this.getUnvisitedChild(n);
			if (unvisitedChild != -1) {
				stack.push(unvisitedChild);
				System.out.println("Visiting node " + unvisitedChild + " ... ");
				visited[unvisitedChild] = true;
			} 
			else {
				stack.pop();
			}
		}
		
	}
	
	/*
	 * 
	 * http://www.codeproject.com/Articles/32212/Introduction-to-Graph-with-Breadth-First-Search-BF
	 */
	public void BFS(int start) {
		
		if ( !(checkBound(start)) ) {
			System.out.println("No such vertices!");
			return;
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		System.out.println("Visiting node " + start + " ... ");
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int node = queue.remove();
			int child = -1;
			while((child=getUnvisitedChild(node)) != -1) {
				queue.add(child);
				System.out.println("Visiting node " + child + " ... ");
				visited[child] = true;
			}
		}
		
	}
		
	public boolean isRoutable(int from, int to) {
	
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(from);
		visited[from]=true;
		
		while(!stack.isEmpty()) {
			int node = stack.peek();
			int child = getUnvisitedChild(node);
			if (child != -1) {
				if(child == to)
					return true;

				stack.push(child);
				visited[child]=true;
			} else {
			 	stack.pop();
			}
		}

		return false;
	}


	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int vertices=5; 
		boolean isDirected = false;
		AdjListsGraph adjg = new AdjListsGraph(vertices,isDirected);
		
		// ~~~~ Prepare for test ~~
		Class[] paramTypes = {int.class, int.class, double.class};
	//	Object[] parameters = {};
		String method = "insertEdge"; 

		int edgeBound = adjg.directed ? vertices*vertices : vertices*vertices/2;
		RandomTestKit.testClass(20/*edgeBound*/, vertices, adjg, method, paramTypes, paramTypes.length);
		
		System.out.println("\nAfter initialization, graph is :");
		adjg.printGraph();
		
		adjg.resetVisitedFlags();
		System.out.println("\n----------------DFS-------------------");
		adjg.traversalDriver(0);		
		
		adjg.resetVisitedFlags();
		System.out.println("\n----------------DFS using stack----------------");
		adjg.traversalDriver(1);
		
		adjg.resetVisitedFlags();
		System.out.println("\n---------------- BFS ----------------");
		adjg.traversalDriver(2);
		
		adjg.resetVisitedFlags();
		int from =0, to =4;
		String isRoutable = adjg.isRoutable(0, 4) ? "yes" : "no"; 
		System.out.println("\nIs there a way from " + from + " " + to +" ? " + isRoutable);
		
	}

}

