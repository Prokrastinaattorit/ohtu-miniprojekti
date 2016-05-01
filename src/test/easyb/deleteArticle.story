import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can delete an Article entry'

scenario "User can delete article entry", {
    given 'on frontpage', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
    }

    when 'user fills the @article form and submits, then deletes', {
        element = driver.findElement(By.id("articleAuthor"));
        element.sendKeys("Author Article");
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

        element = driver.findElement(By.className("deleteButton"));
        element.click();
    }
 
    then 'article is deleted', {
        driver.getPageSource().contains("Author Article").shouldBe false
    }
}
