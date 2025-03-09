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
    private boolean answerCorrect;
    private boolean answerStatus;
}
// Khi gọi Answers lên trên admin , sẽ gọi toàn bộ các thuộc tính
//Khi gọi Answers lên trên user bình thường , không gọi thuộc tính answerCorrect và answerStatus
