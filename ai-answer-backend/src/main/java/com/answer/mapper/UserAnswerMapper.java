package com.answer.mapper;

import com.answer.model.dto.statistic.AppAnswerContentDTO;
import com.answer.model.dto.statistic.AppAnswerResultContentDTO;
import com.answer.model.entity.UserAnswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Amentcity
 * @description 针对表【user_answer(用户答题记录)】的数据库操作Mapper
 * @createDate 2025-01-29 18:20:26
 * @Entitycom.answer.model.entity.UserAnswer
 */
public interface UserAnswerMapper extends BaseMapper<UserAnswer> {

    @Select("select app_id, count(user_id) as answer_content from user_answer group by" +
            " app_id order by answer_content desc limit 10;")
    List<AppAnswerContentDTO> doAppAnswerCount();

    @Select("select result_name,count(result_name) as result_content from user_answer " +
            "where app_id = #{app_id} group by result_name order by result_content desc;")
    List<AppAnswerResultContentDTO> doAppAnswerResultCount(Long appId);

}




