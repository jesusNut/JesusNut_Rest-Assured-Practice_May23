package com.concepts;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class PatchRequest {

    //Create a cart.(in POSTMAN)
    //Add items in card.(in POSTMAN)
    // Replace items using PUT from RestAssured.
    //Change the quantity of the replaced item using PATCH from RestAssured.[Demonstrated here!!]

    @Test
    public void PatchRequestDemo() {

        JSONObject data = new JSONObject();
        data.put("quantity", "19");
        Map<String, Object> replacementData = data.toMap();


        Response response = given().contentType(ContentType.JSON).
                log().
                all().
                body(replacementData).
                patch("https://simple-grocery-store-api.glitch.me/carts/raU78w_KVYk7ISc0ztExZ/items/522064799");

        System.out.println("---------------------------------");
        response.prettyPrint();

        System.out.println("---------------------------------");
        System.out.println("Status code is :" + response.getStatusCode());


    }






}
