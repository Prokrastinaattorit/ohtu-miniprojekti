import app.*
import app.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can add a Book entry'

scenario "User can add a valid book entry", {
    given 'on frontpage', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
    }

    when 'user fills the @book form and submits', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Sofi Oksanen");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Puhdistus");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2008");
        element = driver.findElement(By.name("publisher"));
        element.sendKeys("WSOY");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
 
    then 'new book is created', {
        driver.getPageSource().contains("Sofi Oksanen").shouldBe true
    }
}
