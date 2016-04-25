
import app.Application;
import app.domain.Article;
import app.domain.Book;
import app.domain.Inproceedings;
import app.repositories.ArticleRepository;
import app.repositories.BookRepository;
import app.repositories.InproceedingsRepository;
import java.io.File;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class BibtexControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private InproceedingsRepository inproceedingsRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void sivuLatautuu() throws Exception {
        MvcResult res = mockMvc.perform(get("/bibtexinator"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attributeExists("article"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void kirjanLuontiOnnistuu() throws Exception {
        MvcResult res = mockMvc.perform(post("/bibtexinator/saveBook").param("author", "aaaa").param("title", "bbbb").param("year", "cccc").param("publisher", "dddd"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        boolean vastaus = false;

        if (bookRepository.findAll() != null) {

            for (Book book : bookRepository.findAll()) {
                if (book.getAuthor().equals("aaaa")
                        && book.getTitle().equals("bbbb")
                        && book.getYear().equals("cccc")
                        && book.getPublisher().equals("dddd")) {
                    vastaus = true;
                }
            }
        }

        Assert.assertTrue(vastaus);
    }

    @Test
    public void artikkelinLuontiOnnistuu() throws Exception {
        MvcResult res = mockMvc.perform(post("/bibtexinator/saveArticle").param("author", "aaaa").param("title", "bbbb").param("year", "cccc").param("journal", "dddd").param("volume", "eeee").param("pages", "ffff"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        boolean vastaus = false;

        if (articleRepository.findAll() != null) {

            for (Article article : articleRepository.findAll()) {
                if (article.getAuthor().equals("aaaa")
                        && article.getTitle().equals("bbbb")
                        && article.getYear().equals("cccc")
                        && article.getJournal().equals("dddd")
                        && article.getVolume().equals("eeee")
                        && article.getPages().equals("ffff")) {
                    vastaus = true;
                }
            }
        }

        Assert.assertTrue(vastaus);
    }
    
    

    @Test
    public void tiedostonLatausOnnistuu() throws Exception {
        MvcResult res = mockMvc.perform(get("/bibtexinator/download").param("fileName", "bibfile"))
                .andExpect(status().isOk())
                .andReturn();
    }
    
    @Test
    public void editBookToimii() throws Exception {
        
        MvcResult res = mockMvc.perform(post("/bibtexinator/saveBook").param("author", "aaaa").param("title", "bbbb").param("year", "cccc").param("publisher", "dddd"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        Long id = bookRepository.findAll().get(0).getId();
        res = mockMvc.perform(post("/bibtexinator/editBook/" + id).param("action", "edit").param("author", "xxxx").param("title", "yyyy").param("year", "zzzz").param("publisher", "ffff"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        boolean vastaus = false;

        if (bookRepository.findAll() != null) {

            for (Book book : bookRepository.findAll()) {
                if (book.getAuthor().equals("xxxx")
                        && book.getTitle().equals("yyyy")
                        && book.getYear().equals("zzzz")
                        && book.getPublisher().equals("ffff")) {
                    vastaus = true;
                }
            }
        }

        Assert.assertTrue(vastaus);
    }
    
    @Test
    public void artikkelinEditointiOnnistuu() throws Exception {
        MvcResult res = mockMvc.perform(post("/bibtexinator/saveArticle").param("author", "aaaa").param("title", "bbbb").param("year", "cccc").param("journal", "dddd").param("volume", "eeee").param("pages", "ffff"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        Long id = articleRepository.findAll().get(0).getId();
        res = mockMvc.perform(post("/bibtexinator/editArticle/" + id).param("action", "edit").param("author", "xxxx").param("title", "zzzz").param("year", "cccc").param("journal", "dddd").param("volume", "eeee").param("pages", "ffff"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        boolean vastaus = false;

        if (articleRepository.findAll() != null) {

            for (Article article : articleRepository.findAll()) {
                if (article.getAuthor().equals("xxxx")
                        && article.getTitle().equals("zzzz")
                        && article.getYear().equals("cccc")
                        && article.getJournal().equals("dddd")
                        && article.getVolume().equals("eeee")
                        && article.getPages().equals("ffff")) {
                    vastaus = true;
                }
            }
        }

        Assert.assertTrue(vastaus);
    }
    
    
    @Test
    public void inproceedingsEditointiOnnistuu() throws Exception {
        MvcResult res = mockMvc.perform(post("/bibtexinator/saveInproceedings").param("author", "aaaa").param("title", "bbbb").param("bookTitle", "cccc").param("year", "dddd").param("pages", "eeee").param("publisher", "ffff"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        Long id = inproceedingsRepository.findAll().get(0).getId();
        res = mockMvc.perform(post("/bibtexinator/editInproceedings/" + id).param("action", "edit").param("author", "xxxx").param("title", "yyyy").param("bookTitle", "dadada").param("year", "dddd").param("pages", "eeee").param("publisher", "ffff"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        boolean vastaus = false;

        if (inproceedingsRepository.findAll() != null) {

            for (Inproceedings inproceedings : inproceedingsRepository.findAll()) {
                if (inproceedings.getAuthor().equals("xxxx")
                        && inproceedings.getTitle().equals("yyyy")
                        && inproceedings.getBookTitle().equals("dadada")
                        && inproceedings.getYear().equals("dddd")
                        && inproceedings.getPages().equals("eeee")
                        && inproceedings.getPublisher().equals("ffff")) {
                    vastaus = true;
                }
            }
        }

        Assert.assertTrue(vastaus);
    }
    
    @Test
    public void deleteBookToimii() throws Exception {
        
        MvcResult res = mockMvc.perform(post("/bibtexinator/saveBook").param("author", "aaaa").param("title", "bbbb").param("year", "cccc").param("publisher", "dddd"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        Long id = bookRepository.findAll().get(0).getId();
        res = mockMvc.perform(post("/bibtexinator/editBook/" + id).param("action", "delete"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        
        Assert.assertTrue(bookRepository.findOne(id)== null);
    }
    
    @Test
    public void artikkelinPoistoOnnistuu() throws Exception {
        MvcResult res = mockMvc.perform(post("/bibtexinator/saveArticle").param("author", "aaaa").param("title", "bbbb").param("year", "cccc").param("journal", "dddd").param("volume", "eeee").param("pages", "ffff"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        Long id = articleRepository.findAll().get(0).getId();
        res = mockMvc.perform(post("/bibtexinator/editArticle/" + id).param("action", "delete"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        Assert.assertTrue(articleRepository.findOne(id) == null);
    }
    
    @Test
    public void inproceedingsPoistoOnnistuu() throws Exception {
        MvcResult res = mockMvc.perform(post("/bibtexinator/saveInproceedings").param("author", "aaaa").param("title", "bbbb").param("bookTitle", "cccc").param("year", "dddd").param("pages", "eeee").param("publisher", "ffff"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        Long id = inproceedingsRepository.findAll().get(0).getId();
        res = mockMvc.perform(post("/bibtexinator/editInproceedings/" + id).param("action", "delete"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        
        Assert.assertTrue(inproceedingsRepository.findOne(id) == null);
    }
}
