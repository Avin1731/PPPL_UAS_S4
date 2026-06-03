package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HapusPusdatinPage {
    WebDriver driver;

    public HapusPusdatinPage(WebDriver driver) {
        this.driver = driver;
    }

    // Fungsi taktis mencari tombol hapus yang sebaris dengan email target di tabel
    public void klikHapusBerdasarkanEmail(String email) {
        String xpathTombolHapus = String.format(
                "//td[contains(text(),'%s')]/following-sibling::td//button[contains(.,'Hapus')]",
                email
        );
        WebElement tombolHapus = driver.findElement(By.xpath(xpathTombolHapus));
        tombolHapus.click();
    }
}