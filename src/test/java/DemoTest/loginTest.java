package DemoTest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class loginTest {

    RemoteWebDriver driver;
    DesiredCapabilities dc;

    @Parameters("browser")
    @BeforeTest
    public void setup(String br) throws MalformedURLException, URISyntaxException {
        dc = new DesiredCapabilities();
        if (br.equalsIgnoreCase("chrome")) {
            dc.setBrowserName("chrome");
            dc.setPlatform(Platform.LINUX);
        } 
//        else if (br.equalsIgnoreCase("firefox")) {
//            dc.setBrowserName("firefox");
//            dc.setPlatform(Platform.LINUX);
//        }

        // Corrected "locathost" to "localhost"
        URL url = new URI("http://localhost:4444/wd/hub").toURL();

        driver = new RemoteWebDriver(url, dc);

        // Navigate to the login page
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    public void login() throws InterruptedException {
    	Thread.sleep(5000);
    	System.out.println(driver.getTitle());
        // Locate elements and perform login
        driver.findElement(By.cssSelector("[name='username']")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        // Wait for 5 seconds to observe the result (use WebDriverWait in production)
        Thread.sleep(5000);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
