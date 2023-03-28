package org.example;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
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
public class TestPostRequest2 {
    @Test
    public void testPostRequest() throws IOException{
        String requestBody = Files.readString(Paths.get("src/main/resources/testrequest.json"));
        String responseBody = Files.readString(Paths.get("src/main/resources/testresponse.json"));
        Response response = given().contentType("application/json").body(requestBody).when().post("http://localhost:5000/data");
        Assert.assertEquals(200,response.getStatusCode());
        ObjectMapper objectMapper1=new ObjectMapper();
        JsonNode jsonNode1 = objectMapper1.readTree(response.asString());
        JsonNode jsonNode2 = objectMapper1.readTree(responseBody);
        Assert.assertEquals(jsonNode1,jsonNode2);
    }
}
