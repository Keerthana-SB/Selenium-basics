package final_project_Testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Myntra_assign {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver=new ChromeDriver();
		//Open Browser
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		//Mouse over on WOMEN
		WebElement ele = driver.findElementByXPath("//a[@href='/shop/women']");
		Actions builder = new Actions(driver);
		builder.moveToElement(ele).perform();
		//Click Jackets & Coats
		driver.findElementByXPath("(//a[text()='Jackets & Coats'])[1]").click();
		ChromeOptions ch= new ChromeOptions();
		ch.addArguments("--disable-notifications");
		//Find the total count of item
		String str = driver.findElementByClassName("title-count").getText();
		String text = str.replaceAll("\\D","");
		int totalCount = Integer.parseInt(text);
		System.out.println("Total count of item: "+totalCount);
		WebElement category_Jacket = driver.findElementByXPath("//span[@class='categories-num']");
		WebElement category_Coats = driver.findElementByXPath("(//span[@class='categories-num'])[2]");
		int Jacket = Integer.parseInt(category_Jacket.getText().replaceAll("\\D", ""));
		int Coats = Integer.parseInt(category_Coats.getText().replaceAll("\\D", ""));
		int totalItems= Jacket + Coats; 
		//Validate the sum of categories count matches
		if(totalCount==totalItems)
		{
			System.out.println("Count is matching");
		}
		else
		{
			System.out.println("Count is not matched");
		}
	   //Check Coats 
	   driver.findElementByXPath("(//span[@class='categories-num'])[2]").click();
	   System.out.println("Coat is selected");
	   //Click + More option under BRAND
	   driver.findElementByClassName("brand-more").click();
	   //Type MANGO and click checkbox
	   driver.findElementByClassName("FilterDirectory-searchInput").sendKeys("MANGO");
	   driver.findElementByXPath("(//input[@value='MANGO']/parent::label)[2]").click();
	   //Close the pop-up x
	   driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
	   Thread.sleep(5000);
	   List<WebElement> brandlist = driver.findElementsByXPath("//h3[@class='product-brand']");
	   boolean notMango=false;
		for (WebElement eachbrand : brandlist) 
		{
			String brands = eachbrand.getText();
			if (!brands.equalsIgnoreCase("MANGO")) 
			{
				notMango=true;
			}
		}
		//Confirm all the Coats are of brand MANGO
		if(notMango==false)
		{
			System.out.println("All coats are from brand MANGO");
		}
		//Sort by Better Discount
		WebElement ele1 = driver.findElementByClassName("sort-sortBy");
	    builder.moveToElement(ele1).perform();
	    WebElement ele2 = driver.findElementByXPath("//label[text()='Better Discount']");
	    builder.moveToElement(ele2).click().perform();
	    //Find the price of first displayed item
        String price = driver.findElementByXPath("(//span[@class='product-discountedPrice'])[1]").getText();
		String priceConvert = price.replaceAll("\\D","");
		int intPrice = Integer.parseInt(priceConvert);
	    System.out.println("First item price: "+intPrice);
	    //Mouse over on size of the first item
	    WebElement ele4 = driver.findElementByXPath("//span[@class='product-discountedPrice']");
		builder.moveToElement(ele4).perform();
		//Click on WishList Now
		driver.findElementByXPath("(//span[text()='wishlist now'])[1]").click();
		//Close Browser
		driver.close();
	     
	}

}
