# Tìm CUTOFF tối ưu cho Mergesort

Project thí nghiệm tìm giá trị CUTOFF (ngưỡng chuyển sang insertion sort) tốt nhất cho mergesort trên một máy cụ thể.

## Cấu trúc
- `src/`: mã nguồn (MergeSort, DataGenerator, Experiment, TestMergeSort)
- `data/`: 30 file dữ liệu thí nghiệm
- `results/`: kết quả đo (raw.csv) và bảng tính
- `report/`: báo cáo và biểu đồ

## Cách chạy
1. Biên dịch và chạy `src/TestMergeSort.java` để kiểm tra thuật toán.
2. Chạy `src/DataGenerator.java` để tạo lại dữ liệu (không cần nếu đã có thư mục `data/`).
3. Chạy `src/Experiment.java` để đo thời gian, kết quả ghi vào `results/raw.csv`.

Java 26.0.1 trên Windows. Xem chi tiết trong `report/report.md`.