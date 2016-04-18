package app.filegeneration;

import app.Application;
import app.repositories.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    private FileTextGenerator g;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(post("/bibtexinator/saveBook").param("author", "author").param("title", "title").param("year", "2013").param("publisher", "publisher"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        mockMvc.perform(post("/bibtexinator/saveBook").param("author", "author2").param("title", "title2").param("year", "1999").param("publisher", "publisher2"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        
        System.out.println(g.generateBibtexFromEntrys());
    }
}
