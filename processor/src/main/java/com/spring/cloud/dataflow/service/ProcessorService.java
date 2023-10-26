package com.spring.cloud.dataflow.service;

import com.spring.cloud.dataflow.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProcessorService {

    Logger logger = LoggerFactory.getLogger(ProcessorService.class);

    public List getUserFullNameList(List<User> users) {
        List<String> userFullNameList = new ArrayList<>();
        for (User user : users) {
            logger.info("Processing data for user whose first name is : {}", user.getFirstName());
            userFullNameList.add(user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());
        }
        return userFullNameList;
    }
}
