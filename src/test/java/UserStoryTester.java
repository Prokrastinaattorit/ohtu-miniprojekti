
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
 * Luokka, joka ajaa easyB:tä vastaavia testejä EasyB ei ole tällä hetkellä
 * käytössä, sillä emme ole saaneet sitä konffattua toimimaan Travisin ja
 * Herokun kanssa
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
        WebElement element = driver.findElement(By.id("bookAuthor"));
        element.sendKeys("Sofi Oksanen");
        element = driver.findElement(By.id("bookTitle"));
        element.sendKeys("Puhdistus");
        element = driver.findElement(By.id("bookYear"));
        element.sendKeys("2008");
        element = driver.findElement(By.id("bookPublisher"));
        element.sendKeys("WSOY");
        element = driver.findElement(By.name("addBook"));
        element.submit();

        // then 'new book is created', {
        assertEquals(true, driver.getPageSource().contains("Sofi Oksanen"));
    }

    // Description:
    // User can add an Article entry
    // Scenario:
    // User can add a valid article entry
    @Test
    public void UserCanAddAnValidArticleEntry() {
        // given 'on frontpage', {
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");

        // when 'user fills the @article form and submits', {
        WebElement element = driver.findElement(By.id("articleAuthor"));
        element.sendKeys("Author A");
        element = driver.findElement(By.id("articleTitle"));
        element.sendKeys("Title of article");
        element = driver.findElement(By.id("articleYear"));
        element.sendKeys("1994");
        element = driver.findElement(By.id("articleJournal"));
        element.sendKeys("Journal of article");
        element = driver.findElement(By.id("articleVolume"));
        element.sendKeys("4");
        element = driver.findElement(By.id("articlePages"));
        element.sendKeys("200-205");
        element = driver.findElement(By.name("addArticle"));
        element.submit();

        // then 'new article is created', {
        assertEquals(true, driver.getPageSource().contains("Author A"));
    }
}
