import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can delete a Booklet entry'

scenario "User can delete booklet entry", {
    given 'on frontpage', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/bibtexinator");
        driver.findElement(By.id("deleteAllButton")).submit();
    }

    when 'user fills the @booklet form and submits, then deletes', {
        element = driver.findElement(By.id("bookletTitle"));
        element.sendKeys("Title of booklet");
        element = driver.findElement(By.id("bookletAuthor"));
        element.sendKeys("Some author");
        element = driver.findElement(By.id("bookletYear"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("addBooklet"));
        element.submit();

        element = driver.findElement(By.className("deleteButton"));
        element.click();
    }
 
    then 'booklet is deleted', {
        driver.getPageSource().contains("Title of booklet").shouldBe false
    }
}
