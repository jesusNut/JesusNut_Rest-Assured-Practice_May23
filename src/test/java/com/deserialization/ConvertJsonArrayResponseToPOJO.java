package com.deserialization;

import com.deserializationpojo.EmployeeData;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ConvertJsonArrayResponseToPOJO {


    // Deserialization of JSON API Response : Convert a part of JSON API response to POJO.
    // Rememeber that we have to create our POJO classes before hitting the request,
    // because we have to map our response against the already created POJO classes.

    // Deserializing POJO classes used:

    /**
     * {@link com.deserializationpojo.EmployeeData}
     */

    // API response expected:

//  [
//     {
//            "id": 1,
//            "first_name": "Sebastian",
//            "last_name": "Eschweiler",
//            "email": "sebastian@codingthesmartway.com"
//     },
//     {
//            "id": 2,
//            "first_name": "Steve",
//            "last_name": "Palmer",
//            "email": "steve@codingthesmartway.com"
//     },
//     {
//            "id": 3,
//            "first_name": "Ann",
//            "last_name": "Smith",
//            "email": "ann@codingthesmartway.com"
//     }
//	]


    @Test
    public void convertJsonArrayResponseToPOJOWay1() {

        Response response = given().log().all().get("https://run.mocky.io/v3/f44a2ea1-486f-46e6-8ed6-fbac03cd6b2f");

        System.out.println("-----------------");
        response.prettyPrint();

        System.out.println("-----------------");


        EmployeeData[] employeeDataAsResponse = response.as(EmployeeData[].class);

        for(EmployeeData emp : employeeDataAsResponse){

            System.out.println(emp.getId()+"---"+emp.getFirst_name()+"---"+emp.getLast_name()+"---"+emp.getEmail());
        }

    }

    //Second way to solve the problem asked in above example:
    

    @Test
    public void convertJsonArrayResponseToPOJOWay2() {

        Response response = given().log().all().get("https://run.mocky.io/v3/f44a2ea1-486f-46e6-8ed6-fbac03cd6b2f");

        System.out.println("-----------------");
        response.prettyPrint();

        System.out.println("-----------------");


        List<EmployeeData> employeeDataAsResponse = response.as(new TypeRef<List<EmployeeData>>() {});


        for(EmployeeData emp : employeeDataAsResponse){

            System.out.println(emp.getId()+"---"+emp.getFirst_name()+"---"+emp.getLast_name()+"---"+emp.getEmail());
        }

    }

}
