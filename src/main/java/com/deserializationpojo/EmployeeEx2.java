package com.deserializationpojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmployeeEx2 {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private List<SkillsEx2> skills;




}
