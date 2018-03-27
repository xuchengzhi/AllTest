package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import com.sun.org.apache.xml.internal.resolver.helpers.Debug;
import sun.misc.BASE64Encoder;
import java.util.LinkedList;


//import net.sf.json.JSONObject;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.message.BasicHeader;
//import org.apache.http.protocol.HTTP;


@SuppressWarnings("deprecation")
public class push_pic {
	static String stime=""+System.currentTimeMillis();
//	static String stime="1499334806146";
	public static String URL = "http://hwtest.xiezixiansheng.com/mobile.php?c=Handziku&a=s_uptemplate";
	@SuppressWarnings("deprecation")
	public static void  get_pic(String path,String use,String ziti,String zuobiao,String sys,String ptype,String clientSW,String url) throws Exception{
	        File file = new File(path);
	        String img= "";
	        if (file.exists()) {
	            File[] files = file.listFiles();
	            if (files.length == 0) {
	                System.out.println("文件夹是空的!");
	            } else {
	                for (File file2 : files) {
	                    if (file2.isDirectory()) {
	                        System.out.println("文件夹:" + file2.getAbsolutePath());
	                        
	                    } else {
	                        System.out.println("文件:" + file2.getAbsolutePath());
	                        img=file2.getAbsolutePath(); 
	                        String imgcontent=get_base(img);
	                        String num=img.substring(img.indexOf(".png")-3,img.indexOf(".png"));
	                        String token=get_md5_sign(stime,num,use);
	                        String posarray=zuobiao;
	                        String ziku_id=ziti;
	                        String t=stime;
	                        String user_id=use;
	                        String p1="ziku_num"+"="+num+"&"+"token"+"="+token+"&"+"posarray"+"="+posarray+"&"+"ziku_id"+"="+ziku_id+"&"+"t"+"="+t+"&"+"user_id"+"="+user_id+"&"+"clientSW"+"="+clientSW+"&"+"ptype"+"="+ptype+"&"+"sys"+"="+sys+"&"+"imgcontent"+"="+imgcontent;         
	                        String s=Httprequest.sendPost(url, p1);
	                        System.out.println(s);
//	                        System.out.println(p1);
	                        method2("E:/logs/ceshi.txt",p1);
	                        Thread.sleep(5000);
//	                        System.out.println("tok="+token);
	                        
	                       
	                    }
	                }
	            }
	        } else {
	            System.out.println("文件不存在!");
	        }
			return;
	    }
	public static String get_base(String img){
		 byte[] data = null;
	     
		    // 读取图片字节数组
		    try {
		      InputStream in = new FileInputStream(img);
		      data = new byte[in.available()];
		      in.read(data);
		      in.close();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		     
		    // 对字节数组Base64编码
		    BASE64Encoder encoder = new BASE64Encoder();
		    return encoder.encode(data);
	}
	public static String  get_md5_sign(String stime,String num,String usr) throws Exception {
		
		String sys="ADR5.1.1";
		String tok1=stime+sys;
		String tok2=getmd5(tok1);
		String user=usr;
		String num1=num;
		String tok3=user+tok2+num1;
		String tok=getmd5(tok3);
		return tok;
		
	}
	 @SuppressWarnings("deprecation")
	public static String post(JSONObject json) {
		 
		        HttpClient client = new DefaultHttpClient();
		       
		          HttpPost post = new HttpPost(URL);
		          
		          post.setHeader("Content-Type", "application/json");
		         post.addHeader("Authorization", "Basic YWRtaW46");
		        String result = "";
		         
		         try {
		  
		            StringEntity s = new StringEntity(json.toString(), "utf-8");
		         s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
		           post.setEntity(s);
		
		         // 发送请求
		             HttpResponse httpResponse = client.execute(post);
		 
		            // 获取响应输入流
		             InputStream inStream = httpResponse.getEntity().getContent();
	           BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    inStream, "utf-8"));
		            StringBuilder strber = new StringBuilder();
		             String line = null;
		             while ((line = reader.readLine()) != null)
		                strber.append(line + "\n");
		            inStream.close();
		
		              result = strber.toString();
		            System.out.println(result);
	           
           if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	                
	                System.out.println("请求服务器成功，做相应处理");
	                 
	           } else {
	               
	               System.out.println("请求服务端失败");
		                
		             }
		          

	      } catch (Exception e) {
	         System.out.println("请求异常");
	            throw new RuntimeException(e);
    }

         return result;
   }
	public static String getmd5(String msg){
		try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(msg.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String md5=new BigInteger(1, md.digest()).toString(16);
            //BigInteger会把0省略掉，需补全至32位
            System.out.println(md5);
            return md5;
        } catch (Exception e) {
            throw new RuntimeException("MD5加密错误:"+e.getMessage(),e);
        }
	}
	public static void method2(String file, String conent) {
		BufferedWriter out = null;
		try {
		out = new BufferedWriter(new OutputStreamWriter(
		new FileOutputStream(file, true)));
		out.write(conent+"\r\n");
		} catch (Exception e) {
		e.printStackTrace();
		} finally {
		try {
		out.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
		}
		}
	
	
	public static void main(String args[]) throws Exception {

		
		
		String sys="ADR5.1.1";
        String ptype="iPhone 7plus";
        String clientSW="3.0.0";
        String url="http://hwtest.xiezixiansheng.com/mobile.php?c=Handziku&a=s_uptemplate";
		get_pic("E:/qiefen/datu","3575","6137","1056 1542 160 167 165 1538 1077 210",sys,ptype,clientSW,url);
}
}
