package com.deserialization;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class ConvertDynamicJsonResponseToJavaObject {

// Under-the-hood, Rest assured uses Jackson(or similar library e.g. Gson) to deserialize JSON API response back to a Java List Object.
//If a deserializer is not used, we will get exception.

//We have to write the logic in such a way that whether the json response is starting as a JSON Array or as a JSON Object,
//both should be converted to Java Object Type through deserialization.

//For this example:
//1. Created two mock servers in https://designer.mocky.io/design which returns the below JSON as response body.

//FIRST  TIME (JSON RESPONSE STARTING AS JSON OBJECT WITH {} BRACES) :

//    {
//            "accountNo": 111,
//            "balance": 20.34,
//            "first_name": "Claire",
//            "last_name": "Dennerley",
//            "email": "cdennerley0@uol.com.br",
//            "gender": "Male"
//    }


//SECOND TIME (JSON RESPONSE STARTING AS JSON ARRAY WITH [] BRACES)

//  [
//    {
//             "accountNo": 111,
//            "balance": 20.34,
//            "first_name": "Claire",
//            "last_name": "Dennerley",
//            "email": "cdennerley0@uol.com.br",
//            "gender": "Male"
//    },
//    {
//        "accountNo": 222,
//            "balance": 200.34,
//            "first_name": "Claire",
//            "last_name": "Dennerley",
//            "email": "cdennerley0@uol.com.br",
//            "gender": "Male"
//    }
//  ]

//Solution : Use 'instanceOf' operator.

    @Test

    public void convertDynamicJsonResponseEx1() {

        Response jsonResponse = given().log().all().get("https://run.mocky.io/v3/74b1d9af-8ca6-4115-be30-7a0c14123f4a");

        System.out.println("---------------------");

        jsonResponse.prettyPrint();

        System.out.println("---------------------");

        //Deserialize JSON response as 'Object'

        Object responseObject = jsonResponse.as(Object.class);

        //use instanceOf operator to check whether the API response starts with '{}'(means MAP) or '[]'(means LIST)

        if (responseObject instanceof Map) {

            Map<String, Object> responseAsMap = (Map<String, Object>) responseObject;

            Set<String> setOfKeys = responseAsMap.keySet();

            for (String setOfKey : setOfKeys) {

                System.out.println(setOfKey + "\n");
            }

            //do whetever the f##k you want to with this MAP.


        } else if (responseObject instanceof List) {

            List<Map<String, Object>> responseAsList = (List<Map<String, Object>>) responseObject;

            System.out.println("This API response is a JSON Array type which has " + responseAsList.size() + " nested JSON Objects");


        } else {

            System.out.println("The API response from the server is neither a JSON Array or JSON Object");
        }


    }


}
