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
	                System.out.println("�ļ����ǿյ�!");
	            } else {
	                for (File file2 : files) {
	                    if (file2.isDirectory()) {
	                        System.out.println("�ļ���:" + file2.getAbsolutePath());
	                        
	                    } else {
	                        System.out.println("�ļ�:" + file2.getAbsolutePath());
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
	            System.out.println("�ļ�������!");
	        }
			return;
	    }
	public static String get_base(String img){
		 byte[] data = null;
	     
		    // ��ȡͼƬ�ֽ�����
		    try {
		      InputStream in = new FileInputStream(img);
		      data = new byte[in.available()];
		      in.read(data);
		      in.close();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		     
		    // ���ֽ�����Base64����
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
		
		         // ��������
		             HttpResponse httpResponse = client.execute(post);
		 
		            // ��ȡ��Ӧ������
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
	                
	                System.out.println("����������ɹ�������Ӧ����");
	                 
	           } else {
	               
	               System.out.println("��������ʧ��");
		                
		             }
		          

	      } catch (Exception e) {
	         System.out.println("�����쳣");
	            throw new RuntimeException(e);
    }

         return result;
   }
	public static String getmd5(String msg){
		try {
            // ����һ��MD5���ܼ���ժҪ
            MessageDigest md = MessageDigest.getInstance("MD5");
            // ����md5����
            md.update(msg.getBytes());
            // digest()���ȷ������md5 hashֵ������ֵΪ8Ϊ�ַ�������Ϊmd5 hashֵ��16λ��hexֵ��ʵ���Ͼ���8λ���ַ�
            // BigInteger������8λ���ַ���ת����16λhexֵ�����ַ�������ʾ���õ��ַ�����ʽ��hashֵ
            String md5=new BigInteger(1, md.digest()).toString(16);
            //BigInteger���0ʡ�Ե����貹ȫ��32λ
            System.out.println(md5);
            return md5;
        } catch (Exception e) {
            throw new RuntimeException("MD5���ܴ���:"+e.getMessage(),e);
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
