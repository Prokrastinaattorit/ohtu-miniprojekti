package app.filegeneration;

import app.domain.Book;
import app.domain.Entry;

public class BibTexGenerator {

    public BibTexGenerator() {
    }

    public String booktEntryToBibTex(Book book) {
        StringBuilder sb = new StringBuilder();
        generateStart(sb, "@book", book);
        addAuthor(sb, book);
        addTitle(sb, book);
        addYear(sb, book);
        sb.append("publisher = {");
        sb.append(book.getPublisher());
        addEnd(sb);
        return sb.toString();
    }

    private String generateCite(String author, String year) {
        return author.substring(0, 2) + year.substring(2, 4);
    }

    private void addAuthor(StringBuilder sb, Entry entry) {
        sb.append("author = {");
        sb.append(entry.getAuthor());
        sb.append("},\n");
    }

    private void addTitle(StringBuilder sb, Entry entry) {
        sb.append("title = {");
        sb.append(entry.getTitle());
        sb.append("},\n");
    }

    private void addYear(StringBuilder sb, Entry entry) {
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
        sb.append("},\n}");
    }

}
