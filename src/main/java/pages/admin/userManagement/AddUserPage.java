package pages.admin.userManagement;

import base.BasePage;
import org.openqa.selenium.By;

public class AddUserPage extends BasePage {

    // Locators
    private final By usernameField = By.xpath("//label[contains(text(),'Username')]/../../div[2]/input");
    private final By userRoleDropDown = By.xpath("//label[contains(text(),'User Role')]/../../div[2]//i");
    private final By statusDropDown = By.xpath("//label[contains(text(),'Status')]/../../div[2]//I");
    private final By passwordField = By.xpath("//label[contains(text(),'Password')]/../../div[2]//input");
    private final By confirmPasswordField = By.xpath("//label[contains(text(),'Confirm Password')]/../../div[2]//input");
    private final By employeeNameField = By.xpath("//label[contains(text(),'Employee Name')]/../../div[2]//input");

    private final By saveButton = By.xpath("//button[@type='submit' and text()=' Save ']");

    private final String dropDownValue = "//div[@role='listbox']/*[contains(string(),'%s')]";
    private final String employeeNameDropDownValue = "//div[@role='listbox']/*[contains(string(),'%s')]";


    public void enterUsername(String username) {
        logger.info("Entering username: " + username);
        set(usernameField, username);
    }


    public void enterEmployeeName(String employeeName) {
        logger.info("Entering employee name: " + employeeName);
        set(employeeNameField, employeeName);
        logger.info("Selecting employee name from dropdown: " + employeeName);
        click(By.xpath(employeeNameDropDownValue.replace("%s", employeeName)));
    }


    public void enterPassword(String password) {
        logger.info("Entering password.");
        set(passwordField, password);
    }

    public void enterConfirmPassword(String password) {
        logger.info("Entering confirmation password.");
        set(confirmPasswordField, password);
    }

    public void clickSaveButton() {
        logger.info("Clicking the Save button.");
        click(saveButton);
    }


    public void selectUserRole(String userRole) {
        logger.info("Selecting user role: " + userRole);
        click(userRoleDropDown);
        click(By.xpath(dropDownValue.replace("%s", userRole)));
    }

    public void selectStatus(String status) {
        logger.info("Selecting status: " + status);
        click(statusDropDown);
        click(By.xpath(dropDownValue.replace("%s", status)));
    }
}
