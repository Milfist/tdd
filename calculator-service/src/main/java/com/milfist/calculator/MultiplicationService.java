package com.milfist.calculator;

import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1")
public class MultiplicationService {
  @GET
  @Path("/multiplication")
  @Produces(MediaType.APPLICATION_JSON)
  public String sum(@QueryParam("number1") Integer number1, @QueryParam("number2") Integer number2) {
    return "product = " + (number1 * number2);
  }

}
