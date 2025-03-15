package com.sgu.quanlytracnghiem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Logs {
    private int logsID;
    private int logUserID;
    private String logExamID;
    private String logContent;
    private LocalDateTime logTime;
}
