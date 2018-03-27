package com.test.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.testng.annotations.Test;

public class Adb {
	private static final String line = null;
//	@Test
	public static String run(String em) throws UnsupportedEncodingException{

		InputStream ins = null;

    	String RotatePNG="E:/sdk/platform-tools/adb.exe";
    	
        String[] cmd = new String[] {"adb.exe "+em," "};  // 命令
        System.out.println(cmd[0]);
        System.out.println("==============================");
        
       
        try {
//        	Thread.sleep(5000);
            Process process = Runtime.getRuntime().exec(cmd);
            ins = (InputStream) process.getInputStream();  // 获取执行cmd命令后的信息
            System.out.println(ins);
            BufferedReader reader = new BufferedReader(new InputStreamReader(ins));   
            String line = null;   
            while ((line = reader.readLine()) != null) {   
                System.out.println(new String(line));  // 输出 
//                return line; 
            } 
//            num=line;
            int exitValue = process.waitFor(); 
     
            
            process.getOutputStream().close();  // 不要忘记了一定要关
           
        } catch (Exception e) {
            e.printStackTrace();
        }
		return RotatePNG;
		
    }
//	@Test
	public static String conn() throws IOException{
		Process process;
		//一次性执行多个命令
		String cmd = "cmd /c \"E:/sdk/platform-tools/adb.exe devices\"";

		try {
			Runtime runtime = Runtime.getRuntime();

			System.out.println("开始执行。。。");
			process = runtime.exec(cmd);

			//打印执行的输出结果
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "gbk"); //gbk：解决输出乱码
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null){
				if(line.indexOf("	device")!=-1){
//					System.out.println(line);
					return line;
				}
			}
			

			is.close();
			isr.close();
			br.close();
			return line;
		}catch (IOException e){
			e.printStackTrace();
		}
		return line;
	}
	public static String cli(String em) throws IOException{
		Process process;
		//一次性执行多个命令
		String cmd = "cmd /c \"E:/sdk/platform-tools/adb.exe \""+em;

		try {
			Runtime runtime = Runtime.getRuntime();

			System.out.println("开始执行。。。");
			process = runtime.exec(cmd);

			//打印执行的输出结果
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "gbk"); //gbk：解决输出乱码
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null){
				if(line.indexOf("	device")!=-1){
					System.out.println(line);
					return line;
				}
			}
			

			is.close();
			isr.close();
			br.close();
			return line;
		}catch (IOException e){
			e.printStackTrace();
		}
		return line;
	}
	
}
