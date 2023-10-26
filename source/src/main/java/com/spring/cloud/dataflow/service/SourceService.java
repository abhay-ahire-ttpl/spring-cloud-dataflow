package com.spring.cloud.dataflow.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.spring.cloud.dataflow.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class SourceService {


    Logger logger = LoggerFactory.getLogger(SourceService.class);

    public SourceService() {
    }

    public List<User> readCsvFile() {
        List<User> users = new ArrayList<>();
        try {
            ClassPathResource resource = new ClassPathResource("test_files/users.csv");
            InputStream inputStream = resource.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            // For demonstration purposes, data is read from local resources.
            // You can read data from an AWS S3 bucket for real-world scenarios.
            logger.info("Reading CSV file");
            CSVReader csvReader = new CSVReaderBuilder(inputStreamReader).withSkipLines(1).build();
            String[] record;

            while ((record = csvReader.readNext()) != null) {
                if (record.length == 3) {
                    User user = new User();
                    user.setFirstName(record[0]);
                    user.setMiddleName(record[1]);
                    user.setLastName(record[2]);
                    logger.debug("Read user: {}", user);
                    users.add(user);
                }
            }

            logger.info("CSV file read successfully");
            csvReader.close();

        } catch (IOException e) {
            logger.error("Error while reading CSV file : ", e);
            e.printStackTrace();
        } catch (CsvValidationException e) {
            logger.error("CSV validation error while reading file : ", e);
            throw new RuntimeException(e);
        }

        return users;
    }

}
