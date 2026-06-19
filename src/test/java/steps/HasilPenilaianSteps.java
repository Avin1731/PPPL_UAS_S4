package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import pages.HasilPenilaianPage;

public class HasilPenilaianSteps {
    WebDriver driver;
    LoginPage loginPage;
    HasilPenilaianPage penilaianPage;

    @Given("User masuk sebagai DLH dan berada di halaman Hasil Penilaian")
    public void userMasukSebagaiDlhDanBeradaDiHalamanHasilPenilaian() {
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
        loginPage.ketikEmail("dlh001@test.com");
        loginPage.ketikPassword("password");
        loginPage.klikTombolMasuk();

        // Tunggu login sukses
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("dlh-dashboard"));
        } catch (Exception e) {}

        driver.get("https://area-fe-pad.vercel.app/dlh-dashboard/penilaian/hasil-penilaian");

        // Jeda waktu tunggu halaman render
        try { Thread.sleep(6000); } catch (InterruptedException e) {}
        penilaianPage = new HasilPenilaianPage(driver);
    }

    @When("Modal alert {string} muncul di layar")
    public void modalAlertMunculDiLayar(String text) {
        Assertions.assertTrue(penilaianPage.modalMuncul(), "Modal Belum Dimulai terlihat di layar.");
    }

    @And("User mengklik tombol Mengerti untuk menutup alert")
    public void userMengklikTombolMengertiUntukMenutupAlert() {
        penilaianPage.klikMengerti();
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }

    @Then("Modal alert harus tertutup dari tampilan layar")
    public void modalAlertHarusTertutupDariTampilanLayar() {
        Assertions.assertFalse(penilaianPage.modalMuncul(), "Modal Belum Dimulai telah ditutup.");
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        driver.quit();
    }
}
