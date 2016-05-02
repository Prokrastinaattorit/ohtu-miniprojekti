package app.filegeneration;

import app.domain.Article;
import app.domain.Book;
import app.domain.Booklet;
import app.domain.Inproceedings;
import app.domain.Manual;
import app.repositories.ArticleRepository;
import app.repositories.BookRepository;
import app.repositories.BookletRepository;
import app.repositories.InproceedingsRepository;
import app.repositories.ManualRepository;
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
    @Autowired
    BookletRepository bookletRepository;
    @Autowired
    ManualRepository manualRepository;

    public FileTextGenerator() {
        bibtexGenerator = new BibTexGenerator();
    }

    public String generateBibtexFromEntries() {
        StringBuilder sb = new StringBuilder();

        for (Article article : articleRepository.findAll()) {
            sb.append(bibtexGenerator.articleEntryToBibTex(article));
            sb.append("\n");
        }

        for (Book book : bookRepository.findAll()) {
            sb.append(bibtexGenerator.bookEntryToBibTex(book));
            sb.append("\n");
        }

        for (Inproceedings inproceedings : inproceedingsRepository.findAll()) {
            sb.append(bibtexGenerator.inproceedingsEntryToBibTex(inproceedings));
            sb.append("\n");
        }
        
        for (Booklet booklet : bookletRepository.findAll()) {
            sb.append(bibtexGenerator.bookletEntryToBibTex(booklet));
            sb.append("\n");
        }
        
        for (Manual manual : manualRepository.findAll()) {
            sb.append(bibtexGenerator.manualEntryToBibTex(manual));
            sb.append("\n");
        }

        return sb.toString();
    }
}
