package com.concepts;

import com.authentication.formbased.FormAuthentication;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class HandingCookies_Set {

    @BeforeTest

    public void beforeClass() {

        //Here we used RequestSpecificationBuilder class object below to use a method called 'setRelaxedHTTPSValidation()'.
        //This is just for the application used by OmPrakash (hosted on port 8443 of local host) to work.
        // This is no rest assured concept.

        RestAssured.requestSpecification =
                new RequestSpecBuilder().setRelaxedHTTPSValidation().setBaseUri("https://localhost:8443").build();

    }

    @Test

    /**
     Alternate implementation of :
     {@link FormAuthentication#loginToApp()}
     */

    public void setCookie() {

        //create a FormAuthConfig class object providing parameter values as per
        // https://github.com/rest-assured/rest-assured/wiki/Usage#form-authentication


        FormAuthConfig formAuthConfig =
                new FormAuthConfig("/signin", "txtUsername", "txtPassword")
                        .withLoggingEnabled();


        // The below line will:

        // 1. Fetches the CSRF Token from endpont '/login' [which is the actual webpage where user puts username, password and clicks submit]
        // using GET and get holds of CSRF Token.
        // 2. replace actual username 'dan' & actual password 'dan123' in the placeholders 'txtUsername' & 'txtPassword'
        // in FormAuthConfig class object by doing a GET api call to '/login' endpoint.
        // 3. Makes POST call to endpoint provided by us '/signin' in FormAuthConfig class object along with CSRF token .
        // *** CONCEPT *** : When you defined the new FormAuthConfig("/authentication/", "j_username", "j_password"),
        // you're telling Rest Assured that it should make a POST call to an /authentication endpoint using the
        // two last parameters as form params.
        // 4. Session-cookie value is fetched from response of 3.
        // 5. At last a GET call is again done on https://localhost:8443 which again gives the user already signed in page
        // with Home and Profile menu.


        SessionFilter sessionFilter = new SessionFilter();

        given().log().all().
                spec(RestAssured.requestSpecification).filter(sessionFilter).
                csrf("/login", "_csrf").
                auth().form("dan", "dan123", formAuthConfig).
                get();


        System.out.println("-----------------------------------------------");

        //Fetch session cookie value.

        System.out.println(sessionFilter.getSessionId());

        System.out.println("-----------------------------------------------");

        //6. GET call on https://localhost:8443/profile/index and verify HTML page to verify that user is actually logged in.

        //Setting cookie in below API request.

        Response response = given().log().all().
                spec(RestAssured.requestSpecification).
                cookie("JSESSIONID", sessionFilter.getSessionId()).
                get("/profile/index");

        System.out.println("-----------------------------------------------");

        response.prettyPrint();


    }

    @Test

    /**
     Alternate implementation of :
     {@link FormAuthentication#loginToApp()}
     */

    public void setCookieUsingCookieBuilder() {

        //create a FormAuthConfig class object providing parameter values as per
        // https://github.com/rest-assured/rest-assured/wiki/Usage#form-authentication


        FormAuthConfig formAuthConfig =
                new FormAuthConfig("/signin", "txtUsername", "txtPassword")
                        .withLoggingEnabled();


        // The below line will:

        // 1. Fetches the CSRF Token from endpont '/login' [which is the actual webpage where user puts username, password and clicks submit]
        // using GET and get holds of CSRF Token.
        // 2. replace actual username 'dan' & actual password 'dan123' in the placeholders 'txtUsername' & 'txtPassword'
        // in FormAuthConfig class object by doing a GET api call to '/login' endpoint.
        // 3. Makes POST call to endpoint provided by us '/signin' in FormAuthConfig class object along with CSRF token .
        // *** CONCEPT *** : When you defined the new FormAuthConfig("/authentication/", "j_username", "j_password"),
        // you're telling Rest Assured that it should make a POST call to an /authentication endpoint using the
        // two last parameters as form params.
        // 4. Session-cookie value is fetched from response of 3.
        // 5. At last a GET call is again done on https://localhost:8443 which again gives the user already signed in page
        // with Home and Profile menu.


        SessionFilter sessionFilter = new SessionFilter();

        given().log().all().
                spec(RestAssured.requestSpecification).filter(sessionFilter).
                csrf("/login", "_csrf").
                auth().form("dan", "dan123", formAuthConfig).
                get();


        System.out.println("-----------------------------------------------");

        //Fetch session cookie value.

        System.out.println(sessionFilter.getSessionId());

        System.out.println("-----------------------------------------------");

        //6. GET call on https://localhost:8443/profile/index and verify HTML page to verify that user is actually logged in.

        //Use Cookie Builder concept

        Cookie builtCookie = new Cookie.Builder("JSESSIONID", sessionFilter.getSessionId()).
                setComment("by Abhishek").
                setPath("/").
                setSecured(true).
                setHttpOnly(true).build();

        Response response = given().log().all().
                spec(RestAssured.requestSpecification).
                cookie(builtCookie).
                get("/profile/index");

        System.out.println("-----------------------------------------------");

        response.prettyPrint();


    }

    @Test

    /**
     Alternate implementation of :
     {@link FormAuthentication#loginToApp()}
     */

    public void setMultipleCookies() {

        //create a FormAuthConfig class object providing parameter values as per
        // https://github.com/rest-assured/rest-assured/wiki/Usage#form-authentication


        FormAuthConfig formAuthConfig =
                new FormAuthConfig("/signin", "txtUsername", "txtPassword")
                        .withLoggingEnabled();


        // The below line will:

        // 1. Fetches the CSRF Token from endpont '/login' [which is the actual webpage where user puts username, password and clicks submit]
        // using GET and get holds of CSRF Token.
        // 2. replace actual username 'dan' & actual password 'dan123' in the placeholders 'txtUsername' & 'txtPassword'
        // in FormAuthConfig class object by doing a GET api call to '/login' endpoint.
        // 3. Makes POST call to endpoint provided by us '/signin' in FormAuthConfig class object along with CSRF token .
        // *** CONCEPT *** : When you defined the new FormAuthConfig("/authentication/", "j_username", "j_password"),
        // you're telling Rest Assured that it should make a POST call to an /authentication endpoint using the
        // two last parameters as form params.
        // 4. Session-cookie value is fetched from response of 3.
        // 5. At last a GET call is again done on https://localhost:8443 which again gives the user already signed in page
        // with Home and Profile menu.


        SessionFilter sessionFilter = new SessionFilter();

        given().log().all().
                spec(RestAssured.requestSpecification).filter(sessionFilter).
                csrf("/login", "_csrf").
                auth().form("dan", "dan123", formAuthConfig).
                get();


        System.out.println("-----------------------------------------------");

        //Fetch session cookie value.

        System.out.println(sessionFilter.getSessionId());

        System.out.println("-----------------------------------------------");

        //6. GET call on https://localhost:8443/profile/index and verify HTML page to verify that user is actually logged in.

        //Use Cookie Builder concept - WAY 1

//        Cookie builtCookie1 = new Cookie.Builder("JSESSIONID", sessionFilter.getSessionId()).
//                setComment("by Abhishek").
//                setPath("/").
//                setSecured(true).
//                setHttpOnly(true).build();
//
//        Cookie builtCookie2 = new Cookie.Builder("DEMO NAME", "DEMO VALUE").
//                build();
//
//        Cookies multipleCookies = new Cookies(builtCookie1, builtCookie2);
//
//        Response response = given().log().all().
//                spec(RestAssured.requestSpecification).
//                cookies(multipleCookies).
//                get("/profile/index");

        //-------------------------------------------------------------------------------------------

        //Use Cookie Builder concept - WAY 2

        HashMap<String, String> mapOfCookies = new HashMap<>();

        mapOfCookies.put("JSESSIONID", sessionFilter.getSessionId());
        mapOfCookies.put("DEMO NAME", "DEMO VALUE");

        Response response = given().log().all().
                spec(RestAssured.requestSpecification).
                cookies(mapOfCookies).
                get("/profile/index");

        System.out.println("-----------------------------------------------");
        response.prettyPrint();


    }


}


