package com.exercise.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})

public class WeatherApplication {
    static final Logger log = LoggerFactory.getLogger(WeatherApplication.class);

    public static void main(String[] args) {
//        log.trace("trace");
//        log.debug("debug");
//        log.info("info");
//        log.warn("warn");
//        log.error("error");
        SpringApplication.run(WeatherApplication.class, args);
    }

}

//   h h h h h h