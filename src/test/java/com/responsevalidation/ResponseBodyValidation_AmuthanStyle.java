package com.responsevalidation;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ResponseBodyValidation_AmuthanStyle {

    //All assertions done via AssertJ

    //Expected API response (Starts from ):

//    {
//            "id": 1,
//            "first_name": "Claire",
//            "last_name": null,
//            "email": "cdennerley0@uol.com.br",
//            "gender": "Male",
//            "skills": [
//        {
//                "name": "Testing",
//                "proficiency": "Medium"
//        },
//        {
//                "name": "Aquascaper",
//                "proficiency": "High"
//        }
//     ],
//        "bookings": [
//                "Delhi",
//                "Dubai",
//                "IndiaOccupiedPakistan"
//      ]
//    }


    @Test

    public void doAssertionsAmuthanStyle1() {


        Response response = given().log()
                .all()
                .get("https://run.mocky.io/v3/42250a15-be60-46f2-8c4b-e02e0b6ca08f");

        System.out.println("---------------PRINTING RESPONSE---------------");

        System.out.println(response.prettyPrint());

        System.out.println("----------------DIFFERENT WAYS TO FETCH VALUE AMUTHAN STYLE FROM RESPONSE BODY---------------");

        // Step 1: DONT convert 'Response' Object Type to 'ValidatableResponse' Object type using 'then()'.
        // Step 2: Directly write assertions on JSON body.

        //Way 1:

        String firstNameExtractedWay1 = JsonPath.from(response.asString()).getString("first_name");
        System.out.println("Way 1 : " + firstNameExtractedWay1);

        //Way 2: [we can use response.xmlPath() as well as per requirements]

        JsonPath jsonPathView = response.jsonPath();
        String firstNameExtractedWay2 = jsonPathView.getString("first_name");
        System.out.println("Way 2 : " + firstNameExtractedWay2);


        //Way 3: (Shortening Way 2)

        String firstNameExtractedWay3 = response.jsonPath().getString("first_name");
        System.out.println("Way 3 : " + firstNameExtractedWay3);

        //Way 4:

        String firstNameExtractedWay4 = response.path("first_name");
        System.out.println("Way 4 : " + firstNameExtractedWay4);


        System.out.println("--------------------------- ASSERTING KEY-VALUES in JSON OBJECT -------------------------------");

        //Check if first_name is not null.

        String actualFirstName = response.jsonPath().getString("first_name");

        Assertions.assertThat(actualFirstName).isNotNull();

        //Check if last_name is null.

        String actualLastName = response.jsonPath().getString("last_name");

        Assertions.assertThat(actualLastName).isNull();

        //Check if length of gender property value ('male' here) is equal to 4.

        String actualGender = response.jsonPath().getString("gender");

        Assertions.assertThat(actualGender).hasSize(4);

        //Check if length of gender property value ('male' here) is less than 8. (also check, lessThanOrEqualTo())

        Assertions.assertThat(actualGender).hasSizeLessThan(8);

        //Check if length of gender property value ('male' here) is greater than 2. (also check, greaterThanOrEqualTo())

        Assertions.assertThat(actualGender).hasSizeGreaterThan(2);

        //Check if value for email property is having 'com' sub-string.

        String actualEmail = response.jsonPath().getString("email");

        Assertions.assertThat(actualEmail).containsIgnoringCase("COM");


        System.out.println("--------------------------- ASSERTING LISTS (JSON ARRAY) -------------------------------");

        //Assert if  Skills List is not empty and has size 2.

        List<Object> actualSkills = response.jsonPath().getList("skills");
        Assertions.assertThat(actualSkills).isNotEmpty();
        Assertions.assertThat(actualSkills).hasSize(2);

        //Check Only 'Testing' and 'Aquascaper' elements are in a Skills List collection and in a strict order.

        List<Object> actualListOfNameValuesInSkills = response.jsonPath().getList("skills.name"); //list of name values

        Assertions.assertThat(actualListOfNameValuesInSkills).containsExactly("Testing", "Aquascaper");

        //Check Only 'Testing' and 'Aquascaper' elements are in a Skills List collection and in any order.

        Assertions.assertThat(actualListOfNameValuesInSkills).containsExactlyInAnyOrder("Aquascaper", "Testing");

        //Check if 'Testing' is in Skills List collection or not?

        Assertions.assertThat(actualListOfNameValuesInSkills).contains("Testing");


        //Assert if  'Fomo-Testing' is NOT in Skills List collection.

        Assertions.assertThat(actualListOfNameValuesInSkills).doesNotContain("Fomo-Testing");

        System.out.println("--------------------------- ASSERTING MAP (JSON OBJECT) -------------------------------");

        //Assert if first JSON Object(maps) in skills List is having 2 keys called 'name' & 'proficiency' or not

        Map<String, String> firstJSONObjectInSkills = response.jsonPath().getMap("skills[0]");

        Assertions.assertThat(firstJSONObjectInSkills).containsKeys("name", "proficiency");

        //Assert if second JSON Object(maps) in  skills List has at least one key-value pair (entry)-'"name": "Aquascaper"'

        Map<String, String> secondJSONObjectInSkills = response.jsonPath().getMap("skills[1]");

        Assertions.assertThat(secondJSONObjectInSkills).containsEntry("name", "Aquascaper");

    }

    //Expected API Response:
//        [
//
//           {
//            "firstName" :"Mike",
//            "lastName" :"harvey",
//            "age" :34
//           },
//
//           {
//            "firstName" :"Nick",
//            "lastName" :"young",
//            "age" :75
//           }
//
//        ]

    @Test

    public void doAssertionsAmuthanStyle2() {

        Response response = given().log().all().get("https://run.mocky.io/v3/ec9b1f6f-7f24-4100-8f4a-16f798b6846d");

        System.out.println("---------------------------------");

        //find total number of objects in JSON array

        int size = response.jsonPath().getList("$").size();

        System.out.println("Total number of objects are : "+size);

        //print first JsonObject in JsonArray.

        Map<Object, Object> map1 = response.jsonPath().getMap("[0]");

        System.out.println(map1);

        System.out.println("---------------------------------");

        //print second JsonObject in JsonArray.

        Map<Object, Object> map2 = response.jsonPath().getMap("[1]");

        System.out.println(map2);

        System.out.println("---------------------------------");

        //print value of key 'age' in second JsonObject in JsonArray.

        String age = response.jsonPath().getString("[1].age");

        System.out.println(age);


        //todo:write assertions accordingly
    }


}
