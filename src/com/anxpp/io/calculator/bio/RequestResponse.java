package com.anxpp.io.calculator.bio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

import net.sf.json.JSONObject;

public class RequestResponse {
	
	private String expression;
	private JSONObject js;
	private Socket socket;
	private sendphoto photosend;
	public RequestResponse(String str,Socket socket) {
		this.expression=str;
		this.socket=socket;
		js =JSONObject.fromObject(str);
		photosend = new sendphoto(socket);
	}
	public void DealRequest() throws Exception {
		switch (js.getString("order")) {
			case "denglu":
				System.out.println("���ǵ�¼������"+js);
				photosend.send(CheckDenglu(js));
				break;
				
				
			case "zhuce":
				System.out.println("����ע�������"+js);
				register(js);

				break;
				
				
			 default:
	                break;
            }
		 System.out.println("���յ�����Ϊ��"+js.getString("order"));
	}
	public String CheckDenglu(JSONObject JSON) throws Exception {
		String account;
		String password;
		File file = new File("./data.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String tempString = null;  
		int line = 1;  
		// һ�ζ���һ�У�ֱ������nullΪ�ļ�����  
		while ((tempString = reader.readLine()) != null) {  
		    // ��ʾ�к�  
		    System.out.println("line " + line + ": " + tempString);  
		    line++;  
		}  
		reader.close();  

		
		account=JSON.getString("account");
		password=JSON.getString("password");
		
		if(account.equals("hsiaohuchu")&&password.equals("123456"))	{
			return "true";
		}else{
			return "false";
		}
			
		
		
		
	}
	public String register(JSONObject JSON) throws IOException {
		String account;
		String password;
		File file = new File("./data.txt");
		if(!file.exists()) {
			file.createNewFile();
			System.out.println("�����ļ��ɹ�");
		}
		FileWriter fw = new FileWriter(file,true);
		fw.write(JSON.toString()+"\n");
		fw.close();
		account=JSON.getString("account");
		password=JSON.getString("password");
		
		if(account.equals("hsiaohuchu")&&password.equals("123456"))	{
			return "true";
		}else{
			return "false";
		}
			
		
		
		
	}

}
