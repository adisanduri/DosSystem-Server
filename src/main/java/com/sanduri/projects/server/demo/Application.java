package com.sanduri.projects.server.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static int nClients = 10;
    public static int maxConnectionsPerClient =5;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
