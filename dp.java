import java.util.*;

public class dp {
	
	
	//http://www.techiedelight.com/3-partition-problem/
	public static boolean partition(int[] array, int parts, boolean checked, int subSum){
		/*First check if it can be parted. the sum of the array should be the multiple of the parts.  if not, then it can not be parted. For example:  if the sum of the array is 25, it cannot be parted into 3 parts. */		
		if(!checked){
			int sum = 0;
			for(int n = 0; n < array.length; n ++){
				sum += array[n];				
			}
			if (sum % parts != 0 ) {
				return false;
			}
			else {
				subSum = sum / parts;
			}
		}
		
		
		int[] pickedIndex = new int[array.length];
		int pickedNumb = 0;
		int tempSum = array[ array.length - 1];
		/*From the array, pick a first array that can sum up to the subSum, then continue to find the rest.
		*/
		for(int n = 0; n < array.length -1; n ++){
			
			tempSum += array[n];
			if(tempSum == subSum){
				pickedIndex[pickedNumb] = n;
				pickedNumb ++;
				break;				
			}
			else if(tempSum > subSum){
								
			}
			
		}
		
		return true;
	}
	
	public static boolean partitionArray(){
		int[] arr = {7,3,2,1,5,4,8};
		
		Arrays.sort(arr);
		
		boolean result = partition(arr, 3, false, -1);
		
		return result;
		
		
	}
	//This is a help function to check if the input sorted array can make a certain target sum, and in the resultIndex will have the picked index
	//for example the for {2,7,9,10}, target is 11, will return 0,2 in resultIndex, which is 2+9 = 11, and return 2, which means picked two number.
	//will return 0 if cannot make the target 
	//This is DP programming
	public static int makeSum(int[] array, int target, int[]resultIndex){
		int[] T = new int[target + 1];
		HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		
		T[0] = 0;
	
		for( int n = 1; n < target+1; n ++){
			for (int i = 0; i < array.length; i ++) {
				if(n - array[i] == 0) {
					T[n] = 1;
					List<Integer> list = new ArrayList<Integer>();
					list.add(new Integer(i));
					map.put(n,list);					
				}
				else if (n - array[i] > 0 ){
					T[n] = T[ n - array[i]];
					if(T[n] == 1) {
						List<Integer> list = map.get( new Integer(n - array[i]));
						List<Integer> temp = new ArrayList(list);
						//because every number can use only once, we need to check if the same number alreay have been used
				        //same number will be used many times.
						boolean has = false;
						for (Integer index : list ){
							if(index == i) has = true;
						}
						/*if( n == 15 || n == 17 ){
							System.out.println("doing " + n + " current array valu " + array[i] + " list is : " + list);
							System.out.println(" has = " + has);
						}*/
						if (has){
							T[n] = 0;
						}else {
							temp.add(new Integer(i));
							map.put(n,temp);
							//System.out.println(" T " + n + "= " + T[n]);
						}
					}
				}				
			}
		}
		
		/*for (int n = 0; n < target +1; n ++){
			System.out.println("number " +n +"= " + T[n]);
		}*/
		
		for (Map.Entry<Integer, List<Integer>> entry: map.entrySet()){
			Integer key = entry.getKey();
			List<Integer> list = entry.getValue();
			
			//System.out.println("key " + key +":  " + list);			
		}
		
		return T[target];
	}
	

	//find the minimum coins need to for a target value N
	//http://www.techiedelight.com/coin-change-making-problem-unlimited-supply-coins/
	public static int findMinCoins(int[] coins, int target){
		
		int[] T = new int[target + 1];
		
		T[0] = 0;
		
		for(int i = 1; i < target + 1; i ++){
			//go through coins to find the minimum
			
			T[i] = Integer.MAX_VALUE;
			int temp = Integer.MAX_VALUE;
			
			for (int c = 0; c < coins.length; c ++){
				temp = Integer.MAX_VALUE;
				if(i - coins[c] >= 0 ){
					temp = T[i - coins[c]] + 1;
				}
				if (T[i] > temp ){
					T[i] = temp;
				}				
			}
			
		}
		
	   return T[target];
		
	}
	
