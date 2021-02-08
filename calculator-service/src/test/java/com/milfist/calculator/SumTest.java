package com.milfist.calculator;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class SumTest {

  @Test
  public void testSumEndpoint() {
    Integer number1 = 5;
    Integer number2 = 3;

    given()
        .queryParam("number1", number1)
        .queryParam("number2", number2)
        .when().get("/v1/sum")
        .then()
        .statusCode(200)
        .contentType(is(MediaType.APPLICATION_JSON))
        .body(containsString("sum"), containsString("8"));
  }
}
