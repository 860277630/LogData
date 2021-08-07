package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.dao")//是mapper接口，而不是映射文件
public class Log4j2XmlApplication {

    public static void main(String[] args) {
        SpringApplication.run(Log4j2XmlApplication.class, args);
    }

}
