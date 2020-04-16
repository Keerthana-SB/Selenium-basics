package final_project_Testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Nykaa_website {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(options);
		driver.get("https://www.nykaa.com/#");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		WebElement brands = driver.findElementByXPath("(//li[@class='menu-dropdown-icon']//a)[1]");
		Actions builder= new Actions(driver);
		builder.moveToElement(brands).perform();
		Thread.sleep(5000);
		//Mouseover on Brands and Mouseover on Popular
		builder.moveToElement(driver.findElementByXPath("//div[@class='BrandsCategoryHeading']//a[1]")).perform();
		System.out.println("Popular brands are shown");
		Thread.sleep(5000);
		//Click L'Oreal Paris
		driver.findElementByXPath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']").click();
		//Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> winSet = driver.getWindowHandles();
        List<String> winList= new ArrayList<String>(winSet);
        driver.switchTo().window(winList.get(1));
        driver.manage().window().maximize();
        String title = driver.getTitle(); 
        if (title.contains("L'Oreal Paris")) {
			System.out.println("L'Oreal Paris launched with the title as" + title);
        }
		else {
			System.err.println("L'Oreal Paris launch failed");
		}
        //Click sort By and select customer top rated
        driver.findElementByXPath("//span[text()='popularity']").click();
        System.out.println("Sort By dropdown is selceted");
        Thread.sleep(3000);
        driver.findElementByXPath("//span[text()='customer top rated']").click();;
        System.out.println("Customer top rated option is selected");
        Thread.sleep(3000);
        //Click Category and click Shampoo
        driver.findElementByXPath("(//div[contains(@class,'pull-right')])[1]").click();
		System.out.println("Clicked Category");
		Thread.sleep(3000);
		WebElement select = driver.findElementByXPath("//label[contains(@for,'Shampoo_undefined')]");
		//check whether the Filter is applied with Shampoo
		if(!select.isSelected()) {
			select.click();
			System.out.println("Shampoo is selected");
		}else {
			System.out.println("Shampoo is not selected");
		}
		//Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElementByXPath("//span[contains(text(),'Colour Protect Shampoo')]").click();
		System.out.println("L'Oreal Paris Colour Protect Shampoo is selected");
		//GO to the new window and select size as 175ml
		Set<String> winSet1 = driver.getWindowHandles();
        List<String> winList1= new ArrayList<String>(winSet1);
		driver.switchTo().window(winList1.get(2));
        driver.findElementByXPath("//span[text()='175ml']").click();
        //Print the MRP of the product
		String MRP = driver.findElementByXPath("(//span[contains(@class,'content-price-offer')])[1]").getText().replaceAll("[^0-9]", "");
		System.out.println("The price of the selected producted is "+MRP);
		//Click on ADD to BAG
		driver.findElementByXPath("(//button[contains(@class,'combo-add-to-btn prdt-des-btn')])[1]").click();
		//Go to Shopping Bag 
		driver.findElementByClassName("AddBagIcon").click();
		//Print the Grand Total amount
		String grandTotal = driver.findElementByXPath("//div[@class = 'value medium-strong']").getText().replaceAll("[^0-9]", "");
		System.out.println("The Grand total of the product is " +grandTotal);
		//Click Proceed
		driver.findElementByXPath("//span[text()='Proceed']").click();
		//Click on Continue as Guest
		driver.findElementByXPath("//button[text()='CONTINUE AS GUEST']").click();
		//Print the warning message (delay in shipment)
		String errorMsg = driver.findElementByXPath("//div[@class='message']").getText();
		System.out.println("The warning message :"+errorMsg);
		//Close all windows
		driver.quit();
	}

}
