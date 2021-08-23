package com.steps;

import com.baselibrary.Baseclass;
import com.dataproviderUtilities.ConfigFileReader;
import com.helperUtilities.LoggerHelper;
import com.pagesPF.ConnectionsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class createConnectionsSteps extends Baseclass {
    public ConfigFileReader configFileReader;
    public ConnectionsPage connectionsPage;
    Logger log = LoggerHelper.getLogger(createConnectionsSteps.class);

    public createConnectionsSteps() {
        connectionsPage = PageFactory.initElements(driver, ConnectionsPage.class);
        configFileReader = new ConfigFileReader();

    }


    @And("navigates to the {string} tab")
    public void navigatesToTheTab(String createConnection) throws InterruptedException {
        connectionsPage.navigateToCreateConnectionScreen(createConnection);
    }

    @When("enters {string},{string},{string},principal and fileType")
    public void entersPrincipalAndFileType(String name, String description, String connectionType) throws InterruptedException, IOException {
        connectionsPage.enterDetailsForKerberosConnection(name,description,connectionType);
    }


    @Then("connection should be tested successfully")
    public void connectionShouldBeTestedSuccessfully() throws InterruptedException {
        connectionsPage.testConnection();
    }

    @When("creates the connection")
    public void createsTheConnection() {
    connectionsPage.saveTheConnection();
    }

    @Then("connection with {string} should be validated on Connection listing page")
    public void connectionWithShouldBeValidatedOnConnectionListingPage(String name) throws InterruptedException {
        connectionsPage.validateConnection(name);

    }




}
