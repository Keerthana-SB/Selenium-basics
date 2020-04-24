package final_project_Testcases;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Honda_website {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(options);
		Map<String, String> map = new LinkedHashMap<String, String>();
		driver.get("https://www.honda2wheelersindia.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElementByClassName("close").click();
		//Click on scooters and click dio
		driver.findElementByLinkText("Scooter").click();
		Thread.sleep(5000);
		driver.findElementByXPath("//img[@src='/assets/images/thumb/dioBS6-icon.png']").click();
		//Click on Specifications and mouseover on ENGINE
		driver.findElementByLinkText("Specifications").click();
		Thread.sleep(5000);
		WebElement engine = driver.findElementByLinkText("ENGINE");
		Actions builder=new Actions(driver);
		builder.moveToElement(engine).perform();
		//Get Displacement value
		String dis = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText().replaceAll("//D", "").substring(0, 6);
		System.out.println(dis);
		double disValue=Double.parseDouble(dis);
		//Go to Scooters and click Activa 125
		driver.findElementByLinkText("Scooter").click();
		Thread.sleep(5000);
		driver.findElementByXPath("//img[@src='/assets/images/thumb/activa-125new-icon.png']").click();
		//Click on Specifications and mouseover on ENGINE
		driver.findElementByLinkText("Specifications").click();
		Thread.sleep(5000);
		WebElement engine1 = driver.findElementByLinkText("ENGINE");
		builder.moveToElement(engine1).perform();
		//Get Displacement value
		String dis1 = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText().replaceAll("//D", "").substring(0, 3);
		System.out.println(dis1);
		double disValue1=Double.parseDouble(dis1);
		//Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
		if(disValue > disValue1) {
			System.out.println("Dio having Better Displacement value");
		}
		else {
			System.out.println("Activa 125 is having Better Displacement value");
		}
		//Click FAQ from Menu
		driver.findElementByLinkText("FAQ").click();
		//Click Activa 125 BS-VI under Browse By Product
		driver.findElementByLinkText("Activa 125 BS-VI").click();
		//Click Vehicle Price 
		driver.findElementByLinkText("Vehicle Price").click();
		Thread.sleep(5000);
		WebElement ele = driver.findElementByXPath("//option[text()='Activa 125 BS-VI']");
		//Make sure Activa 125 BS-VI selected and click submit
		if(ele.isSelected()) {
			System.out.println("Activa 125 is selected");
		}
		else {
			System.out.println("Activa 125 is not selected");
		}
		
		Thread.sleep(5000);
		driver.findElementByXPath("//button[@id='submit6']").click();
		Thread.sleep(5000);
		////click the price link
		driver.findElementByLinkText("Click here to know the price of Activa 125 BS-VI.").click();
		//Go to the new Window and select the state as Tamil Nadu and city as Chennai
		Set<String> winSet = driver.getWindowHandles();
        List<String> winList= new ArrayList<String>(winSet);
        driver.switchTo().window(winList.get(1));
        WebElement state = driver.findElementById("StateID");
        Select se1 = new Select(state);
		se1.selectByVisibleText("Tamil Nadu");
        WebElement City = driver.findElementById("CityID");
		Select se2 = new Select(City);
		se2.selectByVisibleText("Chennai");
		//Click Search
		driver.findElementByXPath("//button[text()='Search']").click();
        List<WebElement> models = driver.findElementsByXPath("//table[@id='gvshow']//td[contains(text(),'ACTIVA 125')]");
		System.out.println(models.size());
		//Print all the 3 models and their prices
		System.out.println("All 3 models and their prices are:");
		for (int i=0;i<=models.size()-1;i++)
		{
			String model = models.get(i).getText();
			String modelPrice = driver.findElementByXPath("(//table[@id='gvshow']//td[contains(text(),'ACTIVA 125')]/following-sibling::td)['"+i+"']").getText();
			map.put(model, modelPrice);
		}
		
		for ( Entry<String, String> eachEntry:map.entrySet())
		  
		  {
		  	System.out.println(eachEntry.getKey() +"-"+ eachEntry.getValue()); 
		  }
		
		//Close the Browser
		driver.quit();
	}

}
