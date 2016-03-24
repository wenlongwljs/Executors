package com.wenlong.testexecutor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
/**
 * 这里是所有执行器的超类
 * @author mwlbj
 *
 */
public class Controller {
	public ExecutorService executor;
	
	public Future<List<String>> execMethod(String deptId,List<String> dates){
		return executor.submit(new MyCallable(deptId,dates));
	}

}

