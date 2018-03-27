package com.test.test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.lang.reflect.Method; 
import java.lang.reflect.InvocationTargetException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.server.CommandQueue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.commons.io.FileUtils;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.HideKeyboardStrategy;
import io.appium.java_client.remote.MobileCapabilityType;

// "/XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTabBar[1]/XCUIElementTypeButton[4]"创建入口
//"/XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTabBar[1]/XCUIElementTypeButton[5]"消息
//"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTabBar[1]/XCUIElementTypeButton[6]"我的
//"XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTabBar[1]/XCUIElementTypeButton[3]"发现
//"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTabBar[1]/XCUIElementTypeButton[2]"首页
//"
//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeButton[2]"硬笔书写
//"
//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeButton[3]"大模版
//"
//"XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextView[1]"屏幕手写输入框
//"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[2]"点击创建按钮
//"
//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]"搜索
//"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeTextField[1]"搜索输入框
//"
//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeSheet[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"删除字体按钮
//"
//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]"字体详情返回按钮
//"
//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[2]"提交按钮
//"
//"XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeButton[4]"下一个字
//"
//"XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeButton[1]"预览
//"
//"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeButton[2]"我的字稿
//"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]"字体详情返回

public class IOSTest {

	static IOSDriver driver= null;
	@BeforeClass
	@Test
	public void setUp() throws Exception {
		// set up appium
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir,"SJZZ.app");
		
//		File app = new File(appDir, "kartor.zip");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "iOS");// 设备平台
		capabilities.setCapability("deviceName", "Phone 6 Plus");// 设备名称
		capabilities.setCapability("platformVersion", "10.3");// 版本
		capabilities.setCapability("app", app.getAbsolutePath());// apk路径
//		capabilities.setCapability(MobileCapabilityType.APP, true);// 禁止重签名
		capabilities.setCapability("noSign", true);// 禁止重签名
		capabilities.setCapability("autoAcceptAlerts", true);// 自动接受提示信息
		capabilities.setCapability("udid","062172C4-4A51-4239-BB98-27D3F13CA7AE");
		capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
	/* 随机中文名 */
	public static String getRandomJianHan(int len){
		String ret="";
        for(int i=0;i<len;i++){
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try
            {
                str = new String(b, "GBk"); //转成中文
            }
            catch (UnsupportedEncodingException ex)
            {
                ex.printStackTrace();
            }
             ret+=str;
        }
        return ret;
	}

	@SuppressWarnings("unused")
//	@Test
	/*登录操作*/
	public void testCaseLogIn() throws Exception {
		driver.findElementByName("Allow").click();
//		Thread.sleep(5000);
		driver.swipe(0,0,1,6, 0);
//		System.out.println(driver.findElementsByClassName("Button"));
		driver.findElementByXPath("/XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[3]").click();//点击手机登录
		Thread.sleep(1000);
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]").click();//光标定位到手机输入框
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]").sendKeys("13912345678");//手机输入框输入内容
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeSecureTextField[1]").click();//光标定位到密码输入框
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeSecureTextField[1]").sendKeys("112233");//输入密码
		try{
			driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[3]").click();//点击登录
		}catch ( NoSuchElementException e) {
			Thread.sleep(5000);
			driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[3]").click();//点击登录

		}
//		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[3]").click();//点击登录
		Thread.sleep(5000);
//		testCaseRepeatedly(10);
//		System.out.println("点击后"+driver.findElementsByClassName("Button"));
	}
	//随机上拉下滑页面
	public void testCaseRefresh(String clname){
		System.out.println(clname);
		java.util.Random random=new java.util.Random();// 定义随机类
		int result=random.nextInt(10);// 返回[0,10)集合中的整数，注意不包括10
		String clname1=clname;
		if (clname1=="update"){
				if (result%2==1){
					moveView("UP");
					moveView("UP");
				}else{
					moveView("DOWN");
					moveView("DOWN");
				}
		
		}
		else{
			if (result%2==1){
				driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeButton[2]").click();//点击小模版
			}else{
				driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeButton[3]").click();//点击大模版
			}
		}
	}
	public void testCaseRepeatedly(int num){
		for (int i=0;i<num;i++){
			testCaseRefresh("update");
		}
	}
//	@Test
	public void test() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		Class c = IOSTest.class;//得到对象
	    Method[] methods = c.getDeclaredMethods();//得到方法
