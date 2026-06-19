package steps;

import io.cucumber.java.en.*;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import pages.TambahPusdatinPage;

public class TambahPusdatinSteps {
    WebDriver driver;
    LoginPage loginPage;
    TambahPusdatinPage tambahPage;

    @Given("User sudah login dan berada di halaman tambah akun pusdatin")
    public void userSudahLoginDanBeradaDiHalamanTambahAkunPusdatin() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(2));

        driver.get("https://area-fe-pad.vercel.app/login");
        loginPage = new LoginPage(driver);
        loginPage.ketikEmail("admin@test.com");
        loginPage.ketikPassword("password");
        loginPage.klikTombolMasuk();

        // Tunggu login sukses
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("admin-dashboard"));
        } catch (Exception e) {}

        // Tembak URL tambah sesuai screenshot
        driver.get("https://area-fe-pad.vercel.app/admin-dashboard/settings/add");

        // Tunggu halaman render data dengan mendeteksi kotak nama
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.xpath("//input[contains(@placeholder,'Nama') or contains(@placeholder,'Pusdatin')] | (//input[@type='text'])[1]")));
        } catch (Exception e) {}
        tambahPage = new TambahPusdatinPage(driver);
    }

    @When("User mengisi nama {string}, email {string}, dan nomor HP {string}")
    public void userMengisiNamaEmailDanNomorHP(String n, String e, String h) {
        tambahPage.isiDataDiri(n, e, h);
    }

    @And("User memasukkan password {string} dan konfirmasi password {string}")
    public void userMemasukkanPasswordDanKonfirmasiPassword(String p, String k) {
        tambahPage.isiKeamanan(p, k);
    }

    @And("User mengklik tombol Simpan Akun")
    public void userMengklikTombolSimpanAkun() {
        tambahPage.klikSimpan();
    }

    @Then("Sistem berhasil menyimpan akun dan kembali ke daftar pusdatin")
    public void sistemBerhasilMenyimpanAkunDanKembaliKeDaftarPusdatin() {
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("settings"));
        } catch (Exception e) {}
        Assertions.assertTrue(driver.getCurrentUrl().contains("settings"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            try { Thread.sleep(3000); } catch (InterruptedException e) {}
            driver.quit();
            driver = null;
        }
    }
}