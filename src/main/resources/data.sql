---------------------------------------------------------
-- INSERT DỮ LIỆU CHO BẢNG Question VÀ Answers
---------------------------------------------------------

-- Q1: Topic 1 - Tin học cơ bản, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Tin học là gì?', 1, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (1, 'Là bộ môn nghiên cứu về xử lý thông tin', NULL, 1, 1),
                                                                       (1, 'Là môn học về văn hóa và nghệ thuật', NULL, 0, 1),
                                                                       (1, 'Là môn thể thao', NULL, 0, 1),
                                                                       (1, 'Là ngành kinh tế số', NULL, 0, 1);

-- Q2: Topic 2 - Chương 1: Giới thiệu về máy tính, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Máy tính là gì?', 2, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (2, 'Là thiết bị điện tử dùng để xử lý và lưu trữ thông tin', NULL, 1, 1),
                                                                       (2, 'Là máy in', NULL, 0, 1),
                                                                       (2, 'Là thiết bị giải trí', NULL, 0, 1),
                                                                       (2, 'Là bộ phận của điện thoại', NULL, 0, 1);

-- Q3: Topic 3 - Bài 1: Phần cứng và phần mềm, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Phần cứng là gì?', 3, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (3, 'Là các thành phần vật lý của máy tính như CPU, RAM, ổ cứng', NULL, 1, 1),
                                                                       (3, 'Là các chương trình máy tính', NULL, 0, 1),
                                                                       (3, 'Là dữ liệu lưu trữ', NULL, 0, 1),
                                                                       (3, 'Là giao diện người dùng', NULL, 0, 1);

-- Q4: Topic 4 - Bài 2: Hệ điều hành, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Vai trò của hệ điều hành là gì?', 4, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (4, 'Quản lý tài nguyên máy tính và điều phối hoạt động của các phần mềm', NULL, 1, 1),
                                                                       (4, 'Chỉ lưu trữ dữ liệu', NULL, 0, 1),
                                                                       (4, 'Chỉ quản lý phần cứng', NULL, 0, 1),
                                                                       (4, 'Chỉ phục vụ giao diện đồ họa', NULL, 0, 1);

-- Q5: Topic 5 - Chương 2: Mạng máy tính, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Mạng máy tính là gì?', 5, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (5, 'Là hệ thống liên kết các máy tính để chia sẻ tài nguyên và thông tin', NULL, 1, 1),
                                                                       (5, 'Là một thiết bị đơn lẻ', NULL, 0, 1),
                                                                       (5, 'Là phần mềm điều khiển máy tính', NULL, 0, 1),
                                                                       (5, 'Là công nghệ in ấn', NULL, 0, 1);

-- Q6: Topic 6 - Bài 1: Các mô hình mạng, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Trong mô hình OSI, hệ thống được chia thành bao nhiêu lớp?', 6, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (6, '7 lớp', NULL, 1, 1),
                                                                       (6, '5 lớp', NULL, 0, 1),
                                                                       (6, '6 lớp', NULL, 0, 1),
                                                                       (6, '8 lớp', NULL, 0, 1);

-- Q7: Topic 7 - Bài 2: Giao thức mạng, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Giao thức TCP/IP là gì?', 7, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (7, 'Tập hợp các quy tắc truyền tải dữ liệu qua mạng', NULL, 1, 1),
                                                                       (7, 'Là phần cứng của máy chủ', NULL, 0, 1),
                                                                       (7, 'Chỉ là giao thức truyền hình', NULL, 0, 1),
                                                                       (7, 'Là hệ điều hành của mạng', NULL, 0, 1);

-- Q8: Topic 8 - Tin học nâng cao, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Tin học nâng cao nghiên cứu về lĩnh vực nào?', 8, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (8, 'Công nghệ thông tin và các thuật toán phức tạp', NULL, 1, 1),
                                                                       (8, 'Chỉ về đồ họa máy tính', NULL, 0, 1),
                                                                       (8, 'Chỉ về lập trình web', NULL, 0, 1),
                                                                       (8, 'Chỉ về thiết kế game', NULL, 0, 1);

-- Q9: Topic 9 - Chương 1: Lập trình căn bản, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Nguyên lý cơ bản của lập trình là gì?', 9, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (9, 'Giải quyết bài toán bằng cách chia nhỏ vấn đề', NULL, 1, 1),
                                                                       (9, 'Chỉ ghi chép mã lệnh', NULL, 0, 1),
                                                                       (9, 'Quản lý dữ liệu trực tiếp', NULL, 0, 1),
                                                                       (9, 'Làm đẹp giao diện người dùng', NULL, 0, 1);

-- Q10: Topic 10 - Bài 1: Giới thiệu ngôn ngữ lập trình, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Ngôn ngữ lập trình là gì?', 10, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (10, 'Ngôn ngữ dùng để viết và phát triển các chương trình máy tính', NULL, 1, 1),
                                                                       (10, 'Ngôn ngữ giao tiếp giữa người với người', NULL, 0, 1),
                                                                       (10, 'Ngôn ngữ để thiết kế đồ họa', NULL, 0, 1),
                                                                       (10, 'Ngôn ngữ của hệ thống mạng', NULL, 0, 1);

-- Q11: Topic 11 - Bài 2: Biến và kiểu dữ liệu, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Sự khác biệt giữa biến và hằng số là gì?', 11, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (11, 'Biến có thể thay đổi giá trị, còn hằng số không thay đổi', NULL, 1, 1),
                                                                       (11, 'Hằng số có thể thay đổi, biến thì không', NULL, 0, 1),
                                                                       (11, 'Cả hai đều thay đổi giá trị', NULL, 0, 1),
                                                                       (11, 'Cả hai đều không thay đổi giá trị', NULL, 0, 1);

-- Q12: Topic 12 - Chương 2: Cấu trúc dữ liệu, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Cấu trúc dữ liệu là gì?', 12, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (12, 'Cách tổ chức và lưu trữ dữ liệu một cách hiệu quả', NULL, 1, 1),
                                                                       (12, 'Chỉ là dạng dữ liệu số', NULL, 0, 1),
                                                                       (12, 'Phương pháp thiết kế giao diện người dùng', NULL, 0, 1),
                                                                       (12, 'Chỉ là dữ liệu văn bản', NULL, 0, 1);

