/*

Adjacency Linked Lists Storage :
	https://www.youtube.com/watch?v=zVrPdF7f4-I
	https://www.dropbox.com/s/cn2u1qjuo9nhslv/Graph.java


DFS:  
	https://www.youtube.com/watch?v=fI6X6IBkzcw	
	https://www.youtube.com/watch?v=uT1p5Eiw9CE
	
	Code: https://www.dropbox.com/s/3dzueevdm87yso2/Graph.java

	
BFS:
   https://www.youtube.com/watch?v=ZVJFOrsHxMs
   https://www.dropbox.com/s/23hi7sz6kt7wzf4/Graph.java?dl=0
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
 
 
class Neighbor {
    public int vertexNum;
    public Neighbor next;
    public Neighbor(int vnum, Neighbor nbr) {
            this.vertexNum = vnum;
            next = nbr;
    }
}
 
class Vertex {
    String name;
    Neighbor adjList;
    Vertex(String name, Neighbor neighbors) {
            this.name = name;
            this.adjList = neighbors;
    }
}
public class Graph {
 
    Vertex[] adjLists;
     
    public Graph(String file) throws FileNotFoundException {
         
        Scanner sc = new Scanner(new File(file));
         
        String graphType = sc.next();
        boolean undirected=true;
        if (graphType.equals("directed")) {
            undirected=false;
        }
         
        adjLists = new Vertex[sc.nextInt()];
 
        // read vertices
        for (int v=0; v < adjLists.length; v++) {
            adjLists[v] = new Vertex(sc.next(), null);
        }
 
        // read edges
        while (sc.hasNext()) {
             
            // read vertex names and translate to vertex numbers
            int v1 = indexForName(sc.next());
            int v2 = indexForName(sc.next());
			//System.out.println(" reading edge , v1 = " + v1 + ", v2 = " + v2 );
			
             
            // add v2 to front of v1's adjacency list and
            // add v1 to front of v2's adjacency list
            adjLists[v1].adjList = new Neighbor(v2, adjLists[v1].adjList);
            if (undirected) {
                adjLists[v2].adjList = new Neighbor(v1, adjLists[v2].adjList);
            }
        }
    }
     
    int indexForName(String name) {
        for (int v=0; v < adjLists.length; v++) {
            if (adjLists[v].name.equals(name)) {
                return v;
            }
        }
        return -1;
    }   
    
	// recursive dfs
    private void dfs(int v, boolean[] visited) {
        visited[v] = true;
        System.out.println("visiting " + adjLists[v].name);
        for (Neighbor nbr=adjLists[v].adjList; nbr != null; nbr=nbr.next) {
            if (!visited[nbr.vertexNum]) {
                System.out.println("\n" + adjLists[v].name + "--" + adjLists[nbr.vertexNum].name);
                dfs(nbr.vertexNum, visited);
            }
        }
    }
     
    public void dfs() {
        boolean[] visited = new boolean[adjLists.length];
        for (int v=0; v < visited.length; v++) {
            if (!visited[v]) {
                System.out.println("\nSTARTING AT " + adjLists[v].name);
                dfs(v, visited);
            }
        }
    }
	
	
	//no-recursive using stack, this will just printout the value
	public void dfsNorecursive(){
		if(adjLists.length == 0) return;
		boolean[] visited = new boolean[adjLists.length];
		Stack<Integer> stack = new Stack<Integer>();
		
		stack.push(0); // start from the first vertex
		
		System.out.println("\nSTARTING AT " + adjLists[0].name);
		
		while( ! stack.empty() ){
			int tmp = stack.pop();
			if( !visited[tmp] ){
				
				visited[tmp] = true;				
				System.out.println(adjLists[tmp].name + " ");
				
				for (Neighbor nbr=adjLists[tmp].adjList; nbr != null; nbr=nbr.next) {
					stack.push(nbr.vertexNum);
				}				
			}			
		}        
	}
	
	
    public void bfs() {
        boolean[] visited = new boolean[adjLists.length];
        //Queue<Integer> queue = new Queue<Integer>();
		Queue<Integer> queue =  new LinkedList<Integer>();
        for (int v=0; v < visited.length; v++) {
            if (!visited[v]) {
                System.out.println("\nSTARTING AT " + adjLists[v].name);
                queue.clear();
                bfs(v, visited, queue);
            }
        }
    }
     
	 /* 
	 */
    // BFS starting at some vertex v
    private void bfs(int start, boolean[] visited, Queue<Integer> queue) {
        visited[start] = true;
        System.out.println("visiting " + adjLists[start].name);
        queue.add(start);
         
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (Neighbor nbr=adjLists[v].adjList; nbr != null; nbr=nbr.next) {
                int vnum = nbr.vertexNum;
                if (!visited[vnum]) {
                    System.out.println("\n" + adjLists[v].name + "--" + adjLists[vnum].name);
                    visited[vnum] = true;
                    queue.add(vnum);
                }
            }
        }
    }
     
    /**
     * @param args
     */
    public static void main(String[] args) 
    throws IOException {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter graph input file name: ");
        String file = sc.nextLine();
        Graph graph = new Graph(file);
		
		System.out.println(" --------- BFS ----------");
        graph.bfs();
		
		System.out.println(" --------- DFS ----------");
		graph.dfs();
		
		System.out.println(" --------- No recursive   DFS ----------");
		graph.dfsNorecursive();
    }
 
}