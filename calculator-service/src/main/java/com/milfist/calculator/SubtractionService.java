package com.milfist.calculator;

import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1")
public class SubtractionService {
  @GET
  @Path("/subtraction")
  @Produces(MediaType.TEXT_PLAIN)
  public String subtraction(@QueryParam("number1") Integer number1, @QueryParam("number2") Integer number2) {
    return "subtraction = " + (number1 - number2);
  }
}
