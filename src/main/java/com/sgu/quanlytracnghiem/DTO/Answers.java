package com.sgu.quanlytracnghiem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answers {
    private int answerID;
    private int questionID;
    private String answerContent;
    private String awPicture;
    private boolean isAnswerCorrect;
    private boolean answerStatus;

    // Trả về đối tượng chỉ chứa các thuộc tính cần thiết cho user
    public Answers getUserView() {
        return Answers.builder()
                .answerID(this.answerID)
                .questionID(this.questionID)
                .answerContent(this.answerContent)
                .awPicture(this.awPicture)
                .build();
    }
    public void setAnswerPictures(String answerPictures) {
        this.awPicture = answerPictures;
    }

    public void setIsRight(boolean isAnswerCorrect) {
        this.isAnswerCorrect = isAnswerCorrect;
    }

    public void setAnswerStatus(boolean answerStatus) {
        this.answerStatus = answerStatus;
    }

}
