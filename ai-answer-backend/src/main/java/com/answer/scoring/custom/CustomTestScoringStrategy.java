package com.answer.scoring.custom;

import cn.hutool.json.JSONUtil;
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

@ScoringStrategyConfig(appType = 1, scoringStrategy = 0)
@Service
public class CustomTestScoringStrategy implements ScoringStrategy {

    @Resource
    private QuestionService questionService;

    @Resource
    private ScoringResultService scoringResultService;

    @Override
    public UserAnswer doScore(List<String> choices, App app) throws Exception {
        // 1、根据id查询题目和题目结果信息
        Long appId = app.getId();
        Question question = questionService.getOne(Wrappers.lambdaQuery(Question.class).eq(Question::getAppId, appId));
        List<ScoringResult> scoringResults = scoringResultService.list(
                Wrappers.lambdaQuery(ScoringResult.class).eq(ScoringResult::getAppId, appId)
        );
        // 2、统计用户每个选择对应的属性个数, 如：I = 10 个，E = 5 个
        // 初始化一个Map用于存储,用于存储每个选项的计数
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
                        String result = option.getResult();
                        // 如果result属性不在optionContent中，初始化为0
                        if (!optionCount.containsKey(result)) {
                            optionCount.put(result, 0);
                        }
                        // 在optionCount中增加计数
                        optionCount.put(result, optionCount.get(result) + 1);
                    }
                }
            }
        }
        // 3、遍历每种评分结果，计算哪个结果的得分最高
        // 初始化最高分数和最高分数对应的结果
        int maxScore = 0;
        ScoringResult maxScoreResult = scoringResults.get(0);
        // 遍历评分结果列表
        for (ScoringResult scoringResult : scoringResults) {
            List<String> resultProp = JSONUtil.toList(scoringResult.getResultProp(), String.class);
            // 计算当前评分结果的得分
            int source = resultProp.stream().mapToInt(prop -> optionCount.getOrDefault(prop, 0)).sum();
            // 如果当前得分高于最高得分，更新最高得分和最高分数对应的结果
            if (source > maxScore) {
                maxScore = source;
                maxScoreResult = scoringResult;
            }
        }
        // 4、构造返回值，填充答案对象的属性
        UserAnswer userAnswer = new UserAnswer();
        // TODO 这里需要根据业务需求填充其他属性。覆盖或新增
        BeanUtils.copyProperties(maxScoreResult, userAnswer);
        return userAnswer;
    }
}
