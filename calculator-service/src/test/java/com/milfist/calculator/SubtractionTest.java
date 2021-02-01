package com.milfist.calculator;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class SubtractionTest {

  @Test
  public void given_Two_Numbers_Should_Be_Subtraction_First_Minus_Second() {
    Integer number1 = 5;
    Integer number2 = 3;

    given()
        .queryParam("number1", number1)
        .queryParam("number2", number2)
        .when().get("/v1/subtraction")
        .then()
        .statusCode(200)
        .body(is("subtraction = 2"));
  }
}
