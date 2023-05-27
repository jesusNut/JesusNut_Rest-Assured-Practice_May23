package com.concepts;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteRequest {

    //using https://github.com/vdespa/Postman-Complete-Guide-API-Testing/blob/main/simple-grocery-store-api.md#Create-a-new-cart

    //Create a cart.(in POSTMAN)
    //Add items in card.(in POSTMAN)
    // Delete an item in the Cart using RestAssured.


    @Test
    public void DeleteRequest() {

        Response response = given().contentType(ContentType.JSON).
                log().
                all().
                delete("https://simple-grocery-store-api.glitch.me/carts/raU78w_KVYk7ISc0ztExZ/items/174937603");

        System.out.println("---------------------------------");
        response.prettyPrint();

        System.out.println("---------------------------------");
        System.out.println("Status code is :" + response.getStatusCode());


    }


}
