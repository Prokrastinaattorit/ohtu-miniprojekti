package app.filegeneration;

import app.domain.Article;
import app.domain.Book;
import app.domain.Booklet;
import app.domain.Inproceedings;
import app.domain.Manual;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BibTexGeneratorTest {

    public BibTexGenerator bibtexnator;

    public BibTexGeneratorTest() {
    }

    @Before
    public void setUp() {
        bibtexnator = new BibTexGenerator();
    }

    @Test
    public void testGenerateBook() {
        Book book = new Book();
        book.setAuthor("Beck, Kent and Andres, Cynthia");
        book.setPublisher("publisher");
        book.setYear("2008");
        book.setTitle("title");
        String result = bibtexnator.bookEntryToBibTex(book);
        assertEquals(result, "@book{BA08,\n"
                + "  author = {Beck, Kent and Andres, Cynthia},\n"
                + "  title = {title},\n"
                + "  year = {2008},\n"
                + "  publisher = {publisher},\n"
                + "}\n");
    }

    @Test
    public void testGenerateArticle() {
        Article article = new Article();
        article.setAuthor("article");
        article.setJournal("journal");
        article.setYear("2009");
        article.setTitle("title");
        article.setPages("pages");
        article.setVolume("volume");

        String result = bibtexnator.articleEntryToBibTex(article);
        assertEquals(result, "@article{a09,\n"
                + "  author = {article},\n"
                + "  title = {title},\n"
                + "  year = {2009},\n"
                + "  journal = {journal},\n"
                + "  volume = {volume},\n"
                + "  pages = {pages},\n"
                + "}\n");

    }

    @Test
    public void testGenerateArticleWhenAllFieldsAreFilled() {
        Article article = new Article();
        article.setAuthor("article");
        article.setJournal("journal");
        article.setYear("2009");
        article.setTitle("title");
        article.setPages("100-150");
        article.setVolume("3");
        article.setNumber("5");
        article.setMonth("12");

        String result = bibtexnator.articleEntryToBibTex(article);
        assertEquals(result, "@article{a09,\n"
                + "  author = {article},\n"
                + "  title = {title},\n"
                + "  year = {2009},\n"
                + "  journal = {journal},\n"
                + "  volume = {3},\n"
                + "  pages = {100-150},\n"
                + "  number = {5},\n"
                + "  month = {12},\n"
                + "}\n");

    }

    @Test
    public void testGenerateInproceedings() {
        Inproceedings inproceedings = new Inproceedings();
        inproceedings.setAuthor("author");
        inproceedings.setTitle("title");
        inproceedings.setBookTitle("bookTitle");
        inproceedings.setYear("1994");
        inproceedings.setPages("pages");
        inproceedings.setPublisher("publisher");

        String result = bibtexnator.inproceedingsEntryToBibTex(inproceedings);
        assertEquals(result, "@inproceedings{a94,\n"
                + "  author = {author},\n"
                + "  title = {title},\n"
                + "  booktitle = {bookTitle},\n"
                + "  year = {1994},\n"
                + "  pages = {pages},\n"
                + "  publisher = {publisher},\n"
                + "}\n");

    }

    @Test
    public void testGenerateInproceedingsWhenAllFieldsAreFilled() {
        Inproceedings inproceedings = new Inproceedings();
        inproceedings.setAuthor("author");
        inproceedings.setTitle("title");
        inproceedings.setBookTitle("bookTitle");
        inproceedings.setYear("1994");
        inproceedings.setPages("19-21");
        inproceedings.setOrganization("organization");
        inproceedings.setPublisher("publisher");
        inproceedings.setAddress("address");
        inproceedings.setMonth("5");

        String result = bibtexnator.inproceedingsEntryToBibTex(inproceedings);
        assertEquals(result, "@inproceedings{a94,\n"
                + "  author = {author},\n"
                + "  title = {title},\n"
                + "  booktitle = {bookTitle},\n"
                + "  year = {1994},\n"
                + "  pages = {19-21},\n"
                + "  publisher = {publisher},\n"
                + "  month = {5},\n"
                + "  address = {address},\n"
                + "  organization = {organization},\n"
                + "}\n");

    }

    @Test
    public void testGenerateBooklet() {
        Booklet booklet = new Booklet();
        booklet.setAuthor("Kirjoittaja, Kirjoittaa");
        booklet.setHowpublished("howpublished");
        booklet.setYear("2009");
        booklet.setTitle("title");
        booklet.setMonth("month");
        booklet.setAddress("address");
        String result = bibtexnator.bookletEntryToBibTex(booklet);
        assertEquals(result, "@booklet{K09,\n"
                + "  author = {Kirjoittaja, Kirjoittaa},\n"
                + "  title = {title},\n"
                + "  year = {2009},\n"
                + "  address = {address},\n"
                + "  month = {month},\n"
                + "  howpublished = {howpublished},\n"
                + "}\n");
    }

    @Test
    public void testGenerateManual() {
        Manual manual = new Manual();
        manual.setAuthor("Manuaali, Mane");
        manual.setYear("2001");
        manual.setTitle("title");
        manual.setMonth("month");
        manual.setAddress("address");
        manual.setOrganization("organization");
        manual.setEdition("edition");
        String result = bibtexnator.manualEntryToBibTex(manual);
        System.out.println(result);
        assertEquals(result, "@manual{M01,\n"
                + "  author = {Manuaali, Mane},\n"
                + "  title = {title},\n"
                + "  year = {2001},\n"
                + "  month = {month},\n"
                + "  address = {address},\n"
                + "  edition = {edition},\n"
                + "  organization = {organization},\n"
                + "}\n");
    }

    @Test
    public void testFinnishLetters() {
        Book book = new Book();
        book.setAuthor("Kirja Kirjailija");
        book.setPublisher("Åbo");
        book.setYear("2018");
        book.setTitle("Ääkköset");
        String result = bibtexnator.bookEntryToBibTex(book);
        assertEquals(result, "@book{K18,\n"
                + "  author = {Kirja Kirjailija},\n"
                + "  title = {\\\"{A}\\\"{a}kk\\\"{o}set},\n"
                + "  year = {2018},\n"
                + "  publisher = {\\\"AA{}bo},\n"
                + "}\n");
    }

}
