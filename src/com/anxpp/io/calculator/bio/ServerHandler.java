package com.anxpp.io.calculator.bio;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
 
import com.anxpp.io.utils.Calculator;
/**
 * �ͻ����߳�
 * @author yangtao__anxpp.com
 * ���ڴ���һ���ͻ��˵�Socket��·
 */
public class ServerHandler implements Runnable{
	private Socket socket;
	private RequestResponse request;
	
	
	private sendphoto photosend;
	public ServerHandler(Socket socket) {
		this.socket = socket;
		System.out.println("���룺");
	}
	@Override
	public void run() {
		 
		//System.out.println("����1���ӣ�"+socket);
		//BufferedReader in = null;//�����ַ���
		PrintWriter out = null;//����json
		DataInputStream in = null;
		
		try{
			//System.out.println("�������ӣ�"+socket);
			photosend=new sendphoto(socket);
			System.out.println("�������ӣ�");
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));//����json
			//in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//�����ַ���
			out = new PrintWriter(socket.getOutputStream(),true);
			String expression;
			while(true){
				
				
				//ͨ��BufferedReader��ȡһ��
				//����Ѿ�����������β��������null,�˳�ѭ��
				//����õ��ǿ�ֵ���ͳ��Լ�����������
				if((expression = in.readUTF())==null){
					System.out.println("�Ͽ����ӣ�");
					break;
				}
				request = new RequestResponse(expression,socket);
				request.DealRequest();
				/*
				if(expression.equals("01"))
				{
					System.out.println("�������յ���Ϣ��\n" + expression);
					photosend.sendjson();
					
					System.out.println("����json�ɹ�");
				
					
				
					
				}
				else{
					
					photosend.send("C:/Users/Hsiaohuchu/Downloads/test.jpg");
					System.out.println("�������յ���Ϣ��\n" + expression);
					System.out.println("����ͼƬ�ɹ�");
				}
				 */
	            System.out.println("�������ͣ�");
	       
			}
		}catch(Exception e){
			//e.printStackTrace()������ӡ������Ϣ
		}finally{
			//һЩ��Ҫ��������
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in = null;
			}
			if(out != null){
				out.close();
				out = null;
			}
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				socket = null;
			}
		}
	}

}