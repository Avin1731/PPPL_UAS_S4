package pages;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class DriverSetup {
    public static ChromeDriver getDriver() {
        // Tentukan path folder download (misal di folder target project)
        String downloadFilepath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "downloads";

        // Buat foldernya jika belum ada
        File folder = new File(downloadFilepath);
        if(!folder.exists()){
            folder.mkdirs();
        }

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", downloadFilepath);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        return new ChromeDriver(options);
    }
}