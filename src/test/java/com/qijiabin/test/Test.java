package com.qijiabin.test;

import java.net.MalformedURLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.caucho.hessian.client.HessianProxyFactory;
import com.qijiabin.hessian.DemoApi;


/**
 * ========================================================
 * 日 期：2016年6月23日 上午11:27:00
 * 作 者：qijiabin
 * 版 本：1.0.0
 * 类说明：
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 */
public class Test {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		/**
		 * 调用方式一
		 * 不使用spring
		 */
		try {
			
			String url = "http://localhost:8080/hessianDemo2/remote/helloDemo";
			HessianProxyFactory proxy = new HessianProxyFactory();
			DemoApi api = (DemoApi) proxy.create(url);
			api.setName("test");
			System.out.println(api.sayHello());
			System.out.println(api.getUser());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		/**
		 * 调用方式二
		 * 使用spring
		 */
		ApplicationContext context = new ClassPathXmlApplicationContext("remote-servlet.xml");
        DemoApi da = (DemoApi) context.getBean("helloDemo");
        da.setName("test spring");
        System.out.println(da.sayHello());
        System.out.println(da.getUser());
	}
	
}
