package testproject;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Setup {


	@BeforeTest
	public void setup() {
		Actions.launchBrowser();
	}

	@AfterTest
	public void cleanUp() {
		Actions.closeBrowser();
	}

}