-- Q13: Topic 13 - Bài 1: Danh sách liên kết, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Ưu điểm của danh sách liên kết so với mảng là gì?', 13, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (13, 'Cho phép chèn và xóa phần tử một cách linh hoạt', NULL, 1, 1),
                                                                       (13, 'Truy cập ngẫu nhiên nhanh hơn', NULL, 0, 1),
                                                                       (13, 'Chiếm ít bộ nhớ hơn', NULL, 0, 1),
                                                                       (13, 'Sắp xếp tự động các phần tử', NULL, 0, 1);

-- Q14: Topic 14 - Bài 2: Cây nhị phân, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Đặc điểm của cây nhị phân là gì?', 14, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (14, 'Mỗi nút có tối đa hai con', NULL, 1, 1),
                                                                       (14, 'Mỗi nút có nhiều hơn hai con', NULL, 0, 1),
                                                                       (14, 'Cây có cấu trúc vòng lặp', NULL, 0, 1),
                                                                       (14, 'Không có nút lá', NULL, 0, 1);

-- Q15: Topic 1 - Tin học cơ bản, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Lịch sử phát triển của máy tính bắt đầu từ thời kỳ nào?', 1, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (15, 'Từ máy tính cơ học đến vi xử lý', NULL, 1, 1),
                                                                       (15, 'Bắt đầu từ thập niên 90', NULL, 0, 1),
                                                                       (15, 'Bắt đầu từ thế kỷ 18', NULL, 0, 1),
                                                                       (15, 'Không có lịch sử phát triển rõ ràng', NULL, 0, 1);

-- Q16: Topic 2 - Chương 1: Giới thiệu về máy tính, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Các thành phần cơ bản của một máy tính gồm những gì?', 2, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (16, 'Phần cứng, phần mềm và hệ điều hành', NULL, 1, 1),
                                                                       (16, 'Chỉ phần cứng', NULL, 0, 1),
                                                                       (16, 'Chỉ phần mềm', NULL, 0, 1),
                                                                       (16, 'Chỉ hệ điều hành', NULL, 0, 1);

-- Q17: Topic 3 - Bài 1: Phần cứng và phần mềm, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Tại sao việc phân biệt giữa phần cứng và phần mềm lại quan trọng?', 3, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (17, 'Giúp xác định cách bảo trì và nâng cấp hệ thống', NULL, 1, 1),
                                                                       (17, 'Không có ý nghĩa trong thực tế', NULL, 0, 1),
                                                                       (17, 'Chỉ ảnh hưởng đến giao diện người dùng', NULL, 0, 1),
                                                                       (17, 'Chỉ quan trọng trong thiết kế game', NULL, 0, 1);

-- Q18: Topic 4 - Bài 2: Hệ điều hành, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Hệ điều hành quản lý tài nguyên máy tính như thế nào?', 4, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (18, 'Bằng cách phân bổ CPU, bộ nhớ và thiết bị lưu trữ hợp lý', NULL, 1, 1),
                                                                       (18, 'Chỉ quản lý bộ nhớ', NULL, 0, 1),
                                                                       (18, 'Chỉ quản lý CPU', NULL, 0, 1),
                                                                       (18, 'Không quản lý tài nguyên', NULL, 0, 1);

-- Q19: Topic 5 - Chương 2: Mạng máy tính, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Lợi ích chính của mạng máy tính là gì?', 5, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (19, 'Cho phép chia sẻ tài nguyên và kết nối thông tin', NULL, 1, 1),
                                                                       (19, 'Tăng tốc độ xử lý cho máy tính cá nhân', NULL, 0, 1),
                                                                       (19, 'Giảm chi phí phần cứng', NULL, 0, 1),
                                                                       (19, 'Tạo ra các máy tính độc lập', NULL, 0, 1);

-- Q20: Topic 6 - Bài 1: Các mô hình mạng, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Trong mô hình Internet hiện đại, giao thức chủ yếu được sử dụng là gì?', 6, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (20, 'TCP/IP', NULL, 1, 1),
                                                                       (20, 'OSI', NULL, 0, 1),
                                                                       (20, 'UDP', NULL, 0, 1),
                                                                       (20, 'HTTP', NULL, 0, 1);

-- Q21: Topic 7 - Bài 2: Giao thức mạng, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Giao thức HTTP được sử dụng để làm gì?', 7, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (21, 'Truyền tải dữ liệu trên web', NULL, 1, 1),
                                                                       (21, 'Quản lý hệ thống mạng', NULL, 0, 1),
                                                                       (21, 'Lưu trữ tập tin', NULL, 0, 1),
                                                                       (21, 'Xử lý hình ảnh', NULL, 0, 1);

-- Q22: Topic 8 - Tin học nâng cao, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Trí tuệ nhân tạo (AI) là gì?', 8, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (22, 'Lĩnh vực nghiên cứu phát triển các hệ thống thông minh', NULL, 1, 1),
                                                                       (22, 'Một phần mềm trò chơi', NULL, 0, 1),
                                                                       (22, 'Hệ điều hành mới', NULL, 0, 1),
                                                                       (22, 'Ngôn ngữ lập trình đặc biệt', NULL, 0, 1);

-- Q23: Topic 9 - Chương 1: Lập trình căn bản, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Điều kiện cơ bản của một ngôn ngữ lập trình là gì?', 9, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (23, 'Cú pháp, ngữ nghĩa và cấu trúc điều khiển', NULL, 1, 1),
                                                                       (23, 'Giao diện đồ họa', NULL, 0, 1),
                                                                       (23, 'Chức năng mạng', NULL, 0, 1),
                                                                       (23, 'Bộ nhớ tạm thời', NULL, 0, 1);

-- Q24: Topic 10 - Bài 1: Giới thiệu ngôn ngữ lập trình, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Sự khác biệt giữa ngôn ngữ lập trình bậc cao và bậc thấp là gì?', 10, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (24, 'Bậc cao dễ đọc và hiểu hơn, bậc thấp gần với mã máy', NULL, 1, 1),
                                                                       (24, 'Không có sự khác biệt', NULL, 0, 1),
                                                                       (24, 'Bậc cao chạy nhanh hơn', NULL, 0, 1),
                                                                       (24, 'Bậc thấp dễ bảo trì hơn', NULL, 0, 1);

-- Q25: Topic 11 - Bài 2: Biến và kiểu dữ liệu, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Kiểu dữ liệu số nguyên trong lập trình thường được gọi là gì?', 11, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (25, 'Integer', NULL, 1, 1),
                                                                       (25, 'String', NULL, 0, 1),
                                                                       (25, 'Boolean', NULL, 0, 1),
                                                                       (25, 'Float', NULL, 0, 1);

