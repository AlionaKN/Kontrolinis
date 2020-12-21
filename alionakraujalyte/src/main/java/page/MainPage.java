package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage{

	// links
		@FindBy(linkText = "Laptops & Notebooks")
		private WebElement linkLaptopsAndNotebooks;
	
		@FindBy(linkText = "Show All Laptops & Notebooks")
		private WebElement linkAllLaptopsAndNotebooks;
		
//		@FindBy(linkText = "HP LP3065")
//		public WebElement firstProductName;
//		
//		@FindBy(linkText = "MacBook")
//		public WebElement secondProductName;
		
		@FindBy(linkText = "MacBook Air")
		public WebElement thirdProductName;
		
//		@FindBy(linkText = "MacBook Pro")
//		public WebElement fourthProductName;
//		
//		@FindBy(linkText = "Sony VAIO")
//		public WebElement fifthProductName;
		
	// button	
		@FindBy(xpath = "//*[@id=\"list-view\"]")
		private WebElement buttonList;
		
		@FindBy(css = "#content > div > div.col-sm-4 > div.btn-group > button:nth-child(1)")
		private WebElement buttonAddToWishlist;
		
		@FindBy(xpath = "//*[@id=\"button-cart\"]")
		public WebElement buttonAddToCart;
		
		@FindBy(id = "cart-total")
		public WebElement buttonCartTotal;
		
	public MainPage(WebDriver driver) {
		super(driver);
	}
	public void clickLaptopsAndNotebooks() {
		linkLaptopsAndNotebooks.click();
	}
	public void clickLinkAllLaptopsAndNotebooks() {
		linkAllLaptopsAndNotebooks.click();
	}
	public void clickButtonList() {
		buttonList.click();
	}
	
	public void openThirdProduct() {
		thirdProductName.click();
	}
	
	public void clickbuttonAddToWishlist() {
		buttonAddToWishlist.click();
	}
	
	public void clickbuttonAddToCart() {
		buttonAddToCart.click();
	}
}
