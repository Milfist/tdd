package com.milfist.calculator;

import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class SumService {

  @Path("/v1")
  public class Sum {

    @GET
    @Path("/sum")
    @Produces(MediaType.TEXT_PLAIN)
    public String greeting(@QueryParam("number1") Integer number1, @QueryParam("number2") Integer number2) {
      return "sum = " + (Integer.sum(number1, number2));
    }
  }

}
