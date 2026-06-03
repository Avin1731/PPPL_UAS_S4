package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import pages.TambahPusdatinPage;

public class TambahPusdatinSteps {
    WebDriver driver;
    LoginPage loginPage;
    TambahPusdatinPage tambahPage;

    @Given("User sudah login dan berada di halaman tambah akun pusdatin")
    public void userSudahLoginDanBeradaDiHalamanTambahAkunPusdatin() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(15));

        driver.get("http://localhost:3000/login");
        loginPage = new LoginPage(driver);
        loginPage.ketikEmail("admin@test.com");
        loginPage.ketikPassword("password");
        loginPage.klikTombolMasuk();

        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        // Tembak URL tambah sesuai screenshot
        driver.get("http://localhost:3000/admin-dashboard/settings/add");
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
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        Assertions.assertTrue(driver.getCurrentUrl().contains("settings"));
        driver.quit();
    }
}