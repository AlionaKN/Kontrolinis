package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basetest.BaseTest;
import page.MainPage;
import utilities.FileReaderUtils;


public class TestItems extends BaseTest{

	private MainPage mainPage = new MainPage(driver);
	
	
	@Test
	public void testItems() throws IOException {
		
		mainPage.clickLaptopsAndNotebooks();
		mainPage.clickLinkAllLaptopsAndNotebooks();
		mainPage.clickButtonList();
		
//        List <String> testdata = FileReaderUtils.getTestData("src/test/resources/TestData_products.txt");
//        praleidziu skaityma is failo nes bandziau, bet uzstrigau ant sitos vietos ir praradau daug laiko
		
		mainPage.openThirdProduct();
		mainPage.clickbuttonAddToWishlist();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

		String expectedErrorText = "You must";
		String actualErrorText = driver.findElement(By.cssSelector("#product-product > div.alert.alert-success.alert-dismissible")).getText();
		assertTrue("Chart does not contain expected error message", actualErrorText.contains(expectedErrorText));
		driver.navigate().refresh();
		
		mainPage.clickbuttonAddToCart();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		String expectedSuccessText = "Success";
		String actualSuccessText = driver.findElement(By.cssSelector("#product-product > div.alert.alert-success.alert-dismissible")).getText();
		
		assertTrue("Chart does not contain expected success message", actualSuccessText.contains(expectedSuccessText));
		
		String actualCartTotalText = mainPage.buttonCartTotal.getText();
		String expectedCartTotaltext = "1 item(s)";
		
		assertTrue("Chart does not contain expected text",  actualCartTotalText.contains(expectedCartTotaltext));
		
		
				
				
	}

}
