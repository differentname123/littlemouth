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
		object.put("string","����");
		//object.put("byte",data1);
		object.put("int",2);  
		object.put("boolean",true);
		
	
		System.out.println("������"+object);
		jsonByte=object.toString().getBytes("UTF-8");
		len=jsonByte.length;
		System.out.println("�������ݳ���Ϊ:"+len);
		System.out.println("��������Ϊ:"+jsonByte);
		st=object.toString();
		
	}
	
	

}
