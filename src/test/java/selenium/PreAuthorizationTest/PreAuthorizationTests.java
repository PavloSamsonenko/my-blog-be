package selenium.PreAuthorizationTest;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class PreAuthorizationTests {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeEach
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
  }

  @Test
  public void testSignUpFail() throws IOException {
    driver.get("http://localhost:3000/");
    driver.findElement(By.cssSelector(".header-sign-up-button")).click();
    takeScreenshot("scr1.png");
    driver.findElement(By.cssSelector("#root > div > div")).click();
    takeScreenshot("scr2.png");
    driver.findElement(By.cssSelector(".MuiButton-fullWidth")).click();
    takeScreenshot("scr3.png");
    driver.close();
  }

  @Test
  public void testSignUpSuccess() {
    driver.get("http://localhost:3000/");
    driver.findElement(By.cssSelector(".header-sign-up-button")).click();
    driver.findElement(By.id("email")).sendKeys("test@test");
    driver.findElement(By.id("username")).sendKeys("test");
    driver.findElement(By.id("password")).sendKeys("test");
    driver.findElement(By.id("confirmPassword")).sendKeys("test");
    driver.findElement(By.cssSelector(".MuiButton-fullWidth")).click();
    driver.close();
  }

  @Test
  public void testForgotPasswordSuccess() {
    driver.get("http://localhost:3000/login");
    driver.findElement(By.cssSelector(".header-sign-up-button")).click();
    driver.findElement(By.cssSelector(".css-1vd824g-MuiGrid-root")).click();
    driver.findElement(By.id("email")).sendKeys("test@test");
    driver.findElement(By.cssSelector(".MuiButton-fullWidth")).click();
    driver.findElement(By.id("1")).click();
    driver.close();
  }

  @Test
  public void testLogOutSuccess() {
    driver.get("http://localhost:3000/");
    login();
    driver.findElement(By.cssSelector(".header-log-oug-button")).click();
    driver.close();
  }

  @Test
  public void testSignInFail() {
    driver.get("http://localhost:3000/");
    driver.findElement(By.cssSelector(".header-sign-in-button")).click();
    driver.findElement(By.cssSelector("#root > div > div")).click();
    driver.findElement(By.cssSelector(".MuiButton-fullWidth")).click();
    driver.findElement(By.cssSelector("#root > div > div")).click();
    driver.close();
  }

  @Test
  public void testSignInSuccess() {
    driver.get("http://localhost:3000/");
    login();
    driver.close();
  }

  private void login() {
    driver.findElement(By.cssSelector(".header-sign-in-button")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).sendKeys("test@test");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("test");
    driver.findElement(By.cssSelector(".MuiButton-fullWidth")).click();
  }

  private void takeScreenshot(String screenName) throws IOException {
    TakesScreenshot screenshot = ((TakesScreenshot) driver);
    File SrcFile = screenshot.getScreenshotAs(OutputType.FILE);

    File DestFile = new File("src/test/java/selenium/MyProfileTests/screenshots/" + screenName);

    FileUtils.copyFile(SrcFile, DestFile);
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
