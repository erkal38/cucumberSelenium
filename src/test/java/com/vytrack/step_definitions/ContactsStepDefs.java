package com.vytrack.step_definitions;

import com.vytrack.pages.BasePage;
import com.vytrack.pages.DashboardPage;
import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ContactsStepDefs {
    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String string) {
        Driver.get().get(ConfigurationReader.get("url"));
        String username = null;
        String password = null;

        if (string.equals("driver")) {
            username = ConfigurationReader.get("driver_username");
            password = ConfigurationReader.get("driver_password");
        } else if (string.equals("sales manager")) {
            username = ConfigurationReader.get("sales_manager_username");
            password = ConfigurationReader.get("sales_manager_password");
        } else if (string.equals("store manager")) {
            username = ConfigurationReader.get("store_manager_username");
            password = ConfigurationReader.get("store_manager_password");
        }
        new LoginPage().login(username, password);
    }
    @When("the user should see following options")
    public void the_user_should_see_following_options(List<String> menuOptions) {
        List<String> actualOptions = BrowserUtils.getElementsText(new DashboardPage().menuOptions);
        Assert.assertEquals(menuOptions,actualOptions);

    }
    @When("the user should use following credentials")
    public void the_user_should_use_following_credentials(Map<String,String> userInfo) {
        System.out.println("userInfo="+userInfo);
        new LoginPage().login(userInfo.get("username"),userInfo.get("password"));
        String expectedName=userInfo.get("firstname")+" "+userInfo.get("lastname");
        String actualName=new DashboardPage().getUserName();
        System.out.println("actualname="+actualName);
        System.out.println("expectedname="+expectedName);



    }
}
