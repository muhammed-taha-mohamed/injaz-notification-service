package com.injaz_notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InjazNotificationApplication {

    public static void main(String[] args) {
         System.setProperty("server.servlet.context-path", "/notification");
        SpringApplication.run(InjazNotificationApplication.class, args);
    }

}
