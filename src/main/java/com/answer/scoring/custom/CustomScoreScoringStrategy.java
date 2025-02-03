package com.answer.scoring.custom;

import com.answer.model.dto.question.QuestionContentDTO;
import com.answer.model.entity.App;
import com.answer.model.entity.Question;
import com.answer.model.entity.ScoringResult;
import com.answer.model.entity.UserAnswer;
import com.answer.model.vo.QuestionVO;
import com.answer.scoring.ScoringStrategy;
import com.answer.service.QuestionService;
import com.answer.service.ScoringResultService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * 自定义测评类应用评分策略
 */
@ScoringStrategyConfig(appType = 0, scoringStrategy = 0)
@Service
public class CustomScoreScoringStrategy implements ScoringStrategy {

    @Resource
    private QuestionService questionService;

    @Resource
    private ScoringResultService scoringResultService;

    @Override
    public UserAnswer doScore(List<String> choices, App app) throws Exception {
        // 1. 根据id查询到题目和题目结果信息（按分数降序排序）
        Long appId = app.getId();
        Question question = questionService.getOne(Wrappers.lambdaQuery(Question.class).eq(Question::getAppId, appId));
        List<ScoringResult> scoringResultList = scoringResultService.list(
                Wrappers.lambdaQuery(ScoringResult.class)
                        .eq(ScoringResult::getAppId, appId)
                        .orderByDesc(ScoringResult::getResultScoreRange)
        );
        // 2. 统计用户的总得分
        int totalScore = 0;
        HashMap<String, Integer> optionCount = new HashMap<>();
        QuestionVO questionVO = QuestionVO.objToVo(question);
        List<QuestionContentDTO> questionContent = questionVO.getQuestionContent();
        // 遍历题目列表
        for (QuestionContentDTO questionContentDTO : questionContent) {
            // 遍历答案列表
            for (String answer : choices) {
                // 遍历题目中选项
                for (QuestionContentDTO.Option option : questionContentDTO.getOptions()) {
                    // 如果答案和选项的key相匹配
                    if (answer.equals(option.getKey())) {
                        Integer score = Optional.of(option.getScore()).orElse(0);
                        totalScore += score;
                    }
                }
            }
        }
        // 3. 遍历得分结果，找到第一个用户分数大于得分范围的结果，作为最终结果
        ScoringResult maxScoringResult = scoringResultList.get(0);
        for (ScoringResult scoringResult : scoringResultList) {
            if (totalScore>=scoringResult.getResultScoreRange()) {
                maxScoringResult = scoringResult;
                break;
            }
        }
        // 4. 构建返回值，填充答案对象的属性
        UserAnswer userAnswer = new UserAnswer();
        BeanUtils.copyProperties(question, userAnswer);
        userAnswer.setResultScore(totalScore);
        return userAnswer;
    }
}
