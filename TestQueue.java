import java.util.*;

/*
    Queue is used to:  Breadth-First Search with a Queue

	In breadth-first search we explore all the nearest possibilities by finding all possible successors and enqueue them to a queue.

		Create a queue
		Create a new choice point
		Enqueue the choice point onto the queue
		while (not found and queue is not empty)
			Dequeue the queue
			Find all possible choices after the last one tried
			Enqueue these choices onto the queue 
		Return   
	
	Code example: https://gist.github.com/gennad/791932
	a good link: https://www.youtube.com/watch?v=QRq6p9s8NVg
	
	Java LinkedList from util package implemented Queue interface, and can be used as a Queue, 
		example: https://examples.javacodegeeks.com/core-java/util/queue/java-queue-example/

*/


 class HankQueue<T>{
	
	
	int num = 0;
	LinkedList<T> list = new LinkedList<T>();
	T head = null;
	T tail = null;
	public void enQueue(T t){
		
		if(t == null) return;
		
		list.addLast(t);
		
		tail = t;
		num ++;
		
		if(num == 1) head = t; // remember to set head at the very first element.
		
	}
	public T deQueue(){
		T tmp = head;
		
		if(num >= 1) {
			num --;
			list.remove(0); // remember to remove the first
		}
		
		if(num > 0) head = list.getFirst();
		else head = null; //remember to set the head to null if the queue is empty
		
		return tmp;
		
	}
	
	int getSize(){
		return num;
	}
}

public class TestQueue{
	
	public static void main (String[] arg){
		
		HankQueue<String> qu = new HankQueue<String>();
		
		
		for(int i = 0; i < arg.length; i ++){
			
			qu.enQueue(arg[i]);	
		}
		
		String tmp = qu.deQueue();
		int i = 1;
		
		while(tmp != null){
			
			System.out.println("queue " + i + ": "+ tmp);
			tmp = qu.deQueue();
			i ++;
		}
		
	}

	
}