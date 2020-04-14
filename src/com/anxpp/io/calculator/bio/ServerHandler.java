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
 * 客户端线程
 * @author yangtao__anxpp.com
 * 用于处理一个客户端的Socket链路
 */
public class ServerHandler implements Runnable{
	private Socket socket;
	private RequestResponse request;
	
	
	private sendphoto photosend;
	public ServerHandler(Socket socket) {
		this.socket = socket;
		System.out.println("进入：");
	}
	@Override
	public void run() {
		 
		//System.out.println("进入1连接："+socket);
		//BufferedReader in = null;//接收字符串
		PrintWriter out = null;//接收json
		DataInputStream in = null;
		
		try{
			//System.out.println("进入连接："+socket);
			photosend=new sendphoto(socket);
			System.out.println("进入连接：");
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));//接收json
			//in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//接收字符串
			out = new PrintWriter(socket.getOutputStream(),true);
			String expression;
			while(true){
				
				
				//通过BufferedReader读取一行
				//如果已经读到输入流尾部，返回null,退出循环
				//如果得到非空值，就尝试计算结果并返回
				if((expression = in.readUTF())==null){
					System.out.println("断开连接：");
					break;
				}
				request = new RequestResponse(expression,socket);
				request.DealRequest();
				/*
				if(expression.equals("01"))
				{
					System.out.println("服务器收到消息：\n" + expression);
					photosend.sendjson();
					
					System.out.println("发送json成功");
				
					
				
					
				}
				else{
					
					photosend.send("C:/Users/Hsiaohuchu/Downloads/test.jpg");
					System.out.println("服务器收到消息：\n" + expression);
					System.out.println("发送图片成功");
				}
				 */
	            System.out.println("结束发送：");
	       
			}
		}catch(Exception e){
			//e.printStackTrace()这里会打印错误信息
		}finally{
			//一些必要的清理工作
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