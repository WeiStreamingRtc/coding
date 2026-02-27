import java.util.*;

/*
    Stack is used for: Deapth-First Search with a Stack

	For graph / tree
	In depth-first search we go down a path until we get to a dead end; then we backtrack or back up (by popping a stack) to get an alternative path.

		Create a stack
		Create a new choice point
		Push the choice point onto the stack
		while (not found and stack is not empty)
			Pop the stack
			Find all possible choices after the last one tried
			Push these choices onto the stack 
		Return  
		
	Java code exmaple: https://gist.github.com/gennad/791932
	a good link: https://www.youtube.com/watch?v=iaBEKo5sM7w
*/


class HankStack<T>{
	
	T node = null;
	int num = 0;
	T top = null;
		LinkedList<T> list = new LinkedList<T>();
	
	T pop(){
				
		if (num == 0) return null;		
				
		T tmp = top;
		
		list.remove( num - 1);	// remove the top one
		
		if (num > 0 ) --num;		
		
		if (num >= 1 ) top = list.get(num - 1); // remember to check if the list is empty.
		
		return tmp;
	}
	
	T push(T t){
		num ++;
		
		list.addLast(t);
		top = t;

		return top;
	}
	
	
	int getSize(){
		return num;
	}
}

public class TestStack{
	
	public static void main (String[] arg){
		
		HankStack<String> stack = new HankStack<String>();
		
		
		for(int i = 0; i < arg.length; i ++){
			
			//System.out.println("arg " + i + ": " + arg[i]);
			//stack.push(new Integer(arg[i]));	
			stack.push(arg[i]);	
		}
		
		String tmp = null;
		tmp = stack.pop();
		int i = 1;
		
		//System.out.println("stack size : " + stack.getSize());
		
		while(tmp != null){
			
			System.out.println("stack " + i + ": "+ tmp);
			tmp = stack.pop();
			i ++;
		}
		
		System.out.println(" ------------------");
		
		tmp = stack.pop();
		
		System.out.println("tmp " + tmp);
		
		
	}
	
	
}