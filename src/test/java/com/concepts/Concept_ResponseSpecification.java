package com.concepts;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;

public class Concept_ResponseSpecification {

    private static RequestSpecification requestSpecification;
    private static ResponseSpecification responseSpecification;


    @BeforeClass

    public void commonRequestResponseSpecification() {

        //REQUESTSPECIFICATION

        requestSpecification = with().log().all()
                .baseUri("https://postman-echo.com")
                .basePath("/post")
                .contentType(ContentType.JSON);

        //RESPONSESPECIFICATION

        responseSpecification = expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .time(Matchers.lessThan(2000L));

        //RESPONSESPECIFICATION USING RESPONSESPECBUILER

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();

        responseSpecification = resBuilder
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(2000L))
                .build();

        //using static variable 'RestAssured.responseSpecification'

        RestAssured.responseSpecification = resBuilder
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(2000L))
                .build();


    }

    @Test

    public void understandResponseSpecification() {


        String jsonPayload1 = "{\n" +
                "  \"age\": 14,\n" +
                "  \"rollno\": 101,\n" +
                "  \"firstName\": \"Amuthan\",\n" +
                "  \"lastName\": \"Sakthivel\"\n" +
                "}";

        String jsonPayload2 = "{\n" +
                "  \"age\": 15,\n" +
                "  \"rollno\": 202,\n" +
                "  \"firstName\": \"Abhishek\",\n" +
                "  \"lastName\": \"Bhardwaj\"\n" +
                "}";

//        //api req. 1
//        given().spec(requestSpecification)
//                .header("someHeader", "abcd")
//                .body(jsonPayload1)
//                .post().then().assertThat()
//                .spec(responseSpecification);
//
//        //api req. 2
//        given().spec(requestSpecification)
//                .header("someHeader", "efgh")
//                .body(jsonPayload2)
//                .post().then().assertThat()
//                .spec(responseSpecification);

        //usage when static variable 'RestAssured.responseSpecification'
        // is used for ResponseSpecification creation.

        given().spec(requestSpecification)
                .header("someHeader", "efgh")
                .body(jsonPayload2)
                .post().then().assertThat()
                .spec(RestAssured.responseSpecification);


    }


}
