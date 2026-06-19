package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VerifikasiIKLHPage {
    WebDriver driver;

    By tabIklh = By.xpath("//button[contains(.,'IKLH')] | //*[text()='IKLH']");

    public VerifikasiIKLHPage(WebDriver driver) {
        this.driver = driver;
    }

    public void klikTabIklh() {
        driver.findElement(tabIklh).click();
    }

    public boolean klikTerimaBerdasarkanKabKota(String kabKota) {
        String xpathTombolTerima = String.format(
                "//td[contains(text(),'%s')]/following-sibling::td//button[contains(.,'Terima')]",
                kabKota
        );
        try {
            java.util.List<WebElement> elements = driver.findElements(By.xpath(xpathTombolTerima));
            if (!elements.isEmpty()) {
                elements.get(0).click();
                return true;
            }
        } catch (Exception e) {
            // Ignore
        }
        return false;
    }
}
