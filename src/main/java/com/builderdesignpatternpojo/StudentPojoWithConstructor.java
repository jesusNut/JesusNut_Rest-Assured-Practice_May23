package com.builderdesignpatternpojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class StudentPojoWithConstructor {

    private int rollno;
    private int age;
    private String firstName;
    private String lastName;
    private String email;



}
