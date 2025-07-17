package com.selenium.AssignmentL2_1;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

// Assignment 1: Using the Selenium WebDriver and TestNG, Automate the following actions and do the required validations. Use Chrome Browser
public class AssignmentOne {
	// POM for finding the elements by name, id, xpath
	By blog = By.xpath("//li/child::a[text()='Blog']");
	By blogArchiveXpath = By.xpath("//strong[text()='Blog archive']");

	WebDriver driver;
	String url = "https://demowebshop.tricentis.com/";
	String urlBlog = "https://demowebshop.tricentis.com/blog";
	String blogArchive = "BLOG ARCHIVE";

	@BeforeTest
	public void launchURL() {
		// Step1: Launch URL with Chrome Browser
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);
	}

	@Test
	public void demoWebShop() {
		/*
		 * Step2: Scroll vertically till you find CUSTOMER SERVICE with
		 * JavascriptExecutor
		 */
		driver.manage().window().maximize();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,900)");
		/* Step3: Click on Blog */
		getClick(blog);
		// Step4: Check that the currect url is : https://demowebshop.tricentis.com/blog
		String urlBlogActual = driver.getCurrentUrl();
		if (urlBlog.equals(urlBlogActual)) {
			Assert.assertEquals(urlBlog, urlBlogActual);
		}
		/*
		 * Step5: Assert thet in the new page, you are able to find the text: BLOG
		 * ARCHIVE
		 */
		Assert.assertEquals(getText(blogArchiveXpath), blogArchive);
	}

	@AfterTest
	public void tearDownURL() {
		driver.quit();
	}

	// Function to click element
	public void getClick(By element) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		getWait(element);
		if (driver.findElement(element).isEnabled()) {
			driver.findElement(element).click();
		}
	}

	// Explicit wait
	public void getWait(By element) {
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element)));
	}

	// Function to Return Text
	public String getText(By element) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		getWait(element);
		String text = driver.findElement(element).getText().trim();
		return text;
	}
}
