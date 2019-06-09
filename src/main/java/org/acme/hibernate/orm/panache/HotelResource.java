package org.acme.hibernate.orm.panache;

import io.quarkus.panache.common.Sort;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Path("hotels")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class HotelResource {

    @GET
    public List<Hotel> get() {
        return Hotel.listAll(Sort.by("name"));
    }

    @GET
    @Path("{hotelId}")
    public Hotel getDetails(@PathParam String hotelId) {
        Hotel entity = Hotel.find("hotelId",hotelId).firstResult();
        if (entity == null) {
            throw new WebApplicationException("Hotel with hotelId of " + hotelId + " does not exist.", 404);
        }
        return entity;
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
