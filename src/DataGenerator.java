import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class DataGenerator {

    public static void main(String[] args) throws IOException {
        generate(10_000, 10);
        generate(100_000, 10);
        generate(1_000_000, 10);
        System.out.println("Đã tạo xong bộ dữ liệu trong thư mục data/");
    }

    static void generate(int n, int files) throws IOException {
        for (int t = 0; t < files; t++) {

            Random rnd = new Random(t);
            String name = "data/random_n" + n + "_trial" + t + ".txt";

            try (PrintWriter out = new PrintWriter(name)) {
                for (int i = 0; i < n; i++) {
                    out.println(rnd.nextInt(1_000_000_000));
                }
            }
            System.out.println("Đã tạo " + name);
        }
    }
}