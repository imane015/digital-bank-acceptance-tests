package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.utils.MockData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }
    //MockData mockData = new MockData();

    @FindBy(id="title")
    private WebElement titleDropDown;

    @FindBy(id="firstName")
    private WebElement firstNameTxtBOx;

    @FindBy(id="lastName")
    private WebElement lastNameTxtBox;

    @FindBy(xpath = "//input[@value='M']")
    private WebElement genderM;
    @FindBy(xpath = "//input[@value='F']")
    private WebElement genderF;

    @FindBy(id="dob")
    private WebElement dateOfBirthTxtBox;

    @FindBy(id="ssn")
    private WebElement ssnTxtBox;

    @FindBy(id="emailAddress")
    private WebElement emailTxtBox;

    @FindBy(id="password")
    private WebElement passwordTxtBox;

    @FindBy(id="confirmPassword")
    private WebElement confirmPasswordTxtBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement nextButton;

    @FindBy(xpath =" //a[@href='/bank/login']")
    private WebElement signInLink;

    @FindBy(id="address")
    private WebElement addressTxtBox;

    @FindBy(id="locality")
    private WebElement localityTxtBox;

    @FindBy(id="region")
    private WebElement regionTxtBox;

    @FindBy(id ="postalCode")
    private WebElement postalCodeTxtBox ;

    @FindBy(id="country")
    private WebElement countryTxtBox;

    @FindBy(id="homePhone")
    private WebElement phoneTxtBox;

    @FindBy(id ="agree-terms")
    private WebElement agreeTermsButton ;

    @FindBy(xpath ="//button[@class='btn btn-primary btn-flat m-b-30 m-t-30']")
    private WebElement registerButton ;

    @FindBy(xpath = "//div[@class='sufee-alert alert with-close alert-success alert-dismissible fade show']")
    private WebElement successMessage;





    public void registerNewAccount(List<Map<String,String>> registrationDataListOfMap){
        Select dropdown = new Select(titleDropDown);
        Map<String,String> firstRow = registrationDataListOfMap.get(0);

        if (firstRow.get("title") != null) {
            dropdown.selectByVisibleText(firstRow.get("title"));
        }
        if (firstRow.get("firstName") != null) {
            firstNameTxtBOx.sendKeys(firstRow.get("firstName"));
        }
        if (firstRow.get("lastName") != null) {
            lastNameTxtBox.sendKeys(firstRow.get("lastName"));
        }
        if (firstRow.get("gender") != null) {
            if (firstRow.get("gender").equalsIgnoreCase("M")) {
                genderM.click();
            } else if (firstRow.get("gender").equalsIgnoreCase("F")) {
                genderF.click();
            } else {
                System.out.println("wrong gender");
            }
        }
        if (firstRow.get("dob") != null) {
            dateOfBirthTxtBox.sendKeys(firstRow.get("dob"));
        }


        //Map<String,String> userMockData = mockData.generateRandomUser();
        if (firstRow.get("ssn") != null) {

                ssnTxtBox.sendKeys(firstRow.get("ssn"));

        }
        if (firstRow.get("email") != null) {
            emailTxtBox.sendKeys(firstRow.get("email"));
        }

        if (firstRow.get("password") != null) {
            passwordTxtBox.sendKeys(firstRow.get("password"));
            confirmPasswordTxtBox.sendKeys(firstRow.get("password"));
        }

        nextButton.click();

        if(addressTxtBox.isDisplayed()) {
            if (firstRow.get("address") != null) {
                addressTxtBox.sendKeys(firstRow.get("address"));
            }
            if (firstRow.get("locality") != null) {
                localityTxtBox.sendKeys(firstRow.get("locality"));
            }
            if (firstRow.get("region") != null) {
                regionTxtBox.sendKeys(firstRow.get("region"));
            }
            if (firstRow.get("postalCode") != null) {
                postalCodeTxtBox.sendKeys(firstRow.get("postalCode"));
            }
            if (firstRow.get("country") != null) {
                countryTxtBox.sendKeys(firstRow.get("country"));
            }
            if (firstRow.get("phone") != null) {
                phoneTxtBox.sendKeys(firstRow.get("phone"));
            }
            if (firstRow.get("termsCheckMark") != null) {
                if (firstRow.get("termsCheckMark").equalsIgnoreCase("true")) {
                    agreeTermsButton.click();
                }
            }

            registerButton.click();
        }
    }
    public String getMessage(){
        return successMessage.getText().substring(0,successMessage.getText().lastIndexOf(".")+1);
    }
    public String getRequiredFieldErrorMessage(String fieldName) {
        switch(fieldName.toLowerCase()){
            case "title" :
                return titleDropDown.getAttribute("validationMessage");
            case "firstname" :
                return firstNameTxtBOx.getAttribute("validationMessage");
            case "lastname" :
                return lastNameTxtBox.getAttribute("validationMessage");
            case "gender" :
                return genderF.getAttribute("validationMessage");
            case "dob" :
                return dateOfBirthTxtBox.getAttribute("validationMessage");
            case "ssn" :
                return ssnTxtBox.getAttribute("validationMessage");
            case "email" :
                return emailTxtBox.getAttribute("validationMessage");
            case "password" :
                return passwordTxtBox.getAttribute("validationMessage");
            case "address" :
                return addressTxtBox.getAttribute("validationMessage");
            case "locality" :
                return localityTxtBox.getAttribute("validationMessage");
            case "region" :
                return regionTxtBox.getAttribute("validationMessage");
            case "postalcode" :
                return postalCodeTxtBox.getAttribute("validationMessage");
            case "country" :
                return countryTxtBox.getAttribute("validationMessage");
            case "phone" :
                return phoneTxtBox.getAttribute("validationMessage");
            case "termscheckmark" :
                return agreeTermsButton.getAttribute("validationMessage");
            default :
                return null;
        }

    }










}
