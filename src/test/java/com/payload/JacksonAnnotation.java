package com.payload;

import com.jacksonAnnotation.EmployeeComplex;
import com.jacksonAnnotation.Favfood;
import com.jacksonAnnotation.Marks;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class JacksonAnnotation {

    //Jackson annotations to be learnt:
    //Exclude properties having null values on class level : @JsonInclude(JsonInclude.Include.NON_NULL)
    //Exclude properties having null + empty values on class level : @JsonInclude(JsonInclude.Include.NON_EMPTY)
    //Exclude properties having default-values + null + empty on class level : @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    //order properties as per alphabetical order on class level : @JsonPropertyOrder(alphabetic = true)
    //custom order setting on class level :  @JsonPropertyOrder(value={"marks","favfood"})
    //ignore a property/list of properties on class level : @JsonIgnoreProperties({"first_name","last_name","favfood"})
    //ignore a property on property(field) level : write "@JsonIgnore" above the property to be ignored.
    //ignore a CLASS OBJECT property type for e.g. FAVFOOD type property named 'favfood' : write "@JsonIgnoreType" on class level in FAVFOOD class.



    @Test
    public void jacksonAnnotationPractice() {

        List<String> jobs = Arrays.asList("Trainer", "Tester", null);

        Marks m1 = new Marks(99, 99);
        Marks m2 = new Marks(88, 88);
        List<Marks> marks = Arrays.asList(m1, m2);

        List<String> dinner = Arrays.asList("chapathi", "milk");
        Favfood favfood = new Favfood("dosa", "rice", dinner);


        EmployeeComplex ecomp = new EmployeeComplex
                (0, 999, null, "", "Sandhya.Hridhul@oingg.com", jobs, marks, favfood, null, null);

        Response response = given().contentType(ContentType.JSON).
                body(ecomp).log().all().post("https://postman-echo.com/post");


        System.out.println("----------------------");
        response.prettyPrint();


    }

    // treat a String provided for a field/property during object creation as raw JSON : write "@JsonRawValue()" above the property in POJO.
    //Just to save time, instead of creating a new POJO class for a Java object (enclosed with '{}'), we are directly passing String.
    //Without this annotation the value of the said property; 'Address' here in EmployeeComplex.java; will look ugly on API request.
    //[Amuthan Paid Non-youtube : Video "Part 14 - Builder Pattern 2 , Project Lombak, Jackson Annotations"]

    @Test
    public void jacksonAnnotationPractice2() {

        List<String> jobs = Arrays.asList("Trainer", "Tester", null);

        Marks m1 = new Marks(99, 99);
        Marks m2 = new Marks(88, 88);
        List<Marks> marks = Arrays.asList(m1, m2);

        List<String> dinner = Arrays.asList("chapathi", "milk");
        Favfood favfood = new Favfood("dosa", "rice", dinner);


        EmployeeComplex ecomp =
                new EmployeeComplex
                        (0, 999, null, "", "Sandhya.Hridhul@oingg.com", jobs, marks, favfood, null, "{\n" +
                                "  \"City\": \"Delhi\",\n" +
                                "  \"Pincode\": 420420,\n" +
                                "  \"Landmarks\": [\n" +
                                "    \"DAv school\",\n" +
                                "    \"Famous Temple\",\n" +
                                "    \"XYZ shop\"\n" +
                                "  ]\n" +
                                "}");

        Response response = given().contentType(ContentType.JSON).
                body(ecomp).log().all().post("https://postman-echo.com/post");


        System.out.println("----------------------");
        response.prettyPrint();


    }


}
