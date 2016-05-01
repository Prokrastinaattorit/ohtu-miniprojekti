import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can edit a Booklet entry'

scenario "User can edit a valid booklet entry", {
    given 'on frontpage an book is added', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
        element = driver.findElement(By.id("bookletTitle"));
        element.sendKeys("Booklet title");
        element = driver.findElement(By.id("bookletAuthor"));
        element.sendKeys("Some author");
        element = driver.findElement(By.id("bookletYear"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("addBooklet"));
        element.submit();
    }

    when 'user edits the @booklet form and submits', {
        element = driver.findElement(By.className("bookletAuthorField"));
        element.clear();
        element.sendKeys("New booklet author");
        element = driver.findElement(By.className("editButton"));
        element.click();
        driver.navigate().refresh();
    }
 
    then 'booklet is edited', {
        driver.getPageSource().contains("New booklet author").shouldBe true
    }
}

scenario "User can't break a valid booklet entry", {
    given 'on frontpage an booklet is added', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
        element = driver.findElement(By.id("bookletTitle"));
        element.sendKeys("Booklet title");
        element = driver.findElement(By.id("bookletAuthor"));
        element.sendKeys("Some author");
        element = driver.findElement(By.id("bookletYear"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("addBooklet"));
        element.submit();
    }

    when 'user edits the @booklet form and submits, leaving title blank', {
        element = driver.findElement(By.className("bookletTitleField"));
        element.clear();
        element = driver.findElement(By.className("editButton"));
        element.click();
        driver.navigate().refresh();
    }
 
    then 'booklet is edited', {
        driver.getPageSource().contains("Booklet title").shouldBe false
    }
}
