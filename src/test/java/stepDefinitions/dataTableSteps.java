package stepDefinitions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

@RunWith(Cucumber.class)
public class dataTableSteps {
    SoftAssert soft=new SoftAssert();

    @Given("I am on the new user registration page")
    public void i_am_on_the_new_user_registration_page()  {
        Configuration.browser = "edge";
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

    @When("I enter invalid data on the page")
    public void i_enter_invalid_data_on_the_page(DataTable table)  {
        //Initialize data table
        List<List<String>> data = table.cells();
        System.out.println(data.get(1).get(1));

        //Enter data into form
        $(By.name("firstname")).setValue(data.get(1).get(1));
        $(By.name("lastname")).setValue(data.get(2).get(1));
        $(By.name("reg_email__")).setValue(data.get(3).get(1));
        $(By.name("reg_email_confirmation__")).setValue(data.get(4).get(1));
        $(By.name("reg_passwd__")).setValue(data.get(5).get(1));

        Select dropdownB = new Select($(By.name("birthday_day")));
        dropdownB.selectByValue("15");

        Select dropdownM = new Select($(By.name("birthday_month")));
        dropdownM.selectByValue("6");

        Select dropdownY = new Select($(By.name("birthday_year")));
        dropdownY.selectByValue("1990");

        $(By.className("_58mt")).click();

    }

    @Then("the user registration should be unsuccessful")
    public void the_user_registration_should_be_unsuccessful()  {
        if(WebDriverRunner.url().equalsIgnoreCase("https://www.facebook.com/")){
            System.out.println("Test Pass");
        } else {
            System.out.println("Test Failed");
        }

        soft.assertAll();
        closeWebDriver();

    }

}
