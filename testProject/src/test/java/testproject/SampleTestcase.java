package testproject;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

public class SampleTestcase extends PageObjectConnector {

	/**
	 * From the home page, go to contact page, click submit button, verify error
	 * messages, populate mandatory fields, validate errors are gone.
	 */
	@Test
	public void testCase1() {
		Actions.log.info("testCase1");

		// Navigate to contact tab
		act.clickElement(Locators.CONTACT_TAB);

		// Click submit button
		act.clickElement(Locators.SUBMIT_BTN);

		// Verify error messages
		act.verifyElementIsDisplayed(Locators.FORENAME_ERR_MSG);
		act.verifyElementIsDisplayed(Locators.EMAIL_ERR_MSG);
		act.verifyElementIsDisplayed(Locators.MSG_ERR_MSG);
		act.verifyElementIsDisplayed(Locators.ALERT_MSG);

		// Populate mandatory fields
		act.enterText(Locators.FORENAME_INPUT, "test name");
		act.enterText(Locators.EMAIL_INPUT, "test@ggmm.com");
		act.enterText(Locators.MSG_INPUT, "This is my message");

		// Verify error messages are not displayed
		act.verifyElementIsNotDisplayed(Locators.FORENAME_ERR_MSG);
		act.verifyElementIsNotDisplayed(Locators.EMAIL_ERR_MSG);
		act.verifyElementIsNotDisplayed(Locators.MSG_ERR_MSG);
		act.verifyElementIsNotDisplayed(Locators.ALERT_MSG);
	}

	/**
	 * From the home page, go to contact page, populate mandatory fields, click
	 * submit button, validate successful submission message
	 * 
	 * Run this test 5 times to ensure 100% pass rate
	 */
	@Test(threadPoolSize = 1, invocationCount = 5)
	public void testCase2() {
		Actions.log.info("testCase2");

		// Navigate to contact tab
		act.clickElement(Locators.CONTACT_TAB);

		// Populate mandatory fields
		act.enterText(Locators.FORENAME_INPUT, "test name");
		act.enterText(Locators.EMAIL_INPUT, "test@ggmm.com");
		act.enterText(Locators.MSG_INPUT, "This is my message");

		// Click submit button
		act.clickElement(Locators.SUBMIT_BTN);

		// Validate successful submission message
		act.verifyElementIsDisplayed(Locators.SUCCESS_MSG);

		// Navigate to home tab
		act.clickElement(Locators.HOME_TAB);
	}

	/**
	 * Buy 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear, Go to the cart page
	 * Verify the subtotal for each product is correct, Verify the price for each
	 * product Verify that total = sum(sub totals)
	 */
	@Test
	public void testCase3() {
		Actions.log.info("testCase3");
		JsonNode jn = Actions.loadJson("src/test/java/testproject/shopping.json");

		// Navigate to contact tab
		act.clickElement(Locators.SHOP_TAB);

		// Add items on cart
		for (JsonNode item : jn.get("items")) {
			String itemBtn = "//h4[contains(text(),'" + item.get("name").asText()
					+ "')]//following::a[1]";
			act.clickElement(itemBtn);
		}

		act.clickElement(Locators.CART_BTN);

		// Add QTY to each item and verify details
		for (JsonNode item : jn.get("items")) {
			String qty_input = "//td[contains(text(), '" + item.get("name").asText()
					+ "')]//following-sibling::td//input[@name='quantity']";
			String price = "//td[contains(text(), '" + item.get("name").asText()
					+ "')]//following-sibling::td[1]";
			String subTotal = "//td[contains(text(), '" + item.get("name").asText()
					+ "')]//following-sibling::td[3]";
			act.enterText(qty_input, item.get("quantity").asText());
			assertEquals(act.getText(price), item.get("price").asText());
			assertEquals(act.getText(subTotal), item.get("subTotal").asText());
		}
		assertEquals(act.getText(Locators.TOTAL), jn.get("total").asText());

	}

}
