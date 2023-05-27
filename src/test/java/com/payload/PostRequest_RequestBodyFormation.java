package com.payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javafaker.Faker;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static io.restassured.RestAssured.given;

public class PostRequest_RequestBodyFormation {

    //1. Creating POST request passing request body as a String.

    @Test

    public void postCallPayloadAsString() {

        Response response = given().contentType(ContentType.JSON).
                body("{\n" +
                        "    \"name\": \"Oing Toing\",\n" +
                        "    \"isbn\": \"AB887766\",\n" +
                        "    \"aisle\": 2299,\n" +
                        "    \"author\":\"MorariBapoo\"\n" +
                        "}").log().all().post("https://rahulshettyacademy.com/Library/Addbook.php");

        response.prettyPrint();
    }

    //2. Creating POST request passing request body from  External File.

    @Test

    public void postCallPayloadFromExternalFile() {

        File externalJSONFile = new File(System.getProperty("user.dir") + "/testdata.json");

        Response response = given().contentType(ContentType.JSON).
                body(externalJSONFile).log()
                .all()
                .post("https://rahulshettyacademy.com/Library/Addbook.php");

        response.prettyPrint();

    }

    //3. Creating POST request passing body from  External File -represented as String.

    @Test

    public void postCallPayloadFromExternalFileAsString() throws IOException {

        Faker finstance = Faker.instance();
        String bookname = finstance.book().title();
        String authorname = finstance.book().author();
        String isbn = "ab" + finstance.numerify("##CC##");


        byte[] reqBodyBytes = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/testdata.json"));
        String reqBodyString = new String(reqBodyBytes);
        String modifiedReqBodyString = reqBodyString.replace("NameWildCard", bookname).replace("ISBNWildCard", isbn)
                .replace("AuthorWildCard", authorname);


        Response response = given().contentType(ContentType.JSON).
                body(modifiedReqBodyString).log().all().post("https://rahulshettyacademy.com/Library/Addbook.php");

        response.prettyPrint();

    }

    // Under-the-hood, Rest assured uses Jackson(or similar library e.g. Gson) to serialize 'JSON representation using Java Objects -Map and List'
    // to 'JSON API paylod/request body' to be sent to server.
    //If a Serializer is not used, we will get exception.

    //4. Creating POST request creating request body from Java Objects.
    //{} -> use Maps
    //[] -> use List

    @Test

    public void postCallPayload_JavaObjectsForm() {

        //request body in JSON which needs to be constucted using Java Objects.
//        {
//            "isbn": "123-456-222",
//                "author": {
//            "lastname": "Doe",
//                    "firstname": "Jane"
//        },
//            "editor": {
//            "lastname": "Smith",
//                    "firstname": "Jane"
//        },
//            "title": "The Ultimate Database Study Guide",
//                "category": [
//            "Non-Fiction",
//                    "Technology"
//  ]
//        }

        Map<String, Object> mdata = new LinkedHashMap<>();
        mdata.put("isbn", "123-456-777");
        Map<String, String> author = new HashMap<>();
        author.put("lastname", "Doe");
        author.put("firstname", "Uiii");
        mdata.put("author", author);
        Map<String, String> editor = new HashMap<>();
        editor.put("lastname", "Chonga");
        editor.put("firstname", "Kapoor");
        mdata.put("editor", editor);
        mdata.put("title", "The Ultimate Database Study Guide");
        List<String> category = Arrays.asList("Non-Fiction", "Technology");
        mdata.put("category", category);

        Response response = given().contentType(ContentType.JSON).
                body(mdata).log().all().post("https://postman-echo.com/post");

        response.prettyPrint();

    }

    // Under-the-hood, Rest assured uses Jackson(or similar library e.g. Gson) to serialize
    // 'JSON representation using Java Objects - JSONArray and JSONObject classes of org.JSON library'
    // to 'JSON API paylod/request body' to be sent to server.
    //If a Serializer is not used, we will get exception.

    //5. Creating POST request creating request body from an external org.JSON library.
    //https://www.baeldung.com/java-org-json
    //{} -> use Class JSONObject
    //[] -> use Class JSONArray