//	    System.out.println(methods);
	    

	    for(Method method : methods){
//	      System.out.print(method.getName());//打印参数名
	      Class[] parameterTypes = method.getParameterTypes();
	      for(int i=0;i<parameterTypes.length;i++){
	          String nameString=parameterTypes[i].getName();
//	          System.out.print("parameterType:"+nameString);//打印方法参数类型
	      }
	      
//	      System.out.print("ReturnType:"+method.getReturnType());
//	          System.out.println();
	      }

	      Object obj=c.newInstance();//获取到方法对象,假设方法的参数是一个int,String,method名为getAge
	      
	      Method sAge = c.getMethod("testCaseRepeatedly", new Class[] {int.class,String.class});
	      //获得参数Object
//	       Object[] arguments = new Object[]{new Integer(10),new String(("update"))};
//	      //执行方法
//	      String s=(String)sAge.invoke(obj , arguments);
//	     System.out.print(s);
	     

	}
private String testCaseRepeatedly(String string) {
		// TODO Auto-generated method stub
		return null;
	}
//	@Test
	//创建屏幕手写字体
	public void testCaseAdd() throws Exception{
		testCaseLogIn();
		System.out.println("开始创建字体");
		Thread.sleep(1000);
		driver.swipe(0,0,0,0, 0);
		System.out.println(driver.findElementsByClassName("Button"));
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTabBar[1]/XCUIElementTypeButton[4]").click();//点击创建按钮
//		Thread.sleep(5000);
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeButton[1]").click();//选择屏幕手写
		Thread.sleep(5000);
		driver.swipe(0,0,1,6, 0);
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextView[1]").click();//点击输入区域
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextView[1]").sendKeys(getRandomJianHan(4));//输入字体名称
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[2]").click();//点击创建按钮
		Thread.sleep(10000);
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]").click();
		driver.getKeyboard();
		driver.hideKeyboard();
		
		driver.hideKeyboard("HOME");
//		driver.navigate().back();
//		driver.switchTo().window("FCMineTemplatesController");
		Thread.sleep(5000);
		System.out.println("==============================");
		System.out.println(driver.getPageSource().replaceAll("CUIElementTypeWindow|XCUIElementTypeOther|type=|<XCUIElementTypeWindow|\\|<|>|AppiumAUT|XCUIElementTypeApplication|<<|<XCUIElementTypeOther ", ""));
		
//		driver.findElementByXPath("/XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTabBar[1]/XCUIElementTypeButton[5]").click();//
//		driver.findElementByXPath("XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTabBar[1]/XCUIElementTypeButton[3]").click();//
//		driver.findElementByXPath("XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTabBar[1]/XCUIElementTypeButton[3]").click();//
	
	}
//	@Test
	//创建拍照上传字体
	public void testCaseAddPhoto() throws Exception{
//		testCaseLogIn();
		System.out.println("开始创建字体");
		Thread.sleep(1000);
		driver.swipe(0,0,0,0, 0);
		getScreenShot();
		System.out.println(driver.findElementsByClassName("Button"));
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTabBar[1]/XCUIElementTypeButton[4]").click();//点击创建按钮
		Thread.sleep(1000);
		testCaseRefresh("addphooto");
		Thread.sleep(1000);
		driver.swipe(0,0,1,6, 0);
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextView[1]").click();//点击输入区域
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextView[1]").sendKeys(getRandomJianHan(1));//输入字体名称
		Thread.sleep(3000);
		try{
			driver.findElementByName("开始创建字体").click();//点击创建按钮
		}catch( NoSuchElementException e){
			driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextView[1]").click();//点击输入区域
			driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextView[1]").sendKeys(getRandomJianHan(1));//输入字体名称
			driver.findElementByName("开始创建字体").click();//点击创建按钮
		}
		
		
		Thread.sleep(10000);
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]").click();
		
	}
	@SuppressWarnings("unchecked")
	
//	@Test
	//搜索
	public void testCaseSearch() throws Exception{
//		testCaseLogIn();//调用登录

		String ms=driver.getPageSource();
//		System.out.println( driver.hashCode());
//		System.out.println(driver.getTitle());
//		System.out.println(driver.getContext());
//		System.out.println(ms);
//		driver.shake();
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTabBar[1]/XCUIElementTypeButton[3]").click();//点击发现
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]").click();//点击搜索按钮
		System.out.println("========================================================================"+"\n");
//		System.out.println(ms);
		System.out.println("点击搜索输入框");
		Thread.sleep(5000);
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeTextField[1]").click();//点击搜索输入框
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeTextField[1]").click();//点击搜索输入框
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeTextField[1]").click();//点击搜索输入框
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeTextField[1]").click();//点击搜索输入框
		System.out.println("==========================================================================");
		System.out.println(ms);
		System.out.println("开始输入字体名");
		driver.findElementByXPath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeTextField[1]").sendKeys(getRandomJianHan(2));//输入随机汉字
		getScreenShot();
