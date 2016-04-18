package app.filegeneration;

import app.domain.Article;
import app.domain.Book;
import app.domain.Entry;

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
        return sb.toString();
    }

    public String articletEntryToBibTex(Article article) {
        StringBuilder sb = new StringBuilder();
        generateStart(sb, "@article", article);
        addAuthor(sb, article);
        addTitle(sb, article);
        addYear(sb, article);
        addJournal(sb, article.getJournal());
        addVolume(sb, article.getVolume());
        if (article.getPages() != null) {
            addPages(sb, article.getPages());
        }
        addEnd(sb);
        return sb.toString();
    }

    private String generateCite(String author, String year) {
        return author.substring(0, 2) + year.substring(2, 4);
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

    private void addVolume(StringBuilder sb, String volume) {
        sb.append(tab);
        sb.append("volume = {");
        sb.append(volume);
        sb.append("},\n");
    }

    private void addPages(StringBuilder sb, String pages) {
        sb.append(tab);
        sb.append("pages = {");
        sb.append(pages);
        sb.append("},\n");
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

}
