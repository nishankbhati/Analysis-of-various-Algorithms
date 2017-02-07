import java.util.*;
import java.io.*;
import java.lang.*;

public class Hoares_Quick_Sort_Imple{
    public static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static long iter = 0;

    public static void main(String[] args) throws java.lang.Exception {
        new Quick_Sort_Imple().run();
        out.close();
    }
    void run() throws java.lang.Exception {
        Random gen = new Random();
        int n = 100;
        int[] a = new int[n];
        for(int i = 0;i < n; i++){
            a[i] = gen.nextInt(10000)%1000;
        }
        out.println(Arrays.toString(a));
        QuickSort(a,0,n - 1);
        out.println(Arrays.toString(a));
        out.println("Iterations by Hoare's Algorithm -> " + iter);
    }
    public static void QuickSort(int[] a, int p, int r) {
        if(p < r){
            int q = H_Partition(a,p,r);
            QuickSort(a,p,q);
            QuickSort(a,q + 1,r);
        }
    }
    private static int H_Partition(int[] a, int p, int r) {

        int pivot = a[p];
        int i = p - 1;
        int j = r + 1;

        while (true) {
            do {
                i++;
                iter++;
            } while (a[i] < pivot);

            do {
                j--;
                iter++;
            } while (a[j] > pivot);

            if (i >= j) {
                return j;
            } else {
                a[i] ^= a[j];
                a[j] ^= a[i];
                a[i] ^= a[j];
            }
        }
    }
}