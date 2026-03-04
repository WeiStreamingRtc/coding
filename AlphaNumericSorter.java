/*
Input Array: {"9", “Hello”, “-7”, “100”, “sh”, "6.5", "time”, "apple”}
Reorder above array in alphanumerical order (as shown below)
(1) starting with smallest value to highest value,
(2) sort characters from a to z order

Expected output array: {"-7", "6.5", "9", “100”, ”apple”, “Hello”, ”sh”, "time”}
Rule: Can’t use any OS inbuilt API’s for sorting (Can't use comparator or sort methods). Write your own sorting method.
You can use OS API's for other purposes.

We must:

1. Detect if a string is numeric
2. Compare numbers numerically
3. Compare strings lexicographically
4. Ensure numbers come before strings
5.Implement our own sorting algorithm

We’ll use QuickSort (clean and efficient).
*/

public class AlphaNumericSorter {

    public static void main(String[] args) {

        String[] arr = {"9", "Hello", "-7", "100", "sh", "6.5", "time", "apple", "shine"};

        quickSort(arr, 0, arr.length - 1);

        for (String s : arr) {
            System.out.print("\"" + s + "\" ");
        }
    }

    private static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static int compare(String a, String b) {

        boolean aNum = isNumeric(a);
        boolean bNum = isNumeric(b);

        if (aNum && bNum) {
            double da = Double.parseDouble(a);
            double db = Double.parseDouble(b);
            return Double.compare(da, db);
        }

        if (aNum && !bNum) return -1;
        if (!aNum && bNum) return 1;

        return a.compareToIgnoreCase(b);
    }

    public static void quickSort(String[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /*Our goal after partition:
    [ elements ≤ pivot ]  pivot  [ elements > pivot ]
     */
    private static int partition(String[] arr, int low, int high) {

        String pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }

        /* has to put pivot at the right place
        [ ≤ pivot ] [ > pivot ] pivot
          low       i         high-1  high
         */
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}