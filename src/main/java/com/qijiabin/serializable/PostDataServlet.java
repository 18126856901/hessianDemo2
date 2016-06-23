package com.qijiabin.serializable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

/**
 * ========================================================
 * 日 期：2016年6月23日 下午2:48:21
 * 作 者：qijiabin
 * 版 本：1.0.0
 * 类说明：hessian2序列化与反序列化测试
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 */
@SuppressWarnings("serial")
public class PostDataServlet extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 处理请求
		ServletInputStream sis = req.getInputStream();
		Hessian2Input h2i = new Hessian2Input(sis);

		h2i.startMessage();
		Person person = (Person) h2i.readObject();
		System.out.println("receive:\n" + person.toString());
		System.out.println(h2i.readString());
		h2i.completeMessage();
		h2i.close();
		sis.close();

		// 发送响应
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("x-application/hessian");

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Hessian2Output h2o = new Hessian2Output(bos);

		h2o.startMessage();
		h2o.writeObject(getPerson());
		h2o.writeString("I am server.");
		h2o.completeMessage();
		h2o.close();

		ServletOutputStream sos = resp.getOutputStream();
		sos.write(bos.toByteArray());
		sos.flush();
		bos.close();
		sos.close();

	}

	private static Person getPerson() {
		Person person = new Person();
		person.setAddress(new String[] { "ShangHai", "ShenZhen", "ChengDu" });
		person.setBrithday(new Date());
		person.setGender(true);
		person.setHeight(178.5D);
		person.setId(301);
		person.setName("Tom");
		person.setPhone(188218888);
		person.setWeight(55.2F);
		return person;
	}
	
}

