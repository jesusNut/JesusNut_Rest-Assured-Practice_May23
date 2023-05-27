package com.concepts;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UploadDownload {

//To demonstrate working with multi-part form data - UPLOAD.
//Here demonstarted 'control Name' is 'file'.
//Always check your API request for content name it supports from your developer.
//https://www.youtube.com/watch?v=N7foO9nBF0w
// Take care of Header value here. Don't use 'Content-Type' / its method 'contentType()'/ its value 'application/json' here.
// Hit the request first from Postman and on successful response, use the same request header here in header() method.



    @Test

    public void multipartFormData_Upload() {

        Response response = given().log().all().
                baseUri("http://localhost:8080").
                basePath("/uploadFile").
                header("Content-Type", "multipart/form-data; boundary=--------------------------551696765227944218284653").
                multiPart(new File(System.getProperty("user.dir") + "/testdata.json")).
                post();

        System.out.println("-----------------------");

        response.prettyPrint();


    }


    //To demonstrate working with multi-part form data - DOWNLOAD.
    //Just a simple get request (nothing as a concept here!!!)

    @Test

    public void multipartFormData_download() {

        Response response = given().log().all().
                baseUri("http://localhost:8080").
                basePath("/downloadFile/testdata.json").
                get();

        System.out.println("-----------------------");

        response.prettyPrint();


    }

    //To demonstrate working with multi-part form data - UPLOADING MULTIPLE FILES AT ONCE.
    //Here demonstarted 'control Name' is 'files'.
    //Always check your API Documentation for content name it supports from your developer.

    @Test

    public void multipartFormData_MultipleFileUpload() {

        Response response = given().log().all().
                baseUri("http://localhost:8080").
                basePath("/uploadMultipleFiles").
                header("Content-Type", "multipart/form-data; boundary=--------------------------551696765227944218284653").
                multiPart("files",new File(System.getProperty("user.dir") + "/testdata.json")).
                multiPart("files",new File(System.getProperty("user.dir") + "/testdata1.json")).
                post();

        System.out.println("-----------------------");

        response.prettyPrint();


    }


}
