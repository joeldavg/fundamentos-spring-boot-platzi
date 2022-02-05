package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanImpl implements MyBean {

	@Override
	public void print() {
		System.out.println("Hola desde my impl propia del bean");
	}

}
