package com.anxpp.io.calculator.bio;
import java.io.IOException;
import java.util.Random;
public class test314 {
	//����������
	public static void main(String[] args) throws InterruptedException {
		//���з�����
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
		//����ͻ������ڷ���������ǰִ�д���
		Thread.sleep(100);
		
		//���пͻ��� 
	
	}
}