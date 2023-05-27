package com.concepts;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;

public class PutRequest {

    //using https://github.com/vdespa/Postman-Complete-Guide-API-Testing/blob/main/simple-grocery-store-api.md#Create-a-new-cart

    //Create a cart.(in POSTMAN)
    //Add items in card.(in POSTMAN)
    // Replace items using PUT from RestAssured.


    @Test
    public void PutRequest() {

        JSONObject data = new JSONObject();
      data.put("productId", "2585");
      data.put("quantity", "20");
        Map<String, Object> replacementData = data.toMap();


        Response response = given().contentType(ContentType.JSON).
                log().
                all().
                body(replacementData).
                put("https://simple-grocery-store-api.glitch.me/carts/raU78w_KVYk7ISc0ztExZ/items/522064799");

        System.out.println("---------------------------------");
        response.prettyPrint();

        System.out.println("---------------------------------");
        System.out.println("Status code is :" + response.getStatusCode());


    }


}
