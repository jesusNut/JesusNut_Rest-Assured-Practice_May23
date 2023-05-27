package com.builderdesignpatternpojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StudentRunner1 {

    @Test

    public void samplePost() {

        //create payload using 'StudentPojoWithConstructor.java' class from src->main->java->com.builderdesignpatternpojo

        StudentPojoWithConstructor student1 =
                new StudentPojoWithConstructor(101, 99, "martin", "luther", "abc.def@xyz.co.in");



        Response response = given().log().all()
                .baseUri("https://postman-echo.com")
                .contentType(ContentType.JSON)
                .body(student1)
                .post("/post");

        System.out.println("--------------------------------");

        response.prettyPrint();


    }


}
