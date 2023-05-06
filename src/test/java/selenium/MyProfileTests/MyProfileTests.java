package selenium.MyProfileTests;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MyProfileTests {
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
  public void testMyProfileChangeDataSuccess() {
    driver.get("http://localhost:3000/");
    login();
    driver.findElement(By.cssSelector("div:nth-child(1) > div:nth-child(1) > p")).click();
    driver.findElement(By.cssSelector(".css-1m447wf-MuiButtonBase-root-MuiButton-root")).click();
    driver
        .findElement(
            By.cssSelector(".css-13jw4fr > .css-1m447wf-MuiButtonBase-root-MuiButton-root"))
        .click();
    driver.close();
  }

  @Test
  public void testMyProfileChangePasswordSuccess() {
    driver.get("http://localhost:3000/");
    login();
    driver.findElement(By.cssSelector("div:nth-child(1) > div:nth-child(1) > p")).click();
    driver
        .findElement(By.cssSelector(".MuiBox-root > .css-sghohy-MuiButtonBase-root-MuiButton-root"))
        .click();
    driver.findElement(By.id(":r2:")).sendKeys("test");
    driver.findElement(By.id("confirmPassword")).sendKeys("test");
    driver
        .findElement(
            By.cssSelector(
                ".MuiBox-root:nth-child(3) > .css-1m447wf-MuiButtonBase-root-MuiButton-root"))
        .click();
    driver.close();
  }

  @Test
  public void testMyProfileChangeDataFail() {
    driver.get("http://localhost:3000/");
    login();
    driver.findElement(By.cssSelector("div:nth-child(1) > div:nth-child(1) > p")).click();
    driver.findElement(By.cssSelector(".css-1m447wf-MuiButtonBase-root-MuiButton-root")).click();
    driver.findElement(By.cssSelector(".MuiModal-root")).click();

    int buff = driver.findElement(By.id(":r2:")).getAttribute("value").length();
    for (int i = 0; i < buff; i++) {
      driver.findElement(By.id(":r2:")).sendKeys(Keys.BACK_SPACE);
    }
    buff = driver.findElement(By.id(":r3:")).getAttribute("value").length();
    for (int i = 0; i < buff; i++) {
      driver.findElement(By.id(":r3:")).sendKeys(Keys.BACK_SPACE);
    }
    driver
        .findElement(
            By.cssSelector(".css-13jw4fr > .css-1m447wf-MuiButtonBase-root-MuiButton-root"))
        .click();
  }

  @Test
  public void testMyProfileSuccess() {
    driver.get("http://localhost:3000/");
    login();
    driver.findElement(By.cssSelector("div:nth-child(1) > div:nth-child(1) > p")).click();
    driver.findElement(By.cssSelector(".css-1m447wf-MuiButtonBase-root-MuiButton-root")).click();
    driver.findElement(By.cssSelector(".css-1v45x4w-MuiButtonBase-root-MuiButton-root")).click();
    driver
        .findElement(By.cssSelector(".MuiBox-root > .css-sghohy-MuiButtonBase-root-MuiButton-root"))
        .click();
    driver.findElement(By.cssSelector(".css-1v45x4w-MuiButtonBase-root-MuiButton-root")).click();
    driver.close();
  }

  @Test
  public void testMyProfileChangePasswordFail() {
    driver.get("http://localhost:3000/");
    login();
    driver.findElement(By.cssSelector("div:nth-child(1) > div:nth-child(1) > p")).click();
    driver
        .findElement(By.cssSelector(".MuiBox-root > .css-sghohy-MuiButtonBase-root-MuiButton-root"))
        .click();
    driver
        .findElement(
            By.cssSelector(
                ".MuiBox-root:nth-child(3) > .css-1m447wf-MuiButtonBase-root-MuiButton-root"))
        .click();
    driver.findElement(By.cssSelector(".css-1v45x4w-MuiButtonBase-root-MuiButton-root")).click();
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

  @AfterEach
  public void tearDown() {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
