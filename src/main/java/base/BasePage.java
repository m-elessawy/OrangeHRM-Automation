package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class BasePage {
    private static WebDriver driver;
    private static WebDriverWait wait;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);

    public void setDriver(WebDriver driver) {
        BasePage.driver = driver;
        BasePage.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    protected WebElement find(By locator) {
        return findElement(locator, ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement findClickable(By locator) {
        return findElement(locator, ExpectedConditions.elementToBeClickable(locator));
    }

    private WebElement findElement(By locator, ExpectedCondition<WebElement> condition) {
        try {
            return wait.until(condition);
        } catch (TimeoutException e) {
            logger.error("Timeout: Element not found: " + locator, e);
            throw e;
        } catch (NoSuchElementException e) {
            logger.error("Element not found: " + locator, e);
            throw e;
        } catch (StaleElementReferenceException e) {
            logger.error("Element is no longer attached to the DOM: " + locator, e);
            throw e;
        } catch (ElementNotInteractableException e) {
            logger.error("Element is not interactable: " + locator, e);
            throw e;
        } catch (WebDriverException e) {
            logger.error("WebDriver exception: " + e.getMessage(), e);
            throw e;
        }
    }

    protected void set(By locator, String text) {
        try {
            WebElement element = find(locator);
            element.clear();
            element.sendKeys(text);
            logger.info("Text set in element: " + locator);
        } catch (Exception e) {
            logger.error("Unable to set text at: " + locator, e);
            throw e;
        }
    }

    protected void click(By locator) {
        try {
            WebElement element = findClickable(locator);
            element.click();
            logger.info("Clicked on element: " + locator);
        } catch (Exception e) {
            logger.error("Unable to click on element: " + locator, e);
            throw e;
        }
    }

    protected String getText(By locator) {
        try {
            String text = find(locator).getText();
            logger.info("Text retrieved from element: " + locator +":"+text);
            return text;
        } catch (Exception e) {
            logger.error("Unable to get text from element: " + locator, e);
            throw e;
        }
    }



}