	/* Given the different rod length and it's value, found the max profit to cut a given rod with given length
	For example: rodlen = {1,2,3,4,5,6,7,8}, with price for each of them as val = {1,5,8,9,10,17,17,20}, a rod with len as 4
	//http://www.techiedelight.com/rot-cutting/
	*/
	public static int findMaxProfit(int[] rodlen, int[] val, int length){
		int[] T = new int[length + 1];
		
		int temp = 0;
		T[0] = 0;
		
	
		for(int i = 1; i < length + 1; i ++){
			T[i] = 0;
			for(int n = 0; n < rodlen.length; n ++){
				temp = 0;
				if(i - rodlen[n] >= 0 ){
					temp = T[ i - rodlen[n] ] + val[n];					
				}
				if (temp > T[i]){
					T[i] = temp;					
				}				
				
			}
		}
		
		return T[length];
	}
	/* 	http://www.techiedelight.com/coin-change-problem-find-total-number-ways-get-denomination-coins/
	Given a list of coins like {1,3,5,7}, and a number like 8, like total number of ways to make the value 8, in 
	the example should be 6. 
	
	This is similar to problem for climb the stairs --- https://www.youtube.com/watch?v=5o-kdjv7FD0.
	Give N stairs, and you can only take for example {1,3,5,7} every time, give the number of ways to finishe the stair.
	*/
	public static int findNumberOfWaysCoins(int[] coins, int val){
		
		int T[] = new int[val + 1];
		int temp = 0;
		
		T[0] = 1;
		
		/* NOTE: somehow, following solution is not working as it count 1+3 and 3+1 as two ways to make for 4 for example. 
		 this is the code for the stair problem, because 1 + 3 is different from 3+1 in this case
		*/
		/*for(int i = 1; i < val + 1; i ++){
			for (int n = 0; n < coins.length; n ++){
				if( i - coins[n] >= 0){
					T[i] = T[i] + T[i - coins[n]];
				}
			}
			System.out.println("T[ " + i + "] = " + T[i]);
		}*/
				
	 /* Following works, it only change the order of the two for loops.
	 This works like: if we know the number of way to make N with coins{1 3, 5}, then we will know the number of ways to make N with coins combination of {1, 3, 5, 7 }.
	 
	 */
	  int [] ways = new int[val+1];

	  ways[0] = 1; //Very import, set it to 1 NOT 0, as in theory, there is one way to make to 0
	
      for (int i = 0; i < coins.length; i++) {
		for (int j = coins[i]; j <= val; j++) {
		  ways[j]+=ways[j-coins[i]];
		  System.out.println("i = " + i + ":  " );
		  
		}
	  }
  
  
     return ways[val];
  
	 //return T[val];

	}
	/*
	A message containing letters from A-Z is being encoded to numbers using the following mapping:
	'A' -> 1
	'B' -> 2
	...
	'Z' -> 26
	Given a non-empty string containing only digits, determine the total number of ways to decode it.

	Example 1:

	Input: "12"
	Output: 2
	Explanation: It could be decoded as "AB" (1 2) or "L" (12).
	Example 2:

	Input: "226"
	Output: 3
	Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
	*/
	//This is dp solution, make sure to understand this one.
	public static int numDecoding(String msg){
		int[] ways = new int[msg.length() + 1];
		
		String temp = msg.substring(0,2);
		
		ways[0] = 1;
		
		ways[1] = Integer.parseInt(temp) <= 26 ? 2: 1 ;
		
		for(int n = 2; n < msg.length(); n ++){
			temp = msg.substring(n-1,n +1);
			int num = Integer.parseInt(temp);
			//NOTE: if <=26, way[n] = way[n - 1] + way[n-2], because the last two can be united as a new one to add to n-2
			ways[n] += ways[n -1 ];
			if(num <= 26){
				ways[n] += ways[n - 2 ];
			}
			
		}
		
		return ways[msg.length() -1];
	}
	//This is a recursive solution, which is much more simple than the dp one to understand
	public static int numDecodingRecursive(String msg, int k){
		int result = 0;
		
		if ( k ==0 ) return 1;
		
		int s = msg.length() -k;
		
			
		result = numDecodingRecursive(msg, k -1);
		String temp = "";
		if(k >= 2){
			temp = msg.substring(s, s + 2);
			if(Integer.parseInt(temp) <=26){
				result = result + numDecodingRecursive(msg, k -2);				
			}			
		}
		
		return result;
	}

	public static int numDecodingRecursive(String msg){
		return numDecodingRecursive(msg, msg.length());
	}
	
	
	/*http://www.techiedelight.com/weighted-interval-scheduling-problem/
	Weighted activity selection problem
	Give a list of jobs: start time, end time and weight, found the max weight list of job, for example
	
	[0, 6, 60 ], [1, 4, 30], [3, 5, 10] ,[5, 7, 30], [5, 9, 50],[7 ,8, 10]
	
	1. Sort the job first based on the finish time
	2. Go throught it and build the complete list
	
	*/
	
