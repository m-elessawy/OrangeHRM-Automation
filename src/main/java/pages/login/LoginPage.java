package pages.login;

import base.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // Locators
    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//button[@type='submit']");


    public void enterUsername(String username) {
        logger.info("Entering username: " + username);
        set(usernameField, username);
    }


    public void enterPassword(String password) {
        logger.info("Entering password.");
        set(passwordField, password);
    }


    public void clickLoginButton() {
        logger.info("Clicking on the login button.");
        click(loginButton);
    }

    public void login(String username, String password) {
        logger.info("Starting login process with username: " + username);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        logger.info("Login process completed successfully.");
    }
}
