//http://www.testautomationguru.com/selenium-webdriver-how-to-run-automated-tests-inside-a-docker-container/
package seleniunInDocker;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import org.awaitility.Awaitility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DownloadTest {

    private WebDriver driver;
    private PageObjectForDownload pageObjectForDownload;

    @BeforeTest
    public void setUp() throws MalformedURLException {
/*        DesiredCapabilities dc = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
        */
        
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver.get("http://the-internet.herokuapp.com/download");
    }

    @Test
    public void pageObjectForDownload() throws InterruptedException {
    	pageObjectForDownload = new PageObjectForDownload(driver);
    	pageObjectForDownload.goTo();
    	pageObjectForDownload.downloadFile();
        //Path path = Paths.get("/home/vins/Downloads/some-file.txt");
    	Path path = Paths.get("/Users/ahoss1/Downloads/some-file.txt");

        //this will wait until the file download is complete.
        Awaitility.await()
            .atMost(1, TimeUnit.MINUTES)
            .until(() -> {
                return path.toFile().exists();
            });


        Assert.assertTrue(path.toFile().exists());
    }
    
    @AfterTest
    public void tearDown() throws InterruptedException {
        driver.quit();
    }    

}
