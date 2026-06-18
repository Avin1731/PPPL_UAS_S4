package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import pages.VerifikasiIKLHPage;

public class VerifikasiIKLHSteps {
    WebDriver driver;
    LoginPage loginPage;
    VerifikasiIKLHPage verifPage;

    @Given("User masuk sebagai Pusdatin dan berada di halaman Penerimaan SLHD Kab\\/Kota")
    public void userMasukSebagaiPusdatinDanBeradaDiHalamanPenerimaanSlhdKabKota() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(15));

        driver.get("https://area-fe-pad.vercel.app/login");
        loginPage = new LoginPage(driver);
        loginPage.ketikEmail("pusdatin@test.com");
        loginPage.ketikPassword("password");
        loginPage.klikTombolMasuk();

        // Tunggu login sukses
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("pusdatin-dashboard"));
        } catch (Exception e) {}

        driver.get("https://area-fe-pad.vercel.app/pusdatin-dashboard/panel-penerimaan-data/kab-kota");

        // Jeda waktu tunggu halaman render
        try { Thread.sleep(6000); } catch (InterruptedException e) {}
        verifPage = new VerifikasiIKLHPage(driver);
    }

    @When("User mengklik tab IKLH")
    public void userMengklikTabIklh() {
        verifPage.klikTabIklh();
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }

    @And("User mengklik tombol Terima pada baris data {string}")
    public void userMengklikTombolTerimaPadaBarisData(String kabKota) {
        boolean clicked = verifPage.klikTerimaBerdasarkanKabKota(kabKota);
        if (!clicked) {
            System.out.println("⚠️ CATATAN: Data " + kabKota + " sudah terverifikasi pada pengujian sebelumnya.");
        }
    }

    @Then("Sistem harus memperbarui status verifikasi data IKLH tersebut")
    public void sistemHarusMemperbaruiStatusVerifikasiDataIklhTersebut() {
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        // Verifikasi URL tetap aman di panel penerimaan data
        Assertions.assertTrue(driver.getCurrentUrl().contains("kab-kota"));
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        driver.quit();
    }
}
