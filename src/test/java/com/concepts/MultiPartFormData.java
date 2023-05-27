package com.concepts;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MultiPartFormData {

//To demonstrate working with multi-part from data.
//Here demonstarted 'control Name' is 'text'.
//'Control Name' 'File' is demonstarted in Upload/Download files section.


    @Test

    public void multipartFormData_ControlName_Text() {

        Response response = given().log().all().
                baseUri("https://postman-echo.com").
                basePath("/post").
                multiPart("foo1", "bar1").
                multiPart("foo2", "bar2").
                post();

        System.out.println("-----------------------");

        response.prettyPrint();


    }


}
