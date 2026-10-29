package com.studio.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动入口。
 */
@SpringBootApplication
@MapperScan("com.studio.data.mapper")   // 扫描 Mapper 接口，交给 MyBatis 生成实现
public class DataWorkshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataWorkshopApplication.class, args);
    }
}
