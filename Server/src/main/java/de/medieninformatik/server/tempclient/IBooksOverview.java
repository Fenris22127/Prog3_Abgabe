package de.medieninformatik.server.tempclient;

import de.medieninformatik.common.Book;

/**
 * Das Interface, welches die benötigten Methoden für Clients bereitstellt.
 * @author Elisa Johanna Woelk (m30192)
 */
public interface IBooksOverview {

    /**
     * Gibt alle Bücher zurück.
     */
    void getAllBooks();

    /**
     * Gibt ein Buch zurück.
     * @param id Ein {@link Integer}: Die ID des erfragten Buches
     */
    void getBook(int id);

    /**
     * Erstellt ein neues Buch.
     * @param book Ein {@link Book}-Objekt: Das zu erstellende Buch
     */
    void createBook(Book book);

    /**
     * Verändert ein bereits existierendes Buch.
     * @param book Ein {@link Book}-Objekt: Die, am Buch vorzunehmenden Änderungen
     */
    void editBook(Book book);

    /**
     * Löscht ein Buch.
     * @param book Ein {@link Book}-Objekt: Das zu löschende Buch
     */
    void deleteBook(Book book);
}
