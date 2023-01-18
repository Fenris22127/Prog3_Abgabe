package de.medieninformatik.server.tempclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.medieninformatik.common.Ansi;
import de.medieninformatik.common.Book;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BooksClient implements IBooksOverview {

    /**
     * Erstellt einen {@link Logger} für diese Klasse
     */
    private static final Logger LOGGER = Logger.getLogger(BooksClient.class.getName());

    /**
     * Der Teil der {@link java.net.URI URI}, welche den Pfad zu der Klasse mit aufrufbaren Methoden bestimmt
     */
    private static final String URI = "/books";

    /**
     * Ein {@link String} mit Format Specifiers, welche die {@link #URI} und die Buch-ID aneinanderhängt,
     * um einen Pfad zu bilden.
     */
    private static final String URIFORMAT = "%s/%d";

    /**
     * Die {@link Client}-Instanz dieses Clients, welche für die Serverkommunikation notwendig ist
     */
    private final Client client;

    /**
     * Die Basis-URI, um sich mit dem Server zu verbinden
     */
    private final String baseURI;

    /**
     * Erstellt einen neuen {@link Client} an der {@link java.net.URI URI}, welche in {@link #baseURI} gespeichert wird.
     * @param uri Ein {@link String}: Die zu verwendende {@link java.net.URI URI}
     */
    public BooksClient(String uri) {
        this.baseURI = uri;
        this.client = ClientBuilder.newClient();
    }

    @Override
    public void getAllBooks() {
        WebTarget target = getTarget("GET", URI);
        Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
        int status = status(response);
        if (status == 200) {
            try {
                List<Book> bookList;
                bookList = new ObjectMapper().readValue(response.readEntity(String.class), new TypeReference<>() {
                });
                LOGGER.log(Level.INFO, String.format("%sJSON -> List<Book>\n%d books in list.%s",
                        Ansi.GREEN, bookList.size(), Ansi.RESET));
                //do something
            }
            catch (JsonProcessingException e) {
                LOGGER.log(Level.SEVERE, String.format("%sUnable to parse JSON to List<Book>!%s", Ansi.RED, Ansi.RESET));
                e.printStackTrace();
            }
        }
        else {
            LOGGER.log(Level.SEVERE, String.format("%sError %d: Unable get all books!\nReason: %s%s",
                    Ansi.RED, status, response.getStatusInfo().getReasonPhrase(), Ansi.RESET));
        }
    }

    @Override
    public void getBook(int id) {
        WebTarget target = getTarget("GET", String.format(URIFORMAT, URI, id));
        Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
        int status = status(response);
        if (status == 200) {
            try {
                Book book = new ObjectMapper().readerFor(Book.class).readValue(response.readEntity(String.class));
                LOGGER.log(Level.INFO, String.format("%sJSON -> Book\n\"%s\" by %s%s",
                        Ansi.GREEN, book.title(), book.authors(), Ansi.RESET));
                //do something
            }
            catch (JsonProcessingException e) {
                LOGGER.log(Level.SEVERE, String.format("%sUnable to parse JSON to Book!%s", Ansi.RED, Ansi.RESET));
                e.printStackTrace();
            }
        }
        else {
            LOGGER.log(Level.SEVERE, String.format("%sError %d: Unable get book!\nReason: %s%s",
                    Ansi.RED, status, response.getStatusInfo().getReasonPhrase(), Ansi.RESET));
        }
    }

    @Override
    public void createBook(Book book) {
        WebTarget target = getTarget("PUT", URI);
        try {
            String bookToJson = new ObjectMapper().writeValueAsString(book);
            Entity<String> entity = Entity.entity(bookToJson, MediaType.APPLICATION_JSON);
            Response response = target.request().put(entity);
            int status = status(response);
            if (status == 200) {
                //do something (with sql)
                LOGGER.log(Level.INFO, String.format("%sNew Book created!\n\"%s\" by %s%s",
                        Ansi.GREEN, book.title(), book.authors(), Ansi.RESET));
            }
            else {
                LOGGER.log(Level.SEVERE, String.format("%sError %d: Unable create book!\nReason: %s%s",
                        Ansi.RED, status, response.getStatusInfo().getReasonPhrase(), Ansi.RESET));
            }
        }
        catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, String.format("%sUnable to parse new Book to JSON!%s", Ansi.RED, Ansi.RESET));
            e.printStackTrace();
        }

    }

    @Override
    public void editBook(Book book) {
        WebTarget target = getTarget("POST", String.format(URIFORMAT, URI, book.id()));
        try {
            String bookToJson = new ObjectMapper().writeValueAsString(book);
            Entity<String> entity = Entity.entity(bookToJson, MediaType.APPLICATION_JSON);
            Response response = target.request().accept(MediaType.APPLICATION_JSON).post(entity);
            int status = status(response);
            if (status == 200) {
                //do something (with sql)
                LOGGER.log(Level.INFO, String.format("%sBook edited!\n\"%s\" by %s%s",
                        Ansi.GREEN, book.title(), book.authors(), Ansi.RESET));
            }
            else {
                LOGGER.log(Level.SEVERE, String.format("%sError %d: Unable edit book!\nReason: %s%s",
                        Ansi.RED, status, response.getStatusInfo().getReasonPhrase(), Ansi.RESET));
            }
        }
        catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, String.format("%sUnable to parse edited Book to JSON!%s", Ansi.RED, Ansi.RESET));
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(Book book) {
        WebTarget target = getTarget("DELETE", String.format(URIFORMAT, URI, book.id()));
        Response response = target.request().delete();
        int status = status(response);
        if (status == 200) {
            //do something (with sql)
            LOGGER.log(Level.INFO, String.format("%sBook edited!\n\"%s\" by %s%s",
                    Ansi.GREEN, book.title(), book.authors(), Ansi.RESET));
        }
        else {
            LOGGER.log(Level.SEVERE, String.format("%sError %d: Unable delete book!\nReason: %s%s",
                    Ansi.RED, status, response.getStatusInfo().getReasonPhrase(), Ansi.RESET));
        }
    }

    private WebTarget getTarget(String annotation, String uri) {
        //Ausgabe: --- [POST/PUT/...] [Base-Path][Specific Path]
        LOGGER.log(Level.INFO, String.format("%s%n--- %s %s%s%s%n", Ansi.CYAN, annotation, baseURI, uri, Ansi.RESET));
        return client.target(baseURI + uri);
    }

    private int status(Response response) {
        int code = response.getStatus();
        String reason = response.getStatusInfo().getReasonPhrase();
        LOGGER.log(Level.INFO, String.format("%sStatus: %d %s%s%n", Ansi.CYAN, code, reason, Ansi.RESET));
        return code;
    }
}
