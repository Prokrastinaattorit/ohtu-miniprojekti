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

    when 'user fills the form and submits', {
        
    }
 
    then 'new book is created', {
        System.out.println("Page html: " + driver.getPageSource());
        driver.getPageSource().contains("Bibtexinator").shouldBe true
    }
}
