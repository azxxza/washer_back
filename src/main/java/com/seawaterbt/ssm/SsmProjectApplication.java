package com.seawaterbt.ssm;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan(basePackages = {"com.seawaterbt.ssm.module.*.dao"}) //扫描DAO
@Slf4j
public class SsmProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsmProjectApplication.class, args);
        log.info("SsmProjectApplication Start Success");
    }

}
