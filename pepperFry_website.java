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
		Actions builder=new Actions(driver);
		builder.moveToElement(driver.findElementByLinkText("Furniture")).perform();
		driver.findElementByLinkText("Office Chairs").click();
		driver.findElementByXPath("(//div[@class='cat-wrap-img'])[2]").click();
		Thread.sleep(5000);
		driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]").clear();
		driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]").sendKeys("50",Keys.ENTER);
		Thread.sleep(5000);
		driver.findElementByXPath("(//a[contains(@class,'clip-heart-icn pf-right')])[2]").click();
		builder.moveToElement(driver.findElementByLinkText("Homeware")).perform();
		driver.findElementByLinkText("Pressure Cookers").click();
		driver.findElementByXPath("//label[@for='brandsnamePrestige']").click();
		Thread.sleep(5000);
		driver.findElementByXPath("//label[@for='capacity_db1_Ltr_-_3_Ltr']").click();
		Thread.sleep(5000);
		driver.findElementByXPath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']").click();
		Thread.sleep(1000);
		String wishList = driver.findElementByXPath("//a[contains(@class,'wistlist_img')]/following-sibling::span").getText();
	    if(wishList.equals("2")) {
		System.out.println("Wishlist count is "+wishList);
	    }
	    else{
	    	System.out.println("Wishlist is not matched");
	    }
		driver.findElementByXPath("//a[@data-tooltip='Wishlist']").click();
		driver.findElementByXPath("(//a[@class='deleteicon'])[1]").click();
		Thread.sleep(5000);
		driver.findElementByClassName("addtocart_icon").click();
		driver.findElementByXPath("//div[@class='minicart_footer']//a[1]").click();
		driver.findElementById("pin_code").sendKeys("600128",Keys.TAB);
		Thread.sleep(5000);
		driver.findElementByName("pin_check").click();
		driver.findElementByLinkText("PLACE ORDER").click();
		driver.findElementByXPath("(//div[@class='nCheckout__accrodian-header-right']//span)[1]").click();
		File src = driver.getScreenshotAs(OutputType.FILE);
        File dst = new File("./snaps/snap1.png");
        FileUtils.copyFile(src, dst);
        driver.close();
	}

}
