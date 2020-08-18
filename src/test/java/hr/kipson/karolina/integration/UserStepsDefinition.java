package hr.kipson.karolina.integration;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class UserStepsDefinition {

    WebDriver driver;

    @Given("^Open the Chrome and launch the application$")
    public void open_the_chrome_and_launch_the_application() throws Throwable {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/find");
    }

    @And("^Search page will be displayed$")
    public void verify_search_page() throws Throwable {
        String actualString = driver.findElement(By.id("search")).getText();
        assertTrue(actualString.contains("Welcome to gallery!"));
    }

    @When("^User puts user name image list will be displayed$")
    public void enter_the_Username_and_Password() throws Throwable {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("pat");
        driver.findElement(By.id("password")).sendKeys("pat");
        driver.findElement(By.id("login")).click();
    }

    @Then("^User will see user images page$")
    public void verify_redirect_to_user_images_page() throws Throwable {
        String actualString = driver.findElement(By.id("images")).getText();
        assertTrue(actualString.contains("Image list"));
        driver.close();
    }
}
