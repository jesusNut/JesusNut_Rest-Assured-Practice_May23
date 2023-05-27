package com.builderdesignpatternpojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class StudentPojoClassForBuilder {

    private int rollno;
    private int age;
    private String firstName;
    private String lastName;
    private String email;
}
