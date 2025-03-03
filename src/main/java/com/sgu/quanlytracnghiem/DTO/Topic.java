package com.sgu.quanlytracnghiem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    private int topicID;
    private String topicTitle;
    private int topicParentID;
    private boolean topicStatus;
}
