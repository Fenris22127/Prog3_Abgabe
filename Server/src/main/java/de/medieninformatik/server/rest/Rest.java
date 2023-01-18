package de.medieninformatik.server.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.logging.Logger;

/**
 * Verarbeitet die Anfragen der Clients und stellt die benötigten Methoden bereit.
 * @author Elisa Johanna Woelk (m30192)
 */
@Path("books")
public class Rest {

    /**
     * Erstellt einen {@link Logger} für diese Klasse
     */
    private static final Logger LOGGER = Logger.getLogger(Rest.class.getName());

    @GET
    @Produces("application/json")
    public static Response getAllBooks() {
        StringBuilder stringBuilder = new StringBuilder();
        //...
        return Response.ok(stringBuilder.toString()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public static Response getBook(@PathParam("id") int id) {
        StringBuilder stringBuilder = new StringBuilder();
        //...
        stringBuilder.append("Book" + id);
        return Response.ok(stringBuilder.toString()).build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response createBook(String bookInJson) {
        StringBuilder stringBuilder = new StringBuilder();
        //...
        return Response.ok(stringBuilder.toString()).build();
    }

    @POST
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response editBook(@PathParam("id") int id, String bookInJson) {
        StringBuilder stringBuilder = new StringBuilder();
        //...
        return Response.ok(stringBuilder.toString()).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteBook(@PathParam("id") int id) {
        StringBuilder stringBuilder = new StringBuilder();
        //...
        return Response.ok(stringBuilder.toString()).build();
    }
}
