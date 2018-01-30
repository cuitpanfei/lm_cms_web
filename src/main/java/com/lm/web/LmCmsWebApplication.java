package com.lm.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ServletComponentScan
@ImportResource({"classpath:conf/redisConfig.xml"})
public class LmCmsWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmCmsWebApplication.class, args);
	}
}
