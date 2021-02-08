package com.milfist.calculator;

import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1")
public class SumService {

  @GET
  @Path("/sum")
  @Produces(MediaType.APPLICATION_JSON)
  public Response sum(@QueryParam("number1") Integer number1, @QueryParam("number2") Integer number2) {
    CalculatorResult result = new CalculatorResult("sum", String.valueOf(Integer.sum(number1, number2)));
    return Response.ok(result, MediaType.APPLICATION_JSON).build();
  }

}
