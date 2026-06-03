package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    // 1. Ini alamat kotak input dan tombol sesuai foto web SIPELITA Princess kemarin
    By kotakEmail = By.id("email");
    By kotakPassword = By.id("password");
    By tombolLogin = By.xpath("//button[text()='Login']");
    By pesanError = By.id("error-message"); // Nanti disesuaikan id error aslinya ya

    // 2. Jembatan penghubung driver robot
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // 3. Gerakan atau aksi yang bisa dilakukan si robot
    public void ketikEmail(String email) {
        driver.findElement(kotakEmail).sendKeys(email);
    }

    public void ketikPassword(String password) {
        driver.findElement(kotakPassword).sendKeys(password);
    }

    public void klikTombolMasuk() {
        driver.findElement(tombolLogin).click();
    }

    public String ambilPesanError() {
        return driver.findElement(pesanError).getText();
    }
}