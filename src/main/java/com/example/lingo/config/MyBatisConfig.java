package com.example.lingo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.lingo.mapper")
public class MyBatisConfig {
}
