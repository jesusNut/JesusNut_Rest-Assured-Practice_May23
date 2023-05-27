package com.payload;

import com.pojo.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Serialization_PostRequest_POJO {

    //1. Creating POST request creating request body using POJO - SIMPLE JSON payload.


    // Write : {} - write a class
    //         [] - List<Type>
//    Employee payload to be sent :
//   {
//        "id": 1,
//        "first_name": "Sebastian",
//        "last_name": "Eschweiler",
//        "email": "sebastian@codingthesmartway.com"
//    }

    @Test
    public void postCallPayload_SimplePojo() {

        Employee emp = new Employee(221, "Abhishek", "Bhardwaj", "oing.poing@toing.com");

        Response response = given().contentType(ContentType.JSON).
                body(emp).log().all().post("https://postman-echo.com/post");

        System.out.println("----------------------");
        response.prettyPrint();

    }

    //2. Creating POST request creating request body using POJO - COMPLEX JSON payload (Scenario -1).

//        {
//        "id": 999,
//            "first_name": "Sandhya",
//            "last_name": "Hridhul",
//            "email": "sandhya.hridhul@oingg.com",
//            "job": [
//        "tester",
//                "trainer"
//	],
//        "marks": [{
//        "Tamil": 99,
//                "English": 99
//    }, {
//        "Tamil": 88,
//                "English": 88
//    }],
//        "favfood": {
//        "breakfast": "dosa",
//                "lunch": "rice",
//                "dinner": [
//        "chapathi",
//                "milk"
//		]
//    }
// }

    @Test
    public void postCallPayload_ComplexPojo1() {

        List<String> jobs = Arrays.asList("Trainer", "Tester", "Farmer");

        Marks m1 = new Marks(99, 99);
        Marks m2 = new Marks(88, 88);
        List<Marks> marks = Arrays.asList(m1, m2);

        List<String> dinner = Arrays.asList("chapathi", "milk");
        Favfood favfood = new Favfood("dosa", "rice", dinner);


        EmployeeComplex ecomp =
                new EmployeeComplex(999, "Sandhya", "Hridhul", "Sandhya.Hridhul@oingg.com", jobs, marks, favfood);

        Response response = given().contentType(ContentType.JSON).
                body(ecomp).log().all().post("https://postman-echo.com/post");


        System.out.println("----------------------");
        response.prettyPrint();


    }


    //3. Creating POST request creating request body using POJO - COMPLEX JSON payload (Scenario -2).


//	JSON payload format starting with '[]':

//	[   {
//		  "firstName" : "Mike",
//		  "lastName" : "harvey",
//		  "age" : 34
//		}, {
//		  "firstName" : "Nick",
//		  "lastName" : "young",
//		  "age" : 75
//		} ]

    @Test
    public void postCallPayload_ComplexPojo2() {

        EmployeeArray earr1 = new EmployeeArray("Mike", "harvey", 34);
        EmployeeArray earr2 = new EmployeeArray("Nick", "young", 75);

        List<EmployeeArray> list = Arrays.asList(earr1, earr2);

        Response response = given().contentType(ContentType.JSON).
                body(list).log().all().post("https://postman-echo.com/post");

        System.out.println("----------------------");
        response.prettyPrint();

    }


}
