package com.deserializationpojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserData {

    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

}
