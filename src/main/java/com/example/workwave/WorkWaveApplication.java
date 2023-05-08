package com.example.workwave;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@SpringBootApplication
@EnableScheduling

public class WorkWaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkWaveApplication.class, args);
    }

}
