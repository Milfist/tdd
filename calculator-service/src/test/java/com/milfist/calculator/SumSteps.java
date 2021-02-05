package com.milfist.calculator;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumSteps {

  private static final String CREATE_PATH = "/create";
  private static final String APPLICATION_JSON = "application/json";

  private final InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream("cucumber.json");
  private final String jsonString = new Scanner(jsonInputStream, "UTF-8").useDelimiter("\\Z").next();

  private final WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());
  private final CloseableHttpClient httpClient = HttpClients.createDefault();

  @Given("two numbers")
  public void givenTwoNumbers() {
  }

  @When("User call sum endpoint")
  public void callSumEndPoint() throws IOException {

    wireMockServer.start();

    configureFor("localhost", wireMockServer.port());
    stubFor(post(urlEqualTo(CREATE_PATH))
        .withHeader("content-type", equalTo(APPLICATION_JSON))
        .withRequestBody(containing("testing-framework"))
        .willReturn(aResponse().withStatus(200)));

    HttpPost request = new HttpPost("http://localhost:" + wireMockServer.port() + CREATE_PATH);
    StringEntity entity = new StringEntity(jsonString);
    request.addHeader("content-type", APPLICATION_JSON);
    request.setEntity(entity);
    HttpResponse response;
    response = httpClient.execute(request);

    assertEquals(200, response.getStatusLine().getStatusCode());
    verify(postRequestedFor(urlEqualTo(CREATE_PATH))
        .withHeader("content-type", equalTo(APPLICATION_JSON)));

    wireMockServer.stop();


  }

  @Then("http status respond is 200")
  public void statusRespond() {
  }
}
