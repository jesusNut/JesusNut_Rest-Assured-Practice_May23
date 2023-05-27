package com.deserialization;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class ConvertJsonResponseToJavaList {

// Under-the-hood, Rest assured uses Jackson(or similar library e.g. Gson) to deserialize JSON API response back to a Java List Object.
//If a deserializer is not used, we will get exception.

//For this example:
//1. Created a mock server in https://designer.mocky.io/design which returns the below JSON as response body.
//  [
//     {
//            "id": 1,
//            "first_name": "Claire",
//            "last_name": "Dennerley",
//            "email": "cdennerley0@uol.com.br",
//            "gender": "Male"
//     },
//     {
//            "id": 2,
//            "first_name": "Amod",
//            "last_name": "Mahajan",
//            "email": "amod@notfound.com",
//            "gender": "Male"
//     }
//  ]
//2. Converted that JSON response to Java List Object.
//3. Do any operation that can be done on Java List Object as per requirement.

    @Test

    public void convertJsonResponseToListEx1() {

        Response jsonResponse = given().log().all().get("https://run.mocky.io/v3/7db4566c-3f45-4ad9-a779-b48dbe5a7947");

        System.out.println("---------------------");

        jsonResponse.prettyPrint();

        System.out.println("------------CONVERSION-PRINT VALUES FOR ID-1---------");

        List<Object> jsonToListConvertedResponse = jsonResponse.as(new TypeRef<List<Object>>() {});

        Map<String,Object> firstDataSet = (Map<String, Object>)jsonToListConvertedResponse.get(0);

        Set<String> keysforFirstDataSet = firstDataSet.keySet();

        for(String str : keysforFirstDataSet){

            System.out.println(str +" is :"+firstDataSet.get(str));
        }

        System.out.println("------------CONVERSION-PRINT VALUES FOR ID-2---------");

        Map<String,Object> secondDataSet = (Map<String, Object>)jsonToListConvertedResponse.get(1);

        Set<String> keysforSecondDataSet = secondDataSet.keySet();

        for(String str : keysforSecondDataSet){

            System.out.println(str +" is :"+secondDataSet.get(str));
        }



    }


}
