package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DokumenPage {
    WebDriver driver;

    By tombolUnduh = By.xpath("/html/body/div[3]/main/div/div[2]/div[2]/div[2]/button");

    public DokumenPage(WebDriver driver) {
        this.driver = driver;
    }

    public void klikTombolUnduh() {
        driver.findElement(tombolUnduh).click();
    }
}