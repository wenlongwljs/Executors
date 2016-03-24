package com.wenlong.testexecutor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
/**
 * 这里采用newFixedThreadPool作为线程池，每次可以存放多个线程。
 * 所以站在集团公司的角度看 二级公司是 无序的
 * @author mwlbj
 *
 */
public class FixedThreadController extends Controller{
	public FixedThreadController(){
		//这里实例化父类Controller的executor
		this.executor = Executors.newFixedThreadPool(5);
	}
	
	public static void main(String[] args) {
		FixedThreadController con = new FixedThreadController();
		List<String> dates = new ArrayList<String>();
		dates.add("2015-01-01");
		dates.add("2015-01-02");
		dates.add("2015-01-03");
		dates.add("2015-01-04");
		dates.add("2015-01-05");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long stime = System.currentTimeMillis();
		System.out.println(format.format(stime));
		
		//注意这里是调用的父类Controller的execMethod方法
		con.execMethod(String.valueOf(1), dates);
		con.execMethod(String.valueOf(2), dates);
		con.execMethod(String.valueOf(3), dates);
		con.execMethod(String.valueOf(4), dates);
		con.execMethod(String.valueOf(5), dates);
		
		con.executor.shutdown();
		while(true){
			if(con.executor.isTerminated()){
				long etime = System.currentTimeMillis();
				System.out.println(format.format(etime));
				format = new SimpleDateFormat("mm:ss");
				System.out.println("用时：" + (etime - stime) / 1000 / 60 / 60 + ":" + (etime - stime) / 1000 / 60 + ":" + (etime - stime) / 1000);
				return;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
