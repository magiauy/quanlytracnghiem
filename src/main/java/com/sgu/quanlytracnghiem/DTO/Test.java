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
    private int testStatus;
    private ArrayList<Topic> topics;


    public Test (Test test) {
        this.testID = test.getTestID();
        this.testCode = test.getTestCode();
        this.testTitle = test.getTestTitle();
        this.testTime = test.getTestTime();
        this.testLimit = test.getTestLimit();
        this.testDate = test.getTestDate();
        this.testStatus = test.getTestStatus();
        this.topics = test.getTopics();

    }
}