-- Q26: Topic 12 - Chương 2: Cấu trúc dữ liệu, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Một cấu trúc dữ liệu phổ biến dùng để lưu trữ các giá trị theo thứ tự là gì?', 12, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (26, 'Mảng (Array)', NULL, 1, 1),
                                                                       (26, 'Danh sách liên kết', NULL, 0, 1),
                                                                       (26, 'Cây nhị phân', NULL, 0, 1),
                                                                       (26, 'Bảng băm (Hash Table)', NULL, 0, 1);

-- Q27: Topic 13 - Bài 1: Danh sách liên kết, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Ưu điểm của danh sách liên kết so với mảng là gì?', 13, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (27, 'Dễ dàng chèn và xóa phần tử', NULL, 1, 1),
                                                                       (27, 'Truy cập ngẫu nhiên nhanh hơn', NULL, 0, 1),
                                                                       (27, 'Sử dụng ít bộ nhớ hơn', NULL, 0, 1),
                                                                       (27, 'Tự động sắp xếp các phần tử', NULL, 0, 1);

-- Q28: Topic 14 - Bài 2: Cây nhị phân, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Một cây nhị phân cân bằng giúp cải thiện điều gì?', 14, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (28, 'Tốc độ tìm kiếm', NULL, 1, 1),
                                                                       (28, 'Độ sâu cây tăng', NULL, 0, 1),
                                                                       (28, 'Sắp xếp ngẫu nhiên', NULL, 0, 1),
                                                                       (28, 'Giảm sử dụng bộ nhớ', NULL, 0, 1);

-- Q29: Topic 1 - Tin học cơ bản, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Kiến thức tin học cơ bản bao gồm những nội dung nào?', 1, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (29, 'Lý thuyết máy tính, phần mềm, phần cứng và mạng máy tính', NULL, 1, 1),
                                                                       (29, 'Chỉ phần mềm', NULL, 0, 1),
                                                                       (29, 'Chỉ phần cứng', NULL, 0, 1),
                                                                       (29, 'Chỉ về mạng', NULL, 0, 1);

-- Q30: Topic 2 - Chương 1: Giới thiệu về máy tính, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Vai trò của máy tính trong cuộc sống hiện đại là gì?', 2, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (30, 'Hỗ trợ xử lý thông tin và tự động hóa công việc', NULL, 1, 1),
                                                                       (30, 'Chỉ để giải trí', NULL, 0, 1),
                                                                       (30, 'Chỉ để in ấn', NULL, 0, 1),
                                                                       (30, 'Chỉ để lưu trữ tài liệu', NULL, 0, 1);

-- Q31: Topic 3 - Bài 1: Phần cứng và phần mềm, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Ví dụ về phần mềm là gì?', 3, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (31, 'Hệ điều hành Windows', NULL, 1, 1),
                                                                       (31, 'Bàn phím', NULL, 0, 1),
                                                                       (31, 'Ổ cứng', NULL, 0, 1),
                                                                       (31, 'CPU', NULL, 0, 1);

-- Q32: Topic 4 - Bài 2: Hệ điều hành, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Hệ điều hành phổ biến cho máy tính cá nhân là gì?', 4, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (32, 'Windows', NULL, 1, 1),
                                                                       (32, 'Android', NULL, 0, 1),
                                                                       (32, 'Linux', NULL, 0, 1),
                                                                       (32, 'MacOS', NULL, 0, 1);

-- Q33: Topic 5 - Chương 2: Mạng máy tính, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Giao thức nào thường được dùng cho truyền tải email?', 5, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (33, 'SMTP', NULL, 1, 1),
                                                                       (33, 'HTTP', NULL, 0, 1),
                                                                       (33, 'FTP', NULL, 0, 1),
                                                                       (33, 'TCP', NULL, 0, 1);

-- Q34: Topic 6 - Bài 1: Các mô hình mạng, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Trong mô hình TCP/IP, các giao thức chủ yếu gồm những gì?', 6, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (34, 'IP, TCP, UDP và ICMP', NULL, 1, 1),
                                                                       (34, 'HTTP, FTP, SMTP', NULL, 0, 1),
                                                                       (34, 'DNS, DHCP', NULL, 0, 1),
                                                                       (34, 'SSL, TLS', NULL, 0, 1);

-- Q35: Topic 7 - Bài 2: Giao thức mạng, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Giao thức nào được sử dụng để bảo mật truyền thông trên mạng?', 7, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (35, 'HTTPS', NULL, 1, 1),
                                                                       (35, 'HTTP', NULL, 0, 1),
                                                                       (35, 'FTP', NULL, 0, 1),
                                                                       (35, 'SMTP', NULL, 0, 1);

-- Q36: Topic 8 - Tin học nâng cao, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Khái niệm Big Data liên quan đến điều gì?', 8, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (36, 'Phân tích và xử lý tập dữ liệu khổng lồ', NULL, 1, 1),
                                                                       (36, 'Lưu trữ một lượng nhỏ dữ liệu', NULL, 0, 1),
                                                                       (36, 'Chỉ xử lý dữ liệu văn bản', NULL, 0, 1),
                                                                       (36, 'Chỉ xử lý hình ảnh', NULL, 0, 1);

-- Q37: Topic 9 - Chương 1: Lập trình căn bản, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Các khái niệm lập trình cơ bản bao gồm những gì?', 9, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (37, 'Biến, hàm, vòng lặp và cấu trúc điều kiện', NULL, 1, 1),
                                                                       (37, 'Chỉ biến và hàm', NULL, 0, 1),
                                                                       (37, 'Chỉ vòng lặp', NULL, 0, 1),
                                                                       (37, 'Chỉ điều kiện', NULL, 0, 1);

-- Q38: Topic 10 - Bài 1: Giới thiệu ngôn ngữ lập trình, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Tại sao học ngôn ngữ lập trình lại quan trọng?', 10, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (38, 'Giúp giải quyết vấn đề và tạo ra các ứng dụng phần mềm', NULL, 1, 1),
                                                                       (38, 'Chỉ để giải trí', NULL, 0, 1),
                                                                       (38, 'Không có lợi ích thực tiễn', NULL, 0, 1),
                                                                       (38, 'Chỉ giúp tăng cường kỹ năng gõ phím', NULL, 0, 1);

