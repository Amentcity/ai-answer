package com.answer.model.dto.question;

import lombok.Data;

@Data
public class QuestionAnswerDTO {
    /**
     * 题目
     */
    private String title;

    /**
     * 用户回答
     */
    private String userAnswer;
}
