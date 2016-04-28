import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can edit an Article entry'

scenario "User can edit a valid article entry", {
    given 'on frontpage an article is added', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
        element = driver.findElement(By.id("articleAuthor"));
        element.sendKeys("Author A");
        element = driver.findElement(By.id("articleTitle"));
        element.sendKeys("Title of article");
        element = driver.findElement(By.id("articleYear"));
        element.sendKeys("1994");
        element = driver.findElement(By.id("articleJournal"));
        element.sendKeys("Journal of article");
        element = driver.findElement(By.id("articleVolume"));
        element.sendKeys("4");
        element = driver.findElement(By.id("articlePages"));
        element.sendKeys("200-205");
        element = driver.findElement(By.name("addArticle"));
        element.submit();
    }

    when 'user edits the @article form and submits', {
        element = driver.findElement(By.className("articleAuthorField"));
        element.clear();
        element.sendKeys("Author B");
        element = driver.findElement(By.className("editButton"));
        element.click();
        driver.navigate().refresh();
    }
 
    then 'article is edited', {
        driver.getPageSource().contains("Author B").shouldBe true
    }
}

scenario "User can't make a valid article entry invalid", {
    given 'on frontpage an article is added', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
        element = driver.findElement(By.id("articleAuthor"));
        element.sendKeys("Author A");
        element = driver.findElement(By.id("articleTitle"));
        element.sendKeys("Title of article");
        element = driver.findElement(By.id("articleYear"));
        element.sendKeys("1994");
        element = driver.findElement(By.id("articleJournal"));
        element.sendKeys("Journal of article");
        element = driver.findElement(By.id("articleVolume"));
        element.sendKeys("4");
        element = driver.findElement(By.id("articlePages"));
        element.sendKeys("200-205");
        element = driver.findElement(By.name("addArticle"));
        element.submit();
    }

    when 'user edits the @article form and submits, leaving author blank', {
        element = driver.findElement(By.className("articleAuthorField"));
        element.clear();
        element = driver.findElement(By.className("editButton"));
        element.click();
        driver.navigate().refresh();
    }
 
    then 'article is not edited', {
        driver.getPageSource().contains("Author A").shouldBe false
    }
}