    //How it works :
    //1. User creates request body using JSONArray and JSONObject classes of org.JSON library and its methods.
    //2. User converts that to String OR MAP using methods - toMap() or toString();
    //3. RestAssured uses Jackson Library to create a JSON and sends it to server (internal operation).

    //Cons :  JSONObject -> similar to Java's native Map-like object, which stores unordered key-value pairs.
    // So, no way to create a request body where order of key can be maintained. BUT, with Java Objects we could have used - LinkedHashMap.

    @Test

    public void postCallPayload_ExternalLibJSON() {

//request body in JSON which needs to be constucted.
//        {
//            "isbn": "123-456-222",
//                "author": {
//            "lastname": "Doe",
//                    "firstname": "Jane"
//        },
//            "editor": {
//            "lastname": "Smith",
//                    "firstname": "Jane"
//        },
//            "title": "The Ultimate Database Study Guide",
//                "category": [
//            "Non-Fiction",
//                    "Technology"
//  ]
//        }

        //1. User creates request body using JSONArray and JSONObject classes of org.JSON library and its methods.

        JSONObject mdata = new JSONObject();
        mdata.put("isbn", "123-456-777");
        JSONObject author = new JSONObject();
        author.putOnce("lastname", "Rani");
        author.put("firstname", "Titili");
        mdata.put("author", author);
        JSONObject editor = new JSONObject();
        editor.put("lastname", "Pakalu");
        editor.put("firstname", "Papito");
        mdata.put("editor", editor);
        mdata.accumulate("title", "The Ultimate Kidney Eating Study Guide");
        JSONArray category = new JSONArray();
        category.putAll(Arrays.asList("Non-Fiction", "Technology"));
        mdata.put("category", category);

        //2. User converts that request body to JAVA String Object OR JAVA MAP Object using methods - toMap() or toString();

        Map<String, Object> reqBodyDataInJavaMap = mdata.toMap();

        //3. RestAssured uses Jackson Library to create a JSON and sends it to server (internal operation).

        Response response = given().contentType(ContentType.JSON).
                body(reqBodyDataInJavaMap).log().all().post("https://postman-echo.com/post");

        System.out.println("----------------------");

        response.prettyPrint();


    }

    // Under-the-hood, Rest assured uses Jackson(or similar library e.g. Gson) to serialize
    // 'JSON representation using Java Objects - JsonObject and JsonArray classes of com.google.code.gson library'
    // to 'JSON API paylod/request body' to be sent to server.
    //If a Serializer is not used, we will get exception.

    //6. Creating POST request creating request body from an external com.google.code.gson library.
    //https://www.youtube.com/watch?v=1TFUdSucGCo&list=PLMZdod-kiMhIy1yTFvU_Fb8aJy80KMY-H&index=6
    //https://zetcode.com/java/gson/
    //https://www.youtube.com/watch?v=q7bkBoD8Maw&list=PLK0V_H0fCvPhFH9P7dSwus66FhqUiQo7S&index=9


    //{} -> use Class JsonObject
    //[] -> use Class JsonArray
    // https://stackoverflow.com/questions/22585970/how-to-add-an-object-as-a-property-of-a-jsonobject-object

    //How it works :
    //1. User creates request body using JsonArray and JsonObject classes of com.google.code.gson library and its methods.
    //2. RestAssured uses GSON Library to create a JSON and sends it to server (internal operation).

    //Cons :  JSONObject -> similar to Java's native Map-like object, which stores unordered key-value pairs.
    // So, no way to create a request body where order of key can be maintained. BUT, with Java Objects we could have used - LinkedHashMap.


    @Test

