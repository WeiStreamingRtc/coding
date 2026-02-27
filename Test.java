import java.util.*;

public class Test{
	
	
	/*Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
	Input: ")()())" 
	Output: 4
	Explanation: The longest valid parentheses substring is "()()"
	
	Use a help array with 1 and -1 to  represent the "(" and ")", then zero the matching parentheses, and count the longest number of zero's in the help array.
	*/
	
	public static int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        for(int i = 0; i < s.length();i++){            
            if(s.charAt(i) == '(') 
                dp[i] = 1;
            else
                dp[i] = -1;         
        }
 
        int left = 0, right = 0;
        
        for(int i = 0; i < dp.length; i ++){
            
            if(i + 1 < dp.length && dp[i] == 1 && dp[i]+dp[i+1] == 0 ){
                 dp[i] = 0;
                 dp[i+1] = 0;
                 if(i ==0) continue;
                 left =  i -1;
                 right =  i + 2;
                 System.out.println("left = " + left + ", right = "+ right);
                while(left > 0 && right < dp.length){
                    //mark 0
					
                    if(dp[left] == 1 && dp[left] + dp[right] == 0){
						dp[left] = 0;
						dp[right] = 0;
						System.out.println("test");
                    }
					left --;
					right ++;

                }
                
            }
        }
        
        int max = 0;
        int tempmax = 0;
        
        for(int i = 0; i < dp.length; i ++){
			System.out.println("arr [" + i + "]" + dp[i]);
            if(dp[i] ==0){
                tempmax++;
            }
            else{
                max = max >tempmax?max:tempmax;
                tempmax = 0;
            }
            
        }
        return max >tempmax?max:tempmax;       
        
    }
	
	
	/* Inplace reverse a sentence
		You given a sentence of english words and spaces between them.
		Nothing crazy:
		1) no double spaces
		2) no empty words
		3) no spaces at the ends of a sentence
		Example:
		input "I wish you a merry Christmas"
		output "Christmas merry a you wish I"
	*/
	//First reverse the whole sentence, then reverse each words
	public static String revSentence(String str){
		int len = str.length();
		char [] arr = str.toCharArray();
		int mid = len / 2;
		boolean reset = true;
		int start = 0;
		int wLen = 0;
		
		for(int i = 0; i < len; i ++ ){
			
			if(i < mid){
				char temp = arr[i];
				arr[i] = arr[len - i - 1];
				arr[len - i - 1] = temp;
			}
			
			if(reset ) {
				start = i;
				reset = false;
				wLen = 0;
			}
			
			if(arr[i] == ' ' || i == len -1){
				if(i == len -1){
					wLen ++;//System.out.println("start " + start + ", wlen = " + wLen);
				}
				reverseWord(arr,start,wLen);				
				reset = true;
			}
			else{
				wLen ++;
			}
			
			
		}	
		System.out.println("output: " + new String(arr));
		return new String(arr);
	}
	public static void reverseWord(char[] arr,int start,int len){
		
		for(int i = 0; i < len /2; i ++){
			
			char temp = arr[start + i];
			arr[start + i ] = arr[start - i + len -1];
			arr[start - i + len -1] = temp;
		}
		
	}
	
	
	
    /* Test if two array are rotation of another
	        int[] array1 = {1, 2, 3, 4, 5, 6, 7};
        int[] array2a = {4, 5, 6, 7, 8, 1, 2, 3};
        // isRotation(array1, array2a) should return false.
        int[] array2b = {4, 5, 6, 7, 1, 2, 3};
        // isRotation(array1, array2b) should return true.
        int[] array2c = {4, 5, 6, 9, 1, 2, 3};
        // isRotation(array1, array2c) should return false.
        int[] array2d = {4, 6, 5, 7, 1, 2, 3};
        // isRotation(array1, array2d) should return false.
        int[] array2e = {4, 5, 6, 7, 0, 2, 3};
        // isRotation(array1, array2e) should return false.
        int[] array2f = {1, 2, 3, 4, 5, 6, 7};
        // isRotation(array1, array2f) should return true.
	*/
    public static Boolean isRotation(int[] array1, int[] array2) {
        if (array1.length != array2.length) return false;
        int key = array1[0];
        int keyLoc = -1;
        for (int i = 0; i < array2.length; i++) {
            if (array2[i] == key) {
                keyLoc = i;
                break;
            }
        }
		System.out.println("keyLoc = " + keyLoc);
        if (keyLoc == -1) return false;
        for (int i = 0; i < array1.length; i++) {
            int j = (keyLoc + i) % array1.length;
			System.out.println(" i = " + i + ", j = " +j);
            if (array1[i] != array2[j]) return false;
        }
        return true;
    }
	
	
	
	public static void main(String [] arg){
		
		
		/*StringBuilder str = new StringBuilder();
		
		for (int i = 0; i < arg.length; i ++){
			str.append(arg[i]);
			str.append(" ");
		}	*/
		
		//int m = longestValidParentheses(arg[0]);
		//System.out.println("longestValidParentheses: " + m);

		int[] array1 = {1, 2, 3, 4, 5, 6, 7};
		int[] array2b = {4, 5, 6, 7, 1, 2, 3};
		boolean is = isRotation(array1, array2b);
		System.out.println("is rotation = " + is);
		
		
	}
}