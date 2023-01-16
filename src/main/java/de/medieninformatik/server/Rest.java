package de.medieninformatik.server;

import de.customlogger.logger.ColorLogger;
import de.medieninformatik.objects.Book;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.logging.Logger;

@Path("books")
public class Rest {
    private static final Logger LOGGER = ColorLogger.newLogger(Rest.class.getName());

    @GET
    @Produces("application/json")
    public static Response getAllBooks() {
        StringBuilder stringBuilder = new StringBuilder();
        //...
        return Response.ok(stringBuilder.toString()).build();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public static Response getBook(@PathParam("id") int id) {
        StringBuilder stringBuilder = new StringBuilder();
        //...
        return Response.ok(stringBuilder.toString()).build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response createBook(Book book) {
        StringBuilder stringBuilder = new StringBuilder();
        //...
        return Response.ok(stringBuilder.toString()).build();
    }

    @POST
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response editBook(@PathParam("id") int id, Book book) {
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
