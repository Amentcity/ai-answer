package com.answer.model.dto.statistic;

import lombok.Data;

/**
 * App 答案结果统计
 */
@Data
public class AppAnswerResultContentDTO {
    // 结果名称
    private String resultName;
    // 对应个数
    private String resultContent;
}
