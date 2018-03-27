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
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(msg.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String md5=new BigInteger(1, md.digest()).toString(16);
            //BigInteger会把0省略掉，需补全至32位
//            System.out.println(md5);
            return md5;
        } catch (Exception e) {
            throw new RuntimeException("MD5加密错误:"+e.getMessage(),e);
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
