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
import pages.PengisianIKLHPage;

public class PengisianIKLHSteps {
    WebDriver driver;
    LoginPage loginPage;
    PengisianIKLHPage iklhPage;
    String lastAir;
    String lastUdara;

    @Given("User masuk sebagai DLH dan berada di halaman unggah nilai IKLH")
    public void userMasukSebagaiDlhDanBeradaDiHalamanUnggahNilaiIklh() {
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
        loginPage.ketikEmail("dlh001@test.com");
        loginPage.ketikPassword("password");
        loginPage.klikTombolMasuk();

        // Tunggu login sukses
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("dlh-dashboard"));
        } catch (Exception e) {}

        driver.get("https://area-fe-pad.vercel.app/dlh-dashboard/pengiriman-data/iklh");

        // Tunggu halaman render data dengan mendeteksi input number pertama (Indeks Kualitas Air)
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.xpath("(//input[@type='number'])[1]")));
        } catch (Exception e) {}
        iklhPage = new PengisianIKLHPage(driver);
    }

    @When("User mengisi nilai Indeks Kualitas Air {string} dan Indeks Kualitas Udara {string}")
    public void userMengisiNilaiIndeksKualitasAirDanIndeksKualitasUdara(String air, String udara) {
        lastAir = air;
        lastUdara = udara;
        iklhPage.isiSkorIKLH(air, udara);
    }

    @And("User mengklik tombol Simpan Perubahan Nilai")
    public void userMengklikTombolSimpanPerubahanNilai() {
        iklhPage.klikSimpan();
    }

    @Then("Sistem harus berhasil memproses penyimpanan data nilai IKLH")
    public void sistemHarusBerhasilMemprosesPenyimpananDataNilaiIklh() {
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("iklh"));
        } catch (Exception e) {}
        Assertions.assertTrue(driver.getCurrentUrl().contains("iklh"));

        // PENETAPAN VALIDASI RIIL: Refresh halaman & verifikasi bahwa nilai benar-benar terupdate di server
        driver.navigate().refresh();
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.xpath("(//input[@type='number'])[1]")));
        } catch (Exception e) {}

        String valAir = driver.findElement(org.openqa.selenium.By.xpath("(//input[@type='number'])[1]")).getAttribute("value");
        String valUdara = driver.findElement(org.openqa.selenium.By.xpath("(//input[@type='number'])[2]")).getAttribute("value");

        System.out.println("📊 Nilai kualitas air di server setelah reload: " + valAir + " (Diharapkan: " + lastAir + ")");
        System.out.println("📊 Nilai kualitas udara di server setelah reload: " + valUdara + " (Diharapkan: " + lastUdara + ")");

        try {
            Assertions.assertEquals(lastAir, valAir, "Nilai kualitas air harus tersimpan di database setelah disimpan!");
            Assertions.assertEquals(lastUdara, valUdara, "Nilai kualitas udara harus tersimpan di database setelah disimpan!");
        } catch (AssertionError e) {
            System.err.println("❌ TEST GAGAL: Nilai IKLH yang tersimpan di server tidak cocok!");
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
