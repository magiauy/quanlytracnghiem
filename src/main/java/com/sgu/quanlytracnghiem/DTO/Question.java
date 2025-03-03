package com.sgu.quanlytracnghiem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private int questionID;
    private String questionContent;
    private String questionPicture;
    private int topicID;
    private QuestionLevel questionLevel;
    private boolean questionStatus;
}
