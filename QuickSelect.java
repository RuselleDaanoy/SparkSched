package daa.project;
import java.util.*;

public class QuickSelect{

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size = input.nextInt();
        int[] array = new int[size];

        int k = (int) Math.ceil((double) size / 2);
        System.out.println("\nValue of k: " + k);

        int s = k - 1;
        System.out.print("Value of s: " + s);

        System.out.println("\n \nEnter the elements of the array:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            array[i] = input.nextInt();
        }

        System.out.print("\nThe elements inside the array are ");
        for (int i = 0; i < size; i++){
            System.out.print(array[i] + " ");
        }

        List<String> steps = new ArrayList<>();
        steps.add("Step\t| Left Pointer Index (l)\t| Right Pointer Index (r)\t| Pivot\t\t| Partitioned Array");

        String suffix = getSuffix(k);
        int kthSmallest = quickSelect(array, 0, array.length - 1, k, steps);
        System.out.println("\nThe " + k + suffix + " smallest element: " + kthSmallest);

        System.out.println("\nPARTITIONING PROCESS");
        for (String step : steps) {
            System.out.println(step);
        }

        input.close();
    }

    public static int quickSelect(int[] A, int l, int r, int k, List<String> steps) {
        while (l <= r) {
            int s = lomutoPartition(A, l, r, steps);
            if (s == k - 1) {
                return A[s];
            } else if (s > k - 1) {
                r = s - 1;
            } else {
                l = s + 1;
            }
        }
        return -1;
    }

    public static int lomutoPartition(int[] A, int l, int r, List<String> steps) {
        int p = A[l];
        int s = l;
        for (int i = l + 1; i <= r; i++) {
            if (A[i] < p) {
                s++;
                swap(A, s, i);
            }
        }
        swap(A, l, s);
        steps.add(String.format("%d\t| %d\t\t\t\t| %d\t\t\t\t| %d\t\t| %s", steps.size(), l, r, p, arrayToString(A)));
        return s;
    }

    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private static String arrayToString(int[] A) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < A.length; i++) {
            sb.append(A[i]);
            if (i < A.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static String getSuffix (int number){
        if (number == 1)
            return "st";
        else if (number == 2)
            return "nd";
        else if (number == 3)
            return "rd";
        else
            return "th";
    }
}
