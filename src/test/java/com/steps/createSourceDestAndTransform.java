package com.steps;

import com.baselibrary.Baseclass;
import com.dataproviderUtilities.ConfigFileReader;
import com.helperUtilities.LoggerHelper;
import com.managersUtilities.CommonFunction;
import com.pagesPF.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class createSourceDestAndTransform extends Baseclass {
    ProjectsPage projectspage;
    Project_BuilderPage projectBuilderpage;
    public ConfigFileReader configFileReader;
    Functions_LeanPageObject functions_leanPageObject;
    PipeLIne_ListingPage pipeLIne_listingPage;
    PipeLIne_BuilderPage pipeLIne_builderPage;
    Logger log = LoggerHelper.getLogger(createSourceDestAndTransform.class);

    public createSourceDestAndTransform() {
        projectspage = PageFactory.initElements(driver, ProjectsPage.class);
        projectBuilderpage = PageFactory.initElements(driver, Project_BuilderPage.class);
        functions_leanPageObject = PageFactory.initElements(driver, Functions_LeanPageObject.class);
        pipeLIne_listingPage = PageFactory.initElements(driver, PipeLIne_ListingPage.class);
        pipeLIne_builderPage=PageFactory.initElements(driver, PipeLIne_BuilderPage.class);
        configFileReader = new ConfigFileReader();

    }
    @And("creates a {string} having {string} and {string}")
    public void createsAHavingAnd(String pipelineName, String description, String tag) throws InterruptedException, IOException {
        pipeLIne_listingPage.createPipeLine(pipelineName, description, tag);
        log.info("Pipe line is created with name-->" + pipelineName);

    }


    @And("clicks on {string}")
    public void clicksOn(String Icon) throws InterruptedException {
        pipeLIne_builderPage.clickOnAddSourceORDestinationIcon(Icon);
        log.info("Icon is clicked-->" + Icon);

    }

    @Then("Source should get created with {string}")
    public void sourceShouldGetCreatedWith(String sourceName) throws InterruptedException, IOException {
        Assert.assertTrue(CommonFunction.getCustomisedWebElement(driver,pipeLIne_builderPage.sourceName,sourceName).isDisplayed());
        (CommonFunction.getCustomisedWebElement(driver,pipeLIne_builderPage.sourceName,sourceName)).click();
        log.info("DRecord validated-->" + sourceName);

    }

    @When("Enters {string},{string},{string},{string},{string},{string},{string},{string}")
    public void enters(String Name, String type, String connection, String fileType, String filePath, String separator, String schemaSource,String manualSchema) throws IOException, InterruptedException {
        pipeLIne_builderPage.createSourceWithFile(Name,type,connection,fileType,filePath,separator,schemaSource,manualSchema);
        log.info("Details entered-->" + filePath+"--"+connection);
    }

    @And("click on {string} to delete")
    public void clickOnToDeleteSource(String deleteSource) throws InterruptedException {
        functions_leanPageObject.deleteRecord(pipeLIne_builderPage.deleteRecord,deleteSource);
        CommonFunction.waitForElementToAppear(driver,pipeLIne_builderPage.deleteButtonOnDeleteSourcePopUp);
        pipeLIne_builderPage.deleteButtonOnDeleteSourcePopUp.click();
        CommonFunction.waitForSomeTime();
    }



    @Then("source {string} should get deleted")
    public void sourceShouldGetDeleted(String name) {
        functions_leanPageObject.validatePresenceOfRecord(pipeLIne_builderPage.sourceName,name);
    }

    @When("enters {string},{string},{string},{string} and click add")
    public void entersAndClick(String TransformationName, String Category, String TransformationType, String columnValue) throws IOException, InterruptedException {
     pipeLIne_builderPage.createTransformation(TransformationName,Category,TransformationType,columnValue);
    }

    @Then("Transformation with {string} should get created")
    public void transformationWithShouldGetCreated(String TransformationName) throws InterruptedException {
        Assert.assertTrue(CommonFunction.getCustomisedWebElement(driver,pipeLIne_builderPage.transformation,TransformationName).isDisplayed());
        CommonFunction.getCustomisedWebElement(driver,pipeLIne_builderPage.transformation,TransformationName).click();

    }

    @When("enters {string},{string},{string},{string} ,{string},{string} and click add")
    public void entersAndClickAdd(String DestinationName, String DestinationType, String DestConnection, String fileType,String filePath, String TopicName) throws IOException, InterruptedException {
        pipeLIne_builderPage.createDestination(DestinationName,DestinationType,DestConnection,fileType,filePath,TopicName);
    }

    @Then("Destination with {string} should get created")
    public void destinationWithShouldGetCreated(String DestinationName) {

    }


}