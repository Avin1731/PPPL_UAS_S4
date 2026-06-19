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
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(2));

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

        // Tunggu hingga tab IKLH render di layar
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.xpath("//button[contains(.,'IKLH')] | //*[text()='IKLH']")));
        } catch (Exception e) {}
        verifPage = new VerifikasiIKLHPage(driver);
    }

    @When("User mengklik tab IKLH")
    public void userMengklikTabIklh() {
        verifPage.klikTabIklh();
        // Tunggu hingga data IKLH render di layar (mencari teks Aceh Barat atau tombol Terima)
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.xpath("//td[contains(text(),'Kabupaten Aceh Barat')] | //button[contains(.,'Terima')]")));
        } catch (Exception e) {}
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
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("kab-kota"));
        } catch (Exception e) {}
        // Verifikasi URL tetap aman di panel penerimaan data
        Assertions.assertTrue(driver.getCurrentUrl().contains("kab-kota"));

        // PENETAPAN VALIDASI RIIL: Refresh halaman & verifikasi bahwa tombol Terima sudah hilang (karena sudah disetujui)
        driver.navigate().refresh();
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.xpath("//button[contains(.,'IKLH')] | //*[text()='IKLH']")));
        } catch (Exception e) {}

        // Klik kembali tab IKLH
        verifPage.klikTabIklh();

        // Tunggu tabel render
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.xpath("//td[contains(text(),'Kabupaten Aceh Barat')]")));
        } catch (Exception e) {}

        String xpathTombolTerima = "//td[contains(text(),'Kabupaten Aceh Barat')]/following-sibling::td//button[contains(.,'Terima')]";
        boolean tombolTerimaMasihAda = !driver.findElements(org.openqa.selenium.By.xpath(xpathTombolTerima)).isEmpty();

        System.out.println("🔍 Apakah tombol Terima Kabupaten Aceh Barat masih ada setelah reload? " + tombolTerimaMasihAda);

        try {
            Assertions.assertFalse(tombolTerimaMasihAda, "Tombol 'Terima' harusnya sudah hilang/dinonaktifkan setelah verifikasi sukses disimpan!");
        } catch (AssertionError e) {
            System.err.println("❌ TEST GAGAL: Tombol 'Terima' masih muncul/aktif setelah diklik dan disimpan!");
            throw e;
        }
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
