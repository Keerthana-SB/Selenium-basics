package final_project_Testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class HP_StoreWebsite {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(options);
		
		//Open HP Store website
		driver.get("https://store.hp.com/in-en/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		//Mouse over on Laptops menu and click on Pavilion
		WebElement laptops = driver.findElementByLinkText("Laptops");
		Actions builder = new Actions(driver);
		builder.moveToElement(laptops).perform();
		Thread.sleep(500);
		
		driver.findElementByLinkText("Pavilion").click();
		System.out.println("Pavilion option is selected");
		Thread.sleep(5000);
		
		driver.findElementByXPath("//button[@title='Accept Cookies']").click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
		Thread.sleep(500);
		
		////Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		driver.findElementByXPath("//span[text()='Intel Core i7']").click();
		System.out.println("Processor is Intel core i7");
		Thread.sleep(5000);
		
		//Hard Drive Capacity -->More than 1TB
		driver.findElementByXPath("//span[text()='More than 1 TB']").click();
		Thread.sleep(5000);
		
		//Select Sort By: Price: Low to High
		WebElement ele = driver.findElementById("sorter");
		Select dd= new Select(ele);
		dd.selectByIndex(1);
		
		//Print the First resulting Product Name and Price
		String proName = driver.findElementByClassName("product-item-link").getText();
		System.out.println("The First resulting product name is "+proName);
	
		String proPrice = driver.findElementByXPath("(//span[@class='price'])[2]").getText().replaceAll("[^0-9]", "");
		System.out.println("The First resulting product price is "+proPrice);
		Thread.sleep(5000);
		
		//Click on Add to Cart
		driver.findElementByXPath("(//span[text()='Add To Cart'])[1]").click();
		Thread.sleep(5000);
		
		//Click on Shopping Cart icon --> Click on View and Edit Cart
		driver.findElementByXPath("//a[@class='action showcart']").click();
		
		//Check the Shipping Option --> Check availability at Pincode
		driver.findElementByXPath("//a[@class='action primary viewcart']").click();
		driver.findElementByName("pincode").sendKeys("600109");
		driver.findElementByXPath("//button[text()='check']").click();
		
		//Verify the order Total against the product price
		String subTotal=driver.findElementByXPath("//span[@data-th='Subtotal']").getText().replaceAll("[^0-9]","");
		int Total1=Integer.parseInt(subTotal);
		
		String orderTotal= driver.findElementByXPath("//td[@data-th='Order Total']//span[1]").getText().replaceAll("[^0-9]","");
		int Total2=Integer.parseInt(orderTotal);
		if(Total1==Total2) {
			System.out.println("Order Total and SubTotal are same");
			}
		
		else {
			System.out.println("Order Total and SubTotal are not same");
		}
		
		//Proceed to Checkout if Order Total and Product Price matches
		driver.findElementByXPath("//span[text()='Proceed to Checkout']").click();
		System.out.println("Checked-Out");
		
		//Click on Place Order
		driver.findElementByXPath("//span[text()='Place Order']").click();
		System.out.println("Order placed");
		
		//Capture the Error message and Print
		String errMsg = driver.findElementByXPath("//div[@class='message notice']").getText();
		System.out.println("The error message is displayed as "+errMsg);
		driver.close();
	}

}
