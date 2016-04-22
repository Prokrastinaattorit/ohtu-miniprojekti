package app.controller;

import app.domain.Article;
import app.domain.Book;
import app.domain.FileForm;
import app.domain.Inproceedings;
import app.repositories.ArticleRepository;
import app.repositories.BookRepository;
import app.repositories.InproceedingsRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("bibtexinator")
public class BibtexController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    InproceedingsRepository inproceedingsRepository;

    @ModelAttribute
    private Article getArticle() {
        return new Article();
    }

    @ModelAttribute
    private Book getBook() {
        return new Book();
    }

    @ModelAttribute
    private Inproceedings getInproceedings() {
        return new Inproceedings();
    }

    @ModelAttribute("FileForm")
    private FileForm getFileForm() {
        return new FileForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String redirect(Model model) {
        model.addAttribute("FileForm", new FileForm());
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("article", new Article());
        model.addAttribute("articles", articleRepository.findAll());
        model.addAttribute("inproceedingss", inproceedingsRepository.findAll());
        return "bibtexinator";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveBook")
    public String saveBook(@Valid @ModelAttribute Book book,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "bibtexinator";
        }
        bookRepository.save(book);

        return "redirect:/bibtexinator";
    }
    
    
    @RequestMapping(method = RequestMethod.POST, value = "/editBook/{id}")
    public String editBook(@Valid @ModelAttribute Book book, @PathVariable Long id,
            BindingResult bindingResult) {

        Book oldBook = bookRepository.findOne(id);
        
        if (bindingResult.hasErrors()) {
            return "bibtexinator";
        }
        
        oldBook.setAuthor(book.getAuthor());
        oldBook.setPublisher(book.getPublisher());
        oldBook.setTitle(book.getTitle());
        oldBook.setYear(book.getYear());
        
        bookRepository.save(oldBook);

        return "redirect:/bibtexinator";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveArticle")
    public String saveArticle(@Valid @ModelAttribute Article article,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "bibtexinator";
        }
        articleRepository.save(article);

        return "redirect:/bibtexinator";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveInproceedings")
    public String saveInproceedings(@Valid @ModelAttribute Inproceedings inproceedings,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "bibtexinator";
        }
        inproceedingsRepository.save(inproceedings);

        return "redirect:/bibtexinator";
    }
}
