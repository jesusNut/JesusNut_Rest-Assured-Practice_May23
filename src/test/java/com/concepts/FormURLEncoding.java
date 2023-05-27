package com.concepts;

import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class FormURLEncoding {

//To demonstrate working with FormURLEncoded Payload.
//Here demonstarted 'control Name' is 'text'.
//'Control Name' 'File' is demonstarted in Upload/Download files section.


    @Test

    public void formUrlEncodedRequestPayload() {

        Response response = given().log().all().
                baseUri("https://postman-echo.com").
                basePath("/post").
                config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                header("Content-Type", "application/x-www-form-urlencoded").
                formParams("star", "war movie", "seagal","worst movies").
                post();

        System.out.println("-----------------------");

        response.prettyPrint();


    }


}
