import app.*
import app.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can add a Book entry'

scenario "User can add a valid book entry", {
    given 'on frontpage', {
        goTo("http://localhost:8080/bibtexinator");   
        Assert
    }

    when 'user fills the form and submits', {
        
    }
 
    then 'new book is created', {
        driver.getPageSource().contains("Bibtexinator").shouldBe true
    }
}
