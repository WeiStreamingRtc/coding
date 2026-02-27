import java.util.*;

/*
    Heap:  			https://www.youtube.com/watch?v=v1YUApMYXO4
	HeapSort:   	https://www.youtube.com/watch?v=6NB0GHY11Iw
	Code    :       http://xoax.net/comp_sci/crs/algorithms/lessons/Lesson9/
					http://xoax.net/comp_sci/crs/algorithms/lessons/Lesson10/
	
	Merge sort: 	https://www.youtube.com/watch?v=EeQ8pwjQxTM 
					https://en.wikipedia.org/wiki/Merge_sort
	
	Buble sort: 	https://www.youtube.com/watch?v=P00xJgWzz2c
	
	Insertion sort:  https://www.youtube.com/watch?v=c4BRHC7kTaQ
					https://en.wikipedia.org/wiki/Insertion_sort
					
	Selection sort:  https://www.youtube.com/watch?v=f8hXR_Hvybo
	Quick sort:      https://www.youtube.com/watch?v=y_G9BkAm6B8
	Bucket sort:      https://www.youtube.com/watch?v=GAU102t5n4U
	Radix sort: 	 https://www.youtube.com/watch?v=dPwAA7j-8o4 
					https://www.youtube.com/watch?v=xhr26ia4k38
					implementation:  https://gist.github.com/yeison/5606963
					https://zh.wikipedia.org/wiki/%E5%9F%BA%E6%95%B0%E6%8E%92%E5%BA%8F
	
	External sort:   https://en.wikipedia.org/wiki/External_sorting
	
*/


import java.util.Random;

public class TestSort { 

    // Main function to test performance sorting 1 million integers.
    // Results in about 220 ms on a 2.3 Ghz Core i5 processor w/4GB 1333 Mhz RAM
    public static void main(String[] args){
        final int SIZE = 100;

        Random r = new Random();

		
		int[] test = {10005,205,108,19,8,27,16,5,34,13,42,11,2};
        //long start = System.currentTimeMillis();
        // radixSort(test);
		 //insertionSort(test);
		 quickSort(test,0,test.length -1);
       // long end = System.currentTimeMillis();

        for (Integer i : test){
            System.out.print(i);
			System.out.print(" ");
        }
		System.out.println();
        //System.out.println(end-start);
    }

    // //This is RadixSort, Sort the numbers beginning with least-significant digit
    public static void radixSort(int[] input){

		int max = maxbit(input);
		int count[] = new int[10];
		
		int out[] = new int[input.length];
		int radix = 1;
		
        for(int place=1; place <= max; place ++){
			//Need to reset the count bucket
			for(int i = 0; i < count.length; i ++){
				count[i] = 0;
			}

			//get each remainder's number
			for(int i = 0; i < input.length; i ++){
				int dig = getDigit(input[i],radix);
				count[dig] ++;
			}

			//spread the bucket
			for(int i = 1; i < count.length; i ++){
				count[i] +=count[i-1];
			}
			//put in different bucket
			
			//	System.out.println("-------------place"  + place + "-------------------");
			//This HAS TO be start from the end to the beginning, as the bucket is decresing each time
			for(int i = input.length-1; i >=0; i --){
				int dig = getDigit(input[i],radix);
				out[count[dig]-1] = input[i];
				count[dig] --;
			}
			//System.out.println("--------------------------------");
			
			for(int i = 0; i < input.length; i ++){
				input[i]=out[i];
				//System.out.print(input[i] + " ");
			}
			//System.out.println();
			
			radix = radix*10;		
        }

      
    }
 
	//get the remainder
    private static int getDigit(int value, int radix){
        return ((value/radix ) % 10);
    }
	
	private static int maxbit(int data[]) {
		int maxData = data[0];		
		for (int i = 1; i < data.length; ++i){
			if (maxData < data[i])
				maxData = data[i];
		}
		int d = 1;
		int p = 10;
		while (maxData >= p){
			//p *= 10; // Maybe overflow
			maxData /= 10;
			++d;
		}
		return d;
	}


	public static void quickSort(int[] array, int left, int right){
		
		int pivot = (array[left] + array[right] )/ 2;
		
		/*for (Integer i : array){
            System.out.print(i);
			System.out.print(" ");
        }
		System.out.println();
		
		System.out.println(" left = " + left +   ", right =  " + right + ", pivot = " + pivot );
		*/
		int l = left;
		int r = right;
		int tmp = 0;
		
		
		//NOTE: NOT l<=r, which can end in endless loop
		while(l < r){ 
			
			while( array[l] < pivot ) l ++;
			while( array[r] > pivot ) r --;
			
			
			if(l < r){
				tmp = array[l];
				array[l] = array[r];
				array[r] = tmp;
				if(l<right) l++;
				if(r > left) r --;
			}
		}
		
		if( r > left)
			quickSort(array, left, r);
		if( l < right)
			quickSort(array, l,right);		
		
	}
	

// Insertion sort:

 public static void insertionSort(int toSort[]) {
	 //Note this is has to begin with i = 1, NOT i = 0
	  for (int i = 1; i < toSort.length; i++) {
		   int value = toSort[i];
		   int j = i -1 ;
		   while (j >=0 && toSort[j] > value) {
				toSort[j + 1] = toSort[j];
				j = j - 1;
		   }
		   toSort[j + 1] = value;

	  }
 }
	
}
