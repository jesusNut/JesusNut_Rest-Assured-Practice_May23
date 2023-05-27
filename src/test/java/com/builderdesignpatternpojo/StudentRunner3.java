package com.builderdesignpatternpojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StudentRunner3 {

    @Test

    public void samplePost() {

        // create payload using BUILDER DESIGN PATTERN using Lombok @Builder annotation
        // Here the builder class will be created using LOMBOK @Builder annotation
        // (which internally uses Static Inner Class Concept)
        // No external Builder Class will be created.
        // simply use '@Builder' annotation from lombok in actual Pojo class 'StudentPojoClassWithLombokBuilder.java'
        //  from src->main->java->com.builderdesignpatternpojo

        StudentPojoClassWithLombokBuilder student = StudentPojoClassWithLombokBuilder
                .getInstance()
                .setRollno(22)
                .setAge(99)
                //  .setFirstName("Kerala")
                // .setLastName("Story")
                .setEmail("abc.def@xyz.com")
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
