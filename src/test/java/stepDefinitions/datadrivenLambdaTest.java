package stepDefinitions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
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
public class datadrivenLambdaTest {
        SoftAssert soft= new SoftAssert();

        @Given("^user on Lambda Test registration page$")
        public void user_on_lambda_test_registration_page() {
                Configuration.browser = "chrome";
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

        @When("^user entere data as \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
        public void user_entere_data_as_something_something_something_something_something_something_something_something_something_and_something(String name, String email, String password, String company, String website, String city, String address1, String address2, String state, String zip) {

                $(By.name("name")).setValue(name);
                $(By.id("inputEmail4")).setValue(email);
                $(By.name("password")).setValue(password);
                $(By.name("company")).setValue(company);
                $(By.name("website")).setValue(website);

                Select countrydr=new Select($(By.name("country")));
                countrydr.selectByVisibleText("India");

                $(By.name("city")).setValue(city);
                $(By.name("address_line1")).setValue(address1);
                $(By.name("address_line2")).setValue(address2);
                $(By.id("inputState")).setValue(state);
                $(By.name("zip")).setValue(zip);

        }
        @And("^user click on submit button$")
        public void user_click_on_submit_button()  {
                SelenideElement submitButton = $(By.cssSelector("button[type='submit']"));
                executeJavaScript("arguments[0].click();", submitButton);
        }
        @Then("^validate success message$")
        public void validate_success_message()  {
                soft.assertTrue(WebDriverRunner.source().contains("Thanks for contacting us, we will get back to you shortly."));
                soft.assertAll();
                closeWebDriver();
        }


    }

