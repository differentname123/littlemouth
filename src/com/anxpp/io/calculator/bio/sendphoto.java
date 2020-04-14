package com.anxpp.io.calculator.bio;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

public class sendphoto {
	
	private Socket socket;
	public sendphoto(Socket socket) {
		this.socket = socket;
	}

	public void send(String str){
		//·¢ËÍ×Ö·û´®
		try {
			
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write((str + "\n").getBytes());
			outputStream.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}
	
	public void sendjson(){
		//·¢ËÍÍ¼Æ¬
		DataOutputStream dos;
		try {
			date da=new date();
			dos = new DataOutputStream(socket.getOutputStream());
			//FileInputStream fis = new FileInputStream(str);
	        //int size = fis.available();
			//System.out.println("size = " + size);
	      
	        System.out.println("ÀàÈÝ = " + da.jsonByte);
	        
	      //  dos.writeInt(da.len);
	        dos.writeUTF(da.st);
	       // dos.write(da.object.toString());
	        dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}
	
	
	
	
	
	
}
