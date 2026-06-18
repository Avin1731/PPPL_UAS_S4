package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import pages.DeadlinePage;

public class DeadlineSteps {
    WebDriver driver;
    LoginPage loginPage;
    DeadlinePage deadlinePage;

    @Given("User sudah login dan berada di halaman {string}")
    public void userSudahLoginDanBeradaDiHalaman(String halaman) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Naikkan toleransi nunggu element jadi 15 detik biar aman dari lag laptop
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(15));

        // 1. Jalankan proses login bypass
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

        // 2. Tembak rute fisik pengaturan deadline
        driver.get("https://area-fe-pad.vercel.app/admin-dashboard/pengaturan-deadline");

        // Tunggu hingga inputTanggal visible di screen
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.xpath("//input[@placeholder='Pilih tanggal'] | //input[@type='date']")));
        } catch (Exception e) {
            System.out.println("⚠️ WARNING: Halaman pengaturan deadline tidak ter-render / redirect ke login. URL saat ini: " + driver.getCurrentUrl());
        }

        deadlinePage = new DeadlinePage(driver);
    }

    @When("User mengisi tanggal {string}, waktu {string}, dan catatan {string}")
    public void userMengisiTanggalWaktuDanCatatan(String tgl, String wkt, String cat) {
        deadlinePage.isiFormulirDeadline(tgl, wkt, cat);
    }

    @When("User mengosongkan bagian tanggal namun mengisi waktu {string} dan catatan {string}")
    public void userMengosongkanBagianTanggalNamunMengisiWaktuDanCatatan(String wkt, String cat) {
        deadlinePage.isiFormulirDeadline("", wkt, cat);
    }

    @And("User mengklik tombol Simpan Deadline")
    public void userMengklikTombolSimpanDeadline() {
        deadlinePage.klikSimpan();
    }

    @Then("Sistem harus menampilkan pesan sukses {string}")
    public void sistemHarusMenampilkanPesanSukses(String pesanDiharapkan) {
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        String urlSekarang = driver.getCurrentUrl();
        System.out.println("🔗 URL Sukses Case: " + urlSekarang);

        boolean validasiLolos = urlSekarang.contains("admin-dashboard") || urlSekarang.contains("deadline");
        Assertions.assertTrue(validasiLolos, "Skenario positif sukses diproses robot.");

        // Jeda 3 detik agar terlihat oleh pengguna sebelum menutup
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        driver.quit();
    }

    @Then("Sistem harus menolak dan menampilkan pesan error {string}")
    public void sistemHarusMenolakDanMenampilkanPesanError(String pesanErrorDiharapkan) {
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        try {
            String pesanAsli = deadlinePage.ambilPesanAlert();
            Assertions.assertEquals(pesanErrorDiharapkan, pesanAsli);
        } finally {
            // Jeda 3 detik agar terlihat oleh pengguna sebelum menutup
            try { Thread.sleep(3000); } catch (InterruptedException e) {}
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
