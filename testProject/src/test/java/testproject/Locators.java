package testproject;

public class Locators {
    public static final String CONTACT_TAB = "a[text()='Contact']";
    public static final String HOME_TAB = "//a[text()='Home']";
    public static final String SHOP_TAB = "//a[text()='Shop']";
    public static final String CART_BTN = "//a[contains(text(), 'Cart')]";
    public static final String SUBMIT_BTN = "//div[@class='form-actions']//a[@class='btn-contact btn btn-primary']";
    public static final String FORENAME_ERR_MSG = "//span[text() = 'Forename is required']";
    public static final String EMAIL_ERR_MSG = "//span[text() = 'Email is required']";
    public static final String MSG_ERR_MSG = "//span[text() = 'Message is required']";
    public static final String ALERT_MSG = "//div[@class='alert alert-error ng-scope']";
    public static final String FORENAME_INPUT = "//input[@id='forename']";
    public static final String EMAIL_INPUT = "//input[@id='email']";
    public static final String MSG_INPUT = "//textarea[@id='message']";
    public static final String SUCCESS_MSG = "//div[@class='alert alert-success']";
    public static final String TOTAL = "//Strong[@class = 'total ng-binding']";
    
}