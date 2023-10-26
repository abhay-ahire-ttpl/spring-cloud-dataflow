package com.spring.cloud.dataflow.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private String firstName;
    private String middleName;
    private String lastName;

}
