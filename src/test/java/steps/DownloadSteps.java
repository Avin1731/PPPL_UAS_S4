package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import pages.DokumenPage; // Pastikan import class DokumenPage

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DownloadSteps {
    WebDriver driver;
    LoginPage loginPage;
    DokumenPage dokumenPage; // Gunakan DokumenPage, bukan HasilPenilaianPage

    // Tentukan direktori spesifik untuk menyimpan hasil unduhan
    String downloadPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "downloads";

    @Given("User masuk sebagai DLH dan berada di halaman Unduh Template Dokumen")
    public void userMasukSebagaiDlhDanBeradaDiHalamanUnduhTemplateDokumen() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        // === KONFIGURASI LOKASI DOWNLOAD ===
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", downloadPath);
        options.setExperimentalOption("prefs", prefs);
        // ===================================

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

        driver.get("https://area-fe-pad.vercel.app/dlh-dashboard/pengiriman-data/template");

        // Jeda waktu tunggu halaman render
        try { Thread.sleep(6000); } catch (InterruptedException e) {}

        // Inisialisasi halaman dokumen
        dokumenPage = new DokumenPage(driver);
    }

    @When("user menekan tombol unduh template")
    public void userMenekanTombolUnduhTemplate() {
        // Panggil method untuk klik tombol dari Page Object
        dokumenPage.klikTombolUnduh();

        // Beri jeda agar browser punya waktu untuk memulai proses unduhan
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
    }

    @Then("file template berhasil diunduh ke direktori lokal")
    public void fileTemplateBerhasilDiunduhKeDirektoriLokal() {
        // Ganti string ini dengan nama file yang BENAR-BENAR diunduh dari sistemmu
        // Misalnya: "template_pengiriman_data.xlsx" atau .pdf
        String expectedFileName = "Keanekaragaman_Hayati_Templates.zip";

        File downloadedFile = new File(downloadPath + File.separator + expectedFileName);

        boolean fileExists = false;

        // Cek secara berkala (maksimal 15 detik) apakah file sudah masuk ke folder
        for (int i = 0; i < 15; i++) {
            if (downloadedFile.exists()) {
                fileExists = true;
                break;
            }
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }

        // Verifikasi bahwa file ditemukan
        Assertions.assertTrue(fileExists, "Gagal menemukan file " + expectedFileName + " di folder: " + downloadPath);

        // Hapus file agar folder tetap bersih untuk tes selanjutnya
        if (fileExists) {
            downloadedFile.delete();
        }

        // Jeda 3 detik agar terlihat oleh pengguna sebelum menutup
        try { Thread.sleep(3000); } catch (InterruptedException e) {}

        // Tutup browser
        if (driver != null) {
            driver.quit();
        }
    }
}