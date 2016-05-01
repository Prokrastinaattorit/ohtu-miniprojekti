package app.filegeneration;

import app.domain.Article;
import app.domain.Book;
import app.domain.Booklet;
import app.domain.Entry;
import app.domain.Inproceedings;
import app.domain.Manual;

public class BibTexGenerator {

    private final String tab = "  ";
    private StringBuilder sb;

    public BibTexGenerator() {
    }

    public String booktEntryToBibTex(Book book) {
        sb = new StringBuilder();
        generateStart("@book", book);
        addAuthor(book.getAuthor());
        addTitle(book.getTitle());
        addYear(book.getYear());
        addPublisher(book.getPublisher());
        addVolume(book.getVolume());
        addSeries(book.getSeries());
        addAddress(book.getAddress());
        addEdition(book.getEdition());
        addMonth(book.getMonth());
        addEnd();
        return replaceFinnishLetters();
    }

    public String articletEntryToBibTex(Article article) {
        sb = new StringBuilder();
        generateStart("@article", article);
        addAuthor(article.getAuthor());
        addTitle(article.getTitle());
        addYear(article.getYear());
        addJournal(article.getJournal());
        addVolume(article.getVolume());
        addPages(article.getPages());
        addNumber(article.getNumber());
        addMonth(article.getMonth());
        addEnd();
        return replaceFinnishLetters();
    }

    public String inproceedingstEntryToBibTex(Inproceedings inpr) {
        sb = new StringBuilder();
        generateStart("@inproceedings", inpr);
        addAuthor(inpr.getAuthor());
        addTitle(inpr.getTitle());
        addBookTitle(inpr.getBookTitle());
        addYear(inpr.getYear());
        addPages(inpr.getPages());
        addPublisher(inpr.getPublisher());
        addMonth(inpr.getMonth());
        addAddress(inpr.getAddress());
        addOrganization(inpr.getOrganization());
        addEnd();
        return replaceFinnishLetters();
    }

    public String manualEntryToBibTex(Manual manual) {
        sb = new StringBuilder();
        generateStart("@manual", manual);
        addAuthor(manual.getAuthor());
        addTitle(manual.getTitle());
        addYear(manual.getYear());
        addMonth(manual.getMonth());
        addAddress(manual.getAddress());
        addEdition(manual.getEdition());
        addOrganization(manual.getOrganization());
        addEnd();
        return replaceFinnishLetters();
    }

    public String bookletEntryToBibTex(Booklet booklet) {
        sb = new StringBuilder();
        generateStart("@booklet", booklet);
        addAuthor(booklet.getAuthor());
        addTitle(booklet.getTitle());
        addYear(booklet.getYear());
        addAddress(booklet.getAddress());
        addMonth(booklet.getMonth());
        addHowPublished(booklet.getHowpublished());
        addEnd();
        return replaceFinnishLetters();
    }

    private String replaceFinnishLetters() {
        String string = sb.toString();
        string = string.replace("ä", "\\\"{a}");
        string = string.replace("Ä", "\\\"{A}");
        string = string.replace("ö", "\\\"{o}");
        string = string.replace("Ö", "\\\"{O}");
        string = string.replace("å", "\\\"{aa}");
        string = string.replace("Å", "\\\"{AA}");
        return string;
    }

    private String generateCite(String author, String year) {
        String cite = "";
        if (author != null) {
            cite = cite + getFirstLettersOfAuthors(author);
        }
        if (year != null && year.length() >= 2) {
            cite = cite + year.substring(year.length() - 2, year.length());
        }
        //TODO: Should replace ä and ö to something else?
        return cite;
    }

    private void generateStart(String entryName, Entry entry) {
        sb.append(entryName);
        sb.append("{");
        sb.append(generateCite(entry.getAuthor(), entry.getYear()));
        sb.append(",\n");
    }

    private void addEnd() {
        sb.append("}\n");
    }

    private String getFirstLettersOfAuthors(String author) {
        String[] osat = author.split("and ");
        String result = "";
        for (String string : osat) {
            result = result + string.charAt(0);
        }
        return result;
    }

    private void addField(String field, String value) {
        if (value != null && !value.isEmpty()) {
            sb.append(tab);
            sb.append(field);
            sb.append(" = {");
            sb.append(value);
            sb.append("},\n");
        }
    }

    private void addAuthor(String author) {
        addField("author", author);
    }

    private void addPublisher(String publisher) {
        addField("publisher", publisher);
    }

    private void addTitle(String title) {
        addField("title", title);
    }

    private void addBookTitle(String bookTitle) {
        addField("booktitle", bookTitle);
    }

    private void addVolume(String volume) {
        addField("volume", volume);
    }

    private void addPages(String pages) {
        addField("pages", pages);
    }

    private void addAddress(String address) {
        addField("address", address);
    }

    private void addJournal(String journal) {
        addField("journal", journal);
    }

    private void addYear(String year) {
        addField("year", year);
    }

    private void addMonth(String month) {
        addField("month", month);
    }

    private void addNumber(String number) {
        addField("number", number);
    }

    private void addOrganization(String organization) {
        addField("organization", organization);
    }

    private void addEdition(String edition) {
        addField("edition", edition);
    }

    private void addSeries(String series) {
        addField("series", series);
    }

    private void addHowPublished(String howpublished) {
        addField("howpublished", howpublished);
    }

}
