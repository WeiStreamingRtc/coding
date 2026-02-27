import java.util.*;

/*
  
preorder:  	<root> <left> < right>
inorder: 	<left> <root> <right>
post order: <left> <right > < root>

BST:  A "binary search tree" (BST) or "ordered binary tree" is a type of binary tree
 where the nodes are arranged in order: for each node, all elements in its left subtree are less-or-equal to the node (<=), 
 and all the elements in its right subtree are greater than the node (>).
  
  
  
 Heap, Max Heap, Min Heap
 
 In computer science, a heap is a specialized tree-based data structure that satisfies the heap property: 
 If A is a parent node of B then the key (the value) of node A is ordered with respect to the key of node B with the same 
 ordering applying across the heap. A heap can be classified further as either a "max heap" or a "min heap". 
 In a max heap, the keys of parent nodes are always greater than or equal to those of the children and the highest key is in the root node. 
 In a min heap, the keys of parent nodes are less than or equal to those of the children and the lowest key is in the root node.
  
  
*/

class Tree<T>{
	

	private static class Node<T>{
		public Node left, right;
		public T val;
		boolean visited = false;
		
		public Node(T t){ this.val = t;}
		
	}
	
	public  Node<T> root;
	
	
	private static  Node<Integer> buildFromArray(int[] arr, int start, int end){
		
		if (end < start) return null; //REM:  to break the recursive loop
		
		int midIndex =  (end + start ) / 2;
		int mid = arr[ midIndex];
		
		Node<Integer> r = new Node<Integer>(new Integer(mid));
		
		r.left = buildFromArray(arr,start,midIndex - 1); //REN: midIndex - 1
		r.right = buildFromArray(arr,midIndex + 1,end); //REM midIndex + 1
		
		
		return r;		
		
	}
	
	
	public static Tree<Integer> buildTreeFromArray(int[] array){
		Tree<Integer>  tree = new Tree<Integer>();
		
		int size = array.length;
		
		//sort the array
		Arrays.sort(array);
		/*System.out.println("------------------------- after sorting ------------------------");
		for (int i = 0; i < array.length; i ++){
			System.out.print("  " + array[i]);
		}
		//System.out.println("\n------------- End -------------");
		*/
	
		tree.root = buildFromArray(array,0,size - 1);
		
		return tree;
	}
	

	//DFS, recursive 
	/*public  void printTree( Node<T> root){
		if(root == null) {
			//System.out.println("Empty");
			return;
		}
		
		if(root != null) {
			System.out.print(root.val  + "  ");
			
		}
		printTree(root.left);
		printTree(root.right);
		
	}*/

	
	/* Breadth first search, using a queue
	*/
	
	public  LinkedList<Node<Integer>> queue =  new LinkedList<Node<Integer>>(); 
	
	//https://www.youtube.com/watch?annotation_id=annotation_2645130791&feature=iv&index=33&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P&src_vid=9RHO6jU--GU&v=86g8jAQug04
	public  void bfs(Node<Integer>  root,boolean print ){
		
		if(root == null) return;
		
		queue.clear();
		
		queue.add(root);
		queue.add(null); 
		
		while( !queue.isEmpty() ){

			Node<Integer> temp = queue.remove();	
			if(temp != null) System.out.print(temp.val + " ");
			else System.out.println();
		
			//Note: here using temp, not root
			if(temp != null && temp.left != null ) {
				queue.add(temp.left);
			} 
			if(temp != null && temp.right != null) {
				queue.add(temp.right);
			} 
			if( temp == null && print &&!queue.isEmpty()) queue.add(null); 				
				
		}
			
	}
	/*this will print out tree in BFS, with each level in different line*/	
	public void printTree(Node<Integer> root) {
		if(root == null) return;
		
		queue.clear();
		
		queue.add(root);
		queue.add(null); 

		while (!queue.isEmpty()) {
			Node<Integer>  curr = queue.remove();
			if (curr != null) {
				System.out.print(curr.val+" ");
				if (curr.left != null)      queue.add(curr.left);
				if (curr.right != null)     queue.add(curr.right);
			}
			// when curr==null, it is the next level, all the nodes are already in the queue
			else {
				System.out.println();     // print new line
				if (queue.isEmpty())      break;
				queue.add(null);
			}
		}
	}
	// recursive 
	public void dfs(Node<Integer>  root){
		
		if(root == null) return;
		
		System.out.print(root.val + " ");
		//stack.push(root.val);	
		
		dfs(root.left);
		dfs(root.right);
	}
	/* Deepth First Search, using stack
	*/

	public static java.util.Stack<Node<Integer>> stack = new java.util.Stack<Node<Integer>>();

	public static void dfsNorecursive(Node<Integer>  root){
		
		if(root == null) return;
		
		stack.push(root);
		
		while(!stack.empty()){
			Node<Integer> tmp = stack.pop();
			
			if(!tmp.visited){  //This seems not needed
				System.out.print(tmp.val  + "  ");
				tmp.visited = true;
				
				//NOTE: this will print left first, right following it, because of Stack's LIFO property
				if(tmp.right != null) stack.push(tmp.right);
				if(tmp.left != null) stack.push(tmp.left);
				
				if(tmp.right == null && tmp.left == null)
					System.out.print("(This is a node: " + tmp.val  + "  )");
			
			}			
		}	
		
	}
	
	
}




public class TestTree{
	
	public static void main (String[] arg){
		
		int[] arr = new int[arg.length];
		
		for(int i = 0; i < arg.length; i ++ ){
			
			arr[i] = Integer.valueOf(arg[i]);
		}
		
		
		Tree<Integer> t = Tree.buildTreeFromArray(arr);
		
		//t.printTree(t.root);
		t.printTree(t.root);
		
		
		
		
		System.out.println("\n----------------BFS ---------------");
				
		t.bfs(t.root,true);
		
		
		
		System.out.println("\n----------------DFS ---------------");
				
		t.dfs(t.root);
		
		while(!t.stack.empty()){
			
			System.out.print("  " + t.stack.pop());
			
		}
		
		System.out.println("\n---------------NOT recursive  DFS ---------------");
				
		t.dfsNorecursive(t.root);
		
	}
	
	
}