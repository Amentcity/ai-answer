package com.answer.service.impl;

import com.answer.mapper.AppMapper;
import com.answer.service.AppService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.answer.model.entity.App;
import org.springframework.stereotype.Service;

/**
* @author Amentcity
* @description 针对表【app(应用)】的数据库操作Service实现
* @createDate 2025-01-29 18:20:26
*/
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App>
    implements AppService {

}




