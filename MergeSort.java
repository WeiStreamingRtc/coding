import java.util.*;
// O(n log n)
public class MergeSort {
     
    private int[] array;
    private int[] tempMergArr;
    private int length;
 
    public static void main(String a[]){
         
        int[] inputArr = {11,10,9,8,7,6,5,4,3,2,1};
        MergeSort mms = new MergeSort();
        mms.sort(inputArr);
        for(int i:inputArr){
            System.out.print(i);
            System.out.print(" ");
        }
    }
     
    public void sort(int inputArr[]) {
        this.array = inputArr;
        this.length = inputArr.length;
        this.tempMergArr = new int[length];

        doMergeSort(0, length - 1);
    }
 
    private void doMergeSort(int lowerIndex, int higherIndex) {
         
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }
 
    private void mergeParts(int lowerIndex, int middle, int higherIndex) {
 
		//Have to copy it here as the arry is keep changing.
 		for (int i = 0; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }

        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
		//copy the remaining part, because k starts from lowerindex, we only need to copy the remaining part of lower part, This happens when 
		//the lowerpart is bigger than higher part; Note: higher part, already in the array.
        while (i <= middle) {
			//System.out.println("i = "+ i +", middle = " + middle+", j = " + j+ ", k = " + k);
			//System.out.println("array[k] = " + array[k] + ", tempMergArr[i] = " + tempMergArr[i]);
            array[k] = tempMergArr[i];
            k++;
            i++;
        }
 
    }
}
