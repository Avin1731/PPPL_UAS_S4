package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HasilPenilaianPage {
    WebDriver driver;

    By modalAlert = By.xpath("//h3[contains(text(),'BELUM DIMULAI')]");
    By tombolMengerti = By.xpath("//button[contains(.,'Mengerti')]");

    public HasilPenilaianPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean modalMuncul() {
        try {
            return driver.findElement(modalAlert).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void klikMengerti() {
        driver.findElement(tombolMengerti).click();
    }
}
