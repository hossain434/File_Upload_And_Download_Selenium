//http://www.testautomationguru.com/selenium-webdriver-how-to-run-automated-tests-inside-a-docker-container/
package seleniunInDocker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectForDownload {

    private final WebDriver driver;
    @SuppressWarnings("unused")
	private final WebDriverWait wait;
    
    @FindBy(linkText="some-file.txt")
    private WebElement downloadFile;

    public PageObjectForDownload(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, 30);
    }

    public void goTo() {
        driver.get("http://the-internet.herokuapp.com/download");
    }
    
    public void downloadFile(){
        //this will download file into the /home/seluser/Downloads directory
        downloadFile.click();
    }
}