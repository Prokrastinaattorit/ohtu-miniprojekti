package app.filegeneration;

import app.domain.Article;
import app.domain.Book;
import app.repositories.ArticleRepository;
import app.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileTextGenerator {

    private BibTexGenerator bibtexGenerator;

    @Autowired
    ArticleRepository articleRepository;
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
            sb.append("\n");
        }

        for (Article article : articleRepository.findAll()) {
            sb.append(bibtexGenerator.articletEntryToBibTex(article));
            sb.append("\n");
        }

        return sb.toString();
    }
}
