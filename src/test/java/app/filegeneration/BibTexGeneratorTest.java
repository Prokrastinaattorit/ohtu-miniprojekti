/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.filegeneration;

import app.domain.Book;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author milla
 */
public class BibTexGeneratorTest {

    public BibTexGenerator bibtexnator;

    public BibTexGeneratorTest() {
    }

    @Before
    public void setUp() {
        bibtexnator = new BibTexGenerator();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGenerateBook() {
        Book book = new Book();
        book.setAuthor("author");
        book.setPublisher("publisher");
        book.setYear("2008");
        book.setTitle("title");
        String result = bibtexnator.booktEntryToBibTex(book);
        assertEquals(result, "@book{au08,\n"
                + "author = {author},\n"
                + "title = {title},\n"
                + "year = {2008},\n"
                + "publisher = {publisher},\n"
                + "}");
    }
}
