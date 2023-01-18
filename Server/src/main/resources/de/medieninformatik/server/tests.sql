/* Get all books */
SELECT * from book;

/* Get book with certain ID */
SELECT * from book WHERE ID = 3;

SELECT ID, Title from book;

/* Add all authors to books (book titles may occur multiple times */
SELECT
    b.ID, b.Title, b.Publisher, b.PublicationDate, b.Pages, b.ISBN, b.Rating,
    a.ID, a.FirstName, a.LastName
FROM bookauthors ba
         INNER JOIN book b
                    ON ba.BookID = b.ID
         INNER JOIN author a
                    ON ba.AuthorID = a.ID
ORDER BY a.LastName;

/* Get authors for a certain book */
SELECT a.ID,
       a.FirstName,
       a.LastName
FROM bookauthors ba
         INNER JOIN author a
                    ON ba.AuthorID = a.ID
WHERE ba.BookID = 5
ORDER BY a.LastName;

/* Get author IDs for a certain book */
SELECT a.ID
FROM bookauthors ba
         INNER JOIN author a
                    ON ba.AuthorID = a.ID
WHERE ba.BookID = 5;

/* Add all subfields to books (book titles may occur multiple times */
SELECT
    b.ID, b.Title, b.Publisher, b.PublicationDate, b.Pages, b.ISBN, b.Rating,
    s.ID, s.Name
FROM booksubfields bs
         INNER JOIN book b
                    ON bs.BookID = b.ID
         INNER JOIN subfield s
                    ON bs.SubfieldID = s.ID
ORDER BY s.Name;

/* Get subfields for a certain book */
SELECT
    s.ID, s.Name
FROM booksubfields bs
         INNER JOIN subfield s
                    ON bs.SubfieldID = s.ID
WHERE bs.BookID = 5
ORDER BY s.Name;

/* Get subfield IDs for a certain book */
SELECT
    s.ID
FROM booksubfields bs
         INNER JOIN subfield s
                    ON bs.SubfieldID = s.ID
WHERE bs.BookID = 5;