package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import java.util.*;

import static io.restassured.RestAssured.given;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAssuredPostRequest1 {
    private static String requestBody = "{\n" +
            "    \"name\": \n" +
            "        {\n" +
            "            \"first\": \"Tatu\",\n" +
            "            \"last\": \"Saloranta\"\n" +
            "        },\n" +
            "\n" +
            "    \"title\": \"Jackson founder\",\n" +
            "    \"company\": \"FasterXML\",\n" +
            "    \"pets\" : [\n" +
            "        {\n" +
            "            \"type\": \"dog\",\n" +
            "            \"number\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"type\": \"fish\",\n" +
            "            \"number\": 50\n" +
            "        }\n" +
            "    ]\n" +
            "}";


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI="http://localhost:5000";
    }


    @Test
    public void verifyStatus200Get() throws IOException {
      String requestBody = Files.readString(Paths.get("src/main/resources/testrequest.json"));

        Response response = given()
                .contentType("application/json").body(requestBody)
                .when()
                .post("/data");
        /*Response response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("http://localhost:5000/data");*/

        Assert.assertEquals(200,response.getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode tree1 = objectMapper.readTree(requestBody);
        JsonNode tree2 = objectMapper.readTree(response.asString());

        Assert.assertEquals(tree1,tree2);



    }


}
