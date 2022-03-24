package test.scripts;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class login extends TestBase {

	
	@Test
	public void loginPage() {
		
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		
	}
}
