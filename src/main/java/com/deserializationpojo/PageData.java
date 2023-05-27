package com.deserializationpojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PageData

{
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<UserData> data;
    private Support support;

}
