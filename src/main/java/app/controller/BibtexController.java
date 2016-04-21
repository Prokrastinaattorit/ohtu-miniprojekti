package app.controller;

import app.domain.Article;
import app.domain.Book;
import app.domain.FileForm;
import app.repositories.ArticleRepository;
import app.repositories.BookRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("bibtexinator")
public class BibtexController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ArticleRepository articleRepository;

    @ModelAttribute
    private Article getArticle() {
        return new Article();
    }

    @ModelAttribute
    private Book getBook() {
        return new Book();
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

    @RequestMapping(method = RequestMethod.POST, value = "/saveArticle")
    public String saveBook(@ModelAttribute Article article,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "bibtexinator";
        }
        articleRepository.save(article);

        return "redirect:/bibtexinator";
    }
}
