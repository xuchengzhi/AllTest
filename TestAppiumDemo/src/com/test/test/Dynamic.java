package com.test.test;

import java.lang.reflect.Method;

public class Dynamic {
	public static void main(String[] args) throws Exception{
	    Class c = Dynamic.class;//得到对象
	    Method[] methods = c.getDeclaredMethods();//得到方法

	    for(Method method : methods){
	      System.out.print(method.getName());//打印参数名
	      Class[] parameterTypes = method.getParameterTypes();
	      for(int i=0;i<parameterTypes.length;i++){
	          String nameString=parameterTypes[i].getName();
	          System.out.print("parameterType:"+nameString);//打印方法参数类型
	      }
	      
	      System.out.print("ReturnType:"+method.getReturnType());
	          System.out.println();
	      }

	      Object obj=c.newInstance();//获取到方法对象,假设方法的参数是一个int,String,method名为getAge
	      System.out.print(obj.getClass());
	      Method sAge = c.getMethod("getAge", new Class[] {int.class,String.class});
	      //获得参数Object
	       Object[] arguments = new Object[]{new Integer(23),new String("abc")};
	      //执行方法
	      String s=(String)sAge.invoke(obj , arguments);
	     System.out.print(s);
	     }

	     public String  getAge(int age,String name){
	           return name+": "+age;
	     }

}
