package com.concepts;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetRequest {


    @Test
    public void simpleGetRequest() {

        Response response1 =
                given().get("https://reqres.in/api/users?page=2");
        System.out.println(response1.asPrettyString());
        System.out.println(response1.statusCode());
        System.out.println(response1.getContentType());
        System.out.println(response1.header("Transfer-Encoding"));

        Headers headers = response1.headers();

        for (Header head : headers.asList()) {

            System.out.println(head.getName() + "--------" + head.getValue());
        }
        System.out.println(response1.getSessionId());

    }

    @Test
    public void simpleGetRequestDeletme() {

        String body = "";

        Response response1 =
                given().log().all()
                        .baseUri("https://postman-echo.com/")
                        .pathParam("resource", "post")
                        .contentType(ContentType.JSON).body(body)
                        .post("{resource}");
//        System.out.println(response1.asPrettyString());
//        System.out.println(response1.statusCode());
//        System.out.println(response1.getContentType());
//        System.out.println(response1.header("Transfer-Encoding"));
//
//        Headers headers = response1.headers();
//
//        for (Header head : headers.asList()){
//
//            System.out.println(head.getName()+"--------"+head.getValue());
//        }
//        System.out.println(response1.getSessionId());

    }

}
