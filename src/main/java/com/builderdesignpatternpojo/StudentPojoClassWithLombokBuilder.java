package com.builderdesignpatternpojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@Builder(setterPrefix = "set", builderMethodName = "getInstance",buildMethodName = "getStudent")
public class StudentPojoClassWithLombokBuilder {

    private int rollno;
    private int age;
    private String firstName;
    private String lastName;
    private String email;
}
