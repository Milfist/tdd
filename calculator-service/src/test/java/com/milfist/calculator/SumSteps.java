package com.milfist.calculator;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumSteps {

  private static final String SUM_PATH = "/v1/sum";

  private final InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream("sum.json");
  private final String jsonString = new Scanner(jsonInputStream, "UTF-8").useDelimiter("\\Z").next();

  private final WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());
  private final CloseableHttpClient httpClient = HttpClients.createDefault();

  private HttpResponse response = null;

  @When("^User calls endpoint /v1/sum with queryparams number1 equal to \"(.*?)\" and number2 equal to \"(.*?)\"")
  public void callSumEndPoint(Integer number1, Integer number2) throws IOException, URISyntaxException {

    wireMockServer.start();

    configureFor("localhost", wireMockServer.port());
    stubFor(get(urlPathEqualTo(SUM_PATH)) // Increible: para usar parametros debemos usar urlPathEqualTo en lugar de urlEqualTo
        .withHeader("accept", equalTo(MediaType.APPLICATION_JSON))
        .withQueryParam("number1", equalTo(number1.toString()))
        .withQueryParam("number2", equalTo(number2.toString()))
        .willReturn(aResponse().withBody(jsonString)));

    // Creamos la URI con los query params
    URIBuilder builder = new URIBuilder("http://localhost:" + wireMockServer.port() + SUM_PATH);
    builder.setParameter("number1", number1.toString()).setParameter("number2", number2.toString());

    //Creamos la petición get
    HttpGet request = new HttpGet(builder.build());
    request.addHeader("accept", MediaType.APPLICATION_JSON);

    //Ejecutamos la peticion get y nos devuelve la respuesta que convertimos en string
    response = httpClient.execute(request);

    //Validamos los asserts
    verify(getRequestedFor(urlEqualTo(builder.build().getPath().concat("?").concat(builder.build().getQuery()))).withHeader("accept", equalTo(MediaType.APPLICATION_JSON)));

    wireMockServer.stop();

  }

  @Then("^User receives http status code respond of (\\d+)$")
  public void statusRespond(int statusCode) {
    assertEquals(statusCode, response.getStatusLine().getStatusCode());
  }

  @And("^response contains operation \"(.*?)\" and result (\\d+)$")
  public void checkResponse(String operation, Integer result) throws IOException {
    String responseString = convertResponseToString(response);
    assertThat(responseString, containsString("\"operation\": \"sum\""));
    assertThat(responseString, containsString("\"result\": \"10\""));

    Gson gson = new Gson();

    CalculatorResult calculatorResult = gson.fromJson(responseString, CalculatorResult.class);
    assertEquals(operation, calculatorResult.getOperation());
    assertEquals(result, Integer.parseInt(calculatorResult.getResult()));
  }

  private String convertResponseToString(HttpResponse response) throws IOException {
    InputStream responseStream = response.getEntity().getContent();
    Scanner scanner = new Scanner(responseStream, "UTF-8");
    String responseString = scanner.useDelimiter("\\Z").next();
    scanner.close();
    return responseString;
  }
}
