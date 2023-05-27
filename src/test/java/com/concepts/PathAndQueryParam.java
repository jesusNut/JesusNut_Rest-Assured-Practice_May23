package com.concepts;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.*;

public class PathAndQueryParam {


    @Test

//Two ways to write a path parameter in an API request - NAMED Parameter & INLINE parameter.
// This one is example of Named parameter.
//Hitting a URI : https://reqres.in/api/users/2


    public void namedPathParam() {

        Response response = given().log().all().
                baseUri("https://reqres.in?page=2").
                basePath("/{apiName}/{resource}/{id}")
                .pathParam("apiName", "api")
                .pathParam("resource", "users").
                pathParam("id", "2").
                contentType(ContentType.JSON).get();

        System.out.println("-----------------------");

        response.prettyPrint();


    }

    @Test

    // This one is example of Inline parameter.
    //Hitting a URI : https://reqres.in/api/users/2

    public void inlinePathParam() {

        Response response = given().log().all().
                contentType(ContentType.JSON).get("https://reqres.in/{apiName}/{resource}/{id}", "api", "users", 2);

        System.out.println("-----------------------");

        response.prettyPrint();


    }

    @Test

    // This one is example using path parameter using MAP.
    // Hitting a URI : https://reqres.in/api/users/2

    public void pathParamUsingMap1() {

        Map<String, Object> pathParams = new HashMap<String, Object>();
        pathParams.put("apiName", "api");
        pathParams.put("resource", "users");
        pathParams.put("id", 2);


        Response response = given().log().all().
                contentType(ContentType.JSON).get("https://reqres.in/{apiName}/{resource}/{id}", pathParams);

        System.out.println("-----------------------");

        response.prettyPrint();


    }

    @Test

    // This one is example using path parameter using MAP.
    // Hitting a URI : https://reqres.in/api/users/2

    public void pathParamUsingMap2() {

        Map<String, Object> pathParams = new HashMap<String, Object>();
        pathParams.put("apiName", "api");
        pathParams.put("resource", "users");
        pathParams.put("id", 2);


        Response response = given().log().all().baseUri("https://reqres.in").
                basePath("/{apiName}/{resource}/{id}").
                pathParams(pathParams).
                contentType(ContentType.JSON).get();

        System.out.println("-----------------------");

        response.prettyPrint();


    }

    @Test

    // This one is example using path parameter using MAP.
    // Hitting a URI : https://reqres.in/api/users/2

    public void pathParamUsingMap3() {

        Map<String, Object> pathParams = new HashMap<String, Object>();
        pathParams.put("apiName", "api");
        pathParams.put("resource", "users");
        pathParams.put("id", 2);


        Response response = given().log().all().
                pathParams(pathParams).
                contentType(ContentType.JSON).get("https://reqres.in/{apiName}/{resource}/{id}");

        System.out.println("-----------------------");

        response.prettyPrint();


    }

    @Test

    // This one is example using query parameter - single/multiple in different lines.

    public void queryParamExample1() {

        Map<String, Object> pathParams = new HashMap<String, Object>();
        pathParams.put("repoType", "public");
        pathParams.put("version", "v1");
        pathParams.put("resource", "users");


        Response response = given().log().all().
                baseUri("https://gorest.co.in").
                basePath("/{repoType}/{version}/{resource}").
                pathParams(pathParams).
                queryParam("page", 2).
                queryParam("per_page", 1).
                queryParam("status", "inactive").
                queryParam("gender", "male").
                contentType(ContentType.JSON).get();

        System.out.println("-----------------------");

        response.prettyPrint();


    }

    @Test

    // This one is example using query parameter - multiple in single line

    public void queryParamExample2() {

        Map<String, Object> pathParams = new HashMap<String, Object>();
        pathParams.put("repoType", "public");
        pathParams.put("version", "v1");
        pathParams.put("resource", "users");


        Response response = given().log().all().
                baseUri("https://gorest.co.in").
                basePath("/{repoType}/{version}/{resource}").
                pathParams(pathParams).
                queryParams("page", 3, "per_page", 2, "gender", "female").
                contentType(ContentType.JSON).get();

        System.out.println("-----------------------");

        response.prettyPrint();


    }

    @Test

    // This one is example using query parameter - using Map

    public void queryParamExampleUsingMap() {

        Map<String, Object> pathParams = new HashMap<String, Object>();
        pathParams.put("repoType", "public");
        pathParams.put("version", "v1");
        pathParams.put("resource", "users");

        Map<String, Object> queryParams = new LinkedHashMap<String, Object>();
        queryParams.put("status", "active");
        queryParams.put("page", "11");
        queryParams.put("gender", "female");
        queryParams.put("per_page", "5");

        Response response = given().log().all().
                baseUri("https://gorest.co.in").
                basePath("/{repoType}/{version}/{resource}").
                pathParams(pathParams).
                queryParams(queryParams).
                contentType(ContentType.JSON).get();

        System.out.println("-----------------------");

        response.prettyPrint();


    }

    @Test

    // Creating a request having query param (having multiple values)
    //e.g. : https://postman-echo.com/get?foo1=bar1,bar2,bar3

    public void multiValueQueryParam() {

        Response response = given().log().all().
                baseUri("https://postman-echo.com").
                basePath("/get").
                queryParam("foo1", "bar1", "bar2", "bar3").
                get();

        System.out.println("-----------------------");

        response.prettyPrint();
    }


}
