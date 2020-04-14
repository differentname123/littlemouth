package com.anxpp.io.calculator.bio;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONObject;


public class date {
	
	JSONObject object = new JSONObject();
	byte[] jsonByte;
	String st,st1;
	int len;
	byte[] data1;
	
	Map<String, Object> map = new LinkedHashMap<String, Object>();
	public date() throws IOException{
		
		//FileInputStream fis = new FileInputStream(str);
       // int size = fis.available();
       
		//System.out.println("size = " + size);
       // data1 = new byte[size];
        //fis.read(data1);
        //st1=data1.toString();
		object.put("name","mianhua");
		object.put("string","中文");
		//object.put("byte",data1);
		object.put("int",2);  
		object.put("boolean",true);
		
	
		System.out.println("类里面"+object);
		jsonByte=object.toString().getBytes("UTF-8");
		len=jsonByte.length;
		System.out.println("发的数据长度为:"+len);
		System.out.println("发的数据为:"+jsonByte);
		st=object.toString();
		
	}
	
	

}