	public static int weightedActivitySelection(int[][] jobs){
		ArrayList<List<Integer>> result  = new ArrayList<List<Integer>>(jobs.length);
		int[] lastIndex = new int[jobs.length];
		
		//****Note**** this is a way to sort multi dimention array, when passed to compare, it is one dimentional array		
        Arrays.sort(jobs, new java.util.Comparator<int[]>(){
			public int compare(int[] a, int[] b){
				return (a[1] <  b[1] ? -1 :( a[1] == b[1]? 0: 1)); }			
			});		
		
	
	    Integer index = new Integer(0);
	    ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(index);
		result.add(list);
		lastIndex[0] = 0;
		int helpIndex = 0;
		boolean added = false;
		boolean started = false;
		for (int n = 1; n < jobs.length; n ++ ){
			added = false;
			for( int m = 0; m < result.size(); m ++){
				helpIndex = lastIndex[m];
				if(jobs[helpIndex][1] <= jobs[n][0]){
					List<Integer> tl = result.get(m);
					tl.add(new Integer(n));
					lastIndex[m] = n;
					added = true;
				}			
			}
			if(!added){
				started = false;
				//may start a new list, need to through the jobs again
				//This loop can be optimized to break in the middle
				for(int i = 0; i < jobs.length; i ++){
					int size = result.size();
					if(jobs[i][1] < jobs[n][0]){
						list = new ArrayList<Integer>();
						list.add(new Integer(i));
						list.add(new Integer(n));
						result.add(list);
						lastIndex[size] = n;
						started = true;
					}
					
				}
				if(!started){
					int size = result.size();
					list = new ArrayList<Integer>();
					list.add(new Integer(n));				
					result.add(list);
					lastIndex[size] = n;
				}
				
			}
			
		}
		
		for(int n = 0; n < result.size(); n ++){
	
			List<Integer> temp = result.get(n);
			System.out.print("result " + n +": " );
			for(Integer num: temp){
				System.out.print(" " + num);				
			}
			System.out.println();
			
			
		}
		return 0;
	}
	
	/*https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
	  Found the longest common subsequence: Let X be XMJYAUZ and Y be MZJAWXU. The longest common subsequence between is MJAU
	  To do this, need to contrstruct a two dimentional array, like followingng:
	  T E R R A C E D
	C 0 0 0 0 0 1 1 1
	R 0 0 1 1 1 1 1 1
	A 0 0 1 1 2 2 2 2
	T 1 1 1 1 2 2 2 2
	E 1 2 2 2 2 2 3 3
	R 1 2 3 3 3 3 3 3
	E 1 2 3 3 3 3 4 4
	D 1 2 3 3 3 3 4 5
	
	input one and two have same length
	*/
	public int longestCommonSubsequence(String one, String two){
		int len = one.length() + 1;
		int[][] ret = new int[len][len];
		//intilize the first row and colum to be 0
		for (int i = 0; i < len; i ++ ){
			ret[0][i] = 0;
			ret[i][0] = 0;
		}
		
		for(int n = 1; n < len; n ++){
			for(int m = 1; m < len; m ++){
				if(one.charAt(n -1) == two.charAt(m -1)){
					ret[m][n] = ret[m - 1][n - 1] + 1;
				}
				else {
					ret[m][n] = ret[m - 1][n] > ret[m][n-1] ? ret[m - 1][n] : ret[m][n-1];
				}
			}
		}
		return ret[len -1][len -1];
	}
	public static void main(String [] arg){
		
		int[][] jobs = {{0, 6, 60 }, {1, 4, 30}, {3, 5, 10} ,{5, 7, 30}, {5, 9, 50},{7 ,8, 10}};
		
		weightedActivitySelection(jobs);
		
		System.out.println("number ways to decode: " + "4557562545844617494555774581341211511296816786586787755257741178599337186486723247528324612117156948 : " + numDecoding("4557562545844617494555774581341211511296816786586787755257741178599337186486723247528324612117156948"));
		System.out.println("number ways to decode: " + "4557562545844617494555774581341211511296816786586787755257741178599337186486723247528324612117156948 : " + numDecodingRecursive("4557562545844617494555774581341211511296816786586787755257741178599337186486723247528324612117156948"));
		
		System.out.println("number ways to decode: " + "121212 : " + numDecoding("121212"));
		System.out.println("number ways to decode: " + "121212 : " + numDecodingRecursive("121212"));
		
		System.out.println("number ways to decode: " + "12121 : " + numDecoding("12121"));
		System.out.println("number ways to decode: " + "12121 : " + numDecodingRecursive("12121"));
		
		System.out.println("number ways to decode: " + "1212 : " + numDecoding("1212"));
		System.out.println("number ways to decode: " + "1212 : " + numDecodingRecursive("1212"));


		System.out.println("number ways to decode: " + "121 : " + numDecoding("121"));
		System.out.println("number ways to decode: " + "121 : " + numDecodingRecursive("121"));		
		
		int[] coins = {1, 3, 5, 7};
		int target = 8;
		
		System.out.println("8 minimum = " + findMinCoins(coins, target));
		
		System.out.println("8 number of ways = " + findNumberOfWaysCoins(coins, target));

		
		int[] rodlen = {1,2,3,4,5,6,7,8};
		int[] value = {1,5,8,9,10,17,17,20};
		
		System.out.println("4 profit = " + findMaxProfit(rodlen, value, 4));
	
		
		int [] array = {2,7,8,11, 13, 14};
		int [] picked = new int[array.length];
		System.out.println(" target 32 =  " + makeSum(array, 32, picked) );
		
	
	
	} 
	
}