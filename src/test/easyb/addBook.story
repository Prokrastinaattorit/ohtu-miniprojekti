import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can add a Book entry'

scenario "User can add a valid book entry", {
    given 'on frontpage', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
    }

    when 'user fills the @book form and submits', {
        element = driver.findElement(By.id("bookAuthor"));
        element.sendKeys("Sofi Oksanen");
        element = driver.findElement(By.id("bookTitle"));
        element.sendKeys("Puhdistus");
        element = driver.findElement(By.id("bookYear"));
        element.sendKeys("2008");
        element = driver.findElement(By.id("bookPublisher"));
        element.sendKeys("WSOY");
        element = driver.findElement(By.name("addBook"));
        element.submit();
    }
 
    then 'new book is created', {
        driver.getPageSource().contains("Sofi Oksanen").shouldBe true
    }
}
