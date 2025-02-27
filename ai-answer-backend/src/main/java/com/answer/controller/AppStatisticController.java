package com.answer.controller;

import com.answer.common.BaseResponse;
import com.answer.common.ErrorCode;
import com.answer.common.ResultUtils;
import com.answer.exception.ThrowUtils;
import com.answer.mapper.UserAnswerMapper;
import com.answer.model.dto.statistic.AppAnswerContentDTO;
import com.answer.model.dto.statistic.AppAnswerResultContentDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/statistic")
@Slf4j
public class AppStatisticController {

    @Resource
    private UserAnswerMapper userAnswerMapper;

    /**
     * 热门应用及回答数统计
     * @return
     */
    @GetMapping("answer_count")
    public BaseResponse<List<AppAnswerContentDTO>> getAppAnswerCount() {
        return ResultUtils.success(userAnswerMapper.doAppAnswerCount());
    }

    /**
     * 某个应用回答结果分布统计
     * @param appId
     * @return
     */
    @GetMapping("answer_result_count")
    public BaseResponse<List<AppAnswerResultContentDTO>> getAppAnswerResultCount(Long appId) {
        ThrowUtils.throwIf(appId==null||appId<=0, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(userAnswerMapper.doAppAnswerResultCount(appId));
    }


}
