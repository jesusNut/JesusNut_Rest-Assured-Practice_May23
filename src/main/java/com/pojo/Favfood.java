package com.pojo;

import java.util.List;

public class Favfood {

    private final String breakfast;
    private final String lunch;
    private final List<String> dinner;

    public Favfood(String breakfast, String lunch, List<String> dinner) {
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public String getBreakfast() {
        return breakfast;
    }


    public String getLunch() {
        return lunch;
    }


    public List<String> getDinner() {
        return dinner;
    }

}
