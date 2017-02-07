import java.util.*;
import java.io.*;
import java.lang.*;

public class AnalysisOfVariousAlgorithms{
	public static Scanner scanner = new Scanner(System.in);
    public static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public static void main(String[] args) throws java.lang.Exception {
        new AnalysisOfVariousAlgorithms().run();
        out.close();
    }
    void run() throws java.lang.Exception {
        long comparisons_by_Normal_partition = 0;
        long comparisons_by_Hoares_partition = 0;

        Random gen = new Random();
        int n = scanner.nextInt();
        int[] a = new int[n];
        for(int i = 0;i < n; i++){
            a[i] = gen.nextInt(10000)%1000;
        }
        
        //out.println(Arrays.toString(a));
        int[] b = new int[n];
        System.arraycopy(a, 0, b, 0, n);

        comparisons_by_Hoares_partition = QuickSort_Hoares(a,0,n - 1);
        comparisons_by_Normal_partition = QuickSort_Normal(b,0,n - 1);
        out.println("Comparisons by Hoare's Partition Algorithm -> " + comparisons_by_Hoares_partition);
        out.println("Comparisons by Normal Partition Algorithm -> " + comparisons_by_Normal_partition);
    }
    public static long QuickSort_Hoares(int[] a, int p, int r) {
        long comparisons_by_Hoares_partition = 0;
        if(p < r){
            long[] val = Hoares_Partition(a,p,r);
            int q = (int)val[0];
            comparisons_by_Hoares_partition += val[1];
            comparisons_by_Hoares_partition += QuickSort_Hoares(a,p,q);
            comparisons_by_Hoares_partition += QuickSort_Hoares(a,q + 1,r);
        }
        return comparisons_by_Hoares_partition;
    }
    private static long[] Hoares_Partition(int[] a, int p, int r) {

        int pivot = a[p];
        int i = p - 1;
        int j = r + 1;
        long comparisons_by_Hoares_partition = 0;
        while (true) {
            do {
                i++;
                comparisons_by_Hoares_partition++;
            } while (a[i] < pivot);

            do {
                j--;
                comparisons_by_Hoares_partition++;
            } while (a[j] > pivot);

            if (i >= j) {
                return new long [] {j,comparisons_by_Hoares_partition};
            } else {
                a[i] ^= a[j];
                a[j] ^= a[i];
                a[i] ^= a[j];
            }
        }
    }
    public static long QuickSort_Normal(int[] a, int p, int r) {
        long comparisons_by_Normal_partition = 0;
        long[] val = partition_Of_Normal_QuickSort(a, p, r);
        int ind = (int)val[0];
        comparisons_by_Normal_partition += val[1];
        if(p < ind - 1){
            comparisons_by_Normal_partition += QuickSort_Normal(a, p, ind - 1);
        }
        if(ind < r){
            comparisons_by_Normal_partition += QuickSort_Normal(a, ind, r);
        }
        return comparisons_by_Normal_partition;
    }
    public static long[] partition_Of_Normal_QuickSort(int[] a, int p, int r) {
        long comparisons_by_Normal_partition = 0;
        int i = p;
        int j = r;
        int pivot = a[(p + r) >>> 1];
        while(i <= j){
            while(a[i] < pivot){
                comparisons_by_Normal_partition++;
                i++;
            }
            while(a[j] > pivot){
                comparisons_by_Normal_partition++;
                j--;
            }
            if(i <= j){
                comparisons_by_Normal_partition++;
                a[i] ^= a[j];
                a[j] ^= a[i];
                a[i] ^= a[j];
                i++;
                j--;
            }
        }
        return new long[] {i,comparisons_by_Normal_partition};
    }
}
