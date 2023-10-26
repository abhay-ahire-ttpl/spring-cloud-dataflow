package com.spring.cloud.dataflow.config;

import com.spring.cloud.dataflow.dto.User;
import com.spring.cloud.dataflow.service.SourceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.MessageBuilder;

import java.io.IOException;
import java.util.List;


@Slf4j
@AllArgsConstructor
@Configuration
public class SourceConfig {

    private final SourceService sourceService;

    Logger logger = LoggerFactory.getLogger(SourceService.class);

    @Autowired
    public SourceConfig(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
    public MessageSource<List<User>> sendSource() throws IOException {
        logger.info("Executing source...");
        List<User> users = sourceService.readCsvFile();
        logger.info("Users : {}", users);
        return () -> MessageBuilder.withPayload(users).build();
    }

}
