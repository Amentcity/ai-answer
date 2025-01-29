package com.answer.service.impl;

import com.answer.service.AppService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.App;
import generator.mapper.AppMapper;
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




