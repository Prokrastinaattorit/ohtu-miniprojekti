import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can add an Article entry'

scenario "User can add a valid article entry", {
    given 'on frontpage', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
    }

    when 'user fills the @article form and submits', {
        element = driver.findElement(By.id("articleAuthor"));
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
    }
 
    then 'new book is created', {
        driver.getPageSource().contains("Author A").shouldBe true
    }
}
