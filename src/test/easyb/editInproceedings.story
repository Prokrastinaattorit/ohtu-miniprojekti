import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can edit an Inproceedings entry'

scenario "User can't break a valid Inproceedings entry", {
    given 'on frontpage an inproceedings is added', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
        element = driver.findElement(By.id("inAuthor"));
        element.sendKeys("Jaska Jokunen");
        element = driver.findElement(By.id("inTitle"));
        element.sendKeys("Title of inproceedings");
        element = driver.findElement(By.id("inBookTitle"));
        element.sendKeys("Awesome book title");
        element = driver.findElement(By.id("inYear"));
        element.sendKeys("1906");
        element = driver.findElement(By.id("inPages"));
        element.sendKeys("30-405");
        element = driver.findElement(By.id("inPublisher"));
        element.sendKeys("Publishe");
        element = driver.findElement(By.name("addInproceedings"));
        element.submit();
    }

    when 'user edits the @inproceedings form and submits, leaving author blank', {
        element = driver.findElement(By.className("inproAuthorField"));
        element.clear();
        element = driver.findElement(By.className("editButton"));
        element.click();
        driver.navigate().refresh();
    }
 
    then 'Inproceedings is edited', {
        driver.getPageSource().contains("Jaska Jokunen").shouldBe false
    }
}
