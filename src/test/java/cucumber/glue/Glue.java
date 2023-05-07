package cucumber.glue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Glue {
  private WebDriver driver;
  private final Map<String, String> findMap =
      Map.of(
          "Log In",
          ".MuiButton-fullWidth",
          "Forgot Password",
          ".css-1vd824g-MuiGrid-root",
          "My Profile",
          "div:nth-child(1) > div:nth-child(1) > p",
          "Submit changes",
          ".css-13jw4fr > .css-1m447wf-MuiButtonBase-root-MuiButton-root",
          "Create post",
          "div:nth-child(2) > .MuiButtonBase-root:nth-child(2)",
          "Create Post",
          ".MuiButton-fullWidth",
          "post theme",
          ":r2:",
          "post content",
          ":r3:",
          "Comment",
          ".MuiButton-fullWidth");

  private final Map<String, String> endpoints =
      Map.of(
          "Login",
          "login",
          "Registration",
          "register",
          "My Profile",
          "profile",
          "Create Post",
          "post/create",
          "All Posts",
          "posts");



  @Before
  public void init() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    driver.get("http://localhost:3000");
  }

  @Given("I am a logged in user")
  public void iAmALoggedInUser() {
    driver.findElement(By.cssSelector(".header-sign-in-button")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).sendKeys("test@test");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("test");
    driver.findElement(By.cssSelector(".MuiButton-fullWidth")).click();
  }

  @When("I click on {string} button on the {string} page")
  public void iClickOnLinkOnThePage(String arg0, String arg1) {
    driver.findElement(By.cssSelector(".header-log-oug-button")).click();
  }

  @Then("I should be successfully logged out")
  public void iShouldBeSuccessfullyLoggedOut() {
    assertEquals(driver.getCurrentUrl(), "http://localhost:3000/login");
  }

  @And("I should land on the {string} page")
  public void iShouldLandOnThePage(String arg0) {
    assertEquals(driver.getCurrentUrl(), "http://localhost:3000/" + endpoints.get(arg0));
  }

  @And("I click on  the {string} button")
  public void iClickOnTheButton(String arg0) {
    driver.findElement(By.cssSelector(".MuiButton-fullWidth")).click();
  }

  @Then("I should be successfully registered")
  public void iShouldBeSuccessfullyRegistered() {
    assertEquals(driver.getCurrentUrl(), "http://localhost:3000/login");
  }

  @Given("I am a registered user")
  public void iAmARegisteredUser() {}

  @And("I navigate to the {string} page")
  public void iNavigateToThePage(String arg0) {
    driver.get("http://localhost:3000/" + endpoints.get(arg0));
  }

  @When("I fill in {string} with {string}")
  public void iFillInWith(String arg0, String arg1) {
    driver.findElement(By.id(arg0)).sendKeys(arg1);
  }

  @And("I click on the {string} button")
  public void iClickOnTheButon(String arg0) {
    driver.findElement(By.cssSelector(findMap.get(arg0))).click();
  }

  @Then("I should be successfully logged in")
  public void iShouldBeSuccessfullyLoggedIn() {
    assertEquals(driver.getCurrentUrl(), "http://localhost:3000/login");
    driver.close();
  }

  @Then("I should see {string} message")
  public void iShouldSeeMessage(String arg0) {
    assertEquals(driver.findElement(By.id("email-helper-text")).getText(), "Email is not valid");
    driver.close();
  }

  @Then("I should see {string} message for {string} field on {string} page")
  public void iShouldSeeMessageForFieldOnPage(String arg0, String arg1, String arg2) {
    assertEquals(driver.findElement(By.id(arg1 + "-helper-text")).getText(), arg0);
    driver.close();
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @And("I click {string} link")
  public void iClickLink(String arg0) {
    driver.findElement(By.cssSelector(findMap.get(arg0))).click();
  }

  @And("I click on {string} button")
  public void iClickOnButton(String arg0) {
    driver.findElement(By.cssSelector(".MuiButton-fullWidth")).click();
  }

  @Then("I should land on the  {string} page")
  public void iShouldLandOnThePag(String arg0) {
    assertEquals(driver.getCurrentUrl(), "http://localhost:3000/profile");
  }

  @And("{string} should be <username>")
  public void shouldBeUsername(String arg0) {}

  @And("I fill in email as {string}")
  public void iFillInEmailAs(String arg0) {
    int buff = driver.findElement(By.id(":r2:")).getAttribute("value").length();
    for (int i = 0; i < buff; i++) {
      driver.findElement(By.id(":r2:")).sendKeys(Keys.BACK_SPACE);
    }
    driver.findElement(By.id(":r2:")).sendKeys(arg0);
  }

  @And("I fill in username as {string}")
  public void iFillInUsernameAs(String arg0) {
    int buff = driver.findElement(By.id(":r3:")).getAttribute("value").length();
    for (int i = 0; i < buff; i++) {
      driver.findElement(By.id(":r3:")).sendKeys(Keys.BACK_SPACE);
    }
    driver.findElement(By.id(":r3:")).sendKeys(arg0);
  }

  @When("I click on {string}  button")
  public void clickOnButton(String arg0) {
    driver.findElement(By.cssSelector(".css-1m447wf-MuiButtonBase-root-MuiButton-root")).click();
  }

  @And("I fill  in {string} with {string}")
  public void fillInWith(String arg0, String arg1) {
    driver.findElement(By.id(findMap.get(arg0))).sendKeys(arg1);
  }

  @And("I should see the new blog listing on the Homepage")
  public void iShouldSeeTheNewBlogListingOnTheHomepage() {
    driver.findElement(By.cssSelector(".MuiPaper-root:nth-child(1) .MuiButtonBase-root")).click();
    assertTrue(driver.getCurrentUrl().contains("/post"));
  }

  @When("I click on the {string} link")
  public void iClickOnTheLink(String arg0) {
    driver.findElement(By.cssSelector(".MuiPaper-root:nth-child(1) .MuiButtonBase-root")).click();
  }

  @Then("I should  land on the {string} page")
  public void shouldLandOnThePage(String arg0) {
    assertTrue(driver.getCurrentUrl().contains("/post"));
  }

  @And("I fill in {string} with  {string}")
  public void IfilInWith(String arg0, String arg1) {
    driver.findElement(By.id("commentary")).sendKeys(arg1);
  }
}
