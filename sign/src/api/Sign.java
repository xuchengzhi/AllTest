package api;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.io.IOException;

import java.io.InputStream;

public class Sign {
	public static String  encrypt(String type,String date,String user,String sys,String styleid, String string){
	    String y="";
	    System.out.println("type is:"+type);
	    System.out.println(date);
	    System.out.println(user);
	    System.out.println(sys);
	    System.out.println(styleid);
	  
//	    String ms=new ArrayList();
	 
	     date = date;
//	    String date = "1502848220734";
	    long da=System.currentTimeMillis();
//	    System.out.println(date);
	    String a="photo";
		if (type.equals(a)){
	        y=user+(date);
			String dest=getmd5(y);
//			System.out.println(dest);
			String tk=sys+dest+styleid;
			String tok1=getmd5(tk);
//			ms.add(date);
//			ms.add(tok1);
			System.out.println("photo encrypt is:"+tok1);
			
			return tok1;
			}
			
	    else {
	        String y1=sys+date;
	        String dest=getmd5(y1);
//	        System.out.println(dest);
	        String tk1=user+dest+styleid;
	        String tok1=getmd5(tk1);
//	        ms.add(date);
//			ms.add(tok1);
	        System.out.println("not photo encrypt is:"+tok1);
	        return tok1;
	    }
//		msg={"tk":,"shijian":date,"user":user}
	    
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
//            System.out.println(md5);
            return md5;
        } catch (Exception e) {
            throw new RuntimeException("MD5���ܴ���:"+e.getMessage(),e);
        }
//		
       
		
	}
//	public static void main(String[] args){
////		String type="";
////		String user="";
////		String sys="";
////		String styleid="";
////		System.out.println("input type:");
////		BufferedReader typea=new BufferedReader(new InputStreamReader(System.in));
////		
////		BufferedReader us=new BufferedReader(new InputStreamReader(System.in));
////		
////		BufferedReader sys1=new BufferedReader(new InputStreamReader(System.in));
////		
////		BufferedReader styleid1=new BufferedReader(new InputStreamReader(System.in));
////		type=typea.readLine();
////		System.out.println("input users:");
////		user=us.readLine();
////		System.out.println("input sys:");
////		sys=sys1.readLine();
////		System.out.println("input styleid:");
////		styleid=styleid1.readLine();
//		
//		long date=System.currentTimeMillis();
//		String t=""+date;
//		String tok=encrypt("photoe",t,"2409","ADR4.4.2","1", null);
////		System.out.println(tok.get(1));
//		
//		
//	}
	
	
}
