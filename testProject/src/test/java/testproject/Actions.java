package testproject;

import static org.testng.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Actions {

	public static Logger log = LogManager.getLogger();
	private static String URL = "https://jupiter.cloud.planittesting.com/";
	static WebDriver driver;
	static WebDriverWait wait;

	public void clickElement(String xpath) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).click();
	}

	public static void launchBrowser() {
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		log.info("Launching: {}", URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);

	}

	public static void closeBrowser() {
		log.info("Closing: {} ", URL);
		driver.quit();
	}

	public void verifyElementIsDisplayed(String xpath) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		assertTrue(driver.findElement(By.xpath(xpath)).isDisplayed());
	}

	public void verifyElementIsNotDisplayed(String xpath) {
		assertTrue(driver.findElements(By.xpath(xpath)).size() == 0);
	}

	public void enterText(String xpath, String text) {
		WebElement element = driver.findElement(By.xpath(xpath));
		element.clear();
		element.sendKeys(text);
	}

	public String getText(String xpath) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		return driver.findElement(By.xpath(xpath)).getText();
	}

	public static JsonNode loadJson(String fileName) {
		JsonNode jn = null;
		try {
			String str = new String(Files.readAllBytes(Paths.get(fileName)));
			ObjectMapper mapper = new ObjectMapper();
			jn = mapper.readValue(str, JsonNode.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jn;
	}

}
