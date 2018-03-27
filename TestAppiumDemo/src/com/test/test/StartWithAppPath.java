package com.test.test;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class StartWithAppPath {

	static AndroidDriver driver = null;

	@BeforeClass
	public void setUp() throws Exception {
		// set up appium
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "ctrip.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");// 设备平台
		capabilities.setCapability("deviceName", "QMSDU15A24012345");// 设备名称
		capabilities.setCapability("platformVersion", "4.3");// Android版本
		capabilities.setCapability("app", app.getAbsolutePath());// apk绝对路径，可设置为可访问的网络路径
		capabilities.setCapability("noSign", true);// 禁止重签名
		capabilities.setCapability("unicodeKeyboard", true);// 支持中文输入
		capabilities.setCapability("resetKeyboard", true);// 重设键盘为appium
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@Test
	public void testCase() throws InterruptedException {
		System.out.println(driver.currentActivity());
		Thread.sleep(5000);
		System.out.println(driver.currentActivity());
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
