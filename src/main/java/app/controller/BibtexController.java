package app.controller;

import app.domain.Book;
import app.repositories.BookRepository;
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
    
    @ModelAttribute
    private Book getBook() {
        return new Book();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String redirect(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "bibtexinator";
    }
    
    @RequestMapping(method = RequestMethod.POST, value="/save")
    public String saveBook(@ModelAttribute Book book,
            BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return "bibtexinator";
        }
        bookRepository.save(book);
        
        return "redirect:/bibtexinator";
    }
}
