package com.concepts;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

//Non-Amuthan Way  + Non-BDD way
//understanding breakup of writing API requests.
//Understanding 'RequestSpecification' Interface type.[Amod]
//understanding logging the requests information. [OmPrakash-Udemy-Section11]
//understanding Response Interface , 'then()' & 'ValidatableResponse' [Amod]
//Change Response to ValidatableResponse to write assertions for Statuscodes, cookies, headers etc.
//directly without using any Assert statements of AssertJ/TestNG.
//writing assertions using ValidatableResponse type objects. [OmPrakash-Udemy-Section10]

public class AbcOfWritingAPIReqInlineResponseAssertion {

    @Test
    public void basicPostTest() {


        //Creating JSON payload for post request

        JSONObject jbody = new JSONObject();
        jbody.put("productId", "9482");
        jbody.put("quantity", "15");

        //Writing POST request

        RequestSpecification request = given();
        request = request.baseUri("https://simple-grocery-store-api.glitch.me");
        request = request.basePath("/carts/raU78w_KVYk7ISc0ztExZ/items");
        request = request.contentType(ContentType.JSON);
        request = request.body(jbody.toMap());
        request = request.log().everything();
        Response response = request.post();

        //ValidatableResponse Interface type objects can be asserted for multiple checks like status codes,
        // header values, body etc. directly in RestAssured.

        ValidatableResponse validatableResponse = response.then();

        //we can log all info related to response using below:

        validatableResponse = validatableResponse.log().all();

        //understanding return type of 'assertThat()' method.

        validatableResponse = validatableResponse.assertThat();

        //writing assertion using ValidatableResponse type objects.

        validatableResponse.assertThat().statusCode(201)
                .and().assertThat().statusLine("HTTP/1.1 201 Created")
                .and().assertThat().header("Connection", "keep-alive")
                .and().assertThat().header("Content-Type", "application/json; charset=utf-8");

    }

}
