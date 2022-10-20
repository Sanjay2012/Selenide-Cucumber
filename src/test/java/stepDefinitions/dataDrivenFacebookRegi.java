package stepDefinitions;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@RunWith(Cucumber.class)
    public class dataDrivenFacebookRegi {
    SoftAssert soft=new SoftAssert();

    @Given("^User on the new user registration page$")
    public void user_on_the_new_user_registration_page() {
        Configuration.browser = "chrome";
        Configuration.headless=true;
        Configuration.baseUrl = "https://www.facebook.com";
        open("/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        $(By.linkText("Create New Account"))
                .shouldBe(visible)
                .shouldHave(text("Create New Account"))
                .click();
        soft.assertEquals(WebDriverRunner.url(), "https://www.facebook.com/");
        soft.assertTrue(WebDriverRunner.source().contains("Facebook – log in or sign up"));

    }

    @When("^User enter invalid data as \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_enter_invalid_data_as_something_something_something_something_and_something(String firstname, String lastname, String emailaddress, String reenteremailaddress, String password) {

        $(By.name("firstname")).setValue(firstname);
        $(By.name("lastname")).setValue(lastname);
        $(By.name("reg_email__")).setValue(emailaddress);
        $(By.name("reg_email_confirmation__")).setValue(reenteremailaddress);
        $(By.name("reg_passwd__")).setValue(password);

        Select dropdownB = new Select($(By.name("birthday_day")));
        dropdownB.selectByValue("15");

        Select dropdownM = new Select($(By.name("birthday_month")));
        dropdownM.selectByValue("6");

        Select dropdownY = new Select($(By.name("birthday_year")));
        dropdownY.selectByValue("1990");
    }

        @And("^user click on submit Button$")
        public void user_click_on_submit_button() {
            $(By.className("_58mt")).click();
        }
    @Then("^user registration should be unsuccessful$")
    public void user_registration_should_be_unsuccessful()  {
        if(WebDriverRunner.url().equalsIgnoreCase("https://www.facebook.com/")){
            System.out.println("Test Pass");
        } else {
            System.out.println("Test Failed");
        }

        soft.assertAll();
        closeWebDriver();
    }

    }
