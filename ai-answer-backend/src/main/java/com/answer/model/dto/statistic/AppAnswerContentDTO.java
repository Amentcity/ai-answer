package com.answer.model.dto.statistic;

import lombok.Data;

/**
 * App 用户提交答案数统计
 */
@Data
public class AppAnswerContentDTO {

    private Long appId;

    /**
     * 用户提交答案数
     */
    private Long answerContent;
}
