package com.fundamentos.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImpl implements MyBeanWithDependency {
	
	Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImpl.class);
	
	MyOperation myOperation;
	
	public MyBeanWithDependencyImpl(MyOperation myOperation) {
		this.myOperation = myOperation;
	}

	@Override
	public void printWithDependency() {
		LOGGER.info("Hemos ingresado al metodo printWithDependency");
		int numero = 1;
		LOGGER.debug("El numero enviado como paramentro a la depencia operacion es: " + numero);
		System.out.println(myOperation.sum(numero));
		System.out.println("Hola desde la implementacion de un bean con dependencia");
	}

}
