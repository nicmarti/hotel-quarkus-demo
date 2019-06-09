package org.acme.hibernate.orm.panache;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.quarkus.panache.common.Sort;

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
    @Path("{id}")
    public Hotel getSingle(@PathParam Long id) {
        Hotel entity = Hotel.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Hotel with id of " + id + " does not exist.", 404);
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
