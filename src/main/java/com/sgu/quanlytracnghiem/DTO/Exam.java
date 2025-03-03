package com.sgu.quanlytracnghiem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    private int examID;
    private int testID;
    private String examOrder;
    private ArrayList<Question> questions;
}
