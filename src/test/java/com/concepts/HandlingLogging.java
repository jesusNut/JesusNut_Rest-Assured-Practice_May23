package com.concepts;

import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class HandlingLogging {

    @Test

    //logging entire request and response

    public void logEntireRequestAndResponse() {

        HashMap<String, String> payload = new HashMap<>();
        payload.put("name", "MadhuMeetha");

        //using 'log().all()' with API request to log entire request.

        Response response = given().baseUri("https://postman-echo.com")
                .basePath("/post")
                .body(payload)
                .contentType(ContentType.JSON)
                .header("somename", "abhishek")
                .log().all()
                .post();

        System.out.println("----------------------------------------------------------------");

        //to print entire response - prints only body

        //response.prettyPrint();

        //logging entire response - prints along with status code, status line, response headers etc.

        ValidatableResponse validatableResponse = response.then();

        validatableResponse.log().all();


    }

    @Test

    //logging certain properties like body, status, header etc. of request and response

    public void logPartialRequestAndResponse() {

        HashMap<String, String> payload = new HashMap<>();
        payload.put("name", "MadhuMeetha");

        //log only Headers in the request.

        Response response = given().baseUri("https://postman-echo.com")
                .basePath("/post")
                .body(payload)
                .contentType(ContentType.JSON)
                .header("somename", "abhishek")
                .log().headers()
                .post();

        System.out.println("----------------------------------------------------------------");

        //log only Cookies in the request.

        ValidatableResponse validatableResponse = response.then();

        validatableResponse.log().cookies();


    }

    @Test

    // logging  response only if there is some error
    // not applicable for request logging

    public void logResponseIfError() {

        Response response = given().baseUri("https://httpbin.org/basic-auth/abhishek/bhardwaj")
                .log().all()
                .auth().basic("abhishek", "bhardwaj")
                .get();

        System.out.println("----------------------------------------------------------------");

        //log only Cookies in the request.

        ValidatableResponse validatableResponse = response.then();

        validatableResponse.log().ifError();

    }

    @Test

    //logging  request and response only if there is some validation failure

    public void logResponseIfValidationFails() {

        //if validation fails in response, then, log only request header Otherwise don't log anything in request.

        Response response = given().baseUri("https://httpbin.org/basic-auth/abhishek/bhardwaj")
                .log().ifValidationFails(LogDetail.ALL, true)
                .auth().basic("abhishek", "bharwaj")
                .get();

        System.out.println("----------------------------------------------------------------");

        //if validation fails in response, then, log entire response  Otherwise don't log anything in response.

        ValidatableResponse validatableResponse = response.then();

        validatableResponse.log().ifValidationFails(LogDetail.ALL,true)
                .assertThat().statusCode(200);

    }

    @Test

    //blacklist (restrict) sensitive header value from prinitng to console

    public void blacklistHeaders() {

        Response response = given().baseUri("https://httpbin.org/basic-auth/abhishek/bhardwaj")
                .header("Authorization","Basic YWJoaXNoZWs6YmhhcmR3YWo=")
                .config(config.logConfig(LogConfig.logConfig().blacklistHeader("Authorization")))
                .log().all()
                .get();

        System.out.println("----------------------------------------------------------------");

        //log only Cookies in the request.

        ValidatableResponse validatableResponse = response.then();

        validatableResponse.log().all();


    }

    @Test

    //blacklist (restrict) multiple sensitive header values from prinitng to console

    public void blacklistMultipleHeaders() {

        Response response = given().baseUri("https://httpbin.org/basic-auth/abhishek/bhardwaj")
                .header("Authorization","Basic YWJoaXNoZWs6YmhhcmR3YWo=")
                .header("DummyHeader","1234")
                .config(config.logConfig(LogConfig.logConfig().blacklistHeaders(Arrays.asList("Authorization","DummyHeader"))))
                .log().all()
                .get();

        System.out.println("----------------------------------------------------------------");

        //log only Cookies in the request.

        ValidatableResponse validatableResponse = response.then();

        validatableResponse.log().all();
    }

    @Test

    //log and print to an external file

    public void logResponseBodyToExternalFile() throws IOException {

        Response response = given().baseUri("https://httpbin.org/basic-auth/abhishek/bhardwaj")
                .log().ifValidationFails(LogDetail.ALL, true)
                .auth().basic("abhishek", "bhardwaj")
                .get();

        System.out.println("----------------------------------------------------------------");

        //log response body to file name "responsebody.json"

        Files.write(Paths.get(System.getProperty("user.dir")+"/responsebody.json"), response.asByteArray());

    }

}
