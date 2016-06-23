package com.qijiabin.hessian;

import java.io.Serializable;

/**
 * ========================================================
 * 日 期：2016年6月23日 上午10:35:04
 * 作 者：qijiabin
 * 版 本：1.0.0
 * 类说明：
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 */
public class User implements Serializable {

	private static final long serialVersionUID = -2289097676791726440L;
	
	private String name = "root";
	private String pass = "root";
	
	
	public User(String name, String pass) {
		super();
		this.name = name;
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", pass=" + pass + "]";
	}
}
