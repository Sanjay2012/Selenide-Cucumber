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
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

@RunWith(Cucumber.class)
    public class lambdaFormFillSteps {
    SoftAssert soft=new SoftAssert();

        @Given("^I am on lambda test user registration page$")
        public void i_am_on_lambda_test_user_registration_page()  {
            Configuration.browser = "firefox";
            Configuration.headless=true;
            Configuration.baseUrl = "https://www.lambdatest.com";
            open("/selenium-playground/");
            WebDriverRunner.getWebDriver().manage().window().maximize();
            WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            $(By.linkText("Input Form Submit"))
                    .shouldBe(visible)
                    .shouldHave(text("Input Form Submit"))
                    .click();
            soft.assertEquals(WebDriverRunner.url(), "https://www.lambdatest.com/selenium-playground/input-form-demo");
            soft.assertEquals(title(), "Selenium Grid Online | Run Selenium Test On Cloud");
            soft.assertTrue(WebDriverRunner.source().contains("Selenium Grid Online "));

        }

        @When("^I enter valid data in the form$")
        public void i_enter_valid_data_in_the_form(DataTable table)  {
            //Initialize data table
            List<List<String>> data = table.cells();
            System.out.println(data.get(1).get(1));

            //Enter data into form
            $(By.name("name")).setValue(data.get(1).get(1));
            $(By.id("inputEmail4")).setValue(data.get(2).get(1));
            $(By.name("password")).setValue(data.get(3).get(1));
            $(By.name("company")).setValue(data.get(4).get(1));
            $(By.name("website")).setValue(data.get(5).get(1));

            Select countrydr=new Select($(By.name("country")));
            countrydr.selectByVisibleText("India");

            $(By.name("city")).setValue(data.get(6).get(1));
            $(By.name("address_line1")).setValue(data.get(7).get(1));
            $(By.name("address_line2")).setValue(data.get(8).get(1));
            $(By.id("inputState")).setValue(data.get(9).get(1));
            $(By.name("zip")).setValue(data.get(10).get(1));

        }
    @And("^user submit the form$")
    public void user_submit_the_form()  {
        SelenideElement submitButton = $(By.cssSelector("button[type='submit']"));
        executeJavaScript("arguments[0].click();", submitButton);

    }
    @Then("^validate the message$")
    public void validate_the_message()  {
            soft.assertTrue(WebDriverRunner.source().contains("Thanks for contacting us, we will get back to you shortly."));
            soft.assertAll();
            closeWebDriver();

        }



    }
