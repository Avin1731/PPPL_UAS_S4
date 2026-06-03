package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DeadlinePage {
    WebDriver driver;

    // Taktik baru: Menembak langsung berdasarkan atribut placeholder yang ada di UI kelompokmu
    By inputTanggal = By.xpath("//input[@placeholder='Pilih tanggal'] | //input[@type='date']");
    By inputWaktu = By.xpath("//input[@placeholder='Pilih waktu'] | //input[@type='time']");
    By textCatatan = By.xpath("//textarea[@placeholder='Tambah catatan opsional'] | //textarea");
    By tombolSimpan = By.xpath("//button[contains(.,'Simpan Deadline')]");
    By alertPesan = By.id("alert-message");

    public DeadlinePage(WebDriver driver) {
        this.driver = driver;
    }

    public void isiFormulirDeadline(String tanggal, String waktu, String catatan) {
        // Isi Tanggal
        WebElement elementTanggal = driver.findElement(inputTanggal);
        elementTanggal.clear();
        if (tanggal != null && !tanggal.isEmpty()) {
            elementTanggal.sendKeys(tanggal);
        }

        // Isi Waktu
        WebElement elementWaktu = driver.findElement(inputWaktu);
        try { elementWaktu.clear(); } catch(Exception e) {}
        if (waktu != null && !waktu.isEmpty()) {
            elementWaktu.sendKeys(waktu);
        }

        // Isi Catatan
        if (catatan != null && !catatan.isEmpty()) {
            WebElement elementCatatan = driver.findElement(textCatatan);
            elementCatatan.clear();
            elementCatatan.sendKeys(catatan);
        }
    }

    public void klikSimpan() {
        driver.findElement(tombolSimpan).click();
    }

    public String ambilPesanAlert() {
        return driver.findElement(alertPesan).getText();
    }
}