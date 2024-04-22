package com.test.quota;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuotaManagementStarter {

    private static Logger logger = Logger.getLogger(QuotaManagementStarter.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(QuotaManagementStarter.class, args);
    }
}
