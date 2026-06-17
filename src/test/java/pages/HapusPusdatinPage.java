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
        try {
            java.util.List<WebElement> elements = driver.findElements(By.xpath(xpathTombolHapus));
            if (!elements.isEmpty()) {
                elements.get(0).click();
            } else {
                System.out.println("⚠️ CATATAN: Tombol hapus untuk " + email + " tidak ditemukan (kemungkinan sudah terhapus).");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Gagal mengklik tombol hapus: " + e.getMessage());
        }
    }
}