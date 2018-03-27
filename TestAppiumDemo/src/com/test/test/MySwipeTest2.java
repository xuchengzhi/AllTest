package com.test.test;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import java.io.File;
import java.net.URL;

public class MySwipeTest2 {
    private static AndroidDriver<AndroidElement> driver;
    private boolean isInstall = false;
    private static MyNumSwipe myNumSwipe;
    @Before
    public void setUp()throws Exception{
        //set up appium
        DesiredCapabilities capbilities = new DesiredCapabilities();
        capbilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capbilities.setCapability("platformName", "Android");
        capbilities.setCapability("deviceName", "Android Emulator");
        capbilities.setCapability("platformVersion", "5.0");
        // if no need install don't add this
        if(isInstall){
            File classpathRoot=new File(System.getProperty("user.dir"));
            File appDir = new File(classpathRoot,"apps");
            File app = new File(appDir,"360.apk");
            capbilities.setCapability("app", app.getAbsolutePath());
        }
        capbilities.setCapability("appPackage", "");
        capbilities.setCapability("appPackage", "com.qihoo.appstore");
         // no need sign
        capbilities.setCapability("noSign", "True");
        capbilities.setCapability("appActivity", "com.qihoo.appstore.home.MainActivity");
        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),
                capbilities);
      }
    
    @After  
    public void tearDown() throws Exception {  
       driver.quit();  
    }
    
    public void sleep(long s) {
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public static void swiptest() throws InterruptedException{
        myNumSwipe = new MyNumSwipe();
        Thread.sleep(10000);
        swipeToLeft(driver, 500,3);
        Thread.sleep(10000);
        myNumSwipe.swipeToRight(driver, 500,3);
    }     

    public static void swipeToLeft(AndroidDriver<AndroidElement> driver,int during,int num){
        int width = driver.manage().window().getSize().width;//获取当前屏幕的宽度
        int height = driver.manage().window().getSize().height;//获取当前屏幕的高度
        for(int i=0;i<num;i++){
            driver.swipe(width*3/4, height/2, width/4, height/2, during);
        }
    }
}
