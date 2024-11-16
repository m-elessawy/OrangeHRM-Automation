package pages.sideMenu;

import base.BasePage;
import org.openqa.selenium.By;

public class SideMenu extends BasePage {


    // Locator for the Admin link in the side menu
    private final By adminLink = By.xpath("//nav[@class='oxd-navbar-nav']//span[text()='Admin']");


    public void clickOnAdminLink() {
        try {
            logger.info("Attempting to click on the Admin link in the side menu.");
            click(adminLink);
            logger.info("Successfully clicked on the Admin link.");
        } catch (Exception e) {
            logger.error("Failed to click on the Admin link.", e);
            throw e;
        }
    }
}
