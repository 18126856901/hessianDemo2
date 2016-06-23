package com.qijiabin.serializable;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

/**
 * ========================================================
 * 日 期：2016年6月23日 下午2:52:33
 * 作 者：qijiabin
 * 版 本：1.0.0
 * 类说明：hessian2序列化与反序列化测试
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 */
public class Client {

	public static String urlName = "http://localhost:8080/hessianDemo2/PostDataServlet";

	public static void main(String[] args) throws Throwable {

		// 序列化
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Hessian2Output h2o = new Hessian2Output(os);

		h2o.startMessage();
		h2o.writeObject(getPerson());
		h2o.writeString("I am client.");
		h2o.completeMessage();
		h2o.close();

		byte[] buffer = os.toByteArray();
		os.close();

		ByteArrayEntity byteArrayEntity = new ByteArrayEntity(buffer, ContentType.create("x-application/hessian", "UTF-8"));

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(urlName);
		post.setEntity(byteArrayEntity);
		CloseableHttpResponse response = client.execute(post);

		System.out.println("response status:\n" + response.getStatusLine().getStatusCode());
		HttpEntity body = response.getEntity();
		InputStream is = body.getContent();
		Hessian2Input h2i = new Hessian2Input(is);
		h2i.startMessage();

		Person person = (Person) h2i.readObject();
		System.out.println("response:\n" + person.toString());
		System.out.println(h2i.readString());

		h2i.completeMessage();
		h2i.close();
		is.close();
	}

	public static Person getPerson() {
		Person person = new Person();
		person.setAddress(new String[] { "Beijing", "TaiWan", "GuangZhou" });
		person.setBrithday(new Date());
		person.setGender(false);
		person.setHeight(168.5D);
		person.setId(300);
		person.setName("Jack");
		person.setPhone(188888888);
		person.setWeight(55.2F);
		return person;
	}

}
