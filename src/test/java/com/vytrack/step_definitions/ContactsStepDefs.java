package com.vytrack.step_definitions;

import com.vytrack.pages.*;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.DBUtils;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.awt.image.DataBuffer;
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

    @When("the user clicks the {string} from contacts")
    public void the_user_clicks_the_from_contacts(String email) {
        BrowserUtils.waitFor(10);
        ContactsPage contactsPage=new ContactsPage();
        BrowserUtils.waitFor(10);
        contactsPage.getContactEmail(email).click();
    }

    @Then("the information should be same with database")
    public void the_information_should_be_same_with_database() {
     //get actual data from UI
        BrowserUtils.waitFor(5);
        ContactInfoPage contactInfoPage=new ContactInfoPage();
        String actualFullname = contactInfoPage.contactFullName.getText();
        String actualEmail = contactInfoPage.email.getText();
        String actualPhone = contactInfoPage.phone.getText();
        System.out.println(actualEmail);
        System.out.println(actualFullname);
        System.out.println(actualPhone);
        contactInfoPage.waitUntilLoaderScreenDisappear();
        //get expected data from db
        //compare both of them
        String query="select concat(first_name,last_name) as FullName,e.email,phone from orocrm_contact c join orocrm_contact_email e on c.id=e.owner_id join orocrm_contact_phone p on e.owner_id=p.owner_id where e.email='mbrackstone9@example.com'";
        DBUtils.createConnection();
        Map<String, Object> rowMap = DBUtils.getRowMap(query);
        String fullname = (String) rowMap.get("contactFullName");
        String email = (String) rowMap.get("email");
        String phone = (String) rowMap.get("phone");

        Assert.assertEquals(fullname,actualFullname);
        Assert.assertEquals(email,actualEmail);
        Assert.assertEquals(phone,actualPhone);
        DBUtils.destroy();

    }
    @Then("the information {string} should be same with database")
    public void the_information_should_be_same_with_database(String email) {
        //get actual data from UI-GUI-Front end-Browser-Website(whatever we see)
        ContactInfoPage contactInfoPage = new ContactInfoPage();
        String actualFullname = contactInfoPage.contactFullName.getText();
        String actualEmail = contactInfoPage.email.getText();
        String actualPhone = contactInfoPage.phone.getText();

        System.out.println("actualFullname = " + actualFullname);
        System.out.println("actualEmail = " + actualEmail);
        System.out.println("actualPhone = " + actualPhone);


        //get expected data from database
        String query = "select concat(first_name,' ',last_name) as fullname,e.email,phone\n" +
                "from orocrm_contact c JOIN  orocrm_contact_email e\n" +
                "ON c.id=e.owner_id \n" +
                "JOIN orocrm_contact_phone p\n" +
                "ON e.owner_id=p.owner_id\n" +
                "where e.email='"+email+"'";


        //since the result is only one row, we saved in Map<String,Object>
        //if you are dealing with multiple rows, use List<Map<String,Object>>
        Map<String, Object> rowMap = DBUtils.getRowMap(query);


        String expectedFullname = (String) rowMap.get("fullname");
        String expectedEmail = (String) rowMap.get("email");
        String expectedPhone = (String) rowMap.get("phone");

        System.out.println("expectedFullname = " + expectedFullname);
        System.out.println("expectedEmail = " + expectedEmail);
        System.out.println("expectedPhone = " + expectedPhone);

        //Compare UI to DB

        Assert.assertEquals(expectedFullname,actualFullname);
        Assert.assertEquals(expectedEmail,actualEmail);
        Assert.assertEquals(expectedPhone,actualPhone);


    }




}


