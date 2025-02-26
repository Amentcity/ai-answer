package com.answer;

import cn.hutool.json.JSONUtil;
import com.answer.model.entity.UserAnswer;
import com.answer.service.UserAnswerService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserAnswerShardingTest {

    @Resource
    private UserAnswerService userAnswerService;

    @Test
    void test() {
        UserAnswer userAnswer1 = new UserAnswer();
        userAnswer1.setAppId(1L);
        userAnswer1.setUserId(1L);
        userAnswer1.setChoices("1");
        userAnswerService.save(userAnswer1);

        UserAnswer userAnswer2 = new UserAnswer();
        userAnswer2.setAppId(2L);
        userAnswer2.setUserId(1L);
        userAnswer2.setChoices("2");
        userAnswerService.save(userAnswer2);

        List<UserAnswer> userAnswerOne = userAnswerService.list(Wrappers.lambdaQuery(UserAnswer.class).eq(UserAnswer::getAppId, 1L));
        System.out.println(JSONUtil.toJsonStr(userAnswerOne));

        List<UserAnswer> userAnswerTwo = userAnswerService.list(Wrappers.lambdaQuery(UserAnswer.class).eq(UserAnswer::getAppId, 2L));
        System.out.println(JSONUtil.toJsonStr(userAnswerTwo));
    }
}
