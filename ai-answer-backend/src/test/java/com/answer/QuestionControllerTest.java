package com.answer;

import com.answer.controller.QuestionController;
import com.answer.model.dto.question.AiGenerateQuestionRequest;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionControllerTest {

    @Resource
    private QuestionController questionController;

    @Test
    public void aiGenerateQuestionSSETest() throws InterruptedException {
        // 模拟调用
        AiGenerateQuestionRequest aiGenerateQuestionRequest = new AiGenerateQuestionRequest();
        aiGenerateQuestionRequest.setAppId(1);
        aiGenerateQuestionRequest.setQuestionNumber(10);
        aiGenerateQuestionRequest.setOptionNumber(2);

        // 模拟普通用户
        questionController.aiGenerateQuestionSSETest(aiGenerateQuestionRequest,false);
        // 模拟普通用户
        questionController.aiGenerateQuestionSSETest(aiGenerateQuestionRequest,false);
        // 模拟会员用户
        questionController.aiGenerateQuestionSSETest(aiGenerateQuestionRequest,true);

        // 模拟主线程一直启动
        Thread.sleep(1000000L);
    }

}
