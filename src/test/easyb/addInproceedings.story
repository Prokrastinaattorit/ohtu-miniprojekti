import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can add an Inproceedings entry'

scenario "User can add a valid inproceedings entry", {
    given 'on frontpage', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
    }

    when 'user fills the @inproceedings form and submits', {
        element = driver.findElement(By.id("inAuthor"));
        element.sendKeys("Author B");
        element = driver.findElement(By.id("inTitle"));
        element.sendKeys("Title of inproceedings");
        element = driver.findElement(By.id("inBookTitle"));
        element.sendKeys("Book title");
        element = driver.findElement(By.id("inYear"));
        element.sendKeys("1986");
        element = driver.findElement(By.id("inPages"));
        element.sendKeys("30-45");
        element = driver.findElement(By.id("inPublisher"));
        element.sendKeys("Publisher of inproceedings");
        element = driver.findElement(By.name("addInproceedings"));
        element.submit();
    }
 
    then 'new book is created', {
        driver.getPageSource().contains("Author B").shouldBe true
    }
}
