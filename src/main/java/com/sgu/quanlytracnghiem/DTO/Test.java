package com.sgu.quanlytracnghiem.DTO;

import java.time.LocalDate;
import java.util.ArrayList;

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

}
