package com.test.test;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import io.appium.java_client.android.AndroidDriver;

public class StartWithActivity {

	static AndroidDriver driver= null;
	
	@BeforeClass
	public void setUp() throws Exception {
		// set up appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");// 设备平台
		capabilities.setCapability("deviceName", "127.0.0.1:62001");// 设备名称
		capabilities.setCapability("platformVersion", "4.3");// Android设备的版本
		capabilities.setCapability("noSign", true);// 禁止重签名
		capabilities.setCapability("unicodeKeyboard", true);// 支持中文输入
		capabilities.setCapability("resetKeyboard", true);// 重设键盘为appium
		capabilities.setCapability("appPackage", "ctrip.android.view");
		capabilities.setCapability("appActivity", "ctrip.android.view.splash.CtripSplashActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
	
	@Test
	public void testCase() throws InterruptedException
	{
		System.out.println(driver.currentActivity());
		Thread.sleep(5000);
		System.out.println(driver.currentActivity());
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
}
