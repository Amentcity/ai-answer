package com.answer.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonConfig {

    private String host;

    private Integer port;

    private Integer database;

    private String password;

    @Bean
    public RedissonClient redissonClient() {
        log.info("已经选择的数据库信息{},{},{},{}",host,port,database,password);
        Config config=new Config();
        config.useSingleServer().setAddress("redis://"+host+ ":" + port ).setPassword(password).setDatabase(database);
        return Redisson.create();
    }

}
