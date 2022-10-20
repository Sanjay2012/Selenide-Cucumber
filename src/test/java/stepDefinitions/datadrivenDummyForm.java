package stepDefinitions;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

@RunWith(Cucumber.class)
    public class datadrivenDummyForm {
    SoftAssert soft=new SoftAssert();
        @Given("^User on lambdatest dummy registration page$")
        public void user_on_lambdatest_dummy_registration_page()  {
            Configuration.browser = "chrome";
            Configuration.headless = true;
            Configuration.baseUrl = "https://ecommerce-playground.lambdatest.io";
            open("/index.php?route=account/register");
            WebDriverRunner.getWebDriver().manage().window().maximize();
            WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            soft.assertEquals(title(), "Register Account");
        }

        @When("^User click on continue button without filling required data$")
        public void user_click_on_continue_button_without_filling_required_data()  {
            SelenideElement continueBtn = $(By.xpath("//input[@value=\"Continue\"]"));
            executeJavaScript("arguments[0].click();", continueBtn);
        }

        @Then("^validate field error messages$")
        public void validate_field_error_messages()  {
            soft.assertTrue(WebDriverRunner.source().contains("First Name must be between 1 and 32 characters!"));
            soft.assertTrue(WebDriverRunner.source().contains("Last Name must be between 1 and 32 characters!"));
            soft.assertTrue(WebDriverRunner.source().contains("E-Mail Address does not appear to be valid!"));
            soft.assertTrue(WebDriverRunner.source().contains("Telephone must be between 3 and 32 characters!"));
            soft.assertTrue(WebDriverRunner.source().contains("Password must be between 4 and 20 characters!"));
        }
    @When("^User enter valid data as \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void user_enter_valid_data_as_something_something_something_something_something(String firstname, String lastname, String phone, String password, String confirmpass) {
        $(By.name("firstname")).setValue(firstname);
        $(By.name("lastname")).setValue(lastname);

        $(By.name("email")).setValue(randomEmailGenerator());
        $(By.name("telephone")).setValue(phone);
        $(By.name("password")).setValue(password);
        $(By.name("confirm")).setValue(confirmpass);
        SelenideElement checkbox = $(By.id("input-agree"));
        executeJavaScript("arguments[0].click();", checkbox);

    }

        @Then("^validate the success message after submit the form$")
        public void validate_the_success_message_after_submit_the_form()  {
            SelenideElement continueBtn = $(By.xpath("//input[@value=\"Continue\"]"));
            executeJavaScript("arguments[0].click();", continueBtn);

            soft.assertTrue(WebDriverRunner.source().contains("Your Account Has Been Created!"));
            soft.assertAll();
            closeWebDriver();
        }

    public String randomEmailGenerator() {
        String generatedString = "Sanjay"+ RandomStringUtils.randomAlphanumeric(5)+"@gmail.com";
        return generatedString;
    }
}
