package com.milfist.calculator;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class MultiplicationTest {

  @Test
  public void testMultiplicationEndPoint() {
    Integer number1 = 5;
    Integer number2 = 3;

    given()
        .queryParam("number1", number1)
        .queryParam("number2", number2)
        .when().get("/v1/multiplication")
        .then()
        .statusCode(200)
        .body(is("product = 15"));
  }
}
