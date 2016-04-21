package app.filegeneration;

import app.domain.Article;
import app.domain.Book;
import app.domain.Entry;
import app.domain.Inproceedings;

public class BibTexGenerator {

    private final String tab = "  ";

    public BibTexGenerator() {
    }

    public String booktEntryToBibTex(Book book) {
        StringBuilder sb = new StringBuilder();
        generateStart(sb, "@book", book);
        addAuthor(sb, book);
        addTitle(sb, book);
        addYear(sb, book);
        addPublisher(sb, book.getPublisher());
        addEnd(sb);
        return replaceFinnishLetters(sb.toString());
    }

    public String articletEntryToBibTex(Article article) {
        StringBuilder sb = new StringBuilder();
        generateStart(sb, "@article", article);
        addAuthor(sb, article);
        addTitle(sb, article);
        addYear(sb, article);
        addJournal(sb, article.getJournal());
        addVolume(sb, article.getVolume());
        addPages(sb, article.getPages());
        addEnd(sb);
        return replaceFinnishLetters(sb.toString());
    }

    public String inproceedingstEntryToBibTex(Inproceedings inpr) {
        StringBuilder sb = new StringBuilder();
        generateStart(sb, "@inproceedings", inpr);
        addAuthor(sb, inpr);
        addTitle(sb, inpr);
        addBookTitle(sb, inpr.getBookTitle());
        addYear(sb, inpr);
        addPages(sb, inpr.getPages());
        addPublisher(sb, inpr.getPublisher());
        addEnd(sb);
        return replaceFinnishLetters(sb.toString());
    }

    private String replaceFinnishLetters(String string) {
        string = string.replace("ä", "\"{a}");
        string = string.replace("Ä", "\"{A}");
        string = string.replace("ö", "\"{o}");
        string = string.replace("Ö", "\"{O}");
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

    private void addAuthor(StringBuilder sb, Entry entry) {
        sb.append(tab);
        sb.append("author = {");
        sb.append(entry.getAuthor());
        sb.append("},\n");
    }

    private void addPublisher(StringBuilder sb, String publisher) {
        sb.append(tab);
        sb.append("publisher = {");
        sb.append(publisher);
        sb.append("},\n");
    }

    private void addTitle(StringBuilder sb, Entry entry) {
        sb.append(tab);
        sb.append("title = {");
        sb.append(entry.getTitle());
        sb.append("},\n");
    }

    private void addBookTitle(StringBuilder sb, String bookTitle) {
        sb.append(tab);
        sb.append("bookTitle = {");
        sb.append(bookTitle);
        sb.append("},\n");
    }

    private void addVolume(StringBuilder sb, String volume) {
        sb.append(tab);
        sb.append("volume = {");
        sb.append(volume);
        sb.append("},\n");
    }

    private void addPages(StringBuilder sb, String pages) {
        if (pages != null) {
            sb.append(tab);
            sb.append("pages = {");
            sb.append(pages);
            sb.append("},\n");
        }
    }

    private void addJournal(StringBuilder sb, String journal) {
        sb.append(tab);
        sb.append("journal = {");
        sb.append(journal);
        sb.append("},\n");
    }

    private void addYear(StringBuilder sb, Entry entry) {
        sb.append(tab);
        sb.append("year = {");
        sb.append(entry.getYear());
        sb.append("},\n");
    }

    private void generateStart(StringBuilder sb, String entryName, Entry entry) {
        sb.append(entryName);
        sb.append("{");
        sb.append(generateCite(entry.getAuthor(), entry.getYear()));
        sb.append(",\n");
    }

    private void addEnd(StringBuilder sb) {
        sb.append("}\n");
    }

    private String getFirstLettersOfAuthors(String author) {
        String[] osat = author.split("and");
        String result = "";
        for (String string : osat) {
            result = result + string.charAt(0);
        }
        return result;
    }

}
