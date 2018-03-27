package com.test.test;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class StartWithWeb {
	

	static AndroidDriver driver = null;
	

	@BeforeClass
	public static void setUp() throws Exception {
		// set up appium
		String ad=Adb.conn();
		
		String dev=ad.substring(0, ad.indexOf("	d"));
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Browser");
		capabilities.setCapability("platformName", "Android");// 设备平台
		capabilities.setCapability("deviceName",dev);// 设备名称
		capabilities.setCapability("platformVersion", "5.1.1");// Android设备的版本
		capabilities.setCapability("unicodeKeyboard", true);// 支持中文输入
		capabilities.setCapability("resetKeyboard", true);// 重设键盘为appium
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@Test
	public static void testCase() throws InterruptedException {
		System.out.println("启动浏览器");
		driver.get("http://www.baidu.com");
		System.out.println(driver.currentActivity());
		Thread.sleep(5000);
		System.out.println(driver.getPageSource());
		System.out.println(driver.currentActivity());
	}
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
	
	public static void main(String[] args) throws Exception{
		setUp();
		testCase();
		tearDown();
	}
}
