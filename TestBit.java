import java.util.*;

public class TestBit{

/*This funciton will print out the binary representation of the integer: like 00000000000000000000000000001100, for all 32 bits. it will work even the integer is negative.
*/
public static void printBit(int m){
	int t = 1;
	StringBuilder buf = new StringBuilder();	
	
	for(int i = 31; i >= 0; i --){
		if( (m & (t << i)) > 0)
			buf.append("1");
		else
			buf.append("0");
			
	}

	System.out.println(buf.toString());
}
//Count the number of 1s in the binary representation of m
public static int countOnes(int m){
	int count = 0;
	for(; m != 0; m = m>>1){
		count += m & 1;		
	}
	return count;	
}

/*You are given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to set all bits between i and j in N equal to M (e.g., M becomes a substring of N located at i and starting at j).
EXAMPLE:
Input: N = 10000000000, M = 10101, i = 2, j = 6
Output: N = 10001010100
*/

public static int updateBit(int n, int m, int i, int j) {
	int max = ~0;
	// 1’s through position j, then 0’s, like 11110000
	/*	(1 << j)     = 100...000   (1 followed by j zeros)
		(1 << j) - 1 = 011...111   (0 followed by j ones)
	*/
	int left = max - ((1<<j) - 1);
	// 1’s after position i, like 000001111
	int right = (1<<i) -1;
	//0 between bit i and j, ike:  1110000111
	int mask1 = left | right;
	//1 between bit i and j, like 0001111000
	int mask2 = ~mask1; //or max - mask1
	
	int ret = (n & mask1) | (m & mask2);
	

	System.out.print("max  is  "+ max + ": ");
	printBit(max);
	System.out.print("left  is " + left + ": ");
	printBit(left);
	System.out.print("right is  " + right + ": ");
	printBit(right);
	System.out.print("mask1 : ");
	printBit(mask1);
	System.out.print("mask2 : ");
	printBit(mask2);
	
	System.out.print("n     is  " + n + ": ");
	printBit(n);
	
	System.out.print("m     is  " + m +": ");
	printBit(m);
	
	System.out.print("m & mask2   is " + (m & mask2) + ": ");
	printBit(m & mask2);
	
	System.out.print("ret   : ");
	printBit(ret);
	
	
	return ret;	
}
/*
12.4: Converting to Binary (Theoretical)
First, let's try to convert 12.4 to binary manually:
Integer part (12):
12 ÷ 2 = 6 remainder 0
6 ÷ 2 = 3 remainder 0
3 ÷ 2 = 1 remainder 1
1 ÷ 2 = 0 remainder 1
So 12 decimal = 1100 binary

Fractional part (0.4):
0.4 × 2 = 0.8 → integer part 0
0.8 × 2 = 1.6 → integer part 1
0.6 × 2 = 1.2 → integer part 1
0.2 × 2 = 0.4 → integer part 0
0.4 × 2 = 0.8 → integer part 0
0.8 × 2 = 1.6 → integer part 1
... and it repeats! (0110 pattern repeats)

So 0.4 decimal = 0.01100110011001100110... (repeating 0110)

IEEE 754 Floating-Point Standard

Sign (1 bit)  |  Exponent (8 bits)  |  Mantissa (23 bits)
      0       |      10000010       |  10001100110011001100110

Because 0.4 has a repeating binary representation, it gets rounded to fit in 23 bits. This means:
The computer stores an approximation of 12.4, not the exact value
When you do calculations, small rounding errors can accumulate

If you convert back to decimal, you might get something like 12.3999996185 instead of exactly 12.4.

A decimal number can be stored exactly in binary floating-point if its fractional part, when converted to binary, terminates (doesn't repeat).
When does a binary fraction terminate?
A fraction terminates in binary if the denominator (when reduced) is a power of 2.

Examples of exact representations:

0.5 = 1/2 = binary 0.1 ✓
0.25 = 1/4 = binary 0.01 ✓
0.75 = 3/4 = binary 0.11 ✓
0.125 = 1/8 = binary 0.001 ✓
0.375 = 3/8 = binary 0.011 ✓
0.0625 = 1/16 = binary 0.0001 ✓
0.2? NO — 1/5 = denominator 5 (not a power of 2) → repeating!
So 0.5, 0.25, 0.125, 0.375, 0.0625, 0.9375, etc. can be stored exactly.

Numbers That Are Approximations
Any decimal with a denominator that isn't a power of 2 when reduced will be a repeating binary fraction:

0.1 (1/10) → repeating
0.2 (1/5) → repeating
0.3 (3/10) → repeating
0.4 (2/5) → repeating
0.6 (3/5) → repeating
0.7 (7/10) → repeating
0.8 (4/5) → repeating
0.9 (9/10) → repeating
*/
public static void printBinary(String n){

	int intPart = Integer.parseInt(n.substring(0, n.indexOf('.')));
	
	double decPart = Double.parseDouble(n.substring(n.indexOf('.'), n.length()));
	String int_string = "";
	//the int part to bit stream
	//Folloiwng way to print out binary cannot handle negative number
	while (intPart > 0) {
		 int r = intPart % 2;
		 intPart >>= 1;
		 int_string = r + int_string;
	 }

	 StringBuffer dec_string = new StringBuffer();
	 while (decPart > 0) {
		//if (dec_string.length() > 32) 
			//System.out.println("Error");
		if (decPart == 1) {
			dec_string.append((int)decPart);
			break;
		}
		double r = decPart * 2;
		 if (r >= 1) {
			 dec_string.append(1);
			 decPart = r - 1;
		 } else {
			 dec_string.append(0);
			 decPart = r;
		}
	 }
	 System.out.println( int_string + "." + dec_string.toString());
 
	
}
//conver decimal to binary
public static void  convertBinary(int num){
	
	
	int binary[] = new int[40];
	int index = 0;
	
	System.out.print("num: "+ num + ": ");
	
	while(num > 0){
		binary[index ++] = num %2;
		num=num/2;
		
	}
	
	for(int i = index - 1;i >=0; i -- ){
		System.out.print(binary[i]);	
	}

	System.out.println();	
}



/*
conver decimal to negative 2 based binary
Note:  StringBuilder has a reverse function that just reverse the string at end will solve  the problem
This is base −2, which means each digit represents a power of −2:
  ...  16  -8   4  -2   1
     (-2)^4 (-2)^3 (-2)^2 (-2)^1 (-2)^0
	 1 % -2 = 1
     -1 % -2 = -1   ← problem
But in base −2, digits must be: 0 or 1

If remainder is negative:
	Add 2 to make it positive
	Compensate by increasing quotient by 1
When converting to base −2, we want: x = quotient × (−2) + remainder
*/
public static String negaBinary(int x) {
		StringBuilder sb = new StringBuilder();
		while (x != 0) {
			int rem = x % -2;
			x = x / -2;
			if (rem < 0) {
				rem += 2;
				x += 1;
			}
			sb.append(rem);
		}
		return sb.reverse().toString();
	}


public static void  convertNegativeBinary(int num){
	
	
	int binary[] = new int[40];
	int index = 0;
	
	System.out.println("convertNegativeBinary num: "+ num + ": ");
	
	while(num != 0){
		int rem = num % -2;
		num=num / -2;
		
		System.out.println("Rem  = " + rem + ", num =  " + num + "index = " + index );
		
		if(rem < 0 ){
			System.out.println("Rem is negiave = " + rem + "index = " + index);
			
			rem +=2;
			num+=1;			
		}
		
		binary[index++] = rem;
	}
	
	for(int i = index - 1;i >=0; i -- ){
		System.out.print(binary[i]);	
	}

	System.out.println();	
}
// Overlapping problem: Fibonacci sequence 

public static LinkedList<Integer> list = new LinkedList<Integer>();

public static int [] rem = new int [8];

public static int calFibonacci(int n){
	
	System.out.println("calFibonacci of " + n);
	
		
	int r = 1;
	
	
	if(rem[n -1] != 0){
		r = rem[n-1];		
	}
	else {
		
	  if (n > 2){
		r = calFibonacci(n -1) + calFibonacci(n -2);
	  }
	  rem[n - 1] = r;
	  
	}
	
	
	return r;
		
}


public static void main (String arg[]){
	
	
	    updateBit(-4, -6,2,5);
		
		System.out.println("~122 = " + (~122) + " ~156 = " + (~156));
		int num = countOnes(31);
		System.out.println("number of 1 of " + 31 +" " + num);
		
		num = countOnes(14);
		System.out.println("number of 1 of " + 14 +" " + num);
		
		int max = ~0;
		
		System.out.println("The max is: " + max +" int size: " + Integer.SIZE);
		TestBit.printBit(max );
		
		TestBit.printBit(4 );
		System.out.print("12.4 is : ");
		TestBit.printBinary("12.4");
		
		convertBinary(8);
		convertBinary(13);
		convertBinary(15);
		
		
		//calFibonacci(5);
		calFibonacci(Integer.valueOf(arg[0]));
		

		
	
		for(int i = 0; i < 8; i ++){
			System.out.print(rem[i] + "  ");
		}
			
			
		
		

		
		/*System.out.println(" 3/ -2  = " + (3 / -2));
		System.out.println("  -1 /% -2  = " + (-1  % -2));
		
		System.out.println("  15  / -2  = " + (15 / -2));
		System.out.println("  15  % -2  = " + (15 % -2));
		
		System.out.println(" 1/ -2  = " + (1 / -2));
		System.out.println(" 1/% -2  = " + (1 % -2));
		
		System.out.println("  -1  / -2  = " + (-1/ -2));
	    System.out.println("  -1  % -2  = " + (-1 % -2));
		
		
		convertNegativeBinary(3);
		convertNegativeBinary(4);
		convertNegativeBinary(2);
		convertNegativeBinary(9);
		convertNegativeBinary(15);
		convertNegativeBinary(17);
		convertNegativeBinary(10);
		convertNegativeBinary(20);
		
		
		
		
		convertNegativeBinary(-3);
		convertNegativeBinary(-4);
		convertNegativeBinary(-2);
		convertNegativeBinary(-9);
		convertNegativeBinary(-15);
		convertNegativeBinary(-17);
		convertNegativeBinary(-10);
		convertNegativeBinary(-20);
		
		
	*/
	
	
	
}

}