package com.dmj.cli;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zd
 */
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.dmj.cli.mapper")
public class CliAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(CliAdminApplication.class, args);
	}

}
