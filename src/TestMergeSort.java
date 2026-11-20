import java.util.Arrays;
import java.util.Random;

public class TestMergeSort {

    static int tests = 0;
    static int failures = 0;

    public static void main(String[] args) {
        Random rnd = new Random(42);

        int[] cutoffs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 16, 20, 25, 50, 100};

        for (int c : cutoffs) {
            MergeSort.CUTOFF = c;

            // Nhóm 1: các mảng đặc biệt
            check(new int[]{});
            check(new int[]{7});
            check(new int[]{2, 1});
            check(new int[]{1, 2});
            check(new int[]{5, 5, 5, 5, 5});
            check(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
            check(new int[]{12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1});

            // Nhóm 2: mảng ngẫu nhiên có độ dài từ 0 đến 60
            for (int n = 0; n <= 60; n++) {
                for (int t = 0; t < 20; t++) {
                    check(randomArray(rnd, n, 1000));
                }
            }

            // Nhóm 3: mảng có nhiều giá trị trùng nhau (chỉ gồm 0, 1, 2)
            for (int t = 0; t < 200; t++) {
                check(randomArray(rnd, 1 + rnd.nextInt(80), 3));
            }

            // Nhóm 4: mảng lớn hơn
            check(randomArray(rnd, 10_000, 1_000_000));
            check(sortedArray(10_000));
            check(reversedArray(10_000));
        }

        MergeSort.CUTOFF = 10;

        System.out.println("Tổng số test: " + tests);
        System.out.println("Số test sai: " + failures);
        System.out.println(failures == 0 ? "KẾT QUẢ: ĐẠT" : "KẾT QUẢ: KHÔNG ĐẠT");
    }

    static void check(int[] original) {
        tests++;
        int[] copy = original.clone();
        MergeSort.sort(copy);

        if (!isSorted(copy) || !sameContent(original, copy)) {
            failures++;
            if (failures <= 5) {
                String shown = original.length <= 20
                        ? Arrays.toString(original)
                        : "(mảng dài " + original.length + ")";
                System.out.println("SAI với CUTOFF=" + MergeSort.CUTOFF + ", đầu vào: " + shown);
            }
        }
    }

    // Kiểm tra mảng có tăng dần
    static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) return false;
        }
        return true;
    }

    // Kiểm tra kết quả có cùng các phần tử với đầu vào (

    static boolean sameContent(int[] x, int[] y) {
        if (x.length != y.length) return false;
        long sumX = 0, sumY = 0, sqX = 0, sqY = 0;
        for (int i = 0; i < x.length; i++) {
            sumX += x[i];
            sumY += y[i];
            sqX += (long) x[i] * x[i];
            sqY += (long) y[i] * y[i];
        }
        return sumX == sumY && sqX == sqY;
    }

    static int[] randomArray(Random rnd, int n, int bound) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = rnd.nextInt(bound);
        return a;
    }

    static int[] sortedArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i;
        return a;
    }

    static int[] reversedArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = n - i;
        return a;
    }
}
