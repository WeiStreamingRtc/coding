import java.util.*;
/*
Note: Things to know about String

1. use a StringBuilder to build a string, not String, as it is final, and system need to new lots of temp object, not efficient.

2. Using a help array, index can be just the character, this helps many algorithms like decide if two strings are anagrams or not

4. 
*/

public class TestString{

	public  static void main(String arg[]){
		
		String s = "Hello";

        for (int i=0;i<10;i++) {

            s = s+"a";
            System.out.println(s);
			//This will be different every time
			System.out.println(s.hashCode());
        }
		
		
		System.out.println("*****************With a String builder ******************");
		StringBuilder builder = new StringBuilder("Hello");
		for (int i = 0; i < 10; i ++){
			builder.append("a");
			System.out.println(builder);
			//This will be same for every time
			System.out.println(builder.hashCode());
			
		}
		boolean[] charSet = new boolean[5];
		System.out.println("charSet [2]" + charSet[2]);
		
		
		String s1 = "eHllo";
		String s2 = "olleH";
		
		char [] a1 = s1.toCharArray();
		char [] a2 = s2.toCharArray();
		
		StringBuffer sb1 = new StringBuffer(s1);
		StringBuffer sb2 = new StringBuffer(s2);
		
		Arrays.sort(a1);
		Arrays.sort(a2);
		
		System.out.println(new String(a1));
		System.out.println(new String(a2));
		
		String ret = replaceChar("this is a test",' ',"HANK");
		System.out.println("replaced: " +  ret);
		
		
	}
	
	
	public static String replaceChar(String input, char target, String replaceChar){
		
		int len = input.length();
		StringBuilder ret = new StringBuilder();
		int index = 0, shift = 0;
		shift = replaceChar.length();
		char tempChar ;
		
		for(index = 0; index < len; index ++ ){
			tempChar = input.charAt(index);
			if(tempChar == target){
				ret.append(replaceChar);
			}
			else {
				ret.append(Character.toString(tempChar));
			}			
		}
		
		return ret.toString();		
	}
}