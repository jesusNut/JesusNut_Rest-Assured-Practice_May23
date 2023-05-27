package com.assignments;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class ComplexJSONOmPrakash97 {

    @Test

    public void solution() {

        //creating complex JSON body using Maps and Arrays.

        Map<String, Object> innerData1 = new LinkedHashMap<>();
        innerData1.put("color", "black");
        innerData1.put("category", "hue");
        innerData1.put("type", "primary");
        List<Integer> numbers = Arrays.asList(255, 255, 255, 1);
        Map<String, Object> code = new LinkedHashMap<>();
        code.put("rgba", numbers);
        code.put("hex", "#000");
        innerData1.put("code", code);

        Map<String, Object> innerData2 = new LinkedHashMap<>();
        innerData2.put("color", "white");
        innerData2.put("category", "value");
        List<Integer> numbers1 = Arrays.asList(0, 0, 0, 1);
        Map<String, Object> code1 = new LinkedHashMap<>();
        code1.put("rgba", numbers1);
        code1.put("hex", "#FFF");
        innerData2.put("code", code1);

        Map<String, Object> outerData = new LinkedHashMap<>();

        List<Object> nestedArrayData = new LinkedList<>();
        nestedArrayData.add(innerData1);
        nestedArrayData.add(innerData2);

        outerData.put("colors", nestedArrayData);


        Response response1 =
                given().log().all().body(outerData).header("Content-Type", "application/json")
                        .header("x-mock-match-request-body", true).
                        post("https://9d4e48d8-4495-43b6-8b4c-190d02ffbd0a.mock.pstmn.io/somedata");

        System.out.println("---------------------");

        response1.prettyPrint();


    }


}


