package com.wenlong.testexecutor;

import java.util.Random;

public class MyProcess {
	
	public void process(){
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
