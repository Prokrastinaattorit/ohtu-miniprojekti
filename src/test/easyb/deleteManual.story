import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can delete a Manual entry'

scenario "User can delete manual entry", {
    given 'on frontpage', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
    }

    when 'user fills the @manual form and submits, then deletes', {
        element = driver.findElement(By.id("manualTitle"));
        element.sendKeys("Abcdef");
        element = driver.findElement(By.id("manualAuthor"));
        element.sendKeys("Author H");
        element = driver.findElement(By.id("manualYear"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("addManual"));
        element.submit();

        element = driver.findElement(By.className("deleteButton"));
        element.click();
    }
 
    then 'manual is deleted', {
        // driver.getPageSource().contains("Abcdef").shouldBe false
    }
}
