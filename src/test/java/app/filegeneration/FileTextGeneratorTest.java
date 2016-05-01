package app.filegeneration;

import app.Application;
import app.repositories.ArticleRepository;
import app.repositories.BookRepository;
import app.repositories.InproceedingsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class FileTextGeneratorTest {

    @Autowired
    private WebApplicationContext webAppContext;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private InproceedingsRepository inproceedingsRepository;
    @Autowired
    private FileTextGenerator g;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
        bookRepository.deleteAll();
        articleRepository.deleteAll();
        inproceedingsRepository.deleteAll();
    }

    @Test
    public void returnsEmptyStringIfNoEntrys() {
        assertEquals("", g.generateBibtexFromEntries());
    }

    @Test
    public void generatesCorrectText1() throws Exception {
        mockMvc.perform(post("/bibtexinator/saveBook").param("author", "author").param("title", "title").param("year", "2013").param("publisher", "publisher"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        mockMvc.perform(post("/bibtexinator/saveBook").param("author", "author2").param("title", "title2").param("year", "1999").param("publisher", "publisher2"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        assertEquals("@book{a13,\n"
                + "  author = {author},\n"
                + "  title = {title},\n"
                + "  year = {2013},\n"
                + "  publisher = {publisher},\n"
                + "}\n"
                + "\n"
                + "@book{a99,\n"
                + "  author = {author2},\n"
                + "  title = {title2},\n"
                + "  year = {1999},\n"
                + "  publisher = {publisher2},\n"
                + "}\n\n", g.generateBibtexFromEntries());
    }

    @Test
    public void generatesCorrectText2() throws Exception {
        mockMvc.perform(post("/bibtexinator/saveArticle").param("author", "author").param("title", "title").param("year", "2015").param("journal", "journal").param("volume", "5").param("pages", "2-10"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        assertEquals("@article{a15,\n"
                + "  author = {author},\n"
                + "  title = {title},\n"
                + "  year = {2015},\n"
                + "  journal = {journal},\n"
                + "  volume = {5},\n"
                + "  pages = {2-10},\n"
                + "}\n\n", g.generateBibtexFromEntries());
    }

    @Test
    public void generatesCorrectText3() throws Exception {
        mockMvc.perform(post("/bibtexinator/saveArticle").param("author", "author1").param("title", "title1").param("year", "1980").param("journal", "journal").param("volume", "11").param("pages", "12"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        mockMvc.perform(post("/bibtexinator/saveBook").param("author", "author2").param("title", "title2").param("year", "2000").param("publisher", "publisher"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        assertEquals("@article{a80,\n"
                + "  author = {author1},\n"
                + "  title = {title1},\n"
                + "  year = {1980},\n"
                + "  journal = {journal},\n"
                + "  volume = {11},\n"
                + "  pages = {12},\n"
                + "}\n"
                + "\n"
                + "@book{a00,\n"
                + "  author = {author2},\n"
                + "  title = {title2},\n"
                + "  year = {2000},\n"
                + "  publisher = {publisher},\n"
                + "}\n\n", g.generateBibtexFromEntries());
    }
}
