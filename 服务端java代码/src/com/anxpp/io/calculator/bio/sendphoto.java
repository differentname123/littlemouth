package com.anxpp.io.calculator.bio;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
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

	public void sendphoto(String part,String finame,String n){
		//����ͼƬ
		File[] files = null;
		File file = new File("./"+part);
		int xx=Integer.valueOf(finame).intValue();//ƫ�Ƶ�ʱ��
		int yy;
		int i ;

		// ������·�����ļ���

		if (file.isDirectory()) 
		{
			
			files = file.listFiles();
		}
		for (i = files.length-1; i>=0; i--) {
			System.out.println("Ŀ¼��" + files[i].getName());
			yy=Integer.valueOf(files[i].getName()).intValue();
			if(yy<xx)
				break;
		
		}
		JSONObject js1 = null;
		String str="./"+part+"/"+files[i].getName()+"/"+n+".jpg";
		File file1 = new File(str);
	
		if(!file1.exists()) {
			
			DataOutputStream dos;
			try {
				dos = new DataOutputStream(socket.getOutputStream());
				//FileInputStream fis = new FileInputStream(str);
		        int size = 0;
				System.out.println("size = " + size);
		        byte[] data = new byte[size];
		       // fis.read(data);
		       // System.out.println("size = " + data.toString());
		        dos.writeInt(size);
		        dos.write(data);
		        dos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else
		{
			System.out.println("����ͼƬΪ��" + str);
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
		
       
		
	}
	
	public void sendphoto1(String part,String finame,String n){
		//����ͼƬ
		File[] files = null;
		File file = new File("./"+part);
		int xx=Integer.valueOf(finame).intValue();//ƫ�Ƶ�ʱ��
		int yy;
		int i ;

		// ������·�����ļ���

		if (file.isDirectory()) 
		{
			
			files = file.listFiles();
		}
		for (i =0; i<files.length; i++) {
			System.out.println("Ŀ¼��" + files[i].getName());
			yy=Integer.valueOf(files[i].getName()).intValue();
			if(yy>xx)
				break;
		
		}
		JSONObject js1 = null;
		String str="./"+part+"/"+files[i].getName()+"/"+n+".jpg";
		System.out.println("����ͼƬΪ��" + str);
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
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}
	
	 public synchronized void tcprecievejson(String part,String finame,String n) throws Exception {
	        
	          
	        try {
	        	
	            while(socket==null);
	            System.out.println("��ǰsocketΪ��"+socket);
	            DataInputStream in = null;
	        	in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));//����json
	        	String expression = in.readUTF();
	            
	            /*
	            DataInputStream in;
	            String expression;
	            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	            expression=in.readUTF();*/
	            System.out.println("tcp������ϢΪ��"+expression);
	            JSONObject js =JSONObject.fromObject(expression);
	           filecaozuo fi = new filecaozuo();
	           fi.register(part+"/"+finame,n+".txt",js);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	            
	 }
	        

	    
	
	
	public void  tcprecievephoto(String part,String finame,String n) {

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
                    filecaozuo fi = new filecaozuo();
     	            fi.writeFile(part+"/"+finame,n+".jpg",data);
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
