import java.util.*;


/*

Java LinkedList from util package implemented Queue interface, and can be used as a Queue, 
example: https://examples.javacodegeeks.com/core-java/util/queue/java-queue-example/

For new code, the Java documentation recommends using the Deque interface (pronounced "deck") for LIFO stacks . 
It provides a complete and more consistent set of operations . The ArrayDeque class is a fast, resizable-array implementation of this interface .

Note: Deque can be used as either Stack or Queue.

 Deque<String> deque = new ArrayDeque<>();

*/

class Node<T>{
	Node next = null;
	T data = null;
	
	public Node(T t){		data = t;	}
	
	public void addToTail(Node node){
		Node n = this;
		while (n.next != null){
			
			n = n.next;
			//System.out.println("Add to tail n = " + n);
		}
		n.next = node;
		//node.next = null;
	}
	
	public void addToTail(int d){
		Node end = new Node(new Integer (d));
		Node n = this;
		
		while(n.next != null){
			n = n.next;
		}
		n.next = end;		
	}
	
	public void printList(){
		Node node = this;
		int i = 1;
		while(node != null){
			System.out.println("Node ( " + i + ") = " + node);
			i++;
			node = node.next;	
		}
	}
	public String toString(){ return  "Node: " + data.toString();}
	
}

public class HankLinkedList<T>{
	
	T head = null;
	T end = null;
	
	public void addToTail(T t) {

	}

	
	public static void main (String arg[]){
		
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		
		n1.addToTail(n2);
	
		
		n1.addToTail(n3);
	
		
		
		
		n1.addToTail(n4);
		
	
		
		Node n5 = new Node("String 5");
		Node n6 = new Node("String 6");
		Node n7 = new Node("String 7");
		Node n8 = new Node("String 8");
		
		n3.addToTail(n5);
		n4.addToTail(n6);
		n2.addToTail(n7);
		n6.addToTail(n8);
		
		n1.printList();
		
		
		
	}
	
}






