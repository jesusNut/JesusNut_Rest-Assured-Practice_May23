package com.deserialization;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class ConvertJsonResponseToJavaMap {

// Under-the-hood, Rest assured uses Jackson(or similar library e.g. Gson) to deserialize JSON API response back to a Java Map Object.
//If a deserializer is not used, we will get exception.

//For this example:
//1. Created a mock server in https://designer.mocky.io/design which returns the below JSON as response body.
//    {
//        "id": 1,
//        "first_name": "Claire",
//        "last_name": "Dennerley",
//        "email": "cdennerley0@uol.com.br",
//        "gender": "Male"
//    }
//2. Converted that JSON response to Java Map Object.
//3. Do any operation that can be done on Java Map Object as per requirement.

    @Test

    public void convertJsonResponseToMapEx1() {

        Response jsonResponse = given().log().all().get("https://run.mocky.io/v3/026f840c-bc99-4ba8-82e8-c6132dbf3d6b");

        System.out.println("---------------------");

        jsonResponse.prettyPrint();

        System.out.println("------------CONVERSION-PRINT VALUES---------");

        Map<String, Object> jsonToMapConvertedResponse = jsonResponse.as(new TypeRef<Map<String, Object>>() {});

        int id = (Integer) jsonToMapConvertedResponse.get("id");
        String firstName = (String) jsonToMapConvertedResponse.get("first_name");
        String lastName = (String) jsonToMapConvertedResponse.get("last_name");
        String email = (String) jsonToMapConvertedResponse.get("email");
        String gender = (String) jsonToMapConvertedResponse.get("gender");

        System.out.println(id + "----" + firstName + "----" + lastName + "----" + email + "----" + gender);

        System.out.println("------------CONVERSION-PRINT ALL KEYS---------");

        Set<String> setOfKeys = jsonToMapConvertedResponse.keySet();

        for (Object setOfKey : setOfKeys) {

            System.out.println(setOfKey.toString() + "\n");
        }


    }

//For this example:
//1. Created a mock server in https://designer.mocky.io/design which returns the below JSON as response body.
//    {
//            "id": 1,
//            "first_name": "Claire",
//            "last_name": "Dennerley",
//            "email": "cdennerley0@uol.com.br",
//            "gender": "Male",
//            "skills": {
//             "name": "Testing",
//                "proficiency": "Medium"
//             }
//    }
//2. Converted that JSON response to Java Map Object.
//3. Do any operation that can be done on Java Map Object as per requirement.

    @Test

    public void convertJsonResponseToMapEx2() {

        Response jsonResponse = given().log().all().get("https://run.mocky.io/v3/919d44d3-8420-405f-a70e-759e10bb3bdb");

        System.out.println("---------------------");

        jsonResponse.prettyPrint();

        System.out.println("------------CONVERSION-PRINT INNER SKILLS VALUES---------");

        Map<String, Object> jsonToMapConvertedResponse = jsonResponse.as(new TypeRef<Map<String, Object>>() {
        });

        Map<String, String> innerjsonToMapConvertedResponse = (Map<String, String>) jsonToMapConvertedResponse.get("skills");

        System.out.println("Skill name is " + innerjsonToMapConvertedResponse.get("name"));
        System.out.println("Proficiency level is " + innerjsonToMapConvertedResponse.get("proficiency"));


    }

    //For this example:
//1. Validate resposne body of API request (GET HTTP METHOD): https://gorest.co.in/public/v1/users
//2. Converted that JSON response to Java Map Object.
//3. Do any operation that can be done on Java Map Object as per requirement.

    @Test

    public void convertJsonResponseToMapEx3() {

        Response jsonResponse = given().log().all().get("https://gorest.co.in/public/v1/users");

        System.out.println("---------------------");

        jsonResponse.prettyPrint();

        System.out.println("------PRINT VALUE OF 'next' in 'links' in 'pagination' in 'meta'------");

        Map<String, Object> outerJsonToMapConvertedResponse = jsonResponse.as(new TypeRef<Map<String, Object>>() {
        });

        Map<String, Object> meta = (Map<String, Object>) outerJsonToMapConvertedResponse.get("meta");

        Map<String, Object> pagination = (Map<String, Object>) meta.get("pagination");

        Map<String, Object> links = (Map<String, Object>) pagination.get("links");

        String valueOfNext = (String) links.get("next");

        System.out.println(valueOfNext);

        System.out.println("------PRINT VALUE OF 'next' in 'links' in 'pagination' in 'meta'------");


    }


}
