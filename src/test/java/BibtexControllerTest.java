import app.Application;
import app.domain.Article;
import app.domain.Book;
import app.repositories.ArticleRepository;
import app.repositories.BookRepository;
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
    
    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }
    
    @Test
    public void sivuLatautuu() throws Exception {
        MvcResult res = mockMvc.perform(get("/bibtexinator"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attributeExists("articles"))
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
}
