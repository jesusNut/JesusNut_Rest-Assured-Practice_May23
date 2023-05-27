package com.authentication.formbased;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FormAuthentication {

    //This is to demo and work on an app that allows Form Based Authentication
    //This to demo SESSION FILTER CONCEPT
    //Refer : OmParakash Rest-Assured_Udemy [section-33] + OWN NOTES


    @BeforeTest

    public void beforeClass() {

        //Here we used RequestSpecificationBuilder class object below to use a method called 'setRelaxedHTTPSValidation()'.
        //This is just for the application used by OmPrakash (hosted on port 8443 of local host) to work.
        // This is no rest assured concept.

        RestAssured.requestSpecification =
                new RequestSpecBuilder().setRelaxedHTTPSValidation().setBaseUri("https://localhost:8443").build();

    }


    @Test

    public void loginToApp() {

        //create a FormAuthConfig class object providing parameter values as per
        // https://github.com/rest-assured/rest-assured/wiki/Usage#form-authentication


        FormAuthConfig formAuthConfig =
                new FormAuthConfig("/signin", "txtUsername", "txtPassword")
                        .withLoggingEnabled();


        // The below line will:

        // 1. Fetches the CSRF Token from endpont '/login' [which is the actual webpage where user puts username, password and clicks submit]
        // using GET and get holds of CSRF Token. [ek faltto session ID bhi aayega becoz of app]
        // 2. replace actual username 'dan' & actual password 'dan123' in the placeholders 'txtUsername' & 'txtPassword'
        // in FormAuthConfig class object by doing a GET api call to '/login' endpoint.
        // 3. Makes POST call to endpoint provided by us '/signin' in FormAuthConfig class object along with CSRF token .
        // *** CONCEPT *** : When you defined the new FormAuthConfig("/authentication/", "j_username", "j_password"),
        // you're telling Rest Assured that it should make a POST call to an /authentication endpoint using the
        // two last parameters as form params.
        // 4. Session ID value is fetched from response of 3.
        // 5. At last a GET call is again done on https://localhost:8443 which again gives the user already signed in page
        // with Home and Profile menu.


        SessionFilter sessionFilter = new SessionFilter();

        given().log().all().
                spec(RestAssured.requestSpecification)
                .filter(sessionFilter)
                .csrf("/login", "_csrf").
                auth().form("dan", "dan123", formAuthConfig)
                .get();


        System.out.println("-----------------------------------------------");

        //Fetch session ID value.

        System.out.println(sessionFilter.getSessionId());

        System.out.println("-----------------------------------------------");

        //6. GET call on https://localhost:8443/profile/index and verify HTML page to verify that user is actually logged in.

        Response response = given().log().all().
                spec(RestAssured.requestSpecification).
                filter(sessionFilter).
                get("/profile/index");

        System.out.println("-----------------------------------------------");

        response.prettyPrint();


    }
}
