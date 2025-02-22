package com.answer.model.dto.question;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class AiGenerateQuestionRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -1906393195976933981L;
    /**
     * 应用id
     */
    private long appId;

    /**
     * 题目数
     */
    private int questionNumber = 10;

    /**
     * 选项数
     */
    private int optionNumber = 2;
}
