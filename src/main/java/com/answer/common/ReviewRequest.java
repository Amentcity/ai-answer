package com.answer.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ReviewRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -7639343283453683853L;
    /**
     * id
     */
    private Long id;

    /**
     * 状态 0-未审核 1-审核通过 2-审核不通过
     */
    private Integer reviewStatus;

    /**
     * 审核信息
     */
    private String reviewMessage;

}
