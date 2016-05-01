import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can delete a Book entry'

scenario "User can delete book entry", {
    given 'on frontpage', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
    }

    when 'user fills the @book form and submits, then deletes', {
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

        element = driver.findElement(By.className("deleteButton"));
        element.click();
    }
 
    then 'book is deleted', {
        driver.getPageSource().contains("Sofi Oksanen").shouldBe false
    }
}
