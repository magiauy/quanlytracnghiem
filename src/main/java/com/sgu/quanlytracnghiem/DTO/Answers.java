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


    @Override
    public String toString() {
        return String.valueOf(answerID);
    }

// Khi gọi Answers lên trên admin , sẽ gọi toàn bộ các thuộc tính
//Khi gọi Answers lên trên user bình thường , không gọi thuộc tính answerCorrect và answerStatus
    // Trả về đối tượng chỉ chứa các thuộc tính cần thiết cho user
    public Answers getUserView() {
        return Answers.builder()
                .answerID(this.answerID)
                .questionID(this.questionID)
                .answerContent(this.answerContent)
                .awPicture(this.awPicture)
                .build();
    }

}
