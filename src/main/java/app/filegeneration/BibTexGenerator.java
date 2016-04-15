
package app.filegeneration;

import app.domain.Book;

public class BibTexGenerator {

    public BibTexGenerator() {
    }
    
    public String booktEntryToBibTex(Book book){
        StringBuilder sb = new StringBuilder();
        sb.append("@book{");
        sb.append(generateCite(book.getAuthor(),book.getYear()));
        sb.append(",\n");
        sb.append("author = {");
        sb.append(book.getAuthor());
        sb.append("},\n");
        sb.append("title = {");
        sb.append(book.getTitle());
        sb.append("},\n");
        sb.append("year = {");
        sb.append(book.getYear());
        sb.append("},\n");
        sb.append("publisher = {");
        sb.append(book.getPublisher());
        sb.append("},\n");
        sb.append("}");
        return sb.toString();
    }

    private String generateCite(String author, String year) {
        return author.substring(0, 2)+year.substring(1,3);
    }
    
    
    
}
