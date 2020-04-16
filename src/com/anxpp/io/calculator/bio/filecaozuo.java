package com.anxpp.io.calculator.bio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import net.sf.json.JSONObject;

public class filecaozuo {
	public filecaozuo() {
		System.out.print("进入");
	}
	public String register(JSONObject JSON) throws Exception {
		
		File file = new File("./shuju.txt");
		System.out.println("路径：" + file.getAbsolutePath());
		if(!file.exists()) {
			file.createNewFile();
			System.out.println("创建文件成功");
		}
		
		System.out.print("进入账号比对结果");
		
		
			
			FileWriter fw = new FileWriter(file,true);
			fw.write(JSON.toString()+"\r\n");
			fw.close();
			return "true";
		
		
		

	}
	
	public JSONObject duqu() throws Exception {
		
		JSONObject js1 = null;
		BufferedReader br = new BufferedReader(new FileReader("./shuju.txt"));
		String line;
		while((line = br.readLine()) != null)
		{
			js1 = JSONObject.fromObject(line);
			
			//System.out.println(line);
		}
		br.close();
		return js1;
		
	}
	
	
}
