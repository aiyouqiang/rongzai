package com.qf.springbootdemo3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.qf.springbootdemo3.mapper")
public class Springbootdemo3Application {

    public static void main(String[] args) {

        SpringApplication.run(Springbootdemo3Application.class, args);
    }

}
