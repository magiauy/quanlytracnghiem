package com.sgu.quanlytracnghiem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Result {
    private int resultID;
    private String examID;
    private int userID;
    private ArrayList<Answers> answers;
    private BigDecimal resultScore;
    private LocalDate resultDate;
}

//Khi User bắt đầu làm bài , khởi tạo Array Answers số lượng bằng các câu hỏi của bài thi và giá trị mặc định là null
