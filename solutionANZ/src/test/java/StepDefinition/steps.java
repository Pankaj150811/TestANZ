package StepDefinition;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class steps {
	WebDriver driver;
	
	@Given("^user launch the apllication$")
	public void user_launch_the_apllication() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "src//test//resource//Driver//chromedriver.exe");
	    driver=new ChromeDriver();
	    driver.get("https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/");
	    driver.manage().window().maximize();
	}

	@When("^user fill all the details and vaidate the borrowing estimate$")
	public void user_fill_all_the_details_and_vaidate_the_borrowing_estimate() throws Throwable {
		 //     driver.findElement(By.xpath("//input[@id='application_type_single']")).click();
		 driver.findElement(By.xpath("//span[@id='q2q1i1']/following-sibling::input")).click();
		 driver.findElement(By.xpath("//span[@id='q2q1i1']/following-sibling::input")).sendKeys("80,000");
		 driver.findElement(By.xpath("//input[@aria-describedby='q2q2i1 q2q2i2']")).sendKeys("10,000");
		driver.findElement(By.xpath("//input[@aria-describedby='q3q1i1 q3q1i2']")).sendKeys("500");
		driver.findElement(By.xpath("//input[@aria-describedby='q3q3i1 q3q3i2']")).sendKeys("100");
		driver.findElement(By.xpath("//input[@aria-describedby='q3q5i1']")).sendKeys("10,000");
		driver.findElement(By.id("btnBorrowCalculater")).click();
		 WebElement Amount = driver.findElement(By.xpath("//span[@id='borrowResultTextAmount']"));
	     String Amounttext=Amount.getText();
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,250)","");
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	Assert.assertEquals(Amounttext , "$508,000");
    	System.out.println("Amount is displayed");
    			
	}

	@Then("^user clear all the data clicking on the start over$")
	public void user_clear_all_the_data_clicking_on_the_start_over() throws Throwable {
		driver.findElement(By.xpath("(//button[@class='start-over'])[1]")).click();
		String[] clearedText = new String[6];
		clearedText[0] = driver.findElement(By.xpath("//span[@id='q2q1i1']/following-sibling::input")).getAttribute("value");
		clearedText[1] = driver.findElement(By.xpath("//input[@aria-describedby='q2q1i1 q2q1i2']")).getAttribute("value");
		clearedText[2] = driver.findElement(By.xpath("//input[@aria-describedby='q2q2i1 q2q2i2']")).getAttribute("value");
		clearedText[3] = driver.findElement(By.xpath("//input[@aria-describedby='q3q1i1 q3q1i2']")).getAttribute("value");
		clearedText[4] = driver.findElement(By.xpath("//input[@aria-describedby='q3q3i1 q3q3i2']")).getAttribute("value");
		clearedText[5] = driver.findElement(By.xpath("//input[@aria-describedby='q3q5i1']")).getAttribute("value");
		   for(int i =0;i<clearedText.length;i++) {
		     Assert.assertEquals(clearedText[i] , "0");  
		   }    
		}
	

	@Then("^User enter the \"([^\"]*)\" and leaving all other fields$")
	public void user_enter_the_and_leaving_all_other_fields(String arg1) throws Throwable {
		driver.findElement(By.xpath("//input[@aria-describedby='q3q1i1 q3q1i2']")).sendKeys("1");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("btnBorrowCalculater")).click();
		
	}

	@Then("^user validate the Error message$")
	public void user_validate_the_Error_message() throws Throwable {
		WebElement popup = driver.findElement(By.xpath("//div[@class='box--left text--center']/span"));
		String popuptext=popup.getText();
		         Assert.assertEquals(popuptext , "Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 035 500.");
		         System.out.println("Warning popup message is displayed");
		          driver.quit();
		
	}
	
	

}
