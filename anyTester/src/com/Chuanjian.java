package com;

import java.sql.ResultSet;  
import java.sql.SQLException;  

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
public class Chuanjian {  
    static String sql = null; 
    static DBHelper db1 = null;  
    static ResultSet ret = null;  
    static String sql1 = null; 
    static DBHelper db2 = null;  
	static ResultSet ret1 = null; 
    public static void insert(){
    	Date d = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String dateNowStr = sdf.format(d);
    	System.out.println("当前的日期：" + dateNowStr);
    	int max=2000;
        int min=10;
        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;
        int dw_id=Integer.parseInt(sele())+1;
    	String name="xuchengzhi"+s;
    	sql1 = "INSERT INTO  ceshi (id,ceshiname,time)VALUES"+"("+dw_id+","+"\""+name+"\""+","+"\""+dateNowStr+"\""+")";//SQL语句  
//    	sql="INSERT INTO  ceshi (id,ceshiname,time)VALUES(12,xuchengzhi16,2017-08-26 16:07:14)";
    	System.out.println(sql1);
        db2 = new DBHelper(sql1);//创建DBHelper对象  
        try {  
             db2.pst.executeUpdate();//执行语句，得到结果集  
             System.out.println(dw_id+"插入成功");  
            db2.close();//关闭连接  
          
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
    }
    public static String sele(){
    	sql = "select * from ceshi order by id desc limit 1";//SQL语句  
    	System.out.println(sql);
        db1 = new DBHelper(sql);//创建DBHelper对象  
        String id="";
  
        try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {  
            	
                String id1 = ret.getString(1);  
                String ceshiname = ret.getString(2);  
                String riqi = ret.getString(3);
                System.out.println(id1); 
                return id1;
            }//显示数据  
            ret.close();  
            db1.close();//关闭连接  
            return id;
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
		return id;  
    }
    
    public static void main(String[] args) throws SQLException, InterruptedException {  
    	int num=0;
    	while(num<10000){
    		Thread.sleep(400);
    	insert();
    	num++;
    	}
    }  
  
}  
