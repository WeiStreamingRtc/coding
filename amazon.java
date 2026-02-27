import java.util.*;


	class AmazonNode {
		int val;
		AmazonNode left, right;
		
		AmazonNode(int item){
			val = item;
		}
	}
	
public class amazon {
	
	//This is the function to check if the input string has two same characters
	public static boolean isDistinct(String input){
		
		int[] val = new int[26];
		
		boolean ret = true;
		
		char temp;
		for(int n = 0; n < input.length(); n ++){
			temp = input.charAt(n);
			
			if (val[temp - 'a'] > 0){
				ret = false;
				break;
			}
			else {
				val[temp - 'a'] = 1;
			}
			
		}
		
		return ret;
		
	}
	//This is to found the substring which has the length of num, with distinct characters.
	//This is for question2, amazon2.png
	public static List foundBum(String input, int num){
		ArrayList<String> ret = new ArrayList<String>();
		
		String temp = "";

		for(int n = 0; n <= input.length() - num; n ++){
			temp = input.substring(n, n+num);
			if (isDistinct(temp)) {
				ret.add(temp);
			}
		}
		return ret;
	}
	
	/*Following code spend more time, this is a mistake?
	Should try to use help function like the previous one. even you do not know how to implement the help function yet, 
	which can be done later when coding. should try to think like computer. 
	This help function stratigy is kind of divide and conque: focus on the main problem first, then focus on small problem
	one by one.
	Following approach is trying to solve everything all at the same time--which human can do.
	This approach is sort of a dynamic programming, if I know length of n-1 is found, then if add 1, easy to to know if it is found.
	*/
	
	public static List foundSum2(String in, int num) {
		ArrayList<String> ret = new ArrayList<String>();
		int[] check = new int[4];
		
		check[0] = in.charAt(0);
	
		char test;
		int index = 1;
		boolean found = false;
		for(int n = 1; n < in.length() -1; n++) {
			test = in.charAt(n);
			found = false;
			for(int i = 0; i< 4; i++){
				if(check[i] == test){
					found = true;
				}
			}				
		
			check[index] = test;
			if(found || index == 3){
				//rewind and kick out the first one
				for(int i = 0; i < 3; i ++){
					check[i] = check[i+1];				
				}
			}
			if(!found){
				index ++;
				if(index == 4){
					ret.add(in.substring(n-3,n+1));
					index = 3;
				}
			}

		}
		return ret;
	}
	/*This is for question 1 amazon1.png, give an array, need to build a BST tree, and then found the distance.
	
	Build an BST from array and found the distance between two AmazonNodes. This can be done by find the lowest common ancestor, once we found the lowest common ancestor, it will be very easy to found the distance between two nodes, just cound the steps from common ancestor to each nodes.
	
	This can be done by using recursive very easily, both building the tree and found the distance
	*/
	public static AmazonNode fromArrayToBST(int[] array, int start, int end){
		
		if (start > end){
			return null;
		}
		int n = (start + end ) / 2;
		
		AmazonNode node = new AmazonNode(array[n]);
		node.left = fromArrayToBST(array, start , n -1);
		node.right = fromArrayToBST(array, n + 1, end);
		return node;
	}
	
	public static AmazonNode fromArrayToBST(int[] array) {
		Arrays.sort(array);	
		return fromArrayToBST(array, 0, array.length - 1);		
	}
	
	
	public static int[] findPath(AmazonNode root, int node1){
		return null;
	}
	
	public static void printLevelNodes(AmazonNode root){
		LinkedList<AmazonNode> queue = new LinkedList<AmazonNode>();
		queue.add(root);
		queue.add(null);
		int level = 0;
		
		AmazonNode temp;
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
				System.out.print( temp.val+ " ");
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
	
	public static void printPathOfLeavesWithStack(AmazonNode root){
		
		Stack<AmazonNode> stack = new Stack<AmazonNode>();
		AmazonNode tmp = null;
		Stack<LinkedList<Integer>>  pathList = new Stack<LinkedList<Integer>>();
		LinkedList<Integer> tmpList;
		
		stack.push(root);
		tmpList = new LinkedList<Integer>();
		tmpList.add(new Integer(root.val));
		
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
				
				current.add(new Integer(tmp.left.val));
				pathList.push(current);
				
			}
			if(tmp.right != null) {
				stack.push(tmp.right);
				
				LinkedList<Integer> current = new LinkedList<Integer>();
				for (Integer temp : tmpList){
					current.add(temp);					
				}
				current.add(new Integer(tmp.right.val));
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

	public static void main(String [] arg){
		//awaglknagawunagwkwagl
		//System.out.println(arg[0] + "  is : " + foundSum2(arg[0], 4));
		
		int[] array = { 2, 4, 6, 7,9,5,12,34,21};
		
		AmazonNode tree = fromArrayToBST(array);
		
		printPathOfLeavesWithStack(tree);
		printLevelNodes(tree);
		
		
		
		
		
	}
}