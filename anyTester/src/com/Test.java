package com;

import org.apache.jmeter.functions.GetRandomStr;

import api.Sign;

public class Test {
	public static void main(String[] args) throws InterruptedException{
//		String s=Httprequest.sendPost("http://hwtest.xiezixiansheng.com/mobile.php?c=Users&a=phone_check", "login_name=13888888882");
//        System.out.println(s);
//        Thread.sleep(3000);
//        String sr=Httprequest.sendGet("http://hwtest.xiezixiansheng.com/mobile.php", "c=Client&a=gupdate&platform=adr&cpid=fz&clientSW=1.0.2&ptype=Product%3A+kenzo%2C+CPU_ABI%3A+arm64-v8a%2C+MODEL%3A+Redmi+Note+3%2C+SDK%3A+22%2C+BRAND%3A+Xiaomi%2C+MANUFACTURER%3A+Xiaomi%2C+USER%3A+builder&sys=ADR5.1.1");
//        System.out.println(sr);
//        Thread.sleep(3000);
//        String re=Httprequest.sendPost("http://hwtest.xiezixiansheng.com/mobile.php?c=Handziku&a=s_ziku","ziku_name:¹þ¹þ¹þ¶Ô,&user_id:1914,&ziku_type:0,&brush_width:15,&brush_type:2,&style_id:1,&mode:0,&type:2,&clientSW:2.4.2,&ptype:Product: unknown, CPU_ABI: x86, MODEL: 7PLUS, SDK: 19, BRAND: hwG750-T00, MANUFACTURER: Iphone, USER: samsung,&sys:ADR4.4.2,&t:1495444136000&token:0275dd542fcb0f91f1b0bfbd174c47bc");
       
//		System.out.println(re);
		String s=Sign.encrypt("photo1", "1502863065398", "1471437", "ADR4.4.2", "1", null);
		System.out.println(s);
		
	
	}
}