    public void postCallPayload_ExternalLibGSON() {

//request body in JSON which needs to be constucted .
//        {
//            "isbn": "123-456-222",
//                "author": {
//            "lastname": "Doe",
//                    "firstname": "Jane"
//        },
//            "editor": {
//            "lastname": "Smith",
//                    "firstname": "Jane"
//        },
//            "title": "The Ultimate Database Study Guide",
//                "category": [
//            "Non-Fiction",
//                    "Technology"
//  ]
//        }

        //1. User creates request body using JsonObject and JsonArray classes of com.google.gson library and its methods.

        JsonObject mdata = new JsonObject();
        mdata.addProperty("isbn", "123-456-999");
        JsonObject author = new JsonObject();
        author.addProperty("lastname", "Vinayak");
        author.addProperty("firstname", "Titti");
        mdata.add("author", author);
        JsonObject editor = new JsonObject();
        editor.addProperty("lastname", "Hardayal");
        editor.addProperty("firstname", "Sharma");
        mdata.add("editor", editor);
        mdata.addProperty("title", "The Ultimate Liver Eating Study Guide");
        JsonArray category = new JsonArray();
        category.add("Non-Fiction");
        category.add("Technology");
        mdata.add("category", category);

        //2. RestAssured uses GSON Library to create a JSON out of JsonObject type 'mdata' Object and sends it to server (internal operation).
        //For serilaization also, internally GSON library is used.

        Response response = given().contentType(ContentType.JSON).
                body(mdata,ObjectMapperType.GSON).log().all().post("https://postman-echo.com/post");

        System.out.println("----------------------");

        response.prettyPrint();


    }

    // Under-the-hood, Rest assured uses Jackson(or similar library e.g. Gson) to serialize
    // 'JSON representation using Java Objects - ObjectMapper, ObjectNode and ArrayNode  classes of com.fasterxml.jackson.core library'
    // to 'JSON API paylod/request body' to be sent to server.
    //If a Serializer is not used, we will get exception.

    //7. Creating POST request creating request body from an external com.fasterxml.jackson.core library.
    //Udemy-OmPrakash(Section-24/120)

    //Main class to be used - ObjectMapper
    //{} -> use Class ObjectNode
    //[] -> use Class ArrayNode

    //How it works :
    //1. User creates request body using ObjectNode and ArrayNode classes of com.fasterxml.jackson.core library and its methods.
    //2. RestAssured uses Jackson Library to create a JSON and sends it to server (internal operation).


    @Test

    public void postCallPayload_ExternalLibJackson() throws JsonProcessingException {

//https://stackoverflow.com/questions/38705890/what-is-the-difference-between-objectnode-and-jsonnode-in-jackson

//request body in JSON which needs to be constucted .
//        {
//            "isbn": "123-456-222",
//                "author": {
//            "lastname": "Doe",
//                    "firstname": "Jane"
//        },
//            "editor": {
//            "lastname": "Smith",
//                    "firstname": "Jane"
//        },
//            "title": "The Ultimate Database Study Guide",
//                "category": [
//            "Non-Fiction",
//                    "Technology"
//  ]
//        }

        //1. User creates request body using ObjectNode and ArrayNode classes of com.fasterxml.jackson.core library and its methods.

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode outerObjectNode = objectMapper.createObjectNode();
        outerObjectNode.put("isbn", "123-456-999");

        ObjectNode author = objectMapper.createObjectNode();
        author.put("lastname", "Rajan");
        author.put("firstname", "Bhatti");

        outerObjectNode.set("author", author);

        ObjectNode editor = objectMapper.createObjectNode();
        editor.put("lastname", "Hilawal");
        editor.put("firstname", "Puttoh");

        outerObjectNode.set("editor", editor);

        outerObjectNode.put("title", "The Ultimate Neck Eating Study Guide for Zombies");

        ArrayNode category = objectMapper.createArrayNode();
        category.add("Non-Fiction");
        category.add("Technology");

        outerObjectNode.set("category", category);

        //2. Extract JSON string out of the above. [Optional if you want to pass JSON string in body() method of API request below.]

       // String jsonRequestBodyInString = objectMapper.writeValueAsString(outerObjectNode);


        //3. Feed either
        // a. JSON string fetched from step 2 to body() in API request.[e.g. xx.body(jsonRequestBodyInString).xx]
        // b. the ObjectNode object created to body() in API request.[e.g.  xx.body(outerObjectNode).xx]
        // c. the ObjectNode object created along with ObjectMapperType Enum object to body() in API request.
        // [e.g. xx.body(outerObjectNode, ObjectMapperType.JACKSON_2).xx ]

        Response response = given().contentType(ContentType.JSON).
                body(outerObjectNode, ObjectMapperType.JACKSON_2).log().all().post("https://postman-echo.com/post");

        System.out.println("----------------------");

        response.prettyPrint();


    }




}
