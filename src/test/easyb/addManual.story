import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can add a Manual entry'

scenario "User can add a valid manual entry", {
    given 'on frontpage', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
    }

    when 'user fills the @manual form and submits', {
        element = driver.findElement(By.id("manualTitle"));
        element.sendKeys("Manual title");
        element = driver.findElement(By.id("manualAuthor"));
        element.sendKeys("Some author");
        element = driver.findElement(By.id("manualYear"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("addManual"));
        element.submit();
    }
 
    then 'new manual is created', {
        driver.getPageSource().contains("Manual title").shouldBe true
    }
}

scenario "User can't add an invalid manual entry", {
    given 'on frontpage', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
    }

    when 'user fills the @manual form and submits, missing title', {
        element = driver.findElement(By.id("manualAuthor"));
        element.sendKeys("Author E");
        element = driver.findElement(By.id("manualYear"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("addManual"));
        element.submit();
    }
 
    then 'new manual is not created', {
        driver.getPageSource().contains("Author E").shouldBe false
    }
}
