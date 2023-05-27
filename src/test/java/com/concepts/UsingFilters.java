package com.concepts;

import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class UsingFilters {

    @Test

    //logging entire request and response using filters
    //https://github.com/rest-assured/rest-assured/wiki/Usage#filters

    public void logEntireRequestAndResponseOnConsole() {

        HashMap<String, String> payload = new HashMap<>();
        payload.put("name", "MadhuMeetha");


        Response response = given().baseUri(" ")
                .filter(new RequestLoggingFilter(LogDetail.ALL))
                .filter(new ResponseLoggingFilter(LogDetail.ALL))
                .basePath("/post")
                .body(payload)
                .contentType(ContentType.JSON)
                .header("somename", "abhishek")
                .log().all()
                .post();

        System.out.println("----------------------------------------------------------------");


    }

    @Test

    //logging respose body on error [status code >=400]
    //https://github.com/rest-assured/rest-assured/wiki/Usage#filters

    public void logResponseBodyOnError() {


        Response response = given().baseUri("https://httpbin.org/basic-auth/abhishek/bhardwaj")
                .filter(new ErrorLoggingFilter())
                .auth().basic("abhishek", "bhadwaj")
                .get();

        System.out.println("----------------------------------------------------------------");


    }

    @Test

    //logging entire request and response using filters on external files (.log extension)
    //https://github.com/rest-assured/rest-assured/wiki/Usage#filters

    public void logEntireRequestAndResponseOnExternalFile() throws FileNotFoundException {

        HashMap<String, String> payload = new HashMap<>();
        payload.put("name", "MadhuMeetha");

        PrintStream printStream1 = new PrintStream(new File(System.getProperty("user.dir") + "/MyRequest.log"));
        PrintStream printStream2 = new PrintStream(new File(System.getProperty("user.dir") + "/MyResponse.log"));


        Response response = given().baseUri("https://postman-echo.com")
                .filter(new RequestLoggingFilter(LogDetail.ALL,printStream1))
                .filter(new ResponseLoggingFilter(LogDetail.ALL,printStream2))
                .basePath("/post")
                .body(payload)
                .contentType(ContentType.JSON)
                .header("somename", "abhishek")
                .log().all()
                .post();

        System.out.println("----------------------------------------------------------------");


    }
}
