import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can edit a Manual entry'

scenario "User can edit a valid manual entry", {
    given 'on frontpage an book is added', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
        element = driver.findElement(By.id("manualTitle"));
        element.sendKeys("Manual title");
        element = driver.findElement(By.id("manualAuthor"));
        element.sendKeys("Some author");
        element = driver.findElement(By.id("manualYear"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("addManual"));
        element.submit();
    }

    when 'user edits the @manual form and submits', {
        element = driver.findElement(By.className("manualTitleField"));
        element.clear();
        element.sendKeys("New manual title");
        element = driver.findElement(By.className("editButton"));
        element.click();
        driver.navigate().refresh();
    }
 
    then 'manual is edited', {
        driver.getPageSource().contains("New manual title").shouldBe true
    }
}

scenario "User can't break a valid manual entry", {
    given 'on frontpage an booklet is added', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
        element = driver.findElement(By.id("manualTitle"));
        element.sendKeys("Manual title");
        element = driver.findElement(By.id("manualAuthor"));
        element.sendKeys("Some author");
        element = driver.findElement(By.id("manualYear"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("addManual"));
        element.submit();
    }

    when 'user edits the @manual form and submits, leaving manual blank', {
        element = driver.findElement(By.className("manualTitleField"));
        element.clear();
        element = driver.findElement(By.className("editButton"));
        element.click();
        driver.navigate().refresh();
    }
 
    then 'manual is edited', {
        driver.getPageSource().contains("Manual title").shouldBe false
    }
}
