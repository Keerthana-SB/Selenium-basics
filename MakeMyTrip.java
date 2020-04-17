package final_project_Testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(options);
		
		//Launch MakeMyTrip website
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		//Click Hotels
		driver.findElementByXPath("//span[contains(@class,'chHotels')]").click();
		System.out.println("Hotels selected");
		
		//Enter city as Goa, and choose Goa, India
		driver.findElementByXPath("//label[@for='city']").click();
		driver.findElementByXPath("//input[contains(@class,'react-autosuggest')]").sendKeys("Goa");
		driver.findElementByXPath("//p[@class='font12 greyText appendBottom5']").click();
		
		//Enter Check in date as Next month 15th (May 15) and Check out as start date+5
		String checkinDate = driver.findElementByXPath("(//div[text()='15'])[2]").getText();
		int checkoutDate=Integer.parseInt(checkinDate)+5;
		driver.findElementByXPath("(//div[text()='15'])[2]").click();
		Thread.sleep(5000);
		driver.findElementByXPath("(//div[text()='"+checkoutDate+"'])[2]").click();
		
		//Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
		driver.findElementById("guest").click();
		driver.findElementByXPath("//li[@data-cy='adults-2']").click();
		driver.findElementByXPath("//li[@data-cy='children-1']").click();
		Thread.sleep(5000);
		WebElement ele = driver.findElementByClassName("ageSelectBox");
		Select dd= new Select(ele);
		dd.selectByVisibleText("12");
		System.out.println("Child age is 12");
		Thread.sleep(5000);
		driver.findElementByXPath("//button[@class='primaryBtn btnApply']").click();
		
		//Click Search button
		driver.findElementByXPath("//button[text()='Search']").click();
		Thread.sleep(5000);
		driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']").click();
		driver.switchTo().frame(driver.findElementById("webklipper-publisher-widget-container-notification-frame"));
		driver.findElementByClassName("we_forward").click();
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		
		//Select locality as Baga
		driver.findElementByXPath("//label[text()='Baga']").click();
		System.out.println("Locality is Baga");
		
		//Select 5 start in Star Category under Select Filters
		driver.findElementByXPath("//label[text()='5 Star']").click();
		System.out.println("Star Category is 5 Star hotels");
		
		//Click on the first resulting hotel and go to the new window
		driver.findElementByXPath("//span[text()='Acron Waterfront Resort-Member ITC Hotel Group']").click();
		Set<String> winSet = driver.getWindowHandles();
                List<String> winList= new ArrayList<String>(winSet);
                driver.switchTo().window(winList.get(1));
                
		//Print the Hotel Name
               String hotelName = driver.findElementById("detpg_hotel_name").getText();
               System.out.println("Hotel Name: "+hotelName);
               Thread.sleep(5000);
        
        
               //Click MORE OPTIONS link and Select 3Months plan and close
               driver.findElementByXPath("//span[text()='MORE OPTIONS']").click();
               driver.findElementByXPath("(//span[contains(@class,'right blueText')])[1]").click();
               driver.findElementByClassName("close").click();
               Thread.sleep(5000);
        
                //Click on BOOK THIS NOW
                driver.findElementByXPath("//a[text()='BOOK THIS NOW']").click();
		driver.findElementByClassName("close").click();
		
		//Print the Total Payable amount
		String totalPay= driver.findElementById("revpg_total_payable_amt").getText().replaceAll("[^0-9]", "");
		System.out.println("Total Payable amount is "+totalPay);
		
		//Close all the window
		driver.quit();
		
	}

}
