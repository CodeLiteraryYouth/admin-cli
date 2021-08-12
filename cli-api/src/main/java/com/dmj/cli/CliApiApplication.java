package com.dmj.cli;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zd
 */
@SpringBootApplication
@MapperScan("com.dmj.cli.mapper")
public class CliApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CliApiApplication.class, args);
	}

}
