package de.medieninformatik.server.rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());
    private static final String URI = "http://localhost:3306/rest";

    public static void main(String[] args) {
        try {
            URI baseUri = new URI(URI);
            ResourceConfig config = ResourceConfig.forApplicationClass(BookApplication.class);
            HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
            StaticHttpHandler handler = new StaticHttpHandler("web");
            handler.setFileCacheEnabled(false);
            ServerConfiguration serverConfig = server.getServerConfiguration();
            serverConfig.addHttpHandler(handler, "/");
            if (!server.isStarted()) server.start();
            LOGGER.log(Level.INFO, "http://localhost:3306/rest/");
            LOGGER.log(Level.INFO, "Server started!");
        }
        catch (URISyntaxException e) {
            LOGGER.log(Level.SEVERE, "Server#main(): Error while creating the URI!");
            e.printStackTrace();
        }
        catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Server#main(): Error while starting the server!");
            e.printStackTrace();
        }
    }
}
