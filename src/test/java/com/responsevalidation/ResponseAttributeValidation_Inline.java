package com.responsevalidation;


import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


//writing assertions using ValidatableResponse type objects.

public class ResponseAttributeValidation_Inline {

    // In Inline style, we do below steps:
    // 1. Change "<response>.then()" to convert 'Response' type object to 'ValidatableResponse' type Object.
    // 2. Write Inline Assertions for response attributes e.g. status code, status line, response time , headers, cookies etc.
    // 3. We can use Hamcrest Assertion Library wherever required.
    //[OmPrakash-Udemy-Section10]

    //Expected Headers:
    //foo1: bar1
    //foo2: bar2

    //Expected API-Response :

//    {
//            "id": 1,
//            "first_name": "Claire",
//            "last_name": null,
//            "email": "cdennerley0@uol.com.br",
//            "gender": "Male",
//            "skills": [
//        {
//            "name": "Testing",
//                "proficiency": "Medium"
//        },
//        {
//            "name": "Aquascaper",
//                "proficiency": "High"
//        }
//     ],
//        "bookings": [
//        "Delhi",
//                "Dubai",
//                "IndiaOccupiedPakistan"
//    ]
//       }


    @Test
    public void fetchInfoFromResponse() {


        Response response = given().log().all().
                get("https://run.mocky.io/v3/9486577a-acb4-403d-a036-85be276959aa");


        //ValidatableResponse Interface type objects can be asserted for multiple checks like status codes,
        // header values, body etc. directly in RestAssured.

        //1. Change "<response>.then()" to convert 'Response' type object to 'ValidatableResponse' type Object.

        ValidatableResponse validatableResponse = response.then();

        //we can log all info related to response using below:

        System.out.println("--------- LOGGING API RESPONSE -----------");

        validatableResponse = validatableResponse.log().all();

        //understanding return type of 'assertThat()' method.

        validatableResponse = validatableResponse.assertThat();

        //write Inline Assertions for response attributes e.g. status code, status line, response time , headers, cookies etc.

        System.out.println("--------- INLINE ASSERTIONS ON API RESPONSE -----------");

        validatableResponse.assertThat().statusCode(200)
                .and().assertThat().statusLine("HTTP/1.1 200 OK")
                .and().assertThat().header("foo1", "bar1")
                .and().assertThat().header("foo2", "bar2")
                .and().assertThat().contentType("application/json; charset=UTF-8")
                .and().assertThat().time(Matchers.lessThan((long)1500));

    }

}
