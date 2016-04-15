import app.Application;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Luokka, joka ajaa easyB:tä vastaavia testejä
 * EasyB ei ole tällä hetkellä käytössä, sillä emme ole saaneet sitä konffattua toimimaan Travisin ja Herokun kanssa
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserStoryTester {
    
    // Description:
    // User can add a Book entry
    
    // Scenario:
    // User can add a valid book entry
    @Test
    public void UserCanAddAValidBookEntry() {
        // given 'on frontpage', {
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");

        // when 'user fills the @book form and submits', {
        WebElement element = driver.findElement(By.name("author"));
        element.sendKeys("Sofi Oksanen");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Puhdistus");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2008");
        element = driver.findElement(By.name("publisher"));
        element.sendKeys("WSOY");
        element = driver.findElement(By.name("add"));
        element.submit();
 
        // then 'new book is created', {
        assertEquals(driver.getPageSource().contains("Sofi Oksanen"),true);
    }
    
}
