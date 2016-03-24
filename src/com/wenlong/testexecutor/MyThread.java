package com.wenlong.testexecutor;


/**
 * 这里代表的是一个公司的一个需要修改的日期的执行器
 * @author mwlbj
 *
 */
public class MyThread implements Runnable{
	//二级公司编号
	private String deptId;
	//执行日期
	private String date;
	
	public MyThread() {
		super();
	}

	public MyThread(String deptId, String date) {
		super();
		this.deptId = deptId;
		this.date = date;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public void run() {
		//执行处理任务
		new MyProcess().process();
		System.out.println("--[MyThread][执行完成]部门编号："+this.deptId +"执行日期:"+this.date);
	}
}
