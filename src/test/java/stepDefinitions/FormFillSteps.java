package stepDefinitions;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@RunWith(Cucumber.class)
    public class FormFillSteps {
    SoftAssert soft = new SoftAssert();

    @Given("^I am on lambdatest dummy registration page$")
    public void i_am_on_lambdatest_dummy_registration_page() {
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.baseUrl = "https://ecommerce-playground.lambdatest.io";
        open("/index.php?route=account/register");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        soft.assertEquals(title(), "Register Account");
    }

    @When("^I click on continue button without filling required data$")
    public void i_click_on_continue_button_without_filling_required_data() {
        SelenideElement continueBtn = $(By.xpath("//input[@value=\"Continue\"]"));
        executeJavaScript("arguments[0].click();", continueBtn);
    }

    @Then("^validate error messages$")
    public void validate_error_messages() {
        soft.assertTrue(WebDriverRunner.source().contains("First Name must be between 1 and 32 characters!"));
        soft.assertTrue(WebDriverRunner.source().contains("Last Name must be between 1 and 32 characters!"));
        soft.assertTrue(WebDriverRunner.source().contains("E-Mail Address does not appear to be valid!"));
        soft.assertTrue(WebDriverRunner.source().contains("Telephone must be between 3 and 32 characters!"));
        soft.assertTrue(WebDriverRunner.source().contains("Password must be between 4 and 20 characters!"));
    }

    @When("^I enter required data in the form and submit the form$")
    public void i_enter_required_data_in_the_form_and_submit_the_form(DataTable table) {
        //Initialize data table
        List<List<String>> data = table.cells();
        System.out.println(data.get(1).get(1));

        //Enter data into form
        $(By.name("firstname")).setValue(data.get(1).get(1));
        $(By.name("lastname")).setValue(data.get(2).get(1));
       // $(By.name("email")).setValue(data.get(3).get(1));
        $(By.name("email")).setValue(randomEmailGenerator());
        $(By.name("telephone")).setValue(data.get(3).get(1));
        $(By.name("password")).setValue(data.get(4).get(1));
        $(By.name("confirm")).setValue(data.get(5).get(1));
        SelenideElement checkbox = $(By.id("input-agree"));
        executeJavaScript("arguments[0].click();", checkbox);

        SelenideElement continueBtn = $(By.xpath("//input[@value=\"Continue\"]"));
        executeJavaScript("arguments[0].click();", continueBtn);

    }

    @Then("^validate the success message$")
    public void validate_the_success_message() {
        soft.assertTrue(WebDriverRunner.source().contains("Your Account Has Been Created!"));
        soft.assertAll();
        closeWebDriver();

    }

    public String randomEmailGenerator() {
        String generatedString = "Sanjay"+RandomStringUtils.randomAlphanumeric(5)+"@gmail.com";
       // System.out.println(generatedString);
        return generatedString;
    }

}
