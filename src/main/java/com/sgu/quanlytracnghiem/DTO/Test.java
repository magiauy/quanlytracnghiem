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
    private String testCode;
    private String testTitle;
    private int testTime;
    private int testLimit;
    private LocalDate testDate;
    private boolean testStatus;
    private ArrayList<Topic> topics;

    public boolean getTestStatus() {
        return testStatus;
    }
}
