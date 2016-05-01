import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can add a Booklet entry'

scenario "User can add a valid booklet entry", {
    given 'on frontpage', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
    }

    when 'user fills the @booklet form and submits', {
        element = driver.findElement(By.id("bookletTitle"));
        element.sendKeys("Booklet title");
        element = driver.findElement(By.id("bookletAuthor"));
        element.sendKeys("Some author");
        element = driver.findElement(By.id("bookletYear"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("addBooklet"));
        element.submit();
    }
 
    then 'new booklet is created', {
        driver.getPageSource().contains("Booklet title").shouldBe true
    }
}

scenario "User can't add an invalid booklet entry", {
    given 'on frontpage', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
    }

    when 'user fills the @booklet form and submits, missing title', {
        element = driver.findElement(By.id("bookletAuthor"));
        element.sendKeys("Author D");
        element = driver.findElement(By.id("bookletYear"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("addBooklet"));
        element.submit();
    }
 
    then 'new booklet is not created', {
        driver.getPageSource().contains("Author D").shouldBe false
    }
}
