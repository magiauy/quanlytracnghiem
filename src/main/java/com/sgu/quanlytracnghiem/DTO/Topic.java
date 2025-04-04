package com.sgu.quanlytracnghiem.DTO;

import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import javafx.beans.binding.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Topic {
    private int topicID;
    private String topicTitle;
    private int topicParentID;
    private boolean topicStatus;

    public Topic (int topicID, String topicTitle, int topicParentID, boolean topicStatus) {
        this.topicID = topicID;
        this.topicTitle = topicTitle;
        this.topicParentID = topicParentID;
        this.topicStatus = topicStatus;
    }

    public boolean getTopicStatus() {
        return topicStatus;
    }

    //Sub element
    private int num_easy;
    private int num_medium;
    private int num_diff;

    public int totalNumProperty() {
        return num_diff+num_easy+num_medium ;
    }

}
