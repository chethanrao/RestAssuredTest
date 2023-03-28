package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import java.util.*;

import static io.restassured.RestAssured.given;
public class RestAssuredPostRequest {
    private static String requestBody = "[{\n" +
            "  \"id\": 1,\n" +
            "  \"title\": \"123\",\n" +
            "  \"userId\": \"23\" \n}]";

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI="https://my-json-server.typicode.com/typicode/demo/db";
    }

@Test
    public void verifyStatus200(){

    Response response = given().body("test").when().post();

    Assert.assertEquals(200,response.getStatusCode());

    List<Map<Object,Object>> list = response.jsonPath().get("comments");


    Assert.assertEquals(1,list.get(0).get("id"));



   }

}