//		driver.findElementByAccessibilityId("6").click();
		Thread.sleep(5000);
		System.out.println("================================================================================");
		driver.hideKeyboard(HideKeyboardStrategy.PRESS_KEY, "Done"); 
		System.out.println(driver.getOrientation());//获取屏幕方向
//		@SuppressWarnings("rawtypes")
//		HashMap keycode = new HashMap();
//		keycode.put("keycode",3);
//		driver.executeScript("mobile:touchAndHold", keycode);
	}
//	
////	@Test
//	public void testCaseKartor() throws Exception {
//		Thread.sleep(5000);
//		enterKartor();
//		getElement("name","使用密码登录").click();
//		Thread.sleep(2000);
//		getElement("classname","UIATextField").clear();
//		getElement("classname","UIATextField").sendKeys("12345678");
//		getElement("xpath","//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123214214");
//		getElement("name","登录").click();
//		getScreenShot();
//		Thread.sleep(5000);
//	}
//	
//	@AfterClass
//	public void done()
//	{
//		driver.quit();
//	}
//	
//	public void enterKartor()
//	{
//		System.out.println(getElement("xpath","//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAImage[1]"));
//		while(getElement("xpath", "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAImage[1]") !=null 
//				&& getElement("xpath", "//UIAApplication/UIAWindow[1]/UIAScrollView/UIAImage[1]/UIAButton") ==null)
//		{
//			moveView("LEFT");
//		}
//		if(getElement("name", "enginStart n") !=null)
//		{
//			getElement("name", "enginStart n").click();
////			nakme:enginStart n
////			//UIAApplication/UIAWindow[1]/UIAScrollView/UIAImage[1]/UIAButton
//		}
//	}
//	
//	/**
//	 * 屏幕截图
//	 * @param fileName 保存文件名
//	 */
	public String getScreenShot() {
		String finalName = "";
		//图片保存位置
		String fileDir = System.getProperty("user.dir") + "/pic/";
		File screenShotFile = driver.getScreenshotAs(OutputType.FILE);
		try {
			finalName = fileDir + getCurrentDateTime() + ".jpg";
			FileUtils.copyFile(screenShotFile, new File(finalName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return finalName;
	}
//	
//	/**
//	 * 获取当前的系统时间
//	 * @return yyyyMMddHHmmss 格式的时间
//	 */
	public String getCurrentDateTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		return df.format(new Date());
	}
//	
	public WebElement getElement(String type, String elStr) {
		WebElement we = null;
		// 先按xpath进行查询，如果没找到，按name查询，最后按classname查询。
		try {
			if(type.equals("xpath"))
			{
				we = driver.findElement(MobileBy.xpath(elStr));
				System.out.println("成功找到 [ xpath = " + elStr + " ] 的元素。");
			}
			else if(type.equals("name"))
			{
				we = driver.findElement(MobileBy.name(elStr));
				System.out.println("成功找到 [ name = " + elStr + " ] 的元素。");
			}
			else if(type.equals("classname"))
			{
				we = driver.findElement(MobileBy.className(elStr));
				System.out.println("成功找到 [ classname = " + elStr + " ] 的元素。");
			}
			else
			{
				System.out.println("type类型不存在");
			}
		} catch (NoSuchElementException e) {
//			e.printStackTrace();
		}
		return we;
	}
//	
//	public List<WebElement> getElements(String type, String elStr) {
//		List<WebElement> we = null;
//		// 先按id进行查询，如果没找到，按text查询，最后按xpath查询。
//		try {
//			if(type.equals("xpath"))
//			{
//				we = driver.findElements(MobileBy.xpath(elStr));
//				System.out.println("成功找到 [ xpath = " + elStr + " ] 的元素。");
//			}
//			else if(type.equals("name"))
//			{
//				we = driver.findElements(MobileBy.name(elStr));
//				System.out.println("成功找到 [ name = " + elStr + " ] 的元素。");
//			}
//			else if(type.equals("classname"))
//			{
//				we = driver.findElements(MobileBy.className(elStr));
//				System.out.println("成功找到 [ classname = " + elStr + " ] 的元素。");
//			}
//			else
//			{
//				System.out.println("type类型不存在");
//			}
//		} catch (NoSuchElementException e) {
////			e.printStackTrace();
//		}
//		return we;
//	}
//	
	public void moveView(String orientation) {
		try {
			Dimension sizestr = driver.manage().window().getSize();
			if (sizestr != null) {
				int xsize = sizestr.width;
				int ysize = sizestr.height;
				int Center_X = xsize / 2;
				int Center_Y = ysize / 2;
				
				if (orientation.equals("UP")) {
					driver.swipe(Center_X, Center_Y,Center_X, -ysize/4,  1000);

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
//	
//
}
