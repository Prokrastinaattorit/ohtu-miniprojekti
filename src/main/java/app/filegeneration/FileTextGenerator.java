package app.filegeneration;

import app.domain.Book;
import app.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileTextGenerator {
    private BibTexGenerator bibtexGenerator;
    
    @Autowired
    BookRepository bookRepository;
    
    public FileTextGenerator() {
        bibtexGenerator = new BibTexGenerator();
    }
    
    public String generateBibtexFromEntrys() {
        StringBuilder sb = new StringBuilder();
        
        for (Book book : bookRepository.findAll()) {
            sb.append(bibtexGenerator.booktEntryToBibTex(book));
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