-- Q39: Topic 11 - Bài 2: Biến và kiểu dữ liệu, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Kiểu dữ liệu số thực thường được biểu diễn bằng từ nào?', 11, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (39, 'Float', NULL, 1, 1),
                                                                       (39, 'Integer', NULL, 0, 1),
                                                                       (39, 'String', NULL, 0, 1),
                                                                       (39, 'Boolean', NULL, 0, 1);

-- Q40: Topic 12 - Chương 2: Cấu trúc dữ liệu, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Cấu trúc dữ liệu nào thích hợp cho việc thực hiện tìm kiếm nhị phân?', 12, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (40, 'Mảng đã được sắp xếp', NULL, 1, 1),
                                                                       (40, 'Danh sách liên kết', NULL, 0, 1),
                                                                       (40, 'Cây nhị phân không cân bằng', NULL, 0, 1),
                                                                       (40, 'Hash Table', NULL, 0, 1);

-- Q41: Topic 13 - Bài 1: Danh sách liên kết, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Nhược điểm chính của danh sách liên kết là gì?', 13, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (41, 'Truy cập ngẫu nhiên chậm', NULL, 1, 1),
                                                                       (41, 'Khó chèn phần tử', NULL, 0, 1),
                                                                       (41, 'Chiếm ít bộ nhớ', NULL, 0, 1),
                                                                       (41, 'Dễ sắp xếp tự động', NULL, 0, 1);

-- Q42: Topic 14 - Bài 2: Cây nhị phân, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Phương pháp duyệt cây nhị phân phổ biến nhất là gì?', 14, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (42, 'Duyệt theo thứ tự trung (In-order)', NULL, 1, 1),
                                                                       (42, 'Duyệt theo chiều rộng', NULL, 0, 1),
                                                                       (42, 'Duyệt theo chiều sâu (Depth-first)', NULL, 0, 1),
                                                                       (42, 'Duyệt ngẫu nhiên', NULL, 0, 1);

-- Q43: Topic 1 - Tin học cơ bản, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Kiến thức tin học cơ bản bao gồm những nội dung nào?', 1, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (43, 'Lý thuyết máy tính, phần mềm và phần cứng', NULL, 1, 1),
                                                                       (43, 'Chỉ phần mềm', NULL, 0, 1),
                                                                       (43, 'Chỉ phần cứng', NULL, 0, 1),
                                                                       (43, 'Chỉ về mạng', NULL, 0, 1);

-- Q44: Topic 2 - Chương 1: Giới thiệu về máy tính, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Máy tính đầu tiên được phát minh vào khoảng thời gian nào?', 2, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (44, 'Giữa thế kỷ 20', NULL, 1, 1),
                                                                       (44, 'Thế kỷ 18', NULL, 0, 1),
                                                                       (44, 'Cuối thế kỷ 19', NULL, 0, 1),
                                                                       (44, 'Đầu thế kỷ 21', NULL, 0, 1);

