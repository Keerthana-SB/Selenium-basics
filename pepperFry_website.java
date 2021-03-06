package final_project_Testcases;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pepperFry_website {

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(options);
		
		//Launch Pepperfry website
		driver.get("https://www.pepperfry.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='reg-modal-right-frm-wrap']")));
		driver.findElementByXPath("(//div[@block-type='modal']//a)[1]").click();
		Thread.sleep(5000);
		
		//Mouseover on Furniture and click Office Chairs under Chairs
		Actions builder=new Actions(driver);
		builder.moveToElement(driver.findElementByLinkText("Furniture")).perform();
		driver.findElementByLinkText("Office Chairs").click();
		
		//click Executive Chairs
		driver.findElementByXPath("(//div[@class='cat-wrap-img'])[2]").click();
		Thread.sleep(5000);
		driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]").clear();
		
		//Change the minimum Height as 50 in under Dimensions
		driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]").sendKeys("50",Keys.ENTER);
		Thread.sleep(5000);
		
		//Add "Poise Executive Chair in Black Colour" chair to Wishlist
		driver.findElementByXPath("(//a[contains(@class,'clip-heart-icn pf-right')])[2]").click();
		
		//Mouseover on Homeware and Click Pressure Cookers under Cookware
		builder.moveToElement(driver.findElementByLinkText("Homeware")).perform();
		driver.findElementByLinkText("Pressure Cookers").click();
		
		//Select Prestige as Brand
		driver.findElementByXPath("//label[@for='brandsnamePrestige']").click();
		Thread.sleep(5000);
		
		//Select Capacity as 1-3 Ltr
		driver.findElementByXPath("//label[@for='capacity_db1_Ltr_-_3_Ltr']").click();
		Thread.sleep(5000);
		
		//Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
		driver.findElementByXPath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']").click();
		Thread.sleep(1000);
		
		//Verify the number of items in Wishlist
		String wishList = driver.findElementByXPath("//a[contains(@class,'wistlist_img')]/following-sibling::span").getText();
	        if(wishList.equals("2")) {
		System.out.println("Wishlist count is "+wishList);
	        }
	        else{
	    	System.out.println("Wishlist is not matched");
	        }
		
		//Navigate to Wishlist
		driver.findElementByXPath("//a[@data-tooltip='Wishlist']").click();
		
		//Move Pressure Cooker only to Cart from Wishlist
		driver.findElementByXPath("(//a[@class='deleteicon'])[1]").click();
		Thread.sleep(5000);
		
		//Click Proceed to Pay Securely
		driver.findElementByClassName("addtocart_icon").click();
		driver.findElementByXPath("//div[@class='minicart_footer']//a[1]").click();
		
		//Check for the availability for Pincode 600128
		driver.findElementById("pin_code").sendKeys("600128",Keys.TAB);
		Thread.sleep(5000);
		driver.findElementByName("pin_check").click();
		
		//Click Proceed to Pay
		driver.findElementByLinkText("PLACE ORDER").click();
		driver.findElementByXPath("(//div[@class='nCheckout__accrodian-header-right']//span)[1]").click();
		
		//Capture the screenshot of the item under Order Item
		File src = driver.getScreenshotAs(OutputType.FILE);
                File dst = new File("./snaps/snap1.png");
                FileUtils.copyFile(src, dst);
		
		//Close the browser
                driver.close();
	}

}
