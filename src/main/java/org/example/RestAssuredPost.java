package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

import java.util.*;

public class RestAssuredPost{
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    private static String requestBody = "{\n" +
            "  \"title\": \"test\",\n" +
            "  \"body\": \"123\",\n" +
            "  \"userId\": \"23\" \n}";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void verifyStatusCode() {
        Response response = given().header("Content-Type", "application/json").and().when().body(requestBody).post("/posts");
        Assert.assertEquals("Status code is not 201", 201, response.getStatusCode());
        Assert.assertEquals("test", response.jsonPath().getString("title"));
        Assert.assertEquals("123", response.jsonPath().getString("body"));
        Assert.assertEquals("23", response.jsonPath().getString("userId"));
    }

}