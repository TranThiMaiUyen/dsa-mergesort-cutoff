import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Experiment {

    public static void main(String[] args) throws Exception {
        // 24 giá trị CUTOFF: từ 1 đến 20, rồi thêm 25, 30, 40, 50
        int[] cutoffs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                17, 18, 19, 20, 25, 30, 40, 50};
        int[] sizes = {10_000, 100_000, 1_000_000};
        int trials = 10;

        // Khởi động (warm-up): chạy vài lần trước để Java "làm nóng", không ghi kết quả
        int[] warm = readData("data/random_n100000_trial0.txt");
        for (int r = 0; r < 5; r++) {
            MergeSort.CUTOFF = 10;
            MergeSort.sort(warm.clone());
        }

        try (PrintWriter out = new PrintWriter(new FileWriter("results/raw.csv"))) {
            out.println("cutoff,n,trial,time_ms");

            for (int n : sizes) {
                for (int t = 0; t < trials; t++) {
                    int[] data = readData("data/random_n" + n + "_trial" + t + ".txt");

                    for (int m : cutoffs) {
                        int[] copy = data.clone();     // mỗi lần đo dùng một bản sao mới
                        MergeSort.CUTOFF = m;

                        long start = System.nanoTime();
                        MergeSort.sort(copy);
                        long elapsed = System.nanoTime() - start;

                        if (!isSorted(copy)) {
                            throw new RuntimeException("SAI tại CUTOFF=" + m + ", n=" + n);
                        }
                        out.println(m + "," + n + "," + t + "," + (elapsed / 1_000_000.0));
                    }
                    System.out.println("Xong n=" + n + " trial=" + t);
                }
            }
        }
        System.out.println("Hoàn thành. Kết quả lưu tại results/raw.csv");
    }

    static int[] readData(String path) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(path));
        int[] a = new int[lines.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(lines.get(i).trim());
        }
        return a;
    }

    static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) return false;
        }
        return true;
    }
}