-- Q45: Topic 3 - Bài 1: Phần cứng và phần mềm, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('CPU là viết tắt của từ gì?', 3, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (45, 'Central Processing Unit', NULL, 1, 1),
                                                                       (45, 'Computer Personal Unit', NULL, 0, 1),
                                                                       (45, 'Central Program Unit', NULL, 0, 1),
                                                                       (45, 'Computer Processing Unit', NULL, 0, 1);

-- Q46: Topic 4 - Bài 2: Hệ điều hành, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Linux là một ví dụ về hệ điều hành thuộc loại nào?', 4, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (46, 'Hệ điều hành mã nguồn mở', NULL, 1, 1),
                                                                       (46, 'Hệ điều hành thương mại', NULL, 0, 1),
                                                                       (46, 'Hệ điều hành di động', NULL, 0, 1),
                                                                       (46, 'Hệ điều hành độc quyền', NULL, 0, 1);

-- Q47: Topic 5 - Chương 2: Mạng máy tính, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Mục đích của giao thức DNS là gì?', 5, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (47, 'Chuyển đổi tên miền thành địa chỉ IP', NULL, 1, 1),
                                                                       (47, 'Bảo mật thông tin', NULL, 0, 1),
                                                                       (47, 'Tăng tốc độ truy cập mạng', NULL, 0, 1),
                                                                       (47, 'Lưu trữ dữ liệu website', NULL, 0, 1);

-- Q48: Topic 6 - Bài 1: Các mô hình mạng, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Trong mô hình OSI, tầng Transport có nhiệm vụ gì?', 6, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (48, 'Đảm bảo truyền tải dữ liệu tin cậy giữa các thiết bị', NULL, 1, 1),
                                                                       (48, 'Quản lý kết nối vật lý', NULL, 0, 1),
                                                                       (48, 'Xử lý giao diện người dùng', NULL, 0, 1),
                                                                       (48, 'Bảo mật thông tin truyền tải', NULL, 0, 1);

-- Q49: Topic 7 - Bài 2: Giao thức mạng, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Giao thức FTP được sử dụng để làm gì?', 7, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (49, 'Truyền tải tập tin qua mạng', NULL, 1, 1),
                                                                       (49, 'Gửi email', NULL, 0, 1),
                                                                       (49, 'Truyền tải video trực tuyến', NULL, 0, 1),
                                                                       (49, 'Truyền tải âm thanh', NULL, 0, 1);

-- Q50: Topic 8 - Tin học nâng cao, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Machine Learning là gì?', 8, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (50, 'Một nhánh của trí tuệ nhân tạo, tập trung vào học từ dữ liệu', NULL, 1, 1),
                                                                       (50, 'Chỉ là thuật toán sắp xếp dữ liệu', NULL, 0, 1),
                                                                       (50, 'Một phần mềm quản lý cơ sở dữ liệu', NULL, 0, 1),
                                                                       (50, 'Một loại phần cứng mới', NULL, 0, 1);
---------------------------------------------------------
-- PHẦN 2: INSERT 50 CÂU HỎI VÀ ĐÁP ÁN (Q51 - Q100)
---------------------------------------------------------

-- Q51: Topic 1 - Tin học cơ bản, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Ý nghĩa của tin học trong đời sống hiện đại là gì?', 1, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (51, 'Giúp xử lý thông tin và tự động hóa công việc', NULL, 1, 1),
                                                                       (51, 'Chỉ là môn học lý thuyết', NULL, 0, 1),
                                                                       (51, 'Chỉ dành cho ngành kinh tế', NULL, 0, 1),
                                                                       (51, 'Không có ứng dụng thực tiễn', NULL, 0, 1);

-- Q52: Topic 2 - Chương 1: Giới thiệu về máy tính, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Các bộ phận chính của máy tính là gì?', 2, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (52, 'CPU, RAM, ổ cứng và bo mạch chủ', NULL, 1, 1),
                                                                       (52, 'Bàn phím, chuột và màn hình', NULL, 0, 1),
                                                                       (52, 'Hệ điều hành và phần mềm', NULL, 0, 1),
                                                                       (52, 'Mạng và internet', NULL, 0, 1);

-- Q53: Topic 3 - Bài 1: Phần cứng và phần mềm, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Các ví dụ về phần mềm là gì?', 3, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (53, 'Trình duyệt web và bộ xử lý văn bản', NULL, 1, 1),
                                                                       (53, 'Bàn phím và chuột', NULL, 0, 1),
                                                                       (53, 'Ổ cứng và RAM', NULL, 0, 1),
                                                                       (53, 'Card đồ họa và bo mạch chủ', NULL, 0, 1);

-- Q54: Topic 4 - Bài 2: Hệ điều hành, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Tại sao hệ điều hành lại quan trọng đối với máy tính?', 4, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (54, 'Nó quản lý tài nguyên và điều phối các tiến trình', NULL, 1, 1),
                                                                       (54, 'Chỉ giúp hiển thị giao diện đồ họa', NULL, 0, 1),
                                                                       (54, 'Chỉ chạy các ứng dụng di động', NULL, 0, 1),
                                                                       (54, 'Không ảnh hưởng đến hiệu suất máy tính', NULL, 0, 1);

-- Q55: Topic 5 - Chương 2: Mạng máy tính, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Mạng máy tính là gì?', 5, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (55, 'Hệ thống liên kết các máy tính để chia sẻ tài nguyên và thông tin', NULL, 1, 1),
                                                                       (55, 'Một thiết bị đơn lẻ', NULL, 0, 1),
                                                                       (55, 'Phần mềm điều khiển máy tính', NULL, 0, 1),
                                                                       (55, 'Công nghệ in ấn', NULL, 0, 1);

-- Q56: Topic 6 - Bài 1: Các mô hình mạng, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Mô hình OSI gồm bao nhiêu tầng?', 6, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (56, '7 tầng', NULL, 1, 1),
                                                                       (56, '5 tầng', NULL, 0, 1),
                                                                       (56, '6 tầng', NULL, 0, 1),
                                                                       (56, '8 tầng', NULL, 0, 1);

-- Q57: Topic 7 - Bài 2: Giao thức mạng, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Giao thức TCP/IP là gì?', 7, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (57, 'Tập hợp các quy tắc truyền tải dữ liệu qua mạng', NULL, 1, 1),
                                                                       (57, 'Phần cứng của máy chủ', NULL, 0, 1),
                                                                       (57, 'Giao thức truyền hình', NULL, 0, 1),
                                                                       (57, 'Hệ điều hành của mạng', NULL, 0, 1);

-- Q58: Topic 8 - Tin học nâng cao, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Những xu hướng công nghệ thuộc lĩnh vực tin học nâng cao là gì?', 8, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (58, 'Trí tuệ nhân tạo, Big Data và điện toán đám mây', NULL, 1, 1),
                                                                       (58, 'Chỉ lập trình web', NULL, 0, 1),
                                                                       (58, 'Chỉ thiết kế đồ họa', NULL, 0, 1),
                                                                       (58, 'Chỉ quản trị mạng', NULL, 0, 1);

-- Q59: Topic 9 - Chương 1: Lập trình căn bản, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Các khái niệm cơ bản trong lập trình là gì?', 9, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (59, 'Biến, hàm, vòng lặp, điều kiện và cấu trúc dữ liệu', NULL, 1, 1),
                                                                       (59, 'Chỉ là biến và hàm', NULL, 0, 1),
                                                                       (59, 'Chỉ là vòng lặp', NULL, 0, 1),
                                                                       (59, 'Chỉ là điều kiện', NULL, 0, 1);

-- Q60: Topic 10 - Bài 1: Giới thiệu ngôn ngữ lập trình, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Ngôn ngữ lập trình là gì?', 10, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (60, 'Ngôn ngữ dùng để viết và phát triển các chương trình máy tính', NULL, 1, 1),
                                                                       (60, 'Ngôn ngữ giao tiếp giữa người với người', NULL, 0, 1),
                                                                       (60, 'Ngôn ngữ để thiết kế đồ họa', NULL, 0, 1),
                                                                       (60, 'Ngôn ngữ của hệ thống mạng', NULL, 0, 1);

-- Q61: Topic 11 - Bài 2: Biến và kiểu dữ liệu, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Sự khác biệt giữa biến và hằng số là gì?', 11, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (61, 'Biến có thể thay đổi giá trị, còn hằng số không thay đổi', NULL, 1, 1),
                                                                       (61, 'Hằng số có thể thay đổi, biến thì không', NULL, 0, 1),
                                                                       (61, 'Cả hai đều thay đổi giá trị', NULL, 0, 1),
                                                                       (61, 'Cả hai đều không thay đổi giá trị', NULL, 0, 1);

-- Q62: Topic 12 - Chương 2: Cấu trúc dữ liệu, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Cấu trúc dữ liệu là gì?', 12, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (62, 'Cách tổ chức và lưu trữ dữ liệu một cách hiệu quả', NULL, 1, 1),
                                                                       (62, 'Chỉ là dạng dữ liệu số', NULL, 0, 1),
                                                                       (62, 'Phương pháp thiết kế giao diện người dùng', NULL, 0, 1),
                                                                       (62, 'Chỉ là dữ liệu văn bản', NULL, 0, 1);

-- Q63: Topic 13 - Bài 1: Danh sách liên kết, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Ưu điểm của danh sách liên kết so với mảng là gì?', 13, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (63, 'Cho phép chèn và xóa phần tử một cách linh hoạt', NULL, 1, 1),
                                                                       (63, 'Truy cập ngẫu nhiên nhanh hơn', NULL, 0, 1),
                                                                       (63, 'Chiếm ít bộ nhớ hơn', NULL, 0, 1),
                                                                       (63, 'Sắp xếp tự động các phần tử', NULL, 0, 1);

-- Q64: Topic 14 - Bài 2: Cây nhị phân, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Đặc điểm của cây nhị phân là gì?', 14, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (64, 'Mỗi nút có tối đa hai con', NULL, 1, 1),
                                                                       (64, 'Mỗi nút có nhiều hơn hai con', NULL, 0, 1),
                                                                       (64, 'Cây có cấu trúc vòng lặp', NULL, 0, 1),
                                                                       (64, 'Không có nút lá', NULL, 0, 1);

-- Q65: Topic 1 - Tin học cơ bản, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Lịch sử phát triển của máy tính bắt đầu từ thời kỳ nào?', 1, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (65, 'Từ máy tính cơ học đến vi xử lý', NULL, 1, 1),
                                                                       (65, 'Bắt đầu từ thập niên 90', NULL, 0, 1),
                                                                       (65, 'Bắt đầu từ thế kỷ 18', NULL, 0, 1),
                                                                       (65, 'Không có lịch sử phát triển rõ ràng', NULL, 0, 1);

-- Q66: Topic 2 - Chương 1: Giới thiệu về máy tính, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Các thành phần cơ bản của máy tính gồm những gì?', 2, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (66, 'Phần cứng, phần mềm và hệ điều hành', NULL, 1, 1),
                                                                       (66, 'Chỉ phần cứng', NULL, 0, 1),
                                                                       (66, 'Chỉ phần mềm', NULL, 0, 1),
                                                                       (66, 'Chỉ hệ điều hành', NULL, 0, 1);

-- Q67: Topic 3 - Bài 1: Phần cứng và phần mềm, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Tại sao việc phân biệt giữa phần cứng và phần mềm lại quan trọng?', 3, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (67, 'Giúp xác định cách bảo trì và nâng cấp hệ thống', NULL, 1, 1),
                                                                       (67, 'Không có ý nghĩa trong thực tế', NULL, 0, 1),
                                                                       (67, 'Ảnh hưởng đến giao diện người dùng', NULL, 0, 1),
                                                                       (67, 'Quan trọng chỉ trong thiết kế game', NULL, 0, 1);

-- Q68: Topic 4 - Bài 2: Hệ điều hành, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Hệ điều hành xử lý đa nhiệm như thế nào?', 4, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (68, 'Phân chia thời gian CPU cho từng tiến trình', NULL, 1, 1),
                                                                       (68, 'Chỉ chạy một tiến trình tại một thời điểm', NULL, 0, 1),
                                                                       (68, 'Ưu tiên tiến trình đồ họa', NULL, 0, 1),
                                                                       (68, 'Không hỗ trợ đa nhiệm', NULL, 0, 1);

-- Q69: Topic 5 - Chương 2: Mạng máy tính, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Công nghệ Wi-Fi là gì?', 5, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (69, 'Công nghệ truyền tải dữ liệu không dây qua sóng radio', NULL, 1, 1),
                                                                       (69, 'Công nghệ cáp quang', NULL, 0, 1),
                                                                       (69, 'Công nghệ Bluetooth', NULL, 0, 1),
                                                                       (69, 'Công nghệ mạng dây', NULL, 0, 1);

-- Q70: Topic 6 - Bài 1: Các mô hình mạng, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Các giao thức chính của mô hình TCP/IP là gì?', 6, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (70, 'IP, TCP, UDP, ICMP và ARP', NULL, 1, 1),
                                                                       (70, 'Chỉ TCP và IP', NULL, 0, 1),
                                                                       (70, 'Chỉ UDP và ICMP', NULL, 0, 1),
                                                                       (70, 'Chỉ ARP và DNS', NULL, 0, 1);

-- Q71: Topic 7 - Bài 2: Giao thức mạng, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Giao thức nào được dùng để chuyển giao file giữa các máy tính?', 7, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (71, 'FTP', NULL, 1, 1),
                                                                       (71, 'HTTP', NULL, 0, 1),
                                                                       (71, 'SMTP', NULL, 0, 1),
                                                                       (71, 'SNMP', NULL, 0, 1);

-- Q72: Topic 8 - Tin học nâng cao, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Khái niệm Machine Learning là gì?', 8, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (72, 'Là một nhánh của AI tập trung vào học từ dữ liệu', NULL, 1, 1),
                                                                       (72, 'Là thuật toán sắp xếp dữ liệu', NULL, 0, 1),
                                                                       (72, 'Là hệ thống lưu trữ dữ liệu lớn', NULL, 0, 1),
                                                                       (72, 'Là phần mềm chỉnh sửa ảnh', NULL, 0, 1);

-- Q73: Topic 9 - Chương 1: Lập trình căn bản, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Tại sao cú pháp lại quan trọng trong lập trình?', 9, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (73, 'Giúp máy tính hiểu và thực thi lệnh chính xác', NULL, 1, 1),
                                                                       (73, 'Chỉ để tạo giao diện đẹp', NULL, 0, 1),
                                                                       (73, 'Không ảnh hưởng đến hiệu suất', NULL, 0, 1),
                                                                       (73, 'Ảnh hưởng đến phần cứng', NULL, 0, 1);

-- Q74: Topic 10 - Bài 1: Giới thiệu ngôn ngữ lập trình, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Yếu tố nào làm nên sự khác biệt giữa các ngôn ngữ lập trình?', 10, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (74, 'Cú pháp, thư viện và cộng đồng hỗ trợ', NULL, 1, 1),
                                                                       (74, 'Chỉ giao diện người dùng', NULL, 0, 1),
                                                                       (74, 'Chỉ khả năng xử lý dữ liệu', NULL, 0, 1),
                                                                       (74, 'Chỉ tốc độ biên dịch', NULL, 0, 1);

-- Q75: Topic 11 - Bài 2: Biến và kiểu dữ liệu, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Kiểu dữ liệu nào dùng để lưu trữ giá trị đúng/sai?', 11, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (75, 'Boolean', NULL, 1, 1),
                                                                       (75, 'Integer', NULL, 0, 1),
                                                                       (75, 'Float', NULL, 0, 1),
                                                                       (75, 'String', NULL, 0, 1);

-- Q76: Topic 12 - Chương 2: Cấu trúc dữ liệu, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Cấu trúc dữ liệu nào hỗ trợ truy cập theo chỉ số?', 12, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (76, 'Mảng (Array)', NULL, 1, 1),
                                                                       (76, 'Danh sách liên kết', NULL, 0, 1),
                                                                       (76, 'Cây nhị phân', NULL, 0, 1),
                                                                       (76, 'Bảng băm (Hash Table)', NULL, 0, 1);

-- Q77: Topic 13 - Bài 1: Danh sách liên kết, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Nhược điểm của danh sách liên kết so với mảng là gì?', 13, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (77, 'Không hỗ trợ truy cập ngẫu nhiên hiệu quả', NULL, 1, 1),
                                                                       (77, 'Chiếm ít bộ nhớ hơn', NULL, 0, 1),
                                                                       (77, 'Dễ dàng sắp xếp tự động', NULL, 0, 1),
                                                                       (77, 'Tốc độ xử lý nhanh hơn', NULL, 0, 1);

-- Q78: Topic 14 - Bài 2: Cây nhị phân, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Các phương pháp duyệt cây thông dụng là gì?', 14, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (78, 'Duyệt trung, duyệt trước và duyệt sau', NULL, 1, 1),
                                                                       (78, 'Duyệt theo chiều ngang', NULL, 0, 1),
                                                                       (78, 'Duyệt ngẫu nhiên', NULL, 0, 1),
                                                                       (78, 'Duyệt theo thứ tự đảo ngược', NULL, 0, 1);

-- Q79: Topic 1 - Tin học cơ bản, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Các ứng dụng thiết yếu của tin học trong đời sống là gì?', 1, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (79, 'Quản lý thông tin, tự động hóa và truyền thông', NULL, 1, 1),
                                                                       (79, 'Chỉ để chơi game', NULL, 0, 1),
                                                                       (79, 'Chỉ để xem phim', NULL, 0, 1),
                                                                       (79, 'Chỉ dành cho nghiên cứu khoa học', NULL, 0, 1);

-- Q80: Topic 2 - Chương 1: Giới thiệu về máy tính, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Sự phát triển của máy tính từ quá khứ đến hiện nay có những điểm nổi bật nào?', 2, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (80, 'Chuyển từ máy tính cơ học sang máy tính điện tử và hiện đại', NULL, 1, 1),
                                                                       (80, 'Máy tính luôn không thay đổi theo thời gian', NULL, 0, 1),
                                                                       (80, 'Chỉ thay đổi về kích thước', NULL, 0, 1),
                                                                       (80, 'Chỉ thay đổi về màu sắc', NULL, 0, 1);

-- Q81: Topic 3 - Bài 1: Phần cứng và phần mềm, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Ví dụ nào sau đây là phần cứng?', 3, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (81, 'Bo mạch chủ', NULL, 1, 1),
                                                                       (81, 'Hệ điều hành', NULL, 0, 1),
                                                                       (81, 'Phần mềm diệt virus', NULL, 0, 1),
                                                                       (81, 'Trình duyệt web', NULL, 0, 1);

-- Q82: Topic 4 - Bài 2: Hệ điều hành, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Sự khác biệt chính giữa Windows và Linux là gì?', 4, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (82, 'Windows thân thiện với người dùng, Linux tối ưu về hiệu suất và bảo mật', NULL, 1, 1),
                                                                       (82, 'Hai hệ điều hành không có sự khác biệt', NULL, 0, 1),
                                                                       (82, 'Linux không có giao diện người dùng', NULL, 0, 1),
                                                                       (82, 'Windows không hỗ trợ đa nhiệm', NULL, 0, 1);

-- Q83: Topic 5 - Chương 2: Mạng máy tính, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Công nghệ cáp quang được sử dụng như thế nào trong mạng?', 5, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (83, 'Chuyển dữ liệu với tốc độ cao và khoảng cách xa', NULL, 1, 1),
                                                                       (83, 'Chỉ dùng cho kết nối nội bộ', NULL, 0, 1),
                                                                       (83, 'Chỉ dành cho truyền hình', NULL, 0, 1),
                                                                       (83, 'Không hỗ trợ truyền dữ liệu', NULL, 0, 1);

-- Q84: Topic 6 - Bài 1: Các mô hình mạng, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Lợi ích của mô hình TCP/IP là gì?', 6, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (84, 'Tính linh hoạt và khả năng kết nối toàn cầu', NULL, 1, 1),
                                                                       (84, 'Chỉ hoạt động trong mạng LAN', NULL, 0, 1),
                                                                       (84, 'Không hỗ trợ kết nối internet', NULL, 0, 1),
                                                                       (84, 'Chỉ sử dụng trong doanh nghiệp lớn', NULL, 0, 1);

-- Q85: Topic 7 - Bài 2: Giao thức mạng, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Giao thức nào đảm bảo an toàn truyền tải dữ liệu?', 7, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (85, 'HTTPS', NULL, 1, 1),
                                                                       (85, 'HTTP', NULL, 0, 1),
                                                                       (85, 'FTP', NULL, 0, 1),
                                                                       (85, 'SMTP', NULL, 0, 1);

-- Q86: Topic 8 - Tin học nâng cao, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Những ứng dụng của trí tuệ nhân tạo trong đời sống là gì?', 8, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (86, 'Phân tích dữ liệu, nhận dạng giọng nói và hình ảnh', NULL, 1, 1),
                                                                       (86, 'Chỉ để chơi game', NULL, 0, 1),
                                                                       (86, 'Chỉ phục vụ truyền hình', NULL, 0, 1),
                                                                       (86, 'Chỉ để in ấn', NULL, 0, 1);

-- Q87: Topic 9 - Chương 1: Lập trình căn bản, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Các bước cơ bản khi viết chương trình là gì?', 9, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (87, 'Phân tích, thiết kế, lập trình, kiểm thử và bảo trì', NULL, 1, 1),
                                                                       (87, 'Chỉ lập trình và chạy chương trình', NULL, 0, 1),
                                                                       (87, 'Chỉ phân tích và thiết kế', NULL, 0, 1),
                                                                       (87, 'Chỉ kiểm thử và bảo trì', NULL, 0, 1);

-- Q88: Topic 10 - Bài 1: Giới thiệu ngôn ngữ lập trình, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Lợi ích của việc học lập trình là gì?', 10, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (88, 'Giúp giải quyết vấn đề và tạo ra các ứng dụng sáng tạo', NULL, 1, 1),
                                                                       (88, 'Chỉ để kiếm tiền nhanh chóng', NULL, 0, 1),
                                                                       (88, 'Chỉ để phát triển game', NULL, 0, 1),
                                                                       (88, 'Chỉ để quản lý mạng xã hội', NULL, 0, 1);

-- Q89: Topic 11 - Bài 2: Biến và kiểu dữ liệu, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Tại sao việc lựa chọn kiểu dữ liệu phù hợp lại quan trọng?', 11, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (89, 'Giúp tối ưu hiệu suất và sử dụng bộ nhớ hiệu quả', NULL, 1, 1),
                                                                       (89, 'Không ảnh hưởng nhiều đến chương trình', NULL, 0, 1),
                                                                       (89, 'Chỉ để tăng độ phức tạp của code', NULL, 0, 1),
                                                                       (89, 'Chỉ để làm đẹp giao diện', NULL, 0, 1);

-- Q90: Topic 12 - Chương 2: Cấu trúc dữ liệu, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Ưu điểm của bảng băm (Hash Table) là gì?', 12, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (90, 'Truy cập dữ liệu gần như thời gian hằng số', NULL, 1, 1),
                                                                       (90, 'Sắp xếp dữ liệu tự động', NULL, 0, 1),
                                                                       (90, 'Dễ dàng thay đổi kích thước', NULL, 0, 1),
                                                                       (90, 'Hỗ trợ tìm kiếm nhị phân', NULL, 0, 1);

-- Q91: Topic 13 - Bài 1: Danh sách liên kết, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Những hạn chế của danh sách liên kết là gì?', 13, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (91, 'Không hỗ trợ truy cập ngẫu nhiên hiệu quả', NULL, 1, 1),
                                                                       (91, 'Tốc độ xử lý quá nhanh', NULL, 0, 1),
                                                                       (91, 'Chiếm quá nhiều bộ nhớ', NULL, 0, 1),
                                                                       (91, 'Tự động sắp xếp dữ liệu', NULL, 0, 1);

-- Q92: Topic 14 - Bài 2: Cây nhị phân, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Yếu tố nào ảnh hưởng đến hiệu suất duyệt cây nhị phân?', 14, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (92, 'Cân bằng của cây', NULL, 1, 1),
                                                                       (92, 'Số lượng nút trong cây', NULL, 0, 1),
                                                                       (92, 'Màu sắc của nút', NULL, 0, 1),
                                                                       (92, 'Kích thước của nút', NULL, 0, 1);

-- Q93: Topic 1 - Tin học cơ bản, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Vai trò của công nghệ thông tin trong giáo dục là gì?', 1, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (93, 'Cung cấp công cụ học tập và trao đổi tri thức', NULL, 1, 1),
                                                                       (93, 'Chỉ để giải trí', NULL, 0, 1),
                                                                       (93, 'Chỉ dành cho ngành kinh doanh', NULL, 0, 1),
                                                                       (93, 'Không có vai trò thực tiễn', NULL, 0, 1);

-- Q94: Topic 2 - Chương 1: Giới thiệu về máy tính, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Công nghệ nào giúp máy tính kết nối internet?', 2, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (94, 'Wi-Fi và Ethernet', NULL, 1, 1),
                                                                       (94, 'Bluetooth', NULL, 0, 1),
                                                                       (94, 'GPS', NULL, 0, 1),
                                                                       (94, 'RFID', NULL, 0, 1);

-- Q95: Topic 3 - Bài 1: Phần cứng và phần mềm, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Yếu tố nào giúp xác định hiệu suất của máy tính?', 3, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (95, 'Sự kết hợp giữa phần cứng và phần mềm tối ưu', NULL, 1, 1),
                                                                       (95, 'Chỉ phần mềm', NULL, 0, 1),
                                                                       (95, 'Chỉ phần cứng', NULL, 0, 1),
                                                                       (95, 'Chỉ bộ nhớ RAM', NULL, 0, 1);

-- Q96: Topic 4 - Bài 2: Hệ điều hành, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Những ưu điểm của hệ điều hành Linux là gì?', 4, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (96, 'Mã nguồn mở, bảo mật và hiệu suất cao', NULL, 1, 1),
                                                                       (96, 'Chỉ hỗ trợ chơi game', NULL, 0, 1),
                                                                       (96, 'Chỉ dùng cho máy tính bảng', NULL, 0, 1),
                                                                       (96, 'Không hỗ trợ đa nhiệm', NULL, 0, 1);

-- Q97: Topic 5 - Chương 2: Mạng máy tính, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Vai trò của thiết bị modem là gì?', 5, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (97, 'Kết nối mạng máy tính với internet', NULL, 1, 1),
                                                                       (97, 'Chuyển đổi tín hiệu video', NULL, 0, 1),
                                                                       (97, 'Lưu trữ dữ liệu tạm thời', NULL, 0, 1),
                                                                       (97, 'Không có vai trò trong mạng', NULL, 0, 1);

-- Q98: Topic 6 - Bài 1: Các mô hình mạng, Level: Diff
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Những giao thức thuộc tầng ứng dụng của mô hình TCP/IP là gì?', 6, 'Diff', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (98, 'HTTP, FTP, SMTP, DNS', NULL, 1, 1),
                                                                       (98, 'TCP, UDP', NULL, 0, 1),
                                                                       (98, 'IP, ICMP', NULL, 0, 1),
                                                                       (98, 'DHCP, ARP', NULL, 0, 1);

-- Q99: Topic 7 - Bài 2: Giao thức mạng, Level: Easy
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Vai trò của giao thức SNMP là gì?', 7, 'Easy', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (99, 'Giám sát và quản lý các thiết bị mạng', NULL, 1, 1),
                                                                       (99, 'Chuyển dữ liệu video', NULL, 0, 1),
                                                                       (99, 'Mã hóa dữ liệu', NULL, 0, 1),
                                                                       (99, 'Lưu trữ dữ liệu web', NULL, 0, 1);

-- Q100: Topic 8 - Tin học nâng cao, Level: Medium
INSERT INTO Question (qContent, qTopicID, qLevel, qStatus)
VALUES ('Ứng dụng của học máy trong doanh nghiệp là gì?', 8, 'Medium', 1);
INSERT INTO Answers (qID, awContent, awPictures, isRight, awStatus) VALUES
                                                                       (100, 'Dự báo xu hướng thị trường và tối ưu hóa quy trình kinh doanh', NULL, 1, 1),
                                                                       (100, 'Chỉ để lưu trữ dữ liệu', NULL, 0, 1),
                                                                       (100, 'Chỉ dùng cho mục đích giải trí', NULL, 0, 1),
                                                                       (100, 'Không có ứng dụng thực tiễn', NULL, 0, 1);
