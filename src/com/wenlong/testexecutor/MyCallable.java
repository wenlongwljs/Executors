package com.wenlong.testexecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 这里代表的就是提交的一个公司的所有需要修改的日期执行器
 * 采用的是newFixedThreadPool可以存放多个线程的线程池，更具传进来的daes的size决定。
 * 所以站在二级公司的角度看 他自己的 日期执行情况 是 开始时间 有序 结束时间无序 ，这里也反应了多线程同时处理问题的一个特性。
 * @author mwlbj
 *
 */
public class MyCallable implements Callable<List<String>>{
	private String deptId;
	private List<String> dates;
	
	public MyCallable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyCallable(String deptId, List<String> dates) {
		super();
		this.deptId = deptId;
		this.dates = dates;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public List<String> getDates() {
		return dates;
	}

	public void setDates(List<String> dates) {
		this.dates = dates;
	}

	@Override
	public List<String> call() throws Exception {
		System.out.println("");
		System.out.println("[MyCallable]执行部门编号："+this.deptId);
		if(dates != null && dates.size() > 0){
			List<String> errorList = new ArrayList<String>();
			ExecutorService executor = Executors.newFixedThreadPool(dates.size());
			for(String date : dates){
				try{
					Random random = new Random();
					Thread.sleep(random.nextInt(500));
					executor.submit(new MyThread(this.deptId,date));
				}catch (Exception e) {
					errorList.add(date);
					continue;
				}
			}
			executor.shutdown();
			while(true){
				if(executor.isTerminated()){
					return errorList;
				}
				Thread.sleep(500);
			}
		}
		return null;
	}

}
