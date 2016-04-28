import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can edit a Book entry'

scenario "User can edit a valid book entry", {
    given 'on frontpage an book is added', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
        element = driver.findElement(By.id("bookAuthor"));
        element.sendKeys("Hieno Kirjailija");
        element = driver.findElement(By.id("bookTitle"));
        element.sendKeys("Huono kirja");
        element = driver.findElement(By.id("bookYear"));
        element.sendKeys("1900");
        element = driver.findElement(By.id("bookPublisher"));
        element.sendKeys("WSOY");
        element = driver.findElement(By.name("addBook"));
        element.submit();
    }

    when 'user edits the @book form and submits', {
        element = driver.findElement(By.className("bookAuthorField"));
        element.clear();
        element.sendKeys("Parempi kirjailija");
        element = driver.findElement(By.className("editButton"));
        element.click();
        driver.navigate().refresh();
    }
 
    then 'book is edited', {
        driver.getPageSource().contains("Parempi kirjailija").shouldBe true
    }
}

scenario "User can't break a valid book entry", {
    given 'on frontpage an book is added', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
        element = driver.findElement(By.id("bookAuthor"));
        element.sendKeys("Hieno Kirjailija");
        element = driver.findElement(By.id("bookTitle"));
        element.sendKeys("Huono kirja");
        element = driver.findElement(By.id("bookYear"));
        element.sendKeys("1900");
        element = driver.findElement(By.id("bookPublisher"));
        element.sendKeys("WSOY");
        element = driver.findElement(By.name("addBook"));
        element.submit();
    }

    when 'user edits the @book form and submits, leaving author blank', {
        element = driver.findElement(By.className("bookAuthorField"));
        element.clear();
        element = driver.findElement(By.className("editButton"));
        element.click();
        driver.navigate().refresh();
    }
 
    then 'book is edited', {
        driver.getPageSource().contains("Hieno Kirjailija").shouldBe false
    }
}
