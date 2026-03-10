package common.nopcommerce.pageUI.HomePageUI;

public class RegisterPage {

    //Your Personal Details
    public static final String genderRadioMale = "//input[@id='gender-male']";
    public static final String genderRadioFemale = "//input[@id='gender-female']";
    public static final String firstNameInput = "//input[@id='FirstName']";
    public static final String lastNameInput = "//input[@id='LastName']";
    public static final String emailInput = "//input[@type='email'][1]";

    //Your Company Details
    public static final String companyInput = "//input[@id='Company']";

    //Your Password
    public static final String passwordInput = "//input[@name='Password']";
    public static final String confirmPasswordInput = "//input[@name='ConfirmPassword']";


    public static final String registerButton = "//button[@name='register-button']";

    public static final String completeMessage = "//div[@class='result']";
    public static final String continueButton = "//a[text()='Continue']/parent::div";
}
