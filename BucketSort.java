import java.util.*;
// O(n log n)

//A good post to explain the Big O of bucket sort (O(n + k), where k is the bucket number) 
//and radix sort O(n lg U, where U is the maximum value in the array): while it is O(n + U)
//https://stackoverflow.com/questions/7311415/how-is-the-complexity-of-bucket-sort-is-onk-if-we-implement-buckets-using-lin

public class BucketSort {
     
    private int[] array;
    private int[] tempMergArr;
    private int length;
 
    public static void main(String a[]){
         
        int[] inputArr = {101,99,11,10,9,8,7,6,5,4,3,2,1,98,2000,23,500,12};
        BucketSort mms = new BucketSort();
        mms.sort(inputArr);
   
    }
     
    public void sort(int input[]){
	  int min, max, divider, bucket_num;
	  
	  min = input[0];
	  max = input[0];
	  for(int i = 1; i < input.length; i ++){
		  if (input[i] < min) {
			  min = input[i];
		  } 
		  if(input[i] > max) {
			  max = input[i];
		  }		  
	  }

	  
	  divider = (max ) / input.length;
	  //Note: has to add one more, otherwise, out of index
	  bucket_num = max / divider + 1;
	  
	  System.out.println ("bucket_num = " + bucket_num + ", divider = " + divider);

	  //Note: ERROR: generic array creation!!!
	 // List<Integer> [] buckets = new ArrayList[bucket_num]; 
	 
	 //Note: because of no generic array, following will work, but have a warning: "found raw type: ArrayList"
	 ArrayList<Integer>[] arr = new ArrayList[bucket_num];
	 for (int i = 0; i < bucket_num; i ++){
		 arr[i] = new ArrayList<Integer>();
	 }
	 

	List<List<Integer>> buckets = new ArrayList<List<Integer>>(bucket_num);
	 //Note: has to initialize it first. 
	 for (int i = 0; i < bucket_num; i ++){
		 buckets.add(new ArrayList<Integer>());
	 }
	 
	  int index = 0;
	  
	  for (int n : input){
		  index = n / divider;
		  //System.out.println ("working on = " + n + ",  bucket index = " + index);
		  List<Integer> b = buckets.get(index);
		  b.add(n);
		  //Array version
		  //List<Integer> a = arr[index];
		  //a.add(n);
	  }
	  
	  for (int i = 0; i < bucket_num; i ++){
		  if(buckets.get(i).isEmpty()){
			  //System.out.println("bucket " + i + " :  is empty");
			  continue;			  
		  }
			  
		  Collections.sort(buckets.get(i));
		  System.out.println("bucket " + i + " : " + buckets.get(i).toString());
		  	  
	  }
	  
	  System.out.println(" ------------------Array version ----------------- ");
	  for (int i = 0; i < bucket_num; i ++){
		  if(arr[i].isEmpty()){
			  //System.out.println("bucket " + i + " :  is empty");
			  continue;			  
		  }
			  
		  Collections.sort(arr[i]);
		  System.out.println("bucket " + i + " : " + arr[i].toString());
		  	  
	  }
		
	}
   
}
