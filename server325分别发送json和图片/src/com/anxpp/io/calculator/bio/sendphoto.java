package com.anxpp.io.calculator.bio;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class sendphoto {
	
	private Socket socket;
	public sendphoto(Socket socket) {
		this.socket = socket;
	}

	public void send(String str){
		//∑¢ÀÕÕº∆¨
		DataOutputStream dos;
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			FileInputStream fis = new FileInputStream(str);
	        int size = fis.available();
			System.out.println("size = " + size);
	        byte[] data = new byte[size];
	        fis.read(data);
	        System.out.println("size = " + data.toString());
	        dos.writeInt(size);
	        dos.write(data);
	        dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}
	
	public void sendjson(){
		//∑¢ÀÕÕº∆¨
		DataOutputStream dos;
		try {
			date da=new date();
			dos = new DataOutputStream(socket.getOutputStream());
			//FileInputStream fis = new FileInputStream(str);
	        //int size = fis.available();
			//System.out.println("size = " + size);
	      
	        System.out.println("¿‡»› = " + da.jsonByte);
	        
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
