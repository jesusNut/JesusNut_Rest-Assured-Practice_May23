package com.responsevalidation;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidation {

//For this example:
//1. Created a mock server in Postman which returns the below JSON as response body.
//2. Generate JSON schema online and save it in a file, DIRECTLY UNDER SRC/MAIN/RESOURCES OR SRC/TEST/RESOURCES.

//{
//    "id": 999,
//        "first_name": "Sandhya",
//        "last_name": "Hridhul",
//        "email": "Sandhya.Hridhul@oingg.com",
//        "job": [
//    "Trainer",
//            "Tester",
//            "Farmer"
//    ],
//    "marks": [
//    {
//        "tamil": 99,
//            "english": 99
//    },
//    {
//        "tamil": 88,
//            "english": 88
//    }
//    ],
//    "favfood": {
//    "breakfast": "dosa",
//            "lunch": "rice",
//            "dinner": [
//    "chapathi",
//            "milk"
//        ]
//}
//}

    @Test

    public void validateSchemaExample1() {

        Response response = given().
                log().all().get("https://2b1c7ed1-e4dd-4e79-96ef-77881b860ede.mock.pstmn.io/getData");

        System.out.println("-----------------------------------------");

        ValidatableResponse validatableResponse = response.then();

        System.out.println("---------- LOG RESPONSE BODY ------------");

        validatableResponse.log().body();

        System.out.println("--------- SCHEMA ASSERTION OF RESPONSE BODY---------------");

        validatableResponse.
                assertThat().
                body(matchesJsonSchemaInClasspath("schemaStorage/validateSchemaExample1.json"));


    }



//For this example:
//1. Created a mock server in Postman which returns the below JSON as response body.
//2. Generate JSON schema online and save it in a file, INSIDE A DIRECTORY UNDER SRC/MAIN/RESOURCES OR SRC/TEST/RESOURCES.

//{
//    "id": 999,
//        "first_name": "Sandhya",
//        "last_name": "Hridhul",
//        "email": "Sandhya.Hridhul@oingg.com",
//        "job": [
//    "Trainer",
//            "Tester",
//            "Farmer"
//    ],
//    "marks": [
//    {
//        "tamil": 99,
//            "english": 99
//    },
//    {
//        "tamil": 88,
//            "english": 88
//    }
//    ],
//    "favfood": {
//    "breakfast": "dosa",
//            "lunch": "rice",
//            "dinner": [
//    "chapathi",
//            "milk"
//        ]
//}
//}

        @Test

        public void validateSchemaExample2() {

            Response response = given().
                    log().all().get("https://2b1c7ed1-e4dd-4e79-96ef-77881b860ede.mock.pstmn.io/getData");

            System.out.println("-----------------------------------------");

            ValidatableResponse validatableResponse = response.then();

            System.out.println("---------- LOG RESPONSE BODY ------------");

            validatableResponse.log().body();

            System.out.println("--------- SCHEMA ASSERTION OF RESPONSE BODY---------------");

            validatableResponse.
                    assertThat().
                    body(matchesJsonSchema(new File(System.getProperty("user.dir")+"/src/test/resources/schemaStorage/validateSchemaExample1.json")));


        }


}
