package com.spring.cloud.dataflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.List;

@SpringBootApplication
@EnableBinding(Sink.class)
public class SinkApplication {

    Logger logger = LoggerFactory.getLogger(SinkApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SinkApplication.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void printUserFullName(List<String> userFullNameList) {
        logger.info("Received data from Processor");
        userFullNameList.forEach(userFullName -> {
            logger.info("User full name is : " + userFullName);
            // Here you can pass processed data to external system by hit apis.
        });
    }
}
