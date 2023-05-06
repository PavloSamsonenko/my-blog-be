package selenium.PostTest;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class PostsTests {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;

  @BeforeEach
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
  }

  @Test
  public void testAddPostCommentarySuccess() {
    driver.get("http://localhost:3000/");
    login();
    driver
        .findElement(By.cssSelector("div:nth-child(2) > .MuiButtonBase-root:nth-child(1)"))
        .click();
    driver.findElement(By.cssSelector(".MuiPaper-root:nth-child(1) .MuiButtonBase-root")).click();
    driver.findElement(By.id("commentary")).click();
    driver.findElement(By.id("commentary")).clear();
    driver.findElement(By.id("commentary")).sendKeys("test");
    driver.findElement(By.cssSelector(".MuiButton-fullWidth")).click();
    driver.close();
  }

  @Test
  public void testBrowseAllPostsSuccess() {
    driver.get("http://localhost:3000/");
    login();
    driver
        .findElement(By.cssSelector("div:nth-child(2) > .MuiButtonBase-root:nth-child(1)"))
        .click();
    driver.findElement(By.cssSelector(".posts-page-main")).click();
    driver.close();
  }

  @Test
  public void testCheckPostSuccess() {
    driver.get("http://localhost:3000/");
    login();
    driver
        .findElement(By.cssSelector("div:nth-child(2) > .MuiButtonBase-root:nth-child(1)"))
        .click();
    driver.findElement(By.cssSelector(".MuiButton-text")).click();
    driver.findElement(By.cssSelector(".MuiTypography-p")).click();
    driver.findElement(By.cssSelector(".post-page-main")).click();
    driver.close();
  }

  @Test
  public void testCreatePostSuccess() {
    driver.get("http://localhost:3000/");
    login();
    driver
        .findElement(By.cssSelector("div:nth-child(2) > .MuiButtonBase-root:nth-child(2)"))
        .click();
    driver.findElement(By.id(":r2:")).sendKeys("Test Theme");
    driver.findElement(By.id(":r3:")).sendKeys("Test Content");
    driver.findElement(By.cssSelector(".MuiButton-fullWidth")).click();
    driver.close();
  }

  @Test
  public void testCreatePostFail() {
    driver.get("http://localhost:3000/");
    login();
    driver
        .findElement(By.cssSelector("div:nth-child(2) > .MuiButtonBase-root:nth-child(2)"))
        .click();
    driver.findElement(By.cssSelector(".MuiButton-fullWidth")).click();
    driver.close();
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private void login() {
    driver.findElement(By.cssSelector(".header-sign-in-button")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).sendKeys("test@test");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("test");
    driver.findElement(By.cssSelector(".MuiButton-fullWidth")).click();
  }
}
