package com.concepts;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class HandlingCookies_Fetch {

    @BeforeTest

    public void beforeClass() {

        //Here we used RequestSpecificationBuilder class object below to use a method called 'setRelaxedHTTPSValidation()'.
        //This is just for the application used by OmPrakash (hosted on port 8443 of local host) to work.
        // This is no rest assured concept.

        RestAssured.requestSpecification =
                new RequestSpecBuilder().setRelaxedHTTPSValidation().setBaseUri("https://localhost:8443").build();

    }

    @Test

//We will hit the /login endpoint of the application.
//It will send a cookie named 'JSESSIONID' in Header named 'Set-Cookie'
// We will fetch cookie named 'JSESSIONID' value from header.

    public void fetchingCookieAsHeader() {

        Response response = given(RestAssured.requestSpecification).log().all().get("https://localhost:8443/login");

        System.out.println("-------------------");

        response.prettyPrint();

        System.out.println("-------------------");

        String header = response.getHeader("Set-Cookie");

        System.out.println("The header value is : " + header);

        //write custom code to fetch value of cookie named 'JSESSIONID'

    }

    @Test

//We will hit the /login endpoint of the application.
//It will send a cookie named 'JSESSIONID' in Header named 'Set-Cookie'
// We will fetch cookie named 'JSESSIONID' value dircetly using cookie related methods. - 2 Ways

    public void fetchingCookie() {

        Response response = given(RestAssured.requestSpecification).log().all().get("https://localhost:8443/login");

        System.out.println("-------------------");

        response.prettyPrint();

        System.out.println("-------------------");

        //WAY 1

        System.out.println("Session ID is : " + response.getCookie("JSESSIONID"));
        System.out.println("Session ID is : " + response.getDetailedCookie("JSESSIONID"));

        //WAY 2

        System.out.println("Session ID is : " + response.then().extract().cookie("JSESSIONID"));
        System.out.println("Session ID is : " + response.then().extract().detailedCookie("JSESSIONID"));

    }

    @Test

//We will hit the /login endpoint of the application.
//It will send a cookie named 'JSESSIONID' in Header named 'Set-Cookie'
// We will fetch cookie named 'JSESSIONID' value dircetly using cookie related methods. - 2 Ways

    public void fetchingMultipleCookies() {

        Response response = given(RestAssured.requestSpecification).log().all().get("https://localhost:8443/login");

        System.out.println("-------------------");

        response.prettyPrint();

        System.out.println("-------------------");

        //WAY 1

        Map<String, String> fetchedCookies = response.getCookies();

        Set<Map.Entry<String, String>> entries = fetchedCookies.entrySet();

        for (Map.Entry<String, String> entry : entries) {

            System.out.println("Name of cookie is : " + entry.getKey());
            System.out.println("Value of cookie is : " + entry.getValue());
        }

        Cookies detailedCookies = response.getDetailedCookies();
        List<Cookie> listOfCookiesInDetailedCookies = detailedCookies.asList();

        for (Cookie cook : listOfCookiesInDetailedCookies) {

            System.out.println(cook.toString());
            System.out.println(cook.getName() + "-------" + cook.getValue());
        }


        //WAY 2

        Map<String, String> fetchedCookies1 = response.then().extract().cookies();

        Set<Map.Entry<String, String>> entries1 = fetchedCookies1.entrySet();

        for (Map.Entry<String, String> entry : entries1) {

            System.out.println("Name of cookie is : " + entry.getKey());
            System.out.println("Value of cookie is : " + entry.getValue());
        }

        Cookies detailedCookies1 = response.then().extract().detailedCookies();
        List<Cookie> listOfCookiesInDetailedCookies1 = detailedCookies1.asList();

        for (Cookie cook : listOfCookiesInDetailedCookies1) {

            System.out.println(cook.toString());
            System.out.println(cook.getName() + "-------" + cook.getValue());
        }


    }


}
