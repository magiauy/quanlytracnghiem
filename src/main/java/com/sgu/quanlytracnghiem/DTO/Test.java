package com.sgu.quanlytracnghiem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    private int testID;
    private String testTitle;
    private int testTime;
    private int num_easy;
    private int num_medium;
    private int num_diff;
    private int testLimit;
    private LocalDate testDate;
    private boolean testStatus;
    private ArrayList<Topic> topics;

    public boolean getTestStatus() {
        return testStatus;
    }
}
