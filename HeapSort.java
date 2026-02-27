import java.util.Arrays;

public class HeapSort {

    // Main heap sort function
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Step 1: Build max heap
        // Start from last non-leaf node and heapify each, last non-leaf node index is: n/2 -1
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        System.out.println("After building max heap: " + Arrays.toString(arr));

        // Step 2: Extract elements one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root (maximum) to end
            swap(arr, 0, i);

            // Call heapify on reduced heap, leaving the end not touched
            heapify(arr, i, 0);

            System.out.println("After step " + (n - i) + ": " + Arrays.toString(arr));
        }
    }

    // Heapify subtree rooted at index i
    // n is heap size
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;        // Initialize largest as root
        int left = 2 * i + 1;   // Left child
        int right = 2 * i + 2;  // Right child

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            swap(arr, i, largest);

            // Recursively heapify the affected subtree
            heapify(arr, n, largest);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Visual heap representation (simplified)
    public static void printHeapTree(int[] arr, int n) {
        int levels = (int)(Math.log(n) / Math.log(2)) + 1;
        int index = 0;

        for (int level = 0; level < levels; level++) {
            int nodesInLevel = (int)Math.pow(2, level);
            int spaces = (int)Math.pow(2, levels - level) * 2;

            // Print spaces
            for (int s = 0; s < spaces / 2; s++) {
                System.out.print(" ");
            }

            // Print nodes at this level
            for (int node = 0; node < nodesInLevel && index < n; node++) {
                System.out.print(arr[index]);
                // Print spaces between nodes
                for (int s = 0; s < spaces; s++) {
                    System.out.print(" ");
                }
                index++;
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};

        System.out.println("Original array: " + Arrays.toString(arr));
        System.out.println("\nHeap structure of original array:");
        printHeapTree(arr, arr.length);

        System.out.println("\n=== Sorting Process ===");
        heapSort(arr);

        System.out.println("\nSorted array: " + Arrays.toString(arr));
        System.out.println("\nFinal sorted array as heap structure:");
        printHeapTree(arr, arr.length);

        // More examples
        System.out.println("\n=== More Examples ===");
        int[][] testArrays = {
                {64, 34, 25, 12, 22, 11, 90},
                {5, 2, 8, 1, 9},
                {1, 2, 3, 4, 5}  // Already sorted
        };

        for (int[] test : testArrays) {
            int[] copy = test.clone();
            System.out.println("Before: " + Arrays.toString(copy));
            heapSort(copy);
            System.out.println("After:  " + Arrays.toString(copy));
            System.out.println();
        }
    }
}