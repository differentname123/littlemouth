package com.anxpp.io.calculator.bio;
import java.io.IOException;
import java.util.Random;
public class test314 {
	//测试主方法
	public static void main(String[] args) throws InterruptedException {
		//运行服务器
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ServerBetter.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		//避免客户端先于服务器启动前执行代码
		Thread.sleep(100);
		
		//运行客户端 
	
	}
}