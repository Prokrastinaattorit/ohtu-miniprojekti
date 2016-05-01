package app.filegeneration;

import app.domain.Article;
import app.domain.Book;
import app.domain.Inproceedings;
import app.repositories.ArticleRepository;
import app.repositories.BookRepository;
import app.repositories.InproceedingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileTextGenerator {

    private BibTexGenerator bibtexGenerator;

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    InproceedingsRepository inproceedingsRepository;

    public FileTextGenerator() {
        bibtexGenerator = new BibTexGenerator();
    }

    public String generateBibtexFromEntries() {
        StringBuilder sb = new StringBuilder();

        for (Article article : articleRepository.findAll()) {
            sb.append(bibtexGenerator.articletEntryToBibTex(article));
            sb.append("\n");
        }

        for (Book book : bookRepository.findAll()) {
            sb.append(bibtexGenerator.booktEntryToBibTex(book));
            sb.append("\n");
        }

        for (Inproceedings inproceedings : inproceedingsRepository.findAll()) {
            sb.append(bibtexGenerator.inproceedingstEntryToBibTex(inproceedings));
            sb.append("\n");
        }

        return sb.toString();
    }
}
