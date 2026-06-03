package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TambahPusdatinPage {
    WebDriver driver;

    // SINTAKS XPATH DIBAWAH INI SUDAH DIPERBAIKI (Kondisi logika 'or' berada di dalam kurung siku)
    By inputNama = By.xpath("//input[contains(@placeholder,'Nama') or contains(@placeholder,'Pusdatin')] | (//input[@type='text'])[1]");
    By inputEmail = By.xpath("//input[@type='email'] | //input[contains(@placeholder,'email') or contains(@placeholder,'Email')]");
    By inputHP = By.xpath("//input[contains(@placeholder,'08') or contains(@placeholder,'HP') or contains(@placeholder,'No')]");
    By inputPassword = By.xpath("(//input[@type='password'])[1]");
    By inputKonfirmasi = By.xpath("(//input[@type='password'])[2] | //input[contains(@placeholder,'Ulangi')]");
    By tombolSimpan = By.xpath("//button[contains(.,'Simpan') or contains(.,'Akun')]");

    public TambahPusdatinPage(WebDriver driver) {
        this.driver = driver;
    }

    public void isiDataDiri(String nama, String email, String hp) {
        // Mengisi Nama
        WebElement elNama = driver.findElement(inputNama);
        elNama.clear();
        elNama.sendKeys(nama);

        // Mengisi Email
        WebElement elEmail = driver.findElement(inputEmail);
        elEmail.clear();
        elEmail.sendKeys(email);

        // Mengisi Nomor HP
        WebElement elHP = driver.findElement(inputHP);
        elHP.clear();
        elHP.sendKeys(hp);
    }

    public void isiKeamanan(String pass, String konfirmasi) {
        // Mengisi Password Utama
        WebElement elPass = driver.findElement(inputPassword);
        elPass.clear();
        elPass.sendKeys(pass);

        // Mengisi Konfirmasi Password
        WebElement elKonf = driver.findElement(inputKonfirmasi);
        elKonf.clear();
        elKonf.sendKeys(konfirmasi);
    }

    public void klikSimpan() {
        driver.findElement(tombolSimpan).click();
    }
}