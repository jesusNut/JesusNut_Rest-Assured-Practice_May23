package com.deserialization;

import com.deserializationpojo.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ConvertJsonObjectResponseToPOJO {

    // we will do a get call and deserialize the response below to map it to POJO
    // classes.
    // Rememeber that we have to create our POJO classes before hitting the request,
    // because we have to map our response against the already created POJO classes.

    // Deserializing POJO classes used:

    /**
     * {@link EmployeeEx1}
     * {@link SkillsEx1}
     */

    // Expected response:

//        {
//            "id": 1,
//            "first_name": "Claire",
//            "last_name": "Dennerley",
//            "email": "cdennerley0@uol.com.br",
//            "gender": "Male",
//            "skills": {
//             "name": "Testing",
//                "proficiency": "Medium"
//               }
//         }
    @Test
    public void convertJsonObjectResponseToPOJOExample1() {

        Response response = given().log().all().get("https://run.mocky.io/v3/919d44d3-8420-405f-a70e-759e10bb3bdb");

        System.out.println("-----------------");
        response.prettyPrint();

        System.out.println("-----------------");

        EmployeeEx1 responseAsEmployeeObject = response.as(EmployeeEx1.class);

        System.out.println(responseAsEmployeeObject.getId());
        System.out.println(responseAsEmployeeObject.getFirst_name());
        System.out.println(responseAsEmployeeObject.getLast_name());
        System.out.println(responseAsEmployeeObject.getEmail());
        System.out.println(responseAsEmployeeObject.getGender());

        SkillsEx1 skills = responseAsEmployeeObject.getSkills();

        System.out.println("The skill is " + skills.getName() + " and proficiency is : " + skills.getProficiency());

    }

    // Deserializing POJO classes used:

    /**
     * {@link com.deserializationpojo.EmployeeEx2}
     * {@link com.deserializationpojo.SkillsEx2}
     */

    // Expected response:

//      {
//            "id": 1,
//            "first_name": "Claire",
//            "last_name": "Dennerley",
//            "email": "cdennerley0@uol.com.br",
//            "gender": "Male",
//            "skills": [
//           {
//                "name": "Testing",
//                "proficiency": "Medium"
//            },
//           {
//                "name": "Aquascaper",
//                "proficiency": "High"
//            }
//          ]
//      }
    @Test
    public void convertJsonObjectResponseToPOJOExample2() {

        Response response = given().log().all().get("https://run.mocky.io/v3/3c9189fa-2e1b-4daf-b4d5-53cbde1b0d9e");

        System.out.println("-----------------");
        response.prettyPrint();

        System.out.println("-----------------");

        EmployeeEx2 responseAsEmployeeObject = response.as(EmployeeEx2.class);

        System.out.println(responseAsEmployeeObject.getId());
        System.out.println(responseAsEmployeeObject.getFirst_name());
        System.out.println(responseAsEmployeeObject.getLast_name());
        System.out.println(responseAsEmployeeObject.getEmail());
        System.out.println(responseAsEmployeeObject.getGender());

        List<SkillsEx2> skills = responseAsEmployeeObject.getSkills();

        for (SkillsEx2 sk : skills) {

            System.out.println("The skill is " + sk.getName() + " and proficiency is : " + sk.getProficiency());
        }

    }

    // we will do a get call and deserialize the response below to map it to POJO
    // classes.
    // Rememeber that we have to create our POJO classes before hitting the request,
    // because we have to map our response against the already created POJO classes.

    // Deserializing POJO classes used:

    /**
     * {@link com.deserializationpojo.PageData}
     * {@link com.deserializationpojo.Support}
     * {@link com.deserializationpojo.UserData}
     */

    // Expected response:

//    {
//            "page": 2,
//            "per_page": 6,
//            "total": 12,
//            "total_pages": 2,
//            "data": [
//        {
//            "id": 7,
//                "email": "michael.lawson@reqres.in",
//                "first_name": "Michael",
//                "last_name": "Lawson",
//                "avatar": "https://reqres.in/img/faces/7-image.jpg"
//        },
//        {
//            "id": 8,
//                "email": "lindsay.ferguson@reqres.in",
//                "first_name": "Lindsay",
//                "last_name": "Ferguson",
//                "avatar": "https://reqres.in/img/faces/8-image.jpg"
//        },
//        {
//            "id": 9,
//                "email": "tobias.funke@reqres.in",
//                "first_name": "Tobias",
//                "last_name": "Funke",
//                "avatar": "https://reqres.in/img/faces/9-image.jpg"
//        },
//        {
//            "id": 10,
//                "email": "byron.fields@reqres.in",
//                "first_name": "Byron",
//                "last_name": "Fields",
//                "avatar": "https://reqres.in/img/faces/10-image.jpg"
//        },
//        {
//            "id": 11,
//                "email": "george.edwards@reqres.in",
//                "first_name": "George",
//                "last_name": "Edwards",
//                "avatar": "https://reqres.in/img/faces/11-image.jpg"
//        },
//        {
//            "id": 12,
//                "email": "rachel.howell@reqres.in",
//                "first_name": "Rachel",
//                "last_name": "Howell",
//                "avatar": "https://reqres.in/img/faces/12-image.jpg"
//        }
//  ],
//        "support": {
//                "url": "https://reqres.in/#support-heading",
//                "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
//    }
//    }
    @Test
    public void convertJsonObjectResponseToPOJOExample3() {

        Response response = given().log().all().get("https://reqres.in/api/users?page=2");

        System.out.println("-----------------");
        response.prettyPrint();

        System.out.println("-----------------");

        PageData responseAsPageDataObject = response.as(PageData.class);

        System.out.println(responseAsPageDataObject.getPage());
        System.out.println(responseAsPageDataObject.getPer_page());
        System.out.println(responseAsPageDataObject.getTotal());
        System.out.println(responseAsPageDataObject.getTotal_pages());
        List<UserData> data = responseAsPageDataObject.getData();

        System.out.println("-----------------");

        for (UserData ud : data) {

            System.out.println(ud.getId() + "--" + ud.getFirst_name() + "--" + ud.getLast_name() + "--" + ud.getAvatar());
        }

        System.out.println("-----------------");


        System.out.println("Support url is : " + responseAsPageDataObject.getSupport().getUrl());
        System.out.println("Text for support section is : " + responseAsPageDataObject.getSupport().getText());


    }


}
