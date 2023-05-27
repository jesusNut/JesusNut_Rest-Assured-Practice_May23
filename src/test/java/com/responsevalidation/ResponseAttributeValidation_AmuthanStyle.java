package com.responsevalidation;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class ResponseAttributeValidation_AmuthanStyle {

    //In Amuthan style/Non-inline, we DON'T do "<response>.then()" to convert 'Response' type object to 'ValidatableResponse' type Object.
    //In this style, we fetch all attributes directly from 'Response' type object and then write assertions using TestNG/AssertJ.


    @Test

    //print response
    //status code
    //status line
    //header
    //list of headers
    //response-time

    public void fetchInfoFromResponse() {

        Map<String, Object> pathParams = new HashMap<String, Object>();
        pathParams.put("repoType", "public");
        pathParams.put("version", "v1");
        pathParams.put("resource", "users");


        Response response = given().log().all().
                baseUri("https://gorest.co.in").
                basePath("/{repoType}/{version}/{resource}").
                pathParams(pathParams).
                queryParam("page", 2).
                contentType(ContentType.JSON).get();


        System.out.println("---------PRINT RESPONSE--------------");

        response.prettyPrint();

        System.out.println("----------ASSERTION ON STATUS CODE-------------");

        // System.out.println(response.statusCode());

        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

        System.out.println("----------ASSERTION ON STATUS LINE-------------");

        // System.out.println(response.statusLine());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");

        System.out.println("---------ASSERTION ON HEADER--------------");

        System.out.println("Header value for header 'Server' is :" + response.header("Server"));
        Assert.assertEquals(response.header("Server"), "cloudflare");

        System.out.println("-----------ASSERTION ON LIST OF HEADERS------------");

        List<Header> headerlist = response.getHeaders().asList();

        for (Header abc : headerlist) {

            System.out.println("Header is : " + abc.getName() + " and its value is : " + abc.getValue());
        }
        //TODO : write assertion here

        System.out.println("-----------ASSERTION ON RESPONSE TIME------------");

        // System.out.println("Response time in milliseconds is : "+response.getTime());
        System.out.println("Response time in seconds is : " + response.getTimeIn(TimeUnit.SECONDS));

        //TODO : write assertion here

    }


}
