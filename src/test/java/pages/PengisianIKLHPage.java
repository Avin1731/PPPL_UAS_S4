package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PengisianIKLHPage {
    WebDriver driver;

    By inputAir = By.xpath("(//input[@type='number'])[1]");
    By inputUdara = By.xpath("(//input[@type='number'])[2]");
    By tombolSimpan = By.xpath("//button[contains(.,'Simpan Perubahan')]");

    public PengisianIKLHPage(WebDriver driver) {
        this.driver = driver;
    }

    public void isiSkorIKLH(String air, String udara) {
        WebElement elAir = driver.findElement(inputAir);
        elAir.clear();
        elAir.sendKeys(air);

        WebElement elUdara = driver.findElement(inputUdara);
        elUdara.clear();
        elUdara.sendKeys(udara);
    }

    public void klikSimpan() {
        driver.findElement(tombolSimpan).click();
    }
}
