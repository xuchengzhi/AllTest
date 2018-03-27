package com.test.test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class AndroidTest {

	static AndroidDriver driver= null;
	@SuppressWarnings("rawtypes")
//	@Test
	public static void setUp() throws Exception {
		
		String ad=Adb.conn();
		System.out.println(ad);
		String dev=ad.substring(0, ad.indexOf("	d"));
		System.out.println(dev);
		// set up appium
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "SJZZ_3.5.1.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");// 设备平台
		capabilities.setCapability("deviceName",dev);// 设备名称
//		capabilities.setCapability("platformVersion", "5.1.1");// 版本
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("app", app.getAbsolutePath());// apk路径
		capabilities.setCapability("noSign", true);// 支持中文输入
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("unicodeKeyboard", true);// 支持中文输入
		capabilities.setCapability("resetKeyboard", true);// 重设键盘为appium
		// 这两项可以不用
		capabilities.setCapability("appPackage", "com.handwriting.makefont");
		capabilities.setCapability("appActivity", ".main.SplashActivity");
		capabilities.setCapability("appWaitActivity",".login.LoginMainActivity");
		capabilities.setCapability("automationName","uiautomator2");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
	//登录
	public static boolean login(String login_name) throws Exception{
		String loginname=login_name;
		
		setUp();
		Thread.sleep(1000);
		System.out.println("启动客户端");
		if (loginname=="phone"){
			System.out.println("使用手机号登录");
			driver.findElementById("com.handwriting.makefont:id/phone_login_iv").click();//点击手机登录
			Thread.sleep(1000);
			driver.findElementById("com.handwriting.makefont:id/login_phone_num_et").click();//点击手机号输入框
			driver.findElementById("com.handwriting.makefont:id/login_phone_num_et").sendKeys("13912345678");
			Thread.sleep(1000);
			System.out.println("点击密码输入框");
			driver.findElementById("com.handwriting.makefont:id/login_pwd_num_et").click();
			System.out.println("开始输入密码");
			Thread.sleep(1000);
			driver.findElementById("com.handwriting.makefont:id/login_pwd_num_et").sendKeys("112233");
			Thread.sleep(1000);
			System.out.println("点击登录按钮");
			driver.findElementById("com.handwriting.makefont:id/login_ensure_tv").click();
			String updatemessage="我的字体";
			Thread.sleep(3000);
			if(driver.getPageSource().indexOf(updatemessage)!=-1){
				System.out.println("登录成功");
				return true;
			}
			else{
				System.out.println(driver.getPageSource());
				Thread.sleep(2000);
				System.out.println("登录失败");
				return false;
			}
		}else if(loginname=="微信"){
			System.out.println("使用微信登录");
			driver.findElementById("com.handwriting.makefont:id/weixin_login_iv").click();
		}else{
			System.out.println("使用qq登录");
			driver.findElementById("com.handwriting.makefont:id/qq_login_iv").click();
		}
		return false;
		
		
		
	}
//	@Test
	//创建屏幕手写字体
	public void addFontphoto() throws Exception{
		login("phone");
		/*底部导航：
		我的字体 id/layout_main_tab_create
		发现 com.handwriting.makefont:id/text_firstfavoursfragment_bookgroup
		屏幕手写com.handwriting.makefont:id/writing_image_view
		大模板com.handwriting.makefont:id/layout_main_menu_add_camera_paper
		小模板com.handwriting.makefont:id/layout_main_menu_add_camera
		消息 id/layout_main_tab_msg
		我的 id/layout_main_tab_mine
		新建字体banner com.handwriting.makefont:id/create_font_title_add_rl
		创建字体按钮com.handwriting.makefont:id/create_font_bt
		*/
		Thread.sleep(100);
		driver.findElementById("com.handwriting.makefont:id/create_font_title_add_rl").click();
		driver.findElementById("com.handwriting.makefont:id/camera_image_view").click();
		
	}

	//生成汉字
	public static char getRandomChar() {
	        return (char) (0x4e00 + (int) (Math.random() * (0x96d5 - 0x4e00)));
	    }

	//创建字体
	public static void addcamera(int size) throws Exception{
		String key1=getRandomChar()+"";
		String key2=getRandomChar()+"";
		String key3=getRandomChar()+"";
//		login("phone");
		Thread.sleep(100);
		driver.findElementById("com.handwriting.makefont:id/create_font_title_add_rl").click();
		if (size==1){
			driver.findElementById("com.handwriting.makefont:id/layout_main_menu_add_camera").click();
			System.out.println("选择大模板");
			Thread.sleep(1000);
			driver.findElementById("com.handwriting.makefont:id/create_font_bg_iv").click();
			System.out.println("输入字体:"+key1+key2+key3);
			driver.findElementById("com.handwriting.makefont:id/create_font_name_et").sendKeys(key1+key2+key3);
			Thread.sleep(1000);
			driver.findElementById("com.handwriting.makefont:id/create_font_bt").click();
			Thread.sleep(1000);
			System.out.println(driver.getPageSource());
		}
		else{
			driver.findElementById("com.handwriting.makefont:id/layout_main_menu_add_camera_paper").click();
			System.out.println("选择小模板");
			Thread.sleep(1000);
			System.out.println("输入字体:"+key1+key2+key3);
			driver.findElementById("com.handwriting.makefont:id/create_font_name_et").sendKeys(key1+key2+key3);
			driver.findElementById("com.handwriting.makefont:id/create_font_bt").click();
		}
		System.out.println(driver.getPageSource());
	}
	//启动activity
	public void startActivity(String Acti) throws InterruptedException{
		String act=Acti;
		System.out.println(act);
		Thread.sleep(5000);
		if (driver==null){
			System.out.print("error");
		}
		else{
			driver.startActivity("com.handwriting.makefont", act);
		}
	}
	
	public static void font(String orientation) {
		
	}
//	@Test
	public void actest() throws Exception{
		setUp();
		try {
		
			Thread.sleep(3000);
			startActivity(".settings.ActivityFeedBack");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//执行测试
	public static void run() throws Exception{
		
		if(login("phone")==false){
			done();
		}
		
		Thread.sleep(5000);
		String updatemessage="立即更新";
		if(driver.getPageSource().indexOf(updatemessage)!=-1){
			driver.findElementById("com.handwriting.makefont:id/dialog_font_close").click();
		}
		else{
			System.out.println(driver.getPageSource());
		}
		Thread.sleep(5000);
		
//		addcamera(1);
//		System.out.println(driver.currentActivity());
		
		
//		driver.findElementById("com.handwriting.makefont:id/text_firstfavoursfragment_bookgroup").click();
//		Thread.sleep(5000);
		MySwipeTest2.swipeToLeft(driver, 10, 100);
		
		int widht = driver.manage().window().getSize().width;

		int height = driver.manage().window().getSize().height;

//		for(int i = 0; i<20; i++){
//
//		// 向左滑动！
//			System.out.println("开始滑动");
//	
////			driver.performTouchAction(null);
////			driver.tap(2, 270, 740, 20);
//			Thread.sleep(1000);
//			
//			driver.swipe(270, 949, 810, 995, 50);
//			Thread.sleep(1000);
//			driver.swipe(280, 949, 860, 1005, 20);
//		}
		Adb.cli("shell input swipe 250 250 300 300");
		Thread.sleep(1000);
		Adb.cli("shell input swipe 270 949 810 995 50");
		Thread.sleep(1000);
		Adb.cli("shell input swipe 280 949 860 1005 20");
		
//		MySwipeTest2.swiptest();
//		Thread.sleep(5000);
//		System.out.println("开始滑动");
//		moveView("LEFT");
		Thread.sleep(10000);
//		done();
	}
	public static void main(String[] args) throws Exception {
		run();
//		Adb.run("adb shell input swipe 280, 949, 860, 1005, 20");

		
		
		
	
	
	
//		Thread.sleep(5000);
//		login("phone");
		
		
		
////		moveView("UP");
//		
		
//		
////		Thread.sleep(10000);
//		List<String> handleList = new ArrayList<String>();
//		Set<String> set =  driver.getContextHandles();
//		Iterator<String> it = set.iterator();  
//		while (it.hasNext()) {  
//			handleList.add(it.next());
//		}
////		driver.context(handleList.get(1));
//		
		
		//正则测试
//		String content = "I am noob " +
//        "from runoob.com.";
// 
//		String pattern = ".*runoob.*";
// 
//		boolean isMatch = Pattern.matches(pattern, content);
//		System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);

		
		
	}
	
	public static void moveView(String orientation) {
		try {
			Dimension sizestr = driver.manage().window().getSize();
			if (sizestr != null) {
				int xsize = sizestr.width;
				int ysize = sizestr.height;
				int Center_X = xsize / 2;
				int Center_Y = ysize / 2;
				if (orientation.equals("UP")) {
					driver.swipe(Center_X, Center_Y+100, Center_X, 1, 1000);
					System.out.println("成功向上滑动了");
				} else if (orientation.equals("DOWN")) {
					driver.swipe(Center_X, Center_Y-100, Center_X, ysize - 1, 1000);
					System.out.println("成功向下滑动了");
				} else if (orientation.equals("RIGHT")) {
					driver.swipe(Center_X-100, Center_Y, xsize - 1, Center_Y, 1000);
					System.out.println("成功向右滑动了");
				} else if (orientation.equals("LEFT")) {
					driver.swipe(Center_X+100, Center_Y, 1, Center_Y, 1000);
					System.out.println("成功向左滑动了");
				} else {
					System.err.println("传入的滑动方向参数有误！！");
				}
			} else {
				System.err.println("获取屏幕分辨率错误，滑动失败");
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

    public void find_toast(String message){ 
    	
    }
	
	public static void done()
	{
		driver.quit();
	}
}
