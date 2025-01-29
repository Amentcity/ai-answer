package com.answer.service.impl;

import com.answer.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Question;
import generator.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author Amentcity
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2025-01-29 18:20:26
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService {

}




