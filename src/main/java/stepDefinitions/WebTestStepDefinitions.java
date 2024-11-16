package stepDefinitions;

import base.BasePage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.admin.userManagement.AddUserPage;
import pages.admin.userManagement.UsersPage;
import pages.login.LoginPage;
import pages.sideMenu.SideMenu;
import static org.testng.Assert.assertTrue;

public class WebTestStepDefinitions {

    private static final Logger logger = LogManager.getLogger(WebTestStepDefinitions.class);  // Log4j logger

    private final LoginPage loginPage = new LoginPage();
    private final SideMenu sideMenu = new SideMenu();
    private final UsersPage usersPage = new UsersPage();
    private final AddUserPage addUserPage = new AddUserPage();
    private final BasePage basePage = new BasePage();
    private static WebDriver driver;

    private int recordsCount = 0;
    private String empName = "";

    @Given("I am on the OrangeHRM login page")
    public void i_am_on_the_orange_hrm_login_page() {
        driver = Hooks.driver; // Get the driver from the Hooks class
        basePage.setDriver(driver);
        logger.info("Driver initialized for login page");
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        // Additional logic if needed
        logger.info("Additional logic for navigating to login page if needed");
    }

    @When("I enter the username {string} and password {string}")
    public void i_enter_the_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        logger.info("Entered username: {} and password", username);
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickLoginButton();
        logger.info("Login button clicked");
    }


    @And("I navigate to the Admin page")
    public void i_navigate_to_the_admin_page() {
        sideMenu.clickOnAdminLink();
        logger.info("Navigated to Admin page");
    }

    @Then("I note the current number of users")
    public void i_note_the_current_number_of_users() {
        recordsCount = usersPage.getRecordsCount();
        logger.info("Current user record count: {}", recordsCount);
    }

    @Then("I capture the first employee name")
    public void i_capture_the_first_employee_name() {
        empName = usersPage.getFirstEmployeeName().split(" ")[0]; // Get employee name from users page
        logger.info("Captured Employee Name: {}", empName);
    }

    @When("I click on the add button")
    public void i_click_on_add_button() {
        usersPage.clickAddButton();
        logger.info("Clicked on Add button");
    }

    @When("I fill in the new user details with username {string}, password {string}, user role {string}, and status {string}")
    public void fillInNewUserDetails(String username, String password, String userRole, String status) {
        addUserPage.selectUserRole(userRole);
        addUserPage.selectStatus(status);
        addUserPage.enterEmployeeName(empName);  // Use employee name from earlier captured value
        addUserPage.enterUsername(username);
        addUserPage.enterPassword(password);
        addUserPage.enterConfirmPassword(password);
        logger.info("Entered new user details: username={}, role={}, status={}", username, userRole, status);
    }

    @When("I save the new user")
    public void i_save_the_new_user() {
        addUserPage.clickSaveButton();
        logger.info("Clicked Save button for new user");
    }

    @Then("I should see the user count increased by one")
    public void i_should_see_the_user_count_increased_by_one() {
        assertTrue(usersPage.getRecordsCount() == recordsCount + 1, "User count did not increase by one");
        logger.info("User count increased by one");
    }

    @When("I search for username {string}")
    public void i_search_for(String username) {
        usersPage.searchUser(username);
        logger.info("Searched for user: {}", username);
    }

    @When("I delete the user")
    public void i_delete_the_user() {
        usersPage.deleteUser();
        logger.info("User deleted");
    }

    @Then("I should see the user count decreased by one")
    public void i_should_see_the_user_count_decreased_by_one() {
        assertTrue(usersPage.getRecordsCount() == recordsCount, "User count did not decrease by one");
        logger.info("User count decreased by one");
    }
}
