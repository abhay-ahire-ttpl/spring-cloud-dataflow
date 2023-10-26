package com.spring.cloud.dataflow;

import com.spring.cloud.dataflow.dto.User;
import com.spring.cloud.dataflow.service.ProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

import java.util.List;

@SpringBootApplication
@EnableBinding(Processor.class)
public class ProcessorApplication {

    private final ProcessorService processorService;

    Logger logger = LoggerFactory.getLogger(ProcessorApplication.class);

    public ProcessorApplication(ProcessorService processorService) {

        this.processorService = processorService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProcessorApplication.class, args);
    }

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public List<String> processUserDetails(List<User> users) {
        logger.info("Received data from Source");
        return processorService.getUserFullNameList(users);
    }

}
