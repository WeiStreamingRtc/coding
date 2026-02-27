import java.util.*;

public class TreePath {
	
	// Use this class to create binary trees.
	static class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;

		TreeNode(int value, TreeNode left, TreeNode right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}

		// The string representation of this node.
		// Will be used for testing.
		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}
	
    public static void main(String[] args) {

        // NOTE: The following input values will be used for testing your solution.
        // The mapping we're going to use for constructing a tree.
        // For example, {0: [1, 2]} means that 0's left child is 1, and its right
        // child is 2.
        HashMap<Integer, int[]> mapping1 = new HashMap<Integer, int[]>();
        int[] childrenA = {1, 2};
        int[] childrenB = {3, 4};
        int[] childrenC = {5, 6};
        mapping1.put(0, childrenA);
        mapping1.put(1, childrenB);
        mapping1.put(2, childrenC);

        TreeNode head1 = createTree(mapping1, 0);

        // This tree is:
        // head1 = 0
        //        / \
        //       1   2
        //      /\   /\
        //     3  4 5  6


        HashMap<Integer, int[]> mapping2 = new HashMap<Integer, int[]>();
        int[] childrenD = {1, 4};
        int[] childrenE = {3, 8};
        int[] childrenF = {9, 2};
        int[] childrenG = {6, 7};
        mapping2.put(5, childrenD);
        mapping2.put(1, childrenE);
        mapping2.put(4, childrenF);
        mapping2.put(3, childrenG);

        TreeNode head2 = createTree(mapping2, 5);
        // This tree is:
        //  head2 = 5
        //        /   \
        //       1     4
        //      /\    / \
        //     3  8  9  2
        //    /\
        //   6  7

		
         /*lca(head1, 1, 5);// should return 0
         lca(head1, 3, 1);// should return 1
         lca(head1, 1, 2);// should return 1
         lca(head1, 0, 5);// should return 0
		 
        lca(head2, 4, 7);// should return 5
         lca(head2, 3, 3);// should return 3
        lca(head2, 8, 7);// should return 1
       lca(head2, 3, 0);// should return null (0 does not exist in the tree)
	   */
	  printPathOfLeavesWithStack(head2);
	   
	   System.out.println(" path for tree 1");
	   printPathOfLeavesWithStack(head1);
	   
	   //getLevelNodes(head2);
	   printLevelNodes(head2);
    }

