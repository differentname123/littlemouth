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
				System.out.println("这是登录的命令"+js);
				photosend.sendstr(CheckDenglu(js));
				break;
				
				
			case "zhuce":
				System.out.println("这是注册的命令"+js);
				photosend.sendstr(register(js));

				break;
				
			case "page1":
				System.out.println("这是page1的命令"+js);
				if(js.getString("type").equals("txt")) {
					
					
					if(js.getString("item").equals("-1")) {
						photosend.tcprecievejson();
						
						
					}else {
						filecaozuo fi=new filecaozuo();
						photosend.sendjson1(fi.duqu());
					}
				}else if(js.getString("type").equals("photo1")){
					
					System.out.println("接收图片");
					photosend.tcprecievephoto();
				}
				else {
					photosend.send("C:/Users/Administrator/Downloads/test.jpg");
				}
				
				
			 default:
	                break;
            }
		 System.out.println("接收的命令为："+js.getString("order"));
	}
	public String CheckDenglu(JSONObject JSON) throws Exception {
		String account;
		String password;
		String result="false";
		JSONObject js1;
		
		
		account=JSON.getString("account");
		password=JSON.getString("password");
		
		BufferedReader br = new BufferedReader(new FileReader("./data.txt"));
		String line;
		while((line = br.readLine()) != null)
		{
			js1 = JSONObject.fromObject(line);
			if(js1.get("account").equals(account)&&js1.get("password").equals(password)) {
				result="true";
				break;
				
			}
			//System.out.println(line);
		}
		br.close();
		return result;
			
		
		
		
	}
	public String Isregister(String account,String password) throws Exception {
		String result ="No";
		JSONObject js1;
		BufferedReader br = new BufferedReader(new FileReader("./data.txt"));
		String line;
		while((line = br.readLine()) != null)
		{
			js1 = JSONObject.fromObject(line);
			if(js1.get("account").equals(account)) {
				result="Yes";
				break;
				
			}
			//System.out.println(line);
		}
		br.close();
		return result;
		
	}
	public String register(JSONObject JSON) throws Exception {
		String account;
		String password;
		String result;
		account=JSON.getString("account");
		password=JSON.getString("password");
		
		System.out.print("文件的创建");
		File file = new File("./data.txt");
		System.out.println("路径：" + file.getAbsolutePath());
		if(!file.exists()) {
			file.createNewFile();
			System.out.println("创建文件成功");
		}
		
		System.out.print("进入账号比对结果");
		result = Isregister(account,password);
		System.out.print("账号比对结果为"+result);
		if(result.equals("Yes")) {
			return "false";
		}else {
			
			FileWriter fw = new FileWriter(file,true);
			fw.write(JSON.toString()+"\n");
			fw.close();
			return "true";
		}
		
		
		
	
		
		
			
		
		
		
	}

}
