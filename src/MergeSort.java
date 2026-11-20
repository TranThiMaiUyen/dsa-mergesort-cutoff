import java.util.Arrays;

public class MergeSort {

    public static int CUTOFF = 10;

    // Insertion sort
    public static void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > lo && a[j] < a[j - 1]; j--) {
                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        }
    }

    // Merge
    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    // Mergesort đệ quy trên đoạn có CUTOFF
    private static void sort(int[] a, int[] aux, int lo, int hi) {
        // Đoạn đủ nhỏ: dùng insertion sort rồi dừng
        if (hi <= lo + CUTOFF - 1) {
            insertionSort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(int[] a) {
        int[] aux = new int[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public static void main(String[] args) {
        int[] original = {5, 2, 9, 1, 7, 3, 8, 6, 4, 0};

        for (int c : new int[]{1, 3, 5, 10}) {
            CUTOFF = c;
            int[] a = original.clone();
            sort(a);
            System.out.println("CUTOFF = " + c + " -> " + Arrays.toString(a));
        }
    }
}
