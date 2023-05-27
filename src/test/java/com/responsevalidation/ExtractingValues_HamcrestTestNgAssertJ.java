package com.responsevalidation;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class ExtractingValues_HamcrestTestNgAssertJ {

    //Once we have extracted values from JOSN response body we can use the below assertions libraries to write assertions:
    //a. AssertJ
    //b.TestNG
    //c.Hamcrest

    // Expected API response:
//   {
//           "id": 1,
//           "first_name": "Claire",
//           "last_name": null,
//           "email": "cdennerley0@uol.com.br",
//           "gender": "Male",
//           "skills": [
//       {
//               "name": "Testing",
//               "proficiency": "Medium"
//       },
//       {
//               "name": "Aquascaper",
//               "proficiency": "High"
//       }
//     ],
//       "bookings": [
//               "Delhi",
//               "Dubai",
//               "IndiaOccupiedPakistan"
//        ]
//   }


    @Test

    public void doExractValues() {

        Response response = given().
                get("https://run.mocky.io/v3/42250a15-be60-46f2-8c4b-e02e0b6ca08f");

        //Firstly, change from 'Response' Object to 'ValidatableResponse' Type using then().

        ValidatableResponse validatableResponse = response.then().log().all();

        //extract respone as String/pretty-string

        String responseAsString = validatableResponse.extract().response().asString();

        String responseAsPrettyString = validatableResponse.extract().response().asPrettyString();


        System.out.println("---------PRINTING RESPONSE AS STRING");

        System.out.println(responseAsString);

        System.out.println("---------PRINTING RESPONSE AS PRETTY-STRING");

        System.out.println(responseAsPrettyString);

        // In a similar fashion, we can also extract Headers,Cookies etc.

        // validatableResponse.extract().<Whetever we want to extract>.asString();

        //Extracting single values : Get a value from the response body using the JsonPath or XmlPath syntax (any kind of response)

        //validatableResponse.extract().path("<some_path>");

        //Extracting single values : Get a value from the response body using the JsonPath syntax (ONLY FOR JSON RESPONSE)

        //validatableResponse.extract().jsonPath().getXXX("some path");

        //Extracting single values : Get a value from the response body using the JsonPath syntax (ONLY FOR XML RESPONSE)

        //validatableResponse.extract().xmlPath().getXXX("some path");

        System.out.println("-----------------------------------------------");

        String gender = validatableResponse.extract().path("gender");

        System.out.println("Gender is : " + gender);

        System.out.println("-----------------ASSERTING USING HAMCREST------------------------------");

        MatcherAssert.assertThat(gender, Matchers.is("Male"));
        MatcherAssert.assertThat(gender.length(), Matchers.is(4));

        System.out.println("----------------- ASSERTING USING TESTNG ------------------------------");

        Assert.assertEquals(gender, "Male");
        Assert.assertEquals(gender.length(), 4);

        System.out.println("----------------- ASSERTING USING ASSERTJ ------------------------------");

        Assertions.assertThat(gender).isEqualTo("Male");
        Assertions.assertThat(gender.length()).isEqualTo(4);

        System.out.println("-----------------------------------------------");

        List<String> bookings = validatableResponse.extract().path("bookings");

        for (String str : bookings) {

            System.out.println(str);
        }

        //Asserting values in 'bookings' and size of 'bookings' list (JSON Array).

        System.out.println("-----------------ASSERTING USING HAMCREST------------------------------");

        MatcherAssert.assertThat(bookings, Matchers.containsInAnyOrder("Delhi", "IndiaOccupiedPakistan", "Dubai"));
        MatcherAssert.assertThat(bookings, Matchers.hasSize(3));

        System.out.println("----------------- ASSERTING USING TESTNG ------------------------------");

        //todo: write assertions here

        System.out.println("----------------- ASSERTING USING ASSERTJ ------------------------------");

        //todo: write assertions here

        System.out.println("-----------------------------------------------");

        //printing skill maps

        List<Map<String, String>> skillValues = validatableResponse.extract().path("skills");

        Set<String> keys = null;

        for (Map<String, String> mapper : skillValues) {

            keys = mapper.keySet();

            for (String str : keys) {

                System.out.println(str + "------" + mapper.get(str));
            }


        }

        //Assert keys in each map under 'skills', i.e. keys 'name' and 'proficiency' should be present.

        //Actual Map

        Map<String, String> actualMapToBeTested = skillValues.get(0);

        //Expected Set of Keys

        HashSet<String> expectedKeys = new HashSet<>();
        expectedKeys.add("name");
        expectedKeys.add("proficiency");

        System.out.println("-----------------ASSERTING USING HAMCREST------------------------------");

        MatcherAssert.assertThat(actualMapToBeTested, Matchers.hasKey("name"));
        MatcherAssert.assertThat(actualMapToBeTested, Matchers.hasKey("proficiency"));

        System.out.println("----------------- ASSERTING USING TESTNG ------------------------------");

        Assert.assertEquals(keys, expectedKeys);

        System.out.println("----------------- ASSERTING USING ASSERTJ ------------------------------");

         Assertions.assertThat(keys).containsExactlyElementsOf(expectedKeys);


    }
}
