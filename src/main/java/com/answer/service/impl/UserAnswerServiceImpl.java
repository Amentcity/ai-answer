package com.answer.service.impl;

import com.answer.service.UserAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.UserAnswer;
import generator.mapper.UserAnswerMapper;
import org.springframework.stereotype.Service;

/**
* @author Amentcity
* @description 针对表【user_answer(用户答题记录)】的数据库操作Service实现
* @createDate 2025-01-29 18:20:26
*/
@Service
public class UserAnswerServiceImpl extends ServiceImpl<UserAnswerMapper, UserAnswer>
    implements UserAnswerService {

}




