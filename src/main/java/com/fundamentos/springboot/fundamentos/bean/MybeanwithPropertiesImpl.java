package com.fundamentos.springboot.fundamentos.bean;

public class MybeanwithPropertiesImpl implements MybeanwithProperties {

	private String nombre;
	private String apellido;
	
	public MybeanwithPropertiesImpl(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
	}

	@Override
	public String function() {
		// TODO Auto-generated method stub
		return nombre + "-" + apellido;
	}

}
