package app.controller;

import app.domain.Article;
import app.domain.Book;
import app.domain.Booklet;
import app.domain.FileForm;
import app.domain.Inproceedings;
import app.domain.Manual;
import app.repositories.ArticleRepository;
import app.repositories.BookRepository;
import app.repositories.BookletRepository;
import app.repositories.InproceedingsRepository;
import app.repositories.ManualRepository;
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
    
    @Autowired
    BookletRepository bookletRepository;
    
    @Autowired
    ManualRepository manualRepository;

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
    
    @ModelAttribute
    private Booklet getBooklet() {
        return new Booklet();
    }
    
    @ModelAttribute
    private Manual getManual() {
        return new Manual();
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
        model.addAttribute("booklets", bookletRepository.findAll());
        model.addAttribute("manuals", manualRepository.findAll());
        return "bibtexinator";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveBook")
    public String saveBook(@Valid @ModelAttribute Book book,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/bibtexinator";
        }
        bookRepository.save(book);

        return "redirect:/bibtexinator";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/saveBooklet")
    public String saveBooklet(@Valid @ModelAttribute Booklet booklet,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/bibtexinator";
        }
        bookletRepository.save(booklet);

        return "redirect:/bibtexinator";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/saveManual")
    public String saveManual(@Valid @ModelAttribute Manual manual,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/bibtexinator";
        }
        manualRepository.save(manual);

        return "redirect:/bibtexinator";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/editBook/{id}", params = "action=edit")
    public String editBook(@Valid @ModelAttribute Book book, @PathVariable Long id,
            BindingResult bindingResult) {

        Book oldBook = bookRepository.findOne(id);

        if (bindingResult.hasErrors()) {
            return "redirect:/bibtexinator";
        }

        oldBook.setAuthor(book.getAuthor());
        oldBook.setPublisher(book.getPublisher());
        oldBook.setTitle(book.getTitle());
        oldBook.setYear(book.getYear());
        oldBook.setVolume(book.getVolume());
        oldBook.setSeries(book.getSeries());
        oldBook.setAddress(book.getAddress());
        oldBook.setEdition(book.getEdition());
        oldBook.setMonth(book.getMonth());

        bookRepository.save(oldBook);

        return "redirect:/bibtexinator";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/editBooklet/{id}", params = "action=edit")
    public String editBooklet(@Valid @ModelAttribute Booklet booklet, @PathVariable Long id,
            BindingResult bindingResult) {

        Booklet oldBooklet = bookletRepository.findOne(id);

        if (bindingResult.hasErrors()) {
            return "redirect:/bibtexinator";
        }

        oldBooklet.setAuthor(booklet.getAuthor());
        oldBooklet.setTitle(booklet.getTitle());
        oldBooklet.setYear(booklet.getYear());
        oldBooklet.setHowpublished(booklet.getHowpublished());
        oldBooklet.setMonth(booklet.getMonth());
        oldBooklet.setNote(booklet.getNote());
        oldBooklet.setAddress(booklet.getAddress());

        bookletRepository.save(oldBooklet);

        return "redirect:/bibtexinator";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/editManual/{id}", params = "action=edit")
    public String editManual(@Valid @ModelAttribute Manual manual, @PathVariable Long id,
            BindingResult bindingResult) {

        Manual oldManual = manualRepository.findOne(id);

        if (bindingResult.hasErrors()) {
            return "redirect:/bibtexinator";
        }

        oldManual.setAddress(manual.getAddress());
        oldManual.setAuthor(manual.getAuthor());
        oldManual.setEdition(manual.getEdition());
        oldManual.setMonth(manual.getMonth());
        oldManual.setOrganization(manual.getOrganization());
        oldManual.setTitle(manual.getTitle());
        oldManual.setYear(manual.getYear());
        

        manualRepository.save(oldManual);

        return "redirect:/bibtexinator";
    }

    

    @RequestMapping(method = RequestMethod.POST, value = "/editArticle/{id}", params = "action=edit")
    public String editArticle(@Valid @ModelAttribute Article article, @PathVariable Long id,
            BindingResult bindingResult) {

        Article oldArticle = articleRepository.findOne(id);

        if (bindingResult.hasErrors()) {
            return "redirect:/bibtexinator";
        }

        oldArticle.setAuthor(article.getAuthor());
        oldArticle.setJournal(article.getJournal());
        oldArticle.setTitle(article.getTitle());
        oldArticle.setPages(article.getPages());
        oldArticle.setVolume(article.getVolume());
        oldArticle.setNumber(article.getNumber());
        oldArticle.setMonth(article.getMonth());
        oldArticle.setYear(article.getYear());

        articleRepository.save(oldArticle);

        return "redirect:/bibtexinator";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editInproceedings/{id}", params = "action=edit")
    public String editInproceedings(@Valid @ModelAttribute Inproceedings inproceedings, @PathVariable Long id,
            BindingResult bindingResult) {

        Inproceedings oldInproceedings = inproceedingsRepository.findOne(id);

        if (bindingResult.hasErrors()) {
            return "redirect:/bibtexinator";
        }

        oldInproceedings.setAuthor(inproceedings.getAuthor());
        oldInproceedings.setTitle(inproceedings.getTitle());
        oldInproceedings.setBookTitle(inproceedings.getBookTitle());
        oldInproceedings.setPages(inproceedings.getPages());
        oldInproceedings.setPublisher(inproceedings.getPublisher());
        oldInproceedings.setYear(inproceedings.getYear());

        inproceedingsRepository.save(oldInproceedings);

        return "redirect:/bibtexinator";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveArticle")
    public String saveArticle(@Valid @ModelAttribute Article article,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/bibtexinator";
        }
        articleRepository.save(article);

        return "redirect:/bibtexinator";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveInproceedings")
    public String saveInproceedings(@Valid @ModelAttribute Inproceedings inproceedings,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/bibtexinator";
        }
        inproceedingsRepository.save(inproceedings);

        return "redirect:/bibtexinator";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editBook/{id}", params = "action=delete")
    public String deleteBook(@PathVariable Long id) {

        bookRepository.delete(id);

        return "redirect:/bibtexinator";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/editBooklet/{id}", params = "action=delete")
    public String deleteBooklet(@PathVariable Long id) {

        bookletRepository.delete(id);

        return "redirect:/bibtexinator";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/editManual/{id}", params = "action=delete")
    public String deleteManual(@PathVariable Long id) {

        manualRepository.delete(id);

        return "redirect:/bibtexinator";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editArticle/{id}", params = "action=delete")
    public String deleteArticle(@PathVariable Long id) {

        articleRepository.delete(id);

        return "redirect:/bibtexinator";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editInproceedings/{id}", params = "action=delete")
    public String deleteInproceeding(@PathVariable Long id) {

        inproceedingsRepository.delete(id);

        return "redirect:/bibtexinator";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/deleteAll")
    public String deleteAll() {
        bookRepository.deleteAll();
        articleRepository.deleteAll();
        inproceedingsRepository.deleteAll();
        return "redirect:/bibtexinator";
    }

}
