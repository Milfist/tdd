package com.milfist.calculator;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PowStep {
  @When("^User calls endpoint \"(.*?)\" with body:$")
  public void userCallsEndpointWithBody(String arg0, String body) {

    System.out.println(arg0);
    System.out.println(body);

  }

  @Then("the response status code should be {int}")
  public void theResponseStatusCodeShouldBe(int arg0) {
  }

  @And("the response should be empty")
  public void theResponseShouldBeEmpty() {
  }
}
