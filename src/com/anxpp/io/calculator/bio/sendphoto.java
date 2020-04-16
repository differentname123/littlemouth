package com.anxpp.io.calculator.bio;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

import net.sf.json.JSONObject;

public class sendphoto {
	
	private Socket socket;
	public sendphoto(Socket socket) {
		this.socket = socket;
	}

	public void send(String str){
		//����ͼƬ
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
	public void sendstr(String str){
		//�����ַ���
		try {
			
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write((str + "\n").getBytes());
			outputStream.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}
	
	 public synchronized void tcprecievejson() throws Exception {
	        
	          
	        try {

	           
	            while(socket==null);
	            DataInputStream in;
	            String str;
	            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	            str=in.readUTF();
	            System.out.println("tcp������ϢΪ��"+str);
	            JSONObject js =JSONObject.fromObject(str);
	           filecaozuo fi = new filecaozuo();
	           fi.register(js);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	            
	 }
	        

	    
	
	
	public void  tcprecievephoto() {

        while(socket==null);
                    
                try {

                    
                    DataInputStream dataInput = new DataInputStream(socket.getInputStream());
                    int size = dataInput.readInt();
                    
                    byte[] data = new byte[size];
                    int len = 0;
                    while (len < size) {
                        len += dataInput.read(data, len, size - len);
                    }
                    dataInput.close();
                    
                    System.out.println("�������ݴ�С"+size+data[0]);
                 

                } catch (IOException e) {
                    e.printStackTrace();
                }


    

            }
	
	public void sendjson(){
		//����ͼƬ
		DataOutputStream dos;
		try {
			date da=new date();
			dos = new DataOutputStream(socket.getOutputStream());
			//FileInputStream fis = new FileInputStream(str);
	        //int size = fis.available();
			//System.out.println("size = " + size);
	      
	        System.out.println("���� = " + da.jsonByte);
	        
	      //  dos.writeInt(da.len);
	        dos.writeUTF(da.st);
	       // dos.write(da.object.toString());
	        dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}
	
	public void sendjson1(JSONObject ob){
		//����ͼƬ
		DataOutputStream dos;
		try {
			dos = new DataOutputStream(socket.getOutputStream());
	        System.out.println("���͵�JSONΪ " + ob);
	        
	      //  dos.writeInt(da.len);
	        dos.writeUTF(ob.toString());
	       // dos.write(da.object.toString());
	        dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}
	
	
	
	
}
