package org.acme.hibernate.orm.panache;

import org.jboss.resteasy.resteasy_jaxrs.i18n.Messages;
import org.jboss.resteasy.spi.CorsHeaders;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * CORS Filter required to be able to access RESTeasy from Vue.JS in dev mode only.
 *
 * @author created by N.Martignole, Lunatech, on 2019-06-05.
 * @see https://www.html5rocks.com/en/tutorials/cors/
 * <p>
 * Quarkus 0.17 should add support for CORSFilter, until then I need this filter
 * <p>
 * https://github.com/quarkusio/quarkus/pull/2259
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {

    private String allowedOrigins="*";


    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {
        System.out.println("Filter CORS 1");
        String origin = requestContext.getHeaderString(CorsHeaders.ORIGIN);
        if (origin == null) {
            return;
        }
        if (requestContext.getMethod().equalsIgnoreCase("OPTIONS")) {
            preflight(origin, requestContext);
        }else {
            responseContext.getHeaders().add(
                    CorsHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            responseContext.getHeaders().add(
                    CorsHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            responseContext.getHeaders().add(
                    CorsHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
                    "origin, content-type, accept, authorization");
            responseContext.getHeaders().add(
                    CorsHeaders.ACCESS_CONTROL_ALLOW_METHODS,
                    "GET, POST, PUT, DELETE, OPTIONS, HEAD");
            responseContext.getHeaders().add(
                    "X-Quarkus",
                    "Remove this filter once the project gets upgraded to a more recent version of Quarkus");
        }
    }

    protected void preflight(String origin, ContainerRequestContext requestContext) throws IOException {
        checkOrigin(requestContext, origin);

        System.out.println("Filter CORS preflight check");

        Response.ResponseBuilder builder = Response.ok();
        builder.header(CorsHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
        builder.header(CorsHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        builder.header(CorsHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        builder.header(CorsHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "origin, content-type, accept, authorization");

        requestContext.abortWith(builder.build());
    }

    protected void checkOrigin(ContainerRequestContext requestContext, String origin) {
        if (!allowedOrigins.contains("*") && !allowedOrigins.contains(origin)) {
            System.out.println("Origin error cors.failure");
            requestContext.setProperty("cors.failure", true);
            throw new ForbiddenException(Messages.MESSAGES.originNotAllowed(origin));
        }
    }
}