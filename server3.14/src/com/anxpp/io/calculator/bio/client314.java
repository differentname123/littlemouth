package com.anxpp.io.calculator.bio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * 阻塞式I/O创建的客户端
 * @author yangtao__anxpp.com
 * @version 1.0
 */
public class client314 {
	//默认的端口号
	private static int DEFAULT_SERVER_PORT = 12345;
	private static String DEFAULT_SERVER_IP = "127.0.0.1";
	public static void send(String expression){
		send(DEFAULT_SERVER_PORT,expression);
	}
	public static void send(int port,String expression){
		System.out.println("算术表达式为：" + expression);
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try{
			socket = new Socket(DEFAULT_SERVER_IP,port);
			//应该在这加一个判断语句
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			out.println(expression);
			System.out.println("___结果为：" + in.readLine());
		}catch(Exception e){
			System.out.println("未连接到服务器");
			//e.printStackTrace();
		}finally{
			//一下必要的清理工作
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