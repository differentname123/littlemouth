package com.anxpp.io.calculator.bio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import net.sf.json.JSONObject;

public class filecaozuo {
	public filecaozuo() {
		System.out.print("进入");
	}
	public String register(String path, String fileName,JSONObject JSON) throws Exception {
		
		File f = new File("./"+path);
		if (!f.exists()) {
			f.mkdirs();
			System.out.print("创建文件夹");
		}
		File file = new File("./"+path+"/"+fileName);
		System.out.println("路径：" + file.getAbsolutePath());
		if(!file.exists()) {
			file.createNewFile();
			System.out.println("创建文件成功");
		}
		
		System.out.print("进入账号比对结果");
		
		
			
			FileWriter fw = new FileWriter(file,false);
			fw.write(JSON.toString()+"\r\n");
			fw.close();
			return "true";
		
		
		

	}
	
	public static void writeFile(String path, String fileName, byte[] content) throws IOException {
		try {
			File f = new File("./"+path);
			if (!f.exists()) {
				f.mkdirs();
				System.out.print("创建文件夹");
			}
			FileOutputStream fos = new FileOutputStream("./"+path +"/"+ fileName);
			fos.write(content);
			fos.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	
public JSONObject tongzhi() throws Exception {
		
		
	
		JSONObject js1 = null;
		String str="./"+"shuju"+".txt";
		BufferedReader br = new BufferedReader(new FileReader(str));
		String line;
		while((line = br.readLine()) != null)
		{
			js1 = JSONObject.fromObject(line);
			
			//System.out.println(line);
		}
		br.close();
		return js1;
		
}


	public JSONObject duqu(String path, String fileName,String n) throws Exception {
		
		
		File file = new File("./"+path);
		File[] files = null;
		int xx=Integer.valueOf(fileName).intValue();//偏移的时间
		int yy;
		int i ;

		// 如果这个路径是文件夹

		if (file.isDirectory()) 
		{
			
			files = file.listFiles();
		}
		for (i = files.length-1; i>=0; i--) {
			System.out.println("目录：" + files[i].getName());
			yy=Integer.valueOf(files[i].getName()).intValue();
			if(yy<xx)
				break;
		
		}
		JSONObject js1 = null;
		String str="./"+path+"/"+files[i].getName()+"/"+n+".txt";
		BufferedReader br = new BufferedReader(new FileReader(str));
		String line;
		while((line = br.readLine()) != null)
		{
			js1 = JSONObject.fromObject(line);
			
			//System.out.println(line);
		}
		br.close();
		return js1;
		
	}
	
	public JSONObject duqu1(String path, String fileName,String n) throws Exception {
		
		
		File file = new File("./"+path);
		File[] files = null;
		int xx=Integer.valueOf(fileName).intValue();//偏移的时间
		int yy;
		int i ;

		// 如果这个路径是文件夹

		if (file.isDirectory()) 
		{
			
			files = file.listFiles();
		}
		
		for (i = 0; i<files.length; i++) {
			System.out.println("目录：" + files[i].getName());
			yy=Integer.valueOf(files[i].getName()).intValue();
		
			if(yy>xx)
				break;
		
		}
		JSONObject js1 = null;
		String str="./"+path+"/"+files[i].getName()+"/"+n+".txt";
		System.out.println("txt路径：" + str);
		BufferedReader br = new BufferedReader(new FileReader(str));
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
