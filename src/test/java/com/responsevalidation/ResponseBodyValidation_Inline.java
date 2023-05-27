package com.responsevalidation;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class ResponseBodyValidation_Inline {

    //How to do inline assertions:

    //1. Just convert 'Response' Object Type to 'ValidatableResponse' Object type using 'then()'.
    //2. Then write inline assertions [using Hamcrest Assertiona Library] on body.

    // Hamrest is an assertion libraray just like AssertJ.

    //Expected API Response:

//       {
//            "id": 1,
//            "first_name": "Claire",
//            "last_name": null,
//            "email": "cdennerley0@uol.com.br",
//            "gender": "Male",
//            "skills": [
//           {
//                "name": "Testing",
//                "proficiency": "Medium"
//           },
//           {
//                "name": "Aquascaper",
//                "proficiency": "High"
//           }
//         ],
//             "bookings": [
//                 "Delhi",
//                 "Dubai",
//                 "IndiaOccupiedPakistan"
//         ]
//     }

    @Test

    public void doInlineAssertions() {


        Response response = given().log()
                .all()
                .get("https://run.mocky.io/v3/42250a15-be60-46f2-8c4b-e02e0b6ca08f");

        System.out.println("--------------");

        System.out.println(response.prettyPrint());

        System.out.println("--------------");

        // Step 1: Just convert 'Response' Object Type to 'ValidatableResponse' Object type using 'then()'.

        ValidatableResponse validatableResponse = response.then();

        //Step 2: Then write inline assertions [using Hamcrest] on body.

        System.out.println("--------------------------- ASSERTING KEY-VALUES in JSON OBJECT -------------------------------");

        //Check if first_name is not null.

        validatableResponse.body("first_name", Matchers.notNullValue());

        //Check if last_name is null.

        validatableResponse.body("last_name", Matchers.nullValue());

        //Check if length of gender property value ('male' here) is equal to 4. (2 ways below:)

        validatableResponse.body("gender.length()", Matchers.is(4));
        validatableResponse.body("gender.length()", Matchers.equalTo(4));

        //Check if length of gender property value ('male' here) is less than 8. (also check, lessThanOrEqualTo())

        validatableResponse.body("gender.length()", Matchers.lessThan(8));

        //Check if length of gender property value ('male' here) is greater than 2. (also check, greaterThanOrEqualTo())

        validatableResponse.body("gender.length()", Matchers.greaterThan(2));


        //Check if value for email property is having 'com' sub-string.

        validatableResponse.body("email", Matchers.containsString("com"));

        //Check if value for email property is an empty string

        //validatableResponse.body("email", Matchers.emptyString());

        System.out.println("--------------------------- ASSERTING LISTS (JSON ARRAY) -------------------------------");

        //Assert if  Skills List is not empty and has size 2.

        validatableResponse.body("skills", Matchers.not(Matchers.empty()));
        validatableResponse.body("skills", Matchers.hasSize(2));

        //Check Only 'Testing' and 'Aquascaper' elements are in a Skills List collection and in a strict order.

        validatableResponse.body("skills.name", Matchers.contains("Testing", "Aquascaper"));

        //Check Only 'Testing' and 'Aquascaper' elements are in a Skills List collection and in any order.

        validatableResponse.body("skills.name", Matchers.hasItems("Aquascaper", "Testing"));

        //Check if 'Testing' is in Skills List collection or not?

        validatableResponse.body("skills.name", Matchers.hasItem("Testing"));


        //Assert if  'Fomo-Testing' is NOT in Skills List collection.

        validatableResponse.body("skills.name", Matchers.not(Matchers.hasItem("Fomo-Testing")));

        //Assert if bookings List has the values in any order.

        validatableResponse.body("bookings", Matchers.containsInAnyOrder("Delhi", "IndiaOccupiedPakistan", "Dubai"));

        //Assert if bookings List has the values in specific order.

        validatableResponse.body("bookings", Matchers.contains("Delhi", "Dubai", "IndiaOccupiedPakistan"));


        System.out.println("--------------------------- ASSERTING MAP (JSON OBJECT) -------------------------------");

        //Assert if first JSON Object(maps) in skills List is having a key called 'name' or not

        validatableResponse.body("skills[0]", Matchers.hasKey("name"));

        //Assert if first JSON Object(maps) in  skills List has at least one key matching specified value 'Testing'

        validatableResponse.body("skills[0]", Matchers.hasValue("Testing"));

        //Assert if second JSON Object(maps) in  skills List has at least one key-value pair (entry)-'"name": "Aquascaper"'

        validatableResponse.body("skills[1]", Matchers.hasEntry("name", "Aquascaper"));


        //Assert if the first JSON Object(maps) in  skills List is empty
        //This concept can be used to check any type of empty collection e.g. MAP, LIST , SET etc.

        //validatableResponse.body("skills[0]",Matchers.equalTo(Collections.EMPTY_MAP));


        System.out.println("--------------------------- ASSERTION USING MULTIPLE MATCHERS ---------------------------");

        //Using multiple matchers at once.
        //Use multiple matchers and pass only if all conditions are passed.

        validatableResponse.body("id", Matchers
                .allOf(Matchers.is(1), Matchers.lessThan(5), Matchers.greaterThan(0)));

        //Use multiple matchers and pass if any condition is passed.

        validatableResponse.body("id", Matchers
                .anyOf(Matchers.is(5), Matchers.lessThan(5), Matchers.greaterThan(5)));

    }

}
