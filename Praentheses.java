import java.util.*;

/*
*/
public class Praentheses {
    
    static ArrayList<String> answer = new ArrayList<String>();
	public static ArrayList<String> generateParenthesis(int a) {
	
	    
	    char[] arr= new char[2*a];
	    recurse(arr,0,0,a);
	    return answer;
	}
	
	
	
	public static void recurse(char[] arr,int nLeft,int nRight,int n) {
	    
	    int pos = nLeft + nRight; 
	    if(pos == 2*n) {
	        String s = new String(arr);
			System.out.println("--------------- nLeft =  " + nLeft + ", nRight = " + nRight + ", answer = " + s);
	        answer.add(s);
			return;
	    } 
		else { 
			System.out.println("nLeft =  " + nLeft + ", nRight = " + nRight + ", arr = " + new String(arr));
		}
		
	    if(nLeft < n) {
	        arr[pos] = '(' ; 
			System.out.println("Left + 1  :::  nLeft =  " + nLeft + ", nRight = " + nRight + ", arr = " + new String(arr));
	        recurse(arr,nLeft+1,nRight,n);
	    }
	    if(nRight < nLeft) {
	        arr[pos] = ')'; 
			System.out.println("Right + 1  :::  nLeft =  " + nLeft + ", nRight = " + nRight + ", arr = " + new String(arr));
	        recurse(arr,nLeft,nRight+1,n);
	    }       
	    System.out.println("*********** End nLeft =  " + nLeft + ", nRight = " + nRight + ", arr = " + new String(arr));
	}
	
	public static void main(String [] arg){
		answer = generateParenthesis(3);
        System.out.println(answer);		
		
	}
	
	
}

