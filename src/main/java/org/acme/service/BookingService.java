package org.acme.service;

import io.vavr.control.Either;
import org.acme.dto.BookingRequest;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Service to create Booking request object.
 * Use application/json to aad
 *
 * @author created by N.Martignole, Lunatech, on 2019-06-11.
 */
@Path("bookingService")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingService {
    @POST
    @Path("hotel/{hotelId}")
    public Response create(@PathParam String hotelId,
                           BookingRequest bookingRequest) {
        System.out.println("create new booking for " + hotelId);
        if (hotelId == null) {
            throw new WebApplicationException("hotelId must be specified", 422);
        }

        System.out.println("Booking request " + bookingRequest);

        // With scala we could fold
        Either<String,String> erroMsgOrOk = bookingRequest.isValid();
        if(erroMsgOrOk.isLeft()){
            throw new WebApplicationException(erroMsgOrOk.getLeft(), 400);
        }

        return Response.ok("{\"result\":\"booking created\"}").status(200).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {
        @Override
        public Response toResponse(Exception exception) {
            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }
            return Response.status(code)
                    .entity(Json.createObjectBuilder().add("error", exception.getMessage()).add("code", code).build())
                    .build();
        }

    }

}
