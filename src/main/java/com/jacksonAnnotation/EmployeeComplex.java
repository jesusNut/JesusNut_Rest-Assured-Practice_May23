package com.jacksonAnnotation;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class EmployeeComplex {

    private int defaultDemo;
    private int id;
    private String first_name;
    private  String last_name;
    private  String email;
    private  List<String> job;
    private  List<Marks> marks;
    private  Favfood favfood;
    private Favfood favfood1;

    @JsonRawValue()
    private String address;
}
