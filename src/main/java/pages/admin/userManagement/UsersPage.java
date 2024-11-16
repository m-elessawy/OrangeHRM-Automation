package pages.admin.userManagement;

import base.BasePage;
import org.openqa.selenium.By;

public class UsersPage extends BasePage {

    // Locators
    private final By recordsCount = By.xpath("//span[contains(normalize-space(), 'Record Found') or contains(normalize-space(), 'Records Found')]");
    private final By addButton = By.xpath("//div[@class='orangehrm-header-container']//button[contains(string(),'Add')]");
    private final By searchButton = By.xpath("//button[@type='submit' and text()=' Search ']");
    private final By firstEmployeeName = By.xpath("//div[@class='oxd-table-card'][1]//div[@role='cell'][4]");
    private final By deleteIcon = By.xpath("//i[@class='oxd-icon bi-trash']");
    private final By confirmDeleteButton = By.xpath("//i[@class='oxd-icon bi-trash oxd-button-icon']");
    private final By resetButton = By.xpath("//button[contains(string(),'Reset')]");
    private final By userNameSearchField = By.xpath("//label[contains(string(),'Username')]/../../div[2]/input");


    public int getRecordsCount() {
        logger.info("Fetching the number of records found.");
        int count = extractRecordCount(getText(recordsCount));
        logger.info("Number of records found: " + count);
        System.out.println("Number of records found: " + count);
        return count;
    }

    private int extractRecordCount(String input) {
        String numberStr = input.replaceAll("[^0-9]", "");
        int count = Integer.parseInt(numberStr);
        logger.debug("Extracted record count: " + count);
        return count;
    }

    public void clickAddButton() {
        logger.info("Clicking on the Add button.");
        click(addButton);
    }


    public void searchUser(String username) {
        logger.info("Searching for user with username: " + username);
        set(userNameSearchField, username);
        click(searchButton);
    }


    public String getFirstEmployeeName() {
        logger.info("Fetching the first employee name from the table.");
        String employeeName = getText(firstEmployeeName);
        logger.info("First employee name: " + employeeName);
        return employeeName;
    }

    public void deleteUser() {
        logger.info("Deleting a user.");
        click(deleteIcon);
        logger.info("Confirming the deletion.");
        click(confirmDeleteButton);
        click(resetButton);
        logger.info("Reset completed after deletion.");
    }
}
