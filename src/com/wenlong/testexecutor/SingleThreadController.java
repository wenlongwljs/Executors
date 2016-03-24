package com.wenlong.testexecutor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * 这里采用newSingleThreadExecutor作为线程池，每次都只能放一个线程。
 * 所以站在集团公司的角度看 二级公司是 有序执行
 * @author mwlbj
 *
 */
public class SingleThreadController extends Controller{
	public SingleThreadController(){
		//这里实例化父类Controller的executor
		this.executor = Executors.newSingleThreadExecutor();
	}
	
	public static void main(String[] args) {
		SingleThreadController con = new SingleThreadController();
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
		Future<List<String>> future1 = con.execMethod(String.valueOf(1), dates);
		Future<List<String>> future2 = con.execMethod(String.valueOf(2), dates);
		Future<List<String>> future3 = con.execMethod(String.valueOf(3), dates);
		Future<List<String>> future4 = con.execMethod(String.valueOf(4), dates);
		Future<List<String>> future5 = con.execMethod(String.valueOf(5), dates);
		
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
