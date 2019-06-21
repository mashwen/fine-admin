package com.ant.shop.asauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.ant.shop.asorm.mapper")
public class AdminAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminAuthApplication.class, args);
	}

}
