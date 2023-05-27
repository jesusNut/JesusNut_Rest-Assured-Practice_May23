package com.builderdesignpatternpojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StudentRunner2 {

    @Test

    public void samplePost() {

        //create payload using BUILDER DESIGN PATTERN classes from src->main->java->com.builderdesignpatternpojo
        //classes used : 'StudentBuilderClass' aka builder class & 'StudentPojoClassForBuilder' aka actual POJO class

        StudentPojoClassForBuilder student = StudentBuilderClass.getBuilder().setAge(22).and()
               // .setRollno(111).and()
                .setFirstName("Abhishek").and()
                //.setLastName("bhardwaj").and()
               // .setEmail("abc.def@xyz.com")
                .getStudent();


        Response response = given().log().all()
                .baseUri("https://postman-echo.com")
                .contentType(ContentType.JSON)
                .body(student)
                .post("/post");

        System.out.println("--------------------------------");

        response.prettyPrint();


    }



}