/*This is the function to print out all the nodes of each depth level. This is using a null node to mark the end of a level
*/	
	public static void printLevelNodes(TreeNode root){
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		queue.add(null);
		int level = 0;
		
		TreeNode temp;
		System.out.print("level " + level +": ");
		while(queue.size() > 0){
			
			
			temp = queue.poll();
			
			if(temp != null){
				if(temp.left != null){
					queue.add(temp.left);
				}
				if(temp.right != null){
					queue.add(temp.right);
				}
				System.out.print( temp.value+ " ");
			}
			else{
				System.out.print(" \n");
				level ++;
				//NOTE: remember to add this, other wise, endless loop
				if(queue.size() > 0){
					System.out.print("level " + level +": ");
					queue.add(null);
				}
			}
			
		}
	}
	/* Given a binary search tree, design an algorithm which creates a linked list of all the nodes at each depth (eg, if you have a tree with depth D, you’ll have D linked lists).
	
	This is using an helping array.
	*/
	public static void getLevelNodes(TreeNode root){
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
		int level = 0;
		list.add(root);
		
		result.add(level,list);
		
		while(true){
			
			list = new LinkedList<TreeNode>();
			
			for(int i = 0; i < result.get(level).size(); i ++){
				TreeNode tmp = (TreeNode) result.get(level).get(i);
				if (tmp != null) {
					if(tmp.left != null) list.add(tmp.left);
					if(tmp.right != null) list.add(tmp.right);
				}				
			}
			if(list.size() > 0){
				level ++;
				result.add(level,list);				
			}
			else{
				break;
			}
		}
		
		for(int i = 0; i < result.size();i++){
			LinkedList<TreeNode> temp = result.get(i);
			System.out.print("level  " + i + ":   ");
			for(TreeNode node : temp){
				System.out.print(" " + node.value);
			}
			System.out.print("\n");			
		}
	}
	/*
	This is a working version, has to use a help Stack. 
	This help stack is used to memorize all the path, once we found the node, just print out.
	*/
	public static void printPathOfLeavesWithStack(TreeNode root){
		
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode tmp = null;
		Stack<LinkedList<Integer>>  pathList = new Stack<LinkedList<Integer>>();
		LinkedList<Integer> tmpList;
		
		stack.push(root);
		tmpList = new LinkedList<Integer>();
		tmpList.add(new Integer(root.value));
		
		pathList.push(tmpList);
		
		while(!stack.empty()){
			tmp = stack.pop();
			tmpList = pathList.pop();
			
			if(tmp.right == null && tmp.left == null){
				System.out.print("ListVersion :");
				printArray(tmpList);
				continue;				
			}
			if(tmp.left != null){
				stack.push(tmp.left);
				LinkedList<Integer> current = new LinkedList<Integer>();
				
				for (Integer temp : tmpList){
					current.add(temp);					
				}
				
				current.add(new Integer(tmp.left.value));
				pathList.push(current);
				
			}
			if(tmp.right != null) {
				stack.push(tmp.right);
				
				LinkedList<Integer> current = new LinkedList<Integer>();
				for (Integer temp : tmpList){
					current.add(temp);					
				}
				current.add(new Integer(tmp.right.value));
				pathList.push(current);
			}
		}
	}
	

	public static void printArray(List<Integer> list){
		for(Integer tmp: list ){
			System.out.print(tmp +" ");
		}
		System.out.println();
	}
		

    // A function for creating a tree.
    // Input:
    // - mapping: a node-to-node mapping that shows how the tree should be constructed
    // - headValue: the value that will be used for the head ndoe
    // Output:
    // - The head node of the resulting tree
    public static TreeNode createTree(HashMap<Integer, int[]> mapping, int headValue) {
        TreeNode head = new TreeNode(headValue, null, null);
        HashMap<Integer, TreeNode> nodes = new HashMap<Integer, TreeNode>();
        nodes.put(headValue, head);
        for(Integer key : mapping.keySet()) {
            int[] value = mapping.get(key);
            TreeNode leftChild = new TreeNode(value[0], null, null);
            TreeNode rightChild = new TreeNode(value[1], null, null);
            nodes.put(value[0], leftChild);
            nodes.put(value[1], rightChild);
        }
        for(Integer key : mapping.keySet()) {
            int[] value = mapping.get(key);
			//System.out.println("key: "+ key + " value[0] = " + value[0] +" value[1] = " + value[1]);
            nodes.get(key).left = nodes.get(value[0]);
            nodes.get(key).right = nodes.get(value[1]);
        }
        return head;
    }
 }
 
 
  /*   Following are the recursive solution, depends on the recursive function pathToX() 
  
     public static TreeNode lca(TreeNode root, int j, int k) {
        Stack<TreeNode> pathToJ = pathToX(root, j);
        Stack<TreeNode> pathToK = pathToX(root, k);
        if (pathToJ == null || pathToK == null) {
            return null;
        }

        TreeNode lcaToReturn = null;

        while (!pathToJ.isEmpty() && !pathToK.isEmpty()) {
            TreeNode jPop = pathToJ.pop();
            TreeNode kPop = pathToK.pop();
            if (jPop == kPop) {
                lcaToReturn = jPop;
            } else {
                break;
            }
        }
        return lcaToReturn;
    }

    public static Stack<TreeNode> pathToX(TreeNode root, int x) {
        if (root == null) {
            return null;
        }

        if (root.value == x) {
            Stack<TreeNode> path = new Stack<TreeNode>();
            path.push(root);
            return path;
        }

        Stack<TreeNode> leftPath = pathToX(root.left, x);
        if (leftPath != null) {
            leftPath.push(root);
            return leftPath;
        }

        Stack<TreeNode> rightPath = pathToX(root.right, x);
        if (rightPath != null) {
            rightPath.push(root);
            return rightPath;
        }

        return null;
    }
 */
