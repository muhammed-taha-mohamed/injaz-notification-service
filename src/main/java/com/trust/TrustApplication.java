package com.trust;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class TrustApplication {

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/trust");
        SpringApplication.run(TrustApplication.class, args);
    }


}
