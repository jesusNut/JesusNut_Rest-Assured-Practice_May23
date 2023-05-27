package com.concepts;

import io.restassured.config.HeaderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HandlingHeaders {

    @Test

//adding multiple headers in different lines

    public void requestHeadersExample1() {

        Response response = given().log().all().
                baseUri("https://postman-echo.com").
                basePath("/{resource}").
                pathParam("resource", "headers").
                header("Cache-Control", "no-cache").
                header("Accept", "*/*").
                header("Accept-Encoding", "gzip, deflate, br").
                header("Connection", "keep-alive").
                header("my-sample-header", "Lorem ipsum dolor sit amet").
                header("my-sample-header1", "I love my Fish").
                get();

        System.out.println("-----------------------");

        response.prettyPrint();


    }

    @Test

//adding multiple headers in single line

    public void requestHeadersExample2() {

        Response response = given().log().all().
                baseUri("https://postman-echo.com").
                basePath("/{resource}").
                pathParam("resource", "headers").
                headers("my-sample-header", "Lorem ipsum dolor sit amet", "my-sample-header1", "jeene ke hain chaar din").
                get();

        System.out.println("-----------------------");

        response.prettyPrint();


    }

    @Test

//adding multiple headers using Map

    public void requestHeadersUsingMap() {

        Map<String, Object> headers = new LinkedHashMap<>();
        headers.put("my-sample-header", "Lorem Ipsum");
        headers.put("my-sample-header1", "Motu");
        headers.put("my-sample-header2", "Patlu");

        Response response = given().log().all().
                baseUri("https://postman-echo.com").
                basePath("/{resource}").
                pathParam("resource", "headers").
                headers(headers).
                get();

        System.out.println("-----------------------");

        response.prettyPrint();


    }

    @Test

//adding multiple headers using io.restassured.http.Header class AND
//io.restassured.http.Headers;

    public void requestHeadersUsingHeaderClass() {

        Header myheader1 = new Header("my-sample-header1", "Lorem Ipsum11");
        Header myheader2 = new Header("my-sample-header2", "Lorem Ipsum22");
        Header myheader3 = new Header("my-sample-header3", "Lorem Ipsum33");
        Header myheader4 = new Header("my-sample-header4", "Lorem Ipsum44");
        // Headers myAllHeaders = new Headers(myheader1,myheader2,myheader3,myheader4);
        Headers myAllHeaders = new Headers(Arrays.asList(myheader1, myheader2, myheader3, myheader4));


        Response response = given().log().all().
                baseUri("https://postman-echo.com").
                basePath("/{resource}").
                pathParam("resource", "headers").
                headers(myAllHeaders).
                get();

        System.out.println("-----------------------");

        response.prettyPrint();


    }

    @Test

    //Default behaviour when sending 2 headers with same 'key' but different values.
    //e.g. : two headers ->both have keys as 'same-sample-header' && both having different values 'VaLuE1' & 'VaLuE2'
    // Rest Assured; as default behaviour; merges the values in this case (makes multi-value header).
    //so result sent to the server along with API request will be : "same-sample-header": "VaLuE1, VaLuE2"
    //which is effctively equal to sending header 'same-sample-header' twice with 2 diff. values : "same-sample-header=VaLuE1" && same-sample-header=VaLuE2"
    //Note that by default all headers are merged except the "accept" and "content-type" headers.

    public void requestHeadersScenario1() {

        Header myheader1 = new Header("same-sample-header", "VaLuE1");
        Header myheader2 = new Header("same-sample-header", "VaLuE2");

         Headers myAllHeaders = new Headers(myheader1,myheader2);
        //Headers myAllHeaders = new Headers(Arrays.asList(myheader1, myheader2));


        Response response = given().log().all().
                baseUri("https://postman-echo.com").
                basePath("/{resource}").
                pathParam("resource", "headers").
                headers(myAllHeaders).
                get();

        System.out.println("-----------------------");

        response.prettyPrint();


    }

    @Test

    //Default behaviour when sending 2 headers with same 'key' but different values.
    //e.g. : two headers ->both have keys as 'same-sample-header' && both having different values 'VaLuE1' & 'VaLuE2'
    // Rest Assured; as default behaviour; merges the values in this case (makes multi-value header).
    //so result sent to the server along with API request will be : "same-sample-header": "VaLuE1, VaLuE2"
    //which is effctively equal to sending header 'same-sample-header' twice with 2 diff. values : "same-sample-header=VaLuE1" && same-sample-header=VaLuE2"
    //HERE, WE WILL CHANGE THE DEFAULT BEHAVIOUR, SO THAT MULTI VALUE HEADER IS NOT FORMED FOR ABOVE SCENARIO.
    //With configuration using 'overwriteHeadersWithName()' method, for the above scenrio, only "same-sample-header=VaLuE2" i.e.
    // (last value for that header) will be sent.
    //[Not covered in this example] Also, see description for -  mergeHeadersWithName() [use it in place of - overwriteHeadersWithName()]
    //

    public void requestHeadersScenario2() {

        Header myheader1 = new Header("same-sample-header", "VaLuE1");
        Header myheader2 = new Header("same-sample-header", "VaLuE2");

        // Headers myAllHeaders = new Headers(myheader1,myheader2,myheader3,myheader4);
        Headers myAllHeaders = new Headers(Arrays.asList(myheader1, myheader2));

        RestAssuredConfig restAssuredConfig = RestAssuredConfig.config().headerConfig(HeaderConfig.headerConfig().overwriteHeadersWithName("same-sample-header"));


        Response response = given().log().all().config(restAssuredConfig).
                baseUri("https://postman-echo.com").
                basePath("/{resource}").
                pathParam("resource", "headers").
                headers(myAllHeaders).
                get();

        System.out.println("-----------------------");

        response.prettyPrint();
    }

    @Test

    //Working with multi-value headers.


    public void requestMultiValueHeaders() {


        Response response = given().log().all().
                baseUri("https://postman-echo.com").
                basePath("/{resource}").
                pathParam("resource", "headers").
                header("multi-value header", "value111", "value222").
                get();

        System.out.println("-----------------------");

        response.prettyPrint();


    }


}
