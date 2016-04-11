
import app.Application;
import app.domain.Book;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sara ja Laur
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class BibtexControllerTest {
    
    @Autowired
    private WebApplicationContext webAppContext;
    
    @Autowired
    private BookRepository bookRepository;
    
    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }
    
    @Test
    public void sivuLatautuu() throws Exception {
        MvcResult res = mockMvc.perform(get("/bibtexinator"))
                .andExpect(model().attributeExists("books"))
                .andExpect(status().isOk())
                .andReturn();
    }
    
    @Test
    public void kirjanLuontiOnnistuu() throws Exception {
        MvcResult res = mockMvc.perform(post("/bibtexinator/save").param("author", "aaaa").param("title", "bbbb").param("year", "cccc").param("publisher", "dddd"))
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
}
