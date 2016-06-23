package com.qijiabin.hessian;

/**
 * ========================================================
 * 日 期：2016年6月23日 上午10:36:22
 * 作 者：qijiabin
 * 版 本：1.0.0
 * 类说明：
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 */
public class DemoService implements DemoApi {
	
	private String name;

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String sayHello() {
		String result = "hello : " + name;
		System.out.println(result);
		return result;
	}

	@Override
	public User getUser() {
		return new User("tom", "123");
	}

}
