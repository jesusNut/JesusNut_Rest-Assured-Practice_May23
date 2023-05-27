package com.concepts;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.apache.http.client.methods.RequestBuilder;
import org.mozilla.javascript.commonjs.module.RequireBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Concept_RequestSpecification {

    private static RequestSpecification requestSpecification;

    @BeforeClass

    public void commonRequestSpecification() {

        //Way 1 of creating RequestSepcification

        //The only difference between with() and given() is syntactical
        //Instead given() can also be used.

        requestSpecification = with().log().all()
                .baseUri("https://postman-echo.com")
                .basePath("/post")
                .contentType(ContentType.JSON);

        //Way 2 of creating RequestSepcification using RequestBuilder

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();

        requestSpecification = reqBuilder.log(LogDetail.ALL)
                .setBaseUri("https://postman-echo.com")
                .setBasePath("/post")
                .setContentType(ContentType.JSON)
                .build();

        //using static variable 'RestAssured.requestSpecification'

        RestAssured.requestSpecification = with().log().all()
                .baseUri("https://postman-echo.com")
                .basePath("/post")
                .contentType(ContentType.JSON);
    }

    @Test

    public void understandRequestSpecification() {


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

//        //way 1:
//
//        requestSpecification
//                .header("someHeader", "abcd")
//                .body(jsonPayload1)
//                .post();
//
//        requestSpecification
//                .header("someHeader", "efgh")
//                .body(jsonPayload2)
//                .post();
//
//        //way 2:
//
//        given(requestSpecification)
//                .header("someHeader", "abcd")
//                .body(jsonPayload1)
//                .post();
//
//        given(requestSpecification)
//                .header("someHeader", "efgh")
//                .body(jsonPayload2)
//                .post();

        //way 3:

        given().spec(requestSpecification)
                .header("someHeader", "abcd")
                .body(jsonPayload1)
                .post();

        given().spec(requestSpecification)
                .header("someHeader", "efgh")
                .body(jsonPayload2)
                .post();

        //usage (shown with only one api req. from above) when static variable 'RestAssured.requestSpecification'
        // is used for RequestSpecification creation.

        given().spec(RestAssured.requestSpecification)
                .header("someHeader", "efgh")
                .body(jsonPayload2)
                .post();

        //OR

        given(RestAssured.requestSpecification)
                .header("someHeader", "efgh")
                .body(jsonPayload2)
                .post();


    }

    @Test

    public void queryRequestSpec(){

//in this example we will query about some of the properties of 'requestSpecification' variable which refers to a
// RequestSpecification , we created in @BaseClass annotated method.
// same concept can be used to query static method provided by RestAssured as well i.e. 'RestAssured.requestSpecification'


        QueryableRequestSpecification query = SpecificationQuerier
                                               .query(requestSpecification);
        System.out.println(query.getBaseUri());
        System.out.println(query.getBasePath());
        System.out.println(query.getContentType());

    }

}
